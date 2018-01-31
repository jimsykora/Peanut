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
		return (table.getNumber("Distance", -99.9))*1.4  *0.0  +46.6; //-.04; //multiplier & adder are to get slope & intercept correct (Jetson needs calibration)
	}
	public double getTargetOffset() { //left/right distance between camera center and center of two vertical, reflective targets
		return (table.getNumber("Offset", -99.9))*21.7  +0.4; //multiplier & adder are to get slope & intercept correct (Jetson needs calibration)
	}
	public double getTargetAngle() { //angle between the plane of two vertical, reflective targets and camera mounting plane
		return (table.getNumber("Angle", -99.9))*1.7 +2.5; //multiplier & adder are to get slope & intercept correct (Jetson needs calibration)
	} 
//___________________________________________________________________________________________________________________________
	
	public double getHalfAngleOfOffset() {  //always positive angle, in degrees, needed for a single drive wheel to make 1/2 of an "S" turn
		if(getTargetOffset()>=0.0)  //may be plus or minus
			return (Math.acos(1-(0.5*getTargetOffset()/16.65)))*57.3;  //ArcCos(1-0.5*offset/robot treadwidth)*degrees/radian
		else
			return  (Math.acos(1+(0.5*getTargetOffset()/16.65)))*57.3;  //ArcCos(1-0.5*offset/robot treadwidth)*degrees/radian
	}
	public double getArclengthOffset() {  //arclength of 1/2 of an "S" turn, always positive
		return getHalfAngleOfOffset()*16.65/57.3; //treadwidth*degrees/radian
	}
	public double getCountsOffset() {  //counts needed to make 1/2 of an "S" turn, may be plus or minus
		if(getTargetOffset()>=0.0)  //may be plus or minus
			return getArclengthOffset()/6/3.14159*500;//arclength/wheel dia/PI*counts per wheel revolution
		else 
			return getArclengthOffset()/6/3.14159*500 *-1;//arclength/wheel dia/PI*counts per wheel revolution
	}
	public double getWheelRevsOffset() {  //wheel revs needed to make 1/2 of an "S" turn, may be plus or minus
		return getCountsOffset()/500;//counts offset/counts per wheel revolution
	}
//_________________________________________________________________________________________________________________________	

	public double getEquivalentAngleOffset() {  //, may be plus or minus
		return getTargetDistance()*(Math.sin(getTargetAngle()/57.3)); //distance needed to make 1/2 of an "S" curve correction for camera to target angle (57.3 degrees/radian)
	}
	public double getHalfAngleOfTheta() {   //always positive angle, in degrees, needed for a single drive wheel to make 1/2 of an "S" turn
		if(getTargetAngle()>=0.0)
			return (Math.acos(1-(0.5*getEquivalentAngleOffset()/16.65)))*57.3;  //ArcCos(1-0.5*offset/robot treadwidth)*degrees/radian
		else 
			return (Math.acos(1+(0.5*getEquivalentAngleOffset()/16.65)))*57.3;  //ArcCos(1-0.5*offset/robot treadwidth)*degrees/radian
	}
	public double getArclengthAngle() {  //arclength of 1/2 of an "S" turn, always positive
		return getHalfAngleOfTheta()*16.65/57.3; //treadwidth*degrees/radian
	}
	public double getCountsAngle() {  //counts needed to make 1/2 of an "S" turn, may be plus or minus
		if(getTargetAngle()>=0.0)
			return getArclengthAngle()/6/3.14159*500;//arclength/wheel dia/PI*counts per wheel revolution
		else 
			return getArclengthAngle()/6/3.14159*500 *-1;//arclength/wheel dia/PI*counts per wheel revolution
	}
	public double getWheelRevsAngle() {  //wheel revs needed to make 1/2 of an "S" turn, may be plus or minus
		return getCountsAngle()/500;//counts angle/counts per wheel revolution
	}
	//_________________________________________________________________________________________________________________________	

	
	public double getTotalCounts() { //may be plus or minus
		return getCountsOffset() + getCountsAngle();
	}
	public double getWheelRevsTotalCounts() { //may be plus or minus
		return getTotalCounts()/500;//total counts/counts per wheel revolution
	}
	
	
	public double getAngleCorrectionOnlyCounts() {  //counts needed to eliminate only angle to target, does not address offset, always positive
		if(getTargetAngle()>=0.0)
			return getTargetAngle()*16.65*2/360/6*500;  //(angle*treadwidth*(2/360)/wheel dia)*counts per revolution
		else
			return  getTargetAngle()*16.65*2/360/6*500*-1;  //*-1 to always get positive # of counts
	}
	public double getAngleCorrectionOnlyWheelRevs() { //always positive
		return  getAngleCorrectionOnlyCounts()/500;  //counts/counts per revolution
		}

	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

