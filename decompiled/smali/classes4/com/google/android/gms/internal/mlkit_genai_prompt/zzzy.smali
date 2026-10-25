.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/mlkit/genai/prompt/GenerativeModel;


# static fields
.field public static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

.field private static final zzn:Lkotlin/Lazy;


# instance fields
.field private final zzb:Lcom/google/mlkit/genai/prompt/GenerationConfig;

.field private final zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

.field private final zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

.field private final zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

.field private final zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

.field private final zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

.field private final zzh:Ljava/util/concurrent/ExecutorService;

.field private final zzi:Lkotlinx/coroutines/CoroutineScope;

.field private final zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

.field private zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

.field private zzl:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

.field private final zzm:Lkotlin/Lazy;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzza;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzza;

    invoke-static {v0}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzb;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzb;

    .line 2
    invoke-static {v0}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v0

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzn:Lkotlin/Lazy;

    return-void
.end method

.method public constructor <init>(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;)V
    .locals 6

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzb:Lcom/google/mlkit/genai/prompt/GenerationConfig;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    iput-object p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    iput-object p6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerationConfig;->getWorkerExecutor()Ljava/util/concurrent/ExecutorService;

    move-result-object p1

    if-nez p1, :cond_0

    new-instance p1, Lcom/google/mlkit/common/sdkinternal/MlKitThreadPool;

    invoke-direct {p1}, Lcom/google/mlkit/common/sdkinternal/MlKitThreadPool;-><init>()V

    :cond_0
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzh:Ljava/util/concurrent/ExecutorService;

    .line 2
    invoke-static {p1}, Lkotlinx/coroutines/ExecutorsKt;->from(Ljava/util/concurrent/ExecutorService;)Lkotlinx/coroutines/ExecutorCoroutineDispatcher;

    move-result-object v0

    invoke-static {v0}, Lkotlinx/coroutines/CoroutineScopeKt;->CoroutineScope(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzi:Lkotlinx/coroutines/CoroutineScope;

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    const/4 v1, 0x4

    .line 3
    new-array v1, v1, [Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    const/4 v2, 0x0

    aput-object p3, v1, v2

    const/4 p3, 0x1

    aput-object p4, v1, p3

    const/4 p3, 0x2

    aput-object p5, v1, p3

    const/4 p3, 0x3

    aput-object p6, v1, p3

    .line 4
    invoke-static {v1}, Lkotlin/collections/CollectionsKt;->listOf([Ljava/lang/Object;)Ljava/util/List;

    move-result-object p3

    .line 5
    invoke-direct {v0, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;Ljava/util/List;)V

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    sget-object p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxq;

    .line 6
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    move-result-object p3

    if-nez p3, :cond_2

    monitor-enter p2

    .line 7
    :try_start_0
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    move-result-object p3

    if-nez p3, :cond_1

    new-instance p3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    new-instance p4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    const-string p5, ".mlkit_genai_prompt_explicit_prefix_cache"

    sget-object p6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxu;

    .line 9
    invoke-direct {p4, p1, p5, p6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;-><init>(Ljava/util/concurrent/ExecutorService;Ljava/lang/String;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxj;)V

    const/4 p5, 0x0

    .line 10
    invoke-direct {p3, p4, p5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 11
    invoke-static {p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_1

    .line 12
    :cond_1
    :goto_0
    monitor-exit p2

    goto :goto_2

    :goto_1
    monitor-exit p2

    throw p0

    :cond_2
    :goto_2
    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    sget-object p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzz;

    .line 13
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    move-result-object p3

    if-nez p3, :cond_4

    monitor-enter p2

    .line 14
    :try_start_1
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    move-result-object p3

    if-nez p3, :cond_3

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    const-string p3, ".mlkit_prefix_cache"

    sget-object p4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaac;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaac;

    .line 16
    invoke-direct {v1, p1, p3, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;-><init>(Ljava/util/concurrent/ExecutorService;Ljava/lang/String;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxj;)V

    const/4 v4, 0x6

    const/4 v5, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    .line 17
    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzxp;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 18
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-object p3, v0

    goto :goto_3

    :catchall_1
    move-exception v0

    move-object p0, v0

    goto :goto_4

    .line 19
    :cond_3
    :goto_3
    monitor-exit p2

    goto :goto_5

    :goto_4
    monitor-exit p2

    throw p0

    :cond_4
    :goto_5
    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzl:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzc;

    invoke-direct {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzc;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;)V

    .line 20
    invoke-static {p1}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzm:Lkotlin/Lazy;

    return-void
.end method

.method private final zzA(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 4

    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzze;

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzze;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzze;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzze;->zzc:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzze;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzze;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzze;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzze;->zzc:I

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

    .line 2
    iput v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzze;->zzc:I

    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzD(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_4

    :goto_1
    check-cast p1, Ljava/lang/Number;

    invoke-virtual {p1}, Ljava/lang/Number;->intValue()I

    move-result p0

    const/4 p1, 0x3

    if-ne p0, p1, :cond_3

    goto :goto_2

    :cond_3
    const/4 v3, 0x0

    :goto_2
    invoke-static {v3}, Lkotlin/coroutines/jvm/internal/Boxing;->boxBoolean(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0

    :cond_4
    return-object v1
.end method

.method private final zzC(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x0

    .line 2
    invoke-static {p0}, Lkotlin/coroutines/jvm/internal/Boxing;->boxInt(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0

    .line 3
    :cond_0
    invoke-static {v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method private final zzD(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x0

    .line 2
    invoke-static {p0}, Lkotlin/coroutines/jvm/internal/Boxing;->boxInt(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0

    .line 3
    :cond_0
    invoke-static {v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method private static final zzE(Ljava/lang/String;)I
    .locals 4

    .line 1
    new-instance v0, Lkotlin/text/Regex;

    const-string v1, "nano-v(\\d+)"

    invoke-direct {v0, v1}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    const/4 v1, 0x2

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-static {v0, p0, v3, v1, v2}, Lkotlin/text/Regex;->find$default(Lkotlin/text/Regex;Ljava/lang/CharSequence;IILjava/lang/Object;)Lkotlin/text/MatchResult;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 2
    invoke-interface {p0}, Lkotlin/text/MatchResult;->getGroupValues()Ljava/util/List;

    move-result-object p0

    if-eqz p0, :cond_0

    const/4 v0, 0x1

    invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    if-eqz p0, :cond_0

    invoke-static {p0}, Lkotlin/text/StringsKt;->toIntOrNull(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    return p0

    :cond_0
    return v3
.end method

.method public static final synthetic zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-direct {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzu(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    const/4 p1, 0x0

    const/4 p2, 0x0

    .line 1
    invoke-direct {p0, p1, p1, p2, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzv(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzj(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    const/4 p3, 0x0

    const/4 p4, 0x0

    const/4 p1, 0x0

    const/4 p2, 0x0

    .line 1
    invoke-direct/range {p0 .. p5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzw(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzk(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Ljava/lang/String;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    const/4 p4, 0x0

    const/4 p5, 0x0

    const/4 p1, 0x0

    const/4 p2, 0x0

    const/4 p3, 0x0

    .line 1
    invoke-direct/range {p0 .. p6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzx(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Ljava/lang/String;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzl(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Ljava/lang/String;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    const/4 p4, 0x0

    const/4 p5, 0x0

    const/4 p1, 0x0

    const/4 p2, 0x0

    const/4 p3, 0x0

    .line 1
    invoke-direct/range {p0 .. p6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzy(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Ljava/lang/String;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-direct {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzz(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzn(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-direct {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzA(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzp()Lkotlin/Lazy;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzn:Lkotlin/Lazy;

    return-object v0
.end method

.method public static synthetic zzq(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;)Landroid/content/Context;

    move-result-object v1

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    invoke-direct {v0, v1, v2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;-><init>(Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;)V

    return-object v0
.end method

.method public static synthetic zzr(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lcom/google/mlkit/genai/common/DownloadCallback;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 6

    .line 2
    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzl;

    const/4 v0, 0x0

    invoke-direct {v3, p0, p2, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzl;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/mlkit/genai/common/DownloadCallback;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzi:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object p1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyx;

    invoke-direct {v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyx;-><init>(Lkotlinx/coroutines/Job;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzh:Ljava/util/concurrent/ExecutorService;

    .line 3
    invoke-virtual {p2, v0, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "download"

    return-object p0
.end method

.method public static synthetic zzs(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 6

    .line 2
    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzx;

    const/4 v0, 0x0

    invoke-direct {v3, p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzx;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzi:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyy;

    invoke-direct {v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyy;-><init>(Lkotlinx/coroutines/Job;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzh:Ljava/util/concurrent/ExecutorService;

    .line 3
    invoke-virtual {p1, v1, p0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->addCancellationListener(Ljava/lang/Runnable;Ljava/util/concurrent/Executor;)V

    const-string p0, "warmup"

    return-object p0
.end method

.method private final zzu(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 7

    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzv;

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzv;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzv;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzv;->zzc:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzv;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzv;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzv;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzv;->zzc:I

    const/4 v3, 0x4

    const/4 v4, 0x3

    const/4 v5, 0x2

    const/4 v6, 0x1

    if-eqz v2, :cond_5

    if-eq v2, v6, :cond_4

    if-eq v2, v5, :cond_3

    if-eq v2, v4, :cond_2

    if-ne v2, v3, :cond_1

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_5

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_3

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_2

    :cond_4
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_1

    :cond_5
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    :try_start_2
    iput v6, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzv;->zzc:I

    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzC(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_7

    :goto_1
    check-cast p1, Ljava/lang/Number;

    invoke-virtual {p1}, Ljava/lang/Number;->intValue()I

    move-result p1

    if-eqz p1, :cond_6

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    .line 3
    iput v5, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzv;->zzc:I

    invoke-virtual {p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzE(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v1, :cond_6

    goto :goto_4

    .line 4
    :cond_6
    :goto_2
    iput v4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzv;->zzc:I

    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzD(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_7

    :goto_3
    check-cast p1, Ljava/lang/Number;

    invoke-virtual {p1}, Ljava/lang/Number;->intValue()I

    move-result p1

    if-eqz p1, :cond_8

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    .line 5
    iput v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzv;->zzc:I

    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzE(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0
    :try_end_2
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_2 .. :try_end_2} :catch_0

    if-eq p0, v1, :cond_7

    goto :goto_5

    :cond_7
    :goto_4
    return-object v1

    :catch_0
    move-exception p0

    .line 1
    const-string p1, "GenerativeModel"

    const-string v0, "Failed to prepare inference engine for prefix caching"

    .line 6
    invoke-static {p1, v0, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 7
    :cond_8
    :goto_5
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method private final zzv(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 10

    instance-of v0, p4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;

    if-eqz v0, :cond_0

    move-object v0, p4

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzf:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzf:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;

    invoke-direct {v0, p0, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzd:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzf:I

    const/4 v3, 0x4

    const/4 v4, 0x3

    const/4 v5, 0x2

    const/4 v6, 0x1

    const/4 v7, 0x0

    if-eqz v2, :cond_5

    if-eq v2, v6, :cond_4

    if-eq v2, v5, :cond_3

    if-eq v2, v4, :cond_2

    if-ne v2, v3, :cond_1

    iget-object p0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zza:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;

    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_4

    .line 5
    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    .line 1
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v7

    :cond_2
    iget-object p0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zza:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;

    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_3

    :cond_3
    iget-boolean p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzc:Z

    iget-object p2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzg:Ljava/lang/String;

    iget-object p3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzb:Ljava/lang/Object;

    check-cast p3, Lkotlin/reflect/KClass;

    iget-object v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zza:Ljava/lang/Object;

    check-cast v2, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v9, p4

    move p4, p1

    move-object p1, v2

    move-object v2, v9

    goto :goto_2

    :cond_4
    iget-boolean p3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzc:Z

    iget-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzg:Ljava/lang/String;

    iget-object p2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzb:Ljava/lang/Object;

    check-cast p2, Lkotlin/reflect/KClass;

    iget-object v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zza:Ljava/lang/Object;

    check-cast v2, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v9, p4

    move-object p4, p1

    move-object p1, v2

    move-object v2, v9

    goto :goto_1

    :cond_5
    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getCachedContextName()Ljava/lang/String;

    move-result-object p4

    if-nez p4, :cond_6

    const-string p4, ""

    .line 3
    :cond_6
    invoke-interface {p4}, Ljava/lang/CharSequence;->length()I

    move-result v2

    if-lez v2, :cond_a

    .line 4
    iput-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zza:Ljava/lang/Object;

    iput-object p2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzb:Ljava/lang/Object;

    iput-object p4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzg:Ljava/lang/String;

    iput-boolean p3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzc:Z

    iput v6, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzf:I

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    .line 5
    invoke-virtual {v2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzz(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v2

    if-ne v2, v1, :cond_7

    goto/16 :goto_5

    .line 1
    :cond_7
    :goto_1
    check-cast v2, Ljava/lang/String;

    iget-object v6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    sget-object v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

    .line 6
    invoke-static {v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;)Landroid/content/Context;

    move-result-object v8

    iput-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zza:Ljava/lang/Object;

    iput-object p2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzb:Ljava/lang/Object;

    iput-object p4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzg:Ljava/lang/String;

    iput-boolean p3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzc:Z

    iput v5, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzf:I

    .line 7
    invoke-static {v6, v8, p4, v2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v2

    if-eq v2, v1, :cond_b

    move v9, p3

    move-object p3, p2

    move-object p2, p4

    move p4, v9

    .line 1
    :goto_2
    check-cast v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;

    if-eqz v2, :cond_9

    .line 8
    invoke-virtual {p1, v7, v7}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zza(Lcom/google/mlkit/genai/prompt/ImagePart;Lcom/google/mlkit/genai/prompt/TextPart;)Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;

    move-result-object p1

    new-instance p2, Lcom/google/mlkit/genai/prompt/PromptPrefix;

    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;->zzd()Ljava/lang/String;

    move-result-object v2

    .line 9
    invoke-direct {p2, v2}, Lcom/google/mlkit/genai/prompt/PromptPrefix;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setPromptPrefix(Lcom/google/mlkit/genai/prompt/PromptPrefix;)V

    .line 10
    invoke-virtual {p1, v7}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setCachedContextName(Ljava/lang/String;)V

    .line 11
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->build()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-result-object p1

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    sget-object p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    .line 12
    invoke-direct {v2, p1, p3, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;-><init>(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/reflect/KClass;Z)V

    .line 13
    iput-object p2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zza:Ljava/lang/Object;

    iput-object v7, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzb:Ljava/lang/Object;

    iput-object v7, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzg:Ljava/lang/String;

    iput v4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzf:I

    invoke-virtual {p0, v2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzG(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p4

    if-ne p4, v1, :cond_8

    goto :goto_5

    :cond_8
    move-object p0, p2

    .line 1
    :goto_3
    check-cast p4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdz;

    .line 14
    invoke-virtual {p0, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdz;)Lcom/google/mlkit/genai/prompt/CountTokensResponse;

    move-result-object p0

    return-object p0

    .line 15
    :cond_9
    invoke-static {p2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    const-string v2, "GenerativeModel"

    const-string v4, "Cached context not found for name: "

    invoke-virtual {v4, p2}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-static {v2, p2}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    move-object p2, p3

    move p3, p4

    :cond_a
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    sget-object p4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    .line 16
    invoke-direct {v2, p1, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;-><init>(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/reflect/KClass;Z)V

    .line 17
    iput-object p4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zza:Ljava/lang/Object;

    iput-object v7, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzb:Ljava/lang/Object;

    iput-object v7, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzg:Ljava/lang/String;

    iput v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzi;->zzf:I

    invoke-virtual {p0, v2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzG(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-eq p0, v1, :cond_b

    move-object v9, p4

    move-object p4, p0

    move-object p0, v9

    .line 1
    :goto_4
    check-cast p4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdz;

    .line 18
    invoke-virtual {p0, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdz;)Lcom/google/mlkit/genai/prompt/CountTokensResponse;

    move-result-object p0

    return-object p0

    :cond_b
    :goto_5
    return-object v1
.end method

.method private final zzw(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 10

    instance-of v0, p5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;

    if-eqz v0, :cond_0

    move-object v0, p5

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzf:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzf:I

    :goto_0
    move-object v7, v0

    goto :goto_1

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;

    invoke-direct {v0, p0, p5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)V

    goto :goto_0

    .line 0
    :goto_1
    iget-object p5, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzd:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzf:I

    const/4 v8, 0x5

    const/4 v2, 0x4

    const/4 v3, 0x3

    const/4 v4, 0x2

    const/4 v5, 0x1

    const/4 v9, 0x0

    if-eqz v1, :cond_6

    if-eq v1, v5, :cond_5

    if-eq v1, v4, :cond_4

    if-eq v1, v3, :cond_3

    if-eq v1, v2, :cond_2

    if-ne v1, v8, :cond_1

    invoke-static {p5}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object p5

    .line 4
    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    .line 1
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v9

    :cond_2
    invoke-static {p5}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object p5

    :cond_3
    iget-boolean p1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzc:Z

    iget-object p2, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzh:Ljava/lang/String;

    iget-object p3, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzb:Ljava/lang/Object;

    check-cast p3, Lkotlin/reflect/KClass;

    iget-object p4, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zza:Ljava/lang/Object;

    check-cast p4, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {p5}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move v6, p1

    move-object v5, p3

    move-object v3, p4

    :goto_2
    move-object v4, p2

    goto/16 :goto_a

    :cond_4
    iget-boolean p1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzc:Z

    iget-object p2, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzb:Ljava/lang/Object;

    check-cast p2, Lkotlin/reflect/KClass;

    iget-object p3, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zza:Ljava/lang/Object;

    check-cast p3, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object p4, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {p5}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_6

    :cond_5
    iget-boolean p4, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzc:Z

    iget-object p1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzi:Ljava/lang/String;

    iget-object p2, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzh:Ljava/lang/String;

    iget-object p3, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzb:Ljava/lang/Object;

    check-cast p3, Lkotlin/reflect/KClass;

    iget-object v1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v5, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {p5}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v6, v1

    move-object v1, p1

    move-object p1, v5

    move-object v5, p3

    move p3, v4

    move-object v4, p2

    move-object p2, v6

    :goto_3
    move v6, p4

    goto :goto_5

    :cond_6
    invoke-static {p5}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getPromptPrefix()Lcom/google/mlkit/genai/prompt/PromptPrefix;

    move-result-object p5

    const-string v1, ""

    if-eqz p5, :cond_7

    invoke-virtual {p5}, Lcom/google/mlkit/genai/prompt/PromptPrefix;->getTextString()Ljava/lang/String;

    move-result-object p5

    if-nez p5, :cond_8

    :cond_7
    move-object p5, v1

    .line 3
    :cond_8
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getCachedContextName()Ljava/lang/String;

    move-result-object v6

    if-nez v6, :cond_9

    goto :goto_4

    :cond_9
    move-object v1, v6

    .line 4
    :goto_4
    invoke-interface {p5}, Ljava/lang/CharSequence;->length()I

    move-result v6

    if-lez v6, :cond_e

    iput-object p1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    iput-object p2, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zza:Ljava/lang/Object;

    iput-object p3, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzb:Ljava/lang/Object;

    iput-object p5, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzh:Ljava/lang/String;

    iput-object v1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzi:Ljava/lang/String;

    iput-boolean p4, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzc:Z

    iput v5, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzf:I

    invoke-direct {p0, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzA(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v5

    if-ne v5, v0, :cond_a

    goto/16 :goto_d

    :cond_a
    move-object v6, v5

    move-object v5, p3

    move p3, v4

    move-object v4, p5

    move-object p5, v6

    goto :goto_3

    :goto_5
    check-cast p5, Ljava/lang/Boolean;

    invoke-virtual {p5}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p4

    if-eqz p4, :cond_d

    .line 5
    iput-object p1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    iput-object p2, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zza:Ljava/lang/Object;

    iput-object v5, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzb:Ljava/lang/Object;

    iput-object v9, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzh:Ljava/lang/String;

    iput-object v9, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzi:Ljava/lang/String;

    iput-boolean v6, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzc:Z

    iput p3, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzf:I

    move-object v1, p0

    move-object v2, p1

    move-object v3, p2

    invoke-direct/range {v1 .. v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzx(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Ljava/lang/String;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p5

    move-object p4, v2

    move-object p3, v3

    if-ne p5, v0, :cond_b

    goto/16 :goto_d

    :cond_b
    move-object p2, v5

    move p1, v6

    .line 1
    :goto_6
    check-cast p5, Lcom/google/mlkit/genai/prompt/GenerateContentResponse;

    if-nez p5, :cond_c

    :goto_7
    move-object v1, p0

    goto/16 :goto_b

    :cond_c
    return-object p5

    :cond_d
    move-object p4, p1

    move p4, v6

    :goto_8
    move-object p3, p2

    move-object p2, v1

    goto :goto_9

    :cond_e
    move-object v5, p3

    goto :goto_8

    .line 6
    :goto_9
    invoke-interface {p2}, Ljava/lang/CharSequence;->length()I

    move-result p5

    if-lez p5, :cond_12

    .line 7
    iput-object p1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    iput-object p3, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zza:Ljava/lang/Object;

    iput-object v5, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzb:Ljava/lang/Object;

    iput-object p2, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzh:Ljava/lang/String;

    iput-object v9, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzi:Ljava/lang/String;

    iput-boolean p4, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzc:Z

    iput v3, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzf:I

    invoke-direct {p0, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzA(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p5

    if-ne p5, v0, :cond_f

    goto :goto_d

    :cond_f
    move-object v1, p1

    move-object v3, p3

    move v6, p4

    goto/16 :goto_2

    :goto_a
    check-cast p5, Ljava/lang/Boolean;

    invoke-virtual {p5}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_11

    .line 9
    iput-object v9, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    iput-object v9, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zza:Ljava/lang/Object;

    iput-object v9, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzb:Ljava/lang/Object;

    iput-object v9, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzh:Ljava/lang/String;

    iput v2, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzf:I

    move-object v2, v1

    move-object v1, p0

    invoke-direct/range {v1 .. v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzy(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Ljava/lang/String;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_10

    goto :goto_d

    :cond_10
    return-object p0

    .line 8
    :cond_11
    new-instance p0, Lcom/google/mlkit/genai/common/GenAiException;

    const-string p1, "Prefix Caching feature is not available."

    const/16 p2, -0x67

    invoke-direct {p0, p1, v9, p2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw p0

    :cond_12
    move p2, p4

    move-object p4, p1

    move p1, p2

    move-object p2, v5

    goto :goto_7

    :goto_b
    if-eqz p2, :cond_13

    .line 1
    iget-object p0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    goto :goto_c

    .line 11
    :cond_13
    iget-object p0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    .line 1
    :goto_c
    new-instance p5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    .line 10
    invoke-direct {p5, p4, p2, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;-><init>(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/reflect/KClass;Z)V

    .line 11
    iput-object v9, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    iput-object v9, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zza:Ljava/lang/Object;

    iput-object v9, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzb:Ljava/lang/Object;

    iput-object v9, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzh:Ljava/lang/String;

    iput-object v9, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzi:Ljava/lang/String;

    iput v8, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzf:I

    invoke-virtual {p0, p5, p3, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzI(Ljava/lang/Object;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_14

    :goto_d
    return-object v0

    :cond_14
    return-object p0
.end method

.method private final zzx(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Ljava/lang/String;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 24

    move-object/from16 v1, p0

    move-object/from16 v0, p6

    instance-of v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;

    const-string v3, "Failed to open cache for prefix: "

    const-string v4, "Failed to open file descriptor to create cache for prefix: "

    if-eqz v2, :cond_0

    move-object v2, v0

    check-cast v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;

    iget v5, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    const/high16 v6, -0x80000000

    and-int v7, v5, v6

    if-eqz v7, :cond_0

    sub-int/2addr v5, v6

    iput v5, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    goto :goto_0

    .line 3
    :cond_0
    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;

    invoke-direct {v2, v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object v0, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzf:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v5

    iget v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    const-string v7, "GenerativeModel"

    const/4 v8, 0x1

    const/4 v9, 0x0

    packed-switch v6, :pswitch_data_0

    .line 3
    const-string v0, "call to \'resume\' before \'invoke\' with coroutine"

    .line 1
    invoke-static {v0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v9

    :pswitch_0
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_15

    :pswitch_1
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_14

    :pswitch_2
    iget-object v3, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    check-cast v3, Ljava/lang/String;

    iget-object v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    check-cast v4, Ljava/lang/String;

    :try_start_0
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    goto/16 :goto_12

    :pswitch_3
    iget-boolean v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iget-object v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iget-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    check-cast v10, Lkotlin/reflect/KClass;

    iget-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iget-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    check-cast v12, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    check-cast v13, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    :try_start_1
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_1 .. :try_end_1} :catch_1

    move-object v9, v6

    move-object v6, v12

    move-object v12, v11

    goto/16 :goto_e

    :catch_1
    move-exception v0

    move-object v3, v6

    move-object v4, v11

    goto/16 :goto_12

    :pswitch_4
    iget v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zze:I

    iget-boolean v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iget-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iget-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iget-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    check-cast v12, Lkotlin/reflect/KClass;

    iget-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iget-object v14, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    check-cast v14, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    check-cast v15, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move v8, v4

    move-object v9, v11

    move-object v4, v13

    move-object v13, v15

    :goto_1
    move-object v11, v10

    move-object v10, v12

    move-object v12, v14

    goto/16 :goto_d

    :pswitch_5
    iget v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zze:I

    iget-boolean v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iget-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iget-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iget-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    check-cast v12, Lkotlin/reflect/KClass;

    iget-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iget-object v14, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    check-cast v14, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    check-cast v15, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    :try_start_2
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_2
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_2 .. :try_end_2} :catch_2

    goto/16 :goto_7

    :catch_2
    move-exception v0

    goto/16 :goto_b

    :pswitch_6
    iget v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zze:I

    iget-boolean v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iget-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iget-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iget-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    check-cast v12, Lkotlin/reflect/KClass;

    iget-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iget-object v14, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    check-cast v14, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    check-cast v15, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    :try_start_3
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_3
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_3 .. :try_end_3} :catch_2

    goto/16 :goto_6

    :pswitch_7
    iget v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zze:I

    iget-boolean v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iget-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iget-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iget-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    check-cast v13, Lkotlin/reflect/KClass;

    iget-object v14, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iget-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    check-cast v15, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    check-cast v9, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    :try_start_4
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_4
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_4 .. :try_end_4} :catch_3

    move-object/from16 v18, v15

    move-object v15, v9

    move-object/from16 v9, v18

    move-object/from16 v18, v14

    move v14, v6

    move v6, v10

    move-object v10, v11

    move-object v11, v12

    move-object v12, v13

    goto/16 :goto_5

    :catch_3
    move-exception v0

    move v6, v10

    move-object v10, v11

    move-object v11, v12

    move-object v12, v13

    move-object v13, v14

    move-object v14, v15

    move-object v15, v9

    goto/16 :goto_b

    :pswitch_8
    iget v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zze:I

    iget-boolean v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iget-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iget-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iget-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    check-cast v12, Lkotlin/reflect/KClass;

    iget-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iget-object v14, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    check-cast v14, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    check-cast v15, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object/from16 v23, v14

    move v14, v6

    move v6, v9

    move-object/from16 v9, v23

    goto/16 :goto_4

    :pswitch_9
    iget-boolean v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iget-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iget-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    check-cast v10, Lkotlin/reflect/KClass;

    iget-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iget-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    check-cast v12, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    check-cast v13, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_3

    :pswitch_a
    iget-boolean v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iget-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    check-cast v9, Lkotlin/reflect/KClass;

    iget-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iget-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    check-cast v11, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    check-cast v12, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    :try_start_5
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_5
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_5 .. :try_end_5} :catch_4

    move-object/from16 v23, v10

    move-object v10, v9

    move-object/from16 v9, v23

    goto :goto_2

    :catch_4
    move-exception v0

    goto/16 :goto_18

    :pswitch_b
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object/from16 v0, p1

    .line 2
    :try_start_6
    iput-object v0, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    move-object/from16 v6, p2

    iput-object v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    move-object/from16 v9, p3

    iput-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    move-object/from16 v10, p4

    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    move/from16 v11, p5

    iput-boolean v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iput v8, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    iget-object v12, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    .line 3
    invoke-virtual {v12, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzz(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v12

    if-ne v12, v5, :cond_1

    goto/16 :goto_16

    :cond_1
    move-object/from16 v23, v12

    move-object v12, v0

    move-object/from16 v0, v23

    move/from16 v23, v11

    move-object v11, v6

    move/from16 v6, v23

    .line 4
    :goto_2
    check-cast v0, Ljava/lang/String;
    :try_end_6
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_6 .. :try_end_6} :catch_4

    iget-object v13, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzl:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    sget-object v14, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

    .line 6
    invoke-static {v14}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;)Landroid/content/Context;

    move-result-object v14

    iput-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    iput-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    iput-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    iput-object v0, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iput-boolean v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    const/4 v15, 0x2

    iput v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    .line 7
    invoke-static {v13, v14, v9, v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v13

    if-ne v13, v5, :cond_2

    goto/16 :goto_16

    :cond_2
    move-object/from16 v23, v9

    move-object v9, v0

    move-object v0, v13

    move-object v13, v12

    move-object v12, v11

    move-object/from16 v11, v23

    .line 1
    :goto_3
    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    const/4 v14, 0x0

    if-nez v0, :cond_6

    .line 8
    iput-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    iput-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    iput-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    iput-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    const/4 v15, 0x0

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iput-boolean v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iput v14, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zze:I

    const/4 v15, 0x3

    iput v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    invoke-direct {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzz(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v15

    if-eq v15, v5, :cond_d

    move-object/from16 v23, v10

    move-object v10, v0

    move-object v0, v15

    move-object v15, v13

    move-object v13, v11

    move-object v11, v9

    move-object v9, v12

    move-object/from16 v12, v23

    :goto_4
    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_5

    :try_start_7
    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzl:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    sget-object v16, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

    .line 9
    invoke-static/range {v16 .. v16}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;)Landroid/content/Context;

    move-result-object v8

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    iput-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    iput-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iput-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    iput-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iput-boolean v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iput v14, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zze:I
    :try_end_7
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_7 .. :try_end_7} :catch_6

    move/from16 v16, v6

    const/4 v6, 0x4

    :try_start_8
    iput v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    .line 10
    invoke-static {v0, v8, v13, v11, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0
    :try_end_8
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_8 .. :try_end_8} :catch_5

    if-eq v0, v5, :cond_d

    move-object/from16 v18, v13

    move/from16 v6, v16

    .line 1
    :goto_5
    :try_start_9
    move-object/from16 v19, v0

    check-cast v19, Landroid/os/ParcelFileDescriptor;

    if-eqz v19, :cond_4

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    .line 12
    invoke-virtual {v15}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getPromptPrefix()Lcom/google/mlkit/genai/prompt/PromptPrefix;

    move-result-object v4

    if-eqz v4, :cond_3

    .line 13
    invoke-virtual {v4}, Lcom/google/mlkit/genai/prompt/PromptPrefix;->getTextString()Ljava/lang/String;

    move-result-object v8

    .line 14
    invoke-static {v8, v4}, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequestKt;->createCachedContextRequest(Ljava/lang/String;Lcom/google/mlkit/genai/prompt/PromptPrefix;)Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    move-result-object v4

    new-instance v17, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;

    const/16 v21, 0x4

    const/16 v22, 0x0

    const/16 v20, 0x0

    .line 15
    invoke-direct/range {v17 .. v22}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;-><init>(Ljava/lang/String;Landroid/os/ParcelFileDescriptor;Landroid/os/ParcelFileDescriptor;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    :try_end_9
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_9 .. :try_end_9} :catch_7

    move-object/from16 v8, v17

    move-object/from16 v13, v18

    .line 16
    :try_start_a
    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    iput-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    iput-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iput-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    iput-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iput-boolean v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iput v14, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zze:I
    :try_end_a
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_a .. :try_end_a} :catch_6

    move/from16 v16, v6

    const/4 v6, 0x5

    :try_start_b
    iput v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    .line 17
    invoke-static {v0, v4, v8, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0
    :try_end_b
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_b .. :try_end_b} :catch_5

    if-eq v0, v5, :cond_d

    move v4, v14

    move/from16 v6, v16

    move-object v14, v9

    :goto_6
    :try_start_c
    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzl:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    sget-object v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

    .line 18
    invoke-static {v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;)Landroid/content/Context;

    move-result-object v8

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    iput-object v14, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    iput-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iput-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    iput-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iput-boolean v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iput v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zze:I

    const/4 v9, 0x6

    iput v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    .line 19
    invoke-static {v0, v8, v13, v11, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-eq v0, v5, :cond_d

    .line 18
    :goto_7
    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;
    :try_end_c
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_c .. :try_end_c} :catch_2

    move-object v10, v0

    :goto_8
    move-object v9, v11

    move-object v11, v13

    move-object v13, v15

    goto/16 :goto_c

    :catch_5
    move-exception v0

    goto :goto_a

    :catch_6
    move-exception v0

    move/from16 v16, v6

    goto :goto_a

    :catch_7
    move-exception v0

    move/from16 v16, v6

    move-object/from16 v13, v18

    goto :goto_a

    :cond_3
    move/from16 v16, v6

    move-object/from16 v13, v18

    .line 12
    :try_start_d
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v4, "Prompt prefix is not set."

    .line 13
    invoke-direct {v0, v4}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_4
    move/from16 v16, v6

    move-object/from16 v13, v18

    .line 20
    invoke-static {v13}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, 0x3b

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v7, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_d
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_d .. :try_end_d} :catch_5

    move-object v14, v9

    move-object v9, v11

    move-object v11, v13

    move-object v13, v15

    move/from16 v6, v16

    :goto_9
    const/4 v4, 0x1

    goto :goto_c

    :goto_a
    move-object v14, v9

    move/from16 v6, v16

    .line 21
    :goto_b
    const-string v4, "Failed to create cache for prefix: "

    invoke-static {v13}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v4, v8}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-static {v7, v4, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    move-object v9, v11

    move-object v11, v13

    move-object v13, v15

    goto :goto_9

    :cond_5
    move/from16 v16, v6

    move v4, v14

    move-object v14, v9

    goto :goto_8

    :cond_6
    move v4, v14

    move-object v14, v12

    move-object v12, v10

    move-object v10, v0

    :goto_c
    if-eqz v10, :cond_b

    .line 22
    iput-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    iput-object v14, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    iput-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iput-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    iput-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iput-boolean v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    iput v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zze:I

    const/4 v0, 0x7

    iput v0, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    invoke-direct {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzA(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-eq v0, v5, :cond_d

    move v8, v4

    move-object v4, v11

    goto/16 :goto_1

    :goto_d
    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_a

    :try_start_e
    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzl:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    sget-object v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

    .line 23
    invoke-static {v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;)Landroid/content/Context;

    move-result-object v8

    iput-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    iput-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    iput-object v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    iput-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    const/4 v15, 0x0

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iput-boolean v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzd:Z

    const/16 v14, 0x8

    iput v14, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    .line 24
    invoke-static {v0, v8, v11, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0
    :try_end_e
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_e .. :try_end_e} :catch_9

    if-eq v0, v5, :cond_d

    move-object/from16 v23, v12

    move-object v12, v4

    move v4, v6

    move-object/from16 v6, v23

    .line 1
    :goto_e
    :try_start_f
    move-object v14, v0

    check-cast v14, Landroid/os/ParcelFileDescriptor;

    if-eqz v14, :cond_9

    if-eqz v10, :cond_7

    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    goto :goto_f

    :catch_8
    move-exception v0

    goto :goto_11

    .line 28
    :cond_7
    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    .line 1
    :goto_f
    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    .line 25
    invoke-direct {v3, v13, v10, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;-><init>(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/reflect/KClass;Z)V

    new-instance v11, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;

    const/4 v15, 0x2

    const/16 v16, 0x0

    const/4 v13, 0x0

    .line 26
    invoke-direct/range {v11 .. v16}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;-><init>(Ljava/lang/String;Landroid/os/ParcelFileDescriptor;Landroid/os/ParcelFileDescriptor;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 27
    iput-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    iput-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    const/4 v15, 0x0

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    const/16 v4, 0x9

    iput v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    .line 28
    invoke-static {v0, v3, v11, v6, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-ne v0, v5, :cond_8

    goto/16 :goto_16

    :cond_8
    return-object v0

    .line 29
    :cond_9
    invoke-static {v12}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, 0x21

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v7, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_f
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_f .. :try_end_f} :catch_8

    move-object v11, v12

    :goto_10
    const/4 v8, 0x1

    goto :goto_13

    :goto_11
    move-object v3, v9

    move-object v4, v12

    goto :goto_12

    :catch_9
    move-exception v0

    move-object v3, v9

    .line 21
    :goto_12
    const-string v6, "Inference failed with prefix cache, retry without cache."

    .line 30
    invoke-static {v7, v6, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    move-object v9, v3

    move-object v11, v4

    goto :goto_10

    :cond_a
    move-object v11, v4

    goto :goto_13

    :cond_b
    move v8, v4

    :goto_13
    if-eqz v8, :cond_c

    .line 29
    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzl:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    sget-object v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

    .line 31
    invoke-static {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;)Landroid/content/Context;

    move-result-object v3

    const/4 v15, 0x0

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zza:Ljava/lang/Object;

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzb:Ljava/lang/Object;

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzi:Ljava/lang/String;

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzc:Ljava/lang/Object;

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzj:Ljava/lang/String;

    iput-object v15, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    const/16 v4, 0xa

    iput v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    .line 32
    invoke-static {v0, v3, v11, v9, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-eq v0, v5, :cond_d

    .line 1
    :goto_14
    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    if-eqz v0, :cond_c

    iget-object v1, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzl:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    sget-object v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

    .line 33
    invoke-static {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;)Landroid/content/Context;

    move-result-object v3

    const/16 v4, 0xb

    iput v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzq;->zzh:I

    .line 34
    invoke-static {v1, v3, v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-eq v0, v5, :cond_d

    .line 33
    :goto_15
    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    :cond_c
    const/4 v15, 0x0

    goto :goto_17

    :cond_d
    :goto_16
    return-object v5

    :goto_17
    return-object v15

    .line 30
    :goto_18
    const-string v1, "Failed to get base model name, falling back to major processor"

    .line 5
    invoke-static {v7, v1, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 v15, 0x0

    return-object v15

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private final zzy(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Ljava/lang/String;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 17

    move-object/from16 v0, p0

    move-object/from16 v1, p6

    instance-of v2, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;

    if-eqz v2, :cond_0

    move-object v2, v1

    check-cast v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;

    iget v3, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzf:I

    const/high16 v4, -0x80000000

    and-int v5, v3, v4

    if-eqz v5, :cond_0

    sub-int/2addr v3, v4

    iput v3, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzf:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;

    invoke-direct {v2, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object v1, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzd:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v3

    iget v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzf:I

    const/16 v5, -0x67

    const/4 v6, 0x4

    const/4 v7, 0x3

    const/4 v8, 0x2

    const/4 v9, 0x1

    const/4 v10, 0x0

    if-eqz v4, :cond_5

    if-eq v4, v9, :cond_4

    if-eq v4, v8, :cond_3

    if-eq v4, v7, :cond_2

    if-ne v4, v6, :cond_1

    invoke-static {v1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object v1

    :cond_1
    const-string v0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {v0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v10

    :cond_2
    iget-boolean v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzc:Z

    iget-object v7, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;

    iget-object v8, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzb:Ljava/lang/Object;

    check-cast v8, Lkotlin/reflect/KClass;

    iget-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzh:Ljava/lang/String;

    iget-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zza:Ljava/lang/Object;

    check-cast v11, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {v1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_3

    :cond_3
    iget-boolean v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzc:Z

    iget-object v8, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzb:Ljava/lang/Object;

    check-cast v8, Lkotlin/reflect/KClass;

    iget-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzh:Ljava/lang/String;

    iget-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zza:Ljava/lang/Object;

    check-cast v11, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {v1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_2

    :cond_4
    iget-boolean v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzc:Z

    iget-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzb:Ljava/lang/Object;

    check-cast v9, Lkotlin/reflect/KClass;

    iget-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzh:Ljava/lang/String;

    iget-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zza:Ljava/lang/Object;

    check-cast v12, Lcom/google/mlkit/genai/common/StreamingCallback;

    iget-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {v1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_5
    invoke-static {v1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object/from16 v1, p1

    .line 2
    iput-object v1, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-object/from16 v4, p2

    iput-object v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zza:Ljava/lang/Object;

    move-object/from16 v11, p3

    iput-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzh:Ljava/lang/String;

    move-object/from16 v12, p4

    iput-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzb:Ljava/lang/Object;

    move/from16 v13, p5

    iput-boolean v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzc:Z

    iput v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzf:I

    iget-object v9, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    .line 3
    invoke-virtual {v9, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzz(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v9

    if-eq v9, v3, :cond_a

    move/from16 v16, v13

    move-object v13, v1

    move-object v1, v9

    move-object v9, v12

    move-object v12, v4

    move/from16 v4, v16

    .line 1
    :goto_1
    check-cast v1, Ljava/lang/String;

    iget-object v14, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    sget-object v15, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

    .line 4
    invoke-static {v15}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;)Landroid/content/Context;

    move-result-object v15

    iput-object v13, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    iput-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zza:Ljava/lang/Object;

    iput-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzh:Ljava/lang/String;

    iput-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzb:Ljava/lang/Object;

    iput-boolean v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzc:Z

    iput v8, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzf:I

    .line 5
    invoke-static {v14, v15, v11, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v1

    if-eq v1, v3, :cond_a

    move-object v8, v9

    move-object v9, v11

    move-object v11, v12

    move-object v12, v13

    .line 1
    :goto_2
    check-cast v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;

    if-eqz v1, :cond_9

    .line 6
    iget-object v13, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzk:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    sget-object v14, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;

    .line 8
    invoke-static {v14}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;)Landroid/content/Context;

    move-result-object v14

    iput-object v12, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    iput-object v11, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zza:Ljava/lang/Object;

    iput-object v9, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzh:Ljava/lang/String;

    iput-object v8, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzb:Ljava/lang/Object;

    iput-object v1, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;

    iput-boolean v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzc:Z

    iput v7, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzf:I

    .line 9
    invoke-static {v13, v14, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v7

    if-eq v7, v3, :cond_a

    move-object/from16 v16, v7

    move-object v7, v1

    move-object/from16 v1, v16

    .line 1
    :goto_3
    check-cast v1, Landroid/os/ParcelFileDescriptor;

    if-eqz v1, :cond_8

    if-eqz v8, :cond_6

    .line 10
    iget-object v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    goto :goto_4

    .line 15
    :cond_6
    iget-object v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    .line 10
    :goto_4
    new-instance v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    .line 12
    invoke-direct {v5, v12, v8, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;-><init>(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/reflect/KClass;Z)V

    new-instance v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;

    .line 13
    invoke-virtual {v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;->zzd()Ljava/lang/String;

    move-result-object v7

    const/4 v8, 0x2

    const/4 v9, 0x0

    const/4 v12, 0x0

    move-object/from16 p3, v1

    move-object/from16 p0, v4

    move-object/from16 p1, v7

    move/from16 p4, v8

    move-object/from16 p5, v9

    move-object/from16 p2, v12

    invoke-direct/range {p0 .. p5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;-><init>(Ljava/lang/String;Landroid/os/ParcelFileDescriptor;Landroid/os/ParcelFileDescriptor;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object/from16 v1, p0

    .line 14
    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zza:Ljava/lang/Object;

    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzh:Ljava/lang/String;

    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzb:Ljava/lang/Object;

    iput-object v10, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzi:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;

    iput v6, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzp;->zzf:I

    .line 15
    invoke-static {v0, v5, v1, v11, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-ne v0, v3, :cond_7

    goto :goto_5

    :cond_7
    return-object v0

    .line 10
    :cond_8
    new-instance v0, Lcom/google/mlkit/genai/common/GenAiException;

    .line 11
    invoke-static {v9}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "Failed to open cached context for name: "

    invoke-virtual {v2, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 10
    invoke-direct {v0, v1, v10, v5}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw v0

    .line 6
    :cond_9
    new-instance v0, Lcom/google/mlkit/genai/common/GenAiException;

    .line 7
    invoke-static {v9}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "Cached context not found for name: "

    invoke-virtual {v2, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 6
    invoke-direct {v0, v1, v10, v5}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw v0

    :cond_a
    :goto_5
    return-object v3
.end method

.method private final zzz(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 4

    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzd;

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzd;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzd;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzd;->zzc:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzd;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzd;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzd;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzd;->zzc:I

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

    .line 2
    iput v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzd;->zzc:I

    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzC(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_4

    :goto_1
    check-cast p1, Ljava/lang/Number;

    invoke-virtual {p1}, Ljava/lang/Number;->intValue()I

    move-result p0

    const/4 p1, 0x3

    if-ne p0, p1, :cond_3

    goto :goto_2

    :cond_3
    const/4 v3, 0x0

    :goto_2
    invoke-static {v3}, Lkotlin/coroutines/jvm/internal/Boxing;->boxBoolean(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0

    :cond_4
    return-object v1
.end method


# virtual methods
.method public final checkStatus(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Ljava/lang/Integer;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    invoke-static {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final close()V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->close()V

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    .line 2
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->close()V

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    .line 3
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->close()V

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    .line 4
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->close()V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    .line 5
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->close()V

    return-void
.end method

.method public final countTokens(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lcom/google/mlkit/genai/prompt/CountTokensResponse;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    const/4 v0, 0x0

    const/4 v1, 0x1

    .line 1
    invoke-direct {p0, p1, v0, v1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzv(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final download()Lkotlinx/coroutines/flow/Flow;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lkotlinx/coroutines/flow/Flow<",
            "Lcom/google/mlkit/genai/common/DownloadStatus;",
            ">;"
        }
    .end annotation

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzj;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    invoke-direct {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzj;-><init>(Ljava/lang/Object;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzi:Lkotlinx/coroutines/CoroutineScope;

    .line 3
    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;

    const/4 v2, 0x0

    invoke-direct {v1, p0, v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyo;-><init>(Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)V

    invoke-static {v1}, Lkotlinx/coroutines/flow/FlowKt;->callbackFlow(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;

    move-result-object p0

    return-object p0
.end method

.method public final generateContent(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
            "Lcom/google/mlkit/genai/common/StreamingCallback;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lcom/google/mlkit/genai/prompt/GenerateContentResponse;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    const/4 v3, 0x0

    const/4 v4, 0x1

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v5, p3

    .line 1
    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzw(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final generateContent(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lcom/google/mlkit/genai/prompt/GenerateContentResponse;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    const/4 v3, 0x0

    const/4 v4, 0x1

    const/4 v2, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v5, p2

    .line 2
    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzw(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final getBaseModelName(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Ljava/lang/String;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzz(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final getCaches()Lcom/google/mlkit/genai/prompt/Caches;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzm:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/mlkit/genai/prompt/Caches;

    return-object p0
.end method

.method public final isSystemPromptAvailable(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Ljava/lang/Boolean;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzt;

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzt;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzt;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzt;->zzc:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzt;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzt;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzt;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzt;->zzc:I

    const/4 v3, 0x3

    const/4 v4, 0x2

    const/4 v5, 0x1

    const/4 v6, 0x0

    if-eqz v2, :cond_3

    if-eq v2, v5, :cond_2

    if-ne v2, v4, :cond_1

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    :catch_0
    move-exception p0

    goto :goto_4

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    iput v5, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzt;->zzc:I

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    .line 3
    invoke-static {p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_7

    .line 2
    :goto_1
    check-cast p1, Ljava/lang/Number;

    invoke-virtual {p1}, Ljava/lang/Number;->intValue()I

    move-result p1

    if-eq p1, v3, :cond_4

    .line 4
    invoke-static {v6}, Lkotlin/coroutines/jvm/internal/Boxing;->boxBoolean(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0

    .line 5
    :cond_4
    invoke-static {}, Lcom/google/mlkit/common/sdkinternal/MlKitContext;->getInstance()Lcom/google/mlkit/common/sdkinternal/MlKitContext;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/mlkit/common/sdkinternal/MlKitContext;->getApplicationContext()Landroid/content/Context;

    move-result-object p1

    .line 6
    invoke-static {p1}, Lcom/google/mlkit/genai/common/internal/GenAiUtils;->isSystemPromptSupported(Landroid/content/Context;)Z

    move-result p1

    if-nez p1, :cond_5

    .line 7
    invoke-static {v6}, Lkotlin/coroutines/jvm/internal/Boxing;->boxBoolean(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0

    .line 8
    :cond_5
    :try_start_1
    iput v4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzt;->zzc:I

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    .line 9
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzz(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_7

    .line 1
    :goto_2
    check-cast p1, Ljava/lang/String;

    .line 10
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzE(Ljava/lang/String;)I

    move-result p0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    if-lt p0, v3, :cond_6

    goto :goto_5

    :cond_6
    :goto_3
    move v5, v6

    goto :goto_5

    .line 1
    :goto_4
    const-string p1, "GenerativeModel"

    const-string v0, "Failed to get model name for capability check"

    .line 11
    invoke-static {p1, v0, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_3

    .line 12
    :goto_5
    invoke-static {v5}, Lkotlin/coroutines/jvm/internal/Boxing;->boxBoolean(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0

    :cond_7
    return-object v1
.end method

.method public final isThinkingModeAvailable(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Ljava/lang/Boolean;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzu;

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzu;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzu;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzu;->zzc:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzu;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzu;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzu;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzu;->zzc:I

    const/4 v3, 0x2

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz v2, :cond_3

    if-eq v2, v4, :cond_2

    if-ne v2, v3, :cond_1

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    :catch_0
    move-exception p0

    goto :goto_4

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    iput v4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzu;->zzc:I

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzj:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    .line 3
    invoke-static {p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_7

    .line 2
    :goto_1
    check-cast p1, Ljava/lang/Number;

    invoke-virtual {p1}, Ljava/lang/Number;->intValue()I

    move-result p1

    const/4 v2, 0x3

    if-eq p1, v2, :cond_4

    .line 4
    invoke-static {v5}, Lkotlin/coroutines/jvm/internal/Boxing;->boxBoolean(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0

    .line 5
    :cond_4
    invoke-static {}, Lcom/google/mlkit/common/sdkinternal/MlKitContext;->getInstance()Lcom/google/mlkit/common/sdkinternal/MlKitContext;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/mlkit/common/sdkinternal/MlKitContext;->getApplicationContext()Landroid/content/Context;

    move-result-object p1

    .line 6
    invoke-static {p1}, Lcom/google/mlkit/genai/common/internal/GenAiUtils;->isThinkingModeSupported(Landroid/content/Context;)Z

    move-result p1

    if-nez p1, :cond_5

    .line 7
    invoke-static {v5}, Lkotlin/coroutines/jvm/internal/Boxing;->boxBoolean(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0

    .line 8
    :cond_5
    :try_start_1
    iput v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzu;->zzc:I

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    .line 9
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzz(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_7

    .line 1
    :goto_2
    check-cast p1, Ljava/lang/String;

    .line 10
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzE(Ljava/lang/String;)I

    move-result p0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    const/4 p1, 0x4

    if-lt p0, p1, :cond_6

    goto :goto_5

    :cond_6
    :goto_3
    move v4, v5

    goto :goto_5

    .line 1
    :goto_4
    const-string p1, "GenerativeModel"

    const-string v0, "Failed to get model name for capability check"

    .line 11
    invoke-static {p1, v0, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_3

    .line 12
    :goto_5
    invoke-static {v4}, Lkotlin/coroutines/jvm/internal/Boxing;->boxBoolean(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0

    :cond_7
    return-object v1
.end method

.method public final warmup(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lkotlin/Unit;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzw;

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzw;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzw;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzw;->zzc:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzw;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzw;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzw;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzw;->zzc:I

    const/4 v3, 0x2

    const/4 v4, 0x1

    if-eqz v2, :cond_3

    if-eq v2, v4, :cond_2

    if-ne v2, v3, :cond_1

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_2

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    iput v4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzw;->zzc:I

    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzu(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v1, :cond_4

    goto :goto_3

    :cond_4
    :goto_1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    .line 3
    iput v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzw;->zzc:I

    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzE(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-eq p0, v1, :cond_5

    .line 4
    :goto_2
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :cond_5
    :goto_3
    return-object v1
.end method

.method public final zza(Lcom/google/mlkit/genai/common/DownloadCallback;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyu;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyu;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lcom/google/mlkit/genai/common/DownloadCallback;)V

    invoke-static {v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzb()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyv;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyv;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;)V

    invoke-static {v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzc(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 6

    .line 1
    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;

    const/4 v0, 0x0

    invoke-direct {v3, p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzh;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzi:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/guava/ListenableFutureKt;->future$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzd()Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 6

    .line 1
    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzr;

    const/4 v0, 0x0

    invoke-direct {v3, p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzr;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzi:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/guava/ListenableFutureKt;->future$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method

.method public final zzf()Ljava/util/concurrent/ExecutorService;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzh:Ljava/util/concurrent/ExecutorService;

    return-object p0
.end method
