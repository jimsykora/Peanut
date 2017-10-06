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
public class RightDrive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon Rmotor = null;  //for Peanut base which uses CAN

	Encoder RQEncoder = null;
	
	public RightDrive(){			//added this constructor
		Rmotor = new CANTalon(RobotMap.DRIVETRAIN_R_MOTOR);    //for Peanut base
		Rmotor.enableBrakeMode(true);
		Rmotor.setSafetyEnabled(false);  // ignores feedback from motor that it is running under command
		RQEncoder = new Encoder(RobotMap.R_Q_ENC_CH_A_OFFSET,RobotMap.R_Q_ENC_CH_B_OFFSET);
	}
	
	public void setAngle(double angle){
		Rmotor.set(-angle);
	}
	
	public void resetEncoderCount(){
		RQEncoder.reset();
	}
	public double getREncoderCount(){//this method returns something so we define it as double, if void it would not return anything
		return RQEncoder.get(); // to get encoder directions to match
	}
	public double getREncoderAngle(){//this method returns something so we define it as double, if void it would not return anything
		return (getREncoderInches()/360*2*3.14159);
	}
	public double getREncoderInches(){//this method returns something so we define it as double, if void it would not return anything
		return (getREncoderCount() /(16.38*3.14159)*360);  // =arc length/circumference =arc length/(tread width*PI)*360 degrees
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

