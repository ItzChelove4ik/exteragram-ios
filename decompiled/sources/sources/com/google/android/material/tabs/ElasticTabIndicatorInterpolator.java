package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes5.dex */
class ElasticTabIndicatorInterpolator extends TabIndicatorInterpolator {
    public ElasticTabIndicatorInterpolator() {
    }

    private static float accInterp(float r4) {
        return (float) (1.0d - Math.cos((r4 * 3.141592653589793d) / 2.0d));
    }

    private static float decInterp(float r4) {
        return (float) Math.sin((r4 * 3.141592653589793d) / 2.0d);
    }

    @Override // com.google.android.material.tabs.TabIndicatorInterpolator
    public void updateIndicatorForOffset(TabLayout r2, View r3, View r4, float r5, Drawable r6) {
        RectF r1 = TabIndicatorInterpolator.calculateIndicatorWidthForTab(r2, r3);
        RectF r22 = TabIndicatorInterpolator.calculateIndicatorWidthForTab(r2, r4);
        if (r1.left >= r22.left) goto L5;
        float r32 = accInterp(r5);
        float r42 = decInterp(r5);
    L6:
        r6.setBounds(AnimationUtils.lerp((int) r1.left, (int) r22.left, r32), r6.getBounds().top, AnimationUtils.lerp((int) r1.right, (int) r22.right, r42), r6.getBounds().bottom);
        return;
    L5:
        r32 = decInterp(r5);
        r42 = accInterp(r5);
        goto L6
    }
}
