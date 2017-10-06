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
		//return 5.1;  //5.1 should result in 1/2 wheel rotation on each side for LeftDrivePID
		return 10*table.getNumber("Offset", -99.9); //vision correction
	}
	public double getTargetAngle() { //this is a method
		return table.getNumber("Angle", -99.9);
	}
	
	public double getHalfAngleOfOffset() {
		return  Math.acos((1-(0.5*getTargetOffset()/16.65)))  *0 + 33.0;  //ArcCos(1-0.5*offset/robot treadwidth)
	}
	
	
	public double getRadians(){
		return Math.acos((1-Math.abs(0.5*getTargetOffset()/16.65)));  //ArcCos(1-0.5*offset/robot treadwidth)
	}
	public double getArclength() {
		if(getTargetOffset()>=0.0){
		return (getRadians()*16.65);//radians * robot treadwidth
		}
		return (getRadians()*16.65*-1);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

