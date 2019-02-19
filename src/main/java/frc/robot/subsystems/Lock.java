package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;

public class Lock {

    //public static Solenoid solenoid1 = new Solenoid(RobotMap.panelSolenoid);
    private Solenoid solenoid1;

    public Lock() {
        
        solenoid1 = new Solenoid(RobotMap.wristLockSol);
    }
    
    public void enable() {

        solenoid1.set(true);
    }

    public void disable() {

        solenoid1.set(false);
    }

    public void reset() {
        disable();
    }
}