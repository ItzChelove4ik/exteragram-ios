package com.google.android.gms.internal.play_billing;

import java.util.Arrays;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzbc {
    private final String zza;
    private final zzbb zzb;
    private zzbb zzc;

    public /* synthetic */ zzbc(String r1, zzbd r2) {
        zzbb r22 = new zzbb();
        this.zzb = r22;
        this.zzc = r22;
        r1.getClass();
        this.zza = r1;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder(32);
        r0.append(this.zza);
        r0.append('{');
        zzbb r4 = this.zzb.zzb;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L3:
        if (r4 == null) goto L11;
        Object r2 = r4.zza;
        r0.append(r1);
        if (r2 != null) goto L7;
    L9:
        r0.append(r2);
    L10:
        r4 = r4.zzb;
        r1 = ", ";
        goto L3
    L7:
        if (r2.getClass().isArray() == false) goto L9;
        r0.append(Arrays.deepToString(new Object[]{r2}), 1, r1.length() - 1);
        goto L10
    L11:
        r0.append('}');
        return r0.toString();
    }

    public final zzbc zza(Object r3) {
        zzbb r0 = new zzbb();
        this.zzc.zzb = r0;
        this.zzc = r0;
        r0.zza = r3;
        return this;
    }
}
