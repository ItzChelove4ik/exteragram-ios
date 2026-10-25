package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public class Snackbar extends BaseTransientBottomBar<Snackbar> {
    private static final int[] SNACKBAR_BUTTON_STYLE_ATTR = null;
    private static final int[] SNACKBAR_CONTENT_STYLE_ATTRS = null;
    private final AccessibilityManager accessibilityManager;
    private BaseTransientBottomBar.BaseCallback<Snackbar> callback;
    private boolean hasAction;

    public static class Callback extends BaseTransientBottomBar.BaseCallback<Snackbar> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        public Callback() {
        }

        /* renamed from: onDismissed, reason: avoid collision after fix types in other method */
        public void onDismissed2(Snackbar r1, int r2) {
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
        public /* bridge */ /* synthetic */ void onDismissed(Snackbar r1, int r2) {
            onDismissed2(r1, r2);
        }

        /* renamed from: onShown, reason: avoid collision after fix types in other method */
        public void onShown2(Snackbar r1) {
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
        public /* bridge */ /* synthetic */ void onShown(Snackbar r1) {
            onShown2(r1);
        }
    }

    public static final class SnackbarLayout extends BaseTransientBottomBar.SnackbarBaseLayout {
        public SnackbarLayout(Context r1) {
            super(r1);
        }

        public SnackbarLayout(Context r1, AttributeSet r2) {
            super(r1, r2);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            super.onMeasure(r6, r7);
            int r62 = getChildCount();
            int r72 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            int r0 = 0;
        L3:
            if (r0 >= r62) goto L8;
            View r1 = getChildAt(r0);
            if (r1.getLayoutParams().width != (-1)) goto L7;
            r1.measure(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1.getMeasuredHeight(), TLObject.FLAG_30));
        L7:
            r0 = r0 + 1;
            goto L3
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackground(Drawable r1) {
            super.setBackground(r1);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundDrawable(Drawable r1) {
            super.setBackgroundDrawable(r1);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundTintList(ColorStateList r1) {
            super.setBackgroundTintList(r1);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundTintMode(PorterDuff.Mode r1) {
            super.setBackgroundTintMode(r1);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setLayoutParams(ViewGroup.LayoutParams r1) {
            super.setLayoutParams(r1);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setOnClickListener(View.OnClickListener r1) {
            super.setOnClickListener(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$ZL3iUXwiA9cXrXziL1n5sSCbc_4(Snackbar r0, View.OnClickListener r1, View r2) {
        r0.getClass();
        r1.onClick(r2);
        r0.dispatchDismiss(1);
    }

    /* renamed from: $r8$lambda$xk-LJ2OJECmZliggljX7UvCDyDw, reason: not valid java name */
    public static /* synthetic */ void m1904$r8$lambda$xkLJ2OJECmZliggljX7UvCDyDw(Snackbar r0, View r1) {
        r0.dismiss();
    }

    static {
        int r0 = R.attr.snackbarButtonStyle;
        SNACKBAR_BUTTON_STYLE_ATTR = new int[]{r0};
        SNACKBAR_CONTENT_STYLE_ATTRS = new int[]{r0, R.attr.snackbarTextViewStyle};
    }

    private Snackbar(Context r1, ViewGroup r2, View r3, ContentViewCallback r4) {
        super(r1, r2, r3, r4);
        this.accessibilityManager = (AccessibilityManager) r2.getContext().getSystemService("accessibility");
    }

    private static ViewGroup findSuitableParent(View r3) {
        ViewGroup r1 = null;
    L4:
        if ((r3 instanceof CoordinatorLayout) == true) goto L6;
        if ((r3 instanceof FrameLayout) == true) goto L10;
    L14:
        if (r3 == null) goto L19;
        Object r32 = r3.getParent();
        if ((r32 instanceof View) == false) goto L18;
        r3 = (View) r32;
        goto L19
    L18:
        r3 = null;
    L19:
        if (r3 != null) goto L4;
        return r1;
    L10:
        if (r3.getId() == 16908290) goto L12;
        r1 = (ViewGroup) r3;
        goto L14
    L12:
        return (ViewGroup) r3;
    L6:
        return (ViewGroup) r3;
    }

    private Button getActionView() {
        return getContentLayout().getActionView();
    }

    private MaterialButton getCloseViewOrThrow() {
        if ((getContentLayout().getCloseView() instanceof MaterialButton) == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("The layout of this snackbar does not include a close MaterialButton. This might be because the context's theme is not a Material 3 or later theme or because the Snackbar's layout has been replaced with a custom layout.");
        return null;
    L5:
        return (MaterialButton) getContentLayout().getCloseView();
    }

    private SnackbarContentLayout getContentLayout() {
        return (SnackbarContentLayout) this.view.getChildAt(0);
    }

    private TextView getMessageView() {
        return getContentLayout().getMessageView();
    }

    private BaseTransientBottomBar.SnackbarBaseLayout getSnackbarLayout() {
        return this.view;
    }

    @Deprecated
    public static boolean hasSnackbarButtonStyleAttr(Context r3) {
        TypedArray r32 = r3.obtainStyledAttributes(SNACKBAR_BUTTON_STYLE_ATTR);
        int r2 = r32.getResourceId(0, -1);
        r32.recycle();
        if (r2 == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean hasSnackbarContentStyleAttrs(Context r5) {
        TypedArray r52 = r5.obtainStyledAttributes(SNACKBAR_CONTENT_STYLE_ATTRS);
        int r2 = r52.getResourceId(0, -1);
        int r4 = r52.getResourceId(1, -1);
        r52.recycle();
        if (r2 == (-1)) goto L6;
        if (r4 == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public static Snackbar make(Context r0, View r1, CharSequence r2, int r3) {
        return makeInternal(r0, r1, r2, r3);
    }

    public static Snackbar make(View r1, int r2, int r3) {
        return make(r1, r1.getResources().getText(r2), r3);
    }

    public static Snackbar make(View r1, CharSequence r2, int r3) {
        return makeInternal(null, r1, r2, r3);
    }

    private static Snackbar makeInternal(Context r3, View r4, CharSequence r5, int r6) {
        ViewGroup r42 = findSuitableParent(r4);
        if (r42 == null) goto L12;
        if (r3 != null) goto L6;
        r3 = r42.getContext();
    L6:
        LayoutInflater r0 = LayoutInflater.from(r3);
        if (hasSnackbarContentStyleAttrs(r3) == false) goto L9;
        int r1 = R.layout.mtrl_layout_snackbar_include;
    L10:
        SnackbarContentLayout r02 = (SnackbarContentLayout) r0.inflate(r1, r42, false);
        Snackbar r12 = new Snackbar(r3, r42, r02, r02);
        r12.setText(r5);
        r12.setDuration(r6);
        return r12;
    L9:
        r1 = R.layout.design_layout_snackbar_include;
        goto L10
    L12:
        f$$ExternalSyntheticBUOutline1.m("No suitable parent found from the given view. Please provide a valid view.");
        return null;
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public void dismiss() {
        super.dismiss();
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public int getDuration() {
        int r0 = super.getDuration();
        if (r0 != (-2)) goto L5;
        return -2;
    L5:
        int r2 = Build.VERSION.SDK_INT;
        boolean r3 = this.hasAction;
        if (r2 < 29) goto L12;
        if (r3 == false) goto L9;
        int r1 = 4;
    L11:
        return this.accessibilityManager.getRecommendedTimeoutMillis(r0, r1 | 3);
    L9:
        r1 = 0;
        goto L11
    L12:
        if (r3 == true) goto L14;
    L16:
        return r0;
    L14:
        if (this.accessibilityManager.isTouchExplorationEnabled() == false) goto L16;
        return -2;
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public boolean isShown() {
        return super.isShown();
    }

    public Snackbar setAction(int r2, View.OnClickListener r3) {
        return setAction(getContext().getText(r2), r3);
    }

    public Snackbar setAction(CharSequence r4, View.OnClickListener r5) {
        Button r0 = getActionView();
        if (TextUtils.isEmpty(r4) == true) goto L8;
        if (r5 == null) goto L8;
        this.hasAction = true;
        r0.setVisibility(0);
        r0.setText(r4);
        r0.setOnClickListener(new Snackbar$$ExternalSyntheticLambda0(this, r5));
        return this;
    L8:
        r0.setVisibility(8);
        r0.setOnClickListener(null);
        this.hasAction = false;
        return this;
    }

    public Snackbar setActionTextColor(int r2) {
        getActionView().setTextColor(r2);
        return this;
    }

    public Snackbar setActionTextColor(ColorStateList r2) {
        getActionView().setTextColor(r2);
        return this;
    }

    public Snackbar setBackgroundTint(int r1) {
        return setBackgroundTintList(ColorStateList.valueOf(r1));
    }

    public Snackbar setBackgroundTintList(ColorStateList r2) {
        this.view.setBackgroundTintList(r2);
        return this;
    }

    public Snackbar setBackgroundTintMode(PorterDuff.Mode r2) {
        this.view.setBackgroundTintMode(r2);
        return this;
    }

    @Deprecated
    public Snackbar setCallback(Callback r2) {
        BaseTransientBottomBar.BaseCallback<Snackbar> r0 = this.callback;
        if (r0 == null) goto L5;
        removeCallback(r0);
    L5:
        if (r2 == null) goto L7;
        addCallback(r2);
    L7:
        this.callback = r2;
        return this;
    }

    public Snackbar setCloseIconDrawable(Drawable r2) {
        getCloseViewOrThrow().setIcon(r2);
        return this;
    }

    public Snackbar setCloseIconResource(int r2) {
        getCloseViewOrThrow().setIconResource(r2);
        return this;
    }

    public Snackbar setCloseIconTint(int r1) {
        return setCloseIconTintList(ColorStateList.valueOf(r1));
    }

    public Snackbar setCloseIconTintList(ColorStateList r2) {
        getCloseViewOrThrow().setIconTint(r2);
        return this;
    }

    public Snackbar setCloseIconVisible(boolean r3) {
        MaterialButton r0 = getCloseViewOrThrow();
        if (r3 == false) goto L5;
        int r1 = 0;
    L6:
        r0.setVisibility(r1);
        if (r3 == false) goto L9;
        Snackbar$$ExternalSyntheticLambda1 r12 = new Snackbar$$ExternalSyntheticLambda1(this);
    L10:
        r0.setOnClickListener(r12);
        getSnackbarLayout().removeOrRestorePaddingEnd(r3);
        return this;
    L9:
        r12 = null;
        goto L10
    L5:
        r1 = 8;
        goto L6
    }

    public Snackbar setMaxInlineActionWidth(int r2) {
        getContentLayout().setMaxInlineActionWidth(r2);
        return this;
    }

    public Snackbar setText(int r2) {
        return setText(getContext().getText(r2));
    }

    public Snackbar setText(CharSequence r2) {
        getMessageView().setText(r2);
        return this;
    }

    public Snackbar setTextColor(int r2) {
        getMessageView().setTextColor(r2);
        return this;
    }

    public Snackbar setTextColor(ColorStateList r2) {
        getMessageView().setTextColor(r2);
        return this;
    }

    public Snackbar setTextMaxLines(int r2) {
        getMessageView().setMaxLines(r2);
        return this;
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public void show() {
        super.show();
    }
}
