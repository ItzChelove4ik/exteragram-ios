package com.google.android.material.carousel;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public final class MultiBrowseCarouselStrategy extends CarouselStrategy {
    private static final int[] MEDIUM_COUNTS = null;
    private static final int[] SMALL_COUNTS = null;
    private int keylineCount;

    static {
        SMALL_COUNTS = new int[]{1};
        MEDIUM_COUNTS = new int[]{1, 0};
    }

    public MultiBrowseCarouselStrategy() {
        this.keylineCount = 0;
    }

    public boolean ensureArrangementFitsItemCount(Arrangement r3, int r4) {
        int r2 = r3.getItemCount() - r4;
        if (r2 > 0) goto L5;
    L9:
        boolean r0 = false;
    L10:
        if (r2 <= 0) goto L18;
        int r1 = r3.smallCount;
        if (r1 <= 0) goto L14;
        r3.smallCount = r1 - 1;
    L17:
        r2 = r2 - 1;
        goto L10
    L14:
        int r12 = r3.mediumCount;
        if (r12 <= 1) goto L17;
        r3.mediumCount = r12 - 1;
        goto L17
    L18:
        return r0;
    L5:
        if (r3.smallCount <= 0) goto L7;
    L8:
        r0 = true;
        goto L10
    L7:
        if (r3.mediumCount <= 1) goto L9;
        goto L8
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    public KeylineState onFirstChildMeasuredWithMargins(Carousel r20, View r21) {
        int r1 = r20.getContainerHeight();
        if (r20.isHorizontal() == false) goto L5;
        r1 = r20.getContainerWidth();
    L5:
        RecyclerView.LayoutParams r2 = (RecyclerView.LayoutParams) r21.getLayoutParams();
        float r3 = ((ViewGroup.MarginLayoutParams) r2).topMargin + ((ViewGroup.MarginLayoutParams) r2).bottomMargin;
        float r4 = r21.getMeasuredHeight();
        if (r20.isHorizontal() == false) goto L8;
        r3 = ((ViewGroup.MarginLayoutParams) r2).leftMargin + ((ViewGroup.MarginLayoutParams) r2).rightMargin;
        r4 = r21.getMeasuredWidth();
    L8:
        float r7 = getSmallItemSizeMin() + r3;
        float r8 = Math.max(getSmallItemSizeMax() + r3, r7);
        float r5 = r1;
        float r12 = Math.min(r4 + r3, r5);
        float r6 = MathUtils.clamp((r4 / 3.0f) + r3, r7 + r3, r8 + r3);
        float r10 = (r12 + r6) / 2.0f;
        int[] r22 = SMALL_COUNTS;
        float r42 = 2.0f * r7;
        if (r5 > r42) goto L11;
        r22 = new int[]{0};
    L11:
        int[] r9 = MEDIUM_COUNTS;
        if (r20.getCarouselAlignment() != 1) goto L14;
        r22 = CarouselStrategy.doubleCounts(r22);
        r9 = CarouselStrategy.doubleCounts(r9);
    L14:
        int[] r18 = r9;
        int[] r92 = r22;
        float r15 = r3;
        int r23 = (int) Math.max(1.0d, Math.floor(((r5 - (CarouselStrategyHelper.maxValue(r18) * r10)) - (CarouselStrategyHelper.maxValue(r92) * r8)) / r12));
        int r32 = (int) Math.ceil(r5 / r12);
        int r24 = (r32 - r23) + 1;
        int[] r13 = new int[r24];
        int r14 = 0;
    L15:
        if (r14 >= r24) goto L17;
        r13[r14] = r32 - r14;
        r14 = r14 + 1;
        goto L15
    L17:
        Arrangement r25 = Arrangement.findLowestCostArrangement(r5, r6, r7, r8, r92, r10, r18, r12, r13);
        this.keylineCount = r25.getItemCount();
        boolean r0 = ensureArrangementFitsItemCount(r25, r20.getItemCount());
        int r33 = r25.mediumCount;
        if (r33 == 0) goto L20;
    L24:
        boolean r142 = r0;
    L25:
        if (r142 == false) goto L28;
        r25 = Arrangement.findLowestCostArrangement(r5, r6, r7, r8, new int[]{r25.smallCount}, r10, new int[]{r33}, r12, new int[]{r25.largeCount});
    L28:
        return CarouselStrategyHelper.createKeylineState(r21.getContext(), r15, r1, r25, r20.getCarouselAlignment());
    L20:
        if (r25.smallCount != 0) goto L24;
        if (r5 <= r42) goto L24;
        r25.smallCount = 1;
        r142 = true;
        goto L25
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    public boolean shouldRefreshKeylineState(Carousel r3, int r4) {
        if (r4 >= this.keylineCount) goto L7;
        if (r3.getItemCount() < this.keylineCount) goto L7;
        return true;
    L7:
        if (r4 >= this.keylineCount) goto L9;
        return false;
    L9:
        if (r3.getItemCount() >= this.keylineCount) goto L15;
        return true;
    L15:
        return false;
    }
}
