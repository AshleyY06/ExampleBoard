// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LeftMotor;
import frc.robot.subsystems.RightMotor;
import frc.robot.testingdashboard.TestingDashboard;

public class SpinRightMotorAnalog extends CommandBase {
  RightMotor m_rightMotor;
  /** Creates a new SpinRightMotorAnalog. */
  public SpinRightMotorAnalog() {
    // Use addRequirements() here to declare subsystem dependencies.
    m_rightMotor = RightMotor.getInstance();
    addRequirements(m_rightMotor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  public static void registerWithTestingDashboard() {
    RightMotor rightMotor = RightMotor.getInstance();
    SpinRightMotorAnalog cmd = new SpinRightMotorAnalog();
    TestingDashboard.getInstance().registerCommand(rightMotor, "Motors", cmd);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_rightMotor.spin(LeftMotor.getInstance().getInputSpeed());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
