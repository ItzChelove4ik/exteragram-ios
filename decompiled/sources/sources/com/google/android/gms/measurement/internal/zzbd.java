package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes5.dex */
final class zzbd {
    final String zza;
    final String zzb;
    final long zzc;
    final long zzd;
    final long zze;
    final long zzf;
    final long zzg;
    final Long zzh;
    final Long zzi;
    final Long zzj;
    final Boolean zzk;

    public zzbd(String r14, String r15, long r16, long r18, long r20, long r22, long r24, Long r26, Long r27, Long r28, Boolean r29) {
        Preconditions.checkNotEmpty(r14);
        Preconditions.checkNotEmpty(r15);
        boolean r11 = false;
        if (r16 < 0) goto L5;
        boolean r10 = true;
    L6:
        Preconditions.checkArgument(r10);
        if (r18 < 0) goto L9;
        boolean r102 = true;
    L10:
        Preconditions.checkArgument(r102);
        if (r20 < 0) goto L13;
        boolean r103 = true;
    L14:
        Preconditions.checkArgument(r103);
        if (r24 < 0) goto L17;
        r11 = true;
    L17:
        Preconditions.checkArgument(r11);
        this.zza = r14;
        this.zzb = r15;
        this.zzc = r16;
        this.zzd = r18;
        this.zze = r20;
        this.zzf = r22;
        this.zzg = r24;
        this.zzh = r26;
        this.zzi = r27;
        this.zzj = r28;
        this.zzk = r29;
        return;
    L13:
        r103 = false;
        goto L14
    L9:
        r102 = false;
        goto L10
    L5:
        r10 = false;
        goto L6
    }

    public final zzbd zza(long r18) {
        long r11 = this.zzg;
        Long r13 = this.zzh;
        Long r14 = this.zzi;
        Long r15 = this.zzj;
        Boolean r1 = this.zzk;
        return new zzbd(this.zza, this.zzb, this.zzc, this.zzd, this.zze, r18, r11, r13, r14, r15, r1);
    }

    public final zzbd zzb(long r18, long r20) {
        Long r13 = Long.valueOf(r20);
        Long r14 = this.zzi;
        Long r15 = this.zzj;
        Boolean r2 = this.zzk;
        return new zzbd(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, r18, r13, r14, r15, r2);
    }

    public final zzbd zzc(Long r18, Long r19, Boolean r20) {
        return new zzbd(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, r18, r19, r20);
    }
}
