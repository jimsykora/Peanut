package org.usfirst.frc.team2839.robot.commands;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {
	double distance = 0.0;

    public DriveDistance(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.drivetrainDistancePID);
    	distance = inches;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoderCount();
    	Robot.drivetrainDistancePID.setSetpoint(distance);
    	Robot.drivetrainDistancePID.setRawTolerance(RobotPreferences.driveTolerance());
    	Robot.drivetrainDistancePID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.arcadeDrive(Robot.drivetrainDistancePID.getOutput(), Robot.drivetrain.getDifDistance()*RobotPreferences.autoCorrectDistance());  //motion tweaked by encoder count ratio
    	//Robot.drivetrain.arcadeDrive(Robot.drivetrainDistancePID.getOutput(), Robot.vision.getTargetAngleCorrelation()*RobotPreferences.autoCorrectJetson());  //motion tweaked by Jetson target area ratio
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrainDistancePID.onRawTargrt();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrainDistancePID.disable();
    	Robot.drivetrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
