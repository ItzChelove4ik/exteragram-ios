.class final Lcom/google/android/recaptcha/internal/zzcq;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzmg;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzcl;

.field final synthetic zze:J

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zzakg;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzcl;JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzcq;->zzc:Lcom/google/android/recaptcha/internal/zzmg;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzcq;->zzd:Lcom/google/android/recaptcha/internal/zzcl;

    iput-wide p3, p0, Lcom/google/android/recaptcha/internal/zzcq;->zze:J

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzcq;->zzf:Lcom/google/android/recaptcha/internal/zzakg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/recaptcha/internal/zzcq;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcq;->zzc:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzcq;->zzd:Lcom/google/android/recaptcha/internal/zzcl;

    iget-wide v3, p0, Lcom/google/android/recaptcha/internal/zzcq;->zze:J

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzcq;->zzf:Lcom/google/android/recaptcha/internal/zzakg;

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzcq;-><init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzcl;JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzcq;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzcq;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzcq;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 10

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzcq;->zzb:I

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v3, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    .line 6
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcq;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :try_start_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcq;->zzc:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzcq;->zzd:Lcom/google/android/recaptcha/internal/zzcl;

    iget-wide v6, p0, Lcom/google/android/recaptcha/internal/zzcq;->zze:J

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzcq;->zzf:Lcom/google/android/recaptcha/internal/zzakg;

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzcq;->zza:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzcq;->zzb:I

    .line 3
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzcl;->zzf()I

    move-result p1

    new-instance v4, Lcom/google/android/recaptcha/internal/zzci;

    const/4 v9, 0x0

    invoke-direct/range {v4 .. v9}, Lcom/google/android/recaptcha/internal/zzci;-><init>(Lcom/google/android/recaptcha/internal/zzcl;JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    new-instance v3, Lcom/google/android/recaptcha/internal/zzmb;

    invoke-direct {v3, p1, v4, v2}, Lcom/google/android/recaptcha/internal/zzmb;-><init>(ILkotlin/jvm/functions/Function2;Ljava/lang/Integer;)V

    if-eq v3, v0, :cond_3

    move-object p1, v3

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzcq;->zza:Ljava/lang/Object;

    const/4 v2, 0x2

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzcq;->zzb:I

    .line 4
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_2

    goto :goto_2

    .line 5
    :cond_2
    :goto_1
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_3

    :cond_3
    :goto_2
    return-object v0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 6
    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    :goto_3
    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0
.end method
