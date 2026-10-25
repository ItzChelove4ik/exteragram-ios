package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.carousel.CarouselStrategy;
import com.google.android.material.carousel.KeylineState;

/* loaded from: classes5.dex */
public final class UncontainedCarouselStrategy extends CarouselStrategy {
    private static final float MEDIUM_LARGE_ITEM_PERCENTAGE_THRESHOLD = 0.85f;

    public UncontainedCarouselStrategy() {
    }

    private float calculateMediumChildSize(float r2, float r3, float r4) {
        float r1 = Math.max(1.5f * r4, r2);
        float r22 = MEDIUM_LARGE_ITEM_PERCENTAGE_THRESHOLD * r3;
        if (r1 <= r22) goto L6;
        r1 = Math.max(r22, r4 * 1.2f);
    L6:
        return Math.min(r3, r1);
    }

    private KeylineState createCenterAlignedKeylineState(int r14, float r15, float r16, int r17, float r18, float r19, float r20) {
        float r7 = Math.min(r19, r16);
        float r8 = CarouselStrategy.getChildMaskPercentage(r7, r16, r15);
        float r13 = CarouselStrategy.getChildMaskPercentage(r18, r16, r15);
        float r9 = r18 / 2.0f;
        float r0 = (r20 + 0.0f) - r9;
        float r2 = r0 + r9;
        float r10 = r7 / 2.0f;
        float r11 = (r17 * r16) + r2;
        KeylineState.Builder r142 = new KeylineState.Builder(r16, r14).addAnchorKeyline((r0 - r9) - r10, r8, r7).addKeyline(r0, r13, r18, false).addKeylineRange((r16 / 2.0f) + r2, 0.0f, r16, r17, true);
        r142.addKeyline(r9 + r11, r13, r18, false);
        r142.addAnchorKeyline((r11 + r18) + r10, r8, r7);
        return r142.build();
    }

    private KeylineState createLeftAlignedKeylineState(Context r14, float r15, int r16, float r17, int r18, float r19, int r20, float r21) {
        float r7 = Math.min(r21, r17);
        float r0 = Math.max(r7, 0.5f * r19);
        float r1 = CarouselStrategy.getChildMaskPercentage(r0, r17, r15);
        float r8 = CarouselStrategy.getChildMaskPercentage(r7, r17, r15);
        float r13 = CarouselStrategy.getChildMaskPercentage(r19, r17, r15);
        float r11 = (r18 * r17) + 0.0f;
        KeylineState.Builder r02 = new KeylineState.Builder(r17, r16).addAnchorKeyline(0.0f - (r0 / 2.0f), r1, r0).addKeylineRange(r17 / 2.0f, 0.0f, r17, r18, true);
        if (r20 <= 0) goto L5;
        float r12 = (r19 / 2.0f) + r11;
        r11 = r11 + r19;
        r02.addKeyline(r12, r13, r19, false);
    L5:
        r02.addAnchorKeyline(r11 + (CarouselStrategyHelper.getExtraSmallSize(r14) / 2.0f), r8, r7);
        return r02.build();
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    public CarouselStrategy.StrategyType getStrategyType() {
        return CarouselStrategy.StrategyType.UNCONTAINED;
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    public KeylineState onFirstChildMeasuredWithMargins(Carousel r12, View r13) {
        if (r12.isHorizontal() == false) goto L6;
        int r0 = r12.getContainerWidth();
    L5:
        int r2 = r0;
        RecyclerView.LayoutParams r02 = (RecyclerView.LayoutParams) r13.getLayoutParams();
        float r1 = ((ViewGroup.MarginLayoutParams) r02).topMargin + ((ViewGroup.MarginLayoutParams) r02).bottomMargin;
        float r3 = r13.getMeasuredHeight();
        if (r12.isHorizontal() == false) goto L10;
        r1 = ((ViewGroup.MarginLayoutParams) r02).leftMargin + ((ViewGroup.MarginLayoutParams) r02).rightMargin;
        r3 = r13.getMeasuredWidth();
    L10:
        float r03 = r3;
        float r32 = r1;
        float r4 = r03 + r32;
        float r04 = CarouselStrategyHelper.getExtraSmallSize(r13.getContext()) + r32;
        float r7 = CarouselStrategyHelper.getExtraSmallSize(r13.getContext()) + r32;
        int r6 = 1;
        int r5 = Math.max(1, (int) Math.floor(r1 / r4));
        float r14 = r2 - (r5 * r4);
        if (r12.getCarouselAlignment() != 1) goto L15;
        float r8 = r14 / 2.0f;
        return createCenterAlignedKeylineState(r2, r32, r4, r5, Math.max(Math.min(3.0f * r8, r4), getSmallItemSizeMin() + r32), r7, r8);
    L15:
        if (r14 <= 0.0f) goto L17;
    L16:
        int r82 = r6;
        return createLeftAlignedKeylineState(r13.getContext(), r32, r2, r4, r5, calculateMediumChildSize(r04, r4, r14), r82, r7);
    L17:
        r6 = 0;
        goto L16
    L6:
        r0 = r12.getContainerHeight();
        goto L5
    }
}
