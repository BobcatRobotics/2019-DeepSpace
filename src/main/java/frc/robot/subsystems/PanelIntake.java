package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.lib.RioLogger;

public class PanelIntake extends Subsystem {
    private Solenoid solenoid1;
    private Solenoid solenoid2;
    private boolean panelInOutState=false;

    public PanelIntake() {
        solenoid1 = new Solenoid(RobotMap.panelGripSolenoid);
        solenoid2 = new Solenoid(RobotMap.panelInOutSolenoid);
        RioLogger.errorLog("PanelIntake() Created.");
    }

    public void holdPanel() {
        solenoid1.set(false);
        RioLogger.errorLog("PanelIntake.holdPanel() solenoid1=true");
    }

    public void releasePanel() {
        solenoid1.set(true);
        RioLogger.errorLog("PanelIntake.releasePanel() solenoid1=false");
    }

    public void panelInOutToggle() {
        panelInOutState = !panelInOutState;
        solenoid2.set(panelInOutState);
        RioLogger.errorLog("PanelIntake.panelInOutToggle() solenoid toggle");
    }

    public void panelInOutSetToIn() {
        solenoid2.set(false);
        panelInOutState = false;
    }

    public void panelInOutSetToOut() {
        solenoid2.set(true);
        panelInOutState=true;
    }

    public void reset() {
        holdPanel();
        panelInOutSetToIn();
        RioLogger.errorLog("PanelIntake.reset()");
    }

    @Override
	public void initDefaultCommand() {
	  // Set the default command for a subsystem here.
	  // setDefaultCommand(new MySpecialCommand());
	}
}