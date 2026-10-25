.class final Lcom/google/android/recaptcha/internal/zzgw;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzlp;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzgy;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzgy;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgw;->zzb:Lcom/google/android/recaptcha/internal/zzlp;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzgw;->zzc:Lcom/google/android/recaptcha/internal/zzgy;

    const/4 p1, 0x1

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzgw;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgw;->zzb:Lcom/google/android/recaptcha/internal/zzlp;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgw;->zzc:Lcom/google/android/recaptcha/internal/zzgy;

    invoke-direct {v0, v1, p0, p1}, Lcom/google/android/recaptcha/internal/zzgw;-><init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzgy;Lkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzgw;->create(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzgw;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzgw;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 4

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzgw;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    return-object p1

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgw;->zzb:Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgw;->zzc:Lcom/google/android/recaptcha/internal/zzgy;

    .line 2
    new-instance v2, Lcom/google/android/recaptcha/internal/zzgv;

    const/4 v3, 0x0

    invoke-direct {v2, v1, v3}, Lcom/google/android/recaptcha/internal/zzgv;-><init>(Lcom/google/android/recaptcha/internal/zzgy;Lkotlin/coroutines/Continuation;)V

    const/4 v1, 0x1

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzgw;->zza:I

    const/16 v1, 0x36

    .line 3
    invoke-virtual {p1, v1, v3, v2, p0}, Lcom/google/android/recaptcha/internal/zzlp;->zzd(ILjava/lang/Integer;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_1

    return-object v0

    :cond_1
    return-object p0
.end method
