package org.usfirst.frc.team7179.robot.commands.drive;


import org.usfirst.frc.team7179.robot.subsystems.drive.Drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveTurn extends CommandGroup {

    public DriveTurn(double angle, double velocity, double acceleration) {
	double distance = Math.abs(angle / 360.0 * Drivetrain.TURNING_CIRCUMFERENCE);
	addSequential(new DriveArc(distance, 0.0, velocity, acceleration, angle < 0));
    }
}
