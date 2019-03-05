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

import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Scalar;
import org.opencv.core.Size;
//import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

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

  Thread m_visionThread;

  // Command m_autonomousCommand;
  // SendableChooser<Command> m_chooser = new SendableChooser<>();
  Command m_DriveWithJoysticks;
  Command m_MoveElevator;

  @Override
  public void robotInit() {
    m_DriveWithJoysticks = new DriveWithJoysticks();
    m_MoveElevator = new MoveElevator();

    m_visionThread = new Thread(() -> {
      // Get the UsbCamera from CameraServer
      UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
      // Set the resolution
      camera.setResolution(320,240);
      camera.setFPS(30);

      // Get a CvSink. This will capture Mats from the camera
      CvSink cvSink = CameraServer.getInstance().getVideo();
      // Setup a CvSource. This will send images back to the Dashboard
      CvSource outputStream
          = CameraServer.getInstance().putVideo("Rectangle", 320, 240);
        //  outputStream.setFPS(30);

      // Mats are very memory expensive. Lets reuse this Mat.
      Mat mat = new Mat();
      Mat mat2 = new Mat();
      Size scaleSize = new Size(320,240);
      //Rect roi = new Rect(10,5,310,235);

      // This cannot be 'true'. The program will never exit if it is. This
      // lets the robot stop this thread when restarting robot code or
      // deploying.
      while (!Thread.interrupted()) {
        
        // Tell the CvSink to grab a frame from the camera and put it
        // in the source mat.  If there is an error notify the output.
        if (cvSink.grabFrame(mat) == 0) {
          // Send the output the error.
          outputStream.notifyError(cvSink.getError());
          // skip the rest of the current iteration
          continue;
        }
        
        // Convert the size of the image (again hoping to save bandwith)
        Imgproc.resize(mat, mat2, scaleSize, 0, 0, Imgproc.INTER_NEAREST);
        
        // Try to select a subset of the image
        // Mat mat2 = new Mat(mat,roi);
        // mat(roi).copyTo(mat2);

        // Put a rectangle on the image
        //Imgproc.rectangle(mat, new Point(100, 100), new Point(200, 200),
        //    new Scalar(255, 255, 255), 5);
        
        // Give the output stream a new image to display
        outputStream.putFrame(mat2);
        //mat2.release();
        
        // Try to lower CPU usage
        
        try {
           Thread.sleep(20);
        }
        catch (InterruptedException ie) {
          System.out.println(ie);
        }
        
      }
      
    });
    m_visionThread.setDaemon(true);
    m_visionThread.start();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
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
    if (!commandsStarted) {
      m_DriveWithJoysticks.start();
      m_MoveElevator.start();

      commandsStarted = true;
    }
  }
}
