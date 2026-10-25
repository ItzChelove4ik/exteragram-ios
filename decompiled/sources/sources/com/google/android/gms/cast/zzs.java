package com.google.android.gms.cast;

import java.util.Collection;

/* loaded from: classes4.dex */
final class zzs {
    private String zza;
    private String zzb;
    private Collection zzc;

    private zzs() {
        this.zza = "com.google.android.gms.cast.CATEGORY_CAST";
    }

    public /* synthetic */ zzs(byte[] r1) {
        this.zza = "com.google.android.gms.cast.CATEGORY_CAST";
    }

    public final /* synthetic */ zzs zza(String r1) {
        this.zza = "com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK";
        return this;
    }

    public final /* synthetic */ zzs zzb(String r1) {
        this.zzb = r1;
        return this;
    }

    public final /* synthetic */ zzs zzc(Collection r1) {
        this.zzc = r1;
        return this;
    }

    public final /* synthetic */ zzt zzd() {
        return new zzt(this.zza, this.zzb, this.zzc, false, true, null);
    }
}
