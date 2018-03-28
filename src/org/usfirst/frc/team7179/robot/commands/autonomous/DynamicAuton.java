package org.usfirst.frc.team7179.robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class DynamicAuton {
    private static SendableChooser<Character> posChooser;

    protected DynamicAuton() {
        if (posChooser == null) {
            posChooser = new SendableChooser<>();
            posChooser.addObject("Left", 'L');
            posChooser.addDefault("Right", 'R');
            SmartDashboard.putData("Starting Position", posChooser);
        }
    }

    public final Command build() {
        return buildCommand(posChooser.getSelected(), DriverStation.getInstance().getGameSpecificMessage());
    }

    protected abstract Command buildCommand(char pos, String gameData);
}
