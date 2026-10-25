.class final Lcom/google/android/recaptcha/internal/zzlv;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzlx;

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzamk;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzlx;Lcom/google/android/recaptcha/internal/zzamk;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzlv;->zza:Lcom/google/android/recaptcha/internal/zzlx;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzlv;->zzb:Lcom/google/android/recaptcha/internal/zzamk;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance p1, Lcom/google/android/recaptcha/internal/zzlv;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzlv;->zza:Lcom/google/android/recaptcha/internal/zzlx;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlv;->zzb:Lcom/google/android/recaptcha/internal/zzamk;

    invoke-direct {p1, v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzlv;-><init>(Lcom/google/android/recaptcha/internal/zzlx;Lcom/google/android/recaptcha/internal/zzamk;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzlv;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzlv;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzlv;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :try_start_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzlv;->zza:Lcom/google/android/recaptcha/internal/zzlx;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzlx;->zzc(Lcom/google/android/recaptcha/internal/zzlx;)Lcom/google/android/recaptcha/internal/zzlq;

    move-result-object v0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlv;->zzb:Lcom/google/android/recaptcha/internal/zzamk;

    .line 2
    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzlq;->zzb(Lcom/google/android/recaptcha/internal/zzamk;)V

    const/4 p0, 0x1

    const/4 v0, 0x0

    const/4 v1, 0x0

    .line 3
    invoke-static {p1, v1, p0, v0}, Lcom/google/android/recaptcha/internal/zzlx;->zzb(Lcom/google/android/recaptcha/internal/zzlx;IILjava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 4
    :catch_0
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
