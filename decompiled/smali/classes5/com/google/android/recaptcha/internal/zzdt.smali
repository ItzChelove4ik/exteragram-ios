.class final Lcom/google/android/recaptcha/internal/zzdt;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzdv;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzlp;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzalg;


# direct methods
.method public constructor <init>(JLcom/google/android/recaptcha/internal/zzdv;Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzalg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzdt;->zzc:Lcom/google/android/recaptcha/internal/zzdv;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzdt;->zzd:Lcom/google/android/recaptcha/internal/zzlp;

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzdt;->zze:Lcom/google/android/recaptcha/internal/zzalg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzdt;->zzc:Lcom/google/android/recaptcha/internal/zzdv;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzdt;->zzd:Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzdt;->zze:Lcom/google/android/recaptcha/internal/zzalg;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzdt;

    const-wide/16 v1, 0x2710

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzdt;-><init>(JLcom/google/android/recaptcha/internal/zzdv;Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzalg;Lkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzdt;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzdt;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzdt;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 16

    move-object/from16 v11, p0

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v14

    iget v0, v11, Lcom/google/android/recaptcha/internal/zzdt;->zzb:I

    if-eqz v0, :cond_0

    iget-object v0, v11, Lcom/google/android/recaptcha/internal/zzdt;->zza:Ljava/lang/Object;

    move-object v1, v0

    check-cast v1, Lkotlin/jvm/internal/Ref$ObjectRef;

    :try_start_0
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move-object/from16 v0, p1

    goto :goto_0

    :catch_0
    move-exception v0

    goto :goto_2

    :cond_0
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    new-instance v6, Lkotlin/jvm/internal/Ref$ObjectRef;

    invoke-direct {v6}, Lkotlin/jvm/internal/Ref$ObjectRef;-><init>()V

    :try_start_1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzhp;->zza:Lcom/google/android/recaptcha/internal/zzhp;

    .line 3
    new-instance v2, Lcom/google/android/recaptcha/internal/zzds;

    iget-object v3, v11, Lcom/google/android/recaptcha/internal/zzdt;->zzd:Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v4, v11, Lcom/google/android/recaptcha/internal/zzdt;->zzc:Lcom/google/android/recaptcha/internal/zzdv;

    iget-object v5, v11, Lcom/google/android/recaptcha/internal/zzdt;->zze:Lcom/google/android/recaptcha/internal/zzalg;

    const/4 v7, 0x0

    invoke-direct/range {v2 .. v7}, Lcom/google/android/recaptcha/internal/zzds;-><init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzdv;Lcom/google/android/recaptcha/internal/zzalg;Lkotlin/jvm/internal/Ref$ObjectRef;Lkotlin/coroutines/Continuation;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_2
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    move-object v15, v6

    :try_start_2
    iput-object v15, v11, Lcom/google/android/recaptcha/internal/zzdt;->zza:Ljava/lang/Object;

    const/4 v1, 0x1

    iput v1, v11, Lcom/google/android/recaptcha/internal/zzdt;->zzb:I

    const/16 v12, 0x1e

    const/4 v13, 0x0

    move-object v10, v2

    const-wide/16 v1, 0x2710

    const/4 v3, 0x0

    const-wide/16 v4, 0x0

    const-wide/16 v6, 0x0

    const-wide/16 v8, 0x0

    invoke-static/range {v0 .. v13}, Lcom/google/android/recaptcha/internal/zzhp;->zze(Lcom/google/android/recaptcha/internal/zzhp;JIJJDLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;

    move-result-object v0
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-eq v0, v14, :cond_1

    move-object v1, v15

    .line 1
    :goto_0
    :try_start_3
    check-cast v0, Lcom/google/android/recaptcha/internal/zzali;
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_0
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    return-object v0

    :cond_1
    return-object v14

    :catch_1
    move-exception v0

    goto :goto_1

    :catch_2
    move-exception v0

    move-object v15, v6

    goto :goto_1

    :catchall_0
    move-exception v0

    .line 7
    throw v0

    :goto_1
    move-object v1, v15

    .line 4
    :goto_2
    invoke-virtual {v0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    .line 5
    iget-object v1, v1, Lkotlin/jvm/internal/Ref$ObjectRef;->element:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzhg;

    if-nez v1, :cond_2

    iget-object v1, v11, Lcom/google/android/recaptcha/internal/zzdt;->zzc:Lcom/google/android/recaptcha/internal/zzdv;

    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzdv;->zza(Lcom/google/android/recaptcha/internal/zzdv;Ljava/lang/Exception;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object v1

    .line 6
    :cond_2
    throw v1
.end method
