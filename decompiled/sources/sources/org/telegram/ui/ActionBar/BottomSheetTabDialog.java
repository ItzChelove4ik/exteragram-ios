package org.telegram.ui.ActionBar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AndroidUtilities$$ExternalSyntheticApiModelOutline1;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BottomSheetTabsOverlay;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes6.dex */
public class BottomSheetTabDialog extends Dialog {
    private boolean attached;
    public final View navigationBar;
    public final Paint navigationBarPaint;
    public final BottomSheetTabsOverlay.Sheet sheet;
    public final BottomSheetTabsOverlay.SheetView sheetView;
    public final WindowView windowView;

    /* renamed from: org.telegram.ui.ActionBar.BottomSheetTabDialog$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ BottomSheetTabDialog this$0;

        public AnonymousClass1(BottomSheetTabDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r8) {
            r8.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.this$0.navigationBarPaint);
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.navigationBarHeight);
            setTranslationY(AndroidUtilities.navigationBarHeight);
        }
    }

    public static class WindowView extends FrameLayout implements BottomSheetTabsOverlay.SheetView {
        public final BottomSheetTabsOverlay.SheetView sheetView;

        public WindowView(BottomSheetTabsOverlay.SheetView r2) {
            super(r2.getContext());
            this.sheetView = r2;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.SheetView
        public float drawInto(Canvas r1, RectF r2, float r3, RectF r4, float r5, boolean r6) {
            return this.sheetView.drawInto(r1, r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.SheetView
        public RectF getRect() {
            return this.sheetView.getRect();
        }

        public void putView() {
            View r0 = (View) this.sheetView;
            AndroidUtilities.removeFromParent(r0);
            addView(r0, LayoutHelper.createFrame(-1, -1, 119));
        }

        @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.SheetView
        public void setDrawingFromOverlay(boolean r1) {
            this.sheetView.setDrawingFromOverlay(r1);
        }
    }

    public static /* synthetic */ WindowInsets $r8$lambda$mi9mEbznhj0FGRXpR2lBBTI87Mo(View r2, WindowInsets r3) {
        r2.setPadding(0, 0, 0, r3.getSystemWindowInsetBottom());
        if (Build.VERSION.SDK_INT < 30) goto L7;
        return WindowInsets.CONSUMED;
    L7:
        return r3.consumeSystemWindowInsets();
    }

    public BottomSheetTabDialog(BottomSheetTabsOverlay.Sheet r4) {
        super(r4.mo6194getWindowView().getContext(), R.style.TransparentDialog);
        Paint r0 = new Paint(1);
        this.navigationBarPaint = r0;
        this.sheet = r4;
        BottomSheetTabsOverlay.SheetView r42 = r4.mo6194getWindowView();
        this.sheetView = r42;
        AnonymousClass1 r1 = new AnonymousClass1(this, getContext());
        this.navigationBar = r1;
        r0.setColor(Theme.getColor(Theme.key_windowBackgroundGray));
        WindowView r02 = new WindowView(r42);
        this.windowView = r02;
        setContentView(r02, new ViewGroup.LayoutParams(-1, -1));
        r02.addView(r1, LayoutHelper.createFrame(-1, -2, 80));
        r02.setClipToPadding(false);
    }

    public static BottomSheetTabsOverlay.Sheet checkSheet(BottomSheetTabsOverlay.Sheet r2) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L6;
    L14:
        return r2;
    L6:
        if (AndroidUtilities.isTablet() == false) goto L8;
    L11:
        BottomSheetTabDialog r02 = new BottomSheetTabDialog(r2);
        if (r2.setDialog(r02) == false) goto L14;
        r02.windowView.putView();
        goto L14
    L8:
        if (r2.hadDialog() == true) goto L11;
        if (AndroidUtilities.hasDialogOnTop(r0) == false) goto L14;
        goto L11
    }

    public void attach() {
        if (this.attached == false) goto L5;
        return;
    L5:
        this.attached = true;
        super.show();     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    public void detach() {
        this.sheet.setDialog(null);
        if (this.attached == true) goto L5;
        return;
    L5:
        this.attached = false;
        super.dismiss();     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.sheet.dismiss(false);
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle r5) {
        super.onCreate(r5);
        Window r52 = getWindow();
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 30) goto L5;
        r52.addFlags(-2147483392);
    L6:
        r52.setWindowAnimations(R.style.DialogNoAnimation);
        WindowManager.LayoutParams r1 = r52.getAttributes();
        r1.width = -1;
        r1.gravity = 51;
        r1.dimAmount = 0.0f;
        r1.flags &= -3;
        r1.softInputMode = 16;
        r1.height = -1;
        if (r0 < 28) goto L9;
        AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r1, 1);
    L9:
        r52.setAttributes(r1);
        r52.setStatusBarColor(0);
        this.windowView.setFitsSystemWindows(true);
        this.windowView.setSystemUiVisibility(1792);
        this.windowView.setPadding(0, 0, 0, 0);
        this.windowView.setOnApplyWindowInsetsListener(new BottomSheetTabDialog$$ExternalSyntheticLambda0());
        return;
    L5:
        r52.addFlags(-2147417856);
        goto L6
    }

    public void updateNavigationBarColor() {
        int r0 = this.sheet.getNavigationBarColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.navigationBarPaint.setColor(r0);
        this.navigationBar.invalidate();
        AndroidUtilities.setNavigationBarColor(this, r0);
        if (AndroidUtilities.computePerceivedBrightness(r0) < 0.721f) goto L5;
        boolean r02 = true;
    L6:
        AndroidUtilities.setLightNavigationBar(this, r02);
        LaunchActivity.instance.checkSystemBarColors(true, true, true);
        return;
    L5:
        r02 = false;
        goto L6
    }
}
