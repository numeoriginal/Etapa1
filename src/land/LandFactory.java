package land;

public final class LandFactory {
    private LandFactory() {
    }
    //Creates playground
    public static LandType createLand(final char type) {
        if (type == 'L') {
            return Land.getInstance();
        } else if (type == 'W') {
            return Woods.getInstance();
        } else if (type == 'D') {
            return Desert.getInstance();
        }
        return Volcanic.getInstance();
    }
}
