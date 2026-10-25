package com.google.android.gms.internal.fido;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
abstract class zzfp extends zzfr {
    private final ByteBuffer zza;

    public zzfp() {
        this.zza = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
    }

    @Override // com.google.android.gms.internal.fido.zzfv
    public final zzfv zza(byte[] r3) {
        r3.getClass();
        zzb(r3, 0, r3.length);
        return this;
    }

    public abstract void zzb(byte[] r1, int r2, int r3);
}
