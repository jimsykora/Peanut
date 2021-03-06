package org.usfirst.frc.team2839.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	
	//PWM-avoid duplicate values
	public static final int DRIVETRAIN_L_MOTOR = 3;
	public static final int DRIVETRAIN_R_MOTOR = 7;
	public static final int CAMERA_VERTICAL_SERVO = 8;
	public static final int CAMERA_HORIZONTAL_SERVO = 9;
	
	//Digital Inputs (DIO)-avoid duplicate values
	public static final int L_Q_ENC_CH_A = 0;//workaround two encoders used by Drivetrain, LeftDrive & RightDrive in ports 0-3
	public static final int L_Q_ENC_CH_B = 1;//workaround two encoders used by Drivetrain, LeftDrive & RightDrive in ports 0-3
	public static final int R_Q_ENC_CH_A = 2;//workaround two encoders used by Drivetrain, LeftDrive & RightDrive in ports 0-3
	public static final int R_Q_ENC_CH_B = 3;//workaround two encoders used by Drivetrain, LeftDrive & RightDrive in ports 0-3
	
//	public static final int L_Q_ENC_CH_A = 4;//workaround two encoders used by Drivetrain, LeftDrive & RightDrive in ports 0-3
//	public static final int L_Q_ENC_CH_B = 5;//workaround two encoders used by Drivetrain, LeftDrive & RightDrive in ports 0-3
//	public static final int R_Q_ENC_CH_A = 6;//workaround two encoders used by Drivetrain, LeftDrive & RightDrive in ports 0-3
//	public static final int R_Q_ENC_CH_B = 7;//workaround two encoders used by Drivetrain, LeftDrive & RightDrive in ports 0-3
	
	//joystick
	public static final int OI_JOYSTICK = 2;
	public static final int JOYSTICK_ROTATE_AXIS = 0; // look at joystick in driver station to pick preferred response
	public static final int JOYSTICK_MOVE_AXIS = 3; 
	
	//Analog inputs-avoid duplicate values
	public static final int IR_DISTANCE_SENSOR = 3;
	public static final int US_DISTANCE_SENSOR = 2;
}
