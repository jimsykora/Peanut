package org.usfirst.frc.team2839.robot.commands;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCamera extends Command {
	double endpoint = 0.0;

    public DriveCamera(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.drivetrainCameraPID);
    	endpoint = inches;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrainCameraPID.setSetpoint(endpoint);
    	Robot.drivetrainCameraPID.setRawTolerance(RobotPreferences.driveTolerance());
    	Robot.drivetrainCameraPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.arcadeDrive(Robot.drivetrainCameraPID.getOutput(), Robot.vision.getTargetAngle()*RobotPreferences.autoCorrectCameraAngle()+Robot.vision.getTargetOffset()*RobotPreferences.autoCorrectOffset());
    	//Robot.drivetrain.arcadeDrive(Robot.drivetrainCameraPID.getOutput(), 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return Robot.drivetrainCameraPID.onRawTargrt();
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Done");
    	Robot.drivetrainCameraPID.disable();
    	Robot.drivetrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
