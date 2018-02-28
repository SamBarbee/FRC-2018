package org.usfirst.frc.team7179.robot.commands.autonomous;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.drive.EncoderDrive;
import org.usfirst.frc.team7179.robot.commands.lift.RunLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLineAuto extends CommandGroup {
	public AutoLineAuto() {
		double distance = (1024/(RobotMap.wheelDiameter * Math.PI))*RobotMap.autoLineDist;
		int oldTalonCruise = RobotMap.talonCruise;
		int oldTalonAccel = RobotMap.talonAccel;
		RobotMap.talonCruise = 300;
		addParallel(new RunLift(0.3));
		addSequential(new EncoderDrive(distance));
		RobotMap.talonCruise = oldTalonCruise;
		RobotMap.talonAccel = oldTalonAccel;
		}	
}