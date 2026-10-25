package com.google.android.gms.common;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzv {
    private String zza;
    private Boolean zzb;
    private Boolean zzc;

    public /* synthetic */ zzv(byte[] r1) {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
    }

    public final zzv zza(String r1) {
        this.zza = r1;
        return this;
    }

    public final zzv zzb(boolean r1) {
        this.zzb = Boolean.valueOf(r1);
        return this;
    }

    public final zzv zzc(boolean r1) {
        this.zzc = Boolean.valueOf(r1);
        return this;
    }

    public final zzw zzd() {
        Boolean r0 = this.zzb;
        if (r0 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("allowTestKeys must be set");
        return null;
    L5:
        if (this.zzc != null) goto L7;
        Segment$$ExternalSyntheticBUOutline1.m("isGoogleOrPlatformOnly must be set");
        return null;
    L7:
        return new zzw(this.zza, r0.booleanValue(), false, false, this.zzc.booleanValue(), false, null);
    }
}
