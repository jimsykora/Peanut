package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class LeftDrivePID extends PIDSubsystem {
	double output = 0.0;
	boolean outputValid = false;
	int targetCounter = 0;  //remove later if/when PID loop gets tuned properly. its used to delay turning off PID loop while in motion
	double tolerance = 0.0;
    // Initialize your subsystem here
	
    public LeftDrivePID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super(0,0,0);
    	this.setSetpoint(0.0);
    }
    public  void enable()  {
    	this.getPIDController().setPID(RobotPreferences.angleP(), RobotPreferences.angleI(), RobotPreferences.angleD());
    	double maxSpeed = RobotPreferences.angleMaxSpeed();
    	this.setOutputRange(-maxSpeed, maxSpeed);
    	outputValid = false;
    	super.enable();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Robot.leftDrive.getLEncoderCount();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	this.output = output;
    	outputValid = true;
    }
    public double getOutput() {
    	if(this.getPIDController().isEnabled() == false || outputValid == false) { // == means "is equal to", || means "or"
    		return 0.0;
    	}
    	//output = output*0.8;// find min & max multipliers for stable operation, then put min and max-min in equation below
    	//output = output*Math.sin(Robot.drivetrainAnglePID.getElapsedTime()*(180/RobotPreferences.angleSineTime()/57.3))*0.5+(0.8-0.5);//output should start at 0.5 then cycle sinewave up to 0.8 & back to 0.5
    	return output;
    }
    public void setRawTolerance(double tolerance) {
    	this.tolerance = tolerance;
    }
    public boolean onRawTargrt() {
    	if(Math.abs(getPIDController().getSetpoint() - Robot.leftDrive.getLEncoderCount()) < tolerance) {
    		targetCounter = targetCounter +1;
    	}
    	else {
    		targetCounter = 0;
    	}
    	return (targetCounter >= RobotPreferences.targetCount());
    }
}
