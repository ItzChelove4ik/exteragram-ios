.class final Lcom/google/android/recaptcha/internal/zzjr;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzjt;

.field final synthetic zzd:J

.field final synthetic zze:Ljava/lang/String;

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zziw;

.field final synthetic zzg:Lcom/google/android/recaptcha/internal/zzmd;

.field private synthetic zzh:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzjt;JLjava/lang/String;Lcom/google/android/recaptcha/internal/zziw;Lcom/google/android/recaptcha/internal/zzmd;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzc:Lcom/google/android/recaptcha/internal/zzjt;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzd:J

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzjr;->zze:Ljava/lang/String;

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzf:Lcom/google/android/recaptcha/internal/zziw;

    iput-object p6, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzg:Lcom/google/android/recaptcha/internal/zzmd;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p7}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 8

    new-instance v0, Lcom/google/android/recaptcha/internal/zzjr;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzc:Lcom/google/android/recaptcha/internal/zzjt;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzd:J

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzjr;->zze:Ljava/lang/String;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzf:Lcom/google/android/recaptcha/internal/zziw;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzg:Lcom/google/android/recaptcha/internal/zzmd;

    move-object v7, p2

    invoke-direct/range {v0 .. v7}, Lcom/google/android/recaptcha/internal/zzjr;-><init>(Lcom/google/android/recaptcha/internal/zzjt;JLjava/lang/String;Lcom/google/android/recaptcha/internal/zziw;Lcom/google/android/recaptcha/internal/zzmd;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzjr;->zzh:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzjr;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzjr;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzjr;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 10

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzb:I

    const/4 v2, 0x0

    const/4 v3, 0x3

    const/4 v4, 0x2

    const/4 v5, 0x1

    if-eqz v1, :cond_3

    if-eq v1, v5, :cond_2

    if-eq v1, v4, :cond_1

    if-eq v1, v3, :cond_0

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzh:Ljava/lang/Object;

    check-cast v0, Lcom/google/android/recaptcha/internal/zzkn;

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_3

    .line 7
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzh:Ljava/lang/Object;

    check-cast v3, Lcom/google/android/recaptcha/internal/zzkn;

    :try_start_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_2

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzh:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_1
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_1

    .line 9
    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzh:Ljava/lang/Object;

    check-cast v5, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_3
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_3 .. :try_end_3} :catch_1
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_0

    goto :goto_0

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzh:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    :try_start_4
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzc:Lcom/google/android/recaptcha/internal/zzjt;

    iget-wide v6, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzd:J

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzjr;->zze:Ljava/lang/String;

    .line 2
    invoke-static {p1, v6, v7, v8}, Lcom/google/android/recaptcha/internal/zzjt;->zze(Lcom/google/android/recaptcha/internal/zzjt;JLjava/lang/String;)V

    .line 3
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzjt;->zzc(Lcom/google/android/recaptcha/internal/zzjt;)Lcom/google/android/recaptcha/internal/zzko;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzko;->zzd()Lcom/google/android/recaptcha/internal/zzgy;

    move-result-object p1

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzh:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zza:Ljava/lang/Object;

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzb:I

    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzgy;->zzb(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_5

    move-object v5, v1

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzh:Ljava/lang/Object;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzjr;->zza:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzb:I

    .line 4
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_5

    move-object v1, v5

    :goto_1
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzc:Lcom/google/android/recaptcha/internal/zzjt;

    .line 5
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzjt;->zzc(Lcom/google/android/recaptcha/internal/zzjt;)Lcom/google/android/recaptcha/internal/zzko;

    move-result-object p1

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzjr;->zze:Ljava/lang/String;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzf:Lcom/google/android/recaptcha/internal/zziw;

    invoke-virtual {p1, v4, v5}, Lcom/google/android/recaptcha/internal/zzko;->zzc(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zziw;)Lcom/google/android/recaptcha/internal/zzkn;

    move-result-object p1

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzd:J

    .line 6
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzh:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zza:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzb:I

    invoke-interface {p1, v4, v5, p0}, Lcom/google/android/recaptcha/internal/zzkn;->zzb(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v3

    if-eq v3, v0, :cond_5

    move-object v9, v3

    move-object v3, p1

    move-object p1, v9

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzh:Ljava/lang/Object;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzjr;->zza:Ljava/lang/Object;

    const/4 v2, 0x4

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzb:I

    .line 7
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_4

    goto :goto_4

    :cond_4
    move-object v0, v3

    .line 1
    :goto_3
    new-instance p1, Lcom/google/android/recaptcha/internal/zzjj;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjr;->zze:Ljava/lang/String;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzg:Lcom/google/android/recaptcha/internal/zzmd;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzjr;->zzc:Lcom/google/android/recaptcha/internal/zzjt;

    .line 8
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzjt;->zzc(Lcom/google/android/recaptcha/internal/zzjt;)Lcom/google/android/recaptcha/internal/zzko;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzko;->zze()Lcom/google/android/recaptcha/internal/zzhu;

    move-result-object v3

    invoke-direct {p1, v0, v1, v2, v3}, Lcom/google/android/recaptcha/internal/zzjj;-><init>(Lcom/google/android/recaptcha/internal/zzkn;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzmd;Lcom/google/android/recaptcha/internal/zzhu;)V

    .line 9
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzjt;->zzf(Lcom/google/android/recaptcha/internal/zzjt;Lcom/google/android/recaptcha/internal/zzjj;)V
    :try_end_4
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_4 .. :try_end_4} :catch_1
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    return-object p1

    :cond_5
    :goto_4
    return-object v0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 10
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zza:Lcom/google/android/recaptcha/internal/zzhe;

    .line 11
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 12
    throw v0

    :catch_1
    move-exception v0

    move-object p0, v0

    .line 13
    throw p0
.end method
