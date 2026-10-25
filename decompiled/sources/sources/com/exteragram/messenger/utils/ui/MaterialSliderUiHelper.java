package com.exteragram.messenger.utils.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.math.MathUtils;
import com.google.android.material.R;
import com.google.android.material.slider.Slider;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes4.dex */
public abstract class MaterialSliderUiHelper {
    public static void applyColors(Slider r1, int r2, int r3) {
        if (hasColor(r1.getTrackActiveTintList(), r2) == true) goto L6;
        r1.setTrackActiveTintList(ColorStateList.valueOf(r2));
    L6:
        if (hasColor(r1.getThumbTintList(), r2) == true) goto L9;
        r1.setThumbTintList(ColorStateList.valueOf(r2));
    L9:
        if (hasColor(r1.getTrackInactiveTintList(), r3) == true) goto L12;
        r1.setTrackInactiveTintList(ColorStateList.valueOf(r3));
        return;
    }

    public static void applyContinuousStyle(Slider r2) {
        if (r2.getTickVisibilityMode() == 2) goto L6;
        r2.setTickVisibilityMode(2);
    L6:
        if (r2.getContinuousModeTickCount() == 0) goto L9;
        r2.setContinuousModeTickCount(0);
        return;
    }

    public static void applyDiscreteColors(Slider r0, int r1, int r2, int r3) {
        applyColors(r0, r1, r2);
        if (hasColor(r0.getTickActiveTintList(), r3) == true) goto L6;
        r0.setTickActiveTintList(ColorStateList.valueOf(r3));
    L6:
        if (hasColor(r0.getTickInactiveTintList(), r3) == true) goto L9;
        r0.setTickInactiveTintList(ColorStateList.valueOf(r3));
        return;
    }

    public static void applyDiscreteStyle(Slider r3, int r4) {
        if (r3.getTickVisibilityMode() == 0) goto L6;
        r3.setTickVisibilityMode(0);
    L6:
        if (r3.getTickActiveRadius() == AndroidUtilities.dp(2.0f)) goto L9;
        r3.setTickActiveRadius(AndroidUtilities.dp(2.0f));
    L9:
        if (r3.getTickInactiveRadius() == AndroidUtilities.dp(2.0f)) goto L12;
        r3.setTickInactiveRadius(AndroidUtilities.dp(2.0f));
    L12:
        if (r3.getContinuousModeTickCount() == r4) goto L15;
        r3.setContinuousModeTickCount(r4);
        return;
    }

    public static Slider create(Context r3) {
        Slider r0 = new Slider(new ContextThemeWrapper(r3, R.style.Theme_Material3_DayNight));
        r0.setTrackHeight(AndroidUtilities.dp(8.0f));
        r0.setThumbHeight(AndroidUtilities.dp(24.0f));
        r0.setThumbWidth(AndroidUtilities.dp(3.0f));
        r0.setTrackStopIndicatorSize(0);
        r0.setHaloRadius(0);
        r0.setLabelBehavior(2);
        return r0;
    }

    private static boolean hasColor(ColorStateList r0, int r1) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.getDefaultColor() != r1) goto L9;
        return true;
    L9:
        return false;
    }

    public static void setValue(Slider r2, float r3) {
        float r32 = MathUtils.clamp(r3, r2.getValueFrom(), r2.getValueTo());
        if (Math.abs(r2.getValue() - r32) <= 1.0E-4f) goto L6;
        r2.setValue(r32);
        return;
    }
}
