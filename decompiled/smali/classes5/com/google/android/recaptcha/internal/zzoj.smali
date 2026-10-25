.class final Lcom/google/android/recaptcha/internal/zzoj;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:I

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzon;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzakg;

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zzmg;

.field private synthetic zzg:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzon;Lcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzf:Lcom/google/android/recaptcha/internal/zzmg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 3

    new-instance v0, Lcom/google/android/recaptcha/internal/zzoj;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzf:Lcom/google/android/recaptcha/internal/zzmg;

    invoke-direct {v0, v1, v2, p0, p2}, Lcom/google/android/recaptcha/internal/zzoj;-><init>(Lcom/google/android/recaptcha/internal/zzon;Lcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzoj;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzoj;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzoj;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 8

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    const-string v2, "18.9.2"

    const/4 v3, 0x0

    packed-switch v1, :pswitch_data_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_a

    .line 35
    :pswitch_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_9

    :pswitch_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_8

    :pswitch_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_7

    :pswitch_3
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_6

    :pswitch_4
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_5

    :pswitch_5
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_4

    :pswitch_6
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzaks;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_3

    :pswitch_7
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzb:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzaks;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_2

    :pswitch_8
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzaki;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v7, v2

    move-object v2, v1

    move-object v1, v7

    goto/16 :goto_1

    :pswitch_9
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :pswitch_a
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    const/4 v4, 0x1

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaki;->zzd()Lcom/google/android/recaptcha/internal/zzakh;

    move-result-object v4

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v5

    check-cast v5, Lcom/google/android/recaptcha/internal/zzakf;

    .line 5
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzakg;->zzg()Lcom/google/android/recaptcha/internal/zzake;

    move-result-object v6

    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v6

    check-cast v6, Lcom/google/android/recaptcha/internal/zzakd;

    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzakd;->zzb()Lcom/google/android/recaptcha/internal/zzakd;

    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v6

    check-cast v6, Lcom/google/android/recaptcha/internal/zzake;

    .line 6
    invoke-virtual {v5, v6}, Lcom/google/android/recaptcha/internal/zzakf;->zzb(Lcom/google/android/recaptcha/internal/zzake;)Lcom/google/android/recaptcha/internal/zzakf;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzakg;->zzd()Lcom/google/android/recaptcha/internal/zzakk;

    move-result-object v6

    .line 8
    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v6

    check-cast v6, Lcom/google/android/recaptcha/internal/zzakj;

    .line 9
    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzakj;->zzb()Lcom/google/android/recaptcha/internal/zzakj;

    .line 10
    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzakj;->zzd()Lcom/google/android/recaptcha/internal/zzakj;

    .line 11
    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzakj;->zzf()Lcom/google/android/recaptcha/internal/zzakj;

    .line 12
    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzakj;->zzh()Lcom/google/android/recaptcha/internal/zzakj;

    .line 13
    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v6

    check-cast v6, Lcom/google/android/recaptcha/internal/zzakk;

    .line 14
    invoke-virtual {v5, v6}, Lcom/google/android/recaptcha/internal/zzakf;->zza(Lcom/google/android/recaptcha/internal/zzakk;)Lcom/google/android/recaptcha/internal/zzakf;

    .line 15
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzakf;->zzd()Lcom/google/android/recaptcha/internal/zzakf;

    .line 16
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v5

    check-cast v5, Lcom/google/android/recaptcha/internal/zzakg;

    .line 17
    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzakh;->zza(Lcom/google/android/recaptcha/internal/zzakg;)Lcom/google/android/recaptcha/internal/zzakh;

    .line 18
    invoke-virtual {v4, v2}, Lcom/google/android/recaptcha/internal/zzakh;->zzb(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakh;

    .line 19
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzakg;->zzb()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzhs;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v4, p1}, Lcom/google/android/recaptcha/internal/zzakh;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakh;

    .line 20
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    if-eq p1, v0, :cond_3

    :goto_0
    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzoj;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    .line 1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaki;

    .line 21
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    const/4 v5, 0x2

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    .line 22
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaks;->zzd()Lcom/google/android/recaptcha/internal/zzakr;

    move-result-object v5

    .line 23
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzakg;->zzh()Lcom/google/android/recaptcha/internal/zzako;

    move-result-object v6

    invoke-virtual {v5, v6}, Lcom/google/android/recaptcha/internal/zzakr;->zza(Lcom/google/android/recaptcha/internal/zzako;)Lcom/google/android/recaptcha/internal/zzakr;

    .line 24
    invoke-virtual {v5, v2}, Lcom/google/android/recaptcha/internal/zzakr;->zzb(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakr;

    .line 25
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzakg;->zzb()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzhs;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v5, v2}, Lcom/google/android/recaptcha/internal/zzakr;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakr;

    .line 26
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v2

    if-eq v2, v0, :cond_3

    move-object v7, v2

    move-object v2, p1

    move-object p1, v7

    :goto_1
    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzoj;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    .line 1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaks;

    .line 27
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzakg;->zzi()Z

    move-result v4

    if-eqz v4, :cond_1

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzf:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzon;->zza()Lcom/google/android/recaptcha/internal/zzni;

    move-result-object v5

    .line 28
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzb:Ljava/lang/Object;

    const/4 v6, 0x3

    iput v6, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    .line 29
    invoke-static {v5, v2, p0}, Lcom/google/android/recaptcha/internal/zzoa;->zze(Lcom/google/android/recaptcha/internal/zzoa;Lcom/google/android/recaptcha/internal/zzagx;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v2

    if-eq v2, v0, :cond_3

    move-object v7, v2

    move-object v2, p1

    move-object p1, v7

    move-object v7, v4

    move-object v4, v1

    move-object v1, v7

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzb:Ljava/lang/Object;

    const/4 v5, 0x4

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    .line 30
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_3

    move-object v1, v2

    move-object v2, v4

    :goto_3
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzf:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzon;->zzc()Lcom/google/android/recaptcha/internal/zznm;

    move-result-object v4

    .line 31
    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    const/4 v5, 0x5

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    .line 32
    invoke-static {v4, v1, p0}, Lcom/google/android/recaptcha/internal/zzoa;->zze(Lcom/google/android/recaptcha/internal/zzoa;Lcom/google/android/recaptcha/internal/zzagx;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v1

    if-eq v1, v0, :cond_3

    move-object v7, v1

    move-object v1, p1

    move-object p1, v7

    .line 1
    :goto_4
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    const/4 v4, 0x6

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    .line 33
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_3

    :cond_0
    move-object v1, v2

    goto :goto_6

    .line 41
    :cond_1
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    .line 34
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    const/4 v2, 0x7

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzon;->zzh(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_3

    move-object v2, v1

    .line 1
    :goto_5
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    const/16 v4, 0x8

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    .line 35
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_0

    goto :goto_b

    .line 33
    :goto_6
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzon;->zzb()Lcom/google/android/recaptcha/internal/zzng;

    move-result-object p1

    .line 36
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    const/16 v2, 0x9

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    .line 37
    invoke-static {p1, p0}, Lcom/google/android/recaptcha/internal/zzoa;->zzi(Lcom/google/android/recaptcha/internal/zzoa;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_3

    move-object v2, v1

    .line 1
    :goto_7
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoj;->zza:Ljava/lang/Object;

    const/16 v4, 0xa

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    .line 38
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_3

    move-object v1, v2

    :goto_8
    if-nez p1, :cond_2

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    .line 39
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    const/16 v4, 0xb

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    .line 40
    new-instance v4, Lcom/google/android/recaptcha/internal/zzom;

    invoke-direct {v4, v2, p1, v3}, Lcom/google/android/recaptcha/internal/zzom;-><init>(Lcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zzon;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v4}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_3

    .line 1
    :goto_9
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzg:Ljava/lang/Object;

    const/16 v2, 0xc

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzoj;->zzc:I

    .line 41
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_2

    goto :goto_b

    .line 42
    :cond_2
    :goto_a
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :cond_3
    :goto_b
    return-object v0

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_3
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
