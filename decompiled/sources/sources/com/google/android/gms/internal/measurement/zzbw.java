package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzbw extends zzcc {
    private String zza;
    private byte zzb;
    private int zzc;
    private int zzd;

    public zzbw() {
    }

    public final zzcc zza(String r1) {
        this.zza = _UrlKt.FRAGMENT_ENCODE_SET;
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final zzcc zzb(boolean r1) {
        this.zzb = 1;
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final zzcd zzc() {
        if (this.zzb == 1) goto L5;
    L13:
        StringBuilder r0 = new StringBuilder();
        if (this.zza != null) goto L17;
        r0.append(" fileOwner");
    L17:
        if (this.zzb != 0) goto L20;
        r0.append(" hasDifferentDmaOwner");
    L20:
        if (this.zzc != 0) goto L23;
        r0.append(" fileChecks");
    L23:
        if (this.zzd != 0) goto L25;
        r0.append(" filePurpose");
    L25:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0.toString()));
        return null;
    L5:
        if (this.zza == null) goto L13;
        if (this.zzc == 0) goto L13;
        if (this.zzd == 0) goto L13;
        return new zzbx(this.zza, false, this.zzc, null, this.zzd, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final zzcc zzd(int r1) {
        this.zzc = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final zzcc zze(int r1) {
        this.zzd = 1;
        return this;
    }
}
