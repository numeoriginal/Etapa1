package heroes;

import land.LandType;

import static common.Constans.START_LEVEL;
import static common.Constans.START_XP;
import static common.Constans.ZERO;

public abstract class Heroes {
    private int hP;
    private int xP = START_XP;
    private int lvl = START_LEVEL;
    private int dmgTaken = ZERO;

    private LandType land;
    private boolean alive = true;
    private boolean movement = true;
    private int x;
    private int y;
    private String moves;
    private int dmgOverTime = ZERO;
    private int dmgRounds = ZERO;

    public Heroes(final int posX, final int posY) {
        this.x = posX;
        this.y = posY;
    }

    public abstract float getFireBlastModifier();

    public abstract float getIgniteModifier();

    public abstract float getExecuteModifier();

    public abstract float getSlamModifier();

    public abstract float getBackStabModifier();

    public abstract float getParalysisModifier();

    public abstract float getDrainModifier();

    public abstract float getDeflectModifier();

    public abstract float getMaxHp();

    public abstract char getRace();

    public abstract String toString();


    public abstract void attack(Heroes toAttack, LandType terrain);

    public final void setAlive(final boolean alive) {
        this.alive = alive;
    }

    public final void setMovement(final boolean movement) {
        this.movement = movement;
    }

    public final void setMoves(final String moves) {
        this.moves = moves;
    }

    public final String getMoves() {
        return moves;
    }


    public final boolean isAlive() {
        return alive;
    }

    public final int gethP() {
        return hP;
    }

    public final void sethP(final int hP) {
        this.hP = hP;
    }

    public final int getDmgOverTime() {
        return dmgOverTime;
    }

    public final void setDmgOverTime(final int dmgOverTime) {
        this.dmgOverTime = dmgOverTime;
    }

    public final int getDmgRounds() {
        return dmgRounds;
    }

    public final void setDmgRounds(final int dmgRounds) {
        this.dmgRounds = dmgRounds;
    }

    public final int getxP() {
        return xP;
    }

    public final void setxP(final int xP) {
        this.xP = xP;
    }

    public final int getLvl() {
        return lvl;
    }

    public final void setLvl(final int lvl) {
        this.lvl = lvl;
    }

    public final int getDmgTaken() {
        return dmgTaken;
    }

    public final void setDmgTaken(final int dmgTaken) {
        this.dmgTaken = dmgTaken;
    }

    public final LandType getLand() {
        return land;
    }

    public final void setLand(final LandType land) {
        this.land = land;
    }

    public final boolean isMovement() {
        return movement;
    }

    public final int getX() {
        return x;
    }

    public final void setX(final int x) {
        this.x = x;
    }

    public final int getY() {
        return y;
    }

    public final void setY(final int y) {
        this.y = y;
    }

}
