/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1100.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team1100.robot.subsystems.LeftMotor;
import org.usfirst.frc.team1100.robot.subsystems.Navigation;

public class ChangeHeading extends PIDCommand {


  private PIDController pidController = getPIDController();
	private AHRS ahrs = Navigation.getInstance().getNavX();
	private int countOnTarget;


  public ChangeHeading(double target, double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super(1, .02, .1);
    requires(Navigation.getInstance());
    requires(LeftMotor.getInstance());
    countOnTarget = 0;
    setSetpoint(target);
    setInputRange(-180.0, 180.0);
    pidController.setContinuous();
    pidController.setOutputRange(-speed, speed);
    pidController.setPercentTolerance(1);
  }

      /**
     * Returns the input for the PID controller. Called by that controller.
     */
    protected double returnPIDInput() {
      return ahrs.getYaw();
  }
  
  /**
   * Takes value given by PID controller to then turn to desired heading. Called by the
   * PID controller.
   */
  protected void usePIDOutput(double output) {
    LeftMotor.getInstance().spin(output); // TODO: Are the signs still correct?
  }
  
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (pidController.onTarget()) {
      if (countOnTarget >= 3) {
        return true;
      }
      countOnTarget++;     
    } else {
      countOnTarget = 0;
    }
    return false;
  }

 
}
