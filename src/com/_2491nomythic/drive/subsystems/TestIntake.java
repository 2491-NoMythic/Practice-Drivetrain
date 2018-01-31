package com._2491nomythic.drive.subsystems;

import com._2491nomythic.drive.settings.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TestIntake extends Subsystem {
	TalonSRX left1, left2, right1, right2;
	private static TestIntake instance;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public TestIntake() {
		left1 = new TalonSRX(Constants.intakeLeft1);
		left2 = new TalonSRX(Constants.intakeLeft2);
		right1 = new TalonSRX(Constants.intakeRight1);
		right2 = new TalonSRX(Constants.intakeRight2);
	}
	
	public static TestIntake getInstance() {
		if(instance == null) {
			instance = new TestIntake();
		}
		return instance;
	}
	
	public void run(double speed) {
		left1.set(ControlMode.PercentOutput, speed);
		left2.set(ControlMode.PercentOutput, speed);
		right1.set(ControlMode.PercentOutput, speed);
		right2.set(ControlMode.PercentOutput, speed);
	}
	
	public void stop() {
		left1.set(ControlMode.PercentOutput, 0);
		left2.set(ControlMode.PercentOutput, 0);
		right1.set(ControlMode.PercentOutput, 0);
		right2.set(ControlMode.PercentOutput, 0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

