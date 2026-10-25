.class public final Lcom/google/mlkit/genai/prompt/java/zzal;
.super Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

.field private final zzb:Lkotlinx/coroutines/CoroutineScope;


# direct methods
.method public constructor <init>(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;-><init>()V

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    invoke-interface {p1}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zzf()Ljava/util/concurrent/ExecutorService;

    move-result-object p1

    invoke-static {p1}, Lkotlinx/coroutines/ExecutorsKt;->from(Ljava/util/concurrent/ExecutorService;)Lkotlinx/coroutines/ExecutorCoroutineDispatcher;

    move-result-object p1

    invoke-static {p1}, Lkotlinx/coroutines/CoroutineScopeKt;->CoroutineScope(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object p1

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zzb:Lkotlinx/coroutines/CoroutineScope;

    return-void
.end method

.method public static final synthetic zza(Lcom/google/mlkit/genai/prompt/java/zzal;)Lcom/google/mlkit/genai/prompt/GenerativeModel;
    .locals 0

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    return-object p0
.end method

.method public static synthetic zzb(Lcom/google/mlkit/genai/prompt/java/zzal;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 6

    .line 2
    new-instance v3, Lcom/google/mlkit/genai/prompt/java/zzai;

    const/4 v0, 0x0

    invoke-direct {v3, p1, p0, v0}, Lcom/google/mlkit/genai/prompt/java/zzai;-><init>(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/mlkit/genai/prompt/java/zzal;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zzb:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object v0

    new-instance v1, Lcom/google/mlkit/genai/prompt/java/zzz;

    invoke-direct {v1, v0}, Lcom/google/mlkit/genai/prompt/java/zzz;-><init>(Lkotlinx/coroutines/Job;)V

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    .line 3
    invoke-interface {p0}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zzf()Ljava/util/concurrent/ExecutorService;

    move-result-object p0

    invoke-virtual {p1, v1, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "getBaseModelName"

    return-object p0
.end method

.method public static synthetic zzc(Lcom/google/mlkit/genai/prompt/java/zzal;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 6

    .line 2
    new-instance v3, Lcom/google/mlkit/genai/prompt/java/zzad;

    const/4 v0, 0x0

    invoke-direct {v3, p1, p0, v0}, Lcom/google/mlkit/genai/prompt/java/zzad;-><init>(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/mlkit/genai/prompt/java/zzal;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zzb:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object v0

    new-instance v1, Lcom/google/mlkit/genai/prompt/java/zzaa;

    invoke-direct {v1, v0}, Lcom/google/mlkit/genai/prompt/java/zzaa;-><init>(Lkotlinx/coroutines/Job;)V

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    .line 3
    invoke-interface {p0}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zzf()Ljava/util/concurrent/ExecutorService;

    move-result-object p0

    invoke-virtual {p1, v1, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "checkStatus"

    return-object p0
.end method

.method public static synthetic zzd(Lcom/google/mlkit/genai/prompt/java/zzal;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 6

    .line 2
    new-instance v3, Lcom/google/mlkit/genai/prompt/java/zzaj;

    const/4 v0, 0x0

    invoke-direct {v3, p1, p0, v0}, Lcom/google/mlkit/genai/prompt/java/zzaj;-><init>(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/mlkit/genai/prompt/java/zzal;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zzb:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object v0

    new-instance v1, Lcom/google/mlkit/genai/prompt/java/zzab;

    invoke-direct {v1, v0}, Lcom/google/mlkit/genai/prompt/java/zzab;-><init>(Lkotlinx/coroutines/Job;)V

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    .line 3
    invoke-interface {p0}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zzf()Ljava/util/concurrent/ExecutorService;

    move-result-object p0

    invoke-virtual {p1, v1, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "isSystemPromptAvailable"

    return-object p0
.end method

.method public static synthetic zze(Lcom/google/mlkit/genai/prompt/java/zzal;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 6

    .line 2
    new-instance v3, Lcom/google/mlkit/genai/prompt/java/zzak;

    const/4 v0, 0x0

    invoke-direct {v3, p1, p0, v0}, Lcom/google/mlkit/genai/prompt/java/zzak;-><init>(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/mlkit/genai/prompt/java/zzal;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zzb:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object v0

    new-instance v1, Lcom/google/mlkit/genai/prompt/java/zzo;

    invoke-direct {v1, v0}, Lcom/google/mlkit/genai/prompt/java/zzo;-><init>(Lkotlinx/coroutines/Job;)V

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    .line 3
    invoke-interface {p0}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zzf()Ljava/util/concurrent/ExecutorService;

    move-result-object p0

    invoke-virtual {p1, v1, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "isThinkingModeAvailable"

    return-object p0
.end method

.method public static synthetic zzf(Lcom/google/mlkit/genai/prompt/java/zzal;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 6

    .line 2
    new-instance v3, Lcom/google/mlkit/genai/prompt/java/zzae;

    const/4 v0, 0x0

    invoke-direct {v3, p2, p0, p1, v0}, Lcom/google/mlkit/genai/prompt/java/zzae;-><init>(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/mlkit/genai/prompt/java/zzal;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zzb:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object p1

    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zzp;

    invoke-direct {v0, p1}, Lcom/google/mlkit/genai/prompt/java/zzp;-><init>(Lkotlinx/coroutines/Job;)V

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    .line 3
    invoke-interface {p0}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zzf()Ljava/util/concurrent/ExecutorService;

    move-result-object p0

    invoke-virtual {p2, v0, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "generateContent"

    return-object p0
.end method

.method public static synthetic zzg(Lcom/google/mlkit/genai/prompt/java/zzal;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 6

    .line 2
    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zzaf;

    const/4 v5, 0x0

    move-object v2, p0

    move-object v3, p1

    move-object v4, p2

    move-object v1, p3

    invoke-direct/range {v0 .. v5}, Lcom/google/mlkit/genai/prompt/java/zzaf;-><init>(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/mlkit/genai/prompt/java/zzal;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/coroutines/Continuation;)V

    move-object p1, v1

    iget-object p2, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zzb:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v1, 0x0

    const/4 v2, 0x0

    move-object v3, v0

    move-object v0, p2

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object p2

    new-instance p3, Lcom/google/mlkit/genai/prompt/java/zzq;

    invoke-direct {p3, p2}, Lcom/google/mlkit/genai/prompt/java/zzq;-><init>(Lkotlinx/coroutines/Job;)V

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    .line 3
    invoke-interface {p0}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zzf()Ljava/util/concurrent/ExecutorService;

    move-result-object p0

    invoke-virtual {p1, p3, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "generateContentStream"

    return-object p0
.end method


# virtual methods
.method public final checkStatus()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 1
    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zzn;

    invoke-direct {v0, p0}, Lcom/google/mlkit/genai/prompt/java/zzn;-><init>(Lcom/google/mlkit/genai/prompt/java/zzal;)V

    invoke-static {v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final countTokens(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
            ")",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Lcom/google/mlkit/genai/prompt/CountTokensResponse;",
            ">;"
        }
    .end annotation

    .line 1
    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    invoke-interface {p0, p1}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zzc(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final download(Lcom/google/mlkit/genai/common/DownloadCallback;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/common/DownloadCallback;",
            ")",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Void;",
            ">;"
        }
    .end annotation

    .line 1
    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    invoke-interface {p0, p1}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zza(Lcom/google/mlkit/genai/common/DownloadCallback;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final generateContent(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
            ")",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Lcom/google/mlkit/genai/prompt/GenerateContentResponse;",
            ">;"
        }
    .end annotation

    .line 1
    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zzv;

    invoke-direct {v0, p0, p1}, Lcom/google/mlkit/genai/prompt/java/zzv;-><init>(Lcom/google/mlkit/genai/prompt/java/zzal;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)V

    invoke-static {v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final generateContent(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
            "Lcom/google/mlkit/genai/common/StreamingCallback;",
            ")",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Lcom/google/mlkit/genai/prompt/GenerateContentResponse;",
            ">;"
        }
    .end annotation

    .line 2
    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zzw;

    invoke-direct {v0, p0, p1, p2}, Lcom/google/mlkit/genai/prompt/java/zzw;-><init>(Lcom/google/mlkit/genai/prompt/java/zzal;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;)V

    invoke-static {v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final getBaseModelName()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 1
    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zzac;

    invoke-direct {v0, p0}, Lcom/google/mlkit/genai/prompt/java/zzac;-><init>(Lcom/google/mlkit/genai/prompt/java/zzal;)V

    invoke-static {v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final getTokenLimit()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 1
    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    invoke-interface {p0}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zzd()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final isSystemPromptAvailable()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation

    .line 1
    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zzt;

    invoke-direct {v0, p0}, Lcom/google/mlkit/genai/prompt/java/zzt;-><init>(Lcom/google/mlkit/genai/prompt/java/zzal;)V

    invoke-static {v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final isThinkingModeAvailable()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation

    .line 1
    new-instance v0, Lcom/google/mlkit/genai/prompt/java/zzu;

    invoke-direct {v0, p0}, Lcom/google/mlkit/genai/prompt/java/zzu;-><init>(Lcom/google/mlkit/genai/prompt/java/zzal;)V

    invoke-static {v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final warmup()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Void;",
            ">;"
        }
    .end annotation

    .line 1
    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzal;->zza:Lcom/google/mlkit/genai/prompt/GenerativeModel;

    invoke-interface {p0}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->zzb()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method
