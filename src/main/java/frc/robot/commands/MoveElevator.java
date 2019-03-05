package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command {

    public MoveElevator() {
        super();
        requires(OI.elev1);
    }

    protected int checkSwitches() {
        return OI.elev1.checkSwitches();
    }

    @Override
    protected void execute() {

        double elev = OI.gamePad.getRawAxis(RobotMap.gamePadLeftPwrStick);
        double motorSpeed =  elev;
        if (!OI.elev1.isLimDisable()) {
            if (OI.lock.isLocked()) {
                if (OI.elev1.lowerLimit()) {
                    if (motorSpeed > 0.3) {
                           motorSpeed = 0.3;
                       }
                  }
   
            } else {
                if (OI.elev1.lowerLimit()) {
                    if (motorSpeed > 0.0) {
                           motorSpeed = 0.0;
                       }
                  }
           }

            if (OI.elev1.upperLimit()) {
            
                if (motorSpeed < 0.0) {
    
                    motorSpeed = 0.0;
                }
            }
        }

        OI.elev1.elevate(motorSpeed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        OI.elev1.stop();
    }
}