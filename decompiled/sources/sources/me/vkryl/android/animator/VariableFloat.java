package me.vkryl.android.animator;

/* loaded from: classes.dex */
public class VariableFloat {
    private float from;
    private float now;
    private float to;

    public VariableFloat(float r1) {
        set(r1);
    }

    public boolean applyAnimation(float r3) {
        float r0 = this.from;
        float r02 = r0 + ((this.to - r0) * r3);
        if (this.now == r02) goto L6;
        this.now = r02;
        return true;
    L6:
        return false;
    }

    public boolean differs(float r1) {
        if (this.to == r1) goto L6;
        return true;
    L6:
        return false;
    }

    public void finishAnimation(boolean r1) {
        if (r1 == false) goto L5;
        float r12 = this.to;
        this.now = r12;
        this.from = r12;
        return;
    L5:
        this.from = this.now;
    }

    public float get() {
        return this.now;
    }

    public void set(float r1) {
        this.from = r1;
        this.to = r1;
        this.now = r1;
    }

    public void setFrom(float r1) {
        this.from = r1;
    }

    public void setTo(float r1) {
        this.to = r1;
    }
}
