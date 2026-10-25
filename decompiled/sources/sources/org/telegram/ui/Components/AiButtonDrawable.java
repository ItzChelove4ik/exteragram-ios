package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;

/* loaded from: classes7.dex */
public class AiButtonDrawable extends Drawable {
    private final AnimatedFloat animation;
    private final Drawable base;
    private final Drawable star;

    public AiButtonDrawable(Context r8) {
        this.animation = new AnimatedFloat(new AiButtonDrawable$$ExternalSyntheticLambda0(this), 0, 1200, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.base = r8.getResources().getDrawable(R.drawable.input_ai).mutate();
        this.star = r8.getResources().getDrawable(R.drawable.input_ai_star).mutate();
    }

    public void animate() {
        this.animation.force(0.0f);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r19) {
        this.base.setBounds(getBounds());
        this.base.draw(r19);
        float r3 = this.animation.set(1.0f);
        float r5 = r2.left + (r2.width() * 0.352f);
        float r6 = r2.top + (r2.height() * 0.248f);
        float r7 = (r2.width() * 0.105f) * ((float) (1.0d - Math.sin(AndroidUtilities.cascade(r3, 0.0f, 2.0f, 1.5f) * 3.141592653589793d)));
        float r8 = r2.left + (r2.width() * 0.215f);
        float r11 = r2.top + (r2.height() * 0.43f);
        float r2 = (r2.width() * 0.09f) * ((float) (1.0d - Math.sin(AndroidUtilities.cascade(r3, 1.0f, 2.0f, 1.5f) * 3.141592653589793d)));
        this.star.setBounds((int) (r5 - r7), (int) (r6 - r7), (int) (r5 + r7), (int) (r6 + r7));
        this.star.draw(r19);
        this.star.setBounds((int) (r8 - r2), (int) (r11 - r2), (int) (r8 + r2), (int) (r11 + r2));
        this.star.draw(r19);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.base.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.base.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.base.setAlpha(r2);
        this.star.setAlpha(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.base.setColorFilter(r2);
        this.star.setColorFilter(r2);
    }
}
