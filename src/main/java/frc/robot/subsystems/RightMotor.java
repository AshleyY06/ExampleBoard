// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.testingdashboard.TestingDashboard;

public class RightMotor extends SubsystemBase {
  /** Creates a new RightMotor. */
  private static RightMotor rightMotor;
	private Talon motor;
	private Encoder encoder;

	
	/*
	 * Sets up Talon for right motor
	 */
	private RightMotor() {
		// Instantiate left motor class
		motor = new Talon(RobotMap.M_RIGHT);
	
	}
	
	/**
	 * Gets the singular instance of the LeftMotor subsystem
	 * @return the singular instance of the LeftMotor subsystem
	 */
	public static RightMotor getInstance() {
		if (rightMotor == null) {
			rightMotor = new RightMotor();
			TestingDashboard.getInstance().registerSubsystem(rightMotor, "RightMotor");
			TestingDashboard.getInstance().registerNumber(rightMotor, "Motors", "testMotor", 0);
		}
		
		return rightMotor;
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
	

	
		

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
