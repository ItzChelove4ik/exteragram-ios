package com.google.android.gms.internal.play_billing;

import com.google.protobuf.ByteString$$ExternalSyntheticBUOutline0;
import com.google.protobuf.ByteString$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
final class zzec extends zzeg {
    private final int zzc;

    public zzec(byte[] r1, int r2, int r3) {
        super(r1);
        zzei.zzh(0, r3, r1.length);
        this.zzc = r3;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg, com.google.android.gms.internal.play_billing.zzei
    public final byte zza(int r3) {
        int r0 = this.zzc;
        if (((r0 - (r3 + 1)) | r3) >= 0) goto L10;
        if (r3 >= 0) goto L7;
        ByteString$$ExternalSyntheticBUOutline0.m(r3);
        return 0;
    L7:
        ByteString$$ExternalSyntheticBUOutline1.m(r3, r0);
        return 0;
    L10:
        return this.zza[r3];
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg, com.google.android.gms.internal.play_billing.zzei
    public final byte zzb(int r1) {
        return this.zza[r1];
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg, com.google.android.gms.internal.play_billing.zzei
    public final int zzd() {
        return this.zzc;
    }
}
