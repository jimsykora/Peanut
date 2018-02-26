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
    	requires(Robot.drivetrain);
    	requires(Robot.rightDrivePID);
    	requires(Robot.leftDrivePID);/////////////////////////
    	travel = counts;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetREncoderCount();
    	Robot.drivetrain.resetLEncoderCount();///////////////////////////
    	//double travel = Robot.vision.getCounts();  
    	Robot.rightDrivePID.setSetpoint(travel);
    	Robot.rightDrivePID.setRawTolerance(RobotPreferences.angleTolerance());
    	Robot.leftDrivePID.setSetpoint(0);//////////////////////
    	Robot.leftDrivePID.setRawTolerance((1));/////////////////////
    	//Robot.rightDrivePID.setRawTolerance(RobotPreferences.targetCount());
    	Robot.rightDrivePID.enable();
    	Robot.leftDrivePID.enable();///////////////////
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.rightDrivePID.setSetpoint(travel);
    	Robot.drivetrain.setRTravel(Robot.rightDrivePID.getOutput());  // want right motor to go same # of counts as left motor
    	Robot.leftDrivePID.setSetpoint(0);////////////////////
    	Robot.drivetrain.setLTravel(Robot.leftDrivePID.getOutput());////////////////
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.rightDrivePID.onRawTargrt();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rightDrivePID.disable();
    	Robot.leftDrivePID.disable();///////////////
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
