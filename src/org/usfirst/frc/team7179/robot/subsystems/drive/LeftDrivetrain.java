package org.usfirst.frc.team7179.robot.subsystems.drive;

import org.usfirst.frc.team7179.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LeftDrivetrain extends Subsystem {
	
	public TalonSRX motor1;
	public VictorSPX motor2;
	
	public LeftDrivetrain(){
        motor1 = new TalonSRX(RobotMap.DRIVE_LEFT1);
		motor2 = new VictorSPX(RobotMap.DRIVE_LEFT2);
		motor2.follow(motor1);
		
		motor1.setSensorPhase(false);
		
		motor1.setInverted(false);
		motor2.setInverted(false);
		
		motor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.talonTimeoutMs);
		
		/* Set relevant frame periods to be at least as fast as periodic rate */
		motor1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.talonTimeoutMs);
		motor1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.talonTimeoutMs);

		/* set the peak and nominal outputs */
		motor1.configNominalOutputForward(0, RobotMap.talonTimeoutMs);
		motor1.configNominalOutputReverse(0, RobotMap.talonTimeoutMs);
		motor1.configPeakOutputForward(1, RobotMap.talonTimeoutMs);
		motor1.configPeakOutputReverse(-1, RobotMap.talonTimeoutMs);

		/* set closed loop gains in slot0 - see documentation */
		motor1.selectProfileSlot(RobotMap.LeftSidePID,0);
		motor1.config_kF(RobotMap.LeftSidePID, RobotMap.driveF, RobotMap.talonTimeoutMs);
		motor1.config_kP(RobotMap.LeftSidePID, RobotMap.driveP, RobotMap.talonTimeoutMs);
		motor1.config_kI(RobotMap.LeftSidePID, RobotMap.driveI, RobotMap.talonTimeoutMs);
		motor1.config_kD(RobotMap.LeftSidePID, RobotMap.driveD, RobotMap.talonTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		motor1.configMotionCruiseVelocity(200, RobotMap.talonTimeoutMs);
		motor1.configMotionAcceleration(2000, RobotMap.talonTimeoutMs);
	    
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
