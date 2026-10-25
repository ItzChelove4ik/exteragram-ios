package com.google.android.material.sidesheet;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.R;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class SideSheetDialog extends SheetDialog<SideSheetCallback> {
    private static final int SIDE_SHEET_DIALOG_DEFAULT_THEME_RES = 0;
    private static final int SIDE_SHEET_DIALOG_THEME_ATTR = 0;

    /* renamed from: com.google.android.material.sidesheet.SideSheetDialog$1, reason: invalid class name */
    public class AnonymousClass1 extends SideSheetCallback {
        final /* synthetic */ SideSheetDialog this$0;

        public AnonymousClass1(SideSheetDialog r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.sidesheet.SideSheetCallback, com.google.android.material.sidesheet.SheetCallback
        public void onSlide(View r1, float r2) {
        }

        @Override // com.google.android.material.sidesheet.SideSheetCallback, com.google.android.material.sidesheet.SheetCallback
        public void onStateChanged(View r1, int r2) {
            if (r2 != 5) goto L6;
            this.this$0.cancel();
            return;
        }
    }

    static {
        SIDE_SHEET_DIALOG_THEME_ATTR = R.attr.sideSheetDialogTheme;
        SIDE_SHEET_DIALOG_DEFAULT_THEME_RES = R.style.Theme_Material3_Light_SideSheetDialog;
    }

    public SideSheetDialog(Context r2) {
        this(r2, 0);
    }

    public SideSheetDialog(Context r3, int r4) {
        super(r3, r4, SIDE_SHEET_DIALOG_THEME_ATTR, SIDE_SHEET_DIALOG_DEFAULT_THEME_RES);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    public void addSheetCancelOnHideCallback(Sheet<SideSheetCallback> r2) {
        r2.addCallback(new AnonymousClass1(this));
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, android.app.Dialog, android.content.DialogInterface
    public /* bridge */ /* synthetic */ void cancel() {
        super.cancel();
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    public /* bridge */ /* synthetic */ Sheet getBehavior() {
        return getBehavior();
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    public SideSheetBehavior<? extends View> getBehavior() {
        Sheet r1 = super.getBehavior();
        if ((r1 instanceof SideSheetBehavior) == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("The view is not associated with SideSheetBehavior");
        return null;
    L5:
        return (SideSheetBehavior) r1;
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    public Sheet<SideSheetCallback> getBehaviorFromSheet(FrameLayout r1) {
        return SideSheetBehavior.from(r1);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    public int getDialogId() {
        return R.id.m3_side_sheet;
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    public int getLayoutResId() {
        return R.layout.m3_side_sheet_dialog;
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    public int getStateOnStart() {
        return 3;
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    public /* bridge */ /* synthetic */ boolean isDismissWithSheetAnimationEnabled() {
        return super.isDismissWithSheetAnimationEnabled();
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, android.app.Dialog, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, android.app.Dialog, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, android.app.Dialog
    public /* bridge */ /* synthetic */ void setCancelable(boolean r1) {
        super.setCancelable(r1);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, android.app.Dialog
    public /* bridge */ /* synthetic */ void setCanceledOnTouchOutside(boolean r1) {
        super.setCanceledOnTouchOutside(r1);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public /* bridge */ /* synthetic */ void setContentView(int r1) {
        super.setContentView(r1);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public /* bridge */ /* synthetic */ void setContentView(View r1) {
        super.setContentView(r1);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public /* bridge */ /* synthetic */ void setContentView(View r1, ViewGroup.LayoutParams r2) {
        super.setContentView(r1, r2);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    public /* bridge */ /* synthetic */ void setDismissWithSheetAnimationEnabled(boolean r1) {
        super.setDismissWithSheetAnimationEnabled(r1);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    public /* bridge */ /* synthetic */ void setFitsSystemWindows(boolean r1) {
        super.setFitsSystemWindows(r1);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    public /* bridge */ /* synthetic */ void setSheetEdge(int r1) {
        super.setSheetEdge(r1);
    }
}
