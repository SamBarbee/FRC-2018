package org.usfirst.frc.team7179.robot.commands.autonomous;

import org.usfirst.frc.team7179.robot.Robot;
import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.drive.DriveArc;
import org.usfirst.frc.team7179.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team7179.robot.commands.drive.DriveTime;
import org.usfirst.frc.team7179.robot.commands.drive.DriveTurn;
import org.usfirst.frc.team7179.robot.commands.drive.TurnTime;
import org.usfirst.frc.team7179.robot.commands.intake.RunIntake;
import org.usfirst.frc.team7179.robot.commands.lift.RunLift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Center2Cube extends DynamicAuton { 

	@Override
    protected Command buildCommand(char pos, String gameData) {
        return new Center2CubeCG(pos, gameData);
    }
	
	public class Center2CubeCG extends CommandGroup {
		Center2CubeCG(char pos, String gameData)  {
			Robot.gameData = DriverStation.getInstance().getGameSpecificMessage();
			
			if(Robot.gameData.charAt(0) == 'L')
			{
				addParallel(new RunLift(0.3));
				addSequential(new DriveArc(60,120,true));
				
				addParallel(new RunLift(-0.5));
				addSequential(new WaitCommand(0.1));
				addParallel(new RunIntake(-1));
				addSequential(new WaitCommand(0.3));
				addParallel(new RunLift(-0.5));
				addParallel(new RunIntake(0));
				
				addSequential(new DriveStraight(-30,400,1000));
				addParallel(new RunIntake(1));
				addSequential(new DriveTurn(-60,250,500));
				addSequential(new DriveStraight(12,200,500));
				
	
			} else {
				addParallel(new RunLift(0.3));
				addSequential(new DriveTime(0.25,0.35));
				addSequential(new WaitCommand(0.3));
				addSequential(new TurnTime(0.25,0.35,-1));
				addSequential(new WaitCommand(0.3));
				addSequential(new DriveTime(2.4,0.35));
				addSequential(new WaitCommand(0.3));
				addSequential(new TurnTime(0.25,0.35,1));
				addSequential(new WaitCommand(0.3));
				addSequential(new DriveTime(1,0.4));
				addSequential(new WaitCommand(0.5));
				addParallel(new RunLift(-0.2));
				addSequential(new WaitCommand(0.5));
				addParallel(new RunIntake(-1));
				addSequential(new WaitCommand(0.3));
				addParallel(new RunIntake(0));
				addSequential(new DriveTime(0.75,-0.25));
			}
		}	
}
}