package com.google.android.gms.internal.vision;

import java.util.Comparator;

/* loaded from: classes5.dex */
final class zzhv implements Comparator<zzht> {
    public zzhv() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzht r4, zzht r5) {
        zzht r42 = r4;
        zzht r52 = r5;
        zzhy r3 = (zzhy) r42.iterator();
        zzhy r0 = (zzhy) r52.iterator();
    L4:
        if (r3.hasNext() == false) goto L11;
        if (r0.hasNext() == false) goto L11;
        int r1 = Integer.compare(zzht.zza(r3.zza()), zzht.zza(r0.zza()));
        if (r1 == 0) goto L4;
        return r1;
    L11:
        return Integer.compare(r42.zza(), r52.zza());
    }
}
