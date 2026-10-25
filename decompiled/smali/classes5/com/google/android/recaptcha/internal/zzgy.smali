.class public final Lcom/google/android/recaptcha/internal/zzgy;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzne;

.field private zzb:Lkotlinx/coroutines/Deferred;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzmq;

.field private final zzd:Lcom/google/android/recaptcha/internal/zzhu;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzmq;Lcom/google/android/recaptcha/internal/zzne;Lcom/google/android/recaptcha/internal/zzhu;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgy;->zzc:Lcom/google/android/recaptcha/internal/zzmq;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzgy;->zza:Lcom/google/android/recaptcha/internal/zzne;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzgy;->zzd:Lcom/google/android/recaptcha/internal/zzhu;

    return-void
.end method

.method public static final synthetic zzd(Lcom/google/android/recaptcha/internal/zzgy;)Lkotlinx/coroutines/Deferred;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgy;->zzb:Lkotlinx/coroutines/Deferred;

    return-object p0
.end method

.method public static final synthetic zze(Lcom/google/android/recaptcha/internal/zzgy;Lkotlinx/coroutines/Deferred;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgy;->zzb:Lkotlinx/coroutines/Deferred;

    return-void
.end method


# virtual methods
.method public final zza()Lcom/google/android/recaptcha/internal/zzne;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgy;->zza:Lcom/google/android/recaptcha/internal/zzne;

    return-object p0
.end method

.method public final zzb(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 1

    .line 1
    new-instance p1, Lcom/google/android/recaptcha/internal/zzgt;

    const/4 v0, 0x0

    invoke-direct {p1, p0, v0}, Lcom/google/android/recaptcha/internal/zzgt;-><init>(Lcom/google/android/recaptcha/internal/zzgy;Lkotlin/coroutines/Continuation;)V

    new-instance p0, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    return-object p0
.end method

.method public final zzc(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 6

    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzgq;

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzgq;

    .line 1
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzgq;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzgq;->zzc:I

    goto :goto_0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzgq;

    invoke-direct {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzgq;-><init>(Lcom/google/android/recaptcha/internal/zzgy;Lkotlin/coroutines/Continuation;)V

    :goto_0
    iget-object p1, v0, Lcom/google/android/recaptcha/internal/zzgq;->zza:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzgq;->zzc:I

    const/4 v3, 0x2

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz v2, :cond_3

    if-eq v2, v4, :cond_2

    if-ne v2, v3, :cond_1

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_2

    .line 3
    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    .line 1
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v5

    :cond_2
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgy;->zza:Lcom/google/android/recaptcha/internal/zzne;

    iput v4, v0, Lcom/google/android/recaptcha/internal/zzgq;->zzc:I

    const-string v2, "ovk"

    .line 2
    invoke-interface {p1, v2, v0}, Lcom/google/android/recaptcha/internal/zzoo;->zzc(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_8

    .line 1
    :goto_1
    check-cast p1, Ljava/lang/String;

    if-eqz p1, :cond_4

    return-object p1

    :cond_4
    monitor-enter p0

    :try_start_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzgy;->zzb:Lkotlinx/coroutines/Deferred;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 3
    monitor-exit p0

    if-eqz p1, :cond_5

    iput v3, v0, Lcom/google/android/recaptcha/internal/zzgq;->zzc:I

    invoke-interface {p1, v0}, Lkotlinx/coroutines/Deferred;->await(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_8

    :goto_2
    check-cast p1, Ljava/lang/String;

    goto :goto_3

    :cond_5
    move-object p1, v5

    :goto_3
    if-eqz p1, :cond_7

    .line 4
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result p0

    if-nez p0, :cond_6

    return-object v5

    :cond_6
    return-object p1

    :cond_7
    return-object v5

    :catchall_0
    move-exception p1

    .line 3
    monitor-exit p0

    throw p1

    :cond_8
    return-object v1
.end method

.method public final zzf()Lcom/google/android/recaptcha/internal/zzmq;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgy;->zzc:Lcom/google/android/recaptcha/internal/zzmq;

    return-object p0
.end method

.method public final zzg()Lcom/google/android/recaptcha/internal/zzhu;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgy;->zzd:Lcom/google/android/recaptcha/internal/zzhu;

    return-object p0
.end method
