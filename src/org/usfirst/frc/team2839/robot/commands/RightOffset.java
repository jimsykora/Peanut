package org.usfirst.frc.team2839.robot.commands;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RightOffset extends Command {
	double travel = 0.0;  //travel was arclength

    public RightOffset(double counts) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rightDrive);
    	requires(Robot.rightDrivePID);
    	travel = counts;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.rightDrive.resetEncoderCount();
    	Robot.rightDrivePID.setSetpoint(travel);
    	Robot.rightDrivePID.setRawTolerance(RobotPreferences.angleTolerance());
    	//Robot.rightDrivePID.setRawTolerance(RobotPreferences.targetCount());
    	Robot.rightDrivePID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.rightDrivePID.setSetpoint(travel);
    	Robot.rightDrive.setAngle(Robot.rightDrivePID.getOutput());  // want right motor to go same # of counts as left motor
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.rightDrivePID.onRawTargrt();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rightDrivePID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
