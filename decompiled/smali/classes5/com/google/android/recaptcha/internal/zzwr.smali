.class public final Lcom/google/android/recaptcha/internal/zzwr;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzf:Lcom/google/android/recaptcha/internal/zzwr;

.field private static volatile zzg:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:Ljava/lang/String;

.field private zzd:Lcom/google/android/recaptcha/internal/zzaer;

.field private zze:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzwr;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzwr;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzwr;->zzf:Lcom/google/android/recaptcha/internal/zzwr;

    const-class v1, Lcom/google/android/recaptcha/internal/zzwr;

    .line 2
    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    const-string v0, ""

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzwr;->zza:Ljava/lang/String;

    .line 2
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzwr;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public static zzc()Lcom/google/android/recaptcha/internal/zzwq;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzwr;->zzf:Lcom/google/android/recaptcha/internal/zzwr;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzF()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzwq;

    return-object v0
.end method

.method public static zzd()Lcom/google/android/recaptcha/internal/zzwr;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzwr;->zzf:Lcom/google/android/recaptcha/internal/zzwr;

    return-object v0
.end method

.method public static synthetic zzh()Lcom/google/android/recaptcha/internal/zzwr;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzwr;->zzf:Lcom/google/android/recaptcha/internal/zzwr;

    return-object v0
.end method


# virtual methods
.method public final zza()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzwr;->zza:Ljava/lang/String;

    return-object p0
.end method

.method public final zzb()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzwr;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final synthetic zze(Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzwr;->zza:Ljava/lang/String;

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzwr;->zzg:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzwr;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzwr;->zzg:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzwr;->zzf:Lcom/google/android/recaptcha/internal/zzwr;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzwr;->zzg:Lcom/google/android/recaptcha/internal/zzahe;

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzwr;->zzf:Lcom/google/android/recaptcha/internal/zzwr;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzwq;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzwq;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzwr;

    .line 6
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzwr;-><init>()V

    return-object p0

    .line 1
    :cond_6
    const-string/jumbo p0, "zza"

    const-string/jumbo p1, "zzd"

    const-string/jumbo p2, "zze"

    filled-new-array {p0, p1, p2}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzwr;->zzf:Lcom/google/android/recaptcha/internal/zzwr;

    const-string p2, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0208\u0002\n\u0003\u000c"

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

.method public final synthetic zzg(Lcom/google/android/recaptcha/internal/zzaer;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzwr;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public final zzi()I
    .locals 4

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzwr;->zze:I

    const/4 v0, 0x2

    const/4 v1, 0x1

    if-eqz p0, :cond_4

    const/4 v2, 0x3

    if-eq p0, v1, :cond_3

    const/4 v3, 0x4

    if-eq p0, v0, :cond_2

    if-eq p0, v2, :cond_1

    if-eq p0, v3, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_0
    const/4 v0, 0x6

    goto :goto_0

    :cond_1
    const/4 v0, 0x5

    goto :goto_0

    :cond_2
    move v0, v3

    goto :goto_0

    :cond_3
    move v0, v2

    :cond_4
    :goto_0
    if-nez v0, :cond_5

    return v1

    :cond_5
    return v0
.end method

.method public final synthetic zzj(I)V
    .locals 0

    add-int/lit8 p1, p1, -0x2

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzwr;->zze:I

    return-void
.end method
