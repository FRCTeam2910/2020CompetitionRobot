package org.frcteam2910.c2020;

import org.frcteam2910.common.robot.input.Axis;
import org.frcteam2910.common.robot.input.Controller;
import org.frcteam2910.common.robot.input.XboxController;

public class RobotContainer {
    private final Controller primaryController = new XboxController(Constants.PRIMARY_CONTROLLER_PORT);

    public RobotContainer() {
        configureButtonBindings();
    }

    private void configureButtonBindings() {

    }

    public Axis getDriveForwardAxis() {
        return primaryController.getLeftYAxis();
    }

    public Axis getDriveStrafeAxis() {
        return primaryController.getLeftXAxis();
    }

    public Axis getDriveRotationAxis() {
        return primaryController.getRightXAxis();
    }
}
