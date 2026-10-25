.class final Lcom/google/android/recaptcha/internal/zzkf;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzkh;

.field final synthetic zzc:Ljava/lang/String;

.field final synthetic zzd:Lcom/google/android/recaptcha/RecaptchaAction;

.field final synthetic zze:J

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzkh;Ljava/lang/String;Lcom/google/android/recaptcha/RecaptchaAction;JLjava/lang/String;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzkf;->zzb:Lcom/google/android/recaptcha/internal/zzkh;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzkf;->zzc:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzkf;->zzd:Lcom/google/android/recaptcha/RecaptchaAction;

    iput-wide p4, p0, Lcom/google/android/recaptcha/internal/zzkf;->zze:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p7}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 8

    new-instance v0, Lcom/google/android/recaptcha/internal/zzkf;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkf;->zzb:Lcom/google/android/recaptcha/internal/zzkh;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzkf;->zzc:Ljava/lang/String;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzkf;->zzd:Lcom/google/android/recaptcha/RecaptchaAction;

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzkf;->zze:J

    const/4 v6, 0x0

    move-object v7, p2

    invoke-direct/range {v0 .. v7}, Lcom/google/android/recaptcha/internal/zzkf;-><init>(Lcom/google/android/recaptcha/internal/zzkh;Ljava/lang/String;Lcom/google/android/recaptcha/RecaptchaAction;JLjava/lang/String;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzkf;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzkf;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzkf;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzkf;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 13

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzkf;->zza:I

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v3, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    .line 4
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkf;->zzf:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    move-object v12, p0

    goto :goto_1

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzkf;->zzf:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzkf;->zzb:Lcom/google/android/recaptcha/internal/zzkh;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzkh;->zza(Lcom/google/android/recaptcha/internal/zzkh;)Lcom/google/android/recaptcha/internal/zzkm;

    move-result-object v4

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzb()Lcom/google/android/recaptcha/internal/zzkj;

    move-result-object v5

    invoke-static {v4, v5}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_5

    .line 6
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzkh;->zzg(Lcom/google/android/recaptcha/internal/zzkh;)Lcom/google/android/recaptcha/internal/zziz;

    move-result-object v5

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzkf;->zzc:Ljava/lang/String;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzkf;->zzd:Lcom/google/android/recaptcha/RecaptchaAction;

    .line 3
    invoke-virtual {v4}, Lcom/google/android/recaptcha/RecaptchaAction;->getAction()Ljava/lang/String;

    move-result-object v7

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzkh;->zzf(Lcom/google/android/recaptcha/internal/zzkh;)Lcom/google/android/recaptcha/internal/zzakg;

    move-result-object p1

    if-nez p1, :cond_2

    move-object v8, v2

    goto :goto_0

    :cond_2
    move-object v8, p1

    :goto_0
    iget-wide v9, p0, Lcom/google/android/recaptcha/internal/zzkf;->zze:J

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzkf;->zzf:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzkf;->zza:I

    const/4 v11, 0x0

    move-object v12, p0

    invoke-virtual/range {v5 .. v12}, Lcom/google/android/recaptcha/internal/zziz;->zzb(Ljava/lang/String;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzakg;JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_4

    .line 1
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v2, v12, Lcom/google/android/recaptcha/internal/zzkf;->zzf:Ljava/lang/Object;

    const/4 p0, 0x2

    iput p0, v12, Lcom/google/android/recaptcha/internal/zzkf;->zza:I

    .line 4
    invoke-virtual {p1, v1, v12}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_3

    goto :goto_3

    .line 5
    :cond_3
    :goto_2
    check-cast p1, Ljava/lang/String;

    return-object p1

    :cond_4
    :goto_3
    return-object v0

    .line 6
    :cond_5
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzC:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_1
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 7
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzN:Lcom/google/android/recaptcha/internal/zzhe;

    .line 8
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 7
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0

    :catch_1
    move-exception v0

    move-object p0, v0

    .line 9
    throw p0
.end method
