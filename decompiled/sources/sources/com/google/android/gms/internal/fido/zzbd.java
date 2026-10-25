package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public final class zzbd {
    private final String zza;

    private zzbd(String r1) {
        this.zza = ",\n  ";
    }

    public static zzbd zza(String r1) {
        return new zzbd(",\n  ");
    }

    public static /* bridge */ /* synthetic */ String zzb(zzbd r0) {
        return r0.zza;
    }

    public static final CharSequence zzd(Object r1) {
        Objects.requireNonNull(r1);
        if ((r1 instanceof CharSequence) == false) goto L7;
        return (CharSequence) r1;
    L7:
        return r1.toString();
    }

    public final StringBuilder zzc(StringBuilder r2, Iterator r3) {
    L10:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    L3:
        if (r3.hasNext() == false) goto L9;
        r2.append(zzd(r3.next()));     // Catch: IOException -> L10
    L5:
        if (r3.hasNext() == false) goto L9;
        r2.append(this.zza);     // Catch: IOException -> L10
        r2.append(zzd(r3.next()));     // Catch: IOException -> L10
    L9:
        return r2;
    }
}
