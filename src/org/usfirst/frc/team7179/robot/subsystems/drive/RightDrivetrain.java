package org.usfirst.frc.team7179.robot.subsystems.drive;

import org.usfirst.frc.team7179.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class RightDrivetrain extends PIDSubsystem {
	
	private TalonSRX motor1;
	private VictorSPX motor2;
	Encoder encoder = new Encoder(RobotMap.RIGHT_ENCODER_A,RobotMap.RIGHT_ENCODER_B);
	
	public RightDrivetrain(){
        super("Right DriveTrain", RobotMap.driveP, RobotMap.driveI, RobotMap.driveD);
        
        motor1 = new TalonSRX(RobotMap.DRIVE_RIGHT1);
		motor2 = new VictorSPX(RobotMap.DRIVE_RIGHT2);
		motor2.follow(motor1);		
    }
	public void setMotors(double power) {
		motor1.set(ControlMode.PercentOutput, -power);
	}
	public void stopMotors() {
		motor1.set(ControlMode.PercentOutput, 0);
	}
	
	@Override
	protected double returnPIDInput() {
		return encoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		setMotors(output);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
