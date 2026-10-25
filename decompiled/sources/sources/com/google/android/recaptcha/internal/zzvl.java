package com.google.android.recaptcha.internal;

import java.util.Iterator;

/* loaded from: classes5.dex */
final class zzvl implements Iterator {
    private final Iterator zza;
    private final Iterator zzb;

    public /* synthetic */ zzvl(Iterator r1, Iterator r2, byte[] r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zza.hasNext() == false) goto L5;
        return true;
    L5:
        if (this.zzb.hasNext() == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Iterator r0 = this.zza;
        if (r0.hasNext() == false) goto L7;
        return r0.next();
    L7:
        return this.zzb.next();
    }
}
