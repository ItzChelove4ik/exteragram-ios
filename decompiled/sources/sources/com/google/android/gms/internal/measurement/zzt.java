package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzt extends zzai {
    private final zzr zza;

    public zzt(zzr r6) {
        super("internal.logger");
        this.zza = r6;
        this.zze.put("log", new zzs(this, false, true));
        this.zze.put("silent", new zzp(this, "silent"));
        ((zzai) this.zze.get("silent")).zzm("log", new zzs(this, true, true));
        this.zze.put("unmonitored", new zzq(this, "unmonitored"));
        ((zzai) this.zze.get("unmonitored")).zzm("log", new zzs(this, false, false));
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzao zza(zzg r1, List r2) {
        return zzao.zzf;
    }

    public final /* synthetic */ zzr zzb() {
        return this.zza;
    }
}
