package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command {

    protected double elev = 0.0;

    public MoveElevator() {
        
        super();
    }

    protected void intitalize() {

        OI.elev1.reset();
    }
    
    @Override
    protected boolean isFinished() {

        return false;
    }
}