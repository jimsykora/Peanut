package org.usfirst.frc.team2839.robot.commands;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LeftOffset extends Command {
	double travel = 0.0;

    public LeftOffset(double counts) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.leftDrivePID);
    	travel = counts;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetLEncoderCount();
    	//double travel = Robot.vision.getCounts();  
    	Robot.leftDrivePID.setSetpoint(travel);
    	Robot.leftDrivePID.setRawTolerance(RobotPreferences.angleTolerance());
    	//Robot.leftDrivePID.setRawTolerance(RobotPreferences.targetCount());
    	Robot.leftDrivePID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.leftDrivePID.setSetpoint(travel);
    	Robot.drivetrain.setLTravel(Robot.leftDrivePID.getOutput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.leftDrivePID.onRawTargrt();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.leftDrivePID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
