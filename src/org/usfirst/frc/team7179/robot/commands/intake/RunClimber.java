package org.usfirst.frc.team7179.robot.commands.intake;

import org.usfirst.frc.team7179.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;



public class RunClimber extends Command{
	private double power = 0;
	
	public RunClimber() {
		requires(Robot.intake);
			}
	public RunClimber(double power) {
		requires(Robot.intake);
		this.power = power;
			}
	@Override
	protected void initialize() {
	}
	public void execute() {
		Robot.intake.runClimber(power);
	}
	protected boolean isFinished() {
		return false;
	}
	protected void end() {
		Robot.intake.runClimber(0);
			}
	protected void interrupted() {
		Robot.intake.runClimber(0);
	}
}

