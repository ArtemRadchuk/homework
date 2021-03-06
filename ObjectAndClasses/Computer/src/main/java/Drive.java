public final class Drive {
    private final String dType;
    private final int dVolume;
    private final double dWeight;

    public Drive(String dType, int dVolume, int dWeight) {
        this.dType = dType;
        this.dVolume = dVolume;
        this.dWeight = dWeight;
    }

    public String getDType() {
        return dType;
    }

    public int getDVolume() {
        return dVolume;
    }

    public double getDWeight() {
        return dWeight;
    }
    public String toString(){
        return "Тип диска - " + dType + ", объем диска - " + dVolume + ", вес - " + dWeight;
    }
}
