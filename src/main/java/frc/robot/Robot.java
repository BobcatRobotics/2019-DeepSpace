/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.MoveElevator;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  static OI oi = new OI();
  static boolean commandsStarted = false;

  // Thread m_visionThread;

  // Command m_autonomousCommand;
  // SendableChooser<Command> m_chooser = new SendableChooser<>();
  Command m_DriveWithJoysticks;
  Command m_MoveElevator;

  @Override
  public void robotInit() {
    m_DriveWithJoysticks = new DriveWithJoysticks();
    m_MoveElevator = new MoveElevator();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
    // Set elevator motors to coast mode
    OI.elev1.setElevCoastMode();
  }

  @Override
  public void disabledPeriodic() {
    OI.elev1.getElevatorDistance();
    OI.elev1.getElevatorVelocity();
  }

  @Override
  public void autonomousInit() {
    startCommands();
   }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
 }

  @Override
  public void teleopInit() {
    startCommands();
   }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
 }

  // This function is called periodically during test mode.
  @Override
  public void testPeriodic() {
  }

  // Starts up all commands once 
  private void startCommands() {
    // Put elevator motors into brake mode
    OI.elev1.setElevBrakeMode();

    if (!commandsStarted) {
      m_DriveWithJoysticks.start();
      m_MoveElevator.start();

      commandsStarted = true;
    }
  }
}
