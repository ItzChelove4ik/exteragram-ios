.class public final Lcom/google/android/recaptcha/internal/zzmk;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzmu;


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzhq;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzmx;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzhq;Lcom/google/android/recaptcha/internal/zzmx;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzmk;->zza:Lcom/google/android/recaptcha/internal/zzhq;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzmk;->zzb:Lcom/google/android/recaptcha/internal/zzmx;

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzalg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 7

    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzmj;

    if-eqz v0, :cond_0

    move-object v0, p2

    check-cast v0, Lcom/google/android/recaptcha/internal/zzmj;

    .line 1
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzmj;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzmj;->zzc:I

    goto :goto_0

    .line 7
    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzmj;

    invoke-direct {v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzmj;-><init>(Lcom/google/android/recaptcha/internal/zzmk;Lkotlin/coroutines/Continuation;)V

    .line 1
    :goto_0
    iget-object p2, v0, Lcom/google/android/recaptcha/internal/zzmj;->zza:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzmj;->zzc:I

    const/4 v3, 0x1

    if-eqz v2, :cond_2

    if-ne v2, v3, :cond_1

    :try_start_0
    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    .line 5
    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    .line 1
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :try_start_1
    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzmk;->zzb:Lcom/google/android/recaptcha/internal/zzmx;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzmk;->zza:Lcom/google/android/recaptcha/internal/zzhq;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzhq;->zze()Ljava/lang/String;

    move-result-object p0

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaec;->zzw()[B

    move-result-object p1

    iput v3, v0, Lcom/google/android/recaptcha/internal/zzmj;->zzc:I

    .line 3
    invoke-interface {p2, p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzmx;->zza(Ljava/lang/String;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p2

    if-ne p2, v1, :cond_3

    return-object v1

    .line 1
    :cond_3
    :goto_1
    check-cast p2, Lcom/google/android/recaptcha/internal/zznb;

    .line 4
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzali;->zzc()Lcom/google/android/recaptcha/internal/zzali;

    move-result-object p0

    invoke-virtual {p2, p0}, Lcom/google/android/recaptcha/internal/zznb;->zzd(Lcom/google/android/recaptcha/internal/zzagx;)Lcom/google/android/recaptcha/internal/zzagx;

    move-result-object p0
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

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzk:Lcom/google/android/recaptcha/internal/zzhe;

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
