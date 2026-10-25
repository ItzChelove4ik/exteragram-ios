package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class Tooltip extends TextView {
    private View anchor;
    private ViewPropertyAnimator animator;
    Runnable dismissRunnable;
    private boolean showing;

    /* renamed from: org.telegram.ui.Components.Tooltip$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ Tooltip this$0;

        public AnonymousClass1(Tooltip r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.setVisibility(8);
        }
    }

    /* renamed from: $r8$lambda$HNJOZpHgeJro-gUpgNv42jU_PiU, reason: not valid java name */
    public static /* synthetic */ void m13320$r8$lambda$HNJOZpHgeJrogUpgNv42jU_PiU(Tooltip r0) {
        r0.lambda$new$0();
    }

    public Tooltip(Context r8, ViewGroup r9, int r10, int r11) {
        super(r8);
        this.dismissRunnable = new Tooltip$$ExternalSyntheticLambda0(this);
        setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(3.0f), r10));
        setTextColor(r11);
        setTextSize(1, 14.0f);
        setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(7.0f));
        setGravity(16);
        r9.addView(this, LayoutHelper.createFrame(-2, -2.0f, 51, 5.0f, 0.0f, 5.0f, 3.0f));
        setVisibility(8);
    }

    private /* synthetic */ void lambda$new$0() {
        ViewPropertyAnimator r0 = animate().alpha(0.0f).setListener(new AnonymousClass1(this)).setDuration(300);
        this.animator = r0;
        r0.start();
    }

    private void updateTooltipPosition() {
        if (this.anchor != null) goto L5;
        return;
    L5:
        View r0 = (View) getParent();
        View r1 = this.anchor;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
    L6:
        if (r1 == r0) goto L8;
        r4 = r4 + r1.getTop();
        r3 = r3 + r1.getLeft();
        r1 = (View) r1.getParent();
        goto L6
    L8:
        int r32 = (r3 + (this.anchor.getWidth() / 2)) - (getMeasuredWidth() / 2);
        if (r32 >= 0) goto L12;
    L15:
        setTranslationX(r2);
        setTranslationY(r4 - getMeasuredHeight());
        return;
    L12:
        if ((getMeasuredWidth() + r32) <= r0.getMeasuredWidth()) goto L14;
        r2 = (r0.getMeasuredWidth() - getMeasuredWidth()) - AndroidUtilities.dp(16.0f);
        goto L15
    L14:
        r2 = r32;
        goto L15
    }

    public void hide() {
        if (this.showing == false) goto L8;
        ViewPropertyAnimator r0 = this.animator;
        if (r0 == null) goto L7;
        r0.setListener(null);
        this.animator.cancel();
        this.animator = null;
    L7:
        AndroidUtilities.cancelRunOnUIThread(this.dismissRunnable);
        this.dismissRunnable.run();
    L8:
        this.showing = false;
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updateTooltipPosition();
    }

    public void show(View r4) {
        if (r4 == null) goto L12;
        this.anchor = r4;
        updateTooltipPosition();
        this.showing = true;
        AndroidUtilities.cancelRunOnUIThread(this.dismissRunnable);
        AndroidUtilities.runOnUIThread(this.dismissRunnable, 2000);
        ViewPropertyAnimator r42 = this.animator;
        if (r42 == null) goto L8;
        r42.setListener(null);
        this.animator.cancel();
        this.animator = null;
    L8:
        if (getVisibility() == 0) goto L11;
        setAlpha(0.0f);
        setVisibility(0);
        ViewPropertyAnimator r43 = animate().setDuration(300).alpha(1.0f).setListener(null);
        this.animator = r43;
        r43.start();
        return;
    L11:
        return;
    }
}
