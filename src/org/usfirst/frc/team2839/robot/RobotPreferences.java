package org.usfirst.frc.team2839.robot;

import edu.wpi.first.wpilibj.Preferences;

public class RobotPreferences {
	//for driving PID
	public static double driveP() {
		return Preferences.getInstance().getDouble("driveP", 0.05);  //add to subsystem PID: "enable method"
	}
	public static double driveI() {
		return Preferences.getInstance().getDouble("driveI", 0.0);
	}
	public static double driveD() {
		return Preferences.getInstance().getDouble("driveD", 0.005);
	}
	public static double driveF() {
		return Preferences.getInstance().getDouble("driveF", 0.0);
	}
	public static double driveMaxSpeed() {
		return Preferences.getInstance().getDouble("driveMaxSpeed", 1.0);
	}
	public static double driveTolerance() {
		return Preferences.getInstance().getDouble("driveTolerance", 8.0);
	}
	
	//for angle PID
	public static double angleP() {
		return Preferences.getInstance().getDouble("angleP", 0.02);  //add to subsystem PID: "enable method"
	}
	public static double angleI() {
		return Preferences.getInstance().getDouble("angleI", 0.0);
	}
	public static double angleD() {
		return Preferences.getInstance().getDouble("angleD", 0.005);
	}
	public static double angleF() {
		return Preferences.getInstance().getDouble("angleF", 0.0);
	}
	public static double angleMaxSpeed() {
		return Preferences.getInstance().getDouble("angleMaxSpeed", 0.5);
	}
	public static double angleTolerance() {
		return Preferences.getInstance().getDouble("angleTolerance", 4.0);
	}
	
	
	public static int targetCount() {
		return Preferences.getInstance().getInt("targetCount", 1);
	}
	
	//auto
	public static double autoDistance() {
		return Preferences.getInstance().getDouble("autoDistance", 100);  //38 is about 2 revolutions
	}
	public static double driveSineTime() {
		return Preferences.getInstance().getDouble("driveSineTime", 1.0);  //max time, or less, to go auto distance
	}
	public static double autoAngle() {
		return Preferences.getInstance().getDouble("autoAngle", 180);  //38 is about 2 revolutions
	}
	public static double angleSineTime() {
		return Preferences.getInstance().getDouble("angleSineTime", 0.7);  //max time, or less, to go auto angle
	}
	public static double autoCorrectDistance() {
		return Preferences.getInstance().getDouble("autoCorrectDistance", 0.3);  //to tweak the relative motor speeds
	}
	public static double autoCorrectAngle() {
		return Preferences.getInstance().getDouble("autoCorrectAngle", 0.00047);  //to tweak the relative motor speeds
	}
	public static double autoCorrectJetson() {
		return Preferences.getInstance().getDouble("autoCorrectJetson", 0.3);  //to tweak the relative motor speeds
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
}