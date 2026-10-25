.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;
.source "SourceFile"


# instance fields
.field private final zza:Landroid/os/ParcelFileDescriptor;

.field private final zzb:I

.field private final zzc:I

.field private final zzd:I

.field private final zze:Ljava/lang/String;

.field private final zzf:I

.field private final zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Landroid/os/ParcelFileDescriptor;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;FIIILcom/google/android/gms/internal/mlkit_genai_prompt/zzev;IIIILjava/lang/String;Ljava/lang/String;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;[B)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;-><init>()V

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zza:Landroid/os/ParcelFileDescriptor;

    iput p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzb:I

    iput p6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzc:I

    iput p7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzd:I

    iput p12, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzf:I

    iput-object p13, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zze:Ljava/lang/String;

    move-object/from16 p1, p16

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    return-void
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p1, p0, :cond_0

    return v0

    .line 1
    :cond_0
    instance-of v1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;

    const/4 v2, 0x0

    if-eqz v1, :cond_4

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zza:Landroid/os/ParcelFileDescriptor;

    .line 3
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzb()Landroid/os/ParcelFileDescriptor;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_4

    .line 4
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 v1, 0x0

    .line 5
    invoke-static {v1}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v3

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzd()F

    invoke-static {v1}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v1

    if-ne v3, v1, :cond_4

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzb:I

    .line 6
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zze()I

    move-result v3

    if-ne v1, v3, :cond_4

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzc:I

    .line 7
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzf()I

    move-result v3

    if-ne v1, v3, :cond_4

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzd:I

    .line 8
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzg()I

    move-result v3

    if-ne v1, v3, :cond_4

    .line 9
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzev;

    .line 10
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzi()I

    .line 11
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzj()I

    .line 12
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzk()I

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzf:I

    .line 13
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzo()I

    move-result v3

    if-ne v1, v3, :cond_4

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zze:Ljava/lang/String;

    if-nez v1, :cond_1

    .line 14
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzl()Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_4

    goto :goto_0

    :cond_1
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzl()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_4

    .line 15
    :goto_0
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzm()Ljava/lang/String;

    .line 16
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzn()I

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    if-nez p0, :cond_2

    .line 17
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    move-result-object p0

    if-nez p0, :cond_4

    goto :goto_1

    :cond_2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    move-result-object p1

    if-eq p0, p1, :cond_3

    goto :goto_2

    :cond_3
    :goto_1
    return v0

    :cond_4
    :goto_2
    return v2
.end method

.method public final hashCode()I
    .locals 8

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zza:Landroid/os/ParcelFileDescriptor;

    invoke-virtual {v0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    const v1, -0x2aff6277

    xor-int/2addr v0, v1

    mul-int/2addr v0, v1

    const/4 v1, 0x0

    .line 2
    invoke-static {v1}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v1

    xor-int/2addr v0, v1

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zze:Ljava/lang/String;

    const/4 v2, 0x0

    if-nez v1, :cond_0

    move v1, v2

    goto :goto_0

    .line 3
    :cond_0
    invoke-virtual {v1}, Ljava/lang/String;->hashCode()I

    move-result v1

    :goto_0
    const v3, 0xf4243

    mul-int/2addr v0, v3

    .line 2
    iget v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzb:I

    iget v5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzc:I

    iget v6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzd:I

    iget v7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzf:I

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    if-nez p0, :cond_1

    goto :goto_1

    .line 4
    :cond_1
    invoke-virtual {p0}, Ljava/lang/Object;->hashCode()I

    move-result v2

    :goto_1
    xor-int p0, v0, v4

    mul-int/2addr p0, v3

    xor-int/2addr p0, v5

    mul-int/2addr p0, v3

    xor-int/2addr p0, v6

    const v0, -0x199d4fcd

    mul-int/2addr p0, v0

    xor-int/2addr p0, v7

    mul-int/2addr p0, v3

    xor-int/2addr p0, v1

    const v0, 0x22cd8cdb

    mul-int/2addr p0, v0

    xor-int/2addr p0, v2

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 13

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zza:Landroid/os/ParcelFileDescriptor;

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v1

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    invoke-static {v2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    const/4 v3, 0x0

    invoke-static {v3}, Ljava/lang/String;->valueOf(F)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    iget v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzb:I

    invoke-static {v4}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->length()I

    move-result v5

    iget v6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzc:I

    invoke-static {v6}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/String;->length()I

    move-result v7

    iget v8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzd:I

    invoke-static {v8}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/String;->length()I

    move-result v9

    iget-object v10, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zze:Ljava/lang/String;

    invoke-static {v10}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v11}, Ljava/lang/String;->length()I

    move-result v11

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v12

    add-int/lit8 v1, v1, 0x76

    add-int/2addr v1, v3

    add-int/lit8 v1, v1, 0x7

    add-int/2addr v1, v5

    add-int/lit8 v1, v1, 0x17

    add-int/2addr v1, v7

    new-instance v3, Ljava/lang/StringBuilder;

    add-int/lit8 v1, v1, 0xd

    add-int/2addr v1, v9

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzf:I

    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzen;->zza(I)Ljava/lang/String;

    move-result-object p0

    add-int/lit8 v1, v1, 0x66

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v5

    add-int/2addr v1, v5

    add-int/lit8 v1, v1, 0xf

    add-int/2addr v1, v11

    add-int/lit8 v1, v1, 0x42

    add-int/2addr v1, v12

    add-int/lit8 v1, v1, 0x1

    invoke-direct {v3, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "CortanaRequest{messages=null, cortanaStateFileDescriptorToWrite="

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", cortanaStateFileDescriptorsToRead=null, temperature=0.0, topK="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ", targetReplyLengthMax="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ", numSamples="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ", streamingCallback=null, preferredImageWidth=0, preferredImageHeight=0, numSoftTokens=0, cortanaType="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ", systemPrompt="

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ", textToMemory=null, tokenOffset=0, inferenceStateCachingCallback="

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "}"

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzb()Landroid/os/ParcelFileDescriptor;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zza:Landroid/os/ParcelFileDescriptor;

    return-object p0
.end method

.method public final zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzd()F
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public final zze()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzb:I

    return p0
.end method

.method public final zzf()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzc:I

    return p0
.end method

.method public final zzg()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzd:I

    return p0
.end method

.method public final zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzev;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzi()I
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public final zzj()I
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public final zzk()I
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public final zzl()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zze:Ljava/lang/String;

    return-object p0
.end method

.method public final zzm()Ljava/lang/String;
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzn()I
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public final zzo()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzf:I

    return p0
.end method

.method public final zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    return-object p0
.end method
