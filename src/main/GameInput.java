package main;

import heroes.Heroes;
import land.LandType;

import java.util.List;

public class GameInput {

    private int mSizeRows;
    private int mSizeColumns;
    private int mNoPlayers;
    private LandType[][] mMap;
    private final List<Heroes> mPlayersOrder;
    private int mRounds;


    public GameInput(
            final int columns,
            final int rows,
            final LandType[][] map,
            final int rounds,
            final List<Heroes> players) {

        mSizeColumns = columns;
        mSizeRows = rows;
        mMap = map;
        mPlayersOrder = players;
        mRounds = rounds;
    }
    //Return the created map
    public final LandType[][] getMap() {
        return mMap;
    }
    //Return the created Heroes list
    public final List<Heroes> getPlayers() {
        return mPlayersOrder;
    }

    public final int getRounds() {
        return mRounds;
    }

    public final boolean isValidInput() {
        boolean membersInstantiated = mMap != null && mPlayersOrder != null;
        boolean membersNotEmpty = mSizeColumns > 0 && mSizeRows > 0
                && mPlayersOrder.size() > 0 && mRounds > 0;

        return membersInstantiated && membersNotEmpty;
    }
}
