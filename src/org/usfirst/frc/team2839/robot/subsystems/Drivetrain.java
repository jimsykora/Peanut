package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.RobotMap;
import org.usfirst.frc.team2839.robot.commands.DriveArcade;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.*;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//VictorSP Rmotor = null;  //for swerve base
	//VictorSP Lmotor = null;
	private CANTalon Rmotor = null;  //for Peanut base
	private CANTalon Lmotor = null;  //for Peanut base
	
	RobotDrive robotDrive = null;  //added, null because it initially has no value
	Encoder LQEncoder = null;
	Encoder RQEncoder = null;
	public Drivetrain(){			//added this constructor
		//Rmotor = new VictorSP(RobotMap.DRIVETRAIN_R_MOTOR);
		//Lmotor = new VictorSP(RobotMap.DRIVETRAIN_L_MOTOR);
		Rmotor = new CANTalon(RobotMap.DRIVETRAIN_R_MOTOR);    //for Peanut base
		Lmotor = new CANTalon(RobotMap.DRIVETRAIN_L_MOTOR);    //for Peanut base
		Rmotor.enableBrakeMode(true);
		Lmotor.enableBrakeMode(true);
		robotDrive = new RobotDrive(Lmotor, Rmotor); // must be in this order,LF,LR, RF, RR
		robotDrive.setSafetyEnabled(false);  // ignores feedback from motor that it is running under command
		LQEncoder = new Encoder(RobotMap.L_Q_ENC_CH_A,RobotMap.L_Q_ENC_CH_B);
		RQEncoder = new Encoder(RobotMap.R_Q_ENC_CH_A,RobotMap.R_Q_ENC_CH_B);
	}
	
	public void arcadeDrive(double moveSpeed, double rotateSpeed){ // creates the variable moveSpeed
		robotDrive.arcadeDrive(moveSpeed, rotateSpeed, true);  // true forces a squared input from joystick
	}
	
	public void resetEncoderCount(){
		LQEncoder.reset();
		RQEncoder.reset();
	}
	public double getLEncoderCount(){//this method returns somrthing so we define it as double, if void it would not return anything
		return LQEncoder.get()*-1; // to get encoder directions to match
	}
	public double getREncoderCount(){//this method returns somrthing so we define it as double, if void it would not return anything
		return RQEncoder.get(); // to get encoder directions to match
	}
	public double getLEncoderDistance(){//this method returns somrthing so we define it as double, if void it would not return anything
		return (getLEncoderCount() *3.14159*6/500/1);  // =count*PI*(wheel diameter)/(encoder counts per rev)/(gear ratio, encoder to wheel)
	}
	public double getREncoderDistance(){//this method returns somrthing so we define it as double, if void it would not return anything
		return (getREncoderCount() *3.14159*6/500/1);  // =count*PI*(wheel diameter)/(encoder counts per rev)/(gear ratio, encoder to wheel)
	}
	public double getLEncoderRate(){//this method returns somrthing so we define it as double, if void it would not return anything
		return LQEncoder.getRate()*-1; //to get same rate direction
	}
	public double getREncoderRate(){//this method returns somrthing so we define it as double, if void it would not return anything
		return RQEncoder.getRate();
	}
	
	public double getAvgEncDistance() {
		return ((getLEncoderDistance() +getREncoderDistance())/2);
	}
	public double getDifDistance() {
		return ((getLEncoderDistance()-getREncoderDistance()));
	}
	public double getLEncoderAngle(){//this method returns somrthing so we define it as double, if void it would not return anything
		return (-getLEncoderDistance() /(16.38*3.14159)*360);  // =arc length/circumference =arc length/(tread width*PI)*360 degrees
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveArcade());
    }
}

