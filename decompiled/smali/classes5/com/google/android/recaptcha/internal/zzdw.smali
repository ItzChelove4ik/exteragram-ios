.class final Lcom/google/android/recaptcha/internal/zzdw;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzdy;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzaka;

.field final synthetic zzd:Ljava/lang/String;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzdy;Lcom/google/android/recaptcha/internal/zzaka;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzdw;->zzb:Lcom/google/android/recaptcha/internal/zzdy;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzdw;->zzc:Lcom/google/android/recaptcha/internal/zzaka;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzdw;->zzd:Ljava/lang/String;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance p1, Lcom/google/android/recaptcha/internal/zzdw;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzdw;->zzb:Lcom/google/android/recaptcha/internal/zzdy;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdw;->zzc:Lcom/google/android/recaptcha/internal/zzaka;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzdw;->zzd:Ljava/lang/String;

    invoke-direct {p1, v0, v1, p0, p2}, Lcom/google/android/recaptcha/internal/zzdw;-><init>(Lcom/google/android/recaptcha/internal/zzdy;Lcom/google/android/recaptcha/internal/zzaka;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzdw;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzdw;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzdw;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 7

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzdw;->zza:I

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v3, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_3

    goto :goto_2

    :cond_0
    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_4

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :try_start_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzdw;->zzb:Lcom/google/android/recaptcha/internal/zzdy;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzdy;->zzj(Lcom/google/android/recaptcha/internal/zzdy;)Lkotlinx/coroutines/Deferred;

    move-result-object p1

    if-nez p1, :cond_2

    move-object p1, v2

    .line 2
    :cond_2
    iput v3, p0, Lcom/google/android/recaptcha/internal/zzdw;->zza:I

    invoke-interface {p1, p0}, Lkotlinx/coroutines/Deferred;->await(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_b

    :goto_0
    check-cast p1, Lcom/google/android/gms/recaptchabase/InitResult;
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_4

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzdw;->zzc:Lcom/google/android/recaptcha/internal/zzaka;

    if-eqz p1, :cond_3

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaka;->zze()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    if-eqz p1, :cond_3

    .line 5
    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {p1, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzz(Ljava/nio/charset/Charset;)Ljava/lang/String;

    move-result-object p1

    goto :goto_1

    :cond_3
    move-object p1, v2

    :goto_1
    if-eqz p1, :cond_a

    .line 6
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-eqz v1, :cond_a

    .line 7
    new-instance v1, Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;

    invoke-direct {v1}, Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;-><init>()V

    invoke-virtual {v1, p1}, Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;->setNonce(Ljava/lang/String;)Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;

    move-result-object p1

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdw;->zzd:Ljava/lang/String;

    invoke-virtual {p1, v1}, Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;->setExecuteId(Ljava/lang/String;)Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;->build()Lcom/google/android/gms/recaptchabase/ExecuteRequest;

    move-result-object p1

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdw;->zzb:Lcom/google/android/recaptcha/internal/zzdy;

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzdy;->zzh(Lcom/google/android/recaptcha/internal/zzdy;)Landroid/app/Application;

    move-result-object v1

    .line 8
    invoke-static {v1}, Lcom/google/android/gms/recaptchabase/RecaptchaBase;->getClient(Landroid/content/Context;)Lcom/google/android/gms/recaptchabase/RecaptchaBaseClient;

    move-result-object v1

    .line 9
    :try_start_3
    invoke-interface {v1, p1}, Lcom/google/android/gms/recaptchabase/RecaptchaBaseClient;->execute(Lcom/google/android/gms/recaptchabase/ExecuteRequest;)Lcom/google/android/gms/tasks/Task;

    move-result-object p1

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzic;->zza(Lcom/google/android/gms/tasks/Task;)Lkotlinx/coroutines/Deferred;

    move-result-object p1

    const/4 v1, 0x2

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzdw;->zza:I

    invoke-interface {p1, p0}, Lkotlinx/coroutines/Deferred;->await(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_4

    goto/16 :goto_6

    :cond_4
    :goto_2
    check-cast p1, Lcom/google/android/gms/recaptchabase/ExecuteResult;
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_3

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzdw;->zzd:Ljava/lang/String;

    .line 12
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaku;->zza()Lcom/google/android/recaptcha/internal/zzakt;

    move-result-object v0

    .line 13
    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzakt;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakt;

    .line 14
    invoke-virtual {p1}, Lcom/google/android/gms/recaptchabase/ExecuteResult;->getPayload()Ljava/lang/String;

    move-result-object p0

    if-eqz p0, :cond_9

    const/4 p1, 0x0

    .line 15
    invoke-static {p0, p1}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object p0

    .line 16
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzalt;->zzc([B)Lcom/google/android/recaptcha/internal/zzalt;

    move-result-object p0

    .line 17
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzals;

    .line 18
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzals;->zzd()Lcom/google/android/recaptcha/internal/zzals;

    .line 19
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzalt;->zza()Ljava/util/List;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_3
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_8

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzalr;

    .line 20
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v4

    check-cast v4, Lcom/google/android/recaptcha/internal/zzalp;

    .line 21
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzalr;->zza()Z

    move-result v5

    if-eqz v5, :cond_5

    .line 22
    :try_start_4
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzalr;->zzb()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5, p1}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object v5

    .line 23
    sget-object v6, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    .line 24
    array-length v6, v5

    invoke-static {v5, p1, v6}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v5

    .line 23
    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzalp;->zze(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzalp;
    :try_end_4
    .catch Ljava/lang/IllegalArgumentException; {:try_start_4 .. :try_end_4} :catch_0

    goto :goto_4

    .line 25
    :catch_0
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzalr;->zzh()Lcom/google/android/recaptcha/internal/zzaer;

    .line 26
    :goto_4
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzalp;->zza()Lcom/google/android/recaptcha/internal/zzalp;

    .line 27
    :cond_5
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzalr;->zzc()Z

    move-result v5

    if-eqz v5, :cond_6

    .line 28
    :try_start_5
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzalr;->zzd()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5, p1}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object v5

    .line 29
    sget-object v6, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    .line 30
    array-length v6, v5

    invoke-static {v5, p1, v6}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v5

    .line 29
    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzalp;->zzf(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzalp;
    :try_end_5
    .catch Ljava/lang/IllegalArgumentException; {:try_start_5 .. :try_end_5} :catch_1

    goto :goto_5

    .line 31
    :catch_1
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzalr;->zzh()Lcom/google/android/recaptcha/internal/zzaer;

    .line 32
    :goto_5
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzalp;->zzb()Lcom/google/android/recaptcha/internal/zzalp;

    .line 33
    :cond_6
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzalp;->zzc()Z

    move-result v2

    if-eqz v2, :cond_7

    .line 34
    :try_start_6
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzalp;->zzd()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzamb;->zza(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzamb;

    move-result-object v2

    .line 35
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzaly;

    .line 36
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzama;->zza()Lcom/google/android/recaptcha/internal/zzalz;

    move-result-object v5

    invoke-virtual {v5, v3}, Lcom/google/android/recaptcha/internal/zzalz;->zza(Z)Lcom/google/android/recaptcha/internal/zzalz;

    invoke-virtual {v2, v5}, Lcom/google/android/recaptcha/internal/zzaly;->zzb(Lcom/google/android/recaptcha/internal/zzalz;)Lcom/google/android/recaptcha/internal/zzaly;

    .line 37
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzamb;

    .line 38
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaec;->zzv()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v2

    .line 39
    invoke-virtual {v4, v2}, Lcom/google/android/recaptcha/internal/zzalp;->zze(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzalp;
    :try_end_6
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_6} :catch_2

    .line 40
    :catch_2
    :cond_7
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzalr;

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzals;->zzb(Lcom/google/android/recaptcha/internal/zzalr;)Lcom/google/android/recaptcha/internal/zzals;

    goto/16 :goto_3

    .line 41
    :cond_8
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzakc;->zza()Lcom/google/android/recaptcha/internal/zzakb;

    move-result-object p0

    .line 42
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzalt;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzakb;->zza(Lcom/google/android/recaptcha/internal/zzalt;)Lcom/google/android/recaptcha/internal/zzakb;

    .line 43
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzakc;

    .line 44
    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzakt;->zzd(Lcom/google/android/recaptcha/internal/zzakc;)Lcom/google/android/recaptcha/internal/zzakt;

    .line 45
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    .line 46
    check-cast p0, Lcom/google/android/recaptcha/internal/zzaku;

    .line 47
    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0

    .line 14
    :cond_9
    const-string p0, "Required value was null."

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v2

    .line 10
    :catch_3
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 11
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzH:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 10
    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0

    .line 48
    :cond_a
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 49
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzav:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 48
    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0

    :cond_b
    :goto_6
    return-object v0

    .line 3
    :catch_4
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 4
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzG:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 3
    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0
.end method
