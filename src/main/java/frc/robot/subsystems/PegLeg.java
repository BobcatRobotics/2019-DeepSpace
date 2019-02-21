package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.lib.RioLogger;

public class PegLeg extends Subsystem {
    // public static Solenoid solenoid1 = new Solenoid(RobotMap.panelSolenoid);
    private Solenoid solenoid1;

    public PegLeg() {
        solenoid1 = new Solenoid(RobotMap.pegLegSol);
        RioLogger.errorLog("PegLeg() created");
    }

    public void deploy() {
        solenoid1.set(true);
    }

    public void retract() {
        solenoid1.set(false);
    }

    public void reset() {
        retract();
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}