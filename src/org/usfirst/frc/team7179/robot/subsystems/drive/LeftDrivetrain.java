package org.usfirst.frc.team7179.robot.subsystems.drive;

import org.usfirst.frc.team7179.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LeftDrivetrain extends PIDSubsystem {
	
	private TalonSRX motor1;
	private VictorSPX motor2;
	Encoder encoder = new Encoder(RobotMap.LEFT_ENCODER_A,RobotMap.LEFT_ENCODER_B);
	
	public LeftDrivetrain(){
        super("Left DriveTrain", RobotMap.driveP, RobotMap.driveI, RobotMap.driveD);
        
        motor1 = new TalonSRX(RobotMap.DRIVE_LEFT1);
		motor2 = new VictorSPX(RobotMap.DRIVE_LEFT2);
		motor2.follow(motor1);	
		
		motor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		
		motor1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 10);
		motor1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 10);
		
		motor1.configNominalOutputForward(0, 10);
		motor1.configNominalOutputReverse(0, 10);
		motor1.configPeakOutputForward(1, 10);
		motor1.configPeakOutputReverse(-1, 10);
		
		motor1.selectProfileSlot(0, 0);
		motor1.config_kF(0, 0.2, 10);
		motor1.config_kP(0, 0.2, 10);
		motor1.config_kI(0, 0, 10);
		motor1.config_kD(0, 0, 10);

		motor1.configMotionCruiseVelocity(15000, 10);
		motor1.configMotionAcceleration(6000, 10);
		
		motor1.setSelectedSensorPosition(0, 0, 10);
    }
	public void setMotors(double power) {
		motor1.set(ControlMode.PercentOutput, power);
		SmartDashboard.putNumber("Left Drive", motor1.getSelectedSensorPosition(0));
	}
	public void stopMotors() {
		motor1.set(ControlMode.PercentOutput, 0);
	}
	public void setMotionMagicTarget(int target) {
		motor1.set(ControlMode.MotionMagic, target);
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
