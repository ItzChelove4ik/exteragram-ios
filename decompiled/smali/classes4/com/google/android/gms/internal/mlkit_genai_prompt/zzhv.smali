.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzf(Ljava/lang/String;FILcom/google/android/gms/internal/mlkit_genai_prompt/zzho;Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;
    .locals 6

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;

    if-nez p4, :cond_0

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p4

    :goto_0
    move-object v1, p0

    move v2, p1

    move v3, p2

    move-object v4, p3

    move-object v5, p4

    goto :goto_1

    :cond_0
    invoke-static {p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p4

    goto :goto_0

    :goto_1
    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhg;-><init>(Ljava/lang/String;FILcom/google/android/gms/internal/mlkit_genai_prompt/zzho;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)V

    return-object v0
.end method


# virtual methods
.method public abstract zza()Ljava/lang/String;
.end method

.method public abstract zzb()F
.end method

.method public abstract zzc()I
.end method

.method public abstract zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;
.end method

.method public abstract zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
.end method
