.class public final Lcom/google/android/recaptcha/internal/zzamh;
.super Lcom/google/android/recaptcha/internal/zzafv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# static fields
.field private static final zzf:Lcom/google/android/recaptcha/internal/zzamh;

.field private static volatile zzg:Lcom/google/android/recaptcha/internal/zzahe;


# instance fields
.field private zza:I

.field private zzd:I

.field private zze:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzamh;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzamh;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzamh;->zzf:Lcom/google/android/recaptcha/internal/zzamh;

    const-class v1, Lcom/google/android/recaptcha/internal/zzamh;

    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzafv;-><init>()V

    return-void
.end method

.method public static zzb([B)Lcom/google/android/recaptcha/internal/zzamh;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzamh;->zzf:Lcom/google/android/recaptcha/internal/zzamh;

    invoke-static {v0, p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzV(Lcom/google/android/recaptcha/internal/zzafv;[B)Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzamh;

    return-object p0
.end method

.method public static synthetic zzc()Lcom/google/android/recaptcha/internal/zzamh;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzamh;->zzf:Lcom/google/android/recaptcha/internal/zzamh;

    return-object v0
.end method


# virtual methods
.method public final zza()Lcom/google/android/recaptcha/internal/zzami;
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzamh;->zze:I

    packed-switch p0, :pswitch_data_0

    const/4 p0, 0x0

    goto :goto_0

    :pswitch_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzami;->zzj:Lcom/google/android/recaptcha/internal/zzami;

    goto :goto_0

    :pswitch_1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzami;->zzi:Lcom/google/android/recaptcha/internal/zzami;

    goto :goto_0

    :pswitch_2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzami;->zzh:Lcom/google/android/recaptcha/internal/zzami;

    goto :goto_0

    :pswitch_3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzami;->zzg:Lcom/google/android/recaptcha/internal/zzami;

    goto :goto_0

    :pswitch_4
    sget-object p0, Lcom/google/android/recaptcha/internal/zzami;->zzf:Lcom/google/android/recaptcha/internal/zzami;

    goto :goto_0

    :pswitch_5
    sget-object p0, Lcom/google/android/recaptcha/internal/zzami;->zze:Lcom/google/android/recaptcha/internal/zzami;

    goto :goto_0

    :pswitch_6
    sget-object p0, Lcom/google/android/recaptcha/internal/zzami;->zzd:Lcom/google/android/recaptcha/internal/zzami;

    goto :goto_0

    :pswitch_7
    sget-object p0, Lcom/google/android/recaptcha/internal/zzami;->zzc:Lcom/google/android/recaptcha/internal/zzami;

    goto :goto_0

    :pswitch_8
    sget-object p0, Lcom/google/android/recaptcha/internal/zzami;->zzb:Lcom/google/android/recaptcha/internal/zzami;

    goto :goto_0

    :pswitch_9
    sget-object p0, Lcom/google/android/recaptcha/internal/zzami;->zza:Lcom/google/android/recaptcha/internal/zzami;

    :goto_0
    if-nez p0, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzami;->zzk:Lcom/google/android/recaptcha/internal/zzami;

    :cond_0
    return-object p0

    nop

    :pswitch_data_0
    .packed-switch 0x0
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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzamh;->zzg:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_1

    const-class p1, Lcom/google/android/recaptcha/internal/zzamh;

    monitor-enter p1

    :try_start_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzamh;->zzg:Lcom/google/android/recaptcha/internal/zzahe;

    if-nez p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzafr;

    sget-object p2, Lcom/google/android/recaptcha/internal/zzamh;->zzf:Lcom/google/android/recaptcha/internal/zzamh;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafr;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    sput-object p0, Lcom/google/android/recaptcha/internal/zzamh;->zzg:Lcom/google/android/recaptcha/internal/zzahe;

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzamh;->zzf:Lcom/google/android/recaptcha/internal/zzamh;

    return-object p0

    .line 4
    :cond_4
    new-instance p0, Lcom/google/android/recaptcha/internal/zzamg;

    .line 5
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzamg;-><init>([B)V

    return-object p0

    :cond_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzamh;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzamh;-><init>()V

    return-object p0

    .line 1
    :cond_6
    const-string/jumbo p0, "zza"

    const-string/jumbo p1, "zzd"

    const-string/jumbo p2, "zze"

    filled-new-array {p0, p1, p2}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzamh;->zzf:Lcom/google/android/recaptcha/internal/zzamh;

    const-string p2, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u100c\u0000\u0002\u100c\u0001"

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
