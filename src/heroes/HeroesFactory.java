package heroes;

public final class HeroesFactory {
    private HeroesFactory() {
    }
    //Creates Heroes
    public static Heroes createHero(final char type, final int posX, final int posY) {
        if (type == 'P') {
            return new Pyromancer(posX, posY);
        } else if (type == 'W') {
            return new Wizard(posX, posY);
        } else if (type == 'K') {
            return new Knight(posX, posY);
        }
        return new Rogue(posX, posY);
    }
}
