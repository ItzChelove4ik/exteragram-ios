.class final Lcom/google/android/recaptcha/internal/zzcy;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:I

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzcz;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzdo;

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzcz;Lcom/google/android/recaptcha/internal/zzdo;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzd:Lcom/google/android/recaptcha/internal/zzcz;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzcy;->zze:Lcom/google/android/recaptcha/internal/zzdo;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzcy;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzd:Lcom/google/android/recaptcha/internal/zzcz;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzcy;->zze:Lcom/google/android/recaptcha/internal/zzdo;

    invoke-direct {v0, v1, p0, p2}, Lcom/google/android/recaptcha/internal/zzcy;-><init>(Lcom/google/android/recaptcha/internal/zzcz;Lcom/google/android/recaptcha/internal/zzdo;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzcy;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzcy;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzcy;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzcy;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 8

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzc:I

    const/4 v2, 0x4

    const/4 v3, 0x3

    const/4 v4, 0x2

    const/4 v5, 0x1

    const/4 v6, 0x0

    if-eqz v1, :cond_4

    if-eq v1, v5, :cond_3

    if-eq v1, v4, :cond_2

    if-eq v1, v3, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_4

    .line 8
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzf:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_3

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzcz;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzf:Ljava/lang/Object;

    check-cast v3, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_2

    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzb:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzcy;->zza:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzcz;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzf:Ljava/lang/Object;

    check-cast v5, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_4
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzf:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzd:Lcom/google/android/recaptcha/internal/zzcz;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzcz;->zzb(Lcom/google/android/recaptcha/internal/zzcz;)Z

    move-result v7

    if-eqz v7, :cond_5

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzcz;->zzc(Lcom/google/android/recaptcha/internal/zzcz;)Lkotlinx/coroutines/Deferred;

    move-result-object v7

    if-eqz v7, :cond_5

    const/4 v1, 0x0

    .line 2
    invoke-static {p1, v1}, Lcom/google/android/recaptcha/internal/zzcz;->zzd(Lcom/google/android/recaptcha/internal/zzcz;Z)V

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzcz;->zzc(Lcom/google/android/recaptcha/internal/zzcz;)Lkotlinx/coroutines/Deferred;

    move-result-object p1

    .line 3
    iput v5, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzc:I

    invoke-interface {p1, p0}, Lkotlinx/coroutines/Deferred;->await(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_7

    :goto_0
    check-cast p1, Lkotlin/Result;

    invoke-virtual {p1}, Lkotlin/Result;->unbox-impl()Ljava/lang/Object;

    move-result-object p0

    .line 4
    invoke-static {p0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    check-cast p0, Lcom/google/android/recaptcha/internal/zzaku;

    return-object p0

    :cond_5
    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzcy;->zze:Lcom/google/android/recaptcha/internal/zzdo;

    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzdo;->zzj(Lcom/google/android/recaptcha/internal/zzdo;)Lcom/google/android/recaptcha/internal/zzng;

    move-result-object v5

    .line 5
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzf:Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zza:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzb:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzc:I

    invoke-virtual {v5, p0}, Lcom/google/android/recaptcha/internal/zzoa;->zzh(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v4

    if-eq v4, v0, :cond_7

    move-object v5, v4

    move-object v4, p1

    move-object p1, v5

    move-object v5, v1

    .line 1
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzf:Ljava/lang/Object;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzcy;->zza:Ljava/lang/Object;

    iput-object v6, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzb:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzc:I

    .line 6
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_7

    move-object v1, v4

    move-object v3, v5

    .line 5
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaka;

    invoke-static {v1, p1}, Lcom/google/android/recaptcha/internal/zzcz;->zze(Lcom/google/android/recaptcha/internal/zzcz;Lcom/google/android/recaptcha/internal/zzaka;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zze:Lcom/google/android/recaptcha/internal/zzdo;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzd:Lcom/google/android/recaptcha/internal/zzcz;

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzcz;->zzf(Lcom/google/android/recaptcha/internal/zzcz;)Ljava/lang/String;

    move-result-object v4

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzcz;->zzg(Lcom/google/android/recaptcha/internal/zzcz;)Lcom/google/android/recaptcha/internal/zzaka;

    move-result-object v1

    .line 7
    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzf:Ljava/lang/Object;

    iput-object v6, p0, Lcom/google/android/recaptcha/internal/zzcy;->zza:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzc:I

    invoke-static {p1, v4, v1, p0}, Lcom/google/android/recaptcha/internal/zzdo;->zzf(Lcom/google/android/recaptcha/internal/zzdo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_7

    move-object v1, v3

    .line 1
    :goto_3
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v6, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzf:Ljava/lang/Object;

    const/4 v2, 0x5

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzcy;->zzc:I

    .line 8
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_6

    goto :goto_5

    .line 1
    :cond_6
    :goto_4
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaku;

    return-object p1

    :cond_7
    :goto_5
    return-object v0
.end method
