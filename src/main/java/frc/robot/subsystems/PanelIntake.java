package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.lib.RioLogger;

public class PanelIntake extends Subsystem {
    // public static Solenoid solenoid1 = new Solenoid(RobotMap.panelSolenoid);
    private Solenoid solenoid1;

    public PanelIntake() {
        solenoid1 = new Solenoid(RobotMap.panelSolenoid);
        RioLogger.errorLog("PanelIntake() Created.");
    }

    public void setIn() {
        solenoid1.set(false);
        RioLogger.errorLog("PanelIntake.setIn() solenoid1=true");
    }

    public void setOut() {
        solenoid1.set(true);
        RioLogger.errorLog("PanelIntake.setOut() solenoid1=false");
    }

    public void reset() {
        setOut();
        RioLogger.errorLog("PanelIntake.reset()");
    }

    @Override
	public void initDefaultCommand() {
	  // Set the default command for a subsystem here.
	  // setDefaultCommand(new MySpecialCommand());
	}
}