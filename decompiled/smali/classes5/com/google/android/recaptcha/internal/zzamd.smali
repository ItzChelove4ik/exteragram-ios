.class public final Lcom/google/android/recaptcha/internal/zzamd;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zze:Lcom/google/android/recaptcha/internal/zzamd;

.field private static volatile zzf:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:I

.field private zzd:Ljava/lang/Object;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzamd;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzamd;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzamd;->zze:Lcom/google/android/recaptcha/internal/zzamd;

    const-class v1, Lcom/google/android/recaptcha/internal/zzamd;

    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    const/4 v0, 0x0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzamd;->zza:I

    return-void
.end method

.method public static synthetic zza()Lcom/google/android/recaptcha/internal/zzamd;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzamd;->zze:Lcom/google/android/recaptcha/internal/zzamd;

    return-object v0
.end method


# virtual methods
.method public final zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzamd;->zzf:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzamd;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzamd;->zzf:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzamd;->zze:Lcom/google/android/recaptcha/internal/zzamd;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzamd;->zzf:Lcom/google/android/recaptcha/internal/zzahe;

    goto :goto_0

    :catchall_0
    move-exception p0

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

    .line 6
    :cond_2
    throw p2

    .line 1
    :cond_3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzamd;->zze:Lcom/google/android/recaptcha/internal/zzamd;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzamc;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzamc;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzamd;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzamd;-><init>()V

    return-object p0

    .line 1
    :cond_6
    const-string/jumbo p0, "zzd"

    const-string/jumbo p1, "zza"

    filled-new-array {p0, p1}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzamd;->zze:Lcom/google/android/recaptcha/internal/zzamd;

    const-string p2, "\u0000*\u0001\u0000\u0001**\u0000\u0000\u0000\u0001?\u0000\u0002?\u0000\u0003?\u0000\u0004?\u0000\u0005?\u0000\u0006?\u0000\u0007?\u0000\u0008?\u0000\t?\u0000\n?\u0000\u000b?\u0000\u000c?\u0000\r?\u0000\u000e?\u0000\u000f?\u0000\u0010?\u0000\u0011?\u0000\u0012?\u0000\u0013?\u0000\u0014?\u0000\u0015?\u0000\u0016?\u0000\u0017?\u0000\u0018?\u0000\u0019?\u0000\u001a?\u0000\u001b?\u0000\u001c?\u0000\u001d?\u0000\u001e?\u0000\u001f?\u0000 ?\u0000!?\u0000\"?\u0000#?\u0000$?\u0000%?\u0000&?\u0000\'?\u0000(?\u0000)?\u0000*?\u0000"

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
