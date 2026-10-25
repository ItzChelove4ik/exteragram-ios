package com.google.android.gms.internal.measurement;

import com.google.common.collect.ImmutableList;
import java.util.UUID;

/* loaded from: classes4.dex */
final class zzvp extends zzww {
    private final ImmutableList zza;
    private final ImmutableList zzb;
    private final UUID zzc;
    private final long zzd;

    public /* synthetic */ zzvp(ImmutableList r1, ImmutableList r2, UUID r3, long r4, byte[] r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public final boolean equals(Object r6) {
        if (r6 != this) goto L6;
        return true;
    L6:
        if ((r6 instanceof zzww) == false) goto L16;
        zzww r62 = (zzww) r6;
        if (this.zza.equals(r62.zza()) == false) goto L16;
        if (this.zzb.equals(r62.zzb()) == false) goto L16;
        if (this.zzc.equals(r62.zzc()) == false) goto L16;
        if (this.zzd != r62.zzd()) goto L16;
        return true;
    L16:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        int r02 = (r0 * 1000003) ^ this.zzb.hashCode();
        int r03 = (r02 * 1000003) ^ this.zzc.hashCode();
        long r2 = this.zzd;
        int r6 = (int) (r2 ^ (r2 >>> 32));
        return r6 ^ (r03 * 1000003);
    }

    @Override // com.google.android.gms.internal.measurement.zzww
    public final ImmutableList zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzww
    public final ImmutableList zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzww
    public final UUID zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzww
    public final long zzd() {
        return this.zzd;
    }
}
