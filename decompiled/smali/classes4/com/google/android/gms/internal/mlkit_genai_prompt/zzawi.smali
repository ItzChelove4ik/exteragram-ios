.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxx;


# static fields
.field private static final zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;


# instance fields
.field private zzb:J

.field private zzd:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;

    const-class v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;

    invoke-static {v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzN(Ljava/lang/Class;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;-><init>()V

    return-void
.end method

.method public static zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;

    return-object v0
.end method

.method public static synthetic zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;

    return-object v0
.end method


# virtual methods
.method public final zza()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;->zzb:J

    return-wide v0
.end method

.method public final zzb(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

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
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;

    return-object p0

    .line 4
    :cond_0
    throw p2

    .line 2
    :cond_1
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawh;

    .line 3
    invoke-direct {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawh;-><init>([B)V

    return-object p0

    :cond_2
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;-><init>()V

    return-object p0

    .line 1
    :cond_3
    const-string p0, "zzb"

    const-string p1, "zzd"

    filled-new-array {p0, p1}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawi;

    new-instance p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayf;

    const-string p3, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004"

    .line 2
    invoke-direct {p2, p1, p3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayf;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;Ljava/lang/String;[Ljava/lang/Object;)V

    return-object p2

    :cond_4
    const/4 p0, 0x1

    .line 1
    invoke-static {p0}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object p0

    return-object p0
.end method
