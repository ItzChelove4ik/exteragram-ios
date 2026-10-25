.class final Lcom/google/android/recaptcha/internal/zzkg;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzkh;

.field final synthetic zzd:J

.field private synthetic zze:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzkh;JLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzkg;->zzc:Lcom/google/android/recaptcha/internal/zzkh;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzkg;->zzd:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 4

    new-instance v0, Lcom/google/android/recaptcha/internal/zzkg;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkg;->zzc:Lcom/google/android/recaptcha/internal/zzkh;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzkg;->zzd:J

    invoke-direct {v0, v1, v2, v3, p2}, Lcom/google/android/recaptcha/internal/zzkg;-><init>(Lcom/google/android/recaptcha/internal/zzkh;JLkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzkg;->zze:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzkg;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzkg;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzkg;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 11

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzkg;->zzb:I

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzkg;->zze:Ljava/lang/Object;

    check-cast v0, Lcom/google/android/recaptcha/internal/zzkh;

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_0

    move-object v10, p0

    goto/16 :goto_1

    :catch_0
    move-exception v0

    move-object p1, v0

    move-object v10, p0

    goto/16 :goto_4

    .line 5
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkg;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzkg;->zze:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzkh;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_0

    move-object v10, p0

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzkg;->zze:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzkg;->zzc:Lcom/google/android/recaptcha/internal/zzkh;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzkh;->zza(Lcom/google/android/recaptcha/internal/zzkh;)Lcom/google/android/recaptcha/internal/zzkm;

    move-result-object v3

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzb()Lcom/google/android/recaptcha/internal/zzkj;

    move-result-object v4

    invoke-static {v3, v4}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_5

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzkh;->zza(Lcom/google/android/recaptcha/internal/zzkh;)Lcom/google/android/recaptcha/internal/zzkm;

    move-result-object v3

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzd()Lcom/google/android/recaptcha/internal/zzki;

    move-result-object v4

    invoke-static {v3, v4}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_2

    goto :goto_5

    .line 11
    :cond_2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzc()Lcom/google/android/recaptcha/internal/zzkk;

    move-result-object v3

    .line 3
    invoke-static {p1, v3}, Lcom/google/android/recaptcha/internal/zzkh;->zzd(Lcom/google/android/recaptcha/internal/zzkh;Lcom/google/android/recaptcha/internal/zzkm;)V

    :try_start_2
    iget-wide v3, p0, Lcom/google/android/recaptcha/internal/zzkg;->zzd:J

    long-to-double v3, v3

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzkh;->zzg(Lcom/google/android/recaptcha/internal/zzkh;)Lcom/google/android/recaptcha/internal/zziz;

    move-result-object v5

    const-wide v6, 0x3fd999999999999aL    # 0.4

    mul-double/2addr v6, v3

    const-wide v8, 0x3fe3333333333333L    # 0.6

    mul-double/2addr v3, v8

    .line 4
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzkg;->zze:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzkg;->zza:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzkg;->zzb:I
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_2

    double-to-long v8, v6

    double-to-long v6, v3

    move-object v10, p0

    :try_start_3
    invoke-virtual/range {v5 .. v10}, Lcom/google/android/recaptcha/internal/zziz;->zza(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-eq p0, v0, :cond_4

    move-object v2, p1

    move-object p1, p0

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v2, v10, Lcom/google/android/recaptcha/internal/zzkg;->zze:Ljava/lang/Object;

    const/4 p0, 0x0

    iput-object p0, v10, Lcom/google/android/recaptcha/internal/zzkg;->zza:Ljava/lang/Object;

    const/4 p0, 0x2

    iput p0, v10, Lcom/google/android/recaptcha/internal/zzkg;->zzb:I

    .line 5
    invoke-virtual {p1, v1, v10}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_3

    goto :goto_3

    :cond_3
    move-object v0, v2

    .line 1
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzakg;

    .line 6
    invoke-static {v0, p1}, Lcom/google/android/recaptcha/internal/zzkh;->zze(Lcom/google/android/recaptcha/internal/zzkh;Lcom/google/android/recaptcha/internal/zzakg;)V

    iget-object p0, v10, Lcom/google/android/recaptcha/internal/zzkg;->zzc:Lcom/google/android/recaptcha/internal/zzkh;

    .line 7
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzb()Lcom/google/android/recaptcha/internal/zzkj;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzkh;->zzd(Lcom/google/android/recaptcha/internal/zzkh;Lcom/google/android/recaptcha/internal/zzkm;)V
    :try_end_3
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_3 .. :try_end_3} :catch_1

    .line 10
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :catch_1
    move-exception v0

    :goto_2
    move-object p1, v0

    goto :goto_4

    :cond_4
    :goto_3
    return-object v0

    :catch_2
    move-exception v0

    move-object v10, p0

    goto :goto_2

    .line 1
    :goto_4
    iget-object p0, v10, Lcom/google/android/recaptcha/internal/zzkg;->zzc:Lcom/google/android/recaptcha/internal/zzkh;

    .line 8
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzd()Lcom/google/android/recaptcha/internal/zzki;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzkh;->zzd(Lcom/google/android/recaptcha/internal/zzkh;Lcom/google/android/recaptcha/internal/zzkm;)V

    .line 9
    throw p1

    .line 11
    :cond_5
    :goto_5
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
