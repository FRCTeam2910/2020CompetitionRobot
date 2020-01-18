package org.frcteam2910.c2020.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.frcteam2910.c2020.subsystems.FeederSubsystem;

public class SpinFeederMotorCommand extends CommandBase {

    private double motorSpeed;
    private FeederSubsystem feederSubsystem;

    public SpinFeederMotorCommand(FeederSubsystem feeder, double speed){
        motorSpeed = speed;
        feederSubsystem = feeder;

    }


    @Override
    public void execute(){
        feederSubsystem.spinMotor(motorSpeed);
    }

    @Override
    public void end(boolean interrupted){

    }

}
