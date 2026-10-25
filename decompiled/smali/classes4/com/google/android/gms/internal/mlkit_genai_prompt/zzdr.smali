.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
.source "SourceFile"


# instance fields
.field private zza:Landroid/content/Context;

.field private zzb:Ljava/util/concurrent/ExecutorService;

.field private zzc:Ljava/util/concurrent/Executor;

.field private zzd:Z

.field private zze:Lj$/time/Duration;

.field private zzf:Ljava/util/concurrent/ScheduledExecutorService;

.field private zzg:Ljava/util/Optional;

.field private zzh:B


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;-><init>()V

    invoke-static {}, Ljava/util/Optional;->empty()Ljava/util/Optional;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzg:Ljava/util/Optional;

    return-void
.end method


# virtual methods
.method public final zza(Ljava/util/concurrent/ExecutorService;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
    .locals 0

    if-eqz p1, :cond_0

    .line 1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzb:Ljava/util/concurrent/ExecutorService;

    return-object p0

    :cond_0
    const-string p0, "Null workerExecutor"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzb(Ljava/util/concurrent/Executor;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
    .locals 0

    if-eqz p1, :cond_0

    .line 1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzc:Ljava/util/concurrent/Executor;

    return-object p0

    :cond_0
    const-string p0, "Null callbackExecutor"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzc(Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
    .locals 0

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzd:Z

    iput-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzh:B

    return-object p0
.end method

.method public final zzd(Lj$/time/Duration;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
    .locals 0

    if-eqz p1, :cond_0

    .line 1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zze:Lj$/time/Duration;

    return-object p0

    :cond_0
    const-string p0, "Null autoUnbindTimeoutMs"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zze(Ljava/util/concurrent/ScheduledExecutorService;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
    .locals 0

    if-eqz p1, :cond_0

    .line 1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzf:Ljava/util/concurrent/ScheduledExecutorService;

    return-object p0

    :cond_0
    const-string p0, "Null autoUnbindScheduledExecutor"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzf(Ljava/util/Optional;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
    .locals 0

    if-eqz p1, :cond_0

    .line 1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzg:Ljava/util/Optional;

    return-object p0

    :cond_0
    const-string p0, "Null autoDownloadEnabled"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzg()Ljava/util/Optional;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzb:Ljava/util/concurrent/ExecutorService;

    if-nez p0, :cond_0

    invoke-static {}, Ljava/util/Optional;->empty()Ljava/util/Optional;

    move-result-object p0

    return-object p0

    .line 2
    :cond_0
    invoke-static {p0}, Ljava/util/Optional;->of(Ljava/lang/Object;)Ljava/util/Optional;

    move-result-object p0

    return-object p0
.end method

.method public final zzh()Ljava/util/Optional;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzf:Ljava/util/concurrent/ScheduledExecutorService;

    if-nez p0, :cond_0

    invoke-static {}, Ljava/util/Optional;->empty()Ljava/util/Optional;

    move-result-object p0

    return-object p0

    .line 2
    :cond_0
    invoke-static {p0}, Ljava/util/Optional;->of(Ljava/lang/Object;)Ljava/util/Optional;

    move-result-object p0

    return-object p0
.end method

.method public final zzj()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;
    .locals 11

    .line 1
    iget-byte v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzh:B

    const/4 v1, 0x1

    if-ne v0, v1, :cond_1

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zza:Landroid/content/Context;

    if-eqz v3, :cond_1

    iget-object v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzb:Ljava/util/concurrent/ExecutorService;

    if-eqz v4, :cond_1

    iget-object v5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzc:Ljava/util/concurrent/Executor;

    if-eqz v5, :cond_1

    iget-object v7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zze:Lj$/time/Duration;

    if-eqz v7, :cond_1

    iget-object v8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzf:Ljava/util/concurrent/ScheduledExecutorService;

    if-nez v8, :cond_0

    goto :goto_0

    .line 8
    :cond_0
    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzds;

    iget-boolean v6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzd:Z

    iget-object v9, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzg:Ljava/util/Optional;

    const/4 v10, 0x0

    invoke-direct/range {v2 .. v10}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzds;-><init>(Landroid/content/Context;Ljava/util/concurrent/ExecutorService;Ljava/util/concurrent/Executor;ZLj$/time/Duration;Ljava/util/concurrent/ScheduledExecutorService;Ljava/util/Optional;[B)V

    return-object v2

    .line 1
    :cond_1
    :goto_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zza:Landroid/content/Context;

    if-nez v1, :cond_2

    const-string v1, " context"

    .line 2
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_2
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzb:Ljava/util/concurrent/ExecutorService;

    if-nez v1, :cond_3

    const-string v1, " workerExecutor"

    .line 3
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_3
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzc:Ljava/util/concurrent/Executor;

    if-nez v1, :cond_4

    const-string v1, " callbackExecutor"

    .line 4
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_4
    iget-byte v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzh:B

    if-nez v1, :cond_5

    const-string v1, " bindImportantEnabled"

    .line 5
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_5
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zze:Lj$/time/Duration;

    if-nez v1, :cond_6

    const-string v1, " autoUnbindTimeoutMs"

    .line 6
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_6
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzf:Ljava/util/concurrent/ScheduledExecutorService;

    if-nez p0, :cond_7

    const-string p0, " autoUnbindScheduledExecutor"

    .line 7
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_7
    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v0, "Missing required properties:"

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 8
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzk(Landroid/content/Context;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
    .locals 0

    if-eqz p1, :cond_0

    .line 1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zza:Landroid/content/Context;

    return-object p0

    :cond_0
    const-string p0, "Null context"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method
