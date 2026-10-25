package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class zzcd {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        zzbw r0 = new zzbw();
        r0.zza(_UrlKt.FRAGMENT_ENCODE_SET);
        r0.zzb(false);
        r0.zzd(1);
        r0.zze(1);
        r0.zzc();
        zzbw r02 = new zzbw();
        r02.zza(_UrlKt.FRAGMENT_ENCODE_SET);
        r02.zzb(false);
        r02.zzd(4);
        r02.zze(1);
        r02.zzc();
        zzbw r03 = new zzbw();
        r03.zza(_UrlKt.FRAGMENT_ENCODE_SET);
        r03.zzb(false);
        r03.zzd(2);
        r03.zze(1);
        r03.zzc();
    }

    public zzcd() {
    }

    public abstract String zza();

    public abstract boolean zzb();

    public abstract zzbv zzc();

    public abstract int zzd();

    public abstract int zze();
}
