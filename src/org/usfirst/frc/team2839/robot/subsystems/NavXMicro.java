package org.usfirst.frc.team2839.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NavXMicro extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public  static AHRS ahrs = null;
	
	public NavXMicro(){
		try {
			ahrs = new AHRS(I2C.Port.kOnboard);
		} catch (RuntimeException ex) {
			DriverStation.reportError("NavX error" + ex.getMessage(), true);
		}
	}

	public double getYaw() {
		return ahrs.getYaw();
	}
	
	public void resetYaw() {
		// reset the yaw
		ahrs.reset();
		
		// wait for 1/4 seconds to allow the NavX to reset the yaw
		try {
			Thread.sleep(250);
		}
		catch (InterruptedException e) {
		}
		
		// make the reset yaw position be the zero yaw position
		ahrs.zeroYaw();
	}
	
	public double getPitch() {
		return ahrs.getPitch();
	}
	public double getRoll() {
		return ahrs.getRoll();
	}
	public boolean isCalibrating() {
		return ahrs.isCalibrating();
	}
//	public double getDisplacementX() {
//		return ahrs.getDisplacementX();
//	}
//	public void resetX() {
//		// reset the distance
//		ahrs.resetDisplacement();
//	}
//	public double getRawGyroX() {
//		return ahrs.getRawGyroX();
//	}
//	public double getAccel() {
//		return ahrs.getRawAccelX();
//	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

