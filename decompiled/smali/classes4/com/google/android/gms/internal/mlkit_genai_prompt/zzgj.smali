.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgj;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzar;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgk;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgk;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgk;

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzar;-><init>()V

    return-void
.end method


# virtual methods
.method public final zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgk;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;)V

    return-void
.end method

.method public final zzc(ILjava/lang/String;)V
    .locals 1

    const/4 v0, 0x1

    if-eq p1, v0, :cond_2

    const/4 v0, 0x2

    if-eq p1, v0, :cond_1

    const/4 v0, 0x3

    if-eq p1, v0, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    :cond_0
    const/16 p1, 0x25f

    goto :goto_0

    :cond_1
    const/16 p1, 0x25c

    goto :goto_0

    :cond_2
    const/16 p1, 0x259

    .line 1
    :goto_0
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgk;

    invoke-static {p2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    const-string v0, "AiCore service is not connected. Service provider failure: "

    invoke-virtual {v0, p2}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    const/4 v0, 0x0

    .line 2
    invoke-static {p1, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p1

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;)V

    return-void
.end method
