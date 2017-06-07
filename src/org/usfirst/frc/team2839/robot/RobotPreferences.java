package org.usfirst.frc.team2839.robot;

import edu.wpi.first.wpilibj.Preferences;

public class RobotPreferences {
	//for driving PID
	public static double driveP() {
		return Preferences.getInstance().getDouble("driveP", 5.5);  //add to subsystem PID: "enable method"
	}
	public static double driveI() {
		return Preferences.getInstance().getDouble("driveI", 0.7);
	}
	public static double driveD() {
		return Preferences.getInstance().getDouble("driveD", 9.0);
	}
	public static double driveF() {
		return Preferences.getInstance().getDouble("driveF", 0.0);
	}
	public static double driveMaxSpeed() {
		return Preferences.getInstance().getDouble("driveMaxSpeed", 1.0);
	}
	public static double driveTolerance() {
		return Preferences.getInstance().getDouble("driveTolerance", 0.03);
	}
	
	//for keeping yaw = 0 during drive forward drive
	public static double yawZeroP() {
		return Preferences.getInstance().getDouble("yawP", 0.2); 
	}
	public static double yawZeroI() {
		return Preferences.getInstance().getDouble("yawI", 0.0);
	}
	public static double yawZeroD() {
		return Preferences.getInstance().getDouble("yawD", 0.5);
	}
	public static double yawZeroF() {
		return Preferences.getInstance().getDouble("yawF", 0.0);
	}
	public static double yawZeroMaxSpeed() {
		return Preferences.getInstance().getDouble("yawZeroMaxSpeed", 1.0);
	}
	public static double yawZeroTolerance() {
		return Preferences.getInstance().getDouble("yawZeroTolerance", 1.0);
	}
	
	//for changing yaw during a turn
	public static double yawP() {
		return Preferences.getInstance().getDouble("yawP", 0.1); 
	}
	public static double yawI() {
		return Preferences.getInstance().getDouble("yawI", 0.0);
	}
	public static double yawD() {
		return Preferences.getInstance().getDouble("yawD", 0.1);
	}
	public static double yawF() {
		return Preferences.getInstance().getDouble("yawF", 0.0);
	}
	public static double yawMaxSpeed() {
		return Preferences.getInstance().getDouble("yawMaxSpeed", 1.0);
	}
	public static double yawTolerance() {
		return Preferences.getInstance().getDouble("yawTolerance", 1.5);
	}
	public static int targetCount() {
		return Preferences.getInstance().getInt("targetCount", 5);
	}
	public static double autoDistance() {
		return Preferences.getInstance().getDouble("autoDistance", 10);
	}

}