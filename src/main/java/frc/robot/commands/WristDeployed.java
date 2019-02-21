package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.lib.RioLogger;

public class WristDeployed extends Command {
    boolean toggle = false;

    public WristDeployed() {
        super();
        requires(OI.wrist);
        requires(OI.panel);
        RioLogger.errorLog("WristDeployed() created.");
    }

    @Override
    protected void initialize() {
        RioLogger.errorLog("WristDeployed.initialize() called.");
    }

    @Override
    protected void execute() {
        RioLogger.errorLog("WristDeployed.execute() called.");
        // When the wrist it deployed, the panel intake needs to be retracted
        // so that it does not hit the ground and get damaged
        OI.panel.setIn();
        OI.wrist.deploy();
    }

    @Override
    protected boolean isFinished() {
        RioLogger.errorLog("WristDeployed.isFinished() called. Returning true.");
        return true;
    }

    @Override
    protected void end() {
        RioLogger.errorLog("WristDeployed.end() called.");
    }

    @Override
    protected void interrupted() {
        RioLogger.errorLog("WristDeployed.interrupted() called.");
    }
}