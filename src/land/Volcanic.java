package land;

public final class Volcanic extends LandType {
    //Nothing really to explain here
    private static Volcanic instance = null;
    protected String type = "Volcanic";

    private Volcanic() {
    }

    public static Volcanic getInstance() {
        if (instance == null) {
            instance = new Volcanic();
        }
        return instance;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
