package me.vkryl.android.animator;

import android.graphics.RectF;

/* loaded from: classes.dex */
public class VariableRect implements Animatable {
    private final VariableFloat bottom;
    private final VariableFloat left;
    private final RectF rectF;
    private final VariableFloat right;
    private final VariableFloat top;

    public VariableRect() {
        this(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public VariableRect(float r2, float r3, float r4, float r5) {
        this.rectF = new RectF();
        this.left = new VariableFloat(r2);
        this.top = new VariableFloat(r3);
        this.right = new VariableFloat(r4);
        this.bottom = new VariableFloat(r5);
    }

    @Override // me.vkryl.android.animator.Animatable
    public boolean applyAnimation(float r5) {
        boolean r0 = this.left.applyAnimation(r5);
        if (this.top.applyAnimation(r5) == true) goto L7;
        if (r0 == true) goto L7;
        boolean r02 = false;
    L9:
        if (this.right.applyAnimation(r5) == true) goto L13;
        if (r02 == true) goto L13;
        boolean r03 = false;
    L15:
        if (this.bottom.applyAnimation(r5) == true) goto L19;
        if (r03 == true) goto L19;
        return false;
    L19:
        return true;
    L13:
        r03 = true;
    L7:
        r02 = true;
        goto L9
    }

    public boolean differs(float r2, float r3, float r4, float r5) {
        if (this.left.differs(r2) == false) goto L5;
        return true;
    L5:
        if (this.top.differs(r3) == false) goto L7;
        return true;
    L7:
        if (this.right.differs(r4) == false) goto L9;
        return true;
    L9:
        if (this.bottom.differs(r5) == true) goto L17;
        return false;
    L17:
        return true;
    }

    @Override // me.vkryl.android.animator.Animatable
    public void finishAnimation(boolean r2) {
        this.left.finishAnimation(r2);
        this.top.finishAnimation(r2);
        this.right.finishAnimation(r2);
        this.bottom.finishAnimation(r2);
    }

    public float getBottom() {
        return this.bottom.get();
    }

    public float getLeft() {
        return this.left.get();
    }

    public float getRight() {
        return this.right.get();
    }

    public float getTop() {
        return this.top.get();
    }

    public void set(float r2, float r3, float r4, float r5) {
        this.left.set(r2);
        this.top.set(r3);
        this.right.set(r4);
        this.bottom.set(r5);
    }

    public void setTo(float r2, float r3, float r4, float r5) {
        this.left.setTo(r2);
        this.top.setTo(r3);
        this.right.setTo(r4);
        this.bottom.setTo(r5);
    }

    public RectF toRectF() {
        this.rectF.set(getLeft(), getTop(), getRight(), getBottom());
        return this.rectF;
    }
}
