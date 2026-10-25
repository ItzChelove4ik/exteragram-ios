package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.List;
import java.util.ListIterator;

/* loaded from: classes4.dex */
final class zzu extends zzs implements ListIterator {
    final /* synthetic */ zzv zzd;

    public zzu(zzv r1) {
        this.zzd = r1;
        super(r1);
    }

    public zzu(zzv r2, int r3) {
        this.zzd = r2;
        super(r2, ((List) r2.zzb).listIterator(r3));
    }

    @Override // java.util.ListIterator
    public final void add(Object r3) {
        boolean r0 = this.zzd.isEmpty();
        zza();
        ((ListIterator) this.zza).add(r3);
        zzw r32 = this.zzd.zzf;
        zzw.zzj(r32, zzw.zzd(r32) + 1);
        if (r0 == false) goto L6;
        this.zzd.zza();
        return;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        zza();
        return ((ListIterator) this.zza).hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        zza();
        return ((ListIterator) this.zza).nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        zza();
        return ((ListIterator) this.zza).previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        zza();
        return ((ListIterator) this.zza).previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(Object r1) {
        zza();
        ((ListIterator) this.zza).set(r1);
    }
}
