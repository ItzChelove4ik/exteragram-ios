.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

.field private final zzb:Lkotlin/reflect/KClass;

.field private final zzc:Z


# direct methods
.method public constructor <init>(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/reflect/KClass;Z)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzb:Lkotlin/reflect/KClass;

    iput-boolean p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzc:Z

    return-void
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    :cond_0
    instance-of v1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    :cond_1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    iget-object v3, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_2

    return v2

    :cond_2
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzb:Lkotlin/reflect/KClass;

    iget-object v3, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzb:Lkotlin/reflect/KClass;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_3

    return v2

    :cond_3
    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzc:Z

    iget-boolean p1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzc:Z

    if-eq p0, p1, :cond_4

    return v2

    :cond_4
    return v0
.end method

.method public final hashCode()I
    .locals 2

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-virtual {v0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    mul-int/lit8 v0, v0, 0x1f

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzb:Lkotlin/reflect/KClass;

    if-nez v1, :cond_0

    const/4 v1, 0x0

    goto :goto_0

    :cond_0
    invoke-virtual {v1}, Ljava/lang/Object;->hashCode()I

    move-result v1

    :goto_0
    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x1f

    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzc:Z

    invoke-static {p0}, Ljava/lang/Boolean;->hashCode(Z)I

    move-result p0

    add-int/2addr v0, p0

    return v0
.end method

.method public final toString()Ljava/lang/String;
    .locals 5

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzb:Lkotlin/reflect/KClass;

    invoke-static {v2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzc:Z

    invoke-static {p0}, Ljava/lang/String;->valueOf(Z)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v4

    add-int/lit8 v1, v1, 0x34

    add-int/2addr v1, v3

    add-int/lit8 v1, v1, 0x18

    add-int/2addr v1, v4

    new-instance v3, Ljava/lang/StringBuilder;

    add-int/lit8 v1, v1, 0x1

    invoke-direct {v3, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "RequestWrapper(generateContentRequest="

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", outputClass="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", includeSchemaInPrompt="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zza()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    return-object p0
.end method

.method public final zzb()Lkotlin/reflect/KClass;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzb:Lkotlin/reflect/KClass;

    return-object p0
.end method

.method public final zzc()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzc:Z

    return p0
.end method
