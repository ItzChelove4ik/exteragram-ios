.class public final Lcom/google/android/recaptcha/internal/zzwn;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zze:Lcom/google/android/recaptcha/internal/zzwn;

.field private static volatile zzf:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:I

.field private zzd:Lcom/google/android/recaptcha/internal/zzaer;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzwn;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzwn;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzwn;->zze:Lcom/google/android/recaptcha/internal/zzwn;

    const-class v1, Lcom/google/android/recaptcha/internal/zzwn;

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

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzwn;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public static zzc(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzwn;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzwn;->zze:Lcom/google/android/recaptcha/internal/zzwn;

    invoke-static {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzafv;->zzU(Lcom/google/android/recaptcha/internal/zzafv;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzwn;

    return-object p0
.end method

.method public static zzd()Lcom/google/android/recaptcha/internal/zzwm;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzwn;->zze:Lcom/google/android/recaptcha/internal/zzwn;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzF()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzwm;

    return-object v0
.end method

.method public static zze()Lcom/google/android/recaptcha/internal/zzwn;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzwn;->zze:Lcom/google/android/recaptcha/internal/zzwn;

    return-object v0
.end method

.method public static zzg()Lcom/google/android/recaptcha/internal/zzahe;
    .locals 3

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzwn;->zze:Lcom/google/android/recaptcha/internal/zzwn;

    const/4 v1, 0x7

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2, v2}, Lcom/google/android/recaptcha/internal/zzafv;->zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzahe;

    return-object v0
.end method

.method public static synthetic zzi()Lcom/google/android/recaptcha/internal/zzwn;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzwn;->zze:Lcom/google/android/recaptcha/internal/zzwn;

    return-object v0
.end method


# virtual methods
.method public final zza()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzwn;->zza:I

    return p0
.end method

.method public final zzb()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzwn;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzwn;->zzf:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzwn;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzwn;->zzf:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzwn;->zze:Lcom/google/android/recaptcha/internal/zzwn;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzwn;->zzf:Lcom/google/android/recaptcha/internal/zzahe;

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzwn;->zze:Lcom/google/android/recaptcha/internal/zzwn;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzwm;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzwm;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzwn;

    .line 6
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzwn;-><init>()V

    return-object p0

    .line 1
    :cond_6
    const-string/jumbo p0, "zza"

    const-string/jumbo p1, "zzd"

    filled-new-array {p0, p1}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzwn;->zze:Lcom/google/android/recaptcha/internal/zzwn;

    const-string p2, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n"

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

.method public final synthetic zzh(Lcom/google/android/recaptcha/internal/zzaer;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzwn;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method
