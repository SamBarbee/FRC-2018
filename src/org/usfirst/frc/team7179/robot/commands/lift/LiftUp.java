package org.usfirst.frc.team7179.robot.commands.lift;

import org.usfirst.frc.team7179.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class LiftUp extends Command{
	public LiftUp() {
		requires(Robot.lift);
	}
	@Override
	protected void initialize() {
	}
	public void execute() {
		Robot.lift.setMotors(1);
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
