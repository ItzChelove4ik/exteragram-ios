.class public final Lcom/google/android/recaptcha/internal/zzakg;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzs:Lcom/google/android/recaptcha/internal/zzakg;

.field private static volatile zzt:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:I

.field private zzd:Ljava/lang/String;

.field private zze:Ljava/lang/String;

.field private zzf:Ljava/lang/String;

.field private zzg:Ljava/lang/String;

.field private zzh:Ljava/lang/String;

.field private zzi:Ljava/lang/String;

.field private zzj:Lcom/google/android/recaptcha/internal/zzagd;

.field private zzk:Lcom/google/android/recaptcha/internal/zzaer;

.field private zzl:Lcom/google/android/recaptcha/internal/zzakk;

.field private zzm:Lcom/google/android/recaptcha/internal/zzake;

.field private zzn:Lcom/google/android/recaptcha/internal/zzako;

.field private zzo:Z

.field private zzp:J

.field private zzq:Z

.field private zzr:Z


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzakg;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzakg;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzakg;->zzs:Lcom/google/android/recaptcha/internal/zzakg;

    const-class v1, Lcom/google/android/recaptcha/internal/zzakg;

    .line 2
    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    const-string v0, ""

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzd:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zze:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzf:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzg:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzh:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzi:Ljava/lang/String;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafv;->zzQ()Lcom/google/android/recaptcha/internal/zzagd;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzj:Lcom/google/android/recaptcha/internal/zzagd;

    .line 3
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzk:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public static zzn()Lcom/google/android/recaptcha/internal/zzakg;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzakg;->zzs:Lcom/google/android/recaptcha/internal/zzakg;

    return-object v0
.end method

.method public static synthetic zzt()Lcom/google/android/recaptcha/internal/zzakg;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzakg;->zzs:Lcom/google/android/recaptcha/internal/zzakg;

    return-object v0
.end method


# virtual methods
.method public final zza()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzf:Ljava/lang/String;

    return-object p0
.end method

.method public final zzb()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzh:Ljava/lang/String;

    return-object p0
.end method

.method public final zzc()Z
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zza:I

    and-int/lit16 p0, p0, 0x80

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final zzd()Lcom/google/android/recaptcha/internal/zzakk;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzl:Lcom/google/android/recaptcha/internal/zzakk;

    if-nez p0, :cond_0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzakk;->zzh()Lcom/google/android/recaptcha/internal/zzakk;

    move-result-object p0

    :cond_0
    return-object p0
.end method

