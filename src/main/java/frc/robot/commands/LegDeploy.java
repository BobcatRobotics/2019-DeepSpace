package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class LegDeploy extends Command {

    public LegDeploy() {

        super();
    }

    @Override
    protected void execute() {

        OI.lock.enable();
    }

    @Override
    protected boolean isFinished() {

        return false;
    }
}