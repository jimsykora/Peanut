package org.usfirst.frc.team2839.robot.subsystems;

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
		return 5.1;  //5.1 should result in 1/2 wheel rotation on each side for LeftDrivePID
		//return table.getNumber("Offset", -99.9);
	}
	public double getTargetAngle() { //this is a method
		return table.getNumber("Angle Correlation", -99.9);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

