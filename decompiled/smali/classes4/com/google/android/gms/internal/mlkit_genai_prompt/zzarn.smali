.class public final enum Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;
.super Ljava/lang/Enum;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpt;


# static fields
.field public static final enum zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

.field public static final enum zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

.field public static final enum zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

.field private static final synthetic zze:[Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;


# instance fields
.field private final zzd:I


# direct methods
.method static constructor <clinit>()V
    .locals 5

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

    const-string v1, "GENERATION_MODE_UNKNOWN"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

    const-string v2, "GENERATION_MODE_GENERATIVE"

    const/4 v3, 0x1

    .line 2
    invoke-direct {v1, v2, v3, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;-><init>(Ljava/lang/String;II)V

    sput-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

    const-string v3, "GENERATION_MODE_REGENERATIVE"

    const/4 v4, 0x2

    .line 3
    invoke-direct {v2, v3, v4, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;-><init>(Ljava/lang/String;II)V

    sput-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

    filled-new-array {v0, v1, v2}, [Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

    move-result-object v0

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;->zze:[Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;II)V
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    iput p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;->zzd:I

    return-void
.end method

.method public static values()[Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;->zze:[Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

    invoke-virtual {v0}, [Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

    return-object v0
.end method


# virtual methods
.method public final zza()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;->zzd:I

    return p0
.end method
