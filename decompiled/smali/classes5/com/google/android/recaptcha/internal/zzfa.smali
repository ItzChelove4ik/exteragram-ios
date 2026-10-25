.class final Lcom/google/android/recaptcha/internal/zzfa;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzaka;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzmg;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzfd;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzaka;Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzfd;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzfa;->zzc:Lcom/google/android/recaptcha/internal/zzaka;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzfa;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzfa;->zze:Lcom/google/android/recaptcha/internal/zzfd;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance p1, Lcom/google/android/recaptcha/internal/zzfa;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzfa;->zzc:Lcom/google/android/recaptcha/internal/zzaka;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzfa;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzfa;->zze:Lcom/google/android/recaptcha/internal/zzfd;

    invoke-direct {p1, v0, v1, p0, p2}, Lcom/google/android/recaptcha/internal/zzfa;-><init>(Lcom/google/android/recaptcha/internal/zzaka;Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzfd;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzfa;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzfa;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzfa;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 10

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzfa;->zzb:I

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_2

    .line 11
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzfa;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_1

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzfa;->zzc:Lcom/google/android/recaptcha/internal/zzaka;

    if-eqz p1, :cond_2

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaka;->zzb()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    if-eqz p1, :cond_2

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzrv;->zzi()Lcom/google/android/recaptcha/internal/zzrv;

    move-result-object v1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object p1

    array-length v4, p1

    const/4 v5, 0x0

    .line 3
    invoke-virtual {v1, p1, v5, v4}, Lcom/google/android/recaptcha/internal/zzrv;->zzg([BII)Ljava/lang/String;

    move-result-object p1

    .line 4
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzri;->zza()Lcom/google/android/recaptcha/internal/zzrf;

    move-result-object v1

    sget-object v4, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-interface {v1, p1, v4}, Lcom/google/android/recaptcha/internal/zzrf;->zza(Ljava/lang/CharSequence;Ljava/nio/charset/Charset;)Lcom/google/android/recaptcha/internal/zzre;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzre;->zzb()[B

    move-result-object p1

    .line 5
    array-length v1, p1

    invoke-static {p1, v5, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzrv;->zzj()Lcom/google/android/recaptcha/internal/zzrv;

    move-result-object v1

    .line 6
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object p1

    array-length v4, p1

    .line 7
    invoke-virtual {v1, p1, v5, v4}, Lcom/google/android/recaptcha/internal/zzrv;->zzg([BII)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_2
    move-object p1, v3

    :goto_0
    if-eqz p1, :cond_5

    .line 8
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-eqz v1, :cond_5

    :try_start_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzfa;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzfa;->zze:Lcom/google/android/recaptcha/internal/zzfd;

    invoke-static {v4}, Lcom/google/android/recaptcha/internal/zzfd;->zzi(Lcom/google/android/recaptcha/internal/zzfd;)Lcom/google/android/recaptcha/internal/zzfp;

    move-result-object v4

    .line 9
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzfa;->zza:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzfa;->zzb:I

    .line 10
    new-instance v2, Lcom/google/android/recaptcha/internal/zzfe;

    invoke-direct {v2, v4, p1, v3}, Lcom/google/android/recaptcha/internal/zzfe;-><init>(Lcom/google/android/recaptcha/internal/zzfp;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v2}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_4

    .line 1
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzfa;->zza:Ljava/lang/Object;

    const/4 v2, 0x2

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzfa;->zzb:I

    .line 11
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_3

    goto :goto_3

    .line 1
    :cond_3
    :goto_2
    check-cast p1, Ljava/lang/String;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzfa;->zze:Lcom/google/android/recaptcha/internal/zzfd;

    .line 13
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzamb;->zzb()Lcom/google/android/recaptcha/internal/zzaly;

    move-result-object v0

    .line 14
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzama;->zza()Lcom/google/android/recaptcha/internal/zzalz;

    move-result-object v1

    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzalz;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalz;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzama;

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzaly;->zza(Lcom/google/android/recaptcha/internal/zzama;)Lcom/google/android/recaptcha/internal/zzaly;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzamb;

    .line 12
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzew;->zza(Lcom/google/android/recaptcha/internal/zzev;Lcom/google/android/recaptcha/internal/zzamb;)Lcom/google/android/recaptcha/internal/zzex;

    move-result-object p0
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    return-object p0

    :cond_4
    :goto_3
    return-object v0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 16
    new-instance p1, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhe;->zzt:Lcom/google/android/recaptcha/internal/zzhe;

    .line 17
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v2

    .line 18
    invoke-direct {p1, v0, v1, v2, p0}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;)V

    .line 19
    throw p1

    .line 20
    :cond_5
    new-instance v3, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v4, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v5, Lcom/google/android/recaptcha/internal/zzhe;->zzav:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v8, 0xc

    const/4 v9, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    invoke-direct/range {v3 .. v9}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v3
.end method
