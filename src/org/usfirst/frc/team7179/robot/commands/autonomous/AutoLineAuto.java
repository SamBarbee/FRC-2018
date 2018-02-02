package org.usfirst.frc.team7179.robot.commands.autonomous;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.drive.DriveTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLineAuto extends CommandGroup {
	public AutoLineAuto() {
		double time = RobotMap.autoLineTime;
		double speed = RobotMap.autoLineSpeed;
		addSequential(new DriveTime(time,speed));
	}	
}