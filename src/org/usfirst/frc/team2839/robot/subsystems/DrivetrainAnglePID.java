package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class DrivetrainAnglePID extends PIDSubsystem {
	double output = 0.0;
	boolean outputValid = false;
	int targetCounter = 0;  //remove later if/when PID loop gets tuned properly. its used to delay turning off PID loop while in motion
	double tolerance = 0.0;
	Timer Et = null;  //elapsed time
	
    // Initialize your subsystem here
    public DrivetrainAnglePID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super(0,0,0);
    	this.setSetpoint(0.0);
    	Et = new Timer();
    	}
    
    public  void enable()  {
    	Robot.drivetrainAnglePID.resetTimer();
    	this.getPIDController().setPID(RobotPreferences.angleP(), RobotPreferences.angleI(), RobotPreferences.angleD());
    	double maxSpeed = RobotPreferences.angleMaxSpeed();
    	this.setOutputRange(-maxSpeed, maxSpeed);
    	outputValid = false;
    	super.enable();
    }
    
    public void resetTimer(){
		Et.reset();
		Et.start();
	}
    public double getElapsedTime() {
		return Et.get();
	}
	/*public double getRamp() {
		return Et.get()/2;
	}*/
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Robot.drivetrain.getAvgEncAngle();
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
    	//output=output*Robot.drivetrainAnglePID.getRamp();
    	output=output*Math.sqrt((Robot.drivetrainAnglePID.getElapsedTime())/(Robot.drivetrainAnglePID.getElapsedTime()+0.2)); //soft start
    	return output;
    }
    public void setRawTolerance(double tolerance) {
    	this.tolerance = tolerance;
    }
    public boolean onRawTargrt() {
    	if(Math.abs(getPIDController().getSetpoint() - Robot.drivetrain.getAvgEncAngle()) < tolerance) {
    		targetCounter = targetCounter +1;
    	}
    	else {
    		targetCounter = 0;
    	}
    	return (targetCounter >= RobotPreferences.targetCount());
    }
}
