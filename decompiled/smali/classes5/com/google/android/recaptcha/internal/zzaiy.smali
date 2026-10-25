.class public final Lcom/google/android/recaptcha/internal/zzaiy;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzk:Lcom/google/android/recaptcha/internal/zzaiy;

.field private static volatile zzl:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:Lcom/google/android/recaptcha/internal/zzaer;

.field private zzd:Ljava/lang/String;

.field private zze:Lcom/google/android/recaptcha/internal/zzaer;

.field private zzf:Ljava/lang/String;

.field private zzg:Ljava/lang/String;

.field private zzh:Lcom/google/android/recaptcha/internal/zzaer;

.field private zzi:Ljava/lang/String;

.field private zzj:Lcom/google/android/recaptcha/internal/zzaer;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzaiy;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzaiy;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaiy;->zzk:Lcom/google/android/recaptcha/internal/zzaiy;

    const-class v1, Lcom/google/android/recaptcha/internal/zzaiy;

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

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaiy;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    const-string v1, ""

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzaiy;->zzd:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaiy;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzaiy;->zzf:Ljava/lang/String;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzaiy;->zzg:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaiy;->zzh:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzaiy;->zzi:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaiy;->zzj:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public static synthetic zza()Lcom/google/android/recaptcha/internal/zzaiy;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaiy;->zzk:Lcom/google/android/recaptcha/internal/zzaiy;

    return-object v0
.end method


# virtual methods
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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaiy;->zzl:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzaiy;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaiy;->zzl:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzaiy;->zzk:Lcom/google/android/recaptcha/internal/zzaiy;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzaiy;->zzl:Lcom/google/android/recaptcha/internal/zzahe;

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaiy;->zzk:Lcom/google/android/recaptcha/internal/zzaiy;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzaix;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzaix;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzaiy;

    .line 6
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaiy;-><init>()V

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

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaiy;->zzk:Lcom/google/android/recaptcha/internal/zzaiy;

    const-string p2, "\u0000\u0008\u0000\u0000\u0001\u0008\u0008\u0000\u0000\u0000\u0001\n\u0002\u0208\u0003\n\u0004\u0208\u0005\u0208\u0006\n\u0007\u0208\u0008\n"

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
