// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.LeftMotor;
import frc.robot.testingdashboard.TestingDashboard;

public class SpinLeftMotorAnalog extends CommandBase {
  LeftMotor m_leftMotor;
  /** Creates a new SpinLeftMotorAnalog. */
  private Lights m_led;
  public SpinLeftMotorAnalog() {
    // Use addRequirements() here to declare subsystem dependencies.
    m_leftMotor = LeftMotor.getInstance();
    addRequirements(m_leftMotor);

    m_led = Lights.getInstance();

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  public static void registerWithTestingDashboard() {
    LeftMotor leftMotor = LeftMotor.getInstance();
    SpinLeftMotorAnalog cmd = new SpinLeftMotorAnalog();
    TestingDashboard.getInstance().registerCommand(leftMotor, "Motors", cmd);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_leftMotor.spin(m_leftMotor.getInputSpeed());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_leftMotor.spin(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
