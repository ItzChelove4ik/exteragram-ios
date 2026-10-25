.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/mlkit/genai/common/DownloadCallback;


# instance fields
.field final synthetic zza:Z

.field final synthetic zzb:Lcom/google/mlkit/genai/common/DownloadCallback;

.field final synthetic zzc:Lkotlinx/coroutines/CompletableDeferred;

.field final synthetic zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

.field final synthetic zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

.field final synthetic zzf:Lkotlin/jvm/functions/Function0;


# direct methods
.method public constructor <init>(ZLcom/google/mlkit/genai/common/DownloadCallback;Lkotlinx/coroutines/CompletableDeferred;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;Lkotlin/jvm/functions/Function0;)V
    .locals 0

    iput-boolean p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zza:Z

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zzb:Lcom/google/mlkit/genai/common/DownloadCallback;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    iput-object p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    iput-object p6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zzf:Lkotlin/jvm/functions/Function0;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onDownloadCompleted()V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zzf:Lkotlin/jvm/functions/Function0;

    invoke-interface {p0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    return-void
.end method

.method public final onDownloadFailed(Lcom/google/mlkit/genai/common/GenAiException;)V
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    if-eqz v0, :cond_0

    invoke-interface {v0, p1}, Lkotlinx/coroutines/CompletableDeferred;->completeExceptionally(Ljava/lang/Throwable;)Z

    :cond_0
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zzb:Lcom/google/mlkit/genai/common/DownloadCallback;

    .line 2
    invoke-static {v0, v1, p1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zzi(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;Lcom/google/mlkit/genai/common/GenAiException;Lcom/google/mlkit/genai/common/DownloadCallback;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zzf:Lkotlin/jvm/functions/Function0;

    .line 3
    invoke-interface {p0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    return-void
.end method

.method public final onDownloadProgress(J)V
    .locals 1

    .line 1
    iget-boolean v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zza:Z

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zzb:Lcom/google/mlkit/genai/common/DownloadCallback;

    invoke-interface {p0, p1, p2}, Lcom/google/mlkit/genai/common/DownloadCallback;->onDownloadProgress(J)V

    :cond_0
    return-void
.end method

.method public final onDownloadStarted(J)V
    .locals 1

    .line 1
    iget-boolean v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zza:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zzb:Lcom/google/mlkit/genai/common/DownloadCallback;

    invoke-interface {v0, p1, p2}, Lcom/google/mlkit/genai/common/DownloadCallback;->onDownloadStarted(J)V

    :cond_0
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyc;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    if-eqz p0, :cond_1

    .line 2
    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-interface {p0, p1}, Lkotlinx/coroutines/CompletableDeferred;->complete(Ljava/lang/Object;)Z

    :cond_1
    return-void
.end method
