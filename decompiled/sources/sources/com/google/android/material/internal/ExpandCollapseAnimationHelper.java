package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ExpandCollapseAnimationHelper {
    private ValueAnimator.AnimatorUpdateListener additionalUpdateListener;
    private final View collapsedView;
    private int collapsedViewOffsetY;
    private long duration;
    private final List<View> endAnchoredViews;
    private final View expandedView;
    private int expandedViewOffsetY;
    private final List<AnimatorListenerAdapter> listeners;

    /* renamed from: com.google.android.material.internal.ExpandCollapseAnimationHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ExpandCollapseAnimationHelper this$0;

        public AnonymousClass1(ExpandCollapseAnimationHelper r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            ExpandCollapseAnimationHelper.access$000(this.this$0).setVisibility(0);
        }
    }

    /* renamed from: com.google.android.material.internal.ExpandCollapseAnimationHelper$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ExpandCollapseAnimationHelper this$0;

        public AnonymousClass2(ExpandCollapseAnimationHelper r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ExpandCollapseAnimationHelper.access$000(this.this$0).setVisibility(8);
        }
    }

    public static /* synthetic */ void $r8$lambda$rNtcHfP3zBxS8d5wSmAlJ8Csw9c(ExpandCollapseAnimationHelper r0, Rect r1, ValueAnimator r2) {
        ViewUtils.setBoundsFromRect(r0.expandedView, r1);
    }

    public ExpandCollapseAnimationHelper(View r1, View r2) {
        this.collapsedView = r1;
        this.expandedView = r2;
        this.listeners = new ArrayList();
        this.endAnchoredViews = new ArrayList();
    }

    public static /* synthetic */ View access$000(ExpandCollapseAnimationHelper r0) {
        return r0.expandedView;
    }

    private void addListeners(Animator r1, List<AnimatorListenerAdapter> r2) {
        Iterator<AnimatorListenerAdapter> r0 = r2.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r1.addListener(r0.next());
        goto L4
    }

    private AnimatorSet getAnimatorSet(boolean r4) {
        AnimatorSet r0 = new AnimatorSet();
        r0.playTogether(new Animator[]{getExpandCollapseAnimator(r4), getExpandedViewChildrenAlphaAnimator(r4), getEndAnchoredViewsTranslateAnimator(r4)});
        return r0;
    }

    private Animator getEndAnchoredViewsTranslateAnimator(boolean r4) {
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{(this.expandedView.getLeft() - this.collapsedView.getLeft()) + (this.collapsedView.getRight() - this.expandedView.getRight()), 0.0f});
        r0.addUpdateListener(MultiViewUpdateListener.translationXListener(this.endAnchoredViews));
        r0.setDuration(this.duration);
        r0.setInterpolator(ReversableAnimatedValueInterpolator.of(r4, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return r0;
    }

    private Animator getExpandCollapseAnimator(boolean r5) {
        Rect r0 = ViewUtils.calculateRectFromBounds(this.collapsedView, this.collapsedViewOffsetY);
        Rect r1 = ViewUtils.calculateRectFromBounds(this.expandedView, this.expandedViewOffsetY);
        Rect r2 = new Rect(r0);
        ValueAnimator r02 = ValueAnimator.ofObject(new RectEvaluator(r2), new Object[]{r0, r1});
        r02.addUpdateListener(new ExpandCollapseAnimationHelper$$ExternalSyntheticLambda0(this, r2));
        ValueAnimator.AnimatorUpdateListener r12 = this.additionalUpdateListener;
        if (r12 == null) goto L5;
        r02.addUpdateListener(r12);
    L5:
        r02.setDuration(this.duration);
        r02.setInterpolator(ReversableAnimatedValueInterpolator.of(r5, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return r02;
    }

    private Animator getExpandedViewChildrenAlphaAnimator(boolean r5) {
        List<View> r0 = ViewUtils.getChildren(this.expandedView);
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r1.addUpdateListener(MultiViewUpdateListener.alphaListener(r0));
        r1.setDuration(this.duration);
        r1.setInterpolator(ReversableAnimatedValueInterpolator.of(r5, AnimationUtils.LINEAR_INTERPOLATOR));
        return r1;
    }

    public ExpandCollapseAnimationHelper addEndAnchoredViews(Collection<View> r2) {
        this.endAnchoredViews.addAll(r2);
        return this;
    }

    public ExpandCollapseAnimationHelper addEndAnchoredViews(View... r2) {
        Collections.addAll(this.endAnchoredViews, r2);
        return this;
    }

    public ExpandCollapseAnimationHelper addListener(AnimatorListenerAdapter r2) {
        this.listeners.add(r2);
        return this;
    }

    public Animator getCollapseAnimator() {
        AnimatorSet r0 = getAnimatorSet(false);
        r0.addListener(new AnonymousClass2(this));
        addListeners(r0, this.listeners);
        return r0;
    }

    public Animator getExpandAnimator() {
        AnimatorSet r0 = getAnimatorSet(true);
        r0.addListener(new AnonymousClass1(this));
        addListeners(r0, this.listeners);
        return r0;
    }

    public ExpandCollapseAnimationHelper setAdditionalUpdateListener(ValueAnimator.AnimatorUpdateListener r1) {
        this.additionalUpdateListener = r1;
        return this;
    }

    public ExpandCollapseAnimationHelper setCollapsedViewOffsetY(int r1) {
        this.collapsedViewOffsetY = r1;
        return this;
    }

    public ExpandCollapseAnimationHelper setDuration(long r1) {
        this.duration = r1;
        return this;
    }

    public ExpandCollapseAnimationHelper setExpandedViewOffsetY(int r1) {
        this.expandedViewOffsetY = r1;
        return this;
    }
}
