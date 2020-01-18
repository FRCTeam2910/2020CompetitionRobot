package org.frcteam2910.c2020.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj2.command.Subsystem;
import org.frcteam2910.c2020.Constants;
import org.frcteam2910.common.robot.UpdateManager;

import static org.frcteam2910.common.robot.Constants.CAN_TIMEOUT_MS;

public class ShooterSubsystem implements Subsystem, UpdateManager.Updatable {
    private static final double HOOD_GEAR_REDUCTION = 14.0/60.0;

    private static final double HOOD_COEFFICIENT = ((2 * Math.PI) * HOOD_GEAR_REDUCTION / (2048));
    private static final double FLYWHEEL_SENSOR_COEFFICIENT = (1.0/1024.0) * (100.0/1000.0) * (1.0/60.0);

    private static final double FLYWHEEL_P = 0.0;
    private static final double FLYWHEEL_I = 0.0;
    private static final double FLYWHEEL_D = 0.0;
    private static final double FLYWHEEL_F = 0.0;

    private static final double HOOD_P = 0.0;
    private static final double HOOD_I = 0.0;
    private static final double HOOD_D = 0.0;

    private final TalonFX flywheelMotor1 = new TalonFX(Constants.SHOOTER_DRIVE_MOTOR_PORT_1);
    private final TalonFX flywheelMotor2 = new TalonFX(Constants.SHOOTER_DRIVE_MOTOR_PORT_2);

    private final TalonSRX angleMotor = new TalonSRX(Constants.SHOOTER_ANGLE_MOTOR_PORT);

    public ShooterSubsystem() {
        TalonFXConfiguration flyWheelConfiguration = new TalonFXConfiguration();
        flyWheelConfiguration.slot0.kP = FLYWHEEL_P;
        flyWheelConfiguration.slot0.kI = FLYWHEEL_I;
        flyWheelConfiguration.slot0.kD = FLYWHEEL_D;
        flyWheelConfiguration.slot0.kF = FLYWHEEL_F;
        flyWheelConfiguration.primaryPID.selectedFeedbackSensor = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice();
        flyWheelConfiguration.primaryPID.selectedFeedbackCoefficient = FLYWHEEL_SENSOR_COEFFICIENT;

        flywheelMotor1.configAllSettings(flyWheelConfiguration);
        flywheelMotor2.configAllSettings(flyWheelConfiguration);

        TalonSRXConfiguration hoodConfiguration = new TalonSRXConfiguration();
        hoodConfiguration.slot0.kP = HOOD_P;
        hoodConfiguration.slot0.kI = HOOD_I;
        hoodConfiguration.slot0.kD = HOOD_D;
        hoodConfiguration.feedbackNotContinuous = false;
        hoodConfiguration.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Absolute;
        hoodConfiguration.primaryPID.selectedFeedbackCoefficient = HOOD_COEFFICIENT;


        angleMotor.configAllSettings(hoodConfiguration, CAN_TIMEOUT_MS);
    }

    public double getHoodAngle() {
        return angleMotor.getSelectedSensorPosition();
    }

    public void setHoodTargetAngle(double angle) {
        angleMotor.set(ControlMode.Position, angle);
    }

    public void shootFlywheel(double speed) {
        flywheelMotor1.set(ControlMode.Velocity, speed);
        flywheelMotor2.set(ControlMode.Velocity, speed);
    }

    @Override
    public void update(double time, double dt) {

    }
}
