package com.google.android.material.color;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import androidx.core.os.BuildCompat;
import com.google.android.material.R;
import com.google.android.material.color.DynamicColorsOptions;
import com.google.android.material.color.utilities.Hct;
import com.google.android.material.color.utilities.SchemeContent;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class DynamicColors {
    private static final DeviceSupportCondition DEFAULT_DEVICE_SUPPORT_CONDITION = null;
    private static final Map<String, DeviceSupportCondition> DYNAMIC_COLOR_SUPPORTED_BRANDS = null;
    private static final Map<String, DeviceSupportCondition> DYNAMIC_COLOR_SUPPORTED_MANUFACTURERS = null;
    private static final int[] DYNAMIC_COLOR_THEME_OVERLAY_ATTRIBUTE = null;

    @SuppressLint({"PrivateApi"})
    private static final DeviceSupportCondition SAMSUNG_DEVICE_SUPPORT_CONDITION = null;
    private static final String TAG = null;
    private static final int USE_DEFAULT_THEME_OVERLAY = 0;

    /* renamed from: com.google.android.material.color.DynamicColors$1, reason: invalid class name */
    public class AnonymousClass1 implements DeviceSupportCondition {
        public AnonymousClass1() {
        }

        @Override // com.google.android.material.color.DynamicColors.DeviceSupportCondition
        public boolean isSupported() {
            return true;
        }
    }

    /* renamed from: com.google.android.material.color.DynamicColors$2, reason: invalid class name */
    public class AnonymousClass2 implements DeviceSupportCondition {
        private Long version;

        public AnonymousClass2() {
        }

        @Override // com.google.android.material.color.DynamicColors.DeviceSupportCondition
        public boolean isSupported() {
            if (this.version != null) goto L8;
            Method r0 = Build.class.getDeclaredMethod("getLong", new Class[]{String.class});     // Catch: Exception -> L6
            r0.setAccessible(true);     // Catch: Exception -> L6
            Long r02 = (Long) r0.invoke(null, new Object[]{"ro.build.version.oneui"});     // Catch: Exception -> L6
            r02.longValue();     // Catch: Exception -> L6
            this.version = r02;     // Catch: Exception -> L6
        L6:
            this.version = -1L;
        L8:
            if (this.version.longValue() >= 40100) goto L14;
            return false;
        L14:
            return true;
        }
    }

    public interface DeviceSupportCondition {
        boolean isSupported();
    }

    public static class DynamicColorsActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        private final DynamicColorsOptions dynamicColorsOptions;

        public DynamicColorsActivityLifecycleCallbacks(DynamicColorsOptions r1) {
            this.dynamicColorsOptions = r1;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity r1, Bundle r2) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity r1) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity r1) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreCreated(Activity r1, Bundle r2) {
            DynamicColors.applyToActivityIfAvailable(r1, this.dynamicColorsOptions);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity r1) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity r1, Bundle r2) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity r1) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity r1) {
        }
    }

    public interface OnAppliedCallback {
        void onApplied(Activity r1);
    }

    public interface Precondition {
        boolean shouldApplyDynamicColors(Activity r1, int r2);
    }

    static {
        DYNAMIC_COLOR_THEME_OVERLAY_ATTRIBUTE = new int[]{R.attr.dynamicColorThemeOverlay};
        AnonymousClass1 r0 = new AnonymousClass1();
        DEFAULT_DEVICE_SUPPORT_CONDITION = r0;
        AnonymousClass2 r1 = new AnonymousClass2();
        SAMSUNG_DEVICE_SUPPORT_CONDITION = r1;
        HashMap r2 = new HashMap();
        r2.put("fcnt", r0);
        r2.put("google", r0);
        r2.put("hmd global", r0);
        r2.put("infinix", r0);
        r2.put("infinix mobility limited", r0);
        r2.put("itel", r0);
        r2.put("kyocera", r0);
        r2.put("lenovo", r0);
        r2.put("lge", r0);
        r2.put("meizu", r0);
        r2.put("motorola", r0);
        r2.put("nothing", r0);
        r2.put("oneplus", r0);
        r2.put("oppo", r0);
        r2.put("realme", r0);
        r2.put("robolectric", r0);
        r2.put("samsung", r1);
        r2.put("sharp", r0);
        r2.put("shift", r0);
        r2.put("sony", r0);
        r2.put("tcl", r0);
        r2.put("tecno", r0);
        r2.put("tecno mobile limited", r0);
        r2.put("vivo", r0);
        r2.put("wingtech", r0);
        r2.put("xiaomi", r0);
        DYNAMIC_COLOR_SUPPORTED_MANUFACTURERS = Collections.unmodifiableMap(r2);
        HashMap r12 = new HashMap();
        r12.put("asus", r0);
        r12.put("jio", r0);
        DYNAMIC_COLOR_SUPPORTED_BRANDS = Collections.unmodifiableMap(r12);
        TAG = DynamicColors.class.getSimpleName();
    }

    private DynamicColors() {
    }

    @Deprecated
    public static void applyIfAvailable(Activity r0) {
        applyToActivityIfAvailable(r0);
    }

    @Deprecated
    public static void applyIfAvailable(Activity r1, int r2) {
        applyToActivityIfAvailable(r1, new DynamicColorsOptions.Builder().setThemeOverlay(r2).build());
    }

    @Deprecated
    public static void applyIfAvailable(Activity r1, Precondition r2) {
        applyToActivityIfAvailable(r1, new DynamicColorsOptions.Builder().setPrecondition(r2).build());
    }

    public static void applyToActivitiesIfAvailable(Application r1) {
        applyToActivitiesIfAvailable(r1, new DynamicColorsOptions.Builder().build());
    }

    @Deprecated
    public static void applyToActivitiesIfAvailable(Application r1, int r2) {
        applyToActivitiesIfAvailable(r1, new DynamicColorsOptions.Builder().setThemeOverlay(r2).build());
    }

    @Deprecated
    public static void applyToActivitiesIfAvailable(Application r1, int r2, Precondition r3) {
        applyToActivitiesIfAvailable(r1, new DynamicColorsOptions.Builder().setThemeOverlay(r2).setPrecondition(r3).build());
    }

    @Deprecated
    public static void applyToActivitiesIfAvailable(Application r1, Precondition r2) {
        applyToActivitiesIfAvailable(r1, new DynamicColorsOptions.Builder().setPrecondition(r2).build());
    }

    public static void applyToActivitiesIfAvailable(Application r1, DynamicColorsOptions r2) {
        r1.registerActivityLifecycleCallbacks(new DynamicColorsActivityLifecycleCallbacks(r2));
    }

    public static void applyToActivityIfAvailable(Activity r1) {
        applyToActivityIfAvailable(r1, new DynamicColorsOptions.Builder().build());
    }

    public static void applyToActivityIfAvailable(Activity r5, DynamicColorsOptions r6) {
        if (isDynamicColorAvailable() == true) goto L6;
        return;
    L6:
        if (r6.getContentBasedSeedColor() == null) goto L8;
        int r0 = 0;
    L13:
        if (r6.getPrecondition().shouldApplyDynamicColors(r5, r0) == true) goto L15;
        return;
    L15:
        if (r6.getContentBasedSeedColor() == null) goto L22;
        SchemeContent r02 = new SchemeContent(Hct.fromInt(r6.getContentBasedSeedColor().intValue()), !MaterialColors.isLightTheme(r5), getSystemContrast(r5));
        ColorResourcesOverride r1 = ColorResourcesOverride.getInstance();
        if (r1 != null) goto L20;
        return;
    L20:
        if (r1.applyIfPossible(r5, MaterialColorUtilitiesHelper.createColorResourcesIdsToColorValues(r02)) == false) goto L28;
    L23:
        r6.getOnAppliedCallback().onApplied(r5);
        return;
    L28:
        return;
    L22:
        ThemeUtils.applyThemeOverlay(r5, r0);
        goto L23
    L8:
        if (r6.getThemeOverlay() != 0) goto L10;
        r0 = getDefaultThemeOverlay(r5, DYNAMIC_COLOR_THEME_OVERLAY_ATTRIBUTE);
        goto L13
    L10:
        r0 = r6.getThemeOverlay();
        goto L13
    }

    private static int getDefaultThemeOverlay(Context r0, int[] r1) {
        TypedArray r02 = r0.obtainStyledAttributes(r1);
        int r12 = r02.getResourceId(0, 0);
        r02.recycle();
        return r12;
    }

    private static float getSystemContrast(Context r2) {
        UiModeManager r22 = (UiModeManager) r2.getSystemService("uimode");
        if (r22 != null) goto L5;
        return 0.0f;
    L5:
        if (Build.VERSION.SDK_INT >= 34) goto L8;
        return 0.0f;
    L8:
        return r22.getContrast();
    }

    @SuppressLint({"DefaultLocale"})
    public static boolean isDynamicColorAvailable() {
        if (Build.VERSION.SDK_INT >= 31) goto L6;
        return false;
    L6:
        if (BuildCompat.isAtLeastT() == false) goto L8;
        return true;
    L8:
        Map<String, DeviceSupportCondition> r0 = DYNAMIC_COLOR_SUPPORTED_MANUFACTURERS;
        String r3 = Build.MANUFACTURER;
        Locale r4 = Locale.ROOT;
        DeviceSupportCondition r02 = r0.get(r3.toLowerCase(r4));
        if (r02 != null) goto L11;
        r02 = DYNAMIC_COLOR_SUPPORTED_BRANDS.get(Build.BRAND.toLowerCase(r4));
    L11:
        if (r02 != null) goto L13;
    L15:
        return false;
    L13:
        if (r02.isSupported() == false) goto L15;
        return true;
    }

    public static Context wrapContextIfAvailable(Context r1) {
        return wrapContextIfAvailable(r1, 0);
    }

    public static Context wrapContextIfAvailable(Context r1, int r2) {
        return wrapContextIfAvailable(r1, new DynamicColorsOptions.Builder().setThemeOverlay(r2).build());
    }

    public static Context wrapContextIfAvailable(Context r5, DynamicColorsOptions r6) {
        if (isDynamicColorAvailable() == false) goto L9;
        int r0 = r6.getThemeOverlay();
        if (r0 != 0) goto L8;
        r0 = getDefaultThemeOverlay(r5, DYNAMIC_COLOR_THEME_OVERLAY_ATTRIBUTE);
    L8:
        if (r0 == 0) goto L9;
        if (r6.getContentBasedSeedColor() == null) goto L17;
        SchemeContent r1 = new SchemeContent(Hct.fromInt(r6.getContentBasedSeedColor().intValue()), !MaterialColors.isLightTheme(r5), getSystemContrast(r5));
        ColorResourcesOverride r62 = ColorResourcesOverride.getInstance();
        if (r62 == null) goto L17;
        return r62.wrapContextIfPossible(r5, MaterialColorUtilitiesHelper.createColorResourcesIdsToColorValues(r1));
    L17:
        return new ContextThemeWrapper(r5, r0);
    L9:
        return r5;
    }
}
