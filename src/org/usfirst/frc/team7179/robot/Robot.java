package org.usfirst.frc.team7179.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team7179.robot.OI;
import org.usfirst.frc.team7179.robot.commands.autonomous.DynamicAuton;
import org.usfirst.frc.team7179.robot.commands.autonomous.AutoLineAuto;
import org.usfirst.frc.team7179.robot.commands.autonomous.ScoreSideLeft;
import org.usfirst.frc.team7179.robot.commands.autonomous.SwitchScoreLeft;
import org.usfirst.frc.team7179.robot.commands.autonomous.SwitchScoreRight;
import org.usfirst.frc.team7179.robot.commands.drive.*;
import org.usfirst.frc.team7179.robot.subsystems.*;
import org.usfirst.frc.team7179.robot.subsystems.drive.Drivetrain;
import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends TimedRobot {
	//Subsystems
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Intake intake = new Intake();
	public static final Lift lift = new Lift();
	public static final OI OI = new OI();

	public static enum Side {left,right,center};
	
	Command AutonomousCommand;
	SendableChooser<DynamicAuton> AutonomousChooser = new SendableChooser<>();
	
	public static String gameData;

	@Override
	public void robotInit() {
		AutonomousChooser.addDefault("Auto Disabled", null);
		AutonomousChooser.addObject("Autoline", new AutoLineAuto());
		AutonomousChooser.addObject("Score Switch - Left Start", new SwitchScoreLeft());
		AutonomousChooser.addObject("Score Switch - Right Start", new SwitchScoreRight());
		AutonomousChooser.addObject("ScoreSide - Right", new ScoreSideLeft());
		SmartDashboard.putData("Auto mode", AutonomousChooser);
		
		CameraServer.getInstance().startAutomaticCapture();
	};
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		DynamicAuton builder = AutonomousChooser.getSelected();
		 
		if (builder != null) {
			AutonomousCommand = builder.build();
			AutonomousCommand.start();
		}
	}
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (AutonomousCommand != null) {
			AutonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}
