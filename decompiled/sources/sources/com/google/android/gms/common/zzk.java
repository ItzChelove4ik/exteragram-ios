package com.google.android.gms.common;

import java.util.Arrays;

/* loaded from: classes4.dex */
final class zzk extends zzj {
    private final byte[] zza;

    public zzk(byte[] r3) {
        super(Arrays.copyOfRange(r3, 0, 25));
        this.zza = r3;
    }

    @Override // com.google.android.gms.common.zzj
    public final byte[] zzc() {
        return this.zza;
    }
}
