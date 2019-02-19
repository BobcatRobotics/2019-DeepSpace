package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;

public class PegLeg {

    //public static Solenoid solenoid1 = new Solenoid(RobotMap.panelSolenoid);
    private Solenoid solenoid1;

    public PegLeg() {
        
        solenoid1 = new Solenoid(RobotMap.pegLegSol);
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
}