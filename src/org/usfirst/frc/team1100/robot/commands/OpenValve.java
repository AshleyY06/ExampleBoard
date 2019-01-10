package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.subsystems.Piston;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenValve extends Command {

	Piston piston;
	boolean finished = false;
	
    public OpenValve() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Piston.getInstance());
        piston = Piston.getInstance();
        System.out.println("Open Valve Created.");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	piston.getValve().set(DoubleSolenoid.Value.kForward);
    	finished = false;
    	System.out.println("Initialized Open Valve.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Executed Open Valve.");
    	if (piston.getValve().get() == DoubleSolenoid.Value.kForward) {
	    	piston.getValve().set(DoubleSolenoid.Value.kOff);
	    	finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	piston.getValve().set(DoubleSolenoid.Value.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	piston.getValve().set(DoubleSolenoid.Value.kOff);
    }
}
