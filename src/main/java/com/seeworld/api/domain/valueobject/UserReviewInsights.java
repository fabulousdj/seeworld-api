package com.seeworld.api.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class UserReviewInsights {

    private static int SECOND_DECIMAL_PLACE = 2;

    private long count;
    @JsonProperty("positive")
    private float positivePercentage;
    @JsonProperty("negative")
    private float negativePercentage;
    @JsonProperty("neutral")
    private float neutralPercentage;

    public UserReviewInsights(long count, long positiveCount, long negativeCount, long neutralCount) {
        this.count = count;
        this.positivePercentage = round(((float)positiveCount) / count, SECOND_DECIMAL_PLACE);
        this.negativePercentage = round(((float)negativeCount) / count, SECOND_DECIMAL_PLACE);
        this.neutralPercentage = round(((float)neutralCount) / count, SECOND_DECIMAL_PLACE);
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
