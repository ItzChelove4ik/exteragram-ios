package com.google.android.recaptcha.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
final class zzqd extends zzqb {
    private final transient zzqe zza;

    public zzqd(zzqe r1) {
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzpq, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r3) {
        if ((r3 instanceof Map.Entry) == false) goto L10;
        Map.Entry r32 = (Map.Entry) r3;
        zzqe r2 = this.zza;
        Object r0 = r32.getKey();
        Object r33 = r32.getValue();
        Collection r22 = (Collection) r2.zza().get(r0);
        if (r22 == null) goto L10;
        if (r22.contains(r33) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return new zzpx(this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size;
    }

    @Override // com.google.android.recaptcha.internal.zzqb, com.google.android.recaptcha.internal.zzpq
    public final zzqu zza() {
        return new zzpx(this.zza);
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final boolean zze() {
        return false;
    }
}
