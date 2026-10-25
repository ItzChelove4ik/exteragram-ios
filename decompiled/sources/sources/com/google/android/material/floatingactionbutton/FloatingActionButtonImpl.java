package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ImageMatrixProperty;
import com.google.android.material.animation.MatrixEvaluator;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
class FloatingActionButtonImpl {
    static final int ANIM_STATE_HIDING = 1;
    static final int ANIM_STATE_NONE = 0;
    static final int ANIM_STATE_SHOWING = 2;
    static final long ELEVATION_ANIM_DELAY = 100;
    static final long ELEVATION_ANIM_DURATION = 100;
    static final TimeInterpolator ELEVATION_ANIM_INTERPOLATOR = null;
    static final int[] EMPTY_STATE_SET = null;
    static final int[] ENABLED_STATE_SET = null;
    static final int[] FOCUSED_ENABLED_STATE_SET = null;
    private static final int HIDE_ANIM_DURATION_ATTR = 0;
    private static final int HIDE_ANIM_EASING_ATTR = 0;
    private static final float HIDE_ICON_SCALE = 0.4f;
    private static final float HIDE_OPACITY = 0.0f;
    private static final float HIDE_SCALE = 0.4f;
    static final int[] HOVERED_ENABLED_STATE_SET = null;
    static final int[] HOVERED_FOCUSED_ENABLED_STATE_SET = null;
    static final int[] PRESSED_ENABLED_STATE_SET = null;
    static final float SHADOW_MULTIPLIER = 1.5f;
    private static final int SHOW_ANIM_DURATION_ATTR = 0;
    private static final int SHOW_ANIM_EASING_ATTR = 0;
    private static final float SHOW_ICON_SCALE = 1.0f;
    private static final float SHOW_OPACITY = 1.0f;
    private static final float SHOW_SCALE = 1.0f;
    private static final float SPEC_HIDE_ICON_SCALE = 0.0f;
    private static final float SPEC_HIDE_SCALE = 0.0f;
    private int animState;
    BorderDrawable borderDrawable;
    Drawable contentBackground;
    private Animator currentAnimator;
    float elevation;
    boolean ensureMinTouchTargetSize;
    private ArrayList<Animator.AnimatorListener> hideListeners;
    private MotionSpec hideMotionSpec;
    float hoveredFocusedTranslationZ;
    private float imageMatrixScale;
    private int maxImageSize;
    int minTouchTargetSize;
    private ViewTreeObserver.OnPreDrawListener preDrawListener;
    float pressedTranslationZ;
    Drawable rippleDrawable;
    boolean shadowPaddingEnabled;
    final ShadowViewDelegate shadowViewDelegate;
    ShapeAppearanceModel shapeAppearance;
    MaterialShapeDrawable shapeDrawable;
    private ArrayList<Animator.AnimatorListener> showListeners;
    private MotionSpec showMotionSpec;
    private StateListAnimator stateListAnimator;
    private final Matrix tmpMatrix;
    private final Rect tmpRect;
    private final RectF tmpRectF1;
    private final RectF tmpRectF2;
    private ArrayList<InternalTransformationCallback> transformationCallbacks;
    final FloatingActionButton view;

    /* renamed from: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        private boolean cancelled;
        final /* synthetic */ FloatingActionButtonImpl this$0;
        final /* synthetic */ boolean val$fromUser;
        final /* synthetic */ InternalVisibilityChangedListener val$listener;

