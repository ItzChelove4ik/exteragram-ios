.class public final Lcom/google/android/recaptcha/internal/zzamf;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzq:Lcom/google/android/recaptcha/internal/zzamf;

.field private static volatile zzr:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:I

.field private zzd:Ljava/lang/String;

.field private zze:Ljava/lang/String;

.field private zzf:Ljava/lang/String;

.field private zzg:I

.field private zzh:Ljava/lang/String;

.field private zzi:Ljava/lang/String;

.field private zzj:Ljava/lang/String;

.field private zzk:Z

.field private zzl:Z

.field private zzm:Ljava/lang/String;

.field private zzn:Ljava/lang/String;

.field private zzo:Ljava/lang/String;

.field private zzp:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzamf;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzamf;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzamf;->zzq:Lcom/google/android/recaptcha/internal/zzamf;

    const-class v1, Lcom/google/android/recaptcha/internal/zzamf;

    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    const-string v0, ""

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzd:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzamf;->zze:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzf:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzh:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzi:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzj:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzm:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzn:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzo:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzp:Ljava/lang/String;

    return-void
.end method

.method public static zza()Lcom/google/android/recaptcha/internal/zzame;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzamf;->zzq:Lcom/google/android/recaptcha/internal/zzamf;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzF()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzame;

    return-object v0
.end method

.method public static synthetic zzn()Lcom/google/android/recaptcha/internal/zzamf;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzamf;->zzq:Lcom/google/android/recaptcha/internal/zzamf;

    return-object v0
.end method


# virtual methods
.method public final synthetic zzb(Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzd:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzc(Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzamf;->zze:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzd(Ljava/lang/String;)V
    .locals 0

    .line 1
    const-string p1, "18.9.2"

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzf:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zze(Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzh:Ljava/lang/String;

    return-void
.end method

.method public final zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 14

    add-int/lit8 p0, p1, -0x1

    if-eqz p0, :cond_7

    const/4 v0, 0x2

    if-eq p0, v0, :cond_6

    const/4 v0, 0x3

    if-eq p0, v0, :cond_5

    const/4 v0, 0x4

    const/4 v1, 0x0

    if-eq p0, v0, :cond_4

    const/4 v0, 0x5

    if-eq p0, v0, :cond_3

    const/4 v0, 0x6

    if-ne p0, v0, :cond_2

    .line 2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzamf;->zzr:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class v1, Lcom/google/android/recaptcha/internal/zzamf;

    monitor-enter v1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzamf;->zzr:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzamf;->zzq:Lcom/google/android/recaptcha/internal/zzamf;

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzamf;->zzr:Lcom/google/android/recaptcha/internal/zzahe;

    goto :goto_0

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_1

    .line 3
    :cond_0
    :goto_0
    monitor-exit v1

    return-object p0

    :goto_1
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0

    :cond_1
    return-object p0

    .line 6
    :cond_2
    throw v1

    .line 1
    :cond_3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzamf;->zzq:Lcom/google/android/recaptcha/internal/zzamf;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzame;

    .line 5
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzame;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzamf;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzamf;-><init>()V

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

    const-string/jumbo v8, "zzk"

    const-string/jumbo v9, "zzl"

    const-string/jumbo v10, "zzm"

    const-string/jumbo v11, "zzn"

    const-string/jumbo v12, "zzo"

    const-string/jumbo v13, "zzp"

    filled-new-array/range {v0 .. v13}, [Ljava/lang/Object;

    move-result-object p0

    sget-object v0, Lcom/google/android/recaptcha/internal/zzamf;->zzq:Lcom/google/android/recaptcha/internal/zzamf;

    const-string v1, "\u0000\r\u0000\u0001\u0001\u000e\r\u0000\u0000\u0000\u0001\u0208\u0002\u0208\u0003\u0208\u0004\u000c\u0006\u0208\u0007\u0208\u0008\u0208\t\u0007\n\u0007\u000b\u0208\u000c\u1208\u0000\r\u1208\u0001\u000e\u1208\u0002"

    .line 4
    invoke-static {v0, v1, p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzM(Lcom/google/android/recaptcha/internal/zzagx;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :cond_7
    const/4 p0, 0x1

    .line 1
    invoke-static {p0}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzg(Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzi:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzh(Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzj:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzi(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzk:Z

    return-void
.end method

.method public final synthetic zzj(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzl:Z

    return-void
.end method

.method public final synthetic zzk(Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzm:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzl(Ljava/lang/String;)V
    .locals 1

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzamf;->zza:I

    or-int/lit8 v0, v0, 0x1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzamf;->zza:I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzn:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzo(I)V
    .locals 0

    add-int/lit8 p1, p1, -0x2

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzamf;->zzg:I

    return-void
.end method
