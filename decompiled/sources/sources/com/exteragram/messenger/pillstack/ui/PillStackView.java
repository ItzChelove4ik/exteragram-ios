package com.exteragram.messenger.pillstack.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.pillstack.ui.pills.BasePill;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes.dex */
public class PillStackView extends FrameLayout {
    private ValueAnimator currentAnimator;
    private int currentIndex;
    private float currentSwipeProgress;
    private boolean isSwiping;
    private boolean isSwipingUp;
    private boolean longClickPerformed;
    private final Runnable longPressRunnable;
    private boolean maybeClick;
    private final List<BasePill> pills;
    private boolean stackOnScreen;
    private float startX;
    private float startY;
    private final float touchSlop;
    private float visibilityFactor;

    /* renamed from: com.exteragram.messenger.pillstack.ui.PillStackView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ PillStackView this$0;

        public AnonymousClass1(PillStackView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PillStackView.m1518$$Nest$fgetmaybeClick(this.this$0) == true) goto L5;
            return;
        L5:
            if (PillStackView.m1516$$Nest$fgetisSwiping(this.this$0) == false) goto L7;
            return;
        L7:
            if (PillStackView.m1519$$Nest$fgetpills(this.this$0).isEmpty() == true) goto L17;
            PillStackView r0 = this.this$0;
            PillStackView.m1522$$Nest$fputlongClickPerformed(r0, ((BasePill) PillStackView.m1519$$Nest$fgetpills(r0).get(PillStackView.m1515$$Nest$fgetcurrentIndex(this.this$0))).onPillLongClicked());
            if (PillStackView.m1517$$Nest$fgetlongClickPerformed(this.this$0) == false) goto L18;
            this.this$0.performHapticFeedback(0);
            PillStackView.m1523$$Nest$fputmaybeClick(this.this$0, false);
            int r02 = 0;
        L12:
            if (r02 >= PillStackView.m1519$$Nest$fgetpills(this.this$0).size()) goto L19;
            ((BasePill) PillStackView.m1519$$Nest$fgetpills(this.this$0).get(r02)).setPressed(false);
            r02 = r02 + 1;
            goto L12
        L19:
            return;
        L18:
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.pillstack.ui.PillStackView$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        private boolean cancelled;
        final /* synthetic */ PillStackView this$0;
        final /* synthetic */ boolean val$swipedUp;

