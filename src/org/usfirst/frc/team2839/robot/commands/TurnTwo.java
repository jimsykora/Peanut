package org.usfirst.frc.team2839.robot.commands;

import org.usfirst.frc.team2839.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TurnTwo extends TimedCommand {
	//double offset = 0.0;

    public TurnTwo(double timeout) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires (Robot.leftTurn);
        requires (Robot.rightTurn);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   	 //offset = Robot.vision.getTargetOffset();
   }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.vision.getTargetOffset()>=0.0) {  //getTargetOffset() is a +/ number from the Jetson & changes as robot turns
    	//if(Robot.vision.offset>=0.0) {  //ofset is re-read when this command starts
    		Robot.leftTurn.setSpeed(0.7);
    	}
    	else Robot.rightTurn.setSpeed(-0.7); 
    }

    // Called once after timeout
    protected void end() {
    	Robot.leftTurn.setSpeed(0.0);
    	Robot.rightTurn.setSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();//added
    }
}
