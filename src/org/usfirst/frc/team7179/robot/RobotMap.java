package org.usfirst.frc.team7179.robot;

public class RobotMap {
	public static final int PDP_ID = 0;
	public static final int XBOX_CONTROLLER = 0;
	
	public static final int DRIVE_LEFT1 = 1;
	public static final int DRIVE_LEFT2 = 2;
	public static final int DRIVE_RIGHT1 = 8;
	public static final int DRIVE_RIGHT2 = 7;
	
	public static final int INTAKE_LEFT = 4;
	public static final int INTAKE_RIGHT = 5;
	
	public static final int LIFT_LEFT = 6;
	public static final int LIFT_RIGHT = 3;
	
	public static double driveF = 0.5;
	public static double driveP = 0.6;
	public static double driveI = 0.0;
	public static double driveD = 0.0;
	
	public static double gyroP = 1.0;
	public static double gyroI = 0.0;
	public static double gyroD = 0.0;
	
	public static double autoLineTime = 3;
	public static double autoLineSpeed = 0.35;

	public static int talonTimeoutMs = 10;
	public static int talonCruise = 300;
	public static int talonAccel = 2000;
	
	public static int RightSidePID = 0;
	public static int LeftSidePID = 1;
}
