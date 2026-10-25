package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes5.dex */
class FadeTabIndicatorInterpolator extends TabIndicatorInterpolator {
    private static final float FADE_THRESHOLD = 0.5f;

    public FadeTabIndicatorInterpolator() {
    }

    @Override // com.google.android.material.tabs.TabIndicatorInterpolator
    public void updateIndicatorForOffset(TabLayout r2, View r3, View r4, float r5, Drawable r6) {
        if (r5 < FADE_THRESHOLD) goto L6;
        r3 = r4;
    L6:
        RectF r22 = TabIndicatorInterpolator.calculateIndicatorWidthForTab(r2, r3);
        if (r5 >= FADE_THRESHOLD) goto L9;
        float r1 = AnimationUtils.lerp(1.0f, 0.0f, 0.0f, FADE_THRESHOLD, r5);
    L10:
        r6.setBounds((int) r22.left, r6.getBounds().top, (int) r22.right, r6.getBounds().bottom);
        r6.setAlpha((int) (r1 * 255.0f));
        return;
    L9:
        r1 = AnimationUtils.lerp(0.0f, 1.0f, FADE_THRESHOLD, 1.0f, r5);
        goto L10
    }
}
