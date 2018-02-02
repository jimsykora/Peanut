package org.usfirst.frc.team2839.robot.commands;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;
import org.usfirst.frc.team2839.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {
	//double offset = 5.194;  //this works as expected
	//double polarity = 1.0;  //this works as expected
	//double counts = 250;  //this works as expected
	
	//public static double offset = Robot.vision.getHalfAngleOfOffset(); //always a positive angle
	public static double polarity = 0;
	public static double counts = 0;
	public static double countsAngle = 0;
	
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
		return Robot.drivetrain.getLEncoderCount();
	}
	public double rQEncoderCount() {
		return Robot.drivetrain.getREncoderCount();
	}


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
    	    	    	
//    	addSequential(new DriveDistance(distance()));
//    	addSequential(new TurnAngle(angle()));
    	//addSequential(new DriveCamera(endpoint()));
    	//addSequential(new LeftOffset(offset));   //(halfAngleOfOffset()));
    	//addSequential(new RightOffset(offset));   //(halfAngleOfOffset()));
    	//
    	
    	
/* 		//this is a PID loop approach to correct for the Jetson offset using encoders   
    	counts = Robot.vision.getCounts();	//need to capture & store new Jetson info now after motion for use by the following
    	polarity = Robot.vision.getTargetOffset(); //either positive or negative number
    	if(polarity>=0.0) {						//this is proof of concept; Jetson needs calibrating and angle/time/speed relations need tweaking
    		addSequential(new RightOffset(counts*1)); //multiplier is to amplify error for debug/test purposes only
    		addSequential(new LeftOffset(counts*1));
    	}
    	else {
    		addSequential(new LeftOffset(counts*1));
    		addSequential(new RightOffset(counts*1));
    	}
*/  
    	
/*
 		//this is a PID loop approach to correct for the Jetson angle using encoders 
    	counts = Robot.vision.getCountsAngle();	//need to capture & store new Jetson info now after motion for use by the following
    	polarity = Robot.vision.getTargetAngle(); //either positive or negative number
    	if(polarity>=0.0) {						//this is proof of concept; Jetson needs calibrating and angle/time/speed relations need tweaking
    		addSequential(new RightOffset(counts*1));  //multiplier is to amplify error for debug/test purposes only
    		addSequential(new LeftOffset(counts*1));
    	}
    	else {
    		addSequential(new LeftOffset(counts*1));
    		addSequential(new RightOffset(counts*1));
    	}
*/
    	//drive straight for 1/2 wheel rev
    	//addParallel(new LeftOffset(250));
    	//addSequential(new LeftOffset(250));
    	//addParallel(new RightOffset(250));
    	addSequential(new RightOffset(250));
    	addParallel(new LeftOffset(250));
    	//addParallel(new RightOffset(250));
    	
   /*
    	//this is a PID loop approach to correct for both the Jetson angle & offset using encoders 
    	counts = Robot.vision.getTotalCounts();
    	countsAngle = Robot.vision.getAngleCorrectionOnlyCounts();
    	polarity = Robot.vision.getTargetAngle(); //either positive or negative number
    	if(counts>=0.0) {						
    		addSequential(new RightOffset(counts*1));  //multiplier is to amplify error for debug/test purposes only
    		addSequential(new LeftOffset(counts*1));
    	}
    	else {
    		addSequential(new LeftOffset(counts*-1*1));
    		addSequential(new RightOffset(counts*-1*1));
    	}
 */ 	
/*  	
    	//countsAngle = Robot.vision.getAngleCorrectionOnlyCounts();  //only if using just this sequence
    	//polarity = Robot.vision.getTargetAngle();   //only if using just this sequence	
    	if(polarity>=0.0) {						
    		addSequential(new LeftOffset(countsAngle*1));  //multiplier is to amplify error for debug/test purposes only
    	}
    	else {
    		addSequential(new RightOffset(countsAngle*1));
    	}
*/    	
    	
    	
    	/*
    	 	//this is a timed approach to correct for the Jetson offset
    	if(polarity>=0.0) {						//this is proof of concept; Jetson needs calibrating and angle/time/speed relations need tweaking
    		addSequential(new TurnCCW(offset/500)); //a 50 degree req'd turn results in 0.1 seconds of motor on time per side
    		addSequential(new TurnCW(offset/500));
    	}
    	else {
    		addSequential(new TurnCW(offset/500));
    		addSequential(new TurnCCW(offset/500));
    	}
    	*/
    }
}
