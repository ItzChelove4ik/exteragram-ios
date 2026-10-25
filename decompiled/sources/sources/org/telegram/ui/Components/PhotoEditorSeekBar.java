package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class PhotoEditorSeekBar extends View {
    private PhotoEditorSeekBarDelegate delegate;
    private Paint innerPaint;
    private int maxValue;
    private int minValue;
    private Paint outerPaint;
    private boolean pressed;
    private float progress;
    private int thumbDX;
    private int thumbSize;

    public interface PhotoEditorSeekBarDelegate {
        void onProgressChanged(int r1, int r2);
    }

    public PhotoEditorSeekBar(Context r2) {
        super(r2);
        this.innerPaint = new Paint();
        this.outerPaint = new Paint(1);
        this.thumbSize = AndroidUtilities.dp(16.0f);
        this.thumbDX = 0;
        this.progress = 0.0f;
        this.pressed = false;
        this.innerPaint.setColor(-11711155);
        this.outerPaint.setColor(-1);
    }

    public int getProgress() {
        return (int) (this.minValue + (this.progress * (this.maxValue - r0)));
    }

    @Override // android.view.View
    public void onDraw(Canvas r18) {
        int r1 = (getMeasuredHeight() - this.thumbSize) / 2;
        int r2 = getMeasuredWidth();
        int r3 = this.thumbSize;
        int r22 = (int) ((r2 - r3) * this.progress);
        r18.drawRect(r3 / 2, (getMeasuredHeight() / 2) - AndroidUtilities.dp(1.0f), getMeasuredWidth() - (this.thumbSize / 2), (getMeasuredHeight() / 2) + AndroidUtilities.dp(1.0f), this.innerPaint);
        if (this.minValue != 0) goto L6;
        r18.drawRect(this.thumbSize / 2, (getMeasuredHeight() / 2) - AndroidUtilities.dp(1.0f), r22, (getMeasuredHeight() / 2) + AndroidUtilities.dp(1.0f), this.outerPaint);
    L9:
        int r32 = this.thumbSize;
        r18.drawCircle(r22 + (r32 / 2), r1 + (r32 / 2), r32 / 2, this.outerPaint);
        return;
    L6:
        if (this.progress <= 0.5f) goto L8;
        r18.drawRect((getMeasuredWidth() / 2) - AndroidUtilities.dp(1.0f), (getMeasuredHeight() - this.thumbSize) / 2, getMeasuredWidth() / 2, (getMeasuredHeight() + this.thumbSize) / 2, this.outerPaint);
        r18.drawRect(getMeasuredWidth() / 2, (getMeasuredHeight() / 2) - AndroidUtilities.dp(1.0f), r22, (getMeasuredHeight() / 2) + AndroidUtilities.dp(1.0f), this.outerPaint);
        goto L9
    L8:
        r18.drawRect(getMeasuredWidth() / 2, (getMeasuredHeight() - this.thumbSize) / 2, (getMeasuredWidth() / 2) + AndroidUtilities.dp(1.0f), (getMeasuredHeight() + this.thumbSize) / 2, this.outerPaint);
        r18.drawRect(r22, (getMeasuredHeight() / 2) - AndroidUtilities.dp(1.0f), getMeasuredWidth() / 2, (getMeasuredHeight() / 2) + AndroidUtilities.dp(1.0f), this.outerPaint);
        goto L9
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r9) {
        if (r9 != null) goto L5;
        return false;
    L5:
        float r1 = r9.getX();
        float r2 = r9.getY();
        float r3 = (int) ((getMeasuredWidth() - this.thumbSize) * this.progress);
        float r6 = 0.0f;
        if (r9.getAction() != 0) goto L18;
        int r92 = getMeasuredHeight();
        int r4 = this.thumbSize;
        float r93 = (r92 - r4) / 2;
        if ((r3 - r93) <= r1) goto L10;
    L42:
        return false;
    L10:
        if (r1 > ((r4 + r3) + r93)) goto L42;
        if (r2 < 0.0f) goto L42;
        if (r2 > getMeasuredHeight()) goto L42;
        this.pressed = true;
        this.thumbDX = (int) (r1 - r3);
        getParent().requestDisallowInterceptTouchEvent(true);
        invalidate();
        return true;
    L18:
        if (r9.getAction() == 1) goto L39;
        if (r9.getAction() == 3) goto L39;
        if (r9.getAction() != 2) goto L42;
        if (this.pressed == false) goto L42;
        float r94 = (int) (r1 - this.thumbDX);
        if (r94 >= 0.0f) goto L30;
    L33:
        this.progress = r6 / (getMeasuredWidth() - this.thumbSize);
        PhotoEditorSeekBarDelegate r95 = this.delegate;
        if (r95 == null) goto L36;
        r95.onProgressChanged(((Integer) getTag()).intValue(), getProgress());
    L36:
        invalidate();
        return true;
    L30:
        if (r94 <= (getMeasuredWidth() - this.thumbSize)) goto L32;
        r6 = getMeasuredWidth() - this.thumbSize;
        goto L33
    L32:
        r6 = r94;
    L39:
        if (this.pressed == false) goto L42;
        this.pressed = false;
        invalidate();
        return true;
    }

    public void setDelegate(PhotoEditorSeekBarDelegate r1) {
        this.delegate = r1;
    }

    public void setMinMax(int r1, int r2) {
        this.minValue = r1;
        this.maxValue = r2;
    }

    public void setProgress(int r2) {
        setProgress(r2, true);
    }

    public void setProgress(int r3, boolean r4) {
        int r0 = this.minValue;
        if (r3 >= r0) goto L5;
        r3 = r0;
    L8:
        this.progress = (r3 - r0) / (this.maxValue - r0);
        invalidate();
        if (r4 == false) goto L14;
        PhotoEditorSeekBarDelegate r32 = this.delegate;
        if (r32 == null) goto L15;
        r32.onProgressChanged(((Integer) getTag()).intValue(), getProgress());
        return;
    L15:
        return;
    L14:
        return;
    L5:
        int r1 = this.maxValue;
        if (r3 <= r1) goto L8;
        r3 = r1;
        goto L8
    }
}
