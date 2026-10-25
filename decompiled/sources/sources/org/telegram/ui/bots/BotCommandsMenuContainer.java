package org.telegram.ui.bots;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;

/* loaded from: classes7.dex */
public abstract class BotCommandsMenuContainer extends FrameLayout {
    private BlurredBackgroundDrawable backgroundDrawable;
    private float containerY;
    private ObjectAnimator currentAnimation;
    boolean dismissed;
    private boolean entering;
    public RecyclerListView listView;
    private NestedScrollingParentHelper nestedScrollingParentHelper;
    float scrollYOffset;
    Paint topBackground;

    /* renamed from: org.telegram.ui.bots.BotCommandsMenuContainer$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView {
        final /* synthetic */ BotCommandsMenuContainer this$0;

        public AnonymousClass1(BotCommandsMenuContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            if (this.this$0.listView.getLayoutManager() != null) goto L5;
        L14:
            super.dispatchDraw(r9);
            return;
        L5:
            if (this.this$0.listView.getAdapter() == null) goto L14;
            if (this.this$0.listView.getAdapter().getItemCount() == 0) goto L14;
            float r0 = this.this$0.scrollYOffset - AndroidUtilities.dp(8.0f);
            BotCommandsMenuContainer.m21314$$Nest$fputcontainerY(this.this$0, r0 - AndroidUtilities.dp(16.0f));
            if (BotCommandsMenuContainer.m21313$$Nest$fgetbackgroundDrawable(this.this$0) == null) goto L12;
            BotCommandsMenuContainer.m21313$$Nest$fgetbackgroundDrawable(this.this$0).draw(r9);
        L12:
            RectF r1 = AndroidUtilities.rectTmp;
            r1.set((getMeasuredWidth() / 2.0f) - AndroidUtilities.dp(12.0f), r0 - AndroidUtilities.dp(4.0f), (getMeasuredWidth() / 2.0f) + AndroidUtilities.dp(12.0f), r0);
            r9.drawRoundRect(r1, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.this$0.topBackground);
            super.dispatchDraw(r9);
        }
    }

    /* renamed from: org.telegram.ui.bots.BotCommandsMenuContainer$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ BotCommandsMenuContainer this$0;

        public AnonymousClass2(BotCommandsMenuContainer r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            View r12 = this.this$0.listView.getLayoutManager().findViewByPosition(0);
            float r22 = 0.0f;
            if (r12 == null) goto L5;
            float r13 = r12.getY();
        L7:
            if (r13 < 0.0f) goto L10;
            r22 = r13;
        L10:
            BotCommandsMenuContainer r0 = this.this$0;
            r0.scrollYOffset = r22;
            BotCommandsMenuContainer.m21316$$Nest$mcheckBackgroundBounds(r0);
            return;
        L5:
            r13 = 0.0f;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.bots.BotCommandsMenuContainer$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ BotCommandsMenuContainer this$0;

        public AnonymousClass3(BotCommandsMenuContainer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.setVisibility(8);
            BotCommandsMenuContainer.m21315$$Nest$fputcurrentAnimation(this.this$0, null);
        }
    }

    /* renamed from: -$$Nest$fgetbackgroundDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawable m21313$$Nest$fgetbackgroundDrawable(BotCommandsMenuContainer r0) {
        return r0.backgroundDrawable;
    }

    /* renamed from: -$$Nest$fputcontainerY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21314$$Nest$fputcontainerY(BotCommandsMenuContainer r0, float r1) {
        r0.containerY = r1;
    }

    /* renamed from: -$$Nest$fputcurrentAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21315$$Nest$fputcurrentAnimation(BotCommandsMenuContainer r0, ObjectAnimator r1) {
        r0.currentAnimation = r1;
    }

    /* renamed from: -$$Nest$mcheckBackgroundBounds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21316$$Nest$mcheckBackgroundBounds(BotCommandsMenuContainer r0) {
        r0.checkBackgroundBounds();
    }

    public BotCommandsMenuContainer(Context r3) {
        super(r3);
        this.currentAnimation = null;
        this.topBackground = new Paint(1);
        this.dismissed = true;
        this.nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        AnonymousClass1 r0 = new AnonymousClass1(this, r3);
        this.listView = r0;
        r0.setOverScrollMode(2);
        this.listView.setClipToPadding(false);
        this.listView.setClipToOutline(true);
        this.listView.addOnScrollListener(new AnonymousClass2(this));
        addView(this.listView);
        updateColors();
        setClipChildren(false);
    }

    private void cancelCurrentAnimation() {
        ObjectAnimator r0 = this.currentAnimation;
        if (r0 == null) goto L6;
        r0.removeAllListeners();
        this.currentAnimation.cancel();
        this.currentAnimation = null;
        return;
    }

    private void checkBackgroundBounds() {
        BlurredBackgroundDrawable r0 = this.backgroundDrawable;
        if (r0 == null) goto L6;
        r0.setBounds(0, ((int) this.scrollYOffset) - AndroidUtilities.dp(25.0f), getMeasuredWidth(), getMeasuredHeight() + AndroidUtilities.dp(5.0f));
        this.listView.invalidateOutline();
        this.listView.invalidate();
        return;
    }

    private void checkDismiss() {
        if (this.dismissed == false) goto L6;
        return;
    L6:
        if (this.listView.getTranslationY() <= AndroidUtilities.dp(16.0f)) goto L9;
        dismiss();
        return;
    L9:
        playEnterAnim(false);
    }

    private void playEnterAnim(boolean r5) {
        if (this.dismissed == false) goto L5;
        return;
    L5:
        RecyclerListView r0 = this.listView;
        ObjectAnimator r02 = ObjectAnimator.ofFloat(r0, FrameLayout.TRANSLATION_Y, new float[]{r0.getTranslationY(), 0.0f});
        this.currentAnimation = r02;
        if (r5 == false) goto L8;
        r02.setDuration(320);
        this.currentAnimation.setInterpolator(new OvershootInterpolator(0.8f));
    L9:
        this.currentAnimation.start();
        return;
    L8:
        r02.setDuration(150);
        this.currentAnimation.setInterpolator(CubicBezierInterpolator.DEFAULT);
        goto L9
    }

    public float clipBottom() {
        if (this.dismissed == false) goto L6;
        return 0.0f;
    L6:
        return Math.max(0.0f, getMeasuredHeight() - (this.containerY + this.listView.getTranslationY()));
    }

    public void dismiss() {
        if (this.dismissed == true) goto L6;
        this.dismissed = true;
        cancelCurrentAnimation();
        RecyclerListView r1 = this.listView;
        ObjectAnimator r0 = ObjectAnimator.ofFloat(r1, FrameLayout.TRANSLATION_Y, new float[]{r1.getTranslationY(), (getMeasuredHeight() - this.scrollYOffset) + AndroidUtilities.dp(40.0f)});
        this.currentAnimation = r0;
        r0.addListener(new AnonymousClass3(this));
        this.currentAnimation.setDuration(150);
        this.currentAnimation.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.currentAnimation.start();
        onDismiss();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r4) {
        if (r4.getAction() != 0) goto L9;
        if (r4.getY() >= (this.scrollYOffset - AndroidUtilities.dp(24.0f))) goto L9;
        return false;
    L9:
        return super.dispatchTouchEvent(r4);
    }

    public RecyclerListView getListView() {
        return this.listView;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.nestedScrollingParentHelper.getNestedScrollAxes();
    }

    public void onDismiss() {
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, r3);
        if (this.entering == true) goto L5;
    L7:
        checkBackgroundBounds();
        return;
    L5:
        if (this.dismissed == true) goto L7;
        this.listView.setTranslationY((r2.getMeasuredHeight() - this.listView.getPaddingTop()) + AndroidUtilities.dp(16.0f));
        playEnterAnim(true);
        this.entering = false;
        goto L7
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View r1, float r2, float r3, boolean r4) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View r1, float r2, float r3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View r2, int r3, int r4, int[] r5) {
        if (this.dismissed == true) goto L16;
        cancelCurrentAnimation();
        float r22 = this.listView.getTranslationY();
        float r32 = 0.0f;
        if (r22 <= 0.0f) goto L14;
        if (r4 <= 0) goto L15;
        float r23 = r22 - r4;
        r5[1] = r4;
        if (r23 < 0.0f) goto L12;
        r32 = r23;
    L12:
        this.listView.setTranslationY(r32);
        invalidate();
        return;
    L15:
        return;
    L14:
        return;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View r1, int r2, int r3, int r4, int r5) {
        if (this.dismissed == true) goto L13;
        cancelCurrentAnimation();
        if (r5 == 0) goto L12;
        float r12 = this.listView.getTranslationY() - r5;
        if (r12 >= 0.0f) goto L10;
        r12 = 0.0f;
    L10:
        this.listView.setTranslationY(r12);
        invalidate();
        return;
    L12:
        return;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View r2, View r3, int r4) {
        this.nestedScrollingParentHelper.onNestedScrollAccepted(r2, r3, r4);
        if (this.dismissed == false) goto L5;
        return;
    L5:
        cancelCurrentAnimation();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View r1, View r2, int r3) {
        if (this.dismissed == false) goto L5;
        return false;
    L5:
        if (r3 != 2) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View r2) {
        this.nestedScrollingParentHelper.onStopNestedScroll(r2);
        if (this.dismissed == false) goto L5;
        return;
    L5:
        checkDismiss();
    }

    public void setBackgroundDrawable(BlurredBackgroundDrawable r3) {
        this.backgroundDrawable = r3;
        r3.setRadius(AndroidUtilities.dp(22.0f));
        this.backgroundDrawable.setPadding(AndroidUtilities.dp(5.0f));
        this.listView.setOutlineProvider(r3.getViewOutlineProvider());
    }

    public void show() {
        if (getVisibility() == 0) goto L7;
        setVisibility(0);
        this.listView.scrollToPosition(0);
        this.entering = true;
        this.dismissed = false;
        return;
    L7:
        if (this.dismissed == false) goto L10;
        this.dismissed = false;
        cancelCurrentAnimation();
        playEnterAnim(false);
        return;
    }

    public void updateColors() {
        this.topBackground.setColor(Theme.getColor(Theme.key_sheet_scrollUp));
        BlurredBackgroundDrawable r0 = this.backgroundDrawable;
        if (r0 == null) goto L5;
        r0.updateColors();
    L5:
        invalidate();
    }
}
