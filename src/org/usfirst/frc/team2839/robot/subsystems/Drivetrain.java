package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotMap;
import org.usfirst.frc.team2839.robot.commands.DriveArcade;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	VictorSP Rmotor = null;
	VictorSP Lmotor = null;
	RobotDrive robotDrive = null;  //added, null because it initially has no value
	Encoder LQEncoder = null;
	Encoder RQEncoder = null;
	public Drivetrain(){			//added this constructor
		Rmotor = new VictorSP(RobotMap.DRIVETRAIN_R_MOTOR);
		Lmotor = new VictorSP(RobotMap.DRIVETRAIN_L_MOTOR);
		robotDrive = new RobotDrive(Lmotor, Rmotor); // must be in this order,LF,LR, RF, RR
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
		return LQEncoder.get();
	}
	public double getREncoderCount(){//this method returns somrthing so we define it as double, if void it would not return anything
		return RQEncoder.get();
	}
	public double getREncoderDistance(){//this method returns somrthing so we define it as double, if void it would not return anything
		return (getREncoderCount() / 500/(6*3.14159));
	}
	public double getLEncoderDistance(){//this method returns somrthing so we define it as double, if void it would not return anything
		return (getLEncoderCount() / 500/(6*3.14159));
	}
	public double getLEncoderRate(){//this method returns somrthing so we define it as double, if void it would not return anything
		return LQEncoder.getRate();
	}
	public double getREncoderRate(){//this method returns somrthing so we define it as double, if void it would not return anything
		return RQEncoder.getRate();
	}
	
	public double getAvgEncDistance() {
		return ((getLEncoderDistance() +getREncoderDistance()*-1)/2);
	}
	public double getDifDistance() {
		return ((getLEncoderDistance()-getREncoderDistance()*-1));
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveArcade());
    }
}

