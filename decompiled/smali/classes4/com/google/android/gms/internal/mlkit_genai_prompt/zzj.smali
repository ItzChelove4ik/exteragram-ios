.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxx;


# static fields
.field private static final zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;


# instance fields
.field private zzb:I

.field private zzd:I

.field private zze:I

.field private zzf:I

.field private zzg:F

.field private zzh:F

.field private zzi:F


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;->zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;

    const-class v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;

    invoke-static {v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzN(Ljava/lang/Class;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;-><init>()V

    return-void
.end method

.method public static zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;->zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;

    return-object v0
.end method

.method public static synthetic zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;->zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;

    return-object v0
.end method


# virtual methods
.method public final zza()F
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;->zzg:F

    return p0
.end method

.method public final zzb(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 10

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
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;->zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;

    return-object p0

    .line 4
    :cond_0
    throw p2

    .line 2
    :cond_1
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzi;

    .line 3
    invoke-direct {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzi;-><init>([B)V

    return-object p0

    :cond_2
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;-><init>()V

    return-object p0

    .line 1
    :cond_3
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazj;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;

    move-result-object v2

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazh;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;

    move-result-object v4

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazf;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;

    move-result-object v6

    const-string v8, "zzh"

    const-string v9, "zzi"

    const-string v0, "zzb"

    const-string v1, "zzd"

    const-string v3, "zze"

    const-string v5, "zzf"

    const-string v7, "zzg"

    filled-new-array/range {v0 .. v9}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;->zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;

    const-string p2, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\u180c\u0000\u0002\u180c\u0001\u0003\u180c\u0002\u0004\u1001\u0003\u0005\u1001\u0004\u0006\u1001\u0005"

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

.method public final zzc()F
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;->zzh:F

    return p0
.end method

.method public final zzd()F
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;->zzi:F

    return p0
.end method

.method public final zzg()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;->zzd:I

    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazj;->zzb(I)I

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    :cond_0
    return p0
.end method

.method public final zzh()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;->zze:I

    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazh;->zzb(I)I

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    :cond_0
    return p0
.end method

.method public final zzi()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzj;->zzf:I

    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazf;->zzb(I)I

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    :cond_0
    return p0
.end method
