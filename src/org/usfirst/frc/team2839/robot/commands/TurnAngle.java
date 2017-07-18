package org.usfirst.frc.team2839.robot.commands;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnAngle extends Command {
	double angle = 0.0;

    public TurnAngle(double degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.drivetrainAnglePID);
    	angle = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoderCount();
    	Robot.drivetrainAnglePID.setSetpoint(angle);
    	Robot.drivetrainAnglePID.setRawTolerance(RobotPreferences.angleTolerance());
    	Robot.drivetrainAnglePID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drivetrain.arcadeDrive(Robot.drivetrain.getDifRate()*RobotPreferences.autoCorrectAngle(),Robot.drivetrainAnglePID.getOutput());
    	Robot.drivetrain.arcadeDrive((Robot.drivetrain.getLEncoderCount()+Robot.drivetrain.getREncoderCount())*(-RobotPreferences.autoCorrectAngle())-0.0,Robot.drivetrainAnglePID.getOutput());  //an attempt to force both wheels to same # of counts, a constant of -0.055 balances out close to equal counts
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrainAnglePID.onRawTargrt();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrainAnglePID.disable();
    	Robot.drivetrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
