.class final Lcom/google/android/recaptcha/internal/zzgi;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:I

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzgo;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzakg;

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zzmg;

.field private synthetic zzg:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzd:Lcom/google/android/recaptcha/internal/zzgo;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzgi;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzf:Lcom/google/android/recaptcha/internal/zzmg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 3

    new-instance v0, Lcom/google/android/recaptcha/internal/zzgi;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzd:Lcom/google/android/recaptcha/internal/zzgo;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzgi;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzf:Lcom/google/android/recaptcha/internal/zzmg;

    invoke-direct {v0, v1, v2, p0, p2}, Lcom/google/android/recaptcha/internal/zzgi;-><init>(Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzgi;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzgi;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzgi;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 11

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzc:I

    const/4 v2, 0x5

    const/4 v3, 0x4

    const/4 v4, 0x3

    const/4 v5, 0x2

    const/4 v6, 0x1

    const/4 v7, 0x0

    if-eqz v1, :cond_5

    if-eq v1, v6, :cond_4

    if-eq v1, v5, :cond_3

    if-eq v1, v4, :cond_2

    if-eq v1, v3, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_5

    .line 10
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_4

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzalm;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    check-cast v3, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object p1, v1

    move-object v1, v3

    goto/16 :goto_3

    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzb:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzgi;->zza:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzalm;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    check-cast v5, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v10, v4

    move-object v4, v1

    move-object v1, v10

    goto/16 :goto_2

    :cond_3
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_4
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    check-cast v6, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_5
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzd:Lcom/google/android/recaptcha/internal/zzgo;

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzgi;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    iget-object v9, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzf:Lcom/google/android/recaptcha/internal/zzmg;

    .line 2
    invoke-virtual {v9}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object v9

    invoke-virtual {v9}, Lcom/google/android/recaptcha/internal/zzmd;->zza()Ljava/lang/String;

    move-result-object v9

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zza:Ljava/lang/Object;

    iput v6, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzc:I

    .line 3
    new-instance v6, Lcom/google/android/recaptcha/internal/zzgb;

    invoke-direct {v6, p1, v9, v8, v7}, Lcom/google/android/recaptcha/internal/zzgb;-><init>(Lcom/google/android/recaptcha/internal/zzgo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v6}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_7

    move-object v6, v1

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v6, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    iput-object v7, p0, Lcom/google/android/recaptcha/internal/zzgi;->zza:Ljava/lang/Object;

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzc:I

    .line 4
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_7

    move-object v1, v6

    .line 1
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzalm;

    .line 5
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zza:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzb:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzc:I

    .line 6
    new-instance v4, Lcom/google/android/recaptcha/internal/zzgm;

    invoke-direct {v4, p1, v7}, Lcom/google/android/recaptcha/internal/zzgm;-><init>(Lcom/google/android/recaptcha/internal/zzalm;Lkotlin/coroutines/Continuation;)V

    new-instance v5, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {v5, v4}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq v5, v0, :cond_7

    move-object v4, v1

    move-object v1, p1

    move-object p1, v5

    move-object v5, v4

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zza:Ljava/lang/Object;

    iput-object v7, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzb:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzc:I

    .line 7
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v3

    invoke-virtual {p1, v3, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_7

    move-object p1, v1

    move-object v1, v5

    :goto_3
    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzd:Lcom/google/android/recaptcha/internal/zzgo;

    .line 8
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    iput-object v7, p0, Lcom/google/android/recaptcha/internal/zzgi;->zza:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzc:I

    .line 9
    new-instance v2, Lcom/google/android/recaptcha/internal/zzgh;

    invoke-direct {v2, v3, p1, v7}, Lcom/google/android/recaptcha/internal/zzgh;-><init>(Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzalm;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v2}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_7

    .line 1
    :goto_4
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v7, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzg:Ljava/lang/Object;

    const/4 v2, 0x6

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzgi;->zzc:I

    .line 10
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_6

    goto :goto_6

    .line 11
    :cond_6
    :goto_5
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0

    :cond_7
    :goto_6
    return-object v0
.end method
