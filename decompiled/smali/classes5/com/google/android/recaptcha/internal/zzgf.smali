.class final Lcom/google/android/recaptcha/internal/zzgf;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzgo;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzalm;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzalm;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgf;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzgf;->zzd:Lcom/google/android/recaptcha/internal/zzalm;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance p1, Lcom/google/android/recaptcha/internal/zzgf;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzgf;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgf;->zzd:Lcom/google/android/recaptcha/internal/zzalm;

    invoke-direct {p1, v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzgf;-><init>(Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzalm;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzgf;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzgf;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzgf;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 7

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzgf;->zzb:I

    const/4 v2, 0x2

    const/4 v3, 0x1

    if-eqz v0, :cond_2

    if-eq v0, v3, :cond_1

    if-ne v0, v2, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    :catch_0
    move-exception v0

    move-object p1, v0

    goto :goto_2

    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgf;->zza:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/recaptcha/internal/zzhg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_3

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgf;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzgo;->zzB(Lcom/google/android/recaptcha/internal/zzgo;)Lcom/google/android/recaptcha/internal/zzgy;

    move-result-object p1

    .line 2
    iput v3, p0, Lcom/google/android/recaptcha/internal/zzgf;->zzb:I

    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzgy;->zzc(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_6

    .line 1
    :goto_0
    check-cast p1, Ljava/lang/String;

    if-eqz p1, :cond_5

    .line 3
    :try_start_1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzgf;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzgo;->zzr(Lcom/google/android/recaptcha/internal/zzgo;)Lcom/google/android/recaptcha/internal/zzgz;

    move-result-object v0

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzgf;->zzd:Lcom/google/android/recaptcha/internal/zzalm;

    .line 4
    iput v2, p0, Lcom/google/android/recaptcha/internal/zzgf;->zzb:I

    invoke-interface {v0, v3, p1, p0}, Lcom/google/android/recaptcha/internal/zzgz;->zza(Lcom/google/android/recaptcha/internal/zzalm;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_0

    if-ne p0, v1, :cond_3

    goto :goto_4

    .line 8
    :cond_3
    :goto_1
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 1
    :goto_2
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzgf;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzgo;->zzB(Lcom/google/android/recaptcha/internal/zzgo;)Lcom/google/android/recaptcha/internal/zzgy;

    move-result-object v0

    .line 5
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgf;->zza:Ljava/lang/Object;

    const/4 v2, 0x3

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzgf;->zzb:I

    .line 6
    new-instance p0, Lcom/google/android/recaptcha/internal/zzgr;

    const/4 v2, 0x0

    invoke-direct {p0, v0, v2}, Lcom/google/android/recaptcha/internal/zzgr;-><init>(Lcom/google/android/recaptcha/internal/zzgy;Lkotlin/coroutines/Continuation;)V

    new-instance v0, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-ne v0, v1, :cond_4

    goto :goto_4

    :cond_4
    move-object p0, p1

    .line 7
    :goto_3
    throw p0

    .line 3
    :cond_5
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzaq:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0

    :cond_6
    :goto_4
    return-object v1
.end method
