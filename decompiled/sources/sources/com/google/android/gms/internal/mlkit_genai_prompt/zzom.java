package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
public abstract class zzom extends zzou {
    public zzom() {
    }

    public static zzom zzv(ListenableFuture r1) {
        if ((r1 instanceof zzom) == false) goto L7;
        return (zzom) r1;
    L7:
        return new zzon(r1);
    }
}
