package com.google.android.gms.internal.vision;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
class zzlo extends AbstractSet {
    private final /* synthetic */ zzlh zza;

    private zzlo(zzlh r1) {
        this.zza = r1;
    }

    public /* synthetic */ zzlo(zzlh r1, zzlg r2) {
        this(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public /* synthetic */ boolean add(Object r2) {
        Map.Entry r22 = (Map.Entry) r2;
        if (contains(r22) == true) goto L6;
        this.zza.zza((Comparable) r22.getKey(), r22.getValue());
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object r2) {
        Map.Entry r22 = (Map.Entry) r2;
        Object r1 = this.zza.get(r22.getKey());
        Object r23 = r22.getValue();
        if (r1 == r23) goto L10;
        if (r1 != null) goto L6;
        return false;
    L6:
        if (r1.equals(r23) == true) goto L13;
        return false;
    L13:
        return true;
    L10:
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        return new zzlp(this.zza, null);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object r2) {
        Map.Entry r22 = (Map.Entry) r2;
        if (contains(r22) == false) goto L6;
        this.zza.remove(r22.getKey());
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.zza.size();
    }
}
