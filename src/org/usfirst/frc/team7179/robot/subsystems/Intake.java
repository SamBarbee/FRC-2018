package org.usfirst.frc.team7179.robot.subsystems;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.Intake.IntakeWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	private VictorSPX leftMotor, rightMotor;
	public Intake() {
		leftMotor  = new VictorSPX(RobotMap.INTAKE_LEFT);
		rightMotor = new VictorSPX(RobotMap.INTAKE_RIGHT);
		rightMotor.setInverted(true);
		rightMotor.follow(leftMotor);
	}
	protected void initDefaultCommand(){
		setDefaultCommand(new IntakeWithJoystick());
	}
	public void setMotors (double power) {
		leftMotor.set(ControlMode.PercentOutput, power);
	}
}