.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzc;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zza;
.source "SourceFile"


# direct methods
.method public synthetic constructor <init>([B)V
    .locals 0

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zza;-><init>([B)V

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;Ljava/lang/Thread;)V
    .locals 0

    iput-object p2, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;->thread:Ljava/lang/Thread;

    return-void
.end method

.method public final zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;)V
    .locals 0

    iput-object p2, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;->next:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;

    return-void
.end method

.method public final zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;)Z
    .locals 0

    .line 1
    monitor-enter p1

    :try_start_0
    iget-object p0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;->waitersField:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;

    if-ne p0, p2, :cond_0

    iput-object p3, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;->waitersField:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;

    monitor-exit p1

    const/4 p0, 0x1

    return p0

    :catchall_0
    move-exception p0

    goto :goto_0

    .line 2
    :cond_0
    monitor-exit p1

    const/4 p0, 0x0

    return p0

    .line 3
    :goto_0
    monitor-exit p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public final zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;)Z
    .locals 0

    .line 1
    monitor-enter p1

    :try_start_0
    iget-object p0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;->listenersField:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;

    if-ne p0, p2, :cond_0

    iput-object p3, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;->listenersField:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;

    monitor-exit p1

    const/4 p0, 0x1

    return p0

    :catchall_0
    move-exception p0

    goto :goto_0

    .line 2
    :cond_0
    monitor-exit p1

    const/4 p0, 0x0

    return p0

    .line 3
    :goto_0
    monitor-exit p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public final zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;
    .locals 0

    .line 1
    monitor-enter p1

    :try_start_0
    iget-object p0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;->waitersField:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;

    if-eq p0, p2, :cond_0

    iput-object p2, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;->waitersField:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_0
    :goto_0
    monitor-exit p1

    return-object p0

    .line 2
    :goto_1
    monitor-exit p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public final zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;
    .locals 0

    .line 1
    monitor-enter p1

    :try_start_0
    iget-object p0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;->listenersField:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;

    if-eq p0, p2, :cond_0

    iput-object p2, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;->listenersField:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_0
    :goto_0
    monitor-exit p1

    return-object p0

    .line 2
    :goto_1
    monitor-exit p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public final zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;Ljava/lang/Object;Ljava/lang/Object;)Z
    .locals 0

    .line 1
    monitor-enter p1

    .line 2
    :try_start_0
    iget-object p0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;->valueField:Ljava/lang/Object;

    if-ne p0, p2, :cond_0

    .line 3
    iput-object p3, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;->valueField:Ljava/lang/Object;

    .line 4
    monitor-exit p1

    const/4 p0, 0x1

    return p0

    :catchall_0
    move-exception p0

    goto :goto_0

    .line 5
    :cond_0
    monitor-exit p1

    const/4 p0, 0x0

    return p0

    .line 6
    :goto_0
    monitor-exit p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method
