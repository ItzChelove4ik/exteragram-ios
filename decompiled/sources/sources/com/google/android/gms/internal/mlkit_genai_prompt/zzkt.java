package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class zzkt {
    public static final Appendable zza(Appendable r2, Iterator r3, zzku r4, String r5) {
        if (r3.hasNext() == false) goto L8;
        Map.Entry r0 = (Map.Entry) r3.next();
        r2.append(zzku.zzd(r0.getKey()));
        r2.append(r5);
        r2.append(zzku.zzd(r0.getValue()));
    L6:
        if (r3.hasNext() == false) goto L8;
        r2.append(r4.zzc());
        Map.Entry r02 = (Map.Entry) r3.next();
        r2.append(zzku.zzd(r02.getKey()));
        r2.append(r5);
        r2.append(zzku.zzd(r02.getValue()));
    L8:
        return r2;
    }
}
