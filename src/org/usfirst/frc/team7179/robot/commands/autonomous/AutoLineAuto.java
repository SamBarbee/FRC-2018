package org.usfirst.frc.team7179.robot.commands.autonomous;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.drive.EncoderDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLineAuto extends CommandGroup {
	public AutoLineAuto() {
		double distance = (1024/(RobotMap.wheelDiameter * Math.PI))*RobotMap.autoLineDist;
		int oldTalonCruise = RobotMap.talonCruise;
		int oldTalonAccel = RobotMap.talonAccel;
		RobotMap.talonCruise = 700;
		addSequential(new EncoderDrive(distance));
		RobotMap.talonCruise = oldTalonCruise;
		RobotMap.talonAccel = oldTalonAccel;
		}	
}