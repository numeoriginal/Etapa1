package land;


public final class Land extends LandType {
    //Nothing really to explain here
    private static Land instance = null;
    protected String type = "Land";

    public String getType() {
        return this.type;
    }

    private Land() {
    }

    public static Land getInstance() {
        if (instance == null) {
            instance = new Land();
        }
        return instance;
    }

}
