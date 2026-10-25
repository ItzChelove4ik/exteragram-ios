package com.google.android.recaptcha.internal;

import com.google.android.gms.tasks.Task;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
public abstract class zzic {
    public static final Deferred zza(Task r3) {
        CompletableDeferred r0 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        r3.addOnCompleteListener(zzia.zza, new zzib(r0));
        return r0;
    }
}
