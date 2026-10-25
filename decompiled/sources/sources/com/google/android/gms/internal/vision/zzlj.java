package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
final class zzlj implements Iterator {
    private int zza;
    private Iterator zzb;
    private final /* synthetic */ zzlh zzc;

    private zzlj(zzlh r1) {
        this.zzc = r1;
        this.zza = zzlh.zzb(r1).size();
    }

    public /* synthetic */ zzlj(zzlh r1, zzlg r2) {
        this(r1);
    }

    private final Iterator zza() {
        if (this.zzb != null) goto L6;
        this.zzb = zzlh.zzd(this.zzc).entrySet().iterator();
    L6:
        return this.zzb;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int r0 = this.zza;
        if (r0 <= 0) goto L7;
        if (r0 > zzlh.zzb(this.zzc).size()) goto L7;
        return true;
    L7:
        if (zza().hasNext() == true) goto L12;
        return false;
    L12:
        return true;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        if (zza().hasNext() == true) goto L5;
        List r0 = zzlh.zzb(this.zzc);
        int r1 = this.zza - 1;
        this.zza = r1;
        return (Map.Entry) r0.get(r1);
    L5:
        return (Map.Entry) zza().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
