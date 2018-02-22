package org.usfirst.frc.team7179.robot.commands.intake;

import org.usfirst.frc.team7179.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;



public class RunIntake extends Command{
	private double power = 0;
	
	public RunIntake() {
		requires(Robot.intake);
			}
	public RunIntake(double power) {
		requires(Robot.intake);
		this.power = power;
			}
	@Override
	protected void initialize() {
	}
	public void execute() {
		Robot.intake.setMotors(power);
	}
	protected boolean isFinished() {
		return false;
	}
	protected void end() {
		Robot.intake.setMotors(0);
			}
	protected void interrupted() {
		Robot.intake.setMotors(0);
	}
}

