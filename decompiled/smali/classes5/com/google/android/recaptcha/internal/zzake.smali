.class public final Lcom/google/android/recaptcha/internal/zzake;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzd:Lcom/google/android/recaptcha/internal/zzake;

.field private static volatile zze:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:Lcom/google/android/recaptcha/internal/zzaer;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzake;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzake;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzake;->zzd:Lcom/google/android/recaptcha/internal/zzake;

    const-class v1, Lcom/google/android/recaptcha/internal/zzake;

    .line 2
    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    .line 2
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzake;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public static zzb()Lcom/google/android/recaptcha/internal/zzake;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzake;->zzd:Lcom/google/android/recaptcha/internal/zzake;

    return-object v0
.end method

.method public static synthetic zze()Lcom/google/android/recaptcha/internal/zzake;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzake;->zzd:Lcom/google/android/recaptcha/internal/zzake;

    return-object v0
.end method


# virtual methods
.method public final zza()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzake;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final synthetic zzc(Lcom/google/android/recaptcha/internal/zzaer;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzake;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public final synthetic zzd()V
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzake;->zzd:Lcom/google/android/recaptcha/internal/zzake;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzake;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzake;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzake;->zze:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzake;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzake;->zze:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzake;->zzd:Lcom/google/android/recaptcha/internal/zzake;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzake;->zze:Lcom/google/android/recaptcha/internal/zzahe;

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

    .line 7
    :cond_2
    throw p2

    .line 1
    :cond_3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzake;->zzd:Lcom/google/android/recaptcha/internal/zzake;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzakd;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzakd;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzake;

    .line 6
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzake;-><init>()V

    return-object p0

    .line 1
    :cond_6
    const-string/jumbo p0, "zza"

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzake;->zzd:Lcom/google/android/recaptcha/internal/zzake;

    const-string p2, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n"

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
