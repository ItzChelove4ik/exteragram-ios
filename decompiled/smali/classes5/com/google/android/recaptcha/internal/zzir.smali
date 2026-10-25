.class final Lcom/google/android/recaptcha/internal/zzir;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zziv;

.field final synthetic zzd:J

.field private synthetic zze:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zziv;JLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzir;->zzc:Lcom/google/android/recaptcha/internal/zziv;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzir;->zzd:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 4

    new-instance v0, Lcom/google/android/recaptcha/internal/zzir;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzir;->zzc:Lcom/google/android/recaptcha/internal/zziv;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzir;->zzd:J

    invoke-direct {v0, v1, v2, v3, p2}, Lcom/google/android/recaptcha/internal/zzir;-><init>(Lcom/google/android/recaptcha/internal/zziv;JLkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzir;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzir;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzir;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 11

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzir;->zzb:I

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

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/recaptcha/internal/zzakg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object p0

    .line 8
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzir;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzakg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_4

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_3

    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzir;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_2

    :cond_3
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_4
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzir;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    check-cast v6, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_5
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzir;->zzc:Lcom/google/android/recaptcha/internal/zziv;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zziv;->zza(Lcom/google/android/recaptcha/internal/zziv;)Lcom/google/android/recaptcha/internal/zzif;

    move-result-object p1

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzir;->zza:Ljava/lang/Object;

    iput v6, p0, Lcom/google/android/recaptcha/internal/zzir;->zzb:I

    .line 3
    new-instance v6, Lcom/google/android/recaptcha/internal/zzie;

    invoke-direct {v6, p1, v7}, Lcom/google/android/recaptcha/internal/zzie;-><init>(Lcom/google/android/recaptcha/internal/zzif;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v6}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_7

    move-object v6, v1

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v6, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    iput-object v7, p0, Lcom/google/android/recaptcha/internal/zzir;->zza:Ljava/lang/Object;

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzir;->zzb:I

    .line 4
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_7

    move-object v1, v6

    :goto_1
    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzir;->zzc:Lcom/google/android/recaptcha/internal/zziv;

    iget-wide v8, p0, Lcom/google/android/recaptcha/internal/zzir;->zzd:J

    .line 1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzamf;

    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zziv;->zzb(Lcom/google/android/recaptcha/internal/zziv;)Lcom/google/android/recaptcha/internal/zzkx;

    move-result-object v5

    .line 5
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzir;->zza:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzir;->zzb:I

    invoke-interface {v5, p1, v8, v9, p0}, Lcom/google/android/recaptcha/internal/zzkx;->zza(Lcom/google/android/recaptcha/internal/zzamf;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_7

    move-object v4, v1

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    iput-object v7, p0, Lcom/google/android/recaptcha/internal/zzir;->zza:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzir;->zzb:I

    .line 6
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_7

    move-object v1, v4

    .line 1
    :goto_3
    check-cast p1, Lcom/google/android/recaptcha/internal/zzakg;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzakg;->zzl()Z

    move-result v3

    if-eqz v3, :cond_6

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzir;->zzc:Lcom/google/android/recaptcha/internal/zziv;

    .line 8
    new-instance v4, Lcom/google/android/recaptcha/internal/zziq;

    invoke-direct {v4, v3, v7}, Lcom/google/android/recaptcha/internal/zziq;-><init>(Lcom/google/android/recaptcha/internal/zziv;Lkotlin/coroutines/Continuation;)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzir;->zza:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzir;->zzb:I

    new-instance v2, Lcom/google/android/recaptcha/internal/zzmb;

    const/16 v3, 0x58

    invoke-direct {v2, v3, v4, v7}, Lcom/google/android/recaptcha/internal/zzmb;-><init>(ILkotlin/jvm/functions/Function2;Ljava/lang/Integer;)V

    if-eq v2, v0, :cond_7

    move-object v10, v2

    move-object v2, p1

    move-object p1, v10

    .line 1
    :goto_4
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzir;->zze:Ljava/lang/Object;

    iput-object v7, p0, Lcom/google/android/recaptcha/internal/zzir;->zza:Ljava/lang/Object;

    const/4 v3, 0x6

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzir;->zzb:I

    .line 8
    invoke-virtual {v1, p1, p0}, Lcom/google/android/recaptcha/internal/zzmg;->zzc(Lcom/google/android/recaptcha/internal/zzmb;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-eq p0, v0, :cond_7

    return-object v2

    :cond_6
    return-object p1

    :cond_7
    return-object v0
.end method
