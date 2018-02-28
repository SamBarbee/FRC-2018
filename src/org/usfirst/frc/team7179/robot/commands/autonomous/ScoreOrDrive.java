package org.usfirst.frc.team7179.robot.commands.autonomous;

import org.usfirst.frc.team7179.robot.Robot;
import org.usfirst.frc.team7179.robot.Robot.Side;
import org.usfirst.frc.team7179.robot.RobotMap;
import org.usfirst.frc.team7179.robot.commands.drive.EncoderDrive;
import org.usfirst.frc.team7179.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team7179.robot.commands.intake.RunIntake;
import org.usfirst.frc.team7179.robot.commands.lift.RunLift;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ScoreOrDrive extends CommandGroup {
	public ScoreOrDrive(Robot.Side side) {
		int oldTalonCruise = RobotMap.talonCruise;
		int oldTalonAccel = RobotMap.talonAccel;
		
		
		RobotMap.talonCruise = 700;
		RobotMap.talonAccel = 1000;
		
		addParallel(new RunLift(0.3));
		addSequential(new EncoderDrive(Robot.inchesToTicks(RobotMap.walltoMidSwitch)));
		
		String switchData = Robot.getGameData();
		if((switchData.charAt(0) == 'L')&& side == Side.left) {
			addSequential(new TurnToAngle(90));
			addSequential(new EncoderDrive(Robot.inchesToTicks(24)));
			addParallel(new RunIntake(1.0));
			
			addSequential(new WaitCommand(0.5));
			
			addParallel(new RunIntake(0));
			addParallel(new RunLift(0));
			addSequential(new EncoderDrive(Robot.inchesToTicks(-24)));
		} 
		else if((switchData.charAt(0) == 'L')&& side == Side.right) {
			
		}
		else if((switchData.charAt(0) == 'R')&& side == Side.left) {
			
		}
		else if((switchData.charAt(0) == 'R')&& side == Side.right) {
			addSequential(new TurnToAngle(-90));
			addSequential(new EncoderDrive(Robot.inchesToTicks(24)));
			addParallel(new RunIntake(1.0));
			
			addSequential(new WaitCommand(0.5));
			
			addParallel(new RunIntake(0));
			addParallel(new RunLift(0));
			addSequential(new EncoderDrive(Robot.inchesToTicks(-24)));
		}
		
		
		RobotMap.talonCruise = oldTalonCruise;
		RobotMap.talonAccel = oldTalonAccel;
	}	
}