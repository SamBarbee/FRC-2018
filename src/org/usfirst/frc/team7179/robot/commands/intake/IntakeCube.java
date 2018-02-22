package org.usfirst.frc.team7179.robot.commands.intake;

import org.usfirst.frc.team7179.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeCube extends Command{
	public IntakeCube() {
		requires(Robot.intake);
			}
	@Override
	protected void initialize() {
	}
	public void execute() {
		Robot.intake.setMotors(1);
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
