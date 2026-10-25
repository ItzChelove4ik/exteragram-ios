package com.google.android.gms.internal.mlkit_vision_label;

import androidx.collection.ArraySet$$ExternalSyntheticBUOutline0;
import java.util.Iterator;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
abstract class zzaq implements Iterator {
    int zzb;
    int zzc;
    int zzd;
    final /* synthetic */ zzau zze;

    public /* synthetic */ zzaq(zzau r1, zzam r2) {
        this.zze = r1;
        this.zzb = zzau.zza(r1);
        this.zzc = r1.zze();
        this.zzd = -1;
    }

    private final void zzb() {
        if (zzau.zza(this.zze) != this.zzb) goto L5;
        return;
    L5:
        ArraySet$$ExternalSyntheticBUOutline0.m();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzc < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        zzb();
        if (hasNext() == false) goto L6;
        int r0 = this.zzc;
        this.zzd = r0;
        Object r02 = zza(r0);
        this.zzc = this.zze.zzf(this.zzc);
        return r02;
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzb();
        if (this.zzd < 0) goto L5;
        boolean r0 = true;
    L6:
        zzs.zzd(r0, "no calls to next() since the last call to remove()");
        this.zzb += 32;
        zzau r02 = this.zze;
        r02.remove(zzau.zzg(r02, this.zzd));
        this.zzc--;
        this.zzd = -1;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public abstract Object zza(int r1);
}
