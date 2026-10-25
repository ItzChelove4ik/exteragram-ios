.class final Lcom/google/android/recaptcha/internal/zzla;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzlf;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzamf;

.field final synthetic zze:J

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;JLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzla;->zzc:Lcom/google/android/recaptcha/internal/zzlf;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzla;->zzd:Lcom/google/android/recaptcha/internal/zzamf;

    iput-wide p3, p0, Lcom/google/android/recaptcha/internal/zzla;->zze:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    new-instance v0, Lcom/google/android/recaptcha/internal/zzla;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzla;->zzc:Lcom/google/android/recaptcha/internal/zzlf;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzla;->zzd:Lcom/google/android/recaptcha/internal/zzamf;

    iget-wide v3, p0, Lcom/google/android/recaptcha/internal/zzla;->zze:J

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzla;-><init>(Lcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;JLkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzla;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzla;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzla;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzla;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 13

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzla;->zzb:I

    const/4 v2, 0x4

    const/4 v3, 0x3

    const/4 v4, 0x2

    const/4 v5, 0x1

    const/4 v6, 0x0

    if-eqz v1, :cond_4

    if-eq v1, v5, :cond_3

    if-eq v1, v4, :cond_2

    if-eq v1, v3, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_4

    :cond_0
    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_3

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzla;->zzf:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_2

    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzla;->zzf:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_3
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzla;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzla;->zzf:Ljava/lang/Object;

    check-cast v5, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_4
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzla;->zzf:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzla;->zzc:Lcom/google/android/recaptcha/internal/zzlf;

    iget-object v9, p0, Lcom/google/android/recaptcha/internal/zzla;->zzd:Lcom/google/android/recaptcha/internal/zzamf;

    iget-wide v10, p0, Lcom/google/android/recaptcha/internal/zzla;->zze:J

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzla;->zzf:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzla;->zza:Ljava/lang/Object;

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzla;->zzb:I

    .line 3
    new-instance v7, Lcom/google/android/recaptcha/internal/zzle;

    const/4 v12, 0x0

    invoke-direct/range {v7 .. v12}, Lcom/google/android/recaptcha/internal/zzle;-><init>(Lcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;JLkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v7}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_7

    move-object v5, v1

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzla;->zzf:Ljava/lang/Object;

    iput-object v6, p0, Lcom/google/android/recaptcha/internal/zzla;->zza:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzla;->zzb:I

    .line 4
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_7

    move-object v1, v5

    :goto_1
    :try_start_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzla;->zzc:Lcom/google/android/recaptcha/internal/zzlf;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzlf;->zzb(Lcom/google/android/recaptcha/internal/zzlf;)Lcom/google/android/recaptcha/internal/zzon;

    move-result-object p1

    .line 5
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzla;->zzf:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzla;->zzb:I

    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzon;->zzf(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_7

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v6, p0, Lcom/google/android/recaptcha/internal/zzla;->zzf:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzla;->zzb:I

    .line 6
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_7

    .line 5
    :goto_3
    check-cast p1, Lcom/google/android/recaptcha/internal/zzakg;
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_0

    return-object p1

    .line 1
    :catch_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzla;->zzc:Lcom/google/android/recaptcha/internal/zzlf;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzlf;->zzc(Lcom/google/android/recaptcha/internal/zzlf;)Lkotlinx/coroutines/Deferred;

    move-result-object p1

    if-eqz p1, :cond_6

    .line 7
    iput-object v6, p0, Lcom/google/android/recaptcha/internal/zzla;->zzf:Ljava/lang/Object;

    const/4 v1, 0x5

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzla;->zzb:I

    invoke-interface {p1, p0}, Lkotlinx/coroutines/Deferred;->await(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_5

    goto :goto_5

    :cond_5
    :goto_4
    check-cast p1, Lkotlin/Result;

    invoke-virtual {p1}, Lkotlin/Result;->unbox-impl()Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    check-cast p0, Lcom/google/android/recaptcha/internal/zzakg;

    if-eqz p0, :cond_6

    return-object p0

    .line 8
    :cond_6
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zza:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0

    :cond_7
    :goto_5
    return-object v0
.end method
