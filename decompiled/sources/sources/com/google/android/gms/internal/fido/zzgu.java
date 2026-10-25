package com.google.android.gms.internal.fido;

import com.android.dex.Dex$$ExternalSyntheticBUOutline1;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
class zzgu extends zzgt {
    protected final byte[] zza;

    public zzgu(byte[] r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.fido.zzgx
    public final boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof zzgx) == true) goto L9;
        return false;
    L9:
        if (zzd() == ((zzgx) r8).zzd()) goto L12;
        return false;
    L12:
        if (zzd() != 0) goto L15;
        return true;
    L15:
        if ((r8 instanceof zzgu) == false) goto L38;
        zzgu r82 = (zzgu) r8;
        int r1 = zzk();
        int r3 = r82.zzk();
        if (r1 == 0) goto L22;
        if (r3 == 0) goto L22;
        if (r1 == r3) goto L22;
        return false;
    L22:
        int r12 = zzd();
        if (r12 <= r82.zzd()) goto L25;
        zzgu$$ExternalSyntheticBUOutline0.m(r12, zzd());
        return false;
    L25:
        if (r12 > r82.zzd()) goto L33;
        byte[] r32 = this.zza;
        byte[] r4 = r82.zza;
        int r5 = zzc() + r12;
        int r7 = zzc();
        int r83 = r82.zzc();
    L27:
        if (r7 >= r5) goto L32;
        if (r32[r7] != r4[r83]) goto L30;
        r7 = r7 + 1;
        r83 = r83 + 1;
        goto L27
    L30:
        return false;
    L32:
        return true;
    L33:
        Dex$$ExternalSyntheticBUOutline1.m("Ran off end of other: 0, ", r12, ", ", r82.zzd());
        return false;
    L38:
        return r8.equals(this);
    }

    @Override // com.google.android.gms.internal.fido.zzgx
    public byte zza(int r1) {
        return this.zza[r1];
    }

    @Override // com.google.android.gms.internal.fido.zzgx
    public byte zzb(int r1) {
        return this.zza[r1];
    }

    public int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.fido.zzgx
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.fido.zzgx
    public void zze(byte[] r1, int r2, int r3, int r4) {
        System.arraycopy(this.zza, 0, r1, 0, r4);
    }

    @Override // com.google.android.gms.internal.fido.zzgx
    public final int zzf(int r3, int r4, int r5) {
        int r42 = zzc();
        byte[] r0 = zzhc.zzd;
        int r02 = r42;
    L4:
        if (r02 >= (r42 + r5)) goto L6;
        r3 = (r3 * 31) + this.zza[r02];
        r02 = r02 + 1;
        goto L4
    L6:
        return r3;
    }

    @Override // com.google.android.gms.internal.fido.zzgx
    public final zzgx zzg(int r3, int r4) {
        int r42 = zzgx.zzj(r3, r4, zzd());
        if (r42 != 0) goto L7;
        return zzgx.zzb;
    L7:
        return new zzgr(this.zza, zzc() + r3, r42);
    }

    @Override // com.google.android.gms.internal.fido.zzgx
    public final InputStream zzh() {
        return new ByteArrayInputStream(this.zza, zzc(), zzd());
    }

    @Override // com.google.android.gms.internal.fido.zzgx
    public final ByteBuffer zzi() {
        return ByteBuffer.wrap(this.zza, zzc(), zzd()).asReadOnlyBuffer();
    }
}
