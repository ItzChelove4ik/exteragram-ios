.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
.source "SourceFile"


# instance fields
.field private zza:Landroid/os/ParcelFileDescriptor;

.field private zzb:I

.field private zzc:I

.field private zzd:I

.field private zze:Ljava/lang/String;

.field private zzf:B

.field private zzg:I

.field private zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Landroid/os/ParcelFileDescriptor;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zza:Landroid/os/ParcelFileDescriptor;

    return-object p0
.end method

.method public final zzb(F)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
    .locals 0

    iget-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    or-int/lit8 p1, p1, 0x1

    int-to-byte p1, p1

    iput-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    return-object p0
.end method

.method public final zzc(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
    .locals 0

    const/4 p1, 0x1

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzb:I

    iget-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    or-int/lit8 p1, p1, 0x2

    int-to-byte p1, p1

    iput-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    return-object p0
.end method

.method public final zzd(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
    .locals 0

    const/16 p1, 0x1f4

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzc:I

    iget-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    or-int/lit8 p1, p1, 0x4

    int-to-byte p1, p1

    iput-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    return-object p0
.end method

.method public final zze(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
    .locals 0

    const/4 p1, 0x1

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzd:I

    iget-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    or-int/lit8 p1, p1, 0x8

    int-to-byte p1, p1

    iput-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    return-object p0
.end method

.method public final zzf(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
    .locals 0

    iget-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    or-int/lit8 p1, p1, 0x10

    int-to-byte p1, p1

    iput-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    return-object p0
.end method

.method public final zzg(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
    .locals 0

    iget-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    or-int/lit8 p1, p1, 0x20

    int-to-byte p1, p1

    iput-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    return-object p0
.end method

.method public final zzh(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
    .locals 0

    iget-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    or-int/lit8 p1, p1, 0x40

    int-to-byte p1, p1

    iput-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    return-object p0
.end method

.method public final zzi(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zze:Ljava/lang/String;

    return-object p0
.end method

.method public final zzj(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
    .locals 0

    iget-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    or-int/lit8 p1, p1, -0x80

    int-to-byte p1, p1

    iput-byte p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    return-object p0
.end method

.method public final zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;
    .locals 21

    move-object/from16 v0, p0

    .line 1
    iget-byte v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    const/4 v2, -0x1

    if-ne v1, v2, :cond_1

    iget-object v5, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zza:Landroid/os/ParcelFileDescriptor;

    if-eqz v5, :cond_1

    iget v15, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzg:I

    if-nez v15, :cond_0

    goto :goto_0

    .line 12
    :cond_0
    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;

    iget v8, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzb:I

    iget v9, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzc:I

    iget v10, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzd:I

    iget-object v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zze:Ljava/lang/String;

    iget-object v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    const/16 v20, 0x0

    const/4 v4, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    const/16 v17, 0x0

    const/16 v18, 0x0

    move-object/from16 v19, v0

    move-object/from16 v16, v1

    invoke-direct/range {v3 .. v20}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeg;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Landroid/os/ParcelFileDescriptor;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;FIIILcom/google/android/gms/internal/mlkit_genai_prompt/zzev;IIIILjava/lang/String;Ljava/lang/String;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;[B)V

    return-object v3

    .line 1
    :cond_1
    :goto_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zza:Landroid/os/ParcelFileDescriptor;

    if-nez v2, :cond_2

    const-string v2, " cortanaStateFileDescriptorToWrite"

    .line 2
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_2
    iget-byte v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    and-int/lit8 v2, v2, 0x1

    if-nez v2, :cond_3

    const-string v2, " temperature"

    .line 3
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_3
    iget-byte v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    and-int/lit8 v2, v2, 0x2

    if-nez v2, :cond_4

    const-string v2, " topK"

    .line 4
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_4
    iget-byte v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    and-int/lit8 v2, v2, 0x4

    if-nez v2, :cond_5

    const-string v2, " targetReplyLengthMax"

    .line 5
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_5
    iget-byte v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    and-int/lit8 v2, v2, 0x8

    if-nez v2, :cond_6

    const-string v2, " numSamples"

    .line 6
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_6
    iget-byte v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    and-int/lit8 v2, v2, 0x10

    if-nez v2, :cond_7

    const-string v2, " preferredImageWidth"

    .line 7
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_7
    iget-byte v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    and-int/lit8 v2, v2, 0x20

    if-nez v2, :cond_8

    const-string v2, " preferredImageHeight"

    .line 8
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_8
    iget-byte v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    and-int/lit8 v2, v2, 0x40

    if-nez v2, :cond_9

    const-string v2, " numSoftTokens"

    .line 9
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_9
    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzg:I

    if-nez v2, :cond_a

    const-string v2, " cortanaType"

    .line 10
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_a
    iget-byte v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzf:B

    and-int/lit16 v0, v0, 0x80

    if-nez v0, :cond_b

    const-string v0, " tokenOffset"

    .line 11
    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_b
    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "Missing required properties:"

    invoke-virtual {v1, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 12
    invoke-static {v0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 v0, 0x0

    return-object v0
.end method

.method public final zzl(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
    .locals 0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzg:I

    return-object p0
.end method

.method public final zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzef;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    return-object p0
.end method
