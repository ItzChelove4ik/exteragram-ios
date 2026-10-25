.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:Ljava/lang/Object;

.field zzd:Ljava/lang/Object;

.field zze:Ljava/lang/Object;

.field zzf:Ljava/lang/Object;

.field zzg:I

.field zzh:I

.field final synthetic zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

.field final synthetic zzj:Lcom/google/mlkit/genai/common/DownloadCallback;

.field private synthetic zzk:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lcom/google/mlkit/genai/common/DownloadCallback;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzj:Lcom/google/mlkit/genai/common/DownloadCallback;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzj:Lcom/google/mlkit/genai/common/DownloadCallback;

    invoke-direct {v0, v1, p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lcom/google/mlkit/genai/common/DownloadCallback;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzk:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 22

    move-object/from16 v1, p0

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v2

    iget v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzh:I

    const/4 v3, 0x3

    const/4 v4, 0x2

    const/4 v5, 0x0

    const/4 v6, 0x1

    const/4 v7, 0x0

    if-eqz v0, :cond_4

    if-eq v0, v6, :cond_3

    if-eq v0, v4, :cond_2

    if-eq v0, v3, :cond_0

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzk:Ljava/lang/Object;

    check-cast v0, Ljava/util/Iterator;

    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_b

    .line 13
    :cond_0
    iget v3, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzg:I

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzd:Ljava/lang/Object;

    .line 1
    move-object v4, v0

    check-cast v4, Ljava/util/List;

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzc:Ljava/lang/Object;

    move-object v5, v0

    check-cast v5, Ljava/util/List;

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzb:Ljava/lang/Object;

    move-object v8, v0

    check-cast v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zza:Ljava/lang/Object;

    move-object v9, v0

    check-cast v9, Ljava/util/concurrent/atomic/AtomicInteger;

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzk:Ljava/lang/Object;

    move-object v10, v0

    check-cast v10, Lkotlinx/coroutines/CoroutineScope;

    :try_start_0
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :cond_1
    move v13, v3

    move-object v12, v9

    move-object v0, v10

    goto/16 :goto_9

    :catch_0
    move-exception v0

    goto/16 :goto_7

    .line 28
    :cond_2
    iget v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzg:I

    iget-object v8, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzf:Ljava/lang/Object;

    .line 1
    check-cast v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    iget-object v9, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zze:Ljava/lang/Object;

    iget-object v10, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzd:Ljava/lang/Object;

    check-cast v10, Ljava/util/Iterator;

    iget-object v11, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzc:Ljava/lang/Object;

    check-cast v11, Ljava/util/Collection;

    iget-object v12, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzb:Ljava/lang/Object;

    check-cast v12, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    iget-object v13, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zza:Ljava/lang/Object;

    check-cast v13, Ljava/util/concurrent/atomic/AtomicInteger;

    iget-object v14, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzk:Ljava/lang/Object;

    check-cast v14, Lkotlinx/coroutines/CoroutineScope;

    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object/from16 v15, p1

    goto/16 :goto_3

    .line 6
    :cond_3
    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zza:Ljava/lang/Object;

    .line 1
    check-cast v0, Ljava/util/concurrent/atomic/AtomicInteger;

    iget-object v8, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzk:Ljava/lang/Object;

    check-cast v8, Lkotlinx/coroutines/CoroutineScope;

    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object/from16 v9, p1

    goto :goto_0

    :cond_4
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzk:Ljava/lang/Object;

    move-object v8, v0

    check-cast v8, Lkotlinx/coroutines/CoroutineScope;

    new-instance v0, Ljava/util/concurrent/atomic/AtomicInteger;

    .line 2
    invoke-direct {v0, v5}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    iget-object v9, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    invoke-static {v9}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    move-result-object v9

    .line 3
    iput-object v8, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzk:Ljava/lang/Object;

    iput-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zza:Ljava/lang/Object;

    iput v6, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzh:I

    invoke-virtual {v9, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zza(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v9

    if-eq v9, v2, :cond_11

    :goto_0
    check-cast v9, Ljava/lang/Number;

    invoke-virtual {v9}, Ljava/lang/Number;->intValue()I

    move-result v9

    if-ne v9, v6, :cond_5

    move v9, v6

    goto :goto_1

    :cond_5
    move v9, v5

    :goto_1
    iget-object v10, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    invoke-static {v10}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;)Ljava/util/List;

    move-result-object v11

    .line 4
    check-cast v11, Ljava/lang/Iterable;

    new-instance v12, Ljava/util/ArrayList;

    .line 5
    invoke-direct {v12}, Ljava/util/ArrayList;-><init>()V

    .line 6
    invoke-interface {v11}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v11

    move-object v13, v12

    move-object v12, v10

    move-object v10, v11

    move-object v11, v13

    move-object v13, v0

    move v0, v9

    :goto_2
    invoke-interface {v10}, Ljava/util/Iterator;->hasNext()Z

    move-result v9

    if-eqz v9, :cond_8

    invoke-interface {v10}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v9

    move-object v14, v9

    check-cast v14, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    iput-object v8, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzk:Ljava/lang/Object;

    iput-object v13, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zza:Ljava/lang/Object;

    iput-object v12, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzb:Ljava/lang/Object;

    iput-object v11, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzc:Ljava/lang/Object;

    iput-object v10, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzd:Ljava/lang/Object;

    iput-object v9, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zze:Ljava/lang/Object;

    iput-object v14, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzf:Ljava/lang/Object;

    iput v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzg:I

    iput v4, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzh:I

    .line 7
    invoke-virtual {v14, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zza(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v15

    if-ne v15, v2, :cond_6

    goto/16 :goto_c

    :cond_6
    move-object/from16 v21, v14

    move-object v14, v8

    move-object/from16 v8, v21

    :goto_3
    check-cast v15, Ljava/lang/Number;

    invoke-virtual {v15}, Ljava/lang/Number;->intValue()I

    move-result v15

    if-ne v15, v6, :cond_7

    invoke-static {v12}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;)Ljava/util/Map;

    move-result-object v15

    invoke-interface {v15, v8}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v8

    invoke-static {v6}, Lkotlin/coroutines/jvm/internal/Boxing;->boxBoolean(Z)Ljava/lang/Boolean;

    move-result-object v15

    invoke-static {v8, v15}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v8

    if-nez v8, :cond_7

    .line 6
    invoke-interface {v11, v9}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    :cond_7
    move-object v8, v14

    goto :goto_2

    .line 4
    :cond_8
    check-cast v11, Ljava/util/List;

    if-eqz v0, :cond_9

    move v4, v6

    goto :goto_4

    :cond_9
    move v4, v5

    .line 8
    :goto_4
    invoke-interface {v11}, Ljava/util/List;->size()I

    move-result v9

    add-int v19, v4, v9

    if-nez v19, :cond_a

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzj:Lcom/google/mlkit/genai/common/DownloadCallback;

    .line 9
    invoke-interface {v0}, Lcom/google/mlkit/genai/common/DownloadCallback;->onDownloadCompleted()V

    .line 10
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0

    .line 11
    :cond_a
    invoke-static {v7, v6, v7}, Lkotlinx/coroutines/CompletableDeferredKt;->CompletableDeferred$default(Lkotlinx/coroutines/Job;ILjava/lang/Object;)Lkotlinx/coroutines/CompletableDeferred;

    move-result-object v17

    if-eqz v0, :cond_b

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    move-result-object v0

    move-object v4, v11

    :goto_5
    move-object v15, v0

    goto :goto_6

    .line 12
    :cond_b
    invoke-static {v11}, Lkotlin/collections/CollectionsKt;->first(Ljava/util/List;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    .line 13
    check-cast v11, Ljava/lang/Iterable;

    invoke-static {v11, v6}, Lkotlin/collections/CollectionsKt;->drop(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object v4

    goto :goto_5

    .line 11
    :goto_6
    iget-object v14, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzj:Lcom/google/mlkit/genai/common/DownloadCallback;

    move-object/from16 v18, v13

    .line 14
    new-instance v13, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxz;

    const/16 v20, 0x0

    move-object/from16 v16, v0

    invoke-direct/range {v13 .. v20}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxz;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;Lcom/google/mlkit/genai/common/DownloadCallback;Lkotlinx/coroutines/CompletableDeferred;Ljava/util/concurrent/atomic/AtomicInteger;ILkotlin/coroutines/Continuation;)V

    move-object v10, v15

    move-object/from16 v0, v17

    move/from16 v9, v19

    move-object/from16 v17, v13

    move-object/from16 v13, v18

    const/16 v18, 0x3

    const/16 v19, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x0

    move-object v14, v8

    invoke-static/range {v14 .. v19}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object v8

    new-array v11, v6, [Lkotlinx/coroutines/Job;

    aput-object v8, v11, v5

    .line 15
    invoke-static {v11}, Lkotlin/collections/CollectionsKt;->mutableListOf([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v5

    .line 16
    move-object v8, v4

    check-cast v8, Ljava/util/Collection;

    invoke-interface {v8}, Ljava/util/Collection;->isEmpty()Z

    move-result v8

    if-nez v8, :cond_e

    .line 17
    :try_start_1
    iput-object v14, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzk:Ljava/lang/Object;

    iput-object v13, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zza:Ljava/lang/Object;

    iput-object v10, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzb:Ljava/lang/Object;

    iput-object v4, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzc:Ljava/lang/Object;

    iput-object v5, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzd:Ljava/lang/Object;

    iput-object v7, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zze:Ljava/lang/Object;

    iput-object v7, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzf:Ljava/lang/Object;

    iput v9, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzg:I

    iput v3, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzh:I

    invoke-interface {v0, v1}, Lkotlinx/coroutines/Deferred;->await(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    if-eq v0, v2, :cond_11

    move-object v0, v5

    move-object v5, v4

    move-object v4, v0

    move-object v12, v13

    move-object v0, v14

    move v13, v9

    goto :goto_9

    :catch_1
    move-exception v0

    move-object v3, v5

    move-object v5, v4

    move-object v4, v3

    move v3, v9

    move-object v8, v10

    move-object v9, v13

    move-object v10, v14

    .line 18
    :goto_7
    invoke-virtual {v0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    const-string v11, "DownloadManager"

    const-string v12, "Primary download start failed: "

    invoke-virtual {v12, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v11, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    move-result-object v0

    .line 19
    invoke-static {v8, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 20
    invoke-interface {v4}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_8
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_c

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lkotlinx/coroutines/Job;

    .line 21
    invoke-static {v1, v7, v6, v7}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    goto :goto_8

    .line 22
    :cond_c
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0

    .line 23
    :goto_9
    invoke-interface {v5}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :goto_a
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_d

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    move-object v10, v5

    check-cast v10, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    iget-object v9, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    iget-object v11, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzj:Lcom/google/mlkit/genai/common/DownloadCallback;

    .line 24
    new-instance v17, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;

    const/4 v14, 0x0

    move-object/from16 v8, v17

    invoke-direct/range {v8 .. v14}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;Lcom/google/mlkit/genai/common/DownloadCallback;Ljava/util/concurrent/atomic/AtomicInteger;ILkotlin/coroutines/Continuation;)V

    const/16 v18, 0x3

    const/16 v19, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x0

    move-object v14, v0

    invoke-static/range {v14 .. v19}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object v0

    .line 25
    invoke-interface {v4, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    move-object v0, v14

    goto :goto_a

    :cond_d
    move-object v5, v4

    .line 26
    :cond_e
    invoke-interface {v5}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_f
    :goto_b
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_10

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lkotlinx/coroutines/Job;

    .line 27
    iput-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzk:Ljava/lang/Object;

    iput-object v7, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zza:Ljava/lang/Object;

    iput-object v7, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzb:Ljava/lang/Object;

    iput-object v7, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzc:Ljava/lang/Object;

    iput-object v7, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzd:Ljava/lang/Object;

    iput-object v7, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zze:Ljava/lang/Object;

    iput-object v7, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzf:Ljava/lang/Object;

    const/4 v4, 0x4

    iput v4, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzya;->zzh:I

    invoke-interface {v3, v1}, Lkotlinx/coroutines/Job;->join(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v3

    if-ne v3, v2, :cond_f

    goto :goto_c

    .line 28
    :cond_10
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0

    :cond_11
    :goto_c
    return-object v2
.end method
