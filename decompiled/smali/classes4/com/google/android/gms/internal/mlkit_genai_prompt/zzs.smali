.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
.source "SourceFile"


# instance fields
.field private zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private zzb:I

.field private zzc:I

.field private zzd:I

.field private zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;

.field private zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private zzg:Ljava/lang/String;

.field private zzh:Ljava/lang/String;

.field private zzi:I

.field private zzj:I

.field private zzk:S


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    .line 1
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0
.end method

.method public final zzb(F)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    iget-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    or-int/lit8 p1, p1, 0x1

    int-to-short p1, p1

    iput-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    return-object p0
.end method

.method public final zzc(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzb:I

    iget-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    or-int/lit8 p1, p1, 0x2

    int-to-short p1, p1

    iput-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    return-object p0
.end method

.method public final zzd(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzc:I

    iget-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    or-int/lit8 p1, p1, 0x4

    int-to-short p1, p1

    iput-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    return-object p0
.end method

.method public final zze(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzd:I

    iget-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    or-int/lit8 p1, p1, 0x8

    int-to-short p1, p1

    iput-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    return-object p0
.end method

.method public final zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;

    return-object p0
.end method

.method public final zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    if-eqz p1, :cond_0

    .line 1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0

    :cond_0
    const-string p0, "Null cortanaStateFileDescriptorsToRead"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzh(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    iget-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    or-int/lit8 p1, p1, 0x10

    int-to-short p1, p1

    iput-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    return-object p0
.end method

.method public final zzi(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    iget-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    or-int/lit8 p1, p1, 0x20

    int-to-short p1, p1

    iput-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    return-object p0
.end method

.method public final zzj(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzg:Ljava/lang/String;

    return-object p0
.end method

.method public final zzk(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzh:Ljava/lang/String;

    return-object p0
.end method

.method public final zzl(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzi:I

    iget-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    or-int/lit8 p1, p1, 0x40

    int-to-short p1, p1

    iput-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    return-object p0
.end method

.method public final zzm(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    iget-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    or-int/lit16 p1, p1, 0x80

    int-to-short p1, p1

    iput-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    return-object p0
.end method

.method public final zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 0

    const/4 p1, 0x1

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzj:I

    iget-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    or-int/lit16 p1, p1, 0x100

    int-to-short p1, p1

    iput-short p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    return-object p0
.end method

.method public final zzo()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;
    .locals 20

    move-object/from16 v0, p0

    .line 2
    iget-short v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    const/16 v2, 0x1ff

    if-ne v1, v2, :cond_1

    iget-object v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    if-eqz v1, :cond_1

    iget-object v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    if-eqz v1, :cond_1

    iget-object v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzg:Ljava/lang/String;

    if-nez v1, :cond_0

    goto :goto_0

    .line 1
    :cond_0
    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;

    iget-object v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iget v5, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzb:I

    iget v6, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzc:I

    iget v8, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzd:I

    iget-object v9, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;

    iget-object v10, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iget-object v14, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzg:Ljava/lang/String;

    iget-object v15, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzh:Ljava/lang/String;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzi:I

    const/16 v18, 0x0

    iget v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzj:I

    const/4 v4, 0x0

    const/4 v7, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    const/16 v16, 0x0

    move/from16 v19, v0

    move/from16 v17, v1

    invoke-direct/range {v2 .. v19}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;FIILcom/google/android/gms/internal/mlkit_genai_prompt/zzbc;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;III)V

    return-object v2

    .line 2
    :cond_1
    :goto_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    if-nez v2, :cond_2

    const-string v2, " messages"

    .line 3
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_2
    iget-short v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    and-int/lit8 v2, v2, 0x1

    if-nez v2, :cond_3

    const-string v2, " temperature"

    .line 4
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_3
    iget-short v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    and-int/lit8 v2, v2, 0x2

    if-nez v2, :cond_4

    const-string v2, " topK"

    .line 5
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_4
    iget-short v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    and-int/lit8 v2, v2, 0x4

    if-nez v2, :cond_5

    const-string v2, " targetReplyLengthMax"

    .line 6
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_5
    iget-short v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    and-int/lit8 v2, v2, 0x8

    if-nez v2, :cond_6

    const-string v2, " numSamples"

    .line 7
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_6
    iget-object v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    if-nez v2, :cond_7

    const-string v2, " cortanaStateFileDescriptorsToRead"

    .line 8
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_7
    iget-short v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    and-int/lit8 v2, v2, 0x10

    if-nez v2, :cond_8

    const-string v2, " preferredImageWidth"

    .line 9
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_8
    iget-short v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    and-int/lit8 v2, v2, 0x20

    if-nez v2, :cond_9

    const-string v2, " preferredImageHeight"

    .line 10
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_9
    iget-object v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzg:Ljava/lang/String;

    if-nez v2, :cond_a

    const-string v2, " cortanaType"

    .line 11
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_a
    iget-short v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    and-int/lit8 v2, v2, 0x40

    if-nez v2, :cond_b

    const-string v2, " tokenOffset"

    .line 12
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_b
    iget-short v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    and-int/lit16 v2, v2, 0x80

    if-nez v2, :cond_c

    const-string v2, " numSoftTokens"

    .line 13
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_c
    iget-short v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zzk:S

    and-int/lit16 v0, v0, 0x100

    if-nez v0, :cond_d

    const-string v0, " safetyClassificationMode"

    .line 14
    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_d
    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "Missing required properties:"

    invoke-virtual {v1, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 15
    invoke-static {v0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 v0, 0x0

    return-object v0
.end method
