.class final Lcom/google/android/recaptcha/internal/zzgn;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzgo;

.field private synthetic zzd:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzgo;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p2}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzgn;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    invoke-direct {v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzgn;-><init>(Lcom/google/android/recaptcha/internal/zzgo;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzgn;->zzd:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzgn;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzgn;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzgn;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 9

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzb:I

    const/4 v2, 0x4

    const/4 v3, 0x3

    const/4 v4, 0x2

    const/4 v5, 0x1

    const/4 v6, 0x0

    if-eqz v0, :cond_4

    if-eq v0, v5, :cond_3

    if-eq v0, v4, :cond_2

    if-eq v0, v3, :cond_1

    if-ne v0, v2, :cond_0

    .line 12
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzgn;->zza:Ljava/lang/Object;

    .line 1
    check-cast v0, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzd:Ljava/lang/Object;

    check-cast v2, Ljava/lang/Exception;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_2

    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzd:Ljava/lang/Object;

    check-cast p0, Ljava/lang/Exception;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_3

    :cond_1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzd:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/recaptcha/internal/zzhg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_7

    .line 6
    :cond_2
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzgn;->zza:Ljava/lang/Object;

    .line 1
    check-cast v0, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzd:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzhg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v8, v2

    move-object v2, p1

    move-object p1, v8

    goto/16 :goto_5

    .line 14
    :cond_3
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzd:Ljava/lang/Object;

    .line 1
    move-object v7, v0

    check-cast v7, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_1
    .catch Lkotlinx/coroutines/TimeoutCancellationException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    move-object p1, v0

    move-object v0, v7

    goto :goto_1

    :catch_1
    move-exception v0

    move-object p1, v0

    move-object v0, v7

    goto/16 :goto_4

    :cond_4
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzd:Ljava/lang/Object;

    move-object v7, p1

    check-cast v7, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_1
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzgo;->zzi()Lkotlinx/coroutines/Deferred;

    move-result-object p1

    .line 2
    iput-object v7, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzd:Ljava/lang/Object;

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzb:I

    invoke-interface {p1, p0}, Lkotlinx/coroutines/Deferred;->await(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_9

    :goto_0
    check-cast p1, Lkotlin/Result;

    invoke-virtual {p1}, Lkotlin/Result;->unbox-impl()Ljava/lang/Object;

    move-result-object p1

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_1
    .catch Lkotlinx/coroutines/TimeoutCancellationException; {:try_start_1 .. :try_end_1} :catch_2
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    .line 14
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 8
    :goto_1
    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzgo;->zzi()Lkotlinx/coroutines/Deferred;

    move-result-object v4

    .line 3
    invoke-static {v4, v6, v5, v6}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzgo;->zzx(Lcom/google/android/recaptcha/internal/zzgo;)Lcom/google/android/recaptcha/internal/zzakg;

    move-result-object v4

    if-eqz v4, :cond_7

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzd:Ljava/lang/Object;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzgn;->zza:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzb:I

    .line 4
    invoke-static {v3, v4, p0}, Lcom/google/android/recaptcha/internal/zzgo;->zzk(Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v2

    if-ne v2, v1, :cond_5

    goto :goto_6

    :cond_5
    move-object v8, v2

    move-object v2, p1

    move-object p1, v8

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzd:Ljava/lang/Object;

    iput-object v6, p0, Lcom/google/android/recaptcha/internal/zzgn;->zza:Ljava/lang/Object;

    const/4 v3, 0x5

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzb:I

    .line 5
    invoke-virtual {p1, v0, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v1, :cond_6

    goto :goto_6

    :cond_6
    move-object p0, v2

    goto :goto_3

    :cond_7
    move-object p0, p1

    .line 6
    :goto_3
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzak:Lcom/google/android/recaptcha/internal/zzhe;

    .line 7
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 6
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0

    :catch_2
    move-exception v0

    move-object p0, v0

    .line 8
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzar:Lcom/google/android/recaptcha/internal/zzhe;

    .line 9
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 8
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0

    .line 13
    :goto_4
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzgo;->zzi()Lkotlinx/coroutines/Deferred;

    move-result-object v7

    .line 10
    invoke-static {v7, v6, v5, v6}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzgo;->zzx(Lcom/google/android/recaptcha/internal/zzgo;)Lcom/google/android/recaptcha/internal/zzakg;

    move-result-object v5

    if-eqz v5, :cond_a

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzd:Ljava/lang/Object;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzgn;->zza:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzb:I

    .line 11
    invoke-static {v2, v5, p0}, Lcom/google/android/recaptcha/internal/zzgo;->zzk(Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v2

    if-ne v2, v1, :cond_8

    goto :goto_6

    .line 1
    :cond_8
    :goto_5
    check-cast v2, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzd:Ljava/lang/Object;

    iput-object v6, p0, Lcom/google/android/recaptcha/internal/zzgn;->zza:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzgn;->zzb:I

    .line 12
    invoke-virtual {v2, v0, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v1, :cond_a

    :cond_9
    :goto_6
    return-object v1

    :cond_a
    move-object p0, p1

    .line 13
    :goto_7
    throw p0
.end method
