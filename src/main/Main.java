package main;

import heroes.Heroes;
import land.LandType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static common.Constans.FORTY;
import static common.Constans.TWO_HUNDRED;
import static common.Constans.ZERO;
//
//Dear programmer:
//When I wrote this code, only god and
//I knew hot it worked
//Now, only god knows it!

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) throws IOException {

        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();
        //Get the map
        LandType[][] map = gameInput.getMap();
        //Get the list of players
        List<Heroes> players = gameInput.getPlayers();

        for (int i = 0; i < gameInput.getRounds(); i++) {
            //Verify and apply DoT
            for (Heroes player : players) {
                //check if DoT applies and if Hero is alive
                if ((player.getDmgRounds() != ZERO) && (player.isAlive())) {
                    player.sethP(player.gethP() - player.getDmgOverTime());
                    player.setDmgTaken(ZERO);
                    //Decrement amount of rounds for DoT
                    player.setDmgRounds(player.getDmgRounds() - 1);
                    //Check if player died :(
                    if (player.gethP() <= ZERO) {
                        player.setAlive(false);
                    }
                }
            }
            //Let the PVP START !!
            if (players.get(0).isAlive() && players.get(1).isAlive()) {
                if (!(players.get(0).getRace() == 'W')) {
                    players.get(0).attack(players.get(1), map[0][0]);
                    players.get(1).attack(players.get(0), map[0][0]);
                } else {
                    players.get(1).attack(players.get(0), map[0][0]);
                    players.get(0).attack(players.get(1), map[0][0]);
                }
                //Verify and apply experience for the duel Winner
                if (!players.get(0).isAlive() && (players.get(1).isAlive())) {
                    players.get(1).setxP(players.get(1).getxP() + Integer.max(ZERO,
                            TWO_HUNDRED - (players.get(1).getLvl()
                                    - players.get(0).getLvl()) * (int) FORTY));
                } else if (players.get(0).isAlive() && !players.get(1).isAlive()) {
                    players.get(0).setxP(players.get(0).getxP() + Integer.max(ZERO,
                            TWO_HUNDRED - (players.get(0).getLvl()
                                    - players.get(1).getLvl()) * (int) FORTY));
                }
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        String toPrint;
        //Write the result of the arena in the file
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isAlive()) {
                //Glorious end :)
                toPrint = players.get(i).toString() + '\n';
                writer.write(toPrint);
            } else {
                //Rest in peace :(
                toPrint = players.get(i).getRace() + " dead" + '\n';
                writer.write(toPrint);

            }
        }
        writer.close();

    }
}
