package com.google.mlkit.genai.prompt.java;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.genai.prompt.CachedContext;
import com.google.mlkit.genai.prompt.Caches;
import com.google.mlkit.genai.prompt.CreateCachedContextRequest;
import com.google.mlkit.genai.prompt.GenerativeModel;
import java.util.List;
import java.util.concurrent.ExecutorService;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;

/* loaded from: classes5.dex */
public final class zzm extends CachesFutures {
    private final GenerativeModel zza;
    private final Caches zzb;
    private final CoroutineScope zzc;
    private final ExecutorService zzd;

    public zzm(GenerativeModel r2) {
        this.zza = r2;
        this.zzb = r2.getCaches();
        this.zzc = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(r2.zzf()));
        this.zzd = r2.zzf();
    }

    public static final /* synthetic */ Caches zza(zzm r0) {
        return r0.zzb;
    }

    public static /* synthetic */ Object zzb(zzm r6, CreateCachedContextRequest r7, CallbackToFutureAdapter.Completer r8) {
        zzi r3 = new zzi(r8, r6, r7, null);
        r8.addCancellationListener(new zzd(BuildersKt.launch$default(r6.zzc, null, null, r3, 3, null)), r6.zzd);
        return "create";
    }

    public static /* synthetic */ Object zzc(zzm r6, CallbackToFutureAdapter.Completer r7) {
        zzl r3 = new zzl(r7, r6, null);
        r7.addCancellationListener(new zze(BuildersKt.launch$default(r6.zzc, null, null, r3, 3, null)), r6.zzd);
        return "list";
    }

    public static /* synthetic */ Object zze(zzm r6, String r7, CallbackToFutureAdapter.Completer r8) {
        zzj r3 = new zzj(r8, r6, r7, null);
        r8.addCancellationListener(new zzg(BuildersKt.launch$default(r6.zzc, null, null, r3, 3, null)), r6.zzd);
        return "delete";
    }

    @Override // com.google.mlkit.genai.prompt.java.CachesFutures
    public final ListenableFuture<CachedContext> create(CreateCachedContextRequest r2) {
        return CallbackToFutureAdapter.getFuture(new zzh(this, r2));
    }

    @Override // com.google.mlkit.genai.prompt.java.CachesFutures
    public final ListenableFuture<Boolean> delete(String r2) {
        return CallbackToFutureAdapter.getFuture(new zzc(this, r2));
    }

    @Override // com.google.mlkit.genai.prompt.java.CachesFutures
    public final ListenableFuture<List<CachedContext>> list() {
        return CallbackToFutureAdapter.getFuture(new zza(this));
    }
}
