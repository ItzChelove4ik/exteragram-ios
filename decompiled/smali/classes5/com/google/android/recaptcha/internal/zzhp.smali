.class public final Lcom/google/android/recaptcha/internal/zzhp;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzhp;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzhp;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzhp;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzhp;->zza:Lcom/google/android/recaptcha/internal/zzhp;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static synthetic zzb(Lcom/google/android/recaptcha/internal/zzhp;IJJDLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
    .locals 0

    const-wide/16 p4, 0x3e8

    const-wide/high16 p6, 0x4000000000000000L    # 2.0

    const-wide/16 p2, 0x64

    .line 1
    invoke-virtual/range {p0 .. p9}, Lcom/google/android/recaptcha/internal/zzhp;->zza(IJJDLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zzd(Lcom/google/android/recaptcha/internal/zzhp;Lkotlin/jvm/functions/Function1;JJDLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
    .locals 10

    and-int/lit8 p2, p10, 0x2

    if-eqz p2, :cond_0

    const-wide/16 p2, 0x64

    :goto_0
    move-wide v2, p2

    goto :goto_1

    :cond_0
    const-wide/16 p2, 0x0

    goto :goto_0

    :goto_1
    and-int/lit8 p2, p10, 0x4

    if-eqz p2, :cond_1

    const-wide/16 p2, 0x3e8

    move-wide v4, p2

    goto :goto_2

    :cond_1
    move-wide v4, p4

    :goto_2
    const-wide/high16 v6, 0x4000000000000000L    # 2.0

    move-object v0, p0

    move-object v1, p1

    move-object/from16 v8, p8

    move-object/from16 v9, p9

    .line 1
    invoke-virtual/range {v0 .. v9}, Lcom/google/android/recaptcha/internal/zzhp;->zzc(Lkotlin/jvm/functions/Function1;JJDLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic zze(Lcom/google/android/recaptcha/internal/zzhp;JIJJDLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
    .locals 10

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzho;

    const-wide/high16 v6, 0x4000000000000000L    # 2.0

    const/4 v9, 0x0

    const/16 v1, 0x14

    const-wide/16 v2, 0x64

    const-wide/16 v4, 0x3e8

    move-object/from16 v8, p10

    invoke-direct/range {v0 .. v9}, Lcom/google/android/recaptcha/internal/zzho;-><init>(IJJDLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V

    move-object/from16 p0, p11

    invoke-static {p1, p2, v0, p0}, Lkotlinx/coroutines/TimeoutKt;->withTimeout(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public final zza(IJJDLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 17

    move-object/from16 v0, p9

    instance-of v1, v0, Lcom/google/android/recaptcha/internal/zzhm;

    if-eqz v1, :cond_0

    move-object v1, v0

    check-cast v1, Lcom/google/android/recaptcha/internal/zzhm;

    .line 1
    iget v2, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzh:I

    const/high16 v3, -0x80000000

    and-int v4, v2, v3

    if-eqz v4, :cond_0

    sub-int/2addr v2, v3

    iput v2, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzh:I

    goto :goto_0

    :cond_0
    new-instance v1, Lcom/google/android/recaptcha/internal/zzhm;

    move-object/from16 v2, p0

    invoke-direct {v1, v2, v0}, Lcom/google/android/recaptcha/internal/zzhm;-><init>(Lcom/google/android/recaptcha/internal/zzhp;Lkotlin/coroutines/Continuation;)V

    :goto_0
    iget-object v0, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzf:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v2

    iget v3, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzh:I

    const/4 v4, 0x0

    const/4 v5, 0x3

    const/4 v6, 0x2

    const/4 v7, 0x1

    if-eqz v3, :cond_5

    if-eq v3, v7, :cond_4

    if-eq v3, v6, :cond_2

    if-ne v3, v5, :cond_1

    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object v0

    :cond_1
    const-string v0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {v0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v4

    :cond_2
    iget v3, v1, Lcom/google/android/recaptcha/internal/zzhm;->zze:I

    iget v8, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzd:I

    iget-wide v9, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzb:D

    iget-wide v11, v1, Lcom/google/android/recaptcha/internal/zzhm;->zza:J

    iget-object v13, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzi:Lkotlin/jvm/internal/Ref$LongRef;

    iget-object v14, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzc:Ljava/lang/Object;

    check-cast v14, Lkotlin/jvm/functions/Function1;

    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :cond_3
    move-wide v15, v11

    move-object v11, v1

    move-wide v0, v15

    move v4, v3

    move v12, v8

    move-wide v8, v9

    move-object v10, v13

    move-object v3, v14

    goto/16 :goto_2

    .line 2
    :cond_4
    iget v3, v1, Lcom/google/android/recaptcha/internal/zzhm;->zze:I

    iget v8, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzd:I

    iget-wide v9, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzb:D

    iget-wide v11, v1, Lcom/google/android/recaptcha/internal/zzhm;->zza:J

    iget-object v13, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzi:Lkotlin/jvm/internal/Ref$LongRef;

    .line 1
    iget-object v14, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzc:Ljava/lang/Object;

    check-cast v14, Lkotlin/jvm/functions/Function1;

    :try_start_0
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    return-object v0

    :cond_5
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    new-instance v0, Lkotlin/jvm/internal/Ref$LongRef;

    invoke-direct {v0}, Lkotlin/jvm/internal/Ref$LongRef;-><init>()V

    move-wide/from16 v8, p2

    iput-wide v8, v0, Lkotlin/jvm/internal/Ref$LongRef;->element:J

    add-int/lit8 v3, p1, -0x1

    const/4 v8, 0x0

    move-object v10, v0

    move-object v11, v1

    move v12, v3

    move v13, v8

    move-wide/from16 v0, p4

    move-wide/from16 v8, p6

    move-object/from16 v3, p8

    :goto_1
    if-ge v13, v12, :cond_7

    .line 1
    :try_start_1
    iput-object v3, v11, Lcom/google/android/recaptcha/internal/zzhm;->zzc:Ljava/lang/Object;

    iput-object v10, v11, Lcom/google/android/recaptcha/internal/zzhm;->zzi:Lkotlin/jvm/internal/Ref$LongRef;

    iput-wide v0, v11, Lcom/google/android/recaptcha/internal/zzhm;->zza:J

    iput-wide v8, v11, Lcom/google/android/recaptcha/internal/zzhm;->zzb:D

    iput v12, v11, Lcom/google/android/recaptcha/internal/zzhm;->zzd:I

    iput v13, v11, Lcom/google/android/recaptcha/internal/zzhm;->zze:I

    iput v7, v11, Lcom/google/android/recaptcha/internal/zzhm;->zzh:I

    .line 3
    invoke-interface {v3, v11}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    if-ne v0, v2, :cond_6

    goto :goto_3

    :cond_6
    move-object v2, v0

    goto :goto_3

    :catch_0
    move-object v14, v3

    move v3, v13

    move-object v13, v10

    move-wide v9, v8

    move v8, v12

    move-wide v15, v0

    move-object v1, v11

    move-wide v11, v15

    .line 4
    :catch_1
    iget-wide v4, v13, Lkotlin/jvm/internal/Ref$LongRef;->element:J

    long-to-double v4, v4

    mul-double/2addr v4, v9

    double-to-long v4, v4

    invoke-static {v4, v5, v11, v12}, Lkotlin/ranges/RangesKt;->coerceAtMost(JJ)J

    move-result-wide v4

    iput-wide v4, v13, Lkotlin/jvm/internal/Ref$LongRef;->element:J

    .line 5
    iput-object v14, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzc:Ljava/lang/Object;

    iput-object v13, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzi:Lkotlin/jvm/internal/Ref$LongRef;

    iput-wide v11, v1, Lcom/google/android/recaptcha/internal/zzhm;->zza:J

    iput-wide v9, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzb:D

    iput v8, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzd:I

    iput v3, v1, Lcom/google/android/recaptcha/internal/zzhm;->zze:I

    iput v6, v1, Lcom/google/android/recaptcha/internal/zzhm;->zzh:I

    invoke-static {v4, v5, v1}, Lkotlinx/coroutines/DelayKt;->delay(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-ne v0, v2, :cond_3

    goto :goto_3

    :goto_2
    add-int/lit8 v13, v4, 0x1

    const/4 v4, 0x0

    const/4 v5, 0x3

    goto :goto_1

    :cond_7
    move-object v0, v4

    .line 3
    iput-object v0, v11, Lcom/google/android/recaptcha/internal/zzhm;->zzc:Ljava/lang/Object;

    iput-object v0, v11, Lcom/google/android/recaptcha/internal/zzhm;->zzi:Lkotlin/jvm/internal/Ref$LongRef;

    const/4 v0, 0x3

    iput v0, v11, Lcom/google/android/recaptcha/internal/zzhm;->zzh:I

    .line 6
    invoke-interface {v3, v11}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    if-ne v0, v2, :cond_6

    :goto_3
    return-object v2
.end method

.method public final zzc(Lkotlin/jvm/functions/Function1;JJDLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 16

    move-object/from16 v0, p9

    instance-of v1, v0, Lcom/google/android/recaptcha/internal/zzhn;

    if-eqz v1, :cond_0

    move-object v1, v0

    check-cast v1, Lcom/google/android/recaptcha/internal/zzhn;

    .line 1
    iget v2, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzh:I

    const/high16 v3, -0x80000000

    and-int v4, v2, v3

    if-eqz v4, :cond_0

    sub-int/2addr v2, v3

    iput v2, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzh:I

    goto :goto_0

    :cond_0
    new-instance v1, Lcom/google/android/recaptcha/internal/zzhn;

    move-object/from16 v2, p0

    invoke-direct {v1, v2, v0}, Lcom/google/android/recaptcha/internal/zzhn;-><init>(Lcom/google/android/recaptcha/internal/zzhp;Lkotlin/coroutines/Continuation;)V

    :goto_0
    iget-object v0, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzf:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v2

    iget v3, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzh:I

    const/4 v4, 0x2

    const/4 v5, 0x1

    if-eqz v3, :cond_3

    if-eq v3, v5, :cond_2

    if-ne v3, v4, :cond_1

    iget-wide v6, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzd:J

    iget-wide v8, v1, Lcom/google/android/recaptcha/internal/zzhn;->zze:D

    iget-wide v10, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzc:J

    iget-object v3, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzb:Ljava/lang/Object;

    check-cast v3, Lkotlin/jvm/functions/Function1;

    iget-object v12, v1, Lcom/google/android/recaptcha/internal/zzhn;->zza:Ljava/lang/Object;

    check-cast v12, Lkotlin/jvm/functions/Function1;

    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    :goto_1
    move-object v14, v12

    move-object v12, v1

    move-object v1, v14

    move-wide v14, v10

    move-wide v10, v8

    move-wide v8, v14

    goto :goto_2

    :cond_1
    const-string v0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {v0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 v0, 0x0

    return-object v0

    .line 2
    :cond_2
    iget-wide v6, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzd:J

    iget-wide v8, v1, Lcom/google/android/recaptcha/internal/zzhn;->zze:D

    iget-wide v10, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzc:J

    iget-object v3, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzb:Ljava/lang/Object;

    .line 1
    check-cast v3, Lkotlin/jvm/functions/Function1;

    iget-object v12, v1, Lcom/google/android/recaptcha/internal/zzhn;->zza:Ljava/lang/Object;

    check-cast v12, Lkotlin/jvm/functions/Function1;

    :try_start_0
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    goto :goto_3

    :cond_3
    invoke-static {v0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-wide/from16 v6, p2

    move-wide/from16 v8, p4

    move-wide/from16 v10, p6

    move-object/from16 v3, p8

    move-object v12, v1

    move-object/from16 v1, p1

    :goto_2
    :try_start_1
    iput-object v1, v12, Lcom/google/android/recaptcha/internal/zzhn;->zza:Ljava/lang/Object;

    iput-object v3, v12, Lcom/google/android/recaptcha/internal/zzhn;->zzb:Ljava/lang/Object;

    iput-wide v8, v12, Lcom/google/android/recaptcha/internal/zzhn;->zzc:J

    iput-wide v10, v12, Lcom/google/android/recaptcha/internal/zzhn;->zze:D

    iput-wide v6, v12, Lcom/google/android/recaptcha/internal/zzhn;->zzd:J

    iput v5, v12, Lcom/google/android/recaptcha/internal/zzhn;->zzh:I

    .line 2
    invoke-interface {v3, v12}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    if-ne v0, v2, :cond_4

    goto :goto_4

    :cond_4
    return-object v0

    :catch_1
    move-exception v0

    move-object v14, v12

    move-object v12, v1

    move-object v1, v14

    move-wide v14, v10

    move-wide v10, v8

    move-wide v8, v14

    .line 3
    :goto_3
    invoke-interface {v12, v0}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v13

    check-cast v13, Ljava/lang/Boolean;

    invoke-virtual {v13}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v13

    if-eqz v13, :cond_6

    long-to-double v6, v6

    mul-double/2addr v6, v8

    double-to-long v6, v6

    .line 4
    invoke-static {v6, v7, v10, v11}, Lkotlin/ranges/RangesKt;->coerceAtMost(JJ)J

    move-result-wide v6

    iput-object v12, v1, Lcom/google/android/recaptcha/internal/zzhn;->zza:Ljava/lang/Object;

    iput-object v3, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzb:Ljava/lang/Object;

    iput-wide v10, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzc:J

    iput-wide v8, v1, Lcom/google/android/recaptcha/internal/zzhn;->zze:D

    iput-wide v6, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzd:J

    iput v4, v1, Lcom/google/android/recaptcha/internal/zzhn;->zzh:I

    .line 5
    invoke-static {v6, v7, v1}, Lkotlinx/coroutines/DelayKt;->delay(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v0

    if-eq v0, v2, :cond_5

    goto :goto_1

    :cond_5
    :goto_4
    return-object v2

    .line 6
    :cond_6
    throw v0
.end method
