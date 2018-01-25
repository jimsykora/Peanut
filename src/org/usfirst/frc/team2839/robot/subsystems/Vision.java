package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.*;
/**
 *
 */
public class Vision extends Subsystem {
	
	NetworkTable table;
	public Vision() {  //this is a constructor & is called only once when the code first runs
		table = NetworkTable.getTable("NURDVision");
	}
	public double getTargetDistance() { //distance from Jetson to center of two vertical, reflective targets
		return (table.getNumber("Distance", -99.9))*1.4-.04; //multiplier & adder are to get slope & intercept correct (Jetson needs calibration)
	}
	public double getTargetOffset() { //left/right distance between camera center and center of two vertical, reflective targets
		return (table.getNumber("Offset", -99.9))*21.7+1.1; //multiplier & adder are to get slope & intercept correct (Jetson needs calibration)
	}
	public double getTargetAngle() { //angle between the plane of two vertical, reflective targets and camera mounting plane
		return (table.getNumber("Angle", -99.9))*1.64-1.6; //multiplier & adder are to get slope & intercept correct (Jetson needs calibration)
	} 
//___________________________________________________________________________________________________________________________
	
	public double getHalfAngleOfOffset() {  //angle, in degrees, needed for a single drive wheel to make 1/2 of an "S" turn
		//if(getTargetOffset()>=0.0){
		return  Math.acos(1-(0.5*getTargetOffset()/16.65))*360/2/3.14159;  //ArcCos(1-0.5*offset/robot treadwidth)
		//}
		//return  Math.acos(1-(0.5*getTargetOffset()*-1.0/16.65))*360/2/3.14159;  //ArcCos(1-0.5*offset/robot treadwidth)
	}
	public double getArclengthOffset() {  //arclength of 1/2 of an "S" turn
		return getHalfAngleOfOffset()*16.65*2*3.14159/360; //treadwidth*2*PI/360
	}
	public double getCountsOffset() {  //counts needed to make 1/2 of an "S" turn
		return getArclengthOffset()/6/3.14159*500;//arclength/wheel dia/PI*counts per wheel revolution
	}
	public double getWheelRevsOffset() {  //wheel revs needed to make 1/2 of an "S" turn
		return getCountsOffset()/500;//counts offset/counts per wheel revolution
	}
//_________________________________________________________________________________________________________________________	

	public double getHalfAngleOfTheta() {  //angle, in degrees, needed for a single drive wheel to make 1/2 of an "S" turn
		//if(getTargetAngle()>=0.0){
		return  getTargetDistance()*(Math.sin(getTargetAngle()))*0.5;  //distance needed to make 1/2 of an "S" curve correction for camera to target angle
		//}
		//return  getTargetDistance()*Math.sin(getTargetAngle()*-0.5);  //distance needed to make 1/2 of an "S" curve correction for camera to target angle
	}
	public double getArclengthAngle() {  //arclength needed to eliminate angle between jetson and target
		return getHalfAngleOfTheta()*16.65*2*3.14159/360; //treadwidth*2*PI/360
	}
	public double getCountsAngle() {  //counts needed to eliminate angle between jetson and target 
		return getArclengthAngle()/6/3.14159*500;//arclength/wheel dia/PI*counts per wheel revolution
	}
	public double getWheelRevsAngle() {  //wheel revs needed to eliminate angle between jetson and target
		return getCountsAngle()/500;//counts angle/counts per wheel revolution
	}
	//_________________________________________________________________________________________________________________________	

	
	public double getTotalCounts() {
		return getCountsOffset() + getCountsAngle();
	}
	public double getWheelRevsTotalCounts() {
		return getTotalCounts()/500;//total counts/counts per wheel revolution
	}
	
	
	public double getAngleCorrectionOnlyCounts() {  //counts needed to eliminate only angle to target, does not address offset
		if(getTargetAngle()>=0.0){
		return  getTargetAngle()*16.65*(2/360)/6*500;  //(angle*treadwidth*(2/360)/wheel dia)*counts per revolution
		}
		return  getTargetAngle()*16.65*(2/360)/6*500*-1;  //*-1 to always get positive # of counts
	}
	public double getAngleCorrectionOnlyWheelRevs() {
		return  getAngleCorrectionOnlyCounts()/500;  //counts/counts per revolution
		}

	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