.method public final zze()Z
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zza:I

    and-int/lit16 p0, p0, 0x100

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 18

    add-int/lit8 v0, p1, -0x1

    if-eqz v0, :cond_7

    const/4 v1, 0x2

    if-eq v0, v1, :cond_6

    const/4 v1, 0x3

    if-eq v0, v1, :cond_5

    const/4 v1, 0x4

    const/4 v2, 0x0

    if-eq v0, v1, :cond_4

    const/4 v1, 0x5

    if-eq v0, v1, :cond_3

    const/4 v1, 0x6

    if-ne v0, v1, :cond_2

    .line 2
    sget-object v0, Lcom/google/android/recaptcha/internal/zzakg;->zzt:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez v0, :cond_1

    const-class v1, Lcom/google/android/recaptcha/internal/zzakg;

    monitor-enter v1

    :try_start_0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzakg;->zzt:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez v0, :cond_0

    new-instance v0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzakg;->zzs:Lcom/google/android/recaptcha/internal/zzakg;

    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzakg;->zzt:Lcom/google/android/recaptcha/internal/zzahe;

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 3
    :cond_0
    :goto_0
    monitor-exit v1

    return-object v0

    :goto_1
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    :cond_1
    return-object v0

    .line 7
    :cond_2
    throw v2

    .line 1
    :cond_3
    sget-object v0, Lcom/google/android/recaptcha/internal/zzakg;->zzs:Lcom/google/android/recaptcha/internal/zzakg;

    return-object v0

    .line 4
    :cond_4
    new-instance v0, Lcom/google/android/recaptcha/internal/zzakf;

    .line 5
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzakf;-><init>([B)V

    return-object v0

    :cond_5
    new-instance v0, Lcom/google/android/recaptcha/internal/zzakg;

    .line 6
    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzakg;-><init>()V

    return-object v0

    .line 1
    :cond_6
    const-string/jumbo v2, "zza"

    const-string/jumbo v3, "zzd"

    const-string/jumbo v4, "zze"

    const-string/jumbo v5, "zzf"

    const-string/jumbo v6, "zzg"

    const-string/jumbo v7, "zzh"

    const-string/jumbo v8, "zzi"

    const-string/jumbo v9, "zzj"

    const-string/jumbo v10, "zzk"

    const-string/jumbo v11, "zzl"

    const-string/jumbo v12, "zzm"

    const-string/jumbo v13, "zzn"

    const-string/jumbo v14, "zzo"

    const-string/jumbo v15, "zzp"

    const-string/jumbo v16, "zzq"

    const-string/jumbo v17, "zzr"

    filled-new-array/range {v2 .. v17}, [Ljava/lang/Object;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzakg;->zzs:Lcom/google/android/recaptcha/internal/zzakg;

    const-string v2, "\u0004\u000f\u0000\u0001\u0001\u0011\u000f\u0000\u0001\u0000\u0001\u1208\u0000\u0002\u1208\u0001\u0003\u1208\u0002\u0004\u1208\u0003\u0005\u1208\u0004\u0006\u1208\u0005\u0007%\u0008\u100a\u0006\u000b\u1009\u0007\u000c\u1009\u0008\r\u1009\t\u000e\u1007\n\u000f\u1002\u000b\u0010\u1007\u000c\u0011\u1007\r"

    .line 4
    invoke-static {v1, v2, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzM(Lcom/google/android/recaptcha/internal/zzagx;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    return-object v0

    :cond_7
    const/4 v0, 0x1

    .line 1
    invoke-static {v0}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object v0

    return-object v0
.end method

.method public final zzg()Lcom/google/android/recaptcha/internal/zzake;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzm:Lcom/google/android/recaptcha/internal/zzake;

    if-nez p0, :cond_0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzake;->zzb()Lcom/google/android/recaptcha/internal/zzake;

    move-result-object p0

    :cond_0
    return-object p0
.end method

.method public final zzh()Lcom/google/android/recaptcha/internal/zzako;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzn:Lcom/google/android/recaptcha/internal/zzako;

    if-nez p0, :cond_0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzako;->zzd()Lcom/google/android/recaptcha/internal/zzako;

    move-result-object p0

    :cond_0
    return-object p0
.end method

.method public final zzi()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzo:Z

    return p0
.end method

.method public final zzj()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzp:J

    return-wide v0
.end method

.method public final zzk()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzq:Z

    return p0
.end method

.method public final zzl()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzr:Z

    return p0
.end method

.method public final synthetic zzo(Lcom/google/android/recaptcha/internal/zzakk;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzl:Lcom/google/android/recaptcha/internal/zzakk;

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzakg;->zza:I

    or-int/lit16 p1, p1, 0x80

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzakg;->zza:I

    return-void
.end method

.method public final synthetic zzp(Lcom/google/android/recaptcha/internal/zzake;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzm:Lcom/google/android/recaptcha/internal/zzake;

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzakg;->zza:I

    or-int/lit16 p1, p1, 0x100

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzakg;->zza:I

    return-void
.end method

.method public final synthetic zzq(Lcom/google/android/recaptcha/internal/zzako;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzn:Lcom/google/android/recaptcha/internal/zzako;

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzakg;->zza:I

    or-int/lit16 p1, p1, 0x200

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzakg;->zza:I

    return-void
.end method

.method public final synthetic zzr()V
    .locals 1

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zzn:Lcom/google/android/recaptcha/internal/zzako;

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zza:I

    and-int/lit16 v0, v0, -0x201

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzakg;->zza:I

    return-void
.end method
