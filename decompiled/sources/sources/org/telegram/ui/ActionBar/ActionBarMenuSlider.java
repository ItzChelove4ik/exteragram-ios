package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FloatSeekBarAccessibilityDelegate;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.SeekBarAccessibilityDelegate;
import org.telegram.ui.Components.SpeedIconDrawable;

/* loaded from: classes6.dex */
public abstract class ActionBarMenuSlider extends FrameLayout {
    private boolean backgroundDark;
    private final Paint backgroundPaint;
    private Bitmap blurBitmap;
    private AnimatedFloat blurBitmapAlpha;
    private Matrix blurBitmapMatrix;
    private BitmapShader blurBitmapShader;
    private boolean blurIsInChat;
    private final Paint blurPaint;
    private final Paint brightenBlurPaint;
    private final Paint darkenBlurPaint;
    private boolean dragging;
    private boolean drawBlur;
    private boolean drawShadow;
    private final Paint fillPaint;
    private float fromValue;
    private float fromX;
    private AnimatedTextView.AnimatedTextDrawable leftTextDrawable;
    private int[] location;
    private Utilities.Callback2<Float, Boolean> onValueChange;
    private Runnable prepareBlur;
    private boolean preparingBlur;
    private int pseudoBlurColor1;
    private int pseudoBlurColor2;
    private LinearGradient pseudoBlurGradient;
    private Matrix pseudoBlurMatrix;
    private final Paint pseudoBlurPaint;
    private int pseudoBlurWidth;
    protected Theme.ResourcesProvider resourcesProvider;
    private AnimatedTextView.AnimatedTextDrawable rightTextDrawable;
    private float roundRadiusDp;
    private final Paint shadowPaint;
    private final Paint stopPaint;
    private float[] stops;
    private long tapStart;
    private float value;
    private ValueAnimator valueAnimator;
    private ColorFilter whiteColorFilter;

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuSlider$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatedTextView.AnimatedTextDrawable {
        final /* synthetic */ ActionBarMenuSlider this$0;

        public AnonymousClass1(ActionBarMenuSlider r1, boolean r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.graphics.drawable.Drawable
        public void invalidateSelf() {
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuSlider$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatedTextView.AnimatedTextDrawable {
        final /* synthetic */ ActionBarMenuSlider this$0;

        public AnonymousClass2(ActionBarMenuSlider r1, boolean r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.graphics.drawable.Drawable
        public void invalidateSelf() {
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuSlider$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarMenuSlider this$0;
        final /* synthetic */ float val$newValue;

        public AnonymousClass3(ActionBarMenuSlider r1, float r2) {
            this.this$0 = r1;
            this.val$newValue = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ActionBarMenuSlider.m5531$$Nest$fputvalueAnimator(this.this$0, null);
            ActionBarMenuSlider.m5530$$Nest$fputvalue(this.this$0, this.val$newValue);
            this.this$0.invalidate();
        }
    }

    public static class SpeedSlider extends ActionBarMenuSlider {
        String label;
        private final SeekBarAccessibilityDelegate seekBarAccessibilityDelegate;

        /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuSlider$SpeedSlider$1, reason: invalid class name */
        public class AnonymousClass1 extends FloatSeekBarAccessibilityDelegate {
            final /* synthetic */ SpeedSlider this$0;

            public AnonymousClass1(SpeedSlider r1, boolean r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
            public CharSequence getContentDescription(View r1) {
                return SpeedIconDrawable.formatNumber(this.this$0.getSpeed()) + "x  " + LocaleController.getString(R.string.AccDescrSpeedSlider);
            }

            @Override // org.telegram.ui.Components.FloatSeekBarAccessibilityDelegate
            public float getDelta() {
                return 0.2f;
            }

            @Override // org.telegram.ui.Components.FloatSeekBarAccessibilityDelegate
            public float getMaxValue() {
                return 3.0f;
            }

            @Override // org.telegram.ui.Components.FloatSeekBarAccessibilityDelegate
            public float getMinValue() {
                return 0.2f;
            }

            @Override // org.telegram.ui.Components.FloatSeekBarAccessibilityDelegate
            public float getProgress() {
                return this.this$0.getSpeed();
            }

            @Override // org.telegram.ui.Components.FloatSeekBarAccessibilityDelegate
            public void setProgress(float r2) {
                this.this$0.setSpeed(r2, true);
            }
        }

        public SpeedSlider(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
            this.label = null;
            setFocusable(true);
            setFocusableInTouchMode(true);
            setImportantForAccessibility(1);
            AnonymousClass1 r12 = new AnonymousClass1(this, false);
            this.seekBarAccessibilityDelegate = r12;
            setAccessibilityDelegate(r12);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuSlider
        public int getColorValue(float r4) {
            return ColorUtils.blendARGB(Theme.getColor(Theme.key_color_lightblue, this.resourcesProvider), Theme.getColor(Theme.key_color_blue, this.resourcesProvider), MathUtils.clamp((((r4 * 2.8f) + 0.2f) - 1.0f) / 1.0f, 0.0f, 1.0f));
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuSlider
        public String getLeftStringValue(float r2) {
            String r1 = this.label;
            if (r1 == null) goto L6;
            return r1;
        L6:
            return SpeedIconDrawable.formatNumber((r2 * 2.8f) + 0.2f) + "x";
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuSlider
        public String getRightStringValue(float r2) {
            if (this.label != null) goto L7;
            return null;
        L7:
            return SpeedIconDrawable.formatNumber((r2 * 2.8f) + 0.2f) + "x";
        }

        public float getSpeed() {
            return getSpeed(getValue());
        }

        public float getSpeed(float r1) {
            return (r1 * 2.8f) + 0.2f;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            this.seekBarAccessibilityDelegate.onInitializeAccessibilityNodeInfoInternal(this, r2);
        }

        @Override // android.view.View
        public boolean performAccessibilityAction(int r2, Bundle r3) {
            if (super.performAccessibilityAction(r2, r3) == false) goto L5;
            return true;
        L5:
            if (this.seekBarAccessibilityDelegate.performAccessibilityActionInternal(this, r2, r3) == true) goto L11;
            return false;
        L11:
            return true;
        }

        public void setLabel(String r1) {
            this.label = r1;
        }

        public void setSpeed(float r2, boolean r3) {
            setValue((r2 - 0.2f) / 2.8f, r3);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuSlider
        public void setStops(float[] r4) {
            int r0 = 0;
        L4:
            if (r0 >= r4.length) goto L6;
            r4[r0] = (r4[r0] - 0.2f) / 2.8f;
            r0 = r0 + 1;
            goto L4
        L6:
            super.setStops(r4);
        }
    }

    /* renamed from: $r8$lambda$LIMW76-lYfMOaHF4G4sSFiXdeRo, reason: not valid java name */
    public static /* synthetic */ void m5529$r8$lambda$LIMW76lYfMOaHF4G4sSFiXdeRo(ActionBarMenuSlider r0, ValueAnimator r1) {
        r0.lambda$setValue$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$V3Aj8omqc70zTCRRdDiBb3RVJfU(ActionBarMenuSlider r0, Bitmap r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$tQCzyPV16rNW_0j893O66oOSiG0(ActionBarMenuSlider r0) {
        r0.lambda$new$2();
    }

    /* renamed from: -$$Nest$fputvalue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5530$$Nest$fputvalue(ActionBarMenuSlider r0, float r1) {
        r0.value = r1;
    }

    /* renamed from: -$$Nest$fputvalueAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5531$$Nest$fputvalueAnimator(ActionBarMenuSlider r0, ValueAnimator r1) {
        r0.valueAnimator = r1;
    }

    public ActionBarMenuSlider(Context r23, Theme.ResourcesProvider r24) {
        super(r23);
        this.value = 0.5f;
        CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.blurBitmapAlpha = new AnimatedFloat(1.0f, this, 0, 320, r7);
        this.location = new int[2];
        this.roundRadiusDp = 0.0f;
        Paint r10 = new Paint(1);
        this.shadowPaint = r10;
        Paint r12 = new Paint(1);
        this.backgroundPaint = r12;
        this.blurPaint = new Paint(1);
        Paint r13 = new Paint(1);
        this.brightenBlurPaint = r13;
        Paint r14 = new Paint(1);
        this.darkenBlurPaint = r14;
        Paint r15 = new Paint(1);
        this.pseudoBlurPaint = r15;
        this.fillPaint = new Paint(1);
        Paint r1 = new Paint(1);
        this.stopPaint = r1;
        this.blurIsInChat = true;
        this.preparingBlur = false;
        this.prepareBlur = new ActionBarMenuSlider$$ExternalSyntheticLambda1(this);
        this.resourcesProvider = r24;
        setWillNotDraw(false);
        AnonymousClass1 r3 = new AnonymousClass1(this, false, true, true);
        this.leftTextDrawable = r3;
        r3.setCallback(this);
        this.leftTextDrawable.setTypeface(AndroidUtilities.bold());
        this.leftTextDrawable.setAnimationProperties(0.3f, 0, 165, r7);
        this.leftTextDrawable.setTextSize(AndroidUtilities.dpf2(14.0f));
        TextPaint r16 = this.leftTextDrawable.getPaint();
        Paint.Style r2 = Paint.Style.FILL_AND_STROKE;
        r16.setStyle(r2);
        this.leftTextDrawable.getPaint().setStrokeWidth(AndroidUtilities.dpf2(0.3f));
        AnimatedTextView.AnimatedTextDrawable r17 = this.leftTextDrawable;
        if (LocaleController.isRTL == false) goto L5;
        int r32 = 5;
    L6:
        r17.setGravity(r32);
        AnonymousClass2 r18 = new AnonymousClass2(this, false, true, true);
        this.rightTextDrawable = r18;
        r18.setCallback(this);
        this.rightTextDrawable.setTypeface(AndroidUtilities.bold());
        this.rightTextDrawable.setAnimationProperties(0.3f, 0, 165, r7);
        this.rightTextDrawable.setTextSize(AndroidUtilities.dpf2(14.0f));
        this.rightTextDrawable.getPaint().setStyle(r2);
        this.rightTextDrawable.getPaint().setStrokeWidth(AndroidUtilities.dpf2(0.3f));
        AnimatedTextView.AnimatedTextDrawable r19 = this.rightTextDrawable;
        if (LocaleController.isRTL == false) goto L9;
        int r22 = 3;
    L10:
        r19.setGravity(r22);
        r10.setColor(0);
        r10.setShadowLayer(AndroidUtilities.dpf2(1.33f), 0.0f, AndroidUtilities.dpf2(0.33f), 1056964608);
        ColorMatrix r110 = new ColorMatrix();
        AndroidUtilities.adjustSaturationColorMatrix(r110, -0.4f);
        AndroidUtilities.adjustBrightnessColorMatrix(r110, 0.1f);
        r15.setColorFilter(new ColorMatrixColorFilter(r110));
        r12.setColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, r24));
        if (AndroidUtilities.computePerceivedBrightness(r12.getColor()) > 0.721f) goto L13;
        boolean r11 = true;
    L14:
        this.backgroundDark = r11;
        AnimatedTextView.AnimatedTextDrawable r111 = this.leftTextDrawable;
        if (r11 == false) goto L17;
        int r4 = -1;
    L18:
        r111.setTextColor(r4);
        AnimatedTextView.AnimatedTextDrawable r112 = this.rightTextDrawable;
        if (this.backgroundDark == false) goto L21;
        int r0 = -1;
    L22:
        r112.setTextColor(r0);
        r14.setColor(Theme.multAlpha(-16777216, 0.025f));
        r13.setColor(Theme.multAlpha(-1, 0.35f));
        r1.setColor(Theme.multAlpha(-1, 0.2f));
        return;
    L21:
        r0 = -16777216;
        goto L22
    L17:
        r4 = -16777216;
        goto L18
    L13:
        r11 = false;
        goto L14
    L9:
        r22 = 5;
        goto L10
    L5:
        r32 = 3;
        goto L6
    }

    private void drawStops(Canvas r12) {
        if (this.stops == null) goto L9;
        int r0 = 0;
    L6:
        float[] r1 = this.stops;
        if (r0 >= r1.length) goto L11;
        float r13 = r1[r0];
        RectF r2 = AndroidUtilities.rectTmp;
        r12.drawRect(r2.left + (r2.width() * r13), r2.top, (r2.left + (r2.width() * r13)) + AndroidUtilities.dp(0.66f), r2.bottom, this.stopPaint);
        r0 = r0 + 1;
        goto L6
    L11:
        return;
    }

    private void drawText(Canvas r9, boolean r10) {
        AnimatedTextView.AnimatedTextDrawable r0 = this.leftTextDrawable;
        ColorFilter r1 = null;
        if (r10 == false) goto L7;
        ColorFilter r3 = this.whiteColorFilter;
        if (r3 != null) goto L8;
        r3 = new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN);
        this.whiteColorFilter = r3;
    L8:
        r0.setColorFilter(r3);
        this.leftTextDrawable.setBounds(getPaddingLeft() + AndroidUtilities.dp(20.0f), getMeasuredHeight() / 2, (getMeasuredWidth() - getPaddingRight()) - AndroidUtilities.dp(20.0f), getMeasuredHeight() / 2);
        this.leftTextDrawable.draw(r9);
        AnimatedTextView.AnimatedTextDrawable r02 = this.rightTextDrawable;
        if (r10 == false) goto L13;
        r1 = this.whiteColorFilter;
        if (r1 != null) goto L13;
        r1 = new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN);
        this.whiteColorFilter = r1;
    L13:
        r02.setColorFilter(r1);
        this.rightTextDrawable.setBounds(getPaddingLeft() + AndroidUtilities.dp(20.0f), getMeasuredHeight() / 2, (getMeasuredWidth() - getPaddingRight()) - AndroidUtilities.dp(20.0f), getMeasuredHeight() / 2);
        this.rightTextDrawable.draw(r9);
        return;
    L7:
        r3 = null;
        goto L8
    }

    private Pair<Integer, Integer> getBitmapGradientColors(Bitmap r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        float r2 = this.location[0] / AndroidUtilities.displaySize.x;
        float r1 = (r1 + getMeasuredWidth()) / AndroidUtilities.displaySize.x;
        int r22 = (int) (r2 * r5.getWidth());
        int r12 = (int) (r1 * r5.getWidth());
        int r4 = (int) ((((this.location[1] - AndroidUtilities.statusBarHeight) - ActionBar.getCurrentActionBarHeight()) / AndroidUtilities.displaySize.y) * r5.getHeight());
        if (r22 >= 0) goto L8;
        return null;
    L8:
        if (r22 >= r5.getWidth()) goto L19;
        if (r12 >= 0) goto L11;
        return null;
    L11:
        if (r12 >= r5.getWidth()) goto L21;
        if (r4 >= 0) goto L14;
        return null;
    L14:
        if (r4 < r5.getHeight()) goto L17;
        return null;
    L17:
        return new Pair(Integer.valueOf(r5.getPixel(r22, r4)), Integer.valueOf(r5.getPixel(r12, r4)));
    L21:
        return null;
    L19:
        return null;
    }

    private /* synthetic */ void lambda$new$1(Bitmap r4) {
        this.preparingBlur = false;
        this.blurBitmap = r4;
        Shader.TileMode r2 = Shader.TileMode.CLAMP;
        this.blurBitmapShader = new BitmapShader(r4, r2, r2);
        Matrix r42 = this.blurBitmapMatrix;
        if (r42 != null) goto L5;
        this.blurBitmapMatrix = new Matrix();
    L6:
        this.blurBitmapMatrix.postScale(8.0f, 8.0f);
        Matrix r43 = this.blurBitmapMatrix;
        int[] r1 = this.location;
        r43.postTranslate(-r1[0], -r1[1]);
        this.blurBitmapShader.setLocalMatrix(this.blurBitmapMatrix);
        this.blurPaint.setShader(this.blurBitmapShader);
        ColorMatrix r44 = new ColorMatrix();
        AndroidUtilities.adjustSaturationColorMatrix(r44, -0.2f);
        this.blurPaint.setColorFilter(new ColorMatrixColorFilter(r44));
        invalidate();
        return;
    L5:
        r42.reset();
        goto L6
    }

    private /* synthetic */ void lambda$new$2() {
        this.preparingBlur = true;
        AndroidUtilities.makeGlobalBlurBitmap(new ActionBarMenuSlider$$ExternalSyntheticLambda2(this), 8.0f);
    }

    private /* synthetic */ void lambda$setValue$0(ValueAnimator r1) {
        this.value = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private void updatePseudoBlurColors() {
        if (this.blurIsInChat == false) goto L18;
        Drawable r0 = Theme.getCachedWallpaper();
        if ((r0 instanceof ColorDrawable) == false) goto L8;
        int r02 = ((ColorDrawable) r0).getColor();
    L21:
        int r1 = r02;
    L23:
        if (this.pseudoBlurGradient != null) goto L25;
    L30:
        this.pseudoBlurColor1 = r02;
        this.pseudoBlurColor2 = r1;
        LinearGradient r3 = new LinearGradient(0.0f, 0.0f, 1.0f, 0.0f, new int[]{r02, r1}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        this.pseudoBlurGradient = r3;
        this.pseudoBlurPaint.setShader(r3);
        return;
    L25:
        if (this.pseudoBlurColor1 != r02) goto L30;
        if (this.pseudoBlurColor2 != r1) goto L30;
        return;
    L8:
        if ((r0 instanceof MotionBackgroundDrawable) == false) goto L11;
        Bitmap r03 = ((MotionBackgroundDrawable) r0).getBitmap();
    L14:
        Pair<Integer, Integer> r04 = getBitmapGradientColors(r03);
        if (r04 == null) goto L17;
        int r12 = ((Integer) r04.first).intValue();
        r1 = ((Integer) r04.second).intValue();
        r02 = r12;
        goto L23
    L17:
        r02 = Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider), 0.25f);
        goto L21
    L11:
        if ((r0 instanceof BitmapDrawable) == false) goto L13;
        r03 = ((BitmapDrawable) r0).getBitmap();
        goto L14
    L13:
        r03 = null;
        goto L14
    L18:
        r02 = Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider);
        if (Theme.isCurrentThemeDark() == true) goto L21;
        r02 = Theme.blendOver(r02, Theme.multAlpha(-16777216, 0.18f));
        goto L21
    }

    private void updateValue(float r2, boolean r3) {
        setValue(r2, false);
        Utilities.Callback2<Float, Boolean> r22 = this.onValueChange;
        if (r22 == null) goto L6;
        r22.run(Float.valueOf(this.value), Boolean.valueOf(r3));
        return;
    }

    public abstract int getColorValue(float r1);

    public abstract String getLeftStringValue(float r1);

    public abstract String getRightStringValue(float r1);

    public float getValue() {
        return this.value;
    }

    public void invalidateBlur(boolean r2) {
        this.blurIsInChat = r2;
        this.blurPaint.setShader(null);
        this.blurBitmapShader = null;
        Bitmap r22 = this.blurBitmap;
        if (r22 == null) goto L6;
        r22.recycle();
        this.blurBitmap = null;
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        super.onDraw(r9);
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        if (this.drawShadow == false) goto L6;
        r9.drawRoundRect(r0, AndroidUtilities.dp(this.roundRadiusDp), AndroidUtilities.dp(this.roundRadiusDp), this.shadowPaint);
    L6:
        if (this.drawBlur == false) goto L31;
        AnimatedFloat r1 = this.blurBitmapAlpha;
        if (this.blurBitmap == null) goto L10;
        float r3 = 1.0f;
    L11:
        float r12 = r1.set(r3);
        if (r12 >= 1.0f) goto L24;
        if (this.pseudoBlurMatrix != null) goto L16;
    L17:
        Matrix r32 = this.pseudoBlurMatrix;
        if (r32 != null) goto L20;
        this.pseudoBlurMatrix = new Matrix();
    L21:
        Matrix r33 = this.pseudoBlurMatrix;
        int r6 = (int) r0.width();
        this.pseudoBlurWidth = r6;
        r33.postScale(r6, 1.0f);
        this.pseudoBlurGradient.setLocalMatrix(this.pseudoBlurMatrix);
    L22:
        this.pseudoBlurPaint.setAlpha((int) ((1.0f - r12) * 255.0f));
        r9.drawRoundRect(r0, AndroidUtilities.dp(this.roundRadiusDp), AndroidUtilities.dp(this.roundRadiusDp), this.pseudoBlurPaint);
        goto L24
    L20:
        r32.reset();
        goto L21
    L16:
        if (this.pseudoBlurWidth == ((int) r0.width())) goto L22;
    L24:
        if (this.blurBitmap != null) goto L26;
    L30:
        r9.drawRoundRect(r0, AndroidUtilities.dp(this.roundRadiusDp), AndroidUtilities.dp(this.roundRadiusDp), this.brightenBlurPaint);
        r9.drawRoundRect(r0, AndroidUtilities.dp(this.roundRadiusDp), AndroidUtilities.dp(this.roundRadiusDp), this.darkenBlurPaint);
        this.fillPaint.setColor(-1);
    L32:
        drawStops(r9);
        if (this.backgroundDark == true) goto L36;
        drawText(r9, false);
    L36:
        if (this.value >= 1.0f) goto L38;
        r9.save();
        r9.clipRect(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + (((getWidth() - getPaddingLeft()) - getPaddingRight()) * this.value), getHeight() - getPaddingBottom());
    L38:
        r9.drawRoundRect(r0, AndroidUtilities.dp(this.roundRadiusDp), AndroidUtilities.dp(this.roundRadiusDp), this.fillPaint);
        drawStops(r9);
        if (this.backgroundDark == true) goto L42;
        drawText(r9, true);
    L42:
        if (this.value >= 1.0f) goto L45;
        r9.restore();
    L45:
        if (this.backgroundDark == false) goto L48;
        drawText(r9, false);
        return;
    L48:
        return;
    L26:
        if (this.value >= 1.0f) goto L30;
        if (r12 <= 0.0f) goto L30;
        this.blurPaint.setAlpha((int) (r12 * 255.0f));
        r9.drawRoundRect(r0, AndroidUtilities.dp(this.roundRadiusDp), AndroidUtilities.dp(this.roundRadiusDp), this.blurPaint);
        goto L30
    L10:
        r3 = 0.0f;
        goto L11
    L31:
        r9.drawRoundRect(r0, AndroidUtilities.dp(this.roundRadiusDp), AndroidUtilities.dp(this.roundRadiusDp), this.backgroundPaint);
        goto L32
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        getLocationOnScreen(this.location);
        Matrix r12 = this.blurBitmapMatrix;
        if (r12 == null) goto L7;
        r12.reset();
        this.blurBitmapMatrix.postScale(8.0f, 8.0f);
        Matrix r13 = this.blurBitmapMatrix;
        int[] r22 = this.location;
        r13.postTranslate(-r22[0], -r22[1]);
        BitmapShader r14 = this.blurBitmapShader;
        if (r14 == null) goto L7;
        r14.setLocalMatrix(this.blurBitmapMatrix);
        invalidate();
    L7:
        updatePseudoBlurColors();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        if (this.drawShadow == false) goto L5;
        r3 = View.MeasureSpec.makeMeasureSpec((View.MeasureSpec.getSize(r3) + getPaddingRight()) + getPaddingLeft(), TLObject.FLAG_30);
    L5:
        super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec((AndroidUtilities.dp(44.0f) + getPaddingTop()) + getPaddingBottom(), TLObject.FLAG_30));
        if (SharedConfig.getDevicePerformanceClass() >= 2) goto L8;
    L10:
        boolean r32 = false;
    L12:
        if (this.drawBlur == true) goto L14;
        return;
    L14:
        if (this.blurBitmap == null) goto L16;
        return;
    L16:
        if (this.preparingBlur == true) goto L22;
        if (r32 == false) goto L23;
        this.prepareBlur.run();
        return;
    L23:
        return;
    L22:
        return;
    L8:
        if (LiteMode.isEnabled(256) == false) goto L10;
        r32 = true;
        goto L12
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        float r0 = r8.getX() - getPaddingLeft();
        int r82 = r8.getAction();
        if (r82 != 0) goto L6;
        this.dragging = true;
        this.fromX = r0;
        this.fromValue = this.value;
        this.tapStart = System.currentTimeMillis();
    L33:
        return true;
    L6:
        if (r82 == 2) goto L8;
        if (r82 != 1) goto L33;
    L8:
        int r2 = 0;
        if (r82 != 1) goto L24;
        this.dragging = false;
        if ((System.currentTimeMillis() - this.tapStart) >= ViewConfiguration.getTapTimeout()) goto L24;
        float r02 = (r0 - getPaddingLeft()) / ((getWidth() - getPaddingLeft()) - getPaddingRight());
        if (this.stops == null) goto L20;
    L14:
        float[] r83 = this.stops;
        if (r2 >= r83.length) goto L20;
        if (Math.abs(r02 - r83[r2]) < 0.1f) goto L18;
        r2 = r2 + 1;
        goto L14
    L18:
        r02 = this.stops[r2];
    L20:
        Utilities.Callback2<Float, Boolean> r7 = this.onValueChange;
        if (r7 == null) goto L23;
        r7.run(Float.valueOf(r02), Boolean.TRUE);
    L23:
        return true;
    L24:
        float r84 = this.fromValue + ((r0 - this.fromX) / Math.max(1, (getWidth() - getPaddingLeft()) - getPaddingRight()));
        if (this.stops == null) goto L32;
    L26:
        float[] r03 = this.stops;
        if (r2 >= r03.length) goto L32;
        if (Math.abs(r84 - r03[r2]) < 0.05f) goto L30;
        r2 = r2 + 1;
        goto L26
    L30:
        r84 = this.stops[r2];
    L32:
        updateValue(r84, !this.dragging);
        goto L33
    }

    @Override // android.view.View
    public void setBackgroundColor(int r4) {
        this.backgroundPaint.setColor(r4);
        if (AndroidUtilities.computePerceivedBrightness(this.backgroundPaint.getColor()) > 0.721f) goto L5;
        boolean r42 = true;
    L6:
        this.backgroundDark = r42;
        AnimatedTextView.AnimatedTextDrawable r0 = this.leftTextDrawable;
        int r1 = -16777216;
        if (r42 == false) goto L9;
        int r43 = -1;
    L10:
        r0.setTextColor(r43);
        AnimatedTextView.AnimatedTextDrawable r44 = this.rightTextDrawable;
        if (this.backgroundDark == false) goto L13;
        r1 = -1;
    L13:
        r44.setTextColor(r1);
        return;
    L9:
        r43 = -16777216;
        goto L10
    L5:
        r42 = false;
        goto L6
    }

    public void setDrawBlur(boolean r1) {
        this.drawBlur = r1;
        invalidate();
    }

    public void setDrawShadow(boolean r1) {
        this.drawShadow = r1;
        if (r1 == false) goto L5;
        int r12 = AndroidUtilities.dp(8.0f);
    L6:
        setPadding(r12, r12, r12, r12);
        invalidate();
        return;
    L5:
        r12 = 0;
        goto L6
    }

    public void setOnValueChange(Utilities.Callback2<Float, Boolean> r1) {
        this.onValueChange = r1;
    }

    public void setRoundRadiusDp(float r1) {
        this.roundRadiusDp = r1;
        invalidate();
    }

    public void setStops(float[] r1) {
        this.stops = r1;
    }

    public void setTextColor(int r2) {
        this.leftTextDrawable.setTextColor(r2);
        this.rightTextDrawable.setTextColor(r2);
    }

    public void setValue(float r4, boolean r5) {
        ValueAnimator r0 = this.valueAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.valueAnimator = null;
    L5:
        float r42 = MathUtils.clamp(r4, 0.0f, 1.0f);
        if (r5 == true) goto L8;
        this.value = r42;
        invalidate();
    L9:
        String r52 = getLeftStringValue(r42);
        if (r52 != null) goto L12;
    L14:
        String r53 = getRightStringValue(r42);
        if (r53 != null) goto L17;
    L19:
        this.fillPaint.setColor(getColorValue(r42));
        return;
    L17:
        if (TextUtils.equals(this.rightTextDrawable.getText(), r53) == true) goto L19;
        this.rightTextDrawable.cancelAnimation();
        this.rightTextDrawable.setText(r53, true);
        goto L19
    L12:
        if (TextUtils.equals(this.leftTextDrawable.getText(), r52) == true) goto L14;
        this.leftTextDrawable.cancelAnimation();
        this.leftTextDrawable.setText(r52, true);
        goto L14
    L8:
        ValueAnimator r54 = ValueAnimator.ofFloat(new float[]{this.value, r42});
        this.valueAnimator = r54;
        r54.addUpdateListener(new ActionBarMenuSlider$$ExternalSyntheticLambda0(this));
        this.valueAnimator.addListener(new AnonymousClass3(this, r42));
        this.valueAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.valueAnimator.setDuration(220);
        this.valueAnimator.start();
        goto L9
    }
}
