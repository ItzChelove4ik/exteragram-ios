package androidx.appcompat.widget;

import android.R;
import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.graphics.drawable.DrawableCompat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public abstract class DrawableUtils {
    private static final int[] CHECKED_STATE_SET = null;
    private static final int[] EMPTY_STATE_SET = null;
    public static final Rect INSETS_NONE = null;

    public static class Api18Impl {
        private static final Field sBottom = null;
        private static final Method sGetOpticalInsets = null;
        private static final Field sLeft = null;
        private static final boolean sReflectionSuccessful = false;
        private static final Field sRight = null;
        private static final Field sTop = null;

        static {
            Class<?> r3 = Class.forName("android.graphics.Insets");     // Catch: NoSuchFieldException -> L20 ClassNotFoundException -> L21 NoSuchMethodException -> L22
            Method r4 = Drawable.class.getMethod("getOpticalInsets", null);     // Catch: NoSuchFieldException -> L20 ClassNotFoundException -> L21 NoSuchMethodException -> L22
            Field r5 = r3.getField("left");     // Catch: NoSuchFieldException -> L14 ClassNotFoundException -> L16 NoSuchMethodException -> L18
        L37:
            Field r6 = r3.getField("top");     // Catch: NoSuchFieldException -> L10 ClassNotFoundException -> L12 NoSuchMethodException -> L13
            Field r7 = r3.getField("right");     // Catch: Throwable -> L9
            Field r32 = r3.getField("bottom");     // Catch: Throwable -> L28
            boolean r8 = true;
        L24:
            if (r8 == false) goto L26;
            sGetOpticalInsets = r4;
            sLeft = r5;
            sTop = r6;
            sRight = r7;
            sBottom = r32;
            sReflectionSuccessful = true;
            return;
        L26:
            sGetOpticalInsets = null;
            sLeft = null;
            sTop = null;
            sRight = null;
            sBottom = null;
            sReflectionSuccessful = false;
            return;
        L23:
            r32 = null;
            r8 = false;
        L9:
            r7 = null;
            goto L23
        L12:
            r6 = null;
        L11:
            r7 = r6;
        L10:
            r6 = null;
        L13:
            r6 = null;
        L16:
            r5 = null;
        L17:
            r6 = r5;
        L14:
            r5 = null;
        L15:
            r6 = r5;
        L18:
            r5 = null;
        L19:
            r6 = r5;
            goto L11
        L21:
            r4 = null;
            r5 = null;
        L20:
            r4 = null;
            r5 = null;
        L22:
            r4 = null;
            r5 = null;
            goto L19
        }

        public static Rect getOpticalInsets(Drawable r5) {
            if (Build.VERSION.SDK_INT >= 29) goto L11;
            if (sReflectionSuccessful == false) goto L11;
            Object r52 = sGetOpticalInsets.invoke(r5, null);     // Catch: Throwable -> L12
            if (r52 == null) goto L11;
            return new Rect(sLeft.getInt(r52), sTop.getInt(r52), sRight.getInt(r52), sBottom.getInt(r52));
        L11:
            return DrawableUtils.INSETS_NONE;
        }
    }

    public static class Api29Impl {
        public static Insets getOpticalInsets(Drawable r0) {
            return r0.getOpticalInsets();
        }
    }

    static {
        CHECKED_STATE_SET = new int[]{R.attr.state_checked};
        EMPTY_STATE_SET = new int[0];
        INSETS_NONE = new Rect();
    }

    @Deprecated
    public static boolean canSafelyMutateDrawable(Drawable r0) {
        return true;
    }

    public static void fixDrawable(Drawable r3) {
        String r0 = r3.getClass().getName();
        int r1 = Build.VERSION.SDK_INT;
        if (r1 >= 29) goto L5;
        return;
    L5:
        if (r1 < 31) goto L7;
        return;
    L7:
        if ("android.graphics.drawable.ColorStateListDrawable".equals(r0) == false) goto L12;
        forceDrawableStateChange(r3);
        return;
    }

    private static void forceDrawableStateChange(Drawable r2) {
        int[] r0 = r2.getState();
        if (r0 != null) goto L5;
    L8:
        r2.setState(CHECKED_STATE_SET);
    L9:
        r2.setState(r0);
        return;
    L5:
        if (r0.length == 0) goto L8;
        r2.setState(EMPTY_STATE_SET);
        goto L9
    }

    public static Rect getOpticalBounds(Drawable r4) {
        if (Build.VERSION.SDK_INT < 29) goto L7;
        Insets r42 = Api29Impl.getOpticalInsets(r4);
        return new Rect(DrawableUtils$$ExternalSyntheticApiModelOutline0.m(r42), DrawableUtils$$ExternalSyntheticApiModelOutline1.m(r42), DrawableUtils$$ExternalSyntheticApiModelOutline2.m(r42), DrawableUtils$$ExternalSyntheticApiModelOutline3.m(r42));
    L7:
        return Api18Impl.getOpticalInsets(DrawableCompat.unwrap(r4));
    }

    public static PorterDuff.Mode parseTintMode(int r1, PorterDuff.Mode r2) {
        if (r1 == 3) goto L21;
        if (r1 == 5) goto L19;
        if (r1 == 9) goto L17;
        switch(r1) {
            case 14: goto L15;
            case 15: goto L13;
            case 16: goto L11;
            default: goto L9;
        };
    L9:
        return r2;
    L11:
        return PorterDuff.Mode.ADD;
    L13:
        return PorterDuff.Mode.SCREEN;
    L15:
        return PorterDuff.Mode.MULTIPLY;
    L17:
        return PorterDuff.Mode.SRC_ATOP;
    L19:
        return PorterDuff.Mode.SRC_IN;
    L21:
        return PorterDuff.Mode.SRC_OVER;
    }
}
