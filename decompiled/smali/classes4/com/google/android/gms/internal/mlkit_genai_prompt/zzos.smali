.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzos;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field final zza:Lcom/google/common/util/concurrent/ListenableFuture;

.field final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;


# direct methods
.method public constructor <init>(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzos;->zza:Lcom/google/common/util/concurrent/ListenableFuture;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzos;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzos;->zza:Lcom/google/common/util/concurrent/ListenableFuture;

    instance-of v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpn;

    if-eqz v1, :cond_1

    move-object v1, v0

    check-cast v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpn;

    .line 2
    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpo;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpn;)Ljava/lang/Throwable;

    move-result-object v1

    if-nez v1, :cond_0

    goto :goto_0

    .line 6
    :cond_0
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzos;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;

    .line 7
    invoke-interface {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;->zza(Ljava/lang/Throwable;)V

    return-void

    .line 3
    :cond_1
    :goto_0
    :try_start_0
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzi(Ljava/util/concurrent/Future;)Ljava/lang/Object;

    move-result-object v0
    :try_end_0
    .catch Ljava/util/concurrent/ExecutionException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzos;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;

    .line 6
    invoke-interface {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;->zzb(Ljava/lang/Object;)V

    return-void

    :catchall_0
    move-exception v0

    .line 5
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzos;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;

    .line 4
    invoke-interface {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;->zza(Ljava/lang/Throwable;)V

    return-void

    :catch_0
    move-exception v0

    .line 7
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzos;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;

    .line 5
    invoke-virtual {v0}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object v0

    invoke-interface {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;->zza(Ljava/lang/Throwable;)V

    return-void
.end method

.method public final toString()Ljava/lang/String;
    .locals 1

    .line 1
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzky;->zza(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;

    move-result-object v0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzos;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;

    invoke-virtual {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzc(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
