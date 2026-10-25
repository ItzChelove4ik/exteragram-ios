.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

.field final synthetic zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

.field final synthetic zzd:Lcom/google/mlkit/genai/common/DownloadCallback;

.field final synthetic zze:Ljava/util/concurrent/atomic/AtomicInteger;

.field final synthetic zzf:I


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;Lcom/google/mlkit/genai/common/DownloadCallback;Ljava/util/concurrent/atomic/AtomicInteger;ILkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zzd:Lcom/google/mlkit/genai/common/DownloadCallback;

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zze:Ljava/util/concurrent/atomic/AtomicInteger;

    iput p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zzf:I

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zzd:Lcom/google/mlkit/genai/common/DownloadCallback;

    iget-object v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zze:Ljava/util/concurrent/atomic/AtomicInteger;

    iget v5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zzf:I

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;Lcom/google/mlkit/genai/common/DownloadCallback;Ljava/util/concurrent/atomic/AtomicInteger;ILkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 11

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    goto :goto_0

    :cond_0
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;

    iget-object v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zzd:Lcom/google/mlkit/genai/common/DownloadCallback;

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zze:Ljava/util/concurrent/atomic/AtomicInteger;

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zzf:I

    new-instance v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxw;

    invoke-direct {v7, p1, v1, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxw;-><init>(Ljava/util/concurrent/atomic/AtomicInteger;ILcom/google/mlkit/genai/common/DownloadCallback;)V

    const/4 p1, 0x1

    .line 2
    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxx;->zza:I

    const/16 v9, 0x8

    const/4 v10, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    move-object v8, p0

    invoke-static/range {v2 .. v10}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;Lcom/google/mlkit/genai/common/DownloadCallback;ZLkotlinx/coroutines/CompletableDeferred;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_1

    return-object v0

    .line 3
    :cond_1
    :goto_0
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
