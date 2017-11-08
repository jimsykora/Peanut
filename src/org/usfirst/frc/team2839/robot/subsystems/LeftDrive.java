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
	
	public void setTravel(double travel){
		Lmotor.set(travel);
	}
	
	public void resetEncoderCount(){
		LQEncoder.reset();
	}
	public double getLEncoderCount(){//this method returns something so we define it as double, if void it would not return anything
		return LQEncoder.get()*-1; // to get encoder directions to match
	}
	public double getLEncoderInches(){//this method returns something so we define it as double, if void it would not return anything
		return (getLEncoderCount() /(16.65*3.14159)*360);  // =arc length/circumference =arc length/(tread width*PI)*360 degrees
	}
	public double getLEncoderAngle(){//this method returns something so we define it as double, if void it would not return anything
		return (getLEncoderInches()/360*2*3.14159);
	}
	//public double getLEncoderInches(){//this method returns something so we define it as double, if void it would not return anything
		//return (getLEncoderCount() /(16.65*3.14159)*360);  // =arc length/circumference =arc length/(tread width*PI)*360 degrees
	//}
	
    public void initDefaultCommand() {
    	//double counts = 0;
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new LeftOffset(counts)); //commented out to help DRiveArcade & LeftTurn subsystem
    }
}

