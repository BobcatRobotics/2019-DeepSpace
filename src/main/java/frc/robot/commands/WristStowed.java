package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class WristStowed extends Command {
    boolean toggle = false;

    public WristStowed() {
		super();
    }
    
    
    @Override
    protected void execute () {
        //TODO update these once the wrist is more defined
        //Possibly extend to individual commands
        OI.wrist.setBothSolenoids(false);
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