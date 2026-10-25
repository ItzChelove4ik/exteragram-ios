package com.google.android.gms.internal.fido;

import kotlin.UByte;

/* loaded from: classes4.dex */
final class zzgc extends zzge {
    final char[] zza;

    private zzgc(zzgb r5) {
        super(r5, null);
        this.zza = new char[512];
        int r2 = 0;
        if (zzgb.zzd(r5).length != 16) goto L5;
        boolean r0 = true;
    L6:
        zzbm.zzc(r0);
    L8:
        if (r2 >= 256) goto L10;
        this.zza[r2] = r5.zza(r2 >>> 4);
        this.zza[r2 | 256] = r5.zza(r2 & 15);
        r2 = r2 + 1;
        goto L8
    L10:
        return;
    L5:
        r0 = false;
        goto L6
    }

    public zzgc(String r2, String r3) {
        this(new zzgb("base16()", "0123456789ABCDEF".toCharArray()));
    }

    @Override // com.google.android.gms.internal.fido.zzge
    public final zzgf zza(zzgb r1, Character r2) {
        return new zzgc(r1);
    }

    @Override // com.google.android.gms.internal.fido.zzge, com.google.android.gms.internal.fido.zzgf
    public final void zzb(Appendable r3, byte[] r4, int r5, int r6) {
        int r0 = 0;
        zzbm.zze(0, r6, r4.length);
    L3:
        if (r0 >= r6) goto L5;
        int r52 = r4[r0] & UByte.MAX_VALUE;
        r3.append(this.zza[r52]);
        r3.append(this.zza[r52 | 256]);
        r0 = r0 + 1;
        goto L3
    }
}
