package com.google.android.gms.internal.cast;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzie extends zzhz {
    private final transient zzhy zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    public zzie(zzhy r1, Object[] r2, int r3, int r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
    }

    @Override // com.google.android.gms.internal.cast.zzhr, java.util.AbstractCollection, java.util.Collection
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
        return zze().zzl(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzhr
    public final int zzg(Object[] r1, int r2) {
        return zze().zzg(r1, 0);
    }

    @Override // com.google.android.gms.internal.cast.zzhz
    public final zzhv zzl() {
        return new zzid(this);
    }

    public final /* synthetic */ Object[] zzm() {
        return this.zzb;
    }

    public final /* synthetic */ int zzn() {
        return this.zzc;
    }
}
