.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpi;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static zza()Ljava/util/concurrent/Executor;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzok;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzok;

    return-object v0
.end method

.method public static zzb(Ljava/util/concurrent/ExecutorService;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;
    .locals 1

    .line 1
    instance-of v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    if-eqz v0, :cond_0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    return-object p0

    .line 2
    :cond_0
    instance-of v0, p0, Ljava/util/concurrent/ScheduledExecutorService;

    if-eqz v0, :cond_1

    .line 3
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzph;

    check-cast p0, Ljava/util/concurrent/ScheduledExecutorService;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzph;-><init>(Ljava/util/concurrent/ScheduledExecutorService;)V

    return-object v0

    :cond_1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpd;

    .line 4
    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpd;-><init>(Ljava/util/concurrent/ExecutorService;)V

    return-object v0
.end method

.method public static zzc(Ljava/util/concurrent/Executor;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;)Ljava/util/concurrent/Executor;
    .locals 1

    .line 1
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzok;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzok;

    if-ne p0, v0, :cond_0

    return-object p0

    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpe;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpe;-><init>(Ljava/util/concurrent/Executor;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;)V

    return-object v0
.end method

.method public static synthetic zzd(Ljava/util/concurrent/Executor;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;Ljava/lang/Runnable;)V
    .locals 0

    invoke-static {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpi;->zze(Ljava/util/concurrent/Executor;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;Ljava/lang/Runnable;)V

    return-void
.end method

.method private static synthetic zze(Ljava/util/concurrent/Executor;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;Ljava/lang/Runnable;)V
    .locals 0

    .line 1
    :try_start_0
    invoke-interface {p0, p2}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V
    :try_end_0
    .catch Ljava/util/concurrent/RejectedExecutionException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 2
    invoke-virtual {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;->zzi(Ljava/lang/Throwable;)Z

    return-void
.end method
