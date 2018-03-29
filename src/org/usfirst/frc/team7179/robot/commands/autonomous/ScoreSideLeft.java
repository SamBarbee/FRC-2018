package org.usfirst.frc.team7179.robot.commands.autonomous;

import org.usfirst.frc.team7179.robot.Robot;

import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.drive.DriveTime;
import org.usfirst.frc.team7179.robot.commands.drive.TurnTime;
import org.usfirst.frc.team7179.robot.commands.intake.RunIntake;
import org.usfirst.frc.team7179.robot.commands.lift.RunLift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ScoreSideLeft extends DynamicAuton {
	
	@Override
    protected Command buildCommand(char pos, String gameData) {
        return new ScoreSideLeftCG(pos, gameData);
    }

private class ScoreSideLeftCG extends CommandGroup {
		ScoreSideLeftCG(char pos, String gameData) {
		Robot.gameData = DriverStation.getInstance().getGameSpecificMessage();
		double distance = (1024/(RobotMap.wheelDiameter * Math.PI))*RobotMap.autoLineDist;
		int oldTalonCruise = RobotMap.talonCruise;
		int oldTalonAccel = RobotMap.talonAccel;
		RobotMap.talonCruise = 300;
		
		if(Robot.gameData.charAt(0) == 'R')
		{
			addParallel(new RunLift(0.3));
			addSequential(new DriveTime(2.3,0.4));
			addSequential(new WaitCommand(1));
			addSequential(new TurnTime(0.4,0.35,1));
			addSequential(new WaitCommand(1));
			addSequential(new DriveTime(1,0.4));
			addSequential(new WaitCommand(0.5));
			addParallel(new RunLift(-0.2));
			addSequential(new WaitCommand(0.5));
			addParallel(new RunIntake(-1));
			addSequential(new WaitCommand(0.3));
			addParallel(new RunIntake(0));
			addSequential(new DriveTime(0.75,-0.25));
		} 
		else {
			addParallel(new RunLift(0.3));
			addSequential(new DriveTime(2.3,0.4));
		}
		RobotMap.talonCruise = oldTalonCruise;
		RobotMap.talonAccel = oldTalonAccel;
		}	
}
}