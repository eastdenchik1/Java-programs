package ru.sviridoff.packages;

import lombok.Getter;

public class train {

    private String dstAdress;
    @Getter
    private int numTrain;
    private String timeDst;

    public String getByTrain() {
        return "{STRUCT]: " + this.numTrain + " /// " + this.dstAdress + " /// " + this.timeDst;
    }

    public train (String dstAdress, int numTrain, String timeDst) {
        this.dstAdress = dstAdress;
        this.numTrain = numTrain;
        this.timeDst = timeDst;
    }

}
