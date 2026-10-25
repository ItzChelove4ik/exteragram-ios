package com.google.android.material.carousel;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public class HeroCarouselStrategy extends CarouselStrategy {
    private static final int[] MEDIUM_COUNTS = null;
    private static final int[] SMALL_COUNTS = null;
    private int keylineCount;

    static {
        SMALL_COUNTS = new int[]{1};
        MEDIUM_COUNTS = new int[]{0, 1};
    }

    public HeroCarouselStrategy() {
        this.keylineCount = 0;
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    public KeylineState onFirstChildMeasuredWithMargins(Carousel r17, View r18) {
        int r0 = r17.getContainerHeight();
        if (r17.isHorizontal() == false) goto L5;
        r0 = r17.getContainerWidth();
    L5:
        RecyclerView.LayoutParams r1 = (RecyclerView.LayoutParams) r18.getLayoutParams();
        float r2 = ((ViewGroup.MarginLayoutParams) r1).topMargin + ((ViewGroup.MarginLayoutParams) r1).bottomMargin;
        float r3 = r18.getMeasuredWidth() * 2;
        if (r17.isHorizontal() == false) goto L8;
        r2 = ((ViewGroup.MarginLayoutParams) r1).leftMargin + ((ViewGroup.MarginLayoutParams) r1).rightMargin;
        r3 = r18.getMeasuredHeight() * 2;
    L8:
        float r6 = getSmallItemSizeMin() + r2;
        float r7 = Math.max(getSmallItemSizeMax() + r2, r6);
        float r4 = r0;
        float r11 = Math.min(r3 + r2, r4);
        float r5 = MathUtils.clamp((r3 / 3.0f) + r2, r6 + r2, r7 + r2);
        float r9 = (r11 + r5) / 2.0f;
        int[] r12 = SMALL_COUNTS;
        int r13 = 0;
        if (r4 >= (2.0f * r6)) goto L11;
        int[] r32 = {0};
    L12:
        int r8 = (int) Math.max(1.0d, Math.floor((r4 - (CarouselStrategyHelper.maxValue(r12) * r7)) / r11));
        int r92 = (((int) Math.ceil(r4 / r11)) - r8) + 1;
        int[] r122 = new int[r92];
        int r14 = 0;
    L13:
        if (r14 >= r92) goto L16;
        r122[r14] = r8 + r14;
        r14 = r14 + 1;
        goto L13
    L16:
        if (r17.getCarouselAlignment() != 1) goto L18;
        int r142 = 1;
    L19:
        if (r142 == 0) goto L21;
        int[] r82 = CarouselStrategy.doubleCounts(r32);
    L22:
        if (r142 == 0) goto L25;
        int[] r93 = CarouselStrategy.doubleCounts(MEDIUM_COUNTS);
    L26:
        Arrangement r15 = Arrangement.findLowestCostArrangement(r4, r5, r6, r7, r82, r9, r93, r11, r122);
        this.keylineCount = r15.getItemCount();
        if (r15.getItemCount() <= r17.getItemCount()) goto L29;
        r15 = Arrangement.findLowestCostArrangement(r4, r5, r6, r7, r32, r9, MEDIUM_COUNTS, r11, r122);
    L31:
        return CarouselStrategyHelper.createKeylineState(r18.getContext(), r2, r0, r15, r13);
    L29:
        r13 = r142;
        goto L31
    L25:
        r93 = MEDIUM_COUNTS;
        goto L26
    L21:
        r82 = r32;
        goto L22
    L18:
        r142 = 0;
        goto L19
    L11:
        r32 = r12;
        goto L12
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    public boolean shouldRefreshKeylineState(Carousel r4, int r5) {
        if (r4.getCarouselAlignment() == 1) goto L5;
        return false;
    L5:
        if (r5 >= this.keylineCount) goto L9;
        if (r4.getItemCount() < this.keylineCount) goto L9;
    L12:
        return true;
    L9:
        if (r5 >= this.keylineCount) goto L11;
        return false;
    L11:
        if (r4.getItemCount() < this.keylineCount) goto L12;
        return false;
    }
}
