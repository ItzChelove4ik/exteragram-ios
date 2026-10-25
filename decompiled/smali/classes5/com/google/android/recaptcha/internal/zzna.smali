.class public final Lcom/google/android/recaptcha/internal/zzna;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzmx;


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zznd;

.field private final zzb:Lorg/chromium/net/CronetEngine;

.field private final zzc:Ljava/util/concurrent/ExecutorService;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zznd;Lorg/chromium/net/CronetEngine;Ljava/util/concurrent/ExecutorService;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzna;->zza:Lcom/google/android/recaptcha/internal/zznd;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzna;->zzb:Lorg/chromium/net/CronetEngine;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzna;->zzc:Ljava/util/concurrent/ExecutorService;

    return-void
.end method

.method public static final synthetic zzd(Lcom/google/android/recaptcha/internal/zzna;Ljava/lang/String;Ljava/lang/String;[BLjava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    const/4 p3, 0x0

    const/4 p4, 0x0

    const/4 p1, 0x0

    const/4 p2, 0x0

    .line 1
    invoke-direct/range {p0 .. p5}, Lcom/google/android/recaptcha/internal/zzna;->zze(Ljava/lang/String;Ljava/lang/String;[BLjava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method private final zze(Ljava/lang/String;Ljava/lang/String;[BLjava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 7

    instance-of v0, p5, Lcom/google/android/recaptcha/internal/zzmy;

    if-eqz v0, :cond_0

    move-object v0, p5

    check-cast v0, Lcom/google/android/recaptcha/internal/zzmy;

    .line 1
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzmy;->zzd:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzmy;->zzd:I

    goto :goto_0

    .line 21
    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzmy;

    invoke-direct {v0, p0, p5}, Lcom/google/android/recaptcha/internal/zzmy;-><init>(Lcom/google/android/recaptcha/internal/zzna;Lkotlin/coroutines/Continuation;)V

    .line 1
    :goto_0
    iget-object p5, v0, Lcom/google/android/recaptcha/internal/zzmy;->zzb:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzmy;->zzd:I

    const/4 v3, 0x1

    if-eqz v2, :cond_2

    if-ne v2, v3, :cond_1

    iget-object p0, v0, Lcom/google/android/recaptcha/internal/zzmy;->zza:Ljava/lang/Object;

    check-cast p0, Ljava/util/Map;

    :try_start_0
    invoke-static {p5}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_2
    .catch Lkotlinx/coroutines/TimeoutCancellationException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p5

    .line 19
    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    .line 1
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p5}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p5, p0, Lcom/google/android/recaptcha/internal/zzna;->zza:Lcom/google/android/recaptcha/internal/zznd;

    .line 2
    invoke-virtual {p5, p1}, Lcom/google/android/recaptcha/internal/zznd;->zza(Ljava/lang/String;)Z

    move-result p5

    if-eqz p5, :cond_7

    .line 3
    :try_start_1
    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzmy;->zze:Ljava/lang/String;

    iput-object p2, v0, Lcom/google/android/recaptcha/internal/zzmy;->zzf:Ljava/lang/String;

    iput-object p3, v0, Lcom/google/android/recaptcha/internal/zzmy;->zzg:[B

    iput-object p4, v0, Lcom/google/android/recaptcha/internal/zzmy;->zza:Ljava/lang/Object;

    iput v3, v0, Lcom/google/android/recaptcha/internal/zzmy;->zzd:I

    .line 5
    new-instance p5, Lkotlinx/coroutines/CancellableContinuationImpl;

    invoke-static {v0}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->intercepted(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object v2

    invoke-direct {p5, v2, v3}, Lkotlinx/coroutines/CancellableContinuationImpl;-><init>(Lkotlin/coroutines/Continuation;I)V

    .line 6
    invoke-virtual {p5}, Lkotlinx/coroutines/CancellableContinuationImpl;->initCancellability()V

    .line 7
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzna;->zzb:Lorg/chromium/net/CronetEngine;

    .line 8
    new-instance v3, Lcom/google/android/recaptcha/internal/zznc;

    invoke-direct {v3, p5}, Lcom/google/android/recaptcha/internal/zznc;-><init>(Lkotlinx/coroutines/CancellableContinuation;)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzna;->zzc:Ljava/util/concurrent/ExecutorService;

    .line 9
    invoke-virtual {v2, p1, v3, p0}, Lorg/chromium/net/CronetEngine;->newUrlRequestBuilder(Ljava/lang/String;Lorg/chromium/net/UrlRequest$Callback;Ljava/util/concurrent/Executor;)Lorg/chromium/net/UrlRequest$Builder;

    move-result-object v2

    .line 10
    invoke-virtual {v2, p2}, Lorg/chromium/net/UrlRequest$Builder;->setHttpMethod(Ljava/lang/String;)Lorg/chromium/net/UrlRequest$Builder;

    .line 11
    invoke-interface {p4}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p2

    invoke-interface {p2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :goto_1
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result p4

    if-eqz p4, :cond_3

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p4

    check-cast p4, Ljava/util/Map$Entry;

    .line 12
    invoke-interface {p4}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-interface {p4}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object p4

    check-cast p4, Ljava/lang/String;

    invoke-virtual {v2, v3, p4}, Lorg/chromium/net/UrlRequest$Builder;->addHeader(Ljava/lang/String;Ljava/lang/String;)Lorg/chromium/net/UrlRequest$Builder;

    goto :goto_1

    :cond_3
    if-eqz p3, :cond_4

    .line 13
    invoke-static {p3}, Lorg/chromium/net/apihelpers/UploadDataProviders;->create([B)Lorg/chromium/net/UploadDataProvider;

    move-result-object p2

    invoke-virtual {v2, p2, p0}, Lorg/chromium/net/UrlRequest$Builder;->setUploadDataProvider(Lorg/chromium/net/UploadDataProvider;Ljava/util/concurrent/Executor;)Lorg/chromium/net/UrlRequest$Builder;

    .line 14
    :cond_4
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Thread;->getId()J

    move-result-wide p2

    long-to-int p0, p2

    invoke-virtual {v2, p0}, Lorg/chromium/net/UrlRequest$Builder;->setTrafficStatsTag(I)Lorg/chromium/net/UrlRequest$Builder;

    .line 15
    invoke-virtual {v2}, Lorg/chromium/net/UrlRequest$Builder;->build()Lorg/chromium/net/UrlRequest;

    move-result-object p0

    .line 16
    invoke-virtual {p0}, Lorg/chromium/net/UrlRequest;->start()V

    .line 17
    new-instance p2, Lcom/google/android/recaptcha/internal/zzmz;

    invoke-direct {p2, p1, p0}, Lcom/google/android/recaptcha/internal/zzmz;-><init>(Ljava/lang/String;Lorg/chromium/net/UrlRequest;)V

    invoke-interface {p5, p2}, Lkotlinx/coroutines/CancellableContinuation;->invokeOnCancellation(Lkotlin/jvm/functions/Function1;)V

    .line 18
    invoke-virtual {p5}, Lkotlinx/coroutines/CancellableContinuationImpl;->getResult()Ljava/lang/Object;

    move-result-object p0

    .line 4
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object p1

    if-ne p0, p1, :cond_5

    invoke-static {v0}, Lkotlin/coroutines/jvm/internal/DebugProbesKt;->probeCoroutineSuspended(Lkotlin/coroutines/Continuation;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_2
    .catch Lkotlinx/coroutines/TimeoutCancellationException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    :cond_5
    if-ne p0, v1, :cond_6

    return-object v1

    :cond_6
    return-object p0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 19
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzw:Lcom/google/android/recaptcha/internal/zzhe;

    .line 20
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 19
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0

    :catch_1
    move-exception v0

    move-object p0, v0

    .line 21
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzb:Lcom/google/android/recaptcha/internal/zzhe;

    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0

    :catch_2
    move-exception v0

    move-object p0, v0

    .line 22
    throw p0

    .line 3
    :cond_7
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzp:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0
.end method


# virtual methods
.method public final zza(Ljava/lang/String;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 7

    .line 1
    const-string v0, "Content-Type"

    const-string v1, "application/x-protobuffer"

    invoke-static {v0, v1}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v0

    invoke-static {v0}, Lkotlin/collections/MapsKt;->mapOf(Lkotlin/Pair;)Ljava/util/Map;

    move-result-object v5

    const-string v3, "POST"

    move-object v1, p0

    move-object v2, p1

    move-object v4, p2

    move-object v6, p3

    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzna;->zze(Ljava/lang/String;Ljava/lang/String;[BLjava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final zzb(Ljava/lang/String;[BLjava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 6

    .line 1
    const-string v2, "GET"

    const/4 v3, 0x0

    const-string v1, "https://www.gstatic.com/recaptcha/verify_key/orcas/prod/android/verify_key.txt"

    move-object v0, p0

    move-object v4, p3

    move-object v5, p4

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzna;->zze(Ljava/lang/String;Ljava/lang/String;[BLjava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
