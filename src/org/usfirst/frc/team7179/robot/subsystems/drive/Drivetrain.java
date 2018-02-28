package org.usfirst.frc.team7179.robot.subsystems.drive;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.drive.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem{
	
	public DrivetrainSide leftDrive;
	public DrivetrainSide rightDrive;
	public PigeonIMU pidgey;
	
	
	public Drivetrain() {
		
		leftDrive = new DrivetrainSide(RobotMap.DRIVE_LEFT1,RobotMap.DRIVE_LEFT2,RobotMap.LeftSidePID,false,false);
		rightDrive = new DrivetrainSide(RobotMap.DRIVE_RIGHT1,RobotMap.DRIVE_RIGHT2,RobotMap.RightSidePID,true,false);
		pidgey = new PigeonIMU(RobotMap.pidgeyAddress);

	}
	protected void initDefaultCommand(){
		setDefaultCommand(new DriveWithJoystick());
	}
	public void setMotors(double left, double right) {
		leftDrive.setMotors(left);
		rightDrive.setMotors(right);
	}
	public void stopMotors() {
		leftDrive.stopMotors();
		rightDrive.stopMotors();
	}
	
	public void resetGyro() {
		//gyro.reset();
	}
	 public void resetEncoders() {
		leftDrive.motor1.setSelectedSensorPosition(0, 0, 10);
        rightDrive.motor1.setSelectedSensorPosition(0, 0, 10);
    }
	
	public void logEncoderData() {
		SmartDashboard.putNumber("Right Position", rightDrive.motor1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Velocity", rightDrive.motor1.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Right Setpoint", rightDrive.motor1.getClosedLoopTarget(0));
		SmartDashboard.putNumber("Right Error", rightDrive.motor1.getClosedLoopError(0));
		
		SmartDashboard.putNumber("Left Position", leftDrive.motor1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Left Velocity", leftDrive.motor1.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Left Setpoint", leftDrive.motor1.getClosedLoopTarget(0));
		SmartDashboard.putNumber("Left Error", leftDrive.motor1.getClosedLoopError(0));
	}

    public void setEncoderPID(double setpoint){
        leftDrive.motor1.set(ControlMode.MotionMagic, setpoint);
        rightDrive.motor1.set(ControlMode.MotionMagic, setpoint);
    }

    public boolean getEncoderPIDOnTarget(){
        return (Math.abs(rightDrive.motor1.getSelectedSensorVelocity(0))<10)&&(Math.abs(leftDrive.motor1.getSelectedSensorVelocity(0))<10)&&((Math.abs(leftDrive.motor1.getClosedLoopTarget(0) - leftDrive.motor1.getSelectedSensorPosition(0)))<200) && ((Math.abs(rightDrive.motor1.getClosedLoopTarget(0) - rightDrive.motor1.getSelectedSensorPosition(0)))<200);
    	
    }
}
