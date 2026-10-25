package org.telegram.ui.Components.poll.buttons;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import me.vkryl.android.animator.BoolAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RadialProgress;

/* loaded from: classes7.dex */
public class PollInstantButtonDrawable extends PollButtonDrawableBase {
    private final BoolAnimator animatorProgressVisible;
    private final AnimatedTextView.AnimatedTextDrawable buttonTextAnimatedDrawable;
    private float offsetY;
    private final RadialProgress radialProgress;

    public PollInstantButtonDrawable(View r7, Theme.ResourcesProvider r8) {
        super(r8);
        RadialProgress r0 = new RadialProgress(r7);
        this.radialProgress = r0;
        r0.setBackground(null, true, false);
        r0.setRotationTime(650.0f);
        r0.setProgress(0.69f, false);
        r0.setStrokeWidth(AndroidUtilities.dp(1.5f));
        this.animatorProgressVisible = new BoolAnimator(r7, CubicBezierInterpolator.EASE_OUT_QUINT, 260);
        AnimatedTextView.AnimatedTextDrawable r72 = new AnimatedTextView.AnimatedTextDrawable(true, false, false);
        this.buttonTextAnimatedDrawable = r72;
        r72.setTypeface(AndroidUtilities.bold());
        r72.setTextSize(AndroidUtilities.dp(13.0f));
        r72.setGravity(17);
        setSelectorsColor(Theme.getColor(Theme.key_listSelector, r8));
    }

    private void checkBounds(Rect r5) {
        int r0 = (int) this.offsetY;
        this.buttonTextAnimatedDrawable.setBounds(r5.left, r5.top + r0, r5.right, r5.bottom + r0);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r4) {
        float r0 = this.animatorProgressVisible.getFloatValue();
        if (r0 >= 1.0f) goto L6;
        DrawableUtils.drawWithScale(r4, this.buttonTextAnimatedDrawable, 1.0f - r0);
    L6:
        if (r0 <= 0.0f) goto L9;
        float r1 = getBounds().exactCenterX();
        float r2 = getBounds().exactCenterY();
        r4.save();
        r4.scale(r0, r0, r1, r2);
        this.radialProgress.draw(r4);
        r4.restore();
        return;
    }

    public float getProgressFactor() {
        return this.animatorProgressVisible.getFloatValue();
    }

    @Override // org.telegram.ui.Components.poll.buttons.PollButtonDrawableBase
    public void onAlphaChanged(int r1) {
        super.onAlphaChanged(r1);
        this.buttonTextAnimatedDrawable.setAlpha(r1);
    }

    @Override // org.telegram.ui.Components.poll.buttons.PollButtonDrawableBase, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r5) {
        super.onBoundsChange(r5);
        checkBounds(getBounds());
        int r0 = AndroidUtilities.dp(11.0f);
        int r1 = r5.centerX();
        int r52 = r5.centerY();
        this.radialProgress.setProgressRect(r1 - r0, r52 - r0, r1 + r0, r52 + r0);
    }

    public void setButtonText(CharSequence r1, boolean r2) {
        this.buttonTextAnimatedDrawable.setText(r1, r2);
    }

    public void setButtonTextColor(int r2) {
        this.buttonTextAnimatedDrawable.setTextColor(r2);
        this.radialProgress.setProgressColor(r2);
    }

    public void setLoading(boolean r2, boolean r3) {
        if (this.animatorProgressVisible.getValue() == r2) goto L6;
        this.animatorProgressVisible.setValue(r2, r3);
        return;
    }

    public void setTextOffsetY(float r2) {
        if (this.offsetY == r2) goto L6;
        this.offsetY = r2;
        checkBounds(getBounds());
        return;
    }

    @Override // org.telegram.ui.Components.poll.buttons.PollButtonDrawableBase
    public void setupCallbacks(Drawable.Callback r1) {
        super.setupCallbacks(r1);
        this.buttonTextAnimatedDrawable.setCallback(r1);
    }
}
