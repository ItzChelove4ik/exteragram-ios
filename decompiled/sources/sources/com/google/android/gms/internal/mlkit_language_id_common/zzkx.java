package com.google.android.gms.internal.mlkit_language_id_common;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzkx extends zzlb {
    private String zza;
    private boolean zzb;
    private int zzc;
    private byte zzd;

    public zzkx() {
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzlb
    public final zzlb zza(boolean r2) {
        this.zzb = true;
        this.zzd = (byte) (1 | this.zzd);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzlb
    public final zzlb zzb(int r1) {
        this.zzc = 1;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    public final zzlb zzc(String r1) {
        this.zza = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzlb
    public final zzlc zzd() {
        if (this.zzd != 3) goto L9;
        String r0 = this.zza;
        if (r0 == null) goto L9;
        return new zzkz(r0, this.zzb, this.zzc, null);
    L9:
        StringBuilder r02 = new StringBuilder();
        if (this.zza != null) goto L13;
        r02.append(" libraryName");
    L13:
        if ((this.zzd & 1) != 0) goto L16;
        r02.append(" enableFirelog");
    L16:
        if ((this.zzd & 2) != 0) goto L18;
        r02.append(" firelogEventType");
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r02.toString()));
        return null;
    }
}
