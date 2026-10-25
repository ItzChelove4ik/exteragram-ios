.class final Lcom/google/android/recaptcha/internal/zzgb;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzgo;

.field final synthetic zzc:Ljava/lang/String;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzakg;

.field private synthetic zze:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzgo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgb;->zzb:Lcom/google/android/recaptcha/internal/zzgo;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzgb;->zzc:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzgb;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 3

    new-instance v0, Lcom/google/android/recaptcha/internal/zzgb;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgb;->zzb:Lcom/google/android/recaptcha/internal/zzgo;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzgb;->zzc:Ljava/lang/String;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgb;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    invoke-direct {v0, v1, v2, p0, p2}, Lcom/google/android/recaptcha/internal/zzgb;-><init>(Lcom/google/android/recaptcha/internal/zzgo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzgb;->zze:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzgb;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzgb;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzgb;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 6

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzgb;->zza:I

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object p1

    .line 14
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgb;->zze:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgb;->zze:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgb;->zzb:Lcom/google/android/recaptcha/internal/zzgo;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzalk;->zza()Lcom/google/android/recaptcha/internal/zzalj;

    move-result-object v4

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzgp;->zzb()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzalj;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalj;

    const-string v5, "18.9.2"

    .line 4
    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzalj;->zzb(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalj;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzgb;->zzc:Ljava/lang/String;

    .line 5
    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzalj;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalj;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzgb;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    .line 6
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzakg;->zzb()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzalj;->zzd(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalj;

    .line 7
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v4

    .line 8
    check-cast v4, Lcom/google/android/recaptcha/internal/zzalk;

    .line 9
    :try_start_1
    new-instance v5, Lcom/google/android/recaptcha/internal/zzga;

    invoke-direct {v5, p1, v4, v3}, Lcom/google/android/recaptcha/internal/zzga;-><init>(Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzalk;Lkotlin/coroutines/Continuation;)V

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzgb;->zze:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzgb;->zza:I

    const/16 p1, 0x4d

    .line 10
    invoke-virtual {v1, p1, v3, v5, p0}, Lcom/google/android/recaptcha/internal/zzmg;->zze(ILjava/lang/Integer;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_3

    .line 9
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzalm;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_1

    .line 11
    :catch_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzalm;->zzd()Lcom/google/android/recaptcha/internal/zzalm;

    move-result-object p1

    .line 12
    :goto_1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzalm;->zza()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaer;->zzr()Z

    move-result v2

    if-nez v2, :cond_2

    return-object p1

    :cond_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgb;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    .line 13
    new-instance v2, Lcom/google/android/recaptcha/internal/zzfz;

    invoke-direct {v2, p1, v3}, Lcom/google/android/recaptcha/internal/zzfz;-><init>(Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzgb;->zze:Ljava/lang/Object;

    const/4 p1, 0x2

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzgb;->zza:I

    const/16 p1, 0x56

    .line 14
    invoke-virtual {v1, p1, v3, v2, p0}, Lcom/google/android/recaptcha/internal/zzmg;->zze(ILjava/lang/Integer;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_4

    :cond_3
    return-object v0

    :cond_4
    return-object p0
.end method
