.class final Lcom/google/android/recaptcha/internal/zzju;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzke;

.field final synthetic zzd:J

.field final synthetic zze:Ljava/lang/String;

.field final synthetic zzf:Lcom/google/android/recaptcha/RecaptchaAction;

.field private synthetic zzg:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzke;JLjava/lang/String;Lcom/google/android/recaptcha/RecaptchaAction;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzju;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzju;->zzd:J

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzju;->zze:Ljava/lang/String;

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzju;->zzf:Lcom/google/android/recaptcha/RecaptchaAction;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p7}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 8

    new-instance v0, Lcom/google/android/recaptcha/internal/zzju;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzju;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzju;->zzd:J

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzju;->zze:Ljava/lang/String;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzju;->zzf:Lcom/google/android/recaptcha/RecaptchaAction;

    const/4 v6, 0x0

    move-object v7, p2

    invoke-direct/range {v0 .. v7}, Lcom/google/android/recaptcha/internal/zzju;-><init>(Lcom/google/android/recaptcha/internal/zzke;JLjava/lang/String;Lcom/google/android/recaptcha/RecaptchaAction;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzju;->zzg:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzju;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzju;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzju;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 14

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzju;->zzb:I

    const/4 v2, 0x3

    const/4 v3, 0x2

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz v1, :cond_3

    if-eq v1, v4, :cond_2

    if-eq v1, v3, :cond_1

    if-eq v1, v2, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_4

    .line 8
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzju;->zzg:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    move-object v13, p0

    goto/16 :goto_3

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzju;->zzg:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_1
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_1

    .line 9
    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzju;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzju;->zzg:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_3
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_3 .. :try_end_3} :catch_1
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_0

    goto :goto_0

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzju;->zzg:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_4
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzju;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    iget-wide v6, p0, Lcom/google/android/recaptcha/internal/zzju;->zzd:J

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzju;->zzg:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzju;->zza:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzju;->zzb:I

    .line 3
    new-instance v4, Lcom/google/android/recaptcha/internal/zzjy;

    invoke-direct {v4, p1, v6, v7, v5}, Lcom/google/android/recaptcha/internal/zzjy;-><init>(Lcom/google/android/recaptcha/internal/zzke;JLkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v4}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_6

    move-object v4, v1

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzju;->zzg:Ljava/lang/Object;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzju;->zza:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzju;->zzb:I

    .line 4
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_6

    move-object v1, v4

    .line 2
    :goto_1
    check-cast p1, Ljava/lang/Number;

    invoke-virtual {p1}, Ljava/lang/Number;->longValue()J

    move-result-wide v10

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzju;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzke;->zzn(Lcom/google/android/recaptcha/internal/zzke;)Lcom/google/android/recaptcha/internal/zziz;

    move-result-object v6

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzju;->zze:Ljava/lang/String;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzju;->zzf:Lcom/google/android/recaptcha/RecaptchaAction;

    .line 5
    invoke-virtual {v3}, Lcom/google/android/recaptcha/RecaptchaAction;->getAction()Ljava/lang/String;

    move-result-object v8

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzke;->zzm(Lcom/google/android/recaptcha/internal/zzke;)Lcom/google/android/recaptcha/internal/zzakg;

    move-result-object p1

    if-nez p1, :cond_4

    move-object v9, v5

    goto :goto_2

    :cond_4
    move-object v9, p1

    .line 6
    :goto_2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzju;->zzg:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzju;->zzb:I

    const/4 v12, 0x0

    move-object v13, p0

    .line 7
    invoke-virtual/range {v6 .. v13}, Lcom/google/android/recaptcha/internal/zziz;->zzb(Ljava/lang/String;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzakg;JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_6

    .line 1
    :goto_3
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v5, v13, Lcom/google/android/recaptcha/internal/zzju;->zzg:Ljava/lang/Object;

    const/4 p0, 0x4

    iput p0, v13, Lcom/google/android/recaptcha/internal/zzju;->zzb:I

    .line 8
    invoke-virtual {p1, v1, v13}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_5

    goto :goto_5

    .line 9
    :cond_5
    :goto_4
    check-cast p1, Ljava/lang/String;
    :try_end_4
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_4 .. :try_end_4} :catch_1
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    return-object p1

    :cond_6
    :goto_5
    return-object v0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 10
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzM:Lcom/google/android/recaptcha/internal/zzhe;

    .line 11
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 10
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0

    :catch_1
    move-exception v0

    move-object p0, v0

    .line 12
    throw p0
.end method
