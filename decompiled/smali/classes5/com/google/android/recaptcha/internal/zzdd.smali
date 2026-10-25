.class final Lcom/google/android/recaptcha/internal/zzdd;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:I

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzdo;

.field final synthetic zze:Ljava/lang/String;

.field final synthetic zzf:Ljava/lang/String;

.field final synthetic zzg:Lcom/google/android/recaptcha/internal/zzaka;

.field final synthetic zzh:J

.field private synthetic zzi:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzdo;Ljava/lang/String;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;JLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzdd;->zzd:Lcom/google/android/recaptcha/internal/zzdo;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzdd;->zze:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzdd;->zzf:Ljava/lang/String;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzdd;->zzg:Lcom/google/android/recaptcha/internal/zzaka;

    iput-wide p5, p0, Lcom/google/android/recaptcha/internal/zzdd;->zzh:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p7}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 8

    new-instance v0, Lcom/google/android/recaptcha/internal/zzdd;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdd;->zzd:Lcom/google/android/recaptcha/internal/zzdo;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzdd;->zze:Ljava/lang/String;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzdd;->zzf:Ljava/lang/String;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzdd;->zzg:Lcom/google/android/recaptcha/internal/zzaka;

    iget-wide v5, p0, Lcom/google/android/recaptcha/internal/zzdd;->zzh:J

    move-object v7, p2

    invoke-direct/range {v0 .. v7}, Lcom/google/android/recaptcha/internal/zzdd;-><init>(Lcom/google/android/recaptcha/internal/zzdo;Ljava/lang/String;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;JLkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzdd;->zzi:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzdd;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzdd;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzdd;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 19

    move-object/from16 v6, p0

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v7

    iget v0, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzc:I

    const/4 v8, 0x0

    const/4 v9, 0x2

    const/4 v1, 0x1

    if-eqz v0, :cond_2

    if-eq v0, v1, :cond_1

    if-eq v0, v9, :cond_0

    iget-object v0, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzi:Ljava/lang/Object;

    check-cast v0, Lcom/google/android/recaptcha/internal/zzaku;

    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object/from16 v1, p1

    goto/16 :goto_2

    .line 12
    :cond_0
    iget-object v0, v6, Lcom/google/android/recaptcha/internal/zzdd;->zza:Ljava/lang/Object;

    .line 1
    check-cast v0, Lkotlinx/coroutines/Deferred;

    iget-object v1, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzi:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v10, v1

    move-object v1, v0

    move-object/from16 v0, p1

    goto/16 :goto_1

    :cond_1
    iget-object v0, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzb:Ljava/lang/Object;

    check-cast v0, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v1, v6, Lcom/google/android/recaptcha/internal/zzdd;->zza:Ljava/lang/Object;

    check-cast v1, Lkotlinx/coroutines/Deferred;

    iget-object v2, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzi:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v11, v0

    move-object/from16 v0, p1

    goto :goto_0

    :cond_2
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object v0, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzi:Ljava/lang/Object;

    move-object v11, v0

    check-cast v11, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v12, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzd:Lcom/google/android/recaptcha/internal/zzdo;

    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzdo;->zzk(Lcom/google/android/recaptcha/internal/zzdo;)Lcom/google/android/recaptcha/internal/zzhu;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzhu;->zzc()Lkotlinx/coroutines/CoroutineScope;

    move-result-object v0

    iget-object v13, v6, Lcom/google/android/recaptcha/internal/zzdd;->zze:Ljava/lang/String;

    iget-object v14, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzg:Lcom/google/android/recaptcha/internal/zzaka;

    .line 2
    new-instance v16, Lcom/google/android/recaptcha/internal/zzdc;

    const/4 v15, 0x0

    move-object/from16 v10, v16

    invoke-direct/range {v10 .. v15}, Lcom/google/android/recaptcha/internal/zzdc;-><init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzdo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)V

    move-object v2, v13

    move-object v3, v14

    const/16 v17, 0x3

    const/16 v18, 0x0

    const/4 v14, 0x0

    move-object v13, v0

    .line 3
    invoke-static/range {v13 .. v18}, Lkotlinx/coroutines/BuildersKt;->async$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Deferred;

    move-result-object v10

    move-object v13, v2

    iget-object v2, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzf:Ljava/lang/String;

    iget-wide v4, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzh:J

    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzdo;->zzd(Lcom/google/android/recaptcha/internal/zzdo;)Lcom/google/android/recaptcha/internal/zzcx;

    move-result-object v0

    .line 4
    iput-object v11, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzi:Ljava/lang/Object;

    iput-object v10, v6, Lcom/google/android/recaptcha/internal/zzdd;->zza:Ljava/lang/Object;

    iput-object v11, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzb:Ljava/lang/Object;

    iput v1, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzc:I

    move-object v1, v13

    .line 5
    invoke-virtual/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzcx;->zzb(Ljava/lang/String;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-eq v0, v7, :cond_5

    move-object v1, v10

    move-object v2, v11

    .line 1
    :goto_0
    check-cast v0, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v2, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzi:Ljava/lang/Object;

    iput-object v1, v6, Lcom/google/android/recaptcha/internal/zzdd;->zza:Ljava/lang/Object;

    iput-object v8, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzb:Ljava/lang/Object;

    iput v9, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzc:I

    .line 6
    invoke-virtual {v11}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v3

    invoke-virtual {v0, v3, v6}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-eq v0, v7, :cond_5

    move-object v10, v2

    .line 1
    :goto_1
    check-cast v0, Lcom/google/android/recaptcha/internal/zzaku;

    .line 7
    invoke-interface {v1}, Lkotlinx/coroutines/Job;->isCompleted()Z

    move-result v2

    if-eqz v2, :cond_4

    .line 8
    iput-object v0, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzi:Ljava/lang/Object;

    iput-object v8, v6, Lcom/google/android/recaptcha/internal/zzdd;->zza:Ljava/lang/Object;

    const/4 v2, 0x3

    iput v2, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzc:I

    invoke-interface {v1, v6}, Lkotlinx/coroutines/Deferred;->await(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v1

    if-eq v1, v7, :cond_5

    :goto_2
    check-cast v1, Lkotlin/Result;

    invoke-virtual {v1}, Lkotlin/Result;->unbox-impl()Ljava/lang/Object;

    move-result-object v1

    .line 9
    invoke-static {v1}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object v2

    if-nez v2, :cond_3

    check-cast v1, Lcom/google/android/recaptcha/internal/zzaku;

    .line 10
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzakt;

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafq;->zzq(Lcom/google/android/recaptcha/internal/zzafv;)Lcom/google/android/recaptcha/internal/zzafq;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzaku;

    :cond_3
    return-object v0

    .line 8
    :cond_4
    iget-object v11, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzd:Lcom/google/android/recaptcha/internal/zzdo;

    iget-object v12, v6, Lcom/google/android/recaptcha/internal/zzdd;->zze:Ljava/lang/String;

    iget-object v2, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzg:Lcom/google/android/recaptcha/internal/zzaka;

    new-instance v14, Lcom/google/android/recaptcha/internal/zzcz;

    .line 11
    invoke-direct {v14, v11, v12, v1, v2}, Lcom/google/android/recaptcha/internal/zzcz;-><init>(Lcom/google/android/recaptcha/internal/zzdo;Ljava/lang/String;Lkotlinx/coroutines/Deferred;Lcom/google/android/recaptcha/internal/zzaka;)V

    invoke-static {v11}, Lcom/google/android/recaptcha/internal/zzdo;->zzk(Lcom/google/android/recaptcha/internal/zzdo;)Lcom/google/android/recaptcha/internal/zzhu;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzhu;->zzc()Lkotlinx/coroutines/CoroutineScope;

    move-result-object v2

    iget-object v13, v6, Lcom/google/android/recaptcha/internal/zzdd;->zzf:Ljava/lang/String;

    .line 12
    new-instance v5, Lcom/google/android/recaptcha/internal/zzdb;

    const/4 v15, 0x0

    move-object v9, v5

    invoke-direct/range {v9 .. v15}, Lcom/google/android/recaptcha/internal/zzdb;-><init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzdo;Ljava/lang/String;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzcz;Lkotlin/coroutines/Continuation;)V

    const/4 v6, 0x3

    const/4 v7, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-static/range {v2 .. v7}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-object v0

    :cond_5
    return-object v7
.end method
