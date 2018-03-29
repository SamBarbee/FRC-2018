package org.usfirst.frc.team7179.robot.subsystems.drive.motionmagic;

public class MotionMagicPair {

    public final MotionMagicUnit left;
    public final MotionMagicUnit right;

    public MotionMagicPair(MotionMagicUnit l, MotionMagicUnit r) {
	left = l;
	right = r;
    }

    public void resetPositions() {
	left.resetPosition();
	right.resetPosition();
    }

    public void setMotionMagicCruiseVelocity(int velocity) {
	left.setMotionMagicCruiseVelocity(velocity);
	right.setMotionMagicCruiseVelocity(velocity);
    }

    public void setMotionMagicAcceleration(int acceleration) {
	left.setMotionMagicAcceleration(acceleration);
	right.setMotionMagicAcceleration(acceleration);
    }


    public void setSetpoint(double inches) {
	left.setSetpoint(inches);
	right.setSetpoint(inches);
    }

    public boolean onTarget() {
	return left.onTarget() && right.onTarget();
    }
}