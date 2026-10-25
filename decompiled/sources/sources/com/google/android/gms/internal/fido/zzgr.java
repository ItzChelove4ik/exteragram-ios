package com.google.android.gms.internal.fido;

import com.google.protobuf.ByteString$$ExternalSyntheticBUOutline0;
import com.google.protobuf.ByteString$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzgr extends zzgu {
    private final int zzc;
    private final int zzd;

    public zzgr(byte[] r2, int r3, int r4) {
        super(r2);
        zzgx.zzj(r3, r3 + r4, r2.length);
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // com.google.android.gms.internal.fido.zzgu, com.google.android.gms.internal.fido.zzgx
    public final byte zza(int r3) {
        int r0 = this.zzd;
        if (((r0 - (r3 + 1)) | r3) >= 0) goto L10;
        if (r3 >= 0) goto L7;
        ByteString$$ExternalSyntheticBUOutline0.m(r3);
        return 0;
    L7:
        ByteString$$ExternalSyntheticBUOutline1.m(r3, r0);
        return 0;
    L10:
        return this.zza[this.zzc + r3];
    }

    @Override // com.google.android.gms.internal.fido.zzgu, com.google.android.gms.internal.fido.zzgx
    public final byte zzb(int r2) {
        int r0 = this.zzc;
        return this.zza[r0 + r2];
    }

    @Override // com.google.android.gms.internal.fido.zzgu
    public final int zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.fido.zzgu, com.google.android.gms.internal.fido.zzgx
    public final int zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.fido.zzgu, com.google.android.gms.internal.fido.zzgx
    public final void zze(byte[] r1, int r2, int r3, int r4) {
        System.arraycopy(this.zza, this.zzc, r1, 0, r4);
    }
}
