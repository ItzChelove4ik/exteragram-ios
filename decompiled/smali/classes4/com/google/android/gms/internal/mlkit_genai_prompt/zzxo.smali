.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/mlkit/genai/prompt/Caches;


# instance fields
.field private final zza:Landroid/content/Context;

.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

.field private final zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;


# direct methods
.method public constructor <init>(Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zza:Landroid/content/Context;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    return-void
.end method


# virtual methods
.method public final create(Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 18
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lcom/google/mlkit/genai/prompt/CachedContext;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v1, p2

    instance-of v2, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;

    if-eqz v2, :cond_0

    move-object v2, v1

    check-cast v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;

    iget v3, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzc:I

    const/high16 v4, -0x80000000

    and-int v5, v3, v4

    if-eqz v5, :cond_0

    sub-int/2addr v3, v4

    iput v3, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzc:I

    :goto_0
    move-object v8, v2

    goto :goto_1

    .line 1
    :cond_0
    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;

    invoke-direct {v2, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;Lkotlin/coroutines/Continuation;)V

    goto :goto_0

    .line 0
    :goto_1
    iget-object v1, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v2

    iget v3, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzc:I

    const/4 v9, 0x3

    const/4 v4, 0x2

    const/4 v5, 0x1

    const/4 v10, 0x0

    if-eqz v3, :cond_4

    if-eq v3, v5, :cond_3

    if-eq v3, v4, :cond_2

    if-ne v3, v9, :cond_1

    invoke-static {v1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object v1

    :cond_1
    const-string v0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {v0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v10

    :cond_2
    iget-object v3, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzf:Ljava/lang/String;

    iget-object v4, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zze:Ljava/lang/String;

    iget-object v5, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzd:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    invoke-static {v1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v12, v3

    goto :goto_3

    :cond_3
    iget-object v3, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzf:Ljava/lang/String;

    iget-object v5, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zze:Ljava/lang/String;

    iget-object v6, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzd:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    invoke-static {v1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object/from16 v17, v5

    move-object v5, v1

    move-object v1, v6

    move-object v6, v3

    move-object/from16 v3, v17

    goto :goto_2

    :cond_4
    invoke-static {v1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    invoke-virtual/range {p1 .. p1}, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;->getName()Ljava/lang/String;

    move-result-object v1

    .line 3
    invoke-virtual/range {p1 .. p1}, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;->getPromptPrefix()Lcom/google/mlkit/genai/prompt/PromptPrefix;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/mlkit/genai/prompt/PromptPrefix;->getTextString()Ljava/lang/String;

    move-result-object v3

    iget-object v6, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    move-object/from16 v7, p1

    .line 4
    iput-object v7, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzd:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    iput-object v1, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zze:Ljava/lang/String;

    iput-object v3, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzf:Ljava/lang/String;

    iput v5, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzc:I

    invoke-virtual {v6, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzz(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v5

    if-eq v5, v2, :cond_7

    move-object v6, v3

    move-object v3, v1

    move-object v1, v7

    :goto_2
    iget-object v7, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    iget-object v11, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zza:Landroid/content/Context;

    .line 1
    check-cast v5, Ljava/lang/String;

    .line 5
    iput-object v1, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzd:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    iput-object v3, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zze:Ljava/lang/String;

    iput-object v6, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzf:Ljava/lang/String;

    iput v4, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzc:I

    move-object v4, v5

    move-object v5, v3

    move-object v3, v7

    move-object v7, v4

    move-object v4, v11

    .line 6
    invoke-static/range {v3 .. v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v3

    if-eq v3, v2, :cond_7

    move-object v4, v5

    move-object v12, v6

    move-object v5, v1

    move-object v1, v3

    .line 1
    :goto_3
    move-object v13, v1

    check-cast v13, Landroid/os/ParcelFileDescriptor;

    if-eqz v13, :cond_6

    .line 7
    iget-object v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    new-instance v11, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;

    const/4 v15, 0x4

    const/16 v16, 0x0

    const/4 v14, 0x0

    .line 9
    invoke-direct/range {v11 .. v16}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;-><init>(Ljava/lang/String;Landroid/os/ParcelFileDescriptor;Landroid/os/ParcelFileDescriptor;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 10
    iput-object v10, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzd:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    iput-object v10, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zze:Ljava/lang/String;

    iput-object v10, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzf:Ljava/lang/String;

    iput v9, v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzc:I

    .line 11
    invoke-static {v0, v5, v11, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-ne v0, v2, :cond_5

    goto :goto_4

    :cond_5
    return-object v0

    .line 7
    :cond_6
    new-instance v0, Lcom/google/mlkit/genai/common/GenAiException;

    .line 8
    invoke-static {v4}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "Failed to open file descriptor to create cache for name: "

    invoke-virtual {v2, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const/16 v2, -0x67

    .line 7
    invoke-direct {v0, v1, v10, v2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw v0

    :cond_7
    :goto_4
    return-object v2
.end method

.method public final delete(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Ljava/lang/Boolean;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    instance-of v0, p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxl;

    if-eqz v0, :cond_0

    move-object v0, p2

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxl;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxl;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxl;->zzc:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxl;

    invoke-direct {v0, p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxl;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxl;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxl;->zzc:I

    const/4 v3, 0x0

    const/4 v4, 0x2

    const/4 v5, 0x1

    if-eqz v2, :cond_3

    if-eq v2, v5, :cond_2

    if-ne v2, v4, :cond_1

    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object p2

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v3

    :cond_2
    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_3
    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zza:Landroid/content/Context;

    .line 2
    iput v5, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxl;->zzc:I

    .line 3
    invoke-static {p2, v2, p1, v3, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p2

    if-eq p2, v1, :cond_6

    .line 1
    :goto_1
    check-cast p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;

    if-eqz p2, :cond_5

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zza:Landroid/content/Context;

    .line 4
    iput v4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxl;->zzc:I

    .line 5
    invoke-static {p1, p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v1, :cond_4

    goto :goto_2

    :cond_4
    return-object p0

    :cond_5
    const/4 p0, 0x0

    .line 6
    invoke-static {p0}, Lkotlin/coroutines/jvm/internal/Boxing;->boxBoolean(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0

    :cond_6
    :goto_2
    return-object v1
.end method

.method public final list(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Ljava/util/List<",
            "Lcom/google/mlkit/genai/prompt/CachedContext;",
            ">;>;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxn;

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxn;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxn;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxn;->zzc:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxn;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxn;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxn;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxn;->zzc:I

    const/4 v3, 0x1

    if-eqz v2, :cond_2

    if-ne v2, v3, :cond_1

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->zza:Landroid/content/Context;

    .line 2
    iput v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxn;->zzc:I

    .line 3
    invoke-static {p1, p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_4

    .line 2
    :goto_1
    check-cast p1, Ljava/util/Map;

    invoke-interface {p1}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    new-instance p1, Ljava/util/ArrayList;

    const/16 v0, 0xa

    .line 4
    invoke-static {p0, v0}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v0

    invoke-direct {p1, v0}, Ljava/util/ArrayList;-><init>(I)V

    .line 5
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_2
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_3

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    .line 6
    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;

    .line 7
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;->zza()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/google/mlkit/genai/prompt/CachedContext$Companion;->zza(Ljava/lang/String;)Lcom/google/mlkit/genai/prompt/CachedContext;

    move-result-object v0

    .line 6
    invoke-interface {p1, v0}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_2

    :cond_3
    return-object p1

    :cond_4
    return-object v1
.end method
