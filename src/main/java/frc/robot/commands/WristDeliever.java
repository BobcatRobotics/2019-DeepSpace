package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class WristDeliever extends Command {
    boolean toggle = false;

    public WristDeliever() {
		super();
    }
    
    
    @Override
    protected void execute () {
        
        OI.wrist.setDifferentSolenoids(true);
    }

    @Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		OI.wrist.reset();
	}
    
}