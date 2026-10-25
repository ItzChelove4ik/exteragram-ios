package com.google.android.gms.internal.fido;

import com.android.dx.io.Opcodes;

/* loaded from: classes4.dex */
public final class zzhr {
    private final byte zza;
    private final byte zzb;

    public zzhr(int r2) {
        this.zza = (byte) (r2 & Opcodes.SHL_INT_LIT8);
        this.zzb = (byte) (r2 & 31);
    }

    public final byte zza() {
        return this.zzb;
    }

    public final byte zzb() {
        return this.zza;
    }

    public final int zzc() {
        return (this.zza >> 5) & 7;
    }
}
