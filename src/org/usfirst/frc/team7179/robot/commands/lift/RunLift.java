package org.usfirst.frc.team7179.robot.commands.lift;

import org.usfirst.frc.team7179.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RunLift extends Command{
	private double power;
	public RunLift() {
		requires(Robot.lift);
	}
	public RunLift(double power) {
		requires(Robot.lift);
		this.power = power;
	}
	@Override
	protected void initialize() {
	}
	public void execute() {
		Robot.lift.setMotors(power);
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
