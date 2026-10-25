package com.google.android.gms.internal.icing;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

@VisibleForTesting
/* loaded from: classes4.dex */
public final class zzr {
    private final String zza;
    private String zzb;
    private boolean zzc;
    private boolean zzd;
    private final List<zzm> zze;
    private String zzf;

    public zzr(String r1) {
        this.zza = r1;
        this.zze = new ArrayList();
    }

    public final zzr zza(String r1) {
        this.zzb = "blob";
        return this;
    }

    public final zzr zzb(boolean r1) {
        this.zzc = true;
        return this;
    }

    public final zzs zze() {
        String r1 = this.zza;
        String r2 = this.zzb;
        boolean r3 = this.zzc;
        boolean r5 = this.zzd;
        List<zzm> r4 = this.zze;
        return new zzs(r1, r2, r3, 1, r5, null, (zzm[]) r4.toArray(new zzm[r4.size()]), this.zzf, null);
    }
}
