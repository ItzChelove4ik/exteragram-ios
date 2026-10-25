.class public final enum Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;
.super Ljava/lang/Enum;
.source "SourceFile"


# static fields
.field public static final enum zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

.field public static final enum zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

.field private static final synthetic zzc:[Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    const-string v1, "INFERENCE_WITHOUT_CACHE"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    const-string v2, "INFERENCE_WITH_CACHE"

    const/4 v3, 0x1

    .line 2
    invoke-direct {v1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;-><init>(Ljava/lang/String;I)V

    sput-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    filled-new-array {v0, v1}, [Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    move-result-object v0

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;->zzc:[Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    invoke-static {v0}, Lkotlin/enums/EnumEntriesKt;->enumEntries([Ljava/lang/Enum;)Lkotlin/enums/EnumEntries;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static values()[Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;->zzc:[Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    invoke-virtual {v0}, Ljava/lang/Object;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    return-object v0
.end method
