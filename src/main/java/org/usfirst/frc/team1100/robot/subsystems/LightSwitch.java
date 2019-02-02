package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.DefaultLightSwitch;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LightSwitch extends Subsystem {
	
	private static LightSwitch light_switch;
	private DigitalInput digital_input;
	
	private LightSwitch() {
		digital_input = new DigitalInput(RobotMap.L_SWITCH);
	}
	
	public static LightSwitch getInstance() {
		if (light_switch == null) light_switch = new LightSwitch();
		return light_switch;
	}	
	
	public DigitalInput getDigitalInput( ) {
		return digital_input;
	}
	
	public boolean get() {
		return digital_input.get();
	}
	
	public void initDefaultCommand() {
	  	//setDefaultCommand(new DefaultLightSwitch());
	}

}
