package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class WristDeployed extends Command {
    boolean toggle = false;

    public WristDeployed() {
		super();
    }
    
    
    @Override
    protected void execute () {
        //TODO update these once the wrist is more defined
        //Possibly extend to individual commands
        OI.wrist.setBothSolenoids(true);
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