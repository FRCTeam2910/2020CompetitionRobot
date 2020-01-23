package org.frcteam2910.c2020.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.revrobotics.ColorSensorV3;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import org.frcteam2910.c2020.Constants;
import org.frcteam2910.c2020.util.DetectedColor;
import org.frcteam2910.common.robot.UpdateManager;
import edu.wpi.first.wpilibj.util.Color;

public class WheelOfFortuneSubsystem implements Subsystem, UpdateManager.Updatable {

    private final double RED_MIN = Constants.WHEEL_OF_FORTUNE_RED_HUE - (Constants.WHEEL_OF_FORTUNE_RED_HUE - 0) / 2;
    private final double RED_MAX = Constants.WHEEL_OF_FORTUNE_RED_HUE + (Constants.WHEEL_OF_FORTUNE_YELLOW_HUE - Constants.WHEEL_OF_FORTUNE_RED_HUE) / 2;
    private final double GREEN_MIN = Constants.WHEEL_OF_FORTUNE_GREEN_HUE - (Constants.WHEEL_OF_FORTUNE_GREEN_HUE - Constants.WHEEL_OF_FORTUNE_YELLOW_HUE) / 2;
    private final double GREEN_MAX = Constants.WHEEL_OF_FORTUNE_GREEN_HUE + (Constants.WHEEL_OF_FORTUNE_BLUE_HUE - Constants.WHEEL_OF_FORTUNE_GREEN_HUE) / 2;
    private final double BLUE_MIN = Constants.WHEEL_OF_FORTUNE_BLUE_HUE - (Constants.WHEEL_OF_FORTUNE_BLUE_HUE - Constants.WHEEL_OF_FORTUNE_GREEN_HUE) / 2;
    private final double BLUE_MAX = Constants.WHEEL_OF_FORTUNE_BLUE_HUE + (210 - Constants.WHEEL_OF_FORTUNE_BLUE_HUE) / 2;

    private static final double SPINNER_POSITION_COEFFICIENT = 0.0;
    private static final double SPINNER_INTEGRAL_COEFFICIENT = 0.0;
    private static final double SPINNER_DERIVATIVE_COEFFICIENT = 0.0;
    private static final double SENSOR_COEFFICIENT = 1.0;

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 COLOR_SENSOR = new ColorSensorV3(i2cPort);

    private TalonSRX wheelSpinnerMotor = new TalonSRX(Constants.WHEEL_OF_FORTUNE_MOTOR_PORT);

    private DetectedColor detectedColor;

    public WheelOfFortuneSubsystem(){
        super();

        TalonSRXConfiguration spinnerConfig = new TalonSRXConfiguration();
        spinnerConfig.slot0.kP = SPINNER_POSITION_COEFFICIENT;
        spinnerConfig.slot0.kI = SPINNER_INTEGRAL_COEFFICIENT;
        spinnerConfig.slot0.kD = SPINNER_DERIVATIVE_COEFFICIENT;
        spinnerConfig.primaryPID.selectedFeedbackCoefficient = SENSOR_COEFFICIENT;
        spinnerConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;

        wheelSpinnerMotor.configAllSettings(spinnerConfig);
    }

    @Override
    public void periodic(){
        Color colorFromSensor = COLOR_SENSOR.getColor();
        double hueColor  = calculateHue(colorFromSensor);
        detectedColor = calculateDetectedColor(hueColor);

        SmartDashboard.putString("Color Detected", detectedColor.toString());
    }

    public void spinSpinner(double numRevolutions) {
        wheelSpinnerMotor.set(ControlMode.Position, numRevolutions);
    }


    private double calculateHue(Color colorFromSensor){

        //RGB to Hue formula
        double cMax = Math.max(Math.max(colorFromSensor.red,colorFromSensor.green),colorFromSensor.blue);
        double cMin = Math.min(Math.min(colorFromSensor.red,colorFromSensor.green),colorFromSensor.blue);
        double delta = cMax - cMin;

        if(colorFromSensor.red > colorFromSensor.green && colorFromSensor.red > colorFromSensor.blue){//Red is bigger
             return 60 * (((colorFromSensor.green - colorFromSensor.blue) / delta) % 6);
        }else if(colorFromSensor.green > colorFromSensor.red && colorFromSensor.green > colorFromSensor.blue){//Green is bigger
            return 60 * (((colorFromSensor.blue - colorFromSensor.red) / delta) + 2);
        }else{//Blue is bigger
            return 60 * (((colorFromSensor.red - colorFromSensor.green) / delta) + 4);
        }
    }

    private DetectedColor calculateDetectedColor(double colorHue){
        if(colorHue > RED_MIN && colorHue < RED_MAX){
            return DetectedColor.RED;
        }else if(colorHue > GREEN_MIN && colorHue < GREEN_MAX){
            return DetectedColor.GREEN;
        }else if(colorHue > BLUE_MIN && colorHue < BLUE_MAX){
            return DetectedColor.BLUE;
        }else{
            return DetectedColor.YELLOW;
        }
    }


    public DetectedColor getDetectedColor(){
       return detectedColor;
    }


    @Override
    public void update(double time, double dt){

    }

}
