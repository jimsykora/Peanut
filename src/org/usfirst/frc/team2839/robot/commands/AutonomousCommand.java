package org.usfirst.frc.team2839.robot.commands;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {
	//double offset = 0.0;
	public static double offset = Robot.vision.getHalfAngleOfOffset();
	
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
    	
    	//double offset = Robot.vision.getHalfAngleOfOffset();
    	    	
//    	addSequential(new DriveDistance(distance()));
//    	addSequential(new TurnAngle(angle()));
    	//addSequential(new LeftOffset(offset));   //(halfAngleOfOffset()));
    	//addSequential(new RightOffset(offset));   //(halfAngleOfOffset()));
    	//addSequential(new DriveCamera(endpoint()));
//    	addSequential(new DriveDistance(distance()));
//    	addSequential(new TurnAngle(angle()));
    	addSequential(new TurnOne(offset/2));
    	addSequential(new TurnTwo(offset/2));
    }
}
