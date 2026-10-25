package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzaiw implements zzaiv {

    @Deprecated
    public static final zzom zza = null;

    static {
        zza = zzagr.zzc().zza("measurement.client.sessions.enable_fix_background_engagement", true);
    }

    public zzaiw() {
    }

    @Override // com.google.android.gms.internal.measurement.zzaiv
    public final boolean zza() {
        return ((Boolean) zza.get()).booleanValue();
    }
}
