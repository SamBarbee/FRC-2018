package org.usfirst.frc.team7179.robot.subsystems;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.Drive.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem{
	
	private TalonSRX leftMotor1, rightMotor1;
	private VictorSPX leftMotor2, rightMotor2;
	
	private Encoder encoderRight = new Encoder(RobotMap.RIGHT_ENCODER_A,RobotMap.RIGHT_ENCODER_B);
	private Encoder encoderLeft = new Encoder(RobotMap.LEFT_ENCODER_A,RobotMap.LEFT_ENCODER_B);
	
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	private static final double wheelDiameter = 6;
	private static final int pulsesPerRevolution = 256;
	public double angleError;
	
	public Drivetrain(){
		leftMotor1 = new TalonSRX(RobotMap.DRIVE_LEFT1);
		leftMotor2 = new VictorSPX(RobotMap.DRIVE_LEFT2);
		leftMotor2.follow(leftMotor1);
		
		rightMotor1 = new TalonSRX(RobotMap.DRIVE_RIGHT1);
		rightMotor2 = new VictorSPX(RobotMap.DRIVE_RIGHT2);
		rightMotor2.follow(rightMotor1);
		
		encoderLeft.setReverseDirection(true);
		encoderRight.setDistancePerPulse((Math.PI * wheelDiameter) / pulsesPerRevolution);
		encoderLeft.setDistancePerPulse((Math.PI * wheelDiameter) / pulsesPerRevolution);
	}
	protected void initDefaultCommand(){
		setDefaultCommand(new DriveWithJoystick());
	}
	public void setMotors(double left, double right) {
		leftMotor1.set(ControlMode.PercentOutput, left);
		rightMotor1.set(ControlMode.PercentOutput, -right);
	}

	public double getGyroAngle() {
		double angle = gyro.getAngle();
		SmartDashboard.putNumber("Gyro Angle", angle);
		return angle;
	}
	
	public void driveStraightGyro(double motorPower) {
		double angleError = angleError();
		double compensatedPowerRight = motorPower;
		double compensatedPowerLeft = motorPower;
		if (Math.abs(angleError) < 1) {
			if (angleError > 0) 
				compensatedPowerRight = compensatedPowerRight + motorCompDriveStraightGyro(angleError);
			else
				compensatedPowerLeft = compensatedPowerLeft + motorCompDriveStraightGyro(angleError);
		}

		SmartDashboard.putNumber("Angle Error", angleError);
		SmartDashboard.putNumber("Comp Power Left", compensatedPowerLeft);
		SmartDashboard.putNumber("Comp Power Right", compensatedPowerRight);
		setMotors(compensatedPowerRight, compensatedPowerLeft);
	}
	public double getDistanceSinceLastReset() {
		return (encoderLeft.getDistance());
	}

	public boolean hasDrivenDistance(double distInches) {
		return Math.abs(getDistanceSinceLastReset()) >= distInches;
	}
	public void stopMotors() {
		setMotors(0, 0);
	}
	public double motorCompDriveStraightGyro(double angleError) {
		return (angleError / 90) * 2;
	}
	
	public void turnAngle(double desiredAngle) {

		angleError = desiredAngle - gyro.getAngle();
		SmartDashboard.putNumber("Angle: ", gyro.getAngle());
		SmartDashboard.putNumber("Desired Angle: ", desiredAngle);
		SmartDashboard.putNumber("Angle error: ", angleError);

		if (angleError <= 180.0 || angleError <= -180.0) {
			angleError = Math.abs(angleError);

			setMotors(-motorCompAngle(angleError), motorCompAngle(angleError));
		} else {
			angleError = Math.abs(angleError);
			setMotors(-motorCompAngle(angleError), motorCompAngle(angleError));
		}
	}
	public void turnLeft(double power) {
		setMotors(-power, power);
	}

	public void turnRight(double power) {
		setMotors(power, -power);
	}

	public void turnAngleCCW() {
		angleError = Math.abs(angleError);

		setMotors(motorCompAngle(angleError), -motorCompAngle(angleError));
	}

	public void turnAngleCW() {
		angleError = Math.abs(angleError);

		setMotors(-motorCompAngle(angleError), motorCompAngle(angleError));
	}

	public double angleError() {
		double getAngle = gyro.getAngle();

		SmartDashboard.putNumber("Gyro Angle", getAngle);
		return getAngle;
	}

	public double motorCompAngle(double angleError) {
		if ((angleError + 2) >= 45)
			return 1.0;
		else
			return (1 / 1.65321) * Math.log10(angleError + 2);
	}
	public void resetGyro() {
		gyro.reset();
	}

	public void resetEncoders() {
		encoderRight.reset();
		encoderLeft.reset();
	}
}
