.class final Lcom/google/android/recaptcha/internal/zzom;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzakg;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzon;

.field private synthetic zzd:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zzon;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzom;->zzb:Lcom/google/android/recaptcha/internal/zzakg;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzom;->zzc:Lcom/google/android/recaptcha/internal/zzon;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzom;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzom;->zzb:Lcom/google/android/recaptcha/internal/zzakg;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzom;->zzc:Lcom/google/android/recaptcha/internal/zzon;

    invoke-direct {v0, v1, p0, p2}, Lcom/google/android/recaptcha/internal/zzom;-><init>(Lcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zzon;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzom;->zzd:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzom;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzom;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzom;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 5

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzom;->zza:I

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    .line 14
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzom;->zzd:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzom;->zzd:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzom;->zzb:Lcom/google/android/recaptcha/internal/zzakg;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaka;->zzg()Lcom/google/android/recaptcha/internal/zzajz;

    move-result-object v3

    .line 3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzakg;->zzd()Lcom/google/android/recaptcha/internal/zzakk;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzakk;->zzb()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zzajz;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajz;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzakg;->zzg()Lcom/google/android/recaptcha/internal/zzake;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzake;->zza()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v4

    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zzajz;->zze(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzajz;

    .line 5
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzakg;->zzd()Lcom/google/android/recaptcha/internal/zzakk;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzakk;->zzd()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v4

    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zzajz;->zzb(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzajz;

    .line 6
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzakg;->zzd()Lcom/google/android/recaptcha/internal/zzakk;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzakk;->zze()Ljava/lang/String;

    move-result-object v4

    .line 7
    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zzajz;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajz;

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzakg;->zzd()Lcom/google/android/recaptcha/internal/zzakk;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzakk;->zzg()Ljava/lang/String;

    move-result-object p1

    .line 9
    invoke-virtual {v3, p1}, Lcom/google/android/recaptcha/internal/zzajz;->zzd(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajz;

    .line 10
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    .line 11
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaka;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzom;->zzc:Lcom/google/android/recaptcha/internal/zzon;

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzon;->zzb()Lcom/google/android/recaptcha/internal/zzng;

    move-result-object v3

    .line 12
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzom;->zzd:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzom;->zza:I

    .line 13
    invoke-static {v3, p1, p0}, Lcom/google/android/recaptcha/internal/zzoa;->zze(Lcom/google/android/recaptcha/internal/zzoa;Lcom/google/android/recaptcha/internal/zzagx;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_3

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    const/4 v2, 0x0

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzom;->zzd:Ljava/lang/Object;

    const/4 v2, 0x2

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzom;->zza:I

    .line 14
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_2

    goto :goto_2

    .line 15
    :cond_2
    :goto_1
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :cond_3
    :goto_2
    return-object v0
.end method
