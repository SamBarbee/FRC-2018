package org.usfirst.frc.team7179.robot.subsystems;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.intake.RunIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	private VictorSPX leftMotor, rightMotor;
	public VictorSP climber;
	public Intake() {
		leftMotor  = new VictorSPX(RobotMap.INTAKE_LEFT);
		rightMotor = new VictorSPX(RobotMap.INTAKE_RIGHT);
		climber = new VictorSP(0);
	}
	protected void initDefaultCommand(){
		setDefaultCommand(new RunIntake(0));
	}
	public void setMotors (double power) {
		leftMotor.set(ControlMode.PercentOutput, power);
		rightMotor.set(ControlMode.PercentOutput, power);
	}
	public void runClimber(double power) {
		climber.set(power);
	}
}