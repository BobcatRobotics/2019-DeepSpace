package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class PanelIntakeOut extends Command {

    public PanelIntakeOut() {
        
        super();
    }

    @Override
    protected void execute() {

        OI.panel.setOut();
    }

    @Override
    protected boolean isFinished() {

        return false;
    }

}