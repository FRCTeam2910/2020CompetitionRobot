package org.frcteam2910.c2020.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.frcteam2910.c2020.subsystems.DrivetrainSubsystem;
import org.frcteam2910.common.control.PidConstants;
import org.frcteam2910.common.control.PidController;
import org.frcteam2910.common.math.Vector2;
import org.frcteam2910.common.robot.drivers.Limelight;

public class VisionCommand extends CommandBase {
    private DrivetrainSubsystem drivetrain;
    private final PidConstants PID_CONSTANTS = new PidConstants(0.0, 0.0, 0.0);
    private final String limelightName = "limelight";
    private PidController controller = new PidController(PID_CONSTANTS);
    private double lastTime = 0.0;

    private final Limelight limelight = new Limelight(NetworkTableInstance.getDefault().getTable(limelightName));

    public VisionCommand(DrivetrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
    }

    @Override
    public void initialize() {
        lastTime = Timer.getFPGATimestamp();
        limelight.setCamMode(Limelight.CamMode.VISION);
        controller.setInputRange(Math.toRadians(-27), Math.toRadians(27));
        controller.setOutputRange(-1.0, 1.0);
    }

    @Override
    public void execute() {
        double time = Timer.getFPGATimestamp();
        double dt = time - lastTime;
        lastTime = time;

        if(limelight.hasTarget()) {
            double currentAngle = drivetrain.getPose().rotation.toRadians();
            double targetAngle = currentAngle - limelight.getTargetPosition().x;
            controller.setSetpoint(targetAngle);
            double rotationalVelocity = controller.calculate(currentAngle, dt);
            drivetrain.drive(Vector2.ZERO, rotationalVelocity, false);
        }
    }
}
