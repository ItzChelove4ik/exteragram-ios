.class public final Lcom/google/android/recaptcha/internal/zzmq;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/String;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzmx;


# direct methods
.method public constructor <init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzmx;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-string p1, "https://www.gstatic.com/recaptcha/verify_key/orcas/prod/android/verify_key.txt"

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzmq;->zza:Ljava/lang/String;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzmq;->zzb:Lcom/google/android/recaptcha/internal/zzmx;

    return-void
.end method


# virtual methods
.method public final zza(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 8

    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzmp;

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzmp;

    .line 1
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzmp;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzmp;->zzc:I

    :goto_0
    move-object v5, v0

    goto :goto_1

    .line 7
    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzmp;

    invoke-direct {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzmp;-><init>(Lcom/google/android/recaptcha/internal/zzmq;Lkotlin/coroutines/Continuation;)V

    goto :goto_0

    .line 1
    :goto_1
    iget-object p1, v5, Lcom/google/android/recaptcha/internal/zzmp;->zza:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, v5, Lcom/google/android/recaptcha/internal/zzmp;->zzc:I

    const/4 v2, 0x1

    if-eqz v1, :cond_2

    if-ne v1, v2, :cond_1

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    .line 5
    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    .line 1
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :try_start_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzmq;->zzb:Lcom/google/android/recaptcha/internal/zzmx;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzmq;->zza:Ljava/lang/String;

    iput v2, v5, Lcom/google/android/recaptcha/internal/zzmp;->zzc:I

    const/4 v6, 0x6

    const/4 v7, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    move-object v2, p0

    .line 2
    invoke-static/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzmx;->zzc(Lcom/google/android/recaptcha/internal/zzmx;Ljava/lang/String;[BLjava/util/Map;Lkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_3

    return-object v0

    .line 1
    :cond_3
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zznb;

    .line 3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zznb;->zzb()V

    new-instance p0, Ljava/lang/String;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zznb;->zza()[B

    move-result-object p1

    sget-object v0, Lkotlin/text/Charsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {p0, p1, v0}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    return-object p0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 5
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzap:Lcom/google/android/recaptcha/internal/zzhe;

    .line 6
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 5
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0

    :catch_1
    move-exception v0

    move-object p0, v0

    .line 7
    throw p0
.end method
