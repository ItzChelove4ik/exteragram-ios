.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/IBinder$DeathRecipient;
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;


# static fields
.field public static final synthetic $r8$clinit:I = 0x0

.field private static final zzc:Ljava/lang/String; = "zzgl"

.field private static final zzd:Landroid/content/ComponentName;

.field private static final zze:Landroid/content/ComponentName;


# instance fields
.field protected final zza:Landroid/content/Context;

.field private final zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

.field private final zzg:Ljava/util/concurrent/Executor;

.field private final zzh:Z

.field private final zzi:J

.field private final zzj:Ljava/util/concurrent/ScheduledExecutorService;

.field private final zzk:Landroid/os/UserManager;

.field private final zzl:Z

.field private final zzm:Ljava/lang/Object;

.field private zzn:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;

.field private zzo:Ljava/util/concurrent/ScheduledFuture;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 1
    new-instance v0, Landroid/content/ComponentName;

    const-string v1, "com.google.android.aicore"

    const-string v2, "com.google.android.apps.aicore.service.multiuser.AiCoreMultiUserService"

    .line 2
    invoke-direct {v0, v1, v2}, Landroid/content/ComponentName;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzd:Landroid/content/ComponentName;

    new-instance v1, Landroid/content/ComponentName;

    .line 3
    invoke-virtual {v0}, Landroid/content/ComponentName;->getPackageName()Ljava/lang/String;

    move-result-object v0

    const-string v2, "com.google.android.apps.aicore.service.AiCoreService"

    invoke-direct {v1, v0, v2}, Landroid/content/ComponentName;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    sput-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zze:Landroid/content/ComponentName;

    return-void
.end method

