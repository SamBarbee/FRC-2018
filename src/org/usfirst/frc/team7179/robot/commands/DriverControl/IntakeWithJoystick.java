package org.usfirst.frc.team7179.robot.commands.DriverControl;

import org.usfirst.frc.team7179.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeWithJoystick extends Command{
	public IntakeWithJoystick() {
		requires(Robot.intake);
	}
	@Override
	protected void initialize() {
	}
	public void execute() {
		Robot.intake.setMotors(Robot.OI.getIntakeSpeed());
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
