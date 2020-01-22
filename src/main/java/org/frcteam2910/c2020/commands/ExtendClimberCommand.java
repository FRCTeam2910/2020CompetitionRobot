package org.frcteam2910.c2020.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.frcteam2910.c2020.subsystems.ClimberSubsystem;

public class ExtendClimberCommand extends CommandBase {

    private ClimberSubsystem climberSubsystem;

    public ExtendClimberCommand(ClimberSubsystem climber){
        climberSubsystem = climber;

        addRequirements(climber);
    }

    @Override
    public void execute(){
        climberSubsystem.extendClimber();
    }

    // Basic logic tells me SOMETHING goes here, but i can't figure out what. I'm leaving it just in case.
    @Override
    public void end(boolean interrupted){

    }

}
