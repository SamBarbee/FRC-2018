package org.usfirst.frc.team7179.robot.commands.drive;

import org.usfirst.frc.team7179.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TurnTime extends Command {
	double time; //s
	double speed;
	double direction;
	Timer timer;

	public TurnTime(double desiredTime, double desiredSpeed, double desiredDirection) {
		requires(Robot.drivetrain);
		time = desiredTime;
		speed = desiredSpeed;
		direction = desiredDirection;
	}
	@Override
	protected void initialize() {
		timer = new Timer();
		timer.reset();
		timer.start();
	}
	@Override
	protected void execute() {
		Robot.drivetrain.setMotors((speed*-direction),(speed*direction));
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
