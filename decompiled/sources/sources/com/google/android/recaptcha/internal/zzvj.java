package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzvj implements Iterable {
    final /* synthetic */ List zza;
    final /* synthetic */ List zzb;

    public zzvj(zzvm r1, List r2, List r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        List r0 = this.zzb;
        return new zzvl(this.zza.iterator(), r0.iterator(), null);
    }
}
