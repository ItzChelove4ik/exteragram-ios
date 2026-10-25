.class public final Lcom/google/android/recaptcha/internal/zzajt;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzm:Lcom/google/android/recaptcha/internal/zzajt;

.field private static volatile zzn:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:I

.field private zzd:I

.field private zze:I

.field private zzf:Ljava/lang/String;

.field private zzg:Ljava/lang/String;

.field private zzh:Lcom/google/android/recaptcha/internal/zzafe;

.field private zzi:Lcom/google/android/recaptcha/internal/zzahx;

.field private zzj:I

.field private zzk:Lcom/google/android/recaptcha/internal/zzajg;

.field private zzl:Lcom/google/android/recaptcha/internal/zzage;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzajt;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzajt;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzajt;->zzm:Lcom/google/android/recaptcha/internal/zzajt;

    const-class v1, Lcom/google/android/recaptcha/internal/zzajt;

    .line 2
    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    const-string v0, ""

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzajt;->zzf:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzajt;->zzg:Ljava/lang/String;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafv;->zzR()Lcom/google/android/recaptcha/internal/zzage;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzajt;->zzl:Lcom/google/android/recaptcha/internal/zzage;

    return-void
.end method

.method public static zza()Lcom/google/android/recaptcha/internal/zzajt;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzajt;->zzm:Lcom/google/android/recaptcha/internal/zzajt;

    return-object v0
.end method

.method public static synthetic zzb()Lcom/google/android/recaptcha/internal/zzajt;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzajt;->zzm:Lcom/google/android/recaptcha/internal/zzajt;

    return-object v0
.end method


# virtual methods
.method public final zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 11

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzajt;->zzn:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzajt;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzajt;->zzn:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzajt;->zzm:Lcom/google/android/recaptcha/internal/zzajt;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzajt;->zzn:Lcom/google/android/recaptcha/internal/zzahe;

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzajt;->zzm:Lcom/google/android/recaptcha/internal/zzajt;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzajs;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzajs;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzajt;

    .line 6
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzajt;-><init>()V

    return-object p0

    .line 1
    :cond_6
    const-string/jumbo v0, "zza"

    const-string/jumbo v1, "zzd"

    const-string/jumbo v2, "zzh"

    const-string/jumbo v3, "zzi"

    const-string/jumbo v4, "zzj"

    const-string/jumbo v5, "zzl"

    const-class v6, Lcom/google/android/recaptcha/internal/zzajn;

    const-string/jumbo v7, "zze"

    const-string/jumbo v8, "zzf"

    const-string/jumbo v9, "zzg"

    const-string/jumbo v10, "zzk"

    filled-new-array/range {v0 .. v10}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzajt;->zzm:Lcom/google/android/recaptcha/internal/zzajt;

    const-string p2, "\u0000\t\u0000\u0001\u0001\u000b\t\u0000\u0001\u0000\u0001\u0004\u0003\u1009\u0000\u0004\u1009\u0001\u0005\u000c\u0007\u001b\u0008\u000c\t\u0208\n\u0208\u000b\u1009\u0002"

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
