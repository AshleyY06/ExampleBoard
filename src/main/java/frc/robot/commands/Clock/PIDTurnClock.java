// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Clock;

import frc.robot.subsystems.Clock;
import frc.robot.testingdashboard.TestingDashboard;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PIDTurnClock extends PIDCommand {
  /** Creates a new PIDCommand. */
  public PIDTurnClock() {
    super(
        // The controller that the command will use
        new PIDController(0.1, 0.1, 0),
        // This should return the measurement
        () -> TestingDashboard.getInstance().getNumber(Clock.getInstance(), "HandAngle_E"),
        // This should return the setpoint (can also be a constant)
        () -> TestingDashboard.getInstance().getNumber(Clock.getInstance(), "TargetAngle"),
        // This uses the output
        output -> {
          Clock.getInstance().setHandSpeed(output);
          // Use the output here
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public static void registerWithTestingDashboard() {
    Clock clock = Clock.getInstance();
    PIDTurnClock cmd = new PIDTurnClock();
    TestingDashboard.getInstance().registerCommand(clock, "Basic", cmd);
    TestingDashboard.getInstance().registerSendable(clock, "PIDRotation", "ClockPIDController", cmd.getController());
  }
}
