package org.usfirst.frc.team7179.robot.commands.drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveStraight extends CommandGroup {

    public DriveStraight(double distance, double velocity, double acceleration) {
	addSequential(new DriveArc(distance, Double.POSITIVE_INFINITY, velocity, acceleration, false));
    }
}
