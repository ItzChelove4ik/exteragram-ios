package com.google.android.material.carousel;

import android.content.Context;
import com.google.android.material.R;
import com.google.android.material.carousel.KeylineState;

/* loaded from: classes5.dex */
final class CarouselStrategyHelper {
    private CarouselStrategyHelper() {
    }

    public static float addEnd(float r1, float r2, int r3) {
        return r1 + (Math.max(0, r3 - 1) * r2);
    }

    public static float addStart(float r0, float r1, int r2) {
        if (r2 > 0) goto L4;
        return r0;
    L4:
        return r0 + (r1 / 2.0f);
    }

    public static KeylineState createCenterAlignedKeylineState(Context r19, float r20, int r21, Arrangement r22) {
        float r3 = Math.min(getExtraSmallSize(r19) + r20, r22.largeSize);
        float r5 = r3 / 2.0f;
        float r7 = 0.0f - r5;
        float r8 = addStart(0.0f, r22.smallSize, r22.smallCount);
        float r6 = updateCurPosition(0.0f, addEnd(r8, r22.smallSize, (int) Math.floor(r22.smallCount / 2.0f)), r22.smallSize, r22.smallCount);
        float r9 = addStart(r6, r22.mediumSize, r22.mediumCount);
        float r62 = updateCurPosition(r6, addEnd(r9, r22.mediumSize, (int) Math.floor(r22.mediumCount / 2.0f)), r22.mediumSize, r22.mediumCount);
        float r13 = addStart(r62, r22.largeSize, r22.largeCount);
        float r63 = updateCurPosition(r62, addEnd(r13, r22.largeSize, r22.largeCount), r22.largeSize, r22.largeCount);
        float r10 = addStart(r63, r22.mediumSize, r22.mediumCount);
        float r64 = addStart(updateCurPosition(r63, addEnd(r10, r22.mediumSize, (int) Math.ceil(r22.mediumCount / 2.0f)), r22.mediumSize, r22.mediumCount), r22.smallSize, r22.smallCount);
        float r11 = r21 + r5;
        float r52 = CarouselStrategy.getChildMaskPercentage(r3, r22.largeSize, r20);
        float r12 = CarouselStrategy.getChildMaskPercentage(r22.smallSize, r22.largeSize, r20);
        float r0 = CarouselStrategy.getChildMaskPercentage(r22.mediumSize, r22.largeSize, r20);
        KeylineState.Builder r1 = new KeylineState.Builder(r22.largeSize, r21).addAnchorKeyline(r7, r52, r3);
        if (r22.smallCount <= 0) goto L5;
        float r192 = 2.0f;
        float r18 = r52;
        r1.addKeylineRange(r8, r12, r22.smallSize, (int) Math.floor(r7 / 2.0f));
    L7:
        if (r22.mediumCount <= 0) goto L9;
        r1.addKeylineRange(r9, r0, r22.mediumSize, (int) Math.floor(r4 / r192));
    L9:
        r1.addKeylineRange(r13, 0.0f, r22.largeSize, r22.largeCount, true);
        if (r22.mediumCount <= 0) goto L13;
        r1.addKeylineRange(r10, r0, r22.mediumSize, (int) Math.ceil(r4 / r192));
    L13:
        if (r22.smallCount <= 0) goto L15;
        r1.addKeylineRange(r64, r12, r22.smallSize, (int) Math.ceil(r0 / r192));
    L15:
        r1.addAnchorKeyline(r11, r18, r3);
        return r1.build();
    L5:
        r192 = 2.0f;
        r18 = r52;
        goto L7
    }

    public static KeylineState createKeylineState(Context r1, float r2, int r3, Arrangement r4, int r5) {
        if (r5 != 1) goto L7;
        return createCenterAlignedKeylineState(r1, r2, r3, r4);
    L7:
        return createLeftAlignedKeylineState(r1, r2, r3, r4);
    }

    public static KeylineState createLeftAlignedKeylineState(Context r12, float r13, int r14, Arrangement r15) {
        float r122 = Math.min(getExtraSmallSize(r12) + r13, r15.largeSize);
        float r0 = r122 / 2.0f;
        float r2 = 0.0f - r0;
        float r6 = addStart(0.0f, r15.largeSize, r15.largeCount);
        float r1 = updateCurPosition(0.0f, addEnd(r6, r15.largeSize, r15.largeCount), r15.largeSize, r15.largeCount);
        float r3 = addStart(r1, r15.mediumSize, r15.mediumCount);
        float r16 = addStart(updateCurPosition(r1, r3, r15.mediumSize, r15.mediumCount), r15.smallSize, r15.smallCount);
        float r4 = r14 + r0;
        float r02 = CarouselStrategy.getChildMaskPercentage(r122, r15.largeSize, r13);
        float r11 = CarouselStrategy.getChildMaskPercentage(r15.smallSize, r15.largeSize, r13);
        float r132 = CarouselStrategy.getChildMaskPercentage(r15.mediumSize, r15.largeSize, r13);
        KeylineState.Builder r142 = new KeylineState.Builder(r15.largeSize, r14).addAnchorKeyline(r2, r02, r122).addKeylineRange(r6, 0.0f, r15.largeSize, r15.largeCount, true);
        if (r15.mediumCount <= 0) goto L5;
        r142.addKeyline(r3, r132, r15.mediumSize);
    L5:
        int r133 = r15.smallCount;
        if (r133 <= 0) goto L8;
        r142.addKeylineRange(r16, r11, r15.smallSize, r133);
    L8:
        r142.addAnchorKeyline(r4, r02, r122);
        return r142.build();
    }

    public static float getExtraSmallSize(Context r1) {
        return r1.getResources().getDimension(R.dimen.m3_carousel_gone_size);
    }

    public static float getSmallSizeMax(Context r1) {
        return r1.getResources().getDimension(R.dimen.m3_carousel_small_item_size_max);
    }

    public static float getSmallSizeMin(Context r1) {
        return r1.getResources().getDimension(R.dimen.m3_carousel_small_item_size_min);
    }

    public static int maxValue(int[] r4) {
        int r0 = r4.length;
        int r1 = Integer.MIN_VALUE;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        int r3 = r4[r2];
        if (r3 <= r1) goto L7;
        r1 = r3;
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        return r1;
    }

    public static float updateCurPosition(float r0, float r1, float r2, int r3) {
        if (r3 > 0) goto L4;
        return r0;
    L4:
        return r1 + (r2 / 2.0f);
    }
}
