package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Piston extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static Piston piston;
	Compressor compressor;
	DoubleSolenoid valve;
	
	/*
	 * Sets up Compressor for the piston
	 */
	private Piston() {
		// Instantiate left motor class
		compressor = new Compressor(RobotMap.P_PISTON);
		compressor.setClosedLoopControl(true);
		valve = new DoubleSolenoid(RobotMap.P_PORT0, RobotMap.P_PORT1);
	}
	
	/**
	 * Gets the singular instance of the Piston subsystem
	 * @return the singular instance of the Piston subsystem
	 */
	public static Piston getInstance() {
		if (piston == null) piston = new Piston();
		return piston;
	}
	
	public DoubleSolenoid getValve() {
		return valve;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

