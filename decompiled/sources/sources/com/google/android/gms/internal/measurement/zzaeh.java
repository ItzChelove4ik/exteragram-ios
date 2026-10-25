package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes4.dex */
public class zzaeh extends IOException {
    private boolean zza;

    public zzaeh(IOException r2) {
        super(r2.getMessage(), r2);
    }

    public zzaeh(String r1) {
        super(r1);
    }

    public zzaeh(String r1, IOException r2) {
        super("Unable to parse map entry.", r2);
    }

    public final void zza() {
        this.zza = true;
    }

    public final boolean zzb() {
        return this.zza;
    }
}
