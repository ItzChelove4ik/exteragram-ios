package com.google.firebase.crashlytics.internal.common;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.text.TextUtils;
import com.google.firebase.crashlytics.internal.Logger;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public abstract class CommonUtils {
    private static final char[] HEX_VALUES = null;

    public enum Architecture extends Enum<Architecture> {
        private static final /* synthetic */ Architecture[] $VALUES = null;
        public static final Architecture ARM64 = null;
        public static final Architecture ARMV6 = null;
        public static final Architecture ARMV7 = null;
        public static final Architecture ARMV7S = null;
        public static final Architecture ARM_UNKNOWN = null;
        public static final Architecture PPC = null;
        public static final Architecture PPC64 = null;
        public static final Architecture UNKNOWN = null;
        public static final Architecture X86_32 = null;
        public static final Architecture X86_64 = null;
        private static final Map<String, Architecture> matcher = null;

        private static /* synthetic */ Architecture[] $values() {
            return new Architecture[]{X86_32, X86_64, ARM_UNKNOWN, PPC, PPC64, ARMV6, ARMV7, UNKNOWN, ARMV7S, ARM64};
        }

        static {
            Architecture r0 = new Architecture("X86_32", 0);
            X86_32 = r0;
            X86_64 = new Architecture("X86_64", 1);
            ARM_UNKNOWN = new Architecture("ARM_UNKNOWN", 2);
            PPC = new Architecture("PPC", 3);
            PPC64 = new Architecture("PPC64", 4);
            Architecture r1 = new Architecture("ARMV6", 5);
            ARMV6 = r1;
            Architecture r2 = new Architecture("ARMV7", 6);
            ARMV7 = r2;
            UNKNOWN = new Architecture("UNKNOWN", 7);
            ARMV7S = new Architecture("ARMV7S", 8);
            Architecture r4 = new Architecture("ARM64", 9);
            ARM64 = r4;
            $VALUES = $values();
            HashMap r5 = new HashMap(4);
            matcher = r5;
            r5.put("armeabi-v7a", r2);
            r5.put("armeabi", r1);
            r5.put("arm64-v8a", r4);
            r5.put("x86", r0);
        }

        Architecture(String r1, int r2) {
        }

        public static Architecture getValue() {
            String r0 = Build.CPU_ABI;
            if (TextUtils.isEmpty(r0) == false) goto L6;
            Logger.getLogger().v("Architecture#getValue()::Build.CPU_ABI returned null or empty");
            return UNKNOWN;
        L6:
            String r02 = r0.toLowerCase(Locale.US);
            Architecture r03 = matcher.get(r02);
            if (r03 == null) goto L9;
            return r03;
        L9:
            return UNKNOWN;
        }

        public static Architecture valueOf(String r1) {
            return (Architecture) Enum.valueOf(Architecture.class, r1);
        }

        public static Architecture[] values() {
            return (Architecture[]) $VALUES.clone();
        }
    }

    static {
        HEX_VALUES = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    public static long calculateFreeRamInBytes(Context r2) {
        ActivityManager.MemoryInfo r0 = new ActivityManager.MemoryInfo();
        ((ActivityManager) r2.getSystemService("activity")).getMemoryInfo(r0);
        return r0.availMem;
    }

    public static synchronized long calculateTotalRamInBytes(Context r3) {
        monitor-enter(CommonUtils.class);
        ActivityManager.MemoryInfo r1 = new ActivityManager.MemoryInfo();     // Catch: Throwable -> L7
        ((ActivityManager) r3.getSystemService("activity")).getMemoryInfo(r1);     // Catch: Throwable -> L7
        long r12 = r1.totalMem;     // Catch: Throwable -> L7
        monitor-exit(CommonUtils.class);
        return r12;
    L7:
        th = move-exception;
        throw th;
    }

    public static long calculateUsedDiskSpaceInBytes(String r7) {
        long r1 = new StatFs(r7).getBlockSize();
        return (r0.getBlockCount() * r1) - (r1 * r0.getAvailableBlocks());
    }

    @SuppressLint({"MissingPermission"})
    public static boolean canTryConnection(Context r2) {
        if (checkPermission(r2, "android.permission.ACCESS_NETWORK_STATE") == false) goto L11;
        NetworkInfo r22 = ((ConnectivityManager) r2.getSystemService("connectivity")).getActiveNetworkInfo();
        if (r22 != null) goto L7;
        return false;
    L7:
        if (r22.isConnectedOrConnecting() == false) goto L12;
        return true;
    L12:
        return false;
    L11:
        return true;
    }

    public static boolean checkPermission(Context r0, String r1) {
        if (r0.checkCallingOrSelfPermission(r1) != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static void closeOrLog(Closeable r1, String r2) {
        if (r1 == null) goto L10;
        r1.close();     // Catch: IOException -> L5
        return;
    L5:
        e = move-exception;
        Logger.getLogger().e(r2, e);
        return;
    }

    public static void closeQuietly(Closeable r0) {
        if (r0 != null) goto L9;
        return;
    L9:
        r0.close();     // Catch: Exception -> L8 RuntimeException -> L5
        return;
    L5:
        e = move-exception;
        throw e;
    }

    public static String createInstanceIdFrom(String... r8) {
        if (r8 != null) goto L5;
    L20:
        return null;
    L5:
        if (r8.length == 0) goto L20;
        ArrayList r1 = new ArrayList();
        int r2 = r8.length;
        int r3 = 0;
        int r4 = 0;
    L8:
        if (r4 >= r2) goto L13;
        String r5 = r8[r4];
        if (r5 == null) goto L12;
        r1.add(r5.replace("-", _UrlKt.FRAGMENT_ENCODE_SET).toLowerCase(Locale.US));
    L12:
        r4 = r4 + 1;
        goto L8
    L13:
        Collections.sort(r1);
        StringBuilder r82 = new StringBuilder();
        int r22 = r1.size();
    L14:
        if (r3 >= r22) goto L16;
        Object r42 = r1.get(r3);
        r3 = r3 + 1;
        r82.append((String) r42);
        goto L14
    L16:
        String r83 = r82.toString();
        if (r83.length() <= 0) goto L20;
        return sha1(r83);
    }

    public static boolean getBooleanResourceValue(Context r2, String r3, boolean r4) {
        if (r2 == null) goto L13;
        Resources r0 = r2.getResources();
        if (r0 == null) goto L13;
        int r1 = getResourcesIdentifier(r2, r3, "bool");
        if (r1 > 0) goto L8;
        int r32 = getResourcesIdentifier(r2, r3, "string");
        if (r32 <= 0) goto L13;
        return Boolean.parseBoolean(r2.getString(r32));
    L8:
        return r0.getBoolean(r1);
    L13:
        return r4;
    }

    public static List<BuildIdInfo> getBuildIdInfo(Context r8) {
        ArrayList r0 = new ArrayList();
        int r1 = getResourcesIdentifier(r8, "com.google.firebase.crashlytics.build_ids_lib", "array");
        int r3 = getResourcesIdentifier(r8, "com.google.firebase.crashlytics.build_ids_arch", "array");
        int r2 = getResourcesIdentifier(r8, "com.google.firebase.crashlytics.build_ids_build_id", "array");
        if (r1 == 0) goto L19;
        if (r3 == 0) goto L19;
        if (r2 == 0) goto L19;
        String[] r12 = r8.getResources().getStringArray(r1);
        String[] r32 = r8.getResources().getStringArray(r3);
        String[] r82 = r8.getResources().getStringArray(r2);
        if (r12.length == r82.length) goto L10;
    L17:
        Logger.getLogger().d(String.format("Lengths did not match: %d %d %d", new Object[]{Integer.valueOf(r12.length), Integer.valueOf(r32.length), Integer.valueOf(r82.length)}));
        return r0;
    L10:
        if (r32.length != r82.length) goto L17;
        int r22 = 0;
    L14:
        if (r22 >= r82.length) goto L16;
        r0.add(new BuildIdInfo(r12[r22], r32[r22], r82[r22]));
        r22 = r22 + 1;
        goto L14
    L16:
        return r0;
    L19:
        Logger.getLogger().d(String.format("Could not find resources: %d %d %d", new Object[]{Integer.valueOf(r1), Integer.valueOf(r3), Integer.valueOf(r2)}));
        return r0;
    }

    public static int getCpuArchitectureInt() {
        return Architecture.getValue().ordinal();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    public static int getDeviceState() {
        boolean r0 = isEmulator();
        ?? r02 = r0;
        if (isRooted() == false) goto L6;
        r02 = (r0 ? 1 : 0) | 2;
    L6:
        if (isDebuggerAttached() == true) goto L8;
        return r02;
    L8:
        return r02 | 4;
    }

    public static String getMappingFileId(Context r2) {
        int r0 = getResourcesIdentifier(r2, "com.google.firebase.crashlytics.mapping_file_id", "string");
        if (r0 != 0) goto L5;
        r0 = getResourcesIdentifier(r2, "com.crashlytics.android.build_id", "string");
    L5:
        if (r0 != 0) goto L7;
        return null;
    L7:
        return r2.getResources().getString(r0);
    }

    public static boolean getProximitySensorEnabled(Context r2) {
        if (isEmulator() == false) goto L6;
        return false;
    L6:
        if (((SensorManager) r2.getSystemService("sensor")).getDefaultSensor(8) == null) goto L9;
        return true;
    L9:
        return false;
    }

    public static String getResourcePackageName(Context r2) {
        int r0 = r2.getApplicationContext().getApplicationInfo().icon;
        if (r0 <= 0) goto L12;
        String r02 = r2.getResources().getResourcePackageName(r0);     // Catch: Resources.NotFoundException -> L9
        if ("android".equals(r02) == false) goto L8;
        return r2.getPackageName();
    L8:
        return r02;
    L10:
        return r2.getPackageName();
    L12:
        return r2.getPackageName();
    }

    public static int getResourcesIdentifier(Context r1, String r2, String r3) {
        return r1.getResources().getIdentifier(r2, r3, getResourcePackageName(r1));
    }

    public static SharedPreferences getSharedPrefs(Context r2) {
        return r2.getSharedPreferences("com.google.firebase.crashlytics", 0);
    }

    public static String getVersionControlInfo(Context r2) {
        int r0 = getResourcesIdentifier(r2, "com.google.firebase.crashlytics.version_control_info", "string");
        if (r0 != 0) goto L7;
        return null;
    L7:
        return r2.getResources().getString(r0);
    }

    private static String hash(String r0, String r1) {
        return hash(r0.getBytes(), r1);
    }

    private static String hash(byte[] r3, String r4) {
        MessageDigest r42 = MessageDigest.getInstance(r4);     // Catch: NoSuchAlgorithmException -> L5
        r42.update(r3);
        return hexify(r42.digest());
    L5:
        e = move-exception;
        Logger.getLogger().e("Could not create hashing algorithm: " + r4 + ", returning empty string.", e);
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static String hexify(byte[] r6) {
        char[] r0 = new char[r6.length * 2];
        int r1 = 0;
    L4:
        if (r1 >= r6.length) goto L7;
        byte r2 = r6[r1];
        int r3 = r2 & UByte.MAX_VALUE;
        int r4 = r1 * 2;
        char[] r5 = HEX_VALUES;
        r0[r4] = r5[r3 >>> 4];
        r0[r4 + 1] = r5[r2 & 15];
        r1 = r1 + 1;
        goto L4
    L7:
        return new String(r0);
    }

    public static boolean isAppDebuggable(Context r0) {
        if ((r0.getApplicationInfo().flags & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isDebuggerAttached() {
        if (Debug.isDebuggerConnected() == false) goto L5;
        return true;
    L5:
        if (Debug.waitingForDebugger() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isEmulator() {
        if (Build.PRODUCT.contains("sdk") == true) goto L11;
        String r0 = Build.HARDWARE;
        if (r0.contains("goldfish") == false) goto L7;
        return true;
    L7:
        if (r0.contains("ranchu") == true) goto L14;
        return false;
    L14:
        return true;
    L11:
        return true;
    }

    public static boolean isRooted() {
        boolean r0 = isEmulator();
        String r1 = Build.TAGS;
        if (r0 == true) goto L9;
        if (r1 == null) goto L9;
        if (r1.contains("test-keys") == false) goto L9;
        return true;
    L9:
        if (new File("/system/app/Superuser.apk").exists() == false) goto L11;
        return true;
    L11:
        File r12 = new File("/system/xbin/su");
        if (r0 == false) goto L14;
        return false;
    L14:
        if (r12.exists() == false) goto L18;
        return true;
    L18:
        return false;
    }

    public static boolean nullSafeEquals(String r0, String r1) {
        if (r0 != null) goto L9;
        if (r1 != null) goto L6;
        return true;
    L6:
        return false;
    L9:
        return r0.equals(r1);
    }

    public static String sha1(String r1) {
        return hash(r1, "SHA-1");
    }

    public static String streamToString(InputStream r1) {
        Scanner r12 = new Scanner(r1).useDelimiter("\\A");
    L6:
        th = move-exception;
        if (r12 != null) goto L17;
    L16:
        throw th;
    L17:
        r12.close();     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        th.addSuppressed(th);
        goto L16
    L4:
        if (r12.hasNext() == false) goto L8;
        String r0 = r12.next();     // Catch: Throwable -> L6
    L9:
        r12.close();
        return r0;
    L8:
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    }
}
