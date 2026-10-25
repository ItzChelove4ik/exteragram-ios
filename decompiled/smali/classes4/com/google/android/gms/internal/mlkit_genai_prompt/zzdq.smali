.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzi(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;
    .locals 10

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzi()Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_0

    const-string v0, ""

    :cond_0
    move-object v9, v0

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzb()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzc()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzd()I

    move-result v4

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zze()I

    move-result v5

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzf()I

    move-result v6

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzg()I

    move-result v7

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzh()I

    move-result v8

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdu;

    invoke-direct/range {v1 .. v9}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdu;-><init>(Ljava/lang/String;Ljava/lang/String;IIIIILjava/lang/String;)V

    return-object v1
.end method


# virtual methods
.method public abstract zza()Ljava/lang/String;
.end method

.method public abstract zzb()Ljava/lang/String;
.end method

.method public abstract zzc()I
.end method

.method public abstract zzd()I
.end method

.method public abstract zze()I
.end method

.method public abstract zzf()I
.end method

.method public abstract zzg()I
.end method

.method public abstract zzh()Ljava/lang/String;
.end method

.method public final zzj()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;
    .locals 8

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zza()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzb()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzc()I

    move-result v2

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzd()I

    move-result v3

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zze()I

    move-result v4

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzf()I

    move-result v5

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzg()I

    move-result v6

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzh()Ljava/lang/String;

    move-result-object v7

    invoke-static/range {v0 .. v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zza(Ljava/lang/String;Ljava/lang/String;IIIIILjava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;

    move-result-object p0

    return-object p0
.end method
