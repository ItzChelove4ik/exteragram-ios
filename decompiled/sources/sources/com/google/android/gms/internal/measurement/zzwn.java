package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzwn {
    int zza;
    final int zzb;
    zzwn zzc;
    final Map zzd;

    public zzwn(int r2, int r3, zzwn r4) {
        this.zzd = new HashMap(0);
        if (r2 > r3) goto L6;
        this.zza = r2;
        this.zzb = r3;
        this.zzc = null;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        throw null;
    }

    public final String toString() {
        int r2 = System.identityHashCode(this);
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 4);
        r1.append("Node");
        r1.append(r2);
        return r1.toString();
    }
}
