package org.usfirst.frc.team7179.robot.subsystems;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.intake.IntakeCube;
import org.usfirst.frc.team7179.robot.commands.intake.StopIntake;
import org.usfirst.frc.team7179.robot.commands.lift.StopLift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem{
	private VictorSPX leftMotor, rightMotor;
	public Lift() {
		leftMotor  = new VictorSPX(RobotMap.LIFT_LEFT);
		rightMotor = new VictorSPX(RobotMap.LIFT_RIGHT);
	}
	protected void initDefaultCommand(){
		setDefaultCommand(new StopLift());
	}
	public void setMotors (double power) {
		leftMotor.set(ControlMode.PercentOutput, power);
		rightMotor.set(ControlMode.PercentOutput, power);
	}
}