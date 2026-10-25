.class final Lcom/google/android/recaptcha/internal/zzbw;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzby;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzby;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzbw;->zzc:Lcom/google/android/recaptcha/internal/zzby;

    const/4 p1, 0x1

    invoke-direct {p0, p1, p2}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzbw;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbw;->zzc:Lcom/google/android/recaptcha/internal/zzby;

    invoke-direct {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzbw;-><init>(Lcom/google/android/recaptcha/internal/zzby;Lkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzbw;->create(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzbw;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzbw;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 6

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzbw;->zzb:I

    if-eqz v1, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbw;->zza:Ljava/lang/Object;

    check-cast p0, Landroid/webkit/WebView;

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

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzbw;->zzc:Lcom/google/android/recaptcha/internal/zzby;

    const/4 v1, 0x0

    const/4 v2, 0x1

    .line 2
    invoke-static {v1, v2, v1}, Lkotlinx/coroutines/CompletableDeferredKt;->CompletableDeferred$default(Lkotlinx/coroutines/Job;ILjava/lang/Object;)Lkotlinx/coroutines/CompletableDeferred;

    move-result-object v3

    .line 3
    invoke-static {p1, v3}, Lcom/google/android/recaptcha/internal/zzby;->zze(Lcom/google/android/recaptcha/internal/zzby;Lkotlinx/coroutines/CompletableDeferred;)Landroid/webkit/WebView;

    move-result-object p1

    .line 4
    :try_start_1
    new-instance v4, Lcom/google/android/recaptcha/internal/zzbv;

    invoke-direct {v4, v3, v1}, Lcom/google/android/recaptcha/internal/zzbv;-><init>(Lkotlinx/coroutines/CompletableDeferred;Lkotlin/coroutines/Continuation;)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzbw;->zza:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzbw;->zzb:I

    const-wide/16 v1, 0x3a98

    invoke-static {v1, v2, v4, p0}, Lkotlinx/coroutines/TimeoutKt;->withTimeout(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    if-eq p0, v0, :cond_1

    move-object v5, p1

    move-object p1, p0

    move-object p0, v5

    :goto_0
    :try_start_2
    check-cast p1, Landroid/webkit/WebView;
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    return-object p0

    :cond_1
    return-object v0

    :catch_1
    move-exception p0

    move-object v5, p1

    move-object p1, p0

    move-object p0, v5

    .line 5
    :goto_1
    invoke-virtual {p0}, Landroid/webkit/WebView;->stopLoading()V

    .line 6
    invoke-virtual {p0}, Landroid/webkit/WebView;->destroy()V

    .line 7
    throw p1
.end method
