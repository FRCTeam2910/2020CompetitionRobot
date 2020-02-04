package org.frcteam2910.c2020.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.frcteam2910.c2020.subsystems.ShooterSubsystem;
import org.frcteam2910.common.robot.input.XboxController;

public class TargetWithShooterCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final XboxController primaryController;

    //TODO: Make this use vision
    private static final double TARGET_ANGLE = 45;
    private static final double TARGET_SPEED = 1.0;

    public TargetWithShooterCommand(ShooterSubsystem shooterSubsystem, XboxController primaryController) {
        this.shooterSubsystem = shooterSubsystem;
        this.primaryController = primaryController;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
        shooterSubsystem.shootFlywheel(2500.0);
        shooterSubsystem.setHoodTargetAngle(45);
    }

    @Override
    public void execute() {
        if(shooterSubsystem.getFlywheelVelocity() == TARGET_SPEED && shooterSubsystem.getHoodAngle() == TARGET_ANGLE) {
            primaryController.getRawJoystick().setRumble(GenericHID.RumbleType.kRightRumble, 1.0);
        } else {
            primaryController.getRawJoystick().setRumble(GenericHID.RumbleType.kRightRumble, 0.0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.setFlywheelOutput(0.0);
        primaryController.getRawJoystick().setRumble(GenericHID.RumbleType.kRightRumble, 0.0);
    }
}
