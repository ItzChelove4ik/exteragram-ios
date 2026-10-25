package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public abstract class WorkSourceUtil {
    private static final int zza = 0;
    private static final Method zzb = null;
    private static final Method zzc = null;
    private static final Method zzd = null;
    private static final Method zze = null;
    private static final Method zzf = null;
    private static final Method zzg = null;
    private static final Method zzh = null;
    private static final Method zzi = null;
    private static Boolean zzj;

    static {
        Class r2 = Integer.TYPE;
        zza = Process.myUid();
        Method r5 = WorkSource.class.getMethod("add", new Class[]{r2});     // Catch: Exception -> L5
    L6:
        zzb = r5;
        Method r1 = WorkSource.class.getMethod("add", new Class[]{r2, String.class});     // Catch: Exception -> L9
    L10:
        zzc = r1;
        Method r12 = WorkSource.class.getMethod("size", null);     // Catch: Exception -> L13
    L14:
        zzd = r12;
        Method r13 = WorkSource.class.getMethod("get", new Class[]{r2});     // Catch: Exception -> L17
    L18:
        zze = r13;
        Method r14 = WorkSource.class.getMethod("getName", new Class[]{r2});     // Catch: Exception -> L21
    L22:
        zzf = r14;
        if (PlatformVersion.isAtLeastP() == true) goto L51;
    L28:
        Method r15 = null;
    L29:
        zzg = r15;
        if (PlatformVersion.isAtLeastP() == true) goto L49;
    L35:
        Method r0 = null;
    L36:
        zzh = r0;
        if (PlatformVersion.isAtLeastP() == true) goto L59;
    L42:
        Method r02 = null;
    L43:
        zzi = r02;
        zzj = null;
        return;
    L59:
        r02 = WorkSource.class.getMethod("isEmpty", null);     // Catch: Exception -> L46
        r02.setAccessible(true);     // Catch: Exception -> L45
        goto L43
    L49:
        r0 = Class.forName("android.os.WorkSource$WorkChain").getMethod("addNode", new Class[]{r2, String.class});     // Catch: Exception -> L33
    L33:
        e = move-exception;
        Log.w("WorkSourceUtil", "Missing WorkChain class", e);
        goto L35
    L51:
        r15 = WorkSource.class.getMethod("createWorkChain", null);     // Catch: Exception -> L26
    L26:
        e = move-exception;
        Log.w("WorkSourceUtil", "Missing WorkChain API createWorkChain", e);
    L21:
        r14 = null;
    L17:
        r13 = null;
    L13:
        r12 = null;
    L9:
        r1 = null;
    L5:
        r5 = null;
        goto L6
    }

    public static void add(WorkSource r3, int r4, String r5) {
        Method r0 = zzc;
        if (r0 == null) goto L11;
        if (r5 != null) goto L20;
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        r0.invoke(r3, new Object[]{Integer.valueOf(r4), r5});     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
        return;
    L11:
        Method r52 = zzb;
        if (r52 == null) goto L22;
        r52.invoke(r3, new Object[]{Integer.valueOf(r4)});     // Catch: Exception -> L15
        return;
    L15:
        e = move-exception;
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
        return;
    }

    public static WorkSource fromPackage(Context r3, String r4) {
        if (r3 != null) goto L5;
    L14:
        return null;
    L5:
        if (r3.getPackageManager() == null) goto L14;
        if (r4 == null) goto L14;
        ApplicationInfo r32 = Wrappers.packageManager(r3).getApplicationInfo(r4, 0);     // Catch: PackageManager.NameNotFoundException -> L13
        if (r32 != null) goto L11;
        Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(r4));
        return null;
    L11:
        int r33 = r32.uid;
        WorkSource r0 = new WorkSource();
        add(r0, r33, r4);
        return r0;
    L13:
        Log.e("WorkSourceUtil", "Could not find package: ".concat(r4));
        goto L14
    }

    public static synchronized boolean hasWorkSourcePermission(Context r3) {
        monitor-enter(WorkSourceUtil.class);
        Boolean r1 = zzj;     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        boolean r32 = r1.booleanValue();     // Catch: Throwable -> L9
        monitor-exit(WorkSourceUtil.class);
        return r32;
    L11:
        boolean r12 = false;
        if (r3 != null) goto L16;
        monitor-exit(WorkSourceUtil.class);
        return false;
    L16:
        if (ContextCompat.checkSelfPermission(r3, "android.permission.UPDATE_DEVICE_STATS") != 0) goto L18;
        r12 = true;
    L18:
        zzj = Boolean.valueOf(r12);     // Catch: Throwable -> L9
        monitor-exit(WorkSourceUtil.class);
        return r12;
    L9:
        th = move-exception;
        throw th;
    }

    public static boolean isEmpty(WorkSource r3) {
        Method r0 = zzi;
        if (r0 == null) goto L10;
        Object r02 = r0.invoke(r3, null);     // Catch: Exception -> L7
        Preconditions.checkNotNull(r02);     // Catch: Exception -> L7
        return ((Boolean) r02).booleanValue();
    L7:
        e = move-exception;
        Log.e("WorkSourceUtil", "Unable to check WorkSource emptiness", e);
    L10:
        if (size(r3) != 0) goto L13;
        return true;
    L13:
        return false;
    }

    public static int size(WorkSource r2) {
        Method r0 = zzd;
        if (r0 != null) goto L11;
        return 0;
    L11:
        Object r22 = r0.invoke(r2, null);     // Catch: Exception -> L7
        Preconditions.checkNotNull(r22);     // Catch: Exception -> L7
        return ((Integer) r22).intValue();
    L7:
        e = move-exception;
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
        return 0;
    }
}
