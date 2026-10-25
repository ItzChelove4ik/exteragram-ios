.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/String;

.field private final zzb:F

.field private final zzc:I

.field private final zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;

.field private final zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;


# direct methods
.method public constructor <init>(Ljava/lang/String;FILcom/google/android/gms/internal/mlkit_genai_prompt/zzho;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;-><init>()V

    const/4 v0, 0x0

    if-eqz p1, :cond_1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zza:Ljava/lang/String;

    iput p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzb:F

    iput p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzc:I

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;

    if-eqz p5, :cond_0

    .line 2
    iput-object p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-void

    .line 1
    :cond_0
    const-string p0, "Null functionCalls"

    .line 2
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    throw v0

    .line 1
    :cond_1
    const-string p0, "Null text"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    throw v0
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p1, p0, :cond_0

    return v0

    .line 1
    :cond_0
    instance-of v1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;

    const/4 v2, 0x0

    if-eqz v1, :cond_3

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zza:Ljava/lang/String;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zza()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_3

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzb:F

    .line 3
    invoke-static {v1}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v1

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zzb()F

    move-result v3

    invoke-static {v3}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v3

    if-ne v1, v3, :cond_3

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzc:I

    .line 4
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zzc()I

    move-result v3

    if-ne v1, v3, :cond_3

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;

    if-nez v1, :cond_1

    .line 5
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;

    move-result-object v1

    if-nez v1, :cond_3

    goto :goto_0

    :cond_1
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_2

    goto :goto_1

    :cond_2
    :goto_0
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 6
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_3

    return v0

    :cond_3
    :goto_1
    return v2
.end method

.method public final hashCode()I
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zza:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v0

    const v1, 0xf4243

    xor-int/2addr v0, v1

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzb:F

    mul-int/2addr v0, v1

    .line 2
    invoke-static {v2}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v2

    xor-int/2addr v0, v2

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;

    if-nez v2, :cond_0

    const/4 v2, 0x0

    goto :goto_0

    .line 3
    :cond_0
    invoke-virtual {v2}, Ljava/lang/Object;->hashCode()I

    move-result v2

    .line 2
    :goto_0
    iget v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzc:I

    mul-int/2addr v0, v1

    xor-int/2addr v0, v3

    mul-int/2addr v0, v1

    xor-int/2addr v0, v2

    mul-int/2addr v0, v1

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 4
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->hashCode()I

    move-result p0

    xor-int/2addr p0, v0

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 10

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzb:F

    invoke-static {v2}, Ljava/lang/String;->valueOf(F)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    iget v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzc:I

    invoke-static {v4}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->length()I

    move-result v5

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v6

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v7

    new-instance v8, Ljava/lang/StringBuilder;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zza:Ljava/lang/String;

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v9

    add-int/lit8 v9, v9, 0x16

    add-int/2addr v9, v3

    add-int/lit8 v9, v9, 0xd

    add-int/2addr v9, v5

    add-int/lit8 v9, v9, 0x13

    add-int/2addr v9, v6

    add-int/lit8 v9, v9, 0x10

    add-int/2addr v9, v7

    add-int/lit8 v9, v9, 0x1

    invoke-direct {v8, v9}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v3, "LlmReply{text="

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ", score="

    invoke-virtual {v8, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p0, ", stopReason="

    invoke-virtual {v8, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, ", citationMetadata="

    invoke-virtual {v8, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ", functionCalls="

    invoke-virtual {v8, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "}"

    invoke-virtual {v8, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zza()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zza:Ljava/lang/String;

    return-object p0
.end method

.method public final zzb()F
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzb:F

    return p0
.end method

.method public final zzc()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzc:I

    return p0
.end method

.method public final zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;

    return-object p0
.end method

.method public final zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0
.end method
