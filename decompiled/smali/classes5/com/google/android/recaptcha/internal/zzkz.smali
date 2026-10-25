.class final Lcom/google/android/recaptcha/internal/zzkz;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:J

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzlf;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzamf;

.field private synthetic zze:Ljava/lang/Object;


# direct methods
.method public constructor <init>(JLcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-wide p1, p0, Lcom/google/android/recaptcha/internal/zzkz;->zzb:J

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzkz;->zzc:Lcom/google/android/recaptcha/internal/zzlf;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzkz;->zzd:Lcom/google/android/recaptcha/internal/zzamf;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    new-instance v0, Lcom/google/android/recaptcha/internal/zzkz;

    iget-wide v1, p0, Lcom/google/android/recaptcha/internal/zzkz;->zzb:J

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzkz;->zzc:Lcom/google/android/recaptcha/internal/zzlf;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzkz;->zzd:Lcom/google/android/recaptcha/internal/zzamf;

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzkz;-><init>(JLcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzkz;->zze:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzkz;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzkz;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzkz;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 7

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzkz;->zza:I

    if-eqz v1, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_2
    .catch Lkotlinx/coroutines/TimeoutCancellationException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    move-object p1, v0

    goto :goto_1

    :catch_1
    move-exception v0

    move-object p1, v0

    goto :goto_2

    :catch_2
    move-exception v0

    move-object p1, v0

    goto :goto_3

    :cond_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzkz;->zze:Ljava/lang/Object;

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    :try_start_1
    iget-wide v1, p0, Lcom/google/android/recaptcha/internal/zzkz;->zzb:J

    .line 2
    invoke-static {v1, v2}, Lkotlin/coroutines/jvm/internal/Boxing;->boxLong(J)Ljava/lang/Long;

    .line 3
    new-instance v3, Lcom/google/android/recaptcha/internal/zzky;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzkz;->zzc:Lcom/google/android/recaptcha/internal/zzlf;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzkz;->zzd:Lcom/google/android/recaptcha/internal/zzamf;

    const/4 v6, 0x0

    invoke-direct {v3, p1, v4, v5, v6}, Lcom/google/android/recaptcha/internal/zzky;-><init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;Lkotlin/coroutines/Continuation;)V

    const/4 p1, 0x1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzkz;->zza:I

    invoke-static {v1, v2, v3, p0}, Lkotlinx/coroutines/TimeoutKt;->withTimeout(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_1

    return-object v0

    :cond_1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzakg;
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_2
    .catch Lkotlinx/coroutines/TimeoutCancellationException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    return-object p1

    .line 11
    :goto_1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzkz;->zzc:Lcom/google/android/recaptcha/internal/zzlf;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzlf;->zzf(Lcom/google/android/recaptcha/internal/zzlf;)Lcom/google/android/recaptcha/internal/zzhh;

    move-result-object p0

    .line 4
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzL:Lcom/google/android/recaptcha/internal/zzhe;

    .line 5
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 4
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 6
    invoke-virtual {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzhh;->zza(Ljava/lang/Exception;Lcom/google/android/recaptcha/internal/zzhg;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object p0

    .line 7
    throw p0

    .line 13
    :goto_2
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzkz;->zzc:Lcom/google/android/recaptcha/internal/zzlf;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzlf;->zzf(Lcom/google/android/recaptcha/internal/zzlf;)Lcom/google/android/recaptcha/internal/zzhh;

    move-result-object p0

    .line 9
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzb:Lcom/google/android/recaptcha/internal/zzhe;

    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 10
    invoke-virtual {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzhh;->zza(Ljava/lang/Exception;Lcom/google/android/recaptcha/internal/zzhg;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object p0

    .line 11
    throw p0

    .line 7
    :goto_3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhg;->zza()Lcom/google/android/recaptcha/internal/zzhf;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    .line 12
    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 13
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzkz;->zzc:Lcom/google/android/recaptcha/internal/zzlf;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzlf;->zzf(Lcom/google/android/recaptcha/internal/zzlf;)Lcom/google/android/recaptcha/internal/zzhh;

    move-result-object p0

    .line 14
    invoke-virtual {p0, p1, p1}, Lcom/google/android/recaptcha/internal/zzhh;->zza(Ljava/lang/Exception;Lcom/google/android/recaptcha/internal/zzhg;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object p0

    .line 13
    throw p0

    :cond_2
    throw p1
.end method
