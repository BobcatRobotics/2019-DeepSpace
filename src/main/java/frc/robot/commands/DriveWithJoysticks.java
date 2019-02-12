/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.OI;

import edu.wpi.first.wpilibj.Joystick;

public class DriveWithJoysticks extends DriveCommand {
	
	public DriveWithJoysticks() {
		super();
	}

	@Override
	protected void execute() {
		// Driving
		double left = OI.leftStick.getRawAxis(Joystick.AxisType.kY.value);
		double right = OI.rightStick.getRawAxis(Joystick.AxisType.kY.value);
		//DriverStation.reportError("left stick value: " + left + " right stick value " + right, false);
		OI.driveTrain.setLeftPower(left);
		OI.driveTrain.setRightPower(right);
		OI.driveTrain.drive();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		OI.driveTrain.stop();
	}
}
