.class final Lcom/google/android/recaptcha/internal/zzis;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzdp;

.field final synthetic zzc:J

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzakg;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zziv;

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzdp;JLcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zziv;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzis;->zzb:Lcom/google/android/recaptcha/internal/zzdp;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzis;->zzc:J

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzis;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzis;->zze:Lcom/google/android/recaptcha/internal/zziv;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/recaptcha/internal/zzis;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzis;->zzb:Lcom/google/android/recaptcha/internal/zzdp;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzis;->zzc:J

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzis;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzis;->zze:Lcom/google/android/recaptcha/internal/zziv;

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzis;-><init>(Lcom/google/android/recaptcha/internal/zzdp;JLcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zziv;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzis;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzis;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzis;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzis;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 8

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzis;->zza:I

    const/4 v2, 0x2

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-eqz v1, :cond_2

    if-eq v1, v4, :cond_1

    if-ne v1, v2, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    :catch_0
    move-exception p1

    goto :goto_2

    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzis;->zzf:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/recaptcha/internal/zzhg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_4

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzis;->zzf:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0

    :cond_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzis;->zzf:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzis;->zzb:Lcom/google/android/recaptcha/internal/zzdp;

    iget-wide v5, p0, Lcom/google/android/recaptcha/internal/zzis;->zzc:J

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzis;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzis;->zzf:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzis;->zza:I

    invoke-interface {p1, v5, v6, v7, p0}, Lcom/google/android/recaptcha/internal/zzdp;->zza(JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_4

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzis;->zzf:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzis;->zza:I

    .line 3
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_0

    if-ne p0, v0, :cond_3

    goto :goto_3

    .line 7
    :cond_3
    :goto_1
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 1
    :goto_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzis;->zze:Lcom/google/android/recaptcha/internal/zziv;

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zziv;->zzd(Lcom/google/android/recaptcha/internal/zziv;)Lcom/google/android/recaptcha/internal/zzhu;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzhu;->zzb()Lkotlinx/coroutines/CoroutineScope;

    move-result-object v2

    .line 4
    invoke-interface {v2}, Lkotlinx/coroutines/CoroutineScope;->getCoroutineContext()Lkotlin/coroutines/CoroutineContext;

    move-result-object v2

    invoke-static {v2, v3, v4, v3}, Lkotlinx/coroutines/JobKt;->cancelChildren$default(Lkotlin/coroutines/CoroutineContext;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zziv;->zzd(Lcom/google/android/recaptcha/internal/zziv;)Lcom/google/android/recaptcha/internal/zzhu;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzhu;->zzb()Lkotlinx/coroutines/CoroutineScope;

    move-result-object v1

    .line 5
    invoke-interface {v1}, Lkotlinx/coroutines/CoroutineScope;->getCoroutineContext()Lkotlin/coroutines/CoroutineContext;

    move-result-object v1

    invoke-static {v1}, Lkotlinx/coroutines/JobKt;->getJob(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/Job;

    move-result-object v1

    invoke-interface {v1}, Lkotlinx/coroutines/Job;->getChildren()Lkotlin/sequences/Sequence;

    move-result-object v1

    invoke-static {v1}, Lkotlin/sequences/SequencesKt;->toList(Lkotlin/sequences/Sequence;)Ljava/util/List;

    move-result-object v1

    check-cast v1, Ljava/util/Collection;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzis;->zzf:Ljava/lang/Object;

    const/4 v2, 0x3

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzis;->zza:I

    invoke-static {v1, p0}, Lkotlinx/coroutines/AwaitKt;->joinAll(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_5

    :cond_4
    :goto_3
    return-object v0

    :cond_5
    move-object p0, p1

    .line 6
    :goto_4
    throw p0
.end method
