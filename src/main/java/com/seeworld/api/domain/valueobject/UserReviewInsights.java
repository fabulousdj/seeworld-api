package com.seeworld.api.domain.valueobject;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class UserReviewInsights {

    private static int SECOND_DECIMAL_PLACE = 2;

    private long count;
    @SerializedName("positive")
    private float positivePercentage;
    @SerializedName("negative")
    private float negativePercentage;
    @SerializedName("neutral")
    private float neutralPercentage;

    public UserReviewInsights(long count, long positiveCount, long negativeCount, long neutralCount) {
        this.count = count;
        this.positivePercentage = round(((float)positiveCount) / count, 2);
        this.negativePercentage = round(((float)negativeCount) / count, 2);
        this.neutralPercentage = round(((float)neutralCount) / count, 2);
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public long getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPositivePercentage() {
        return positivePercentage;
    }

    public void setPositivePercentage(float positivePercentage) {
        this.positivePercentage = positivePercentage;
    }

    public float getNegativePercentage() {
        return negativePercentage;
    }

    public void setNegativePercentage(float negativePercentage) {
        this.negativePercentage = negativePercentage;
    }

    public float getNeutralPercentage() {
        return neutralPercentage;
    }

    public void setNeutralPercentage(float neutralPercentage) {
        this.neutralPercentage = neutralPercentage;
    }
}
