package org.usfirst.frc.team7179.robot.commands.drive;

import org.usfirst.frc.team7179.robot.Robot;
import org.usfirst.frc.team7179.robot.RobotMap;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToAngle extends Command {
    double targetAngle, turnPower, currentAngle, currentAngularRate;
    boolean angleIsGood;
    
    Timer timer;
    
    PigeonIMU.GeneralStatus genStatus;
	PigeonIMU.FusionStatus fusionStatus;
	double [] xyz_dps;

    public TurnToAngle(double targetAngle){
        this.targetAngle = targetAngle;
        requires(Robot.drivetrain);
        Robot.drivetrain.pidgey.setFusedHeading(0.0, 10);
        timer = new Timer();
		timer.reset();
		timer.start();
    }

    public void execute(){
    	genStatus = new PigeonIMU.GeneralStatus();
    	fusionStatus = new PigeonIMU.FusionStatus();
    	xyz_dps = new double [3];
    			
		Robot.drivetrain.pidgey.getGeneralStatus(genStatus);
		Robot.drivetrain.pidgey.getRawGyro(xyz_dps);
		Robot.drivetrain.pidgey.getFusedHeading(fusionStatus);
		currentAngle = fusionStatus.heading;
		currentAngularRate = xyz_dps[2];
		
		turnPower = (targetAngle - currentAngle) * RobotMap.gyroP - (currentAngularRate) * RobotMap.gyroD;
		
		SmartDashboard.putNumber("Angular Rate", currentAngularRate);
		SmartDashboard.putBoolean("Angle Is Good", angleIsGood);
		
		if((currentAngularRate < 5) && (timer.get()>0.25)) {
			angleIsGood = true;
		}
		else {
			timer.reset();
			timer.start();
		}
		
		Robot.drivetrain.setMotors(-turnPower, turnPower);
    }

    @Override
    protected boolean isFinished() {
        return angleIsGood;
    }

    @Override
    protected void end() {
        Robot.drivetrain.resetGyro();
        Robot.drivetrain.leftDrive.motor1.setSelectedSensorPosition(0, 0, 10);
    	Robot.drivetrain.rightDrive.motor1.setSelectedSensorPosition(0, 0, 10);
    	Robot.drivetrain.resetEncoders();
    	Robot.drivetrain.logEncoderData();
    }
}
