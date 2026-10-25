.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzz;

.field private static volatile zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;


# instance fields
.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

.field private final zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxp;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzz;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzz;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzz;

    return-void
.end method

.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzxp;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 1
    sget-object p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxp;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxp;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxp;

    return-void
.end method

.method public static synthetic zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 5

    instance-of v0, p4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaf;

    if-eqz v0, :cond_0

    move-object v0, p4

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaf;

    iget v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaf;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaf;->zzc:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaf;

    invoke-direct {v0, p0, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaf;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p4, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaf;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaf;->zzc:I

    const/4 v3, 0x1

    const/4 v4, 0x0

    if-eqz v2, :cond_2

    if-ne v2, v3, :cond_1

    iget-object p3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaf;->zzd:Ljava/lang/String;

    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    .line 5
    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    .line 1
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v4

    :cond_2
    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    invoke-interface {p2}, Ljava/lang/CharSequence;->length()I

    move-result p4

    if-nez p4, :cond_3

    return-object v4

    .line 3
    :cond_3
    invoke-static {p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzj(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    .line 4
    iput-object p3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaf;->zzd:Ljava/lang/String;

    iput v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaf;->zzc:I

    .line 5
    invoke-static {p0, p1, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p4

    if-ne p4, v1, :cond_4

    return-object v1

    .line 1
    :cond_4
    :goto_1
    check-cast p4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    if-eqz p4, :cond_5

    invoke-virtual {p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;->zze()Ljava/lang/String;

    move-result-object p0

    goto :goto_2

    :cond_5
    move-object p0, v4

    .line 6
    :goto_2
    invoke-static {p0, p3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_6

    return-object p4

    :cond_6
    return-object v4
.end method

.method public static synthetic zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 17

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p3

    instance-of v3, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;

    if-eqz v3, :cond_0

    move-object v3, v2

    check-cast v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;

    iget v4, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zze:I

    const/high16 v5, -0x80000000

    and-int v6, v4, v5

    if-eqz v6, :cond_0

    sub-int/2addr v4, v5

    iput v4, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zze:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;

    invoke-direct {v3, v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object v2, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzc:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v4

    iget v5, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zze:I

    const/4 v6, 0x3

    const/4 v7, 0x2

    const/4 v8, 0x1

    const/4 v9, 0x0

    if-eqz v5, :cond_4

    if-eq v5, v8, :cond_3

    if-eq v5, v7, :cond_2

    if-ne v5, v6, :cond_1

    iget-object v0, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zza:Ljava/lang/Object;

    check-cast v0, Landroid/os/ParcelFileDescriptor;

    invoke-static {v2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object v0

    :cond_1
    const-string v0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {v0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v9

    :cond_2
    iget-object v0, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iget-object v1, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzf:Ljava/lang/String;

    iget-object v5, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzb:Ljava/lang/Object;

    check-cast v5, Landroid/content/Context;

    iget-object v7, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zza:Ljava/lang/Object;

    check-cast v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    invoke-static {v2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v11, v1

    goto :goto_2

    :cond_3
    iget-object v0, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzf:Ljava/lang/String;

    iget-object v1, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzb:Ljava/lang/Object;

    check-cast v1, Landroid/content/Context;

    iget-object v5, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zza:Ljava/lang/Object;

    check-cast v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    invoke-static {v2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object/from16 v16, v2

    move-object v2, v0

    move-object v0, v5

    move-object/from16 v5, v16

    goto :goto_1

    :cond_4
    invoke-static {v2}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    invoke-virtual/range {p2 .. p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;->zza()Ljava/lang/String;

    move-result-object v2

    iget-object v5, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    .line 3
    iput-object v0, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zza:Ljava/lang/Object;

    iput-object v1, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzb:Ljava/lang/Object;

    iput-object v2, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzf:Ljava/lang/String;

    iput v8, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zze:I

    .line 4
    invoke-static {v5, v1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v5

    if-eq v5, v4, :cond_7

    .line 1
    :goto_1
    check-cast v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    if-nez v5, :cond_5

    return-object v9

    .line 5
    :cond_5
    iget-object v8, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iput-object v0, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zza:Ljava/lang/Object;

    iput-object v1, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzb:Ljava/lang/Object;

    iput-object v2, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzf:Ljava/lang/String;

    iput-object v5, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iput v7, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zze:I

    .line 6
    invoke-static {v8, v1, v5, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v7

    if-eq v7, v4, :cond_7

    move-object v11, v2

    move-object v2, v7

    move-object v7, v0

    move-object v0, v5

    move-object v5, v1

    .line 1
    :goto_2
    check-cast v2, Landroid/os/ParcelFileDescriptor;

    if-eqz v2, :cond_6

    .line 7
    iget-object v1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxp;

    .line 8
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v13

    new-instance v10, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    .line 9
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;->zzb()Ljava/lang/String;

    move-result-object v12

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;->zze()Ljava/lang/String;

    move-result-object v15

    invoke-direct/range {v10 .. v15}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;-><init>(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V

    .line 10
    iget-object v0, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iput-object v2, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zza:Ljava/lang/Object;

    iput-object v9, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzb:Ljava/lang/Object;

    iput-object v9, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzf:Ljava/lang/String;

    iput-object v9, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    iput v6, v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zze:I

    .line 11
    invoke-static {v0, v5, v10, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-eq v0, v4, :cond_7

    :cond_6
    return-object v2

    :cond_7
    return-object v4
.end method

.method public static synthetic zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 6

    .line 1
    invoke-interface {p2}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 2
    :cond_0
    invoke-static {p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzj(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 3
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxh;->zza()Ljava/lang/String;

    move-result-object v2

    .line 4
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    move-object v5, p3

    .line 5
    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;-><init>(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V

    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    .line 6
    new-instance p3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaa;

    invoke-direct {p3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaa;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;)V

    .line 7
    invoke-static {p2, p1, v0, p3, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    .line 2
    invoke-static {p0, p1, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    return-object v0
.end method

.method public static final synthetic zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;)V
    .locals 0

    sput-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    return-void
.end method

.method public static synthetic zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Ljava/util/Map;)Ljava/util/Collection;
    .locals 0

    invoke-static {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzi(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Ljava/util/Map;)Ljava/util/Collection;

    move-result-object p0

    return-object p0
.end method

.method private static final zzi(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Ljava/util/Map;)Ljava/util/Collection;
    .locals 3

    .line 1
    invoke-interface {p1}, Ljava/util/Map;->size()I

    move-result p0

    const/16 v0, 0xa

    if-le p0, v0, :cond_1

    .line 2
    invoke-interface {p1}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaae;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaae;-><init>()V

    .line 3
    invoke-static {p0, v0}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    .line 4
    invoke-interface {p1}, Ljava/util/Map;->size()I

    move-result p1

    add-int/lit8 p1, p1, -0xa

    .line 5
    invoke-static {p0, p1}, Lkotlin/collections/CollectionsKt;->take(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object p0

    .line 6
    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;

    .line 7
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;->zza()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "ImplicitCacheManager"

    const-string v2, "Cache limit reached. Remove least recent used cache with name "

    invoke-virtual {v2, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 8
    :cond_0
    check-cast p0, Ljava/util/Collection;

    return-object p0

    .line 9
    :cond_1
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/util/Collection;

    return-object p0
.end method

.method private static final zzj(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    .line 1
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    invoke-static {p1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    new-instance v2, Ljava/lang/StringBuilder;

    add-int/lit8 v0, v0, 0x1

    add-int/2addr v0, v1

    invoke-direct {v2, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "_"

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
