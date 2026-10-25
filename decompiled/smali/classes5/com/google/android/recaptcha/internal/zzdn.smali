.class final Lcom/google/android/recaptcha/internal/zzdn;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:Ljava/lang/Object;

.field zzd:Ljava/lang/Object;

.field zze:Ljava/lang/Object;

.field zzf:I

.field final synthetic zzg:Lcom/google/android/recaptcha/internal/zzdo;

.field final synthetic zzh:Ljava/lang/String;

.field final synthetic zzi:Lcom/google/android/recaptcha/internal/zzcz;

.field final synthetic zzj:Ljava/lang/String;

.field private synthetic zzk:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzdo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzcz;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzdn;->zzg:Lcom/google/android/recaptcha/internal/zzdo;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzdn;->zzh:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzdn;->zzi:Lcom/google/android/recaptcha/internal/zzcz;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzdn;->zzj:Ljava/lang/String;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    new-instance v0, Lcom/google/android/recaptcha/internal/zzdn;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdn;->zzg:Lcom/google/android/recaptcha/internal/zzdo;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzdn;->zzh:Ljava/lang/String;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzdn;->zzi:Lcom/google/android/recaptcha/internal/zzcz;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzdn;->zzj:Ljava/lang/String;

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzdn;-><init>(Lcom/google/android/recaptcha/internal/zzdo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzcz;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzdn;->zzk:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzdn;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzdn;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzdn;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 22

    move-object/from16 v9, p0

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v12

    iget v0, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzf:I

    const/4 v1, 0x1

    const/4 v13, 0x0

    if-eqz v0, :cond_1

    if-eq v0, v1, :cond_0

    iget-object v0, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzk:Ljava/lang/Object;

    move-object v1, v0

    check-cast v1, Lkotlinx/coroutines/sync/Mutex;

    :try_start_0
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto/16 :goto_2

    :catchall_0
    move-exception v0

    goto/16 :goto_4

    .line 3
    :cond_0
    iget-object v0, v9, Lcom/google/android/recaptcha/internal/zzdn;->zze:Ljava/lang/Object;

    .line 1
    check-cast v0, Ljava/lang/String;

    iget-object v1, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzd:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzcz;

    iget-object v2, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzc:Ljava/lang/Object;

    check-cast v2, Ljava/lang/String;

    iget-object v3, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzb:Ljava/lang/Object;

    check-cast v3, Lcom/google/android/recaptcha/internal/zzdo;

    iget-object v4, v9, Lcom/google/android/recaptcha/internal/zzdn;->zza:Ljava/lang/Object;

    check-cast v4, Lkotlinx/coroutines/sync/Mutex;

    iget-object v5, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzk:Ljava/lang/Object;

    check-cast v5, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object/from16 v19, v0

    move-object/from16 v18, v1

    move-object v1, v4

    :goto_0
    move-object/from16 v17, v2

    move-object/from16 v16, v3

    move-object v15, v5

    goto :goto_1

    :cond_1
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object v0, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzk:Ljava/lang/Object;

    move-object v5, v0

    check-cast v5, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v3, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzg:Lcom/google/android/recaptcha/internal/zzdo;

    iget-object v2, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzh:Ljava/lang/String;

    iget-object v0, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzi:Lcom/google/android/recaptcha/internal/zzcz;

    iget-object v4, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzj:Ljava/lang/String;

    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzdo;->zzh(Lcom/google/android/recaptcha/internal/zzdo;)Lkotlinx/coroutines/sync/Mutex;

    move-result-object v6

    .line 2
    iput-object v5, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzk:Ljava/lang/Object;

    iput-object v6, v9, Lcom/google/android/recaptcha/internal/zzdn;->zza:Ljava/lang/Object;

    iput-object v3, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzb:Ljava/lang/Object;

    iput-object v2, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzc:Ljava/lang/Object;

    iput-object v0, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzd:Ljava/lang/Object;

    iput-object v4, v9, Lcom/google/android/recaptcha/internal/zzdn;->zze:Ljava/lang/Object;

    iput v1, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzf:I

    invoke-interface {v6, v13, v9}, Lkotlinx/coroutines/sync/Mutex;->lock(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v1

    if-eq v1, v12, :cond_2

    move-object/from16 v18, v0

    move-object/from16 v19, v4

    move-object v1, v6

    goto :goto_0

    :goto_1
    :try_start_1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzhp;->zza:Lcom/google/android/recaptcha/internal/zzhp;

    .line 3
    new-instance v14, Lcom/google/android/recaptcha/internal/zzdm;

    const/16 v20, 0x0

    invoke-direct/range {v14 .. v20}, Lcom/google/android/recaptcha/internal/zzdm;-><init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzdo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzcz;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    iput-object v1, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzk:Ljava/lang/Object;

    iput-object v13, v9, Lcom/google/android/recaptcha/internal/zzdn;->zza:Ljava/lang/Object;

    iput-object v13, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzb:Ljava/lang/Object;

    iput-object v13, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzc:Ljava/lang/Object;

    iput-object v13, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzd:Ljava/lang/Object;

    iput-object v13, v9, Lcom/google/android/recaptcha/internal/zzdn;->zze:Ljava/lang/Object;

    const/4 v2, 0x2

    iput v2, v9, Lcom/google/android/recaptcha/internal/zzdn;->zzf:I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_2

    const/16 v10, 0xe

    const/4 v11, 0x0

    move-object v4, v1

    const/4 v1, 0x5

    const-wide/16 v2, 0x0

    move-object v6, v4

    const-wide/16 v4, 0x0

    move-object v8, v6

    const-wide/16 v6, 0x0

    move-object/from16 v21, v14

    move-object v14, v8

    move-object/from16 v8, v21

    :try_start_2
    invoke-static/range {v0 .. v11}, Lcom/google/android/recaptcha/internal/zzhp;->zzb(Lcom/google/android/recaptcha/internal/zzhp;IJJDLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;

    move-result-object v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    if-eq v0, v12, :cond_2

    move-object v1, v14

    .line 4
    :goto_2
    :try_start_3
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 5
    invoke-interface {v1, v13}, Lkotlinx/coroutines/sync/Mutex;->unlock(Ljava/lang/Object;)V

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0

    :catchall_1
    move-exception v0

    goto :goto_3

    :catchall_2
    move-exception v0

    move-object v14, v1

    :goto_3
    move-object v1, v14

    :goto_4
    invoke-interface {v1, v13}, Lkotlinx/coroutines/sync/Mutex;->unlock(Ljava/lang/Object;)V

    throw v0

    :cond_2
    return-object v12
.end method
