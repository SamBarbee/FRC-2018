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
	
	public static int pidgeyAddress = 9;
	
	public static double driveF = 0.5;
	public static double driveP = 0.6;
	public static double driveI = 0.0;
	public static double driveD = 0.0;
	
	public static double gyroP = 0.1;
	public static double gyroI = 0.0;
	public static double gyroD = 0.01;
	
	public static int talonTimeoutMs = 10;
	public static int talonCruise = 500;
	public static int talonAccel = 1500;
	
	//public static int RightSidePID = 0;
	//public static int LeftSidePID = 1;
	
	public static final int DrivePID = 0;
	
	public static double wheelDiameter = 6;
	
	public static double autoLineDist = 11*12;
}
