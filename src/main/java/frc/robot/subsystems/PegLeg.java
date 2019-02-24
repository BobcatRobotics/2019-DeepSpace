package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.RobotMap;
import frc.robot.lib.RioLogger;

public class PegLeg extends Subsystem {

    // Declare motor for pegleg
    private WPI_VictorSPX pegLegMotor;
    // public static Solenoid solenoid1 = new Solenoid(RobotMap.panelSolenoid);
    private Solenoid solenoid1;

    public PegLeg() {

        pegLegMotor = new WPI_VictorSPX(RobotMap.pegLegMotor);
        RioLogger.errorLog("PegLegRoller() created.");
        solenoid1 = new Solenoid(RobotMap.pegLegSol);
        RioLogger.errorLog("PegLeg() created");
    }

    public void deploy() {
        solenoid1.set(true);
    }

    public void retract() {
        solenoid1.set(false);
    }

    public void drivepegmotor(double pegPwr) {

		pegLegMotor.set(pegPwr);

    }

    public void reset() {
        retract();
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}