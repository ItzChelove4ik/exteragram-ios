package com.google.android.gms.internal.fido;

import java.io.Serializable;
import kotlin.UByte;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzfs extends zzft implements Serializable {
    final byte[] zza;

    public zzfs(byte[] r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.fido.zzft
    public final int zza() {
        byte[] r4 = this.zza;
        int r0 = r4.length;
        if (r0 < 4) goto L6;
        int r02 = r4[0] & UByte.MAX_VALUE;
        int r1 = r4[1] & UByte.MAX_VALUE;
        int r2 = r4[2] & UByte.MAX_VALUE;
        int r42 = (r4[3] & UByte.MAX_VALUE) << 24;
        return r42 | ((r02 | (r1 << 8)) | (r2 << 16));
    L6:
        Segment$$ExternalSyntheticBUOutline1.m(zzbo.zza("HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", new Object[]{Integer.valueOf(r0)}));
        return 0;
    }

    @Override // com.google.android.gms.internal.fido.zzft
    public final int zzb() {
        return this.zza.length * 8;
    }

    @Override // com.google.android.gms.internal.fido.zzft
    public final boolean zzc(zzft r7) {
        if (this.zza.length != r7.zze().length) goto L13;
        boolean r3 = true;
        int r1 = 0;
    L5:
        byte[] r4 = this.zza;
        if (r1 >= r4.length) goto L12;
        if (r4[r1] != r7.zze()[r1]) goto L10;
        boolean r42 = true;
    L11:
        r3 = r3 & r42;
        r1 = r1 + 1;
        goto L5
    L10:
        r42 = false;
        goto L11
    L12:
        return r3;
    L13:
        return false;
    }

    @Override // com.google.android.gms.internal.fido.zzft
    public final byte[] zzd() {
        return (byte[]) this.zza.clone();
    }

    @Override // com.google.android.gms.internal.fido.zzft
    public final byte[] zze() {
        return this.zza;
    }
}
