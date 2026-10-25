package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* loaded from: classes3.dex */
public class ClipRoundedDrawable extends Drawable {
    private Drawable.Callback callback;
    private Drawable drawable;
    private boolean hasRadius;
    private RectF padding;
    private Path path;
    private float[] radii;
    private RectF tempBounds;

    /* renamed from: org.telegram.ui.Components.ClipRoundedDrawable$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements Drawable.Callback {
        final /* synthetic */ ClipRoundedDrawable this$0;

        public AnonymousClass1(ClipRoundedDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable r1) {
            this.this$0.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
            this.this$0.scheduleSelf(r2, r3);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable r1, Runnable r2) {
            this.this$0.unscheduleSelf(r2);
        }
    }

    public ClipRoundedDrawable(Drawable r2) {
        this.callback = new AnonymousClass1(this);
        this.tempBounds = new RectF();
        this.padding = new RectF();
        this.hasRadius = false;
        this.radii = new float[8];
        setDrawable(r2);
    }

    private void updatePath() {
        if (this.hasRadius == true) goto L5;
        return;
    L5:
        Path r0 = this.path;
        if (r0 != null) goto L8;
        this.path = new Path();
    L9:
        this.tempBounds.set(getBounds());
        RectF r02 = this.tempBounds;
        float r1 = r02.left;
        RectF r2 = this.padding;
        r02.left = r1 + r2.left;
        r02.top += r2.top;
        r02.right -= r2.right;
        r02.bottom -= r2.bottom;
        this.path.addRoundRect(r02, this.radii, Path.Direction.CW);
        return;
    L8:
        r0.rewind();
        goto L9
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r3) {
        Drawable r0 = this.drawable;
        if (r0 == null) goto L10;
        r0.setBounds(getBounds());
        if (this.hasRadius == true) goto L8;
        r3.save();
        r3.clipRect(getBounds());
        this.drawable.draw(r3);
        r3.restore();
        return;
    L8:
        r3.save();
        updatePath();
        r3.clipPath(this.path);
        this.drawable.draw(r3);
        r3.restore();
        return;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable r0 = this.drawable;
        if (r0 == null) goto L7;
        return r0.getIntrinsicHeight();
    L7:
        return super.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable r0 = this.drawable;
        if (r0 == null) goto L7;
        return r0.getIntrinsicWidth();
    L7:
        return super.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        Drawable r0 = this.drawable;
        if (r0 == null) goto L6;
        r0.setAlpha(r1);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        Drawable r0 = this.drawable;
        if (r0 == null) goto L6;
        r0.setColorFilter(r1);
        return;
    }

    public void setDrawable(Drawable r3) {
        Drawable r0 = this.drawable;
        if (r0 == null) goto L5;
        r0.setCallback(null);
    L5:
        this.drawable = r3;
        if (r3 == null) goto L9;
        r3.setBounds(getBounds());
        this.drawable.setCallback(this.callback);
        return;
    }

    public ClipRoundedDrawable setRadii(float r7, float r8, float r9, float r10) {
        float[] r0 = this.radii;
        float r2 = Math.max(0.0f, r7);
        boolean r3 = true;
        r0[1] = r2;
        r0[0] = r2;
        float[] r02 = this.radii;
        float r22 = Math.max(0.0f, r8);
        r02[3] = r22;
        r02[2] = r22;
        float[] r03 = this.radii;
        float r23 = Math.max(0.0f, r9);
        r03[5] = r23;
        r03[4] = r23;
        float[] r04 = this.radii;
        float r24 = Math.max(0.0f, r10);
        r04[7] = r24;
        r04[6] = r24;
        if (r7 <= 0.0f) goto L5;
    L12:
        this.hasRadius = r3;
        updatePath();
        return this;
    L5:
        if (r8 > 0.0f) goto L12;
        if (r9 > 0.0f) goto L12;
        if (r10 > 0.0f) goto L12;
        r3 = false;
        goto L12
    }
}
