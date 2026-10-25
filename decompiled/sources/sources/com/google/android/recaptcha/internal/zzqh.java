package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: classes5.dex */
final class zzqh extends zzqk implements Serializable {
    final Comparator zza;

    public zzqh(Comparator r1) {
        this.zza = r1;
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(Object r4, Object r5) {
        Iterator r42 = ((Iterable) r4).iterator();
        Iterator r52 = ((Iterable) r5).iterator();
    L4:
        if (r42.hasNext() == false) goto L13;
        if (r52.hasNext() == false) goto L7;
        int r0 = this.zza.compare(r42.next(), r52.next());
        if (r0 == 0) goto L4;
        return r0;
    L7:
        return 1;
    L13:
        if (r52.hasNext() == false) goto L16;
        return -1;
    L16:
        return 0;
    }

    @Override // java.util.Comparator
    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzqh) == true) goto L8;
        return false;
    L8:
        return this.zza.equals(((zzqh) r2).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode() ^ 2075626741;
    }

    public final String toString() {
        return this.zza.toString().concat(".lexicographical()");
    }
}
