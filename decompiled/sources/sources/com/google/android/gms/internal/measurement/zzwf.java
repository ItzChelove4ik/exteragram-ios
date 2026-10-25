package com.google.android.gms.internal.measurement;

import java.util.UUID;

/* loaded from: classes4.dex */
final class zzwf extends zzvt implements zzvs {
    private final Exception zza;
    private final boolean zzb;

    public zzwf(String r2, zzvs r3, zzwl r4, boolean r5, zzwq r6) {
        zzwl r42 = zzwl.zza(r4, zzwk.zzb);
        super("<missing root>:".concat(r2), r3, r42, r6);
        this.zza = r3.zzf();
        this.zzb = r5;
    }

    public zzwf(UUID r7, String r8, String r9, zzwl r10, Exception r11, boolean r12, boolean r13, zzwq r14) {
        zzwl r4 = zzwl.zza(r10, zzwk.zzb);
        super("<missing root>:".concat(r9), r7, r8, r4, r14);
        this.zza = r11;
        this.zzb = false;
    }

    @Override // com.google.android.gms.internal.measurement.zzvs
    public final Exception zzf() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzvs
    public final zzws zzg(String r8, zzwl r9, boolean r10, zzwq r11) {
        if (r10 == true) goto L4;
    L6:
        boolean r0 = true;
        if (r10 == false) goto L13;
        if (this.zzb == true) goto L13;
    L17:
        return new zzwf(r8, this, r9, r0, r11);
    L13:
        if (this.zzb == true) goto L17;
        r0 = false;
        goto L17
    L4:
        if (this.zzb == true) goto L6;
        zzrg r02 = zzvy.zza;
        goto L6
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final zzws zzj(String r1, String r2, String r3, int r4, zzwl r5, zzwq r6) {
        zzrg r22 = zzvy.zza;
        return zzg(r1, r5, true, r6);
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final long zzk() {
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final zzwl zzl() {
        return zzwk.zza;
    }
}
