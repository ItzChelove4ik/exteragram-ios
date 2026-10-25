.class public final Lcom/google/android/recaptcha/internal/zzai;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field public final zza:Ljava/util/ArrayList;

.field public zzb:I


# direct methods
.method public constructor <init>(I)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzai;->zza:Ljava/util/ArrayList;

    const/4 p1, 0x0

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    return-void
.end method


# virtual methods
.method public final zza(J)I
    .locals 24

    move-object/from16 v0, p0

    move-wide/from16 v1, p1

    const/16 v3, 0x9

    new-array v4, v3, [I

    fill-array-data v4, :array_0

    const/4 v5, 0x0

    aget v6, v4, v5

    const/4 v7, 0x1

    aget v8, v4, v7

    const/4 v9, 0x2

    aget v10, v4, v9

    const/4 v11, 0x3

    aget v12, v4, v11

    const/4 v13, 0x4

    aget v14, v4, v13

    const/4 v15, 0x5

    aget v16, v4, v15

    const/16 v17, 0x6

    aget v18, v4, v17

    const/16 v19, 0x7

    aget v4, v4, v19

    move/from16 v20, v5

    not-int v5, v6

    and-int/2addr v5, v8

    or-int/2addr v5, v10

    and-int/2addr v6, v12

    or-int/2addr v6, v14

    add-int/2addr v5, v6

    sub-int v5, v5, v16

    add-int v18, v18, v5

    const v5, 0x3d1b58ba

    rem-int/2addr v4, v5

    new-array v3, v3, [J

    fill-array-data v3, :array_1

    aget-wide v5, v3, v20

    aget-wide v7, v3, v7

    aget-wide v9, v3, v9

    aget-wide v11, v3, v11

    aget-wide v13, v3, v13

    aget-wide v15, v3, v15

    aget-wide v20, v3, v17

    aget-wide v22, v3, v19

    move/from16 v17, v4

    not-long v3, v5

    and-long/2addr v3, v7

    or-long/2addr v3, v9

    and-long/2addr v5, v11

    or-long/2addr v5, v13

    add-long/2addr v3, v5

    sub-long/2addr v3, v15

    add-long v20, v20, v3

    const-wide/32 v3, 0x737b8ddc

    rem-long v22, v22, v3

    const-wide/16 v3, 0x0

    cmp-long v5, v1, v3

    if-ltz v5, :cond_0

    xor-int v5, v18, v17

    iget v6, v0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    add-int/2addr v6, v5

    int-to-long v5, v6

    sub-long/2addr v5, v1

    goto :goto_0

    :cond_0
    xor-long v5, v20, v22

    neg-long v1, v1

    add-long/2addr v5, v1

    :goto_0
    cmp-long v1, v5, v3

    if-ltz v1, :cond_1

    iget v0, v0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    int-to-long v0, v0

    cmp-long v0, v5, v0

    if-gez v0, :cond_1

    long-to-int v0, v5

    return v0

    :cond_1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzag;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzag;-><init>()V

    throw v0

    nop

    :array_0
    .array-data 4
        0x2eb141f2
        0x79328962
        0x3c45e104
        -0x3cc5b79a
        -0x6db2bfe4
        0x1ad3fdc4
        0x141b02e
        0x46e87ccd
        0x3d1b58ba
    .end array-data

    :array_1
    .array-data 8
        0x22221a70
        0x74045da6
        0x314b8007
        -0x31f38060
        -0x74655dbf
        0xd7473cb
        0x3c7c481
        0x7c3dbd3d
        0x737b8ddc
    .end array-data
.end method

.method public final zzb(Lcom/google/android/recaptcha/internal/zzar;)V
    .locals 10

    const/16 v0, 0x9

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    const/4 v1, 0x0

    aget v1, v0, v1

    const/4 v2, 0x1

    aget v3, v0, v2

    const/4 v4, 0x2

    aget v4, v0, v4

    const/4 v5, 0x3

    aget v5, v0, v5

    const/4 v6, 0x4

    aget v6, v0, v6

    const/4 v7, 0x5

    aget v7, v0, v7

    const/4 v8, 0x6

    aget v8, v0, v8

    const/4 v9, 0x7

    aget v0, v0, v9

    not-int v9, v1

    and-int/2addr v3, v9

    or-int/2addr v3, v4

    and-int/2addr v1, v5

    or-int/2addr v1, v6

    add-int/2addr v3, v1

    sub-int/2addr v3, v7

    add-int/2addr v8, v3

    const v1, 0x24f6ab8e

    rem-int/2addr v0, v1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    xor-int/2addr v0, v8

    if-ge v1, v0, :cond_1

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzai;->zza:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ne v1, v3, :cond_0

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_0
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    invoke-virtual {v0, v1, p1}, Ljava/util/ArrayList;->set(ILjava/lang/Object;)Ljava/lang/Object;

    :goto_0
    iget p1, p0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    add-int/2addr p1, v2

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    return-void

    :cond_1
    new-instance p0, Lcom/google/android/recaptcha/internal/zzah;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzah;-><init>()V

    throw p0

    nop

    :array_0
    .array-data 4
        0x24e99dd7
        0x2a30c6b5
        0x55cf5998
        0x6eb08625
        0x44c54142
        -0x4003b1d1
        0x69b9c99
        0x5551b9f3
        0x24f6ab8e
    .end array-data
.end method

.method public final zzc()Lcom/google/android/recaptcha/internal/zzar;
    .locals 9

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

    const v1, 0x26bba08c

    rem-int/2addr v0, v1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    if-lez v1, :cond_0

    xor-int/2addr v0, v7

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzai;->zza:Ljava/util/ArrayList;

    add-int/2addr v1, v0

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzar;

    iget v3, p0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    add-int/2addr v3, v0

    const/4 v4, 0x0

    invoke-virtual {v2, v3, v4}, Ljava/util/ArrayList;->set(ILjava/lang/Object;)Ljava/lang/Object;

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    add-int/2addr v2, v0

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    return-object v1

    :cond_0
    new-instance p0, Lcom/google/android/recaptcha/internal/zzag;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzag;-><init>()V

    throw p0

    :array_0
    .array-data 4
        0x4a495ddd    # 3299191.2f
        0x50b220e9
        0x2dc01614
        -0x2cddb17
        -0x50b328ea
        0x418015c5
        0x24e8599
        0x5f69c330
        0x26bba08c
    .end array-data
.end method

.method public final zzd(J)Lcom/google/android/recaptcha/internal/zzar;
    .locals 1

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzai;->zza:Ljava/util/ArrayList;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzai;->zza(J)I

    move-result p0

    invoke-virtual {v0, p0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzar;

    return-object p0
.end method

.method public final zze(JLcom/google/android/recaptcha/internal/zzar;)V
    .locals 1

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzai;->zza:Ljava/util/ArrayList;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzai;->zza(J)I

    move-result p0

    invoke-virtual {v0, p0, p3}, Ljava/util/ArrayList;->set(ILjava/lang/Object;)Ljava/lang/Object;

    return-void
.end method
