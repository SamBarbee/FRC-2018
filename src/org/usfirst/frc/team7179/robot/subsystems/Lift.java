package org.usfirst.frc.team7179.robot.subsystems;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.intake.IntakeCube;
import org.usfirst.frc.team7179.robot.commands.intake.StopIntake;
import org.usfirst.frc.team7179.robot.commands.lift.StopLift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem{
	TalonSRX motor1;
	VictorSPX motor2;
	public Lift() {
		motor1 = new TalonSRX(RobotMap.LIFT_LEFT);
		motor2 = new VictorSPX(RobotMap.LIFT_RIGHT);
	}
	protected void initDefaultCommand(){
		setDefaultCommand(new StopLift());
	}
	public void setMotors (double power) {
		motor1.set(ControlMode.PercentOutput, power);
		motor2.set(ControlMode.PercentOutput, power);
	}
}