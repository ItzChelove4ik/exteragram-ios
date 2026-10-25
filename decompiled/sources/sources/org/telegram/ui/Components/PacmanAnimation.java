package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class PacmanAnimation {
    private boolean currentGhostWalk;
    private Paint edgePaint;
    private Runnable finishRunnable;
    private Path ghostPath;
    private float ghostProgress;
    private boolean ghostWalk;
    private long lastUpdateTime;
    private Paint paint;
    private View parentView;
    private float progress;
    private RectF rect;
    private float translationProgress;

    public PacmanAnimation(View r3) {
        this.paint = new Paint(1);
        this.edgePaint = new Paint(1);
        this.lastUpdateTime = 0;
        this.rect = new RectF();
        this.edgePaint.setStyle(Paint.Style.STROKE);
        this.edgePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.parentView = r3;
    }

    private void drawGhost(Canvas r19, int r20) {
        Path r3 = this.ghostPath;
        if (r3 != null) goto L5;
    L8:
        if (r3 != null) goto L10;
        this.ghostPath = new Path();
    L10:
        this.ghostPath.reset();
        boolean r32 = this.ghostWalk;
        this.currentGhostWalk = r32;
        Path r8 = this.ghostPath;
        float r16 = 35.0f;
        if (r32 == false) goto L13;
        r8.moveTo(0.0f, AndroidUtilities.dp(50.0f));
        this.ghostPath.lineTo(0.0f, AndroidUtilities.dp(24.0f));
        float r17 = 28.0f;
        this.rect.set(0.0f, 0.0f, AndroidUtilities.dp(42.0f), AndroidUtilities.dp(24.0f));
        this.ghostPath.arcTo(this.rect, 180.0f, 180.0f, false);
        this.ghostPath.lineTo(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(50.0f));
        this.ghostPath.lineTo(AndroidUtilities.dp(35.0f), AndroidUtilities.dp(43.0f));
        this.ghostPath.lineTo(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(50.0f));
        this.ghostPath.lineTo(AndroidUtilities.dp(21.0f), AndroidUtilities.dp(43.0f));
        this.ghostPath.lineTo(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(50.0f));
        this.ghostPath.lineTo(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(43.0f));
    L14:
        this.ghostPath.close();
    L15:
        r19.drawPath(this.ghostPath, this.edgePaint);
        if (r20 != 0) goto L18;
        this.paint.setColor(-90112);
    L22:
        r19.drawPath(this.ghostPath, this.paint);
        this.paint.setColor(-1);
        this.rect.set(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(r17));
        r19.drawOval(this.rect, this.paint);
        this.rect.set(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(36.0f), AndroidUtilities.dp(r17));
        r19.drawOval(this.rect, this.paint);
        this.paint.setColor(-16777216);
        this.rect.set(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(19.0f), AndroidUtilities.dp(24.0f));
        r19.drawOval(this.rect, this.paint);
        this.rect.set(AndroidUtilities.dp(30.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(r16), AndroidUtilities.dp(24.0f));
        r19.drawOval(this.rect, this.paint);
        return;
    L18:
        Paint r33 = this.paint;
        if (r20 != 1) goto L21;
        r33.setColor(-85326);
        goto L22
    L21:
        r33.setColor(-16720161);
        goto L22
    L13:
        r17 = 28.0f;
        r8.moveTo(0.0f, AndroidUtilities.dp(43.0f));
        this.ghostPath.lineTo(0.0f, AndroidUtilities.dp(24.0f));
        this.rect.set(0.0f, 0.0f, AndroidUtilities.dp(42.0f), AndroidUtilities.dp(24.0f));
        this.ghostPath.arcTo(this.rect, 180.0f, 180.0f, false);
        this.ghostPath.lineTo(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(43.0f));
        this.ghostPath.lineTo(AndroidUtilities.dp(35.0f), AndroidUtilities.dp(50.0f));
        this.ghostPath.lineTo(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(43.0f));
        this.ghostPath.lineTo(AndroidUtilities.dp(21.0f), AndroidUtilities.dp(50.0f));
        this.ghostPath.lineTo(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(43.0f));
        this.ghostPath.lineTo(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(50.0f));
        goto L14
    L5:
        if (this.ghostWalk != this.currentGhostWalk) goto L8;
        r16 = 35.0f;
        r17 = 28.0f;
        goto L15
    }

    private void update() {
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        if (r2 <= 17) goto L6;
        r2 = 17;
    L6:
        if (this.progress < 1.0f) goto L8;
        this.progress = 0.0f;
    L8:
        float r22 = r2;
        float r02 = this.progress + (r22 / 400.0f);
        this.progress = r02;
        if (r02 <= 1.0f) goto L11;
        this.progress = 1.0f;
    L11:
        float r03 = this.translationProgress + (r22 / 2000.0f);
        this.translationProgress = r03;
        if (r03 <= 1.0f) goto L14;
        this.translationProgress = 1.0f;
    L14:
        float r04 = this.ghostProgress + (r22 / 200.0f);
        this.ghostProgress = r04;
        if (r04 < 1.0f) goto L17;
        this.ghostWalk = !this.ghostWalk;
        this.ghostProgress = 0.0f;
    L17:
        this.parentView.invalidate();
    }

    public void draw(Canvas r14, int r15) {
        int r6 = AndroidUtilities.dp(110.0f);
        if (SharedConfig.useThreeLinesLayout == false) goto L5;
        float r0 = 78.0f;
    L6:
        int r02 = AndroidUtilities.dp(r0);
        float r9 = ((this.parentView.getMeasuredWidth() + r1) * this.translationProgress) - ((AndroidUtilities.dp(62.0f) * 3) + r6);
        int r1 = r6 / 2;
        this.paint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        int r03 = r02 / 2;
        float r3 = r9 + r1;
        r14.drawRect(0.0f, r15 - r03, r3, (r03 + r15) + 1, this.paint);
        this.paint.setColor(-69120);
        float r12 = r9 + r6;
        this.rect.set(r9, r15 - r1, r12, r10 + r6);
        float r04 = this.progress;
        if (r04 >= 0.5f) goto L10;
        float r05 = (1.0f - (r04 / 0.5f)) * 35.0f;
    L9:
        int r06 = (int) r05;
        float r2 = r06;
        float r32 = 360 - (r06 * 2);
        r14.drawArc(this.rect, r2, r32, true, this.edgePaint);
        r14.drawArc(this.rect, r2, r32, true, this.paint);
        this.paint.setColor(-16777216);
        r14.drawCircle(r3 - AndroidUtilities.dp(8.0f), r10 + (r6 / 4), AndroidUtilities.dp(8.0f), this.paint);
        r14.save();
        r14.translate(r12 + AndroidUtilities.dp(20.0f), r15 - AndroidUtilities.dp(25.0f));
        int r13 = 0;
    L12:
        if (r13 >= 3) goto L14;
        drawGhost(r14, r13);
        r14.translate(AndroidUtilities.dp(62.0f), 0.0f);
        r13 = r13 + 1;
        goto L12
    L14:
        r14.restore();
        if (this.translationProgress < 1.0f) goto L17;
        this.finishRunnable.run();
    L17:
        update();
        return;
    L10:
        r05 = ((r04 - 0.5f) * 35.0f) / 0.5f;
        goto L9
    L5:
        r0 = 72.0f;
        goto L6
    }

    public void setFinishRunnable(Runnable r1) {
        this.finishRunnable = r1;
    }

    public void start() {
        this.translationProgress = 0.0f;
        this.progress = 0.0f;
        this.lastUpdateTime = System.currentTimeMillis();
        this.parentView.invalidate();
    }
}
