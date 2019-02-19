package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.lib.RioLogger;
import frc.robot.lib.RioLoggerThread;
import frc.robot.lib.SmartDashLog;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  // Loggers
  public static RioLoggerThread logFile;
  public static SmartDashLog smartLog = new SmartDashLog();

  // Drive Chain Subsystem
  public static DriveTrain driveTrain = new DriveTrain();

  
  //Wrist Subsystem
  public static Wrist wrist = new Wrist();

  // Joysticks
  public static Joystick rightStick = new Joystick(RobotMap.rightJoystick);
  public static Joystick leftStick = new Joystick(RobotMap.leftJoystick);
  public static Joystick gamePad = new Joystick(RobotMap.gamePad);

  //Solenoids
  public static Solenoid shifter = new Solenoid(RobotMap.shiftSolenoid);

  //Elevator
  public static Elevator elev1 = new Elevator();
  public static boolean limitOn = false;

  //Panel Intake
  public static PanelIntake panel = new PanelIntake();

  //Cargo Roller
  public static CargoRoller cargo = new CargoRoller();

  //Peg leg
  public static PegLeg pegleg = new PegLeg();

  //Lock
  public static Lock lock = new Lock();


  // Buttons
  public static Button btnRollerIn = new JoystickButton(gamePad, RobotMap.cargoInB);
  public static Button btnRollerOut = new JoystickButton(gamePad, RobotMap.cargoOutB);
  public static Button btnPanelIn = new JoystickButton(gamePad, RobotMap.panelInB);
  public static Button btnPanelOut = new JoystickButton(gamePad, RobotMap.panelOutB);
  public static Button btnWristStow = new JoystickButton(gamePad, RobotMap.wristStowB);
  public static Button btnWristDep = new JoystickButton(gamePad, RobotMap.wristDepB);
  public static Button btnWristDel = new JoystickButton(gamePad, RobotMap.wristDelB);
  public static Button btnLock = new JoystickButton(gamePad, RobotMap.lockB);
  //public static Button btnUnlock = new JoystickButton(gamePad, RobotMap.unlockB);

  // Triggers
  public static Trigger trigShifter = new JoystickButton(rightStick, RobotMap.stickShift);

  static {
   
    // Start Logging Thread
    logFile = RioLoggerThread.getInstance();
    RioLogger.log("OI static block finished.");

    trigShifter.whenActive(new ShiftHigh());
    trigShifter.whenInactive(new ShiftLow());
    btnRollerIn.whileHeld(new CargoRollerIn());
    btnRollerOut.whileHeld(new CargoRollerOut());
    btnPanelIn.whenPressed(new PanelIntakeIn());
    btnPanelOut.whenPressed(new PanelIntakeOut());
    btnWristStow.whenPressed(new WristStowed());
    btnWristDep.whenPressed(new WristDeployed());
    btnWristDel.whenPressed(new WristDeliever());
    btnLock.whenPressed(new LockEnable());
    btnLock.whenPressed(new LegDeploy());
    //btnUnlock.whenPressed(new LockDisable());
    //btnUnlock.whenPressed(new LegRetract());
  }
}
