package com.google.android.recaptcha.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzqz extends zzqy {
    ByteBuffer zza;
    final /* synthetic */ zzra zzb;

    public zzqz(zzra r1, int r2) {
        Objects.requireNonNull(r1);
        this.zzb = r1;
        this.zza = ByteBuffer.allocate(32).order(ByteOrder.LITTLE_ENDIAN);
    }

    @Override // com.google.android.recaptcha.internal.zzqy
    public final zzrg zzc(byte[] r2, int r3, int r4) {
        if (this.zza.remaining() >= r4) goto L5;
        int r32 = this.zza.capacity();
        int r33 = r32 + r32;
        ByteBuffer r34 = ByteBuffer.allocate(Math.max(r33, this.zza.position() + r4)).order(ByteOrder.LITTLE_ENDIAN);
        this.zza.flip();
        r34.put(this.zza);
        this.zza = r34;
    L5:
        this.zza.put(r2, 0, r4);
        return this;
    }

    @Override // com.google.android.recaptcha.internal.zzrg
    public final zzre zzf() {
        byte[] r0 = this.zza.array();
        int r1 = this.zza.position();
        return this.zzb.zzb(r0, 0, r1);
    }
}
