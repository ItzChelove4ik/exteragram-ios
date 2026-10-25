package org.telegram.ui.ActionBar;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public class AlertDialogDecor extends AlertDialog {
    private static final int[] ATTRS = null;
    private View contentView;
    private View dimView;
    private boolean isDismissed;
    private DialogInterface.OnDismissListener onDismissListener;
    private DialogInterface.OnShowListener onShowListener;
    private long openDelay;
    private int resEnterAnimation;
    private int resExitAnimation;
    private View rootView;
    private final Runnable showRunnable;

    /* renamed from: org.telegram.ui.ActionBar.AlertDialogDecor$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ AlertDialogDecor this$0;

        public AnonymousClass1(AlertDialogDecor r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (AlertDialogDecor.m5656$$Nest$fgetonShowListener(this.this$0) == null) goto L6;
            AlertDialogDecor.m5656$$Nest$fgetonShowListener(this.this$0).onShow(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AlertDialogDecor$2, reason: invalid class name */
    public class AnonymousClass2 implements Animation.AnimationListener {
        final /* synthetic */ AlertDialogDecor this$0;

        public AnonymousClass2(AlertDialogDecor r1) {
            this.this$0 = r1;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation r1) {
            AlertDialogDecor.m5654$$Nest$fgetcontentView(this.this$0).setAlpha(0.0f);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation r1) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation r1) {
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AlertDialogDecor$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ AlertDialogDecor this$0;

        public AnonymousClass3(AlertDialogDecor r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            AlertDialogDecor.m5658$$Nest$mgetDecorView(this.this$0).removeView(AlertDialogDecor.m5657$$Nest$fgetrootView(this.this$0));
            if (AlertDialogDecor.m5655$$Nest$fgetonDismissListener(this.this$0) == null) goto L6;
            AlertDialogDecor.m5655$$Nest$fgetonDismissListener(this.this$0).onDismiss(this.this$0);
            return;
        }
    }

    public static class Builder extends AlertDialog.Builder {
        public Builder(Context r2) {
            super(r2, null);
        }

        public Builder(Context r2, Theme.ResourcesProvider r3) {
            super(r2, 0, r3);
        }

        @Override // org.telegram.ui.ActionBar.AlertDialog.Builder
        public AlertDialog createAlertDialog(Context r1, int r2, Theme.ResourcesProvider r3) {
            return new AlertDialogDecor(r1, r2, r3);
        }
    }

    /* renamed from: $r8$lambda$0xmZb_aOu-YsP79NnQgHAjXxu5Y, reason: not valid java name */
    public static /* synthetic */ void m5653$r8$lambda$0xmZb_aOuYsP79NnQgHAjXxu5Y(AlertDialogDecor r0, View r1) {
        r0.lambda$show$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$7qU8gXkNODsF6GRbUp7KZTMCvvs(AlertDialogDecor r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$MfRho_Mx6ljB7hFhFOV_Aw5clhE(FrameLayout r4, View r5, WindowInsetsCompat r6) {
        Rect r52 = new Rect();
        if (Build.VERSION.SDK_INT < 30) goto L5;
        Insets r0 = r6.getInsets(WindowInsetsCompat.Type.ime() | WindowInsetsCompat.Type.systemBars());
        r52.set(r0.left, r0.top, r0.right, r0.bottom);
    L6:
        r4.setPadding(r52.left, r52.top, r52.right, r52.bottom + AndroidUtilities.navigationBarHeight);
        r4.requestLayout();
        return r6;
    L5:
        r52.set(r6.getStableInsetLeft(), r6.getStableInsetTop(), r6.getStableInsetRight(), r6.getStableInsetBottom());
        goto L6
    }

    /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m5654$$Nest$fgetcontentView(AlertDialogDecor r0) {
        return r0.contentView;
    }

    /* renamed from: -$$Nest$fgetonDismissListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogInterface.OnDismissListener m5655$$Nest$fgetonDismissListener(AlertDialogDecor r0) {
        return r0.onDismissListener;
    }

    /* renamed from: -$$Nest$fgetonShowListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogInterface.OnShowListener m5656$$Nest$fgetonShowListener(AlertDialogDecor r0) {
        return r0.onShowListener;
    }

    /* renamed from: -$$Nest$fgetrootView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m5657$$Nest$fgetrootView(AlertDialogDecor r0) {
        return r0.rootView;
    }

    /* renamed from: -$$Nest$mgetDecorView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m5658$$Nest$mgetDecorView(AlertDialogDecor r0) {
        return r0.getDecorView();
    }

    static {
        ATTRS = new int[]{R.attr.windowEnterAnimation, R.attr.windowExitAnimation};
    }

    public AlertDialogDecor(Context r1, int r2, Theme.ResourcesProvider r3) {
        super(r1, r2, r3);
        this.isDismissed = false;
        this.openDelay = 0;
        this.showRunnable = new AlertDialogDecor$$ExternalSyntheticLambda0(this);
    }

    private void extractAnimations() {
        TypedValue r0 = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.windowAnimationStyle, r0, true);
        TypedArray r02 = getContext().obtainStyledAttributes(r0.resourceId, ATTRS);
        this.resEnterAnimation = r02.getResourceId(0, -1);
        this.resExitAnimation = r02.getResourceId(1, -1);
        r02.recycle();
    }

    private Activity getActivity(Context r2) {
        if ((r2 instanceof Activity) == false) goto L7;
        return (Activity) r2;
    L7:
        if ((r2 instanceof ContextThemeWrapper) == true) goto L9;
        return null;
    L9:
        return getActivity(((ContextThemeWrapper) r2).getBaseContext());
    }

    private ViewGroup getDecorView() {
        return (ViewGroup) getActivity(getContext()).getWindow().getDecorView();
    }

    private /* synthetic */ void lambda$new$0() {
        this.rootView.setVisibility(0);
        this.dimView.setAlpha(0.0f);
        this.contentView.startAnimation(AnimationUtils.loadAnimation(getContext(), this.resEnterAnimation));
        this.dimView.animate().setDuration(300).alpha(1.0f).setListener(new AnonymousClass1(this)).start();
    }

    private /* synthetic */ void lambda$show$1(View r1) {
        dismiss();
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (isShowing() == true) goto L6;
        return;
    L6:
        if (this.isDismissed == false) goto L8;
        return;
    L8:
        this.isDismissed = true;
        AndroidUtilities.cancelRunOnUIThread(this.showRunnable);
        if (this.rootView.getVisibility() == 0) goto L12;
        getDecorView().removeView(this.rootView);
        return;
    L12:
        Animation r0 = AnimationUtils.loadAnimation(getContext(), this.resExitAnimation);
        r0.setAnimationListener(new AnonymousClass2(this));
        this.contentView.clearAnimation();
        this.contentView.startAnimation(r0);
        this.dimView.animate().setListener(null).cancel();
        this.dimView.animate().setDuration(300).alpha(0.0f).setListener(new AnonymousClass3(this)).start();
    }

    @Override // android.app.Dialog
    public boolean isShowing() {
        if (getDecorView().indexOfChild(this.rootView) != (-1)) goto L5;
        return false;
    L5:
        if (this.isDismissed == true) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.app.Dialog
    public void setOnDismissListener(DialogInterface.OnDismissListener r1) {
        this.onDismissListener = r1;
    }

    @Override // android.app.Dialog
    public void setOnShowListener(DialogInterface.OnShowListener r1) {
        this.onShowListener = r1;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog, android.app.Dialog
    public void show() {
        extractAnimations();
        setDismissDialogByButtons(true);
        View r1 = inflateContent(false);
        this.contentView = r1;
        r1.setClickable(true);
        WindowManager.LayoutParams r0 = getWindow().getAttributes();
        FrameLayout r12 = new FrameLayout(getContext());
        r12.setOnClickListener(new AlertDialogDecor$$ExternalSyntheticLambda1(this));
        View r2 = new View(getContext());
        this.dimView = r2;
        r2.setBackgroundColor(Theme.multAlpha(-16777216, r0.dimAmount));
        r12.addView(this.dimView, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout r22 = new FrameLayout(getContext());
        r22.addView(this.contentView, new FrameLayout.LayoutParams(-1, -2, 17));
        r12.addView(r22, new FrameLayout.LayoutParams(r0.width, -2, 17));
        this.rootView = r12;
        getDecorView().addView(this.rootView);
        ViewCompat.requestApplyInsets(this.rootView);
        ViewCompat.setOnApplyWindowInsetsListener(this.rootView, new AlertDialogDecor$$ExternalSyntheticLambda2(r22));
        this.rootView.setVisibility(4);
        long r02 = this.openDelay;
        Runnable r8 = this.showRunnable;
        if (r02 != 0) goto L6;
        r8.run();
        return;
    L6:
        AndroidUtilities.runOnUIThread(r8, r02);
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog
    public void showDelayed(long r2) {
        if (isShowing() == false) goto L5;
        return;
    L5:
        this.openDelay = r2;
        show();
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog
    public boolean supportsNativeBlur() {
        return false;
    }
}
