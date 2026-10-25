package com.google.android.gms.internal.mlkit_vision_label;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;

/* loaded from: classes4.dex */
abstract class zzbp extends AbstractMap {
    private transient Set zza;
    private transient Collection zzc;

    public zzbp() {
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set r0 = this.zza;
        if (r0 != null) goto L6;
        Set r02 = zza();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection r0 = this.zzc;
        if (r0 != null) goto L6;
        zzbo r02 = new zzbo(this);
        this.zzc = r02;
        return r02;
    L6:
        return r0;
    }

    public abstract Set zza();
}
