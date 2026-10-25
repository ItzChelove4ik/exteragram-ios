.class final Lcom/google/android/recaptcha/internal/zzkq;
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

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzkq;->zzb:Lcom/google/android/recaptcha/internal/zzkw;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzkq;->zzc:Lcom/google/android/recaptcha/internal/zzala;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance p1, Lcom/google/android/recaptcha/internal/zzkq;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzkq;->zzb:Lcom/google/android/recaptcha/internal/zzkw;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzkq;->zzc:Lcom/google/android/recaptcha/internal/zzala;

    invoke-direct {p1, v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzkq;-><init>(Lcom/google/android/recaptcha/internal/zzkw;Lcom/google/android/recaptcha/internal/zzala;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzkq;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzkq;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzkq;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 4

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzkq;->zza:I

    if-eqz v1, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catch_0
    move-exception p1

    goto :goto_1

    :cond_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :try_start_1
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzkq;->zzb:Lcom/google/android/recaptcha/internal/zzkw;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzkw;->zzc(Lcom/google/android/recaptcha/internal/zzkw;)Lcom/google/android/recaptcha/internal/zzmw;

    move-result-object v1

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzkw;->zzd(Lcom/google/android/recaptcha/internal/zzkw;)Lcom/google/android/recaptcha/internal/zzhq;

    move-result-object p1

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzkq;->zzc:Lcom/google/android/recaptcha/internal/zzala;

    const/4 v3, 0x1

    .line 2
    iput v3, p0, Lcom/google/android/recaptcha/internal/zzkq;->zza:I

    invoke-interface {v1, p1, v2, p0}, Lcom/google/android/recaptcha/internal/zzmw;->zza(Lcom/google/android/recaptcha/internal/zzhq;Lcom/google/android/recaptcha/internal/zzala;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_1

    return-object v0

    .line 1
    :cond_1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzalc;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    return-object p1

    :catchall_0
    move-exception p0

    .line 4
    throw p0

    .line 2
    :goto_1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzkq;->zzb:Lcom/google/android/recaptcha/internal/zzkw;

    .line 3
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzkw;->zzb(Lcom/google/android/recaptcha/internal/zzkw;Ljava/lang/Exception;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object p0

    throw p0
.end method
