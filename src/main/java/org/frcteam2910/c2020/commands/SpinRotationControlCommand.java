package org.frcteam2910.c2020.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.frcteam2910.c2020.subsystems.WheelOfFortuneSubsystem;

import static org.frcteam2910.c2020.subsystems.WheelOfFortuneSubsystem.SPINNER_REVOLUTIONS_PER_WHEEL_SECTION;

public class SpinRotationControlCommand extends CommandBase {
    private static final double ROTATION_CONTROL_NUM_SECTIONS = 27;

    private WheelOfFortuneSubsystem spinner;

    public SpinRotationControlCommand(WheelOfFortuneSubsystem wheelOfFortuneSpinner) {
        spinner = wheelOfFortuneSpinner;
    }

    @Override
    public void initialize() {
        spinner.getEncoder().setPosition(0.0);
        spinner.spin(ROTATION_CONTROL_NUM_SECTIONS * SPINNER_REVOLUTIONS_PER_WHEEL_SECTION);
    }

    @Override
    public boolean isFinished() {
        return spinner.getEncoder().getPosition() >= ROTATION_CONTROL_NUM_SECTIONS * SPINNER_REVOLUTIONS_PER_WHEEL_SECTION;
    }

    @Override
    public void end(boolean interrupted) {
        spinner.stopMotor();
    }
}
