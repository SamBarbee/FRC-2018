package org.usfirst.frc.team7179.robot.subsystems;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.intake.IntakeCube;
import org.usfirst.frc.team7179.robot.commands.intake.StopIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{
	private VictorSPX leftMotor, rightMotor;
	public Shooter() {
		leftMotor  = new VictorSPX(RobotMap.SHOOTER_LEFT);
		rightMotor = new VictorSPX(RobotMap.SHOOTER_RIGHT);
		rightMotor.setInverted(true);
	}
	protected void initDefaultCommand(){
		setDefaultCommand(new StopIntake());
	}
	public void setMotors (double power) {
		leftMotor.set(ControlMode.PercentOutput, power);
		rightMotor.set(ControlMode.PercentOutput, power);
	}
}