.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private final zzb:F

.field private final zzc:I

.field private final zzd:F

.field private final zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private final zzf:I

.field private final zzg:I

.field private final zzh:I

.field private final zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;

.field private final zzj:Z

.field private final zzk:I

.field private final zzl:I

.field private final zzm:I

.field private final zzn:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;

.field private final zzo:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;

.field private final zzp:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

.field private final zzq:Z

.field private final zzr:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;FIFLcom/google/android/gms/internal/mlkit_genai_prompt/zzml;IIILcom/google/android/gms/internal/mlkit_genai_prompt/zzik;ZIIILjava/io/File;Ljava/io/File;Ljava/io/File;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;ZLcom/google/android/gms/internal/mlkit_genai_prompt/zzja;[B)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzb:F

    iput p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzc:I

    iput p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzd:F

    iput-object p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput p6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzf:I

    iput p7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzg:I

    iput p8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzh:I

    iput-object p9, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;

    iput-boolean p10, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzj:Z

    iput p11, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzk:I

    iput p12, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzl:I

    move/from16 p1, p17

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzm:I

    move-object/from16 p1, p18

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzn:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;

    move-object/from16 p1, p20

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzo:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;

    move-object/from16 p1, p21

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzp:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    move/from16 p1, p22

    iput-boolean p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzq:Z

    move-object/from16 p1, p23

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzr:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;

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
    instance-of v1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;

    const/4 v2, 0x0

    if-eqz v1, :cond_5

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v3

    invoke-virtual {v1, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_5

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzb:F

    .line 3
    invoke-static {v1}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v1

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzb()F

    move-result v3

    invoke-static {v3}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v3

    if-ne v1, v3, :cond_5

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzc:I

    .line 4
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzc()I

    move-result v3

    if-ne v1, v3, :cond_5

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzd:F

    .line 5
    invoke-static {v1}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v1

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzd()F

    move-result v3

    invoke-static {v3}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v3

    if-ne v1, v3, :cond_5

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 6
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v3

    invoke-virtual {v1, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_5

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzf:I

    .line 7
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzf()I

    move-result v3

    if-ne v1, v3, :cond_5

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzg:I

    .line 8
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzg()I

    move-result v3

    if-ne v1, v3, :cond_5

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzh:I

    .line 9
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzh()I

    move-result v3

    if-ne v1, v3, :cond_5

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;

    if-nez v1, :cond_1

    .line 10
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;

    move-result-object v1

    if-nez v1, :cond_5

    goto :goto_0

    :cond_1
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_5

    :goto_0
    iget-boolean v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzj:Z

    .line 11
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzj()Z

    move-result v3

    if-ne v1, v3, :cond_5

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzk:I

    .line 12
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzk()I

    move-result v3

    if-ne v1, v3, :cond_5

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzl:I

    .line 13
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzl()I

    move-result v3

    if-ne v1, v3, :cond_5

    .line 14
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzm()I

    .line 15
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzn()Ljava/io/File;

    .line 16
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzo()Ljava/io/File;

    .line 17
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzp()Ljava/io/File;

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzm:I

    .line 18
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzq()I

    move-result v3

    if-ne v1, v3, :cond_5

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzn:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;

    .line 19
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzr()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_5

    .line 20
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzs()I

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzo:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;

    if-nez v1, :cond_2

    .line 21
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzt()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;

    move-result-object v1

    if-nez v1, :cond_5

    goto :goto_1

    :cond_2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzt()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_5

    :goto_1
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzp:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    if-nez v1, :cond_3

    .line 22
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    move-result-object v1

    if-nez v1, :cond_5

    goto :goto_2

    :cond_3
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_4

    goto :goto_3

    :cond_4
    :goto_2
    iget-boolean v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzq:Z

    .line 23
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzv()Z

    move-result v3

    if-ne v1, v3, :cond_5

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzr:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;

    .line 24
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzw()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_5

    return v0

    :cond_5
    :goto_3
    return v2
.end method

.method public final hashCode()I
    .locals 8

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->hashCode()I

    move-result v0

    const v1, 0xf4243

    xor-int/2addr v0, v1

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzb:F

    mul-int/2addr v0, v1

    .line 2
    invoke-static {v2}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v2

    xor-int/2addr v0, v2

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzd:F

    mul-int/2addr v0, v1

    iget v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzc:I

    xor-int/2addr v0, v3

    mul-int/2addr v0, v1

    .line 3
    invoke-static {v2}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v2

    xor-int/2addr v0, v2

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    mul-int/2addr v0, v1

    .line 4
    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->hashCode()I

    move-result v2

    xor-int/2addr v0, v2

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;

    const/4 v3, 0x0

    if-nez v2, :cond_0

    move v2, v3

    goto :goto_0

    .line 5
    :cond_0
    invoke-virtual {v2}, Ljava/lang/Object;->hashCode()I

    move-result v2

    :goto_0
    mul-int/2addr v0, v1

    .line 4
    iget v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzf:I

    xor-int/2addr v0, v4

    mul-int/2addr v0, v1

    iget v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzg:I

    xor-int/2addr v0, v4

    mul-int/2addr v0, v1

    iget v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzh:I

    xor-int/2addr v0, v4

    mul-int/2addr v0, v1

    xor-int/2addr v0, v2

    mul-int/2addr v0, v1

    iget-boolean v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzj:Z

    const/16 v4, 0x4cf

    const/16 v5, 0x4d5

    const/4 v6, 0x1

    if-eq v6, v2, :cond_1

    move v2, v5

    goto :goto_1

    :cond_1
    move v2, v4

    :goto_1
    xor-int/2addr v0, v2

    mul-int/2addr v0, v1

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzk:I

    xor-int/2addr v0, v2

    mul-int/2addr v0, v1

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzl:I

    xor-int/2addr v0, v2

    const v2, -0x199d4fcd

    mul-int/2addr v0, v2

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzm:I

    xor-int/2addr v0, v2

    mul-int/2addr v0, v1

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzo:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;

    if-nez v2, :cond_2

    move v2, v3

    goto :goto_2

    .line 6
    :cond_2
    invoke-virtual {v2}, Ljava/lang/Object;->hashCode()I

    move-result v2

    :goto_2
    const v7, 0xf4696

    xor-int/2addr v0, v7

    const v7, -0x2aff6277

    mul-int/2addr v0, v7

    xor-int/2addr v0, v2

    mul-int/2addr v0, v1

    .line 4
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzp:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    if-nez v2, :cond_3

    goto :goto_3

    .line 7
    :cond_3
    invoke-virtual {v2}, Ljava/lang/Object;->hashCode()I

    move-result v3

    :goto_3
    xor-int/2addr v0, v3

    mul-int/2addr v0, v1

    .line 4
    iget-boolean v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzq:Z

    if-eq v6, v2, :cond_4

    move v4, v5

    :cond_4
    xor-int/2addr v0, v4

    mul-int/2addr v0, v1

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzr:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;

    .line 8
    invoke-virtual {p0}, Ljava/lang/Object;->hashCode()I

    move-result p0

    xor-int/2addr p0, v0

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 34

    move-object/from16 v0, p0

    .line 1
    iget-object v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v2

    iget v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzb:F

    iget-object v4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {v4}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v4

    iget-object v5, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;

    invoke-static {v5}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    iget-object v6, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzo:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;

    invoke-static {v6}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v6

    iget-object v7, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzp:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    invoke-static {v7}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v7

    iget-object v8, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzr:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;

    invoke-virtual {v8}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v3}, Ljava/lang/String;->valueOf(F)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/String;->length()I

    move-result v9

    iget v10, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzc:I

    invoke-static {v10}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v11}, Ljava/lang/String;->length()I

    move-result v11

    iget v12, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzd:F

    invoke-static {v12}, Ljava/lang/String;->valueOf(F)Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v13}, Ljava/lang/String;->length()I

    move-result v13

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v14

    iget v15, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzf:I

    invoke-static {v15}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v16

    invoke-virtual/range {v16 .. v16}, Ljava/lang/String;->length()I

    move-result v16

    move/from16 v17, v2

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzg:I

    invoke-static {v2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v18

    invoke-virtual/range {v18 .. v18}, Ljava/lang/String;->length()I

    move-result v18

    move/from16 v19, v9

    iget v9, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzh:I

    invoke-static {v9}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v20

    invoke-virtual/range {v20 .. v20}, Ljava/lang/String;->length()I

    move-result v20

    invoke-virtual {v5}, Ljava/lang/String;->length()I

    move-result v21

    move/from16 v22, v11

    iget-boolean v11, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzj:Z

    invoke-static {v11}, Ljava/lang/String;->valueOf(Z)Ljava/lang/String;

    move-result-object v23

    invoke-virtual/range {v23 .. v23}, Ljava/lang/String;->length()I

    move-result v23

    move/from16 v24, v13

    iget v13, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzk:I

    invoke-static {v13}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v25

    invoke-virtual/range {v25 .. v25}, Ljava/lang/String;->length()I

    move-result v25

    move/from16 v26, v14

    iget v14, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzl:I

    invoke-static {v14}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v27

    invoke-virtual/range {v27 .. v27}, Ljava/lang/String;->length()I

    move-result v27

    move-object/from16 v28, v8

    iget v8, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzm:I

    invoke-static {v8}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v29

    invoke-virtual/range {v29 .. v29}, Ljava/lang/String;->length()I

    move-result v29

    invoke-virtual {v6}, Ljava/lang/String;->length()I

    move-result v30

    invoke-virtual {v7}, Ljava/lang/String;->length()I

    move-result v31

    iget-boolean v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzq:Z

    invoke-static {v0}, Ljava/lang/String;->valueOf(Z)Ljava/lang/String;

    move-result-object v32

    invoke-virtual/range {v32 .. v32}, Ljava/lang/String;->length()I

    move-result v32

    invoke-virtual/range {v28 .. v28}, Ljava/lang/String;->length()I

    move-result v33

    add-int/lit8 v17, v17, 0x22

    add-int v17, v17, v19

    add-int/lit8 v17, v17, 0x7

    add-int v17, v17, v22

    add-int/lit8 v17, v17, 0x7

    add-int v17, v17, v24

    add-int/lit8 v17, v17, 0x11

    add-int v17, v17, v26

    add-int/lit8 v17, v17, 0x17

    add-int v17, v17, v16

    add-int/lit8 v17, v17, 0x17

    add-int v17, v17, v18

    add-int/lit8 v17, v17, 0xd

    add-int v17, v17, v20

    add-int/lit8 v17, v17, 0x14

    add-int v17, v17, v21

    add-int/lit8 v17, v17, 0x1a

    add-int v17, v17, v23

    add-int/lit8 v17, v17, 0x16

    add-int v17, v17, v25

    add-int/lit8 v17, v17, 0x17

    add-int v17, v17, v27

    add-int/lit8 v17, v17, 0x57

    add-int v17, v17, v29

    add-int/lit8 v17, v17, 0x5a

    add-int v17, v17, v30

    add-int/lit8 v17, v17, 0x14

    add-int v17, v17, v31

    add-int/lit8 v17, v17, 0x11

    add-int v17, v17, v32

    add-int/lit8 v17, v17, 0x8

    add-int v17, v17, v33

    move/from16 v16, v0

    new-instance v0, Ljava/lang/StringBuilder;

    move-object/from16 v18, v7

    add-int/lit8 v7, v17, 0x1

    invoke-direct {v0, v7}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v7, "LlmRequest{messages="

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", temperature="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v1, ", topK="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v10}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", topP="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v12}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v1, ", stopTokensList="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", targetReplyLengthMin="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", targetReplyLengthMax="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", numSamples="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v9}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", streamingCallback="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", shouldApplySafetyFilter="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v11}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, ", preferredImageWidth="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v13}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", preferredImageHeight="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", numSoftTokens=0, loraFile=null, drafterLoraFile=null, sessionStateFile=null, rngSeed="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", userProperties=UserProperties{isMinorUser=false}, overrideRequestKind=0, llmPrefixParam="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", llmResponseSchema="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-object/from16 v1, v18

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", enableThinking="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move/from16 v1, v16

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, ", tools="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-object/from16 v1, v28

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "}"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public final zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0
.end method

.method public final zzb()F
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzb:F

    return p0
.end method

.method public final zzc()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzc:I

    return p0
.end method

.method public final zzd()F
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzd:F

    return p0
.end method

.method public final zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0
.end method

.method public final zzf()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzf:I

    return p0
.end method

.method public final zzg()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzg:I

    return p0
.end method

.method public final zzh()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzh:I

    return p0
.end method

.method public final zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;

    return-object p0
.end method

.method public final zzj()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzj:Z

    return p0
.end method

.method public final zzk()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzk:I

    return p0
.end method

.method public final zzl()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzl:I

    return p0
.end method

.method public final zzm()I
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public final zzn()Ljava/io/File;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzo()Ljava/io/File;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzp()Ljava/io/File;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzq()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzm:I

    return p0
.end method

.method public final zzr()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzn:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;

    return-object p0
.end method

.method public final zzs()I
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public final zzt()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzo:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;

    return-object p0
.end method

.method public final zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzp:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    return-object p0
.end method

.method public final zzv()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzq:Z

    return p0
.end method

.method public final zzw()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhi;->zzr:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;

    return-object p0
.end method
