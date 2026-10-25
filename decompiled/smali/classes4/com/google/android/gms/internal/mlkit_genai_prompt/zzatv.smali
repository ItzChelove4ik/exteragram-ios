.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatv;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;


# instance fields
.field final synthetic zza:Lcom/google/mlkit/genai/common/DownloadCallback;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Lcom/google/mlkit/genai/common/DownloadCallback;)V
    .locals 0

    .line 1
    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatv;->zza:Lcom/google/mlkit/genai/common/DownloadCallback;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;J)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatv;->zza:Lcom/google/mlkit/genai/common/DownloadCallback;

    invoke-interface {p0, p2, p3}, Lcom/google/mlkit/genai/common/DownloadCallback;->onDownloadStarted(J)V

    return-void
.end method

.method public final zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;)V
    .locals 1
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "WrongConstant"
        }
    .end annotation

    .line 1
    new-instance p1, Lcom/google/mlkit/genai/common/GenAiException;

    invoke-virtual {p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzg()I

    move-result p2

    .line 2
    invoke-virtual {p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzh()Lj$/time/Duration;

    move-result-object v0

    invoke-direct {p1, p3, p2, v0}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/Throwable;ILj$/time/Duration;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatv;->zza:Lcom/google/mlkit/genai/common/DownloadCallback;

    .line 1
    invoke-interface {p0, p1}, Lcom/google/mlkit/genai/common/DownloadCallback;->onDownloadFailed(Lcom/google/mlkit/genai/common/GenAiException;)V

    return-void
.end method

.method public final zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;J)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatv;->zza:Lcom/google/mlkit/genai/common/DownloadCallback;

    invoke-interface {p0, p2, p3}, Lcom/google/mlkit/genai/common/DownloadCallback;->onDownloadProgress(J)V

    return-void
.end method

.method public final zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatv;->zza:Lcom/google/mlkit/genai/common/DownloadCallback;

    invoke-interface {p0}, Lcom/google/mlkit/genai/common/DownloadCallback;->onDownloadCompleted()V

    return-void
.end method
