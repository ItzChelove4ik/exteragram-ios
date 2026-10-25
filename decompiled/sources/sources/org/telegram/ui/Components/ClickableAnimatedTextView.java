package org.telegram.ui.Components;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.StateSet;
import android.view.MotionEvent;

/* loaded from: classes7.dex */
public class ClickableAnimatedTextView extends AnimatedTextView {
    private Drawable backgroundDrawable;
    private final Rect bounds;
    private boolean pressed;

    public ClickableAnimatedTextView(Context r1) {
        super(r1);
        this.bounds = new Rect();
    }

    public Rect getClickBounds() {
        return this.bounds;
    }

    @Override // org.telegram.ui.Components.AnimatedTextView, android.view.View
    public void onDraw(Canvas r5) {
        if (this.backgroundDrawable == null) goto L14;
        this.bounds.set(getDrawable().getBounds());
        int r0 = (int) Math.ceil(getDrawable().getCurrentWidth());
        if (getDrawable().getGravity() != 3) goto L8;
        Rect r1 = this.bounds;
        r1.right = r1.left + r0;
    L13:
        this.bounds.left -= getPaddingLeft();
        this.bounds.top -= getPaddingTop();
        this.bounds.right += getPaddingRight();
        this.bounds.bottom += getPaddingBottom();
        this.backgroundDrawable.setBounds(this.bounds);
        this.backgroundDrawable.draw(r5);
        goto L14
    L8:
        if (getDrawable().getGravity() != 5) goto L11;
        Rect r12 = this.bounds;
        r12.left = r12.right - r0;
        goto L13
    L11:
        if (getDrawable().getGravity() != 17) goto L13;
        Rect r13 = this.bounds;
        int r2 = (r13.left + r13.right) / 2;
        int r02 = r0 / 2;
        r13.left = r2 - r02;
        r13.right = r2 + r02;
    L14:
        super.onDraw(r5);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r5) {
        boolean r0 = getClickBounds().contains((int) r5.getX(), (int) r5.getY());
        if (r5.getAction() != 0) goto L11;
        if (r0 == false) goto L11;
        this.pressed = true;
        Drawable r1 = this.backgroundDrawable;
        if (r1 == null) goto L8;
        r1.setHotspot(r5.getX(), r5.getY());
        this.backgroundDrawable.setState(new int[]{R.attr.state_pressed, R.attr.state_enabled});
    L8:
        invalidate();
        return r0;
    L11:
        if (r5.getAction() != 1) goto L21;
        if (this.pressed == false) goto L16;
        if (r0 == false) goto L16;
        callOnClick();
    L16:
        this.pressed = false;
        Drawable r4 = this.backgroundDrawable;
        if (r4 == null) goto L25;
        r4.setState(StateSet.NOTHING);
        return r0;
    L25:
        return r0;
    L21:
        if (r5.getAction() != 3) goto L25;
        this.pressed = false;
        Drawable r42 = this.backgroundDrawable;
        if (r42 == null) goto L25;
        r42.setState(StateSet.NOTHING);
        goto L25
    }

    @Override // android.view.View
    public void setBackground(Drawable r3) {
        Drawable r0 = this.backgroundDrawable;
        if (r0 == null) goto L5;
        r0.setCallback(null);
    L5:
        this.backgroundDrawable = r3;
        if (r3 == null) goto L8;
        r3.setCallback(this);
    L8:
        invalidate();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable r3) {
        Drawable r0 = this.backgroundDrawable;
        if (r0 == null) goto L5;
        r0.setCallback(null);
    L5:
        this.backgroundDrawable = r3;
        if (r3 == null) goto L8;
        r3.setCallback(this);
    L8:
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.backgroundDrawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
