.class abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzny;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static zza(D)Z
    .locals 0

    .line 1
    invoke-static {p0, p1}, Ljava/lang/Math;->getExponent(D)I

    move-result p0

    const/16 p1, 0x3ff

    if-gt p0, p1, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
