package com.google.android.material.textfield;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.material.internal.CheckableImageButton;
import java.util.Arrays;

/* loaded from: classes5.dex */
class IconHelper {
    private IconHelper() {
    }

    public static void applyIconTint(TextInputLayout r2, CheckableImageButton r3, ColorStateList r4, PorterDuff.Mode r5) {
        Drawable r0 = r3.getDrawable();
        if (r0 == null) goto L13;
        r0 = DrawableCompat.wrap(r0).mutate();
        if (r4 != null) goto L7;
    L9:
        r0.setTintList(r4);
    L10:
        if (r5 == null) goto L13;
        r0.setTintMode(r5);
        goto L13
    L7:
        if (r4.isStateful() == false) goto L9;
        r0.setTintList(ColorStateList.valueOf(r4.getColorForState(mergeIconState(r2, r3), r4.getDefaultColor())));
    L13:
        if (r3.getDrawable() == r0) goto L16;
        r3.setImageDrawable(r0);
        return;
    }

    public static ImageView.ScaleType convertScaleType(int r1) {
        if (r1 == 0) goto L26;
        if (r1 == 1) goto L24;
        if (r1 == 2) goto L22;
        if (r1 == 3) goto L20;
        if (r1 == 5) goto L18;
        if (r1 == 6) goto L16;
        return ImageView.ScaleType.CENTER;
    L16:
        return ImageView.ScaleType.CENTER_INSIDE;
    L18:
        return ImageView.ScaleType.CENTER_CROP;
    L20:
        return ImageView.ScaleType.FIT_END;
    L22:
        return ImageView.ScaleType.FIT_CENTER;
    L24:
        return ImageView.ScaleType.FIT_START;
    L26:
        return ImageView.ScaleType.FIT_XY;
    }

    private static int[] mergeIconState(TextInputLayout r3, CheckableImageButton r4) {
        int[] r32 = r3.getDrawableState();
        int[] r42 = r4.getDrawableState();
        int r0 = r32.length;
        int[] r33 = Arrays.copyOf(r32, r32.length + r42.length);
        System.arraycopy(r42, 0, r33, r0, r42.length);
        return r33;
    }

    public static void refreshIconDrawableState(TextInputLayout r2, CheckableImageButton r3, ColorStateList r4) {
        Drawable r0 = r3.getDrawable();
        if (r3.getDrawable() == null) goto L10;
        if (r4 != null) goto L6;
        return;
    L6:
        if (r4.isStateful() == false) goto L12;
        int r22 = r4.getColorForState(mergeIconState(r2, r3), r4.getDefaultColor());
        Drawable r42 = DrawableCompat.wrap(r0).mutate();
        r42.setTintList(ColorStateList.valueOf(r22));
        r3.setImageDrawable(r42);
        return;
    L12:
        return;
    }

    public static void setCompatRippleBackgroundIfNeeded(CheckableImageButton r0) {
    }

    private static void setIconClickable(CheckableImageButton r4, View.OnLongClickListener r5) {
        boolean r0 = r4.hasOnClickListeners();
        boolean r1 = false;
        int r2 = 1;
        if (r5 == null) goto L5;
        boolean r52 = true;
    L6:
        if (r0 == true) goto L8;
        if (r52 == true) goto L8;
    L9:
        r4.setFocusable(r1);
        r4.setClickable(r0);
        r4.setPressable(r0);
        if (Build.VERSION.SDK_INT >= 26) goto L13;
        if (r1 == false) goto L13;
        if (r52 == true) goto L13;
    L14:
        if (r1 == true) goto L17;
        r2 = 2;
    L17:
        r4.setImportantForAccessibility(r2);
        return;
    L13:
        r4.setLongClickable(r52);
    L8:
        r1 = true;
        goto L9
    L5:
        r52 = false;
        goto L6
    }

    public static void setIconMinSize(CheckableImageButton r0, int r1) {
        r0.setMinimumWidth(r1);
        r0.setMinimumHeight(r1);
    }

    public static void setIconOnClickListener(CheckableImageButton r0, View.OnClickListener r1, View.OnLongClickListener r2) {
        r0.setOnClickListener(r1);
        setIconClickable(r0, r2);
    }

    public static void setIconOnLongClickListener(CheckableImageButton r0, View.OnLongClickListener r1) {
        r0.setOnLongClickListener(r1);
        setIconClickable(r0, r1);
    }

    public static void setIconScaleType(CheckableImageButton r0, ImageView.ScaleType r1) {
        r0.setScaleType(r1);
    }

    public static void updateIconTooltip(CheckableImageButton r2, View.OnLongClickListener r3, CharSequence r4) {
        if (r2.isFocusable() == true) goto L7;
        r4 = null;
    L7:
        if (Build.VERSION.SDK_INT < 26) goto L10;
        r2.setTooltipText(r4);
        return;
    L10:
        if (r3 != null) goto L13;
        TooltipCompat.setTooltipText(r2, r4);
        return;
    }
}
