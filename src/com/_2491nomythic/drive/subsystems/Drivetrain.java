package com._2491nomythic.drive.subsystems;

import com._2491nomythic.drive.commands.QuadraticDrive2Stick;
import com._2491nomythic.drive.settings.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.kauailabs.navx.frc.AHRS;

/**
 * The system used to move the robot
 */
public class Drivetrain extends Subsystem {
    private TalonSRX left1, left2, right1, right2;
    private AHRS gyro;
    
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
    	left1 = new TalonSRX(Constants.driveTalonLeft1Channel);
    	left2 = new TalonSRX(Constants.driveTalonLeft2Channel);
    	right1 = new TalonSRX(Constants.driveTalonRight1Channel);
    	right2 = new TalonSRX(Constants.driveTalonRight2Channel);
    	
    	left2.follow(left1);
    	right2.follow(right1);
    	
    	 try {
             gyro = new AHRS(SPI.Port.kMXP);
         } 
    	 catch (RuntimeException ex ) {
             DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
         }
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
		left1.set(ControlMode.PercentOutput, speed);
	}
	
	/**
	 * Sets the right drive motors to a specific speed
	 * 
	 * @param speed
	 *            The power fed to the right drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public void driveRight(double speed) {
		right1.set(ControlMode.PercentOutput, -1.0 * speed);
	}
	
	public double getRawGyro() {
		return gyro.getAngle();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new QuadraticDrive2Stick());
    }
}

