.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/String;

.field private final zzb:Ljava/lang/String;

.field private final zzc:Ljava/lang/Boolean;

.field private final zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private final zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

.field private final zzf:Ljava/lang/Long;

.field private final zzg:Ljava/lang/Long;

.field private final zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private final zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

.field private final zzj:Ljava/lang/Double;

.field private final zzk:Ljava/lang/Double;

.field private final zzl:I


# direct methods
.method public synthetic constructor <init>(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;Ljava/lang/Long;Ljava/lang/Long;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Ljava/lang/String;[B)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;-><init>()V

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzl:I

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zza:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzb:Ljava/lang/String;

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzc:Ljava/lang/Boolean;

    iput-object p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput-object p6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    iput-object p7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzf:Ljava/lang/Long;

    iput-object p8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzg:Ljava/lang/Long;

    iput-object p9, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput-object p10, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    iput-object p13, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzj:Ljava/lang/Double;

    iput-object p14, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzk:Ljava/lang/Double;

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
    instance-of v1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    const/4 v2, 0x0

    if-eqz v1, :cond_e

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzl:I

    if-nez v1, :cond_1

    .line 2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzt()I

    move-result v1

    if-nez v1, :cond_e

    goto :goto_0

    :cond_1
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzt()I

    move-result v3

    if-ne v1, v3, :cond_e

    :goto_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zza:Ljava/lang/String;

    if-nez v1, :cond_2

    .line 3
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zza()Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_e

    goto :goto_1

    :cond_2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zza()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_e

    :goto_1
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzb:Ljava/lang/String;

    if-nez v1, :cond_3

    .line 4
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzb()Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_e

    goto :goto_2

    :cond_3
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzb()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_e

    :goto_2
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzc:Ljava/lang/Boolean;

    if-nez v1, :cond_4

    .line 5
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzc()Ljava/lang/Boolean;

    move-result-object v1

    if-nez v1, :cond_e

    goto :goto_3

    :cond_4
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzc()Ljava/lang/Boolean;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/Boolean;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_e

    :goto_3
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    if-nez v1, :cond_5

    .line 6
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    if-nez v1, :cond_e

    goto :goto_4

    :cond_5
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v3

    invoke-virtual {v1, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_e

    :goto_4
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    if-nez v1, :cond_6

    .line 7
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    move-result-object v1

    if-nez v1, :cond_e

    goto :goto_5

    :cond_6
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_e

    :goto_5
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzf:Ljava/lang/Long;

    if-nez v1, :cond_7

    .line 8
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzf()Ljava/lang/Long;

    move-result-object v1

    if-nez v1, :cond_e

    goto :goto_6

    :cond_7
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzf()Ljava/lang/Long;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/Long;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_e

    :goto_6
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzg:Ljava/lang/Long;

    if-nez v1, :cond_8

    .line 9
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzg()Ljava/lang/Long;

    move-result-object v1

    if-nez v1, :cond_e

    goto :goto_7

    :cond_8
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzg()Ljava/lang/Long;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/Long;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_e

    :goto_7
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    if-nez v1, :cond_9

    .line 10
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    if-nez v1, :cond_e

    goto :goto_8

    :cond_9
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v3

    invoke-virtual {v1, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_e

    :goto_8
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    if-nez v1, :cond_a

    .line 11
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object v1

    if-nez v1, :cond_e

    goto :goto_9

    :cond_a
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object v3

    invoke-virtual {v1, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_e

    .line 12
    :goto_9
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzj()Ljava/lang/Long;

    .line 13
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzk()Ljava/lang/Long;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzj:Ljava/lang/Double;

    if-nez v1, :cond_b

    .line 14
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzl()Ljava/lang/Double;

    move-result-object v1

    if-nez v1, :cond_e

    goto :goto_a

    :cond_b
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzl()Ljava/lang/Double;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/Double;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_e

    :goto_a
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzk:Ljava/lang/Double;

    if-nez p0, :cond_c

    .line 15
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzm()Ljava/lang/Double;

    move-result-object p0

    if-nez p0, :cond_e

    goto :goto_b

    :cond_c
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzm()Ljava/lang/Double;

    move-result-object v1

    invoke-virtual {p0, v1}, Ljava/lang/Double;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_d

    goto :goto_c

    .line 16
    :cond_d
    :goto_b
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzn()Ljava/lang/Long;

    .line 17
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzo()Ljava/lang/Long;

    .line 18
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzp()Ljava/lang/String;

    .line 19
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzq()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 20
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzr()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 21
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzs()Ljava/lang/String;

    return v0

    :cond_e
    :goto_c
    return v2
.end method

.method public final hashCode()I
    .locals 5

    .line 11
    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzl:I

    const/4 v1, 0x0

    if-nez v0, :cond_0

    move v0, v1

    :cond_0
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zza:Ljava/lang/String;

    if-nez v2, :cond_1

    move v2, v1

    goto :goto_0

    .line 1
    :cond_1
    invoke-virtual {v2}, Ljava/lang/String;->hashCode()I

    move-result v2

    :goto_0
    const v3, 0xf4243

    xor-int/2addr v0, v3

    mul-int/2addr v0, v3

    xor-int/2addr v0, v2

    mul-int/2addr v0, v3

    .line 11
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzb:Ljava/lang/String;

    if-nez v2, :cond_2

    move v2, v1

    goto :goto_1

    .line 2
    :cond_2
    invoke-virtual {v2}, Ljava/lang/String;->hashCode()I

    move-result v2

    :goto_1
    xor-int/2addr v0, v2

    mul-int/2addr v0, v3

    .line 11
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzc:Ljava/lang/Boolean;

    if-nez v2, :cond_3

    move v2, v1

    goto :goto_2

    .line 3
    :cond_3
    invoke-virtual {v2}, Ljava/lang/Boolean;->hashCode()I

    move-result v2

    :goto_2
    xor-int/2addr v0, v2

    mul-int/2addr v0, v3

    .line 11
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    if-nez v2, :cond_4

    move v2, v1

    goto :goto_3

    .line 4
    :cond_4
    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->hashCode()I

    move-result v2

    :goto_3
    xor-int/2addr v0, v2

    mul-int/2addr v0, v3

    .line 11
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    if-nez v2, :cond_5

    move v2, v1

    goto :goto_4

    .line 5
    :cond_5
    invoke-virtual {v2}, Ljava/lang/Object;->hashCode()I

    move-result v2

    :goto_4
    xor-int/2addr v0, v2

    mul-int/2addr v0, v3

    .line 11
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzf:Ljava/lang/Long;

    if-nez v2, :cond_6

    move v2, v1

    goto :goto_5

    .line 6
    :cond_6
    invoke-virtual {v2}, Ljava/lang/Long;->hashCode()I

    move-result v2

    :goto_5
    xor-int/2addr v0, v2

    mul-int/2addr v0, v3

    .line 11
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzg:Ljava/lang/Long;

    if-nez v2, :cond_7

    move v2, v1

    goto :goto_6

    .line 7
    :cond_7
    invoke-virtual {v2}, Ljava/lang/Long;->hashCode()I

    move-result v2

    :goto_6
    xor-int/2addr v0, v2

    mul-int/2addr v0, v3

    .line 11
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    if-nez v2, :cond_8

    move v2, v1

    goto :goto_7

    .line 8
    :cond_8
    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->hashCode()I

    move-result v2

    :goto_7
    xor-int/2addr v0, v2

    mul-int/2addr v0, v3

    .line 11
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    if-nez v2, :cond_9

    move v2, v1

    goto :goto_8

    .line 9
    :cond_9
    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->hashCode()I

    move-result v2

    :goto_8
    xor-int/2addr v0, v2

    .line 11
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzj:Ljava/lang/Double;

    if-nez v2, :cond_a

    move v2, v1

    goto :goto_9

    .line 10
    :cond_a
    invoke-virtual {v2}, Ljava/lang/Double;->hashCode()I

    move-result v2

    :goto_9
    const v4, 0x22cd8cdb

    mul-int/2addr v0, v4

    xor-int/2addr v0, v2

    mul-int/2addr v0, v3

    .line 11
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzk:Ljava/lang/Double;

    if-nez p0, :cond_b

    goto :goto_a

    :cond_b
    invoke-virtual {p0}, Ljava/lang/Double;->hashCode()I

    move-result v1

    :goto_a
    xor-int p0, v0, v1

    const v0, 0x103e4359

    mul-int/2addr p0, v0

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 25

    move-object/from16 v0, p0

    .line 1
    iget-object v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    iget-object v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iget-object v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    iget-object v4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-static {v4}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-static {v3}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-static {v2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    iget-object v5, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zza:Ljava/lang/String;

    invoke-static {v5}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/String;->length()I

    move-result v6

    iget-object v7, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzb:Ljava/lang/String;

    invoke-static {v7}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/String;->length()I

    move-result v8

    iget-object v9, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzc:Ljava/lang/Boolean;

    invoke-static {v9}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/String;->length()I

    move-result v10

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v11

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v12

    iget-object v13, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzf:Ljava/lang/Long;

    invoke-static {v13}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v14

    invoke-virtual {v14}, Ljava/lang/String;->length()I

    move-result v14

    iget-object v15, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzg:Ljava/lang/Long;

    invoke-static {v15}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v16

    invoke-virtual/range {v16 .. v16}, Ljava/lang/String;->length()I

    move-result v16

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v17

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v18

    move/from16 v19, v6

    iget-object v6, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzj:Ljava/lang/Double;

    invoke-static {v6}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v20

    invoke-virtual/range {v20 .. v20}, Ljava/lang/String;->length()I

    move-result v20

    move/from16 v21, v8

    iget-object v8, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzk:Ljava/lang/Double;

    invoke-static {v8}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v22

    invoke-virtual/range {v22 .. v22}, Ljava/lang/String;->length()I

    move-result v22

    iget v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzl:I

    move/from16 p0, v0

    new-instance v0, Ljava/lang/StringBuilder;

    move/from16 v23, v10

    invoke-static/range {p0 .. p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzia;->zza(I)Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/String;->length()I

    move-result v24

    add-int/lit8 v24, v24, 0x20

    add-int v24, v24, v19

    add-int/lit8 v24, v24, 0xe

    add-int v24, v24, v21

    add-int/lit8 v24, v24, 0xb

    add-int v24, v24, v23

    add-int/lit8 v24, v24, 0xd

    add-int v24, v24, v11

    add-int/lit8 v24, v24, 0x8

    add-int v24, v24, v12

    add-int/lit8 v24, v24, 0xb

    add-int v24, v24, v14

    add-int/lit8 v24, v24, 0xb

    add-int v24, v24, v16

    add-int/lit8 v24, v24, 0xb

    add-int v24, v24, v17

    add-int/lit8 v24, v24, 0xd

    add-int v24, v24, v18

    add-int/lit8 v24, v24, 0x32

    add-int v24, v24, v20

    add-int/lit8 v24, v24, 0xa

    add-int v24, v24, v22

    add-int/lit8 v11, v24, 0x5e

    invoke-direct {v0, v11}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v11, "LlmResponseSchema{type="

    invoke-virtual {v0, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v10, ", format="

    invoke-virtual {v0, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, ", description="

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, ", nullable="

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v5, ", enumValues="

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, ", items="

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ", maxItems="

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, ", minItems="

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, ", required="

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, ", properties="

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", minProperties=null, maxProperties=null, minimum="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ", maximum="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ", minLength=null, maxLength=null, pattern=null, anyOf=null, propertyOrdering=null, title=null}"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public final zza()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zza:Ljava/lang/String;

    return-object p0
.end method

.method public final zzb()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzb:Ljava/lang/String;

    return-object p0
.end method

.method public final zzc()Ljava/lang/Boolean;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzc:Ljava/lang/Boolean;

    return-object p0
.end method

.method public final zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0
.end method

.method public final zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    return-object p0
.end method

.method public final zzf()Ljava/lang/Long;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzf:Ljava/lang/Long;

    return-object p0
.end method

.method public final zzg()Ljava/lang/Long;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzg:Ljava/lang/Long;

    return-object p0
.end method

.method public final zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0
.end method

.method public final zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    return-object p0
.end method

.method public final zzj()Ljava/lang/Long;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzk()Ljava/lang/Long;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzl()Ljava/lang/Double;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzj:Ljava/lang/Double;

    return-object p0
.end method

.method public final zzm()Ljava/lang/Double;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzk:Ljava/lang/Double;

    return-object p0
.end method

.method public final zzn()Ljava/lang/Long;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzo()Ljava/lang/Long;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzp()Ljava/lang/String;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzq()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzr()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzs()Ljava/lang/String;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzt()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhk;->zzl:I

    return p0
.end method
