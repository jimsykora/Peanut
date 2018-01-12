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
		return table.getNumber("Distance", -99.9)*36/25;
	}
	public double getTargetOffset() { //eft/right distance between camera center and center of two vertical, reflective targets
		//return 5.2;  //5.2 should result in 1/2 wheel rotation & 50 counts on each side for LeftDrivePID
		return table.getNumber("Offset", -99.9)*10; //*vision correction----Jetson needs calibration
	}
	public double getTargetAngle() { //angle between the plane of two vertical, reflective targets and camera mounting plane
		return table.getNumber("Angle", -99.9);
	}
		
	public double getHalfAngleOfOffset() {  //angle, in degrees, needed for a single drive wheel to make 1/2 of an "S" turn
		if(getTargetOffset()>=0.0){
		return  Math.acos(1-(0.5*getTargetOffset()/16.65))*360/2/3.14159;  //ArcCos(1-0.5*offset/robot treadwidth)
		}
		return  Math.acos(1-(0.5*getTargetOffset()*-1.0/16.65))*360/2/3.14159;  //ArcCos(1-0.5*offset/robot treadwidth)
	}
	public double getArclength() {  //arclength of 1/2 of an "S" turn
		return getHalfAngleOfOffset()*16.65*2*3.14159/360;
	}
	public double getCounts() {  //counts needed to make 1/2 of an "S" turn
		return getArclength()/6/3.14159*500;//arclength/wheel dia/PI*counts per wheel revolution
	}
	public double getWheelRevs() {  //wheel revs needed to make 1/2 of an "S" turn
		return getCounts()/500;//arclength/counts per wheel revolution
	}
	
	public double getAngleCorrectionHalfDistance() {  //angle, in degrees, needed for a single drive wheel to make 1/2 of an "S" turn
		if(getTargetAngle()>=0.0){
		return  getTargetDistance()*Math.sin(getTargetAngle()*0.5);  //distance needed to make 1/2 of an "S" curve correction for camera to target angle
		}
		return  getTargetDistance()*Math.sin(getTargetAngle()*-0.5);  //distance needed to make 1/2 of an "S" curve correction for camera to target angle
		}
	    
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

