.class final Lcom/google/android/recaptcha/internal/zzjx;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# instance fields
.field zza:I

.field final synthetic zzb:J

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzke;


# direct methods
.method public constructor <init>(JLcom/google/android/recaptcha/internal/zzke;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-wide p1, p0, Lcom/google/android/recaptcha/internal/zzjx;->zzb:J

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzjx;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    const/4 p1, 0x1

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 3

    new-instance v0, Lcom/google/android/recaptcha/internal/zzjx;

    iget-wide v1, p0, Lcom/google/android/recaptcha/internal/zzjx;->zzb:J

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzjx;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    invoke-direct {v0, v1, v2, p0, p1}, Lcom/google/android/recaptcha/internal/zzjx;-><init>(JLcom/google/android/recaptcha/internal/zzke;Lkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzjx;->create(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzjx;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzjx;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 5

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzjx;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    goto :goto_0

    :cond_0
    iget-wide v1, p0, Lcom/google/android/recaptcha/internal/zzjx;->zzb:J

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzjx;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    new-instance v3, Lcom/google/android/recaptcha/internal/zzjw;

    const/4 v4, 0x0

    invoke-direct {v3, p1, v4}, Lcom/google/android/recaptcha/internal/zzjw;-><init>(Lcom/google/android/recaptcha/internal/zzke;Lkotlin/coroutines/Continuation;)V

    const/4 p1, 0x1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzjx;->zza:I

    invoke-static {v1, v2, v3, p0}, Lkotlinx/coroutines/TimeoutKt;->withTimeout(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_1

    return-object v0

    :cond_1
    :goto_0
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
