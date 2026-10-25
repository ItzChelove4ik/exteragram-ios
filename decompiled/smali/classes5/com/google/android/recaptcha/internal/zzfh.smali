.class final Lcom/google/android/recaptcha/internal/zzfh;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzfp;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzfp;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzfh;->zzc:Lcom/google/android/recaptcha/internal/zzfp;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p2}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 0

    new-instance p1, Lcom/google/android/recaptcha/internal/zzfh;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzfh;->zzc:Lcom/google/android/recaptcha/internal/zzfp;

    invoke-direct {p1, p0, p2}, Lcom/google/android/recaptcha/internal/zzfh;-><init>(Lcom/google/android/recaptcha/internal/zzfp;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzfh;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzfh;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzfh;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 9

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzfh;->zzb:I

    if-eqz v1, :cond_0

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzfh;->zza:Ljava/lang/Object;

    move-object v1, v0

    check-cast v1, Lkotlin/jvm/internal/Ref$ObjectRef;

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    move-object p1, v0

    goto :goto_1

    :cond_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    new-instance v1, Lkotlin/jvm/internal/Ref$ObjectRef;

    invoke-direct {v1}, Lkotlin/jvm/internal/Ref$ObjectRef;-><init>()V

    .line 3
    :try_start_1
    new-instance p1, Lcom/google/android/recaptcha/internal/zzfg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzfh;->zzc:Lcom/google/android/recaptcha/internal/zzfp;

    const/4 v3, 0x0

    invoke-direct {p1, v2, v1, v3}, Lcom/google/android/recaptcha/internal/zzfg;-><init>(Lcom/google/android/recaptcha/internal/zzfp;Lkotlin/jvm/internal/Ref$ObjectRef;Lkotlin/coroutines/Continuation;)V

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzfh;->zza:Ljava/lang/Object;

    const/4 v2, 0x1

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzfh;->zzb:I

    const-wide/32 v2, 0xea60

    invoke-static {v2, v3, p1, p0}, Lkotlinx/coroutines/TimeoutKt;->withTimeout(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    if-ne p0, v0, :cond_1

    return-object v0

    .line 12
    :cond_1
    :goto_0
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 3
    :goto_1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzfh;->zzc:Lcom/google/android/recaptcha/internal/zzfp;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzfp;->zza()Lkotlinx/coroutines/CompletableDeferred;

    move-result-object v0

    .line 4
    iget-object v2, v1, Lkotlin/jvm/internal/Ref$ObjectRef;->element:Ljava/lang/Object;

    check-cast v2, Ljava/lang/Throwable;

    if-nez v2, :cond_2

    move-object v2, p1

    .line 5
    :cond_2
    invoke-interface {v0, v2}, Lkotlinx/coroutines/CompletableDeferred;->completeExceptionally(Ljava/lang/Throwable;)Z

    sget-object v0, Lcom/google/android/recaptcha/internal/zzfq;->zza:Lcom/google/android/recaptcha/internal/zzfq;

    .line 6
    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzfp;->zzf(Lcom/google/android/recaptcha/internal/zzfp;Lcom/google/android/recaptcha/internal/zzfq;)V

    .line 7
    new-instance v2, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    .line 8
    iget-object p0, v1, Lkotlin/jvm/internal/Ref$ObjectRef;->element:Ljava/lang/Object;

    check-cast p0, Ljava/lang/Throwable;

    if-nez p0, :cond_3

    move-object p0, p1

    .line 9
    :cond_3
    instance-of v0, p0, Lcom/google/android/play/core/integrity/StandardIntegrityException;

    if-eqz v0, :cond_9

    .line 10
    check-cast p0, Lcom/google/android/play/core/integrity/StandardIntegrityException;

    invoke-virtual {p0}, Lcom/google/android/play/core/integrity/StandardIntegrityException;->getErrorCode()I

    move-result p0

    const/16 v0, -0x64

    if-eq p0, v0, :cond_8

    const/16 v0, -0xc

    if-eq p0, v0, :cond_7

    const/4 v0, -0x3

    if-eq p0, v0, :cond_6

    const/4 v0, -0x2

    if-eq p0, v0, :cond_5

    const/4 v0, -0x1

    if-eq p0, v0, :cond_4

    packed-switch p0, :pswitch_data_0

    packed-switch p0, :pswitch_data_1

    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zza:Lcom/google/android/recaptcha/internal/zzhe;

    :goto_2
    move-object v4, p0

    goto :goto_3

    .line 7
    :pswitch_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzU:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :pswitch_1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzV:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :pswitch_2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzW:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :pswitch_3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzX:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :pswitch_4
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzY:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :pswitch_5
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzaa:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :pswitch_6
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzab:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :pswitch_7
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzac:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :pswitch_8
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzad:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :pswitch_9
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzae:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :pswitch_a
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzaf:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :cond_4
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzR:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :cond_5
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzS:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :cond_6
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzT:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :cond_7
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzZ:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :cond_8
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zzag:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    :cond_9
    sget-object p0, Lcom/google/android/recaptcha/internal/zzhe;->zza:Lcom/google/android/recaptcha/internal/zzhe;

    goto :goto_2

    .line 11
    :goto_3
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v5

    const/16 v7, 0x8

    const/4 v8, 0x0

    const/4 v6, 0x0

    .line 7
    invoke-direct/range {v2 .. v8}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v2

    :pswitch_data_0
    .packed-switch -0x13
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
    .end packed-switch

    :pswitch_data_1
    .packed-switch -0x9
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
