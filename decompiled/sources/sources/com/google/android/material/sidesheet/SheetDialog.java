package com.google.android.material.sidesheet;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.sidesheet.SheetCallback;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
abstract class SheetDialog<C extends SheetCallback> extends AppCompatDialog {
    private static final int COORDINATOR_LAYOUT_ID = 0;
    private static final int TOUCH_OUTSIDE_ID = 0;
    private MaterialBackOrchestrator backOrchestrator;
    private Sheet<C> behavior;
    boolean cancelable;
    private boolean canceledOnTouchOutside;
    private boolean canceledOnTouchOutsideSet;
    private FrameLayout container;
    private CoordinatorLayout coordinator;
    boolean dismissWithAnimation;
    private boolean fitsSystemWindows;
    private FrameLayout sheet;

    /* renamed from: com.google.android.material.sidesheet.SheetDialog$1, reason: invalid class name */
    public class AnonymousClass1 extends AccessibilityDelegateCompat {
        final /* synthetic */ SheetDialog this$0;

        public AnonymousClass1(SheetDialog r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            if (this.this$0.cancelable == false) goto L6;
            r2.addAction(1048576);
            r2.setDismissable(true);
            return;
        L6:
            r2.setDismissable(false);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View r3, int r4, Bundle r5) {
            if (r4 != 1048576) goto L9;
            SheetDialog r0 = this.this$0;
            if (r0.cancelable == false) goto L9;
            r0.cancel();
            return true;
        L9:
            return super.performAccessibilityAction(r3, r4, r5);
        }
    }

    /* renamed from: $r8$lambda$mcfSNnLeC1nhFpP-13QvN-GQH5Q, reason: not valid java name */
    public static /* synthetic */ void m1898$r8$lambda$mcfSNnLeC1nhFpP13QvNGQH5Q(SheetDialog r0, View r1) {
        if (r0.cancelable == true) goto L5;
        return;
    L5:
        if (r0.isShowing() == true) goto L7;
        return;
    L7:
        if (r0.shouldWindowCloseOnTouchOutside() == false) goto L12;
        r0.cancel();
        return;
    }

    static {
        COORDINATOR_LAYOUT_ID = R.id.coordinator;
        TOUCH_OUTSIDE_ID = R.id.touch_outside;
    }

    public SheetDialog(Context r1, int r2, int r3, int r4) {
        super(r1, getThemeResId(r1, r2, r3, r4));
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.fitsSystemWindows = true;
        supportRequestWindowFeature(1);
    }

    private void ensureContainerAndBehavior() {
        if (this.container != null) goto L6;
        FrameLayout r0 = (FrameLayout) View.inflate(getContext(), getLayoutResId(), null);
        this.container = r0;
        FrameLayout r02 = (FrameLayout) r0.findViewById(getDialogId());
        this.sheet = r02;
        Sheet<C> r03 = getBehaviorFromSheet(r02);
        this.behavior = r03;
        addSheetCancelOnHideCallback(r03);
        this.backOrchestrator = new MaterialBackOrchestrator(this.behavior, this.sheet);
        return;
    }

    private FrameLayout getContainer() {
        if (this.container != null) goto L6;
        ensureContainerAndBehavior();
    L6:
        return this.container;
    }

    private FrameLayout getSheet() {
        if (this.sheet != null) goto L6;
        ensureContainerAndBehavior();
    L6:
        return this.sheet;
    }

    private static int getThemeResId(Context r1, int r2, int r3, int r4) {
        if (r2 != 0) goto L8;
        TypedValue r22 = new TypedValue();
        if (r1.getTheme().resolveAttribute(r3, r22, true) == true) goto L6;
        return r4;
    L6:
        return r22.resourceId;
    L8:
        return r2;
    }

    private void maybeUpdateWindowAnimationsBasedOnLayoutDirection() {
        Window r0 = getWindow();
        if (r0 == null) goto L14;
        FrameLayout r1 = this.sheet;
        if (r1 != null) goto L7;
        return;
    L7:
        if ((r1.getLayoutParams() instanceof CoordinatorLayout.LayoutParams) == true) goto L9;
        return;
    L9:
        if (Gravity.getAbsoluteGravity(((CoordinatorLayout.LayoutParams) this.sheet.getLayoutParams()).gravity, this.sheet.getLayoutDirection()) != 3) goto L11;
        int r2 = R.style.Animation_Material3_SideSheetDialog_Left;
    L12:
        r0.setWindowAnimations(r2);
        return;
    L11:
        r2 = R.style.Animation_Material3_SideSheetDialog_Right;
        goto L12
    }

    private boolean shouldWindowCloseOnTouchOutside() {
        if (this.canceledOnTouchOutsideSet == true) goto L6;
        TypedArray r0 = getContext().obtainStyledAttributes(new int[]{android.R.attr.windowCloseOnTouchOutside});
        this.canceledOnTouchOutside = r0.getBoolean(0, true);
        r0.recycle();
        this.canceledOnTouchOutsideSet = true;
    L6:
        return this.canceledOnTouchOutside;
    }

