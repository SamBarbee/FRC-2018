package org.usfirst.frc.team7179.robot.commands.lift;

import org.usfirst.frc.team7179.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class StopLift extends Command{
	public StopLift() {
		requires(Robot.lift);
	}
	@Override
	protected void initialize() {
	}
	public void execute() {
		Robot.lift.setMotors(0);
	}
	protected boolean isFinished() {
		return false;
	}
	protected void end() {
		Robot.lift.setMotors(0);
	}
	protected void interrupted() {
		Robot.lift.setMotors(0);
	}
}
