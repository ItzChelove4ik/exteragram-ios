package com.google.android.recaptcha.internal;

import java.util.ArrayDeque;
import java.util.Iterator;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class zzahl implements Iterator {
    private final ArrayDeque zza;
    private zzaeo zzb;

    public /* synthetic */ zzahl(zzaer r2, byte[] r3) {
        if ((r2 instanceof zzahm) == false) goto L6;
        zzahm r22 = (zzahm) r2;
        ArrayDeque r32 = new ArrayDeque(r22.zzo());
        this.zza = r32;
        r32.push(r22);
        this.zzb = zzb(r22.zzn());
        return;
    L6:
        this.zza = null;
        this.zzb = (zzaeo) r2;
    }

    private final zzaeo zzb(zzaer r2) {
    L3:
        if ((r2 instanceof zzahm) == false) goto L6;
        zzahm r22 = (zzahm) r2;
        this.zza.push(r22);
        r2 = r22.zzn();
        goto L3
    L6:
        return (zzaeo) r2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzb == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return zza();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final zzaeo zza() {
        zzaeo r0 = this.zzb;
        zzaeo r1 = null;
        if (r0 == null) goto L14;
    L4:
        ArrayDeque r2 = this.zza;
        if (r2 == null) goto L12;
        if (r2.isEmpty() == true) goto L12;
        zzaeo r22 = zzb(((zzahm) r2.pop()).zzD());
        if (r22.zzr() == true) goto L4;
        r1 = r22;
    L12:
        this.zzb = r1;
        return r0;
    L14:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
