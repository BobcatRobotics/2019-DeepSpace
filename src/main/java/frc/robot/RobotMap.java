package frc.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // Drive  Train
	public static int driveRightMotorFront = 0;
	public static int driveRightMotorMiddle = 1;
	public static int driveRightMotorRear = 2;
	public static int driveLeftMotorFront = 3;
	public static int driveLeftMotorMiddle = 4;
	public static int driveLeftMotorRear = 5;
	
	// Drive Train Encoders
	public static int leftEncoderChannel1 = 2;
	public static int leftEncoderChannel2 = 3;
	public static int rightEncoderChannel1 = 0;
	public static int rightEncoderChannel2 = 1;

 	// Joy Sticks
	public static int leftJoystick = 0;
	public static int rightJoystick = 1;
	public static int gamePad = 2;
	public static int stickShift = 3;
	
	//Shifter
	public static int shiftSolenoid = 0;

	//Elev motors
	/*Update this to match wiring*/
	public static int elevMotor1 = 10;
	public static int elevMotor2 = 11;	
	public static int elevMotor3 = 12;
	public static int gamePadLeftPwrStick = 1;
	public static int gamePadRightPwrStick = 3; 

	
	//Elev limit Switches
	/*Update this to match wiring*/
	public static int elevLSwitchT = 0;
	public static int elevLSwitchB = 1;
	//Wrist
	/*Update this to match wiring*/
	public static int wristSolenoid1 = 5;
	public static int wristSolenoid2 = 7;
	
	//Lock
	public static int wristLockSol = 4;

	//Peg Leg
	public static int pegLegSol = 1;
	public static int pegLegMotor = 7;

	//Panel Intake
	public static int panelSolenoid = 6;

	//Cargo Roller
	public static int cargoMotor = 6;

	//Buttons
	public static int cargoInB = 6;
	public static int cargoOutB = 8;
	public static int panelInB = 5;
	public static int panelOutB = 7;
	public static int wristStowB = 4;
	public static int wristDepB = 2;
	public static int wristDelB = 3;
	public static int lockB = 1;
	public static int unlockB = 12;
}
