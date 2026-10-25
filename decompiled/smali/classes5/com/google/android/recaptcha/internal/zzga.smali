.class final Lcom/google/android/recaptcha/internal/zzga;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzgo;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzalk;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzalk;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzga;->zzb:Lcom/google/android/recaptcha/internal/zzgo;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzga;->zzc:Lcom/google/android/recaptcha/internal/zzalk;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance p1, Lcom/google/android/recaptcha/internal/zzga;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzga;->zzb:Lcom/google/android/recaptcha/internal/zzgo;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzga;->zzc:Lcom/google/android/recaptcha/internal/zzalk;

    invoke-direct {p1, v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzga;-><init>(Lcom/google/android/recaptcha/internal/zzgo;Lcom/google/android/recaptcha/internal/zzalk;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzga;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzga;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzga;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 7

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzga;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzga;->zzb:Lcom/google/android/recaptcha/internal/zzgo;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzgo;->zzo(Lcom/google/android/recaptcha/internal/zzgo;)Lcom/google/android/recaptcha/internal/zzmv;

    move-result-object v1

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzgo;->zzp(Lcom/google/android/recaptcha/internal/zzgo;)Lcom/google/android/recaptcha/internal/zzhq;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhq;->zzb()Ljava/lang/String;

    move-result-object p1

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzga;->zzc:Lcom/google/android/recaptcha/internal/zzalk;

    const/4 v3, 0x1

    .line 2
    iput v3, p0, Lcom/google/android/recaptcha/internal/zzga;->zza:I

    invoke-interface {v1, p1, v2, p0}, Lcom/google/android/recaptcha/internal/zzmv;->zza(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzalk;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_1

    return-object v0

    .line 1
    :cond_1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzalm;

    .line 3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzalm;->zza()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzr()Z

    move-result p0

    if-nez p0, :cond_2

    return-object p1

    .line 4
    :cond_2
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzah:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0
.end method
