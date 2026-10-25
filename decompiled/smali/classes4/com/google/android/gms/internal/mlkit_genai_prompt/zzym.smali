.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/mlkit/genai/common/DownloadCallback;


# instance fields
.field final synthetic zza:Lkotlinx/coroutines/channels/ProducerScope;


# direct methods
.method public constructor <init>(Lkotlinx/coroutines/channels/ProducerScope;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;->zza:Lkotlinx/coroutines/channels/ProducerScope;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onDownloadCompleted()V
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;->zza:Lkotlinx/coroutines/channels/ProducerScope;

    sget-object v0, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadCompleted;->INSTANCE:Lcom/google/mlkit/genai/common/DownloadStatus$DownloadCompleted;

    invoke-interface {p0, v0}, Lkotlinx/coroutines/channels/SendChannel;->trySend-JP2dKIU(Ljava/lang/Object;)Ljava/lang/Object;

    const/4 v0, 0x0

    .line 2
    invoke-interface {p0, v0}, Lkotlinx/coroutines/channels/SendChannel;->close(Ljava/lang/Throwable;)Z

    return-void
.end method

.method public final onDownloadFailed(Lcom/google/mlkit/genai/common/GenAiException;)V
    .locals 1

    .line 1
    new-instance v0, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadFailed;

    invoke-direct {v0, p1}, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadFailed;-><init>(Lcom/google/mlkit/genai/common/GenAiException;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;->zza:Lkotlinx/coroutines/channels/ProducerScope;

    invoke-interface {p0, v0}, Lkotlinx/coroutines/channels/SendChannel;->trySend-JP2dKIU(Ljava/lang/Object;)Ljava/lang/Object;

    .line 2
    invoke-interface {p0, p1}, Lkotlinx/coroutines/channels/SendChannel;->close(Ljava/lang/Throwable;)Z

    return-void
.end method

.method public final onDownloadProgress(J)V
    .locals 1

    .line 1
    new-instance v0, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadProgress;

    invoke-direct {v0, p1, p2}, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadProgress;-><init>(J)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;->zza:Lkotlinx/coroutines/channels/ProducerScope;

    invoke-interface {p0, v0}, Lkotlinx/coroutines/channels/SendChannel;->trySend-JP2dKIU(Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method public final onDownloadStarted(J)V
    .locals 1

    .line 1
    new-instance v0, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadStarted;

    invoke-direct {v0, p1, p2}, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadStarted;-><init>(J)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;->zza:Lkotlinx/coroutines/channels/ProducerScope;

    invoke-interface {p0, v0}, Lkotlinx/coroutines/channels/SendChannel;->trySend-JP2dKIU(Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method
