package com.google.android.material.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.core.graphics.drawable.DrawableCompat;
import java.io.IOException;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public final class DrawableUtils {
    public static final int INTRINSIC_SIZE = -1;
    private static final int UNSPECIFIED_HEIGHT = -1;
    private static final int UNSPECIFIED_WIDTH = -1;

    public static class OutlineCompatL {
        private OutlineCompatL() {
        }

        public static void setConvexPath(Outline r0, Path r1) {
            r0.setConvexPath(r1);
        }
    }

    public static class OutlineCompatR {
        private OutlineCompatR() {
        }

        public static void setPath(Outline r0, Path r1) {
            r0.setPath(r1);
        }
    }

    private DrawableUtils() {
    }

    public static Drawable compositeTwoLayeredDrawable(Drawable r1, Drawable r2) {
        return compositeTwoLayeredDrawable(r1, r2, -1, -1);
    }

    public static Drawable compositeTwoLayeredDrawable(Drawable r2, Drawable r3, int r4, int r5) {
        if (r2 != null) goto L4;
        return r3;
    L4:
        if (r3 != null) goto L7;
        return r2;
    L7:
        if (r4 != (-1)) goto L9;
        r4 = getTopLayerIntrinsicWidth(r2, r3);
    L9:
        if (r5 != (-1)) goto L12;
        r5 = getTopLayerIntrinsicHeight(r2, r3);
    L12:
        if (r4 <= r2.getIntrinsicWidth()) goto L14;
    L16:
        float r42 = r4 / r5;
        if (r42 < (r2.getIntrinsicWidth() / r2.getIntrinsicHeight())) goto L19;
        int r52 = r2.getIntrinsicWidth();
        r5 = (int) (r52 / r42);
        r4 = r52;
    L20:
        LayerDrawable r0 = new LayerDrawable(new Drawable[]{r2, r3});
        r0.setLayerSize(1, r4, r5);
        r0.setLayerGravity(1, 17);
        return r0;
    L19:
        r5 = r2.getIntrinsicHeight();
        r4 = (int) (r42 * r5);
        goto L20
    L14:
        if (r5 > r2.getIntrinsicHeight()) goto L16;
        goto L16
    }

    public static Drawable createTintableDrawableIfNeeded(Drawable r1, ColorStateList r2, PorterDuff.Mode r3) {
        return createTintableMutatedDrawableIfNeeded(r1, r2, r3, false);
    }

    public static Drawable createTintableMutatedDrawableIfNeeded(Drawable r1, ColorStateList r2, PorterDuff.Mode r3) {
        return createTintableMutatedDrawableIfNeeded(r1, r2, r3, false);
    }

    private static Drawable createTintableMutatedDrawableIfNeeded(Drawable r0, ColorStateList r1, PorterDuff.Mode r2, boolean r3) {
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r1 == null) goto L10;
        Drawable r02 = DrawableCompat.wrap(r0).mutate();
        if (r2 == null) goto L9;
        r02.setTintMode(r2);
    L9:
        return r02;
    L10:
        if (r3 == false) goto L12;
        r0.mutate();
    L12:
        return r0;
    }

    public static int[] getCheckedState(int[] r3) {
        int r0 = 0;
    L4:
        if (r0 >= r3.length) goto L12;
        int r1 = r3[r0];
        if (r1 == 16842912) goto L7;
        if (r1 == 0) goto L9;
        r0 = r0 + 1;
        goto L4
    L9:
        int[] r32 = (int[]) r3.clone();
        r32[r0] = 16842912;
        return r32;
    L7:
        return r3;
    L12:
        int[] r02 = Arrays.copyOf(r3, r3.length + 1);
        r02[r3.length] = 16842912;
        return r02;
    }

    public static ColorStateList getColorStateListOrNull(Drawable r2) {
        if ((r2 instanceof ColorDrawable) == false) goto L7;
        return ColorStateList.valueOf(((ColorDrawable) r2).getColor());
    L7:
        if (Build.VERSION.SDK_INT >= 29) goto L9;
        return null;
    L9:
        if (DrawableUtils$$ExternalSyntheticApiModelOutline0.m(r2) == true) goto L11;
        return null;
    L11:
        return DrawableUtils$$ExternalSyntheticApiModelOutline1.m(r2).getColorStateList();
    }

    private static int getTopLayerIntrinsicHeight(Drawable r1, Drawable r2) {
        int r22 = r2.getIntrinsicHeight();
        if (r22 == (-1)) goto L6;
        return r22;
    L6:
        return r1.getIntrinsicHeight();
    }

    private static int getTopLayerIntrinsicWidth(Drawable r1, Drawable r2) {
        int r22 = r2.getIntrinsicWidth();
        if (r22 == (-1)) goto L6;
        return r22;
    L6:
        return r1.getIntrinsicWidth();
    }

    public static int[] getUncheckedState(int[] r6) {
        int[] r0 = new int[r6.length];
        int r1 = r6.length;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r1) goto L8;
        int r4 = r6[r2];
        if (r4 == 16842912) goto L7;
        r0[r3] = r4;
        r3 = r3 + 1;
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        return r0;
    }

    public static AttributeSet parseDrawableXml(Context r3, int r4, CharSequence r5) {
        XmlResourceParser r32 = r3.getResources().getXml(r4);     // Catch: IOException -> L12 XmlPullParserException -> L14
    L3:
        int r0 = r32.next();     // Catch: IOException -> L12 XmlPullParserException -> L14
        if (r0 == 2) goto L7;
        if (r0 != 1) goto L3;
    L7:
        if (r0 != 2) goto L19;
        if (TextUtils.equals(r32.getName(), r5) == false) goto L17;
        return Xml.asAttributeSet(r32);
    L17:
        throw new XmlPullParserException("Must have a <" + r5 + "> start tag");     // Catch: IOException -> L12 XmlPullParserException -> L14
    L19:
        throw new XmlPullParserException("No start tag found");     // Catch: IOException -> L12 XmlPullParserException -> L14
    L12:
        e = e;
    L20:
        Resources.NotFoundException r52 = new Resources.NotFoundException("Can't load badge resource ID #0x" + Integer.toHexString(r4));
        r52.initCause(e);
        throw r52;
    L14:
        e = e;
        goto L20
    }

    public static void setOutlineToPath(Outline r2, Path r3) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 30) goto L7;
        OutlineCompatR.setPath(r2, r3);
        return;
    L7:
        if (r0 < 29) goto L11;
        OutlineCompatL.setConvexPath(r2, r3);     // Catch: IllegalArgumentException -> L14
        return;
    L17:
        return;
    L11:
        if (r3.isConvex() == false) goto L18;
        OutlineCompatL.setConvexPath(r2, r3);
        return;
    }

    public static void setRippleDrawableRadius(RippleDrawable r0, int r1) {
        r0.setRadius(r1);
    }

    public static void setTint(Drawable r0, int r1) {
        if (r1 == 0) goto L5;
        r0.setTint(r1);
        return;
    L5:
        r0.setTintList(null);
    }

    public static PorterDuffColorFilter updateTintFilter(Drawable r1, ColorStateList r2, PorterDuff.Mode r3) {
        if (r2 == null) goto L7;
        if (r3 != null) goto L6;
        return null;
    L6:
        return new PorterDuffColorFilter(r2.getColorForState(r1.getState(), 0), r3);
    L7:
        return null;
    }
}
