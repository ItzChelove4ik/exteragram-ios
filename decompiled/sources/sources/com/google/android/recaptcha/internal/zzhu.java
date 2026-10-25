package com.google.android.recaptcha.internal;

import java.util.concurrent.Executors;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;

/* loaded from: classes5.dex */
public final class zzhu {
    private final CoroutineScope zza;
    private final CoroutineScope zzb;
    private final CoroutineScope zzc;
    private final CoroutineScope zzd;
    private final CoroutineScope zze;
    private final CoroutineScope zzf;

    public zzhu() {
        this.zza = CoroutineScopeKt.MainScope();
        this.zzb = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(Executors.newSingleThreadExecutor(new zzhw("r1", 8))));
        this.zzc = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(Executors.newCachedThreadPool(new zzhw("rIo", null))));
        this.zzd = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(Executors.newSingleThreadExecutor(new zzhw("r2", null))));
        this.zze = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(Executors.newSingleThreadExecutor(new zzhw("r3", 8))));
        this.zzf = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(Executors.newSingleThreadExecutor(new zzhw("rM", null))));
    }

    public final CoroutineScope zza() {
        return this.zza;
    }

    public final CoroutineScope zzb() {
        return this.zzb;
    }

    public final CoroutineScope zzc() {
        return this.zzc;
    }

    public final CoroutineScope zzd() {
        return this.zzd;
    }

    public final CoroutineScope zze() {
        return this.zze;
    }

    public final CoroutineScope zzf() {
        return this.zzf;
    }
}
