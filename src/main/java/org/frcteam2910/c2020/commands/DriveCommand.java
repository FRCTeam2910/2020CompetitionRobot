package org.frcteam2910.c2020.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.frcteam2910.c2020.RobotContainer;
import org.frcteam2910.c2020.subsystems.DrivetrainSubsystem;
import org.frcteam2910.common.math.Vector2;

public class DriveCommand extends CommandBase {

    public DriveCommand() {
        addRequirements(DrivetrainSubsystem.getInstance());
    }

    @Override
    public void execute() {
        var container = new RobotContainer();
        double forward = container.getDriveForwardAxis().get(true);
        double strafe = container.getDriveStrafeAxis().get(true);
        double rotation = container.getDriveRotationAxis().get(true);

        DrivetrainSubsystem.getInstance().drive(new Vector2(forward, strafe), rotation, true);
    }

    @Override
    public void end(boolean interrupted) {
        DrivetrainSubsystem.getInstance().drive(Vector2.ZERO, 0, false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
