.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzed;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static final zza(ILjava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzec;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzec;-><init>(I)V

    invoke-static {v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzd(Ljava/util/concurrent/Callable;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method
