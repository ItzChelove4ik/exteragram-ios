package com.google.android.gms.internal.mlkit_vision_label;

import java.util.Iterator;

/* loaded from: classes4.dex */
abstract class zzcc implements Iterator {
    final Iterator zza;

    public zzcc(Iterator r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return zza(this.zza.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.zza.remove();
    }

    public abstract Object zza(Object r1);
}
