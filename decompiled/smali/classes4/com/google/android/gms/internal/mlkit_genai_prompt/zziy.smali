.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zziy;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzix;
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzis;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzis;-><init>()V

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzis;->zzb(Ljava/util/Map;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzix;

    return-object v0
.end method


# virtual methods
.method public abstract zza()Ljava/lang/String;
.end method

.method public abstract zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;
.end method
