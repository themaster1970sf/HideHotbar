package dev.lutsu.hidehotbar.config;

public enum MinecraftVersion {
    MC_1_21_11(21.11),
    MC_1_21_7(21.7),
    MC_1_21_4(21.4),
    MC_1_21_1(21.1);

    private final double versionNum;

    MinecraftVersion(double versionNum) {
        this.versionNum = versionNum;
    }

    public double getVersionNum(){
        return versionNum;
    }
}
