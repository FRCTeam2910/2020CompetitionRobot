package org.frcteam2910.c2020.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Subsystem;
import org.frcteam2910.common.robot.UpdateManager;

public class ClimberSubsystem implements Subsystem, UpdateManager.Updatable {

    // bad names, but issue said "first solenoid" and "second solenoid"
    private static final Solenoid firstSolenoid = new Solenoid(0);
    private static final Solenoid secondSolenoid = new Solenoid(1);

    /*
    From the issue:

    We will have 2 buttons controlling the system.
    One will close the first solonoid with will arm the climber.
    The second button will open the second solenoid which will extend the climber

    Thus, ArmClimber and ExtendClimber Commands?
     */

    public ClimberSubsystem(){

    }

    public void armClimber(){
        firstSolenoid.set(false);
    }

    public void extendClimber(){
        secondSolenoid.set(true);
    }

    // Maybe I need to delete this?
    @Override
    public void update(double time, double dt){

    }

}
