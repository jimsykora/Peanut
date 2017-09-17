package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotMap;
import org.usfirst.frc.team2839.robot.RobotPreferences;
import org.usfirst.frc.team2839.robot.commands.LeftOffset;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LeftDrive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon Lmotor = null;  //for Peanut base which uses CAN

	Encoder LQEncoder = null;
	
	public LeftDrive(){			//added this constructor
		Lmotor = new CANTalon(RobotMap.DRIVETRAIN_L_MOTOR);    //for Peanut base
		Lmotor.enableBrakeMode(true);
		Lmotor.setSafetyEnabled(false);  // ignores feedback from motor that it is running under command
		LQEncoder = new Encoder(RobotMap.L_Q_ENC_CH_A_OFFSET,RobotMap.L_Q_ENC_CH_B_OFFSET);
	}
	
	public void setAngle(double angle){
		Lmotor.setPosition(getReqdCounts());
	}
	
	public void resetEncoderCount(){
		LQEncoder.reset();
	}
	public double getLEncoderCount(){//this method returns somrthing so we define it as double, if void it would not return anything
		return LQEncoder.get()*-1; // to get encoder directions to match
	}
	public double getCountsPerInch() {
		return 500*1/(6*3.14159);  //(encoder counts per rev)*(gear reduction)/(wheel dia * PI)
	}
	public double getRadians(){
		return Math.acos((1-Math.abs(0.5*Robot.vision.getTargetOffset()/16.65)));  //ArcCos(1-0.5*offset/robot treadwidth)
	}
	public double getArclength() {
		if(Robot.vision.getTargetOffset()>=0.0){
		return (getRadians()*16.65);//radians * robot treadwidth
		}
		return (getRadians()*16.65*-1);
	}
	public double getReqdCounts() {
		return getCountsPerInch()*getRadians();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new LeftOffset(getReqdCounts()));
    }
}

