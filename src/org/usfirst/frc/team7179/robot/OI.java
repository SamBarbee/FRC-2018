package org.usfirst.frc.team7179.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
	private XboxController xboxController = new XboxController(RobotMap.XBOX_CONTROLLER);
	
	private Hand rightHand = Hand.kRight; private Hand leftHand  = Hand.kLeft;
	private double leftDriverSpeed, rightDriverSpeed;
	 
	
	public double getIntakeSpeed() {
		return constrain(xboxController.getTriggerAxis(rightHand));
	}
	
	public double getLeftSpeed(){
		leftDriverSpeed = constrain(xboxController.getX(rightHand)-(xboxController.getY(rightHand)/Math.abs(((xboxController.getX(rightHand)/2)+Math.signum(xboxController.getY(rightHand))))));
		return Math.abs(leftDriverSpeed)>0.2?leftDriverSpeed:0;
	}
	public double getRightSpeed(){
		rightDriverSpeed = constrain(xboxController.getX(rightHand)+(xboxController.getY(rightHand)/Math.abs(((xboxController.getX(rightHand)/2)+Math.signum(xboxController.getY(rightHand))))));
		return Math.abs(rightDriverSpeed)>0.2?rightDriverSpeed:0;
	}
	private double constrain(double value){
		return Math.max(-1, Math.min(1, value));
	}
	
	
}
