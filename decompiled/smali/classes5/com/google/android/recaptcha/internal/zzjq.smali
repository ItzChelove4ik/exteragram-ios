.class final Lcom/google/android/recaptcha/internal/zzjq;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:Ljava/lang/Object;

.field zzd:Ljava/lang/Object;

.field zze:J

.field zzf:I

.field final synthetic zzg:Lcom/google/android/recaptcha/internal/zzjt;

.field final synthetic zzh:Lcom/google/android/recaptcha/internal/zziw;

.field final synthetic zzi:Ljava/lang/String;

.field final synthetic zzj:J


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzjt;Lcom/google/android/recaptcha/internal/zziw;Ljava/lang/String;JLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzg:Lcom/google/android/recaptcha/internal/zzjt;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzh:Lcom/google/android/recaptcha/internal/zziw;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzi:Ljava/lang/String;

    iput-wide p4, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzj:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/recaptcha/internal/zzjq;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzg:Lcom/google/android/recaptcha/internal/zzjt;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzh:Lcom/google/android/recaptcha/internal/zziw;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzi:Ljava/lang/String;

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzj:J

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzjq;-><init>(Lcom/google/android/recaptcha/internal/zzjt;Lcom/google/android/recaptcha/internal/zziw;Ljava/lang/String;JLkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzjq;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzjq;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzjq;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 14

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzf:I

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzjq;->zza:Ljava/lang/Object;

    check-cast p0, Lkotlinx/coroutines/sync/Mutex;

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto/16 :goto_3

    :catchall_0
    move-exception v0

    move-object p1, v0

    goto/16 :goto_4

    .line 4
    :cond_0
    iget-wide v1, p0, Lcom/google/android/recaptcha/internal/zzjq;->zze:J

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzd:Ljava/lang/Object;

    .line 1
    check-cast v4, Ljava/lang/String;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzc:Ljava/lang/Object;

    check-cast v5, Lcom/google/android/recaptcha/internal/zzjt;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzb:Ljava/lang/Object;

    check-cast v6, Lcom/google/android/recaptcha/internal/zziw;

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzjq;->zza:Ljava/lang/Object;

    check-cast v7, Lkotlinx/coroutines/sync/Mutex;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object p1, v7

    move-wide v7, v1

    :goto_0
    move-object v9, v6

    move-object v6, v4

    goto :goto_1

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzg:Lcom/google/android/recaptcha/internal/zzjt;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzh:Lcom/google/android/recaptcha/internal/zziw;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzi:Ljava/lang/String;

    iget-wide v7, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzj:J

    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzjt;->zzb(Lcom/google/android/recaptcha/internal/zzjt;)Lkotlinx/coroutines/sync/Mutex;

    move-result-object p1

    .line 2
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjq;->zza:Ljava/lang/Object;

    iput-object v6, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzb:Ljava/lang/Object;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzc:Ljava/lang/Object;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzd:Ljava/lang/Object;

    iput-wide v7, p0, Lcom/google/android/recaptcha/internal/zzjq;->zze:J

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzf:I

    invoke-interface {p1, v3, p0}, Lkotlinx/coroutines/sync/Mutex;->lock(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v1

    if-eq v1, v0, :cond_4

    goto :goto_0

    :goto_1
    :try_start_1
    sget-object v1, Lcom/google/android/recaptcha/internal/zziw;->zza:Lcom/google/android/recaptcha/internal/zziw;

    .line 3
    invoke-static {v9, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    const/4 v2, 0x2

    if-eqz v1, :cond_2

    const/4 v1, 0x3

    goto :goto_2

    .line 7
    :cond_2
    sget-object v1, Lcom/google/android/recaptcha/internal/zziw;->zzb:Lcom/google/android/recaptcha/internal/zziw;

    .line 4
    invoke-static {v9, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_3

    const/4 v1, 0x4

    goto :goto_2

    :cond_3
    move v1, v2

    .line 5
    :goto_2
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzjt;->zzc(Lcom/google/android/recaptcha/internal/zzjt;)Lcom/google/android/recaptcha/internal/zzko;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzko;->zza()Lcom/google/android/recaptcha/internal/zzlj;

    move-result-object v11

    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzjt;->zzc(Lcom/google/android/recaptcha/internal/zzjt;)Lcom/google/android/recaptcha/internal/zzko;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzko;->zzb()Lcom/google/android/recaptcha/internal/zzht;

    move-result-object v12

    new-instance v4, Lcom/google/android/recaptcha/internal/zzjp;

    const/4 v10, 0x0

    invoke-direct/range {v4 .. v10}, Lcom/google/android/recaptcha/internal/zzjp;-><init>(Lcom/google/android/recaptcha/internal/zzjt;Ljava/lang/String;JLcom/google/android/recaptcha/internal/zziw;Lkotlin/coroutines/Continuation;)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjq;->zza:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzb:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzc:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzd:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzjq;->zzf:I

    new-instance v2, Lcom/google/android/recaptcha/internal/zzmd;

    .line 6
    invoke-direct {v2, v11, v12, v6, v1}, Lcom/google/android/recaptcha/internal/zzmd;-><init>(Lcom/google/android/recaptcha/internal/zzlj;Lcom/google/android/recaptcha/internal/zzht;Ljava/lang/String;I)V

    .line 7
    invoke-interface {v4, v2, p0}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    if-eq p0, v0, :cond_4

    move-object v13, p1

    move-object p1, p0

    move-object p0, v13

    .line 1
    :goto_3
    :try_start_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzjj;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 8
    invoke-interface {p0, v3}, Lkotlinx/coroutines/sync/Mutex;->unlock(Ljava/lang/Object;)V

    return-object p1

    :catchall_1
    move-exception v0

    move-object p0, v0

    move-object v13, p1

    move-object p1, p0

    move-object p0, v13

    :goto_4
    invoke-interface {p0, v3}, Lkotlinx/coroutines/sync/Mutex;->unlock(Ljava/lang/Object;)V

    throw p1

    :cond_4
    return-object v0
.end method
