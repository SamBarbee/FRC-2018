package org.usfirst.frc.team7179.robot.subsystems.drive;

import org.usfirst.frc.team7179.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DrivetrainSide extends Subsystem {
	
	int motor1ID, motor2ID, pidID; boolean motorPhase, sensorPhase;
	
	public TalonSRX motor1;
	public VictorSPX motor2;
	
	public DrivetrainSide(int motor1ID, int motor2ID, int pidID, boolean motorPhase, boolean sensorPhase) {
		this.motor1ID = motor1ID;
		this.motor2ID = motor2ID;
		this.pidID = pidID;
		this.motorPhase = motorPhase;
		this.sensorPhase = sensorPhase;
		
		motor1 = new TalonSRX(this.motor1ID);
		motor2 = new VictorSPX(this.motor2ID);
		motor2.follow(motor1);
		
		motor1.setSensorPhase(this.sensorPhase);
		
		motor1.setInverted(this.motorPhase);
		motor2.setInverted(this.motorPhase);
		
		motor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.talonTimeoutMs);
		
		/* Set relevant frame periods to be at least as fast as periodic rate */
		motor1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.talonTimeoutMs);
		motor1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.talonTimeoutMs);
		
		motor1.configNominalOutputForward(0, RobotMap.talonTimeoutMs);
		motor1.configNominalOutputReverse(0, RobotMap.talonTimeoutMs);
		motor1.configPeakOutputForward(1, RobotMap.talonTimeoutMs);
		motor1.configPeakOutputReverse(-1, RobotMap.talonTimeoutMs);
		
		motor1.selectProfileSlot(this.pidID,0);
		motor1.config_kF(this.pidID, RobotMap.driveF, RobotMap.talonTimeoutMs);
		motor1.config_kP(this.pidID, RobotMap.driveP, RobotMap.talonTimeoutMs);
		motor1.config_kI(this.pidID, RobotMap.driveI, RobotMap.talonTimeoutMs);
		motor1.config_kD(this.pidID, RobotMap.driveD, RobotMap.talonTimeoutMs);
		
		motor1.configMotionCruiseVelocity(RobotMap.talonCruise, RobotMap.talonTimeoutMs);
		motor1.configMotionAcceleration(RobotMap.talonAccel, RobotMap.talonTimeoutMs);
	    
		motor1.setSelectedSensorPosition(0, 0, RobotMap.talonTimeoutMs);
	}
	public void setMotors(double power) {
		motor1.set(ControlMode.PercentOutput, power);
	}
	public void stopMotors() {
		motor1.set(ControlMode.PercentOutput, 0);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
