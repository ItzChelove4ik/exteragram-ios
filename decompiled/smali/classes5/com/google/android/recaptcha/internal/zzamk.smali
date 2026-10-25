.class public final Lcom/google/android/recaptcha/internal/zzamk;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zze:Lcom/google/android/recaptcha/internal/zzamk;

.field private static volatile zzf:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:I

.field private zzd:Ljava/lang/Object;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzamk;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzamk;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzamk;->zze:Lcom/google/android/recaptcha/internal/zzamk;

    const-class v1, Lcom/google/android/recaptcha/internal/zzamk;

    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    const/4 v0, 0x0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzamk;->zza:I

    return-void
.end method

.method public static zzc([B)Lcom/google/android/recaptcha/internal/zzamk;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzamk;->zze:Lcom/google/android/recaptcha/internal/zzamk;

    invoke-static {v0, p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzV(Lcom/google/android/recaptcha/internal/zzafv;[B)Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzamk;

    return-object p0
.end method

.method public static zzd()Lcom/google/android/recaptcha/internal/zzamj;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzamk;->zze:Lcom/google/android/recaptcha/internal/zzamk;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzF()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzamj;

    return-object v0
.end method

.method public static synthetic zzg()Lcom/google/android/recaptcha/internal/zzamk;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzamk;->zze:Lcom/google/android/recaptcha/internal/zzamk;

    return-object v0
.end method


# virtual methods
.method public final zza()Lcom/google/android/recaptcha/internal/zzajj;
    .locals 2

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzamk;->zza:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzamk;->zzd:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/recaptcha/internal/zzajj;

    return-object p0

    .line 2
    :cond_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzajj;->zze()Lcom/google/android/recaptcha/internal/zzajj;

    move-result-object p0

    return-object p0
.end method

.method public final zzb()Lcom/google/android/recaptcha/internal/zzajt;
    .locals 2

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzamk;->zza:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzamk;->zzd:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/recaptcha/internal/zzajt;

    return-object p0

    .line 2
    :cond_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzajt;->zza()Lcom/google/android/recaptcha/internal/zzajt;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zze(Lcom/google/android/recaptcha/internal/zzajj;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzamk;->zzd:Ljava/lang/Object;

    const/4 p1, 0x1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzamk;->zza:I

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzamk;->zzf:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzamk;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzamk;->zzf:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzamk;->zze:Lcom/google/android/recaptcha/internal/zzamk;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzamk;->zzf:Lcom/google/android/recaptcha/internal/zzahe;

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzamk;->zze:Lcom/google/android/recaptcha/internal/zzamk;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzamj;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzamj;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzamk;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzamk;-><init>()V

    return-object p0

    .line 1
    :cond_6
    const-string/jumbo p0, "zzd"

    const-string/jumbo p1, "zza"

    const-class p2, Lcom/google/android/recaptcha/internal/zzajj;

    const-class p3, Lcom/google/android/recaptcha/internal/zzajt;

    filled-new-array {p0, p1, p2, p3}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzamk;->zze:Lcom/google/android/recaptcha/internal/zzamk;

    const-string p2, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000"

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

.method public final zzh()I
    .locals 1

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzamk;->zza:I

    if-eqz p0, :cond_1

    const/4 v0, 0x1

    if-eq p0, v0, :cond_0

    const/4 v0, 0x2

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    return v0

    :cond_1
    const/4 p0, 0x3

    return p0
.end method
