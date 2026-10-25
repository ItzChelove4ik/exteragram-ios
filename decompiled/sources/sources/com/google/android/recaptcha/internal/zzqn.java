package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
final class zzqn extends zzqb {
    private final transient zzpw zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    public zzqn(zzpw r1, Object[] r2, int r3, int r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
    }

    @Override // com.google.android.recaptcha.internal.zzpq, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r3) {
        if ((r3 instanceof Map.Entry) == false) goto L10;
        Map.Entry r32 = (Map.Entry) r3;
        Object r0 = r32.getKey();
        Object r33 = r32.getValue();
        if (r33 == null) goto L10;
        if (r33.equals(this.zza.get(r0)) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return zzj().zzj(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.recaptcha.internal.zzqb, com.google.android.recaptcha.internal.zzpq
    public final zzqu zza() {
        return zzj().zzj(0);
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final boolean zze() {
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final int zzf(Object[] r1, int r2) {
        return zzj().zzf(r1, 0);
    }

    @Override // com.google.android.recaptcha.internal.zzqb
    public final zzpt zzk() {
        return new zzqm(this);
    }

    public final /* synthetic */ Object[] zzn() {
        return this.zzb;
    }

    public final /* synthetic */ int zzo() {
        return this.zzc;
    }
}
