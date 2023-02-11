// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.testingdashboard.TestingDashboard;

public class Arm extends SubsystemBase {
  
  public static Arm m_arm;

  private CANSparkMax m_shoulder;
  private CANSparkMax m_elbow;
  private CANSparkMax m_turret;

  private Encoder m_shoulderEncoder;
  private Encoder m_elbowEncoder;
  private Encoder m_turretEncoder;
  private Encoder m_clockEncoder;

  private AnalogInput m_shoulderPot;
  private AnalogInput m_elbowPot;
  private AnalogInput m_turretPot;

  private double elbowOffset;
  private double shoulderOffset;
  private double turretOffset;
  
  /** Creates a new Arm. */
  public Arm() {
    m_elbow = new CANSparkMax(RobotMap.M_ELBOW_MOTOR, MotorType.kBrushed);
    m_shoulder = new CANSparkMax(RobotMap.m_SHOULDER_MOTOR, MotorType.kBrushless);
    m_turret = new CANSparkMax(RobotMap.M_TURRET_MOTOR, MotorType.kBrushless);

    m_shoulderEncoder = new Encoder(RobotMap.M_SHOULDER_ENCODER_IN, RobotMap.M_SHOULDER_ENCODER_OUT);
    m_elbowEncoder = new Encoder(RobotMap.M_ELBOW_ENCODER_IN, RobotMap.M_ELBOW_ENCODER_OUT);
    m_turretEncoder = new Encoder(RobotMap.M_TURRET_ENCODER_IN, RobotMap.M_TURRET_ENCODER_OUT);

    m_elbowPot = new AnalogInput(RobotMap.M_ELBOW_POT);
    m_shoulderPot = new AnalogInput(RobotMap.M_SHOULDER_POT);
    m_turretPot = new AnalogInput(RobotMap.M_TURRET_POT);

    elbowOffset = m_elbowPot.getVoltage() / 0.0164;
    shoulderOffset = m_shoulderPot.getVoltage() / 0.0136;

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

      TestingDashboard.getInstance().registerNumber(m_arm, "Encoders", "ElbowEncoderPulses", 0);
      TestingDashboard.getInstance().registerNumber(m_arm, "Encoders", "ShoulderEncoderPulses", 0);
      TestingDashboard.getInstance().registerNumber(m_arm, "Encoders", "TurretEncoderPulses", 0);

      TestingDashboard.getInstance().registerNumber(m_arm, "EncoderAngles", "ElbowAngle_E", 0);
      TestingDashboard.getInstance().registerNumber(m_arm, "EncoderAngles", "ShoulderAngle_E", 0);
      TestingDashboard.getInstance().registerNumber(m_arm, "EncoderAngles", "TurretAngle_E", 0);

      TestingDashboard.getInstance().registerNumber(m_arm, "PotAngles", "ElbowAngle_V", 0);
      TestingDashboard.getInstance().registerNumber(m_arm, "PotAngles", "ShoulderAngle_V", 0);
      TestingDashboard.getInstance().registerNumber(m_arm, "PotAngles", "TurretAngle_V", 0);

      TestingDashboard.getInstance().registerNumber(m_arm, "AngleDiff", "ElbowAngle_Diff", 0);
      TestingDashboard.getInstance().registerNumber(m_arm, "AngleDiff", "ShoulderAngle_Diff", 0);
      TestingDashboard.getInstance().registerNumber(m_arm, "AngleDiff", "TurretAngle_Diff", 0);

      TestingDashboard.getInstance().registerNumber(m_arm, "RotationAngles", "ElbowAngle", 0);
      TestingDashboard.getInstance().registerNumber(m_arm, "RotationAngles", "PIDElbowSetPoint", 90);

    }
    return m_arm;
  }

  public void setElbowSpeed(double elbowSpeed) {
    m_elbow.set(elbowSpeed);
  }

  public void rotateElbowToAngle(double radians, double velocity) {
    ArmFeedforward feedforward = new ArmFeedforward(1, 2, 3);
    m_elbow.setVoltage(feedforward.calculate(radians, velocity));
  }

  public void setElbowVoltage(double voltage) {
    m_elbow.setVoltage(voltage);
  }

  public double getElbowAngle() {
    double angle = m_elbowPot.getVoltage() / 0.0164;
    return angle;
  }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    TestingDashboard.getInstance().updateNumber(m_arm, "ElbowPotVoltage", m_elbowPot.getVoltage());
    TestingDashboard.getInstance().updateNumber(m_arm, "ShoulderPotVoltage", m_shoulderPot.getVoltage());
    TestingDashboard.getInstance().updateNumber(m_arm, "TurretPotVoltage", m_turretPot.getVoltage());

    double elbowAngle_E = (m_elbowEncoder.get() * 1.286) + elbowOffset;        //pulses per degree
    double shoulderAngle_E = (m_shoulderEncoder.get() * 1.286) - shoulderOffset;
    double turretAngle_E = m_turretEncoder.get() * 1.286;

    double elbowAngle_V = m_elbowPot.getVoltage() / 0.0164;
    double shoulderAngle_V = m_shoulderPot.getVoltage() / 0.0136;
    double turretAngle_V = m_turretPot.getVoltage() / 1.286;

    double elbowAngle_Diff = Math.abs(Math.abs(elbowAngle_E) - Math.abs(elbowAngle_V));
    double shoulderAngle_Diff = Math.abs(Math.abs(shoulderAngle_E) - Math.abs(shoulderAngle_V));
    double turretAngle_Diff = Math.abs(Math.abs(turretAngle_E) - Math.abs(turretAngle_V));

    TestingDashboard.getInstance().updateNumber(m_arm, "ElbowEncoderPulses", m_elbowEncoder.get());
    TestingDashboard.getInstance().updateNumber(m_arm, "ShoulderEncoderPulses", m_shoulderEncoder.get());
    TestingDashboard.getInstance().updateNumber(m_arm, "TurretEncoderPulses", m_turretEncoder.get());

    TestingDashboard.getInstance().updateNumber(m_arm, "ElbowAngle_E", elbowAngle_E);
    TestingDashboard.getInstance().updateNumber(m_arm, "ShoulderAngle_E", shoulderAngle_E);
    TestingDashboard.getInstance().updateNumber(m_arm, "TurretAngle_E", turretAngle_E);

    TestingDashboard.getInstance().updateNumber(m_arm, "ElbowAngle_V", elbowAngle_V);
    TestingDashboard.getInstance().updateNumber(m_arm, "ShoulderAngle_V", shoulderAngle_V);
    TestingDashboard.getInstance().updateNumber(m_arm, "TurretAngle_V", turretAngle_V);

    TestingDashboard.getInstance().updateNumber(m_arm, "ElbowAngle_Diff", elbowAngle_Diff);
    TestingDashboard.getInstance().updateNumber(m_arm, "ShoulderAngle_Diff", shoulderAngle_Diff);
    TestingDashboard.getInstance().updateNumber(m_arm, "TurretAngle_Diff", turretAngle_Diff);
    
  }
}

class ArmSegment {

  public ArmSegment(double length) {

  }
}
