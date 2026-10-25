.class final Lcom/google/android/recaptcha/internal/zzjy;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzke;

.field final synthetic zzc:J

.field private synthetic zzd:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzke;JLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzb:Lcom/google/android/recaptcha/internal/zzke;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzc:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 4

    new-instance v0, Lcom/google/android/recaptcha/internal/zzjy;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzb:Lcom/google/android/recaptcha/internal/zzke;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzc:J

    invoke-direct {v0, v1, v2, v3, p2}, Lcom/google/android/recaptcha/internal/zzjy;-><init>(Lcom/google/android/recaptcha/internal/zzke;JLkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzjy;->zzd:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzjy;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzjy;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzjy;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 12

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzjy;->zza:I

    const/4 v2, 0x2

    const/4 v3, 0x1

    const/4 v4, 0x0

    if-eqz v1, :cond_2

    if-eq v1, v3, :cond_1

    if-eq v1, v2, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    :catch_0
    move-exception v0

    move-object p1, v0

    goto :goto_3

    :cond_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzd:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzd:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzb:Lcom/google/android/recaptcha/internal/zzke;

    iget-wide v5, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzc:J

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzd:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzjy;->zza:I

    invoke-static {p1, v5, v6, p0}, Lcom/google/android/recaptcha/internal/zzke;->zzd(Lcom/google/android/recaptcha/internal/zzke;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_b

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzd:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzjy;->zza:I

    .line 3
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_3

    goto/16 :goto_5

    :cond_3
    :goto_1
    :try_start_1
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzb:Lcom/google/android/recaptcha/internal/zzke;

    .line 4
    new-instance v1, Lcom/google/android/recaptcha/internal/zzjx;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzc:J

    invoke-direct {v1, v2, v3, p1, v4}, Lcom/google/android/recaptcha/internal/zzjx;-><init>(JLcom/google/android/recaptcha/internal/zzke;Lkotlin/coroutines/Continuation;)V

    const/4 v2, 0x3

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzjy;->zza:I

    invoke-static {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzke;->zzk(Lcom/google/android/recaptcha/internal/zzke;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_4

    goto :goto_5

    :cond_4
    :goto_2
    check-cast p1, Ljava/lang/Number;

    invoke-virtual {p1}, Ljava/lang/Number;->longValue()J

    move-result-wide v0

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzc:J
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    sub-long/2addr v2, v0

    const-wide/16 v0, 0x1f4

    cmp-long p1, v2, v0

    if-ltz p1, :cond_5

    .line 5
    invoke-static {v2, v3}, Lkotlin/coroutines/jvm/internal/Boxing;->boxLong(J)Ljava/lang/Long;

    move-result-object p0

    return-object p0

    .line 6
    :cond_5
    :try_start_2
    new-instance v5, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v6, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v7, Lcom/google/android/recaptcha/internal/zzhe;->zzC:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v10, 0xc

    const/4 v11, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    invoke-direct/range {v5 .. v11}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v5
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    .line 1
    :goto_3
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzhg;

    if-eqz v0, :cond_6

    .line 7
    move-object v4, p1

    check-cast v4, Lcom/google/android/recaptcha/internal/zzhg;

    :cond_6
    if-nez v4, :cond_7

    .line 8
    new-instance v5, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v6, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v7, Lcom/google/android/recaptcha/internal/zzhe;->zzC:Lcom/google/android/recaptcha/internal/zzhe;

    .line 9
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v8

    const/16 v10, 0x8

    const/4 v11, 0x0

    const/4 v9, 0x0

    .line 8
    invoke-direct/range {v5 .. v11}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v4, v5

    :cond_7
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzjy;->zzb:Lcom/google/android/recaptcha/internal/zzke;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzke;->zza()Lcom/google/android/recaptcha/internal/zzkm;

    move-result-object p1

    .line 10
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzd()Lcom/google/android/recaptcha/internal/zzki;

    move-result-object v0

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_9

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzke;->zza()Lcom/google/android/recaptcha/internal/zzkm;

    move-result-object p1

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzc()Lcom/google/android/recaptcha/internal/zzkk;

    move-result-object v0

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_8

    goto :goto_4

    .line 5
    :cond_8
    throw v4

    .line 10
    :cond_9
    :goto_4
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzke;->zzf(Lcom/google/android/recaptcha/internal/zzke;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object p0

    if-eqz p0, :cond_a

    move-object v4, p0

    .line 11
    :cond_a
    throw v4

    :cond_b
    :goto_5
    return-object v0
.end method
