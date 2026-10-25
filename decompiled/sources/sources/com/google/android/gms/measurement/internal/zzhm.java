package com.google.android.gms.measurement.internal;

import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzhm extends LruCache {
    final /* synthetic */ zzht zza;

    public zzhm(zzht r1, int r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(20);
    }

    @Override // androidx.collection.LruCache
    public final /* bridge */ /* synthetic */ Object create(Object r1) {
        String r12 = (String) r1;
        Preconditions.checkNotEmpty(r12);
        return this.zza.zzD(r12);
    }
}
