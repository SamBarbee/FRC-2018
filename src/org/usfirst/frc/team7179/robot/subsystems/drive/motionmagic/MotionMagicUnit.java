package org.usfirst.frc.team7179.robot.subsystems.drive.motionmagic;

import org.usfirst.frc.team7179.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class MotionMagicUnit {

    private final String name;	
    private TalonSRX talon;
    private final double inchesPerRotation;
    private final double allowedError;

    /**
     * Constructs a MagicMotionUnit wrapper object
     * 
     * @param t
     *            the TalonSRX object to be used. It should already be
     *            configured
     */
    public MotionMagicUnit(String name, TalonSRX talon, double inchesPerRotation, double allowedError) {
	this.name = name;
	this.talon = talon;
	this.inchesPerRotation = inchesPerRotation;
	this.allowedError = allowedError;
    }

    private boolean isInMotionMagicMode() {
	return talon.getControlMode().equals(ControlMode.MotionMagic);
    }

    public void resetPosition() {
	talon.setSelectedSensorPosition(0, 0, RobotMap.talonTimeoutMs);
    }

    public void setMotionMagicCruiseVelocity(int velocity) {
	talon.configMotionCruiseVelocity(velocity,0);
    }

    public void setMotionMagicAcceleration(int acceleration) {
	talon.configMotionAcceleration(acceleration, 0);
    }

    /**
     * Sets the setpoint for the magic motion to drive to<br>
     * Only works if currently in MotionMagic Mode
     * 
     * @param inches
     *            setpoint in inches
     */
    public void setSetpoint(double inches) {
	if (isInMotionMagicMode()) {
	    talon.set(ControlMode.MotionMagic, inches / inchesPerRotation);
	}
    }

    public boolean onTarget() {
	return Math.abs(talon.getClosedLoopError(0)) < allowedError / inchesPerRotation;
    }

    public double getVelocity() {
	return talon.getSelectedSensorVelocity(0);
    }

    public double getPosition() {
	return talon.getSelectedSensorPosition(0);
    }

}