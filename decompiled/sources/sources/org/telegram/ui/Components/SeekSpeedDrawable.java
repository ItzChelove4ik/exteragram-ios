package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;

/* loaded from: classes7.dex */
public class SeekSpeedDrawable extends Drawable {
    private final AnimatedFloat animatedDirection;
    private final AnimatedFloat animatedHintShown;
    private final AnimatedFloat animatedShown;
    private final AnimatedFloat animatedSpeed;
    private final Paint arrowPaint;
    private final Paint backgroundPaint;
    private int direction;
    private final Runnable hideHintRunnable;
    private boolean hideHintScheduled;
    private final Path hintArrow;
    private RLottieDrawable hintDrawable;
    private final RectF hintRect;
    private final Text hintText;
    private Runnable invalidate;
    private final boolean isPiP;
    private final boolean isRound;
    private long lastFrameTime;
    private float lastSpeed;
    private final Path leftArrow;
    private final Path rightArrow;
    private boolean showHint;
    private boolean shown;
    private final RectF speedRect;
    private final AnimatedTextView.AnimatedTextDrawable speedText;
    private float t;

    /* renamed from: org.telegram.ui.Components.SeekSpeedDrawable$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatedTextView.AnimatedTextDrawable {
        final /* synthetic */ SeekSpeedDrawable this$0;
        final /* synthetic */ Runnable val$invalidate;

        public AnonymousClass1(SeekSpeedDrawable r1, boolean r2, boolean r3, boolean r4, boolean r5, Runnable r6) {
            this.this$0 = r1;
            this.val$invalidate = r6;
            super(r2, r3, r4, r5);
        }

