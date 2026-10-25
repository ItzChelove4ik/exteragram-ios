package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.camera.camera2.pipe.CameraColorSpace$$ExternalSyntheticApiModelOutline10;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class FlashViews {
    public static final int[] COLORS = null;
    private ValueAnimator animator;
    public final View backgroundView;
    private int color;
    private final Context context;
    public final View foregroundView;
    private RadialGradient gradient;
    private final Matrix gradientMatrix;
    public float intensity;
    private float invert;
    private final ArrayList<Invertable> invertableViews;
    private int lastColor;
    private int lastHeight;
    private float lastInvert;
    private int lastWidth;
    private final Paint paint;
    public float warmth;
    private final WindowManager windowManager;
    private final View windowView;
    private final WindowManager.LayoutParams windowViewParams;

    /* renamed from: org.telegram.ui.Stories.recorder.FlashViews$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ FlashViews this$0;

        public AnonymousClass1(FlashViews r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r2) {
            FlashViews.m19997$$Nest$fgetgradientMatrix(this.this$0).reset();
            this.this$0.drawGradient(r2, true);
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            FlashViews.m19999$$Nest$minvalidateGradient(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.FlashViews$2, reason: invalid class name */
    public class AnonymousClass2 extends View {
        final /* synthetic */ FlashViews this$0;

        public AnonymousClass2(FlashViews r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r6) {
            FlashViews.m19997$$Nest$fgetgradientMatrix(this.this$0).reset();
            FlashViews.m19997$$Nest$fgetgradientMatrix(this.this$0).postTranslate(-getX(), (-getY()) + AndroidUtilities.statusBarHeight);
            FlashViews.m19997$$Nest$fgetgradientMatrix(this.this$0).postScale(1.0f / getScaleX(), 1.0f / getScaleY(), getPivotX(), getPivotY());
            this.this$0.drawGradient(r6, false);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.FlashViews$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ FlashViews this$0;
        final /* synthetic */ float val$value;
        final /* synthetic */ Runnable val$whenDone;

        public AnonymousClass3(FlashViews r1, float r2, Runnable r3) {
            this.this$0 = r1;
            this.val$value = r2;
            this.val$whenDone = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            FlashViews.m19998$$Nest$fputinvert(this.this$0, this.val$value);
            FlashViews.m20000$$Nest$mupdate(this.this$0);
            Runnable r1 = this.val$whenDone;
            if (r1 == null) goto L6;
            r1.run();
            return;
        }
    }

    public static class ImageViewInvertable extends ImageView implements Invertable {
        public ImageViewInvertable(Context r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Stories.recorder.FlashViews.Invertable
        public void setInvert(float r4) {
            setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(-1, -16777216, r4), PorterDuff.Mode.MULTIPLY));
        }
    }

    public interface Invertable {
        void invalidate();

        void setInvert(float r1);
    }

    public static /* synthetic */ void $r8$lambda$Gzmda5k0NyFJg9q6ldVrssZJSHk(FlashViews r0, Runnable r1) {
        r0.lambda$flash$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$K61xvjLMipvt01xLip3kQaM6ndk(FlashViews r0, Utilities.Callback r1) {
        r0.lambda$flash$2(r1);
    }

    /* renamed from: $r8$lambda$bF-6VAQbsqZGWnhkV8qF3TJGbfA, reason: not valid java name */
    public static /* synthetic */ void m19995$r8$lambda$bF6VAQbsqZGWnhkV8qF3TJGbfA(FlashViews r0, ValueAnimator r1) {
        r0.lambda$flashTo$4(r1);
    }

    /* renamed from: $r8$lambda$dVHDB3ypDURRQjO5Fi-zk6s7-xc, reason: not valid java name */
    public static /* synthetic */ void m19996$r8$lambda$dVHDB3ypDURRQjO5Fizk6s7xc(FlashViews r0, Runnable r1) {
        r0.lambda$flash$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$esjPUyY6JY2d6Qy_bjJdZhF6JqU(FlashViews r0, Utilities.Callback r1) {
        r0.lambda$flash$3(r1);
    }

    /* renamed from: -$$Nest$fgetgradientMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m19997$$Nest$fgetgradientMatrix(FlashViews r0) {
        return r0.gradientMatrix;
    }

    /* renamed from: -$$Nest$fputinvert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19998$$Nest$fputinvert(FlashViews r0, float r1) {
        r0.invert = r1;
    }

    /* renamed from: -$$Nest$minvalidateGradient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19999$$Nest$minvalidateGradient(FlashViews r0) {
        r0.invalidateGradient();
    }

    /* renamed from: -$$Nest$mupdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20000$$Nest$mupdate(FlashViews r0) {
        r0.update();
    }

    static {
        COLORS = new int[]{-1, -70004, -7544833};
    }

    public FlashViews(Context r3, WindowManager r4, View r5, WindowManager.LayoutParams r6) {
        this.invertableViews = new ArrayList();
        this.invert = 0.0f;
        this.warmth = 0.75f;
        this.intensity = 1.0f;
        this.gradientMatrix = new Matrix();
        Paint r0 = new Paint(1);
        this.paint = r0;
        this.context = r3;
        this.windowManager = r4;
        this.windowView = r5;
        this.windowViewParams = r6;
        this.backgroundView = new AnonymousClass1(this, r3);
        this.foregroundView = new AnonymousClass2(this, r3);
        r0.setAlpha(0);
    }

    private void flashTo(float r4, long r5, Runnable r7) {
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L6;
        r0.cancel();
        this.animator = null;
    L6:
        if (r5 > 0) goto L11;
        this.invert = r4;
        update();
        if (r7 == null) goto L13;
        r7.run();
        return;
    L13:
        return;
    L11:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.invert, r4});
        this.animator = r02;
        r02.addUpdateListener(new FlashViews$$ExternalSyntheticLambda2(this));
        this.animator.addListener(new AnonymousClass3(this, r4, r7));
        this.animator.setDuration(r5);
        this.animator.setInterpolator(CubicBezierInterpolator.EASE_IN);
        this.animator.start();
    }

    public static int getColor(float r5) {
        if (r5 >= 0.5f) goto L7;
        return ColorUtils.blendARGB(-7544833, -1, Utilities.clamp(r5 / 0.5f, 1.0f, 0.0f));
    L7:
        return ColorUtils.blendARGB(-1, -70004, Utilities.clamp((r5 - 0.5f) / 0.5f, 1.0f, 0.0f));
    }

    private float intensityValue() {
        return this.intensity;
    }

    private void invalidate() {
        this.backgroundView.invalidate();
        this.foregroundView.invalidate();
    }

    private void invalidateGradient() {
        if (this.lastColor == this.color) goto L5;
    L10:
        this.lastColor = this.color;
        this.lastWidth = this.backgroundView.getMeasuredWidth();
        int r1 = this.backgroundView.getMeasuredHeight();
        this.lastHeight = r1;
        this.lastInvert = this.invert;
        if (this.lastWidth <= 0) goto L20;
        if (r1 > 0) goto L14;
        return;
    L14:
        if (Build.VERSION.SDK_INT < 29) goto L16;
        FlashViews$$ExternalSyntheticApiModelOutline1.m();
        int r12 = this.lastWidth;
        int r2 = this.lastHeight;
        ColorSpace.Named r10 = CameraColorSpace$$ExternalSyntheticApiModelOutline10.m();
        this.gradient = FlashViews$$ExternalSyntheticApiModelOutline0.m(r12 * 0.5f, r2 * 0.4f, ((Math.min(r12, r2) / 2.0f) * 1.35f) * (2.0f - this.invert), new long[]{Color.valueOf(Color.red(this.color) / 255.0f, Color.green(this.color) / 255.0f, Color.blue(this.color) / 255.0f, 0.0f, ColorSpace.get(r10)).pack(), Color.valueOf(Color.red(this.color) / 255.0f, Color.green(this.color) / 255.0f, Color.blue(this.color) / 255.0f, 1.0f, ColorSpace.get(r10)).pack()}, new float[]{AndroidUtilities.lerp(0.9f, 0.22f, this.invert), 1.0f}, Shader.TileMode.CLAMP);
    L17:
        this.paint.setShader(this.gradient);
        invalidate();
        return;
    L16:
        this.gradient = new RadialGradient(this.lastWidth * 0.5f, this.lastHeight * 0.4f, ((Math.min(r1, r10) / 2.0f) * 1.35f) * (2.0f - this.invert), new int[]{ColorUtils.setAlphaComponent(this.color, 0), this.color}, new float[]{AndroidUtilities.lerp(0.9f, 0.22f, this.invert), 1.0f}, Shader.TileMode.CLAMP);
        goto L17
    L20:
        return;
    L5:
        if (this.lastWidth != this.backgroundView.getMeasuredWidth()) goto L10;
        if (this.lastHeight != this.backgroundView.getMeasuredHeight()) goto L10;
        if (Math.abs(this.lastInvert - this.invert) > 0.005f) goto L10;
    }

    private /* synthetic */ void lambda$flash$0(Runnable r4) {
        flashTo(0.0f, 240, r4);
    }

    private /* synthetic */ void lambda$flash$1(Runnable r2) {
        setScreenBrightness(-1.0f);
        AndroidUtilities.runOnUIThread(new FlashViews$$ExternalSyntheticLambda6(this, r2), 80);
    }

    private /* synthetic */ void lambda$flash$2(Utilities.Callback r2) {
        r2.run(new FlashViews$$ExternalSyntheticLambda5(this));
    }

    private /* synthetic */ void lambda$flash$3(Utilities.Callback r2) {
        AndroidUtilities.runOnUIThread(new FlashViews$$ExternalSyntheticLambda4(this, r2), 320);
    }

    private /* synthetic */ void lambda$flashTo$4(ValueAnimator r1) {
        this.invert = ((Float) r1.getAnimatedValue()).floatValue();
        update();
    }

    private void setScreenBrightness(float r3) {
        View r0 = this.windowView;
        if (r0 == null) goto L10;
        WindowManager.LayoutParams r1 = this.windowViewParams;
        if (r1 == null) goto L10;
        r1.screenBrightness = r3;
        WindowManager r2 = this.windowManager;
        if (r2 == null) goto L22;
        r2.updateViewLayout(r0, r1);
        return;
    L22:
        return;
    L10:
        Activity r22 = AndroidUtilities.findActivity(this.context);
        if (r22 != null) goto L13;
        r22 = LaunchActivity.instance;
    L13:
        if (r22 != null) goto L15;
        return;
    L15:
        if (r22.isFinishing() == true) goto L24;
        Window r23 = r22.getWindow();
        if (r23 == null) goto L25;
        WindowManager.LayoutParams r02 = r23.getAttributes();
        r02.screenBrightness = r3;
        r23.setAttributes(r02);
        return;
    L25:
        return;
    }

    private void update() {
        int r0 = 0;
    L4:
        if (r0 >= this.invertableViews.size()) goto L6;
        this.invertableViews.get(r0).setInvert(this.invert);
        this.invertableViews.get(r0).invalidate();
        r0 = r0 + 1;
        goto L4
    L6:
        this.paint.setAlpha((int) ((intensityValue() * 255.0f) * this.invert));
        this.backgroundView.invalidate();
        this.foregroundView.invalidate();
    }

    public void add(Invertable r2) {
        r2.setInvert(this.invert);
        this.invertableViews.add(r2);
    }

    public void drawGradient(Canvas r7, boolean r8) {
        if (this.gradient == null) goto L10;
        invalidateGradient();
        this.gradient.setLocalMatrix(this.gradientMatrix);
        if (r8 == false) goto L8;
        r7.drawRect(0.0f, 0.0f, this.lastWidth, this.lastHeight, this.paint);
        return;
    L8:
        RectF r72 = AndroidUtilities.rectTmp;
        r72.set(0.0f, 0.0f, this.foregroundView.getMeasuredWidth(), this.foregroundView.getMeasuredHeight());
        r7.drawRoundRect(r72, AndroidUtilities.dp(12.0f) - 2, AndroidUtilities.dp(12.0f) - 2, this.paint);
        return;
    }

    public void flash(Utilities.Callback<Utilities.Callback<Runnable>> r4) {
        setScreenBrightness(intensityValue());
        flashTo(1.0f, 320, new FlashViews$$ExternalSyntheticLambda3(this, r4));
    }

    public void flashIn(Runnable r4) {
        setScreenBrightness(intensityValue());
        flashTo(1.0f, 320, r4);
    }

    public void flashOut() {
        setScreenBrightness(-1.0f);
        flashTo(0.0f, 240, null);
    }

    public void previewEnd() {
        flashTo(0.0f, 240, null);
    }

    public void previewStart() {
        flashTo(0.85f, 240, null);
    }

    public void remove(Invertable r1) {
        this.invertableViews.remove(r1);
    }

    public void setIntensity(float r1) {
        this.intensity = r1;
        update();
    }

    public void setWarmth(float r1) {
        this.warmth = r1;
        this.color = getColor(r1);
        invalidateGradient();
    }
}
