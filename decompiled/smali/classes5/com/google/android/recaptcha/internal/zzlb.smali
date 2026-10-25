.class final Lcom/google/android/recaptcha/internal/zzlb;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzlf;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzamf;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzlb;->zzb:Lcom/google/android/recaptcha/internal/zzlf;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzlb;->zzc:Lcom/google/android/recaptcha/internal/zzamf;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance p1, Lcom/google/android/recaptcha/internal/zzlb;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzlb;->zzb:Lcom/google/android/recaptcha/internal/zzlf;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlb;->zzc:Lcom/google/android/recaptcha/internal/zzamf;

    invoke-direct {p1, v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzlb;-><init>(Lcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzlb;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzlb;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzlb;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 4

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzlb;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzlb;->zzb:Lcom/google/android/recaptcha/internal/zzlf;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzlf;->zzg(Lcom/google/android/recaptcha/internal/zzlf;)Lcom/google/android/recaptcha/internal/zzmt;

    move-result-object v1

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzlf;->zzh(Lcom/google/android/recaptcha/internal/zzlf;)Lcom/google/android/recaptcha/internal/zzhq;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhq;->zza()Ljava/lang/String;

    move-result-object p1

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzlb;->zzc:Lcom/google/android/recaptcha/internal/zzamf;

    const/4 v3, 0x1

    .line 2
    iput v3, p0, Lcom/google/android/recaptcha/internal/zzlb;->zza:I

    invoke-interface {v1, p1, v2, p0}, Lcom/google/android/recaptcha/internal/zzmt;->zza(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzamf;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_1

    return-object v0

    .line 1
    :cond_1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzakg;

    return-object p1
.end method
