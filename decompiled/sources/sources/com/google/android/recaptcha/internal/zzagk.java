package com.google.android.recaptcha.internal;

import java.io.IOException;

/* loaded from: classes5.dex */
public class zzagk extends IOException {
    private boolean zza;

    public zzagk(IOException r2) {
        super(r2.getMessage(), r2);
    }

    public zzagk(String r1) {
        super(r1);
    }

    public final void zza() {
        this.zza = true;
    }

    public final boolean zzb() {
        return this.zza;
    }
}
