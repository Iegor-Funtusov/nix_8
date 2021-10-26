package ua.com.alevel.recordclass;

public class ImmutableClass {

    private final String some1;
    private final String some2;
    private final String some3;

    public ImmutableClass(String some1, String some2, String some3) {
        this.some1 = some1;
        this.some2 = some2;
        this.some3 = some3;
    }

    public String getSome1() {
        return some1;
    }

    public String getSome2() {
        return some2;
    }

    public String getSome3() {
        return some3;
    }
}
