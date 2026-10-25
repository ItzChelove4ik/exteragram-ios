.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lkotlinx/coroutines/CoroutineScope;

.field final synthetic zzc:Lkotlin/jvm/functions/Function2;

.field private synthetic zze:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;->zzb:Lkotlinx/coroutines/CoroutineScope;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;->zzc:Lkotlin/jvm/functions/Function2;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;->zzb:Lkotlinx/coroutines/CoroutineScope;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;->zzc:Lkotlin/jvm/functions/Function2;

    invoke-direct {v0, v1, p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;-><init>(Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;->zze:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/channels/ProducerScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 8

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;->zze:Ljava/lang/Object;

    check-cast p1, Lkotlinx/coroutines/channels/ProducerScope;

    .line 2
    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;

    invoke-direct {v1, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;-><init>(Lkotlinx/coroutines/channels/ProducerScope;)V

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;->zzb:Lkotlinx/coroutines/CoroutineScope;

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;->zzc:Lkotlin/jvm/functions/Function2;

    .line 3
    new-instance v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;

    const/4 v4, 0x0

    invoke-direct {v5, v3, v1, p1, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyn;-><init>(Lkotlin/jvm/functions/Function2;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzym;Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)V

    const/4 v6, 0x3

    const/4 v7, 0x0

    const/4 v3, 0x0

    invoke-static/range {v2 .. v7}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object v1

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyl;

    invoke-direct {v2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyl;-><init>(Lkotlinx/coroutines/Job;)V

    const/4 v1, 0x1

    .line 4
    iput v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;->zza:I

    invoke-static {p1, v2, p0}, Lkotlinx/coroutines/channels/ProduceKt;->awaitClose(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_1

    return-object v0

    .line 5
    :cond_1
    :goto_0
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
