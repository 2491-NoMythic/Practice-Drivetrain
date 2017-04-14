package com._2491nomythic.drive.subsystems;

import com._2491nomythic.drive.commands.ProportionalAccelDrive;
import com._2491nomythic.drive.settings.Constants;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The system used to move the robot
 */
public class Drivetrain extends Subsystem {
    private CANTalon left1, left2, right1, right2;
    
    private static Drivetrain instance;
	
	public static Drivetrain getInstance() {
		if (instance == null) {
			instance = new Drivetrain();
		}
		return instance;
	}
    
    /**
     * The system used to move the robot
     */
    private Drivetrain() {
    	left1 = new CANTalon(Constants.driveTalonLeft1Channel);
    	left2 = new CANTalon(Constants.driveTalonLeft2Channel);
    	right1 = new CANTalon(Constants.driveTalonRight1Channel);
    	right2 = new CANTalon(Constants.driveTalonRight2Channel);
    }
    
    /**
	 * Sets the drive motors to independent specific speeds
	 * 
	 * @param leftSpeed
	 *            The power fed to the left drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 * @param rightSpeed
	 *            The power fed to the right drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public void drive(double leftSpeed, double rightSpeed) {
		driveLeft(leftSpeed);
		driveRight(rightSpeed);
	}
	
	/**
	 * Sets the drive motors to a unified specific speed
	 * 
	 * @param speed
	 *            The power fed to all drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public void drive(double speed) {
		driveLeft(speed);
		driveRight(speed);
	}
	
	/**
	 * Sets the left drive motors to a specific speed
	 * 
	 * @param speed
	 *            The power fed to the left drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public void driveLeft(double speed) {
		left1.set(speed);
		left2.set(speed);
	}
	
	/**
	 * Sets the right drive motors to a specific speed
	 * 
	 * @param speed
	 *            The power fed to the right drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public void driveRight(double speed) {
		right1.set(-1.0 * speed);
		right2.set(-1.0 * speed);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ProportionalAccelDrive());
    }
}

