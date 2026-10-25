package com.google.android.recaptcha.internal;

import java.util.Comparator;

/* loaded from: classes5.dex */
final class zzu implements Comparator {
    public zzu() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object r4, Object r5) {
        zzv r42 = (zzv) r4;
        zzv r52 = (zzv) r5;
        int r3 = 0;
        int r0 = 0;
    L3:
        int r1 = r42.zza.length;
        if (r3 >= r1) goto L12;
        if (r0 >= r52.zza.length) goto L12;
        int r12 = Integer.compare(zzv.zzg(r42.zzb(r3)), zzv.zzg(r52.zzb(r0)));
        if (r12 != 0) goto L9;
        r3 = r3 + 1;
        r0 = r0 + 1;
        goto L3
    L9:
        return r12;
    L12:
        return Integer.compare(r1, r52.zza.length);
    }
}
