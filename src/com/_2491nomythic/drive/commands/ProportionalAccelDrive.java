package com._2491nomythic.drive.commands;

import com._2491nomythic.drive.settings.ControllerMap;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class ProportionalAccelDrive extends CommandBase {
	private double turnSpeed, leftSpeed, rightSpeed, rawLeftSpeed, rawRightSpeed, accelerationInterval;
	int state;
	Timer timer;

    public ProportionalAccelDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = 0;
    	timer.start();
    	timer.reset();
    	accelerationInterval = .5;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	turnSpeed = 0.5 * oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveTurnAxis, 0.1);
    	rawLeftSpeed = -oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveAxis, .05);
    	rawRightSpeed = -oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveAxis, .05);
    	
    	
    	switch (state) {
    	case 0:
    		if (timer.get() < .5) {
    			leftSpeed = .2 * rawLeftSpeed;
    			rightSpeed = .2 * rawRightSpeed;
    			state++;
    		}
    		if (Math.abs(rawLeftSpeed) < .2 * rawLeftSpeed && Math.abs(rawRightSpeed) < .2 * rawRightSpeed) {
    			timer.reset();
    		}
    		break;
    	case 1:
    		if (timer.get() > (1*accelerationInterval) && timer.get() < (2*accelerationInterval)) {
    			leftSpeed = .4 * rawLeftSpeed;
    			rightSpeed = .4 * rawRightSpeed;
    			state++;
    		}
    		if (Math.abs(rawLeftSpeed) < .2 * rawLeftSpeed && Math.abs(rawRightSpeed) < .2 * rawRightSpeed) {
    			timer.reset();
    		}
    		break;
    	case 2:
    		if (timer.get() > (2*accelerationInterval) && timer.get() < (3*accelerationInterval)) {
    			leftSpeed = .6 * rawLeftSpeed;
    			rightSpeed = .6 * rawRightSpeed;
    			state++;
    		}
    		if (Math.abs(rawLeftSpeed) < .2 * rawLeftSpeed && Math.abs(rawRightSpeed) < .2 * rawRightSpeed) {
    			timer.reset();
    		}
    		break;
    	case 3:
    		if (timer.get() > (3*accelerationInterval) && timer.get() < (4*accelerationInterval)) {
    			leftSpeed = .8 * rawLeftSpeed;
    			rightSpeed = .8 * rawRightSpeed;
    			state++;
    		}
    		if (Math.abs(rawLeftSpeed) < .2 * rawLeftSpeed && Math.abs(rawRightSpeed) < .2 * rawRightSpeed) {
    			timer.reset();
    		}
    		break;
    	case 4:
    		if (timer.get() > (4*accelerationInterval)) {
    			leftSpeed = rawLeftSpeed;
    			rightSpeed = rawRightSpeed;
    		}
    		if (Math.abs(rawLeftSpeed) < .2 * rawLeftSpeed && Math.abs(rawRightSpeed) < .2 * rawRightSpeed) {
    			timer.reset();
    		}
    		break;
    	}
    	
    	
    	drivetrain.drive(leftSpeed + turnSpeed, rightSpeed - turnSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.drive(0);
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
