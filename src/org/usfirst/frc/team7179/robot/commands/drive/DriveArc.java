package org.usfirst.frc.team7179.robot.commands.drive;


import org.usfirst.frc.team7179.robot.Robot;
import org.usfirst.frc.team7179.robot.subsystems.drive.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class DriveArc extends Command {

    private final double distance;
    private final double radius;
    private final double velocity;
    private final double acceleration;
    private final boolean goLeft;

    public static final double INNER_SCALE = 1.25;

    public DriveArc(double distance, double radius, boolean goLeft) {
	this(distance, radius, Drivetrain.DEFAULT_VELOCITY, Drivetrain.DEFAULT_ACCELERATION, goLeft);
    }

    public DriveArc(double distance, double radius, double velocity, double acceleration, boolean goLeft) {
	super();
	this.distance = distance;
	this.radius = Math.abs(radius);
	this.velocity = Math.min(Math.abs(velocity), Drivetrain.MAX_ALLOWED_VELOCITY);
	this.acceleration = Math.min(Math.abs(acceleration), Drivetrain.MAX_ALLOWED_ACCELERATION);
	this.goLeft = goLeft;
    }

    public static double degreesToDistance(double degrees, double radius) {
	return radius * 2 * Math.PI * degrees / 360.0;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
	Robot.drivetrain.setControlMode(ControlMode.MotionMagic);

	double outerRadius = radius + Drivetrain.ROBOT_WIDTH_INCHES / 2.0;
	double innerRadius = radius - Drivetrain.ROBOT_WIDTH_INCHES / 2.0;

	double percentDriven = distance / circumference(radius);
	double outerDistance = percentDriven * circumference(outerRadius);
	double innerDistance = percentDriven * circumference(innerRadius);

	if (radius < Drivetrain.ROBOT_WIDTH_INCHES / 2.0) {
	    innerDistance = -innerDistance;
	}

	double outerVelocity = velocity;
	double outerAcceleration = acceleration;

	/*
	 * velocity and acceleration don't need to be converted to inch/min and
	 * inch/min/sec even though the circumference is inches because later
	 * they would be converted back to RPM and RPM/sec anyways
	 */
	double rotationalCruiseVelocity = outerVelocity / circumference(outerRadius);
	double rotationalAcceleration = outerAcceleration / circumference(outerRadius);

	// unused but I think having these here improves understanding
	double angularCruiseVelocity = rotationalCruiseVelocity * 2 * Math.PI;
	double angularAcceleration = rotationalAcceleration * 2 * Math.PI;

	double innerVelocity = rotationalCruiseVelocity * circumference(innerRadius) * INNER_SCALE;
	double innerAcceleration = rotationalAcceleration * circumference(innerRadius) * INNER_SCALE;

	/*
	 * theoretically, if we did the limit as radius approached infinity all
	 * the infinite radius stuff would cancel out and this if block wouldn't
	 * be needed, but java doesn't work like that
	 */
	if (radius == Double.POSITIVE_INFINITY) {
	    outerRadius = radius;
	    innerRadius = radius;
	    innerDistance = distance;
	    outerDistance = distance;
	    innerVelocity = outerVelocity;
	    innerAcceleration = outerAcceleration;
	}

	if (goLeft) {
	    Robot.drivetrain.motionMagic.left.setMotionMagicCruiseVelocity((int) innerVelocity);
	    Robot.drivetrain.motionMagic.left.setMotionMagicAcceleration((int) innerAcceleration);

	    Robot.drivetrain.motionMagic.right.setMotionMagicCruiseVelocity((int) outerVelocity);
	    Robot.drivetrain.motionMagic.right.setMotionMagicAcceleration((int) outerAcceleration);

	    Robot.drivetrain.motionMagic.left.setSetpoint(innerDistance);
	    Robot.drivetrain.motionMagic.right.setSetpoint(outerDistance);
	} else {
	    Robot.drivetrain.motionMagic.right.setMotionMagicCruiseVelocity((int) innerVelocity);
	    Robot.drivetrain.motionMagic.right.setMotionMagicAcceleration((int) innerAcceleration);

	    Robot.drivetrain.motionMagic.left.setMotionMagicCruiseVelocity((int) outerVelocity);
	    Robot.drivetrain.motionMagic.left.setMotionMagicAcceleration((int) outerAcceleration);

	    Robot.drivetrain.motionMagic.right.setSetpoint(innerDistance);
	    Robot.drivetrain.motionMagic.left.setSetpoint(outerDistance);
	}
	Robot.drivetrain.motionMagic.resetPositions();
    }

    public static double circumference(double r) {
	return Math.PI * r * 2;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
	return Robot.drivetrain.motionMagic.onTarget();
    }

    @Override
    protected void end() {
	Robot.drivetrain.setControlMode(ControlMode.PercentOutput);

    }
}
