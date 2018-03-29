package org.usfirst.frc.team7179.robot.subsystems.drive;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.drive.DriveWithJoystick;
import org.usfirst.frc.team7179.robot.subsystems.drive.motionmagic.MotionMagicPair;
import org.usfirst.frc.team7179.robot.subsystems.drive.motionmagic.MotionMagicUnit;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem{
	
	public static final String NAME = "Drive Train";

	public static final double ROBOT_WIDTH_INCHES = 25;
	public static final double ROBOT_LENGTH_INCHES = 11;
	public static final double TURNING_CIRCUMFERENCE = Math.PI * ROBOT_WIDTH_INCHES;
	public static final double EFFECTIVE_TURNING_CIRCUMFERENCE = TURNING_CIRCUMFERENCE * 1;
	
	private final TalonSRX left1 = new TalonSRX(RobotMap.DRIVE_LEFT1);
	private final VictorSPX left2 = new VictorSPX(RobotMap.DRIVE_LEFT2);
	private final TalonSRX right1 = new TalonSRX(RobotMap.DRIVE_RIGHT1);
	private final VictorSPX right2 = new VictorSPX(RobotMap.DRIVE_RIGHT2);
	
	public final MotionMagicPair motionMagic;
	private MotionMagicUnit leftMM;
	private MotionMagicUnit rightMM;
	
	public static final double MAX_VOLTAGE = 12;
	public static final int MAX_AMPS = 40;

	public static final int DEFAULT_PROFILE = 0;
	
	public static final int ENCODER_CODES_PER_REV = 512;
	
	public static final double INCHES_PER_REV = 6.0 * Math.PI;
	
	public static final double MAX_ALLOWED_VELOCITY = 525; // RMP
	public static final double MAX_ALLOWED_ACCELERATION = 1000; // RPM/sec
	
	public static final double MAX_LEFT_RPM = 550;
	public static final double MAX_RIGHT_RPM = 550;
	
	public static final int VELOCITY_MEASUREMENT_WINDOW = 10; // ms
	public static final int ALLOWED_ERROR = 0; // give in inches
	public static final int IZONE = 0; // give in inches
	public static final double CLOSED_LOOP_RAMP_RATE = MAX_VOLTAGE * 4;
	
	public static final double DEFAULT_ACCELERATION = 450; // RPM per second
	public static final double DEFAULT_VELOCITY = 450;
	
	public Drivetrain() {
		super(NAME);
		configureTalons();
		
		motionMagic = new MotionMagicPair(leftMM, rightMM);
	}
	
	private void configureTalons() {
		
		left2.follow(left1);
		right2.follow(right2);

		right1.setInverted(true);
		right2.setInverted(true);
		
		left1.configNominalOutputForward(0, RobotMap.talonTimeoutMs);
		left1.configNominalOutputReverse(0, RobotMap.talonTimeoutMs);
		left1.configPeakOutputForward(1, RobotMap.talonTimeoutMs);
		left1.configPeakOutputReverse(-1, RobotMap.talonTimeoutMs);

		right1.configNominalOutputForward(0, RobotMap.talonTimeoutMs);
		right1.configNominalOutputReverse(0, RobotMap.talonTimeoutMs);
		right1.configPeakOutputForward(1, RobotMap.talonTimeoutMs);
		right1.configPeakOutputReverse(-1, RobotMap.talonTimeoutMs);
		
		left1.selectProfileSlot(RobotMap.DrivePID,0);
		left1.config_kF(RobotMap.DrivePID, RobotMap.driveF, RobotMap.talonTimeoutMs);
		left1.config_kP(RobotMap.DrivePID, RobotMap.driveP, RobotMap.talonTimeoutMs);
		left1.config_kI(RobotMap.DrivePID, RobotMap.driveI, RobotMap.talonTimeoutMs);
		left1.config_kD(RobotMap.DrivePID, RobotMap.driveD, RobotMap.talonTimeoutMs);
		
		right1.selectProfileSlot(RobotMap.DrivePID,0);
		right1.config_kF(RobotMap.DrivePID, RobotMap.driveF, RobotMap.talonTimeoutMs);
		right1.config_kP(RobotMap.DrivePID, RobotMap.driveP, RobotMap.talonTimeoutMs);
		right1.config_kI(RobotMap.DrivePID, RobotMap.driveI, RobotMap.talonTimeoutMs);
		right1.config_kD(RobotMap.DrivePID, RobotMap.driveD, RobotMap.talonTimeoutMs);

		leftMM = new MotionMagicUnit("Left", left1, INCHES_PER_REV, ALLOWED_ERROR);
		rightMM = new MotionMagicUnit("Right", right1, INCHES_PER_REV, ALLOWED_ERROR);
		
		left1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.talonTimeoutMs);
		left1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.talonTimeoutMs);
		
		right1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.talonTimeoutMs);
		right1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.talonTimeoutMs);
		
	}
	
	public void setControlMode(ControlMode mode) {
		left1.set(mode,0);
		right1.set(mode,0);
	}
	
	public boolean isControlMode(ControlMode mode) {
		return left1.getControlMode().equals(mode) && right1.getControlMode().equals(mode);
	}

	public ControlMode getControlMode() {
		return left1.getControlMode(); // both sides should be same
	}
	
	protected void initDefaultCommand(){
		setDefaultCommand(new DriveWithJoystick());
	}
	public void setMotors(double left, double right) {
		left1.set(ControlMode.PercentOutput, left);
		right1.set(ControlMode.PercentOutput, right);
	}
	public void stopMotors() {
		left1.set(ControlMode.PercentOutput, 0);
		right1.set(ControlMode.PercentOutput, 0);
	}
	
	public static double encoderTicksToRev(double ticks) {
		return ticks / ENCODER_CODES_PER_REV;
	}

	public static double revToInches(double rev) {
		return INCHES_PER_REV * rev;
	}

	public static double inchesToRev(double inches) {
		return inches / INCHES_PER_REV;
	}

	public static double revToEncoderTicks(double rev) {
		return rev * ENCODER_CODES_PER_REV;
	}

	public static double encoderTicksToInches(double ticks) {
		return revToInches(encoderTicksToRev(ticks));
	}

	public static double inchesToEncoderTicks(double inches) {
		return revToEncoderTicks(inchesToRev(inches));
	}
	
}
