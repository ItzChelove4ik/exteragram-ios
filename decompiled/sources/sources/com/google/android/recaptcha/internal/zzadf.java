package com.google.android.recaptcha.internal;

import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public final class zzadf {
    public static final zzadf zza = null;
    public static final zzadf zzb = null;
    public static final zzadf zzc = null;
    private final zzade zzd;

    static {
        new zzadf(new zzadg());
        new zzadf(new zzadk());
        zza = new zzadf(new zzadm());
        zzb = new zzadf(new zzadl());
        new zzadf(new zzadh());
        new zzadf(new zzadj());
        zzc = new zzadf(new zzadi());
    }

    public zzadf(zzadn r4) {
        if (zztf.zza() == false) goto L5;
        zzade r0 = new zzadd(r4, null);
    L7:
        this.zzd = r0;
        return;
    L5:
        if ("The Android Project".equals(System.getProperty("java.vendor")) == false) goto L9;
        r0 = new zzadb(r4, null);
        goto L7
    L9:
        r0 = new zzadc(r4, null);
        goto L7
    }

    public static List zza(String... r3) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r3.length) goto L9;
        Provider r2 = Security.getProvider(r3[r1]);
        if (r2 == null) goto L8;
        r0.add(r2);
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        return r0;
    }

    public final Object zzb(String r1) {
        return this.zzd.zza(r1);
    }
}
