.class public final Lcom/google/android/recaptcha/internal/zzaku;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzo:Lcom/google/android/recaptcha/internal/zzaku;

.field private static volatile zzp:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:I

.field private zzd:Ljava/lang/String;

.field private zze:Ljava/lang/String;

.field private zzf:Ljava/lang/String;

.field private zzg:Ljava/lang/String;

.field private zzh:Ljava/lang/String;

.field private zzi:Lcom/google/android/recaptcha/internal/zzaky;

.field private zzj:Lcom/google/android/recaptcha/internal/zzakm;

.field private zzk:Lcom/google/android/recaptcha/internal/zzakw;

.field private zzl:Lcom/google/android/recaptcha/internal/zzakc;

.field private zzm:Lcom/google/android/recaptcha/internal/zzakq;

.field private zzn:Lcom/google/android/recaptcha/internal/zzajy;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzaku;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzaku;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaku;->zzo:Lcom/google/android/recaptcha/internal/zzaku;

    const-class v1, Lcom/google/android/recaptcha/internal/zzaku;

    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    const-string v0, ""

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaku;->zzd:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaku;->zze:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaku;->zzf:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaku;->zzg:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaku;->zzh:Ljava/lang/String;

    return-void
.end method

.method public static zza()Lcom/google/android/recaptcha/internal/zzakt;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaku;->zzo:Lcom/google/android/recaptcha/internal/zzaku;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzF()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzakt;

    return-object v0
.end method

.method public static zzb()Lcom/google/android/recaptcha/internal/zzaku;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaku;->zzo:Lcom/google/android/recaptcha/internal/zzaku;

    return-object v0
.end method

.method public static synthetic zzi()Lcom/google/android/recaptcha/internal/zzaku;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaku;->zzo:Lcom/google/android/recaptcha/internal/zzaku;

    return-object v0
.end method


# virtual methods
.method public final synthetic zzc(Ljava/lang/String;)V
    .locals 1

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaku;->zza:I

    or-int/lit8 v0, v0, 0x1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaku;->zza:I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaku;->zzd:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zzd()V
    .locals 1

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaku;->zza:I

    and-int/lit8 v0, v0, -0x2

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaku;->zza:I

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaku;->zzo:Lcom/google/android/recaptcha/internal/zzaku;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzaku;->zzd:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaku;->zzd:Ljava/lang/String;

    return-void
.end method

.method public final synthetic zze(Lcom/google/android/recaptcha/internal/zzakm;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaku;->zzj:Lcom/google/android/recaptcha/internal/zzakm;

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzaku;->zza:I

    or-int/lit8 p1, p1, 0x40

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaku;->zza:I

    return-void
.end method

.method public final zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 12

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaku;->zzp:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzaku;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaku;->zzp:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzaku;->zzo:Lcom/google/android/recaptcha/internal/zzaku;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzaku;->zzp:Lcom/google/android/recaptcha/internal/zzahe;

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

    .line 6
    :cond_2
    throw p2

    .line 1
    :cond_3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaku;->zzo:Lcom/google/android/recaptcha/internal/zzaku;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzakt;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzakt;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzaku;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaku;-><init>()V

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

    filled-new-array/range {v0 .. v11}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaku;->zzo:Lcom/google/android/recaptcha/internal/zzaku;

    const-string p2, "\u0004\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001\u1208\u0000\u0002\u1208\u0001\u0003\u1208\u0002\u0004\u1208\u0003\u0005\u1208\u0004\u0006\u1009\u0005\u0007\u1009\u0006\u0008\u1009\u0007\t\u1009\u0008\n\u1009\t\u000b\u1009\n"

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

.method public final synthetic zzg(Lcom/google/android/recaptcha/internal/zzakc;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaku;->zzl:Lcom/google/android/recaptcha/internal/zzakc;

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzaku;->zza:I

    or-int/lit16 p1, p1, 0x100

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaku;->zza:I

    return-void
.end method

.method public final synthetic zzh(Lcom/google/android/recaptcha/internal/zzakq;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaku;->zzm:Lcom/google/android/recaptcha/internal/zzakq;

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzaku;->zza:I

    or-int/lit16 p1, p1, 0x200

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaku;->zza:I

    return-void
.end method
