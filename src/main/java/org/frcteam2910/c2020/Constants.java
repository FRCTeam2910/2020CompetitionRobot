package org.frcteam2910.c2020;

public class Constants {
    public static final int DRIVETRAIN_FRONT_LEFT_DRIVE_MOTOR = 10;
    public static final int DRIVETRAIN_FRONT_RIGHT_DRIVE_MOTOR = 6;
    public static final int DRIVETRAIN_BACK_LEFT_DRIVE_MOTOR = 9;
    public static final int DRIVETRAIN_BACK_RIGHT_DRIVE_MOTOR = 7;
    public static final int DRIVETRAIN_FRONT_RIGHT_ANGLE_MOTOR = 14;

    public static final int DRIVETRAIN_FRONT_LEFT_ANGLE_MOTOR = 11;
    public static final int DRIVETRAIN_BACK_LEFT_ANGLE_MOTOR = 12;
    public static final int DRIVETRAIN_BACK_RIGHT_ANGLE_MOTOR = 13;

    public static final int DRIVETRAIN_FRONT_LEFT_ENCODER_PORT = 3;
    public static final int DRIVETRAIN_FRONT_RIGHT_ENCODER_PORT = 1;
    public static final int DRIVETRAIN_BACK_LEFT_ENCODER_PORT = 2;
    public static final int DRIVETRAIN_BACK_RIGHT_ENCODER_PORT = 0;

    public static final double DRIVETRAIN_FRONT_LEFT_ENCODER_OFFSET;
    public static final double DRIVETRAIN_FRONT_RIGHT_ENCODER_OFFSET;
    public static final double DRIVETRAIN_BACK_LEFT_ENCODER_OFFSET;
    public static final double DRIVETRAIN_BACK_RIGHT_ENCODER_OFFSET;

    public static final int SHOOTER_DRIVE_MOTOR_PORT_1 = 15;
    public static final int SHOOTER_DRIVE_MOTOR_PORT_2 = 16;

    public static final int SHOOTER_ANGLE_MOTOR_PORT = 17;
    public static final double SHOOTER_HOOD_MIN_ANGLE;
    public static final double SHOOTER_HOOD_MAX_ANGLE;
    public static final double SHOOTER_HOOD_OFFSET;
    public static final double SHOOTER_HOOD_GEAR_RATIO;

    public static final double WHEEL_OF_FORTUNE_RED_HUE = 45;
    public static final double WHEEL_OF_FORTUNE_GREEN_HUE = 130;
    public static final double WHEEL_OF_FORTUNE_BLUE_HUE = 170;
    public static final double WHEEL_OF_FORTUNE_YELLOW_HUE = 90;

    public static final int INTAKE_MOTOR = 19;
    public static final int INTAKE_EXTENSION_SOLENOID = 0;

    public static final int PRIMARY_CONTROLLER_PORT = 0;
    public static final int SECONDARY_CONTROLLER_PORT = 1;

    public static final int FEEDER_MOTOR_PORT = 18;

    public static final int WHEEL_OF_FORTUNE_MOTOR_PORT = 5;

    public static final int WHEEL_OF_FORTUNE_DEPLOY_SOLENOID_PORT = 2;

    public static final int CLIMBER_DEPLOY_SOLENOID_PORT = 1;
    public static final int CLIMBER_EXTEND_SOLENOID_1_PORT = 6;
    public static final int CLIMBER_EXTEND_SOLENOID_2_PORT = 7;

    public static final int FEEDER_IS_FULL_SENSOR_PORT = 0;
    public static final int FEEDER_INTAKE_BALL_SENSOR_PORT = 1;

    private static final double DRIVETRAIN_FRONT_LEFT_ENCODER_OFFSET_COMPETITION = -Math.toRadians(68.2);
    private static final double DRIVETRAIN_FRONT_RIGHT_ENCODER_OFFSET_COMPETITION = -Math.toRadians(51.8);
    private static final double DRIVETRAIN_BACK_LEFT_ENCODER_OFFSET_COMPETITION = -Math.toRadians(354.1);
    private static final double DRIVETRAIN_BACK_RIGHT_ENCODER_OFFSET_COMPETITION = -Math.toRadians(200.25);
    private static final double SHOOTER_HOOD_MIN_ANGLE_COMPETITION = Math.toRadians(26.90);
    private static final double SHOOTER_HOOD_MAX_ANGLE_COMPETITION = Math.toRadians(64.54);
    private static final double SHOOTER_HOOD_OFFSET_COMPETITION = Math.toRadians(50.9) + SHOOTER_HOOD_MAX_ANGLE_COMPETITION;
    private static final double SHOOTER_HOOD_GEAR_RATIO_COMPETITION = 22.0 / 72.0;

