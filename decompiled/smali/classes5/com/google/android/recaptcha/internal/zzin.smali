.class final Lcom/google/android/recaptcha/internal/zzin;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzalc;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzio;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzaka;

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zzdp;

.field final synthetic zzg:Ljava/lang/String;

.field final synthetic zzh:Lcom/google/android/recaptcha/internal/zzakg;

.field private synthetic zzi:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzalc;Lcom/google/android/recaptcha/internal/zzio;Lcom/google/android/recaptcha/internal/zzaka;Lcom/google/android/recaptcha/internal/zzdp;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzin;->zzc:Lcom/google/android/recaptcha/internal/zzalc;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzin;->zzd:Lcom/google/android/recaptcha/internal/zzio;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzin;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzin;->zzf:Lcom/google/android/recaptcha/internal/zzdp;

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzin;->zzg:Ljava/lang/String;

    iput-object p6, p0, Lcom/google/android/recaptcha/internal/zzin;->zzh:Lcom/google/android/recaptcha/internal/zzakg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p7}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 8

    new-instance v0, Lcom/google/android/recaptcha/internal/zzin;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzin;->zzc:Lcom/google/android/recaptcha/internal/zzalc;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzin;->zzd:Lcom/google/android/recaptcha/internal/zzio;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzin;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzin;->zzf:Lcom/google/android/recaptcha/internal/zzdp;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzin;->zzg:Ljava/lang/String;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzin;->zzh:Lcom/google/android/recaptcha/internal/zzakg;

    move-object v7, p2

    invoke-direct/range {v0 .. v7}, Lcom/google/android/recaptcha/internal/zzin;-><init>(Lcom/google/android/recaptcha/internal/zzalc;Lcom/google/android/recaptcha/internal/zzio;Lcom/google/android/recaptcha/internal/zzaka;Lcom/google/android/recaptcha/internal/zzdp;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzin;->zzi:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzin;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzin;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzin;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 11

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzin;->zzb:I

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

    goto/16 :goto_7

    .line 12
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzin;->zzi:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto/16 :goto_6

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzin;->zzi:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    :try_start_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_1
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    goto/16 :goto_3

    .line 18
    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzin;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzin;->zzi:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzlp;

    :try_start_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_3
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_3 .. :try_end_3} :catch_1
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_0

    goto :goto_2

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzin;->zzi:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    :try_start_4
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzin;->zzc:Lcom/google/android/recaptcha/internal/zzalc;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzalc;->zzc()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v6

    .line 3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzalc;->zzd()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v7

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzalc;->zzb()Ljava/util/List;

    move-result-object p1

    .line 5
    check-cast p1, Ljava/lang/Iterable;

    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_4
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v8

    if-eqz v8, :cond_5

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v8

    move-object v9, v8

    check-cast v9, Lcom/google/android/recaptcha/internal/zzale;

    invoke-virtual {v9}, Lcom/google/android/recaptcha/internal/zzale;->zza()Ljava/lang/String;

    move-result-object v9

    const-string v10, "_GRECAPTCHA_KC"

    invoke-static {v9, v10}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_4

    goto :goto_0

    :cond_5
    move-object v8, v5

    :goto_0
    check-cast v8, Lcom/google/android/recaptcha/internal/zzale;

    if-eqz v8, :cond_6

    invoke-virtual {v8}, Lcom/google/android/recaptcha/internal/zzale;->zzb()Ljava/lang/String;

    move-result-object p1

    goto :goto_1

    :cond_6
    move-object p1, v5

    :goto_1
    if-eqz p1, :cond_a

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzin;->zzd:Lcom/google/android/recaptcha/internal/zzio;

    invoke-static {v8}, Lcom/google/android/recaptcha/internal/zzio;->zza(Lcom/google/android/recaptcha/internal/zzio;)Lcom/google/android/recaptcha/internal/zzon;

    move-result-object v8

    .line 6
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzin;->zzi:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzin;->zza:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzin;->zzb:I

    invoke-virtual {v8, v6, v7, p1, p0}, Lcom/google/android/recaptcha/internal/zzon;->zze(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_9

    move-object v4, v1

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzin;->zzi:Ljava/lang/Object;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzin;->zza:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzin;->zzb:I

    .line 7
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_9

    move-object v1, v4

    .line 1
    :goto_3
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaka;

    if-eqz p1, :cond_7

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaka;->zza()Ljava/lang/String;

    move-result-object v3

    goto :goto_4

    :cond_7
    move-object v3, v5

    :goto_4
    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzin;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    if-eqz v4, :cond_8

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaka;->zza()Ljava/lang/String;

    move-result-object v4

    goto :goto_5

    :cond_8
    move-object v4, v5

    .line 8
    :goto_5
    invoke-static {v3, v4}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_a

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzin;->zzf:Lcom/google/android/recaptcha/internal/zzdp;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzin;->zzg:Ljava/lang/String;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzin;->zzh:Lcom/google/android/recaptcha/internal/zzakg;

    .line 9
    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzakg;->zzb()Ljava/lang/String;

    move-result-object v6

    .line 10
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzin;->zzi:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzin;->zzb:I

    .line 11
    invoke-interface {v3, v4, v6, p1, p0}, Lcom/google/android/recaptcha/internal/zzdp;->zzc(Ljava/lang/String;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_9

    .line 1
    :goto_6
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzin;->zzi:Ljava/lang/Object;

    const/4 v2, 0x4

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzin;->zzb:I

    .line 12
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0
    :try_end_4
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_4 .. :try_end_4} :catch_1
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    if-ne p0, v0, :cond_a

    :cond_9
    return-object v0

    .line 18
    :cond_a
    :goto_7
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 13
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzI:Lcom/google/android/recaptcha/internal/zzhe;

    .line 14
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 15
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 16
    throw v0

    :catch_1
    move-exception v0

    move-object p0, v0

    .line 17
    throw p0
.end method
