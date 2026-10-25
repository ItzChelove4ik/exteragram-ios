package com.google.android.gms.internal.fido;

import java.util.Comparator;
import kotlin.UByte;

/* loaded from: classes4.dex */
final class zzgp implements Comparator {
    public zzgp() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object r4, Object r5) {
        zzgx r42 = (zzgx) r4;
        zzgx r52 = (zzgx) r5;
        zzgo r3 = new zzgo(r42);
        zzgo r0 = new zzgo(r52);
    L4:
        if (r3.hasNext() == false) goto L11;
        if (r0.hasNext() == false) goto L11;
        int r1 = Integer.valueOf(r3.zza() & UByte.MAX_VALUE).compareTo(Integer.valueOf(r0.zza() & UByte.MAX_VALUE));
        if (r1 == 0) goto L4;
        return r1;
    L11:
        return Integer.valueOf(r42.zzd()).compareTo(Integer.valueOf(r52.zzd()));
    }
}
