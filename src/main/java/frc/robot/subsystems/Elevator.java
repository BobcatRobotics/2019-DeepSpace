package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.lib.RioLogger;


public class Elevator extends Subsystem {
    private WPI_TalonSRX elevatorMotor1;
    private WPI_TalonSRX elevatorMotor2;
    private WPI_TalonSRX elevatorMotor3;

    private DigitalInput tLimit;
    private DigitalInput bLimit;

    private double elevatorSpeed = 0.0;


    public Elevator() {
        elevatorMotor1 = new WPI_TalonSRX(RobotMap.elevMotor1);
        elevatorMotor2 = new WPI_TalonSRX(RobotMap.elevMotor2);
        elevatorMotor3 = new WPI_TalonSRX(RobotMap.elevMotor3);

        elevatorMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,0,0);
        elevatorMotor2.follow(elevatorMotor1);
        elevatorMotor3.follow(elevatorMotor1);
        elevatorMotor1.setSelectedSensorPosition(0,0,0);
        
        tLimit = new DigitalInput(RobotMap.elevLSwitchT);
        bLimit = new DigitalInput(RobotMap.elevLSwitchB);

        reset();
        RioLogger.errorLog("Elevator() created.");
    }

    public void reset() {
        elevatorMotor1.set(0.0);
        elevatorMotor2.set(0.0);
        elevatorMotor3.set(0.0);
        elevatorSpeed = 0.0;
    }

    public void stop() {
        elevatorMotor1.stopMotor();
        elevatorMotor2.stopMotor();
        elevatorMotor3.stopMotor();
        elevatorSpeed = 0.0;
    }

    public void elevate(double speed) {
        elevatorMotor1.set(speed);
        elevatorSpeed = speed;
    }

    public double getMotor1Speed() {
        return elevatorMotor1.get();
    }

    public double getMotor2Speed() {
        return elevatorMotor2.get();
    }

    public double getMotor3Speed() {
        return elevatorMotor3.get();
    }

    public double getCurrentSpeed() {
        return elevatorSpeed;
    }

    public boolean upperLimit() {
        return tLimit.get();
    }

    public boolean lowerLimit() {
        return bLimit.get();
    }

    public int checkSwitches() {
        if (upperLimit()) {
            return 1;
        } else if (lowerLimit()) {
            return -1;
        } else {
            return 0;
        }
    }

    public void displayDashboard() {
        SmartDashboard.putBoolean("Current Upper Value", tLimit.get());
        SmartDashboard.putBoolean("Current Lower Value", bLimit.get());
    }

    @Override
	public void initDefaultCommand() {
	  // Set the default command for a subsystem here.
	  // setDefaultCommand(new MySpecialCommand());
	}
}