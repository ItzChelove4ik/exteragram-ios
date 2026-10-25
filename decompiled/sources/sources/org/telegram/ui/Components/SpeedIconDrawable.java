package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.AnimatedTextView;

/* loaded from: classes7.dex */
public class SpeedIconDrawable extends Drawable {
    private final Drawable.Callback callback;
    private final Paint outlinePaint;
    private final AnimatedTextView.AnimatedTextDrawable textDrawable;

    /* renamed from: org.telegram.ui.Components.SpeedIconDrawable$1, reason: invalid class name */
    public class AnonymousClass1 implements Drawable.Callback {
        final /* synthetic */ SpeedIconDrawable this$0;

        public AnonymousClass1(SpeedIconDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable r1) {
            this.this$0.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
            this.this$0.scheduleSelf(r2, r3);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable r1, Runnable r2) {
            this.this$0.unscheduleSelf(r2);
        }
    }

    public SpeedIconDrawable(boolean r10) {
        AnonymousClass1 r0 = new AnonymousClass1(this);
        this.callback = r0;
        AnimatedTextView.AnimatedTextDrawable r1 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.textDrawable = r1;
        r1.setCallback(r0);
        r1.setAnimationProperties(0.3f, 0, 165, CubicBezierInterpolator.EASE_OUT_QUINT);
        r1.setGravity(1);
        r1.setTypeface(AndroidUtilities.bold());
        r1.setTextSize(AndroidUtilities.dp(10.0f));
        r1.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        r1.getPaint().setStrokeWidth(AndroidUtilities.dpf2(0.6f));
        if (r10 == false) goto L6;
        Paint r102 = new Paint(1);
        this.outlinePaint = r102;
        r102.setStyle(Paint.Style.STROKE);
        return;
    L6:
        this.outlinePaint = null;
    }

    public static String formatNumber(float r4) {
        Math.abs(r4 - 0.25f);
        float r42 = Math.round(r4 * 10.0f) / 10.0f;
        long r0 = (long) r42;
        if (r42 != r0) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET + r0;
    L7:
        return _UrlKt.FRAGMENT_ENCODE_SET + r42;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r9) {
        Paint r0 = this.outlinePaint;
        if (r0 == null) goto L5;
        r0.setStrokeWidth(AndroidUtilities.dpf2(1.6f));
        RectF r02 = AndroidUtilities.rectTmp;
        r02.set(((getIntrinsicWidth() - this.textDrawable.getCurrentWidth()) / 2.0f) - AndroidUtilities.dpf2(3.0f), ((getIntrinsicHeight() - this.textDrawable.getHeight()) / 2.0f) + AndroidUtilities.dpf2(0.2f), ((getIntrinsicWidth() + this.textDrawable.getCurrentWidth()) / 2.0f) + AndroidUtilities.dpf2(3.0f), (getIntrinsicHeight() + this.textDrawable.getHeight()) / 2.0f);
        r9.drawRoundRect(r02, AndroidUtilities.dpf2(3.0f), AndroidUtilities.dpf2(3.0f), this.outlinePaint);
    L5:
        this.textDrawable.getPaint().setStrokeWidth(AndroidUtilities.dpf2(0.3f));
        this.textDrawable.setBounds(0, (int) ((getIntrinsicHeight() - this.textDrawable.getHeight()) / 2.0f), getIntrinsicWidth(), (int) ((getIntrinsicHeight() + this.textDrawable.getHeight()) / 2.0f));
        this.textDrawable.draw(r9);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.textDrawable.setAlpha(r2);
        Paint r1 = this.outlinePaint;
        if (r1 == null) goto L6;
        r1.setAlpha(r2);
        return;
    }

    public void setColor(int r2) {
        this.textDrawable.setTextColor(r2);
        Paint r1 = this.outlinePaint;
        if (r1 == null) goto L6;
        r1.setColor(r2);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setValue(float r2, boolean r3) {
        String r22 = formatNumber(r2) + "X";
        if (r3 == true) goto L5;
    L8:
        this.textDrawable.cancelAnimation();
        this.textDrawable.setText(r22, r3);
        invalidateSelf();
        return;
    L5:
        if (TextUtils.equals(this.textDrawable.getText(), r22) == false) goto L8;
    }
}