        public AnonymousClass2(PillStackView r1, boolean r2) {
            this.this$0 = r1;
            this.val$swipedUp = r2;
            this.cancelled = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.cancelled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r6) {
            if (this.cancelled == false) goto L5;
            return;
        L5:
            BasePill r62 = (BasePill) PillStackView.m1519$$Nest$fgetpills(this.this$0).get(PillStackView.m1515$$Nest$fgetcurrentIndex(this.this$0));
            r62.setVisibility(8);
            r62.setPressed(false);
            r62.setScaleX(1.0f);
            r62.setScaleY(1.0f);
            r62.onPillUnselected();
            PillStackView r63 = this.this$0;
            if (this.val$swipedUp == false) goto L8;
            int r3 = PillStackView.m1515$$Nest$fgetcurrentIndex(r63) + 1;
        L9:
            PillStackView.m1520$$Nest$fputcurrentIndex(r63, r3);
            if (PillStackConfig.getInfiniteScrolling() == true) goto L12;
        L17:
            int r64 = 0;
        L18:
            int r32 = PillStackView.m1519$$Nest$fgetpills(this.this$0).size();
            PillStackView r4 = this.this$0;
            if (r64 >= r32) goto L24;
            if (r64 == PillStackView.m1515$$Nest$fgetcurrentIndex(r4)) goto L23;
            ((BasePill) PillStackView.m1519$$Nest$fgetpills(this.this$0).get(r64)).setVisibility(8);
        L23:
            r64 = r64 + 1;
            goto L18
        L24:
            BasePill r65 = (BasePill) PillStackView.m1519$$Nest$fgetpills(r4).get(PillStackView.m1515$$Nest$fgetcurrentIndex(this.this$0));
            r65.setVisibility(0);
            r65.setScaleX(1.0f);
            r65.setScaleY(1.0f);
            r65.setTranslationY(0.0f);
            r65.setAlpha(1.0f);
            r65.onPillSelected();
            PillStackView.m1521$$Nest$fputcurrentSwipeProgress(this.this$0, 0.0f);
            PillStackConfig.saveLastActivePillId(r65.getPillId());
            return;
        L12:
            if (PillStackView.m1515$$Nest$fgetcurrentIndex(this.this$0) < PillStackView.m1519$$Nest$fgetpills(this.this$0).size()) goto L15;
            PillStackView.m1520$$Nest$fputcurrentIndex(this.this$0, 0);
        L15:
            if (PillStackView.m1515$$Nest$fgetcurrentIndex(this.this$0) >= 0) goto L17;
            PillStackView.m1520$$Nest$fputcurrentIndex(this.this$0, PillStackView.m1519$$Nest$fgetpills(r6).size() - 1);
            goto L17
        L8:
            r3 = PillStackView.m1515$$Nest$fgetcurrentIndex(r63) - 1;
            goto L9
        }
    }

    /* renamed from: com.exteragram.messenger.pillstack.ui.PillStackView$3, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        private boolean cancelled;
        final /* synthetic */ PillStackView this$0;

        public AnonymousClass3(PillStackView r1) {
            this.this$0 = r1;
            this.cancelled = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.cancelled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            if (this.cancelled == false) goto L5;
            return;
        L5:
            int r0 = 0;
        L6:
            int r1 = PillStackView.m1519$$Nest$fgetpills(this.this$0).size();
            PillStackView r2 = this.this$0;
            if (r0 >= r1) goto L12;
            if (r0 == PillStackView.m1515$$Nest$fgetcurrentIndex(r2)) goto L11;
            BasePill r12 = (BasePill) PillStackView.m1519$$Nest$fgetpills(this.this$0).get(r0);
            r12.setVisibility(8);
            r12.setPressed(false);
            r12.setScaleX(1.0f);
            r12.setScaleY(1.0f);
        L11:
            r0 = r0 + 1;
            goto L6
        L12:
            BasePill r52 = (BasePill) PillStackView.m1519$$Nest$fgetpills(r2).get(PillStackView.m1515$$Nest$fgetcurrentIndex(this.this$0));
            r52.setTranslationY(0.0f);
            r52.setAlpha(1.0f);
            r52.setScaleX(1.0f);
            r52.setScaleY(1.0f);
            PillStackView.m1521$$Nest$fputcurrentSwipeProgress(this.this$0, 0.0f);
        }
    }

    public static /* synthetic */ void $r8$lambda$cWJx3vHzhNT0xjN6XCQagRksPi8(PillStackView r0, boolean r1, ValueAnimator r2) {
        r0.lambda$cancelSwipe$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$vRI0TE6ZldQfh8TthHXwywoSABk(PillStackView r0, boolean r1, ValueAnimator r2) {
        r0.lambda$animateToNextPill$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcurrentIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1515$$Nest$fgetcurrentIndex(PillStackView r0) {
        return r0.currentIndex;
    }

    /* renamed from: -$$Nest$fgetisSwiping, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1516$$Nest$fgetisSwiping(PillStackView r0) {
        return r0.isSwiping;
    }

    /* renamed from: -$$Nest$fgetlongClickPerformed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1517$$Nest$fgetlongClickPerformed(PillStackView r0) {
        return r0.longClickPerformed;
    }

    /* renamed from: -$$Nest$fgetmaybeClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1518$$Nest$fgetmaybeClick(PillStackView r0) {
        return r0.maybeClick;
    }

    /* renamed from: -$$Nest$fgetpills, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m1519$$Nest$fgetpills(PillStackView r0) {
        return r0.pills;
    }

    /* renamed from: -$$Nest$fputcurrentIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1520$$Nest$fputcurrentIndex(PillStackView r0, int r1) {
        r0.currentIndex = r1;
    }

    /* renamed from: -$$Nest$fputcurrentSwipeProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1521$$Nest$fputcurrentSwipeProgress(PillStackView r0, float r1) {
        r0.currentSwipeProgress = r1;
    }

    /* renamed from: -$$Nest$fputlongClickPerformed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1522$$Nest$fputlongClickPerformed(PillStackView r0, boolean r1) {
        r0.longClickPerformed = r1;
    }

    /* renamed from: -$$Nest$fputmaybeClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1523$$Nest$fputmaybeClick(PillStackView r0, boolean r1) {
        r0.maybeClick = r1;
    }

    public PillStackView(Context r3) {
        super(r3);
        this.pills = new ArrayList();
        this.currentIndex = 0;
        this.longPressRunnable = new AnonymousClass1(this);
        this.currentSwipeProgress = 0.0f;
        this.isSwipingUp = false;
        this.visibilityFactor = -1.0f;
        this.stackOnScreen = true;
        this.touchSlop = ViewConfiguration.get(r3).getScaledTouchSlop();
        setClipChildren(false);
    }

    private void animateToNextPill(boolean r4) {
        ValueAnimator r0 = this.currentAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.currentSwipeProgress, 1.0f});
        this.currentAnimator = r02;
        r02.setDuration(250);
        this.currentAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.currentAnimator.addUpdateListener(new PillStackView$$ExternalSyntheticLambda0(this, r4));
        this.currentAnimator.addListener(new AnonymousClass2(this, r4));
        this.currentAnimator.start();
    }

    private void applyProgress(float r8, boolean r9) {
        BasePill r0 = this.pills.get(this.currentIndex);
        int r1 = this.currentIndex;
        if (r9 == false) goto L5;
        int r12 = r1 + 1;
    L7:
        if (PillStackConfig.getInfiniteScrolling() == true) goto L9;
    L13:
        int r2 = 0;
    L15:
        if (r2 >= this.pills.size()) goto L24;
        if (r2 == this.currentIndex) goto L22;
        if (r2 == r12) goto L22;
        if (this.pills.get(r2).getVisibility() == 8) goto L22;
        this.pills.get(r2).setVisibility(8);
    L22:
        r2 = r2 + 1;
        goto L15
    L24:
        if (PillStackConfig.getInfiniteScrolling() == false) goto L26;
    L33:
        float r82 = Math.min(r8, 1.0f);
        BasePill r13 = this.pills.get(r12);
        if (r13.getVisibility() == 0) goto L36;
        r13.setVisibility(0);
    L36:
        float r22 = getHeight() * r82;
        if (r9 == false) goto L39;
        r22 = -r22;
    L39:
        r0.setTranslationY(r22);
        r0.setAlpha(1.0f - r82);
        float r23 = 0.2f * r82;
        float r4 = 1.0f - r23;
        r0.setScaleX(r4);
        r0.setScaleY(r4);
        float r24 = r23 + 0.8f;
        r13.setScaleX(r24);
        r13.setScaleY(r24);
        r13.setAlpha(r82);
        int r7 = getHeight();
        if (r9 == false) goto L42;
    L41:
        float r72 = r7;
        r13.setTranslationY(r72 - (r82 * r72));
        return;
    L42:
        r7 = -r7;
        goto L41
    L26:
        if (r12 >= this.pills.size()) goto L28;
        if (r12 >= 0) goto L33;
    L28:
        float r73 = getHeight() * ((float) (1.0d - (1.0d / ((r8 * 0.18f) + 1.0d))));
        if (r9 == false) goto L31;
        r73 = -r73;
    L31:
        r0.setTranslationY(r73);
        r0.setAlpha(1.0f);
        return;
    L9:
        if (r12 < this.pills.size()) goto L11;
        r12 = 0;
    L11:
        if (r12 >= 0) goto L13;
        r12 = this.pills.size() - 1;
        goto L13
    L5:
        r12 = r1 - 1;
        goto L7
    }

    private void cancelSwipe(boolean r4) {
        ValueAnimator r0 = this.currentAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.currentSwipeProgress, 0.0f});
        this.currentAnimator = r02;
        r02.setDuration(200);
        this.currentAnimator.addUpdateListener(new PillStackView$$ExternalSyntheticLambda1(this, r4));
        this.currentAnimator.addListener(new AnonymousClass3(this));
        this.currentAnimator.start();
    }

    private void finishSwipe(float r5) {
        int r0 = getHeight();
        if (r0 > 0) goto L6;
        cancelSwipe(this.isSwipingUp);
        return;
    L6:
        float r02 = r0 * 0.25f;
        boolean r2 = true;
        if (PillStackConfig.getInfiniteScrolling() == true) goto L17;
        boolean r1 = this.isSwipingUp;
        int r3 = this.currentIndex;
        if (r1 == false) goto L11;
        int r32 = r3 + 1;
    L13:
        if (r32 >= this.pills.size()) goto L15;
        if (r32 >= 0) goto L17;
    L15:
        r2 = false;
        goto L17
    L11:
        r32 = r3 - 1;
    L17:
        if (Math.abs(r5) <= r02) goto L21;
        if (r2 == false) goto L21;
        animateToNextPill(this.isSwipingUp);
        return;
    L21:
        cancelSwipe(this.isSwipingUp);
    }

    private void handleSwipeProgress(float r4) {
        if (this.pills.size() <= 1) goto L25;
        int r0 = getHeight();
        if (r0 > 0) goto L9;
        return;
    L9:
        if (r4 >= 0.0f) goto L11;
        boolean r2 = true;
    L12:
        this.isSwipingUp = r2;
        float r42 = Math.abs(r4) / r0;
        boolean r02 = this.isSwipingUp;
        int r22 = this.currentIndex;
        if (r02 == false) goto L15;
        int r23 = r22 + 1;
    L17:
        if (PillStackConfig.getInfiniteScrolling() == false) goto L19;
    L22:
        this.currentSwipeProgress = Math.min(r42, 1.0f);
    L23:
        applyProgress(this.currentSwipeProgress, this.isSwipingUp);
        return;
    L19:
        if (r23 >= this.pills.size()) goto L21;
        if (r23 >= 0) goto L22;
    L21:
        this.currentSwipeProgress = r42;
        goto L23
    L15:
        r23 = r22 - 1;
        goto L17
    L11:
        r2 = false;
        goto L12
    }

    private /* synthetic */ void lambda$animateToNextPill$0(boolean r1, ValueAnimator r2) {
        applyProgress(((Float) r2.getAnimatedValue()).floatValue(), r1);
    }

    private /* synthetic */ void lambda$cancelSwipe$1(boolean r1, ValueAnimator r2) {
        applyProgress(((Float) r2.getAnimatedValue()).floatValue(), r1);
    }

    public void addPill(BasePill r3) {
        this.pills.add(r3);
        addView(r3);
        if ((this.pills.size() - 1) == this.currentIndex) goto L5;
        r3.setAlpha(0.0f);
        r3.setScaleX(0.8f);
        r3.setScaleY(0.8f);
        r3.setVisibility(8);
    L6:
        r3.onStackVisibilityChanged(this.stackOnScreen);
        return;
    L5:
        r3.setVisibility(0);
        r3.onPillSelected();
        goto L6
    }

    public void clearPills() {
        if (this.pills.isEmpty() == false) goto L5;
    L7:
        this.pills.clear();
        removeAllViews();
        this.currentIndex = 0;
        return;
    L5:
        if (this.currentIndex >= this.pills.size()) goto L7;
        this.pills.get(this.currentIndex).onPillUnselected();
        goto L7
    }

    public int getPillsCount() {
        return this.pills.size();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r5) {
        if (this.pills.isEmpty() == true) goto L5;
        int r0 = r5.getActionMasked();
        if (r0 != 0) goto L9;
        this.startX = r5.getRawX();
        this.startY = r5.getRawY();
        this.isSwiping = false;
    L32:
        return super.onInterceptTouchEvent(r5);
    L9:
        if (r0 != 2) goto L32;
        float r02 = r5.getRawX() - this.startX;
        float r1 = r5.getRawY() - this.startY;
        if (Math.abs(r1) > this.touchSlop) goto L16;
        if (Math.abs(r02) <= this.touchSlop) goto L32;
    L16:
        if (Math.abs(r1) <= this.touchSlop) goto L32;
        if (this.pills.size() <= 1) goto L32;
        this.isSwiping = true;
        ValueAnimator r03 = this.currentAnimator;
        if (r03 == null) goto L22;
        r03.cancel();
    L22:
        boolean r04 = this.isSwipingUp;
        float r2 = this.currentSwipeProgress;
        if (r04 == false) goto L25;
        float r05 = -(r2 * getHeight());
    L26:
        this.startY = r5.getRawY() - r05;
        if (getParent() == null) goto L29;
        getParent().requestDisallowInterceptTouchEvent(true);
    L29:
        return true;
    L25:
        r05 = getHeight() * r2;
        goto L26
    L5:
        return super.onInterceptTouchEvent(r5);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r7) {
        if (this.pills.isEmpty() == true) goto L5;
        int r0 = r7.getActionMasked();
        if (r0 == 0) goto L59;
        if (r0 != 1) goto L10;
    L43:
        removeCallbacks(this.longPressRunnable);
        if (this.isSwiping == false) goto L47;
        finishSwipe(r7.getRawY() - this.startY);
        this.isSwiping = false;
    L53:
        int r72 = 0;
    L55:
        if (r72 >= this.pills.size()) goto L57;
        this.pills.get(r72).setPressed(false);
        r72 = r72 + 1;
        goto L55
    L57:
        this.maybeClick = false;
        return true;
    L47:
        if (this.maybeClick == false) goto L53;
        if (this.longClickPerformed == true) goto L53;
        if (r7.getActionMasked() != 1) goto L53;
        this.pills.get(this.currentIndex).onPillClicked();
        goto L53
    L10:
        if (r0 == 2) goto L15;
        if (r0 == 3) goto L43;
    L42:
        return super.onTouchEvent(r7);
    L15:
        if (this.longClickPerformed == false) goto L17;
        return true;
    L17:
        float r02 = r7.getRawX() - this.startX;
        float r3 = r7.getRawY() - this.startY;
        if (Math.abs(r3) <= this.touchSlop) goto L20;
    L21:
        this.maybeClick = false;
        removeCallbacks(this.longPressRunnable);
    L23:
        if (this.isSwiping == false) goto L27;
        handleSwipeProgress(r3);
        return true;
    L27:
        if (Math.abs(r3) <= this.touchSlop) goto L42;
        if (this.pills.size() <= 1) goto L42;
        this.isSwiping = true;
        ValueAnimator r03 = this.currentAnimator;
        if (r03 == null) goto L33;
        r03.cancel();
    L33:
        boolean r04 = this.isSwipingUp;
        float r1 = this.currentSwipeProgress;
        if (r04 == false) goto L36;
        float r05 = -(r1 * getHeight());
    L37:
        this.startY = r7.getRawY() - r05;
        if (getParent() == null) goto L40;
        getParent().requestDisallowInterceptTouchEvent(true);
    L40:
        return true;
    L36:
        r05 = getHeight() * r1;
        goto L37
    L20:
        if (Math.abs(r02) <= this.touchSlop) goto L23;
    L59:
        this.startX = r7.getRawX();
        this.startY = r7.getRawY();
        this.isSwiping = false;
        this.maybeClick = true;
        this.longClickPerformed = false;
        removeCallbacks(this.longPressRunnable);
        postDelayed(this.longPressRunnable, ViewConfiguration.getLongPressTimeout());
        BasePill r6 = this.pills.get(this.currentIndex);
        r6.setPressed(true);
        r6.drawableHotspotChanged(r7.getX() - r6.getLeft(), r7.getY() - r6.getTop());
        return true;
    L5:
        return super.onTouchEvent(r7);
    }

    @Override // android.view.View
    public void onVisibilityAggregated(boolean r2) {
        super.onVisibilityAggregated(r2);
        if (this.stackOnScreen == r2) goto L9;
        this.stackOnScreen = r2;
        Iterator<BasePill> r1 = this.pills.iterator();
    L7:
        if (r1.hasNext() == false) goto L11;
        r1.next().onStackVisibilityChanged(r2);
        goto L7
    L11:
        return;
    }

    public void setCurrentIndex(int r3) {
        if (r3 >= 0) goto L4;
        return;
    L4:
        if (r3 >= this.pills.size()) goto L11;
        int r0 = this.currentIndex;
        if (r3 == r0) goto L12;
        BasePill r02 = this.pills.get(r0);
        r02.setVisibility(8);
        r02.onPillUnselected();
        this.currentIndex = r3;
        BasePill r32 = this.pills.get(r3);
        r32.setVisibility(0);
        r32.setAlpha(1.0f);
        r32.setScaleX(1.0f);
        r32.setScaleY(1.0f);
        r32.setTranslationY(0.0f);
        r32.onPillSelected();
        requestLayout();
        return;
    L12:
        return;
    }

    public void setVisibilityFactor(float r3) {
        if (this.visibilityFactor != r3) goto L5;
        return;
    L5:
        this.visibilityFactor = r3;
        if (r3 > 0.01f) goto L8;
        setVisibility(8);
        return;
    L8:
        if (getVisibility() == 0) goto L10;
        setVisibility(0);
    L10:
        setAlpha(this.visibilityFactor);
        setScaleX(AndroidUtilities.lerp(0.6f, 1.0f, this.visibilityFactor));
        setScaleY(AndroidUtilities.lerp(0.6f, 1.0f, this.visibilityFactor));
    }

    public void updateColors() {
        Iterator<BasePill> r1 = this.pills.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().updateColors();
        goto L4
    }
}
