package org.frcteam2910.c2020.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.frcteam2910.c2020.subsystems.DrivetrainSubsystem;
import org.frcteam2910.common.control.PidConstants;
import org.frcteam2910.common.control.PidController;
import org.frcteam2910.common.math.Vector2;
import org.frcteam2910.common.robot.drivers.Limelight;

public class VisionRotateToTargetCommand extends CommandBase {
    private static final PidConstants PID_CONSTANTS = new PidConstants(0.0, 0.0, 0.0);
    private static final String LIMELIGHT_NAME = "limelight";
    private static final Limelight LIMELIGHT = new Limelight(NetworkTableInstance.getDefault().getTable(LIMELIGHT_NAME));


    private final DrivetrainSubsystem drivetrain;
    private PidController controller = new PidController(PID_CONSTANTS);
    private double lastTime = 0.0;

    public VisionRotateToTargetCommand(DrivetrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        lastTime = Timer.getFPGATimestamp();
        LIMELIGHT.setCamMode(Limelight.CamMode.VISION);
        controller.reset();
    }

    @Override
    public void execute() {
        double time = Timer.getFPGATimestamp();
        double dt = time - lastTime;
        lastTime = time;

        if(LIMELIGHT.hasTarget()) {
            double currentAngle = drivetrain.getPose().rotation.toRadians();
            double targetAngle = drivetrain.getPoseAtTime(time - LIMELIGHT.getPipelineLatency() / 1000.0).rotation.toRadians() - LIMELIGHT.getTargetPosition().x;
            controller.setSetpoint(targetAngle);
            double rotationalVelocity = controller.calculate(currentAngle, dt);
            drivetrain.drive(Vector2.ZERO, rotationalVelocity, false);
        }
    }
}