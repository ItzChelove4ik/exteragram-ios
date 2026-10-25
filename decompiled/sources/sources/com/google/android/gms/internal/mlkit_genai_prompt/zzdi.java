package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public interface zzdi extends AutoCloseable {
    @Override // java.lang.AutoCloseable
    void close();

    ListenableFuture zza(int r1, int r2);

    ListenableFuture zzb(zzdq r1);

    ListenableFuture zzc(zzdq r1, zzdy r2);

    ListenableFuture zzd();

    zzpc zze();

    Executor zzf();

    Context zzg();

    boolean zzh();
}
