package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class DriveTrain extends Subsystem {
	/** Inverts drive direction **/
	private static final double INVERT_MOTOR = -1.0;
	
	private WPI_VictorSPX leftFront;
	private WPI_VictorSPX leftMiddle;
	private WPI_VictorSPX leftRear;
	private WPI_VictorSPX rightFront;
	private WPI_VictorSPX rightMiddle;
	private WPI_VictorSPX rightRear;
	private boolean invertLeft = true;
	private double leftPower = 0.0;
	private double rightPower = 0.0;

	public DriveTrain() {
		// Initialize Drive Train
		setRightMotors(RobotMap.driveRightMotorFront, RobotMap.driveRightMotorMiddle,RobotMap.driveRightMotorRear);
		setLeftMotors(RobotMap.driveLeftMotorFront, RobotMap.driveLeftMotorMiddle, RobotMap.driveLeftMotorRear);
		setLeftMotorsReverse(false);
		
	}
	
	
	// Put methods for controlling this subsystem here. Call these from Commands.
	public void setLeftMotors(int lf,int lm,int lr) {
		leftFront = new WPI_VictorSPX(lf);
		leftMiddle = new WPI_VictorSPX(lm);
		leftRear = new WPI_VictorSPX(lr);
	}
	
	public void setRightMotors(int rf,int rm,int rr) {
		rightFront = new WPI_VictorSPX(rf);
		rightMiddle = new WPI_VictorSPX(rm);
		rightRear = new WPI_VictorSPX(rr);
	}

	public void setLeftMotorsReverse(boolean invert) {
		invertLeft = invert;
	}
	
	

	public double getLeftPower() {
		return leftPower;
	}

	public void setLeftPower(double leftPwr) {
		if (leftPwr > 1.0)
			leftPwr = 1.0;
		else
		if (leftPwr < -1.0)
			leftPwr = -1.0;
			//was leftPwr = 1.0, we thought it should be -1.0
		this.leftPower = leftPwr;
	}

	public double getRightPower() {
		return rightPower;
	}

	public void setRightPower(double rightPwr) {
		if (rightPwr > 1.0)
			rightPwr = 1.0;
		else
		if (rightPwr < -1.0)
			rightPwr = -1.0;
			//was rightPwr = 1.0, we thought it should be -1.0
		this.rightPower = rightPwr;
	}

	public void drive() {
		drive(leftPower,rightPower);
	}
	
	public void drive(double leftPwr, double rightPwr) {
		if (invertLeft)
			leftPwr *= INVERT_MOTOR;
		else
			rightPwr *= INVERT_MOTOR;
		
		leftFront.set(leftPwr);
		leftMiddle.set(leftPwr);
		leftRear.set(leftPwr);
		rightFront.set(rightPwr);
		rightMiddle.set(rightPwr);
		rightRear.set(rightPwr);
	}

	public void stop() {
		leftPower = 0.0;
		rightPower = 0.0;
		leftFront.stopMotor();
		leftMiddle.stopMotor();
		leftRear.stopMotor();
		rightFront.stopMotor();
		rightMiddle.stopMotor();
		rightRear.stopMotor();;
	}

	public void reset() {
		leftPower = 0.0;
		rightPower = 0.0;
		
	}

	@Override
	public void initDefaultCommand() {
	  // Set the default command for a subsystem here.
	  // setDefaultCommand(new MySpecialCommand());
	}
}