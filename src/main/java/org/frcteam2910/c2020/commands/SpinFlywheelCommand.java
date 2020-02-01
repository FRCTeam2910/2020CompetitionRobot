package org.frcteam2910.c2020.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.frcteam2910.c2020.subsystems.ShooterSubsystem;
import org.frcteam2910.common.math.MathUtils;

public class SpinFlywheelCommand extends CommandBase {
    private final ShooterSubsystem shooter;
    private final double targetVelocity;

    private double initTime;

    public SpinFlywheelCommand(ShooterSubsystem shooter, double targetVelocity) {
        this.shooter = shooter;
        this.targetVelocity = targetVelocity;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        initTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        if (shooter.getFlywheelVelocity() < targetVelocity * 0.5) {
            shooter.setFlywheelOutput(1.0);
        } else {
            shooter.shootFlywheel(targetVelocity);
        }

        if (MathUtils.epsilonEquals(shooter.getFlywheelVelocity(), targetVelocity, targetVelocity * 0.005) && initTime > 0.0) {
            SmartDashboard.putNumber("Shooter response time", Timer.getFPGATimestamp() - initTime);
            initTime = -1;
        } else if (shooter.getFlywheelVelocity() < targetVelocity * 0.95 && initTime < 0.0) {
            initTime = Timer.getFPGATimestamp();
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopFlywheel();
    }
}
