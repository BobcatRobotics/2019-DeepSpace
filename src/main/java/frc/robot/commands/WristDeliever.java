package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class WristDeliever extends Command {
    boolean toggle = false;

    public WristDeliever() {
		super();
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

	@Override
	protected void end() {
        OI.wrist.deliver();;
	}
    
    @Override
	protected void interrupted() {
        OI.wrist.deliver();;
	}
}