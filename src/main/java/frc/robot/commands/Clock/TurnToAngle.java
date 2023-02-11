// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Clock;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Clock;
import frc.robot.testingdashboard.TestingDashboard;

public class TurnToAngle extends CommandBase {
  Clock m_clock;
  
  double m_targetAngle;
  /** Creates a new TurnToAngle. */
  public TurnToAngle() {
    // Use addRequirements() here to declare subsystem dependencies.
    m_clock = Clock.getInstance();
    addRequirements(m_clock);
    m_targetAngle = 0;
  }
  
  public static void registerWithTestingDashboard() {
    Clock clock = Clock.getInstance();
    TurnToAngle cmd = new TurnToAngle();
    TestingDashboard.getInstance().registerCommand(clock, "Basic", cmd);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_targetAngle = TestingDashboard.getInstance().getNumber(m_clock, "TargetAngle");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = TestingDashboard.getInstance().getNumber(m_clock, "HandSpeed");
    m_clock.setHandSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_clock.setHandSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
