package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;
import java.util.Iterator;

/* loaded from: classes5.dex */
final class zzacr implements zzst {
    private final zzvm zza;

    public zzacr(zzvm r1, zzum r2) {
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzst
    public final void zza(byte[] r2, byte[] r3) {
        Iterator r1 = this.zza.zza(r2).iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        ((zzacs) r1.next()).zza.zza(r2, r3);     // Catch: GeneralSecurityException -> L10
        return;
    L8:
        zzsj$$ExternalSyntheticBUOutline1.m("invalid signature");
    }
}
