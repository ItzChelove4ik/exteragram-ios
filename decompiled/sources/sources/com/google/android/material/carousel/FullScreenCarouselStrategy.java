package com.google.android.material.carousel;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public class FullScreenCarouselStrategy extends CarouselStrategy {
    public FullScreenCarouselStrategy() {
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    public KeylineState onFirstChildMeasuredWithMargins(Carousel r12, View r13) {
        RecyclerView.LayoutParams r11 = (RecyclerView.LayoutParams) r13.getLayoutParams();
        if (r12.isHorizontal() == false) goto L6;
        int r122 = r12.getContainerWidth();
        int r0 = ((ViewGroup.MarginLayoutParams) r11).leftMargin;
        int r112 = ((ViewGroup.MarginLayoutParams) r11).rightMargin;
    L5:
        float r113 = r0 + r112;
        float r10 = r122;
        return CarouselStrategyHelper.createLeftAlignedKeylineState(r13.getContext(), r113, r122, new Arrangement(0, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0, Math.min(r10 + r113, r10), 1, r10));
    L6:
        r122 = r12.getContainerHeight();
        r0 = ((ViewGroup.MarginLayoutParams) r11).topMargin;
        r112 = ((ViewGroup.MarginLayoutParams) r11).bottomMargin;
        goto L5
    }
}
