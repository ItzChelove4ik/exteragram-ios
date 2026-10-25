package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import androidx.core.math.MathUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.EmojiTabsStrip;

/* loaded from: classes7.dex */
abstract class ScrollableHorizontalScrollView extends HorizontalScrollView {
    public LinearLayout contentView;
    private ValueAnimator scrollAnimator;
    protected boolean scrollingAnimation;
    private int scrollingTo;
    ValueAnimator showAnimator;
    private boolean touch;
    boolean touching;

    /* renamed from: org.telegram.ui.Components.ScrollableHorizontalScrollView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ScrollableHorizontalScrollView this$0;

        public AnonymousClass1(ScrollableHorizontalScrollView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.scrollingAnimation = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            ScrollableHorizontalScrollView r22 = this.this$0;
            r22.scrollingAnimation = true;
            if ((r22.getParent() instanceof HorizontalScrollView) == false) goto L6;
            ((HorizontalScrollView) this.this$0.getParent()).requestDisallowInterceptTouchEvent(false);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$rbo03_gIMgUh4ECoC8L_HuJET4I(ScrollableHorizontalScrollView r0, ValueAnimator r1) {
        r0.lambda$scrollTo$0(r1);
    }

    public ScrollableHorizontalScrollView(Context r1) {
        super(r1);
        this.scrollingTo = -1;
    }

    private /* synthetic */ void lambda$scrollTo$0(ValueAnimator r1) {
        setScrollX((int) ((Float) r1.getAnimatedValue()).floatValue());
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updateButtonsVisibility();
    }

    @Override // android.view.View
    public void onScrollChanged(int r1, int r2, int r3, int r4) {
        super.onScrollChanged(r1, r2, r3, r4);
        if (Math.abs(r2 - r4) < 2) goto L8;
        if (r2 >= getMeasuredHeight()) goto L8;
        if (r2 == 0) goto L8;
    L10:
        updateButtonsVisibility();
        return;
    L8:
        if (this.touching == true) goto L10;
        requestDisallowInterceptTouchEvent(false);
        goto L10
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        if (r3.getAction() != 0) goto L6;
        this.touch = true;
    L11:
        return super.onTouchEvent(r3);
    L6:
        if (r3.getAction() != 1) goto L8;
    L9:
        this.touch = false;
        goto L11
    L8:
        if (r3.getAction() != 3) goto L11;
        goto L9
    }

    public void resetScrollTo() {
        this.scrollingTo = -1;
    }

    public void scrollTo(int r4) {
        if (this.scrollingTo == r4) goto L13;
        this.scrollingTo = r4;
        ValueAnimator r0 = this.scrollAnimator;
        if (r0 == null) goto L9;
        r0.cancel();
    L9:
        if (getScrollX() != r4) goto L11;
        return;
    L11:
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{getScrollX(), r4});
        this.scrollAnimator = r42;
        r42.addUpdateListener(new ScrollableHorizontalScrollView$$ExternalSyntheticLambda0(this));
        this.scrollAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.scrollAnimator.setDuration(250);
        this.scrollAnimator.addListener(new AnonymousClass1(this));
        this.scrollAnimator.start();
        return;
    }

    public boolean scrollToVisible(int r4, int r5) {
        if (getChildCount() > 0) goto L5;
        return false;
    L5:
        int r0 = AndroidUtilities.dp(50.0f);
        if (r4 >= (getScrollX() + r0)) goto L9;
        int r42 = r4 - r0;
    L11:
        scrollTo(MathUtils.clamp(r42, 0, getChildAt(0).getMeasuredWidth() - getMeasuredWidth()));
        return true;
    L9:
        if (r5 <= (getScrollX() + (getMeasuredWidth() - r0))) goto L13;
        r42 = (r5 - getMeasuredWidth()) + r0;
        goto L11
    L13:
        return false;
    }

    public void updateButtonsVisibility() {
        int r0 = this.contentView.getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L22;
        View r3 = this.contentView.getChildAt(r2);
        if ((r3 instanceof EmojiTabsStrip.EmojiTabButton) == false) goto L21;
        EmojiTabsStrip.EmojiTabButton r4 = (EmojiTabsStrip.EmojiTabButton) r3;
        boolean r6 = true;
        if ((r3.getRight() - getScrollX()) > 0) goto L9;
    L11:
        boolean r32 = false;
    L13:
        if (this.scrollingAnimation == false) goto L19;
        ValueAnimator r5 = this.showAnimator;
        if (r5 != null) goto L17;
    L20:
        r4.updateVisibilityInbounds(r32, r6);
        goto L21
    L17:
        if (r5.isRunning() == false) goto L20;
    L19:
        r6 = false;
        goto L20
    L9:
        if ((r3.getLeft() - getScrollX()) >= getMeasuredWidth()) goto L11;
        r32 = true;
    L21:
        r2 = r2 + 1;
        goto L3
    }
}
