.class final Lcom/google/android/recaptcha/internal/zzol;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Ljava/lang/String;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzaer;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzaer;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzon;

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzon;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzol;->zzb:Ljava/lang/String;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzol;->zzc:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzol;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzol;->zze:Lcom/google/android/recaptcha/internal/zzon;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    new-instance v0, Lcom/google/android/recaptcha/internal/zzol;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzol;->zzb:Ljava/lang/String;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzol;->zzc:Lcom/google/android/recaptcha/internal/zzaer;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzol;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzol;->zze:Lcom/google/android/recaptcha/internal/zzon;

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzol;-><init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzon;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzol;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzol;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzol;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzol;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 4

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzol;->zza:I

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    .line 10
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzol;->zzf:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzol;->zzf:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzol;->zzb:Ljava/lang/String;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaka;->zzg()Lcom/google/android/recaptcha/internal/zzajz;

    move-result-object v3

    .line 3
    invoke-virtual {v3, p1}, Lcom/google/android/recaptcha/internal/zzajz;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajz;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzol;->zzc:Lcom/google/android/recaptcha/internal/zzaer;

    .line 4
    invoke-virtual {v3, p1}, Lcom/google/android/recaptcha/internal/zzajz;->zze(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzajz;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzol;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    .line 5
    invoke-virtual {v3, p1}, Lcom/google/android/recaptcha/internal/zzajz;->zzb(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzajz;

    .line 6
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    .line 7
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaka;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzol;->zze:Lcom/google/android/recaptcha/internal/zzon;

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzon;->zzb()Lcom/google/android/recaptcha/internal/zzng;

    move-result-object v3

    .line 8
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzol;->zzf:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzol;->zza:I

    .line 9
    invoke-static {v3, p1, p0}, Lcom/google/android/recaptcha/internal/zzoa;->zzg(Lcom/google/android/recaptcha/internal/zzoa;Lcom/google/android/recaptcha/internal/zzagx;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_3

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    const/4 v2, 0x0

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzol;->zzf:Ljava/lang/Object;

    const/4 v2, 0x2

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzol;->zza:I

    .line 10
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_2

    goto :goto_2

    .line 1
    :cond_2
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaka;

    return-object p1

    :cond_3
    :goto_2
    return-object v0
.end method
