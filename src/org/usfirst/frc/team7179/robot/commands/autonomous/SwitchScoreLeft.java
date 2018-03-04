package org.usfirst.frc.team7179.robot.commands.autonomous;

import org.usfirst.frc.team7179.robot.Robot;
import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.drive.DriveTime;
import org.usfirst.frc.team7179.robot.commands.drive.EncoderDrive;
import org.usfirst.frc.team7179.robot.commands.drive.TurnTime;
import org.usfirst.frc.team7179.robot.commands.intake.RunIntake;
import org.usfirst.frc.team7179.robot.commands.lift.RunLift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SwitchScoreLeft extends CommandGroup {
	public SwitchScoreLeft() {
		Robot.gameData = DriverStation.getInstance().getGameSpecificMessage();
		double distance = (1024/(RobotMap.wheelDiameter * Math.PI))*RobotMap.autoLineDist;
		int oldTalonCruise = RobotMap.talonCruise;
		int oldTalonAccel = RobotMap.talonAccel;
		RobotMap.talonCruise = 300;
		
		if(Robot.gameData.charAt(0) == 'L')
		{
			addParallel(new RunLift(0.3));
			addSequential(new DriveTime(2,0.4));
			addSequential(new WaitCommand(0.5));
			addParallel(new RunIntake(-0.75));
			addSequential(new WaitCommand(0.3));
			addParallel(new RunIntake(0));
			addSequential(new DriveTime(0.75,-0.25));
		} else {
			addParallel(new RunLift(0.3));
			addSequential(new DriveTime(0.25,0.35));
			addSequential(new WaitCommand(0.3));
			addSequential(new TurnTime(0.25,0.35,-1));
			addSequential(new WaitCommand(0.3));
			addSequential(new DriveTime(3,0.35));
			addSequential(new WaitCommand(0.3));
			addSequential(new TurnTime(0.25,0.35,1));
			addSequential(new WaitCommand(0.3));
			addSequential(new DriveTime(1,0.4));
			addSequential(new WaitCommand(0.5));
			addParallel(new RunIntake(-0.75));
			addSequential(new WaitCommand(0.3));
			addParallel(new RunIntake(0));
			addSequential(new DriveTime(0.75,-0.25));
		}
		RobotMap.talonCruise = oldTalonCruise;
		RobotMap.talonAccel = oldTalonAccel;
		}	
}