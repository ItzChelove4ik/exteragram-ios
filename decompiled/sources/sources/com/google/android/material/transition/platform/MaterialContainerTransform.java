package com.google.android.material.transition.platform;

import android.R;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.ArcMotion;
import android.transition.PathMotion;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.core.util.Preconditions;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes5.dex */
public final class MaterialContainerTransform extends Transition {
    private static final ProgressThresholdsGroup DEFAULT_ENTER_THRESHOLDS = null;
    private static final ProgressThresholdsGroup DEFAULT_ENTER_THRESHOLDS_ARC = null;
    private static final ProgressThresholdsGroup DEFAULT_RETURN_THRESHOLDS = null;
    private static final ProgressThresholdsGroup DEFAULT_RETURN_THRESHOLDS_ARC = null;
    private static final float ELEVATION_NOT_SET = -1.0f;
    public static final int FADE_MODE_CROSS = 2;
    public static final int FADE_MODE_IN = 0;
    public static final int FADE_MODE_OUT = 1;
    public static final int FADE_MODE_THROUGH = 3;
    public static final int FIT_MODE_AUTO = 0;
    public static final int FIT_MODE_HEIGHT = 2;
    public static final int FIT_MODE_WIDTH = 1;
    private static final String PROP_BOUNDS = "materialContainerTransition:bounds";
    private static final String PROP_SHAPE_APPEARANCE = "materialContainerTransition:shapeAppearance";
    private static final String TAG = "MaterialContainerTransform";
    public static final int TRANSITION_DIRECTION_AUTO = 0;
    public static final int TRANSITION_DIRECTION_ENTER = 1;
    public static final int TRANSITION_DIRECTION_RETURN = 2;
    private static final String[] TRANSITION_PROPS = null;
    private boolean appliedThemeValues;
    private int containerColor;
    private boolean drawDebugEnabled;
    private int drawingViewId;
    private boolean elevationShadowEnabled;
    private int endContainerColor;
    private float endElevation;
    private ShapeAppearanceModel endShapeAppearanceModel;
    private View endView;
    private int endViewId;
    private int fadeMode;
    private ProgressThresholds fadeProgressThresholds;
    private int fitMode;
    private boolean holdAtEndEnabled;
    private boolean pathMotionCustom;
    private ProgressThresholds scaleMaskProgressThresholds;
    private ProgressThresholds scaleProgressThresholds;
    private int scrimColor;
    private ProgressThresholds shapeMaskProgressThresholds;
    private int startContainerColor;
    private float startElevation;
    private ShapeAppearanceModel startShapeAppearanceModel;
    private View startView;
    private int startViewId;
    private int transitionDirection;

    /* renamed from: com.google.android.material.transition.platform.MaterialContainerTransform$1, reason: invalid class name */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ MaterialContainerTransform this$0;
        final /* synthetic */ TransitionDrawable val$transitionDrawable;

