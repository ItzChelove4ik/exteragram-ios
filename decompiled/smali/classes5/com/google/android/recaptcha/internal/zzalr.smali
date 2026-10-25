.class public final Lcom/google/android/recaptcha/internal/zzalr;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzl:Lcom/google/android/recaptcha/internal/zzalr;

.field private static volatile zzm:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:I

.field private zzd:Ljava/lang/String;

.field private zze:Ljava/lang/String;

.field private zzf:Lcom/google/android/recaptcha/internal/zzaer;

.field private zzg:Lcom/google/android/recaptcha/internal/zzaer;

.field private zzh:Lcom/google/android/recaptcha/internal/zzaer;

.field private zzi:Lcom/google/android/recaptcha/internal/zzagc;

.field private zzj:I

.field private zzk:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzalr;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzalr;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzalr;->zzl:Lcom/google/android/recaptcha/internal/zzalr;

    const-class v1, Lcom/google/android/recaptcha/internal/zzalr;

    .line 2
    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    const-string v0, ""

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzd:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zze:Ljava/lang/String;

    .line 2
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzf:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzg:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzh:Lcom/google/android/recaptcha/internal/zzaer;

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafv;->zzO()Lcom/google/android/recaptcha/internal/zzagc;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzi:Lcom/google/android/recaptcha/internal/zzagc;

    return-void
.end method

.method public static zzi()Lcom/google/android/recaptcha/internal/zzalp;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzalr;->zzl:Lcom/google/android/recaptcha/internal/zzalr;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzF()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzalp;

    return-object v0
.end method

.method public static synthetic zzp()Lcom/google/android/recaptcha/internal/zzalr;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzalr;->zzl:Lcom/google/android/recaptcha/internal/zzalr;

    return-object v0
.end method


# virtual methods
.method public final zza()Z
    .locals 1
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    const/4 v0, 0x1

    and-int/2addr p0, v0

    if-eqz p0, :cond_0

    return v0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final zzb()Ljava/lang/String;
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzd:Ljava/lang/String;

    return-object p0
.end method

.method public final zzc()Z
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    and-int/lit8 p0, p0, 0x2

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final zzd()Ljava/lang/String;
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zze:Ljava/lang/String;

    return-object p0
.end method

.method public final zze()Z
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    and-int/lit8 p0, p0, 0x4

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 10

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzalr;->zzm:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzalr;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzalr;->zzm:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzalr;->zzl:Lcom/google/android/recaptcha/internal/zzalr;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzalr;->zzm:Lcom/google/android/recaptcha/internal/zzahe;

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzalr;->zzl:Lcom/google/android/recaptcha/internal/zzalr;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzalp;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzalp;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzalr;

    .line 6
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzalr;-><init>()V

    return-object p0

    .line 1
    :cond_6
    const-string/jumbo v0, "zza"

    const-string/jumbo v1, "zzd"

    const-string/jumbo v2, "zze"

    const-string/jumbo v3, "zzi"

    const-string/jumbo v4, "zzj"

    sget-object v5, Lcom/google/android/recaptcha/internal/zzalq;->zza:Lcom/google/android/recaptcha/internal/zzaga;

    const-string/jumbo v6, "zzk"

    const-string/jumbo v7, "zzf"

    const-string/jumbo v8, "zzg"

    const-string/jumbo v9, "zzh"

    filled-new-array/range {v0 .. v9}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzalr;->zzl:Lcom/google/android/recaptcha/internal/zzalr;

    const-string p2, "\u0001\u0008\u0000\u0001\u0001\u0008\u0008\u0000\u0001\u0000\u0001\u1008\u0000\u0002\u1008\u0001\u0003\'\u0004\u180c\u0005\u0005\u1004\u0006\u0006\u100a\u0002\u0007\u100a\u0003\u0008\u100a\u0004"

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

.method public final zzg()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzf:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final zzh()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzh:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final synthetic zzj()V
    .locals 1

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    and-int/lit8 v0, v0, -0x2

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    sget-object v0, Lcom/google/android/recaptcha/internal/zzalr;->zzl:Lcom/google/android/recaptcha/internal/zzalr;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzalr;->zzd:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzd:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzk()V
    .locals 1

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    and-int/lit8 v0, v0, -0x3

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    sget-object v0, Lcom/google/android/recaptcha/internal/zzalr;->zzl:Lcom/google/android/recaptcha/internal/zzalr;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzalr;->zze:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zze:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzl(Lcom/google/android/recaptcha/internal/zzaer;)V
    .locals 1

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    or-int/lit8 v0, v0, 0x4

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzf:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public final synthetic zzn(Lcom/google/android/recaptcha/internal/zzaer;)V
    .locals 1

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    or-int/lit8 v0, v0, 0x8

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzg:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public final synthetic zzo(Lcom/google/android/recaptcha/internal/zzaer;)V
    .locals 1

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    or-int/lit8 v0, v0, 0x10

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzh:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public final synthetic zzq(I)V
    .locals 0

    const/4 p1, 0x2

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzalr;->zzj:I

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    or-int/lit8 p1, p1, 0x20

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzalr;->zza:I

    return-void
.end method
