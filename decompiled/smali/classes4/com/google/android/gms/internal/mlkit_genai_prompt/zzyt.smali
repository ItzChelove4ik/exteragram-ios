.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final synthetic zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;)Landroid/content/Context;
    .locals 0

    .line 1
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzp()Lkotlin/Lazy;

    move-result-object p0

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/content/Context;

    return-object p0
.end method

.method public static final zzb(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;)I
    .locals 6

    .line 1
    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/GenerationConfig;->getModelConfig()Lcom/google/mlkit/genai/prompt/ModelConfig;

    move-result-object p0

    .line 2
    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/ModelConfig;->getReleaseStage()I

    move-result v0

    const/4 v1, 0x3

    const/4 v2, 0x2

    const/4 v3, -0x1

    const/4 v4, 0x1

    if-eqz v0, :cond_7

    if-eq v0, v4, :cond_0

    return v3

    .line 3
    :cond_0
    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/ModelConfig;->getPreference()I

    move-result p0

    if-eq p0, v4, :cond_4

    if-eq p0, v2, :cond_1

    return v3

    .line 4
    :cond_1
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;

    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    if-eqz p0, :cond_3

    if-eq p0, v1, :cond_2

    return v3

    :cond_2
    const/16 p0, 0x28c

    return p0

    :cond_3
    const/16 p0, 0x287

    return p0

    .line 5
    :cond_4
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;

    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    if-eqz p0, :cond_6

    if-eq p0, v1, :cond_5

    return v3

    :cond_5
    const/16 p0, 0x28d

    return p0

    :cond_6
    const/16 p0, 0x286

    return p0

    .line 6
    :cond_7
    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/ModelConfig;->getPreference()I

    move-result p0

    const/4 v0, 0x0

    const/4 v5, 0x4

    if-eq p0, v4, :cond_e

    if-eq p0, v2, :cond_8

    return v3

    .line 7
    :cond_8
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;

    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    if-eqz p0, :cond_d

    if-eq p0, v4, :cond_c

    if-eq p0, v2, :cond_b

    if-eq p0, v1, :cond_a

    if-ne p0, v5, :cond_9

    const/16 p0, 0x28e

    return p0

    :cond_9
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return v0

    :cond_a
    const/16 p0, 0x288

    return p0

    :cond_b
    const/16 p0, 0x284

    return p0

    :cond_c
    const/16 p0, 0x283

    return p0

    :cond_d
    const/16 p0, 0x27c

    return p0

    .line 8
    :cond_e
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;

    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    if-eqz p0, :cond_13

    if-eq p0, v4, :cond_12

    if-eq p0, v2, :cond_11

    if-eq p0, v1, :cond_10

    if-ne p0, v5, :cond_f

    const/16 p0, 0x28f

    return p0

    :cond_f
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return v0

    :cond_10
    const/16 p0, 0x28b

    return p0

    :cond_11
    const/16 p0, 0x28a

    return p0

    :cond_12
    const/16 p0, 0x289

    return p0

    :cond_13
    const/16 p0, 0x285

    return p0
.end method
