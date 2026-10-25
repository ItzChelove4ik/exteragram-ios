.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;

.field final synthetic zzc:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;->zzc:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;->zzc:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-direct {p1, v0, p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 3

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    return-object p1

    :cond_0
    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;->zzc:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    const/4 v2, 0x1

    iput v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;->zza:I

    invoke-virtual {p1, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->countTokens(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_1

    return-object v0

    :cond_1
    return-object p0
.end method
