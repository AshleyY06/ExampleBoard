package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.subsystems.Piston;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseValve extends Command {

	Piston piston;
	boolean finished = false;
	
    public CloseValve() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Piston.getInstance());
        piston = Piston.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	piston.getValve().set(DoubleSolenoid.Value.kReverse);
    	finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (piston.getValve().get() == DoubleSolenoid.Value.kReverse) {
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
