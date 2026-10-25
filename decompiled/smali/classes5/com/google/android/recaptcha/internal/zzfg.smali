.class final Lcom/google/android/recaptcha/internal/zzfg;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:J

.field zzb:Z

.field zzc:I

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzfp;

.field final synthetic zze:Lkotlin/jvm/internal/Ref$ObjectRef;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzfp;Lkotlin/jvm/internal/Ref$ObjectRef;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzfg;->zzd:Lcom/google/android/recaptcha/internal/zzfp;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzfg;->zze:Lkotlin/jvm/internal/Ref$ObjectRef;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance p1, Lcom/google/android/recaptcha/internal/zzfg;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzfg;->zzd:Lcom/google/android/recaptcha/internal/zzfp;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzfg;->zze:Lkotlin/jvm/internal/Ref$ObjectRef;

    invoke-direct {p1, v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzfg;-><init>(Lcom/google/android/recaptcha/internal/zzfp;Lkotlin/jvm/internal/Ref$ObjectRef;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzfg;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzfg;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzfg;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 7

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzfg;->zzc:I

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v3, :cond_0

    iget-boolean v1, p0, Lcom/google/android/recaptcha/internal/zzfg;->zzb:Z

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzfg;->zza:J

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :goto_0
    move p1, v1

    goto/16 :goto_5

    :cond_0
    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzfg;->zza:J

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    :catch_0
    move-exception p1

    goto :goto_3

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    const-wide/16 v4, 0x3e8

    move p1, v3

    :goto_1
    if-eqz p1, :cond_6

    :try_start_1
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzfg;->zzd:Lcom/google/android/recaptcha/internal/zzfp;

    .line 2
    iput-wide v4, p0, Lcom/google/android/recaptcha/internal/zzfg;->zza:J

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzfg;->zzc:I

    invoke-static {p1, p0}, Lcom/google/android/recaptcha/internal/zzfp;->zzg(Lcom/google/android/recaptcha/internal/zzfp;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_4

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/play/core/integrity/StandardIntegrityManager$StandardIntegrityTokenProvider;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzfg;->zzd:Lcom/google/android/recaptcha/internal/zzfp;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzfp;->zza()Lkotlinx/coroutines/CompletableDeferred;

    move-result-object v6

    .line 3
    invoke-interface {v6, p1}, Lkotlinx/coroutines/CompletableDeferred;->complete(Ljava/lang/Object;)Z

    sget-object p1, Lcom/google/android/recaptcha/internal/zzfq;->zzc:Lcom/google/android/recaptcha/internal/zzfq;

    .line 4
    invoke-static {v1, p1}, Lcom/google/android/recaptcha/internal/zzfp;->zzf(Lcom/google/android/recaptcha/internal/zzfp;Lcom/google/android/recaptcha/internal/zzfq;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    move p1, v2

    goto :goto_1

    .line 1
    :goto_3
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzfg;->zze:Lkotlin/jvm/internal/Ref$ObjectRef;

    .line 5
    iput-object p1, v1, Lkotlin/jvm/internal/Ref$ObjectRef;->element:Ljava/lang/Object;

    .line 6
    instance-of v1, p1, Lcom/google/android/play/core/integrity/StandardIntegrityException;

    if-eqz v1, :cond_2

    .line 7
    move-object v1, p1

    check-cast v1, Lcom/google/android/play/core/integrity/StandardIntegrityException;

    invoke-virtual {v1}, Lcom/google/android/play/core/integrity/StandardIntegrityException;->getErrorCode()I

    move-result v1

    const/16 v6, -0x64

    if-eq v1, v6, :cond_3

    const/16 v6, -0x12

    if-eq v1, v6, :cond_3

    const/16 v6, -0xc

    if-eq v1, v6, :cond_3

    const/4 v6, -0x8

    if-eq v1, v6, :cond_3

    const/4 v6, -0x3

    if-eq v1, v6, :cond_3

    :cond_2
    move v1, v2

    goto :goto_4

    :cond_3
    move v1, v3

    :goto_4
    if-eqz v1, :cond_5

    .line 8
    iput-wide v4, p0, Lcom/google/android/recaptcha/internal/zzfg;->zza:J

    iput-boolean v3, p0, Lcom/google/android/recaptcha/internal/zzfg;->zzb:Z

    const/4 p1, 0x2

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzfg;->zzc:I

    invoke-static {v4, v5, p0}, Lkotlinx/coroutines/DelayKt;->delay(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_4

    goto :goto_0

    :goto_5
    add-long/2addr v4, v4

    goto :goto_1

    :cond_4
    return-object v0

    .line 9
    :cond_5
    throw p1

    .line 10
    :cond_6
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
