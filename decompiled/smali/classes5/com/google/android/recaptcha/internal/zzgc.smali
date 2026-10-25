.class final Lcom/google/android/recaptcha/internal/zzgc;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzgo;

.field final synthetic zzd:Ljava/lang/String;

.field private synthetic zze:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzgo;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzd:Ljava/lang/String;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzgc;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzd:Ljava/lang/String;

    invoke-direct {v0, v1, p0, p2}, Lcom/google/android/recaptcha/internal/zzgc;-><init>(Lcom/google/android/recaptcha/internal/zzgo;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzgc;->zze:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzgc;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzgc;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzgc;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 9

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzb:I

    const/4 v2, 0x3

    const/4 v3, 0x2

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz v1, :cond_3

    if-eq v1, v4, :cond_2

    if-eq v1, v3, :cond_1

    if-eq v1, v2, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_3

    :catch_0
    move-exception p1

    goto/16 :goto_5

    .line 8
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgc;->zze:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_2

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgc;->zze:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_1

    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgc;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzgc;->zze:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_0

    goto :goto_0

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgc;->zze:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzgo;->zzw(Lcom/google/android/recaptcha/internal/zzgo;)Ljava/util/HashMap;

    move-result-object v6

    const/16 v7, 0xd4

    .line 2
    invoke-static {v7}, Lkotlin/coroutines/jvm/internal/Boxing;->boxInt(I)Ljava/lang/Integer;

    move-result-object v7

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzd:Ljava/lang/String;

    invoke-interface {v6, v7, v8}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 3
    :try_start_4
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzgc;->zze:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzgc;->zza:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzb:I

    .line 4
    new-instance v4, Lcom/google/android/recaptcha/internal/zzgn;

    invoke-direct {v4, p1, v5}, Lcom/google/android/recaptcha/internal/zzgn;-><init>(Lcom/google/android/recaptcha/internal/zzgo;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v4}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_5

    move-object v4, v1

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzgc;->zze:Ljava/lang/Object;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzgc;->zza:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzb:I

    .line 5
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_5

    move-object v1, v4

    :goto_1
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzd:Ljava/lang/String;

    .line 6
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzgc;->zze:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzb:I

    .line 7
    new-instance v2, Lcom/google/android/recaptcha/internal/zzfx;

    invoke-direct {v2, p1, v3, v5}, Lcom/google/android/recaptcha/internal/zzfx;-><init>(Lcom/google/android/recaptcha/internal/zzgo;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmb;

    const/16 v3, 0x33

    invoke-direct {p1, v3, v2, v5}, Lcom/google/android/recaptcha/internal/zzmb;-><init>(ILkotlin/jvm/functions/Function2;Ljava/lang/Integer;)V

    if-eq p1, v0, :cond_5

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzgc;->zze:Ljava/lang/Object;

    const/4 v2, 0x4

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzb:I

    .line 8
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_4

    goto :goto_4

    .line 6
    :cond_4
    :goto_3
    check-cast p1, Lkotlin/Result;

    invoke-virtual {p1}, Lkotlin/Result;->unbox-impl()Ljava/lang/Object;

    move-result-object p1

    .line 9
    invoke-static {p1}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    return-object p0

    :cond_5
    :goto_4
    return-object v0

    .line 10
    :goto_5
    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgc;->zzc:Lcom/google/android/recaptcha/internal/zzgo;

    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzgo;->zzA(Lcom/google/android/recaptcha/internal/zzgo;Ljava/lang/Exception;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object p0

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0
.end method
