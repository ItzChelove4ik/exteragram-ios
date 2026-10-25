package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class ProgressView {
    public float currentProgress;
    public int height;
    private Paint innerPaint;
    private Paint outerPaint;
    public float progressHeight;
    public int width;

    public ProgressView() {
        this.currentProgress = 0.0f;
        this.progressHeight = AndroidUtilities.dp(2.0f);
        this.innerPaint = new Paint();
        this.outerPaint = new Paint();
    }

    public void draw(Canvas r19) {
        int r1 = this.height;
        float r3 = this.progressHeight;
        r19.drawRect(0.0f, (r1 / 2) - (r3 / 2.0f), this.width, (r1 / 2) + (r3 / 2.0f), this.innerPaint);
        int r12 = this.height;
        float r32 = this.progressHeight;
        r19.drawRect(0.0f, (r12 / 2) - (r32 / 2.0f), this.width * this.currentProgress, (r12 / 2) + (r32 / 2.0f), this.outerPaint);
    }

    public void setProgress(float r3) {
        this.currentProgress = r3;
        if (r3 >= 0.0f) goto L7;
        this.currentProgress = 0.0f;
        return;
    L7:
        if (r3 <= 1.0f) goto L10;
        this.currentProgress = 1.0f;
        return;
    }

    public void setProgressColors(int r2, int r3) {
        this.innerPaint.setColor(r2);
        this.outerPaint.setColor(r3);
    }
}
