package com.google.android.recaptcha.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;

/* loaded from: classes5.dex */
public final class zzhh {
    private final Context zza;

    public zzhh(Context r1) {
        this.zza = r1;
        new ConcurrentHashMap();
        zzc();
    }

    private static final Map zzc() {
        Map r0 = MapsKt.mutableMapOf(new Pair[]{TuplesKt.to(0, zzaiv.zzb), TuplesKt.to(1, zzaiv.zzc), TuplesKt.to(2, zzaiv.zzd), TuplesKt.to(3, zzaiv.zze), TuplesKt.to(4, zzaiv.zzf), TuplesKt.to(5, zzaiv.zzg), TuplesKt.to(6, zzaiv.zzh), TuplesKt.to(7, zzaiv.zzi), TuplesKt.to(8, zzaiv.zzj), TuplesKt.to(9, zzaiv.zzk), TuplesKt.to(10, zzaiv.zzl), TuplesKt.to(11, zzaiv.zzm), TuplesKt.to(12, zzaiv.zzn), TuplesKt.to(13, zzaiv.zzo), TuplesKt.to(14, zzaiv.zzp), TuplesKt.to(15, zzaiv.zzq)});
        r0.put(17, zzaiv.zzs);
        r0.put(16, zzaiv.zzr);
        int r1 = Build.VERSION.SDK_INT;
        if (r1 < 28) goto L6;
        r0.put(18, zzaiv.zzt);
        r0.put(19, zzaiv.zzu);
        r0.put(20, zzaiv.zzv);
        r0.put(21, zzaiv.zzw);
    L6:
        if (r1 < 29) goto L9;
        r0.put(23, zzaiv.zzy);
    L9:
        if (r1 < 30) goto L12;
        r0.put(25, zzaiv.zzA);
    L12:
        if (r1 < 31) goto L15;
        r0.put(32, zzaiv.zzH);
        r0.put(29, zzaiv.zzE);
    L15:
        if (r1 < 33) goto L17;
        r0.put(35, zzaiv.zzK);
        r0.put(34, zzaiv.zzJ);
        r0.put(33, zzaiv.zzI);
    L17:
        return r0;
    }

    public final zzhg zza(Exception r8, zzhg r9) {
        ConnectivityManager r7 = (ConnectivityManager) this.zza.getSystemService("connectivity");     // Catch: Exception -> L12
        Network r0 = r7.getActiveNetwork();     // Catch: Exception -> L12
        if (r0 == null) goto L11;
        NetworkCapabilities r72 = r7.getNetworkCapabilities(r0);     // Catch: Exception -> L12
        if (r72 == null) goto L11;
        if (r72.hasCapability(16) == false) goto L11;
        return r9;
    L11:
        return new zzhg(zzhf.zzc, zzhe.zzy, r8.getMessage(), null, 8, null);
    }

    public final Set zzb() {
        LinkedHashSet r0 = new LinkedHashSet();     // Catch: Exception -> L24
        ConnectivityManager r3 = (ConnectivityManager) this.zza.getSystemService("connectivity");     // Catch: Exception -> L24
        NetworkCapabilities r32 = r3.getNetworkCapabilities(r3.getActiveNetwork());     // Catch: Exception -> L24
        if (r32 != null) goto L5;
    L7:
        if (r32 != null) goto L9;
    L11:
        if (r32 != null) goto L13;
    L15:
        if (r32 != null) goto L17;
    L19:
        if (r32 != null) goto L21;
    L23:
        return r0;
    L21:
        if (r32.hasCapability(16) != true) goto L23;
        r0.add(zzaiv.zzr);     // Catch: Exception -> L24
    L17:
        if (r32.hasTransport(3) != true) goto L19;
        r0.add(zzaiv.zzP);     // Catch: Exception -> L24
        goto L19
    L13:
        if (r32.hasTransport(4) != true) goto L15;
        r0.add(zzaiv.zzO);     // Catch: Exception -> L24
        goto L15
    L9:
        if (r32.hasTransport(0) != true) goto L11;
        r0.add(zzaiv.zzN);     // Catch: Exception -> L24
        goto L11
    L5:
        if (r32.hasTransport(1) != true) goto L7;
        r0.add(zzaiv.zzM);     // Catch: Exception -> L24
    L25:
        return SetsKt.emptySet();
    }
}
