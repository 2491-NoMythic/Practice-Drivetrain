package com._2491nomythic.drive.commands;


/**
 *
 */
public class DriveStraight extends CommandBase {
	double speed; 
	
    public DriveStraight(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (speed > 0) {
			drivetrain.drive(Math.min(speed, speed - Math.min(0.5 * speed, 0.1 * drivetrain.getRawGyro())), Math.min(speed, speed + Math.max(-0.5 * speed, 0.1 * drivetrain.getRawGyro())));
		}
		else {
			drivetrain.drive(Math.max(speed, speed - Math.max(0.5 * speed, 0.1 * drivetrain.getRawGyro())), Math.max(speed, speed + Math.min(-0.5 * speed, 0.1 * drivetrain.getRawGyro())));
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
