package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.ViewPagerFixed;

/* loaded from: classes7.dex */
public abstract class ChatSearchTabs extends FrameLayout {
    private ValueAnimator actionBarTagsAnimator;
    private float actionBarTagsT;
    private boolean shown;
    public float shownT;
    public ViewPagerFixed.TabsView tabs;

    /* renamed from: org.telegram.ui.Components.ChatSearchTabs$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatSearchTabs this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass1(ChatSearchTabs r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2 == ChatSearchTabs.m9955$$Nest$fgetactionBarTagsAnimator(this.this$0)) goto L5;
            return;
        L5:
            ChatSearchTabs r22 = this.this$0;
            if (this.val$show == false) goto L8;
            float r0 = 1.0f;
        L9:
            ChatSearchTabs.m9957$$Nest$fputactionBarTagsT(r22, r0);
            ChatSearchTabs r23 = this.this$0;
            r23.setShown(ChatSearchTabs.m9956$$Nest$fgetactionBarTagsT(r23));
            if (this.val$show == true) goto L12;
            this.this$0.setVisibility(8);
        L12:
            this.this$0.onShownUpdate(true);
            return;
        L8:
            r0 = 0.0f;
            goto L9
        }
    }

    /* renamed from: $r8$lambda$nDAcewqdKR8g0qZPM-ABt9bTl80, reason: not valid java name */
    public static /* synthetic */ void m9954$r8$lambda$nDAcewqdKR8g0qZPMABt9bTl80(ChatSearchTabs r0, ValueAnimator r1) {
        r0.lambda$show$0(r1);
    }

    /* renamed from: -$$Nest$fgetactionBarTagsAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m9955$$Nest$fgetactionBarTagsAnimator(ChatSearchTabs r0) {
        return r0.actionBarTagsAnimator;
    }

    /* renamed from: -$$Nest$fgetactionBarTagsT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9956$$Nest$fgetactionBarTagsT(ChatSearchTabs r0) {
        return r0.actionBarTagsT;
    }

    /* renamed from: -$$Nest$fputactionBarTagsT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9957$$Nest$fputactionBarTagsT(ChatSearchTabs r0, float r1) {
        r0.actionBarTagsT = r1;
    }

    public ChatSearchTabs(Context r1) {
        super(r1);
    }

    private /* synthetic */ void lambda$show$0(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.actionBarTagsT = r12;
        setShown(r12);
        onShownUpdate(false);
    }

    public int getCurrentHeight() {
        return (int) (getMeasuredHeight() * this.shownT);
    }

    @Override // android.view.View
    public boolean isShown() {
        return this.shown;
    }

    public abstract void onShownUpdate(boolean r1);

    public void setShown(float r5) {
        this.shownT = r5;
        ViewPagerFixed.TabsView r0 = this.tabs;
        if (r0 == null) goto L5;
        r0.setPivotX(r0.getWidth() / 2.0f);
        this.tabs.setPivotY(0.0f);
        this.tabs.setScaleX(AndroidUtilities.lerp(0.8f, 1.0f, r5));
        this.tabs.setScaleY(AndroidUtilities.lerp(0.8f, 1.0f, r5));
    L5:
        setAlpha(r5);
        invalidate();
    }

    public void setTabs(ViewPagerFixed.TabsView r3) {
        this.tabs = r3;
        addView(r3, LayoutHelper.createFrame(-1, -1.0f));
    }

    public void show(boolean r5) {
        this.shown = r5;
        ValueAnimator r0 = this.actionBarTagsAnimator;
        if (r0 == null) goto L6;
        this.actionBarTagsAnimator = null;
        r0.cancel();
    L6:
        if (r5 == false) goto L8;
        setVisibility(0);
    L8:
        float r1 = this.actionBarTagsT;
        if (r5 == false) goto L11;
        float r2 = 1.0f;
    L12:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{r1, r2});
        this.actionBarTagsAnimator = r02;
        r02.addUpdateListener(new ChatSearchTabs$$ExternalSyntheticLambda0(this));
        this.actionBarTagsAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.actionBarTagsAnimator.setDuration(320);
        this.actionBarTagsAnimator.addListener(new AnonymousClass1(this, r5));
        this.actionBarTagsAnimator.start();
        return;
    L11:
        r2 = 0.0f;
        goto L12
    }

    public boolean shown() {
        if (this.shownT <= 0.5f) goto L6;
        return true;
    L6:
        return false;
    }
}
