package com.google.android.gms.internal.mlkit_genai_prompt;

import java.io.Serializable;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
abstract class zzmb implements Comparable, Serializable {
    final Comparable zza;

    public zzmb(Comparable r1) {
        this.zza = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public final boolean equals(Object r3) {
        if ((r3 instanceof zzmb) == true) goto L11;
    L9:
        return false;
    L11:
        if (zzc((zzmb) r3) != 0) goto L9;
        return true;
    }

    public abstract int hashCode();

    public abstract void zza(StringBuilder r1);

    public abstract void zzb(StringBuilder r1);

    public abstract int zzc(zzmb r1);
}
