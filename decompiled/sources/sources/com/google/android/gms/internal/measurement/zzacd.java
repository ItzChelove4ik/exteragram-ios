package com.google.android.gms.internal.measurement;

import java.io.InputStream;

/* loaded from: classes4.dex */
public abstract class zzacd implements zzafj {
    static {
        zzadf r0 = zzadf.zza;
        int r02 = zzacf.$r8$clinit;
    }

    public zzacd() {
    }

    @Override // com.google.android.gms.internal.measurement.zzafj
    public final /* synthetic */ Object zza(InputStream r2, zzadf r3) {
        zzacv r22 = zzacv.zzM(r2, 4096);
        zzafc r1 = (zzafc) zzb(r22, r3);
        r22.zzb(0);
        if (r1 != null) goto L5;
    L12:
        return r1;
    L5:
        if (r1.zzcJ() == true) goto L12;
        if ((r1 instanceof zzacb) == true) goto L9;
        zzafy r23 = new zzafy(r1);
    L11:
        throw r23.zza();
    L9:
        r23 = new zzafy((zzacb) r1);
        goto L11
    }
}
