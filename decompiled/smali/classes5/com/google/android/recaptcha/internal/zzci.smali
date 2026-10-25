.class final Lcom/google/android/recaptcha/internal/zzci;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzcl;

.field final synthetic zzc:J

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzakg;

.field private synthetic zze:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzcl;JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzci;->zzb:Lcom/google/android/recaptcha/internal/zzcl;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzci;->zzc:J

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzci;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    new-instance v0, Lcom/google/android/recaptcha/internal/zzci;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzci;->zzb:Lcom/google/android/recaptcha/internal/zzcl;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzci;->zzc:J

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzci;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzci;-><init>(Lcom/google/android/recaptcha/internal/zzcl;JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzci;->zze:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzci;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzci;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzci;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 11

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzci;->zza:I

    const/4 v2, 0x2

    const/4 v3, 0x1

    if-eqz v0, :cond_2

    if-eq v0, v3, :cond_1

    if-ne v0, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_2

    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzci;->zze:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/recaptcha/internal/zzhg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_4

    :cond_1
    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    move-object p1, v0

    goto :goto_1

    :cond_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzci;->zze:Ljava/lang/Object;

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzci;->zzb:Lcom/google/android/recaptcha/internal/zzcl;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzcl;->zza()Z

    move-result v4

    if-eqz v4, :cond_3

    .line 2
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :cond_3
    :try_start_1
    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzci;->zzc:J

    .line 3
    new-instance v6, Lcom/google/android/recaptcha/internal/zzch;

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzci;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    const/4 v8, 0x0

    invoke-direct {v6, p1, v0, v7, v8}, Lcom/google/android/recaptcha/internal/zzch;-><init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzcl;Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzci;->zza:I

    invoke-static {v4, v5, v6, p0}, Lkotlinx/coroutines/TimeoutKt;->withTimeout(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_5

    :goto_0
    check-cast p1, Lkotlin/Result;

    invoke-virtual {p1}, Lkotlin/Result;->unbox-impl()Ljava/lang/Object;

    move-result-object p1

    .line 4
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzci;->zzb:Lcom/google/android/recaptcha/internal/zzcl;

    .line 5
    invoke-static {p1, v3}, Lcom/google/android/recaptcha/internal/zzcl;->zze(Lcom/google/android/recaptcha/internal/zzcl;Z)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    .line 14
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 1
    :goto_1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzci;->zzb:Lcom/google/android/recaptcha/internal/zzcl;

    const/4 v3, 0x0

    .line 6
    invoke-static {v0, v3}, Lcom/google/android/recaptcha/internal/zzcl;->zze(Lcom/google/android/recaptcha/internal/zzcl;Z)V

    .line 7
    iput v2, p0, Lcom/google/android/recaptcha/internal/zzci;->zza:I

    .line 8
    new-instance v4, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v5, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v6, Lcom/google/android/recaptcha/internal/zzhe;->zzz:Lcom/google/android/recaptcha/internal/zzhe;

    .line 9
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v7

    const/16 v9, 0x8

    const/4 v10, 0x0

    const/4 v8, 0x0

    .line 8
    invoke-direct/range {v4 .. v10}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 10
    invoke-static {p1, v4}, Lcom/google/android/recaptcha/internal/zzcm;->zza(Ljava/lang/Exception;Lcom/google/android/recaptcha/internal/zzhg;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object p1

    if-ne p1, v1, :cond_4

    goto :goto_3

    .line 1
    :cond_4
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzhg;

    .line 11
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzci;->zze:Ljava/lang/Object;

    const/4 v0, 0x3

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzci;->zza:I

    .line 12
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    if-ne p0, v1, :cond_6

    :cond_5
    :goto_3
    return-object v1

    :cond_6
    move-object p0, p1

    .line 13
    :goto_4
    throw p0
.end method
