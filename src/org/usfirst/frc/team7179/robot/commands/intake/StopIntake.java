package org.usfirst.frc.team7179.robot.commands.intake;

import org.usfirst.frc.team7179.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class StopIntake extends Command{
	public StopIntake() {
		requires(Robot.intake);
		requires(Robot.shooter);
	}
	@Override
	protected void initialize() {
	}
	public void execute() {
		Robot.intake.setMotors(0);
		Robot.shooter.setMotors(0);
	}
	protected boolean isFinished() {
		return false;
	}
	protected void end() {
		Robot.intake.setMotors(0);
		Robot.shooter.setMotors(0);
	}
	protected void interrupted() {
		Robot.intake.setMotors(0);
		Robot.shooter.setMotors(0);
	}
}
