package org.usfirst.frc.team7179.robot.commands.drive;

import org.usfirst.frc.team7179.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderDrive extends Command {
    public double setpoint;


    public EncoderDrive(double targetValue){
        requires(Robot.drivetrain);
        setpoint = targetValue;
        Robot.drivetrain.leftDrive.motor1.setSelectedSensorPosition(0, 0, 10);
    	Robot.drivetrain.rightDrive.motor1.setSelectedSensorPosition(0, 0, 10);
        Robot.drivetrain.logEncoderData();
        Robot.drivetrain.resetEncoders();
    }

    public void execute(){
    	Robot.drivetrain.setEncoderPID(setpoint);
    	Robot.drivetrain.logEncoderData();
        SmartDashboard.putString("Encoder", "run");
    }

    @Override
    protected boolean isFinished() {
        return Robot.drivetrain.getEncoderPIDOnTarget();
    }

    @Override
    protected void end() {
    	Robot.drivetrain.leftDrive.motor1.setSelectedSensorPosition(0, 0, 10);
    	Robot.drivetrain.rightDrive.motor1.setSelectedSensorPosition(0, 0, 10);
    	SmartDashboard.putString("Encoder", "done");
    	Robot.drivetrain.resetEncoders();
    	Robot.drivetrain.logEncoderData();
    }
}
