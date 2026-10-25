.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/AutoCloseable;


# instance fields
.field private final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

.field private final zzb:I

.field private final zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;

.field private volatile zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

.field private zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;

.field private zzf:Z


# direct methods
.method public constructor <init>(Ljava/util/concurrent/ExecutorService;IILcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;)V
    .locals 2

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p3, 0x1

    iput-boolean p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzf:Z

    invoke-static {}, Lcom/google/mlkit/common/sdkinternal/MlKitContext;->getInstance()Lcom/google/mlkit/common/sdkinternal/MlKitContext;

    move-result-object p3

    invoke-virtual {p3}, Lcom/google/mlkit/common/sdkinternal/MlKitContext;->getApplicationContext()Landroid/content/Context;

    move-result-object p3

    invoke-static {p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;->zzh(Landroid/content/Context;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;

    move-result-object p3

    const-wide/16 v0, 0xa

    .line 2
    invoke-static {v0, v1}, Lj$/time/Duration;->ofMinutes(J)Lj$/time/Duration;

    move-result-object v0

    invoke-virtual {p3, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;->zzd(Lj$/time/Duration;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;

    if-eqz p1, :cond_0

    .line 3
    invoke-virtual {p3, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;->zza(Ljava/util/concurrent/ExecutorService;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;

    .line 4
    :cond_0
    invoke-virtual {p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;->zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;

    move-result-object p1

    .line 5
    new-instance p3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    invoke-direct {p3, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;)V

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    iput p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzb:I

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;

    .line 6
    invoke-interface {p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;->zza()V

    return-void
.end method

.method private static zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)I
    .locals 0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzf()I

    move-result p0

    return p0

    :cond_0
    const/4 p0, -0x1

    return p0
.end method

.method private final declared-synchronized zzb()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 3

    monitor-enter p0

    .line 1
    :try_start_0
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object v0

    :catchall_0
    move-exception v0

    goto :goto_0

    :cond_0
    :try_start_1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzg()Landroid/content/Context;

    move-result-object v1

    .line 2
    invoke-static {v1}, Lcom/google/mlkit/genai/common/internal/GenAiUtils;->isAiCoreCompatible(Landroid/content/Context;)Z

    move-result v1

    if-nez v1, :cond_1

    .line 3
    new-instance v0, Lcom/google/mlkit/genai/common/GenAiException;

    const/4 v1, 0x0

    const/16 v2, -0x65

    invoke-direct {v0, v1, v2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/Throwable;I)V

    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    monitor-exit p0

    return-object v0

    :cond_1
    :try_start_2
    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzb:I

    const/4 v2, -0x1

    .line 4
    invoke-interface {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zza(II)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v1

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauk;

    invoke-direct {v2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauk;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;)V

    invoke-interface {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object v0

    .line 5
    invoke-static {v1, v2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzg(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzks;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    monitor-exit p0

    return-object v0

    :goto_0
    :try_start_3
    monitor-exit p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    throw v0
.end method

.method private final zzc(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "WrongConstant"
        }
    .end annotation

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauc;

    invoke-direct {v0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauc;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object p0

    const-class p2, Ljava/lang/Throwable;

    invoke-static {p1, p2, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zze(Lcom/google/common/util/concurrent/ListenableFuture;Ljava/lang/Class;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method private final synthetic zzg(Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1

    const/4 v0, 0x0

    .line 1
    :try_start_0
    invoke-virtual {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zze(Ljava/lang/Object;Lcom/google/mlkit/genai/common/StreamingCallback;)Ljava/lang/Object;

    move-result-object p0

    const/4 p1, 0x1

    .line 2
    invoke-interface {p2, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;->zzc(Ljava/lang/Object;Z)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0
    :try_end_0
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    .line 3
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public final close()V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->close()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzf:Z

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;

    .line 2
    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;->zzc()V

    return-void
.end method

.method public abstract zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;
.end method

.method public abstract zze(Ljava/lang/Object;Lcom/google/mlkit/genai/common/StreamingCallback;)Ljava/lang/Object;
.end method

.method public abstract zzf(Ljava/lang/Object;)Ljava/lang/Object;
.end method

.method public final zzi()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 3

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzb()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaum;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaum;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object v2

    .line 2
    invoke-static {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;

    .line 3
    invoke-direct {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzc(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzj()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzg()Landroid/content/Context;

    move-result-object v1

    invoke-static {v1}, Lcom/google/mlkit/genai/common/internal/GenAiUtils;->isAiCoreCompatible(Landroid/content/Context;)Z

    move-result v1

    if-nez v1, :cond_0

    const/4 p0, 0x0

    .line 2
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0

    .line 3
    :cond_0
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzb()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v1

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaub;

    invoke-direct {v2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaub;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;)V

    invoke-interface {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object v0

    .line 4
    invoke-static {v1, v2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;

    .line 5
    invoke-direct {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzc(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzk(Lcom/google/mlkit/genai/common/DownloadCallback;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 2

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzb()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaud;

    invoke-direct {v1, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaud;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Lcom/google/mlkit/genai/common/DownloadCallback;)V

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object p1

    .line 2
    invoke-static {v0, v1, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;

    .line 3
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzc(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzl()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 3

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzo()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatx;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatx;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object v2

    .line 2
    invoke-static {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;

    .line 3
    invoke-direct {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzc(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzm(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 6

    .line 1
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzo()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v2

    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaue;

    invoke-direct {v3, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaue;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Ljava/lang/Object;)V

    iget-object v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object v5

    .line 3
    invoke-static {v2, v3, v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v2

    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauf;

    invoke-direct {v3, p0, v0, v1, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauf;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;JLjava/lang/Object;)V

    invoke-interface {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object v0

    .line 4
    invoke-static {v2, v3, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzg(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzks;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaug;

    invoke-direct {v1, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaug;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Ljava/lang/Object;)V

    .line 5
    invoke-direct {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzc(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzn(Ljava/lang/Object;Lcom/google/mlkit/genai/common/StreamingCallback;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 7

    .line 1
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v3

    .line 2
    new-instance v2, Ljava/util/concurrent/atomic/AtomicLong;

    invoke-direct {v2}, Ljava/util/concurrent/atomic/AtomicLong;-><init>()V

    if-eqz p2, :cond_0

    .line 3
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatw;

    move-object v1, p0

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatw;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Ljava/util/concurrent/atomic/AtomicLong;JLcom/google/mlkit/genai/common/StreamingCallback;)V

    goto :goto_0

    :cond_0
    move-object v1, p0

    const/4 v0, 0x0

    .line 4
    :goto_0
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzo()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    new-instance p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauh;

    invoke-direct {p2, v1, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauh;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Ljava/lang/Object;Lcom/google/mlkit/genai/common/StreamingCallback;)V

    iget-object v6, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object v0

    .line 5
    invoke-static {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaui;

    move-object v5, p1

    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaui;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Ljava/util/concurrent/atomic/AtomicLong;JLjava/lang/Object;)V

    invoke-interface {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object p1

    .line 6
    invoke-static {p0, v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzg(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzks;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauj;

    invoke-direct {p1, v1, v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauj;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Ljava/lang/Object;)V

    .line 7
    invoke-direct {v1, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzc(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzo()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;

    if-eqz v0, :cond_0

    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0

    .line 2
    :cond_0
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzb()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaul;

    invoke-direct {v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaul;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object p0

    .line 3
    invoke-static {v0, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzg(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzks;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzp()Ljava/util/concurrent/ExecutorService;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzq(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 0

    if-eqz p1, :cond_0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    .line 2
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzr(Lcom/google/mlkit/genai/common/DownloadCallback;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatv;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatv;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Lcom/google/mlkit/genai/common/DownloadCallback;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzs(Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzg(Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzt(JLjava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdz;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdz;
    .locals 2

    .line 1
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    sub-long/2addr v0, p1

    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-direct {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;-><init>()V

    sget-object p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;

    .line 2
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    invoke-static {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)I

    move-result p2

    .line 3
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zza(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    iget-boolean p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzf:Z

    .line 4
    invoke-static {p2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzc(Ljava/lang/Boolean;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    .line 5
    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzf(Ljava/lang/Long;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    const/4 p2, 0x0

    iput-boolean p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzf:Z

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;

    .line 6
    invoke-virtual {p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdz;->zza()I

    move-result p2

    .line 7
    invoke-static {v0, v1}, Lj$/time/Duration;->ofMillis(J)Lj$/time/Duration;

    move-result-object v0

    .line 8
    invoke-interface {p0, p1, p3, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;->zzi(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;Ljava/lang/Object;ILj$/time/Duration;)V

    return-object p4
.end method

.method public final synthetic zzu(Ljava/lang/Object;Lcom/google/mlkit/genai/common/GenAiException;)V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)I

    move-result v0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;

    invoke-virtual {p2}, Lcom/google/mlkit/genai/common/GenAiException;->getErrorCode()I

    move-result p2

    .line 2
    invoke-interface {p0, p1, v0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;->zzj(Ljava/lang/Object;II)V

    return-void
.end method

.method public final synthetic zzv(Ljava/util/concurrent/atomic/AtomicLong;JLjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;-><init>()V

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)I

    move-result v1

    .line 2
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zza(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    iget-boolean v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzf:Z

    .line 3
    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzc(Ljava/lang/Boolean;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    .line 4
    invoke-virtual {p1}, Ljava/util/concurrent/atomic/AtomicLong;->get()J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzg(Ljava/lang/Long;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    .line 5
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v1

    sub-long/2addr v1, p2

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzf(Ljava/lang/Long;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzf:Z

    .line 6
    invoke-virtual {p0, p5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzf(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;

    .line 7
    invoke-interface {p0, v0, p4, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;Ljava/lang/Object;Ljava/lang/Object;)V

    return-object p1
.end method

.method public final synthetic zzw(Ljava/lang/Object;Lcom/google/mlkit/genai/common/GenAiException;)V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)I

    move-result v0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;

    invoke-virtual {p2}, Lcom/google/mlkit/genai/common/GenAiException;->getErrorCode()I

    move-result p2

    .line 2
    invoke-interface {p0, p1, v0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;->zzh(Ljava/lang/Object;II)V

    return-void
.end method

.method public final synthetic zzx(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    return-object p1
.end method

.method public final synthetic zzy(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-virtual {p0, v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;

    return-object p1
.end method
