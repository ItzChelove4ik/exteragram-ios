package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzku {
    private final String zza;

    private zzku(String r1) {
        this.zza = r1;
    }

    public static zzku zza(String r1) {
        return new zzku(r1);
    }

    public static final CharSequence zzd(Object r1) {
        Objects.requireNonNull(r1);
        if ((r1 instanceof CharSequence) == false) goto L7;
        return (CharSequence) r1;
    L7:
        return r1.toString();
    }

    public final Appendable zzb(Appendable r2, Iterator r3) {
        if (r3.hasNext() == false) goto L8;
        r2.append(zzd(r3.next()));
    L6:
        if (r3.hasNext() == false) goto L8;
        r2.append(this.zza);
        r2.append(zzd(r3.next()));
    L8:
        return r2;
    }

    public final /* synthetic */ String zzc() {
        return this.zza;
    }
}
