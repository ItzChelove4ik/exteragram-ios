package com.google.android.gms.internal.fido;

import kotlin.UByte;

/* loaded from: classes4.dex */
final class zzgd extends zzge {
    private zzgd(zzgb r1, Character r2) {
        super(r1, r2);
        if (zzgb.zzd(r1).length != 64) goto L5;
        boolean r0 = true;
    L6:
        zzbm.zzc(r0);
        return;
    L5:
        r0 = false;
        goto L6
    }

    public zzgd(String r2, String r3, Character r4) {
        this(new zzgb(r2, r3.toCharArray()), r4);
    }

    @Override // com.google.android.gms.internal.fido.zzge
    public final zzgf zza(zzgb r1, Character r2) {
        return new zzgd(r1, r2);
    }

    @Override // com.google.android.gms.internal.fido.zzge, com.google.android.gms.internal.fido.zzgf
    public final void zzb(Appendable r6, byte[] r7, int r8, int r9) {
        int r0 = 0;
        zzbm.zze(0, r9, r7.length);
        int r82 = r9;
    L4:
        if (r82 < 3) goto L6;
        int r2 = r7[r0] & UByte.MAX_VALUE;
        int r1 = r7[r0 + 1] & UByte.MAX_VALUE;
        int r12 = ((r1 << 8) | (r2 << 16)) | (r7[r0 + 2] & UByte.MAX_VALUE);
        r6.append(this.zzb.zza(r12 >>> 18));
        r6.append(this.zzb.zza((r12 >>> 12) & 63));
        r6.append(this.zzb.zza((r12 >>> 6) & 63));
        r6.append(this.zzb.zza(r12 & 63));
        r0 = r0 + 3;
        r82 = r82 - 3;
        goto L4
    L6:
        if (r0 >= r9) goto L10;
        zze(r6, r7, r0, r9 - r0);
        return;
    }
}
