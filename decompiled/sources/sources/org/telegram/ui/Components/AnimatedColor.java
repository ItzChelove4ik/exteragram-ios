package org.telegram.ui.Components;

import android.animation.TimeInterpolator;
import android.os.SystemClock;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;

/* loaded from: classes3.dex */
public class AnimatedColor {
    private boolean firstSet;
    private Runnable invalidate;
    private View parent;
    private int startValue;
    private int targetValue;
    private boolean transition;
    private long transitionDelay;
    private long transitionDuration;
    private TimeInterpolator transitionInterpolator;
    private long transitionStart;
    private int value;

    public AnimatedColor(View r3) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        this.transitionInterpolator = CubicBezierInterpolator.DEFAULT;
        this.parent = r3;
        this.firstSet = true;
    }

    public AnimatedColor(View r3, long r4, long r6, TimeInterpolator r8) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        CubicBezierInterpolator r0 = CubicBezierInterpolator.DEFAULT;
        this.parent = r3;
        this.transitionDelay = r4;
        this.transitionDuration = r6;
        this.transitionInterpolator = r8;
        this.firstSet = true;
    }

    public AnimatedColor(View r3, long r4, TimeInterpolator r6) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        CubicBezierInterpolator r0 = CubicBezierInterpolator.DEFAULT;
        this.parent = r3;
        this.transitionDuration = r4;
        this.transitionInterpolator = r6;
        this.firstSet = true;
    }

    public AnimatedColor(Runnable r3, long r4, TimeInterpolator r6) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        CubicBezierInterpolator r0 = CubicBezierInterpolator.DEFAULT;
        this.invalidate = r3;
        this.transitionDuration = r4;
        this.transitionInterpolator = r6;
        this.firstSet = true;
    }

    public int get() {
        return this.value;
    }

    public int set(int r2) {
        return set(r2, false);
    }

    public int set(int r8, boolean r9) {
        long r0 = SystemClock.elapsedRealtime();
        if (r9 == false) goto L5;
    L12:
        this.targetValue = r8;
        this.value = r8;
        this.transition = false;
        this.firstSet = false;
    L14:
        if (this.transition == false) goto L31;
        float r82 = MathUtils.clamp(((r0 - this.transitionStart) - this.transitionDelay) / this.transitionDuration, 0.0f, 1.0f);
        if ((r0 - this.transitionStart) < this.transitionDelay) goto L22;
        TimeInterpolator r92 = this.transitionInterpolator;
        int r02 = this.startValue;
        if (r92 != null) goto L20;
        this.value = ColorUtils.blendARGB(r02, this.targetValue, r82);
        goto L22
    L20:
        this.value = ColorUtils.blendARGB(r02, this.targetValue, r92.getInterpolation(r82));
    L22:
        if (r82 < 1.0f) goto L24;
        this.transition = false;
        goto L31
    L24:
        View r83 = this.parent;
        if (r83 == null) goto L27;
        r83.invalidate();
    L27:
        Runnable r84 = this.invalidate;
        if (r84 == null) goto L31;
        r84.run();
    L31:
        return this.value;
    L5:
        if (this.transitionDuration <= 0) goto L12;
        if (this.firstSet == true) goto L12;
        if (this.targetValue == r8) goto L14;
        this.transition = true;
        this.targetValue = r8;
        this.startValue = this.value;
        this.transitionStart = r0;
        goto L14
    }
}
