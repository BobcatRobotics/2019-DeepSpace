package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class PanelIntakeIn extends Command {

    public PanelIntakeIn() {
        
        super();
    }

    @Override
    protected void execute() {

        OI.panel.setIn();
    }

    @Override
    protected boolean isFinished() {

        return true;
    }

}