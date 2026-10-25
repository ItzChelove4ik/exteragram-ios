package org.telegram.ui.ActionBar;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.SparseArray;
import com.exteragram.messenger.utils.ui.MonetUtils;
import java.io.File;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.MotionBackgroundDrawable;

/* loaded from: classes3.dex */
public abstract class MonetAccentHelper {
    private static final int[] ACCENT_IDS = null;
    private static Bitmap fallbackPatternBitmap;
    private static int fallbackPatternHeight;
    private static int fallbackPatternWidth;

    static {
        ACCENT_IDS = new int[]{91, 90, 89};
    }

    public static void appendAccentOptions(Theme.ThemeInfo r3) {
        if (isSupported() == false) goto L14;
        if (r3 != null) goto L6;
        return;
    L6:
        if (r3.themeAccentsMap != null) goto L8;
        return;
    L8:
        if (r3.themeAccents == null) goto L18;
        int r0 = 0;
    L11:
        int[] r1 = ACCENT_IDS;
        if (r0 >= r1.length) goto L19;
        ensureAccent(r3, r1[r0], r0);
        r0 = r0 + 1;
        goto L11
    L19:
        return;
    L18:
        return;
    }

    public static boolean canEditAccent(Theme.ThemeAccent r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (r2.id >= 100) goto L6;
        return false;
    L6:
        if (r2.isDefault == false) goto L8;
        return false;
    L8:
        if (isMonetAccent(r2) == true) goto L15;
        return true;
    L15:
        return false;
    }

    private static long changeWallpaperColor(Theme.ThemeInfo r4, int r5, long r6) {
        if (r6 != 0) goto L5;
    L15:
        return 0;
    L5:
        if (r6 == 4294967296L) goto L15;
        if (r5 == 0) goto L14;
        int r0 = r4.accentBaseColor;
        if (r0 == 0) goto L14;
        if (r5 == r0) goto L14;
        Color.colorToHSV(r0, new float[3]);
        Color.colorToHSV(r5, new float[3]);
        int r52 = (int) r6;
        return Theme.changeColorAccent(r2, r1, r52, r4.isDark(), r52);
    L14:
        return r6;
    }

    public static int countLeadingMonetAccents(ArrayList<Theme.ThemeAccent> r4) {
        int r0 = 0;
        if (r4 != null) goto L5;
        return 0;
    L5:
        int r1 = r4.size();
        int r2 = 0;
    L6:
        if (r0 >= r1) goto L11;
        if (isMonetAccent(r4.get(r0)) == false) goto L11;
        r2 = r2 + 1;
        r0 = r0 + 1;
    L11:
        return r2;
    }

    public static MotionBackgroundDrawable createFallbackPatternDrawable(int r8, int r9, int r10, int r11, int r12, int r13, boolean r14, int r15) {
        if (r9 == 0) goto L9;
        if (r10 != 0) goto L7;
        if (r11 != 0) goto L7;
        MotionBackgroundDrawable r0 = new MotionBackgroundDrawable(r8, r9, r8, r9, r12, r14);
    L10:
        r0.setPhase(r15);
        r0.setPatternBitmap(r13, getFallbackPatternBitmap());
        r0.setPatternColorFilter(r0.getPatternColor());
        return r0;
    L7:
        MotionBackgroundDrawable r1 = new MotionBackgroundDrawable(r8, r9, r10, r11, r12, r14);
    L8:
        r0 = r1;
        goto L10
    L9:
        r1 = new MotionBackgroundDrawable(r8, r8, r8, r8, r12, r14);
        goto L8
    }

