package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.lib.RioLogger;

public class Lock extends Subsystem {
    //public static Solenoid solenoid1 = new Solenoid(RobotMap.panelSolenoid);
    private Solenoid solenoid1;
    private boolean lockState;

    public Lock() {
        solenoid1 = new Solenoid(RobotMap.wristLockSol);
        RioLogger.errorLog("Lock() created.");
    }
    
    public void enable() {
        lockState = !lockState;
        solenoid1.set(lockState);
    }

    public void disable() {
        solenoid1.set(false);
    }

    public void reset() {
        disable();
    }

    @Override
	public void initDefaultCommand() {
	  // Set the default command for a subsystem here.
	  // setDefaultCommand(new MySpecialCommand());
	}
}