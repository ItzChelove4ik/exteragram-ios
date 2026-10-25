.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public abstract zza(Ljava/util/concurrent/ExecutorService;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
.end method

.method public abstract zzb(Ljava/util/concurrent/Executor;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
.end method

.method public abstract zzc(Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
.end method

.method public abstract zzd(Lj$/time/Duration;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
.end method

.method public abstract zze(Ljava/util/concurrent/ScheduledExecutorService;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
.end method

.method public abstract zzf(Ljava/util/Optional;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
.end method

.method public abstract zzg()Ljava/util/Optional;
.end method

.method public abstract zzh()Ljava/util/Optional;
.end method

.method public final zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;->zzg()Ljava/util/Optional;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/Optional;->isPresent()Z

    move-result v0

    if-nez v0, :cond_0

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdm;

    const-string v1, "AiCoreClientWorker"

    .line 2
    invoke-direct {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdm;-><init>(Ljava/lang/String;)V

    .line 3
    invoke-static {v0}, Ljava/util/concurrent/Executors;->newCachedThreadPool(Ljava/util/concurrent/ThreadFactory;)Ljava/util/concurrent/ExecutorService;

    move-result-object v0

    .line 4
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;->zza(Ljava/util/concurrent/ExecutorService;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;

    .line 5
    :cond_0
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;->zzh()Ljava/util/Optional;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/Optional;->isPresent()Z

    move-result v0

    if-nez v0, :cond_1

    .line 6
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdj;->zza()Ljava/util/concurrent/ScheduledExecutorService;

    move-result-object v0

    .line 7
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;->zze(Ljava/util/concurrent/ScheduledExecutorService;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;

    .line 8
    :cond_1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;->zzj()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;

    move-result-object p0

    return-object p0
.end method

.method public abstract zzj()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;
.end method
