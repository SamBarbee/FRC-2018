package org.usfirst.frc.team7179.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team7179.robot.commands.intake.IntakeCube;
import org.usfirst.frc.team7179.robot.commands.intake.OutCube;
import org.usfirst.frc.team7179.robot.commands.intake.StopIntake;
import org.usfirst.frc.team7179.robot.commands.lift.LiftDown;
import org.usfirst.frc.team7179.robot.commands.lift.LiftUp;
import org.usfirst.frc.team7179.robot.commands.lift.StopLift;

public class OI {
	private XboxController xboxController = new XboxController(RobotMap.XBOX_CONTROLLER);
	
	private Hand rightHand = Hand.kRight; private Hand leftHand  = Hand.kLeft;
	private double leftDriverSpeed, rightDriverSpeed;
	 
	public OI() {
	
	JoystickButton upRightPad = new JoystickButton(xboxController,2);
	JoystickButton downRightPad = new JoystickButton(xboxController,4);
	
	JoystickButton leftBump = new JoystickButton(xboxController, 5);
	JoystickButton rightBump = new JoystickButton(xboxController, 6);
	
	
	rightBump.whenPressed(new IntakeCube());
	leftBump.whenPressed(new OutCube());
	
	rightBump.whenReleased(new StopIntake());
	leftBump.whenReleased(new StopIntake());
	
	upRightPad.whenPressed(new LiftUp());
	downRightPad.whenPressed(new LiftDown());

	upRightPad.whenReleased(new StopLift());
	downRightPad.whenReleased(new StopLift());
	
	
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
