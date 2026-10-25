package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzmn {
    Object[] zza;
    int zzb;
    zzmm zzc;

    public zzmn() {
        this(4);
    }

    public zzmn(int r1) {
        this.zza = new Object[r1 + r1];
        this.zzb = 0;
    }

    private final void zzd(int r3) {
        Object[] r0 = this.zza;
        int r1 = r0.length;
        int r32 = r3 + r3;
        if (r32 <= r1) goto L6;
        this.zza = Arrays.copyOf(r0, zzmf.zzc(r1, r32));
        return;
    }

    public final zzmn zza(Object r4, Object r5) {
        zzd(this.zzb + 1);
        zzlu.zza(r4, r5);
        Object[] r0 = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + r1;
        r0[r2] = r4;
        r0[r2 + 1] = r5;
        this.zzb = r1 + 1;
        return this;
    }

    public final zzmn zzb(Iterable r3) {
        if ((r3 instanceof Collection) == false) goto L5;
        zzd(this.zzb + ((Collection) r3).size());
    L5:
        Iterator r32 = r3.iterator();
    L7:
        if (r32.hasNext() == false) goto L9;
        Map.Entry r0 = (Map.Entry) r32.next();
        zza(r0.getKey(), r0.getValue());
        goto L7
    L9:
        return this;
    }

    public final zzmo zzc() {
        zzmm r0 = this.zzc;
        if (r0 != null) goto L10;
        zznl r02 = zznl.zzj(this.zzb, this.zza, this);
        zzmm r2 = this.zzc;
        if (r2 != null) goto L8;
        return r02;
    L8:
        throw r2.zza();
    L10:
        throw r0.zza();
    }
}
