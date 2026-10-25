package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public abstract class LayoutHelper {
    public static FrameLayout.LayoutParams createFrame(float r1, float r2, int r3) {
        return new FrameLayout.LayoutParams(getSize(r1), getSize(r2), r3);
    }

    public static FrameLayout.LayoutParams createFrame(float r1, float r2, int r3, float r4, float r5, float r6, float r7) {
        FrameLayout.LayoutParams r0 = new FrameLayout.LayoutParams(getSize(r1), getSize(r2), r3);
        r0.setMargins(AndroidUtilities.dp(r4), AndroidUtilities.dp(r5), AndroidUtilities.dp(r6), AndroidUtilities.dp(r7));
        return r0;
    }

    public static FrameLayout.LayoutParams createFrame(int r1, float r2) {
        return new FrameLayout.LayoutParams(getSize(r1), getSize(r2));
    }

    public static FrameLayout.LayoutParams createFrame(int r1, float r2, int r3, float r4, float r5, float r6, float r7) {
        FrameLayout.LayoutParams r0 = new FrameLayout.LayoutParams(getSize(r1), getSize(r2), r3);
        r0.setMargins(AndroidUtilities.dp(r4), AndroidUtilities.dp(r5), AndroidUtilities.dp(r6), AndroidUtilities.dp(r7));
        return r0;
    }

    public static FrameLayout.LayoutParams createFrame(int r1, int r2, int r3) {
        return new FrameLayout.LayoutParams(getSize(r1), getSize(r2), r3);
    }

    public static FrameLayout.LayoutParams createFrameMarginPx(int r1, float r2, int r3, int r4, int r5, int r6, int r7) {
        FrameLayout.LayoutParams r0 = new FrameLayout.LayoutParams(getSize(r1), getSize(r2), r3);
        r0.setMargins(r4, r5, r6, r7);
        return r0;
    }

    public static FrameLayout.LayoutParams createFrameMatchParent() {
        return createFrame(-1, -1.0f);
    }

    public static FrameLayout.LayoutParams createFrameRelatively(float r1, float r2, int r3) {
        return new FrameLayout.LayoutParams(getSize(r1), getSize(r2), getAbsoluteGravity(r3));
    }

    public static FrameLayout.LayoutParams createFrameRelatively(float r1, float r2, int r3, float r4, float r5, float r6, float r7) {
        FrameLayout.LayoutParams r0 = new FrameLayout.LayoutParams(getSize(r1), getSize(r2), getAbsoluteGravity(r3));
        if (LocaleController.isRTL == false) goto L5;
        float r12 = r6;
    L6:
        r0.leftMargin = AndroidUtilities.dp(r12);
        r0.topMargin = AndroidUtilities.dp(r5);
        if (LocaleController.isRTL == true) goto L10;
        r4 = r6;
    L10:
        r0.rightMargin = AndroidUtilities.dp(r4);
        r0.bottomMargin = AndroidUtilities.dp(r7);
        return r0;
    L5:
        r12 = r4;
        goto L6
    }

    public static LinearLayout.LayoutParams createLinear(float r1, float r2) {
        return new LinearLayout.LayoutParams(getSize(r1), getSize(r2));
    }

    public static LinearLayout.LayoutParams createLinear(float r1, float r2, int r3) {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(getSize(r1), getSize(r2));
        r0.gravity = r3;
        return r0;
    }

    public static LinearLayout.LayoutParams createLinear(float r1, float r2, int r3, float r4, float r5, float r6, float r7) {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(getSize(r1), getSize(r2));
        r0.setMargins(AndroidUtilities.dp(r4), AndroidUtilities.dp(r5), AndroidUtilities.dp(r6), AndroidUtilities.dp(r7));
        r0.gravity = r3;
        return r0;
    }

    public static LinearLayout.LayoutParams createLinear(int r1, float r2, int r3, int r4, int r5, int r6, int r7) {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(getSize(r1), getSize(r2));
        r0.setMargins(AndroidUtilities.dp(r4), AndroidUtilities.dp(r5), AndroidUtilities.dp(r6), AndroidUtilities.dp(r7));
        r0.gravity = r3;
        return r0;
    }

    public static LinearLayout.LayoutParams createLinear(int r1, int r2) {
        return new LinearLayout.LayoutParams(getSize(r1), getSize(r2));
    }

    public static LinearLayout.LayoutParams createLinear(int r1, int r2, float r3) {
        return new LinearLayout.LayoutParams(getSize(r1), getSize(r2), r3);
    }

    public static LinearLayout.LayoutParams createLinear(int r1, int r2, float r3, float r4, float r5, float r6) {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(getSize(r1), getSize(r2));
        r0.setMargins(AndroidUtilities.dp(r3), AndroidUtilities.dp(r4), AndroidUtilities.dp(r5), AndroidUtilities.dp(r6));
        return r0;
    }

    public static LinearLayout.LayoutParams createLinear(int r1, int r2, float r3, int r4) {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(getSize(r1), getSize(r2), r3);
        r0.gravity = r4;
        return r0;
    }

    public static LinearLayout.LayoutParams createLinear(int r1, int r2, float r3, int r4, int r5, int r6, int r7) {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(getSize(r1), getSize(r2), r3);
        r0.setMargins(AndroidUtilities.dp(r4), AndroidUtilities.dp(r5), AndroidUtilities.dp(r6), AndroidUtilities.dp(r7));
        return r0;
    }

    public static LinearLayout.LayoutParams createLinear(int r1, int r2, float r3, int r4, int r5, int r6, int r7, int r8) {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(getSize(r1), getSize(r2), r3);
        r0.setMargins(AndroidUtilities.dp(r5), AndroidUtilities.dp(r6), AndroidUtilities.dp(r7), AndroidUtilities.dp(r8));
        r0.gravity = r4;
        return r0;
    }

    public static LinearLayout.LayoutParams createLinear(int r1, int r2, int r3) {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(getSize(r1), getSize(r2));
        r0.gravity = r3;
        return r0;
    }

    public static LinearLayout.LayoutParams createLinear(int r1, int r2, int r3, float r4, float r5, float r6, float r7) {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(getSize(r1), getSize(r2));
        r0.setMargins(AndroidUtilities.dp(r4), AndroidUtilities.dp(r5), AndroidUtilities.dp(r6), AndroidUtilities.dp(r7));
        r0.gravity = r3;
        return r0;
    }

    public static LinearLayout.LayoutParams createLinear(int r1, int r2, int r3, int r4, int r5, int r6, int r7) {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(getSize(r1), getSize(r2));
        r0.setMargins(AndroidUtilities.dp(r4), AndroidUtilities.dp(r5), AndroidUtilities.dp(r6), AndroidUtilities.dp(r7));
        r0.gravity = r3;
        return r0;
    }

    public static LinearLayout.LayoutParams createLinearRelatively(float r1, float r2, int r3) {
        return new LinearLayout.LayoutParams(getSize(r1), getSize(r2), getAbsoluteGravity(r3));
    }

    public static LinearLayout.LayoutParams createLinearRelatively(float r1, float r2, int r3, float r4, float r5, float r6, float r7) {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(getSize(r1), getSize(r2), getAbsoluteGravity(r3));
        if (LocaleController.isRTL == false) goto L5;
        float r12 = r6;
    L6:
        r0.leftMargin = AndroidUtilities.dp(r12);
        r0.topMargin = AndroidUtilities.dp(r5);
        if (LocaleController.isRTL == true) goto L10;
        r4 = r6;
    L10:
        r0.rightMargin = AndroidUtilities.dp(r4);
        r0.bottomMargin = AndroidUtilities.dp(r7);
        return r0;
    L5:
        r12 = r4;
        goto L6
    }

    public static RelativeLayout.LayoutParams createRelative(float r1, float r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        RelativeLayout.LayoutParams r0 = new RelativeLayout.LayoutParams(getSize(r1), getSize(r2));
        if (r7 < 0) goto L5;
        r0.addRule(r7);
    L5:
        if (r8 < 0) goto L8;
        if (r9 < 0) goto L8;
        r0.addRule(r8, r9);
    L8:
        r0.leftMargin = AndroidUtilities.dp(r3);
        r0.topMargin = AndroidUtilities.dp(r4);
        r0.rightMargin = AndroidUtilities.dp(r5);
        r0.bottomMargin = AndroidUtilities.dp(r6);
        return r0;
    }

    public static RelativeLayout.LayoutParams createRelative(int r9, int r10) {
        return createRelative(r9, r10, 0, 0, 0, 0, -1, -1, -1);
    }

    public static RelativeLayout.LayoutParams createRelative(int r9, int r10, int r11) {
        return createRelative(r9, r10, 0, 0, 0, 0, r11, -1, -1);
    }

    public static RelativeLayout.LayoutParams createRelative(int r9, int r10, int r11, int r12, int r13, int r14, int r15) {
        return createRelative(r9, r10, r11, r12, r13, r14, r15, -1, -1);
    }

    public static FrameLayout.LayoutParams createScroll(int r1, int r2, int r3) {
        return new FrameLayout.LayoutParams(getSize(r1), getSize(r2), r3);
    }

    private static int getAbsoluteGravity(int r1) {
        return Gravity.getAbsoluteGravity(r1, LocaleController.isRTL ? 1 : 0);
    }

    @SuppressLint({"RtlHardcoded"})
    public static int getAbsoluteGravityStart() {
        if (LocaleController.isRTL == false) goto L6;
        return 5;
    L6:
        return 3;
    }

    private static int getSize(float r1) {
        if (r1 < 0.0f) goto L7;
        r1 = AndroidUtilities.dp(r1);
    L7:
        return (int) r1;
    }

    public static int measureSpecExactly(int r1) {
        return View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30);
    }

    public static int measureSpecExactlyDp(int r1) {
        return View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r1), TLObject.FLAG_30);
    }
}
