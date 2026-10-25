package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

/* loaded from: classes5.dex */
public final class zzbc {
    final String zza;
    final String zzb;
    final String zzc;
    final long zzd;
    final long zze;
    final long zzf;
    final zzbf zzg;

    public zzbc(zzic r2, String r3, String r4, String r5, long r6, long r8, long r10, Bundle r12) {
        Preconditions.checkNotEmpty(r4);
        Preconditions.checkNotEmpty(r5);
        this.zza = r4;
        this.zzb = r5;
        if (true != TextUtils.isEmpty(r3)) goto L5;
        r3 = null;
    L5:
        this.zzc = r3;
        this.zzd = r6;
        this.zze = r8;
        this.zzf = r10;
        if (r10 != 0) goto L8;
    L10:
        if (r12 != null) goto L12;
    L24:
        zzbf r22 = new zzbf(new Bundle());
    L25:
        this.zzg = r22;
        return;
    L12:
        if (r12.isEmpty() == true) goto L24;
        Bundle r32 = new Bundle(r12);
        Iterator<String> r42 = r32.keySet().iterator();
    L15:
        if (r42.hasNext() == false) goto L23;
        String r52 = r42.next();
        if (r52 == null) goto L18;
        Object r62 = r2.zzk().zzG(r52, r32.get(r52));
        if (r62 == null) goto L21;
        r2.zzk().zzO(r32, r52, r62);
        goto L15
    L21:
        r2.zzaW().zze().zzb("Param value can't be null", r2.zzl().zzb(r52));
        r42.remove();
        goto L15
    L18:
        r2.zzaW().zzb().zza("Param name can't be null");
        r42.remove();
        goto L15
    L23:
        r22 = new zzbf(r32);
        goto L25
    L8:
        if (r10 <= r6) goto L10;
        r2.zzaW().zze().zzb("Event created with reverse previous/current timestamps. appId", zzgu.zzl(r4));
        goto L10
    }

    private zzbc(zzic r3, String r4, String r5, String r6, long r7, long r9, long r11, zzbf r13) {
        Preconditions.checkNotEmpty(r5);
        Preconditions.checkNotEmpty(r6);
        Preconditions.checkNotNull(r13);
        this.zza = r5;
        this.zzb = r6;
        if (true != TextUtils.isEmpty(r4)) goto L5;
        r4 = null;
    L5:
        this.zzc = r4;
        this.zzd = r7;
        this.zze = r9;
        this.zzf = r11;
        if (r11 != 0) goto L8;
    L10:
        this.zzg = r13;
        return;
    L8:
        if (r11 <= r7) goto L10;
        r3.zzaW().zze().zzc("Event created with reverse previous/current timestamps. appId, name", zzgu.zzl(r5), zzgu.zzl(r6));
        goto L10
    }

    public final String toString() {
        String r0 = this.zzg.toString();
        String r1 = this.zza;
        int r2 = String.valueOf(r1).length();
        String r5 = this.zzb;
        int r3 = String.valueOf(r5).length();
        int r22 = ((r2 + 22) + r3) + 10;
        StringBuilder r32 = new StringBuilder((r22 + r0.length()) + 1);
        r32.append("Event{appId='");
        r32.append(r1);
        r32.append("', name='");
        r32.append(r5);
        r32.append("', params=");
        r32.append(r0);
        r32.append("}");
        return r32.toString();
    }

    public final zzbc zza(zzic r13, long r14) {
        zzbf r11 = this.zzg;
        return new zzbc(r13, this.zzc, this.zza, this.zzb, this.zzd, this.zze, r14, r11);
    }
}
