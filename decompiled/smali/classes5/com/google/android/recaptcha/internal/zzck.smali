.class final Lcom/google/android/recaptcha/internal/zzck;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:J

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzcl;

.field final synthetic zzd:Ljava/lang/String;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzaka;

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(JLcom/google/android/recaptcha/internal/zzcl;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-wide p1, p0, Lcom/google/android/recaptcha/internal/zzck;->zzb:J

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzck;->zzc:Lcom/google/android/recaptcha/internal/zzcl;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzck;->zzd:Ljava/lang/String;

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzck;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/recaptcha/internal/zzck;

    iget-wide v1, p0, Lcom/google/android/recaptcha/internal/zzck;->zzb:J

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzck;->zzc:Lcom/google/android/recaptcha/internal/zzcl;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzck;->zzd:Ljava/lang/String;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzck;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzck;-><init>(JLcom/google/android/recaptcha/internal/zzcl;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzck;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzck;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzck;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzck;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 11

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzck;->zza:I

    const/4 v2, 0x1

    if-eqz v0, :cond_1

    if-ne v0, v2, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    move-object p1, v0

    goto :goto_1

    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzck;->zzf:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/recaptcha/internal/zzhg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_2

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzck;->zzf:Ljava/lang/Object;

    move-object v4, p1

    check-cast v4, Lcom/google/android/recaptcha/internal/zzlp;

    :try_start_1
    iget-wide v9, p0, Lcom/google/android/recaptcha/internal/zzck;->zzb:J

    .line 2
    new-instance v3, Lcom/google/android/recaptcha/internal/zzcj;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzck;->zzc:Lcom/google/android/recaptcha/internal/zzcl;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzck;->zzd:Ljava/lang/String;

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzck;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    const/4 v8, 0x0

    invoke-direct/range {v3 .. v8}, Lcom/google/android/recaptcha/internal/zzcj;-><init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzcl;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)V

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzck;->zza:I

    invoke-static {v9, v10, v3, p0}, Lkotlinx/coroutines/TimeoutKt;->withTimeout(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_2

    :goto_0
    check-cast p1, Lkotlin/Result;

    invoke-virtual {p1}, Lkotlin/Result;->unbox-impl()Ljava/lang/Object;

    move-result-object p1

    .line 3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    check-cast p1, Lcom/google/android/recaptcha/internal/zzaku;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    return-object p1

    .line 4
    :goto_1
    new-instance v2, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v4, Lcom/google/android/recaptcha/internal/zzhe;->zzt:Lcom/google/android/recaptcha/internal/zzhe;

    .line 5
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v5

    const/16 v7, 0x8

    const/4 v8, 0x0

    const/4 v6, 0x0

    .line 4
    invoke-direct/range {v2 .. v8}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 6
    invoke-static {p1, v2}, Lcom/google/android/recaptcha/internal/zzcm;->zza(Ljava/lang/Exception;Lcom/google/android/recaptcha/internal/zzhg;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object p1

    .line 7
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzck;->zzf:Ljava/lang/Object;

    const/4 v0, 0x2

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzck;->zza:I

    .line 8
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    if-ne p0, v1, :cond_3

    :cond_2
    return-object v1

    :cond_3
    move-object p0, p1

    .line 9
    :goto_2
    throw p0
.end method