        public AnonymousClass1(FloatingActionButtonImpl r1, boolean r2, InternalVisibilityChangedListener r3) {
            this.this$0 = r1;
            this.val$fromUser = r2;
            this.val$listener = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.cancelled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            FloatingActionButtonImpl.access$002(this.this$0, 0);
            FloatingActionButtonImpl.access$102(this.this$0, null);
            if (this.cancelled == true) goto L12;
            FloatingActionButton r32 = this.this$0.view;
            boolean r0 = this.val$fromUser;
            if (r0 == false) goto L7;
            int r1 = 8;
        L8:
            r32.internalSetVisibility(r1, r0);
            InternalVisibilityChangedListener r2 = this.val$listener;
            if (r2 == null) goto L13;
            r2.onHidden();
            return;
        L13:
            return;
        L7:
            r1 = 4;
            goto L8
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r4) {
            this.this$0.view.internalSetVisibility(0, this.val$fromUser);
            FloatingActionButtonImpl.access$002(this.this$0, 1);
            FloatingActionButtonImpl.access$102(this.this$0, r4);
            this.cancelled = false;
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ FloatingActionButtonImpl this$0;
        final /* synthetic */ boolean val$fromUser;
        final /* synthetic */ InternalVisibilityChangedListener val$listener;

        public AnonymousClass2(FloatingActionButtonImpl r1, boolean r2, InternalVisibilityChangedListener r3) {
            this.this$0 = r1;
            this.val$fromUser = r2;
            this.val$listener = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            FloatingActionButtonImpl.access$002(this.this$0, 0);
            FloatingActionButtonImpl.access$102(this.this$0, null);
            InternalVisibilityChangedListener r1 = this.val$listener;
            if (r1 == null) goto L6;
            r1.onShown();
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r4) {
            this.this$0.view.internalSetVisibility(0, this.val$fromUser);
            FloatingActionButtonImpl.access$002(this.this$0, 2);
            FloatingActionButtonImpl.access$102(this.this$0, r4);
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$3, reason: invalid class name */
    public class AnonymousClass3 extends MatrixEvaluator {
        final /* synthetic */ FloatingActionButtonImpl this$0;

        public AnonymousClass3(FloatingActionButtonImpl r1) {
            this.this$0 = r1;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.android.material.animation.MatrixEvaluator
        public Matrix evaluate(float r2, Matrix r3, Matrix r4) {
            FloatingActionButtonImpl.access$202(this.this$0, r2);
            return super.evaluate2(r2, r3, r4);
        }

        @Override // com.google.android.material.animation.MatrixEvaluator, android.animation.TypeEvaluator
        public /* bridge */ /* synthetic */ Matrix evaluate(float r1, Matrix r2, Matrix r3) {
            return evaluate(r1, r2, r3);
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$4, reason: invalid class name */
    public class AnonymousClass4 implements TypeEvaluator<Float> {
        final FloatEvaluator floatEvaluator;
        final /* synthetic */ FloatingActionButtonImpl this$0;

        public AnonymousClass4(FloatingActionButtonImpl r1) {
            this.this$0 = r1;
            this.floatEvaluator = new FloatEvaluator();
        }

        /* renamed from: evaluate, reason: avoid collision after fix types in other method */
        public Float evaluate2(float r1, Float r2, Float r3) {
            float r0 = this.floatEvaluator.evaluate(r1, r2, r3).floatValue();
            if (r0 >= 0.1f) goto L6;
            r0 = FloatingActionButtonImpl.HIDE_OPACITY;
        L6:
            return Float.valueOf(r0);
        }

        @Override // android.animation.TypeEvaluator
        public /* bridge */ /* synthetic */ Float evaluate(float r1, Float r2, Float r3) {
            return evaluate2(r1, r2, r3);
        }
    }

    public static class AlwaysStatefulMaterialShapeDrawable extends MaterialShapeDrawable {
        public AlwaysStatefulMaterialShapeDrawable(ShapeAppearanceModel r1) {
            super(r1);
        }

        @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    public interface InternalTransformationCallback {
        void onScaleChanged();

        void onTranslationChanged();
    }

    public interface InternalVisibilityChangedListener {
        void onHidden();

        void onShown();
    }

    public static /* synthetic */ void $r8$lambda$c0Si7M_kRre8X8QgWjMpZhSwJ2U(FloatingActionButtonImpl r3, float r4, float r5, float r6, float r7, float r8, float r9, float r10, Matrix r11, ValueAnimator r12) {
        r3.getClass();
        float r122 = ((Float) r12.getAnimatedValue()).floatValue();
        r3.view.setAlpha(AnimationUtils.lerp(r4, r5, HIDE_OPACITY, 0.2f, r122));
        r3.view.setScaleX(AnimationUtils.lerp(r6, r7, r122));
        r3.view.setScaleY(AnimationUtils.lerp(r8, r7, r122));
        r3.imageMatrixScale = AnimationUtils.lerp(r9, r10, r122);
        r3.calculateImageMatrixFromScale(AnimationUtils.lerp(r9, r10, r122), r11);
        r3.view.setImageMatrix(r11);
    }

    static {
        ELEVATION_ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
        SHOW_ANIM_DURATION_ATTR = R.attr.motionDurationLong2;
        SHOW_ANIM_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;
        HIDE_ANIM_DURATION_ATTR = R.attr.motionDurationMedium1;
        HIDE_ANIM_EASING_ATTR = R.attr.motionEasingEmphasizedAccelerateInterpolator;
        PRESSED_ENABLED_STATE_SET = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        HOVERED_FOCUSED_ENABLED_STATE_SET = new int[]{android.R.attr.state_hovered, android.R.attr.state_focused, android.R.attr.state_enabled};
        FOCUSED_ENABLED_STATE_SET = new int[]{android.R.attr.state_focused, android.R.attr.state_enabled};
        HOVERED_ENABLED_STATE_SET = new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled};
        ENABLED_STATE_SET = new int[]{android.R.attr.state_enabled};
        EMPTY_STATE_SET = new int[0];
    }

    public FloatingActionButtonImpl(FloatingActionButton r2, ShadowViewDelegate r3) {
        this.shadowPaddingEnabled = true;
        this.imageMatrixScale = 1.0f;
        this.animState = 0;
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpMatrix = new Matrix();
        this.view = r2;
        this.shadowViewDelegate = r3;
    }

    public static /* synthetic */ int access$002(FloatingActionButtonImpl r0, int r1) {
        r0.animState = r1;
        return r1;
    }

    public static /* synthetic */ Animator access$102(FloatingActionButtonImpl r0, Animator r1) {
        r0.currentAnimator = r1;
        return r1;
    }

    public static /* synthetic */ float access$202(FloatingActionButtonImpl r0, float r1) {
        r0.imageMatrixScale = r1;
        return r1;
    }

    private void calculateImageMatrixFromScale(float r6, Matrix r7) {
        r7.reset();
        if (this.view.getDrawable() != null) goto L5;
        return;
    L5:
        if (this.maxImageSize == 0) goto L9;
        RectF r1 = this.tmpRectF1;
        RectF r2 = this.tmpRectF2;
        r1.set(HIDE_OPACITY, HIDE_OPACITY, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        int r0 = this.maxImageSize;
        r2.set(HIDE_OPACITY, HIDE_OPACITY, r0, r0);
        r7.setRectToRect(r1, r2, Matrix.ScaleToFit.CENTER);
        int r5 = this.maxImageSize;
        r7.postScale(r6, r6, r5 / 2.0f, r5 / 2.0f);
        return;
    }

    private AnimatorSet createAnimator(MotionSpec r7, float r8, float r9, float r10) {
        ArrayList r0 = new ArrayList();
        ObjectAnimator r82 = ObjectAnimator.ofFloat(this.view, View.ALPHA, new float[]{r8});
        r7.getTiming("opacity").apply(r82);
        r0.add(r82);
        ObjectAnimator r83 = ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{r9});
        r7.getTiming("scale").apply(r83);
        workAroundOreoBug(r83);
        r0.add(r83);
        ObjectAnimator r84 = ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{r9});
        r7.getTiming("scale").apply(r84);
        workAroundOreoBug(r84);
        r0.add(r84);
        calculateImageMatrixFromScale(r10, this.tmpMatrix);
        ObjectAnimator r6 = ObjectAnimator.ofObject(this.view, new ImageMatrixProperty(), new AnonymousClass3(this), new Matrix[]{new Matrix(this.tmpMatrix)});
        r7.getTiming("iconScale").apply(r6);
        r0.add(r6);
        AnimatorSet r62 = new AnimatorSet();
        AnimatorSetCompat.playTogether(r62, r0);
        return r62;
    }

    private AnimatorSet createDefaultAnimator(float r14, float r15, float r16, int r17, int r18) {
        AnimatorSet r10 = new AnimatorSet();
        ArrayList r11 = new ArrayList();
        ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{HIDE_OPACITY, 1.0f});
        r12.addUpdateListener(new FloatingActionButtonImpl$$ExternalSyntheticLambda0(this, this.view.getAlpha(), r14, this.view.getScaleX(), r15, this.view.getScaleY(), this.imageMatrixScale, r16, new Matrix(this.tmpMatrix)));
        r11.add(r12);
        AnimatorSetCompat.playTogether(r10, r11);
        r10.setDuration(MotionUtils.resolveThemeDuration(this.view.getContext(), r17, this.view.getContext().getResources().getInteger(R.integer.material_motion_duration_long_1)));
        r10.setInterpolator(MotionUtils.resolveThemeInterpolator(this.view.getContext(), r18, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return r10;
    }

    private StateListAnimator createDefaultStateListAnimator(float r9, float r10, float r11) {
        StateListAnimator r0 = new StateListAnimator();
        r0.addState(PRESSED_ENABLED_STATE_SET, createElevationAnimator(r9, r11));
        r0.addState(HOVERED_FOCUSED_ENABLED_STATE_SET, createElevationAnimator(r9, r10));
        r0.addState(FOCUSED_ENABLED_STATE_SET, createElevationAnimator(r9, r10));
        r0.addState(HOVERED_ENABLED_STATE_SET, createElevationAnimator(r9, r10));
        AnimatorSet r102 = new AnimatorSet();
        ArrayList r112 = new ArrayList();
        r112.add(ObjectAnimator.ofFloat(this.view, "elevation", new float[]{r9}).setDuration(0));
        if (Build.VERSION.SDK_INT > 24) goto L5;
        FloatingActionButton r92 = this.view;
        r112.add(ObjectAnimator.ofFloat(r92, View.TRANSLATION_Z, new float[]{r92.getTranslationZ()}).setDuration(100));
    L5:
        r112.add(ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Z, new float[]{HIDE_OPACITY}).setDuration(100));
        r102.playSequentially((Animator[]) r112.toArray(new Animator[0]));
        r102.setInterpolator(ELEVATION_ANIM_INTERPOLATOR);
        r0.addState(ENABLED_STATE_SET, r102);
        r0.addState(EMPTY_STATE_SET, createElevationAnimator(HIDE_OPACITY, HIDE_OPACITY));
        return r0;
    }

    private Animator createElevationAnimator(float r8, float r9) {
        AnimatorSet r0 = new AnimatorSet();
        r0.play(ObjectAnimator.ofFloat(this.view, "elevation", new float[]{r8}).setDuration(0)).with(ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Z, new float[]{r9}).setDuration(100));
        r0.setInterpolator(ELEVATION_ANIM_INTERPOLATOR);
        return r0;
    }

    private boolean shouldAnimateVisibilityChange() {
        if (this.view.isLaidOut() == true) goto L5;
        return false;
    L5:
        if (this.view.isInEditMode() == true) goto L10;
        return true;
    L10:
        return false;
    }

    private void workAroundOreoBug(ObjectAnimator r3) {
        if (Build.VERSION.SDK_INT == 26) goto L5;
        return;
    L5:
        r3.setEvaluator(new AnonymousClass4(this));
    }

    public void addOnHideAnimationListener(Animator.AnimatorListener r2) {
        if (this.hideListeners != null) goto L5;
        this.hideListeners = new ArrayList();
    L5:
        this.hideListeners.add(r2);
    }

    public void addOnShowAnimationListener(Animator.AnimatorListener r2) {
        if (this.showListeners != null) goto L5;
        this.showListeners = new ArrayList();
    L5:
        this.showListeners.add(r2);
    }

    public void addTransformationCallback(InternalTransformationCallback r2) {
        if (this.transformationCallbacks != null) goto L5;
        this.transformationCallbacks = new ArrayList();
    L5:
        this.transformationCallbacks.add(r2);
    }

    public BorderDrawable createBorderDrawable(int r6, ColorStateList r7) {
        Context r0 = this.view.getContext();
        BorderDrawable r1 = new BorderDrawable((ShapeAppearanceModel) Preconditions.checkNotNull(this.shapeAppearance));
        r1.setGradientColors(ContextCompat.getColor(r0, R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(r0, R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(r0, R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(r0, R.color.design_fab_stroke_end_outer_color));
        r1.setBorderWidth(r6);
        r1.setBorderTint(r7);
        return r1;
    }

    public MaterialShapeDrawable createShapeDrawable() {
        return new AlwaysStatefulMaterialShapeDrawable((ShapeAppearanceModel) Preconditions.checkNotNull(this.shapeAppearance));
    }

    public final Drawable getContentBackground() {
        return this.contentBackground;
    }

    public float getElevation() {
        return this.view.getElevation();
    }

    public boolean getEnsureMinTouchTargetSize() {
        return this.ensureMinTouchTargetSize;
    }

    public final MotionSpec getHideMotionSpec() {
        return this.hideMotionSpec;
    }

    public float getHoveredFocusedTranslationZ() {
        return this.hoveredFocusedTranslationZ;
    }

    public void getPadding(Rect r5) {
        if (this.shadowViewDelegate.isCompatPaddingEnabled() == false) goto L11;
        int r0 = getTouchTargetPadding();
        if (this.shadowPaddingEnabled == false) goto L7;
        float r1 = getElevation() + this.pressedTranslationZ;
    L8:
        int r4 = Math.max(r0, (int) Math.ceil(r1));
        int r02 = Math.max(r0, (int) Math.ceil(r1 * SHADOW_MULTIPLIER));
        r5.set(r4, r02, r4, r02);
        return;
    L7:
        r1 = HIDE_OPACITY;
        goto L8
    L11:
        if (ignoreExpandBoundsForA11y() == false) goto L14;
        int r03 = (this.minTouchTargetSize - this.view.getSizeDimension()) / 2;
        r5.set(r03, r03, r03, r03);
        return;
    L14:
        r5.set(0, 0, 0, 0);
    }

    public float getPressedTranslationZ() {
        return this.pressedTranslationZ;
    }

    public final ShapeAppearanceModel getShapeAppearance() {
        return this.shapeAppearance;
    }

    public final MotionSpec getShowMotionSpec() {
        return this.showMotionSpec;
    }

    public int getTouchTargetPadding() {
        if (this.ensureMinTouchTargetSize == true) goto L5;
        return 0;
    L5:
        return Math.max((this.minTouchTargetSize - this.view.getSizeDimension()) / 2, 0);
    }

    public void hide(InternalVisibilityChangedListener r8, boolean r9) {
        if (isOrWillBeHidden() == true) goto L31;
        Animator r0 = this.currentAnimator;
        if (r0 == null) goto L9;
        r0.cancel();
    L9:
        if (shouldAnimateVisibilityChange() == false) goto L21;
        MotionSpec r02 = this.hideMotionSpec;
        if (r02 == null) goto L13;
        AnimatorSet r03 = createAnimator(r02, HIDE_OPACITY, HIDE_OPACITY, HIDE_OPACITY);
        FloatingActionButtonImpl r1 = this;
    L14:
        r03.addListener(new AnonymousClass1(r1, r9, r8));
        ArrayList<Animator.AnimatorListener> r7 = r1.hideListeners;
        if (r7 == null) goto L19;
        int r82 = r7.size();
        int r92 = 0;
    L17:
        if (r92 >= r82) goto L19;
        Animator.AnimatorListener r12 = r7.get(r92);
        r92 = r92 + 1;
        r03.addListener(r12);
    L19:
        r03.start();
        return;
    L13:
        r1 = this;
        r03 = r1.createDefaultAnimator(HIDE_OPACITY, 0.4f, 0.4f, HIDE_ANIM_DURATION_ATTR, HIDE_ANIM_EASING_ATTR);
        goto L14
    L21:
        FloatingActionButton r72 = this.view;
        if (r9 == false) goto L24;
        int r04 = 8;
    L25:
        r72.internalSetVisibility(r04, r9);
        if (r8 == null) goto L30;
        r8.onHidden();
        return;
    L30:
        return;
    L24:
        r04 = 4;
        goto L25
    }

    public final boolean ignoreExpandBoundsForA11y() {
        if (this.ensureMinTouchTargetSize == true) goto L5;
        return false;
    L5:
        if (this.view.getSizeDimension() >= this.minTouchTargetSize) goto L10;
        return true;
    L10:
        return false;
    }

    public void initializeBackgroundDrawable(ColorStateList r2, PorterDuff.Mode r3, ColorStateList r4, int r5) {
        MaterialShapeDrawable r0 = createShapeDrawable();
        this.shapeDrawable = r0;
        r0.setTintList(r2);
        if (r3 == null) goto L5;
        this.shapeDrawable.setTintMode(r3);
    L5:
        this.shapeDrawable.initializeElevationOverlay(this.view.getContext());
        if (r5 <= 0) goto L8;
        this.borderDrawable = createBorderDrawable(r5, r2);
        Drawable r22 = new LayerDrawable(new Drawable[]{(Drawable) Preconditions.checkNotNull(this.borderDrawable), (Drawable) Preconditions.checkNotNull(this.shapeDrawable)});
    L9:
        RippleDrawable r52 = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(r4), r22, null);
        this.rippleDrawable = r52;
        FocusRingDrawable.layer(this.view.getContext(), r52, this.shapeDrawable);
        this.contentBackground = r52;
        return;
    L8:
        this.borderDrawable = null;
        r22 = this.shapeDrawable;
        goto L9
    }

    public boolean isOrWillBeHidden() {
        int r0 = this.view.getVisibility();
        int r3 = this.animState;
        if (r0 != 0) goto L8;
        if (r3 != 1) goto L6;
        return true;
    L6:
        return false;
    L8:
        if (r3 == 2) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isOrWillBeShown() {
        int r0 = this.view.getVisibility();
        int r3 = this.animState;
        if (r0 != 0) goto L5;
        if (r3 == 1) goto L10;
        return true;
    L10:
        return false;
    L5:
        if (r3 != 2) goto L7;
        return true;
    L7:
        return false;
    }

    public void onAttachedToWindow() {
        MaterialShapeDrawable r0 = this.shapeDrawable;
        if (r0 == null) goto L6;
        MaterialShapeUtils.setParentAbsoluteElevation(this.view, r0);
        return;
    }

    public void onCompatShadowChanged() {
        updatePadding();
    }

    public void onDetachedFromWindow() {
        ViewTreeObserver r0 = this.view.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener r1 = this.preDrawListener;
        if (r1 == null) goto L6;
        r0.removeOnPreDrawListener(r1);
        this.preDrawListener = null;
        return;
    }

    public void onDrawableStateChangedForLollipop() {
        boolean r0 = this.view.isEnabled();
        FloatingActionButton r1 = this.view;
        if (r0 == false) goto L17;
        r1.setElevation(this.elevation);
        boolean r02 = this.view.isPressed();
        FloatingActionButton r12 = this.view;
        if (r02 == false) goto L9;
        r12.setTranslationZ(this.pressedTranslationZ);
        return;
    L9:
        if (r12.isFocused() == false) goto L11;
    L15:
        this.view.setTranslationZ(this.hoveredFocusedTranslationZ);
        return;
    L11:
        if (this.view.isHovered() == true) goto L15;
        this.view.setTranslationZ(HIDE_OPACITY);
        return;
    L17:
        r1.setElevation(HIDE_OPACITY);
        this.view.setTranslationZ(HIDE_OPACITY);
    }

    public void onElevationsChanged(float r3, float r4, float r5) {
        if (this.view.getStateListAnimator() != this.stateListAnimator) goto L6;
        StateListAnimator r32 = createDefaultStateListAnimator(r3, r4, r5);
        this.stateListAnimator = r32;
        this.view.setStateListAnimator(r32);
    L6:
        if (shouldAddPadding() == false) goto L9;
        updatePadding();
        return;
    }

    public void onPaddingUpdated(Rect r8) {
        Preconditions.checkNotNull(this.contentBackground, "Didn't initialize content background");
        if (shouldAddPadding() == false) goto L6;
        InsetDrawable r1 = new InsetDrawable(this.contentBackground, r8.left, r8.top, r8.right, r8.bottom);
        this.shadowViewDelegate.setBackgroundDrawable(r1);
        return;
    L6:
        this.shadowViewDelegate.setBackgroundDrawable(this.contentBackground);
    }

    public void onScaleChanged() {
        ArrayList<InternalTransformationCallback> r3 = this.transformationCallbacks;
        if (r3 == null) goto L7;
        int r0 = r3.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L9;
        InternalTransformationCallback r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.onScaleChanged();
        goto L5
    L9:
        return;
    }

    public void onTranslationChanged() {
        ArrayList<InternalTransformationCallback> r3 = this.transformationCallbacks;
        if (r3 == null) goto L7;
        int r0 = r3.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L9;
        InternalTransformationCallback r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.onTranslationChanged();
        goto L5
    L9:
        return;
    }

    public void removeOnHideAnimationListener(Animator.AnimatorListener r1) {
        ArrayList<Animator.AnimatorListener> r0 = this.hideListeners;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.remove(r1);
    }

    public void removeOnShowAnimationListener(Animator.AnimatorListener r1) {
        ArrayList<Animator.AnimatorListener> r0 = this.showListeners;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.remove(r1);
    }

    public void removeTransformationCallback(InternalTransformationCallback r1) {
        ArrayList<InternalTransformationCallback> r0 = this.transformationCallbacks;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.remove(r1);
    }

    public void setBackgroundTintList(ColorStateList r2) {
        MaterialShapeDrawable r0 = this.shapeDrawable;
        if (r0 == null) goto L5;
        r0.setTintList(r2);
    L5:
        BorderDrawable r1 = this.borderDrawable;
        if (r1 == null) goto L9;
        r1.setBorderTint(r2);
        return;
    }

    public void setBackgroundTintMode(PorterDuff.Mode r1) {
        MaterialShapeDrawable r0 = this.shapeDrawable;
        if (r0 == null) goto L6;
        r0.setTintMode(r1);
        return;
    }

    public final void setElevation(float r3) {
        if (this.elevation == r3) goto L6;
        this.elevation = r3;
        onElevationsChanged(r3, this.hoveredFocusedTranslationZ, this.pressedTranslationZ);
        return;
    }

    public void setEnsureMinTouchTargetSize(boolean r1) {
        this.ensureMinTouchTargetSize = r1;
    }

    public final void setHideMotionSpec(MotionSpec r1) {
        this.hideMotionSpec = r1;
    }

    public final void setHoveredFocusedTranslationZ(float r3) {
        if (this.hoveredFocusedTranslationZ == r3) goto L6;
        this.hoveredFocusedTranslationZ = r3;
        onElevationsChanged(this.elevation, r3, this.pressedTranslationZ);
        return;
    }

    public final void setImageMatrixScale(float r2) {
        this.imageMatrixScale = r2;
        Matrix r0 = this.tmpMatrix;
        calculateImageMatrixFromScale(r2, r0);
        this.view.setImageMatrix(r0);
    }

    public final void setMaxImageSize(int r2) {
        if (this.maxImageSize == r2) goto L6;
        this.maxImageSize = r2;
        updateImageMatrixScale();
        return;
    }

    public void setMinTouchTargetSize(int r1) {
        this.minTouchTargetSize = r1;
    }

    public final void setPressedTranslationZ(float r3) {
        if (this.pressedTranslationZ == r3) goto L6;
        this.pressedTranslationZ = r3;
        onElevationsChanged(this.elevation, this.hoveredFocusedTranslationZ, r3);
        return;
    }

    public void setRippleColor(ColorStateList r2) {
        Drawable r1 = this.rippleDrawable;
        if ((r1 instanceof RippleDrawable) == false) goto L6;
        ((RippleDrawable) r1).setColor(RippleUtils.sanitizeRippleDrawableColor(r2));
        return;
    L6:
        if (r1 == null) goto L9;
        r1.setTintList(RippleUtils.sanitizeRippleDrawableColor(r2));
        return;
    }

    public void setShadowPaddingEnabled(boolean r1) {
        this.shadowPaddingEnabled = r1;
        updatePadding();
    }

    public final void setShapeAppearance(ShapeAppearanceModel r3) {
        this.shapeAppearance = r3;
        MaterialShapeDrawable r0 = this.shapeDrawable;
        if (r0 == null) goto L5;
        r0.setShapeAppearanceModel(r3);
    L5:
        Object r02 = this.rippleDrawable;
        if ((r02 instanceof Shapeable) == false) goto L8;
        ((Shapeable) r02).setShapeAppearanceModel(r3);
    L8:
        BorderDrawable r2 = this.borderDrawable;
        if (r2 == null) goto L12;
        r2.setShapeAppearanceModel(r3);
        return;
    }

    public final void setShowMotionSpec(MotionSpec r1) {
        this.showMotionSpec = r1;
    }

    public boolean shouldAddPadding() {
        if (this.shadowViewDelegate.isCompatPaddingEnabled() == false) goto L5;
        return true;
    L5:
        if (ignoreExpandBoundsForA11y() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void show(InternalVisibilityChangedListener r9, boolean r10) {
        if (isOrWillBeShown() == true) goto L45;
        Animator r0 = this.currentAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        int r1 = 0;
        if (this.showMotionSpec != null) goto L11;
        boolean r02 = true;
    L12:
        boolean r2 = shouldAnimateVisibilityChange();
        FloatingActionButton r3 = this.view;
        if (r2 == true) goto L15;
        r3.internalSetVisibility(0, r10);
        this.view.setAlpha(1.0f);
        this.view.setScaleY(1.0f);
        this.view.setScaleX(1.0f);
        setImageMatrixScale(1.0f);
        if (r9 == null) goto L44;
        r9.onShown();
        return;
    L44:
        return;
    L15:
        if (r3.getVisibility() == 0) goto L28;
        FloatingActionButton r22 = this.view;
        float r32 = HIDE_OPACITY;
        r22.setAlpha(HIDE_OPACITY);
        FloatingActionButton r23 = this.view;
        if (r02 == false) goto L19;
        float r6 = 0.4f;
    L20:
        r23.setScaleY(r6);
        FloatingActionButton r24 = this.view;
        if (r02 == false) goto L23;
        float r62 = 0.4f;
    L24:
        r24.setScaleX(r62);
        if (r02 == false) goto L27;
        r32 = 0.4f;
    L27:
        setImageMatrixScale(r32);
        goto L28
    L23:
        r62 = 0.0f;
        goto L24
    L19:
        r6 = 0.0f;
    L28:
        MotionSpec r03 = this.showMotionSpec;
        if (r03 == null) goto L31;
        AnimatorSet r04 = createAnimator(r03, 1.0f, 1.0f, 1.0f);
        FloatingActionButtonImpl r25 = this;
    L32:
        r04.addListener(new AnonymousClass2(r25, r10, r9));
        ArrayList<Animator.AnimatorListener> r8 = r25.showListeners;
        if (r8 == null) goto L37;
        int r92 = r8.size();
    L35:
        if (r1 >= r92) goto L37;
        Animator.AnimatorListener r102 = r8.get(r1);
        r1 = r1 + 1;
        r04.addListener(r102);
    L37:
        r04.start();
        return;
    L31:
        r25 = this;
        r04 = r25.createDefaultAnimator(1.0f, 1.0f, 1.0f, SHOW_ANIM_DURATION_ATTR, SHOW_ANIM_EASING_ATTR);
        goto L32
    L11:
        r02 = false;
        goto L12
    }

    public final void updateImageMatrixScale() {
        setImageMatrixScale(this.imageMatrixScale);
    }

    public final void updatePadding() {
        Rect r0 = this.tmpRect;
        getPadding(r0);
        onPaddingUpdated(r0);
        this.shadowViewDelegate.setShadowPadding(r0.left, r0.top, r0.right, r0.bottom);
    }

    public void updateShapeElevation(float r1) {
        MaterialShapeDrawable r0 = this.shapeDrawable;
        if (r0 == null) goto L6;
        r0.setElevation(r1);
        return;
    }
}
