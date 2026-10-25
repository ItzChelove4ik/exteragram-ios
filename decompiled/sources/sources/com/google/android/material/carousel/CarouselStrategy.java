package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;

/* loaded from: classes5.dex */
public abstract class CarouselStrategy {
    private float smallSizeMax;
    private float smallSizeMin;

    public enum StrategyType extends Enum<StrategyType> {
        private static final /* synthetic */ StrategyType[] $VALUES = null;
        public static final StrategyType CONTAINED = null;
        public static final StrategyType UNCONTAINED = null;

        private static /* synthetic */ StrategyType[] $values() {
            return new StrategyType[]{CONTAINED, UNCONTAINED};
        }

        static {
            CONTAINED = new StrategyType("CONTAINED", 0);
            UNCONTAINED = new StrategyType("UNCONTAINED", 1);
            $VALUES = $values();
        }

        StrategyType(String r1, int r2) {
        }

        public static StrategyType valueOf(String r1) {
            return (StrategyType) Enum.valueOf(StrategyType.class, r1);
        }

        public static StrategyType[] values() {
            return (StrategyType[]) $VALUES.clone();
        }
    }

    public CarouselStrategy() {
    }

    public static int[] doubleCounts(int[] r4) {
        int r0 = r4.length;
        int[] r1 = new int[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = r4[r2] * 2;
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    public static float getChildMaskPercentage(float r0, float r1, float r2) {
        return 1.0f - ((r0 - r2) / (r1 - r2));
    }

    public float getSmallItemSizeMax() {
        return this.smallSizeMax;
    }

    public float getSmallItemSizeMin() {
        return this.smallSizeMin;
    }

    public StrategyType getStrategyType() {
        return StrategyType.CONTAINED;
    }

    public void initialize(Context r4) {
        float r0 = this.smallSizeMin;
        if (r0 > 0.0f) goto L6;
        r0 = CarouselStrategyHelper.getSmallSizeMin(r4);
    L6:
        this.smallSizeMin = r0;
        float r02 = this.smallSizeMax;
        if (r02 > 0.0f) goto L10;
        r02 = CarouselStrategyHelper.getSmallSizeMax(r4);
    L10:
        this.smallSizeMax = r02;
    }

    public abstract KeylineState onFirstChildMeasuredWithMargins(Carousel r1, View r2);

    public void setSmallItemSizeMax(float r1) {
        this.smallSizeMax = r1;
    }

    public void setSmallItemSizeMin(float r1) {
        this.smallSizeMin = r1;
    }

    public boolean shouldRefreshKeylineState(Carousel r1, int r2) {
        return false;
    }
}
