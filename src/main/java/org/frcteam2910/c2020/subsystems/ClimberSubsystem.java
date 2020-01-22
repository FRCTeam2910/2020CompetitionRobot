package org.frcteam2910.c2020.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Subsystem;
import org.frcteam2910.common.robot.UpdateManager;

public class ClimberSubsystem implements Subsystem, UpdateManager.Updatable {

    private static final Solenoid deploySolenoid = new Solenoid(0);
    private static final Solenoid extendSolenoid = new Solenoid(1);

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
