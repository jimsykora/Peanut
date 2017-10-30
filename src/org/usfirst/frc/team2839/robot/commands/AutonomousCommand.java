package org.usfirst.frc.team2839.robot.commands;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {
	//double offset = 4.0;  //this works as expected
	//double polarity = -1.0;  //this works as expected
	public static final double offset = Robot.vision.getHalfAngleOfOffset(); //always a positive angle
	public static final double polarity = Robot.vision.getTargetOffset(); //either positive or negative number
	
	public double distance() {
		return RobotPreferences.autoDistance();
	}
	public double angle() {
		return RobotPreferences.autoAngle();
	}
	public double endpoint() {
		return RobotPreferences.autoEndpoint();
	}
	public double lQEncoderCount() {
		return Robot.leftDrive.getLEncoderCount();
	}
	/*public double halfAngleOfOffset() {
		return Robot.vision.getHalfAngleOfOffset();
	}*/

    public AutonomousCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	    	    	
//    	addSequential(new DriveDistance(distance()));
//    	addSequential(new TurnAngle(angle()));
    	//addSequential(new LeftOffset(offset));   //(halfAngleOfOffset()));
    	//addSequential(new RightOffset(offset));   //(halfAngleOfOffset()));
    	//addSequential(new DriveCamera(endpoint()));
//    	addSequential(new DriveDistance(distance()));
//    	addSequential(new TurnAngle(angle()));
    	if(polarity>=0.0) {						//this is proof of concept; Jetson needs calibrating and angle/time/speed relations need tweaking
    		addSequential(new TurnOne(offset/2)); 
    		addSequential(new TurnTwo(offset/2));
    	}
    	else {addSequential(new TurnTwo(offset/2));
    	addSequential(new TurnOne(offset/2));
    	}
    }
}
