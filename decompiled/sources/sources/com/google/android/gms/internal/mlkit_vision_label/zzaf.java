package com.google.android.gms.internal.mlkit_vision_label;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes4.dex */
class zzaf extends zzad implements List {
    final /* synthetic */ zzag zzf;

    public zzaf(zzag r1, Object r2, List r3, zzad r4) {
        this.zzf = r1;
        super(r1, r2, r3, r4);
    }

    @Override // java.util.List
    public final void add(int r3, Object r4) {
        zzb();
        boolean r0 = this.zzb.isEmpty();
        ((List) this.zzb).add(r3, r4);
        zzag.zzd(this.zzf);
        if (r0 == false) goto L6;
        zza();
        return;
    }

    @Override // java.util.List
    public final boolean addAll(int r3, Collection r4) {
        if (r4.isEmpty() == false) goto L6;
        return false;
    L6:
        int r0 = size();
        boolean r32 = ((List) this.zzb).addAll(r3, r4);
        if (r32 == false) goto L12;
        int r42 = this.zzb.size();
        zzag.zzf(this.zzf, r42 - r0);
        if (r0 != 0) goto L12;
        zza();
        return true;
    L12:
        return r32;
    }

    @Override // java.util.List
    public final Object get(int r1) {
        zzb();
        return ((List) this.zzb).get(r1);
    }

    @Override // java.util.List
    public final int indexOf(Object r1) {
        zzb();
        return ((List) this.zzb).indexOf(r1);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object r1) {
        zzb();
        return ((List) this.zzb).lastIndexOf(r1);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        zzb();
        return new zzae(this);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int r2) {
        zzb();
        return new zzae(this, r2);
    }

    @Override // java.util.List
    public final Object remove(int r2) {
        zzb();
        Object r22 = ((List) this.zzb).remove(r2);
        zzag.zze(this.zzf);
        zzc();
        return r22;
    }

    @Override // java.util.List
    public final Object set(int r1, Object r2) {
        zzb();
        return ((List) this.zzb).set(r1, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [com.google.android.gms.internal.mlkit_vision_label.zzad] */
    @Override // java.util.List
    public final List subList(int r4, int r5) {
        zzb();
        zzag r0 = this.zzf;
        Object r1 = this.zza;
        List r42 = ((List) this.zzb).subList(r4, r5);
        ?? r52 = this.zzc;
        if (r52 == 0) goto L6;
        zzaf this = r52;
    L6:
        return r0.zzi(r1, r42, this);
    }
}
