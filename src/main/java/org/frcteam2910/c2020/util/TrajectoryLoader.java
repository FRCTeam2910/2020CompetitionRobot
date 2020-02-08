package org.frcteam2910.c2020.util;

import org.frcteam2910.common.control.Path;
import org.frcteam2910.common.control.Trajectory;
import org.frcteam2910.common.control.TrajectoryConstraint;
import org.frcteam2910.common.io.PathReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TrajectoryLoader {

    private static final double SAMPLE_DISTANCE = 0.01;

    private static final String EIGHT_BALL_AUTO_PART_ONE_NAME = "autos/8ball1.path";
    private static final String EIGHT_BALL_AUTO_PART_TWO_NAME = "autos/8ball2.path";
    private static final String TEN_BALL_AUTO_PART_ONE_NAME = "autos/10ball1.path";
    private static final String TEN_BALL_AUTO_PART_TWO_NAME = "autos/10ball2.path";


    private Trajectory eightBallAutoPartOne;
    private Trajectory eightBallAutoPartTwo;
    private Trajectory tenBallAutoPartOne;
    private Trajectory tenBallAutoPartTwo;




    public TrajectoryLoader(TrajectoryConstraint[] trajectoryConstraints) throws IOException {
        eightBallAutoPartOne = new Trajectory(getPath(EIGHT_BALL_AUTO_PART_ONE_NAME), trajectoryConstraints, SAMPLE_DISTANCE);
        eightBallAutoPartTwo = new Trajectory(getPath(EIGHT_BALL_AUTO_PART_TWO_NAME), trajectoryConstraints, SAMPLE_DISTANCE);
        tenBallAutoPartOne  = new Trajectory(getPath(TEN_BALL_AUTO_PART_ONE_NAME), trajectoryConstraints, SAMPLE_DISTANCE);
        tenBallAutoPartTwo = new Trajectory(getPath(TEN_BALL_AUTO_PART_TWO_NAME), trajectoryConstraints, SAMPLE_DISTANCE);
    }

    private Path getPath(String name) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(name);
        if(in == null){
            throw new FileNotFoundException("Path file not found: " + name);
        }

        try (PathReader reader = new PathReader(new InputStreamReader(in))) {
            return reader.read();
        }
    }

    public Trajectory getEightBallAutoPartOne(){
        return eightBallAutoPartOne;
    }

    public Trajectory getEightBallAutoPartTwo(){
        return eightBallAutoPartTwo;
    }

    public Trajectory getTenBallAutoPartOne(){
        return tenBallAutoPartOne;
    }

    private Trajectory getTenBallAutoPartTwo(){
        return tenBallAutoPartTwo;
    }









}
