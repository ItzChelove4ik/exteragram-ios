.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazh;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazg;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;

    return-object v0
.end method

.method public static zzb(I)I
    .locals 2

    const/4 v0, 0x1

    if-eqz p0, :cond_2

    const/4 v1, 0x2

    if-eq p0, v0, :cond_1

    if-eq p0, v1, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    const/4 p0, 0x3

    return p0

    :cond_1
    return v1

    :cond_2
    return v0
.end method
