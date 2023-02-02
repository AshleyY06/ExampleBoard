// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.testingdashboard.TestingDashboard;

public class ArmMotor extends CommandBase {
  /** Creates a new SpinTurretMotor. */
  private Arm m_arm;
  private double m_armSpeed;
  private boolean m_parameterized;

  
  public ArmMotor(double armSpeed, boolean parameterized) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_armSpeed = armSpeed;
    m_parameterized = parameterized;
  }

  public static void registerWithTestingDashboard() {
    Arm arm = Arm.getInstance();
    ArmMotor cmd = new ArmMotor(0, false);
    TestingDashboard.getInstance().registerCommand(arm, "MoveArm", cmd);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_arm.setMotorSpeed(0, 0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!m_parameterized) {
      m_armSpeed = TestingDashboard.getInstance().getNumber(m_arm, "MoveSpeed");
    }
    
    double elbowSpeed = TestingDashboard.getInstance().getNumber(m_arm, "ElbowSpeed");;
    double shoulderSpeed = TestingDashboard.getInstance().getNumber(m_arm, "ShoulderSpeed");;
    double turretSpeed = TestingDashboard.getInstance().getNumber(m_arm, "TurretSpeed");;
    
    m_arm.setMotorSpeed(elbowSpeed, turretSpeed, shoulderSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_arm.setMotorSpeed(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
