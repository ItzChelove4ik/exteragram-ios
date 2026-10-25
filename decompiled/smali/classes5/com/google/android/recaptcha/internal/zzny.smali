.class final Lcom/google/android/recaptcha/internal/zzny;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:Ljava/lang/Object;

.field zzd:I

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzoa;

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zzagx;

.field private synthetic zzg:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzoa;Lcom/google/android/recaptcha/internal/zzagx;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzny;->zze:Lcom/google/android/recaptcha/internal/zzoa;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzny;->zzf:Lcom/google/android/recaptcha/internal/zzagx;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzny;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzny;->zze:Lcom/google/android/recaptcha/internal/zzoa;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzny;->zzf:Lcom/google/android/recaptcha/internal/zzagx;

    invoke-direct {v0, v1, p0, p2}, Lcom/google/android/recaptcha/internal/zzny;-><init>(Lcom/google/android/recaptcha/internal/zzoa;Lcom/google/android/recaptcha/internal/zzagx;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzny;->zzg:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzny;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzny;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzny;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 6

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzny;->zzd:I

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzny;->zzg:Ljava/lang/Object;

    check-cast p0, Lkotlinx/coroutines/sync/Mutex;

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_1

    :catchall_0
    move-exception p1

    goto :goto_2

    .line 3
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzny;->zzc:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzagx;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzny;->zzb:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzoa;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzny;->zza:Ljava/lang/Object;

    check-cast v4, Lkotlinx/coroutines/sync/Mutex;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzny;->zzg:Ljava/lang/Object;

    check-cast v5, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzny;->zzg:Ljava/lang/Object;

    move-object v5, p1

    check-cast v5, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzny;->zze:Lcom/google/android/recaptcha/internal/zzoa;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzny;->zzf:Lcom/google/android/recaptcha/internal/zzagx;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzoa;->zzd()Lkotlinx/coroutines/sync/Mutex;

    move-result-object v4

    .line 2
    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzny;->zzg:Ljava/lang/Object;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzny;->zza:Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzny;->zzb:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzny;->zzc:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzny;->zzd:I

    invoke-interface {v4, v3, p0}, Lkotlinx/coroutines/sync/Mutex;->lock(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v2

    if-eq v2, v0, :cond_2

    move-object v2, p1

    .line 3
    :goto_0
    :try_start_1
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzmd;->zza()Ljava/lang/String;

    move-result-object p1

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzny;->zzg:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzny;->zza:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzny;->zzb:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzny;->zzc:Ljava/lang/Object;

    const/4 v5, 0x2

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzny;->zzd:I

    invoke-static {v2, v1, p1, p0}, Lcom/google/android/recaptcha/internal/zzoa;->zzj(Lcom/google/android/recaptcha/internal/zzoa;Lcom/google/android/recaptcha/internal/zzagx;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    if-eq p0, v0, :cond_2

    move-object p0, v4

    :goto_1
    :try_start_2
    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 4
    invoke-interface {p0, v3}, Lkotlinx/coroutines/sync/Mutex;->unlock(Ljava/lang/Object;)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :catchall_1
    move-exception p0

    move-object p1, p0

    move-object p0, v4

    :goto_2
    invoke-interface {p0, v3}, Lkotlinx/coroutines/sync/Mutex;->unlock(Ljava/lang/Object;)V

    throw p1

    :cond_2
    return-object v0
.end method
