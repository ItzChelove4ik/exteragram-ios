.class public final Lcom/google/android/recaptcha/internal/zzakk;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzk:Lcom/google/android/recaptcha/internal/zzakk;

.field private static volatile zzl:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:Lcom/google/android/recaptcha/internal/zzaer;

.field private zzd:Ljava/lang/String;

.field private zze:J

.field private zzf:Lcom/google/android/recaptcha/internal/zzaer;

.field private zzg:Ljava/lang/String;

.field private zzh:Ljava/lang/String;

.field private zzi:Z

.field private zzj:Z


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzakk;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzakk;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzakk;->zzk:Lcom/google/android/recaptcha/internal/zzakk;

    const-class v1, Lcom/google/android/recaptcha/internal/zzakk;

    .line 2
    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 2

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    .line 2
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakk;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    const-string v1, ""

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzd:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzf:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzg:Ljava/lang/String;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzh:Ljava/lang/String;

    return-void
.end method

.method public static zzh()Lcom/google/android/recaptcha/internal/zzakk;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzakk;->zzk:Lcom/google/android/recaptcha/internal/zzakk;

    return-object v0
.end method

.method public static synthetic zzr()Lcom/google/android/recaptcha/internal/zzakk;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzakk;->zzk:Lcom/google/android/recaptcha/internal/zzakk;

    return-object v0
.end method


# virtual methods
.method public final zza()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzakk;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final zzb()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzd:Ljava/lang/String;

    return-object p0
.end method

.method public final zzc()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/recaptcha/internal/zzakk;->zze:J

    return-wide v0
.end method

.method public final zzd()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzf:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final zze()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzg:Ljava/lang/String;

    return-object p0
.end method

.method public final zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 8

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzakk;->zzl:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzakk;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzakk;->zzl:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzakk;->zzk:Lcom/google/android/recaptcha/internal/zzakk;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzakk;->zzl:Lcom/google/android/recaptcha/internal/zzahe;

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzakk;->zzk:Lcom/google/android/recaptcha/internal/zzakk;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzakj;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzakj;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzakk;

    .line 6
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzakk;-><init>()V

    return-object p0

    .line 1
    :cond_6
    const-string/jumbo v0, "zza"

    const-string/jumbo v1, "zzd"

    const-string/jumbo v2, "zze"

    const-string/jumbo v3, "zzf"

    const-string/jumbo v4, "zzg"

    const-string/jumbo v5, "zzh"

    const-string/jumbo v6, "zzi"

    const-string/jumbo v7, "zzj"

    filled-new-array/range {v0 .. v7}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzakk;->zzk:Lcom/google/android/recaptcha/internal/zzakk;

    const-string p2, "\u0004\u0008\u0000\u0000\u0001\u0008\u0008\u0000\u0000\u0000\u0001\n\u0002\u0208\u0003\u0002\u0004\n\u0005\u0208\u0006\u0208\u0007\u0007\u0008\u0007"

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

.method public final zzg()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzh:Ljava/lang/String;

    return-object p0
.end method

.method public final synthetic zzi(Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzd:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzj()V
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzakk;->zzk:Lcom/google/android/recaptcha/internal/zzakk;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzakk;->zzd:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzd:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzk(Lcom/google/android/recaptcha/internal/zzaer;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzf:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public final synthetic zzl()V
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzakk;->zzk:Lcom/google/android/recaptcha/internal/zzakk;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzakk;->zzf:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzf:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public final synthetic zzn(Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzg:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzo()V
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzakk;->zzk:Lcom/google/android/recaptcha/internal/zzakk;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzakk;->zzg:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzg:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzp(Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzh:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzq()V
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzakk;->zzk:Lcom/google/android/recaptcha/internal/zzakk;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzakk;->zzh:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakk;->zzh:Ljava/lang/String;

    return-void
.end method
