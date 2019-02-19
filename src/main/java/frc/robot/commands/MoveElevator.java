package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command {

    public MoveElevator() {
        
        super();
    }

    protected int checkSwitches() {

        return OI.elev1.checkSwitches();
    }

    @Override
    protected void execute() {

        double elev = OI.gamePad.getRawAxis(RobotMap.gamePadLeftPwrStick);
        double motorSpeed = -1*elev;
        if (OI.limitOn) {

            if ((motorSpeed > 0.0) || (motorSpeed < 0.0)) {
                motorSpeed = 0.0;
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