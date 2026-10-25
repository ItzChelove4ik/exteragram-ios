package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.Iterator;
import java.util.Map;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzpj {
    private final long zza;
    private final com.google.android.gms.internal.measurement.zzib zzb;
    private final String zzc;
    private final Map zzd;
    private final zzls zze;
    private final long zzf;
    private final long zzg;
    private final long zzh;
    private final int zzi;

    public /* synthetic */ zzpj(long r1, com.google.android.gms.internal.measurement.zzib r3, String r4, Map r5, zzls r6, long r7, long r9, long r11, int r13, byte[] r14) {
        this.zza = r1;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r9;
        this.zzh = r11;
        this.zzi = r13;
    }

    public final zzot zza() {
        return new zzot(this.zzc, this.zzd, this.zze, null);
    }

    public final zzom zzb() {
        Bundle r5 = new Bundle();
        Iterator r0 = this.zzd.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        Map.Entry r1 = (Map.Entry) r0.next();
        r5.putString((String) r1.getKey(), (String) r1.getValue());
        goto L4
    L6:
        long r12 = this.zza;
        com.google.android.gms.internal.measurement.zzib r02 = this.zzb;
        String r4 = this.zzc;
        zzls r3 = this.zze;
        long r7 = this.zzg;
        return new zzom(r12, r02.zzcd(), r4, r5, r3.zza(), r7, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public final long zzc() {
        return this.zza;
    }

    public final com.google.android.gms.internal.measurement.zzib zzd() {
        return this.zzb;
    }

    public final String zze() {
        return this.zzc;
    }

    public final zzls zzf() {
        return this.zze;
    }

    public final long zzg() {
        return this.zzf;
    }

    public final long zzh() {
        return this.zzh;
    }

    public final int zzi() {
        return this.zzi;
    }
}
