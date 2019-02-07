/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.kauailabs.navx.frc.AHRS;

/**
 * Add your docs here.
 */
public class Navigation extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private AHRS ahrs;
  private static Navigation navi;

  private Navigation() {
    ahrs = new AHRS(RobotMap.N_NAVX);
  }

  public AHRS getNavX() {
    return ahrs;
  }

  public static Navigation getInstance() {
		if (navi == null) navi = new Navigation();
		return navi;
	}	

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
