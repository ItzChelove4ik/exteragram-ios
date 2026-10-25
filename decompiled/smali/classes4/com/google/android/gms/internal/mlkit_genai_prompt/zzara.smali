.class public final enum Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;
.super Ljava/lang/Enum;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpt;


# static fields
.field public static final enum zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

.field public static final enum zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

.field public static final enum zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

.field private static final synthetic zze:[Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;


# instance fields
.field private final zzd:I


# direct methods
.method static constructor <clinit>()V
    .locals 5

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    const-string v1, "MODALITY_TYPE_UNKNOWN"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    const-string v2, "MODALITY_TYPE_TEXT"

    const/4 v3, 0x1

    .line 2
    invoke-direct {v1, v2, v3, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;-><init>(Ljava/lang/String;II)V

    sput-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    const-string v3, "MODALITY_TYPE_IMAGE"

    const/4 v4, 0x2

    .line 3
    invoke-direct {v2, v3, v4, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;-><init>(Ljava/lang/String;II)V

    sput-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    filled-new-array {v0, v1, v2}, [Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    move-result-object v0

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;->zze:[Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;II)V
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    iput p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;->zzd:I

    return-void
.end method

.method public static values()[Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;->zze:[Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    invoke-virtual {v0}, [Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    return-object v0
.end method


# virtual methods
.method public final zza()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;->zzd:I

    return p0
.end method
