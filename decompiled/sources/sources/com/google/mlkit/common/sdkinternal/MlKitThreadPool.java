package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzav;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class MlKitThreadPool extends zzav {
    private static final ThreadLocal zza = null;
    private final ThreadPoolExecutor zzb;

    static {
        zza = new ThreadLocal();
    }

    public MlKitThreadPool() {
        ThreadFactory r0 = Executors.defaultThreadFactory();
        int r3 = Runtime.getRuntime().availableProcessors();
        LinkedBlockingQueue r8 = new LinkedBlockingQueue();
        zzj r9 = new zzj(r0);
        ThreadPoolExecutor r2 = new ThreadPoolExecutor(r3, r3, 60, TimeUnit.SECONDS, r8, r9);
        this.zzb = r2;
        r2.allowCoreThreadTimeOut(true);
    }

    public static /* synthetic */ void zzc(Runnable r1) {
        zze((Deque) zza.get(), r1);
    }

    public static /* synthetic */ void zzd(Runnable r2) {
        zza.set(new ArrayDeque());
        r2.run();
    }

    private static void zze(Deque r2, Runnable r3) {
        Preconditions.checkNotNull(r2);
        r2.add(r3);
        if (r2.size() > 1) goto L6;
    L4:
        r3.run();
        r2.removeFirst();
        r3 = (Runnable) r2.peekFirst();
        if (r3 != null) goto L4;
        return;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r4) {
        Deque r0 = (Deque) zza.get();
        if (r0 != null) goto L5;
    L8:
        this.zzb.execute(new zzi(r4));
        return;
    L5:
        if (r0.size() > 1) goto L8;
        zze(r0, r4);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzx
    public final /* synthetic */ Object zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzav
    public final ExecutorService zzb() {
        return this.zzb;
    }
}
