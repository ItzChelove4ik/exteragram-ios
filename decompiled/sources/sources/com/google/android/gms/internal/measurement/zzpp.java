package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzpp {
    private static final Object zza = null;
    private static volatile Map zzb;
    private final String zzc;

    static {
        zza = new Object();
    }

    public zzpp(Context r3, zzpr r4) {
        if (r4.zzb() == false) goto L5;
        String r32 = zzlg.zzb(r3, r4.zza(), false);
    L6:
        this.zzc = r32;
        r4.zzc();
        r4.zzf();
        r4.zzd();
        r4.zze();
        return;
    L5:
        r32 = r4.zza();
        goto L6
    }

    public static Map zza(Context r12) {
        Map r2 = zzb;
        if (r2 != null) goto L42;
        Object r3 = zza;
        monitor-enter(r3);
        Map r22 = zzb;     // Catch: Throwable -> L21
        if (r22 != null) goto L38;
        ImmutableMap.Builder r23 = ImmutableMap.builder();     // Catch: Throwable -> L21
        String[] r4 = r12.getAssets().list("phenotype");     // Catch: Throwable -> L21 IOException -> L23
        if (r4 == null) goto L37;
        int r5 = r4.length;     // Catch: Throwable -> L21 IOException -> L23
        int r6 = 0;
    L12:
        if (r6 >= r5) goto L37;
        String r7 = r4[r6];     // Catch: Throwable -> L21 IOException -> L23
        if (r7.endsWith("_package_metadata.binarypb") == false) goto L35;
        AssetManager r8 = r12.getAssets();     // Catch: Throwable -> L21 IOException -> L23 zzaeh -> L25
        StringBuilder r10 = new StringBuilder(r7.length() + 10);     // Catch: Throwable -> L21 IOException -> L23 zzaeh -> L25
        r10.append("phenotype/");     // Catch: Throwable -> L21 IOException -> L23 zzaeh -> L25
        r10.append(r7);     // Catch: Throwable -> L21 IOException -> L23 zzaeh -> L25
        InputStream r82 = r8.open(r10.toString());     // Catch: Throwable -> L21 IOException -> L23 zzaeh -> L25
        zzpp r9 = new zzpp(r12, zzpr.zzh(r82, zzadf.zza()));     // Catch: Throwable -> L27
        r23.put(r9.zzc, r9);     // Catch: Throwable -> L27
        if (r82 == null) goto L35;
        r82.close();     // Catch: Throwable -> L21 IOException -> L23 zzaeh -> L25
    L27:
        th = move-exception;
        if (r82 != null) goto L43;
    L33:
        throw th;     // Catch: Throwable -> L21 IOException -> L23 zzaeh -> L25
    L43:
        r82.close();     // Catch: Throwable -> L31
    L31:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L21 IOException -> L23 zzaeh -> L25
    L25:
        e = move-exception;
        StringBuilder r11 = new StringBuilder(r7.length() + 45);     // Catch: Throwable -> L21 IOException -> L23
        r11.append("Unable to read Phenotype PackageMetadata for ");     // Catch: Throwable -> L21 IOException -> L23
        r11.append(r7);     // Catch: Throwable -> L21 IOException -> L23
        Log.e("PackageInfo", r11.toString(), e);     // Catch: Throwable -> L21 IOException -> L23
    L35:
        r6 = r6 + 1;
    L37:
        r22 = r23.buildOrThrow();     // Catch: Throwable -> L21
        zzb = r22;     // Catch: Throwable -> L21
    L23:
        e = move-exception;
        Log.e("PackageInfo", "Unable to read Phenotype PackageMetadata from assets.", e);     // Catch: Throwable -> L21
    L38:
        monitor-exit(r3);     // Catch: Throwable -> L21
        return r22;
    L21:
        th = move-exception;
        throw th;
    L42:
        return r2;
    }
}
