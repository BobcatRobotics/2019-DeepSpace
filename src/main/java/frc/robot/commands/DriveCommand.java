/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.OI;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;

/**
 * The base command for Driving
 */
public abstract class DriveCommand extends Command {
	protected double distanceToDrive = 0.0;
	protected double intialLeftPower = 0.0;
	protected double intialRightPower = 0.0;

	// Used to correct driving path. make the robot drive straight
	private static final double INCREASE_CORRECTION = 1.05;
	private static final double DECREASE_CORRECTION = 0.95;
	private double prevLeftDistance = 0.0;
	private double prevRightDistance = 0.0;

	// Used to gracefully ramp up the speed of the robot
	private static final double RAMP_CTR_MAX = 25; // max nbr of execute() loops
	private double rampScaleFactor = 0.0;
	private double rampCounter = 0.0;

	// used to gracefully ramp down the speed of the robot
	private static final double STOP_RAMP_DISTANCE = 20.0; // how far away to ramp down
	private double averageDistance;
	private double distanceRemaining;
	private double distanceScale;
	
	
	public DriveCommand() {
		requires(OI.driveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		OI.driveTrain.reset();
		rampScaleFactor = 0.0;
		rampCounter = 0.0;
}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}

	// Called repeatedly when this Command is scheduled to run
	abstract protected  void execute(); 

	// Make this return true when this Command no longer needs to run execute()
	abstract protected boolean isFinished();

	// Called once after isFinished returns true
	abstract protected void end();
	
	protected void adjustDriveStraight() {
		double ldist = OI.driveTrain.getLeftDistance();
		double rdist = OI.driveTrain.getRightDistance();
		double leftPower = OI.driveTrain.getLeftPower();
		double rightPower = OI.driveTrain.getRightPower();
 		//logger.log(format(ldist,rdist,leftPower,rightPower));
			
		double leftdiff  = ldist - prevLeftDistance;
		prevLeftDistance = ldist;
		double rightdiff = rdist - prevRightDistance;
		prevRightDistance = rdist;
		
		double ldistChk = Math.abs(leftdiff);
		double rdistChk = Math.abs(rightdiff);
		// XXXXXXXX Do we need a dead band
//		if (Math.abs(ldistChk - rdistChk) < deadBandRange) {
//			return;
//		}
			
		if (ldistChk > rdistChk) {
			rightPower *= INCREASE_CORRECTION;
			leftPower *= DECREASE_CORRECTION;
		} else 
   		if (ldistChk < rdistChk) {
			leftPower *= INCREASE_CORRECTION;
			rightPower *= DECREASE_CORRECTION;
   		}  	
		
		OI.driveTrain.setLeftPower(leftPower);
		OI.driveTrain.setRightPower(rightPower);
	}
	
	// returns the scaling factor for a smooth ramp up to speed
	protected double rampUpFactor() {
		if (rampCounter <= RAMP_CTR_MAX) {
			rampScaleFactor = rampCounter/RAMP_CTR_MAX;
			rampCounter += 1.0;
		}
		if (rampScaleFactor > 1.0) {rampScaleFactor=1.0;} // Limit ramp scale factor to be no larger than 1.0;
		if (rampScaleFactor < 0.0) {rampScaleFactor=0.0;} // Limit ramp scale factor to be no lower than 0.0;
		
		return rampScaleFactor;
	}
	
	// returns the scaling factor for a smooth ramp down, based on distance 
	protected double rampDownFactor() {
		// As the robot is getting closer to the desired distance, ramp
		// the power to the drive train down.
		// Average left and right side distances to get robot distance
		averageDistance = (OI.driveTrain.getLeftDistance() + OI.driveTrain.getRightDistance())/2.0;
		
		// How much distance is left to travel?
		distanceRemaining = distanceToDrive - averageDistance;
		
		// As the remaining distance approaches the distanceToDrive ramp down the motor power
		// starting to ramp when within the STOP_RAMP_DISTANCE.
		distanceScale = distanceRemaining/STOP_RAMP_DISTANCE;
		if (distanceScale > 1.0) {distanceScale=1.0;} // Limit distance scale factor to be no larger than 1.0;
		if (distanceScale < 0.0) {distanceScale=0.0;} // Limit distance scale factor to be no lower than 0.0;
		
		return distanceScale;
	}
	
}
