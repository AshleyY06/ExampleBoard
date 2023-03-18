/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Input.XboxController;
import frc.robot.commands.Lights.ConeLight;
import frc.robot.commands.Lights.CubeLight;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private static OI oi;

  private static XboxController DriverXboxController;
  private static XboxController OperatorXboxController;

  /**
   * Used outside of the OI class to return an instance of the class.
   * @return Returns instance of OI class formed from constructor.
   */
  public static OI getInstance() {
    if (oi == null) {
      oi = new OI();
    }
    return oi;
  }

  public OI() {
    // User Input
    // TODO: Tune deadband
    
    DriverXboxController = new XboxController(0, .1);
    OperatorXboxController = new XboxController(1, .1);

    DriverXboxController.getButtonA().onTrue(new ConeLight());
    DriverXboxController.getButtonB().onTrue(new CubeLight());
    /* 
    ////////////////////////////////////////////////////
    // Now Mapping Commands to XBox
    ////////////////////////////////////////////////////
    if (Constants.XBOX_CONTROLLER_DRIVER_ENABLE) {

    }
    if (Constants.XBOX_CONTROLLER_OPERATOR_ENABLE) {

    }
    
    ////////////////////////////////////////////////////
    // Now Mapping Commands to AttackThree controllers
    ////////////////////////////////////////////////////
    if (Constants.ATTACK_THREE_ENABLE) {
    }

    ////////////////////////////////////////////////////
    // Now Mapping Commands to Button Box
    ////////////////////////////////////////////////////
    if (Constants.BUTTON_BOX_ENABLE) {
    }


    ////////////////////////////////////////////////////
    // Now Mapping Commands to Keyboard Box
    ////////////////////////////////////////////////////

    if (Constants.KEYBOARD_BOX_ENABLE) {
    }
    */
  }

  /**
   * Returns the Xbox Controller
   * @return the Xbox Controller
   */
  public XboxController getDriverXboxController() {
      return DriverXboxController;
  }

  public XboxController getOperatorXboxController() {
    return OperatorXboxController;
}
  
}
