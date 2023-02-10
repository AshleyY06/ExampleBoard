// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.testingdashboard.TestingDashboard;

public class RotateElbowToAngle extends CommandBase {

  private Arm m_arm;

  private double m_radians;
  private boolean finished;
  /** Creates a new RotateElbowToAngle. */
  public RotateElbowToAngle(double radians) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_radians = radians;
    m_arm = Arm.getInstance();
    
  }

  public static void registerWithTestingDashboard() {
    Arm arm = Arm.getInstance();
    RotateElbowToAngle cmd = new RotateElbowToAngle(0);
    TestingDashboard.getInstance().registerCommand(arm, "RotateArm", cmd);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_radians = TestingDashboard.getInstance().getNumber(m_arm, "ElbowAngle");
    m_arm.rotateElbowToAngle(m_radians, .05);
    System.out.println(finished);
    finished = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_arm.setElbowVoltage(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
