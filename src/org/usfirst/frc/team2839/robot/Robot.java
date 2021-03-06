
package org.usfirst.frc.team2839.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2839.robot.commands.AutonomousCommand;
import org.usfirst.frc.team2839.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2839.robot.subsystems.DrivetrainAnglePID;
import org.usfirst.frc.team2839.robot.subsystems.DrivetrainCameraPID;
import org.usfirst.frc.team2839.robot.subsystems.DrivetrainDistancePID;
import org.usfirst.frc.team2839.robot.subsystems.LeftDrivePID;
import org.usfirst.frc.team2839.robot.subsystems.LeftTurn;
import org.usfirst.frc.team2839.robot.subsystems.NavXMicro;
import org.usfirst.frc.team2839.robot.subsystems.RightDrivePID;
import org.usfirst.frc.team2839.robot.subsystems.RightTurn;
import org.usfirst.frc.team2839.robot.subsystems.Telemetry;
import org.usfirst.frc.team2839.robot.subsystems.Vision;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static  Drivetrain drivetrain;  //Drivetrain was System, new Drivetrain() was null
	public static  NavXMicro navXMicro;
	public static Vision vision;
	public static Telemetry telemetry; //Smart Dashboard & OI must be at the end
	public static OI oi;
	public static DrivetrainDistancePID drivetrainDistancePID;
	public static DrivetrainAnglePID drivetrainAnglePID;
	public static DrivetrainCameraPID drivetrainCameraPID;
	public static LeftDrivePID leftDrivePID;
	public static RightDrivePID rightDrivePID;
	public static LeftTurn leftTurn;
	public static RightTurn rightTurn;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		navXMicro = new NavXMicro();
		vision = new Vision();
//		CameraServer.getInstance().startAutomaticCapture();
		drivetrainDistancePID = new DrivetrainDistancePID();
		drivetrainAnglePID = new DrivetrainAnglePID();
		drivetrainCameraPID = new DrivetrainCameraPID();
		leftDrivePID = new LeftDrivePID();
		rightDrivePID = new RightDrivePID();
		leftTurn = new LeftTurn();
		rightTurn = new RightTurn();
		
			
		chooser.addDefault("Default Auto", new AutonomousCommand());    /////////////
		//chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		telemetry  = new Telemetry(); //Smart Dashboard & OI must be at the end
		oi = new OI();
		//chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		telemetry.update();  //makes SmartDashboard live when in disabled mode
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = new AutonomousCommand();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		telemetry.update();  //makes SmartDashboard live when in disabled mode
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		telemetry.update();  //makes SmartDashboard live when in disabled mode
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		
		//motor.set(joystick.getY();   /////an attempt to implement setVoltageRamp() method
	}
}
