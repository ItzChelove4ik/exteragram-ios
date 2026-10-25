package org.telegram.ui.Components;

import android.animation.TimeInterpolator;
import android.os.SystemClock;
import android.view.View;
import androidx.core.math.MathUtils;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes3.dex */
public class AnimatedFloat {
    private boolean firstSet;
    private Runnable invalidate;
    private View parent;
    private float startValue;
    private float targetValue;
    private boolean transition;
    private long transitionDelay;
    private long transitionDuration;
    private TimeInterpolator transitionInterpolator;
    private long transitionStart;
    private float value;

    public AnimatedFloat(float r3, View r4) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        this.transitionInterpolator = CubicBezierInterpolator.DEFAULT;
        this.parent = r4;
        this.targetValue = r3;
        this.value = r3;
        this.firstSet = false;
    }

    public AnimatedFloat(float r3, View r4, long r5, long r7, TimeInterpolator r9) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        CubicBezierInterpolator r0 = CubicBezierInterpolator.DEFAULT;
        this.parent = r4;
        this.targetValue = r3;
        this.value = r3;
        this.transitionDelay = r5;
        this.transitionDuration = r7;
        this.transitionInterpolator = r9;
        this.firstSet = false;
    }

    public AnimatedFloat(float r3, Runnable r4, long r5, long r7, TimeInterpolator r9) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        CubicBezierInterpolator r0 = CubicBezierInterpolator.DEFAULT;
        this.invalidate = r4;
        this.targetValue = r3;
        this.value = r3;
        this.transitionDelay = r5;
        this.transitionDuration = r7;
        this.transitionInterpolator = r9;
        this.firstSet = false;
    }

    public AnimatedFloat(long r3, long r5, TimeInterpolator r7) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        CubicBezierInterpolator r0 = CubicBezierInterpolator.DEFAULT;
        this.parent = null;
        this.transitionDelay = r3;
        this.transitionDuration = r5;
        this.transitionInterpolator = r7;
        this.firstSet = true;
    }

    public AnimatedFloat(long r3, TimeInterpolator r5) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        CubicBezierInterpolator r0 = CubicBezierInterpolator.DEFAULT;
        this.parent = null;
        this.transitionDuration = r3;
        this.transitionInterpolator = r5;
        this.firstSet = true;
    }

    public AnimatedFloat(View r3) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        this.transitionInterpolator = CubicBezierInterpolator.DEFAULT;
        this.parent = r3;
        this.firstSet = true;
    }

    public AnimatedFloat(View r3, long r4, long r6, TimeInterpolator r8) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        CubicBezierInterpolator r0 = CubicBezierInterpolator.DEFAULT;
        this.parent = r3;
        this.transitionDelay = r4;
        this.transitionDuration = r6;
        this.transitionInterpolator = r8;
        this.firstSet = true;
    }

    public AnimatedFloat(View r3, long r4, TimeInterpolator r6) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        CubicBezierInterpolator r0 = CubicBezierInterpolator.DEFAULT;
        this.parent = r3;
        this.transitionDuration = r4;
        this.transitionInterpolator = r6;
        this.firstSet = true;
    }

    public AnimatedFloat(Runnable r3) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        this.transitionInterpolator = CubicBezierInterpolator.DEFAULT;
        this.invalidate = r3;
        this.firstSet = true;
    }

    public AnimatedFloat(Runnable r3, long r4, long r6, TimeInterpolator r8) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        CubicBezierInterpolator r0 = CubicBezierInterpolator.DEFAULT;
        this.invalidate = r3;
        this.transitionDelay = r4;
        this.transitionDuration = r6;
        this.transitionInterpolator = r8;
        this.firstSet = true;
    }

    public AnimatedFloat(Runnable r3, long r4, TimeInterpolator r6) {
        this.transitionDelay = 0;
        this.transitionDuration = 200;
        CubicBezierInterpolator r0 = CubicBezierInterpolator.DEFAULT;
        this.invalidate = r3;
        this.transitionDuration = r4;
        this.transitionInterpolator = r6;
        this.firstSet = true;
    }

    public void force(float r2) {
        set(r2, true);
    }

    public void force(boolean r2) {
        if (r2 == false) goto L4;
        float r22 = 1.0f;
    L5:
        set(r22, true);
        return;
    L4:
        r22 = 0.0f;
        goto L5
    }

    @Deprecated
    public float get() {
        return this.value;
    }

    public long getDuration() {
        return this.transitionDuration;
    }

    public float getTransitionProgress() {
        if (this.transition == true) goto L6;
        return 0.0f;
    L6:
        return MathUtils.clamp(((SystemClock.elapsedRealtime() - this.transitionStart) - this.transitionDelay) / this.transitionDuration, 0.0f, 1.0f);
    }

    public float getTransitionProgressInterpolated() {
        TimeInterpolator r0 = this.transitionInterpolator;
        if (r0 == null) goto L7;
        return r0.getInterpolation(getTransitionProgress());
    L7:
        return getTransitionProgress();
    }

    public float getValue() {
        if (this.transition == false) goto L20;
        long r0 = SystemClock.elapsedRealtime();
        float r2 = MathUtils.clamp(((r0 - this.transitionStart) - this.transitionDelay) / this.transitionDuration, 0.0f, 1.0f);
        if ((r0 - this.transitionStart) < this.transitionDelay) goto L11;
        TimeInterpolator r02 = this.transitionInterpolator;
        float r1 = this.startValue;
        if (r02 != null) goto L9;
        this.value = AndroidUtilities.lerp(r1, this.targetValue, r2);
        goto L11
    L9:
        this.value = AndroidUtilities.lerp(r1, this.targetValue, r02.getInterpolation(r2));
    L11:
        if (r2 < 1.0f) goto L13;
        this.transition = false;
        goto L20
    L13:
        View r03 = this.parent;
        if (r03 == null) goto L16;
        r03.invalidate();
    L16:
        Runnable r04 = this.invalidate;
        if (r04 == null) goto L20;
        r04.run();
    L20:
        return this.value;
    }

    public boolean isInProgress() {
        return this.transition;
    }

    public float set(float r2) {
        return set(r2, false);
    }

    public float set(float r5, boolean r6) {
        if (r6 == false) goto L4;
    L11:
        this.targetValue = r5;
        this.value = r5;
        this.transition = false;
        this.firstSet = false;
    L13:
        return getValue();
    L4:
        if (this.transitionDuration <= 0) goto L11;
        if (this.firstSet == true) goto L11;
        if (Math.abs(this.targetValue - r5) <= 1.0E-4f) goto L13;
        this.transition = true;
        this.targetValue = r5;
        this.startValue = this.value;
        this.transitionStart = SystemClock.elapsedRealtime();
        goto L13
    }

    public float set(boolean r2) {
        if (r2 == false) goto L4;
        float r22 = 1.0f;
    L6:
        return set(r22, false);
    L4:
        r22 = 0.0f;
        goto L6
    }

    public float set(boolean r1, boolean r2) {
        if (r1 == false) goto L4;
        float r12 = 1.0f;
    L6:
        return set(r12, r2);
    L4:
        r12 = 0.0f;
        goto L6
    }

    public void setDelay(long r1) {
        this.transitionDelay = r1;
    }

    public void setDuration(long r1) {
        this.transitionDuration = r1;
    }

    public void setParent(View r1) {
        this.parent = r1;
    }
}
