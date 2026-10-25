package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentDiscovery;
import com.google.firebase.components.ComponentRuntime;
import com.google.mlkit.common.internal.MlKitComponentDiscoveryService;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class MlKitContext {
    private static final Object zza = null;
    private static MlKitContext zzb;
    private ComponentRuntime zzc;

    static {
        zza = new Object();
    }

    private MlKitContext() {
    }

    public static MlKitContext getInstance() {
        Object r0 = zza;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (zzb == null) goto L7;
        boolean r1 = true;
    L8:
        Preconditions.checkState(r1, "MlKitContext has not been initialized");     // Catch: Throwable -> L11
        MlKitContext r12 = (MlKitContext) Preconditions.checkNotNull(zzb);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r12;
    L7:
        r1 = false;
        goto L8
    }

    public static MlKitContext zza(Context r2) {
        Object r0 = zza;
        monitor-enter(r0);
        MlKitContext r22 = zzb(r2, TaskExecutors.MAIN_THREAD);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r22;
    L7:
        th = move-exception;
        throw th;
    }

    public static MlKitContext zzb(Context r6, Executor r7) {
        Object r0 = zza;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (zzb != null) goto L7;
        boolean r1 = true;
    L8:
        Preconditions.checkState(r1, "MlKitContext is already initialized");     // Catch: Throwable -> L11
        MlKitContext r12 = new MlKitContext();     // Catch: Throwable -> L11
        zzb = r12;     // Catch: Throwable -> L11
        Context r62 = zzc(r6);     // Catch: Throwable -> L11
        ComponentRuntime r63 = ComponentRuntime.builder(r7).addLazyComponentRegistrars(ComponentDiscovery.forContext(r62, MlKitComponentDiscoveryService.class).discoverLazy()).addComponent(Component.of(r62, Context.class, new Class[0])).addComponent(Component.of(r12, MlKitContext.class, new Class[0])).build();     // Catch: Throwable -> L11
        r12.zzc = r63;     // Catch: Throwable -> L11
        r63.initializeEagerComponents(true);     // Catch: Throwable -> L11
        MlKitContext r64 = zzb;     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r64;
    L7:
        r1 = false;
        goto L8
    }

    private static Context zzc(Context r1) {
        Context r0 = r1.getApplicationContext();
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r1;
    }

    public <T> T get(Class<T> r3) {
        if (zzb != this) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkState(r0, "MlKitContext has been deleted");
        Preconditions.checkNotNull(this.zzc);
        return (T) this.zzc.get(r3);
    L5:
        r0 = false;
        goto L6
    }

    public Context getApplicationContext() {
        return (Context) get(Context.class);
    }
}
