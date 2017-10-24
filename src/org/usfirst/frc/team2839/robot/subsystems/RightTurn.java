package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.RobotMap;
import org.usfirst.frc.team2839.robot.commands.TurnOne;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RightTurn extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon Rmotor = null;  //for Peanut base which uses CAN
	
	public RightTurn(){			//added this constructor
		Rmotor = new CANTalon(RobotMap.DRIVETRAIN_R_MOTOR);    //for Peanut base
		Rmotor.enableBrakeMode(true);
		Rmotor.setSafetyEnabled(false);  // ignores feedback from motor that it is running under command
	}

	public void setSpeed(double speed){
		Rmotor.set(-0.4);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new TurnOne(0.0));
    }
}

