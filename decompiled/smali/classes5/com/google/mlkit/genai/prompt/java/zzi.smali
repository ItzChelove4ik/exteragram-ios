.class final Lcom/google/mlkit/genai/prompt/java/zzi;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

.field final synthetic zzd:Lcom/google/mlkit/genai/prompt/java/zzm;

.field final synthetic zze:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;


# direct methods
.method public constructor <init>(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/mlkit/genai/prompt/java/zzm;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zzc:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    iput-object p2, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zzd:Lcom/google/mlkit/genai/prompt/java/zzm;

    iput-object p3, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zze:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance p1, Lcom/google/mlkit/genai/prompt/java/zzi;

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zzc:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    iget-object v1, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zzd:Lcom/google/mlkit/genai/prompt/java/zzm;

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zze:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    invoke-direct {p1, v0, v1, p0, p2}, Lcom/google/mlkit/genai/prompt/java/zzi;-><init>(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/mlkit/genai/prompt/java/zzm;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/mlkit/genai/prompt/java/zzi;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/mlkit/genai/prompt/java/zzi;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/mlkit/genai/prompt/java/zzi;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 4

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zzb:I

    if-eqz v1, :cond_0

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zza:Ljava/lang/Object;

    check-cast v0, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    goto :goto_1

    :cond_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :try_start_1
    iget-object p1, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zzc:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    iget-object v1, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zzd:Lcom/google/mlkit/genai/prompt/java/zzm;

    invoke-static {v1}, Lcom/google/mlkit/genai/prompt/java/zzm;->zza(Lcom/google/mlkit/genai/prompt/java/zzm;)Lcom/google/mlkit/genai/prompt/Caches;

    move-result-object v1

    iget-object v2, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zze:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    .line 2
    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zza:Ljava/lang/Object;

    const/4 v3, 0x1

    iput v3, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zzb:I

    invoke-interface {v1, v2, p0}, Lcom/google/mlkit/genai/prompt/Caches;->create(Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v1

    if-eq v1, v0, :cond_1

    move-object v0, p1

    move-object p1, v1

    :goto_0
    invoke-virtual {v0, p1}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->set(Ljava/lang/Object;)Z
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_2

    :cond_1
    return-object v0

    :goto_1
    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzi;->zzc:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    .line 3
    invoke-virtual {p0, p1}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    .line 4
    :goto_2
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
