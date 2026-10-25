package com.google.mlkit.common.sdkinternal;

import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class Cleaner {
    private final ReferenceQueue zza;
    private final Set zzb;

    /* loaded from: classes5.dex */
    public interface Cleanable {
        void clean();
    }

    private Cleaner() {
        this.zza = new ReferenceQueue();
        this.zzb = Collections.synchronizedSet(new HashSet());
    }

    public static Cleaner create() {
        Cleaner r0 = new Cleaner();
        r0.register(r0, new zza());
        Thread r1 = new Thread(new zzb(r0.zza, r0.zzb), "MlKitCleaner");
        r1.setDaemon(true);
        r1.start();
        return r0;
    }

    public Cleanable register(Object r7, Runnable r8) {
        zzd r0 = new zzd(r7, this.zza, this.zzb, r8, null);
        this.zzb.add(r0);
        return r0;
    }
}