        @Override // android.graphics.drawable.Drawable
        public void invalidateSelf() {
            this.val$invalidate.run();
        }
    }

    /* renamed from: org.telegram.ui.Components.SeekSpeedDrawable$2, reason: invalid class name */
    public class AnonymousClass2 implements Drawable.Callback {
        final /* synthetic */ SeekSpeedDrawable this$0;

        public AnonymousClass2(SeekSpeedDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable r1) {
            SeekSpeedDrawable.m12619$$Nest$fgetinvalidate(this.this$0).run();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable r1, Runnable r2) {
        }
    }

    /* renamed from: $r8$lambda$tAcRf-HhlHlZdPqfnn-qjWJRv_g, reason: not valid java name */
    public static /* synthetic */ void m12618$r8$lambda$tAcRfHhlHlZdPqfnnqjWJRv_g(SeekSpeedDrawable r0) {
        r0.lambda$new$0();
    }

    /* renamed from: -$$Nest$fgetinvalidate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m12619$$Nest$fgetinvalidate(SeekSpeedDrawable r0) {
        return r0.invalidate;
    }

    public SeekSpeedDrawable(Runnable r24, boolean r25, boolean r26) {
        boolean r9 = true;
        this.backgroundPaint = new Paint(1);
        Paint r10 = new Paint(1);
        this.arrowPaint = r10;
        Path r11 = new Path();
        this.hintArrow = r11;
        this.hintText = new Text(LocaleController.getString(R.string.SeekSpeedHint), 14.0f);
        Path r12 = new Path();
        this.leftArrow = r12;
        Path r13 = new Path();
        this.rightArrow = r13;
        this.direction = 1;
        this.speedRect = new RectF();
        this.hintRect = new RectF();
        this.hideHintRunnable = new SeekSpeedDrawable$$ExternalSyntheticLambda0(this);
        this.invalidate = r24;
        this.isPiP = r25;
        this.isRound = r26;
        CubicBezierInterpolator r20 = CubicBezierInterpolator.EASE_OUT_QUINT;
        AnimatedFloat r14 = new AnimatedFloat(r24, 0, 360, r20);
        this.animatedShown = r14;
        r14.set(false, true);
        this.animatedDirection = new AnimatedFloat(r24, 0, 320, r20);
        this.animatedSpeed = new AnimatedFloat(r24, 0, 200, r20);
        AnimatedFloat r142 = new AnimatedFloat(r24, 0, 360, r20);
        this.animatedHintShown = r142;
        r142.set(false, true);
        AnonymousClass1 r15 = new AnonymousClass1(this, false, true, true, true, r24);
        this.speedText = r15;
        r15.setScaleProperty(0.3f);
        r15.setAnimationProperties(0.4f, 0, 650, 1.6f, r20);
        r15.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
        r15.setTextSize(AndroidUtilities.dp(16.0f));
        setSpeed(2.0f, false);
        r15.setTextColor(-1);
        r15.setGravity(17);
        r10.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(1.66f)));
        r12.moveTo(AndroidUtilities.dp(8.66f), -AndroidUtilities.dp(6.33f));
        r12.lineTo(0.0f, 0.0f);
        r12.lineTo(AndroidUtilities.dp(8.66f), AndroidUtilities.dp(6.33f));
        r12.close();
        r13.moveTo(0.0f, -AndroidUtilities.dp(6.33f));
        r13.lineTo(AndroidUtilities.dp(8.66f), 0.0f);
        r13.lineTo(0.0f, AndroidUtilities.dp(6.33f));
        r13.close();
        if (r25 == true) goto L8;
        if (r26 == true) goto L8;
        if (MessagesController.getGlobalMainSettings().getBoolean("seekSpeedHintShowed", false) == true) goto L8;
    L9:
        this.showHint = r9;
        r11.moveTo(-AndroidUtilities.dp(6.5f), 0.0f);
        r11.lineTo(0.0f, -AndroidUtilities.dp(6.33f));
        r11.lineTo(AndroidUtilities.dp(6.5f), 0.0f);
        r11.close();
        return;
    L8:
        r9 = false;
        goto L9
    }

    private /* synthetic */ void lambda$new$0() {
        this.showHint = false;
        this.invalidate.run();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r25) {
        Rect r2 = getBounds();
        float r3 = this.speedText.getCurrentWidth() + AndroidUtilities.dp(46.0f);
        float r4 = this.animatedShown.set(this.shown);
        float r5 = this.animatedDirection.set(this.direction);
        if (r4 <= 0.0f) goto L27;
        float r7 = this.animatedSpeed.set(Math.abs(this.lastSpeed));
        long r8 = System.currentTimeMillis();
        float r10 = Math.min(0.016f, (r8 - this.lastFrameTime) / 1000.0f);
        this.lastFrameTime = r8;
        this.t += r10 * (Math.min(r7, 4.0f) * 1.5f);
        this.invalidate.run();
        float r32 = r3 / 2.0f;
        this.speedRect.set(r2.centerX() - r32, r2.top + AndroidUtilities.dp(9.0f), r2.centerX() + r32, r2.top + AndroidUtilities.dp(37.0f));
        r25.save();
        float r82 = r4 * 0.4f;
        float r102 = 0.6f + r82;
        if (r2.width() >= (AndroidUtilities.displaySize.x * 0.7f)) goto L10;
        r102 = r102 * 0.75f;
        if (this.isPiP == false) goto L10;
        r25.translate(-AndroidUtilities.dp(45.0f), 0.0f);
    L10:
        r25.scale(r102, r102, this.speedRect.centerX(), this.speedRect.top);
        r25.translate(0.0f, (-AndroidUtilities.dp(15.0f)) * (1.0f - r4));
        r25.clipRect(this.speedRect);
        this.backgroundPaint.setColor(Theme.multAlpha(-16777216, r82));
        RectF r83 = this.speedRect;
        r25.drawRoundRect(r83, r83.height() / 2.0f, this.speedRect.height() / 2.0f, this.backgroundPaint);
        this.speedText.setBounds(this.speedRect);
        r25.save();
        float r11 = -r5;
        r25.translate(((this.speedRect.centerX() - r32) + AndroidUtilities.dp(9.0f)) - (AndroidUtilities.dp(30.0f) * (1.0f - Math.max(0.0f, r11))), this.speedRect.centerY());
        this.arrowPaint.setColor(Theme.multAlpha(-1, (Math.max(0.0f, r11) * r4) * ((((((float) Math.sin(this.t * 3.141592653589793d)) / 2.0f) + 1.0f) * 0.75f) + 0.2f)));
        r25.drawPath(this.leftArrow, this.arrowPaint);
        r25.translate(AndroidUtilities.dp(10.66f), 0.0f);
        this.arrowPaint.setColor(Theme.multAlpha(-1, (Math.max(0.0f, r11) * r4) * ((((((float) Math.sin((this.t + 0.17f) * 3.141592653589793d)) / 2.0f) + 1.0f) * 0.75f) + 0.2f)));
        r25.drawPath(this.leftArrow, this.arrowPaint);
        r25.restore();
        r25.save();
        r25.translate(((-AndroidUtilities.dp(28.0f)) / 2.0f) * r5, 0.0f);
        this.speedText.setAlpha((int) (r4 * 255.0f));
        this.speedText.draw(r25);
        r25.restore();
        r25.save();
        r25.translate(((this.speedRect.centerX() + r32) - AndroidUtilities.dp(30.0f)) + (AndroidUtilities.dp(30.0f) * (1.0f - Math.max(0.0f, r5))), this.speedRect.centerY());
        this.arrowPaint.setColor(Theme.multAlpha(-1, (Math.max(0.0f, r5) * r4) * ((((((float) Math.sin(this.t * 3.141592653589793d)) / 2.0f) + 1.0f) * 0.75f) + 0.2f)));
        r25.drawPath(this.rightArrow, this.arrowPaint);
        r25.translate(AndroidUtilities.dp(10.66f), 0.0f);
        this.arrowPaint.setColor(Theme.multAlpha(-1, (Math.max(0.0f, r5) * r4) * ((((((float) Math.sin((this.t - 0.17f) * 3.141592653589793d)) / 2.0f) + 1.0f) * 0.75f) + 0.2f)));
        r25.drawPath(this.rightArrow, this.arrowPaint);
        r25.restore();
        r25.restore();
        AnimatedFloat r33 = this.animatedHintShown;
        if (this.showHint == true) goto L13;
    L15:
        boolean r52 = false;
    L16:
        float r53 = r33.set(r52);
        if (r53 > 0.0f) goto L19;
        return;
    L19:
        if (this.hintDrawable != null) goto L21;
        RLottieDrawable r17 = new RLottieDrawable(R.raw.seek_speed_hint, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), true, null);
        this.hintDrawable = r17;
        r17.setAllowDecodeSingleFrame(true);
        this.hintDrawable.setCallback(new AnonymousClass2(this));
        this.hintDrawable.setAutoRepeat(1);
        this.hintDrawable.start();
    L21:
        float r34 = this.hintText.getCurrentWidth() + AndroidUtilities.dp(54.0f);
        float r9 = AndroidUtilities.dp(32.0f);
        RectF r103 = this.hintRect;
        float r35 = r34 / 2.0f;
        float r12 = r2.centerX() - r35;
        RectF r84 = this.speedRect;
        float r15 = (r84.top + (r84.height() * r4)) + AndroidUtilities.dp(11.0f);
        float r22 = r2.centerX() + r35;
        RectF r36 = this.speedRect;
        r103.set(r12, r15, r22, ((r36.top + (r36.height() * r4)) + AndroidUtilities.dp(11.0f)) + r9);
        r25.save();
        float r23 = (0.25f * r53) + 0.75f;
        r25.scale(r23, r23, this.hintRect.centerX(), this.hintRect.top);
        this.backgroundPaint.setColor(Theme.multAlpha(-16777216, r53 * 0.4f));
        r25.save();
        r25.translate(this.hintRect.centerX(), this.hintRect.top);
        r25.drawPath(this.hintArrow, this.backgroundPaint);
        r25.restore();
        r25.drawRoundRect(this.hintRect, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.backgroundPaint);
        this.hintDrawable.setBounds(((int) this.hintRect.left) + AndroidUtilities.dp(11.0f), ((int) this.hintRect.centerY()) - (AndroidUtilities.dp(24.0f) / 2), ((int) this.hintRect.left) + AndroidUtilities.dp(35.0f), ((int) this.hintRect.centerY()) + (AndroidUtilities.dp(24.0f) / 2));
        this.hintDrawable.setAlpha((int) (r53 * 255.0f));
        if (this.hintDrawable.isRunning() == true) goto L24;
        this.hintDrawable.restart(true);
    L24:
        this.hintDrawable.draw(r25);
        this.hintText.draw(r25, this.hintRect.left + AndroidUtilities.dp(39.0f), this.hintRect.centerY(), -1, r53);
        r25.restore();
        return;
    L13:
        if (this.shown == false) goto L15;
        r52 = true;
        goto L16
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public boolean isShown() {
        if (this.shown == false) goto L5;
        return true;
    L5:
        if (this.animatedShown.get() > 0.0f) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setShown(boolean r2, boolean r3) {
        this.shown = r2;
        if (r3 == true) goto L5;
        this.animatedShown.set(r2, true);
    L5:
        this.invalidate.run();
        RLottieDrawable r32 = this.hintDrawable;
        if (r32 != null) goto L8;
        return;
    L8:
        if (this.showHint == false) goto L15;
        if (r2 == false) goto L12;
        r32.restart();
        return;
    L12:
        r32.stop();
        return;
    }

    public void setSpeed(float r5, boolean r6) {
        if (Math.floor(this.lastSpeed * 10.0f) == Math.floor(10.0f * r5)) goto L6;
        this.speedText.cancelAnimation();
        this.speedText.setText(String.format(Locale.US, "%.1fx", new Object[]{Float.valueOf(Math.abs(r5))}), r6);
        this.lastSpeed = r5;
    L6:
        if (r5 <= 0.0f) goto L8;
        int r0 = 1;
    L9:
        this.direction = r0;
        if (r6 == true) goto L12;
        this.animatedDirection.set(r0, true);
    L12:
        this.invalidate.run();
        if (this.showHint == true) goto L15;
        return;
    L15:
        if (Math.abs(r5) > 3.0f) goto L17;
        return;
    L17:
        if (this.hideHintScheduled == true) goto L22;
        this.hideHintScheduled = true;
        AndroidUtilities.runOnUIThread(this.hideHintRunnable, 2500);
        MessagesController.getGlobalMainSettings().edit().putBoolean("seekSpeedHintShowed", true).apply();
        return;
    L22:
        return;
    L8:
        r0 = -1;
        goto L9
    }
}
