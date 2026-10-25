package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzaft implements Iterator {
    final /* synthetic */ zzafv zza;
    private int zzb;
    private boolean zzc;
    private Iterator zzd;

    public /* synthetic */ zzaft(zzafv r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = -1;
    }

    private final Iterator zza() {
        if (this.zzd != null) goto L6;
        this.zzd = this.zza.zzk().entrySet().iterator();
    L6:
        return this.zzd;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int r0 = this.zzb + 1;
        zzafv r2 = this.zza;
        if (r0 >= r2.zzj()) goto L5;
        return true;
    L5:
        if (r2.zzk().isEmpty() == false) goto L7;
    L9:
        return false;
    L7:
        if (zza().hasNext() == false) goto L9;
        return true;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        this.zzc = true;
        int r1 = this.zzb + 1;
        this.zzb = r1;
        zzafv r0 = this.zza;
        if (r1 >= r0.zzj()) goto L7;
        return (zzafs) r0.zzi()[r1];
    L7:
        return (Map.Entry) zza().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.zzc == false) goto L10;
        this.zzc = false;
        zzafv r0 = this.zza;
        r0.zzh();
        int r1 = this.zzb;
        if (r1 >= r0.zzj()) goto L8;
        this.zzb = r1 - 1;
        r0.zzg(r1);
        return;
    L8:
        zza().remove();
        return;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("remove() was called before next()");
    }
}
