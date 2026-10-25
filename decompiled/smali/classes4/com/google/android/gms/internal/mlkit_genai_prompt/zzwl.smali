.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;
.source "SourceFile"

# interfaces
.implements Ljava/lang/AutoCloseable;


# instance fields
.field private final zza:Ljava/util/concurrent/ConcurrentHashMap;

.field private final zzb:Ljava/util/concurrent/ConcurrentHashMap;


# direct methods
.method public constructor <init>(ILjava/util/concurrent/ExecutorService;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;)V
    .locals 1

    const/4 v0, -0x1

    .line 1
    invoke-direct {p0, p2, p1, v0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;-><init>(Ljava/util/concurrent/ExecutorService;IILcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;)V

    new-instance p1, Ljava/util/concurrent/ConcurrentHashMap;

    .line 2
    invoke-direct {p1}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zza:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance p1, Ljava/util/concurrent/ConcurrentHashMap;

    .line 3
    invoke-direct {p1}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzb:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzp()Ljava/util/concurrent/ExecutorService;

    move-result-object p0

    .line 4
    invoke-static {p0}, Lkotlinx/coroutines/ExecutorsKt;->from(Ljava/util/concurrent/ExecutorService;)Lkotlinx/coroutines/ExecutorCoroutineDispatcher;

    move-result-object p0

    invoke-static {p0}, Lkotlinx/coroutines/CoroutineScopeKt;->CoroutineScope(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;

    return-void
.end method

.method public static synthetic zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 4

    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwg;

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwg;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwg;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwg;->zzc:I

    goto :goto_0

    .line 2
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwg;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwg;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwg;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwg;->zzc:I

    const/4 v3, 0x1

    if-eqz v2, :cond_2

    if-ne v2, v3, :cond_1

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p1

    .line 2
    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    .line 1
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    :try_start_1
    iput v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwg;->zzc:I

    invoke-super {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zza(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0
    :try_end_1
    .catch Lcom/google/mlkit/genai/common/GenAiException; {:try_start_1 .. :try_end_1} :catch_0

    if-ne p0, v1, :cond_3

    return-object v1

    :cond_3
    return-object p0

    :catch_0
    const/4 p0, 0x0

    .line 3
    invoke-static {p0}, Lkotlin/coroutines/jvm/internal/Boxing;->boxInt(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;

    const/4 v1, 0x0

    invoke-direct {v0, p2, p0, p1, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwk;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Lkotlin/coroutines/Continuation;)V

    invoke-static {v0, p3}, Lkotlinx/coroutines/CoroutineScopeKt;->coroutineScope(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;)Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzb:Ljava/util/concurrent/ConcurrentHashMap;

    return-object p0
.end method

.method public static final synthetic zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;)Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zza:Ljava/util/concurrent/ConcurrentHashMap;

    return-object p0
.end method


# virtual methods
.method public final zza(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    invoke-static {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;
    .locals 0

    .line 1
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeu;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzet;

    move-result-object p0

    invoke-virtual {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzet;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzet;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzet;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeu;

    move-result-object p0

    .line 2
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzer;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeu;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzer;

    move-result-object p0

    return-object p0
.end method

.method public final bridge synthetic zze(Ljava/lang/Object;Lcom/google/mlkit/genai/common/StreamingCallback;)Ljava/lang/Object;
    .locals 5

    .line 1
    check-cast p1, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    .line 2
    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzb:Ljava/util/concurrent/ConcurrentHashMap;

    .line 3
    invoke-virtual {p2, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;

    const/16 v0, -0x67

    const/4 v1, 0x0

    if-eqz p2, :cond_3

    .line 4
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;->zza()Ljava/lang/String;

    move-result-object v2

    .line 5
    invoke-interface {v2}, Ljava/lang/CharSequence;->length()I

    move-result v3

    if-eqz v3, :cond_2

    .line 7
    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v3

    const/16 v4, 0x7d00

    if-gt v3, v4, :cond_1

    .line 8
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;->zzb()Landroid/os/ParcelFileDescriptor;

    move-result-object p2

    if-eqz p2, :cond_0

    .line 10
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzq()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;

    move-result-object v0

    .line 11
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;->zzi(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;

    const/4 v1, 0x2

    .line 12
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;->zzl(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;

    .line 13
    invoke-virtual {v0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;->zza(Landroid/os/ParcelFileDescriptor;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;

    new-instance p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    invoke-direct {p2, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;)V

    .line 15
    invoke-virtual {v0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;->zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;

    .line 16
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzem;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;

    move-result-object p0

    return-object p0

    .line 9
    :cond_0
    new-instance p0, Lcom/google/mlkit/genai/common/GenAiException;

    const-string p1, "File descriptor to write is null"

    invoke-direct {p0, p1, v1, v0}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw p0

    .line 8
    :cond_1
    new-instance p0, Lcom/google/mlkit/genai/common/GenAiException;

    const-string p1, "Prompt prefix length exceeds the limit. Please check the countTokens API."

    const/16 p2, 0xc

    invoke-direct {p0, p1, v1, p2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw p0

    .line 6
    :cond_2
    new-instance p0, Lcom/google/mlkit/genai/common/GenAiException;

    const-string p1, "Prompt prefix is empty"

    const/4 p2, 0x0

    invoke-direct {p0, p1, v1, p2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw p0

    .line 4
    :cond_3
    new-instance p0, Lcom/google/mlkit/genai/common/GenAiException;

    const-string p1, "Prefix parameters not found for request"

    invoke-direct {p0, p1, v1, v0}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw p0
.end method

.method public final bridge synthetic zzf(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzep;

    .line 3
    sget-object p0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    return-object p0
.end method
