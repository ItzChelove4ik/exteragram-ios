package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import androidx.annotation.Keep;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class AnimatedArrowDrawable extends Drawable {
    private float animProgress;
    private float animateToProgress;
    private float customHeightDp;
    private float customStrokeWidthDp;
    private float customWidthDp;
    private boolean isSmall;
    private long lastUpdateTime;
    private Paint paint;
    private Path path;

    public AnimatedArrowDrawable(int r4, float r5, float r6, float r7) {
        this.path = new Path();
        Paint r0 = new Paint(1);
        this.paint = r0;
        r0.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(AndroidUtilities.dpf2(r7));
        this.paint.setColor(r4);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.isSmall = true;
        this.customWidthDp = r5;
        this.customHeightDp = r6;
        this.customStrokeWidthDp = r7;
        updatePath();
    }

    public AnimatedArrowDrawable(int r3, boolean r4) {
        this.path = new Path();
        Paint r0 = new Paint(1);
        this.paint = r0;
        r0.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.paint.setColor(r3);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.isSmall = r4;
        updatePath();
    }

    private void checkAnimation() {
        if (this.animateToProgress == this.animProgress) goto L14;
        long r0 = SystemClock.elapsedRealtime();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        float r02 = this.animProgress;
        float r1 = this.animateToProgress;
        if (r02 >= r1) goto L9;
        float r03 = r02 + (r2 / 180.0f);
        this.animProgress = r03;
        if (r03 <= r1) goto L12;
        this.animProgress = r1;
    L12:
        updatePath();
        invalidateSelf();
        return;
    L9:
        float r04 = r02 - (r2 / 180.0f);
        this.animProgress = r04;
        if (r04 >= r1) goto L12;
        this.animProgress = r1;
        goto L12
    }

    private void updatePath() {
        this.path.reset();
        float r0 = (this.animProgress * 2.0f) - 1.0f;
        if (this.customWidthDp > 0.0f) goto L5;
    L8:
        boolean r2 = this.isSmall;
        Path r3 = this.path;
        if (r2 == false) goto L12;
        r3.moveTo(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(6.0f) - (AndroidUtilities.dp(2.0f) * r0));
        this.path.lineTo(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(6.0f) + (AndroidUtilities.dp(2.0f) * r0));
        this.path.lineTo(AndroidUtilities.dp(13.0f), AndroidUtilities.dp(6.0f) - (AndroidUtilities.dp(2.0f) * r0));
        return;
    L12:
        r3.moveTo(AndroidUtilities.dp(4.5f), (AndroidUtilities.dp(12.0f) - (AndroidUtilities.dp(4.0f) * r0)) + (AndroidUtilities.dp(2.0f) * this.animProgress));
        this.path.lineTo(AndroidUtilities.dp(13.0f), (AndroidUtilities.dp(12.0f) + (AndroidUtilities.dp(4.0f) * r0)) + (AndroidUtilities.dp(2.0f) * this.animProgress));
        this.path.lineTo(AndroidUtilities.dp(21.5f), (AndroidUtilities.dp(12.0f) - (AndroidUtilities.dp(4.0f) * r0)) + (AndroidUtilities.dp(2.0f) * this.animProgress));
        return;
    L5:
        if (this.customHeightDp <= 0.0f) goto L8;
        float r02 = AndroidUtilities.dpf2(this.customStrokeWidthDp) / 2.0f;
        float r22 = AndroidUtilities.dpf2(this.customWidthDp) - r02;
        float r1 = AndroidUtilities.dpf2(this.customHeightDp) - r02;
        float r4 = r1 - r02;
        this.path.moveTo(r02, r1 - (this.animProgress * r4));
        this.path.lineTo((r02 + r22) / 2.0f, r02 + (this.animProgress * r4));
        this.path.lineTo(r22, r1 - (r4 * this.animProgress));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r3) {
        r3.drawPath(this.path, this.paint);
        checkAnimation();
    }

    @Keep
    public float getAnimationProgress() {
        return this.animProgress;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        float r1 = this.customHeightDp;
        if (r1 <= 0.0f) goto L7;
        return AndroidUtilities.dp(r1);
    L7:
        return AndroidUtilities.dp(26.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        float r1 = this.customWidthDp;
        if (r1 <= 0.0f) goto L7;
        return AndroidUtilities.dp(r1);
    L7:
        return AndroidUtilities.dp(26.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Keep
    public void setAnimationProgress(float r1) {
        this.animProgress = r1;
        this.animateToProgress = r1;
        updatePath();
        invalidateSelf();
    }

    public void setAnimationProgressAnimated(float r3) {
        if (this.animateToProgress != r3) goto L5;
        return;
    L5:
        this.animateToProgress = r3;
        this.lastUpdateTime = SystemClock.elapsedRealtime();
        invalidateSelf();
    }

    public void setColor(int r2) {
        this.paint.setColor(r2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.paint.setColorFilter(r1);
    }
}
