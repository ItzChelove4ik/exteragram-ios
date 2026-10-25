package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes3.dex */
public abstract class DrawableCompat {
    public static void applyTheme(Drawable r0, Resources.Theme r1) {
        r0.applyTheme(r1);
    }

    public static boolean canApplyTheme(Drawable r0) {
        return r0.canApplyTheme();
    }

    public static void clearColorFilter(Drawable r0) {
        r0.clearColorFilter();
    }

    @Deprecated
    public static int getAlpha(Drawable r0) {
        return r0.getAlpha();
    }

    public static ColorFilter getColorFilter(Drawable r0) {
        return r0.getColorFilter();
    }

    public static int getLayoutDirection(Drawable r0) {
        return r0.getLayoutDirection();
    }

    public static void inflate(Drawable r0, Resources r1, XmlPullParser r2, AttributeSet r3, Resources.Theme r4) {
        r0.inflate(r1, r2, r3, r4);
    }

    @Deprecated
    public static boolean isAutoMirrored(Drawable r0) {
        return r0.isAutoMirrored();
    }

    @Deprecated
    public static void jumpToCurrentState(Drawable r0) {
        r0.jumpToCurrentState();
    }

    @Deprecated
    public static void setAutoMirrored(Drawable r0, boolean r1) {
        r0.setAutoMirrored(r1);
    }

    public static void setHotspot(Drawable r0, float r1, float r2) {
        r0.setHotspot(r1, r2);
    }

    public static void setHotspotBounds(Drawable r0, int r1, int r2, int r3, int r4) {
        r0.setHotspotBounds(r1, r2, r3, r4);
    }

    public static boolean setLayoutDirection(Drawable r0, int r1) {
        return r0.setLayoutDirection(r1);
    }

    public static void setTint(Drawable r0, int r1) {
        r0.setTint(r1);
    }

    public static void setTintList(Drawable r0, ColorStateList r1) {
        r0.setTintList(r1);
    }

    public static void setTintMode(Drawable r0, PorterDuff.Mode r1) {
        r0.setTintMode(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Drawable> T unwrap(Drawable r0) {
        return r0;
    }

    public static Drawable wrap(Drawable r0) {
        return r0;
    }
}
