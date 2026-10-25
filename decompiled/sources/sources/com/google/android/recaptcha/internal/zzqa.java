package com.google.android.recaptcha.internal;

import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzqa extends zzpo {
    Object[] zzd;
    private int zze;

    public zzqa(int r1, boolean r2) {
        super(r1);
        this.zzd = new Object[zzqb.zzg(r1)];
    }

    @Override // com.google.android.recaptcha.internal.zzpp
    public final /* bridge */ /* synthetic */ zzpp zzb(Object r6) {
        r6.getClass();
        if (this.zzd == null) goto L15;
        int r0 = zzqb.zzg(this.zzb);
        Object[] r1 = this.zzd;
        if (r0 > r1.length) goto L15;
        int r02 = r1.length - 1;
        int r12 = r6.hashCode();
        int r2 = zzpn.zza(r12);
    L7:
        int r22 = r2 & r02;
        Object[] r3 = this.zzd;
        Object r4 = r3[r22];
        if (r4 == null) goto L9;
        if (r4.equals(r6) == true) goto L14;
        r2 = r22 + 1;
        goto L7
    L14:
        return this;
    L9:
        r3[r22] = r6;
        this.zze += r12;
        super.zza(r6);
        return this;
    L15:
        this.zzd = null;
        super.zza(r6);
        return this;
    }

    public final zzqb zzd() {
        int r0 = this.zzb;
        if (r0 == 0) goto L20;
        if (r0 != 1) goto L7;
        Object r9 = this.zza[0];
        Objects.requireNonNull(r9);
        return new zzqt(r9);
    L7:
        if (this.zzd != null) goto L9;
    L14:
        zzqb r3 = zzqb.zzl(this.zzb, this.zza);
        this.zzb = r3.size();
    L15:
        this.zzc = true;
        this.zzd = null;
        return r3;
    L9:
        if (zzqb.zzg(r0) != this.zzd.length) goto L14;
        int r02 = this.zzb;
        Object[] r2 = this.zza;
        if (zzqb.zzm(r02, r2.length) == false) goto L13;
        r2 = Arrays.copyOf(r2, r02);
    L13:
        r3 = new zzqr(r2, this.zze, this.zzd, r6.length - 1, this.zzb);
        goto L15
    L20:
        return zzqr.zza;
    }
}
