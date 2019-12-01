package heroes;

import land.LandType;

import static common.Constans.DRAIN;
import static common.Constans.FIVE;
import static common.Constans.HUNDERED;
import static common.Constans.MAGIC;
import static common.Constans.TEN;
import static common.Constans.THIRTY;
import static common.Constans.TWENTY;
import static common.Constans.TWO;
import static common.Constans.WIZARD_DRAIN;
import static common.Constans.WIZARD_START_HP;
import static common.Constans.Z;
import static common.Constans.ZERO;

public final class Wizard extends Heroes {
    //Calculate maximum hp he can have
    private final float maxHp = WIZARD_START_HP + this.getLvl() * THIRTY;
    private final char race = 'W';

    public Wizard(final int posX, final int posY) {
        super(posX, posY);
        this.sethP(WIZARD_START_HP);
    }

    public char getRace() {
        return race;
    }

    @Override
    public String toString() {
        return race + " " + getLvl() + " " + getxP() + " " + gethP() + " " + " "
                + getX() + " " + getY() + " ";
    }
    //Methods below
    @Override
    public float getFireBlastModifier() {
        return FIVE;
    }

    @Override
    public float getIgniteModifier() {
        return FIVE;
    }

    @Override
    public float getExecuteModifier() {
        return TWENTY * (-1);
    }

    @Override
    public float getSlamModifier() {
        return FIVE;
    }

    @Override
    public float getBackStabModifier() {
        return TWENTY + FIVE;
    }

    @Override
    public float getParalysisModifier() {
        return TWENTY + FIVE;
    }

    @Override
    public float getDrainModifier() {
        return FIVE;
    }

    @Override
    public float getDeflectModifier() {
        return Z;
    }

    @Override
    public float getMaxHp() {
        return this.maxHp;
    }

    @Override
    public void attack(final Heroes toAttack, final LandType terrain) {
        skillDrain(toAttack, terrain);
        skillDeflect(toAttack, terrain);
    //Attack logic ^^
    }
    //Drain skill
    public void skillDrain(final Heroes attacked, final LandType land) {
        float bonusDMG = Z;
        float dmg;
        float totalDMG;
        if (land.getType().equals("Desert")) {
            bonusDMG = TEN;
        }
        //Check if we are on advantageous land
        if (bonusDMG != Z) {
            dmg = (TWENTY + this.getLvl() * FIVE) / HUNDERED;
            dmg += (dmg * bonusDMG) / HUNDERED;
            dmg += (dmg * attacked.getDrainModifier()) / HUNDERED;
            totalDMG = dmg * Float.min(WIZARD_DRAIN * attacked.getMaxHp(), attacked.gethP());
        } else {
            //else if on normal
            dmg = (TWENTY + this.getLvl() * FIVE) / HUNDERED;
            dmg += (dmg * attacked.getDrainModifier()) / HUNDERED;
            totalDMG = dmg * Float.min(WIZARD_DRAIN * attacked.getMaxHp(), attacked.gethP());
        }
        //Round final damage to drain
        int dmgToDo = Math.round(totalDMG);
        //Hurting enemy
        attacked.sethP(Integer.max(attacked.gethP() - dmgToDo, ZERO));
        //Check if fainted
        if (attacked.gethP() <= ZERO) {
            attacked.setAlive(false);
        }
    }
    //Skill deflect
    public void skillDeflect(final Heroes attacked, final LandType land) {
        float bonusDMG = Z;
        float dmg;
        if (land.getType().equals("Desert")) {
            bonusDMG = TEN;
        }
        //Check if advantageous land
        if (bonusDMG != Z) {
            dmg = ((float) this.getDmgTaken() * (DRAIN + this.getLvl() * TWO)) / HUNDERED;
            dmg += (dmg * bonusDMG) / HUNDERED;
            dmg += (dmg * attacked.getDeflectModifier()) / HUNDERED;
            dmg -= MAGIC;
        } else {
            //else if normal land
            dmg = ((float) this.getDmgTaken() * (DRAIN + this.getLvl() * TWO)) / HUNDERED;
            dmg += (dmg * attacked.getDeflectModifier()) / HUNDERED;
            dmg -= MAGIC;
        }
        //Round final damage to deflect
        int totalDMG = Math.round(dmg);
        //Hurting opponent
        attacked.sethP(Integer.max(ZERO, attacked.gethP() - totalDMG));
        //Check if enemy fainted
        if (attacked.gethP() <= ZERO) {
            attacked.setAlive(false);
        }
    }
}