    private void updateFitsSystemWindows() {
        FrameLayout r0 = this.container;
        if (r0 == null) goto L5;
        r0.setFitsSystemWindows(this.fitsSystemWindows);
    L5:
        CoordinatorLayout r02 = this.coordinator;
        if (r02 == null) goto L8;
        r02.setFitsSystemWindows(this.fitsSystemWindows);
    L8:
        Window r03 = getWindow();
        if (r03 == null) goto L12;
        WindowCompat.setDecorFitsSystemWindows(r03, this.fitsSystemWindows);
        return;
    }

    private void updateListeningForBackCallbacks() {
        MaterialBackOrchestrator r0 = this.backOrchestrator;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.cancelable == false) goto L9;
        r0.startListeningForBackCallbacks();
        return;
    L9:
        r0.stopListeningForBackCallbacks();
    }

    private View wrapInSheet(int r3, View r4, ViewGroup.LayoutParams r5) {
        ensureContainerAndBehavior();
        this.coordinator = (CoordinatorLayout) getContainer().findViewById(COORDINATOR_LAYOUT_ID);
        if (r3 == 0) goto L6;
        if (r4 != null) goto L6;
        r4 = getLayoutInflater().inflate(r3, this.coordinator, false);
    L6:
        FrameLayout r32 = getSheet();
        r32.removeAllViews();
        if (r5 != null) goto L9;
        r32.addView(r4);
    L10:
        this.coordinator.findViewById(TOUCH_OUTSIDE_ID).setOnClickListener(new SheetDialog$$ExternalSyntheticLambda0(this));
        ViewCompat.setAccessibilityDelegate(getSheet(), new AnonymousClass1(this));
        return this.container;
    L9:
        r32.addView(r4, r5);
        goto L10
    }

    public abstract void addSheetCancelOnHideCallback(Sheet<C> r1);

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        Sheet<C> r0 = getBehavior();
        if (this.dismissWithAnimation == true) goto L5;
    L9:
        super.cancel();
        return;
    L5:
        if (r0.getState() == 5) goto L9;
        r0.setState(5);
    }

    public Sheet<C> getBehavior() {
        if (this.behavior != null) goto L6;
        ensureContainerAndBehavior();
    L6:
        return this.behavior;
    }

    public abstract Sheet<C> getBehaviorFromSheet(FrameLayout r1);

    public abstract int getDialogId();

    public abstract int getLayoutResId();

    public abstract int getStateOnStart();

    public boolean isDismissWithSheetAnimationEnabled() {
        return this.dismissWithAnimation;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        maybeUpdateWindowAnimationsBasedOnLayoutDirection();
        updateFitsSystemWindows();
        updateListeningForBackCallbacks();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle r1) {
        super.onCreate(r1);
        Window r0 = getWindow();
        if (r0 == null) goto L6;
        EdgeToEdgeUtils.setStatusBarColor(r0, 0);
        r0.addFlags(Integer.MIN_VALUE);
        r0.setLayout(-1, -1);
        return;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MaterialBackOrchestrator r0 = this.backOrchestrator;
        if (r0 == null) goto L6;
        r0.stopListeningForBackCallbacks();
        return;
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public void onStart() {
        super.onStart();
        Sheet<C> r0 = this.behavior;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getState() != 5) goto L9;
        this.behavior.setState(getStateOnStart());
        return;
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean r2) {
        super.setCancelable(r2);
        if (this.cancelable == r2) goto L6;
        this.cancelable = r2;
    L6:
        if (getWindow() == null) goto L9;
        updateListeningForBackCallbacks();
        return;
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean r3) {
        super.setCanceledOnTouchOutside(r3);
        if (r3 == true) goto L5;
    L7:
        this.canceledOnTouchOutside = r3;
        this.canceledOnTouchOutsideSet = true;
        return;
    L5:
        if (this.cancelable == true) goto L7;
        this.cancelable = true;
        goto L7
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(int r2) {
        super.setContentView(wrapInSheet(r2, null, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(View r3) {
        super.setContentView(wrapInSheet(0, r3, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(View r2, ViewGroup.LayoutParams r3) {
        super.setContentView(wrapInSheet(0, r2, r3));
    }

    public void setDismissWithSheetAnimationEnabled(boolean r1) {
        this.dismissWithAnimation = r1;
    }

    public void setFitsSystemWindows(boolean r1) {
        this.fitsSystemWindows = r1;
        updateFitsSystemWindows();
    }

    public void setSheetEdge(int r3) {
        FrameLayout r0 = this.sheet;
        if (r0 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Sheet view reference is null; sheet edge cannot be changed if the sheet view is null.");
        return;
    L5:
        if (r0.isLaidOut() == true) goto L10;
        ViewGroup.LayoutParams r02 = this.sheet.getLayoutParams();
        if ((r02 instanceof CoordinatorLayout.LayoutParams) == false) goto L14;
        ((CoordinatorLayout.LayoutParams) r02).gravity = r3;
        maybeUpdateWindowAnimationsBasedOnLayoutDirection();
        return;
    L14:
        return;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("Sheet view has been laid out; sheet edge cannot be changed once the sheet has been laid out.");
    }
}
