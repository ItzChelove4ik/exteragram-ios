package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzabg extends zzabh {
    private final zzabf zza;

    private zzabg(zzza r1, int r2, zzabf r3) {
        super(r1, r2);
        this.zza = r3;
        StringBuilder r0 = new StringBuilder("%");
        r1.zzl(r0);
        if (true == r1.zzk()) goto L5;
        char r12 = 't';
    L6:
        r0.append(r12);
        r0.append(r3.zzb());
        return;
    L5:
        r12 = 'T';
        goto L6
    }

    public static zzabh zza(zzabf r1, zzza r2, int r3) {
        return new zzabg(r2, r3, r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzabh
    public final void zzb(zzabi r2, Object r3) {
        r2.zzd(r3, this.zza, zzd());
    }
}
