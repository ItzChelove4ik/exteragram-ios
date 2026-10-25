package com.exteragram.messenger.utils.ui;

import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.core.graphics.ColorUtils;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.exteragram.messenger.ExteraConfig;
import me.vkryl.android.animator.BoolAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes4.dex */
public abstract class SwitchUiHelper {
    private static final float[] MATERIAL3_ICON_CHECK = null;
    private static final float[] MATERIAL3_ICON_CROSS = null;
    private static final Interpolator MATERIAL3_THUMB_INTERPOLATOR = null;
    private static final double[] colorLab = null;

    static {
        MATERIAL3_ICON_CROSS = new float[]{6.0f, 6.0f, 18.0f, 18.0f, 6.0f, 18.0f, 18.0f, 6.0f};
        MATERIAL3_ICON_CHECK = new float[]{4.5f, 13.1f, 9.0f, 17.6f, 9.0f, 17.6f, 19.8f, 6.8f};
        MATERIAL3_THUMB_INTERPOLATOR = new AccelerateDecelerateInterpolator();
        colorLab = new double[3];
    }

    public static BoolAnimator createThumbPressedAnimator(View r4) {
        return new BoolAnimator(r4, CubicBezierInterpolator.Standard, 100);
    }

    public static <K> SpringAnimation createThumbSpring(K r1, FloatPropertyCompat<K> r2) {
        return new SpringAnimation(r1, r2).setMinimumVisibleChange(0.001f).setSpring(new SpringForce().setDampingRatio(0.65f).setStiffness(510.0f));
    }

    public static float getClockHandLength(boolean r0) {
        if (r0 == false) goto L4;
        float r02 = 8.5f;
    L6:
        return iconGrid(r02);
    L4:
        r02 = 6.8f;
        goto L6
    }

    public static float getIconStrokeWidth() {
        return iconGrid(2.0f);
    }

    public static int getOverlayPadding() {
        if (isMaterial3SwitchStyle() == true) goto L5;
        return 0;
    L5:
        return AndroidUtilities.dp(5.0f);
    }

    public static int getSelectedThumbColor(int r2, Theme.ResourcesProvider r3) {
        if (isMaterial3SwitchStyle() == false) goto L11;
        if (Build.VERSION.SDK_INT < 31) goto L11;
        if (Theme.isCurrentThemeMonet(r3) == false) goto L11;
        return Theme.getColor(Theme.key_switchTrackBlueThumbChecked, r3);
    L11:
        return Theme.getColor(r2, r3);
    }

    public static int getStateLayerRadius() {
        if (isMaterial3SwitchStyle() == false) goto L7;
        return (int) scaled(20.0f);
    L7:
        return AndroidUtilities.dp(18.0f);
    }

    public static float getThumbCenterX(int r1, float r2) {
        return (r1 / 2.0f) + (((r2 * 2.0f) - 1.0f) * scaled(10.0f));
    }

    public static float getThumbIconScale(int r1, int r2) {
        return scaled(13.333333f) / Math.max(r1, r2);
    }

    public static float getTrackOutlineWidth() {
        return scaled(2.0f);
    }

    public static int getUnselectedThumbColor(int r2, int r3, Theme.ResourcesProvider r4) {
        if (isMaterial3SwitchStyle() == false) goto L5;
        int r32 = trackAtTone(r2, r4, 50.0f, 60.0f);
        if (Theme.isCurrentThemeMonet(r4) == false) goto L9;
        return r32;
    L9:
        int r22 = trackAtTone(r2, r4, 90.0f, 22.0f);
        if (isLightSurface(r4) == false) goto L12;
        float r42 = 0.55f;
    L14:
        return ColorUtils.blendARGB(r22, r32, r42);
    L12:
        r42 = 0.8f;
        goto L14
    L5:
        return Theme.getColor(r3, r4);
    }

    public static int getUnselectedTrackColor(int r2, Theme.ResourcesProvider r3) {
        if (isMaterial3SwitchStyle() == true) goto L7;
        return Theme.getColor(r2, r3);
    L7:
        return trackAtTone(r2, r3, 90.0f, 22.0f);
    }

