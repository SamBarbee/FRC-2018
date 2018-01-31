package org.usfirst.frc.team7179.robot.commands.Autonomous;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.Drive.DriveTime;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLineAuto extends CommandGroup {

	public AutoLineAuto() {
		double time = Preferences.getInstance().getDouble(RobotMap.autoLineTime, 0.0);
		double speed = Preferences.getInstance().getDouble(RobotMap.autoLineSpeed, 0.0);

		addSequential(new DriveTime(time,speed));
	}
	
}