.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;)V
    .locals 3

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzm:Ljava/lang/Object;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;->zza()Landroid/content/Context;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zza:Landroid/content/Context;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;->zzb()Ljava/util/concurrent/ExecutorService;

    move-result-object v1

    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpi;->zzb(Ljava/util/concurrent/ExecutorService;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    move-result-object v1

    iput-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;->zzc()Ljava/util/concurrent/Executor;

    move-result-object v1

    iput-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzg:Ljava/util/concurrent/Executor;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;->zzd()Z

    move-result v1

    iput-boolean v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzh:Z

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;->zze()Lj$/time/Duration;

    move-result-object v1

    .line 2
    invoke-virtual {v1}, Lj$/time/Duration;->toMillis()J

    move-result-wide v1

    iput-wide v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzi:J

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;->zzf()Ljava/util/concurrent/ScheduledExecutorService;

    move-result-object v1

    iput-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzj:Ljava/util/concurrent/ScheduledExecutorService;

    const-string v1, "user"

    .line 3
    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/os/UserManager;

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzk:Landroid/os/UserManager;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;->zzg()Ljava/util/Optional;

    move-result-object p1

    .line 4
    sget-object v0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    invoke-virtual {p1, v0}, Ljava/util/Optional;->orElse(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    iput-boolean p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzl:Z

    return-void
.end method

.method public static synthetic zzk(IILcom/google/android/gms/internal/mlkit_genai_prompt/zzan;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1

    const/4 p1, 0x6

    .line 1
    :try_start_0
    invoke-interface {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;->zzg()I

    move-result v0

    if-ge v0, p1, :cond_0

    const-string p0, "getFeatureOrControl is not supported before AICoreVersion V7"

    const/16 p2, 0x8

    const/4 v0, 0x0

    .line 2
    invoke-static {p2, p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzc(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 3
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0

    :catch_0
    move-exception p0

    goto :goto_0

    :cond_0
    const/4 v0, -0x1

    .line 4
    invoke-interface {p2, p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;->zzd(II)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;

    move-result-object p1
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    if-nez p1, :cond_1

    sget-object p1, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    .line 8
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    const-string p2, "Feature %d is not available."

    invoke-static {p1, p2, p0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    .line 9
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzf(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 10
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0

    .line 11
    :cond_1
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzi(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0

    :goto_0
    sget-object p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzc:Ljava/lang/String;

    .line 5
    const-string v0, "AiCore service failed to get feature."

    invoke-static {p2, v0, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 6
    invoke-static {p1, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzc(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 7
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zzl(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 3

    .line 1
    :try_start_0
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzj()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;

    move-result-object v0

    invoke-interface {p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;)I

    move-result p1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzc:Ljava/lang/String;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zza()Ljava/lang/String;

    move-result-object v1

    const-string v2, "AiCore service failed to get feature status for "

    invoke-virtual {v2, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 2
    invoke-static {v0, v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zza()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v2, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    const/4 v0, 0x6

    .line 3
    invoke-static {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzc(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 4
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zzp()Ljava/lang/String;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzc:Ljava/lang/String;

    return-object v0
.end method

.method public static synthetic zzq()Landroid/content/ComponentName;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zze:Landroid/content/ComponentName;

    return-object v0
.end method

.method public static final zzt()Landroid/content/ComponentName;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzd:Landroid/content/ComponentName;

    return-object v0
.end method

.method private final zzu()V
    .locals 6

    .line 1
    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzi:J

    const-wide/16 v2, 0x0

    cmp-long v2, v0, v2

    if-gtz v2, :cond_0

    return-void

    :cond_0
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzm:Ljava/lang/Object;

    monitor-enter v2

    :try_start_0
    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzo:Ljava/util/concurrent/ScheduledFuture;

    if-eqz v3, :cond_1

    const/4 v4, 0x0

    invoke-interface {v3, v4}, Ljava/util/concurrent/Future;->cancel(Z)Z

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_1
    :goto_0
    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzj:Ljava/util/concurrent/ScheduledExecutorService;

    new-instance v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgf;

    invoke-direct {v4, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgf;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;)V

    sget-object v5, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    .line 2
    invoke-interface {v3, v4, v0, v1, v5}, Ljava/util/concurrent/ScheduledExecutorService;->schedule(Ljava/lang/Runnable;JLjava/util/concurrent/TimeUnit;)Ljava/util/concurrent/ScheduledFuture;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzo:Ljava/util/concurrent/ScheduledFuture;

    .line 3
    monitor-exit v2

    return-void

    :goto_1
    monitor-exit v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method


# virtual methods
.method public final binderDied()V
    .locals 0

    .line 0
    return-void
.end method

.method public final binderDied(Landroid/os/IBinder;)V
    .locals 2

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzc:Ljava/lang/String;

    invoke-static {p1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    const-string v1, "Binder died for component: "

    invoke-virtual {v1, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzj()V

    return-void
.end method

.method public final close()V
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzj()V

    return-void
.end method

.method public final zza(II)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzi()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p2

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgi;

    const/4 v1, -0x1

    invoke-direct {v0, p1, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgi;-><init>(II)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 2
    invoke-static {p2, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzi()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgd;

    invoke-direct {v1, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgd;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 2
    invoke-static {v0, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzd()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzge;

    invoke-direct {v1, p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzge;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 2
    invoke-static {v0, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzf(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzd()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzk:Landroid/os/UserManager;

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Landroid/os/UserManager;->isUserUnlocked()Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzm:Ljava/lang/Object;

    monitor-enter v0

    .line 2
    :try_start_0
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzu()V

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzn:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;

    if-eqz v2, :cond_0

    .line 3
    monitor-exit v0

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_3

    .line 10
    :cond_0
    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgk;

    .line 4
    invoke-direct {v2, p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgk;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;[B)V

    iput-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzn:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;

    .line 5
    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzb()V

    .line 6
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 3
    :goto_0
    iget-object p0, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zza:Ljava/lang/Object;

    monitor-enter p0

    :try_start_1
    iget-object v0, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    .line 8
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;->isCancelled()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 9
    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zza()V

    goto :goto_1

    :catchall_1
    move-exception v0

    goto :goto_2

    :cond_1
    :goto_1
    iget-object v0, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    .line 10
    monitor-exit p0

    return-object v0

    .line 11
    :goto_2
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    throw v0

    .line 7
    :goto_3
    :try_start_2
    monitor-exit v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p0

    :cond_2
    const/16 p0, 0x259

    .line 6
    const-string v0, "User is not unlocked."

    .line 12
    invoke-static {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 13
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    return-object p0
.end method

.method public final zzf()Ljava/util/concurrent/Executor;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzg:Ljava/util/concurrent/Executor;

    return-object p0
.end method

.method public final zzg()Landroid/content/Context;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zza:Landroid/content/Context;

    return-object p0
.end method

.method public final zzh()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzl:Z

    return p0
.end method

.method public final zzi()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 2

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzu()V

    .line 2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzd()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;->zzv(Lcom/google/common/util/concurrent/ListenableFuture;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    move-result-object p0

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgc;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgc;

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpi;->zza()Ljava/util/concurrent/Executor;

    move-result-object v1

    .line 3
    invoke-static {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzg(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzks;Ljava/util/concurrent/Executor;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    return-object p0
.end method

.method public final zzj()V
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzm:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzn:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzc()V

    iput-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzn:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_0
    :goto_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzo:Ljava/util/concurrent/ScheduledFuture;

    if-eqz v1, :cond_1

    const/4 v3, 0x0

    .line 2
    invoke-interface {v1, v3}, Ljava/util/concurrent/Future;->cancel(Z)Z

    iput-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzo:Ljava/util/concurrent/ScheduledFuture;

    .line 3
    :cond_1
    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public final synthetic zzm()V
    .locals 2

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzc:Ljava/lang/String;

    const-string v1, "Cleaning up due to autoUnbind."

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzj()V

    return-void
.end method

.method public final synthetic zzn(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 2

    .line 1
    :try_start_0
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;

    move-result-object p1

    .line 2
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzj()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;

    invoke-direct {v1, p0, p3, p2, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)V

    .line 3
    invoke-interface {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbe;)I
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    :catch_0
    move-exception p0

    goto :goto_0

    :catch_1
    move-exception p0

    goto :goto_1

    .line 13
    :goto_0
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzc:Ljava/lang/String;

    .line 4
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zza()Ljava/lang/String;

    move-result-object p3

    const-string v0, "AiCore service failed to download feature due to runtime error "

    invoke-virtual {v0, p3}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    .line 5
    invoke-static {p1, p3, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 6
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zza()Ljava/lang/String;

    move-result-object p1

    const-string p2, "AICore service failed to download feature due to runtime error "

    invoke-virtual {p2, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    const/4 p2, 0x0

    .line 7
    invoke-static {p2, p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zza(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 8
    invoke-virtual {p4, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    goto :goto_2

    .line 3
    :goto_1
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzc:Ljava/lang/String;

    .line 9
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zza()Ljava/lang/String;

    move-result-object p3

    const-string v0, "AiCore service failed to download feature "

    invoke-virtual {v0, p3}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    .line 10
    invoke-static {p1, p3, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 11
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zza()Ljava/lang/String;

    move-result-object p1

    const-string p2, "AICore service failed to download feature "

    invoke-virtual {p2, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    const/4 p2, 0x6

    .line 12
    invoke-static {p2, p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zza(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    .line 13
    invoke-virtual {p4, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    .line 3
    :goto_2
    const-string p0, "requestDownloadableFeatureFuture"

    return-object p0
.end method

.method public final synthetic zzo()Z
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzk:Landroid/os/UserManager;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroid/os/UserManager;->isSystemUser()Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final synthetic zzr()Ljava/util/concurrent/Executor;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzg:Ljava/util/concurrent/Executor;

    return-object p0
.end method

.method public final synthetic zzs()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzh:Z

    return p0
.end method
