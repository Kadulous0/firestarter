package firespread.modid;

public class FirespreadConfig {

    public boolean fireSlows;
    public boolean fireSlows() { return this.fireSlows; }

    public int fakeAge;
    public int fakeAge() { return fakeAge; }

    public float burnChanceMultiplier;
    public float burnChanceMultiplier() { return this.burnChanceMultiplier; }
    public float spreadChanceMultiplier;
    public float spreadChanceMultiplier() { return this.spreadChanceMultiplier; }

    public int spreadDistanceHorizontal;
    public int spreadDistanceHorizontal() { return this.spreadDistanceHorizontal; }
    public int spreadDistanceUp;
    public int spreadDistanceUp() { return this.spreadDistanceUp; }
    public int spreadDistanceDown;
    public int spreadDistanceDown() { return this.spreadDistanceDown; }

    public FirespreadConfig(boolean fireSlows,
                            int fakeAge,
                            float burnChanceMultiplier, float fireSpreadMultiplier,
                            int spreadDistanceHorizontal, int spreadDistanceUp, int spreadDistanceDown)
    {
        this.fireSlows = fireSlows;

        this.fakeAge = fakeAge;

        this.burnChanceMultiplier = burnChanceMultiplier;
        this.spreadChanceMultiplier = fireSpreadMultiplier;

        this.spreadDistanceHorizontal = spreadDistanceHorizontal;
        this.spreadDistanceUp = spreadDistanceUp;
        this.spreadDistanceDown = spreadDistanceDown;
    }
}
