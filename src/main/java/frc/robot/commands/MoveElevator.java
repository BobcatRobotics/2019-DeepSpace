package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command {

    protected double elev = 0.0;

    public MoveElevator() {
        
        super();
    }

    protected void intitalize() {

        OI.elev1.reset();
    }

    protected int checkSwitches() {

        return OI.elev1.checkSwitches();
    }

    @Override
    protected void execute() {

        elev = OI.gamePad.getRawAxis(RobotMap.leftJoystick);
        double motorSpeed = -1*elev;
        if (OI.limitOn) {

            if ((motorSpeed > 0.0) || (motorSpeed < 0.0)) {
                motorSpeed = 0.0;
            }
        }
    }
    
    protected boolean isFinished() {

        return false;
    }

    protected void end() {

        OI.elev1.stop();
    }

    protected void interruppted() {

        OI.elev1.stop();
    }
}