package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotMap;
import org.usfirst.frc.team2839.robot.commands.LeftOffset;
import org.usfirst.frc.team2839.robot.commands.RightOffset;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RightDrive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon Rmotor = null;  //for Peanut base which uses CAN

	Encoder RQEncoder = null;
	
	public RightDrive(){			//added this constructor
		Rmotor = new CANTalon(RobotMap.DRIVETRAIN_R_MOTOR);    //for Peanut base
		Rmotor.enableBrakeMode(true);
		Rmotor.setSafetyEnabled(false);  // ignores feedback from motor that it is running under command
		RQEncoder = new Encoder(RobotMap.R_Q_ENC_CH_A,RobotMap.R_Q_ENC_CH_B);
	}
	
	public void setAngle(double angle){
		Rmotor.setPosition(getReqdCounts());
	}
	
	public void resetEncoderCount(){
		RQEncoder.reset();
	}
	public double getREncoderCount(){//this method returns somrthing so we define it as double, if void it would not return anything
		return RQEncoder.get()*-1; // to get encoder directions to match
	}
	public double getCountsPerInch() {
		return 500*1/(6*3.14159);  //(encoder counts per rev)*(gear reduction)/(wheel dia * PI)
	}
	public double getRadians(){
		return Math.acos((1-Math.abs(0.5*Robot.vision.getTargetOffset()/16.65)));  //ArcCos(1-0.5*offset/robot treadwidth)
	}
	public double getArclength() {
		if(Robot.vision.getTargetOffset()>=0.0){
		return (getRadians()*16.65)*-1;//radians * robot treadwidth
		}
		return (getRadians()*16.65);
	}
	public double getReqdCounts() {
		return getCountsPerInch()*getRadians();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new RightOffset(Robot.leftDrive.getLEncoderCount()));
    }
}

