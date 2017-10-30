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
	public double getTargetDistance() { //this is a method
		return table.getNumber("Distance", -99.9);
	}
	public double getTargetOffset() { //this is a method
		//return 5.2;  //5.2 should result in 1/2 wheel rotation & 50 counts on each side for LeftDrivePID
		return table.getNumber("Offset", -99.9)*39.37; //vision correction
	}
	public double getTargetAngle() { //this is a method
		return table.getNumber("Angle", -99.9);
	}
		
	public double getHalfAngleOfOffset() {  //angle in degrees
		if(getTargetOffset()>=0.0){
		return  Math.acos(1-(0.5*getTargetOffset()/16.65))*360/2/3.14159;  //ArcCos(1-0.5*offset/robot treadwidth)
		}
		return  Math.acos(1-(0.5*getTargetOffset()*-1.0/16.65))*360/2/3.14159;  //ArcCos(1-0.5*offset/robot treadwidth)
	}
	/*public double getRadians(){
		return Math.acos((1-Math.abs(0.5*getTargetOffset()/16.65)));  //ArcCos(1-0.5*offset/robot treadwidth)
	}*/
	public double getArclength() {
		return getHalfAngleOfOffset()*16.65*2*3.14159/360;
	}
	public double getCounts() {
		return getArclength()/6/3.14159*500;//arclength/wheel dia/PI*counts per wheel revolution
	}
	public double getWheelRevs() {
		return getCounts()/500;//arclength/counts per wheel revolution
	}
	    
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

