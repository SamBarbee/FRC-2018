package org.usfirst.frc.team7179.robot.commands.DriverControl;

import org.usfirst.frc.team7179.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {
	public DriveWithJoystick() {
		requires(Robot.drivetrain);
	}
	@Override
	protected void initialize() {
	}
	public void execute() {
		Robot.drivetrain.setMotors(Robot.OI.getLeftSpeed(), -Robot.OI.getRightSpeed());
	}
	protected boolean isFinished() {
		return false;
	}
	protected void end() {
		Robot.drivetrain.setMotors(0, 0);
	}
	protected void interrupted() {
		Robot.drivetrain.setMotors(0, 0);
	}

}
