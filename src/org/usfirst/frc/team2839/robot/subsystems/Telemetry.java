package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Telemetry extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Telemetry(){
		
	}
	public void update() {
		SmartDashboard.putNumber("L Encoder Count", Robot.drivetrain.getLEncoderCount());
		SmartDashboard.putNumber("L Encoder Distance", Robot.drivetrain.getLEncoderDistance());
		SmartDashboard.putNumber("L Encoder Rate", Robot.drivetrain.getLEncoderRate());
		SmartDashboard.putNumber("R Encoder Count", Robot.drivetrain.getREncoderCount());
		SmartDashboard.putNumber("R Encoder Distance", Robot.drivetrain.getREncoderDistance());
		SmartDashboard.putNumber("R Encoder Rate", Robot.drivetrain.getREncoderRate());
		SmartDashboard.putNumber("AvgDistance", Robot.drivetrain.getAvgEncDistance());
		SmartDashboard.putNumber("DifDistance", Robot.drivetrain.getDifDistance());

		SmartDashboard.putNumber("NavX yaw", Robot.navXMicro.getYaw());
		SmartDashboard.putNumber("NavX pitch", Robot.navXMicro.getPitch());
		SmartDashboard.putNumber("NavX roll", Robot.navXMicro.getRoll());
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

