package land;

public final class Woods extends LandType {
    //Nothing really to explain here
    private static Woods instance = null;
    protected String type = "Woods";

    private Woods() {
    }

    public static Woods getInstance() {
        if (instance == null) {
            instance = new Woods();
        }
        return instance;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
