package org.telegram.ui.Components;

import android.animation.TimeInterpolator;
import android.graphics.Paint;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class AnimatedPaint extends Paint {
    private final AnimatedColor color;
    private final Theme.ResourcesProvider resourcesProvider;

    public AnimatedPaint(View r1, long r2, TimeInterpolator r4, int r5, Theme.ResourcesProvider r6) {
        super(r5);
        this.resourcesProvider = r6;
        this.color = new AnimatedColor(r1, r2, r4);
    }

    public AnimatedPaint(View r8, Theme.ResourcesProvider r9) {
        this(r8, 320, CubicBezierInterpolator.EASE_OUT_QUINT, 3, r9);
    }

    public AnimatedPaint blendTo(int r2, float r3) {
        setColor(ColorUtils.blendARGB(getColor(), r2, r3));
        return this;
    }

    public AnimatedPaint multAlpha(float r2) {
        setAlpha((int) (getAlpha() * r2));
        return this;
    }

    public AnimatedPaint setByKey(int r3) {
        setColor(this.color.set(Theme.getColor(r3, this.resourcesProvider)));
        return this;
    }

    public AnimatedPaint setByKey(int r3, float r4) {
        setColor(Theme.multAlpha(this.color.set(Theme.getColor(r3, this.resourcesProvider)), r4));
        return this;
    }
}
