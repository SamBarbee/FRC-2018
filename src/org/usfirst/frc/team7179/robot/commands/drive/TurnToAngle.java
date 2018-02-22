package org.usfirst.frc.team7179.robot.commands.drive;

import org.usfirst.frc.team7179.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command {
    double setpoint;

    public TurnToAngle(double targetAngle){
        setpoint = targetAngle;
        requires(Robot.drivetrain);
    }

    public void execute(){
       // Robot.drivetrain.disableEncoderPID();

//        Robot.drivetrain.getPIDController().enable();
//        Robot.drivetrain.getPIDController().setSetpoint(setpoint);
    }

    @Override
    protected boolean isFinished() {
        return /*Robot.drivetrain.getPIDController().onTarget();*/ true;
    }

    @Override
    protected void end() {
        //Robot.drivetrain.getPIDController().disable();
        Robot.drivetrain.resetGyro();
    }
}
