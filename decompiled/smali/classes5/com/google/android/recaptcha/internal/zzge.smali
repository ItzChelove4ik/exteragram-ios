.class final Lcom/google/android/recaptcha/internal/zzge;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field final synthetic zza:Ljava/lang/Long;

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzgo;

.field final synthetic zzc:Ljava/util/Optional;


# direct methods
.method public constructor <init>(Ljava/lang/Long;Lcom/google/android/recaptcha/internal/zzgo;Ljava/util/Optional;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzge;->zza:Ljava/lang/Long;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzge;->zzb:Lcom/google/android/recaptcha/internal/zzgo;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzge;->zzc:Ljava/util/Optional;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance p1, Lcom/google/android/recaptcha/internal/zzge;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzge;->zza:Ljava/lang/Long;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzge;->zzb:Lcom/google/android/recaptcha/internal/zzgo;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzge;->zzc:Ljava/util/Optional;

    invoke-direct {p1, v0, v1, p0, p2}, Lcom/google/android/recaptcha/internal/zzge;-><init>(Ljava/lang/Long;Lcom/google/android/recaptcha/internal/zzgo;Ljava/util/Optional;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzge;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzge;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzge;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 5

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :try_start_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzge;->zza:Ljava/lang/Long;

    const/4 v0, 0x0

    if-eqz p1, :cond_1

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzge;->zzb:Lcom/google/android/recaptcha/internal/zzgo;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzge;->zzc:Ljava/util/Optional;

    .line 2
    invoke-virtual {p1}, Ljava/lang/Number;->longValue()J

    move-result-wide v3

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzgo;->zzq(Lcom/google/android/recaptcha/internal/zzgo;)Lcom/google/android/recaptcha/internal/zzd;

    move-result-object p1

    if-nez p1, :cond_0

    move-object p1, v0

    :cond_0
    invoke-interface {p1, v3, v4, v2}, Lcom/google/android/recaptcha/internal/zzd;->zzd(JLjava/util/Optional;)Ljava/lang/Object;

    move-result-object p1

    if-nez p1, :cond_3

    :cond_1
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzge;->zzb:Lcom/google/android/recaptcha/internal/zzgo;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzgo;->zzq(Lcom/google/android/recaptcha/internal/zzgo;)Lcom/google/android/recaptcha/internal/zzd;

    move-result-object p1

    if-nez p1, :cond_2

    goto :goto_0

    :cond_2
    move-object v0, p1

    :goto_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzge;->zzc:Ljava/util/Optional;

    invoke-interface {v0, p0}, Lcom/google/android/recaptcha/internal/zzd;->zzc(Ljava/util/Optional;)Ljava/lang/Object;

    move-result-object p1

    .line 3
    :cond_3
    invoke-static {p1}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    :catch_0
    move-exception p0

    .line 4
    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    :goto_1
    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0
.end method
