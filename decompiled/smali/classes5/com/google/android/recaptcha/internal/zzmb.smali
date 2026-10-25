.class public final Lcom/google/android/recaptcha/internal/zzmb;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lkotlin/jvm/functions/Function2;

.field private final zzb:Ljava/lang/Integer;

.field private final zzc:I


# direct methods
.method public constructor <init>(ILkotlin/jvm/functions/Function2;Ljava/lang/Integer;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzmb;->zzc:I

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzmb;->zza:Lkotlin/jvm/functions/Function2;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzmb;->zzb:Ljava/lang/Integer;

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 8

    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzma;

    if-eqz v0, :cond_0

    move-object v0, p2

    check-cast v0, Lcom/google/android/recaptcha/internal/zzma;

    .line 1
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzma;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzma;->zzc:I

    goto :goto_0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzma;

    invoke-direct {v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzma;-><init>(Lcom/google/android/recaptcha/internal/zzmb;Lkotlin/coroutines/Continuation;)V

    :goto_0
    iget-object p2, v0, Lcom/google/android/recaptcha/internal/zzma;->zza:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzma;->zzc:I

    const/4 v3, 0x1

    if-eqz v2, :cond_2

    if-ne v2, v3, :cond_1

    .line 4
    iget-object p0, v0, Lcom/google/android/recaptcha/internal/zzma;->zzd:Lcom/google/android/recaptcha/internal/zzlp;

    .line 1
    :try_start_0
    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_2
    .catch Lkotlinx/coroutines/TimeoutCancellationException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_1

    :catch_0
    move-exception v0

    move-object p1, v0

    goto :goto_3

    :catch_1
    move-exception v0

    move-object p1, v0

    goto :goto_5

    :catch_2
    move-exception v0

    move-object p1, v0

    goto/16 :goto_7

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget p2, p0, Lcom/google/android/recaptcha/internal/zzmb;->zzc:I

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzmb;->zzb:Ljava/lang/Integer;

    .line 2
    invoke-virtual {p1, p2, v2}, Lcom/google/android/recaptcha/internal/zzmg;->zzf(ILjava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzlp;

    move-result-object p1

    :try_start_1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzmb;->zza:Lkotlin/jvm/functions/Function2;

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzma;->zzd:Lcom/google/android/recaptcha/internal/zzlp;

    iput v3, v0, Lcom/google/android/recaptcha/internal/zzma;->zzc:I

    .line 3
    invoke-interface {p0, p1, v0}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_5
    .catch Lkotlinx/coroutines/TimeoutCancellationException; {:try_start_1 .. :try_end_1} :catch_4
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_3
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eq p2, v1, :cond_3

    move-object p0, p1

    .line 4
    :goto_1
    :try_start_2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzlp;->zzb()V
    :try_end_2
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_2 .. :try_end_2} :catch_2
    .catch Lkotlinx/coroutines/TimeoutCancellationException; {:try_start_2 .. :try_end_2} :catch_1
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    return-object p2

    :cond_3
    return-object v1

    :catch_3
    move-exception v0

    move-object p0, v0

    goto :goto_2

    :catch_4
    move-exception v0

    move-object p0, v0

    goto :goto_4

    :catch_5
    move-exception v0

    move-object p0, v0

    goto :goto_6

    :catchall_0
    move-exception v0

    move-object p0, v0

    .line 17
    throw p0

    :goto_2
    move-object v7, p1

    move-object p1, p0

    move-object p0, v7

    .line 5
    :goto_3
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zza:Lcom/google/android/recaptcha/internal/zzhe;

    .line 6
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 7
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 8
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzlp;->zzc(Lcom/google/android/recaptcha/internal/zzhg;)V

    .line 9
    throw v0

    :goto_4
    move-object v7, p1

    move-object p1, p0

    move-object p0, v7

    .line 10
    :goto_5
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzb:Lcom/google/android/recaptcha/internal/zzhe;

    .line 11
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 12
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 13
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzlp;->zzc(Lcom/google/android/recaptcha/internal/zzhg;)V

    .line 14
    throw v0

    :goto_6
    move-object v7, p1

    move-object p1, p0

    move-object p0, v7

    .line 15
    :goto_7
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzlp;->zzc(Lcom/google/android/recaptcha/internal/zzhg;)V

    .line 16
    throw p1
.end method
