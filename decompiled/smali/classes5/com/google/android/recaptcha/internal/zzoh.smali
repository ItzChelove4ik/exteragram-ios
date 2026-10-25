.class final Lcom/google/android/recaptcha/internal/zzoh;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:I

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzon;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzmg;

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzon;Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzoh;->zze:Lcom/google/android/recaptcha/internal/zzmg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzoh;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzoh;->zze:Lcom/google/android/recaptcha/internal/zzmg;

    invoke-direct {v0, v1, p0, p2}, Lcom/google/android/recaptcha/internal/zzoh;-><init>(Lcom/google/android/recaptcha/internal/zzon;Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzoh;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzoh;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzoh;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 9

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    const-string v2, "18.9.2"

    const/4 v3, 0x0

    packed-switch v1, :pswitch_data_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object p1

    .line 40
    :pswitch_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzaks;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzaki;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_b

    :pswitch_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzb:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzaks;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzaki;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v8, v4

    move-object v4, v1

    move-object v1, v2

    :goto_0
    move-object v2, v8

    goto/16 :goto_a

    :pswitch_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_e

    :pswitch_3
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_d

    :pswitch_4
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzaki;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_8

    :pswitch_5
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzb:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzaki;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    check-cast v5, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v8, v4

    move-object v4, v1

    move-object v1, v8

    goto/16 :goto_7

    :pswitch_6
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_11

    :pswitch_7
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_10

    :pswitch_8
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_5

    :pswitch_9
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_4

    :pswitch_a
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_2

    :pswitch_b
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :pswitch_c
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzon;->zza()Lcom/google/android/recaptcha/internal/zzni;

    move-result-object p1

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    const/4 v4, 0x1

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    .line 3
    invoke-static {p1, p0}, Lcom/google/android/recaptcha/internal/zzoa;->zzi(Lcom/google/android/recaptcha/internal/zzoa;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_d

    move-object v4, v1

    .line 1
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    const/4 v5, 0x2

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    .line 4
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_d

    move-object v1, v4

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaki;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaki;->zzb()Ljava/lang/String;

    move-result-object v4

    goto :goto_3

    :cond_0
    move-object v4, v3

    .line 5
    :goto_3
    invoke-static {v4, v2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_2

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    .line 6
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    const/4 v2, 0x3

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzon;->zzh(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_d

    .line 1
    :goto_4
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    const/4 v2, 0x4

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    .line 7
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_1

    goto/16 :goto_12

    .line 8
    :cond_1
    :goto_5
    new-instance v1, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhe;->zzax:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v6, 0xc

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v1

    :cond_2
    if-eqz p1, :cond_3

    .line 9
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaki;->zza()Lcom/google/android/recaptcha/internal/zzakg;

    move-result-object v4

    if-eqz v4, :cond_3

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzakg;->zzj()J

    move-result-wide v4

    invoke-static {v4, v5}, Lkotlin/coroutines/jvm/internal/Boxing;->boxLong(J)Ljava/lang/Long;

    move-result-object v4

    goto :goto_6

    :cond_3
    move-object v4, v3

    :goto_6
    if-eqz v4, :cond_b

    .line 10
    invoke-virtual {v4}, Ljava/lang/Long;->longValue()J

    move-result-wide v4

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v6

    cmp-long v4, v4, v6

    if-gez v4, :cond_4

    goto/16 :goto_f

    .line 12
    :cond_4
    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzon;->zzc()Lcom/google/android/recaptcha/internal/zznm;

    move-result-object v4

    .line 14
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzb:Ljava/lang/Object;

    const/4 v5, 0x7

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    .line 15
    invoke-static {v4, p0}, Lcom/google/android/recaptcha/internal/zzoa;->zzi(Lcom/google/android/recaptcha/internal/zzoa;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v4

    if-eq v4, v0, :cond_d

    move-object v5, v1

    move-object v1, p1

    move-object p1, v4

    move-object v4, v5

    .line 1
    :goto_7
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzb:Ljava/lang/Object;

    const/16 v6, 0x8

    iput v6, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    .line 16
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v4

    invoke-virtual {p1, v4, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_d

    move-object v4, v5

    .line 1
    :goto_8
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaks;

    if-eqz p1, :cond_5

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaks;->zzb()Ljava/lang/String;

    move-result-object v5

    goto :goto_9

    :cond_5
    move-object v5, v3

    .line 17
    :goto_9
    invoke-static {v5, v2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_9

    .line 18
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaks;->zzc()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzaki;->zzc()Ljava/lang/String;

    move-result-object v5

    invoke-static {v2, v5}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_6

    goto/16 :goto_c

    .line 42
    :cond_6
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzon;->zzb()Lcom/google/android/recaptcha/internal/zzng;

    move-result-object v2

    .line 19
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzb:Ljava/lang/Object;

    const/16 v5, 0xb

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    .line 20
    invoke-static {v2, p0}, Lcom/google/android/recaptcha/internal/zzoa;->zzi(Lcom/google/android/recaptcha/internal/zzoa;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v2

    if-eq v2, v0, :cond_d

    move-object v8, v1

    move-object v1, p1

    move-object p1, v2

    goto/16 :goto_0

    .line 1
    :goto_a
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzb:Ljava/lang/Object;

    const/16 v5, 0xc

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    .line 21
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v4

    invoke-virtual {p1, v4, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_d

    .line 1
    :goto_b
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaka;

    if-eqz p1, :cond_8

    .line 23
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaki;->zza()Lcom/google/android/recaptcha/internal/zzakg;

    move-result-object v2

    .line 24
    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    const/16 v3, 0xd

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    .line 25
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzakf;

    .line 26
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzakg;->zzg()Lcom/google/android/recaptcha/internal/zzake;

    move-result-object v3

    .line 27
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v3

    check-cast v3, Lcom/google/android/recaptcha/internal/zzakd;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaka;->zze()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v4

    .line 28
    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zzakd;->zza(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzakd;

    .line 29
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v3

    check-cast v3, Lcom/google/android/recaptcha/internal/zzake;

    .line 30
    invoke-virtual {p0, v3}, Lcom/google/android/recaptcha/internal/zzakf;->zzb(Lcom/google/android/recaptcha/internal/zzake;)Lcom/google/android/recaptcha/internal/zzakf;

    .line 31
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzakg;->zzd()Lcom/google/android/recaptcha/internal/zzakk;

    move-result-object v2

    .line 32
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzakj;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaka;->zza()Ljava/lang/String;

    move-result-object v3

    .line 33
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzakj;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakj;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaka;->zzb()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v3

    .line 34
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzakj;->zzc(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzakj;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaka;->zzc()Ljava/lang/String;

    move-result-object v3

    .line 35
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzakj;->zze(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakj;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaka;->zzd()Ljava/lang/String;

    move-result-object p1

    .line 36
    invoke-virtual {v2, p1}, Lcom/google/android/recaptcha/internal/zzakj;->zzg(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakj;

    .line 37
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzakk;

    .line 38
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzakf;->zza(Lcom/google/android/recaptcha/internal/zzakk;)Lcom/google/android/recaptcha/internal/zzakf;

    .line 39
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzaks;->zza()Lcom/google/android/recaptcha/internal/zzako;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzakf;->zzc(Lcom/google/android/recaptcha/internal/zzako;)Lcom/google/android/recaptcha/internal/zzakf;

    .line 40
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    if-ne p0, v0, :cond_7

    goto/16 :goto_12

    :cond_7
    return-object p0

    .line 22
    :cond_8
    new-instance v1, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhe;->zzaA:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v6, 0xc

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v1

    .line 18
    :cond_9
    :goto_c
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    .line 41
    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoh;->zza:Ljava/lang/Object;

    const/16 v1, 0x9

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzon;->zzh(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_d

    move-object v1, v4

    .line 1
    :goto_d
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    const/16 v2, 0xa

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    .line 42
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_a

    goto :goto_12

    .line 43
    :cond_a
    :goto_e
    new-instance v1, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhe;->zzaz:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v6, 0xc

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v1

    .line 10
    :cond_b
    :goto_f
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzoh;->zze:Lcom/google/android/recaptcha/internal/zzmg;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzon;->zza()Lcom/google/android/recaptcha/internal/zzni;

    move-result-object p1

    .line 11
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzmd;->zza()Ljava/lang/String;

    move-result-object v2

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    const/4 v4, 0x5

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    invoke-virtual {p1, v2, p0}, Lcom/google/android/recaptcha/internal/zzni;->zzb(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_d

    .line 1
    :goto_10
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzf:Ljava/lang/Object;

    const/4 v2, 0x6

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzoh;->zzc:I

    .line 12
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_c

    goto :goto_12

    .line 13
    :cond_c
    :goto_11
    new-instance v1, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhe;->zzay:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v6, 0xc

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v1

    :cond_d
    :goto_12
    return-object v0

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
