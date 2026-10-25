package com.google.android.recaptcha.internal;

import java.util.List;

/* loaded from: classes5.dex */
final class zzago {
    public zzago() {
    }

    public static final List zza(Object r2, long r3) {
        zzage r0 = (zzage) zzaig.zzl(r2, r3);
        if (r0.zza() == true) goto L10;
        int r1 = r0.size();
        if (r1 != 0) goto L7;
        int r12 = 10;
    L8:
        zzage r02 = r0.zzg(r12);
        zzaig.zzm(r2, r3, r02);
        return r02;
    L7:
        r12 = r1 + r1;
        goto L8
    L10:
        return r0;
    }
}
