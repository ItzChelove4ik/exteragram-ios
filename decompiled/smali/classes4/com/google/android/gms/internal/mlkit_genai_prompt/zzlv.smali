.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlv;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;-><init>([B)V

    return-void
.end method


# virtual methods
.method public final zza(Ljava/lang/Comparable;Ljava/lang/Comparable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;
    .locals 0

    .line 1
    invoke-interface {p1, p2}, Ljava/lang/Comparable;->compareTo(Ljava/lang/Object;)I

    move-result p0

    if-gez p0, :cond_0

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;

    move-result-object p0

    return-object p0

    :cond_0
    if-lez p0, :cond_1

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;->zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;

    move-result-object p0

    return-object p0

    :cond_1
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;

    move-result-object p0

    return-object p0
.end method

.method public final zzb()I
    .locals 0

    const/4 p0, 0x0

    return p0
.end method
