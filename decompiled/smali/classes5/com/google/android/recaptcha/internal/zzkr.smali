.class final Lcom/google/android/recaptcha/internal/zzkr;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzkw;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzala;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzkw;Lcom/google/android/recaptcha/internal/zzala;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzkr;->zzb:Lcom/google/android/recaptcha/internal/zzkw;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzkr;->zzc:Lcom/google/android/recaptcha/internal/zzala;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance p1, Lcom/google/android/recaptcha/internal/zzkr;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzkr;->zzb:Lcom/google/android/recaptcha/internal/zzkw;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzkr;->zzc:Lcom/google/android/recaptcha/internal/zzala;

    invoke-direct {p1, v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzkr;-><init>(Lcom/google/android/recaptcha/internal/zzkw;Lcom/google/android/recaptcha/internal/zzala;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzkr;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzkr;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzkr;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 4

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzkr;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    return-object p1

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzkr;->zzb:Lcom/google/android/recaptcha/internal/zzkw;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkr;->zzc:Lcom/google/android/recaptcha/internal/zzala;

    .line 2
    new-instance v2, Lcom/google/android/recaptcha/internal/zzkq;

    const/4 v3, 0x0

    invoke-direct {v2, p1, v1, v3}, Lcom/google/android/recaptcha/internal/zzkq;-><init>(Lcom/google/android/recaptcha/internal/zzkw;Lcom/google/android/recaptcha/internal/zzala;Lkotlin/coroutines/Continuation;)V

    const/4 p1, 0x1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzkr;->zza:I

    new-instance p0, Lcom/google/android/recaptcha/internal/zzmb;

    const/16 p1, 0x30

    invoke-direct {p0, p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzmb;-><init>(ILkotlin/jvm/functions/Function2;Ljava/lang/Integer;)V

    if-eq p0, v0, :cond_1

    return-object p0

    :cond_1
    return-object v0
.end method
