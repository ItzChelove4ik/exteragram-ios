.class public final Lcom/google/android/recaptcha/internal/zzaf;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field public final zza:Ljava/util/ArrayDeque;


# direct methods
.method public constructor <init>(I)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance p1, Ljava/util/ArrayDeque;

    invoke-direct {p1}, Ljava/util/ArrayDeque;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaf;->zza:Ljava/util/ArrayDeque;

    return-void
.end method


# virtual methods
.method public final zza(JJJ)V
    .locals 15

    const/16 v0, 0x9

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    const/4 v1, 0x0

    aget v1, v0, v1

    const/4 v2, 0x1

    aget v2, v0, v2

    const/4 v3, 0x2

    aget v3, v0, v3

    const/4 v4, 0x3

    aget v4, v0, v4

    const/4 v5, 0x4

    aget v5, v0, v5

    const/4 v6, 0x5

    aget v6, v0, v6

    const/4 v7, 0x6

    aget v7, v0, v7

    const/4 v8, 0x7

    aget v0, v0, v8

    not-int v8, v1

    and-int/2addr v2, v8

    or-int/2addr v2, v3

    and-int/2addr v1, v4

    or-int/2addr v1, v5

    add-int/2addr v2, v1

    sub-int/2addr v2, v6

    add-int/2addr v7, v2

    const v1, 0x5dc79ea8

    rem-int/2addr v0, v1

    new-instance v8, Lcom/google/android/recaptcha/internal/zzac;

    move-wide/from16 v9, p1

    move-wide/from16 v11, p3

    move-wide/from16 v13, p5

    invoke-direct/range {v8 .. v14}, Lcom/google/android/recaptcha/internal/zzac;-><init>(JJJ)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaf;->zza:Ljava/util/ArrayDeque;

    invoke-virtual {p0}, Ljava/util/ArrayDeque;->size()I

    move-result v1

    xor-int/2addr v0, v7

    if-ge v1, v0, :cond_0

    invoke-virtual {p0, v8}, Ljava/util/ArrayDeque;->push(Ljava/lang/Object;)V

    return-void

    :cond_0
    new-instance p0, Lcom/google/android/recaptcha/internal/zzad;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzad;-><init>()V

    throw p0

    nop

    :array_0
    .array-data 4
        0x7bd3ee7b
        0x5060ec00
        0xbb72b14
        0x5c40c412
        0xd043b73
        -0x5cc43c15
        0x42963e5a
        0x661e3f1e
        0x5dc79ea8
    .end array-data
.end method

.method public final zzb()Lcom/google/android/recaptcha/internal/zzac;
    .locals 1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaf;->zza:Ljava/util/ArrayDeque;

    invoke-virtual {p0}, Ljava/util/ArrayDeque;->peek()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzac;

    invoke-static {p0}, Ljava/util/Optional;->ofNullable(Ljava/lang/Object;)Ljava/util/Optional;

    move-result-object p0

    sget-object v0, Lcom/google/android/recaptcha/internal/zzab;->zza:Lcom/google/android/recaptcha/internal/zzab;

    invoke-virtual {p0, v0}, Ljava/util/Optional;->orElseThrow(Ljava/util/function/Supplier;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzac;

    return-object p0
.end method
