.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;


# static fields
.field public static final synthetic $r8$clinit:I = 0x0

.field private static final zzd:Ljava/lang/String; = "zzfv"


# instance fields
.field protected final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

.field protected final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

.field private final zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

.field private final zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

.field private final zzg:Ljava/util/concurrent/Executor;

.field private final zzh:Ljava/lang/Object;

.field private zzi:Lcom/google/common/util/concurrent/ListenableFuture;

.field private zzj:Lcom/google/common/util/concurrent/ListenableFuture;

.field private zzk:Lcom/google/common/util/concurrent/ListenableFuture;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzh:Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    invoke-interface {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object p2

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    invoke-interface {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzf()Ljava/util/concurrent/Executor;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzg:Ljava/util/concurrent/Executor;

    return-void
.end method

.method private final zzd()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 5

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzh:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzj:Lcom/google/common/util/concurrent/ListenableFuture;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzd()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v3

    if-ne v1, v3, :cond_0

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzk:Lcom/google/common/util/concurrent/ListenableFuture;

    if-eqz v1, :cond_0

    .line 2
    monitor-exit v0

    return-object v1

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    invoke-interface {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzh()Z

    move-result v3

    if-nez v3, :cond_1

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    const-string v3, "Auto-download is disabled, skipping download."

    .line 5
    invoke-static {v1, v3}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 6
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzb()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v1

    goto :goto_0

    .line 12
    :cond_1
    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    .line 3
    invoke-interface {v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v3

    invoke-static {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;->zzv(Lcom/google/common/util/concurrent/ListenableFuture;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    move-result-object v3

    new-instance v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfo;

    invoke-direct {v4, p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfo;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 4
    invoke-static {v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v1

    check-cast v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    .line 7
    :goto_0
    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;->zzv(Lcom/google/common/util/concurrent/ListenableFuture;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    move-result-object v1

    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfm;

    invoke-direct {v3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfm;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;)V

    iget-object v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 8
    invoke-static {v1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v1

    check-cast v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfn;

    invoke-direct {v3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfn;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;)V

    .line 9
    invoke-static {v1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v1

    check-cast v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    iput-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzk:Lcom/google/common/util/concurrent/ListenableFuture;

    .line 10
    invoke-interface {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzd()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v2

    iput-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzj:Lcom/google/common/util/concurrent/ListenableFuture;

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzey;

    .line 11
    invoke-direct {v2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzey;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;)V

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpi;->zza()Ljava/util/concurrent/Executor;

    move-result-object p0

    invoke-static {v1, v2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzh(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;Ljava/util/concurrent/Executor;)V

    .line 12
    monitor-exit v0

    return-object v1

    .line 13
    :goto_1
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public static synthetic zzp(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;)V
    .locals 2

    if-eqz p0, :cond_0

    .line 1
    :try_start_0
    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;->zzd()V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    const-string v1, "Failed to cancel inference"

    .line 2
    invoke-static {v0, v1, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :cond_0
    return-void
.end method

.method public static synthetic zzr(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;)V
    .locals 2

    if-eqz p0, :cond_0

    .line 1
    :try_start_0
    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;->zzd()V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    const-string v1, "Failed to cancel preparation of inference engine"

    .line 2
    invoke-static {v0, v1, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :cond_0
    return-void
.end method

.method public static synthetic zzt(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;)V
    .locals 2

    .line 1
    :try_start_0
    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;->zzd()V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    const-string v1, "Failed to cancel tokenization request"

    .line 2
    invoke-static {v0, v1, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    return-void
.end method


# virtual methods
.method public final zza()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzi()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzez;

    invoke-direct {v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzez;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 2
    invoke-static {v0, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzb(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzi()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfj;

    invoke-direct {v1, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfj;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;Ljava/lang/Object;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 2
    invoke-static {v0, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzc(Ljava/lang/Object;Z)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzi()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p2

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfs;

    const/4 v1, 0x1

    invoke-direct {v0, p0, p1, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfs;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;Ljava/lang/Object;Z)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 2
    invoke-static {p2, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public abstract zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;)Ljava/lang/Object;
.end method

.method public abstract zzf(Ljava/lang/Object;Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;
.end method

.method public abstract zzg(Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbn;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;
.end method

.method public zzh(Ljava/lang/Object;ZLjava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbp;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;
    .locals 0

    .line 1
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    const-string p1, "getTokenInfo() is not supported."

    invoke-static {p0, p1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    const/16 p0, 0x8

    .line 2
    invoke-interface {p4, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbp;->zzc(I)V

    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgv;

    .line 3
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgv;-><init>()V

    return-object p0
.end method

.method public final zzi()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzh:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzj:Lcom/google/common/util/concurrent/ListenableFuture;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzd()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v3

    if-ne v1, v3, :cond_0

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzi:Lcom/google/common/util/concurrent/ListenableFuture;

    if-nez v1, :cond_1

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 2
    :cond_0
    :goto_0
    invoke-interface {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzd()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v1

    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;->zzv(Lcom/google/common/util/concurrent/ListenableFuture;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    move-result-object v1

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfl;

    invoke-direct {v2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfl;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;)V

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 3
    invoke-static {v1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v1

    check-cast v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    iput-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzi:Lcom/google/common/util/concurrent/ListenableFuture;

    .line 4
    :cond_1
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzi:Lcom/google/common/util/concurrent/ListenableFuture;

    .line 5
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;->zzv(Lcom/google/common/util/concurrent/ListenableFuture;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfk;

    invoke-direct {v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfk;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 6
    invoke-static {v0, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    return-object p0

    .line 4
    :goto_1
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method public final synthetic zzj(Ljava/lang/Integer;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 2

    .line 1
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v0

    if-eqz v0, :cond_1

    .line 2
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const v1, 0x7ffffffe

    if-ne v0, v1, :cond_0

    goto :goto_0

    .line 3
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object v0

    .line 4
    invoke-static {p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzed;->zza(ILjava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p1

    .line 5
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;->zzv(Lcom/google/common/util/concurrent/ListenableFuture;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    move-result-object p1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfr;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfr;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 6
    invoke-static {p1, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    return-object p0

    .line 7
    :cond_1
    :goto_0
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzk(Ljava/lang/Void;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzd()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzl(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 2

    .line 1
    const-string v0, "Failed to initialize service."

    :try_start_0
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;

    move-result-object v1

    invoke-virtual {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;)Ljava/lang/Object;

    move-result-object p0
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgm;

    .line 8
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    move-result-object p1

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgm;-><init>(Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;)V

    .line 9
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0

    :catch_0
    move-exception p0

    goto :goto_0

    :catch_1
    move-exception p0

    goto :goto_1

    .line 7
    :goto_0
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    .line 2
    invoke-static {p1, v0, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 p1, 0x0

    .line 3
    invoke-static {p1, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzb(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 4
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    goto :goto_2

    .line 9
    :goto_1
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    .line 5
    invoke-static {p1, v0, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 p1, 0x6

    .line 6
    invoke-static {p1, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzb(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 7
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    :goto_2
    return-object p0
.end method

.method public final synthetic zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Ljava/lang/Integer;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 4

    .line 1
    invoke-virtual {p2}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v1, 0x0

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    const/4 v3, 0x1

    if-eq v0, v3, :cond_1

    const/4 v3, 0x2

    if-eq v0, v3, :cond_1

    const/4 v3, 0x3

    if-eq v0, v3, :cond_0

    .line 9
    invoke-static {p2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    new-instance p1, Ljava/lang/StringBuilder;

    add-int/lit8 p0, p0, 0x1b

    invoke-direct {p1, p0}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string p0, "Unexpected feature status: "

    invoke-static {p2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 10
    invoke-static {v2, p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zza(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 9
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0

    :cond_0
    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zza()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result v1

    new-instance v2, Ljava/lang/StringBuilder;

    add-int/lit8 v1, v1, 0x21

    .line 2
    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "Feature "

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, " is downloaded and ready."

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-static {v0, p2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzg:Ljava/util/concurrent/Executor;

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfq;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfq;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V

    .line 3
    invoke-interface {p2, v0}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    .line 4
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzb()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0

    :cond_1
    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    .line 5
    invoke-interface {p2, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0

    :cond_2
    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzg:Ljava/util/concurrent/Executor;

    const-string v0, "Feature is unavailable."

    .line 6
    invoke-static {v2, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zza(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfp;

    invoke-direct {v1, p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfp;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;)V

    .line 7
    invoke-interface {p2, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    .line 8
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzn(Ljava/lang/Boolean;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1

    .line 1
    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-nez p1, :cond_0

    const-string p0, "Request blocked due to background use."

    const/4 p1, 0x0

    const/16 v0, 0x1e

    .line 2
    invoke-static {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 3
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0

    .line 4
    :cond_0
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzo(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;Ljava/lang/Object;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 2

    .line 1
    const-string v0, "Failed to run inference."

    :try_start_0
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;->zza()Ljava/lang/Object;

    move-result-object p1

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzft;

    invoke-direct {v1, p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzft;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)V

    .line 2
    invoke-virtual {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzf(Ljava/lang/Object;Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;

    move-result-object p1
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    new-instance p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfc;

    invoke-direct {p2, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfc;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 9
    invoke-virtual {p3, p2, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "runInferenceFuture"

    return-object p0

    :catch_0
    move-exception p0

    goto :goto_0

    :catch_1
    move-exception p0

    goto :goto_1

    .line 8
    :goto_0
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    .line 3
    invoke-static {p1, v0, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 p1, 0x0

    .line 4
    invoke-static {p1, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzb(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 5
    invoke-virtual {p3, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    goto :goto_2

    .line 9
    :goto_1
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    .line 6
    invoke-static {p1, v0, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 p1, 0x6

    .line 7
    invoke-static {p1, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzb(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 8
    invoke-virtual {p3, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    :goto_2
    const/4 p0, 0x0

    return-object p0
.end method

.method public final synthetic zzq(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzex;

    invoke-direct {v0, p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzex;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)V

    const-string v1, "Failed to prepare inference engine."

    .line 2
    :try_start_0
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;->zza()Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzg(Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbn;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;

    move-result-object p1
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzff;

    invoke-direct {v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzff;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 9
    invoke-virtual {p2, v0, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "prepareInferenceEngineFuture"

    return-object p0

    :catch_0
    move-exception p0

    goto :goto_0

    :catch_1
    move-exception p0

    goto :goto_1

    .line 8
    :goto_0
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    .line 3
    invoke-static {p1, v1, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 p1, 0x0

    .line 4
    invoke-static {p1, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzc(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 5
    invoke-virtual {p2, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    goto :goto_2

    .line 9
    :goto_1
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    .line 6
    invoke-static {p1, v1, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 p1, 0x6

    .line 7
    invoke-static {p1, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzc(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 8
    invoke-virtual {p2, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    :goto_2
    const/4 p0, 0x0

    return-object p0
.end method

.method public final synthetic zzs(Ljava/lang/Object;ZLcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 2

    .line 1
    new-instance p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzew;

    invoke-direct {p2, p0, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzew;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)V

    const-string v0, "Failed to tokenize the input query."

    .line 2
    :try_start_0
    invoke-virtual {p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;->zza()Ljava/lang/Object;

    move-result-object p3

    const/4 v1, 0x1

    .line 3
    invoke-virtual {p0, p1, v1, p3, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzh(Ljava/lang/Object;ZLjava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbp;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;

    move-result-object p1
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    new-instance p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfi;

    invoke-direct {p2, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfi;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 10
    invoke-virtual {p4, p2, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "getTokenInfoFuture"

    return-object p0

    :catch_0
    move-exception p0

    goto :goto_0

    :catch_1
    move-exception p0

    goto :goto_1

    .line 9
    :goto_0
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    .line 4
    invoke-static {p1, v0, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 p1, 0x0

    .line 5
    invoke-static {p1, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzd(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 6
    invoke-virtual {p4, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    goto :goto_2

    .line 10
    :goto_1
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzd:Ljava/lang/String;

    .line 7
    invoke-static {p1, v0, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 p1, 0x6

    .line 8
    invoke-static {p1, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzd(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 9
    invoke-virtual {p4, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    :goto_2
    const/4 p0, 0x0

    return-object p0
.end method

.method public final synthetic zzu()Ljava/lang/Object;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzh:Ljava/lang/Object;

    return-object p0
.end method

.method public final synthetic zzv(Lcom/google/common/util/concurrent/ListenableFuture;)V
    .locals 0

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzk:Lcom/google/common/util/concurrent/ListenableFuture;

    return-void
.end method
