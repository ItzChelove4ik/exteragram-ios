package com.google.mlkit.common.sdkinternal;

import com.google.mlkit.common.sdkinternal.Cleaner;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Set;

/* loaded from: classes5.dex */
final class zzd extends PhantomReference implements Cleaner.Cleanable {
    private final Set zza;
    private final Runnable zzb;

    public /* synthetic */ zzd(Object r1, ReferenceQueue r2, Set r3, Runnable r4, zzc r5) {
        super(r1, r2);
        this.zza = r3;
        this.zzb = r4;
    }

    @Override // com.google.mlkit.common.sdkinternal.Cleaner.Cleanable
    public final void clean() {
        if (this.zza.remove(this) == true) goto L5;
        return;
    L5:
        clear();
        this.zzb.run();
    }
}
