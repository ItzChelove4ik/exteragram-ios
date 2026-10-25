.class public final Lcom/google/android/recaptcha/internal/zzmm;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzhq;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzmx;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzhq;Lcom/google/android/recaptcha/internal/zzmx;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzmm;->zza:Lcom/google/android/recaptcha/internal/zzhq;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzmm;->zzb:Lcom/google/android/recaptcha/internal/zzmx;

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzajl;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 4

    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzml;

    if-eqz v0, :cond_0

    move-object v0, p2

    check-cast v0, Lcom/google/android/recaptcha/internal/zzml;

    .line 1
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzml;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzml;->zzc:I

    goto :goto_0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzml;

    invoke-direct {v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzml;-><init>(Lcom/google/android/recaptcha/internal/zzmm;Lkotlin/coroutines/Continuation;)V

    :goto_0
    iget-object p2, v0, Lcom/google/android/recaptcha/internal/zzml;->zza:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzml;->zzc:I

    const/4 v3, 0x1

    if-eqz v2, :cond_2

    if-ne v2, v3, :cond_1

    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzmm;->zzb:Lcom/google/android/recaptcha/internal/zzmx;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzmm;->zza:Lcom/google/android/recaptcha/internal/zzhq;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzhq;->zzc()Ljava/lang/String;

    move-result-object p0

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaec;->zzw()[B

    move-result-object p1

    iput v3, v0, Lcom/google/android/recaptcha/internal/zzml;->zzc:I

    invoke-interface {p2, p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzmx;->zza(Ljava/lang/String;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p2

    if-eq p2, v1, :cond_4

    .line 1
    :goto_1
    check-cast p2, Lcom/google/android/recaptcha/internal/zznb;

    .line 3
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zznb;->zzc()I

    move-result p0

    const/16 p1, 0xc8

    if-ne p0, p1, :cond_3

    goto :goto_2

    :cond_3
    const/4 v3, 0x0

    :goto_2
    invoke-static {v3}, Lkotlin/coroutines/jvm/internal/Boxing;->boxBoolean(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0

    :cond_4
    return-object v1
.end method