    private static final double DRIVETRAIN_FRONT_LEFT_ENCODER_OFFSET_PRACTICE = -Math.toRadians(75.5);
    private static final double DRIVETRAIN_FRONT_RIGHT_ENCODER_OFFSET_PRACTICE = -Math.toRadians(347.7);
    private static final double DRIVETRAIN_BACK_LEFT_ENCODER_OFFSET_PRACTICE = -Math.toRadians(298.5);
    private static final double DRIVETRAIN_BACK_RIGHT_ENCODER_OFFSET_PRACTICE = -Math.toRadians(77.0);
    private static final double SHOOTER_HOOD_MIN_ANGLE_PRACTICE = Math.toRadians(24.0);
    private static final double SHOOTER_HOOD_MAX_ANGLE_PRACTICE = Math.toRadians(58.0);
    private static final double SHOOTER_HOOD_OFFSET_PRACTICE = Math.toRadians(57.38);
    private static final double SHOOTER_HOOD_GEAR_RATIO_PRACTICE = 14.0 / 60.0;

    static {
        if (Robot.isCompetitionBot()) {
            DRIVETRAIN_FRONT_LEFT_ENCODER_OFFSET = DRIVETRAIN_FRONT_LEFT_ENCODER_OFFSET_COMPETITION;
            DRIVETRAIN_FRONT_RIGHT_ENCODER_OFFSET = DRIVETRAIN_FRONT_RIGHT_ENCODER_OFFSET_COMPETITION;
            DRIVETRAIN_BACK_LEFT_ENCODER_OFFSET = DRIVETRAIN_BACK_LEFT_ENCODER_OFFSET_COMPETITION;
            DRIVETRAIN_BACK_RIGHT_ENCODER_OFFSET = DRIVETRAIN_BACK_RIGHT_ENCODER_OFFSET_COMPETITION;

            SHOOTER_HOOD_MIN_ANGLE = SHOOTER_HOOD_MIN_ANGLE_COMPETITION;
            SHOOTER_HOOD_MAX_ANGLE = SHOOTER_HOOD_MAX_ANGLE_COMPETITION;
            SHOOTER_HOOD_OFFSET = SHOOTER_HOOD_OFFSET_COMPETITION;
            SHOOTER_HOOD_GEAR_RATIO = SHOOTER_HOOD_GEAR_RATIO_COMPETITION;
        } else {
            DRIVETRAIN_FRONT_LEFT_ENCODER_OFFSET = DRIVETRAIN_FRONT_LEFT_ENCODER_OFFSET_PRACTICE;
            DRIVETRAIN_FRONT_RIGHT_ENCODER_OFFSET = DRIVETRAIN_FRONT_RIGHT_ENCODER_OFFSET_PRACTICE;
            DRIVETRAIN_BACK_LEFT_ENCODER_OFFSET = DRIVETRAIN_BACK_LEFT_ENCODER_OFFSET_PRACTICE;
            DRIVETRAIN_BACK_RIGHT_ENCODER_OFFSET = DRIVETRAIN_BACK_RIGHT_ENCODER_OFFSET_PRACTICE;

            SHOOTER_HOOD_MIN_ANGLE = SHOOTER_HOOD_MIN_ANGLE_PRACTICE;
            SHOOTER_HOOD_MAX_ANGLE = SHOOTER_HOOD_MAX_ANGLE_PRACTICE;
            SHOOTER_HOOD_OFFSET = SHOOTER_HOOD_OFFSET_PRACTICE;
            SHOOTER_HOOD_GEAR_RATIO = SHOOTER_HOOD_GEAR_RATIO_PRACTICE;
        }
    }
}
