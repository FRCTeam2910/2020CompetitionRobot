package org.frcteam2910.c2020.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Subsystem;
import org.frcteam2910.c2020.Constants;
import org.frcteam2910.common.robot.UpdateManager;

public class ClimberSubsystem implements Subsystem, UpdateManager.Updatable {

    private static final Solenoid deploySolenoid = new Solenoid(Constants.DEPLOY_SOLENOID_PORT);
    private static final Solenoid extendSolenoid = new Solenoid(Constants.EXTEND_SOLENOID_PORT);

    public void deployClimber(){
        deploySolenoid.set(false);
    }

    public void extendClimber(){
        extendSolenoid.set(true);
    }

    public void retractClimber(){
        extendSolenoid.set(false);
    }

    @Override
    public void update(double time, double dt){

    }

}
