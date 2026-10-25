.class final Lcom/google/android/recaptcha/internal/zzem;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:Ljava/lang/Object;

.field zzd:I

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzakg;

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zzeo;

.field final synthetic zzg:Lcom/google/android/recaptcha/internal/zzmg;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zzeo;Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzem;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzem;->zzf:Lcom/google/android/recaptcha/internal/zzeo;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzem;->zzg:Lcom/google/android/recaptcha/internal/zzmg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance p1, Lcom/google/android/recaptcha/internal/zzem;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzem;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzem;->zzf:Lcom/google/android/recaptcha/internal/zzeo;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzem;->zzg:Lcom/google/android/recaptcha/internal/zzmg;

    invoke-direct {p1, v0, v1, p0, p2}, Lcom/google/android/recaptcha/internal/zzem;-><init>(Lcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zzeo;Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzem;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzem;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzem;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 8

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzem;->zzd:I

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzem;->zzb:Ljava/lang/Object;

    check-cast v1, Ljava/util/Iterator;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzem;->zza:Ljava/lang/Object;

    check-cast v3, Lcom/google/android/recaptcha/internal/zzakk;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    .line 12
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzem;->zzc:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzem;->zzb:Ljava/lang/Object;

    check-cast v3, Ljava/util/Iterator;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzem;->zza:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzakk;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_1

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzem;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzakg;->zzc()Z

    move-result v1

    if-nez v1, :cond_2

    .line 3
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 4
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzu:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 3
    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0

    .line 5
    :cond_2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzakg;->zzd()Lcom/google/android/recaptcha/internal/zzakk;

    move-result-object v3

    .line 6
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzakk;->zza()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzr()Z

    move-result p1

    if-eqz p1, :cond_3

    .line 7
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 8
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzu:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 7
    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0

    :cond_3
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzem;->zzf:Lcom/google/android/recaptcha/internal/zzeo;

    .line 9
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzakk;->zza()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v1

    invoke-static {p1, v1}, Lcom/google/android/recaptcha/internal/zzeo;->zzi(Lcom/google/android/recaptcha/internal/zzeo;Lcom/google/android/recaptcha/internal/zzaer;)V

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzeo;->zzj(Lcom/google/android/recaptcha/internal/zzeo;)Ljava/util/List;

    move-result-object p1

    .line 10
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_5

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzev;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzem;->zzg:Lcom/google/android/recaptcha/internal/zzmg;

    .line 11
    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzem;->zza:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzem;->zzb:Ljava/lang/Object;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzem;->zzc:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzem;->zzd:I

    invoke-interface {p1, v3, p0}, Lcom/google/android/recaptcha/internal/zzev;->zzg(Lcom/google/android/recaptcha/internal/zzakk;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_4

    move-object v7, v3

    move-object v3, v1

    move-object v1, v4

    move-object v4, v7

    .line 1
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzem;->zza:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzem;->zzb:Ljava/lang/Object;

    const/4 v5, 0x0

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzem;->zzc:Ljava/lang/Object;

    const/4 v5, 0x2

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzem;->zzd:I

    .line 11
    invoke-virtual {v1, p1, p0}, Lcom/google/android/recaptcha/internal/zzmg;->zzc(Lcom/google/android/recaptcha/internal/zzmb;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_4

    move-object v1, v3

    move-object v3, v4

    goto :goto_0

    :cond_4
    return-object v0

    .line 12
    :cond_5
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0
.end method
