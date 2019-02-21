package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.lib.RioLogger;

public class CargoRollerOut extends Command {

    public CargoRollerOut() {
        super();
        requires(OI.cargo);
    }

    @Override
    protected void execute() {
        OI.cargo.deposit();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {
        OI.cargo.stop();
        RioLogger.errorLog("CargoRollerOut.interrupted() called");
    }

    @Override
    protected void end() {
        OI.cargo.stop();
        RioLogger.errorLog("CargoRollerOut.end() called");
    }
}