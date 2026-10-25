package androidx.core.view;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.InputDevice;
import android.view.ViewConfiguration;
import androidx.core.util.Supplier;
import java.lang.reflect.Method;
import java.util.Objects;

/* loaded from: classes.dex */
public abstract class ViewConfigurationCompat {
    private static Method sGetScaledScrollFactorMethod;

    public static class Api26Impl {
        public static float getScaledHorizontalScrollFactor(ViewConfiguration r0) {
            return r0.getScaledHorizontalScrollFactor();
        }

        public static float getScaledVerticalScrollFactor(ViewConfiguration r0) {
            return r0.getScaledVerticalScrollFactor();
        }
    }

    /* loaded from: classes3.dex */
    public static class Api28Impl {
        public static int getScaledHoverSlop(ViewConfiguration r0) {
            return r0.getScaledHoverSlop();
        }

        public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration r0) {
            return r0.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
    }

    /* loaded from: classes3.dex */
    public static class Api34Impl {
        public static int getScaledMaximumFlingVelocity(ViewConfiguration r0, int r1, int r2, int r3) {
            return r0.getScaledMaximumFlingVelocity(r1, r2, r3);
        }

        public static int getScaledMinimumFlingVelocity(ViewConfiguration r0, int r1, int r2, int r3) {
            return r0.getScaledMinimumFlingVelocity(r1, r2, r3);
        }
    }

    static {
        if (Build.VERSION.SDK_INT != 25) goto L10;
        sGetScaledScrollFactorMethod = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor", null);     // Catch: Exception -> L6
        return;
    L6:
        Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
        return;
    }

    private static int getCompatFlingVelocityThreshold(Resources r1, int r2, Supplier<Integer> r3, int r4) {
        if (r2 == (-1)) goto L11;
        if (r2 == 0) goto L9;
        int r12 = r1.getDimensionPixelSize(r2);
        if (r12 < 0) goto L9;
        return r12;
    L9:
        return r4;
    L11:
        return r3.get().intValue();
    }

    private static float getLegacyScrollFactor(ViewConfiguration r3, Context r4) {
        if (Build.VERSION.SDK_INT >= 25) goto L5;
    L11:
        TypedValue r32 = new TypedValue();
        if (r4.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, r32, true) == true) goto L14;
        return 0.0f;
    L14:
        return r32.getDimension(r4.getResources().getDisplayMetrics());
    L5:
        if (sGetScaledScrollFactorMethod == null) goto L11;
        return ((Integer) r0.invoke(r3, null)).intValue();
    L10:
        Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
        goto L11
    }

    private static int getPlatformResId(Resources r1, String r2, String r3) {
        return r1.getIdentifier(r2, r3, "android");
    }

    private static int getPreApi34MaximumFlingVelocityResId(Resources r1, int r2, int r3) {
        if (r2 == 4194304) goto L5;
        return -1;
    L5:
        if (r3 == 26) goto L7;
        return -1;
    L7:
        return getPlatformResId(r1, "config_viewMaxRotaryEncoderFlingVelocity", "dimen");
    }

    private static int getPreApi34MinimumFlingVelocityResId(Resources r1, int r2, int r3) {
        if (r2 == 4194304) goto L5;
        return -1;
    L5:
        if (r3 == 26) goto L7;
        return -1;
    L7:
        return getPlatformResId(r1, "config_viewMinRotaryEncoderFlingVelocity", "dimen");
    }

    public static float getScaledHorizontalScrollFactor(ViewConfiguration r2, Context r3) {
        if (Build.VERSION.SDK_INT < 26) goto L7;
        return Api26Impl.getScaledHorizontalScrollFactor(r2);
    L7:
        return getLegacyScrollFactor(r2, r3);
    }

    public static int getScaledHoverSlop(ViewConfiguration r2) {
        if (Build.VERSION.SDK_INT < 28) goto L7;
        return Api28Impl.getScaledHoverSlop(r2);
    L7:
        return r2.getScaledTouchSlop() / 2;
    }

    public static int getScaledMaximumFlingVelocity(Context r2, ViewConfiguration r3, int r4, int r5, int r6) {
        if (Build.VERSION.SDK_INT < 34) goto L7;
        return Api34Impl.getScaledMaximumFlingVelocity(r3, r4, r5, r6);
    L7:
        if (isInputDeviceInfoValid(r4, r5, r6) == true) goto L9;
        return Integer.MIN_VALUE;
    L9:
        Resources r22 = r2.getResources();
        int r42 = getPreApi34MaximumFlingVelocityResId(r22, r6, r5);
        Objects.requireNonNull(r3);
        return getCompatFlingVelocityThreshold(r22, r42, new ViewConfigurationCompat$$ExternalSyntheticLambda0(r3), Integer.MIN_VALUE);
    }

    public static int getScaledMinimumFlingVelocity(Context r2, ViewConfiguration r3, int r4, int r5, int r6) {
        if (Build.VERSION.SDK_INT < 34) goto L7;
        return Api34Impl.getScaledMinimumFlingVelocity(r3, r4, r5, r6);
    L7:
        if (isInputDeviceInfoValid(r4, r5, r6) == true) goto L9;
        return Integer.MAX_VALUE;
    L9:
        Resources r22 = r2.getResources();
        int r42 = getPreApi34MinimumFlingVelocityResId(r22, r6, r5);
        Objects.requireNonNull(r3);
        return getCompatFlingVelocityThreshold(r22, r42, new ViewConfigurationCompat$$ExternalSyntheticLambda1(r3), Integer.MAX_VALUE);
    }

    public static float getScaledVerticalScrollFactor(ViewConfiguration r2, Context r3) {
        if (Build.VERSION.SDK_INT < 26) goto L7;
        return Api26Impl.getScaledVerticalScrollFactor(r2);
    L7:
        return getLegacyScrollFactor(r2, r3);
    }

    private static boolean isInputDeviceInfoValid(int r0, int r1, int r2) {
        InputDevice r02 = InputDevice.getDevice(r0);
        if (r02 != null) goto L5;
        return false;
    L5:
        if (r02.getMotionRange(r1, r2) == null) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration r2, Context r3) {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        Resources r22 = r3.getResources();
        int r32 = getPlatformResId(r22, "config_showMenuShortcutsWhenKeyboardPresent", "bool");
        if (r32 != 0) goto L9;
        return false;
    L9:
        if (r22.getBoolean(r32) == false) goto L14;
        return true;
    L14:
        return false;
    L5:
        return Api28Impl.shouldShowMenuShortcutsWhenKeyboardPresent(r2);
    }
}
