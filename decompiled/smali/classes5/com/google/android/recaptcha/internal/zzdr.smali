.class final Lcom/google/android/recaptcha/internal/zzdr;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzdv;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzalg;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzdv;Lcom/google/android/recaptcha/internal/zzalg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzdr;->zzb:Lcom/google/android/recaptcha/internal/zzdv;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzdr;->zzc:Lcom/google/android/recaptcha/internal/zzalg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance p1, Lcom/google/android/recaptcha/internal/zzdr;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzdr;->zzb:Lcom/google/android/recaptcha/internal/zzdv;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzdr;->zzc:Lcom/google/android/recaptcha/internal/zzalg;

    invoke-direct {p1, v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzdr;-><init>(Lcom/google/android/recaptcha/internal/zzdv;Lcom/google/android/recaptcha/internal/zzalg;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzdr;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzdr;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzdr;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 4

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzdr;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    return-object p1

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzdr;->zzb:Lcom/google/android/recaptcha/internal/zzdv;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdr;->zzc:Lcom/google/android/recaptcha/internal/zzalg;

    .line 2
    new-instance v2, Lcom/google/android/recaptcha/internal/zzdq;

    const/4 v3, 0x0

    invoke-direct {v2, p1, v1, v3}, Lcom/google/android/recaptcha/internal/zzdq;-><init>(Lcom/google/android/recaptcha/internal/zzdv;Lcom/google/android/recaptcha/internal/zzalg;Lkotlin/coroutines/Continuation;)V

    const/4 p1, 0x1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzdr;->zza:I

    new-instance p0, Lcom/google/android/recaptcha/internal/zzmb;

    const/16 p1, 0x40

    invoke-direct {p0, p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzmb;-><init>(ILkotlin/jvm/functions/Function2;Ljava/lang/Integer;)V

    if-eq p0, v0, :cond_1

    return-object p0

    :cond_1
    return-object v0
.end method