    private static void deleteCachedWallpaper(File r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r1.exists() == false) goto L8;
        r1.delete();
        return;
    }

    private static void ensureAccent(Theme.ThemeInfo r2, int r3, int r4) {
        Theme.ThemeAccent r0 = r2.themeAccentsMap.get(r3);
        if (r0 != null) goto L5;
        r0 = new Theme.ThemeAccent();
        r0.id = r3;
        r0.parentTheme = r2;
        r2.themeAccentsMap.put(r3, r0);
        r2.themeAccents.add(r0);
        r2.defaultAccentCount++;
    L5:
        fillAccentValues(r2, r0, r4, false);
    }

    private static boolean fillAccentValues(Theme.ThemeInfo r3, Theme.ThemeAccent r4, int r5, boolean r6) {
        File r0 = r4.getPathToWallpaper();
        int r52 = MonetUtils.getSystemAccentColor(r5, r3.isDark());
        Theme.ThemeAccent r1 = getSourceAccent(r3, r4.id);
        r4.accentColor = r52;
        r4.accentColor2 = 0;
        r4.myMessagesAccentColor = r52;
        r4.myMessagesGradientAccentColor1 = 0;
        r4.myMessagesGradientAccentColor2 = 0;
        r4.myMessagesGradientAccentColor3 = 0;
        r4.myMessagesAnimated = false;
        fillWallpaperValues(r3, r4, r1, r52);
        if (r1 == null) goto L8;
        if (TextUtils.isEmpty(r1.patternSlug) == true) goto L8;
        r4.patternSlug = r1.patternSlug;
        r4.patternIntensity = r1.patternIntensity;
        r4.patternMotion = r1.patternMotion;
    L11:
        if (r6 == false) goto L13;
        deleteCachedWallpaper(r0);
        deleteCachedWallpaper(r4.getPathToWallpaper());
    L13:
        if (r6 == true) goto L15;
    L18:
        return false;
    L15:
        if (hasRemotePatternWallpaper(r4) == false) goto L18;
        return true;
    L8:
        if (r3.firstAccentIsDefault == false) goto L10;
        r4.patternSlug = "__monet_default_pattern__";
        r4.patternIntensity = 0.34f;
        r4.patternMotion = false;
        goto L11
    L10:
        r4.patternSlug = _UrlKt.FRAGMENT_ENCODE_SET;
        r4.patternIntensity = 0.0f;
        r4.patternMotion = false;
        goto L11
    }

    private static void fillWallpaperValues(Theme.ThemeInfo r2, Theme.ThemeAccent r3, Theme.ThemeAccent r4, int r5) {
        if (r4 != null) goto L4;
    L8:
        r3.backgroundOverrideColor = 0;
        r3.backgroundGradientOverrideColor1 = 0;
        r3.backgroundGradientOverrideColor2 = 0;
        r3.backgroundGradientOverrideColor3 = 0;
        r3.backgroundRotation = 45;
        return;
    L4:
        if (hasWallpaperColors(r4) == false) goto L8;
        r3.backgroundOverrideColor = changeWallpaperColor(r2, r5, r4.backgroundOverrideColor);
        r3.backgroundGradientOverrideColor1 = changeWallpaperColor(r2, r5, r4.backgroundGradientOverrideColor1);
        r3.backgroundGradientOverrideColor2 = changeWallpaperColor(r2, r5, r4.backgroundGradientOverrideColor2);
        r3.backgroundGradientOverrideColor3 = changeWallpaperColor(r2, r5, r4.backgroundGradientOverrideColor3);
        r3.backgroundRotation = r4.backgroundRotation;
    }

    private static Bitmap getFallbackPatternBitmap() {
        Point r0 = AndroidUtilities.displaySize;
        int r02 = Math.max(1, Math.min(r0.x, r0.y));
        Point r2 = AndroidUtilities.displaySize;
        int r1 = Math.max(1, Math.max(r2.x, r2.y));
        monitor-enter(MonetAccentHelper.class);
        Bitmap r3 = fallbackPatternBitmap;     // Catch: Throwable -> L13
        if (r3 != null) goto L7;
    L15:
        fallbackPatternBitmap = SvgHelper.getBitmap(R.raw.default_pattern, r02, r1, -16777216);     // Catch: Throwable -> L13
        fallbackPatternWidth = r02;     // Catch: Throwable -> L13
        fallbackPatternHeight = r1;     // Catch: Throwable -> L13
    L16:
        Bitmap r03 = fallbackPatternBitmap;     // Catch: Throwable -> L13
        monitor-exit(MonetAccentHelper.class);     // Catch: Throwable -> L13
        return r03;
    L7:
        if (r3.isRecycled() == true) goto L15;
        if (fallbackPatternWidth != r02) goto L15;
        if (fallbackPatternHeight == r1) goto L16;
    L13:
        th = move-exception;
        throw th;
    }

    private static Theme.ThemeAccent getSourceAccent(Theme.ThemeInfo r5, int r6) {
        SparseArray<Theme.ThemeAccent> r0 = r5.themeAccentsMap;
        if (r0 != null) goto L5;
    L24:
        return null;
    L5:
        if (r5.themeAccents == null) goto L24;
        int r3 = 0;
        if (r5.firstAccentIsDefault == false) goto L10;
        int r2 = Theme.DEFALT_THEME_ACCENT_ID;
    L11:
        Theme.ThemeAccent r02 = r0.get(r2);
        if (r02 != null) goto L14;
    L16:
        int r03 = r5.themeAccents.size();
    L17:
        if (r3 >= r03) goto L24;
        Theme.ThemeAccent r22 = r5.themeAccents.get(r3);
        if (isMonetAccent(r22) == true) goto L23;
        if (r22.id == r6) goto L23;
        return r22;
    L23:
        r3 = r3 + 1;
        goto L17
    L14:
        if (r02.id == r6) goto L16;
        return r02;
    L10:
        r2 = 0;
        goto L11
    }

    public static boolean hasRemotePatternWallpaper(Theme.ThemeAccent r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (TextUtils.isEmpty(r1.patternSlug) == false) goto L6;
        return false;
    L6:
        if (isFallbackPattern(r1) == true) goto L12;
        return true;
    L12:
        return false;
    }

    private static boolean hasWallpaperColors(Theme.ThemeAccent r4) {
        if (r4.backgroundOverrideColor == 0) goto L5;
        return true;
    L5:
        if (r4.backgroundGradientOverrideColor1 == 0) goto L7;
        return true;
    L7:
        if (r4.backgroundGradientOverrideColor2 == 0) goto L9;
        return true;
    L9:
        if (r4.backgroundGradientOverrideColor3 != 0) goto L17;
        return false;
    L17:
        return true;
    }

    public static boolean isFallbackPattern(Theme.ThemeAccent r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if ("__monet_default_pattern__".equals(r1.patternSlug) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isMonetAccent(Theme.ThemeAccent r2) {
        if (r2 != null) goto L5;
        return false;
    L5:
        int r22 = r2.id;
        if (r22 != 91) goto L8;
        return true;
    L8:
        if (r22 != 90) goto L10;
        return true;
    L10:
        if (r22 == 89) goto L16;
        return false;
    L16:
        return true;
    }

    private static boolean isSupported() {
        return MonetUtils.isSupported();
    }

    public static boolean refresh(Theme.ThemeInfo r1) {
        if (r1 != null) goto L5;
        return false;
    L5:
        boolean r0 = refreshAccents(r1);
        refreshPreviewColors(r1);
        return r0;
    }

    private static boolean refreshAccents(Theme.ThemeInfo r4) {
        int r1 = 0;
        if (isSupported() == true) goto L5;
    L15:
        return false;
    L5:
        if (r4.themeAccentsMap == null) goto L15;
        boolean r0 = false;
    L8:
        int[] r2 = ACCENT_IDS;
        if (r1 >= r2.length) goto L14;
        Theme.ThemeAccent r22 = r4.themeAccentsMap.get(r2[r1]);
        if (r22 == null) goto L13;
        r0 = r0 | fillAccentValues(r4, r22, r1, true);
    L13:
        r1 = r1 + 1;
        goto L8
    L14:
        return r0;
    }

    private static void refreshPreviewColors(Theme.ThemeInfo r4) {
        if (isSupported() == true) goto L5;
        return;
    L5:
        if (r4.isMonet() == true) goto L8;
        return;
    L8:
        if ("Monet Light".equals(r4.name) == false) goto L12;
        r4.setPreviewBackgroundColor(MonetUtils.getColor("n1_10"));
        r4.setPreviewInColor(MonetUtils.getColor("n1_50"));
        r4.setPreviewOutColor(MonetUtils.getColor("a1_600"));
        return;
    L12:
        if ("Monet Dark".equals(r4.name) == false) goto L16;
        r4.setPreviewBackgroundColor(MonetUtils.getColor("n1_900"));
        r4.setPreviewInColor(MonetUtils.getColor("n1_800"));
        r4.setPreviewOutColor(MonetUtils.getColor("a1_200"));
        return;
    L16:
        if ("Monet Black".equals(r4.name) == false) goto L20;
        r4.setPreviewBackgroundColor(MonetUtils.getColor("mBlack"));
        r4.setPreviewInColor(MonetUtils.getColor("n1_800"));
        r4.setPreviewOutColor(MonetUtils.getColor("a1_200"));
        return;
    }
}
