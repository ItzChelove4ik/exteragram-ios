package androidx.camera.core.impl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.arch.core.util.Function;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.QuirkSettings;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public class QuirkSettingsLoader implements Function<Context, QuirkSettings> {

    public static class MetadataHolderService extends Service {
    }

    public QuirkSettingsLoader() {
    }

    private static QuirkSettings buildQuirkSettings(Context r4, Bundle r5) {
        boolean r0 = r5.getBoolean("androidx.camera.core.quirks.DEFAULT_QUIRK_ENABLED", true);
        String[] r1 = loadQuirks(r4, r5, "androidx.camera.core.quirks.FORCE_ENABLED");
        String[] r42 = loadQuirks(r4, r5, "androidx.camera.core.quirks.FORCE_DISABLED");
        Logger.d("QuirkSettingsLoader", "Loaded quirk settings from metadata:");
        Logger.d("QuirkSettingsLoader", "  KEY_DEFAULT_QUIRK_ENABLED = " + r0);
        Logger.d("QuirkSettingsLoader", "  KEY_QUIRK_FORCE_ENABLED = " + Arrays.toString(r1));
        Logger.d("QuirkSettingsLoader", "  KEY_QUIRK_FORCE_DISABLED = " + Arrays.toString(r42));
        return new QuirkSettings.Builder().setEnabledWhenDeviceHasQuirk(r0).forceEnableQuirks(resolveQuirkNames(r1)).forceDisableQuirks(resolveQuirkNames(r42)).build();
    }

    private static String[] loadQuirks(Context r3, Bundle r4, String r5) {
        if (r4.containsKey(r5) == false) goto L5;
        int r42 = r4.getInt(r5, -1);
        if (r42 != (-1)) goto L15;
        Logger.w("QuirkSettingsLoader", "Resource ID not found for key: " + r5);
        return new String[0];
    L15:
        return r3.getResources().getStringArray(r42);
    L12:
        e = move-exception;
        Logger.w("QuirkSettingsLoader", "Quirk class names resource not found: " + r42, e);
        return new String[0];
    L5:
        return new String[0];
    }

    private static Class<? extends Quirk> resolveQuirkName(String r4) {
        Class r1 = Class.forName(r4);     // Catch: ClassNotFoundException -> L8
        if (Quirk.class.isAssignableFrom(r1) == false) goto L6;
        return r1;
    L6:
        Logger.w("QuirkSettingsLoader", r4 + " does not implement the Quirk interface.");     // Catch: ClassNotFoundException -> L8
        return null;
    L8:
        e = move-exception;
        Logger.w("QuirkSettingsLoader", "Class not found: " + r4, e);
        return null;
    }

    private static Set<Class<? extends Quirk>> resolveQuirkNames(String[] r4) {
        HashSet r0 = new HashSet();
        int r1 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L8;
        Class<? extends Quirk> r3 = resolveQuirkName(r4[r2]);
        if (r3 == null) goto L7;
        r0.add(r3);
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        return r0;
    }

    /* renamed from: apply, reason: avoid collision after fix types in other method */
    public QuirkSettings apply2(Context r5) {
        PackageManager r0 = r5.getPackageManager();
        Bundle r02 = r0.getServiceInfo(new ComponentName(r5, MetadataHolderService.class), 640).metaData;     // Catch: PackageManager.NameNotFoundException -> L9
        if (r02 != null) goto L8;
        Logger.w("QuirkSettingsLoader", "No metadata in MetadataHolderService.");     // Catch: PackageManager.NameNotFoundException -> L9
        return null;
    L8:
        return buildQuirkSettings(r5, r02);
    L9:
        Logger.d("QuirkSettingsLoader", "QuirkSettings$MetadataHolderService is not found.");
        return null;
    }

    @Override // androidx.arch.core.util.Function
    public /* bridge */ /* synthetic */ QuirkSettings apply(Context r1) {
        return apply2(r1);
    }
}