        public AnonymousClass1(MaterialContainerTransform r1, TransitionDrawable r2) {
            this.this$0 = r1;
            this.val$transitionDrawable = r2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r1) {
            TransitionDrawable.access$200(this.val$transitionDrawable, r1.getAnimatedFraction());
        }
    }

    /* renamed from: com.google.android.material.transition.platform.MaterialContainerTransform$2, reason: invalid class name */
    public class AnonymousClass2 extends TransitionListenerAdapter {
        final /* synthetic */ MaterialContainerTransform this$0;
        final /* synthetic */ View val$drawingView;
        final /* synthetic */ View val$endView;
        final /* synthetic */ View val$startView;
        final /* synthetic */ TransitionDrawable val$transitionDrawable;

        public AnonymousClass2(MaterialContainerTransform r1, View r2, TransitionDrawable r3, View r4, View r5) {
            this.this$0 = r1;
            this.val$drawingView = r2;
            this.val$transitionDrawable = r3;
            this.val$startView = r4;
            this.val$endView = r5;
        }

        @Override // com.google.android.material.transition.platform.TransitionListenerAdapter, android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r2) {
            this.this$0.removeListener(this);
            if (MaterialContainerTransform.access$300(this.this$0) == false) goto L5;
            return;
        L5:
            this.val$startView.setAlpha(1.0f);
            this.val$endView.setAlpha(1.0f);
            this.val$drawingView.getOverlay().remove(this.val$transitionDrawable);
        }

        @Override // com.google.android.material.transition.platform.TransitionListenerAdapter, android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r2) {
            this.val$drawingView.getOverlay().add(this.val$transitionDrawable);
            this.val$startView.setAlpha(0.0f);
            this.val$endView.setAlpha(0.0f);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FadeMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FitMode {
    }

    public static class ProgressThresholds {
        private final float end;
        private final float start;

        public ProgressThresholds(float r1, float r2) {
            this.start = r1;
            this.end = r2;
        }

        public static /* synthetic */ float access$1000(ProgressThresholds r0) {
            return r0.start;
        }

        public static /* synthetic */ float access$1100(ProgressThresholds r0) {
            return r0.end;
        }

        public float getEnd() {
            return this.end;
        }

        public float getStart() {
            return this.start;
        }
    }

    public static class ProgressThresholdsGroup {
        private final ProgressThresholds fade;
        private final ProgressThresholds scale;
        private final ProgressThresholds scaleMask;
        private final ProgressThresholds shapeMask;

        private ProgressThresholdsGroup(ProgressThresholds r1, ProgressThresholds r2, ProgressThresholds r3, ProgressThresholds r4) {
            this.fade = r1;
            this.scale = r2;
            this.scaleMask = r3;
            this.shapeMask = r4;
        }

        public /* synthetic */ ProgressThresholdsGroup(ProgressThresholds r1, ProgressThresholds r2, ProgressThresholds r3, ProgressThresholds r4, AnonymousClass1 r5) {
            this(r1, r2, r3, r4);
        }

        public static /* synthetic */ ProgressThresholds access$400(ProgressThresholdsGroup r0) {
            return r0.fade;
        }

        public static /* synthetic */ ProgressThresholds access$500(ProgressThresholdsGroup r0) {
            return r0.scale;
        }

        public static /* synthetic */ ProgressThresholds access$600(ProgressThresholdsGroup r0) {
            return r0.scaleMask;
        }

        public static /* synthetic */ ProgressThresholds access$700(ProgressThresholdsGroup r0) {
            return r0.shapeMask;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TransitionDirection {
    }

    public static final class TransitionDrawable extends Drawable {
        private static final int COMPAT_SHADOW_COLOR = -7829368;
        private static final int SHADOW_COLOR = 754974720;
        private static final float SHADOW_DX_MULTIPLIER_ADJUSTMENT = 0.3f;
        private static final float SHADOW_DY_MULTIPLIER_ADJUSTMENT = 1.5f;
        private final MaterialShapeDrawable compatShadowDrawable;
        private final Paint containerPaint;
        private float currentElevation;
        private float currentElevationDy;
        private final RectF currentEndBounds;
        private final RectF currentEndBoundsMasked;
        private RectF currentMaskBounds;
        private final RectF currentStartBounds;
        private final RectF currentStartBoundsMasked;
        private final Paint debugPaint;
        private final Path debugPath;
        private final float displayHeight;
        private final float displayWidth;
        private final boolean drawDebugEnabled;
        private final boolean elevationShadowEnabled;
        private final RectF endBounds;
        private final Paint endContainerPaint;
        private final float endElevation;
        private final ShapeAppearanceModel endShapeAppearanceModel;
        private final View endView;
        private final boolean entering;
        private final FadeModeEvaluator fadeModeEvaluator;
        private FadeModeResult fadeModeResult;
        private final FitModeEvaluator fitModeEvaluator;
        private FitModeResult fitModeResult;
        private final MaskEvaluator maskEvaluator;
        private final float motionPathLength;
        private final PathMeasure motionPathMeasure;
        private final float[] motionPathPosition;
        private float progress;
        private final ProgressThresholdsGroup progressThresholds;
        private final Paint scrimPaint;
        private final Paint shadowPaint;
        private final RectF startBounds;
        private final Paint startContainerPaint;
        private final float startElevation;
        private final ShapeAppearanceModel startShapeAppearanceModel;
        private final View startView;

        /* renamed from: com.google.android.material.transition.platform.MaterialContainerTransform$TransitionDrawable$1, reason: invalid class name */
        public class AnonymousClass1 implements CanvasCompat.CanvasOperation {
            final /* synthetic */ TransitionDrawable this$0;

            public AnonymousClass1(TransitionDrawable r1) {
                this.this$0 = r1;
            }

            @Override // com.google.android.material.canvas.CanvasCompat.CanvasOperation
            public void run(Canvas r1) {
                TransitionDrawable.access$800(this.this$0).draw(r1);
            }
        }

        /* renamed from: com.google.android.material.transition.platform.MaterialContainerTransform$TransitionDrawable$2, reason: invalid class name */
        public class AnonymousClass2 implements CanvasCompat.CanvasOperation {
            final /* synthetic */ TransitionDrawable this$0;

            public AnonymousClass2(TransitionDrawable r1) {
                this.this$0 = r1;
            }

            @Override // com.google.android.material.canvas.CanvasCompat.CanvasOperation
            public void run(Canvas r1) {
                TransitionDrawable.access$900(this.this$0).draw(r1);
            }
        }

        private TransitionDrawable(PathMotion r11, View r12, RectF r13, ShapeAppearanceModel r14, float r15, View r16, RectF r17, ShapeAppearanceModel r18, float r19, int r20, int r21, int r22, int r23, boolean r24, boolean r25, FadeModeEvaluator r26, FitModeEvaluator r27, ProgressThresholdsGroup r28, boolean r29) {
            Paint r0 = new Paint();
            this.containerPaint = r0;
            Paint r1 = new Paint();
            this.startContainerPaint = r1;
            Paint r2 = new Paint();
            this.endContainerPaint = r2;
            this.shadowPaint = new Paint();
            Paint r3 = new Paint();
            this.scrimPaint = r3;
            this.maskEvaluator = new MaskEvaluator();
            this.motionPathPosition = new float[]{r13.centerX(), r13.top};
            MaterialShapeDrawable r6 = new MaterialShapeDrawable();
            this.compatShadowDrawable = r6;
            Paint r7 = new Paint();
            this.debugPaint = r7;
            this.debugPath = new Path();
            this.startView = r12;
            this.startBounds = r13;
            this.startShapeAppearanceModel = r14;
            this.startElevation = r15;
            this.endView = r16;
            this.endBounds = r17;
            this.endShapeAppearanceModel = r18;
            this.endElevation = r19;
            this.entering = r24;
            this.elevationShadowEnabled = r25;
            this.fadeModeEvaluator = r26;
            this.fitModeEvaluator = r27;
            this.progressThresholds = r28;
            this.drawDebugEnabled = r29;
            WindowManager r122 = (WindowManager) r12.getContext().getSystemService("window");
            r122.getDefaultDisplay().getMetrics(new DisplayMetrics());
            this.displayWidth = r9.widthPixels;
            this.displayHeight = r9.heightPixels;
            r0.setColor(r20);
            r1.setColor(r21);
            r2.setColor(r22);
            r6.setFillColor(ColorStateList.valueOf(0));
            r6.setShadowCompatibilityMode(2);
            r6.setShadowBitmapDrawingEnable(false);
            r6.setShadowColor(COMPAT_SHADOW_COLOR);
            RectF r02 = new RectF(r13);
            this.currentStartBounds = r02;
            this.currentStartBoundsMasked = new RectF(r02);
            RectF r110 = new RectF(r02);
            this.currentEndBounds = r110;
            this.currentEndBoundsMasked = new RectF(r110);
            PointF r03 = getMotionPathPoint(r13);
            PointF r111 = getMotionPathPoint(r17);
            PathMeasure r04 = new PathMeasure(r11.getPath(r03.x, r03.y, r111.x, r111.y), false);
            this.motionPathMeasure = r04;
            this.motionPathLength = r04.getLength();
            r3.setStyle(Paint.Style.FILL);
            r3.setShader(TransitionUtils.createColorShader(r23));
            r7.setStyle(Paint.Style.STROKE);
            r7.setStrokeWidth(10.0f);
            updateProgress(0.0f);
        }

        public /* synthetic */ TransitionDrawable(PathMotion r1, View r2, RectF r3, ShapeAppearanceModel r4, float r5, View r6, RectF r7, ShapeAppearanceModel r8, float r9, int r10, int r11, int r12, int r13, boolean r14, boolean r15, FadeModeEvaluator r16, FitModeEvaluator r17, ProgressThresholdsGroup r18, boolean r19, AnonymousClass1 r20) {
            this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19);
        }

        public static /* synthetic */ void access$200(TransitionDrawable r0, float r1) {
            r0.setProgress(r1);
        }

        public static /* synthetic */ View access$800(TransitionDrawable r0) {
            return r0.startView;
        }

        public static /* synthetic */ View access$900(TransitionDrawable r0) {
            return r0.endView;
        }

        private static float calculateElevationDxMultiplier(RectF r1, float r2) {
            return ((r1.centerX() / (r2 / 2.0f)) - 1.0f) * SHADOW_DX_MULTIPLIER_ADJUSTMENT;
        }

        private static float calculateElevationDyMultiplier(RectF r0, float r1) {
            return (r0.centerY() / r1) * SHADOW_DY_MULTIPLIER_ADJUSTMENT;
        }

        private void drawDebugCumulativePath(Canvas r3, RectF r4, Path r5, int r6) {
            PointF r42 = getMotionPathPoint(r4);
            if (this.progress != 0.0f) goto L6;
            r5.reset();
            r5.moveTo(r42.x, r42.y);
            return;
        L6:
            r5.lineTo(r42.x, r42.y);
            this.debugPaint.setColor(r6);
            r3.drawPath(r5, this.debugPaint);
        }

        private void drawDebugRect(Canvas r2, RectF r3, int r4) {
            this.debugPaint.setColor(r4);
            r2.drawRect(r3, this.debugPaint);
        }

        private void drawElevationShadow(Canvas r3) {
            r3.save();
            r3.clipPath(this.maskEvaluator.getPath(), Region.Op.DIFFERENCE);
            if (Build.VERSION.SDK_INT <= 28) goto L5;
            drawElevationShadowWithPaintShadowLayer(r3);
        L6:
            r3.restore();
            return;
        L5:
            drawElevationShadowWithMaterialShapeDrawable(r3);
            goto L6
        }

        private void drawElevationShadowWithMaterialShapeDrawable(Canvas r6) {
            MaterialShapeDrawable r0 = this.compatShadowDrawable;
            RectF r1 = this.currentMaskBounds;
            r0.setBounds((int) r1.left, (int) r1.top, (int) r1.right, (int) r1.bottom);
            this.compatShadowDrawable.setElevation(this.currentElevation);
            this.compatShadowDrawable.setShadowVerticalOffset((int) this.currentElevationDy);
            this.compatShadowDrawable.setShapeAppearanceModel(this.maskEvaluator.getCurrentShapeAppearanceModel());
            this.compatShadowDrawable.draw(r6);
        }

        private void drawElevationShadowWithPaintShadowLayer(Canvas r3) {
            ShapeAppearanceModel r0 = this.maskEvaluator.getCurrentShapeAppearanceModel();
            if (r0.isRoundRect(this.currentMaskBounds) == false) goto L6;
            float r02 = r0.getTopLeftCornerSize().getCornerSize(this.currentMaskBounds);
            r3.drawRoundRect(this.currentMaskBounds, r02, r02, this.shadowPaint);
            return;
        L6:
            r3.drawPath(this.maskEvaluator.getPath(), this.shadowPaint);
        }

        private void drawEndView(Canvas r9) {
            maybeDrawContainerColor(r9, this.endContainerPaint);
            Rect r2 = getBounds();
            RectF r0 = this.currentEndBounds;
            TransitionUtils.transform(r9, r2, r0.left, r0.top, this.fitModeResult.endScale, this.fadeModeResult.endAlpha, new AnonymousClass2(this));
        }

        private void drawStartView(Canvas r9) {
            maybeDrawContainerColor(r9, this.startContainerPaint);
            Rect r2 = getBounds();
            RectF r0 = this.currentStartBounds;
            TransitionUtils.transform(r9, r2, r0.left, r0.top, this.fitModeResult.startScale, this.fadeModeResult.startAlpha, new AnonymousClass1(this));
        }

        private static PointF getMotionPathPoint(RectF r2) {
            return new PointF(r2.centerX(), r2.top);
        }

        private void maybeDrawContainerColor(Canvas r2, Paint r3) {
            if (r3.getColor() != 0) goto L5;
            return;
        L5:
            if (r3.getAlpha() <= 0) goto L9;
            r2.drawRect(getBounds(), r3);
            return;
        }

        private void setProgress(float r2) {
            if (this.progress == r2) goto L6;
            updateProgress(r2);
            return;
        }

        private void updateProgress(float r14) {
            this.progress = r14;
            Paint r0 = this.scrimPaint;
            if (this.entering == false) goto L5;
            float r2 = TransitionUtils.lerp(0.0f, 255.0f, r14);
        L6:
            r0.setAlpha((int) r2);
            this.motionPathMeasure.getPosTan(this.motionPathLength * r14, this.motionPathPosition, null);
            float[] r02 = this.motionPathPosition;
            float r3 = r02[0];
            float r6 = r02[1];
            if (r14 <= 1.0f) goto L9;
        L12:
            if (r14 <= 1.0f) goto L14;
            float r7 = (r14 - 1.0f) / 0.00999999f;
            float r10 = 0.99f;
        L15:
            this.motionPathMeasure.getPosTan(this.motionPathLength * r10, r02, null);
            float[] r03 = this.motionPathPosition;
            r3 = r3 + ((r3 - r03[0]) * r7);
            r6 = r6 + ((r6 - r03[1]) * r7);
        L11:
            float r102 = r3;
            float r11 = r6;
            FitModeResult r04 = this.fitModeEvaluator.evaluate(r14, ((Float) Preconditions.checkNotNull(Float.valueOf(ProgressThresholds.access$1000(ProgressThresholdsGroup.access$500(this.progressThresholds))))).floatValue(), ((Float) Preconditions.checkNotNull(Float.valueOf(ProgressThresholds.access$1100(ProgressThresholdsGroup.access$500(this.progressThresholds))))).floatValue(), this.startBounds.width(), this.startBounds.height(), this.endBounds.width(), this.endBounds.height());
            this.fitModeResult = r04;
            RectF r22 = this.currentStartBounds;
            float r32 = r04.currentStartWidth;
            r22.set(r102 - (r32 / 2.0f), r11, (r32 / 2.0f) + r102, r04.currentStartHeight + r11);
            RectF r05 = this.currentEndBounds;
            FitModeResult r23 = this.fitModeResult;
            float r33 = r23.currentEndWidth;
            r05.set(r102 - (r33 / 2.0f), r11, r102 + (r33 / 2.0f), r23.currentEndHeight + r11);
            this.currentStartBoundsMasked.set(this.currentStartBounds);
            this.currentEndBoundsMasked.set(this.currentEndBounds);
            float r06 = ((Float) Preconditions.checkNotNull(Float.valueOf(ProgressThresholds.access$1000(ProgressThresholdsGroup.access$600(this.progressThresholds))))).floatValue();
            float r24 = ((Float) Preconditions.checkNotNull(Float.valueOf(ProgressThresholds.access$1100(ProgressThresholdsGroup.access$600(this.progressThresholds))))).floatValue();
            boolean r34 = this.fitModeEvaluator.shouldMaskStartBounds(this.fitModeResult);
            if (r34 == false) goto L19;
            RectF r4 = this.currentStartBoundsMasked;
        L20:
            float r07 = TransitionUtils.lerp(0.0f, 1.0f, r06, r24, r14);
            if (r34 == true) goto L24;
            r07 = 1.0f - r07;
        L24:
            this.fitModeEvaluator.applyMask(r4, r07, this.fitModeResult);
            this.currentMaskBounds = new RectF(Math.min(this.currentStartBoundsMasked.left, this.currentEndBoundsMasked.left), Math.min(this.currentStartBoundsMasked.top, this.currentEndBoundsMasked.top), Math.max(this.currentStartBoundsMasked.right, this.currentEndBoundsMasked.right), Math.max(this.currentStartBoundsMasked.bottom, this.currentEndBoundsMasked.bottom));
            this.maskEvaluator.evaluate(r14, this.startShapeAppearanceModel, this.endShapeAppearanceModel, this.currentStartBounds, this.currentStartBoundsMasked, this.currentEndBoundsMasked, ProgressThresholdsGroup.access$700(this.progressThresholds));
            this.currentElevation = TransitionUtils.lerp(this.startElevation, this.endElevation, r14);
            float r08 = calculateElevationDxMultiplier(this.currentMaskBounds, this.displayWidth);
            float r25 = calculateElevationDyMultiplier(this.currentMaskBounds, this.displayHeight);
            float r35 = this.currentElevation;
            float r26 = (int) (r25 * r35);
            this.currentElevationDy = r26;
            this.shadowPaint.setShadowLayer(r35, (int) (r08 * r35), r26, SHADOW_COLOR);
            this.fadeModeResult = this.fadeModeEvaluator.evaluate(r14, ((Float) Preconditions.checkNotNull(Float.valueOf(ProgressThresholds.access$1000(ProgressThresholdsGroup.access$400(this.progressThresholds))))).floatValue(), ((Float) Preconditions.checkNotNull(Float.valueOf(ProgressThresholds.access$1100(ProgressThresholdsGroup.access$400(this.progressThresholds))))).floatValue(), 0.35f);
            if (this.startContainerPaint.getColor() == 0) goto L28;
            this.startContainerPaint.setAlpha(this.fadeModeResult.startAlpha);
        L28:
            if (this.endContainerPaint.getColor() == 0) goto L30;
            this.endContainerPaint.setAlpha(this.fadeModeResult.endAlpha);
        L30:
            invalidateSelf();
            return;
        L19:
            r4 = this.currentEndBoundsMasked;
            goto L20
        L14:
            r10 = 0.01f;
            r7 = (r14 / 0.01f) * MaterialContainerTransform.ELEVATION_NOT_SET;
            goto L15
        L9:
            if (r14 >= 0.0f) goto L11;
        L5:
            r2 = TransitionUtils.lerp(255.0f, 0.0f, r14);
            goto L6
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r4) {
            if (this.scrimPaint.getAlpha() <= 0) goto L6;
            r4.drawRect(getBounds(), this.scrimPaint);
        L6:
            if (this.drawDebugEnabled == false) goto L8;
            int r0 = r4.save();
        L10:
            if (this.elevationShadowEnabled == true) goto L12;
        L14:
            this.maskEvaluator.clip(r4);
            maybeDrawContainerColor(r4, this.containerPaint);
            if (this.fadeModeResult.endOnTop == false) goto L17;
            drawStartView(r4);
            drawEndView(r4);
        L19:
            if (this.drawDebugEnabled == false) goto L22;
            r4.restoreToCount(r0);
            drawDebugCumulativePath(r4, this.currentStartBounds, this.debugPath, -65281);
            drawDebugRect(r4, this.currentStartBoundsMasked, -256);
            drawDebugRect(r4, this.currentStartBounds, -16711936);
            drawDebugRect(r4, this.currentEndBoundsMasked, -16711681);
            drawDebugRect(r4, this.currentEndBounds, -16776961);
            return;
        L22:
            return;
        L17:
            drawEndView(r4);
            drawStartView(r4);
            goto L19
        L12:
            if (this.currentElevation <= 0.0f) goto L14;
            drawElevationShadow(r4);
            goto L14
        L8:
            r0 = -1;
            goto L10
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            throw new UnsupportedOperationException("Setting alpha on is not supported");
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            throw new UnsupportedOperationException("Setting a color filter is not supported");
        }
    }

    static {
        TRANSITION_PROPS = new String[]{PROP_BOUNDS, PROP_SHAPE_APPEARANCE};
        DEFAULT_ENTER_THRESHOLDS = new ProgressThresholdsGroup(new ProgressThresholds(0.0f, 0.25f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.75f), null);
        DEFAULT_RETURN_THRESHOLDS = new ProgressThresholdsGroup(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.3f, 0.9f), null);
        DEFAULT_ENTER_THRESHOLDS_ARC = new ProgressThresholdsGroup(new ProgressThresholds(0.1f, 0.4f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 0.9f), null);
        DEFAULT_RETURN_THRESHOLDS_ARC = new ProgressThresholdsGroup(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.2f, 0.9f), null);
    }

    public MaterialContainerTransform() {
        boolean r0 = false;
        this.drawDebugEnabled = false;
        this.holdAtEndEnabled = false;
        this.pathMotionCustom = false;
        this.appliedThemeValues = false;
        this.drawingViewId = R.id.content;
        this.startViewId = -1;
        this.endViewId = -1;
        this.containerColor = 0;
        this.startContainerColor = 0;
        this.endContainerColor = 0;
        this.scrimColor = 1375731712;
        this.transitionDirection = 0;
        this.fadeMode = 0;
        this.fitMode = 0;
        if (Build.VERSION.SDK_INT < 28) goto L5;
        r0 = true;
    L5:
        this.elevationShadowEnabled = r0;
        this.startElevation = ELEVATION_NOT_SET;
        this.endElevation = ELEVATION_NOT_SET;
    }

    public MaterialContainerTransform(Context r5, boolean r6) {
        boolean r0 = false;
        this.drawDebugEnabled = false;
        this.holdAtEndEnabled = false;
        this.pathMotionCustom = false;
        this.appliedThemeValues = false;
        this.drawingViewId = R.id.content;
        this.startViewId = -1;
        this.endViewId = -1;
        this.containerColor = 0;
        this.startContainerColor = 0;
        this.endContainerColor = 0;
        this.scrimColor = 1375731712;
        this.transitionDirection = 0;
        this.fadeMode = 0;
        this.fitMode = 0;
        if (Build.VERSION.SDK_INT < 28) goto L5;
        r0 = true;
    L5:
        this.elevationShadowEnabled = r0;
        this.startElevation = ELEVATION_NOT_SET;
        this.endElevation = ELEVATION_NOT_SET;
        maybeApplyThemeValues(r5, r6);
        this.appliedThemeValues = true;
    }

    public static /* synthetic */ boolean access$300(MaterialContainerTransform r0) {
        return r0.holdAtEndEnabled;
    }

    private ProgressThresholdsGroup buildThresholdsGroup(boolean r3) {
        PathMotion r0 = getPathMotion();
        if ((r0 instanceof ArcMotion) == true) goto L10;
        if ((r0 instanceof MaterialArcMotion) == true) goto L10;
        return getThresholdsOrDefault(r3, DEFAULT_ENTER_THRESHOLDS, DEFAULT_RETURN_THRESHOLDS);
    L10:
        return getThresholdsOrDefault(r3, DEFAULT_ENTER_THRESHOLDS_ARC, DEFAULT_RETURN_THRESHOLDS_ARC);
    }

    private static RectF calculateDrawableBounds(View r0, View r1, float r2, float r3) {
        if (r1 == null) goto L6;
        RectF r02 = TransitionUtils.getLocationInWindow(r1);
        r02.offset(r2, r3);
        return r02;
    L6:
        return new RectF(0.0f, 0.0f, r0.getWidth(), r0.getHeight());
    }

    private static ShapeAppearanceModel captureShapeAppearance(View r0, RectF r1, ShapeAppearanceModel r2) {
        return TransitionUtils.convertToRelativeCornerSizes(getShapeAppearance(r0, r2), r1);
    }

    private static void captureValues(TransitionValues r2, View r3, int r4, ShapeAppearanceModel r5) {
        if (r4 == (-1)) goto L5;
        r2.view = TransitionUtils.findDescendantOrAncestorById(r2.view, r4);
    L10:
        View r32 = r2.view;
        if (r32.isLaidOut() == true) goto L19;
        if (r32.getWidth() != 0) goto L19;
        if (r32.getHeight() != 0) goto L19;
        return;
    L19:
        if (r32.getParent() != null) goto L21;
        RectF r42 = TransitionUtils.getRelativeBounds(r32);
    L22:
        r2.values.put(PROP_BOUNDS, r42);
        r2.values.put(PROP_SHAPE_APPEARANCE, captureShapeAppearance(r32, r42, r5));
        return;
    L21:
        r42 = TransitionUtils.getLocationInWindow(r32);
        goto L22
    L5:
        if (r3 == null) goto L8;
        r2.view = r3;
        goto L10
    L8:
        if ((r2.view.getTag(com.google.android.material.R.id.mtrl_motion_snapshot_view) instanceof View) == false) goto L10;
        View r33 = (View) r2.view.getTag(com.google.android.material.R.id.mtrl_motion_snapshot_view);
        r2.view.setTag(com.google.android.material.R.id.mtrl_motion_snapshot_view, null);
        r2.view = r33;
        goto L10
    }

    private static float getElevationOrDefault(float r1, View r2) {
        if (r1 == ELEVATION_NOT_SET) goto L6;
        return r1;
    L6:
        return r2.getElevation();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static ShapeAppearanceModel getShapeAppearance(View r2, ShapeAppearanceModel r3) {
        if (r3 == null) goto L5;
        return r3;
    L5:
        if ((r2.getTag(com.google.android.material.R.id.mtrl_motion_snapshot_view) instanceof ShapeAppearanceModel) == true) goto L7;
        Context r32 = r2.getContext();
        int r0 = getTransitionShapeAppearanceResId(r32);
        if (r0 == (-1)) goto L13;
        return ShapeAppearanceModel.builder(r32, r0, 0).build();
    L13:
        if ((r2 instanceof Shapeable) == false) goto L17;
        return ((Shapeable) r2).getShapeAppearanceModel();
    L17:
        return ShapeAppearanceModel.builder().build();
    L7:
        return (ShapeAppearanceModel) r2.getTag(com.google.android.material.R.id.mtrl_motion_snapshot_view);
    }

    private ProgressThresholdsGroup getThresholdsOrDefault(boolean r7, ProgressThresholdsGroup r8, ProgressThresholdsGroup r9) {
        if (r7 == true) goto L6;
        r8 = r9;
    L6:
        return new ProgressThresholdsGroup((ProgressThresholds) TransitionUtils.defaultIfNull(this.fadeProgressThresholds, ProgressThresholdsGroup.access$400(r8)), (ProgressThresholds) TransitionUtils.defaultIfNull(this.scaleProgressThresholds, ProgressThresholdsGroup.access$500(r8)), (ProgressThresholds) TransitionUtils.defaultIfNull(this.scaleMaskProgressThresholds, ProgressThresholdsGroup.access$600(r8)), (ProgressThresholds) TransitionUtils.defaultIfNull(this.shapeMaskProgressThresholds, ProgressThresholdsGroup.access$700(r8)), null);
    }

    private static int getTransitionShapeAppearanceResId(Context r2) {
        TypedArray r22 = r2.obtainStyledAttributes(new int[]{com.google.android.material.R.attr.transitionShapeAppearance});
        int r0 = r22.getResourceId(0, -1);
        r22.recycle();
        return r0;
    }

    private boolean isEntering(RectF r4, RectF r5) {
        int r0 = this.transitionDirection;
        if (r0 == 0) goto L12;
        if (r0 != 1) goto L6;
        return true;
    L6:
        if (r0 != 2) goto L8;
        return false;
    L8:
        IntegerType$$ExternalSyntheticBUOutline0.m("Invalid transition direction: ", this.transitionDirection);
        return false;
    L12:
        if (TransitionUtils.calculateArea(r5) <= TransitionUtils.calculateArea(r4)) goto L14;
        return true;
    L14:
        return false;
    }

    private void maybeApplyThemeValues(Context r3, boolean r4) {
        TransitionUtils.maybeApplyThemeInterpolator(this, r3, com.google.android.material.R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        if (r4 == false) goto L5;
        int r42 = com.google.android.material.R.attr.motionDurationLong2;
    L6:
        TransitionUtils.maybeApplyThemeDuration(this, r3, r42);
        if (this.pathMotionCustom == true) goto L10;
        TransitionUtils.maybeApplyThemePath(this, r3, com.google.android.material.R.attr.motionPath);
        return;
    L10:
        return;
    L5:
        r42 = com.google.android.material.R.attr.motionDurationMedium4;
        goto L6
    }

    @Override // android.transition.Transition
    public void captureEndValues(TransitionValues r3) {
        captureValues(r3, this.endView, this.endViewId, this.endShapeAppearanceModel);
    }

    @Override // android.transition.Transition
    public void captureStartValues(TransitionValues r3) {
        captureValues(r3, this.startView, this.startViewId, this.startShapeAppearanceModel);
    }

    @Override // android.transition.Transition
    public Animator createAnimator(ViewGroup r28, TransitionValues r29, TransitionValues r30) {
        View r3 = null;
        if (r29 == null) goto L30;
        if (r30 == null) goto L30;
        RectF r9 = (RectF) r29.values.get(PROP_BOUNDS);
        ShapeAppearanceModel r10 = (ShapeAppearanceModel) r29.values.get(PROP_SHAPE_APPEARANCE);
        if (r9 == null) goto L29;
        if (r10 == null) goto L29;
        RectF r13 = (RectF) r30.values.get(PROP_BOUNDS);
        ShapeAppearanceModel r14 = (ShapeAppearanceModel) r30.values.get(PROP_SHAPE_APPEARANCE);
        if (r13 == null) goto L27;
        if (r14 == null) goto L27;
        View r4 = r29.view;
        View r12 = r30.view;
        if (r12.getParent() == null) goto L17;
        View r0 = r12;
    L19:
        if (this.drawingViewId != r0.getId()) goto L21;
        View r2 = (View) r0.getParent();
        r3 = r0;
    L22:
        RectF r5 = TransitionUtils.getLocationInWindow(r2);
        float r6 = -r5.left;
        float r52 = -r5.top;
        RectF r32 = calculateDrawableBounds(r2, r3, r6, r52);
        r9.offset(r6, r52);
        r13.offset(r6, r52);
        boolean r53 = isEntering(r9, r13);
        if (this.appliedThemeValues == true) goto L25;
        maybeApplyThemeValues(r0.getContext(), r53);
    L25:
        TransitionDrawable r62 = new TransitionDrawable(getPathMotion(), r4, r9, r10, getElevationOrDefault(this.startElevation, r4), r12, r13, r14, getElevationOrDefault(this.endElevation, r12), this.containerColor, this.startContainerColor, this.endContainerColor, this.scrimColor, r53, this.elevationShadowEnabled, FadeModeEvaluators.get(this.fadeMode, r53), FitModeEvaluators.get(this.fitMode, r53, r9, r13), buildThresholdsGroup(r53), this.drawDebugEnabled, null);
        r62.setBounds(Math.round(r32.left), Math.round(r32.top), Math.round(r32.right), Math.round(r32.bottom));
        ValueAnimator r7 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r7.addUpdateListener(new AnonymousClass1(this, r62));
        addListener(new AnonymousClass2(this, r2, r62, r4, r12));
        return r7;
    L21:
        r2 = TransitionUtils.findAncestorById(r0, this.drawingViewId);
        goto L22
    L17:
        r0 = r4;
    L27:
        Log.w(TAG, "Skipping due to null end bounds. Ensure end view is laid out and measured.");
        return null;
    L29:
        Log.w(TAG, "Skipping due to null start bounds. Ensure start view is laid out and measured.");
    L30:
        return null;
    }

    public int getContainerColor() {
        return this.containerColor;
    }

    public int getDrawingViewId() {
        return this.drawingViewId;
    }

    public int getEndContainerColor() {
        return this.endContainerColor;
    }

    public float getEndElevation() {
        return this.endElevation;
    }

    public ShapeAppearanceModel getEndShapeAppearanceModel() {
        return this.endShapeAppearanceModel;
    }

    public View getEndView() {
        return this.endView;
    }

    public int getEndViewId() {
        return this.endViewId;
    }

    public int getFadeMode() {
        return this.fadeMode;
    }

    public ProgressThresholds getFadeProgressThresholds() {
        return this.fadeProgressThresholds;
    }

    public int getFitMode() {
        return this.fitMode;
    }

    public ProgressThresholds getScaleMaskProgressThresholds() {
        return this.scaleMaskProgressThresholds;
    }

    public ProgressThresholds getScaleProgressThresholds() {
        return this.scaleProgressThresholds;
    }

    public int getScrimColor() {
        return this.scrimColor;
    }

    public ProgressThresholds getShapeMaskProgressThresholds() {
        return this.shapeMaskProgressThresholds;
    }

    public int getStartContainerColor() {
        return this.startContainerColor;
    }

    public float getStartElevation() {
        return this.startElevation;
    }

    public ShapeAppearanceModel getStartShapeAppearanceModel() {
        return this.startShapeAppearanceModel;
    }

    public View getStartView() {
        return this.startView;
    }

    public int getStartViewId() {
        return this.startViewId;
    }

    public int getTransitionDirection() {
        return this.transitionDirection;
    }

    @Override // android.transition.Transition
    public String[] getTransitionProperties() {
        return TRANSITION_PROPS;
    }

    public boolean isDrawDebugEnabled() {
        return this.drawDebugEnabled;
    }

    public boolean isElevationShadowEnabled() {
        return this.elevationShadowEnabled;
    }

    public boolean isHoldAtEndEnabled() {
        return this.holdAtEndEnabled;
    }

    public void setAllContainerColors(int r1) {
        this.containerColor = r1;
        this.startContainerColor = r1;
        this.endContainerColor = r1;
    }

    public void setContainerColor(int r1) {
        this.containerColor = r1;
    }

    public void setDrawDebugEnabled(boolean r1) {
        this.drawDebugEnabled = r1;
    }

    public void setDrawingViewId(int r1) {
        this.drawingViewId = r1;
    }

    public void setElevationShadowEnabled(boolean r1) {
        this.elevationShadowEnabled = r1;
    }

    public void setEndContainerColor(int r1) {
        this.endContainerColor = r1;
    }

    public void setEndElevation(float r1) {
        this.endElevation = r1;
    }

    public void setEndShapeAppearanceModel(ShapeAppearanceModel r1) {
        this.endShapeAppearanceModel = r1;
    }

    public void setEndView(View r1) {
        this.endView = r1;
    }

    public void setEndViewId(int r1) {
        this.endViewId = r1;
    }

    public void setFadeMode(int r1) {
        this.fadeMode = r1;
    }

    public void setFadeProgressThresholds(ProgressThresholds r1) {
        this.fadeProgressThresholds = r1;
    }

    public void setFitMode(int r1) {
        this.fitMode = r1;
    }

    public void setHoldAtEndEnabled(boolean r1) {
        this.holdAtEndEnabled = r1;
    }

    @Override // android.transition.Transition
    public void setPathMotion(PathMotion r1) {
        super.setPathMotion(r1);
        this.pathMotionCustom = true;
    }

    public void setScaleMaskProgressThresholds(ProgressThresholds r1) {
        this.scaleMaskProgressThresholds = r1;
    }

    public void setScaleProgressThresholds(ProgressThresholds r1) {
        this.scaleProgressThresholds = r1;
    }

    public void setScrimColor(int r1) {
        this.scrimColor = r1;
    }

    public void setShapeMaskProgressThresholds(ProgressThresholds r1) {
        this.shapeMaskProgressThresholds = r1;
    }

    public void setStartContainerColor(int r1) {
        this.startContainerColor = r1;
    }

    public void setStartElevation(float r1) {
        this.startElevation = r1;
    }

    public void setStartShapeAppearanceModel(ShapeAppearanceModel r1) {
        this.startShapeAppearanceModel = r1;
    }

    public void setStartView(View r1) {
        this.startView = r1;
    }

    public void setStartViewId(int r1) {
        this.startViewId = r1;
    }

    public void setTransitionDirection(int r1) {
        this.transitionDirection = r1;
    }
}
