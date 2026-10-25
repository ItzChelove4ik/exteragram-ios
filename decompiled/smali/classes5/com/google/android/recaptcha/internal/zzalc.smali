.class public final Lcom/google/android/recaptcha/internal/zzalc;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzo:Lcom/google/android/recaptcha/internal/zzalc;

.field private static volatile zzp:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:I

.field private zzd:Ljava/lang/String;

.field private zze:J

.field private zzf:I

.field private zzg:Lcom/google/android/recaptcha/internal/zzage;

.field private zzh:Lcom/google/android/recaptcha/internal/zzaer;

.field private zzi:Ljava/lang/String;

.field private zzj:Ljava/lang/String;

.field private zzk:Lcom/google/android/recaptcha/internal/zzaer;

.field private zzl:Ljava/lang/String;

.field private zzm:Ljava/lang/String;

.field private zzn:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzalc;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzalc;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzalc;->zzo:Lcom/google/android/recaptcha/internal/zzalc;

    const-class v1, Lcom/google/android/recaptcha/internal/zzalc;

    .line 2
    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 2

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    const-string v0, ""

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzd:Ljava/lang/String;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafv;->zzR()Lcom/google/android/recaptcha/internal/zzage;

    move-result-object v1

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzg:Lcom/google/android/recaptcha/internal/zzage;

    .line 3
    sget-object v1, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzh:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzi:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzj:Ljava/lang/String;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzk:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzl:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzm:Ljava/lang/String;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzn:Ljava/lang/String;

    return-void
.end method

.method public static zze()Lcom/google/android/recaptcha/internal/zzalc;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzalc;->zzo:Lcom/google/android/recaptcha/internal/zzalc;

    return-object v0
.end method

.method public static synthetic zzg()Lcom/google/android/recaptcha/internal/zzalc;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzalc;->zzo:Lcom/google/android/recaptcha/internal/zzalc;

    return-object v0
.end method


# virtual methods
.method public final zza()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzd:Ljava/lang/String;

    return-object p0
.end method

.method public final zzb()Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzg:Lcom/google/android/recaptcha/internal/zzage;

    return-object p0
.end method

.method public final zzc()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzh:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final zzd()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzk:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 13

    add-int/lit8 p1, p1, -0x1

    if-eqz p1, :cond_7

    const/4 p0, 0x2

    if-eq p1, p0, :cond_6

    const/4 p0, 0x3

    if-eq p1, p0, :cond_5

    const/4 p0, 0x4

    const/4 v0, 0x0

    if-eq p1, p0, :cond_4

    const/4 p0, 0x5

    if-eq p1, p0, :cond_3

    const/4 p0, 0x6

    if-ne p1, p0, :cond_2

    .line 2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzalc;->zzp:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzalc;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzalc;->zzp:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzalc;->zzo:Lcom/google/android/recaptcha/internal/zzalc;

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzalc;->zzp:Lcom/google/android/recaptcha/internal/zzahe;

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
    throw v0

    .line 1
    :cond_3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzalc;->zzo:Lcom/google/android/recaptcha/internal/zzalc;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzalb;

    .line 5
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzalb;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzalc;

    .line 6
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzalc;-><init>()V

    return-object p0

    .line 1
    :cond_6
    const-string/jumbo v0, "zza"

    const-string/jumbo v1, "zzd"

    const-string/jumbo v2, "zze"

    const-string/jumbo v3, "zzf"

    const-string/jumbo v4, "zzg"

    const-class v5, Lcom/google/android/recaptcha/internal/zzale;

    const-string/jumbo v6, "zzh"

    const-string/jumbo v7, "zzi"

    const-string/jumbo v8, "zzj"

    const-string/jumbo v9, "zzk"

    const-string/jumbo v10, "zzl"

    const-string/jumbo v11, "zzm"

    const-string/jumbo v12, "zzn"

    filled-new-array/range {v0 .. v12}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzalc;->zzo:Lcom/google/android/recaptcha/internal/zzalc;

    const-string v0, "\u0004\u000b\u0000\u0001\u0001\u000c\u000b\u0000\u0001\u0000\u0001\u1208\u0000\u0002\u1002\u0001\u0004\u100c\u0002\u0005\u001b\u0006\u100a\u0003\u0007\u1208\u0004\u0008\u1208\u0005\t\u100a\u0006\n\u1208\u0007\u000b\u1208\u0008\u000c\u1208\t"

    .line 4
    invoke-static {p1, v0, p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzM(Lcom/google/android/recaptcha/internal/zzagx;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;

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

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzalc;->zzf:I

    const/16 v0, 0xf

    if-eq p0, v0, :cond_1

    const/16 v0, 0x10

    if-eq p0, v0, :cond_0

    packed-switch p0, :pswitch_data_0

    const/4 p0, 0x0

    goto :goto_0

    :pswitch_0
    const/16 p0, 0xc

    goto :goto_0

    :pswitch_1
    const/16 p0, 0xb

    goto :goto_0

    :pswitch_2
    const/16 p0, 0xa

    goto :goto_0

    :pswitch_3
    const/16 p0, 0x9

    goto :goto_0

    :pswitch_4
    const/16 p0, 0x8

    goto :goto_0

    :pswitch_5
    const/4 p0, 0x7

    goto :goto_0

    :pswitch_6
    const/4 p0, 0x6

    goto :goto_0

    :pswitch_7
    const/4 p0, 0x5

    goto :goto_0

    :pswitch_8
    const/4 p0, 0x4

    goto :goto_0

    :pswitch_9
    const/4 p0, 0x3

    goto :goto_0

    :pswitch_a
    const/4 p0, 0x2

    goto :goto_0

    :cond_0
    const/16 p0, 0x12

    goto :goto_0

    :cond_1
    const/16 p0, 0x11

    :goto_0
    if-nez p0, :cond_2

    const/4 p0, 0x1

    :cond_2
    return p0

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
