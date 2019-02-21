package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.lib.RioLogger;

public class PanelIntakeOut extends Command {

    public PanelIntakeOut() {
        super();
        requires(OI.panel);
        RioLogger.errorLog("PanelIntakeOut() Created.");
    }

    @Override
    protected void execute() {
        OI.panel.setOut();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}