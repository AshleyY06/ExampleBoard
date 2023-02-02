// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.RobotMap;
import frc.robot.testingdashboard.TestingDashboard;

public class Arm extends SubsystemBase {
  
  public static Arm m_arm;

  private CANSparkMax m_shoulder;
  private CANSparkMax m_elbow;
  private CANSparkMax m_turret;

  private AnalogInput m_shoulderPot;
  private AnalogInput m_elbowPot;
  private AnalogInput m_turretPot;
  
  /** Creates a new Arm. */
  public Arm() {
    m_elbow = new CANSparkMax(RobotMap.M_ELBOW_MOTOR, MotorType.kBrushless);
    m_shoulder = new CANSparkMax(RobotMap.m_SHOULDER_MOTOR, MotorType.kBrushless);
    m_turret = new CANSparkMax(RobotMap.M_TURRET_MOTOR, MotorType.kBrushless);

    m_elbowPot = new AnalogInput(RobotMap.M_ELBOW_POT);
    m_shoulderPot = new AnalogInput(RobotMap.M_SHOULDER_POT);
    m_turretPot = new AnalogInput(RobotMap.M_TURRET_POT);

  }

  public static Arm getInstance() {
    if(m_arm == null) {
      m_arm = new Arm();
			TestingDashboard.getInstance().registerSubsystem(m_arm, "Arm");
			TestingDashboard.getInstance().registerNumber(m_arm, "Motors", "ElbowSpeed", .5);
      TestingDashboard.getInstance().registerNumber(m_arm, "Motors", "ShoulderSpeed", .5);
      TestingDashboard.getInstance().registerNumber(m_arm, "Motors", "TurretSpeed", .5);

      TestingDashboard.getInstance().registerNumber(m_arm, "Potentiometers", "ElbowPotVoltage", 0);
      TestingDashboard.getInstance().registerNumber(m_arm, "Potentiometers", "ShoulderPotVoltage", 0);
      TestingDashboard.getInstance().registerNumber(m_arm, "Potentiometers", "TurretPotVoltage", 0);

    }
    return m_arm;
  }

  public void setMotorSpeed(double elbowSpeed, double turretSpeed, double shoulderSpeed) {
    m_elbow.set(elbowSpeed);
    m_shoulder.set(shoulderSpeed);
    m_turret.set(turretSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    TestingDashboard.getInstance().updateNumber(m_arm, "ElbowPotVoltage", m_elbowPot.getVoltage());
    TestingDashboard.getInstance().updateNumber(m_arm, "ShoulderPotVoltage", m_shoulderPot.getVoltage());
    TestingDashboard.getInstance().updateNumber(m_arm, "TurretPotVoltage", m_turretPot.getVoltage());

  }
}
