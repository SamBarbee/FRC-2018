package org.usfirst.frc.team7179.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team7179.robot.commands.intake.RunIntake;
import org.usfirst.frc.team7179.robot.commands.lift.RunLift;

public class OI {
	private XboxController xboxController = new XboxController(RobotMap.XBOX_CONTROLLER);
	
	private Hand rightHand = Hand.kRight; private Hand leftHand  = Hand.kLeft;
	private double leftDriverSpeed, rightDriverSpeed;
	 
	public OI() {
	
	JoystickButton upRightPad = new JoystickButton(xboxController,2);
	JoystickButton downRightPad = new JoystickButton(xboxController,4);
	
	JoystickButton upLeftPad = new JoystickButton(xboxController,1);
	JoystickButton downLeftPad = new JoystickButton(xboxController,3);
	
	JoystickButton leftBump = new JoystickButton(xboxController, 5);
	JoystickButton rightBump = new JoystickButton(xboxController, 6);
	
	
	rightBump.whenPressed(new RunIntake(1));
	leftBump.whenPressed(new RunIntake(-1));
	
	rightBump.whenReleased(new RunIntake(0));
	leftBump.whenReleased(new RunIntake(0));
	
	upRightPad.whenPressed(new RunLift(0.65));
	downRightPad.whenPressed(new RunLift(-0.8));
	upLeftPad.whenPressed(new RunLift(0.3));
	downLeftPad.whenPressed(new RunLift(-0.3));

	upRightPad.whenReleased(new RunLift(0.0));
	downRightPad.whenReleased(new RunLift(0.0));
	upLeftPad.whenReleased(new RunLift(0.0));
	downLeftPad.whenReleased(new RunLift(0.0));
	
	
	}
	
	public double getLeftSpeed(){
		leftDriverSpeed = constrain(xboxController.getX(rightHand)-(xboxController.getY(rightHand)/Math.abs(((xboxController.getX(rightHand)/2)+Math.signum(xboxController.getY(rightHand))))));
		return Math.abs(leftDriverSpeed)>0.1?leftDriverSpeed:0;
	}
	public double getRightSpeed(){
		rightDriverSpeed = constrain(xboxController.getX(rightHand)+(xboxController.getY(rightHand)/Math.abs(((xboxController.getX(rightHand)/2)+Math.signum(xboxController.getY(rightHand))))));
		return Math.abs(rightDriverSpeed)>0.1?rightDriverSpeed:0;
	}
	private double constrain(double value){
		return Math.max(-1, Math.min(1, value));
	}
	
	
}
