.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/content/ServiceConnection;


# instance fields
.field protected final zza:Ljava/lang/Object;

.field protected zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

.field protected zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

.field final synthetic zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance p1, Ljava/lang/Object;

    invoke-direct {p1}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zza:Ljava/lang/Object;

    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    .line 2
    invoke-direct {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    .line 3
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zza()V

    return-void
.end method


# virtual methods
.method public final onBindingDied(Landroid/content/ComponentName;)V
    .locals 2

    .line 1
    const-string p1, "AiCore service binding died."

    const/4 v0, 0x0

    const/16 v1, 0x25b

    invoke-static {v1, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p1

    .line 2
    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;)V

    return-void
.end method

.method public final onNullBinding(Landroid/content/ComponentName;)V
    .locals 2

    .line 1
    const-string p1, "AiCore service returns null on binding."

    const/4 v0, 0x0

    const/16 v1, 0x25d

    invoke-static {v1, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p1

    .line 2
    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;)V

    return-void
.end method

.method public final onServiceDisconnected(Landroid/content/ComponentName;)V
    .locals 4

    .line 1
    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zza:Ljava/lang/Object;

    monitor-enter p1

    :try_start_0
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;->zzb()V

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    const-string v1, "AiCore service disconnected."

    const/16 v2, 0x25a

    const/4 v3, 0x0

    .line 2
    invoke-static {v2, v1, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object v1

    .line 3
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;->zzi(Ljava/lang/Throwable;)Z

    .line 4
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zza()V

    .line 5
    monitor-exit p1

    return-void

    :catchall_0
    move-exception p0

    monitor-exit p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public final zza()V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zza:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    move-result-object v1

    iput-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    .line 2
    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;-><init>()V

    iput-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    .line 3
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public abstract zzb()V
.end method

.method public final zzc()V
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zza:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;->isDone()Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    .line 2
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;->isCancelled()Z

    move-result v1

    if-nez v1, :cond_0

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzo()Z

    move-result v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-nez v2, :cond_0

    :try_start_1
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    .line 3
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzi(Ljava/util/concurrent/Future;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;

    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;

    move-result-object v2

    invoke-interface {v2}, Landroid/os/IInterface;->asBinder()Landroid/os/IBinder;

    move-result-object v2

    const/4 v3, 0x0

    .line 4
    invoke-interface {v2, v1, v3}, Landroid/os/IBinder;->unlinkToDeath(Landroid/os/IBinder$DeathRecipient;I)Z
    :try_end_1
    .catch Ljava/util/NoSuchElementException; {:try_start_1 .. :try_end_1} :catch_2
    .catch Ljava/util/concurrent/ExecutionException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_2

    :catchall_0
    move-exception p0

    goto :goto_3

    :catch_0
    move-exception v1

    goto :goto_0

    :catch_1
    move-exception v1

    goto :goto_0

    :catch_2
    move-exception v1

    goto :goto_1

    .line 6
    :goto_0
    :try_start_2
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzp()Ljava/lang/String;

    move-result-object v2

    const-string v3, "Failed to get service for unbind, unable to call unlinkToDeath. Cause: "

    .line 5
    invoke-static {v2, v3, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_2

    .line 10
    :goto_1
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzp()Ljava/lang/String;

    move-result-object v2

    const-string v3, "Attempted to unlink a death recipient that was not registered. This is safe to ignore."

    .line 6
    invoke-static {v2, v3, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_2

    .line 7
    :cond_0
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzp()Ljava/lang/String;

    move-result-object v1

    const-string v2, "Service context future was cancelled, no need to unlinkToDeath."

    invoke-static {v1, v2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 4
    :cond_1
    :goto_2
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    iget-object v1, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zza:Landroid/content/Context;

    .line 8
    invoke-virtual {v1, p0}, Landroid/content/Context;->unbindService(Landroid/content/ServiceConnection;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    .line 9
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;->zzb()V

    .line 10
    monitor-exit v0

    return-void

    :goto_3
    monitor-exit v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p0
.end method

.method public final zzd(Landroid/content/Intent;)Z
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzs()Z

    move-result v1

    const/4 v2, 0x1

    if-eq v2, v1, :cond_0

    goto :goto_0

    :cond_0
    const/16 v2, 0x41

    :goto_0
    iget-object v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zza:Landroid/content/Context;

    invoke-virtual {v0, p1, p0, v2}, Landroid/content/Context;->bindService(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z

    move-result p0

    return p0
.end method

.method public final zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;)V
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zza:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    invoke-static {p1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;->zzh(Ljava/lang/Object;)Z

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    .line 2
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzo()Z

    move-result v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-nez v2, :cond_0

    .line 3
    :try_start_1
    invoke-interface {p1}, Landroid/os/IInterface;->asBinder()Landroid/os/IBinder;

    move-result-object p1

    const/4 v2, 0x0

    invoke-interface {p1, v1, v2}, Landroid/os/IBinder;->linkToDeath(Landroid/os/IBinder$DeathRecipient;I)V
    :try_end_1
    .catch Landroid/os/RemoteException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :catch_0
    move-exception p1

    .line 6
    :try_start_2
    const-string v1, "AICore service died before linking death recipient."

    const/4 v2, 0x6

    .line 4
    invoke-static {v2, v1, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p1

    .line 5
    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;)V

    .line 6
    :cond_0
    :goto_0
    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p0
.end method

.method public final zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;)V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zza:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    invoke-virtual {v1, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;->zzi(Ljava/lang/Throwable;)Z

    .line 2
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    .line 3
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzj()V

    return-void

    :catchall_0
    move-exception p0

    .line 2
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method
