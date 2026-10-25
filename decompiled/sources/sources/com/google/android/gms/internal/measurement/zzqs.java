package com.google.android.gms.internal.measurement;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzqs {
    private final boolean zza;
    private final String zzb;
    private final zzacr zzc;
    private final ImmutableMap zzd;
    private final zzqr zze;

    private zzqs(zznd r5, zzqr r6) {
        this.zza = true;
        r5.zzi();
        this.zzb = r5.zzd();
        this.zzc = r5.zzg();
        r5.zze();
        r5.zzf();
        Map r0 = r5.zzh();
        if (r0 == null) goto L5;
        ImmutableSet.copyOf(r0.keySet());
    L6:
        zzmw r02 = r5.zzc();
        ImmutableMap.Builder r1 = ImmutableMap.builderWithExpectedSize(r02.zzf() + 3);
        r02.zzc(r1);
        r1.put("__phenotype_server_token", r5.zze()).put("__phenotype_snapshot_token", r5.zzd()).put("__phenotype_configuration_version", Long.valueOf(r5.zzf()));
        this.zzd = r1.buildKeepingLast();
        this.zze = r6;
        return;
    L5:
        ImmutableSet.of();
        goto L6
    }

    private zzqs(zzqv r8, zzqr r9) {
        this.zza = false;
        zzqv.zzi().equals(r8);
        this.zzb = r8.zza();
        this.zzc = r8.zzb();
        r8.zzc();
        r8.zzd();
        ImmutableSet.of();
        ImmutableMap.Builder r0 = ImmutableMap.builderWithExpectedSize(r8.zzf() + 3);
        Iterator r2 = r8.zze().iterator();
    L4:
        if (r2.hasNext() == false) goto L23;
        zzqx r3 = (zzqx) r2.next();
        int r4 = r3.zzp();
        int r5 = r4 - 1;
        if (r4 == 0) goto L22;
        if (r5 != 0) goto L9;
        r0.put(r3.zza(), Long.valueOf(r3.zzb()));
        goto L4
    L9:
        if (r5 != 1) goto L11;
        r0.put(r3.zza(), Boolean.valueOf(r3.zzc()));
        goto L4
    L11:
        if (r5 != 2) goto L12;
        r0.put(r3.zza(), Double.valueOf(r3.zzd()));
        goto L4
    L12:
        if (r5 != 3) goto L14;
        r0.put(r3.zza(), r3.zze());
        goto L4
    L14:
        if (r5 != 4) goto L4;
        r0.put(r3.zza(), r3.zzf().zzm());
        goto L4
    L22:
        throw null;
    L23:
        r0.put("__phenotype_server_token", r8.zzc());
        r0.put("__phenotype_snapshot_token", r8.zza());
        r0.put("__phenotype_configuration_version", Long.valueOf(r8.zzd()));
        this.zzd = r0.buildKeepingLast();
        this.zze = r9;
    }

    public static zzqs zza(zzqv r1, zzqr r2) {
        return new zzqs(r1, r2);
    }

    public static zzqs zzb(zzqv r1, zzqs r2) {
        return new zzqs(r1, r2.zze);
    }

    public static zzqs zzc(zznd r1, zzqr r2) {
        return new zzqs(r1, r2);
    }

    public final String zzd() {
        return this.zzb;
    }

    public final zzacr zze() {
        return this.zzc;
    }

    public final ImmutableMap zzf() {
        return this.zzd;
    }

    public final boolean zzg() {
        if (this.zze.zzc() != 3) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzh() {
        return this.zza;
    }

    public final zzmd zzi() {
        return this.zze.zza();
    }

    public final boolean zzj() {
        if (this.zze.zzb() != 17) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzk() {
        int r1 = this.zze.zzb() - 2;
        if (r1 != 15) goto L5;
        return true;
    L5:
        if (r1 == 16) goto L10;
        return false;
    L10:
        return true;
    }
}
