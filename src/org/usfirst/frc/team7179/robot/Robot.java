package org.usfirst.frc.team7179.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team7179.robot.OI;
import org.usfirst.frc.team7179.robot.commands.*;
import org.usfirst.frc.team7179.robot.commands.DriverControl.*;
import org.usfirst.frc.team7179.robot.subsystems.*;

public class Robot extends TimedRobot {
	//Subsystems
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Intake intake = new Intake();
	public static final OI OI = new OI();

	Command m_autonomousCommand;
	SendableChooser<Command> AutonomousChooser = new SendableChooser<>();
	
	public String gameData;

	@Override
	public void robotInit() {
		AutonomousChooser.addDefault("Default Auto", new DriveWithJoystick());
		//AutonomousChooser.addObject("Baseline", new BaselineAuto());
		SmartDashboard.putData("Auto mode", AutonomousChooser);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L')
		{
			//Put left auto code here
		} else {
			//Put right auto code here
		}
		
		m_autonomousCommand = AutonomousChooser.getSelected();
		 

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
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
