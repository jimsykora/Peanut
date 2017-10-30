package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotMap;
import org.usfirst.frc.team2839.robot.commands.TurnCCW;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LeftTurn extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon Lmotor = null;  //for Peanut base which uses CAN
	
	public LeftTurn(){			//added this constructor
		Lmotor = new CANTalon(RobotMap.DRIVETRAIN_L_MOTOR);    //for Peanut base
		Lmotor.enableBrakeMode(true);
		Lmotor.setSafetyEnabled(false);  // ignores feedback from motor that it is running under command
	}

	public void setSpeed(double speed){
		Lmotor.set(0.63); //tweak this # along with the divisor in autonomous as needed when robot is on carpet
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

