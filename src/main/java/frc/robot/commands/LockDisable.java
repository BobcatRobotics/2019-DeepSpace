package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class LockDisable extends Command {

    public LockDisable() {

        super();
    }

    @Override
    protected void execute() {

        OI.lock.disable();
    }

    @Override
    protected boolean isFinished() {

        return true;
    }
}