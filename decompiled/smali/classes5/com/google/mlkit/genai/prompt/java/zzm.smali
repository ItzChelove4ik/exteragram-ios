.class public final Lcom/google/mlkit/genai/prompt/java/zzm;
.super Lcom/google/mlkit/genai/prompt/java/CachesFutures;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

.field private final zzb:Lcom/google/mlkit/genai/prompt/Caches;

.field private final zzc:Lkotlinx/coroutines/CoroutineScope;

.field private final zzd:Ljava/util/concurrent/ExecutorService;


# direct methods
.method public constructor <init>(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/mlkit/genai/prompt/java/CachesFutures;-><init>()V

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/java/zzm;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    invoke-interface {p1}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->getCaches()Lcom/google/mlkit/genai/prompt/Caches;

    move-result-object v0

    iput-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzm;->zzb:Lcom/google/mlkit/genai/prompt/Caches;

    .line 2
    invoke-interface {p1}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zzf()Ljava/util/concurrent/ExecutorService;

    move-result-object v0

    invoke-static {v0}, Lkotlinx/coroutines/ExecutorsKt;->from(Ljava/util/concurrent/ExecutorService;)Lkotlinx/coroutines/ExecutorCoroutineDispatcher;

    move-result-object v0

    invoke-static {v0}, Lkotlinx/coroutines/CoroutineScopeKt;->CoroutineScope(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object v0

    iput-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzm;->zzc:Lkotlinx/coroutines/CoroutineScope;

    .line 3
    invoke-interface {p1}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zzf()Ljava/util/concurrent/ExecutorService;

    move-result-object p1

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/java/zzm;->zzd:Ljava/util/concurrent/ExecutorService;

    return-void
.end method

.method public static final synthetic zza(Lcom/google/mlkit/genai/prompt/java/zzm;)Lcom/google/mlkit/genai/prompt/Caches;
    .locals 0

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzm;->zzb:Lcom/google/mlkit/genai/prompt/Caches;

    return-object p0
.end method

.method public static synthetic zzb(Lcom/google/mlkit/genai/prompt/java/zzm;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 6

    .line 2
    new-instance v3, Lcom/google/mlkit/genai/prompt/java/zzi;

    const/4 v0, 0x0

    invoke-direct {v3, p2, p0, p1, v0}, Lcom/google/mlkit/genai/prompt/java/zzi;-><init>(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/mlkit/genai/prompt/java/zzm;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzm;->zzc:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object p1

    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zzd;

    invoke-direct {v0, p1}, Lcom/google/mlkit/genai/prompt/java/zzd;-><init>(Lkotlinx/coroutines/Job;)V

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzm;->zzd:Ljava/util/concurrent/ExecutorService;

    .line 3
    invoke-virtual {p2, v0, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "create"

    return-object p0
.end method

.method public static synthetic zzc(Lcom/google/mlkit/genai/prompt/java/zzm;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 6

    .line 2
    new-instance v3, Lcom/google/mlkit/genai/prompt/java/zzl;

    const/4 v0, 0x0

    invoke-direct {v3, p1, p0, v0}, Lcom/google/mlkit/genai/prompt/java/zzl;-><init>(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/mlkit/genai/prompt/java/zzm;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzm;->zzc:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object v0

    new-instance v1, Lcom/google/mlkit/genai/prompt/java/zze;

    invoke-direct {v1, v0}, Lcom/google/mlkit/genai/prompt/java/zze;-><init>(Lkotlinx/coroutines/Job;)V

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzm;->zzd:Ljava/util/concurrent/ExecutorService;

    .line 3
    invoke-virtual {p1, v1, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "list"

    return-object p0
.end method

.method public static synthetic zze(Lcom/google/mlkit/genai/prompt/java/zzm;Ljava/lang/String;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 6

    .line 2
    new-instance v3, Lcom/google/mlkit/genai/prompt/java/zzj;

    const/4 v0, 0x0

    invoke-direct {v3, p2, p0, p1, v0}, Lcom/google/mlkit/genai/prompt/java/zzj;-><init>(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/mlkit/genai/prompt/java/zzm;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzm;->zzc:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object p1

    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zzg;

    invoke-direct {v0, p1}, Lcom/google/mlkit/genai/prompt/java/zzg;-><init>(Lkotlinx/coroutines/Job;)V

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzm;->zzd:Ljava/util/concurrent/ExecutorService;

    .line 3
    invoke-virtual {p2, v0, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "delete"

    return-object p0
.end method


# virtual methods
.method public final create(Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;",
            ")",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Lcom/google/mlkit/genai/prompt/CachedContext;",
            ">;"
        }
    .end annotation

    .line 1
    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zzh;

    invoke-direct {v0, p0, p1}, Lcom/google/mlkit/genai/prompt/java/zzh;-><init>(Lcom/google/mlkit/genai/prompt/java/zzm;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;)V

    invoke-static {v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final delete(Ljava/lang/String;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation

    .line 1
    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zzc;

    invoke-direct {v0, p0, p1}, Lcom/google/mlkit/genai/prompt/java/zzc;-><init>(Lcom/google/mlkit/genai/prompt/java/zzm;Ljava/lang/String;)V

    invoke-static {v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final list()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/util/List<",
            "Lcom/google/mlkit/genai/prompt/CachedContext;",
            ">;>;"
        }
    .end annotation

    .line 1
    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zza;

    invoke-direct {v0, p0}, Lcom/google/mlkit/genai/prompt/java/zza;-><init>(Lcom/google/mlkit/genai/prompt/java/zzm;)V

    invoke-static {v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method
