package org.usfirst.frc.team2839.robot.commands;

import org.usfirst.frc.team2839.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TurnCW extends TimedCommand {

    public TurnCW(double timeout) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires (Robot.leftTurn);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
     Robot.leftTurn.setSpeed(0.5); //seems to have little/no impact on motor speed
    }

    // Called once after timeout
    protected void end() {
    	Robot.leftTurn.setSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();//added
    }
}
