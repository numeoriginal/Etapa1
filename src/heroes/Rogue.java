package heroes;

import land.LandType;

import static common.Constans.APPLY_STAB;
import static common.Constans.BACKSTAB_DMG;
import static common.Constans.FIVE;
import static common.Constans.FORTY;
import static common.Constans.HUNDERED;
import static common.Constans.PARALYSIS_F;
import static common.Constans.PARALYSIS_T;
import static common.Constans.ROGUE_START_HP;
import static common.Constans.STAB_CRIT;
import static common.Constans.TEN;
import static common.Constans.THREE;
import static common.Constans.TWENTY;
import static common.Constans.Z;
import static common.Constans.ZERO;

public final class Rogue extends Heroes {
    //Counter for critical chance
    private int nrStabs = THREE;
    private boolean stabAplied = false;
    private final char race = 'R';
    private float maxHp = ROGUE_START_HP + this.getLvl() * (TWENTY * 2);

    public Rogue(final int posX, final int posY) {
        super(posX, posY);
        this.sethP(ROGUE_START_HP);
    }

    public char getRace() {
        return race;
    }
    //Methods below return modifiers for skills
    @Override
    public float getFireBlastModifier() {
        return TWENTY * (-1);
    }

    @Override
    public float getIgniteModifier() {
        return TWENTY * (-1);
    }

    @Override
    public float getExecuteModifier() {
        return TEN + FIVE;
    }

    @Override
    public float getSlamModifier() {
        return TWENTY * (-1);
    }

    @Override
    public float getBackStabModifier() {
        return TWENTY;
    }

    @Override
    public float getParalysisModifier() {
        return TEN * (-1);
    }

    @Override
    public float getDrainModifier() {
        return TWENTY * (-1);
    }

    @Override
    public float getDeflectModifier() {
        return TWENTY;
    }

    @Override
    public float getMaxHp() {
        return this.maxHp;
    }

    @Override
    public void attack(final Heroes toAttack, final LandType terrain) {
        skillBackstab(toAttack, terrain);
        skillParalysis(toAttack, terrain);
        //Attack logic
    }

    public void skillBackstab(final Heroes attacked, final LandType type) {
        this.nrStabs--;
        float bonusDMG = Z;
        float dmg;
        float dmgDeflect;
        if (type.getType().equals("Woods")) {
            bonusDMG = TEN + FIVE;
        }
        //Verify if land gives buffs :)
        if (bonusDMG != Z) {
            dmg = BACKSTAB_DMG + this.getLvl() * TWENTY;
            dmg += (dmg * bonusDMG) / HUNDERED;
            dmgDeflect = dmg;
            dmg += (dmg * attacked.getBackStabModifier()) / HUNDERED;
        } else {
            //No buffs :(
            dmg = BACKSTAB_DMG + this.getLvl() * TWENTY;
            dmgDeflect = dmg;
            dmg += (dmg * attacked.getBackStabModifier()) / HUNDERED;
        }
        //Check if critical strikes are available
        if ((type.getType().equals("Woods")) && !(stabAplied) && (nrStabs <= APPLY_STAB)) {
            dmg *= STAB_CRIT;
            dmgDeflect *= STAB_CRIT;
            this.stabAplied = true;
            this.nrStabs = THREE;
        } else if (!type.getType().equals("Woods") && (nrStabs == THREE) && !(stabAplied)) {
            this.nrStabs = THREE;
            //no critical hits :(
        }
        //Round the damage to strike for
        int dmgToDeflect = Math.round(dmgDeflect);
        int dmgTaken = Math.round(dmg);
        //Hurt the enemy >_<
        attacked.sethP(Integer.max(0, attacked.gethP() - dmgTaken));
        attacked.setDmgTaken(attacked.getDmgTaken() + dmgToDeflect);
        //Check if he fainted
        if (attacked.gethP() <= ZERO) {
            attacked.setAlive(false);
        }
        if ((this.nrStabs == ZERO) && this.stabAplied) {
            this.stabAplied = false;
        }
        //Reset the critical strike chance ^
    }

    public void skillParalysis(final Heroes attacked, final LandType type) {
        float bonusDMG = Z;
        float dmg;
        float dmgDeflect;
        if (type.getType().equals("Woods")) {
            bonusDMG = FIVE + TEN;
        }
        //Verify buff land :)
        if (bonusDMG != Z) {
            dmg = FORTY + this.getLvl() * TEN;
            dmg += (dmg * bonusDMG) / HUNDERED;
            dmgDeflect = dmg;
            dmg += (dmg * attacked.getParalysisModifier()) / HUNDERED;
        } else {
            //No buffs :(
            dmg = FORTY + this.getLvl() * TEN;
            dmgDeflect = dmg;
            dmg += (dmg * attacked.getParalysisModifier()) / HUNDERED;
        }
        //Total dmg to apply
        int totalDeflect = Math.round(dmgDeflect);
        int totalDMG = Math.round(dmg);
        //STRIKE !!
        attacked.sethP(Integer.max(0, attacked.gethP() - totalDMG));
        attacked.setDmgTaken(attacked.getDmgTaken() + totalDeflect);
        //Apply the de buff
        if (type.getType().equals("Woods")) {
            attacked.setDmgOverTime(totalDMG);
            attacked.setDmgRounds(PARALYSIS_T);

        } else {
            attacked.setDmgRounds(PARALYSIS_F);
            attacked.setDmgOverTime(totalDMG);
        }
        //Check if he fainted :(
        if (attacked.gethP() <= ZERO) {
            attacked.setAlive(false);
        }
    }

    @Override
    public String toString() {
        return race + " " + getLvl() + " " + getxP() + " " + gethP() + " " + " "
                + getX() + " " + getY() + " ";
    }
}
