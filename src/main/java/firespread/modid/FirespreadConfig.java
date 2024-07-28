package firespread.modid;

public class FirespreadConfig {

    public float burnChanceMultiplier;
    public float burnChanceMultiplier() { return this.burnChanceMultiplier; }
    public float fireSpreadMultiplier;
    public float fireSpreadMultiplier() { return this.fireSpreadMultiplier; }

    public int spreadDistanceHorizontal;
    public int spreadDistanceHorizontal() { return this.spreadDistanceHorizontal; }
    public int spreadDistanceVerticalUp;
    public int spreadDistanceVerticalUp() { return this.spreadDistanceVerticalUp; }
    public int spreadDistanceVerticalDown;
    public int spreadDistanceVerticalDown() { return this.spreadDistanceVerticalDown; }

    public FirespreadConfig(float burnChanceMultiplier, float fireSpreadMultiplier,
                            int spreadDistanceHorizontal, int spreadDistanceVerticalUp, int spreadDistanceVerticalDown)
    {
        this.burnChanceMultiplier = burnChanceMultiplier;
        this.fireSpreadMultiplier = fireSpreadMultiplier;
        this.spreadDistanceHorizontal = spreadDistanceHorizontal;
        this.spreadDistanceVerticalUp = spreadDistanceVerticalUp;
        this.spreadDistanceVerticalDown = spreadDistanceVerticalDown;
    }
}
