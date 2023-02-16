// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.SpinLeftMotorAnalog;
import frc.robot.commands.SpinRightMotorAnalog;
import frc.robot.commands.Arm.ArmMotor;
import frc.robot.commands.Arm.PIDRotateElbowToAngle;
import frc.robot.commands.Arm.RotateElbowToAngle;
import frc.robot.commands.Clock.PIDTurnClock;
import frc.robot.commands.Clock.TurnToAngle;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.LeftMotor;
import frc.robot.subsystems.RightMotor;
import frc.robot.testingdashboard.TestingDashboard;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    SpinLeftMotorAnalog.registerWithTestingDashboard();
    SpinRightMotorAnalog.registerWithTestingDashboard();
    ArmMotor.registerWithTestingDashboard();
    RotateElbowToAngle.registerWithTestingDashboard();
    PIDRotateElbowToAngle.registerWithTestingDashboard();
    PIDTurnClock.registerWithTestingDashboard();
    TurnToAngle.registerWithTestingDashboard();

    LeftMotor.getInstance().setDefaultCommand(new SpinLeftMotorAnalog());
    RightMotor.getInstance().setDefaultCommand(new SpinRightMotorAnalog());

    TestingDashboard.getInstance().createTestingDashboard();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
