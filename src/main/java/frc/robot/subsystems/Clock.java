// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.testingdashboard.TestingDashboard;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Clock extends SubsystemBase {
  
  public static Clock m_clock;
  private CANSparkMax m_handMotor;
  private Encoder m_handEncoder;
  private double m_handOffset;



  /** Creates a new Clock. */
  private Clock() {
    m_handMotor = new CANSparkMax(RobotMap.H_HAND_MOTOR, MotorType.kBrushed);
    m_handEncoder = new Encoder(RobotMap.H_HAND_ENCODER_A, RobotMap.H_HAND_ENCODER_B);
    m_handOffset = 0;
  }
  public static Clock getInstance() {
    if(m_clock == null) {
      m_clock = new Clock();

			TestingDashboard.getInstance().registerSubsystem(m_clock, "Clock");
      TestingDashboard.getInstance().registerNumber(m_clock, "Motors", "HandSpeed", .2);
      TestingDashboard.getInstance().registerNumber(m_clock, "Encoders", "HandEncoderPulses", 0);
      TestingDashboard.getInstance().registerNumber(m_clock, "EncoderAngles", "HandAngle_E", 0);
      TestingDashboard.getInstance().registerNumber(m_clock, "Inputs", "TargetAngle", 0);

    
    }
    return m_clock;
  }
  
  public void setHandSpeed(double handSpeed) {
    m_handMotor.set(handSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    double HandAngle_E = (m_handEncoder.get() * .857) + m_handOffset;  
    TestingDashboard.getInstance().updateNumber(m_clock, "HandEncoderPulses", m_handEncoder.get());
    TestingDashboard.getInstance().updateNumber(m_clock, "HandAngle_E", HandAngle_E);
    // TestingDashboard.getInstance().updateNumber(m_clock, "HandSpeed", m_handMotor.get());
  }
}
