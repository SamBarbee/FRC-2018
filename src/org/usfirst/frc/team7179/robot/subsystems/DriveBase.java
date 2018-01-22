package org.usfirst.frc.team7179.robot.subsystems;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.RunDrivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem{
	private TalonSRX leftMotor1, rightMotor1;
	private VictorSPX leftMotor2, rightMotor2;
	public DriveBase(){
		//Create left motors
		leftMotor1 = new TalonSRX(RobotMap.DRIVE_LEFT1);
		leftMotor2 = new VictorSPX(RobotMap.DRIVE_LEFT2);
		leftMotor2.follow(leftMotor1);
		
		//Create right motors
		rightMotor1 = new TalonSRX(RobotMap.DRIVE_RIGHT1);
		rightMotor2 = new VictorSPX(RobotMap.DRIVE_RIGHT2);
		rightMotor2.follow(rightMotor1);
	}
	protected void initDefaultCommand(){
		setDefaultCommand(new RunDrivetrain());
	}
	public void move(double left, double right) {
		leftMotor1.set(ControlMode.PercentOutput, left);
		rightMotor1.set(ControlMode.PercentOutput, -right);
	}

}
