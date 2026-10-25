package com.exteragram.messenger.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Point;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.Keep;
import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.Calendar;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildConfig;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes.dex */
public class AppUtils {
    private static Gson gson;

    /* renamed from: com.exteragram.messenger.utils.AppUtils$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass1 implements ExclusionStrategy {
        public AnonymousClass1() {
        }

        @Override // com.google.gson.ExclusionStrategy
        public boolean shouldSkipClass(Class<?> r2) {
            if (r2.getPackage() != null) goto L5;
            return false;
        L5:
            String r1 = r2.getPackage().getName();
            if (r1.startsWith("android.") == false) goto L8;
            return true;
        L8:
            if (r1.startsWith("androidx.") == true) goto L13;
            return false;
        L13:
            return true;
        }

        @Override // com.google.gson.ExclusionStrategy
        public boolean shouldSkipField(FieldAttributes r2) {
            if (r2.getDeclaringClass().getPackage() != null) goto L5;
            return false;
        L5:
            String r1 = r2.getDeclaringClass().getPackage().getName();
            if (r1.startsWith("android.") == false) goto L8;
            return true;
        L8:
            if (r1.startsWith("androidx.") == true) goto L13;
            return false;
        L13:
            return true;
        }
    }

    public static int compareVersionValues(String r5, String r6) {
        String[] r52 = r5.split("\\.");
        String[] r62 = r6.split("\\.");
        int r0 = Math.max(r52.length, r62.length);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L16;
        if (r2 >= r52.length) goto L7;
        int r3 = Integer.parseInt(r52[r2]);
    L9:
        if (r2 >= r62.length) goto L11;
        int r4 = Integer.parseInt(r62[r2]);
    L12:
        if (r3 != r4) goto L14;
        r2 = r2 + 1;
        goto L3
    L14:
        return Integer.compare(r3, r4);
    L11:
        r4 = 0;
        goto L12
    L7:
        r3 = 0;
        goto L9
    L16:
        return 0;
    }

    public static boolean compareVersions(String r3, int r4, int r5) {
        int r42 = Integer.compare(r4, r5);
        r3.getClass();
        char r2 = 65535;
        switch(r3.hashCode()) {
            case 60: goto L22;
            case 62: goto L18;
            case 1921: goto L14;
            case 1952: goto L10;
            case 1983: goto L6;
            default: goto L25;
        };
    L25:
        switch(r2) {
            case 0: goto L40;
            case 1: goto L37;
            case 2: goto L34;
            case 3: goto L31;
            case 4: goto L28;
            default: goto L26;
        };
    L26:
        f$$ExternalSyntheticBUOutline1.m("Unsupported operator: ".concat(r3));
        return false;
    L28:
        if (r42 < 0) goto L30;
        return true;
    L30:
        return false;
    L31:
        if (r42 != 0) goto L33;
        return true;
    L33:
        return false;
    L34:
        if (r42 > 0) goto L36;
        return true;
    L36:
        return false;
    L37:
        if (r42 <= 0) goto L39;
        return true;
    L39:
        return false;
    L40:
        if (r42 >= 0) goto L42;
        return true;
    L42:
        return false;
    L6:
        if (r3.equals(">=") == false) goto L25;
        r2 = 4;
        goto L25
    L10:
        if (r3.equals("==") == false) goto L25;
        r2 = 3;
        goto L25
    L14:
        if (r3.equals("<=") == false) goto L25;
        r2 = 2;
        goto L25
    L18:
        if (r3.equals(">") == false) goto L25;
        r2 = 1;
        goto L25
    L22:
        if (r3.equals("<") == false) goto L25;
        r2 = 0;
        goto L25
    }

    public static boolean compareVersions(String r3, String r4, String r5) {
        int r42 = compareVersionValues(r4, r5);
        r3.getClass();
        char r2 = 65535;
        switch(r3.hashCode()) {
            case 60: goto L22;
            case 62: goto L18;
            case 1921: goto L14;
            case 1952: goto L10;
            case 1983: goto L6;
            default: goto L25;
        };
    L25:
        switch(r2) {
            case 0: goto L40;
            case 1: goto L37;
            case 2: goto L34;
            case 3: goto L31;
            case 4: goto L28;
            default: goto L26;
        };
    L26:
        f$$ExternalSyntheticBUOutline1.m("Unsupported operator: ".concat(r3));
        return false;
    L28:
        if (r42 < 0) goto L30;
        return true;
    L30:
        return false;
    L31:
        if (r42 != 0) goto L33;
        return true;
    L33:
        return false;
    L34:
        if (r42 > 0) goto L36;
        return true;
    L36:
        return false;
    L37:
        if (r42 <= 0) goto L39;
        return true;
    L39:
        return false;
    L40:
        if (r42 >= 0) goto L42;
        return true;
    L42:
        return false;
    L6:
        if (r3.equals(">=") == false) goto L25;
        r2 = 4;
        goto L25
    L10:
        if (r3.equals("==") == false) goto L25;
        r2 = 3;
        goto L25
    L14:
        if (r3.equals("<=") == false) goto L25;
        r2 = 2;
        goto L25
    L18:
        if (r3.equals(">") == false) goto L25;
        r2 = 1;
        goto L25
    L22:
        if (r3.equals("<") == false) goto L25;
        r2 = 0;
        goto L25
    }

    public static Gson getGson() {
        if (gson != null) goto L6;
        gson = new GsonBuilder().setPrettyPrinting().serializeSpecialFloatingPointValues().addSerializationExclusionStrategy(new AnonymousClass1()).create();
    L6:
        return gson;
    }

    public static int getNotificationColor() {
        if (Theme.getActiveTheme().hasAccentColors() == false) goto L5;
        int r0 = Theme.getActiveTheme().getAccentColor(Theme.getActiveTheme().currentAccentId);
    L7:
        if (r0 != 0) goto L9;
        r0 = Theme.getColor(Theme.key_actionBarDefault) | (-16777216);
    L9:
        float r2 = AndroidUtilities.computePerceivedBrightness(r0);
        if (r2 >= 0.721f) goto L16;
        if (r2 <= 0.279f) goto L16;
        return r0;
    L16:
        return Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader) | (-16777216);
    L5:
        r0 = 0;
        goto L7
    }

    @Keep
    public static Object getPrivateField(Object r2, String r3) {
        Class<?> r1 = r2.getClass();     // Catch: Exception -> L7
    L4:
        if (r1 == null) goto L10;
        Field r32 = r1.getDeclaredField(r3);     // Catch: Exception -> L7 NoSuchFieldException -> L9
    L11:
        if (r32 == null) goto L15;
        r32.setAccessible(true);     // Catch: Exception -> L7
        return r32.get(r2);
    L15:
        return null;
    L9:
        r1 = r1.getSuperclass();     // Catch: Exception -> L7
        goto L4
    L10:
        r32 = null;
    L7:
        e = move-exception;
        logInternal(r2.getClass().getName(), e, 6);
        goto L15
    }

    @Keep
    public static Object getPrivateStaticField(Class<?> r2, String r3) {
        Class<?> r0 = r2;
    L4:
        if (r0 == null) goto L10;
        Field r32 = r0.getDeclaredField(r3);     // Catch: Exception -> L7 NoSuchFieldException -> L9
    L11:
        if (r32 == null) goto L15;
        r32.setAccessible(true);     // Catch: Exception -> L7
        return r32.get(null);
    L15:
        return null;
    L9:
        r0 = r0.getSuperclass();     // Catch: Exception -> L7
    L7:
        e = move-exception;
        logInternal(r2.getName(), e, 6);
        goto L15
    L10:
        r32 = null;
        goto L11
    }

    public static int getSwipeVelocity() {
        Point r0 = AndroidUtilities.displaySize;
        if (r0.x <= r0.y) goto L6;
        return 1250;
    L6:
        return 850;
    }

    public static String getVersionText() {
        StringBuilder r0 = new StringBuilder();
        r0.append(LocaleUtils.getAppName());
        r0.append(" ");
        if (BuildVars.IS_LITE_VERSION == false) goto L5;
        r0.append("Lite ");
    L5:
        r0.append(BuildVars.BUILD_VERSION_STRING);
        PackageInfo r1 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);     // Catch: PackageManager.NameNotFoundException -> L8
        r0.append(" (");     // Catch: PackageManager.NameNotFoundException -> L8
        r0.append(r1.versionCode);     // Catch: PackageManager.NameNotFoundException -> L8
        r0.append(")");     // Catch: PackageManager.NameNotFoundException -> L8
    L11:
        if (isAppModified() == false) goto L14;
        r0.append("\nbased on @exteraGram");
    L14:
        return r0.toString();
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L11
    }

    public static boolean isAppModified() {
        PackageInfo r1 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 64);     // Catch: Exception -> L9
        String r2 = r1.packageName;     // Catch: Exception -> L9
        Signature r12 = r1.signatures[0];     // Catch: Exception -> L9
        String r13 = Base64.encodeToString(MessageDigest.getInstance("MD5").digest(r12.toByteArray()), 0).trim();     // Catch: Exception -> L9
        if (BuildConfig.APPLICATION_ID.equals(r2) == false) goto L11;
        if ("VdBS+IkXbbu+mQuHS4vyXw==".equals(r13) == false) goto L11;
        return false;
    L11:
        return true;
    L9:
        e = move-exception;
        FileLog.e(e);
        return true;
    }

    public static boolean isWinter() {
        int r0 = Calendar.getInstance().get(2);
        if (r0 == 11) goto L9;
        if (r0 == 0) goto L9;
        if (r0 == 1) goto L9;
        return false;
    L9:
        return true;
    }

    @Keep
    public static void log(String r2) {
        logInternal(r2, null, 5);
    }

    @Keep
    public static void log(String r1, Throwable r2) {
        logInternal(r1, r2, 5);
    }

    @Keep
    public static void log(Throwable r2) {
        logInternal(_UrlKt.FRAGMENT_ENCODE_SET, r2, 5);
    }

    private static void logInternal(String r3, Throwable r4, int r5) {
        StackTraceElement r52 = Thread.currentThread().getStackTrace()[Math.max(3, Math.min(r5, r0.length - 1))];
        String r0 = r52.getClassName();
        if (r0.contains(".") == false) goto L6;
        r0 = r0.substring(r0.lastIndexOf(46) + 1);
    L6:
        if (r0.contains("$") == false) goto L8;
        r0 = r0.substring(r0.lastIndexOf(36) + 1);
    L8:
        String r02 = "[" + r0 + "]";
        String r32 = String.format("[%s] %s", new Object[]{r52.getMethodName(), r3});
        if (r4 == null) goto L12;
        Log.e(r02, r32, r4);
        return;
    L12:
        Log.d(r02, r32);
    }

    @Keep
    public static void printObjectDetails(Object r3) {
        if (r3 != null) goto L10;
        return;
    L10:
        logInternal(r3.getClass().getName() + ": " + getGson().toJson(r3), null, 6);     // Catch: Exception -> L7
        return;
    L7:
        e = move-exception;
        logInternal(r3.getClass().getName(), e, 6);
    }

    @Keep
    public static void setPrivateField(Object r1, String r2, Object r3) {
        Class<?> r0 = r1.getClass();     // Catch: Exception -> L6
    L3:
        if (r0 == null) goto L9;
        Field r22 = r0.getDeclaredField(r2);     // Catch: Exception -> L6 NoSuchFieldException -> L8
    L10:
        if (r22 == null) goto L21;
        r22.setAccessible(true);     // Catch: Exception -> L6
        r22.set(r1, r3);     // Catch: Exception -> L6
        return;
    L21:
        return;
    L8:
        r0 = r0.getSuperclass();     // Catch: Exception -> L6
        goto L3
    L9:
        r22 = null;
    L6:
        e = move-exception;
        logInternal(r1.getClass().getName(), e, 6);
    }

    @Keep
    public static void setPrivateStaticField(Class<?> r2, String r3, Object r4) {
        Class<?> r0 = r2;
    L4:
        if (r0 == null) goto L10;
        Field r32 = r0.getDeclaredField(r3);     // Catch: Exception -> L7 NoSuchFieldException -> L9
    L11:
        if (r32 == null) goto L21;
        r32.setAccessible(true);     // Catch: Exception -> L7
        r32.set(null, r4);     // Catch: Exception -> L7
        return;
    L21:
        return;
    L9:
        r0 = r0.getSuperclass();     // Catch: Exception -> L7
    L7:
        e = move-exception;
        logInternal(r2.getName(), e, 6);
        return;
    L10:
        r32 = null;
        goto L11
    }

    public static String stackTraceToString(Throwable r2) {
        StringWriter r0 = new StringWriter();
        r2.printStackTrace(new PrintWriter(r0));
        return r0.toString();
    }
}
