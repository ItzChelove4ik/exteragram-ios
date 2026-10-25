.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;
.source "SourceFile"

# interfaces
.implements Ljava/lang/AutoCloseable;


# instance fields
.field private final zza:Ljava/util/concurrent/ConcurrentHashMap;


# direct methods
.method public constructor <init>(ILjava/util/concurrent/ExecutorService;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;)V
    .locals 1

    const/4 v0, -0x1

    .line 1
    invoke-direct {p0, p2, p1, v0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;-><init>(Ljava/util/concurrent/ExecutorService;IILcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;)V

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzp()Ljava/util/concurrent/ExecutorService;

    move-result-object p1

    .line 2
    invoke-static {p1}, Lkotlinx/coroutines/ExecutorsKt;->from(Ljava/util/concurrent/ExecutorService;)Lkotlinx/coroutines/ExecutorCoroutineDispatcher;

    move-result-object p1

    invoke-static {p1}, Lkotlinx/coroutines/CoroutineScopeKt;->CoroutineScope(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;

    new-instance p1, Ljava/util/concurrent/ConcurrentHashMap;

    .line 3
    invoke-direct {p1}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;->zza:Ljava/util/concurrent/ConcurrentHashMap;

    return-void
.end method

.method public static synthetic zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 4

    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaai;

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaai;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaai;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaai;->zzc:I

    goto :goto_0

    .line 2
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaai;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaai;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaai;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaai;->zzc:I

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
    iput v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaai;->zzc:I

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

.method public static synthetic zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 4

    instance-of v0, p4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;

    if-eqz v0, :cond_0

    move-object v0, p4

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zzc:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;

    invoke-direct {v0, p0, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zzc:I

    const/4 v3, 0x1

    if-eqz v2, :cond_2

    if-ne v2, v3, :cond_1

    iget-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    iget-object p0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    :try_start_0
    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_1

    :catchall_0
    move-exception p2

    goto :goto_2

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;->zza:Ljava/util/concurrent/ConcurrentHashMap;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-result-object v2

    invoke-interface {p4, v2, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 3
    :try_start_1
    iput-object p0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    iput-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    iput v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zzc:I

    invoke-virtual {p0, p1, p3, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;->zzI(Ljava/lang/Object;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p4
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-ne p4, v1, :cond_3

    return-object v1

    .line 4
    :cond_3
    :goto_1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;->zza:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    return-object p4

    :goto_2
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;->zza:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    throw p2
.end method

.method private static final zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;
    .locals 5

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;->zza()Ljava/lang/String;

    move-result-object v0

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v1

    const/4 v2, 0x0

    const/4 v3, 0x0

    if-eqz v1, :cond_2

    .line 3
    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v1

    const/16 v4, 0x7d00

    if-gt v1, v4, :cond_1

    .line 4
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;->zzc()Landroid/os/ParcelFileDescriptor;

    move-result-object v1

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;->zzc()Landroid/os/ParcelFileDescriptor;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 5
    invoke-static {v2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zzj(ILjava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;

    move-result-object p0

    .line 6
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzht;

    move-result-object v0

    .line 7
    invoke-static {p0}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p0

    invoke-virtual {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzht;->zza(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzht;

    .line 8
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzht;->zzb(Landroid/os/ParcelFileDescriptor;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzht;

    .line 9
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzht;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;

    move-result-object p0

    return-object p0

    .line 5
    :cond_0
    new-instance p0, Lcom/google/mlkit/genai/common/GenAiException;

    const-string v0, "File descriptor to read is null"

    const/16 v1, -0x67

    invoke-direct {p0, v0, v3, v1}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw p0

    .line 4
    :cond_1
    new-instance p0, Lcom/google/mlkit/genai/common/GenAiException;

    const-string v0, "Prompt prefix length exceeds the limit. Please check the countTokens API."

    const/16 v1, 0xc

    invoke-direct {p0, v0, v3, v1}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw p0

    .line 2
    :cond_2
    new-instance p0, Lcom/google/mlkit/genai/common/GenAiException;

    const-string v0, "Prompt prefix is empty"

    invoke-direct {p0, v0, v3, v2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw p0
.end method


# virtual methods
.method public final zza(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    invoke-static {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;
    .locals 0

    .line 1
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzij;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzii;

    move-result-object p0

    invoke-virtual {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzii;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzii;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzii;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzij;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzij;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;

    move-result-object p0

    return-object p0
.end method

.method public final bridge synthetic zze(Ljava/lang/Object;Lcom/google/mlkit/genai/common/StreamingCallback;)Ljava/lang/Object;
    .locals 1

    .line 1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    .line 3
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-result-object v0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;->zza:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0, v0}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;

    if-eqz p0, :cond_0

    .line 5
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;->zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;

    move-result-object p0

    .line 6
    invoke-static {p1, p2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;Lcom/google/mlkit/genai/common/StreamingCallback;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;

    move-result-object p0

    return-object p0

    .line 4
    :cond_0
    new-instance p0, Lcom/google/mlkit/genai/common/GenAiException;

    const/4 p1, 0x0

    const/16 p2, -0x67

    const-string v0, "Prefix parameters not found for request"

    invoke-direct {p0, v0, p1, p2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw p0
.end method

.method public final bridge synthetic zzf(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;

    .line 3
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;)Lcom/google/mlkit/genai/prompt/GenerateContentResponse;

    move-result-object p0

    return-object p0
.end method
