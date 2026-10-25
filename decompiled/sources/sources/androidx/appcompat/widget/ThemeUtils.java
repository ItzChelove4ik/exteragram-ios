package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.appcompat.R$styleable;
import androidx.core.graphics.ColorUtils;

/* loaded from: classes3.dex */
public abstract class ThemeUtils {
    static final int[] ACTIVATED_STATE_SET = null;
    static final int[] CHECKED_STATE_SET = null;
    static final int[] DISABLED_STATE_SET = null;
    static final int[] EMPTY_STATE_SET = null;
    static final int[] FOCUSED_STATE_SET = null;
    static final int[] NOT_PRESSED_OR_FOCUSED_STATE_SET = null;
    static final int[] PRESSED_STATE_SET = null;
    static final int[] SELECTED_STATE_SET = null;
    private static final int[] TEMP_ARRAY = null;
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = null;

    static {
        TL_TYPED_VALUE = new ThreadLocal();
        DISABLED_STATE_SET = new int[]{-16842910};
        FOCUSED_STATE_SET = new int[]{R.attr.state_focused};
        ACTIVATED_STATE_SET = new int[]{R.attr.state_activated};
        PRESSED_STATE_SET = new int[]{R.attr.state_pressed};
        CHECKED_STATE_SET = new int[]{R.attr.state_checked};
        SELECTED_STATE_SET = new int[]{R.attr.state_selected};
        NOT_PRESSED_OR_FOCUSED_STATE_SET = new int[]{-16842919, -16842908};
        EMPTY_STATE_SET = new int[0];
        TEMP_ARRAY = new int[1];
    }

    public static void checkAppCompatTheme(View r3, Context r4) {
        TypedArray r42 = r4.obtainStyledAttributes(R$styleable.AppCompatTheme);
    L7:
        th = move-exception;
        r42.recycle();
        throw th;
    L4:
        if (r42.hasValue(R$styleable.AppCompatTheme_windowActionBar) == true) goto L9;
        Log.e("ThemeUtils", "View " + r3.getClass() + " is an AppCompat widget that can only be used with a Theme.AppCompat theme (or descendant).");     // Catch: Throwable -> L7
    L9:
        r42.recycle();
    }

    public static int getDisabledThemeAttrColor(Context r4, int r5) {
        ColorStateList r0 = getThemeAttrColorStateList(r4, r5);
        if (r0 != null) goto L5;
    L8:
        TypedValue r02 = getTypedValue();
        r4.getTheme().resolveAttribute(R.attr.disabledAlpha, r02, true);
        return getThemeAttrColor(r4, r5, r02.getFloat());
    L5:
        if (r0.isStateful() == false) goto L8;
        return r0.getColorForState(DISABLED_STATE_SET, r0.getDefaultColor());
    }

    public static int getThemeAttrColor(Context r2, int r3) {
        int[] r0 = TEMP_ARRAY;
        r0[0] = r3;
        TintTypedArray r22 = TintTypedArray.obtainStyledAttributes(r2, null, r0);
        int r32 = r22.getColor(0, 0);     // Catch: Throwable -> L6
        r22.recycle();
        return r32;
    L6:
        th = move-exception;
        r22.recycle();
        throw th;
    }

    public static int getThemeAttrColor(Context r0, int r1, float r2) {
        return ColorUtils.setAlphaComponent(getThemeAttrColor(r0, r1), Math.round(Color.alpha(r0) * r2));
    }

    public static ColorStateList getThemeAttrColorStateList(Context r2, int r3) {
        int[] r0 = TEMP_ARRAY;
        r0[0] = r3;
        TintTypedArray r22 = TintTypedArray.obtainStyledAttributes(r2, null, r0);
        ColorStateList r32 = r22.getColorStateList(0);     // Catch: Throwable -> L6
        r22.recycle();
        return r32;
    L6:
        th = move-exception;
        r22.recycle();
        throw th;
    }

    private static TypedValue getTypedValue() {
        ThreadLocal<TypedValue> r0 = TL_TYPED_VALUE;
        TypedValue r1 = r0.get();
        if (r1 != null) goto L6;
        TypedValue r12 = new TypedValue();
        r0.set(r12);
        return r12;
    L6:
        return r1;
    }
}
