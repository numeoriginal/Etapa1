package main;

import fileio.FileSystem;
import heroes.Heroes;
import heroes.HeroesFactory;
import land.LandFactory;
import land.LandType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static common.Constans.ZERO;

public final class GameInputLoader {
    private final String mInput;
    private final String mOutput;

    GameInputLoader(final String inputPath, final String outputPath) {
        mInput = inputPath;
        mOutput = outputPath;
    }

    public GameInput load() throws IOException {
        List<Heroes> playerOrder = new ArrayList<>();
        int rounds = ZERO;
        int noPlayers;
        int rows;
        int columns;
        String playerType;
        int posX;
        int posY;
        String move;

        FileSystem a = new FileSystem(mInput, mOutput);
        //Read dimensions of map
        rows = a.nextInt();
        columns = a.nextInt();
        LandType[][] map = new LandType[rows][columns];
        try {
            FileSystem fs = new FileSystem(mInput, mOutput);
            rows = fs.nextInt();
            columns = fs.nextInt();
            for (int i = 0; i < rows; i++) {
                move = fs.nextWord();
                for (int j = 0; j < columns; j++) {
                    //create lands
                    map[i][j] = LandFactory.createLand((move.charAt(j)));
                }
            }

            noPlayers = fs.nextInt();
            for (int i = 0; i < noPlayers; i++) {
                playerType = fs.nextWord();
                posX = fs.nextInt();
                posY = fs.nextInt();
                //Create Heroes
                Heroes player = HeroesFactory.createHero(playerType.charAt(0), posX, posY);
                playerOrder.add(player);
            }

            rounds = fs.nextInt();
            for (int i = 0; i < noPlayers; i++) {
                move = fs.nextWord();
                playerOrder.get(i).setMoves(move);
            }
            fs.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return new GameInput(columns, rows, map, rounds, playerOrder);
    }
}
