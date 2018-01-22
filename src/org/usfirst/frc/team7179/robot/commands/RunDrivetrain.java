package org.usfirst.frc.team7179.robot.commands;

import org.usfirst.frc.team7179.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RunDrivetrain extends Command {
	public RunDrivetrain() {
		requires(Robot.drive);
	}
	@Override
	protected void initialize() {
	}
	public void execute() {
		Robot.drive.move(Robot.OI.getLeftSpeed(), Robot.OI.getRightSpeed());
	}
	protected boolean isFinished() {
		return false;
	}
	protected void end() {
		Robot.drive.move(0, 0);
	}
	protected void interrupted() {
		Robot.drive.move(0, 0);
	}

}
