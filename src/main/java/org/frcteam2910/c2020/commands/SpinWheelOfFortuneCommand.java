package org.frcteam2910.c2020.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import org.frcteam2910.c2020.subsystems.WheelOfFortuneSubsystem;
import org.frcteam2910.c2020.util.DetectedColor;

public class SpinWheelOfFortuneCommand extends CommandBase
{
    private static final double REVOLUTIONS_PER_WHEEL_SECTION = 1.0;
    private static final double ROTATION_CONTROL_NUM_SECTIONS = 27;

    public enum Mode {
        ROTATION_CONTROL, POSITION_CONTROL
    }

    private Mode mode;

    private WheelOfFortuneSubsystem spinner;
    private DetectedColor desiredColor;

    public SpinWheelOfFortuneCommand(WheelOfFortuneSubsystem wheelOfFortuneSpinner, Mode controlType) {
        mode = controlType;
        spinner = wheelOfFortuneSpinner;

        if (mode == Mode.POSITION_CONTROL) {
            desiredColor = DetectedColor.convertGameMessageToColor(DriverStation.getInstance().getGameSpecificMessage());
        }
    }

    @Override
    public void initialize() {
        if (mode == Mode.ROTATION_CONTROL) {
            spinner.getEncoder().setPosition(0.0);
            spinForRotationControl();
        }
        else {
            spinTowardsColorForFieldSensor(desiredColor);
        }
    }

    @Override
    public boolean isFinished() {
        if (mode == Mode.POSITION_CONTROL) {
            return spinner.getDetectedColor().getColorOnFieldSensor() == desiredColor;
        }
        else {
            return spinner.getEncoder().getPosition() >= ROTATION_CONTROL_NUM_SECTIONS * REVOLUTIONS_PER_WHEEL_SECTION;
        }
    }

    @Override
    public void end(boolean interrupted) {
        spinner.stopMotor();
    }

    private void spinForRotationControl() {
        spinner.spin(ROTATION_CONTROL_NUM_SECTIONS * REVOLUTIONS_PER_WHEEL_SECTION);
    }

    private void spinTowardsColorForFieldSensor(DetectedColor color) {
        DetectedColor currentRobotColor = spinner.getDetectedColor();
        DetectedColor fieldSensorColor = currentRobotColor.getColorOnFieldSensor();
        if (fieldSensorColor != null) {
            int numSections = fieldSensorColor.findNumSectionsAwayFromColor(color);
            spinner.spin(numSections * REVOLUTIONS_PER_WHEEL_SECTION);
        }
    }
} // TODO put rotation control and position control stuff in different classes, unless DriverStation has a way to get stage number
