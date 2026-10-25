package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AnimationUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class PlayPauseDrawable extends Drawable {
    private int alpha;
    float duration;
    private long lastUpdateTime;
    private final Paint paint;
    private View parent;
    private boolean pause;
    private float progress;
    private final int size;

    public PlayPauseDrawable(int r2) {
        this.alpha = 255;
        this.duration = 300.0f;
        this.size = AndroidUtilities.dp(r2);
        Paint r22 = new Paint(1);
        this.paint = r22;
        r22.setColor(-1);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r10) {
        long r0 = AnimationUtils.currentAnimationTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        if (r2 <= 18) goto L5;
        r2 = 16;
    L5:
        boolean r02 = this.pause;
        if (r02 == false) goto L16;
        float r4 = this.progress;
        if (r4 >= 1.0f) goto L16;
        float r42 = r4 + (r2 / this.duration);
        this.progress = r42;
        if (r42 < 1.0f) goto L12;
        this.progress = 1.0f;
    L26:
        Rect r03 = getBounds();
        int r7 = this.alpha;
        if (r7 != 255) goto L29;
        r10.save();
        Canvas r22 = r10;
    L30:
        r22.translate(r03.centerX() + (AndroidUtilities.dp(1.0f) * (1.0f - this.progress)), r03.centerY());
        float r04 = this.progress * 500.0f;
        if (r04 >= 100.0f) goto L34;
        float r102 = CubicBezierInterpolator.EASE_BOTH.getInterpolation(r04 / 100.0f) * (-5.0f);
    L37:
        r22.scale((this.size * 1.45f) / AndroidUtilities.dp(28.0f), (this.size * 1.5f) / AndroidUtilities.dp(28.0f));
        r22.rotate(r102);
        PathAnimator r103 = Theme.playPauseAnimator;
        if (r103 == null) goto L40;
        r103.draw(r22, this.paint, r04);
        r22.scale(1.0f, -1.0f);
        Theme.playPauseAnimator.draw(r22, this.paint, r04);
    L40:
        r22.restore();
        return;
    L34:
        if (r04 >= 484.0f) goto L36;
        r102 = (CubicBezierInterpolator.EASE_BOTH.getInterpolation((r04 - 100.0f) / 384.0f) * 95.0f) - 5.0f;
        goto L37
    L36:
        r102 = 90.0f;
        goto L37
    L29:
        r22 = r10;
        r22.saveLayerAlpha(r03.left, r03.top, r03.right, r03.bottom, r7, 31);
        goto L30
    L12:
        View r05 = this.parent;
        if (r05 == null) goto L15;
        r05.invalidate();
    L15:
        invalidateSelf();
    L16:
        if (r02 == true) goto L26;
        float r06 = this.progress;
        if (r06 <= 0.0f) goto L26;
        float r07 = r06 - (r2 / this.duration);
        this.progress = r07;
        if (r07 > 0.0f) goto L22;
        this.progress = 0.0f;
        goto L26
    L22:
        View r08 = this.parent;
        if (r08 == null) goto L25;
        r08.invalidate();
    L25:
        invalidateSelf();
        goto L26
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.size;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.size;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    public void setColor(int r1) {
        this.paint.setColor(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.paint.setColorFilter(r1);
    }

    public void setDuration(int r1) {
        this.duration = r1;
    }

    public void setParent(View r1) {
        this.parent = r1;
    }

    public void setPause(boolean r2) {
        setPause(r2, true);
    }

    public void setPause(boolean r2, boolean r3) {
        if (this.pause == r2) goto L12;
        this.pause = r2;
        if (r3 == true) goto L10;
        if (r2 == false) goto L8;
        float r22 = 1.0f;
    L9:
        this.progress = r22;
        goto L10
    L8:
        r22 = 0.0f;
    L10:
        this.lastUpdateTime = AnimationUtils.currentAnimationTimeMillis();
        invalidateSelf();
        return;
    }
}
