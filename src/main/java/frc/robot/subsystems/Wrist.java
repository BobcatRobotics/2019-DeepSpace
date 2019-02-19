package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;

public class Wrist {
    public static Solenoid solenoid1 = new Solenoid(RobotMap.wristSolenoid1); 
    public static Solenoid solenoid2 = new Solenoid(RobotMap.wristSolenoid2); 


    public void deploy() {
        
        solenoid1.set(true);
        solenoid2.set(false);
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
}