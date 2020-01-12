package org.frcteam2910.c2020;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.frcteam2910.c2020.commands.DriveCommand;
import org.frcteam2910.c2020.subsystems.DrivetrainSubsystem;
import org.frcteam2910.common.robot.UpdateManager;

public class Robot extends TimedRobot {
    private RobotContainer robotContainer;
    private UpdateManager updateManager = new UpdateManager(
            DrivetrainSubsystem.getInstance()
    );

    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();
        updateManager.startLoop(5.0e-3);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().schedule(new DriveCommand());
        CommandScheduler.getInstance().run();
    }
}
