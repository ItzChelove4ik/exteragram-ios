package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/* loaded from: classes7.dex */
public class ActivityWindowEmptyBackgroundDrawable extends Drawable {
    private int mAlpha;
    private ColorFilter mColorFilter;

    public ActivityWindowEmptyBackgroundDrawable() {
        this.mAlpha = 255;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.mColorFilter;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.mAlpha = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.mColorFilter = r1;
    }
}
