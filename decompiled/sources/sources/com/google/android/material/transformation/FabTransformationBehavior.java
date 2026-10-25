package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.animation.ChildrenAlphaProperty;
import com.google.android.material.animation.DrawableAlphaProperty;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.MotionTiming;
import com.google.android.material.animation.Positioning;
import com.google.android.material.circularreveal.CircularRevealCompat;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.math.MathUtils;
import java.util.ArrayList;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Deprecated
/* loaded from: classes5.dex */
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    private float dependencyOriginalTranslationX;
    private float dependencyOriginalTranslationY;
    private final int[] tmpArray;
    private final Rect tmpRect;
    private final RectF tmpRectF1;
    private final RectF tmpRectF2;

    /* renamed from: com.google.android.material.transformation.FabTransformationBehavior$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ FabTransformationBehavior this$0;
        final /* synthetic */ View val$child;
        final /* synthetic */ View val$dependency;
        final /* synthetic */ boolean val$expanded;

        public AnonymousClass1(FabTransformationBehavior r1, boolean r2, View r3, View r4) {
            this.this$0 = r1;
            this.val$expanded = r2;
            this.val$child = r3;
            this.val$dependency = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.val$expanded == true) goto L6;
            this.val$child.setVisibility(4);
            this.val$dependency.setAlpha(1.0f);
            this.val$dependency.setVisibility(0);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            if (this.val$expanded == false) goto L6;
            this.val$child.setVisibility(0);
            this.val$dependency.setAlpha(0.0f);
            this.val$dependency.setVisibility(4);
            return;
        }
    }

    /* renamed from: com.google.android.material.transformation.FabTransformationBehavior$2, reason: invalid class name */
    public class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ FabTransformationBehavior this$0;
        final /* synthetic */ View val$child;

        public AnonymousClass2(FabTransformationBehavior r1, View r2) {
            this.this$0 = r1;
            this.val$child = r2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r1) {
            this.val$child.invalidate();
        }
    }

    /* renamed from: com.google.android.material.transformation.FabTransformationBehavior$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ FabTransformationBehavior this$0;
        final /* synthetic */ CircularRevealWidget val$circularRevealChild;
        final /* synthetic */ Drawable val$icon;

        public AnonymousClass3(FabTransformationBehavior r1, CircularRevealWidget r2, Drawable r3) {
            this.this$0 = r1;
            this.val$circularRevealChild = r2;
            this.val$icon = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.val$circularRevealChild.setCircularRevealOverlayDrawable(null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.val$circularRevealChild.setCircularRevealOverlayDrawable(this.val$icon);
        }
    }

    /* renamed from: com.google.android.material.transformation.FabTransformationBehavior$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ FabTransformationBehavior this$0;
        final /* synthetic */ CircularRevealWidget val$circularRevealChild;

        public AnonymousClass4(FabTransformationBehavior r1, CircularRevealWidget r2) {
            this.this$0 = r1;
            this.val$circularRevealChild = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            CircularRevealWidget.RevealInfo r22 = this.val$circularRevealChild.getRevealInfo();
            r22.radius = Float.MAX_VALUE;
            this.val$circularRevealChild.setRevealInfo(r22);
        }
    }

    public static class FabTransformationSpec {
        public Positioning positioning;
        public MotionSpec timings;

        public FabTransformationSpec() {
        }
    }

    public FabTransformationBehavior() {
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpArray = new int[2];
    }

    public FabTransformationBehavior(Context r1, AttributeSet r2) {
        super(r1, r2);
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpArray = new int[2];
    }

    private ViewGroup calculateChildContentContainer(View r2) {
        View r0 = r2.findViewById(R.id.mtrl_child_content_container);
        if (r0 == null) goto L7;
        return toViewGroupOrNull(r0);
    L7:
        if ((r2 instanceof TransformationChildLayout) == true) goto L14;
        if ((r2 instanceof TransformationChildCard) == true) goto L14;
        return toViewGroupOrNull(r2);
    L14:
        return toViewGroupOrNull(((ViewGroup) r2).getChildAt(0));
    }

    private void calculateChildVisibleBoundsAtEndOfExpansion(View r1, FabTransformationSpec r2, MotionTiming r3, MotionTiming r4, float r5, float r6, float r7, float r8, RectF r9) {
        float r32 = calculateValueOfAnimationAtEndOfExpansion(r2, r3, r5, r7);
        float r22 = calculateValueOfAnimationAtEndOfExpansion(r2, r4, r6, r8);
        Rect r42 = this.tmpRect;
        r1.getWindowVisibleDisplayFrame(r42);
        RectF r52 = this.tmpRectF1;
        r52.set(r42);
        RectF r43 = this.tmpRectF2;
        calculateWindowBounds(r1, r43);
        r43.offset(r32, r22);
        r43.intersect(r52);
        r9.set(r43);
    }

    private void calculateDependencyWindowBounds(View r1, RectF r2) {
        calculateWindowBounds(r1, r2);
        r2.offset(this.dependencyOriginalTranslationX, this.dependencyOriginalTranslationY);
    }

    private Pair<MotionTiming, MotionTiming> calculateMotionTiming(float r1, float r2, boolean r3, FabTransformationSpec r4) {
        if (r1 != 0.0f) goto L5;
    L14:
        MotionTiming r0 = r4.timings.getTiming("translationXLinear");
        MotionTiming r12 = r4.timings.getTiming("translationYLinear");
    L16:
        return new Pair(r0, r12);
    L5:
        if (r2 == 0.0f) goto L14;
        if (r3 == true) goto L9;
    L10:
        if (r3 == true) goto L13;
        if (r2 <= 0.0f) goto L13;
    L12:
        r0 = r4.timings.getTiming("translationXCurveUpwards");
        r12 = r4.timings.getTiming("translationYCurveUpwards");
    L13:
        r0 = r4.timings.getTiming("translationXCurveDownwards");
        r12 = r4.timings.getTiming("translationYCurveDownwards");
        goto L16
    L9:
        if (r2 < 0.0f) goto L12;
        goto L10
    }

    private float calculateRevealCenterX(View r3, View r4, Positioning r5) {
        RectF r0 = this.tmpRectF1;
        RectF r1 = this.tmpRectF2;
        calculateDependencyWindowBounds(r3, r0);
        calculateWindowBounds(r4, r1);
        r1.offset(-calculateTranslationX(r3, r4, r5), 0.0f);
        return r0.centerX() - r1.left;
    }

    private float calculateRevealCenterY(View r3, View r4, Positioning r5) {
        RectF r0 = this.tmpRectF1;
        RectF r1 = this.tmpRectF2;
        calculateDependencyWindowBounds(r3, r0);
        calculateWindowBounds(r4, r1);
        r1.offset(0.0f, -calculateTranslationY(r3, r4, r5));
        return r0.centerY() - r1.top;
    }

    private float calculateTranslationX(View r3, View r4, Positioning r5) {
        RectF r0 = this.tmpRectF1;
        RectF r1 = this.tmpRectF2;
        calculateDependencyWindowBounds(r3, r0);
        calculateWindowBounds(r4, r1);
        int r2 = r5.gravity & 7;
        if (r2 != 1) goto L5;
        float r22 = r1.centerX();
        float r32 = r0.centerX();
    L10:
        float r23 = r22 - r32;
    L14:
        return r23 + r5.xAdjustment;
    L5:
        if (r2 != 3) goto L7;
        r22 = r1.left;
        r32 = r0.left;
        goto L10
    L7:
        if (r2 == 5) goto L9;
        r23 = 0.0f;
        goto L14
    L9:
        r22 = r1.right;
        r32 = r0.right;
        goto L10
    }

    private float calculateTranslationY(View r3, View r4, Positioning r5) {
        RectF r0 = this.tmpRectF1;
        RectF r1 = this.tmpRectF2;
        calculateDependencyWindowBounds(r3, r0);
        calculateWindowBounds(r4, r1);
        int r2 = r5.gravity & 112;
        if (r2 != 16) goto L5;
        float r22 = r1.centerY();
        float r32 = r0.centerY();
    L10:
        float r23 = r22 - r32;
    L14:
        return r23 + r5.yAdjustment;
    L5:
        if (r2 != 48) goto L7;
        r22 = r1.top;
        r32 = r0.top;
        goto L10
    L7:
        if (r2 == 80) goto L9;
        r23 = 0.0f;
        goto L14
    L9:
        r22 = r1.bottom;
        r32 = r0.bottom;
        goto L10
    }

    private float calculateValueOfAnimationAtEndOfExpansion(FabTransformationSpec r7, MotionTiming r8, float r9, float r10) {
        long r0 = r8.getDelay();
        long r2 = r8.getDuration();
        MotionTiming r6 = r7.timings.getTiming("expansion");
        return AnimationUtils.lerp(r9, r10, r8.getInterpolator().getInterpolation((((r6.getDelay() + r6.getDuration()) + 17) - r0) / r2));
    }

    private void calculateWindowBounds(View r4, RectF r5) {
        r5.set(0.0f, 0.0f, r4.getWidth(), r4.getHeight());
        r4.getLocationInWindow(this.tmpArray);
        r5.offsetTo(r3[0], r3[1]);
        r5.offset((int) (-r4.getTranslationX()), (int) (-r4.getTranslationY()));
    }

    private void createChildrenFadeAnimation(View r1, View r2, boolean r3, boolean r4, FabTransformationSpec r5, List<Animator> r6, List<Animator.AnimatorListener> r7) {
        if ((r2 instanceof ViewGroup) == false) goto L16;
        ViewGroup r0 = calculateChildContentContainer(r2);
        if (r0 != null) goto L9;
        return;
    L9:
        if (r3 == false) goto L13;
        if (r4 == true) goto L12;
        ChildrenAlphaProperty.CHILDREN_ALPHA.set(r0, Float.valueOf(0.0f));
    L12:
        ObjectAnimator r02 = ObjectAnimator.ofFloat(r0, ChildrenAlphaProperty.CHILDREN_ALPHA, new float[]{1.0f});
    L14:
        r5.timings.getTiming("contentFade").apply(r02);
        r6.add(r02);
        return;
    L13:
        r02 = ObjectAnimator.ofFloat(r0, ChildrenAlphaProperty.CHILDREN_ALPHA, new float[]{0.0f});
        goto L14
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void createColorAnimation(View r1, View r2, boolean r3, boolean r4, FabTransformationSpec r5, List<Animator> r6, List<Animator.AnimatorListener> r7) {
        if ((r2 instanceof CircularRevealWidget) == true) goto L5;
        return;
    L5:
        CircularRevealWidget r22 = (CircularRevealWidget) r2;
        int r0 = getBackgroundTint(r1);
        int r12 = 16777215 & r0;
        if (r3 == false) goto L10;
        if (r4 == true) goto L9;
        r22.setCircularRevealScrimColor(r0);
    L9:
        ObjectAnimator r02 = ObjectAnimator.ofInt(r22, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, new int[]{r12});
    L11:
        r02.setEvaluator(ArgbEvaluatorCompat.getInstance());
        r5.timings.getTiming("color").apply(r02);
        r6.add(r02);
        return;
    L10:
        r02 = ObjectAnimator.ofInt(r22, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, new int[]{r0});
        goto L11
    }

    private void createDependencyTranslationAnimation(View r7, View r8, boolean r9, FabTransformationSpec r10, List<Animator> r11) {
        float r0 = calculateTranslationX(r7, r8, r10.positioning);
        float r82 = calculateTranslationY(r7, r8, r10.positioning);
        Pair<MotionTiming, MotionTiming> r102 = calculateMotionTiming(r0, r82, r9, r10);
        MotionTiming r1 = (MotionTiming) r102.first;
        MotionTiming r103 = (MotionTiming) r102.second;
        Property r2 = View.TRANSLATION_X;
        if (r9 == true) goto L6;
        r0 = this.dependencyOriginalTranslationX;
    L6:
        ObjectAnimator r02 = ObjectAnimator.ofFloat(r7, r2, new float[]{r0});
        Property r22 = View.TRANSLATION_Y;
        if (r9 == true) goto L10;
        r82 = this.dependencyOriginalTranslationY;
    L10:
        ObjectAnimator r6 = ObjectAnimator.ofFloat(r7, r22, new float[]{r82});
        r1.apply(r02);
        r103.apply(r6);
        r11.add(r02);
        r11.add(r6);
    }

    private void createElevationAnimation(View r1, View r2, boolean r3, boolean r4, FabTransformationSpec r5, List<Animator> r6, List<Animator.AnimatorListener> r7) {
        float r0 = r2.getElevation() - r1.getElevation();
        if (r3 == false) goto L7;
        if (r4 == true) goto L6;
        r2.setTranslationZ(-r0);
    L6:
        ObjectAnimator r02 = ObjectAnimator.ofFloat(r2, View.TRANSLATION_Z, new float[]{0.0f});
    L8:
        r5.timings.getTiming("elevation").apply(r02);
        r6.add(r02);
        return;
    L7:
        r02 = ObjectAnimator.ofFloat(r2, View.TRANSLATION_Z, new float[]{-r0});
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void createExpansionAnimation(View r16, View r17, boolean r18, boolean r19, FabTransformationSpec r20, float r21, float r22, List<Animator> r23, List<Animator.AnimatorListener> r24) {
        if ((r17 instanceof CircularRevealWidget) == true) goto L5;
        return;
    L5:
        CircularRevealWidget r12 = (CircularRevealWidget) r17;
        float r3 = calculateRevealCenterX(r16, r17, r20.positioning);
        float r4 = calculateRevealCenterY(r16, r17, r20.positioning);
        ((FloatingActionButton) r16).getMeasuredContentRect(this.tmpRect);
        float r10 = this.tmpRect.width() / 2.0f;
        MotionTiming r13 = r20.timings.getTiming("expansion");
        if (r18 == false) goto L12;
        if (r19 == true) goto L9;
        r12.setRevealInfo(new CircularRevealWidget.RevealInfo(r3, r4, r10));
    L9:
        if (r19 == false) goto L11;
        r10 = r12.getRevealInfo().radius;
    L11:
        Animator r8 = CircularRevealCompat.createCircularReveal(r12, r3, r4, MathUtils.distanceToFurthestCorner(r3, r4, 0.0f, 0.0f, r21, r22));
        r8.addListener(new AnonymousClass4(this, r12));
        createPreFillRadialExpansion(r17, r13.getDelay(), (int) r3, (int) r4, r10, r23);
    L13:
        r13.apply(r8);
        r23.add(r8);
        r24.add(CircularRevealCompat.createCircularRevealListener(r12));
        return;
    L12:
        float r6 = r12.getRevealInfo().radius;
        Animator r14 = CircularRevealCompat.createCircularReveal(r12, r3, r4, r10);
        int r2 = (int) r3;
        int r9 = (int) r4;
        createPreFillRadialExpansion(r17, r13.getDelay(), r2, r9, r6, r23);
        createPostFillRadialExpansion(r17, r13.getDelay(), r13.getDuration(), r20.timings.getTotalDuration(), r2, r9, r10, r23);
        r8 = r14;
        goto L13
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void createIconFadeAnimation(View r3, View r4, boolean r5, boolean r6, FabTransformationSpec r7, List<Animator> r8, List<Animator.AnimatorListener> r9) {
        if ((r4 instanceof CircularRevealWidget) == true) goto L5;
        return;
    L5:
        if ((r3 instanceof ImageView) == false) goto L19;
        CircularRevealWidget r0 = (CircularRevealWidget) r4;
        Drawable r32 = ((ImageView) r3).getDrawable();
        if (r32 == null) goto L20;
        r32.mutate();
        if (r5 == false) goto L15;
        if (r6 == true) goto L14;
        r32.setAlpha(255);
    L14:
        ObjectAnimator r52 = ObjectAnimator.ofInt(r32, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, new int[]{0});
    L16:
        r52.addUpdateListener(new AnonymousClass2(this, r4));
        r7.timings.getTiming("iconFade").apply(r52);
        r8.add(r52);
        r9.add(new AnonymousClass3(this, r0, r32));
        return;
    L15:
        r52 = ObjectAnimator.ofInt(r32, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, new int[]{255});
        goto L16
    L20:
        return;
    }

    private void createPostFillRadialExpansion(View r1, long r2, long r4, long r6, int r8, int r9, float r10, List<Animator> r11) {
        long r22 = r2 + r4;
        if (r22 >= r6) goto L6;
        Animator r0 = ViewAnimationUtils.createCircularReveal(r1, r8, r9, r10, r10);
        r0.setStartDelay(r22);
        r0.setDuration(r6 - r22);
        r11.add(r0);
        return;
    }

    private void createPreFillRadialExpansion(View r3, long r4, int r6, int r7, float r8, List<Animator> r9) {
        if (r4 <= 0) goto L6;
        Animator r2 = ViewAnimationUtils.createCircularReveal(r3, r6, r7, r8, r8);
        r2.setStartDelay(0);
        r2.setDuration(r4);
        r9.add(r2);
        return;
    }

    private void createTranslationAnimation(View r14, View r15, boolean r16, boolean r17, FabTransformationSpec r18, List<Animator> r19, List<Animator.AnimatorListener> r20, RectF r21) {
        float r1 = calculateTranslationX(r14, r15, r18.positioning);
        float r142 = calculateTranslationY(r14, r15, r18.positioning);
        Pair<MotionTiming, MotionTiming> r3 = calculateMotionTiming(r1, r142, r16, r18);
        MotionTiming r4 = (MotionTiming) r3.first;
        MotionTiming r32 = (MotionTiming) r3.second;
        if (r16 == false) goto L7;
        if (r17 == true) goto L6;
        r15.setTranslationX(-r1);
        r15.setTranslationY(-r142);
    L6:
        ObjectAnimator r11 = ObjectAnimator.ofFloat(r15, View.TRANSLATION_X, new float[]{0.0f});
        ObjectAnimator r12 = ObjectAnimator.ofFloat(r15, View.TRANSLATION_Y, new float[]{0.0f});
        MotionTiming r42 = r32;
        MotionTiming r33 = r4;
        calculateChildVisibleBoundsAtEndOfExpansion(r15, r18, r33, r42, -r1, -r142, 0.0f, 0.0f, r21);
    L8:
        r33.apply(r11);
        r42.apply(r12);
        r19.add(r11);
        r19.add(r12);
        return;
    L7:
        r42 = r32;
        r33 = r4;
        r11 = ObjectAnimator.ofFloat(r15, View.TRANSLATION_X, new float[]{-r1});
        r12 = ObjectAnimator.ofFloat(r15, View.TRANSLATION_Y, new float[]{-r142});
        goto L8
    }

    private int getBackgroundTint(View r2) {
        ColorStateList r1 = r2.getBackgroundTintList();
        if (r1 != null) goto L5;
        return 0;
    L5:
        return r1.getColorForState(r2.getDrawableState(), r1.getDefaultColor());
    }

    private ViewGroup toViewGroupOrNull(View r1) {
        if ((r1 instanceof ViewGroup) == true) goto L5;
        return null;
    L5:
        return (ViewGroup) r1;
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean layoutDependsOn(CoordinatorLayout r2, View r3, View r4) {
        if (r3.getVisibility() != 8) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        return false;
    L5:
        if ((r4 instanceof FloatingActionButton) == false) goto L14;
        int r1 = ((FloatingActionButton) r4).getExpandedComponentIdHint();
        if (r1 != 0) goto L9;
        return true;
    L9:
        if (r1 == r3.getId()) goto L17;
        return false;
    L17:
        return true;
    L14:
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams r1) {
        if (r1.dodgeInsetEdges != 0) goto L6;
        r1.dodgeInsetEdges = 80;
        return;
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    public AnimatorSet onCreateExpandedStateChangeAnimation(View r12, View r13, boolean r14, boolean r15) {
        FabTransformationSpec r6 = onCreateMotionSpec(r13.getContext(), r14);
        if (r14 == false) goto L5;
        this.dependencyOriginalTranslationX = r12.getTranslationX();
        this.dependencyOriginalTranslationY = r12.getTranslationY();
    L5:
        ArrayList r7 = new ArrayList();
        ArrayList r8 = new ArrayList();
        createElevationAnimation(r12, r13, r14, r15, r6, r7, r8);
        RectF r9 = this.tmpRectF1;
        createTranslationAnimation(r12, r13, r14, r15, r6, r7, r8, r9);
        float r122 = r9.width();
        float r132 = r9.height();
        createDependencyTranslationAnimation(r12, r13, r14, r6, r7);
        createIconFadeAnimation(r12, r13, r14, r15, r6, r7, r8);
        createExpansionAnimation(r12, r13, r14, r15, r6, r122, r132, r7, r8);
        createColorAnimation(r12, r13, r14, r15, r6, r7, r8);
        createChildrenFadeAnimation(r12, r13, r14, r15, r6, r7, r8);
        AnimatorSet r11 = new AnimatorSet();
        AnimatorSetCompat.playTogether(r11, r7);
        r11.addListener(new AnonymousClass1(this, r14, r13, r12));
        int r123 = r8.size();
        int r133 = 0;
    L6:
        if (r133 >= r123) goto L8;
        r11.addListener(r8.get(r133));
        r133 = r133 + 1;
        goto L6
    L8:
        return r11;
    }

    public abstract FabTransformationSpec onCreateMotionSpec(Context r1, boolean r2);
}
