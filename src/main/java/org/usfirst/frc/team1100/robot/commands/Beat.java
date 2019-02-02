package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.subsystems.LeftMotor;
import org.usfirst.frc.team1100.robot.subsystems.Piston;
import org.usfirst.frc.team1100.robot.subsystems.RightMotor;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Beat extends Command {

	private static final int STATE1 = 1;
	private static final int STATE2 = 2;
	private static final int STATE3 = 3;
	
	private static final double PERIOD = 0.5;
	private static final double SPERIOD = 0.25;

	Piston piston;
	LeftMotor leftMotor;
	RightMotor rightMotor;
	int state = STATE1;
	Timer timer;
	Timer sTimer;
	DoubleSolenoid.Value solenoidState;
	int stateCounter = 0;
	
    public Beat() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Piston.getInstance());
        requires(LeftMotor.getInstance());
        requires(RightMotor.getInstance());
        
        piston = Piston.getInstance();
        leftMotor = LeftMotor.getInstance();
        rightMotor = RightMotor.getInstance();
        timer = new Timer();
        sTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = STATE1;
    	timer.start();
    	sTimer.start();
    	leftMotor.spinCW();
    	solenoidState = DoubleSolenoid.Value.kForward;
    	stateCounter = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (state) {
    	case STATE1:
    		if (timer.hasPeriodPassed(PERIOD)) {
    			leftMotor.spin(0);
    			state = STATE2;
    			piston.getValve().set(solenoidState);
    		}
    		break;
    	case STATE2:
    		if (piston.getValve().get() == solenoidState) {
    	    	if (solenoidState == DoubleSolenoid.Value.kForward) {
    				solenoidState = DoubleSolenoid.Value.kReverse;
    			} else {
    				solenoidState = DoubleSolenoid.Value.kForward;
    			}
    	    	piston.getValve().set(DoubleSolenoid.Value.kOff);
    		}
    		if (sTimer.hasPeriodPassed(SPERIOD)) {
				piston.getValve().set(solenoidState);
    		}
    		if (timer.hasPeriodPassed(PERIOD)) {
    			piston.getValve().set(DoubleSolenoid.Value.kOff);
    			state = STATE3;
    			rightMotor.spinCW();
    		}
    		break;
    	case STATE3:
    		if (timer.hasPeriodPassed(PERIOD)) {
    			rightMotor.spin(0);
    			stateCounter++;
    			state = STATE1;
    		}
    		break;
    	default:
    		break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return stateCounter >= SmartDashboard.getNumber("num_beats", 3);
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	sTimer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
