.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzen;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static synthetic zza(I)Ljava/lang/String;
    .locals 1

    const/4 v0, 0x1

    if-eq p0, v0, :cond_4

    const/4 v0, 0x2

    if-eq p0, v0, :cond_3

    const/4 v0, 0x3

    if-eq p0, v0, :cond_2

    const/4 v0, 0x4

    if-eq p0, v0, :cond_1

    const/4 v0, 0x5

    if-eq p0, v0, :cond_0

    const-string p0, "UNSPECIFIED"

    return-object p0

    :cond_0
    const-string p0, "INFERENCE_WITH_BLOCK_ATTENTION"

    return-object p0

    :cond_1
    const-string p0, "INFERENCE_WITH_PREFIX_CACHING"

    return-object p0

    :cond_2
    const-string p0, "INFERENCE_WITH_SESSION_STATE_CACHING"

    return-object p0

    :cond_3
    const-string p0, "PREFIX_CACHING"

    return-object p0

    :cond_4
    const-string p0, "BLOCK_ATTENTION"

    return-object p0
.end method
