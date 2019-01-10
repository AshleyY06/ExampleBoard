package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LeftMotor extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static LeftMotor leftMotor;
	private Talon motor;
	private Encoder encoder;
	
	/*
	 * Sets up Talon for left motor
	 */
	private LeftMotor() {
		// Instantiate left motor class
		motor = new Talon(RobotMap.M_LEFT);
		encoder = new Encoder(RobotMap.M_LEFT_ENCODER_CW, RobotMap.M_LEFT_ENCODER_CCW);
	}
	
	/**
	 * Gets the singular instance of the LeftMotor subsystem
	 * @return the singular instance of the LeftMotor subsystem
	 */
	public static LeftMotor getInstance() {
		if (leftMotor == null) leftMotor = new LeftMotor();
		return leftMotor;
	}
	
	public void spinCCW() {
		double speed = -0.5;
		motor.set(speed);
	}
	
	public void spinCW() {
		double speed = 0.5;
		motor.set(speed);
	}
	
	public void spin(double speed) {
		motor.set(speed);
	}
	
	public int getRotations() {
		return encoder.get();
	}
	
	public Encoder getEncoder() {
		return encoder;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new Spin());
    }
    
}
