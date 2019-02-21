package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.lib.RioLogger;

public class Wrist  extends Subsystem {
    public static Solenoid solenoid1 = new Solenoid(RobotMap.wristSolenoid1);
    public static Solenoid solenoid2 = new Solenoid(RobotMap.wristSolenoid2);

    public void deploy() {
        solenoid1.set(true);
        solenoid2.set(false);
        RioLogger.errorLog("Wrist.deploy() called. solenoid1=true, solenoid2=false");
    }

    public void deliver() {
        solenoid1.set(false);
        solenoid2.set(true);
    }

    public void stow() {
        solenoid1.set(false);
        solenoid2.set(false);
    }

    public void reset() {
        solenoid1.set(false);
        solenoid2.set(false);
    }

    @Override
	public void initDefaultCommand() {
	  // Set the default command for a subsystem here.
	  // setDefaultCommand(new MySpecialCommand());
	}
}