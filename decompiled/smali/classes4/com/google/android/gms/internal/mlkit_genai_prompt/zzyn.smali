.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lkotlin/jvm/functions/Function2;

.field final synthetic zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;

.field final synthetic zzd:Lkotlinx/coroutines/channels/ProducerScope;


# direct methods
.method public constructor <init>(Lkotlin/jvm/functions/Function2;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->zzb:Lkotlin/jvm/functions/Function2;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->zzd:Lkotlinx/coroutines/channels/ProducerScope;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->zzb:Lkotlin/jvm/functions/Function2;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->zzd:Lkotlinx/coroutines/channels/ProducerScope;

    invoke-direct {p1, v0, v1, p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;-><init>(Lkotlin/jvm/functions/Function2;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 3

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->zza:I

    if-eqz v1, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_2

    :catchall_0
    move-exception p1

    goto :goto_0

    :catch_0
    move-exception p1

    goto :goto_1

    :cond_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :try_start_1
    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->zzb:Lkotlin/jvm/functions/Function2;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;

    const/4 v2, 0x1

    iput v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->zza:I

    .line 2
    invoke-interface {p1, v1, p0}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_1
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-ne p0, v0, :cond_2

    return-object v0

    .line 3
    :goto_0
    new-instance v0, Lcom/google/mlkit/genai/common/GenAiException;

    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_1

    const-string v1, "Unknown error"

    :cond_1
    const/4 v2, 0x0

    invoke-direct {v0, v1, p1, v2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->zzd:Lkotlinx/coroutines/channels/ProducerScope;

    .line 4
    new-instance p1, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadFailed;

    invoke-direct {p1, v0}, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadFailed;-><init>(Lcom/google/mlkit/genai/common/GenAiException;)V

    invoke-interface {p0, p1}, Lkotlinx/coroutines/channels/SendChannel;->trySend-JP2dKIU(Ljava/lang/Object;)Ljava/lang/Object;

    .line 5
    invoke-interface {p0, v0}, Lkotlinx/coroutines/channels/SendChannel;->close(Ljava/lang/Throwable;)Z

    goto :goto_2

    .line 2
    :goto_1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;->zzd:Lkotlinx/coroutines/channels/ProducerScope;

    .line 6
    new-instance v0, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadFailed;

    invoke-direct {v0, p1}, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadFailed;-><init>(Lcom/google/mlkit/genai/common/GenAiException;)V

    invoke-interface {p0, v0}, Lkotlinx/coroutines/channels/SendChannel;->trySend-JP2dKIU(Ljava/lang/Object;)Ljava/lang/Object;

    .line 7
    invoke-interface {p0, p1}, Lkotlinx/coroutines/channels/SendChannel;->close(Ljava/lang/Throwable;)Z

    .line 8
    :cond_2
    :goto_2
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
