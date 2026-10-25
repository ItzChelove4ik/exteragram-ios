.class final Lcom/google/android/recaptcha/internal/zzgk;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:Ljava/lang/Object;

.field zzd:I

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzgo;

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zzakg;

.field private synthetic zzg:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgk;->zze:Lcom/google/android/recaptcha/internal/zzgo;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzgk;->zzf:Lcom/google/android/recaptcha/internal/zzakg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzgk;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgk;->zze:Lcom/google/android/recaptcha/internal/zzgo;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgk;->zzf:Lcom/google/android/recaptcha/internal/zzakg;

    invoke-direct {v0, v1, p0, p2}, Lcom/google/android/recaptcha/internal/zzgk;-><init>(Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzgk;->zzg:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzgk;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzgk;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzgk;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 11

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzgk;->zzd:I

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz v1, :cond_0

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzgk;->zzc:Ljava/lang/Object;

    check-cast v0, Lcom/google/android/recaptcha/internal/zzakg;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgk;->zzb:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzgo;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzgk;->zza:Ljava/lang/Object;

    check-cast v4, Lkotlinx/coroutines/sync/Mutex;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgk;->zzg:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgk;->zzg:Ljava/lang/Object;

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgk;->zze:Lcom/google/android/recaptcha/internal/zzgo;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzgk;->zzf:Lcom/google/android/recaptcha/internal/zzakg;

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzgo;->zzl(Lcom/google/android/recaptcha/internal/zzgo;)Lkotlinx/coroutines/sync/Mutex;

    move-result-object v5

    .line 2
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgk;->zzg:Ljava/lang/Object;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzgk;->zza:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzgk;->zzb:Ljava/lang/Object;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzgk;->zzc:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzgk;->zzd:I

    invoke-interface {v5, v3, p0}, Lkotlinx/coroutines/sync/Mutex;->lock(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-eq p0, v0, :cond_2

    move-object p0, p1

    move-object v0, v4

    move-object v4, v5

    .line 3
    :goto_0
    :try_start_0
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzgo;->zzm(Lcom/google/android/recaptcha/internal/zzgo;)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 4
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 5
    invoke-interface {v4, v3}, Lkotlinx/coroutines/sync/Mutex;->unlock(Ljava/lang/Object;)V

    return-object p0

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_1

    .line 6
    :cond_1
    :try_start_1
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzgo;->zzC(Lcom/google/android/recaptcha/internal/zzgo;)Lcom/google/android/recaptcha/internal/zzhu;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhu;->zzc()Lkotlinx/coroutines/CoroutineScope;

    move-result-object v5

    new-instance v8, Lcom/google/android/recaptcha/internal/zzgj;

    invoke-direct {v8, p0, v1, v0, v3}, Lcom/google/android/recaptcha/internal/zzgj;-><init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    const/4 v9, 0x3

    const/4 v10, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    invoke-static/range {v5 .. v10}, Lkotlinx/coroutines/BuildersKt;->async$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Deferred;

    move-result-object p0

    iput-object p0, v1, Lcom/google/android/recaptcha/internal/zzgo;->zzb:Lkotlinx/coroutines/Deferred;

    .line 7
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzgo;->zzn(Lcom/google/android/recaptcha/internal/zzgo;Z)V

    .line 8
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 5
    invoke-interface {v4, v3}, Lkotlinx/coroutines/sync/Mutex;->unlock(Ljava/lang/Object;)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :goto_1
    invoke-interface {v4, v3}, Lkotlinx/coroutines/sync/Mutex;->unlock(Ljava/lang/Object;)V

    throw p0

    :cond_2
    return-object v0
.end method
