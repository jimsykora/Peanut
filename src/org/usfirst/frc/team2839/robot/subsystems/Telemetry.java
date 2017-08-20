package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.commands.ResetEncoders;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Telemetry extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Telemetry(){
		SmartDashboard.putData("reset encoders", new ResetEncoders());
		
	}
	public void update() {
		SmartDashboard.putNumber("L Encoder Count", Robot.drivetrain.getLEncoderCount());
		SmartDashboard.putNumber("L Encoder Distance", Robot.drivetrain.getLEncoderDistance());
		SmartDashboard.putNumber("L Encoder Rate", Robot.drivetrain.getLEncoderRate());
		SmartDashboard.putNumber("L Encoder Angle", Robot.drivetrain.getLEncoderAngle());
		SmartDashboard.putNumber("R Encoder Count", Robot.drivetrain.getREncoderCount());
		SmartDashboard.putNumber("R Encoder Distance", Robot.drivetrain.getREncoderDistance());
		SmartDashboard.putNumber("R Encoder Rate", Robot.drivetrain.getREncoderRate());
		SmartDashboard.putNumber("R Encoder Angle", Robot.drivetrain.getREncoderAngle());
		
		SmartDashboard.putNumber("DifRate", Robot.drivetrain.getDifRate());
		SmartDashboard.putNumber("AvgDistance", Robot.drivetrain.getAvgEncDistance());
		SmartDashboard.putNumber("DifDistance", Robot.drivetrain.getDifDistance());
		SmartDashboard.putNumber("AvgAngle", Robot.drivetrain.getAvgEncAngle());
		SmartDashboard.putNumber("IRVoltage", Robot.drivetrain.getIRSensorVoltage());
		SmartDashboard.putNumber("USVoltage", Robot.drivetrain.getUSSensorVoltage());
		
		SmartDashboard.putNumber("Navx vs Encoder Angle", (Robot.navXMicro.getYaw()-Robot.drivetrain.getAvgEncAngle()));

		SmartDashboard.putNumber("NavX yaw", Robot.navXMicro.getYaw());
		SmartDashboard.putNumber("NavX pitch", Robot.navXMicro.getPitch());
		SmartDashboard.putNumber("NavX roll", Robot.navXMicro.getRoll());
		//SmartDashboard.putNumber("ET", Robot.drivetrainDistancePID.Et.get());
		//SmartDashboard.putNumber("output, D", Robot.drivetrainDistancePID.output);
		//SmartDashboard.putNumber("output, A", Robot.drivetrainAnglePID.output);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

