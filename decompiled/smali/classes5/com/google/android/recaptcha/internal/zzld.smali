.class final Lcom/google/android/recaptcha/internal/zzld;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:I

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzmg;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzlf;

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zzamf;

.field final synthetic zzg:J


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;JLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzld;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzld;->zze:Lcom/google/android/recaptcha/internal/zzlf;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzld;->zzf:Lcom/google/android/recaptcha/internal/zzamf;

    iput-wide p4, p0, Lcom/google/android/recaptcha/internal/zzld;->zzg:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/recaptcha/internal/zzld;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzld;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzld;->zze:Lcom/google/android/recaptcha/internal/zzlf;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzld;->zzf:Lcom/google/android/recaptcha/internal/zzamf;

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzld;->zzg:J

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzld;-><init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;JLkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzld;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzld;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzld;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 13

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzld;->zzc:I

    const/4 v2, 0x3

    const/4 v3, 0x2

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz v1, :cond_3

    if-eq v1, v4, :cond_2

    if-eq v1, v3, :cond_1

    if-eq v1, v2, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzld;->zza:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/recaptcha/internal/zzakg;

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_3

    .line 8
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzld;->zzb:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzld;->zza:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzakg;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v12, v2

    move-object v2, p1

    move-object p1, v12

    goto :goto_2

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_1

    .line 6
    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzld;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_0

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :try_start_3
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzld;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v9, p0, Lcom/google/android/recaptcha/internal/zzld;->zze:Lcom/google/android/recaptcha/internal/zzlf;

    iget-object v10, p0, Lcom/google/android/recaptcha/internal/zzld;->zzf:Lcom/google/android/recaptcha/internal/zzamf;

    iget-wide v7, p0, Lcom/google/android/recaptcha/internal/zzld;->zzg:J

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzld;->zza:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzld;->zzc:I

    .line 3
    new-instance v6, Lcom/google/android/recaptcha/internal/zzkz;

    const/4 v11, 0x0

    invoke-direct/range {v6 .. v11}, Lcom/google/android/recaptcha/internal/zzkz;-><init>(JLcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmb;

    const/16 v4, 0x37

    invoke-direct {p1, v4, v6, v5}, Lcom/google/android/recaptcha/internal/zzmb;-><init>(ILkotlin/jvm/functions/Function2;Ljava/lang/Integer;)V

    if-eq p1, v0, :cond_5

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzld;->zza:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzld;->zzc:I

    .line 4
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_5

    .line 1
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzakg;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzld;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzld;->zze:Lcom/google/android/recaptcha/internal/zzlf;

    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzlf;->zzb(Lcom/google/android/recaptcha/internal/zzlf;)Lcom/google/android/recaptcha/internal/zzon;

    move-result-object v3

    .line 5
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzld;->zza:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzld;->zzb:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzld;->zzc:I

    invoke-virtual {v3, p1, p0}, Lcom/google/android/recaptcha/internal/zzon;->zzd(Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v2

    if-ne v2, v0, :cond_4

    goto :goto_4

    .line 1
    :cond_4
    :goto_2
    check-cast v2, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzld;->zza:Ljava/lang/Object;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzld;->zzb:Ljava/lang/Object;

    const/4 v3, 0x4

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzld;->zzc:I

    .line 6
    invoke-virtual {v2, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-eq p0, v0, :cond_5

    move-object p0, p1

    .line 7
    :goto_3
    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_3
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_3 .. :try_end_3} :catch_0

    goto :goto_5

    :cond_5
    :goto_4
    return-object v0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 8
    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    :goto_5
    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0
.end method
