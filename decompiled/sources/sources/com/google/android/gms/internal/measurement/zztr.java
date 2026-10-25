package com.google.android.gms.internal.measurement;

import android.net.Uri;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/* loaded from: classes4.dex */
public abstract class zztr {
    public zztr() {
    }

    public static zztq zzh() {
        zztd r0 = new zztd();
        r0.zzd(zztw.zza());
        r0.zzf(false);
        r0.zze(true);
        return r0;
    }

    public abstract Uri zza();

    public abstract zzafc zzb();

    public abstract Optional zzc();

    public abstract ImmutableList zzd();

    public abstract zzuj zze();

    public abstract boolean zzf();

    public abstract boolean zzg();
}
