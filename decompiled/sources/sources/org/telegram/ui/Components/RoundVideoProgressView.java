package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public final class RoundVideoProgressView extends FrameLayout {
    private final RectF arcBounds;
    private final Paint backgroundPaint;
    private float progress;
    private final Paint progressPaint;
    private final float ringInset;
    private final float strokeWidth;

    /* renamed from: org.telegram.ui.Components.RoundVideoProgressView$1, reason: invalid class name */
    public class AnonymousClass1 extends Paint {
        final /* synthetic */ RoundVideoProgressView this$0;

        public AnonymousClass1(RoundVideoProgressView r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.graphics.Paint
        public void setAlpha(int r3) {
            super.setAlpha(r3);
            RoundVideoProgressView.m12499$$Nest$fgetbackgroundPaint(this.this$0).setAlpha(Math.round(r3 * 0.2f));
            this.this$0.invalidate();
        }
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12499$$Nest$fgetbackgroundPaint(RoundVideoProgressView r0) {
        return r0.backgroundPaint;
    }

    public RoundVideoProgressView(Context r5) {
        super(r5);
        Paint r52 = new Paint(1);
        this.backgroundPaint = r52;
        AnonymousClass1 r1 = new AnonymousClass1(this, 1);
        this.progressPaint = r1;
        this.arcBounds = new RectF();
        float r0 = AndroidUtilities.dp(3.0f);
        this.strokeWidth = r0;
        this.ringInset = AndroidUtilities.dp(5.0f) + (r0 * 0.5f);
        configureStroke(r52, 0.2f);
        configureStroke(r1, 1.0f);
        setWillNotDraw(false);
    }

    private static float clamp(float r0, float r1, float r2) {
        return Math.max(r1, Math.min(r2, r0));
    }

    private static void configureStroke(Paint r1, float r2) {
        r1.setColor(-1);
        r1.setAlpha(Math.round(r2 * 255.0f));
        r1.setStyle(Paint.Style.STROKE);
        r1.setStrokeWidth(AndroidUtilities.dp(3.0f));
        r1.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r9) {
        super.dispatchDraw(r9);
        RectF r0 = this.arcBounds;
        float r1 = this.ringInset;
        r0.set(r1, r1, getWidth() - this.ringInset, getHeight() - this.ringInset);
        r9.drawOval(this.arcBounds, this.backgroundPaint);
        r9.drawArc(this.arcBounds, -90.0f, this.progress * 360.0f, false, this.progressPaint);
    }

    public Paint getPaint() {
        return this.progressPaint;
    }

    public void setProgress(float r3) {
        float r32 = clamp(r3, 0.0f, 1.0f);
        if (this.progress != r32) goto L5;
        return;
    L5:
        this.progress = r32;
        invalidate();
    }
}
