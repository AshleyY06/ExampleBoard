// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.testingdashboard.TestingDashboard;

public class LeftMotor extends SubsystemBase {
  /** Creates a new LeftMotor. */
  private static LeftMotor leftMotor;
	private Talon motor;
	private Encoder encoder;
  private AnalogInput potentiometer;
  public static final double MAX_POTENTIOMETER_VOLTAGE = 5;
	
	/*
	 * Sets up Talon for left motor
	 */
	private LeftMotor() {
		// Instantiate left motor class
		motor = new Talon(RobotMap.M_LEFT);
		encoder = new Encoder(RobotMap.M_LEFT_ENCODER_CW, RobotMap.M_LEFT_ENCODER_CCW);
    	potentiometer = new AnalogInput(RobotMap.M_LEFT_POTENTIOMETER);
  }
	
	/**
	 * Gets the singular instance of the LeftMotor subsystem
	 * @return the singular instance of the LeftMotor subsystem
	 */
	public static LeftMotor getInstance() {
		if (leftMotor == null) leftMotor = new LeftMotor();
		
		TestingDashboard.getInstance().registerSubsystem(leftMotor, "leftMotor");
		TestingDashboard.getInstance().registerNumber(leftMotor, "Motors", "testMotor", 0);
		return leftMotor;
	}
	
	public void spinCCW() {
		double speed = SmartDashboard.getNumber("l_motor_speed", -0.5);
		motor.set(speed);
	}
	
	public void spinCW() {
		double speed = SmartDashboard.getNumber("l_motor_speed", 0.5);
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
	
	public AnalogInput getPotentiometer() {
		return potentiometer;
	}
	
	public double getInputSpeed() {
		return potentiometer.getVoltage() / MAX_POTENTIOMETER_VOLTAGE;
	}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
