package me.vkryl.android.animator;

import android.view.View;
import android.view.animation.Interpolator;
import me.vkryl.android.animator.FactorAnimator;

/* loaded from: classes.dex */
public class BoolAnimator implements FactorAnimator.Target {
    private FactorAnimator animator;
    private long duration;
    private float floatValue;
    private final int id;
    private Interpolator interpolator;
    private long startDelay;
    private final FactorAnimator.Target target;
    private boolean value;

    public static /* synthetic */ void $r8$lambda$LQEk70qBezZwpTX8syQwiPgCE_I(View r0, int r1, float r2, float r3, FactorAnimator r4) {
        r0.invalidate();
    }

    public static /* synthetic */ void $r8$lambda$uxRPWBc1eZGBZ6ZtzGA_c71Jbzo(View r0, int r1, float r2, float r3, FactorAnimator r4) {
        r0.invalidate();
    }

    public BoolAnimator(int r8, FactorAnimator.Target r9, Interpolator r10, long r11) {
        this(r8, r9, r10, r11, false);
    }

    public BoolAnimator(int r1, FactorAnimator.Target r2, Interpolator r3, long r4, boolean r6) {
        this.id = r1;
        this.target = r2;
        this.interpolator = r3;
        this.duration = r4;
        this.value = r6;
        if (r6 == false) goto L5;
        float r12 = 1.0f;
    L6:
        this.floatValue = r12;
        return;
    L5:
        r12 = 0.0f;
        goto L6
    }

    public BoolAnimator(View r8, Interpolator r9, long r10) {
        this(0, new BoolAnimator$$ExternalSyntheticLambda0(r8), r9, r10, false);
    }

    public BoolAnimator(View r8, Interpolator r9, long r10, boolean r12) {
        this(0, new BoolAnimator$$ExternalSyntheticLambda1(r8), r9, r10, r12);
    }

    private void setFloatValue(float r4) {
        if (this.floatValue == r4) goto L6;
        this.floatValue = r4;
        this.target.onFactorChanged(this.id, r4, -1.0f, null);
        return;
    }

    public void changeValueSilently(float r1) {
        this.floatValue = r1;
    }

    public void changeValueSilently(boolean r1) {
        this.value = r1;
    }

    public float getFloatValue() {
        return this.floatValue;
    }

    public boolean getValue() {
        return this.value;
    }

    public boolean isAnimating() {
        FactorAnimator r0 = this.animator;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isAnimating() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChangeFinished(int r1, float r2, FactorAnimator r3) {
        this.target.onFactorChangeFinished(this.id, r2, null);
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        setFloatValue(r2);
    }

    public void setValue(boolean r2, boolean r3) {
        setValue(r2, r3, null);
    }

    public void setValue(boolean r9, boolean r10, View r11) {
        if (this.value != r9) goto L5;
        if (r10 == false) goto L5;
        return;
    L5:
        this.value = r9;
        if (r9 == false) goto L8;
        float r92 = 1.0f;
    L9:
        FactorAnimator r0 = this.animator;
        if (r10 == false) goto L19;
        if (r0 != null) goto L15;
        BoolAnimator r3 = this;
        FactorAnimator r1 = new FactorAnimator(0, r3, this.interpolator, this.duration, this.floatValue);
        r3.animator = r1;
        long r4 = r3.startDelay;
        if (r4 == 0) goto L16;
        r1.setStartDelay(r4);
    L16:
        r3.animator.animateTo(r92, r11);
        return;
    L15:
        r3 = this;
        goto L16
    L19:
        if (r0 == null) goto L22;
        r0.forceFactor(r92);
    L22:
        if (this.floatValue == r92) goto L26;
        setFloatValue(r92);
        this.target.onFactorChangeFinished(this.id, r92, null);
        return;
    L26:
        return;
    L8:
        r92 = 0.0f;
        goto L9
    }
}
