package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Objects;
import java.util.function.Function;

/* loaded from: classes4.dex */
final /* synthetic */ class zzlq implements Function {
    static final /* synthetic */ zzlq zza = null;

    static {
        zza = new zzlq();
    }

    private /* synthetic */ zzlq() {
    }

    @Override // java.util.function.Function
    public final /* synthetic */ Object apply(Object r3) {
        zzmr r32 = (zzmr) r3;
        int r2 = r32.zzb;
        if (r2 == 0) goto L11;
        Object[] r0 = r32.zza;
        if (r2 == 1) goto L8;
        zzms r22 = zzms.zzk(r2, r0);
        r32.zzb = r22.size();
        r32.zzc = true;
        return r22;
    L8:
        Object r23 = r0[0];
        Objects.requireNonNull(r23);
        return new zznp(r23);
    L11:
        return zznm.zza;
    }
}
