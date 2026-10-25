package com.google.android.gms.internal.play_billing;

/* loaded from: classes.dex */
public final class zzhg extends RuntimeException {
    public zzhg(zzgl r1) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzfq zza() {
        return new zzfq(getMessage());
    }
}
