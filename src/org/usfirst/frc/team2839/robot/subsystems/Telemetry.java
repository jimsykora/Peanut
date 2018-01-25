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
		//SmartDashboard.putNumber("LD Encoder Angle", Robot.leftDrive.getLEncoderAngle());
		SmartDashboard.putNumber("L Encoder Distance", Robot.drivetrain.getLEncoderDistance());
		SmartDashboard.putNumber("L Encoder Rate", Robot.drivetrain.getLEncoderRate());
		SmartDashboard.putNumber("L Encoder Angle", Robot.drivetrain.getLEncoderAngle());
		SmartDashboard.putNumber("R Encoder Count", Robot.drivetrain.getREncoderCount());
		SmartDashboard.putNumber("RD Encoder Angle", Robot.drivetrain.getREncoderAngleOffset());
		SmartDashboard.putNumber("R Encoder Distance", Robot.drivetrain.getREncoderDistance());
		SmartDashboard.putNumber("R Encoder Rate", Robot.drivetrain.getREncoderRate());
		SmartDashboard.putNumber("R Encoder Angle", Robot.drivetrain.getREncoderAngle());
		
		SmartDashboard.putNumber("DifRate", Robot.drivetrain.getDifRate());
		SmartDashboard.putNumber("AvgDistance", Robot.drivetrain.getAvgEncDistance());
		SmartDashboard.putNumber("DifDistance", Robot.drivetrain.getDifDistance());
		SmartDashboard.putNumber("AvgAngle", Robot.drivetrain.getAvgEncAngle());
		SmartDashboard.putNumber("IRVoltage", Robot.drivetrain.getIRSensorVoltage());
		SmartDashboard.putNumber("USVoltage", Robot.drivetrain.getUSSensorVoltage());
		
		SmartDashboard.putNumber("JetsonAngle", Robot.vision.getTargetAngle());
		SmartDashboard.putNumber("JetsonDistance", Robot.vision.getTargetDistance());
		SmartDashboard.putNumber("JetsonOffset", Robot.vision.getTargetOffset());
		SmartDashboard.putNumber("Offset degrees", Robot.vision.getHalfAngleOfOffset());
		SmartDashboard.putNumber("Offset arclength", Robot.vision.getArclengthOffset());
		SmartDashboard.putNumber("Offset counts", Robot.vision.getCountsOffset());
		SmartDashboard.putNumber("Offset wheel revs", Robot.vision.getWheelRevsOffset());
		SmartDashboard.putNumber("Angle degrees", Robot.vision.getHalfAngleOfTheta());
		SmartDashboard.putNumber("Angle arclength", Robot.vision.getArclengthAngle());
		SmartDashboard.putNumber("Angle counts", Robot.vision.getCountsAngle());
		SmartDashboard.putNumber("Angle wheel revs", Robot.vision.getWheelRevsAngle());
		SmartDashboard.putNumber("Total counts", Robot.vision.getTotalCounts());
		SmartDashboard.putNumber("Total counts wheel revs", Robot.vision.getWheelRevsTotalCounts());
		SmartDashboard.putNumber("Angle correction only counts", Robot.vision.getAngleCorrectionOnlyCounts());
		SmartDashboard.putNumber("Angle correction only wheel revs", Robot.vision.getAngleCorrectionOnlyWheelRevs());

		SmartDashboard.putNumber("Navx vs Encoder Angle", (Robot.navXMicro.getYaw()-Robot.drivetrain.getAvgEncAngle()));
		SmartDashboard.putNumber("NavX yaw", Robot.navXMicro.getYaw());
		SmartDashboard.putNumber("NavX pitch", Robot.navXMicro.getPitch());
		SmartDashboard.putNumber("NavX roll", Robot.navXMicro.getRoll());
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

