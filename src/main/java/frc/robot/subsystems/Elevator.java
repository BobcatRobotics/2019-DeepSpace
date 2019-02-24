package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.RobotMap;
import frc.robot.lib.RioLogger;


public class Elevator extends Subsystem {
    private ShuffleboardTab tab = Shuffleboard.getTab("Elevator");
    private NetworkTableEntry elevDist = tab.add("Elevator Distance", 0).getEntry();
    private NetworkTableEntry elevVel = tab.add("Elevator Velocity", 0).getEntry();
    private NetworkTableEntry elevCmd = tab.add("Elevator Command", 0).getEntry();
    private NetworkTableEntry elevBiasNT = tab.add("Elevator Command Bias", 0.1).getEntry();
    private NetworkTableEntry elevScaleNT = tab.add("Elevator Command Scale", 0.6).getEntry();

    private WPI_TalonSRX elevatorMotor1;
    private WPI_TalonSRX elevatorMotor2;
    private WPI_TalonSRX elevatorMotor3;

    private DigitalInput tLimit;
    private DigitalInput bLimit;

    private double elevatorCmd = 0.0;
    private double elevatorVelocity = 0.0;
    private double elevatorDistance = 0.0;

    private double elevScale=0.6;
    private double elevBias=0.1;

    public Elevator() {
        elevatorMotor1 = new WPI_TalonSRX(RobotMap.elevMotor1);
        elevatorMotor2 = new WPI_TalonSRX(RobotMap.elevMotor2);
        elevatorMotor3 = new WPI_TalonSRX(RobotMap.elevMotor3);

        tab.add("elevMotor1", elevatorMotor1);
        tab.add("elevMotor2", elevatorMotor2);
        tab.add("elevMotor3", elevatorMotor3);

        elevatorMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,0,0);
        elevatorMotor2.follow(elevatorMotor1);
        elevatorMotor3.follow(elevatorMotor1);
        elevatorMotor1.setSelectedSensorPosition(0,0,0);

        // Configure the Talons to be in brake mode
        elevatorMotor1.setNeutralMode(NeutralMode.Brake);
        elevatorMotor2.setNeutralMode(NeutralMode.Brake);
        elevatorMotor3.setNeutralMode(NeutralMode.Brake);
        
        tLimit = new DigitalInput(RobotMap.elevLSwitchT);
        bLimit = new DigitalInput(RobotMap.elevLSwitchB);

        reset();
        RioLogger.errorLog("Elevator() created.");
    }

    public void reset() {
        elevatorCmd = 0.0;
        elevatorMotor1.set(elevatorCmd);
        elevatorMotor2.set(elevatorCmd);
        elevatorMotor3.set(elevatorCmd);
    }

    public void stop() {
        elevatorMotor1.stopMotor();
        elevatorMotor2.stopMotor();
        elevatorMotor3.stopMotor();
        elevatorCmd = 0.0;
    }

    public void elevate(double cmd) {
        elevScale=elevScaleNT.getDouble(0.6);
        elevBias=elevBiasNT.getDouble(0.1);
        elevatorCmd = cmd * elevScale +  elevBias;
        elevatorMotor1.set(elevatorCmd);
        elevatorMotor2.set(elevatorCmd);
        elevatorMotor3.set(elevatorCmd);
        elevatorDistance = elevatorMotor1.getSelectedSensorPosition(0);
        elevatorVelocity = elevatorMotor1.getSelectedSensorVelocity(0);
        elevDist.setDouble(elevatorDistance);
        elevVel.setDouble(elevatorVelocity);
        elevCmd.setDouble(elevatorCmd);
    }

    public double getElevatorDistance() {
        return elevatorDistance;
    }

    public double getElevatorVelocity() {
        return elevatorVelocity;
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

    public double getCurrentCmd() {
        return elevatorCmd;
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