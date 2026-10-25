package com.exteragram.messenger.icons.ui.picker;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes4.dex */
public abstract class IconPickerController {
    private static boolean isInitializing;

    @SuppressLint({"StaticFieldLeak"})
    private static IconPickerView pickerView;

    /* renamed from: $r8$lambda$OfRV3mAQKhYExDNLrcksaoq-PBI, reason: not valid java name */
    public static /* synthetic */ void m1465$r8$lambda$OfRV3mAQKhYExDNLrcksaoqPBI(LaunchActivity r0, IconPickerView r1) {
        FrameLayout r02 = r0.getMainContainerFrameLayout();
        if (r02 == null) goto L7;
        r02.removeView(r1);
        return;
    L7:
        if ((r1.getParent() instanceof ViewGroup) == false) goto L10;
        ((ViewGroup) r1.getParent()).removeView(r1);
        return;
    }

    private static void hidePicker(LaunchActivity r2) {
        if (pickerView != null) goto L5;
        return;
    L5:
        IconObserver.INSTANCE.clear();
        IconPickerView r0 = pickerView;
        pickerView = null;
        r0.dismiss(new IconPickerController$$ExternalSyntheticLambda0(r2, r0));
    }

    public static boolean onBackPressed(boolean r1) {
        IconPickerView r0 = pickerView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.onBackPressed(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static void onDestroy() {
        IconPickerView r0 = pickerView;
        if (r0 == null) goto L7;
        r0.saveConfig();
        if ((pickerView.getParent() instanceof ViewGroup) == false) goto L7;
        ((ViewGroup) pickerView.getParent()).removeView(pickerView);
    L7:
        pickerView = null;
    }

    public static void setActive(LaunchActivity r1, boolean r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r1.isFinishing() == false) goto L6;
        return;
    L6:
        if (r1.isDestroyed() == false) goto L9;
        return;
    L9:
        if (pickerView == null) goto L11;
        boolean r0 = true;
    L12:
        if (r2 == r0) goto L22;
        if (r2 == false) goto L17;
        showPicker(r1);
        return;
    L17:
        hidePicker(r1);
        return;
    L22:
        return;
    L11:
        r0 = false;
        goto L12
    }

    private static void showPicker(LaunchActivity r4) {
        if (ExteraConfig.getEditingIconPackId() != null) goto L6;
        return;
    L6:
        if (pickerView == null) goto L8;
        return;
    L8:
        if (isInitializing == true) goto L25;
        isInitializing = true;
        pickerView = new IconPickerView(r4);     // Catch: Throwable -> L15
        FrameLayout r42 = r4.getMainContainerFrameLayout();     // Catch: Throwable -> L15
        if (r42 == null) goto L17;
        r42.addView(pickerView, new FrameLayout.LayoutParams(-1, -1));     // Catch: Throwable -> L15
        pickerView.showFab();     // Catch: Throwable -> L15
    L17:
        isInitializing = false;
        return;
    L15:
        th = move-exception;
        isInitializing = false;
        throw th;
    }
}
