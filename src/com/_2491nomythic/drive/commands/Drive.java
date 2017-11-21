package com._2491nomythic.drive.commands;

import com._2491nomythic.drive.settings.ControllerMap;

/**
 *
 */
public class Drive extends CommandBase {
	private double turnSpeed, leftSpeed, rightSpeed;

    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	turnSpeed = 0.5 * oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveTurnAxis, 0.1);
    	leftSpeed = -oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveAxis, .05);
    	rightSpeed = -oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveAxis, .05);
    
    	drivetrain.drive(leftSpeed + turnSpeed, rightSpeed - turnSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.drive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
