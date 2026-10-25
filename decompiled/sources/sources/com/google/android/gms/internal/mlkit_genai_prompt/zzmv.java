package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Iterator;

/* loaded from: classes4.dex */
final class zzmv implements zznc {
    private final Iterator zza;
    private boolean zzb;
    private Object zzc;

    public zzmv(Iterator r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzb == false) goto L5;
        return true;
    L5:
        if (this.zza.hasNext() == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zznc, java.util.Iterator
    public final Object next() {
        if (this.zzb == false) goto L5;
        Object r0 = this.zzc;
        this.zzb = false;
        this.zzc = null;
        return r0;
    L5:
        return this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzla.zzc(!this.zzb, "Can't remove after you've peeked at next");
        this.zza.remove();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zznc
    public final Object zza() {
        if (this.zzb == true) goto L6;
        this.zzc = this.zza.next();
        this.zzb = true;
    L6:
        return this.zzc;
    }
}
