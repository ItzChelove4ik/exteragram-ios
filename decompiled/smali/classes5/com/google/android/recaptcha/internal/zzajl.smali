.class public final Lcom/google/android/recaptcha/internal/zzajl;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzg:Lcom/google/android/recaptcha/internal/zzajl;

.field private static volatile zzh:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:I

.field private zzd:Lcom/google/android/recaptcha/internal/zzage;

.field private zze:Lcom/google/android/recaptcha/internal/zzage;

.field private zzf:Lcom/google/android/recaptcha/internal/zzaiy;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzajl;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzajl;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzajl;->zzg:Lcom/google/android/recaptcha/internal/zzajl;

    const-class v1, Lcom/google/android/recaptcha/internal/zzajl;

    .line 2
    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafv;->zzR()Lcom/google/android/recaptcha/internal/zzage;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzajl;->zzd:Lcom/google/android/recaptcha/internal/zzage;

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafv;->zzR()Lcom/google/android/recaptcha/internal/zzage;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzajl;->zze:Lcom/google/android/recaptcha/internal/zzage;

    return-void
.end method

.method public static zzc()Lcom/google/android/recaptcha/internal/zzajk;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzajl;->zzg:Lcom/google/android/recaptcha/internal/zzajl;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzF()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzajk;

    return-object v0
.end method

.method public static synthetic zzg()Lcom/google/android/recaptcha/internal/zzajl;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzajl;->zzg:Lcom/google/android/recaptcha/internal/zzajl;

    return-object v0
.end method


# virtual methods
.method public final zza()I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzajl;->zzd:Lcom/google/android/recaptcha/internal/zzage;

    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result p0

    return p0
.end method

.method public final zzb()I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzajl;->zze:Lcom/google/android/recaptcha/internal/zzage;

    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result p0

    return p0
.end method

.method public final synthetic zzd(Lcom/google/android/recaptcha/internal/zzajj;)V
    .locals 2

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzajl;->zzd:Lcom/google/android/recaptcha/internal/zzage;

    .line 2
    invoke-interface {v0}, Lcom/google/android/recaptcha/internal/zzage;->zza()Z

    move-result v1

    if-nez v1, :cond_0

    .line 3
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzS(Lcom/google/android/recaptcha/internal/zzage;)Lcom/google/android/recaptcha/internal/zzage;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzajl;->zzd:Lcom/google/android/recaptcha/internal/zzage;

    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzajl;->zzd:Lcom/google/android/recaptcha/internal/zzage;

    .line 4
    invoke-interface {p0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public final synthetic zze(Lcom/google/android/recaptcha/internal/zzajt;)V
    .locals 2

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzajl;->zze:Lcom/google/android/recaptcha/internal/zzage;

    .line 2
    invoke-interface {v0}, Lcom/google/android/recaptcha/internal/zzage;->zza()Z

    move-result v1

    if-nez v1, :cond_0

    .line 3
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzS(Lcom/google/android/recaptcha/internal/zzage;)Lcom/google/android/recaptcha/internal/zzage;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzajl;->zze:Lcom/google/android/recaptcha/internal/zzage;

    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzajl;->zze:Lcom/google/android/recaptcha/internal/zzage;

    .line 4
    invoke-interface {p0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void
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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzajl;->zzh:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzajl;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzajl;->zzh:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzajl;->zzg:Lcom/google/android/recaptcha/internal/zzajl;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzajl;->zzh:Lcom/google/android/recaptcha/internal/zzahe;

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzajl;->zzg:Lcom/google/android/recaptcha/internal/zzajl;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzajk;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzajk;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzajl;

    .line 6
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzajl;-><init>()V

    return-object p0

    .line 1
    :cond_6
    const-string/jumbo v0, "zza"

    const-string/jumbo v1, "zzd"

    const-class v2, Lcom/google/android/recaptcha/internal/zzajj;

    const-string/jumbo v3, "zze"

    const-class v4, Lcom/google/android/recaptcha/internal/zzajt;

    const-string/jumbo v5, "zzf"

    filled-new-array/range {v0 .. v5}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzajl;->zzg:Lcom/google/android/recaptcha/internal/zzajl;

    const-string p2, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\u001b\u0002\u001b\u0003\u1009\u0000"

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
