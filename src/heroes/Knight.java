package heroes;

import land.LandType;

import static common.Constans.EXECUTE_DMG;
import static common.Constans.FIVE;
import static common.Constans.FORTY;
import static common.Constans.HUNDERED;
import static common.Constans.KNIGHT_START_HP;
import static common.Constans.KNIGHT_STUN;
import static common.Constans.SLAM_DMG;
import static common.Constans.TEN;
import static common.Constans.THIRTY;
import static common.Constans.TWENTY;
import static common.Constans.Z;
import static common.Constans.ZERO;

public final class Knight extends Heroes {
    //Calculate max health a Hero can have
    private final float maxHp = KNIGHT_START_HP + this.getLvl() * (TWENTY * 4);
    private final char race = 'K';

    public char getRace() {
        return race;
    }

    @Override
    public String toString() {
        return race + " " + getLvl() + " " + getxP() + " " + gethP() + " " + " "
                + getX() + " " + getY() + " ";
    }
    //Methods below return race Modifiers for skills
    public float getFireBlastModifier() {
        return TWENTY;
    }

    public float getIgniteModifier() {
        return TWENTY;
    }

    @Override
    public float getExecuteModifier() {
        return ZERO;
    }

    @Override
    public float getSlamModifier() {
        return TWENTY;
    }

    @Override
    public float getBackStabModifier() {
        return TEN * (-1);
    }

    @Override
    public float getParalysisModifier() {
        return TWENTY * (-1);
    }

    @Override
    public float getDrainModifier() {
        return TWENTY;
    }

    @Override
    public float getDeflectModifier() {
        return TWENTY * 2;
    }

    @Override
    public float getMaxHp() {
        return this.maxHp;
    }
    //Attack logic
    @Override
    public void attack(final Heroes toAttack, final LandType terrain) {
        skillExecute(toAttack, terrain);
        skillSlam(toAttack, terrain);

    }

    public Knight(final int posX, final int posY) {
        super(posX, posY);
        this.sethP(KNIGHT_START_HP);
    }
    //Execute skill in action
    public void skillExecute(final Heroes attacked, final LandType type) {
        float bonusDMG = Z;
        float dmg = Z;
        float dmgDeflect = Z;

        if (type.getType().equals("Land")) {
            bonusDMG = TEN + FIVE;
        }
        //Calculation of damage if we are on advantageous lang
        if (bonusDMG != Z) {
            dmg = EXECUTE_DMG + this.getLvl() * THIRTY;
            dmg += (dmg * bonusDMG) / HUNDERED;
            dmgDeflect = dmg;
            dmg += (dmg * attacked.getExecuteModifier()) / HUNDERED;
        } else {
            //else if on simple land
            dmg = EXECUTE_DMG + this.getLvl() * THIRTY;
            dmgDeflect = dmg;
            dmg += (dmg * attacked.getExecuteModifier()) / HUNDERED;
        }
        //Calculating if we use special execute function
        if (attacked.gethP() <= (((attacked.getMaxHp() * TWENTY) / HUNDERED))) {
            attacked.sethP(ZERO);
            attacked.setAlive(false);
        }
        //Round final result
        int totalDMG = Math.round(dmg);
        //Round the damage for deflection
        int totalDeflect = Math.round(dmgDeflect);
        //EXECUTE !!
        attacked.sethP(attacked.gethP() - totalDMG);
        attacked.setDmgTaken(attacked.getDmgTaken() + totalDeflect);
        //Check if he fainted
        if (attacked.gethP() <= ZERO) {
            attacked.setAlive(false);
        }
    }
    //Slam skill
    public void skillSlam(final Heroes attacked, final LandType type) {
        float bonusDMG = Z;
        float dmg;
        float deflectDMG;
        if (type.getType().equals("Land")) {
            bonusDMG = TEN + FIVE;
        }
        //Check if advantageous land
        if (bonusDMG != Z) {
            dmg = SLAM_DMG + (this.getLvl() * FORTY);
            dmg += (dmg * bonusDMG) / HUNDERED;
            deflectDMG = dmg;
            dmg += (dmg * attacked.getSlamModifier()) / HUNDERED;
        } else {
            //else if simple land
            dmg = SLAM_DMG + (this.getLvl() * FORTY);
            deflectDMG = dmg;
            dmg += (dmg * attacked.getSlamModifier()) / HUNDERED;
        }
        //Final damage to for slam
        int totalDmg = Math.round(dmg);
        int totalDeflect = Math.round(deflectDMG);
        //SLAAAAAAAAAAM !!!
        attacked.sethP(attacked.gethP() - totalDmg);
        //Making him immobile
        attacked.setMovement(false);
        //Overwriting enemy's debuff
        attacked.setDmgRounds(KNIGHT_STUN);
        attacked.setDmgOverTime(ZERO);
        attacked.setDmgTaken(attacked.getDmgTaken() + totalDeflect);
        //Check if opponent fainted
        if (attacked.gethP() <= ZERO) {
            attacked.setAlive(false);
        }
    }
}
