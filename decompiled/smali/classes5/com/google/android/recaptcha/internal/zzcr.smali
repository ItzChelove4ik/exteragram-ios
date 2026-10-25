.class final Lcom/google/android/recaptcha/internal/zzcr;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzct;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzmg;

.field final synthetic zzd:J

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzakg;

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzct;Lcom/google/android/recaptcha/internal/zzmg;JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzcr;->zzb:Lcom/google/android/recaptcha/internal/zzct;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzcr;->zzc:Lcom/google/android/recaptcha/internal/zzmg;

    iput-wide p3, p0, Lcom/google/android/recaptcha/internal/zzcr;->zzd:J

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzcr;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/recaptcha/internal/zzcr;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcr;->zzb:Lcom/google/android/recaptcha/internal/zzct;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzcr;->zzc:Lcom/google/android/recaptcha/internal/zzmg;

    iget-wide v3, p0, Lcom/google/android/recaptcha/internal/zzcr;->zzd:J

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzcr;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzcr;-><init>(Lcom/google/android/recaptcha/internal/zzct;Lcom/google/android/recaptcha/internal/zzmg;JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzcr;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzcr;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzcr;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzcr;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 12

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzcr;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    goto :goto_1

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzcr;->zzf:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lkotlinx/coroutines/CoroutineScope;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzcr;->zzb:Lcom/google/android/recaptcha/internal/zzct;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzct;->zza()Ljava/util/List;

    move-result-object v2

    .line 2
    invoke-interface {v2}, Ljava/util/List;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 3
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :cond_1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzct;->zza()Ljava/util/List;

    move-result-object p1

    .line 4
    check-cast p1, Ljava/lang/Iterable;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzcr;->zzc:Lcom/google/android/recaptcha/internal/zzmg;

    iget-wide v5, p0, Lcom/google/android/recaptcha/internal/zzcr;->zzd:J

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzcr;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    new-instance v9, Ljava/util/ArrayList;

    const/16 v2, 0xa

    .line 5
    invoke-static {p1, v2}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v2

    invoke-direct {v9, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 6
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 7
    move-object v4, v2

    check-cast v4, Lcom/google/android/recaptcha/internal/zzcl;

    .line 8
    new-instance v2, Lcom/google/android/recaptcha/internal/zzcq;

    const/4 v8, 0x0

    invoke-direct/range {v2 .. v8}, Lcom/google/android/recaptcha/internal/zzcq;-><init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzcl;JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    move-wide v10, v5

    move-object v8, v7

    move-object v7, v3

    const/4 v5, 0x3

    const/4 v6, 0x0

    move-object v4, v2

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-static/range {v1 .. v6}, Lkotlinx/coroutines/BuildersKt;->async$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Deferred;

    move-result-object v2

    .line 7
    invoke-interface {v9, v2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    move-object v3, v7

    move-object v7, v8

    move-wide v5, v10

    goto :goto_0

    :cond_2
    const/4 p1, 0x1

    .line 9
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzcr;->zza:I

    invoke-static {v9, p0}, Lkotlinx/coroutines/AwaitKt;->awaitAll(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_3

    return-object v0

    .line 1
    :cond_3
    :goto_1
    check-cast p1, Ljava/util/List;

    .line 10
    check-cast p1, Ljava/lang/Iterable;

    .line 11
    instance-of p0, p1, Ljava/util/Collection;

    if-eqz p0, :cond_4

    move-object p0, p1

    check-cast p0, Ljava/util/Collection;

    invoke-interface {p0}, Ljava/util/Collection;->isEmpty()Z

    move-result p0

    if-nez p0, :cond_6

    .line 12
    :cond_4
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_5
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_6

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lkotlin/Result;

    invoke-virtual {p1}, Lkotlin/Result;->unbox-impl()Ljava/lang/Object;

    move-result-object p1

    .line 10
    invoke-static {p1}, Lkotlin/Result;->isFailure-impl(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_5

    .line 14
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 13
    :cond_6
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzr:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0
.end method
