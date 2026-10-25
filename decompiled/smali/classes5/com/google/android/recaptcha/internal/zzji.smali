.class final Lcom/google/android/recaptcha/internal/zzji;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzjj;

.field final synthetic zzc:J

.field final synthetic zzd:Lcom/google/android/recaptcha/RecaptchaAction;

.field private synthetic zze:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzjj;JLcom/google/android/recaptcha/RecaptchaAction;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzji;->zzb:Lcom/google/android/recaptcha/internal/zzjj;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzji;->zzc:J

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzji;->zzd:Lcom/google/android/recaptcha/RecaptchaAction;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    new-instance v0, Lcom/google/android/recaptcha/internal/zzji;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzji;->zzb:Lcom/google/android/recaptcha/internal/zzjj;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzji;->zzc:J

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzji;->zzd:Lcom/google/android/recaptcha/RecaptchaAction;

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzji;-><init>(Lcom/google/android/recaptcha/internal/zzjj;JLcom/google/android/recaptcha/RecaptchaAction;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzji;->zze:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzji;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzji;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzji;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 18

    move-object/from16 v6, p0

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v7

    iget v0, v6, Lcom/google/android/recaptcha/internal/zzji;->zza:I

    const/4 v8, 0x0

    const/4 v1, 0x1

    if-eqz v0, :cond_1

    if-eq v0, v1, :cond_0

    :try_start_0
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-object/from16 v0, p1

    goto/16 :goto_2

    .line 9
    :cond_0
    iget-object v0, v6, Lcom/google/android/recaptcha/internal/zzji;->zze:Ljava/lang/Object;

    .line 1
    check-cast v0, Lcom/google/android/recaptcha/internal/zzlp;

    :try_start_1
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    move-object v9, v0

    move-object/from16 v0, p1

    goto :goto_1

    :cond_1
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object v0, v6, Lcom/google/android/recaptcha/internal/zzji;->zze:Ljava/lang/Object;

    move-object v9, v0

    check-cast v9, Lcom/google/android/recaptcha/internal/zzlp;

    :try_start_2
    iget-wide v3, v6, Lcom/google/android/recaptcha/internal/zzji;->zzc:J

    iget-object v2, v6, Lcom/google/android/recaptcha/internal/zzji;->zzd:Lcom/google/android/recaptcha/RecaptchaAction;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzja;->zza()Lkotlin/text/Regex;

    move-result-object v0

    invoke-virtual {v2}, Lcom/google/android/recaptcha/RecaptchaAction;->getAction()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v0, v5}, Lkotlin/text/Regex;->matches(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 3
    new-instance v10, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v11, Lcom/google/android/recaptcha/internal/zzhf;->zzg:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v12, Lcom/google/android/recaptcha/internal/zzhe;->zzh:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v15, 0xc

    const/16 v16, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    .line 4
    invoke-direct/range {v10 .. v16}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    goto :goto_0

    :cond_2
    move-object v10, v8

    :goto_0
    const-wide/16 v11, 0x1388

    cmp-long v0, v3, v11

    if-gez v0, :cond_3

    .line 5
    new-instance v11, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v12, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v13, Lcom/google/android/recaptcha/internal/zzhe;->zzm:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v16, 0xc

    const/16 v17, 0x0

    const/4 v14, 0x0

    const/4 v15, 0x0

    .line 6
    invoke-direct/range {v11 .. v17}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v10, v11

    :cond_3
    if-nez v10, :cond_7

    iget-object v0, v6, Lcom/google/android/recaptcha/internal/zzji;->zzb:Lcom/google/android/recaptcha/internal/zzjj;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzjj;->zza()Lcom/google/android/recaptcha/internal/zzkn;

    move-result-object v0

    .line 7
    invoke-virtual {v9}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v5

    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzmg;->zzb()Ljava/lang/String;

    move-result-object v5

    iput-object v9, v6, Lcom/google/android/recaptcha/internal/zzji;->zze:Ljava/lang/Object;

    iput v1, v6, Lcom/google/android/recaptcha/internal/zzji;->zza:I

    move-object v1, v5

    const/4 v5, 0x0

    .line 8
    invoke-interface/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzkn;->zzc(Ljava/lang/String;Lcom/google/android/recaptcha/RecaptchaAction;JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-eq v0, v7, :cond_6

    .line 1
    :goto_1
    check-cast v0, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v8, v6, Lcom/google/android/recaptcha/internal/zzji;->zze:Ljava/lang/Object;

    const/4 v1, 0x2

    iput v1, v6, Lcom/google/android/recaptcha/internal/zzji;->zza:I

    .line 9
    invoke-virtual {v9}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {v0, v1, v6}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-ne v0, v7, :cond_4

    goto :goto_3

    .line 1
    :cond_4
    :goto_2
    check-cast v0, Ljava/lang/String;

    .line 10
    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-eqz v1, :cond_5

    return-object v0

    .line 11
    :cond_5
    new-instance v2, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v4, Lcom/google/android/recaptcha/internal/zzhe;->zzam:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v7, 0xc

    const/4 v8, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    invoke-direct/range {v2 .. v8}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v2

    :cond_6
    :goto_3
    return-object v7

    .line 12
    :cond_7
    throw v10
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_1
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    :catch_0
    move-exception v0

    .line 13
    new-instance v1, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhe;->zzq:Lcom/google/android/recaptcha/internal/zzhe;

    .line 14
    invoke-virtual {v0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v4

    const/16 v6, 0x8

    const/4 v7, 0x0

    const/4 v5, 0x0

    .line 13
    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v1

    :catch_1
    move-exception v0

    .line 15
    throw v0
.end method
