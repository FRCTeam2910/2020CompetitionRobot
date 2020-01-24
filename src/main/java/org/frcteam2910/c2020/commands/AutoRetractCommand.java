package org.frcteam2910.c2020.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.frcteam2910.c2020.subsystems.FeederSubsystem;
import org.frcteam2910.c2020.subsystems.IntakeSubsystem;

public class AutoRetractCommand extends CommandBase {

    IntakeSubsystem intake;
    FeederSubsystem feeder;

    public AutoRetractCommand(IntakeSubsystem intakeSubsystem, FeederSubsystem feederSubsystem){
        intake = intakeSubsystem;
        feeder = feederSubsystem;

        addRequirements(intakeSubsystem, feederSubsystem);
    }

    @Override
    public void execute() {
        if(feeder.isFull()){
            intake.setExtended(false);
        }
    }
}
