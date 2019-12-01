package land;

public final class Desert extends LandType {
    //Nothing really to explain here
    private static Desert instance = null;
    protected String type = "Desert";

    private Desert() {
    }

    public static Desert getInstance() {
        if (instance == null) {
            instance = new Desert();
        }
        return instance;
    }

    @Override
    public String getType() {
        return this.type;
    }

}
