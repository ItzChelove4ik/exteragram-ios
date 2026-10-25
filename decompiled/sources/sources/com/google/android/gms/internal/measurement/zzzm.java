package com.google.android.gms.internal.measurement;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzzm {
    private static final zzzp zza = null;
    private static final zzzo zzb = null;
    private final Map zzc;
    private final Map zzd;
    private final zzzp zze;
    private zzzo zzf;

    static {
        zza = new zzzk();
        zzb = new zzzl();
    }

    public /* synthetic */ zzzm(zzzp r1, byte[] r2) {
        this.zzc = new HashMap();
        this.zzd = new HashMap();
        this.zzf = null;
        this.zze = r1;
    }

    public final zzzm zza(zzzo r1) {
        this.zzf = r1;
        return this;
    }

    public final void zzb(zzyl r4) {
        zzabr.zza(r4, PluginsConstants.Settings.KEY);
        if (r4.zzf() == false) goto L6;
        zzzo r1 = zzb;
        zzabr.zza(r4, PluginsConstants.Settings.KEY);
        zzabr.zzb(r4.zzf(), "key must be repeating");
        this.zzc.remove(r4);
        this.zzd.put(r4, r1);
        return;
    L6:
        zzzp r12 = zza;
        zzabr.zza(r4, PluginsConstants.Settings.KEY);
        this.zzd.remove(r4);
        this.zzc.put(r4, r12);
    }

    public final zzzq zzc() {
        return new zzzn(this, null);
    }

    public final /* synthetic */ Map zzd() {
        return this.zzc;
    }

    public final /* synthetic */ Map zze() {
        return this.zzd;
    }

    public final /* synthetic */ zzzp zzf() {
        return this.zze;
    }

    public final /* synthetic */ zzzo zzg() {
        return this.zzf;
    }
}
