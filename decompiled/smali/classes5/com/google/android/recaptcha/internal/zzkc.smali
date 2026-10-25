.class final Lcom/google/android/recaptcha/internal/zzkc;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzke;

.field final synthetic zzc:Lkotlinx/coroutines/CompletableDeferred;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzmg;

.field final synthetic zze:J


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzke;Lkotlinx/coroutines/CompletableDeferred;Lcom/google/android/recaptcha/internal/zzmg;JLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzkc;->zzb:Lcom/google/android/recaptcha/internal/zzke;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzkc;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzkc;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iput-wide p4, p0, Lcom/google/android/recaptcha/internal/zzkc;->zze:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/recaptcha/internal/zzkc;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkc;->zzb:Lcom/google/android/recaptcha/internal/zzke;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzkc;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzkc;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzkc;->zze:J

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzkc;-><init>(Lcom/google/android/recaptcha/internal/zzke;Lkotlinx/coroutines/CompletableDeferred;Lcom/google/android/recaptcha/internal/zzmg;JLkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzkc;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzkc;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzkc;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 13

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzkc;->zza:I

    if-eqz v1, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_0

    move-object v10, p0

    goto :goto_0

    :catch_0
    move-exception v0

    move-object p1, v0

    move-object v10, p0

    goto :goto_2

    :cond_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :try_start_1
    sget-object v1, Lcom/google/android/recaptcha/internal/zzhp;->zza:Lcom/google/android/recaptcha/internal/zzhp;

    .line 2
    new-instance v2, Lcom/google/android/recaptcha/internal/zzjz;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzkc;->zzb:Lcom/google/android/recaptcha/internal/zzke;

    invoke-direct {v2, v5}, Lcom/google/android/recaptcha/internal/zzjz;-><init>(Ljava/lang/Object;)V

    new-instance v3, Lcom/google/android/recaptcha/internal/zzkb;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzkc;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iget-wide v6, p0, Lcom/google/android/recaptcha/internal/zzkc;->zze:J

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzkc;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    const/4 v9, 0x0

    invoke-direct/range {v3 .. v9}, Lcom/google/android/recaptcha/internal/zzkb;-><init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzke;JLkotlinx/coroutines/CompletableDeferred;Lkotlin/coroutines/Continuation;)V

    const/4 p1, 0x1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzkc;->zza:I
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_2

    const/16 v11, 0xe

    const/4 v12, 0x0

    move-object v9, v3

    const-wide/16 v3, 0x0

    const-wide/16 v5, 0x0

    const-wide/16 v7, 0x0

    move-object v10, p0

    :try_start_2
    invoke-static/range {v1 .. v12}, Lcom/google/android/recaptcha/internal/zzhp;->zzd(Lcom/google/android/recaptcha/internal/zzhp;Lkotlin/jvm/functions/Function1;JJDLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_1

    return-object v0

    :cond_1
    :goto_0
    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_1

    goto :goto_3

    :catch_1
    move-exception v0

    :goto_1
    move-object p1, v0

    goto :goto_2

    :catch_2
    move-exception v0

    move-object v10, p0

    goto :goto_1

    :goto_2
    iget-object p0, v10, Lcom/google/android/recaptcha/internal/zzkc;->zzb:Lcom/google/android/recaptcha/internal/zzke;

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzd()Lcom/google/android/recaptcha/internal/zzki;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzke;->zzg(Lcom/google/android/recaptcha/internal/zzke;Lcom/google/android/recaptcha/internal/zzkm;)V

    iget-object p0, v10, Lcom/google/android/recaptcha/internal/zzkc;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    .line 4
    invoke-interface {p0, p1}, Lkotlinx/coroutines/CompletableDeferred;->completeExceptionally(Ljava/lang/Throwable;)Z

    .line 5
    :goto_3
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
