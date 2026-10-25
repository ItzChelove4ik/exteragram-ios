.class public final Lcom/google/android/recaptcha/internal/zzlx;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzlr;


# static fields
.field private static zzb:Ljava/util/Timer;


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzlq;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzlz;

.field private final zzd:Lcom/google/android/recaptcha/internal/zzhu;


# direct methods
.method public constructor <init>(Landroid/content/Context;Lcom/google/android/recaptcha/internal/zzlz;Lcom/google/android/recaptcha/internal/zzhu;Lcom/google/android/recaptcha/internal/zzlq;)V
    .locals 6

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzlx;->zzc:Lcom/google/android/recaptcha/internal/zzlz;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzlx;->zzd:Lcom/google/android/recaptcha/internal/zzhu;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzlx;->zza:Lcom/google/android/recaptcha/internal/zzlq;

    sget-object p1, Lcom/google/android/recaptcha/internal/zzlx;->zzb:Ljava/util/Timer;

    if-nez p1, :cond_0

    new-instance v0, Ljava/util/Timer;

    invoke-direct {v0}, Ljava/util/Timer;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzlx;->zzb:Ljava/util/Timer;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzlu;

    .line 2
    invoke-direct {v1, p0}, Lcom/google/android/recaptcha/internal/zzlu;-><init>(Lcom/google/android/recaptcha/internal/zzlx;)V

    const-wide/16 v2, 0x7530

    move-wide v4, v2

    invoke-virtual/range {v0 .. v5}, Ljava/util/Timer;->schedule(Ljava/util/TimerTask;JJ)V

    :cond_0
    return-void
.end method

.method public static synthetic zzb(Lcom/google/android/recaptcha/internal/zzlx;IILjava/lang/Object;)V
    .locals 0

    const/16 p1, 0x14

    .line 1
    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzlx;->zzg(I)V

    return-void
.end method

.method public static final synthetic zzc(Lcom/google/android/recaptcha/internal/zzlx;)Lcom/google/android/recaptcha/internal/zzlq;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlx;->zza:Lcom/google/android/recaptcha/internal/zzlq;

    return-object p0
.end method

