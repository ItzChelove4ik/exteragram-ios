package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import androidx.core.internal.view.SupportMenuItem;

/* loaded from: classes3.dex */
public abstract class MenuItemCompat {

    public static class Api26Impl {
        public static MenuItem setAlphabeticShortcut(MenuItem r0, char r1, int r2) {
            return r0.setAlphabeticShortcut(r1, r2);
        }

        public static MenuItem setContentDescription(MenuItem r0, CharSequence r1) {
            return r0.setContentDescription(r1);
        }

        public static MenuItem setIconTintList(MenuItem r0, ColorStateList r1) {
            return r0.setIconTintList(r1);
        }

        public static MenuItem setIconTintMode(MenuItem r0, PorterDuff.Mode r1) {
            return r0.setIconTintMode(r1);
        }

        public static MenuItem setNumericShortcut(MenuItem r0, char r1, int r2) {
            return r0.setNumericShortcut(r1, r2);
        }

        public static MenuItem setTooltipText(MenuItem r0, CharSequence r1) {
            return r0.setTooltipText(r1);
        }
    }

    public static ActionProvider getActionProvider(MenuItem r1) {
        if ((r1 instanceof SupportMenuItem) == true) goto L5;
        Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
        return null;
    L5:
        return ((SupportMenuItem) r1).getSupportActionProvider();
    }

    public static MenuItem setActionProvider(MenuItem r1, ActionProvider r2) {
        if ((r1 instanceof SupportMenuItem) == true) goto L5;
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return r1;
    L5:
        return ((SupportMenuItem) r1).setSupportActionProvider(r2);
    }

    public static void setAlphabeticShortcut(MenuItem r2, char r3, int r4) {
        if ((r2 instanceof SupportMenuItem) == false) goto L7;
        ((SupportMenuItem) r2).setAlphabeticShortcut(r3, r4);
        return;
    L7:
        if (Build.VERSION.SDK_INT < 26) goto L10;
        Api26Impl.setAlphabeticShortcut(r2, r3, r4);
        return;
    }

    public static void setContentDescription(MenuItem r2, CharSequence r3) {
        if ((r2 instanceof SupportMenuItem) == false) goto L7;
        ((SupportMenuItem) r2).setContentDescription(r3);
        return;
    L7:
        if (Build.VERSION.SDK_INT < 26) goto L10;
        Api26Impl.setContentDescription(r2, r3);
        return;
    }

    public static void setIconTintList(MenuItem r2, ColorStateList r3) {
        if ((r2 instanceof SupportMenuItem) == false) goto L7;
        ((SupportMenuItem) r2).setIconTintList(r3);
        return;
    L7:
        if (Build.VERSION.SDK_INT < 26) goto L10;
        Api26Impl.setIconTintList(r2, r3);
        return;
    }

    public static void setIconTintMode(MenuItem r2, PorterDuff.Mode r3) {
        if ((r2 instanceof SupportMenuItem) == false) goto L7;
        ((SupportMenuItem) r2).setIconTintMode(r3);
        return;
    L7:
        if (Build.VERSION.SDK_INT < 26) goto L10;
        Api26Impl.setIconTintMode(r2, r3);
        return;
    }

    public static void setNumericShortcut(MenuItem r2, char r3, int r4) {
        if ((r2 instanceof SupportMenuItem) == false) goto L7;
        ((SupportMenuItem) r2).setNumericShortcut(r3, r4);
        return;
    L7:
        if (Build.VERSION.SDK_INT < 26) goto L10;
        Api26Impl.setNumericShortcut(r2, r3, r4);
        return;
    }

    public static void setTooltipText(MenuItem r2, CharSequence r3) {
        if ((r2 instanceof SupportMenuItem) == false) goto L7;
        ((SupportMenuItem) r2).setTooltipText(r3);
        return;
    L7:
        if (Build.VERSION.SDK_INT < 26) goto L10;
        Api26Impl.setTooltipText(r2, r3);
        return;
    }
}
