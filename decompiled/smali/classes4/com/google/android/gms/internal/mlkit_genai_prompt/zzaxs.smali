.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxs;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final zza(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    .line 1
    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;

    .line 2
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;

    .line 3
    invoke-virtual {p1}, Ljava/util/AbstractMap;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_1

    .line 4
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;->zze()Z

    move-result v0

    if-nez v0, :cond_0

    .line 5
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;

    move-result-object p0

    .line 6
    :cond_0
    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;)V

    :cond_1
    return-object p0
.end method
