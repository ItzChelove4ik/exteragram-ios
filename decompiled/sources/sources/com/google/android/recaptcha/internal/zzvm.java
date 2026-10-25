package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public final class zzvm {
    private static final zzady zza = null;
    private final Map zzb;

    static {
        zza = zzady.zza(new byte[0]);
    }

    public /* synthetic */ zzvm(Map r1, byte[] r2) {
        this.zzb = r1;
    }

    public final Iterable zza(byte[] r5) {
        Map r0 = this.zzb;
        List r1 = (List) r0.get(zza);
        if (r5.length < 5) goto L5;
        List r52 = (List) r0.get(zzady.zzb(r5, 0, 5));
    L6:
        if (r1 != null) goto L11;
        if (r52 != null) goto L11;
        return new ArrayList();
    L11:
        if (r1 != null) goto L13;
        return r52;
    L13:
        if (r52 != null) goto L16;
        return r1;
    L16:
        return new zzvj(this, r52, r1);
    L5:
        r52 = null;
        goto L6
    }
}
