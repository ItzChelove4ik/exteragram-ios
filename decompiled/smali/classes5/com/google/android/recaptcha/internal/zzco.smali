.class final Lcom/google/android/recaptcha/internal/zzco;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzct;

.field final synthetic zzc:Ljava/lang/String;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzmg;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzaka;

.field final synthetic zzf:J

.field private synthetic zzg:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzct;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzaka;JLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzco;->zzb:Lcom/google/android/recaptcha/internal/zzct;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzco;->zzc:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzco;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzco;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    iput-wide p5, p0, Lcom/google/android/recaptcha/internal/zzco;->zzf:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p7}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 8

    new-instance v0, Lcom/google/android/recaptcha/internal/zzco;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzco;->zzb:Lcom/google/android/recaptcha/internal/zzct;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzco;->zzc:Ljava/lang/String;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzco;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzco;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    iget-wide v5, p0, Lcom/google/android/recaptcha/internal/zzco;->zzf:J

    move-object v7, p2

    invoke-direct/range {v0 .. v7}, Lcom/google/android/recaptcha/internal/zzco;-><init>(Lcom/google/android/recaptcha/internal/zzct;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzaka;JLkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzco;->zzg:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzco;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzco;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzco;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 20

    move-object/from16 v0, p0

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzco;->zza:I

    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v2, :cond_0

    move-object/from16 v2, p1

    goto/16 :goto_2

    :cond_0
    iget-object v2, v0, Lcom/google/android/recaptcha/internal/zzco;->zzg:Ljava/lang/Object;

    move-object v3, v2

    check-cast v3, Lkotlinx/coroutines/CoroutineScope;

    iget-object v2, v0, Lcom/google/android/recaptcha/internal/zzco;->zzb:Lcom/google/android/recaptcha/internal/zzct;

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzct;->zza()Ljava/util/List;

    move-result-object v2

    .line 2
    check-cast v2, Ljava/lang/Iterable;

    new-instance v9, Ljava/util/ArrayList;

    .line 3
    invoke-direct {v9}, Ljava/util/ArrayList;-><init>()V

    .line 4
    invoke-interface {v2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_1
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_2

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    move-object v5, v4

    check-cast v5, Lcom/google/android/recaptcha/internal/zzcl;

    .line 2
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzcl;->zza()Z

    move-result v5

    if-eqz v5, :cond_1

    .line 4
    invoke-interface {v9, v4}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 5
    :cond_2
    invoke-interface {v9}, Ljava/util/List;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_7

    .line 7
    iget-object v11, v0, Lcom/google/android/recaptcha/internal/zzco;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v13, v0, Lcom/google/android/recaptcha/internal/zzco;->zzc:Ljava/lang/String;

    iget-object v14, v0, Lcom/google/android/recaptcha/internal/zzco;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    iget-wide v4, v0, Lcom/google/android/recaptcha/internal/zzco;->zzf:J

    new-instance v2, Ljava/util/ArrayList;

    const/16 v6, 0xa

    .line 8
    invoke-static {v9, v6}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v6

    invoke-direct {v2, v6}, Ljava/util/ArrayList;-><init>(I)V

    .line 9
    invoke-virtual {v9}, Ljava/util/ArrayList;->size()I

    move-result v6

    const/4 v7, 0x0

    :goto_1
    if-ge v7, v6, :cond_3

    invoke-virtual {v9, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v8

    add-int/lit8 v18, v7, 0x1

    .line 10
    move-object v12, v8

    check-cast v12, Lcom/google/android/recaptcha/internal/zzcl;

    .line 11
    new-instance v10, Lcom/google/android/recaptcha/internal/zzcn;

    const/16 v17, 0x0

    move-wide v15, v4

    invoke-direct/range {v10 .. v17}, Lcom/google/android/recaptcha/internal/zzcn;-><init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzcl;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;JLkotlin/coroutines/Continuation;)V

    const/4 v7, 0x3

    const/4 v8, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    move-object/from16 v19, v10

    move v10, v6

    move-object/from16 v6, v19

    invoke-static/range {v3 .. v8}, Lkotlinx/coroutines/BuildersKt;->async$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Deferred;

    move-result-object v4

    .line 10
    invoke-interface {v2, v4}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    move v6, v10

    move-wide v4, v15

    move/from16 v7, v18

    goto :goto_1

    :cond_3
    const/4 v3, 0x1

    .line 12
    iput v3, v0, Lcom/google/android/recaptcha/internal/zzco;->zza:I

    invoke-static {v2, v0}, Lkotlinx/coroutines/AwaitKt;->awaitAll(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v2

    if-ne v2, v1, :cond_4

    return-object v1

    .line 1
    :cond_4
    :goto_2
    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzco;->zzc:Ljava/lang/String;

    check-cast v2, Ljava/util/List;

    .line 13
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaku;->zza()Lcom/google/android/recaptcha/internal/zzakt;

    move-result-object v1

    invoke-virtual {v1, v0}, Lcom/google/android/recaptcha/internal/zzakt;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakt;

    .line 14
    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_5
    :goto_3
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_6

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lkotlin/Result;

    invoke-virtual {v2}, Lkotlin/Result;->unbox-impl()Ljava/lang/Object;

    move-result-object v2

    .line 15
    invoke-static {v2}, Lkotlin/Result;->isSuccess-impl(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_5

    check-cast v2, Lcom/google/android/recaptcha/internal/zzaku;

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzafq;->zzq(Lcom/google/android/recaptcha/internal/zzafv;)Lcom/google/android/recaptcha/internal/zzafq;

    goto :goto_3

    .line 16
    :cond_6
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzaku;

    return-object v0

    .line 6
    :cond_7
    new-instance v1, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhe;->zzr:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v6, 0xc

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v1
.end method
