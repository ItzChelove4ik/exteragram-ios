package com.google.android.gms.internal.cast;

import java.util.Arrays;

/* loaded from: classes4.dex */
public final class zzhs extends zzhp {
    public zzhs() {
        super(4);
    }

    public final zzhs zzb(Object r4) {
        r4.getClass();
        int r0 = this.zza.length;
        int r1 = zzhq.zza(r0, this.zzb + 1);
        if (r1 <= r0) goto L5;
    L6:
        this.zza = Arrays.copyOf(this.zza, r1);
        this.zzc = false;
    L7:
        Object[] r02 = this.zza;
        int r12 = this.zzb;
        this.zzb = r12 + 1;
        r02[r12] = r4;
        return this;
    L5:
        if (this.zzc == false) goto L7;
        goto L6
    }

    public final zzhv zzc() {
        this.zzc = true;
        return zzhv.zzk(this.zza, this.zzb);
    }
}
