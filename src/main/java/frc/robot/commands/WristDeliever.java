package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class WristDeliever extends Command {
    boolean toggle = false;

    public WristDeliever() {
        super();
        requires(OI.wrist);
    }
    
    @Override
    protected void initialize() {
        OI.wrist.deliver();
    }

    @Override
    protected void execute () {
    }

    @Override
	protected boolean isFinished() {
		return true;
	}


}