    private static float iconGrid(float r1) {
        return scaled((r1 * 16.0f) / 24.0f);
    }

    private static boolean isLightSurface(Theme.ResourcesProvider r5) {
        int r52 = Theme.getColor(Theme.key_windowBackgroundWhite, r5);
        double[] r0 = colorLab;
        ColorUtils.colorToLAB(r52, r0);
        if (r0[0] <= 50.0d) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isMaterial3SwitchStyle() {
        return ExteraConfig.getNewSwitchStyle();
    }

    private static float scaled(float r1) {
        return AndroidUtilities.dpf2(r1 * 0.8125f);
    }

    public static void setCheckIconLines(float[] r3, float r4, float r5, float r6) {
        float r62 = Utilities.clamp01(r6);
        int r0 = 0;
    L4:
        if (r0 >= r3.length) goto L10;
        float r1 = iconGrid(AndroidUtilities.lerp(MATERIAL3_ICON_CROSS[r0], MATERIAL3_ICON_CHECK[r0], r62) - 12.0f);
        if ((r0 % 2) != 0) goto L8;
        float r2 = r4;
    L9:
        r3[r0] = r1 + r2;
        r0 = r0 + 1;
        goto L4
    L8:
        r2 = r5;
        goto L9
    }

    public static void setThumbBounds(RectF r6, float r7, float r8, float r9, boolean r10, float r11, float r12) {
        float r1 = 12.0f;
        float r112 = AndroidUtilities.lerp(8.0f, 12.0f, r11);
        float r92 = (float) (Math.acos(1.0f - (Utilities.clamp01(r9) * 2.0f)) / 3.141592653589793d);
        if (r10 == true) goto L6;
        r92 = 1.0f - r92;
    L6:
        if (r10 == false) goto L8;
        float r2 = 0.6f;
    L9:
        if (r10 == false) goto L11;
        float r3 = r112;
    L12:
        if (r10 == true) goto L16;
        r1 = r112;
    L16:
        if (r92 >= r2) goto L19;
        float r93 = MATERIAL3_THUMB_INTERPOLATOR.getInterpolation(r92 / r2);
        float r102 = AndroidUtilities.lerp(r3, 11.0f, r93);
    L18:
        float r94 = r93 * 5.0f;
        if (r12 <= 0.0f) goto L23;
        r102 = AndroidUtilities.lerp(r102, 14.0f, r12);
        r94 = r94 * (1.0f - r12);
    L23:
        float r103 = scaled(r102);
        float r95 = scaled(r94);
        r6.set((r7 - r95) - r103, r8 - r103, (r7 + r95) + r103, r8 + r103);
        return;
    L19:
        float r96 = MATERIAL3_THUMB_INTERPOLATOR.getInterpolation((r92 - r2) / (1.0f - r2));
        r102 = AndroidUtilities.lerp(11.0f, r1, r96);
        r93 = 1.0f - r96;
        goto L18
    L11:
        r3 = 12.0f;
        goto L12
    L8:
        r2 = 0.4f;
        goto L9
    }

    public static void setTrackBounds(RectF r4, int r5, int r6) {
        float r0 = scaled(26.0f);
        float r1 = scaled(16.0f);
        float r52 = r5 / 2.0f;
        float r62 = r6 / 2.0f;
        r4.set(r52 - r0, r62 - r1, r52 + r0, r62 + r1);
    }

    private static int trackAtTone(int r8, Theme.ResourcesProvider r9, float r10, float r11) {
        if (isLightSurface(r9) == true) goto L6;
        r10 = r11;
    L6:
        int r82 = Theme.getColor(r8, r9);
        double[] r92 = colorLab;
        ColorUtils.colorToLAB(r82, r92);
        double r0 = Math.hypot(r92[1], r92[2]);
        if (r0 <= 32.0d) goto L10;
        double r2 = 32.0d / r0;
        r92[1] = r92[1] * r2;
        r92[2] = r92[2] * r2;
    L10:
        return ColorUtils.LABToColor(r10, r92[1], r92[2]);
    }
}
