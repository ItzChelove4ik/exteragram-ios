package com.google.android.material.ripple;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.Log;
import android.util.StateSet;
import androidx.appcompat.R$attr;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.color.MaterialColors;

/* loaded from: classes5.dex */
public class RippleUtils {
    private static final int[] ENABLED_PRESSED_STATE_SET = null;
    private static final int[] FOCUSED_STATE_SET = null;
    static final String LOG_TAG = null;
    private static final int[] PRESSED_STATE_SET = null;
    private static final int[] SELECTED_PRESSED_STATE_SET = null;
    private static final int[] SELECTED_STATE_SET = null;
    static final String TRANSPARENT_DEFAULT_COLOR_WARNING = "Use a non-transparent color for the default color as it will be used to finish ripple animations.";

    @Deprecated
    public static final boolean USE_FRAMEWORK_RIPPLE = true;

    public static class RippleUtilsLollipop {
        private RippleUtilsLollipop() {
        }

        public static /* synthetic */ Drawable access$000(Context r0, int r1) {
            return createOvalRipple(r0, r1);
        }

        private static Drawable createOvalRipple(Context r6, int r7) {
            GradientDrawable r1 = new GradientDrawable();
            r1.setColor(-1);
            r1.setShape(1);
            InsetDrawable r0 = new InsetDrawable(r1, r7, r7, r7, r7);
            return new RippleDrawable(MaterialColors.getColorStateList(r6, R$attr.colorControlHighlight, ColorStateList.valueOf(0)), null, r0);
        }
    }

    static {
        PRESSED_STATE_SET = new int[]{R.attr.state_pressed};
        FOCUSED_STATE_SET = new int[]{R.attr.state_focused};
        SELECTED_PRESSED_STATE_SET = new int[]{R.attr.state_selected, R.attr.state_pressed};
        SELECTED_STATE_SET = new int[]{R.attr.state_selected};
        ENABLED_PRESSED_STATE_SET = new int[]{R.attr.state_enabled, R.attr.state_pressed};
        LOG_TAG = RippleUtils.class.getSimpleName();
    }

    private RippleUtils() {
    }

    public static ColorStateList convertToRippleDrawableColor(ColorStateList r4) {
        int[] r2 = FOCUSED_STATE_SET;
        return new ColorStateList(new int[][]{SELECTED_STATE_SET, r2, StateSet.NOTHING}, new int[]{getColorForState(r4, SELECTED_PRESSED_STATE_SET), getColorForState(r4, r2), getColorForState(r4, PRESSED_STATE_SET)});
    }

    public static Drawable createOvalRippleLollipop(Context r0, int r1) {
        return RippleUtilsLollipop.access$000(r0, r1);
    }

    private static int doubleAlpha(int r2) {
        return ColorUtils.setAlphaComponent(r2, Math.min(Color.alpha(r2) * 2, 255));
    }

    private static int getColorForState(ColorStateList r1, int[] r2) {
        if (r1 == null) goto L4;
        int r12 = r1.getColorForState(r2, r1.getDefaultColor());
    L6:
        return doubleAlpha(r12);
    L4:
        r12 = 0;
        goto L6
    }

    public static ColorStateList sanitizeRippleDrawableColor(ColorStateList r3) {
        if (r3 == null) goto L13;
        if (Build.VERSION.SDK_INT <= 27) goto L7;
    L11:
        return r3;
    L7:
        if (Color.alpha(r3.getDefaultColor()) != 0) goto L11;
        if (Color.alpha(r3.getColorForState(ENABLED_PRESSED_STATE_SET, 0)) == 0) goto L11;
        Log.w(LOG_TAG, TRANSPARENT_DEFAULT_COLOR_WARNING);
        goto L11
    L13:
        return ColorStateList.valueOf(0);
    }

    public static boolean shouldDrawRippleCompat(int[] r8) {
        int r0 = r8.length;
        int r2 = 0;
        boolean r3 = false;
        boolean r4 = false;
    L4:
        if (r2 >= r0) goto L18;
        int r6 = r8[r2];
        if (r6 != 16842910) goto L9;
        r3 = true;
    L17:
        r2 = r2 + 1;
        goto L4
    L9:
        if (r6 != 16842908) goto L12;
    L10:
        r4 = true;
        goto L17
    L12:
        if (r6 == 16842919) goto L10;
        if (r6 != 16843623) goto L17;
    L18:
        if (r3 == false) goto L21;
        if (r4 == false) goto L21;
        return true;
    L21:
        return false;
    }
}
