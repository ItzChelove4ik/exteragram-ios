.class public final Lcom/google/android/recaptcha/internal/zzbh;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzbe;


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzbr;

.field private final zzb:Landroid/content/Context;

.field private final zzc:Ljava/util/concurrent/atomic/AtomicReference;

.field private final zzd:Lcom/google/android/recaptcha/internal/zzhu;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzbr;Lcom/google/android/recaptcha/internal/zzhu;Landroid/content/Context;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzbh;->zza:Lcom/google/android/recaptcha/internal/zzbr;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzbh;->zzd:Lcom/google/android/recaptcha/internal/zzhu;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzbh;->zzb:Landroid/content/Context;

    new-instance p1, Ljava/util/concurrent/atomic/AtomicReference;

    const/4 p2, 0x0

    invoke-direct {p1, p2}, Ljava/util/concurrent/atomic/AtomicReference;-><init>(Ljava/lang/Object;)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzbh;->zzc:Ljava/util/concurrent/atomic/AtomicReference;

    return-void
.end method

.method private final zzb(Lcom/google/android/recaptcha/internal/zzbk;)V
    .locals 8

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzbh;->zzc:Ljava/util/concurrent/atomic/AtomicReference;

    const/4 v1, 0x0

    invoke-static {v0, p1, v1}, Landroidx/media3/exoplayer/mediacodec/AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;->m(Ljava/util/concurrent/atomic/AtomicReference;Ljava/lang/Object;Ljava/lang/Object;)Z

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzbj;->zza()V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbh;->zzd:Lcom/google/android/recaptcha/internal/zzhu;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzhu;->zza()Lkotlinx/coroutines/CoroutineScope;

    move-result-object v2

    .line 3
    new-instance v5, Lcom/google/android/recaptcha/internal/zzbg;

    invoke-direct {v5, p1, v1}, Lcom/google/android/recaptcha/internal/zzbg;-><init>(Lcom/google/android/recaptcha/internal/zzbk;Lkotlin/coroutines/Continuation;)V

    const/4 v6, 0x3

    const/4 v7, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-static/range {v2 .. v7}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzalc;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 7

    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzbf;

    if-eqz v0, :cond_0

    move-object v0, p2

    check-cast v0, Lcom/google/android/recaptcha/internal/zzbf;

    .line 1
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzbf;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzbf;->zzc:I

    goto :goto_0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzbf;

    invoke-direct {v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzbf;-><init>(Lcom/google/android/recaptcha/internal/zzbh;Lkotlin/coroutines/Continuation;)V

    :goto_0
    iget-object p2, v0, Lcom/google/android/recaptcha/internal/zzbf;->zza:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzbf;->zzc:I

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-eqz v2, :cond_2

    if-ne v2, v4, :cond_1

    .line 11
    iget-object p1, v0, Lcom/google/android/recaptcha/internal/zzbf;->zzd:Lcom/google/android/recaptcha/internal/zzbp;

    .line 1
    :try_start_0
    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_1

    :catchall_0
    move-exception v0

    move-object p2, v0

    goto :goto_2

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v3

    :cond_2
    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzbh;->zza:Lcom/google/android/recaptcha/internal/zzbr;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzbh;->zzd:Lcom/google/android/recaptcha/internal/zzhu;

    new-instance v5, Lcom/google/android/recaptcha/internal/zzbp;

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzhu;->zza()Lkotlinx/coroutines/CoroutineScope;

    move-result-object v2

    .line 2
    invoke-direct {v5, p1, p2, v2}, Lcom/google/android/recaptcha/internal/zzbp;-><init>(Lcom/google/android/recaptcha/internal/zzalc;Lcom/google/android/recaptcha/internal/zzbr;Lkotlinx/coroutines/CoroutineScope;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzbh;->zzc:Ljava/util/concurrent/atomic/AtomicReference;

    .line 3
    invoke-static {p1, v3, v5}, Landroidx/media3/exoplayer/mediacodec/AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;->m(Ljava/util/concurrent/atomic/AtomicReference;Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_4

    .line 5
    :try_start_1
    iput-object v5, v0, Lcom/google/android/recaptcha/internal/zzbf;->zzd:Lcom/google/android/recaptcha/internal/zzbp;

    iput v4, v0, Lcom/google/android/recaptcha/internal/zzbf;->zzc:I

    .line 6
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzbj;->zzc(Lcom/google/android/recaptcha/internal/zzbk;)V

    new-instance p1, Landroid/content/Intent;

    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzbh;->zzb:Landroid/content/Context;

    const-class v2, Lcom/google/android/recaptcha/internal/zzca;

    .line 7
    invoke-direct {p1, p2, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const/high16 v2, 0x10000000

    .line 8
    invoke-virtual {p1, v2}, Landroid/content/Intent;->setFlags(I)Landroid/content/Intent;

    .line 9
    invoke-virtual {p2, p1}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V

    .line 10
    invoke-interface {v5, v0}, Lcom/google/android/recaptcha/internal/zzbk;->zza(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    if-eq p2, v1, :cond_3

    move-object p1, v5

    .line 11
    :goto_1
    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzbh;->zzb(Lcom/google/android/recaptcha/internal/zzbk;)V

    return-object p2

    :cond_3
    return-object v1

    :catchall_1
    move-exception v0

    move-object p1, v0

    move-object p2, p1

    move-object p1, v5

    :goto_2
    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzbh;->zzb(Lcom/google/android/recaptcha/internal/zzbk;)V

    throw p2

    .line 4
    :cond_4
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzD:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0
.end method
