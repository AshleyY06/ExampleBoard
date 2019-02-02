package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.subsystems.LeftMotor;
import org.usfirst.frc.team1100.robot.subsystems.LightSwitch;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultLightSwitch extends Command {

	LeftMotor lmotor;
	LightSwitch lsw;
	
    public DefaultLightSwitch() {
        // Use requires() here to declare subsystem dependencies
        requires(LightSwitch.getInstance());
        requires(LeftMotor.getInstance());
        lsw = LightSwitch.getInstance();
        lmotor = LeftMotor.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lmotor.spin(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (lsw.get()) {
    		lmotor.spinCW();
    	} else {
    		lmotor.spin(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	lmotor.spin(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	lmotor.spin(0);
    }
}
