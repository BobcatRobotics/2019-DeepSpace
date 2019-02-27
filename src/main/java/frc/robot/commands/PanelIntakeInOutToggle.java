package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.lib.RioLogger;

public class PanelIntakeInOutToggle extends Command {

    public PanelIntakeInOutToggle() {
        super();
        requires(OI.panel);
        RioLogger.errorLog("PanelIntakeInOutToggle() Created.");
    }

    @Override
    protected void execute() {
        OI.panel.panelInOutToggle();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}