package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes4.dex */
abstract class zzavp extends AbstractList implements zzaxh {
    private boolean zza;

    public zzavp(boolean r1) {
        this.zza = r1;
    }

    private void zzd() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int r1, Collection r2) {
        zzV();
        return super.addAll(r1, r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection r1) {
        zzV();
        return super.addAll(r1);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzV();
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
        zzV();
        int r22 = indexOf(r2);
        if (r22 != (-1)) goto L6;
        return false;
    L6:
        remove(r22);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection r1) {
        zzV();
        return super.removeAll(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection r1) {
        zzV();
        return super.retainAll(r1);
    }

    public final void zzV() {
        if (this.zza == true) goto L6;
        zzd();
        return;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaxh
    public final boolean zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaxh
    public final void zzb() {
        if (this.zza == false) goto L6;
        this.zza = false;
        return;
    }
}
