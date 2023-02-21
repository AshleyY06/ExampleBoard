// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Arm;
import frc.robot.testingdashboard.TestingDashboard;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PIDRotateElbowToAngle extends PIDCommand {
  /** Creates a new PIDRotateElbowToAngle. */
  public PIDRotateElbowToAngle() {
    super(
        // The controller that the command will use
        new PIDController(0.01, 0.005, 0),
        // This should return the measurement
        () -> Arm.getInstance().getElbowAngle(),
        // This should return the setpoint (can also be a constant)
        () -> TestingDashboard.getInstance().getNumber(Arm.getInstance(), "ElbowTargetAngle"),
        // This uses the output
        output -> {
          // Use the output here
          Arm.getInstance().setElbowSpeed(output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  public static void registerWithTestingDashboard() {
    Arm arm = Arm.getInstance();
    PIDRotateElbowToAngle cmd = new PIDRotateElbowToAngle();
    TestingDashboard.getInstance().registerCommand(arm, "PIDRotateArm", cmd);
    TestingDashboard.getInstance().registerSendable(arm, "RotationAngles", "ElbowPIDController", cmd.getController());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
