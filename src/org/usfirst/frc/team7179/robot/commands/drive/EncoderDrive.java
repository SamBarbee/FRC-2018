package org.usfirst.frc.team7179.robot.commands.drive;

import org.usfirst.frc.team7179.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class EncoderDrive extends Command {
    double setpoint;


    public EncoderDrive(double targetValue){
        requires(Robot.drivetrain);
        setpoint = targetValue;
    }

    public void execute(){
        Robot.drivetrain.getPIDController().disable(); //Disable Gyro PID
        Robot.drivetrain.enableEncoderPID();
        Robot.drivetrain.setSetpointEncoderDrivePID(setpoint);
    }

    @Override
    protected boolean isFinished() {
        return Robot.drivetrain.getEncoderPIDOnTarget();
    }

    @Override
    protected void end() {
        Robot.drivetrain.disableEncoderPID();
        Robot.drivetrain.resetEncoders();
    }
}
