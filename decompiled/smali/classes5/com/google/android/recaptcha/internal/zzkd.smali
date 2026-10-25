.class final Lcom/google/android/recaptcha/internal/zzkd;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzke;

.field final synthetic zzb:J

.field private synthetic zzc:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzke;JLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzkd;->zza:Lcom/google/android/recaptcha/internal/zzke;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzkd;->zzb:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 4

    new-instance v0, Lcom/google/android/recaptcha/internal/zzkd;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkd;->zza:Lcom/google/android/recaptcha/internal/zzke;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzkd;->zzb:J

    invoke-direct {v0, v1, v2, v3, p2}, Lcom/google/android/recaptcha/internal/zzkd;-><init>(Lcom/google/android/recaptcha/internal/zzke;JLkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzkd;->zzc:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzkd;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzkd;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzkd;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 10

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzkd;->zzc:Ljava/lang/Object;

    move-object v3, p1

    check-cast v3, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkd;->zza:Lcom/google/android/recaptcha/internal/zzke;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzke;->zza()Lcom/google/android/recaptcha/internal/zzkm;

    move-result-object p1

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzb()Lcom/google/android/recaptcha/internal/zzkj;

    move-result-object v0

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_2

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzke;->zza()Lcom/google/android/recaptcha/internal/zzkm;

    move-result-object p1

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzc()Lcom/google/android/recaptcha/internal/zzkk;

    move-result-object v0

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    goto :goto_0

    .line 11
    :cond_0
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzke;->zza()Lcom/google/android/recaptcha/internal/zzkm;

    move-result-object p1

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzd()Lcom/google/android/recaptcha/internal/zzki;

    move-result-object v0

    .line 3
    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzke;->zzf(Lcom/google/android/recaptcha/internal/zzke;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object p1

    .line 4
    invoke-static {v1, p1}, Lcom/google/android/recaptcha/internal/zzke;->zze(Lcom/google/android/recaptcha/internal/zzke;Ljava/lang/Exception;)Z

    move-result p1

    if-nez p1, :cond_1

    .line 5
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :cond_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zzc()Lcom/google/android/recaptcha/internal/zzkk;

    move-result-object p1

    .line 6
    invoke-static {v1, p1}, Lcom/google/android/recaptcha/internal/zzke;->zzg(Lcom/google/android/recaptcha/internal/zzke;Lcom/google/android/recaptcha/internal/zzkm;)V

    const/4 p1, 0x1

    const/4 v0, 0x0

    .line 7
    invoke-static {v0, p1, v0}, Lkotlinx/coroutines/CompletableDeferredKt;->CompletableDeferred$default(Lkotlinx/coroutines/Job;ILjava/lang/Object;)Lkotlinx/coroutines/CompletableDeferred;

    move-result-object v2

    .line 8
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzke;->zzh(Lcom/google/android/recaptcha/internal/zzke;Lkotlinx/coroutines/CompletableDeferred;)V

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzke;->zzo(Lcom/google/android/recaptcha/internal/zzke;)Lcom/google/android/recaptcha/internal/zzhu;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhu;->zzc()Lkotlinx/coroutines/CoroutineScope;

    move-result-object p1

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzkd;->zzb:J

    .line 9
    new-instance v0, Lcom/google/android/recaptcha/internal/zzkc;

    const/4 v6, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzkc;-><init>(Lcom/google/android/recaptcha/internal/zzke;Lkotlinx/coroutines/CompletableDeferred;Lcom/google/android/recaptcha/internal/zzmg;JLkotlin/coroutines/Continuation;)V

    const/4 v8, 0x3

    const/4 v9, 0x0

    const/4 v5, 0x0

    move-object v4, p1

    move-object v7, v0

    invoke-static/range {v4 .. v9}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    .line 10
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 11
    :cond_2
    :goto_0
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
