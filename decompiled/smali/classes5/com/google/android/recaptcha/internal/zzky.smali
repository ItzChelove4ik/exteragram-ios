.class final Lcom/google/android/recaptcha/internal/zzky;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzlp;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzlf;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzamf;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzky;->zzc:Lcom/google/android/recaptcha/internal/zzlp;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzky;->zzd:Lcom/google/android/recaptcha/internal/zzlf;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzky;->zze:Lcom/google/android/recaptcha/internal/zzamf;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance p1, Lcom/google/android/recaptcha/internal/zzky;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzky;->zzc:Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzky;->zzd:Lcom/google/android/recaptcha/internal/zzlf;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzky;->zze:Lcom/google/android/recaptcha/internal/zzamf;

    invoke-direct {p1, v0, v1, p0, p2}, Lcom/google/android/recaptcha/internal/zzky;-><init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzky;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzky;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzky;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 4

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzky;->zzb:I

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    .line 3
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzky;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzky;->zzc:Lcom/google/android/recaptcha/internal/zzlp;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzky;->zzd:Lcom/google/android/recaptcha/internal/zzlf;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzky;->zze:Lcom/google/android/recaptcha/internal/zzamf;

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzky;->zza:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzky;->zzb:I

    invoke-static {p1, v3, p0}, Lcom/google/android/recaptcha/internal/zzlf;->zze(Lcom/google/android/recaptcha/internal/zzlf;Lcom/google/android/recaptcha/internal/zzamf;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_3

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    const/4 v2, 0x0

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzky;->zza:Ljava/lang/Object;

    const/4 v2, 0x2

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzky;->zzb:I

    .line 3
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_2

    goto :goto_2

    .line 1
    :cond_2
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzakg;

    return-object p1

    :cond_3
    :goto_2
    return-object v0
.end method
