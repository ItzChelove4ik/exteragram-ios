package com.google.android.recaptcha.internal;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes5.dex */
abstract class zzaee extends AbstractList implements zzage {
    private boolean zza;

    public zzaee(boolean r1) {
        this.zza = r1;
    }

    private void zzd() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int r1, Collection r2) {
        zzad();
        return super.addAll(r1, r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection r1) {
        zzad();
        return super.addAll(r1);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzad();
        super.clear();
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object r7) {
        if (r7 != this) goto L6;
        return true;
    L6:
        if ((r7 instanceof List) == true) goto L9;
        return false;
    L9:
        if ((r7 instanceof RandomAccess) == false) goto L11;
        List r72 = (List) r7;
        int r1 = size();
        if (r1 != r72.size()) goto L21;
        int r3 = 0;
    L15:
        if (r3 >= r1) goto L20;
        if (get(r3).equals(r72.get(r3)) == false) goto L18;
        r3 = r3 + 1;
        goto L15
    L18:
        return false;
    L20:
        return true;
    L21:
        return false;
    L11:
        return super.equals(r7);
    }

    @Override // java.util.AbstractList, java.util.List
    public abstract Object remove(int r1);

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object r2) {
        zzad();
        int r22 = indexOf(r2);
        if (r22 != (-1)) goto L6;
        return false;
    L6:
        remove(r22);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection r1) {
        zzad();
        return super.removeAll(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection r1) {
        zzad();
        return super.retainAll(r1);
    }

    @Override // com.google.android.recaptcha.internal.zzage
    public final boolean zza() {
        return this.zza;
    }

    public final void zzad() {
        if (this.zza == true) goto L6;
        zzd();
        return;
    }

    @Override // com.google.android.recaptcha.internal.zzage
    public final void zzb() {
        if (this.zza == false) goto L6;
        this.zza = false;
        return;
    }
}
