.class final Lcom/google/android/recaptcha/internal/zzjg;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:Ljava/lang/Object;

.field zzd:J

.field zze:I

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zzjj;

.field final synthetic zzg:J

.field final synthetic zzh:Lcom/google/android/recaptcha/RecaptchaAction;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzjj;JLcom/google/android/recaptcha/RecaptchaAction;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzf:Lcom/google/android/recaptcha/internal/zzjj;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzg:J

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzh:Lcom/google/android/recaptcha/RecaptchaAction;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    new-instance v0, Lcom/google/android/recaptcha/internal/zzjg;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzf:Lcom/google/android/recaptcha/internal/zzjj;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzg:J

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzh:Lcom/google/android/recaptcha/RecaptchaAction;

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzjg;-><init>(Lcom/google/android/recaptcha/internal/zzjj;JLcom/google/android/recaptcha/RecaptchaAction;Lkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzjg;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzjg;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzjg;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 11

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzjg;->zze:I

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzjg;->zza:Ljava/lang/Object;

    check-cast p0, Lkotlinx/coroutines/sync/Mutex;

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_2

    :catchall_0
    move-exception v0

    move-object p1, v0

    goto :goto_3

    .line 4
    :cond_0
    iget-wide v1, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzd:J

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzc:Ljava/lang/Object;

    .line 1
    check-cast v4, Lcom/google/android/recaptcha/RecaptchaAction;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzb:Ljava/lang/Object;

    check-cast v5, Lcom/google/android/recaptcha/internal/zzjj;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzjg;->zza:Ljava/lang/Object;

    check-cast v6, Lkotlinx/coroutines/sync/Mutex;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object p1, v6

    move-wide v6, v1

    :goto_0
    move-object v8, v4

    goto :goto_1

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzf:Lcom/google/android/recaptcha/internal/zzjj;

    iget-wide v6, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzg:J

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzh:Lcom/google/android/recaptcha/RecaptchaAction;

    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzjj;->zzd(Lcom/google/android/recaptcha/internal/zzjj;)Lkotlinx/coroutines/sync/Mutex;

    move-result-object p1

    .line 2
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjg;->zza:Ljava/lang/Object;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzb:Ljava/lang/Object;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzc:Ljava/lang/Object;

    iput-wide v6, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzd:J

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzjg;->zze:I

    invoke-interface {p1, v3, p0}, Lkotlinx/coroutines/sync/Mutex;->lock(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v1

    if-eq v1, v0, :cond_2

    goto :goto_0

    .line 3
    :goto_1
    :try_start_1
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzjj;->zze(Lcom/google/android/recaptcha/internal/zzjj;)Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object v1

    new-instance v4, Lcom/google/android/recaptcha/internal/zzjf;

    const/4 v9, 0x0

    invoke-direct/range {v4 .. v9}, Lcom/google/android/recaptcha/internal/zzjf;-><init>(Lcom/google/android/recaptcha/internal/zzjj;JLcom/google/android/recaptcha/RecaptchaAction;Lkotlin/coroutines/Continuation;)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjg;->zza:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzb:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzjg;->zzc:Ljava/lang/Object;

    const/4 v2, 0x2

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzjg;->zze:I

    .line 4
    invoke-interface {v4, v1, p0}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    if-eq p0, v0, :cond_2

    move-object v10, p1

    move-object p1, p0

    move-object p0, v10

    .line 3
    :goto_2
    :try_start_2
    check-cast p1, Lkotlin/Result;

    invoke-virtual {p1}, Lkotlin/Result;->unbox-impl()Ljava/lang/Object;

    move-result-object p1

    .line 5
    invoke-static {p1}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 6
    invoke-interface {p0, v3}, Lkotlinx/coroutines/sync/Mutex;->unlock(Ljava/lang/Object;)V

    return-object p1

    :catchall_1
    move-exception v0

    move-object p0, v0

    move-object v10, p1

    move-object p1, p0

    move-object p0, v10

    :goto_3
    invoke-interface {p0, v3}, Lkotlinx/coroutines/sync/Mutex;->unlock(Ljava/lang/Object;)V

    throw p1

    :cond_2
    return-object v0
.end method
