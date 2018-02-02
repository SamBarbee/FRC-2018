package org.usfirst.frc.team7179.robot.commands.drive;

import org.usfirst.frc.team7179.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTime extends Command {
	double time; //s
	double speed;
	Timer timer;

	public DriveTime(double desiredTime, double desiredSpeed) {
		requires(Robot.drivetrain);
		time = desiredTime;
		speed = desiredSpeed;
	}
	@Override
	protected void initialize() {
		Robot.drivetrain.resetGyro();
		timer = new Timer();
		timer.reset();
		timer.start();
	}
	@Override
	protected void execute() {
		Robot.drivetrain.setMotors(1,1);
	}
	@Override
	protected boolean isFinished() {
		return (timer.get() >= time);
	}
	@Override
	protected void end() {
		Robot.drivetrain.stopMotors();
		timer.stop();
	}
	@Override
	protected void interrupted() {
		Robot.drivetrain.stopMotors();
	}
}
