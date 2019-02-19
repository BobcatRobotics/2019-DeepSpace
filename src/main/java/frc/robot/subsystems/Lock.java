package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;

public class Lock {

    //public static Solenoid solenoid1 = new Solenoid(RobotMap.panelSolenoid);
    private Solenoid solenoid1;

    public Lock() {
        
<<<<<<< HEAD
        solenoid1 = new Solenoid(RobotMap.wristLockSol);
=======
        solenoid1 = new Solenoid(RobotMap.wristLock);
>>>>>>> 08831865c458182a788e0576b52875d1fac1b04c
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