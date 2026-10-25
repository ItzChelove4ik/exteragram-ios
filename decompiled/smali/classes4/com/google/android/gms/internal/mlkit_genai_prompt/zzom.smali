.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzou;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzou;-><init>()V

    return-void
.end method

.method public static zzv(Lcom/google/common/util/concurrent/ListenableFuture;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;
    .locals 1

    .line 1
    instance-of v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    if-eqz v0, :cond_0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzom;

    return-object p0

    .line 2
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzon;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzon;-><init>(Lcom/google/common/util/concurrent/ListenableFuture;)V

    return-object v0
.end method
