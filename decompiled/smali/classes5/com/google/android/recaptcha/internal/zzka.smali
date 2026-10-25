.class final Lcom/google/android/recaptcha/internal/zzka;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzke;

.field final synthetic zzd:J

.field final synthetic zze:Lkotlinx/coroutines/CompletableDeferred;

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzke;JLkotlinx/coroutines/CompletableDeferred;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzka;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzka;->zzd:J

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzka;->zze:Lkotlinx/coroutines/CompletableDeferred;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    new-instance v0, Lcom/google/android/recaptcha/internal/zzka;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzka;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzka;->zzd:J

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzka;->zze:Lkotlinx/coroutines/CompletableDeferred;

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzka;-><init>(Lcom/google/android/recaptcha/internal/zzke;JLkotlinx/coroutines/CompletableDeferred;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzka;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzka;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzka;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzka;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 9

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzka;->zzb:I

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzka;->zzf:Ljava/lang/Object;

    check-cast v0, Lcom/google/android/recaptcha/internal/zzke;

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_0

    move-object v8, p0

    goto :goto_2

    :catch_0
    move-exception v0

    move-object p1, v0

    move-object v8, p0

    goto/16 :goto_4

    .line 8
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzka;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzka;->zzf:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzke;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_0

    move-object v8, p0

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzka;->zzf:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    :try_start_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzka;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzke;->zzn(Lcom/google/android/recaptcha/internal/zzke;)Lcom/google/android/recaptcha/internal/zziz;

    move-result-object v3

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzka;->zzd:J

    .line 2
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzka;->zzf:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzka;->zza:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzka;->zzb:I
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_2

    move-wide v6, v4

    move-object v8, p0

    :try_start_3
    invoke-virtual/range {v3 .. v8}, Lcom/google/android/recaptcha/internal/zziz;->zza(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_2

    goto :goto_1

    :cond_2
    move-object v2, p1

    move-object p1, p0

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v2, v8, Lcom/google/android/recaptcha/internal/zzka;->zzf:Ljava/lang/Object;

    const/4 p0, 0x0

    iput-object p0, v8, Lcom/google/android/recaptcha/internal/zzka;->zza:Ljava/lang/Object;

    const/4 p0, 0x2

    iput p0, v8, Lcom/google/android/recaptcha/internal/zzka;->zzb:I

    .line 3
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object p0

    invoke-virtual {p1, p0, v8}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_3

    :goto_1
    return-object v0

    :cond_3
    move-object v0, v2

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzakg;

    .line 4
    invoke-static {v0, p1}, Lcom/google/android/recaptcha/internal/zzke;->zzi(Lcom/google/android/recaptcha/internal/zzke;Lcom/google/android/recaptcha/internal/zzakg;)V

    iget-object p0, v8, Lcom/google/android/recaptcha/internal/zzka;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    .line 5
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzb()Lcom/google/android/recaptcha/internal/zzkj;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzke;->zzg(Lcom/google/android/recaptcha/internal/zzke;Lcom/google/android/recaptcha/internal/zzkm;)V

    iget-object p0, v8, Lcom/google/android/recaptcha/internal/zzka;->zze:Lkotlinx/coroutines/CompletableDeferred;

    .line 6
    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-interface {p0, p1}, Lkotlinx/coroutines/CompletableDeferred;->complete(Ljava/lang/Object;)Z

    move-result p0
    :try_end_3
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_3 .. :try_end_3} :catch_1

    .line 8
    invoke-static {p0}, Lkotlin/coroutines/jvm/internal/Boxing;->boxBoolean(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0

    :catch_1
    move-exception v0

    :goto_3
    move-object p1, v0

    goto :goto_4

    :catch_2
    move-exception v0

    move-object v8, p0

    goto :goto_3

    .line 2
    :goto_4
    iget-object p0, v8, Lcom/google/android/recaptcha/internal/zzka;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    .line 7
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzke;->zzj(Lcom/google/android/recaptcha/internal/zzke;Lcom/google/android/recaptcha/internal/zzhg;)V

    .line 8
    throw p1
.end method
