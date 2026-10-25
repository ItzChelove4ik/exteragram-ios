package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Stories.recorder.FlashViews;

/* loaded from: classes7.dex */
public class HintTextView extends View implements FlashViews.Invertable {
    private final AnimatedTextView.AnimatedTextDrawable textDrawable;

    public HintTextView(Context r8) {
        super(r8);
        AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(true, true, true);
        this.textDrawable = r0;
        r0.setAnimationProperties(0.35f, 0, 300, CubicBezierInterpolator.EASE_OUT_QUINT);
        r0.setTextColor(-1);
        r0.setTextSize(AndroidUtilities.dp(14.0f));
        r0.setShadowLayer(AndroidUtilities.dp(1.4f), 0.0f, AndroidUtilities.dp(0.4f), 1275068416);
        r0.setGravity(1);
        r0.setCallback(this);
        r0.setOverrideFullWidth(AndroidUtilities.displaySize.x);
    }

    @Override // android.view.View
    public void draw(Canvas r5) {
        super.draw(r5);
        this.textDrawable.setBounds(0, 0, getWidth(), getHeight());
        this.textDrawable.draw(r5);
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        this.textDrawable.setOverrideFullWidth(getMeasuredWidth());
    }

    @Override // org.telegram.ui.Stories.recorder.FlashViews.Invertable
    public void setInvert(float r3) {
        this.textDrawable.setTextColor(ColorUtils.blendARGB(-1, -16777216, r3));
    }

    public void setText(CharSequence r2, boolean r3) {
        this.textDrawable.setText(r2, r3);
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.textDrawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
