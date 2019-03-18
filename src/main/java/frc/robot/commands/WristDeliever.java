package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class WristDeliever extends Command {
    boolean toggle = false;
    CargoRollerIn4sec cargoRollerIn4sec;

    public WristDeliever() {
        super();
        requires(OI.wrist);
    }
    
    @Override
    protected void initialize() {
        // check the state, if 2, then run cargo in for 4 seconds
        if (OI.wrist.getWristState() == 2) {
            cargoRollerIn4sec = new CargoRollerIn4sec();
            cargoRollerIn4sec.start();
        }
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