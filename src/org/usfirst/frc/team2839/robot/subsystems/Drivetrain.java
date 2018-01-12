package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotMap;
import org.usfirst.frc.team2839.robot.commands.DriveArcade;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.*;
import org.usfirst.frc.team2839.robot.subsystems.Vision;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//VictorSP Rmotor = null;  //for swerve base
	//VictorSP Lmotor = null;
	private CANTalon Rmotor = null;  //for Peanut base which uses CAN
	private CANTalon Lmotor = null;  //for Peanut base which uses CAN
	
	RobotDrive robotDrive = null;  //added, null because it initially has no value
	Encoder LQEncoder = null;
	Encoder RQEncoder = null;
	AnalogInput IRSensor = null;
	AnalogInput USSensor = null;
	Vision TargetDistance = null;
	Vision TargetOffset = null;
	Vision TargetAngleCorrelation = null;
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
		IRSensor = new AnalogInput(RobotMap.IR_DISTANCE_SENSOR);
		USSensor = new AnalogInput(RobotMap.US_DISTANCE_SENSOR);
		
		//setVoltageRampRate(96);  //look into this
	}
	
	public void arcadeDrive(double moveSpeed, double rotateSpeed){ // creates the variable moveSpeed
		robotDrive.arcadeDrive(moveSpeed, rotateSpeed, true);  // true forces a squared input from joystick
	}
	
	public void setSpeed (double speed) {  //for TurnOne & TurnTwo commands
		Rmotor.set(speed);
		Lmotor.set(speed); //TODO maybe invert
	}
	
	public void setLTravel (double speed) {
		Lmotor.set(speed);
	}
	
	public void setRTravel (double speed) {
		Rmotor.set(-speed);
	}
	
	public void resetEncoderCount(){
		LQEncoder.reset();
		RQEncoder.reset();
	}
	
	public void resetREncoderCount(){
		RQEncoder.reset();
	}
	
	public void resetLEncoderCount(){
		LQEncoder.reset();
	}

	public double getLEncoderCount(){//this method returns something so we define it as double, if void it would not return anything
		return LQEncoder.get()*-1; // to get encoder directions to match
	}
	public double getREncoderCount(){//this method returns something so we define it as double, if void it would not return anything
		return RQEncoder.get(); // to get encoder directions to match
	}
	public double getLEncoderDistance(){//this method returns something so we define it as double, if void it would not return anything
		return (getLEncoderCount() *3.14159*6/500/1);  // =count*PI*(wheel diameter)/(encoder counts per rev)/(gear ratio, encoder to wheel)
	}
	public double getREncoderDistance(){//this method returns something so we define it as double, if void it would not return anything
		return (getREncoderCount() *3.14159*6/500/1);  // =count*PI*(wheel diameter)/(encoder counts per rev)/(gear ratio, encoder to wheel)
	}
	public double getLEncoderRate(){//this method returns something so we define it as double, if void it would not return anything
		return LQEncoder.getRate()*-1; //to get same rate direction
	}
	public double getREncoderRate(){//this method returns something so we define it as double, if void it would not return anything
		return RQEncoder.getRate();
	}
	public double getDifRate() {
		return ((-getLEncoderRate()-getREncoderRate()));
	}
	
	public double getAvgEncDistance() {
		return ((getLEncoderDistance() +getREncoderDistance())/2);
	}
	public double getDifDistance() {
		return ((getLEncoderDistance()-getREncoderDistance()));
	}
	public double getLEncoderAngle(){//this method returns something so we define it as double, if void it would not return anything
		return (-getLEncoderDistance() /(16.38*3.14159)*360);  // =arc length/circumference =arc length/(tread width*PI)*360 degrees
	}
	public double getREncoderAngle(){//this method returns something so we define it as double, if void it would not return anything
		return (getREncoderDistance() /(16.38*3.14159)*360);  // =arc length/circumference =arc length/(tread width*PI)*360 degrees
	}
	public double getAvgEncAngle() {
		return ((getLEncoderAngle() +getREncoderAngle())/2);
	}
	public double getIRSensorVoltage(){//this method returns something so we define it as double, if void it would not return anything
		return IRSensor.getAverageVoltage();
	}	
	public double getUSSensorVoltage(){//this method returns something so we define it as double, if void it would not return anything
		return USSensor.getAverageVoltage();
	}
	
	// OFFESET PID
	public double getREncoderInchesOffset(){//this method returns something so we define it as double, if void it would not return anything
		return (getREncoderCount() /(16.38*3.14159)*360);  // =arc length/circumference =arc length/(tread width*PI)*360 degrees
	}
	public double getREncoderAngleOffset(){//this method returns something so we define it as double, if void it would not return anything
		return (getREncoderInchesOffset()/360*2*3.14159);
	}
	
	public double getLEncoderInchesOffset(){//this method returns something so we define it as double, if void it would not return anything
		return (getLEncoderCount() /(16.65*3.14159)*360);  // =arc length/circumference =arc length/(tread width*PI)*360 degrees
	}
	public double getLEncoderAngleOffset(){//this method returns something so we define it as double, if void it would not return anything
		return (getLEncoderInchesOffset()/360*2*3.14159);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveArcade());
    }
}

