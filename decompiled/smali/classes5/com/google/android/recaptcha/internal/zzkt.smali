.class final Lcom/google/android/recaptcha/internal/zzkt;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzlp;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzkw;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzala;

.field final synthetic zzf:Lkotlin/jvm/internal/Ref$ObjectRef;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzkw;Lcom/google/android/recaptcha/internal/zzala;Lkotlin/jvm/internal/Ref$ObjectRef;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzkt;->zzc:Lcom/google/android/recaptcha/internal/zzlp;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzkt;->zzd:Lcom/google/android/recaptcha/internal/zzkw;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzkt;->zze:Lcom/google/android/recaptcha/internal/zzala;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzkt;->zzf:Lkotlin/jvm/internal/Ref$ObjectRef;

    const/4 p1, 0x1

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    new-instance v0, Lcom/google/android/recaptcha/internal/zzkt;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkt;->zzc:Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzkt;->zzd:Lcom/google/android/recaptcha/internal/zzkw;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzkt;->zze:Lcom/google/android/recaptcha/internal/zzala;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzkt;->zzf:Lkotlin/jvm/internal/Ref$ObjectRef;

    move-object v5, p1

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzkt;-><init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzkw;Lcom/google/android/recaptcha/internal/zzala;Lkotlin/jvm/internal/Ref$ObjectRef;Lkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzkt;->create(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzkt;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzkt;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 11

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzkt;->zzb:I

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v3, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    :catch_0
    move-exception v0

    move-object p1, v0

    goto :goto_4

    .line 6
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkt;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :try_start_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkt;->zzc:Lcom/google/android/recaptcha/internal/zzlp;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzkt;->zzd:Lcom/google/android/recaptcha/internal/zzkw;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzkt;->zze:Lcom/google/android/recaptcha/internal/zzala;

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzkt;->zza:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzkt;->zzb:I

    .line 3
    new-instance v5, Lcom/google/android/recaptcha/internal/zzkr;

    invoke-direct {v5, p1, v4, v2}, Lcom/google/android/recaptcha/internal/zzkr;-><init>(Lcom/google/android/recaptcha/internal/zzkw;Lcom/google/android/recaptcha/internal/zzala;Lkotlin/coroutines/Continuation;)V

    invoke-static {v5, p0}, Lkotlinx/coroutines/CoroutineScopeKt;->coroutineScope(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_2

    goto :goto_1

    .line 1
    :cond_2
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzkt;->zza:Ljava/lang/Object;

    const/4 v2, 0x2

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzkt;->zzb:I

    .line 4
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_3

    :goto_1
    return-object v0

    .line 1
    :cond_3
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzalc;

    .line 5
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzalc;->zza()Ljava/lang/String;

    move-result-object v0

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_5

    .line 6
    new-instance v4, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v5, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v6, Lcom/google/android/recaptcha/internal/zzhe;->zzam:Lcom/google/android/recaptcha/internal/zzhe;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzalc;->zzh()I

    move-result p1

    if-ne p1, v3, :cond_4

    .line 8
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzagf;->zza()I

    move-result p1

    goto :goto_3

    :cond_4
    add-int/lit8 p1, p1, -0x2

    .line 7
    :goto_3
    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v7

    const/16 v9, 0x8

    const/4 v10, 0x0

    const/4 v8, 0x0

    .line 6
    invoke-direct/range {v4 .. v10}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v4
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_0

    :cond_5
    return-object p1

    .line 3
    :goto_4
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzkt;->zzf:Lkotlin/jvm/internal/Ref$ObjectRef;

    .line 9
    iput-object p1, p0, Lkotlin/jvm/internal/Ref$ObjectRef;->element:Ljava/lang/Object;

    .line 10
    throw p1
.end method
