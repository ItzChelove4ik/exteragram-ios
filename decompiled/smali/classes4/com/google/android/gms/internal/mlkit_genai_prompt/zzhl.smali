.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

.field private final zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;

.field private final zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;-><init>()V

    if-eqz p1, :cond_0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-void

    :cond_0
    const-string p0, "Null results"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p1, p0, :cond_0

    return v0

    .line 1
    :cond_0
    instance-of v1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;

    const/4 v2, 0x0

    if-eqz v1, :cond_6

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v3

    invoke-virtual {v1, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_6

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

    if-nez v1, :cond_1

    .line 3
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

    move-result-object v1

    if-nez v1, :cond_6

    goto :goto_0

    :cond_1
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

    move-result-object v3

    if-eq v1, v3, :cond_2

    goto :goto_3

    :cond_2
    :goto_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;

    if-nez v1, :cond_3

    .line 4
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;

    move-result-object v1

    if-nez v1, :cond_6

    goto :goto_1

    :cond_3
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_6

    :goto_1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    if-nez p0, :cond_4

    .line 5
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    if-nez p0, :cond_6

    goto :goto_2

    :cond_4
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_5

    goto :goto_3

    :cond_5
    :goto_2
    return v0

    :cond_6
    :goto_3
    return v2
.end method

.method public final hashCode()I
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->hashCode()I

    move-result v0

    const v1, 0xf4243

    xor-int/2addr v0, v1

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

    const/4 v3, 0x0

    if-nez v2, :cond_0

    move v2, v3

    goto :goto_0

    .line 2
    :cond_0
    invoke-virtual {v2}, Ljava/lang/Object;->hashCode()I

    move-result v2

    :goto_0
    mul-int/2addr v0, v1

    xor-int/2addr v0, v2

    mul-int/2addr v0, v1

    .line 1
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;

    if-nez v2, :cond_1

    move v2, v3

    goto :goto_1

    .line 3
    :cond_1
    invoke-virtual {v2}, Ljava/lang/Object;->hashCode()I

    move-result v2

    :goto_1
    xor-int/2addr v0, v2

    mul-int/2addr v0, v1

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    if-nez p0, :cond_2

    goto :goto_2

    .line 4
    :cond_2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->hashCode()I

    move-result v3

    :goto_2
    xor-int p0, v0, v3

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 7

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v1

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-static {v3}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-static {v2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v4

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v5

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v6

    add-int/lit8 v1, v1, 0x2e

    add-int/2addr v1, v4

    add-int/lit8 v1, v1, 0x17

    add-int/2addr v1, v5

    add-int/lit8 v1, v1, 0x11

    add-int/2addr v1, v6

    new-instance v4, Ljava/lang/StringBuilder;

    add-int/lit8 v1, v1, 0x1

    invoke-direct {v4, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "LlmResult{results="

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", inferenceEventTraceResult="

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ", legionResultMetadata="

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ", thoughtProcess="

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "}"

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0
.end method

.method public final zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

    return-object p0
.end method

.method public final zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;

    return-object p0
.end method

.method public final zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0
.end method