.method public static final synthetic zzd(Lcom/google/android/recaptcha/internal/zzlx;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzlx;->zzh(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zze(Lcom/google/android/recaptcha/internal/zzlx;I)V
    .locals 0

    const/4 p1, 0x0

    .line 1
    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzlx;->zzg(I)V

    return-void
.end method

.method public static final synthetic zzf(Lcom/google/android/recaptcha/internal/zzlx;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    const/4 p1, 0x0

    .line 1
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzlx;->zzi(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method private final zzg(I)V
    .locals 7

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzlx;->zzd:Lcom/google/android/recaptcha/internal/zzhu;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzhu;->zzf()Lkotlinx/coroutines/CoroutineScope;

    move-result-object v1

    new-instance v4, Lcom/google/android/recaptcha/internal/zzlw;

    const/4 v0, 0x0

    invoke-direct {v4, p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzlw;-><init>(Lcom/google/android/recaptcha/internal/zzlx;ILkotlin/coroutines/Continuation;)V

    const/4 v5, 0x3

    const/4 v6, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-static/range {v1 .. v6}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-void
.end method

.method private final zzh(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 4

    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzls;

    if-eqz v0, :cond_0

    move-object v0, p2

    check-cast v0, Lcom/google/android/recaptcha/internal/zzls;

    .line 1
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzls;->zzd:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzls;->zzd:I

    goto :goto_0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzls;

    invoke-direct {v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzls;-><init>(Lcom/google/android/recaptcha/internal/zzlx;Lkotlin/coroutines/Continuation;)V

    :goto_0
    iget-object p2, v0, Lcom/google/android/recaptcha/internal/zzls;->zzb:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzls;->zzd:I

    const/4 v3, 0x1

    if-eqz v2, :cond_2

    if-ne v2, v3, :cond_1

    .line 6
    iget-object p1, v0, Lcom/google/android/recaptcha/internal/zzls;->zza:Ljava/lang/Object;

    .line 1
    check-cast p1, Ljava/util/Iterator;

    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    check-cast p1, Ljava/lang/Iterable;

    const/16 p2, 0x14

    .line 3
    invoke-static {p1, p2, p2, v3}, Lkotlin/collections/CollectionsKt;->windowed(Ljava/lang/Iterable;IIZ)Ljava/util/List;

    move-result-object p1

    .line 4
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_3
    :goto_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result p2

    if-eqz p2, :cond_4

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/util/List;

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzls;->zza:Ljava/lang/Object;

    iput v3, v0, Lcom/google/android/recaptcha/internal/zzls;->zzd:I

    .line 5
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzlx;->zzi(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p2

    if-ne p2, v1, :cond_3

    return-object v1

    .line 6
    :cond_4
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method private final zzi(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 9

    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzlt;

    if-eqz v0, :cond_0

    move-object v0, p2

    check-cast v0, Lcom/google/android/recaptcha/internal/zzlt;

    .line 1
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzlt;->zzd:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzlt;->zzd:I

    goto :goto_0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzlt;

    invoke-direct {v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzlt;-><init>(Lcom/google/android/recaptcha/internal/zzlx;Lkotlin/coroutines/Continuation;)V

    :goto_0
    iget-object p2, v0, Lcom/google/android/recaptcha/internal/zzlt;->zzb:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzlt;->zzd:I

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-eqz v2, :cond_2

    if-ne v2, v4, :cond_1

    .line 17
    iget-object p1, v0, Lcom/google/android/recaptcha/internal/zzlt;->zza:Ljava/lang/Object;

    .line 1
    check-cast p1, Ljava/util/List;

    :try_start_0
    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_2

    goto/16 :goto_3

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v3

    :cond_2
    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzajl;->zzc()Lcom/google/android/recaptcha/internal/zzajk;

    move-result-object p2

    new-instance v2, Ljava/util/ArrayList;

    .line 3
    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 4
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :catch_0
    :goto_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_7

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/google/android/recaptcha/internal/zzlo;

    .line 5
    :try_start_1
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzlo;->zza()Ljava/lang/String;

    move-result-object v6

    .line 6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzrv;->zzi()Lcom/google/android/recaptcha/internal/zzrv;

    move-result-object v7

    invoke-virtual {v7, v6}, Lcom/google/android/recaptcha/internal/zzrv;->zzh(Ljava/lang/CharSequence;)[B

    move-result-object v6

    .line 5
    invoke-static {v6}, Lcom/google/android/recaptcha/internal/zzamk;->zzc([B)Lcom/google/android/recaptcha/internal/zzamk;

    move-result-object v6
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    .line 7
    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzamk;->zzh()I

    move-result v7

    add-int/lit8 v8, v7, -0x1

    if-eqz v7, :cond_6

    if-eqz v8, :cond_5

    if-eq v8, v4, :cond_4

    const/4 v6, 0x2

    if-ne v8, v6, :cond_3

    goto :goto_2

    :cond_3
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return-object v3

    .line 9
    :cond_4
    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzamk;->zzb()Lcom/google/android/recaptcha/internal/zzajt;

    move-result-object v6

    invoke-virtual {p2, v6}, Lcom/google/android/recaptcha/internal/zzajk;->zzd(Lcom/google/android/recaptcha/internal/zzajt;)Lcom/google/android/recaptcha/internal/zzajk;

    goto :goto_2

    .line 8
    :cond_5
    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzamk;->zza()Lcom/google/android/recaptcha/internal/zzajj;

    move-result-object v6

    invoke-virtual {p2, v6}, Lcom/google/android/recaptcha/internal/zzajk;->zzb(Lcom/google/android/recaptcha/internal/zzajj;)Lcom/google/android/recaptcha/internal/zzajk;

    .line 10
    :goto_2
    invoke-interface {v2, v5}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 7
    :cond_6
    throw v3

    .line 11
    :cond_7
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzajk;->zza()I

    move-result p1

    .line 12
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzajk;->zzc()I

    move-result v3

    add-int/2addr p1, v3

    if-nez p1, :cond_8

    .line 13
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :cond_8
    :try_start_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzlx;->zzc:Lcom/google/android/recaptcha/internal/zzlz;

    .line 14
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p2

    check-cast p2, Lcom/google/android/recaptcha/internal/zzajl;

    iput-object v2, v0, Lcom/google/android/recaptcha/internal/zzlt;->zza:Ljava/lang/Object;

    iput v4, v0, Lcom/google/android/recaptcha/internal/zzlt;->zzd:I

    invoke-virtual {p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzlz;->zza(Lcom/google/android/recaptcha/internal/zzajl;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p2
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1

    if-eq p2, v1, :cond_9

    move-object p1, v2

    :goto_3
    :try_start_3
    check-cast p2, Ljava/lang/Boolean;

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-eqz p2, :cond_a

    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzlx;->zza:Lcom/google/android/recaptcha/internal/zzlq;

    .line 15
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzlq;->zzd(Ljava/util/List;)V
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_2

    goto :goto_4

    :cond_9
    return-object v1

    :catch_1
    move-object p1, v2

    .line 7
    :catch_2
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlx;->zza:Lcom/google/android/recaptcha/internal/zzlq;

    .line 16
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzlq;->zza(Ljava/util/List;)V

    .line 17
    :cond_a
    :goto_4
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzamk;)V
    .locals 7

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzlx;->zzd:Lcom/google/android/recaptcha/internal/zzhu;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzhu;->zzc()Lkotlinx/coroutines/CoroutineScope;

    move-result-object v1

    new-instance v4, Lcom/google/android/recaptcha/internal/zzlv;

    const/4 v0, 0x0

    invoke-direct {v4, p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzlv;-><init>(Lcom/google/android/recaptcha/internal/zzlx;Lcom/google/android/recaptcha/internal/zzamk;Lkotlin/coroutines/Continuation;)V

    const/4 v5, 0x3

    const/4 v6, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-static/range {v1 .. v6}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-void
.end method
