.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxq;

.field private static volatile zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;


# instance fields
.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxq;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxq;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxq;

    return-void
.end method

.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    return-void
.end method

.method public static synthetic zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 5

    instance-of v0, p4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;

    if-eqz v0, :cond_0

    move-object v0, p4

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;->zzc:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;

    invoke-direct {v0, p0, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;->zzc:I

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-eqz v2, :cond_2

    if-ne v2, v4, :cond_1

    iget-object p3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;->zze:Ljava/lang/String;

    iget-object p2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;->zzd:Ljava/lang/String;

    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v3

    :cond_2
    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    .line 2
    iput-object p2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;->zzd:Ljava/lang/String;

    iput-object p3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;->zze:Ljava/lang/String;

    iput v4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxr;->zzc:I

    .line 3
    invoke-static {p0, p1, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p4

    if-eq p4, v1, :cond_6

    .line 2
    :goto_1
    check-cast p4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;

    if-nez p4, :cond_3

    return-object v3

    :cond_3
    if-eqz p3, :cond_5

    invoke-virtual {p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;->zze()Ljava/lang/String;

    move-result-object p0

    .line 4
    invoke-static {p0, p3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_4

    goto :goto_2

    .line 5
    :cond_4
    new-instance p0, Lcom/google/mlkit/genai/common/GenAiException;

    invoke-virtual {p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;->zze()Ljava/lang/String;

    move-result-object p1

    .line 6
    invoke-static {p2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p4

    invoke-virtual {p4}, Ljava/lang/String;->length()I

    move-result p4

    invoke-static {p1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    add-int/lit8 p4, p4, 0x2a

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/2addr p4, v0

    add-int/lit8 p4, p4, 0x21

    invoke-virtual {p3}, Ljava/lang/String;->length()I

    move-result v0

    add-int/2addr p4, v0

    add-int/lit8 p4, p4, 0x3c

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, p4}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string p4, "Cached context \'"

    invoke-virtual {v0, p4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, "\' was created with model \'"

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "\', but is being used with model \'"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "\'. Please either update the cache or use the matching model."

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const/16 p2, -0x67

    .line 5
    invoke-direct {p0, p1, v3, p2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw p0

    :cond_5
    :goto_2
    return-object p4

    :cond_6
    return-object v1
.end method

.method public static synthetic zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    invoke-static {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    .line 2
    invoke-static {p0, p1, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 3

    .line 1
    invoke-static {p2}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v0

    const-string v1, "ExplicitCacheManager"

    const/4 v2, 0x0

    if-eqz v0, :cond_0

    const-string p0, "Cache name is blank. Will not open cache for writing."

    .line 2
    invoke-static {v1, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    return-object v2

    .line 3
    :cond_0
    invoke-static {p3}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 4
    invoke-static {p2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    new-instance p1, Ljava/lang/StringBuilder;

    add-int/lit8 p0, p0, 0x49

    invoke-direct {p1, p0}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string p0, "Prompt prefix is blank for cache name: "

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ". Will not open cache for writing."

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v1, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    return-object v2

    .line 5
    :cond_1
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxh;->zza()Ljava/lang/String;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;

    .line 6
    invoke-direct {v1, p2, v0, p3, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    .line 8
    invoke-static {p0, p1, v1, v2, p5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    .line 2
    invoke-static {p0, p1, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    return-object v0
.end method

.method public static final synthetic zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;)V
    .locals 0

    sput-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxs;

    return-void
.end method
