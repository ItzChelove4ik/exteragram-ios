.class final Lcom/google/android/recaptcha/internal/zzdf;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzcz;

.field final synthetic zzd:Ljava/lang/String;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzdo;

.field private synthetic zzg:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzcz;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzdo;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzc:Lcom/google/android/recaptcha/internal/zzcz;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzd:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzdf;->zze:Lcom/google/android/recaptcha/internal/zzdo;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 3

    new-instance v0, Lcom/google/android/recaptcha/internal/zzdf;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzc:Lcom/google/android/recaptcha/internal/zzcz;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzd:Ljava/lang/String;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzdf;->zze:Lcom/google/android/recaptcha/internal/zzdo;

    invoke-direct {v0, v1, v2, p0, p2}, Lcom/google/android/recaptcha/internal/zzdf;-><init>(Lcom/google/android/recaptcha/internal/zzcz;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzdo;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzdf;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzdf;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzdf;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 14

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzb:I

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

    .line 18
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_4

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_3

    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_2

    :cond_3
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_4
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    check-cast v6, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_5
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzc:Lcom/google/android/recaptcha/internal/zzcz;

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zza:Ljava/lang/Object;

    iput v6, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzb:I

    .line 3
    new-instance v6, Lcom/google/android/recaptcha/internal/zzcy;

    iget-object v8, p1, Lcom/google/android/recaptcha/internal/zzcz;->zza:Lcom/google/android/recaptcha/internal/zzdo;

    invoke-direct {v6, p1, v8, v7}, Lcom/google/android/recaptcha/internal/zzcy;-><init>(Lcom/google/android/recaptcha/internal/zzcz;Lcom/google/android/recaptcha/internal/zzdo;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v6}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_9

    move-object v6, v1

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v6, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    iput-object v7, p0, Lcom/google/android/recaptcha/internal/zzdf;->zza:Ljava/lang/Object;

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzb:I

    .line 4
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_9

    move-object v1, v6

    .line 1
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaku;

    .line 5
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object v5

    check-cast v5, Lcom/google/android/recaptcha/internal/zzakt;

    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzakt;->zzb()Lcom/google/android/recaptcha/internal/zzakt;

    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v5

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaku;->zzb()Lcom/google/android/recaptcha/internal/zzaku;

    move-result-object v6

    .line 6
    invoke-static {v5, v6}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_6

    .line 7
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 8
    :cond_6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzalg;->zza()Lcom/google/android/recaptcha/internal/zzalf;

    move-result-object v5

    .line 9
    invoke-virtual {v5, p1}, Lcom/google/android/recaptcha/internal/zzalf;->zzc(Lcom/google/android/recaptcha/internal/zzaku;)Lcom/google/android/recaptcha/internal/zzalf;

    .line 10
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzmd;->zza()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v5, p1}, Lcom/google/android/recaptcha/internal/zzalf;->zzb(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalf;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzd:Ljava/lang/String;

    .line 11
    invoke-virtual {v5, p1}, Lcom/google/android/recaptcha/internal/zzalf;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalf;

    .line 12
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    .line 13
    move-object v12, p1

    check-cast v12, Lcom/google/android/recaptcha/internal/zzalg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zze:Lcom/google/android/recaptcha/internal/zzdo;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzdo;->zzi(Lcom/google/android/recaptcha/internal/zzdo;)Lcom/google/android/recaptcha/internal/zzdv;

    move-result-object v9

    .line 14
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zza:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzb:I

    .line 15
    new-instance v8, Lcom/google/android/recaptcha/internal/zzdu;

    const-wide/16 v10, 0x2710

    const/4 v13, 0x0

    invoke-direct/range {v8 .. v13}, Lcom/google/android/recaptcha/internal/zzdu;-><init>(Lcom/google/android/recaptcha/internal/zzdv;JLcom/google/android/recaptcha/internal/zzalg;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmb;

    const/16 v4, 0x3f

    invoke-direct {p1, v4, v8, v7}, Lcom/google/android/recaptcha/internal/zzmb;-><init>(ILkotlin/jvm/functions/Function2;Ljava/lang/Integer;)V

    if-eq p1, v0, :cond_9

    move-object v4, v1

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    iput-object v7, p0, Lcom/google/android/recaptcha/internal/zzdf;->zza:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzb:I

    .line 16
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_9

    move-object v1, v4

    :goto_3
    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzdf;->zze:Lcom/google/android/recaptcha/internal/zzdo;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzc:Lcom/google/android/recaptcha/internal/zzcz;

    .line 1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzali;

    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzdo;->zzj(Lcom/google/android/recaptcha/internal/zzdo;)Lcom/google/android/recaptcha/internal/zzng;

    move-result-object v3

    new-instance v5, Lcom/google/android/recaptcha/internal/zzde;

    invoke-direct {v5, v4, p1}, Lcom/google/android/recaptcha/internal/zzde;-><init>(Lcom/google/android/recaptcha/internal/zzcz;Lcom/google/android/recaptcha/internal/zzali;)V

    .line 17
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzb:I

    invoke-virtual {v3, v5, p0}, Lcom/google/android/recaptcha/internal/zzoa;->zzf(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_9

    .line 1
    :goto_4
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v7, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzg:Ljava/lang/Object;

    const/4 v2, 0x6

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzdf;->zzb:I

    .line 18
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_7

    goto :goto_6

    .line 19
    :cond_7
    :goto_5
    check-cast p1, Ljava/lang/Boolean;

    .line 20
    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    if-eqz p0, :cond_8

    .line 22
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 21
    :cond_8
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzaw:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0

    :cond_9
    :goto_6
    return-object v0
.end method
