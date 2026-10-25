.class final Lcom/google/android/recaptcha/internal/zzof;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzon;

.field private synthetic zzd:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzon;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzof;->zzc:Lcom/google/android/recaptcha/internal/zzon;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p2}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzof;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzof;->zzc:Lcom/google/android/recaptcha/internal/zzon;

    invoke-direct {v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzof;-><init>(Lcom/google/android/recaptcha/internal/zzon;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzof;->zzd:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzof;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzof;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzof;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 7

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzof;->zzb:I

    const/4 v2, 0x3

    const/4 v3, 0x2

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz v1, :cond_3

    if-eq v1, v4, :cond_2

    if-eq v1, v3, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_3

    .line 6
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzof;->zzd:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_2

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzof;->zzd:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzof;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzof;->zzd:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzof;->zzd:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzof;->zzc:Lcom/google/android/recaptcha/internal/zzon;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzon;->zza()Lcom/google/android/recaptcha/internal/zzni;

    move-result-object p1

    .line 2
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object v6

    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzmd;->zza()Ljava/lang/String;

    move-result-object v6

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzof;->zzd:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzof;->zza:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzof;->zzb:I

    invoke-virtual {p1, v6, p0}, Lcom/google/android/recaptcha/internal/zzni;->zzb(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_5

    move-object v4, v1

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzof;->zzd:Ljava/lang/Object;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzof;->zza:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzof;->zzb:I

    .line 3
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_5

    move-object v1, v4

    :goto_1
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzof;->zzc:Lcom/google/android/recaptcha/internal/zzon;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzon;->zzc()Lcom/google/android/recaptcha/internal/zznm;

    move-result-object p1

    .line 4
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzmd;->zza()Ljava/lang/String;

    move-result-object v3

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzof;->zzd:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzof;->zzb:I

    .line 5
    new-instance v2, Lcom/google/android/recaptcha/internal/zznl;

    invoke-direct {v2, p1, v3, v5}, Lcom/google/android/recaptcha/internal/zznl;-><init>(Lcom/google/android/recaptcha/internal/zznm;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v2}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_5

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzof;->zzd:Ljava/lang/Object;

    const/4 v2, 0x4

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzof;->zzb:I

    .line 6
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_4

    goto :goto_4

    .line 7
    :cond_4
    :goto_3
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :cond_5
    :goto_4
    return-object v0
.end method
