package org.frcteam2910.c2020.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import org.frcteam2910.c2020.subsystems.WheelOfFortuneSubsystem;
import org.frcteam2910.c2020.util.DetectedColor;

import static org.frcteam2910.c2020.subsystems.WheelOfFortuneSubsystem.SPINNER_REVOLUTIONS_PER_WHEEL_SECTION;

public class SpinPositionControlCommand extends CommandBase {
    private WheelOfFortuneSubsystem spinner;
    private DetectedColor desiredColor;

    public SpinPositionControlCommand(WheelOfFortuneSubsystem wheelOfFortuneSpinner) {
        spinner = wheelOfFortuneSpinner;
        desiredColor = DetectedColor.convertGameMessageToColor(DriverStation.getInstance().getGameSpecificMessage());
    }

    @Override
    public void initialize() {
        DetectedColor currentRobotColor = spinner.getDetectedColor();
        DetectedColor fieldSensorColor = currentRobotColor.getColorOnFieldSensor();
        if (fieldSensorColor != null) {
            int numSections = fieldSensorColor.findNumSectionsAwayFromColor(desiredColor);
            spinner.getEncoder().setPosition(0.0);
            spinner.spin(numSections * SPINNER_REVOLUTIONS_PER_WHEEL_SECTION);
        }
    }

    @Override
    public boolean isFinished() {
        return spinner.getDetectedColor().getColorOnFieldSensor() == desiredColor;
    }

    @Override
    public void end(boolean interrupted) {
        spinner.stopMotor();
    }
}
