package heroes;

import land.LandType;

import static common.Constans.FIFTY;
import static common.Constans.FIREBLAST_DMG;
import static common.Constans.FIVE;
import static common.Constans.HUNDERED;
import static common.Constans.IGNITE_DMG;
import static common.Constans.PYROMANCER_START_HP;
import static common.Constans.PYRO_BURN;
import static common.Constans.TEN;
import static common.Constans.THIRTY;
import static common.Constans.TWENTY;
import static common.Constans.Z;
import static common.Constans.ZERO;

public final class Pyromancer extends Heroes {
    //Calculate max hp a Pyromancer can have
    private final float maxHp = PYROMANCER_START_HP + this.getLvl() * FIFTY;
    protected final char race = 'P';

    public char getRace() {
        return race;
    }

    @Override
    public String toString() {
        return race + " " + getLvl() + " " + getxP() + " " + gethP() + " " + " "
                + getX() + " " + getY() + " ";
    }
    //Methods above return skill/race modifiers
    public float getFireBlastModifier() {
        return TEN * (-1);
    }

    public float getIgniteModifier() {
        return TEN * (-1);
    }

    @Override
    public float getExecuteModifier() {
        return TEN;
    }

    @Override
    public float getSlamModifier() {
        return TEN * (-1);
    }

    @Override
    public float getBackStabModifier() {
        return TWENTY + FIVE;
    }

    @Override
    public float getParalysisModifier() {
        return TWENTY;
    }

    @Override
    public float getDrainModifier() {
        return TEN * (-1);
    }

    @Override
    public float getDeflectModifier() {
        return THIRTY;
    }

    @Override
    public float getMaxHp() {
        return this.maxHp;
    }

    @Override
    public void attack(final Heroes toAttack, final LandType terrain) {
        fireBlast(toAttack, terrain);
        ignite(toAttack, terrain);
        //the attack logic
    }

    public Pyromancer(final int posX, final int posY) {
        super(posX, posY);
        this.sethP(PYROMANCER_START_HP);
    }
    //First Pyromancer skill
    public void fireBlast(final Heroes attacked, final LandType land) {
        float bonusDMG = Z;
        float dmg;
        float dmgDeflect;
        if (land.getType().equals("Volcanic")) {
            bonusDMG = TWENTY + FIVE;
        }
        //Verify if land gives us buffs
        if (bonusDMG != Z) {
            dmg = FIREBLAST_DMG + this.getLvl() * FIFTY;
            dmg += (dmg * bonusDMG) / HUNDERED;
            dmgDeflect = dmg;
            dmg += (dmg * attacked.getFireBlastModifier()) / HUNDERED;
        } else {
            //no buffs :(
            dmg = FIREBLAST_DMG + this.getLvl() * FIFTY;
            dmgDeflect = dmg;
            dmg += ((dmg * attacked.getFireBlastModifier()) / HUNDERED);
        }
        //Round final damage to be applied
        int totalDMG = Math.round(dmg);
        int totalDeflect = Math.round(dmgDeflect);
        //FIRE!!
        attacked.sethP(Integer.max(0, attacked.gethP() - totalDMG));
        attacked.setDmgTaken(attacked.getDmgTaken() + totalDeflect);
        //Check if he burned to death
        if (attacked.gethP() <= ZERO) {
            attacked.setAlive(false);
        }
    }
    //second Pyromancer skill
    public void ignite(final Heroes attacked, final LandType land) {
        float bonusDMG = Z;
        float dmg;
        float dmgPerRound;
        float dmgDeflect;
        if (land.getType().equals("Volcanic")) {
            bonusDMG = TWENTY + FIVE;
        }
        //verify if land gives us increased damage
        if (bonusDMG != Z) {
            dmg = IGNITE_DMG + this.getLvl() * TWENTY;
            dmg += (dmg * bonusDMG) / HUNDERED;
            dmgDeflect = dmg;
            dmg += (dmg * attacked.getIgniteModifier()) / HUNDERED;
            //Calculate DoT
            dmgPerRound = FIFTY + this.getLvl() * THIRTY;
            dmgPerRound += (dmgPerRound * bonusDMG) / HUNDERED;
            dmgPerRound += (dmgPerRound * attacked.getIgniteModifier()) / HUNDERED;

        } else {
            //No land buff
            dmg = IGNITE_DMG + this.getLvl() * TWENTY;
            dmgDeflect = dmg;
            dmg += (dmg * attacked.getIgniteModifier()) / HUNDERED;

            dmgPerRound = FIFTY + this.getLvl() * THIRTY;
            dmgPerRound += (dmgPerRound * attacked.getIgniteModifier()) / HUNDERED;
        }
        //Final damage calculated
        int totalDmg = Math.round(dmg);
        int totalDmgRound = Math.round(dmgPerRound);
        int totalToDeflect = Math.round(dmgDeflect);
        //Don't play with fire kids !
        attacked.sethP(Integer.max(0, attacked.gethP() - totalDmg));
        attacked.setDmgTaken(attacked.getDmgTaken() + totalToDeflect);
        //Check if he burned to death
        if (attacked.gethP() <= ZERO) {
            attacked.setAlive(false);
        } else {
            attacked.setDmgOverTime(totalDmgRound);
            attacked.setDmgRounds(PYRO_BURN);
            //Apply DoT
        }
    }

}
