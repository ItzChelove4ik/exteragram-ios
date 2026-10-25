package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes3.dex */
public class MsgClockDrawable extends Drawable {
    private int alpha;
    private int color;
    private int colorAlpha;
    private Drawable.ConstantState constantState;
    private Paint paint;
    private long startTime;

    /* renamed from: org.telegram.ui.Components.MsgClockDrawable$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends Drawable.ConstantState {
        final /* synthetic */ MsgClockDrawable this$0;

        public AnonymousClass1(MsgClockDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new MsgClockDrawable();
        }
    }

    public MsgClockDrawable() {
        Paint r0 = new Paint(1);
        this.paint = r0;
        this.alpha = 255;
        this.colorAlpha = 255;
        r0.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        this.startTime = System.currentTimeMillis();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r12) {
        Rect r7 = getBounds();
        int r2 = Math.min(r7.width(), r7.height());
        r12.drawCircle(r7.centerX(), r7.centerY(), (r2 >> 1) - AndroidUtilities.dp(0.5f), this.paint);
        long r8 = System.currentTimeMillis();
        r12.save();
        r12.rotate((((r8 - this.startTime) % 1500.0f) * 360.0f) / 1500.0f, r7.centerX(), r7.centerY());
        r12.drawLine(r7.centerX(), r7.centerY(), r7.centerX(), r7.centerY() - AndroidUtilities.dp(3.0f), this.paint);
        r12.restore();
        r12.save();
        r12.rotate((((r8 - this.startTime) % 4500.0f) * 360.0f) / 4500.0f, r7.centerX(), r7.centerY());
        r12.drawLine(r7.centerX(), r7.centerY(), r7.centerX() + AndroidUtilities.dp(2.3f), r7.centerY(), this.paint);
        r12.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.constantState != null) goto L6;
        this.constantState = new AnonymousClass1(this);
    L6:
        return this.constantState;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(12.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(12.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r3) {
        if (this.alpha == r3) goto L6;
        this.alpha = r3;
        this.paint.setAlpha((int) (r3 * (this.colorAlpha / 255.0f)));
        return;
    }

    public void setColor(int r5) {
        if (r5 == this.color) goto L5;
        int r0 = Color.alpha(r5);
        this.colorAlpha = r0;
        this.paint.setColor(ColorUtils.setAlphaComponent(r5, (int) (this.alpha * (r0 / 255.0f))));
    L5:
        this.color = r5;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
