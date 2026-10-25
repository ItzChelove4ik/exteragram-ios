.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxx;


# static fields
.field private static final zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;


# instance fields
.field private zzb:I

.field private zzd:J

.field private zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;

.field private zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;

.field private zzg:F


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;

    const-class v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;

    .line 2
    invoke-static {v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzN(Ljava/lang/Class;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;-><init>()V

    .line 2
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzQ()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;

    return-void
.end method

.method public static zzf([BLcom/google/android/gms/internal/mlkit_genai_prompt/zzawl;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;

    invoke-static {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzR(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;[BLcom/google/android/gms/internal/mlkit_genai_prompt/zzawl;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;

    return-object p0
.end method

.method public static zzg()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;

    return-object v0
.end method

.method public static synthetic zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;

    return-object v0
.end method


# virtual methods
.method public final zza()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;->zzd:J

    return-wide v0
.end method

.method public final zzb(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 6

    add-int/lit8 p1, p1, -0x1

    if-eqz p1, :cond_4

    const/4 p0, 0x2

    if-eq p1, p0, :cond_3

    const/4 p0, 0x3

    if-eq p1, p0, :cond_2

    const/4 p0, 0x4

    const/4 p2, 0x0

    if-eq p1, p0, :cond_1

    const/4 p0, 0x5

    if-ne p1, p0, :cond_0

    .line 1
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;

    return-object p0

    .line 5
    :cond_0
    throw p2

    .line 2
    :cond_1
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzjd;

    .line 3
    invoke-direct {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzjd;-><init>([B)V

    return-object p0

    :cond_2
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;

    .line 4
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;-><init>()V

    return-object p0

    .line 1
    :cond_3
    const-string v4, "zzf"

    const-string v5, "zzg"

    const-string v0, "zzb"

    const-string v1, "zzd"

    const-string v2, "zze"

    const-class v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzjc;

    filled-new-array/range {v0 .. v5}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;

    const-string p2, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u1003\u0000\u0002\u001b\u0003\u1009\u0001\u0004\u1001\u0002"

    .line 2
    invoke-static {p1, p2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzO(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :cond_4
    const/4 p0, 0x1

    .line 1
    invoke-static {p0}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object p0

    return-object p0
.end method

.method public final zzc()Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;

    return-object p0
.end method

.method public final zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;

    if-nez p0, :cond_0

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;

    move-result-object p0

    :cond_0
    return-object p0
.end method

.method public final zze()F
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzje;->zzg:F

    return p0
.end method
