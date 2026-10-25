package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public class SliderView extends View {
    private final Path clipPath;
    private final int currentType;
    public int fixWidth;
    private int h;
    private float lastTouchX;
    private float maxVolume;
    private float minVolume;
    private Utilities.Callback<Float> onValueChange;
    private long pressTime;
    private float r;
    private final Paint speaker1Paint;
    private final Path speaker1Path;
    private final Paint speaker2Paint;
    private final Path speaker2Path;
    private final Paint speakerWave1Paint;
    private final Path speakerWave1Path;
    private final Paint speakerWave2Paint;
    private final Path speakerWave2Path;
    private final AnimatedTextView.AnimatedTextDrawable text;
    private final AnimatedTextView.AnimatedTextDrawable text2;
    private final TextPaint textPaint;
    private float value;
    private AnimatedFloat valueAnimated;
    private boolean valueIsAnimated;
    private int w;
    private final AnimatedFloat wave1Alpha;
    private final AnimatedFloat wave2Alpha;
    private final Paint whitePaint;

    public SliderView(Context r18, int r19) {
        super(r18);
        this.minVolume = 0.0f;
        this.maxVolume = 1.0f;
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.valueAnimated = new AnimatedFloat(this, 0, 320, r6);
        Paint r15 = new Paint(1);
        this.whitePaint = r15;
        Paint r9 = new Paint(1);
        this.speaker1Paint = r9;
        Paint r10 = new Paint(1);
        this.speaker2Paint = r10;
        Paint r11 = new Paint(1);
        this.speakerWave1Paint = r11;
        Paint r12 = new Paint(1);
        this.speakerWave2Paint = r12;
        AnimatedTextView.AnimatedTextDrawable r13 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.text = r13;
        this.clipPath = new Path();
        this.speaker1Path = new Path();
        this.speaker2Path = new Path();
        this.speakerWave1Path = new Path();
        this.speakerWave2Path = new Path();
        this.wave1Alpha = new AnimatedFloat(this, 0, 350, r6);
        this.wave2Alpha = new AnimatedFloat(this, 0, 350, r6);
        this.textPaint = new TextPaint(1);
        this.currentType = r19;
        r13.setTypeface(AndroidUtilities.bold());
        r13.setAnimationProperties(0.3f, 0, 40, r6);
        r13.setCallback(this);
        r13.setTextColor(-1);
        r13.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        if (r19 != 0) goto L5;
        r13.setTextSize(AndroidUtilities.dp(15.0f));
        this.text2 = null;
        r9.setColor(-1);
        r10.setColor(-1);
        r11.setColor(-1);
        r12.setColor(-1);
        r12.setStyle(Paint.Style.STROKE);
        r12.setStrokeCap(Paint.Cap.ROUND);
        AnimatedTextView.AnimatedTextDrawable r2 = r13;
        int r0 = -1;
    L14:
        r2.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        r15.setColor(r0);
        r15.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        return;
    L5:
        r13.setTextSize(AndroidUtilities.dp(14.0f));
        r13.setGravity(5);
        r2 = r13;
        AnimatedTextView.AnimatedTextDrawable r8 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.text2 = r8;
        r8.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        r8.setTextSize(AndroidUtilities.dp(14.0f));
        r8.setTypeface(AndroidUtilities.bold());
        r0 = -1;
        r8.setAnimationProperties(0.3f, 0, 40, r6);
        r8.setCallback(this);
        r8.setTextColor(-1);
        if (r19 != 1) goto L9;
        r8.setText(LocaleController.getString(R.string.FlashWarmth));
        goto L14
    L9:
        if (r19 != 2) goto L12;
        r8.setText(LocaleController.getString(R.string.FlashIntensity));
        goto L14
    L12:
        if (r19 != 3) goto L14;
        r8.setText(LocaleController.getString(R.string.WallpaperDimming));
        goto L14
    }

    private void updateText(float r10) {
        String r0 = Math.round(100.0f * r10) + "%";
        if (TextUtils.equals(this.text.getText(), r0) == true) goto L11;
        this.text.cancelAnimation();
        AnimatedTextView.AnimatedTextDrawable r2 = this.text;
        if (this.valueIsAnimated == false) goto L8;
        long r3 = 320;
    L9:
        r2.setAnimationProperties(0.3f, 0, r3, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.text.setText(r0);
        goto L11
    L8:
        r3 = 40;
    L11:
        if (this.currentType != 1) goto L13;
        this.whitePaint.setColor(FlashViews.getColor(r10));
    L13:
        invalidate();
    }

    public void animateValueTo(float r4) {
        this.valueIsAnimated = true;
        float r0 = this.minVolume;
        this.value = (r4 - r0) / (this.maxVolume - r0);
        updateText(r4);
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r15) {
        super.dispatchDraw(r15);
        r15.save();
        RectF r2 = AndroidUtilities.rectTmp;
        r2.set(0.0f, 0.0f, this.w, this.h);
        this.clipPath.rewind();
        Path r3 = this.clipPath;
        float r4 = this.r;
        r3.addRoundRect(r2, r4, r4, Path.Direction.CW);
        r15.clipPath(this.clipPath);
        if (this.valueIsAnimated == false) goto L6;
        float r22 = this.valueAnimated.set(this.value);
    L5:
        float r9 = r22;
        r15.saveLayerAlpha(0.0f, 0.0f, this.w, this.h, 255, 31);
        if (this.currentType != 0) goto L10;
        this.text.setBounds(AndroidUtilities.dp(42.0f), -AndroidUtilities.dp(1.0f), this.w, this.h - AndroidUtilities.dp(1.0f));
        this.text.draw(r15);
    L12:
        if (this.currentType != 0) goto L25;
        r15.drawPath(this.speaker1Path, this.speaker1Paint);
        r15.drawPath(this.speaker2Path, this.speaker2Paint);
        float r23 = this.maxVolume;
        float r42 = this.minVolume;
        if ((r23 - r42) == 0.0f) goto L16;
        float r43 = r42 + (this.value * (r23 - r42));
    L17:
        AnimatedFloat r24 = this.wave1Alpha;
        double r44 = r43;
        boolean r7 = false;
        if (r44 <= 0.25d) goto L20;
        boolean r6 = true;
    L21:
        float r25 = r24.set(r6);
        r15.save();
        r15.translate((-AndroidUtilities.dpf2(0.33f)) * (1.0f - r25), 0.0f);
        this.speakerWave1Paint.setAlpha((int) (r25 * 255.0f));
        r15.drawPath(this.speakerWave1Path, this.speakerWave1Paint);
        r15.restore();
        AnimatedFloat r26 = this.wave2Alpha;
        if (r44 <= 0.5d) goto L24;
        r7 = true;
    L24:
        float r27 = r26.set(r7);
        r15.save();
        r15.translate((-AndroidUtilities.dpf2(0.66f)) * (1.0f - r27), 0.0f);
        this.speakerWave2Paint.setAlpha((int) (r27 * 255.0f));
        r15.drawPath(this.speakerWave2Path, this.speakerWave2Paint);
        r15.restore();
        goto L25
    L20:
        r6 = false;
        goto L21
    L16:
        r43 = 0.0f;
    L25:
        r15.save();
        r15.drawRect(0.0f, 0.0f, this.w * r9, this.h, this.whitePaint);
        r15.restore();
        r15.restore();
        r15.restore();
        return;
    L10:
        this.text2.setBounds(AndroidUtilities.dp(12.33f), -AndroidUtilities.dp(1.0f), (this.w - ((int) this.text.getCurrentWidth())) - AndroidUtilities.dp(6.0f), this.h - AndroidUtilities.dp(1.0f));
        this.text2.draw(r15);
        this.text.setBounds(this.w - AndroidUtilities.dp(111.0f), -AndroidUtilities.dp(1.0f), this.w - AndroidUtilities.dp(11.0f), this.h - AndroidUtilities.dp(1.0f));
        this.text.draw(r15);
        goto L12
    L6:
        r22 = this.value;
        goto L5
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent r12) {
        boolean r1 = false;
        if (this.w > 0) goto L5;
        return false;
    L5:
        float r0 = r12.getX();
        if (r12.getAction() != 0) goto L9;
        this.pressTime = System.currentTimeMillis();
        this.valueIsAnimated = false;
    L43:
        this.lastTouchX = r0;
        return true;
    L9:
        if (r12.getAction() != 2) goto L11;
    L12:
        float r2 = this.maxVolume;
        float r4 = this.minVolume;
        float r6 = 0.0f;
        if ((r2 - r4) == 0.0f) goto L15;
        float r42 = r4 + (this.value * (r2 - r4));
    L17:
        if (r12.getAction() == 1) goto L19;
    L21:
        this.value = Utilities.clamp(this.value + ((r0 - this.lastTouchX) / this.w), 1.0f, 0.0f);
        this.valueIsAnimated = false;
        r1 = true;
    L22:
        float r122 = this.maxVolume;
        float r22 = this.minVolume;
        if ((r122 - r22) == 0.0f) goto L25;
        r6 = r22 + (this.value * (r122 - r22));
    L25:
        if (r1 == true) goto L27;
    L40:
        updateText(r6);
        Utilities.Callback<Float> r123 = this.onValueChange;
        if (r123 == null) goto L43;
        r123.run(Float.valueOf(r6));
        goto L43
    L27:
        if (r6 > r22) goto L31;
        if (r42 <= r6) goto L31;
    L46:
        performHapticFeedback(3, 1);     // Catch: Exception -> L45
    L31:
        if (r6 < r122) goto L38;
        if (r42 < r6) goto L46;
    L38:
        if (Math.floor(r42 * 5.0f) == Math.floor(5.0f * r6)) goto L40;
        AndroidUtilities.vibrateCursor(this);
        goto L40
    L19:
        if ((System.currentTimeMillis() - this.pressTime) >= ViewConfiguration.getTapTimeout()) goto L21;
        this.valueAnimated.set(this.value, true);
        this.value = r0 / this.w;
        this.valueIsAnimated = true;
        goto L22
    L15:
        r42 = 0.0f;
        goto L17
    L11:
        if (r12.getAction() != 1) goto L43;
        goto L12
    }

    @Override // android.view.View
    public void onMeasure(int r9, int r10) {
        this.r = AndroidUtilities.dp(12.0f);
        this.textPaint.setTextSize(AndroidUtilities.dp(16.0f));
        this.text.setTextSize(AndroidUtilities.dp(15.0f));
        int r102 = this.fixWidth;
        if (r102 <= 0) goto L6;
        this.w = r102;
        this.h = AndroidUtilities.dp(48.0f);
    L9:
        setMeasuredDimension(this.w, this.h);
        if (this.currentType != 0) goto L13;
        float r92 = AndroidUtilities.dp(25.0f);
        float r103 = this.h / 2.0f;
        this.speaker1Paint.setPathEffect(new CornerPathEffect(AndroidUtilities.dpf2(1.33f)));
        this.speaker1Path.rewind();
        this.speaker1Path.moveTo(r92 - AndroidUtilities.dpf2(8.66f), r103 - AndroidUtilities.dpf2(2.9f));
        this.speaker1Path.lineTo(r92 - AndroidUtilities.dpf2(3.0f), r103 - AndroidUtilities.dpf2(2.9f));
        this.speaker1Path.lineTo(r92 - AndroidUtilities.dpf2(3.0f), AndroidUtilities.dpf2(2.9f) + r103);
        this.speaker1Path.lineTo(r92 - AndroidUtilities.dpf2(8.66f), AndroidUtilities.dpf2(2.9f) + r103);
        this.speaker1Path.close();
        this.speaker2Paint.setPathEffect(new CornerPathEffect(AndroidUtilities.dpf2(2.66f)));
        this.speaker2Path.rewind();
        this.speaker2Path.moveTo(r92 - AndroidUtilities.dpf2(7.5f), r103);
        this.speaker2Path.lineTo(r92, r103 - AndroidUtilities.dpf2(7.33f));
        this.speaker2Path.lineTo(r92, AndroidUtilities.dpf2(7.33f) + r103);
        this.speaker2Path.close();
        this.speakerWave1Path.rewind();
        RectF r1 = AndroidUtilities.rectTmp;
        r1.set((r92 - AndroidUtilities.dpf2(0.33f)) - AndroidUtilities.dp(4.33f), r103 - AndroidUtilities.dp(4.33f), (r92 - AndroidUtilities.dpf2(0.33f)) + AndroidUtilities.dp(4.33f), AndroidUtilities.dp(4.33f) + r103);
        this.speakerWave1Path.arcTo(r1, -60.0f, 120.0f);
        this.speakerWave1Path.close();
        this.speakerWave2Paint.setStyle(Paint.Style.STROKE);
        this.speakerWave2Paint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.speakerWave2Path.rewind();
        r1.set((r92 - AndroidUtilities.dpf2(0.33f)) - AndroidUtilities.dp(8.0f), r103 - AndroidUtilities.dp(8.0f), (r92 - AndroidUtilities.dpf2(0.33f)) + AndroidUtilities.dp(8.0f), r103 + AndroidUtilities.dp(8.0f));
        this.speakerWave2Path.arcTo(r1, -70.0f, 140.0f);
        return;
    L13:
        return;
    L6:
        if (this.currentType != 0) goto L8;
        this.w = (int) Math.min(this.textPaint.measureText(LocaleController.getString(R.string.StoryAudioRemove)) + AndroidUtilities.dp(88.0f), View.MeasureSpec.getSize(r9));
        this.h = AndroidUtilities.dp(48.0f);
        goto L9
    L8:
        this.w = AndroidUtilities.dp(190.0f);
        this.h = AndroidUtilities.dp(44.0f);
        goto L9
    }

    public SliderView setMinMax(float r1, float r2) {
        this.minVolume = r1;
        this.maxVolume = r2;
        return this;
    }

    public SliderView setOnValueChange(Utilities.Callback<Float> r1) {
        this.onValueChange = r1;
        return this;
    }

    public SliderView setValue(float r4) {
        float r0 = this.minVolume;
        float r1 = (r4 - r0) / (this.maxVolume - r0);
        this.value = r1;
        this.valueAnimated.set(r1, true);
        updateText(r4);
        return this;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.text) goto L5;
        return true;
    L5:
        if (r2 != this.text2) goto L7;
        return true;
    L7:
        if (super.verifyDrawable(r2) == true) goto L14;
        return false;
    L14:
        return true;
    }
}
