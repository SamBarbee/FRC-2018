package org.usfirst.frc.team7179.robot.subsystems.drive;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.drive.DriveWithJoystick;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Drivetrain extends PIDSubsystem{
	
	LeftDrivetrain leftDrive;
	RightDrivetrain rightDrive;
	
	ADXRS450_Gyro gyro;
	
	public Drivetrain() {
		
		super("Drivetrain", RobotMap.gyroP, RobotMap.gyroI, RobotMap.gyroD);
		
		leftDrive = new LeftDrivetrain();
		rightDrive = new RightDrivetrain();
		gyro = new ADXRS450_Gyro();
		
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
		gyro.reset();
	}
	 public void resetEncoders() {
        leftDrive.encoder.reset();
        rightDrive.encoder.reset();
    }

	@Override
	protected double returnPIDInput() {
		return gyro.getAngle();
	}
	@Override
	protected void usePIDOutput(double output) {
		setMotors(-output,output);
	}
	public void enableEncoderPID(){
        this.getPIDController().disable();
        leftDrive.getPIDController().enable();
        rightDrive.getPIDController().enable();
    }

    public void setSetpointEncoderDrivePID(double setpoint){
        leftDrive.getPIDController().setSetpoint(setpoint);
        rightDrive.getPIDController().setSetpoint(setpoint);
    }

    public boolean getEncoderPIDOnTarget(){
        return leftDrive.onTarget() && rightDrive.onTarget();
    }
    public void disableEncoderPID(){
        leftDrive.getPIDController().disable();
        rightDrive.getPIDController().disable();
    }
}
