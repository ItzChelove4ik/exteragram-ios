.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:I

.field final synthetic zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;

.field final synthetic zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

.field final synthetic zzf:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

.field private synthetic zzg:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzf:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 3

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzf:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    invoke-direct {v0, v1, v2, p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzg:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 20

    move-object/from16 v1, p0

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v2, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzc:I

    const-string v3, "Cache creation failed for prefix: "

    const-string v4, "CacheCreationProcessor"

    const/4 v6, 0x1

    const/4 v7, 0x0

    const-string v8, "Future not found in map for request with prefix: "

    if-eqz v2, :cond_1

    if-eq v2, v6, :cond_0

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zza:Ljava/lang/Object;

    move-object v2, v0

    check-cast v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzg:Ljava/lang/Object;

    move-object v6, v0

    check-cast v6, Ljava/lang/String;

    :try_start_0
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move-object v5, v2

    move-object/from16 v2, p1

    goto/16 :goto_2

    :catchall_0
    move-exception v0

    goto/16 :goto_8

    :catch_0
    move-exception v0

    goto/16 :goto_7

    .line 11
    :cond_0
    iget-object v2, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzb:Ljava/lang/Object;

    .line 1
    check-cast v2, Lkotlinx/coroutines/Deferred;

    iget-object v6, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zza:Ljava/lang/Object;

    check-cast v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    iget-object v9, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzg:Ljava/lang/Object;

    check-cast v9, Ljava/lang/String;

    :try_start_1
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-object v5, v6

    move-object/from16 v6, p1

    goto :goto_1

    :catchall_1
    move-exception v0

    move-object v6, v9

    goto/16 :goto_8

    :catch_1
    move-exception v0

    move-object v2, v6

    :goto_0
    move-object v6, v9

    goto/16 :goto_7

    :cond_1
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object v2, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzg:Ljava/lang/Object;

    move-object v9, v2

    check-cast v9, Lkotlinx/coroutines/CoroutineScope;

    iget-object v2, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;

    iget-object v15, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;->zza()Ljava/lang/String;

    move-result-object v10

    invoke-static {v15}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;)Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v11

    .line 2
    iget-object v12, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzf:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    invoke-interface {v11, v12, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 3
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    move-result-object v2

    invoke-static {v15}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;)Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v11

    .line 4
    invoke-virtual {v11, v12, v2}, Ljava/util/concurrent/ConcurrentHashMap;->putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v11

    if-nez v11, :cond_7

    move-object v11, v12

    .line 8
    new-instance v12, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwi;

    invoke-direct {v12, v2, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwi;-><init>(Lcom/google/common/util/concurrent/ListenableFuture;Lkotlin/coroutines/Continuation;)V

    const/4 v13, 0x3

    const/4 v14, 0x0

    move-object/from16 v16, v10

    const/4 v10, 0x0

    move-object/from16 v17, v11

    const/4 v11, 0x0

    move-object/from16 v5, v16

    move-object/from16 v18, v17

    .line 7
    invoke-static/range {v9 .. v14}, Lkotlinx/coroutines/BuildersKt;->async$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Deferred;

    move-result-object v9

    .line 9
    new-instance v10, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwj;

    invoke-direct {v10, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwj;-><init>(Lcom/google/common/util/concurrent/ListenableFuture;)V

    invoke-interface {v9, v10}, Lkotlinx/coroutines/Job;->invokeOnCompletion(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;

    .line 10
    :try_start_2
    iput-object v5, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzg:Ljava/lang/Object;

    iput-object v2, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zza:Ljava/lang/Object;

    iput-object v9, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzb:Ljava/lang/Object;

    iput v6, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzc:I

    move-object/from16 v11, v18

    invoke-virtual {v15, v11, v7, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzI(Ljava/lang/Object;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v6
    :try_end_2
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_2 .. :try_end_2} :catch_4
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    if-eq v6, v0, :cond_5

    move-object/from16 v19, v5

    move-object v5, v2

    move-object v2, v9

    move-object/from16 v9, v19

    :goto_1
    :try_start_3
    check-cast v6, Ljava/lang/Boolean;

    invoke-virtual {v6}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 11
    iput-object v9, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzg:Ljava/lang/Object;

    iput-object v5, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zza:Ljava/lang/Object;

    iput-object v7, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzb:Ljava/lang/Object;

    const/4 v6, 0x2

    iput v6, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzc:I

    invoke-interface {v2, v1}, Lkotlinx/coroutines/Deferred;->await(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v2
    :try_end_3
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_3 .. :try_end_3} :catch_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    if-ne v2, v0, :cond_2

    goto :goto_4

    :cond_2
    move-object v6, v9

    :goto_2
    :try_start_4
    check-cast v2, Ljava/lang/Boolean;

    invoke-virtual {v2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 16
    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzf:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    .line 12
    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lcom/google/mlkit/genai/prompt/CachedContext$Companion;->zza(Ljava/lang/String;)Lcom/google/mlkit/genai/prompt/CachedContext;

    move-result-object v2
    :try_end_4
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_4 .. :try_end_4} :catch_2
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    iget-object v1, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;)Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v3

    .line 13
    invoke-virtual {v3, v0}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;)Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v1

    .line 14
    invoke-virtual {v1, v0}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    if-nez v0, :cond_3

    .line 15
    invoke-static {v6}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v8, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v4, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    :cond_3
    return-object v2

    :catch_2
    move-exception v0

    goto :goto_3

    .line 16
    :cond_4
    :try_start_5
    new-instance v0, Lcom/google/mlkit/genai/common/GenAiException;

    .line 17
    invoke-static {v6}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v2, v2, 0x22

    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9, v2}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v9, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    const/16 v3, -0x67

    .line 16
    invoke-direct {v0, v2, v7, v3}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw v0
    :try_end_5
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_5 .. :try_end_5} :catch_2
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    :goto_3
    move-object v2, v5

    goto :goto_7

    :catch_3
    move-exception v0

    move-object v2, v5

    goto/16 :goto_0

    :cond_5
    :goto_4
    return-object v0

    :catchall_2
    move-exception v0

    goto :goto_5

    :catch_4
    move-exception v0

    goto :goto_6

    :goto_5
    move-object v6, v5

    goto :goto_8

    :goto_6
    move-object v6, v5

    .line 18
    :goto_7
    :try_start_6
    invoke-virtual {v2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;->zzi(Ljava/lang/Throwable;)Z

    .line 19
    throw v0
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    :goto_8
    iget-object v2, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    iget-object v1, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;->zzf:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;)Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v3

    .line 13
    invoke-virtual {v3, v1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;)Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v2

    .line 14
    invoke-virtual {v2, v1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    if-nez v1, :cond_6

    .line 15
    invoke-static {v6}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v8, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v4, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    :cond_6
    throw v0

    :cond_7
    move-object v5, v10

    .line 5
    new-instance v0, Lcom/google/mlkit/genai/common/GenAiException;

    .line 6
    invoke-static {v5}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "Cache creation already in progress for prefix: "

    invoke-virtual {v2, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const/16 v3, -0x67

    .line 5
    invoke-direct {v0, v1, v7, v3}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw v0
.end method
