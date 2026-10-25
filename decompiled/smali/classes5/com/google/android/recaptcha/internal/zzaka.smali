.class public final Lcom/google/android/recaptcha/internal/zzaka;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzi:Lcom/google/android/recaptcha/internal/zzaka;

.field private static volatile zzj:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:I

.field private zzd:Ljava/lang/String;

.field private zze:Lcom/google/android/recaptcha/internal/zzaer;

.field private zzf:Ljava/lang/String;

.field private zzg:Ljava/lang/String;

.field private zzh:Lcom/google/android/recaptcha/internal/zzaer;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzaka;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzaka;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaka;->zzi:Lcom/google/android/recaptcha/internal/zzaka;

    const-class v1, Lcom/google/android/recaptcha/internal/zzaka;

    .line 2
    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 2

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    const-string v0, ""

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zzd:Ljava/lang/String;

    .line 2
    sget-object v1, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzaka;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zzf:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zzg:Ljava/lang/String;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzaka;->zzh:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public static zzg()Lcom/google/android/recaptcha/internal/zzajz;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaka;->zzi:Lcom/google/android/recaptcha/internal/zzaka;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzF()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzajz;

    return-object v0
.end method

.method public static zzh()Lcom/google/android/recaptcha/internal/zzahe;
    .locals 3

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaka;->zzi:Lcom/google/android/recaptcha/internal/zzaka;

    const/4 v1, 0x7

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2, v2}, Lcom/google/android/recaptcha/internal/zzafv;->zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzahe;

    return-object v0
.end method

.method public static synthetic zzo()Lcom/google/android/recaptcha/internal/zzaka;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaka;->zzi:Lcom/google/android/recaptcha/internal/zzaka;

    return-object v0
.end method


# virtual methods
.method public final zza()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zzd:Ljava/lang/String;

    return-object p0
.end method

.method public final zzb()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final zzc()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zzf:Ljava/lang/String;

    return-object p0
.end method

.method public final zzd()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zzg:Ljava/lang/String;

    return-object p0
.end method

.method public final zze()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zzh:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 6

    add-int/lit8 p1, p1, -0x1

    if-eqz p1, :cond_7

    const/4 p0, 0x2

    if-eq p1, p0, :cond_6

    const/4 p0, 0x3

    if-eq p1, p0, :cond_5

    const/4 p0, 0x4

    const/4 p2, 0x0

    if-eq p1, p0, :cond_4

    const/4 p0, 0x5

    if-eq p1, p0, :cond_3

    const/4 p0, 0x6

    if-ne p1, p0, :cond_2

    .line 2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaka;->zzj:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzaka;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaka;->zzj:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzaka;->zzi:Lcom/google/android/recaptcha/internal/zzaka;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzaka;->zzj:Lcom/google/android/recaptcha/internal/zzahe;

    goto :goto_0

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_1

    .line 3
    :cond_0
    :goto_0
    monitor-exit p1

    return-object p0

    :goto_1
    monitor-exit p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0

    :cond_1
    return-object p0

    .line 7
    :cond_2
    throw p2

    .line 1
    :cond_3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaka;->zzi:Lcom/google/android/recaptcha/internal/zzaka;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzajz;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzajz;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzaka;

    .line 6
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaka;-><init>()V

    return-object p0

    .line 1
    :cond_6
    const-string/jumbo v0, "zza"

    const-string/jumbo v1, "zzd"

    const-string/jumbo v2, "zze"

    const-string/jumbo v3, "zzf"

    const-string/jumbo v4, "zzg"

    const-string/jumbo v5, "zzh"

    filled-new-array/range {v0 .. v5}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaka;->zzi:Lcom/google/android/recaptcha/internal/zzaka;

    const-string p2, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u1208\u0000\u0002\u100a\u0001\u0003\u1208\u0002\u0004\u1208\u0003\u0005\u100a\u0004"

    .line 4
    invoke-static {p1, p2, p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzM(Lcom/google/android/recaptcha/internal/zzagx;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :cond_7
    const/4 p0, 0x1

    .line 1
    invoke-static {p0}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzi(Ljava/lang/String;)V
    .locals 1

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zza:I

    or-int/lit8 v0, v0, 0x1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zza:I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaka;->zzd:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzj(Lcom/google/android/recaptcha/internal/zzaer;)V
    .locals 1

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zza:I

    or-int/lit8 v0, v0, 0x2

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zza:I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaka;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public final synthetic zzk(Ljava/lang/String;)V
    .locals 1

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zza:I

    or-int/lit8 v0, v0, 0x4

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zza:I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaka;->zzf:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzl(Ljava/lang/String;)V
    .locals 1

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zza:I

    or-int/lit8 v0, v0, 0x8

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zza:I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaka;->zzg:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzn(Lcom/google/android/recaptcha/internal/zzaer;)V
    .locals 1

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zza:I

    or-int/lit8 v0, v0, 0x10

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaka;->zza:I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaka;->zzh:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method
