package org.usfirst.frc.team7179.robot;

public class RobotMap {
	public static final int PDP_ID = 61;
	public static final int XBOX_CONTROLLER = 0;
	
	public static final int DRIVE_LEFT1 = 1;
	public static final int DRIVE_LEFT2 = 2;
	public static final int DRIVE_RIGHT1 = 10;
	public static final int DRIVE_RIGHT2 = 9;
	
	public static final int RIGHT_ENCODER_A = 1;
	public static final int RIGHT_ENCODER_B = 2;
	public static final int LEFT_ENCODER_A = 3;
	public static final int LEFT_ENCODER_B = 4;
	
	public static final int INTAKE_LEFT = 5;
	public static final int INTAKE_RIGHT = 6;

	public static final int SHOOTER_LEFT = 4;
	public static final int SHOOTER_RIGHT = 7;
	
	public static final int LIFT_LEFT = 3;
	public static final int LIFT_RIGHT = 8;
	
	public static double driveP = 1.0;
	public static double driveI = 0.0;
	public static double driveD = 0.0;
	
	public static double gyroP = 1.0;
	public static double gyroI = 0.0;
	public static double gyroD = 0.0;
	
	public static double autoLineTime = 3;
	public static double autoLineSpeed = 0.35;

}
