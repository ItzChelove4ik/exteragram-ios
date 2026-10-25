.class public final Lcom/google/android/recaptcha/internal/zzaa;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field public zza:I

.field public zzb:Lcom/google/android/recaptcha/internal/zzv;

.field public zzc:Lcom/google/android/recaptcha/internal/zzj;

.field public zzd:Lcom/google/android/recaptcha/internal/zzm;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzm;)V
    .locals 3

    .line 0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzv;->zzb:Lcom/google/android/recaptcha/internal/zzv;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzk;

    invoke-direct {v1}, Lcom/google/android/recaptcha/internal/zzk;-><init>()V

    const/4 v2, 0x0

    invoke-direct {p0, v0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaa;-><init>(Lcom/google/android/recaptcha/internal/zzv;ILcom/google/android/recaptcha/internal/zzj;)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzd:Lcom/google/android/recaptcha/internal/zzm;

    return-void
.end method

.method private constructor <init>(Lcom/google/android/recaptcha/internal/zzv;ILcom/google/android/recaptcha/internal/zzj;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzb:Lcom/google/android/recaptcha/internal/zzv;

    iput p2, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzc:Lcom/google/android/recaptcha/internal/zzj;

    return-void
.end method

.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzv;ILcom/google/android/recaptcha/internal/zzj;Lcom/google/android/recaptcha/internal/zzm;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzaa;-><init>(Lcom/google/android/recaptcha/internal/zzv;ILcom/google/android/recaptcha/internal/zzj;)V

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzd:Lcom/google/android/recaptcha/internal/zzm;

    return-void
.end method

.method private final zzg()J
    .locals 18

    move-object/from16 v0, p0

    const v1, 0x13c329b6

    not-int v2, v1

    const v3, 0x22118513

    and-int/2addr v2, v3

    const v3, 0x190f304f

    or-int/2addr v2, v3

    const v3, 0x23548f10

    and-int/2addr v1, v3

    const v3, 0x51ed4aa9

    or-int/2addr v1, v3

    add-int/2addr v2, v1

    const v1, -0x78773b6f

    sub-int/2addr v2, v1

    const v1, 0x12346df9

    const v3, 0x17b8a930

    rem-int/2addr v3, v1

    const v1, 0x53af4bb7

    not-int v4, v1

    const v5, 0xb1910c1

    and-int/2addr v4, v5

    const v5, 0x4e8d8379

    or-int/2addr v4, v5

    const v5, 0x21d41080

    and-int/2addr v1, v5

    const v5, 0x20ee8c32

    or-int/2addr v1, v5

    add-int/2addr v4, v1

    const v1, 0x6c5bdc14

    sub-int/2addr v4, v1

    const v1, 0x46a14f8

    const v5, 0x30551618

    rem-int/2addr v5, v1

    const v1, 0xcf9e7dc

    not-int v6, v1

    const v7, 0x8992921

    and-int/2addr v6, v7

    const v7, 0x4606e7d2

    or-int/2addr v6, v7

    const v7, 0x6a998a23

    and-int/2addr v1, v7

    const v7, 0x6360864a

    or-int/2addr v1, v7

    add-int/2addr v6, v1

    const v1, -0x55f07d91

    sub-int/2addr v6, v1

    const v1, 0xd213efb

    const v7, 0x49972ed8    # 1238491.0f

    rem-int/2addr v7, v1

    const v1, 0x614ef8eb

    not-int v8, v1

    const v9, 0x4029d441

    and-int/2addr v8, v9

    const v9, 0x188eaf32

    or-int/2addr v8, v9

    const v9, 0x42317061

    and-int/2addr v1, v9

    const v9, 0xa92ad24

    or-int/2addr v1, v9

    add-int/2addr v8, v1

    const v1, 0x608b797a

    sub-int/2addr v8, v1

    const v1, 0x12888409

    const v9, 0x5f61c7ca

    rem-int/2addr v9, v1

    const v1, 0x788a6b53

    not-int v10, v1

    const v11, 0x41f58000    # 30.6875f

    and-int/2addr v10, v11

    const v11, 0x19ca7e1

    or-int/2addr v10, v11

    const v11, 0x40614080

    and-int/2addr v1, v11

    const v11, 0x260e648c

    or-int/2addr v1, v11

    add-int/2addr v10, v1

    const v1, 0x5fb1be12

    sub-int/2addr v10, v1

    const v1, 0x141210d7

    const v11, 0x307e700a

    rem-int/2addr v11, v1

    const/4 v1, 0x0

    const-wide/16 v12, 0x0

    :goto_0
    xor-int v14, v2, v3

    if-ge v1, v14, :cond_3

    :try_start_0
    iget-object v14, v0, Lcom/google/android/recaptcha/internal/zzaa;->zzc:Lcom/google/android/recaptcha/internal/zzj;

    iget-object v15, v0, Lcom/google/android/recaptcha/internal/zzaa;->zzb:Lcom/google/android/recaptcha/internal/zzv;

    move/from16 v16, v2

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    move/from16 v17, v3

    add-int/lit8 v3, v2, 0x1

    iput v3, v0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    invoke-interface {v14, v15, v2}, Lcom/google/android/recaptcha/internal/zzj;->zza(Lcom/google/android/recaptcha/internal/zzv;I)B

    move-result v2

    xor-int v3, v6, v7

    xor-int v14, v4, v5

    and-int/2addr v14, v2

    int-to-long v14, v14

    shl-long/2addr v14, v1

    or-long/2addr v12, v14

    const/4 v14, 0x1

    if-ne v1, v3, :cond_1

    if-gt v2, v14, :cond_0

    move v1, v3

    goto :goto_1

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzx;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzx;-><init>()V

    throw v0

    :cond_1
    :goto_1
    xor-int v3, v8, v9

    and-int/2addr v2, v3

    if-nez v2, :cond_2

    ushr-long v0, v12, v14

    const-wide/16 v2, 0x1

    and-long/2addr v2, v12

    neg-long v2, v2

    xor-long/2addr v0, v2

    return-wide v0

    :cond_2
    xor-int v2, v10, v11

    add-int/2addr v1, v2

    move/from16 v2, v16

    move/from16 v3, v17

    goto :goto_0

    :cond_3
    new-instance v0, Lcom/google/android/recaptcha/internal/zzx;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzx;-><init>()V

    throw v0
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    move-exception v0

    new-instance v1, Lcom/google/android/recaptcha/internal/zzz;

    invoke-direct {v1, v0}, Lcom/google/android/recaptcha/internal/zzz;-><init>(Ljava/lang/Throwable;)V

    throw v1
.end method

.method private static final zzh(J)V
    .locals 19

    const/16 v0, 0x9

    new-array v0, v0, [J

    fill-array-data v0, :array_0

    const/4 v1, 0x0

    aget-wide v1, v0, v1

    const/4 v3, 0x1

    aget-wide v3, v0, v3

    const/4 v5, 0x2

    aget-wide v5, v0, v5

    const/4 v7, 0x3

    aget-wide v7, v0, v7

    const/4 v9, 0x4

    aget-wide v9, v0, v9

    const/4 v11, 0x5

    aget-wide v11, v0, v11

    const/4 v13, 0x6

    aget-wide v13, v0, v13

    const/4 v15, 0x7

    aget-wide v15, v0, v15

    move-wide/from16 v17, v3

    not-long v3, v1

    and-long v3, v3, v17

    or-long/2addr v3, v5

    and-long v0, v1, v7

    or-long/2addr v0, v9

    add-long/2addr v3, v0

    sub-long/2addr v3, v11

    add-long/2addr v13, v3

    const-wide/32 v0, 0x35fedc54

    rem-long/2addr v15, v0

    xor-long v0, v13, v15

    rem-long v0, p0, v0

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-nez v0, :cond_0

    return-void

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzy;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzy;-><init>()V

    throw v0

    :array_0
    .array-data 8
        0x49e1fd6f
        0x10286c08
        0x23f74b21
        0x30092428
        0x24859036
        0x52825e4b
        0x8de1f75
        0x44df9cd5
        0x35fedc54
    .end array-data
.end method


# virtual methods
.method public final zza(J)V
    .locals 20

    move-object/from16 v0, p0

    const/16 v1, 0x9

    new-array v1, v1, [J

    fill-array-data v1, :array_0

    const/4 v2, 0x0

    aget-wide v2, v1, v2

    const/4 v4, 0x1

    aget-wide v4, v1, v4

    const/4 v6, 0x2

    aget-wide v6, v1, v6

    const/4 v8, 0x3

    aget-wide v8, v1, v8

    const/4 v10, 0x4

    aget-wide v10, v1, v10

    const/4 v12, 0x5

    aget-wide v12, v1, v12

    const/4 v14, 0x6

    aget-wide v14, v1, v14

    const/16 v16, 0x7

    aget-wide v16, v1, v16

    move-wide/from16 v18, v4

    not-long v4, v2

    and-long v4, v4, v18

    or-long/2addr v4, v6

    and-long v1, v2, v8

    or-long/2addr v1, v10

    add-long/2addr v4, v1

    sub-long/2addr v4, v12

    add-long/2addr v14, v4

    const-wide/32 v1, 0x6fc1a0d4

    rem-long v16, v16, v1

    invoke-static/range {p1 .. p2}, Lcom/google/android/recaptcha/internal/zzaa;->zzh(J)V

    xor-long v1, v14, v16

    div-long v1, p1, v1

    const-wide/16 v3, 0x0

    cmp-long v3, v1, v3

    if-ltz v3, :cond_0

    iget-object v3, v0, Lcom/google/android/recaptcha/internal/zzaa;->zzb:Lcom/google/android/recaptcha/internal/zzv;

    iget-object v3, v3, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length v3, v3

    int-to-long v3, v3

    cmp-long v3, v1, v3

    if-gtz v3, :cond_0

    long-to-int v1, v1

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    return-void

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzz;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzz;-><init>()V

    throw v0

    :array_0
    .array-data 8
        0x5bd772bb
        0x220a6836
        0x587eda11
        0x2a442226
        0x5d7652c1
        0xd9ba1780L
        0xb726edc
        0x796f4530
        0x6fc1a0d4
    .end array-data
.end method

.method public final zzb()J
    .locals 19

    const/16 v0, 0x9

    new-array v0, v0, [J

    fill-array-data v0, :array_0

    const/4 v1, 0x0

    aget-wide v1, v0, v1

    const/4 v3, 0x1

    aget-wide v3, v0, v3

    const/4 v5, 0x2

    aget-wide v5, v0, v5

    const/4 v7, 0x3

    aget-wide v7, v0, v7

    const/4 v9, 0x4

    aget-wide v9, v0, v9

    const/4 v11, 0x5

    aget-wide v11, v0, v11

    const/4 v13, 0x6

    aget-wide v13, v0, v13

    const/4 v15, 0x7

    aget-wide v15, v0, v15

    move-wide/from16 v17, v3

    not-long v3, v1

    and-long v3, v3, v17

    or-long/2addr v3, v5

    and-long v0, v1, v7

    or-long/2addr v0, v9

    add-long/2addr v3, v0

    sub-long/2addr v3, v11

    add-long/2addr v13, v3

    const-wide/32 v0, 0x327b23c6

    rem-long/2addr v15, v0

    move-object/from16 v0, p0

    iget v0, v0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    int-to-long v0, v0

    xor-long v2, v13, v15

    mul-long/2addr v0, v2

    return-wide v0

    :array_0
    .array-data 8
        0x66334873
        0x68d1943c
        0xa69006e
        0x62909610
        0x2454aec
        0x8a3800c0L
        0x238e1f29
        0x6b8b4567
        0x327b23c6
    .end array-data
.end method

.method public final zzc()J
    .locals 4

    :try_start_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzc:Lcom/google/android/recaptcha/internal/zzj;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzb:Lcom/google/android/recaptcha/internal/zzv;

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    add-int/lit8 v3, v2, 0x1

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    invoke-interface {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzj;->zza(Lcom/google/android/recaptcha/internal/zzv;I)B

    move-result p0
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_0

    int-to-long v0, p0

    return-wide v0

    :catch_0
    move-exception p0

    new-instance v0, Lcom/google/android/recaptcha/internal/zzz;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzz;-><init>(Ljava/lang/Throwable;)V

    throw v0
.end method

.method public final zzd()I
    .locals 12

    const v0, 0x71482545

    not-int v1, v0

    const v2, 0x4db8223

    and-int/2addr v1, v2

    const v2, 0x402868da

    or-int/2addr v1, v2

    const v2, 0x44f3ca65

    and-int/2addr v0, v2

    const v2, 0x6a284dc4

    or-int/2addr v0, v2

    add-int/2addr v1, v0

    const v0, -0x6dbfa9bc

    sub-int/2addr v1, v0

    const v0, 0x539f7f12

    const v2, 0x70836196

    rem-int/2addr v2, v0

    const v0, 0x7f618fcd

    not-int v3, v0

    const v4, 0xe8026e3

    and-int/2addr v3, v4

    const v4, 0x580481b2

    or-int/2addr v3, v4

    const v4, 0x4680a649

    and-int/2addr v0, v4

    const v4, 0x4873898e

    or-int/2addr v0, v4

    add-int/2addr v3, v0

    const v0, -0x6a6e4791

    sub-int/2addr v3, v0

    const v0, 0x392edbe4

    const v4, 0x4a9554fe    # 4893311.0f

    rem-int/2addr v4, v0

    const v0, 0x1978ebeb

    not-int v5, v0

    const v6, 0x181855fc

    and-int/2addr v5, v6

    const v6, 0x4e99519e

    or-int/2addr v5, v6

    const v6, 0x34228460

    and-int/2addr v0, v6

    const v6, 0x6c76e283

    or-int/2addr v0, v6

    add-int/2addr v5, v0

    const v0, -0x4159effa    # -0.324341f

    sub-int/2addr v5, v0

    const v0, 0xe0d31ff

    const v6, 0x6ec68664

    rem-int/2addr v6, v0

    const v0, 0x7fbd7a3e

    not-int v7, v0

    const v8, 0x5111455

    and-int/2addr v7, v8

    const v8, 0x19a9a09a

    or-int/2addr v7, v8

    const v8, 0x345c1c65

    and-int/2addr v0, v8

    const v8, 0x70ed8a20

    or-int/2addr v0, v8

    add-int/2addr v7, v0

    const v0, -0x7917e7f9

    sub-int/2addr v7, v0

    const v0, 0x606ed7f6

    const v8, 0x682dfed6

    rem-int/2addr v8, v0

    :try_start_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzc:Lcom/google/android/recaptcha/internal/zzj;

    iget-object v9, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzb:Lcom/google/android/recaptcha/internal/zzv;

    iget v10, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    add-int/lit8 v11, v10, 0x1

    iput v11, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    invoke-interface {v0, v9, v10}, Lcom/google/android/recaptcha/internal/zzj;->zza(Lcom/google/android/recaptcha/internal/zzv;I)B

    move-result v0

    xor-int/2addr v1, v2

    and-int/2addr v0, v1

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzc:Lcom/google/android/recaptcha/internal/zzj;

    iget-object v9, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzb:Lcom/google/android/recaptcha/internal/zzv;

    iget v10, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    add-int/lit8 v11, v10, 0x1

    iput v11, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    invoke-interface {v2, v9, v10}, Lcom/google/android/recaptcha/internal/zzj;->zza(Lcom/google/android/recaptcha/internal/zzv;I)B

    move-result v2

    xor-int/2addr v3, v4

    and-int/2addr v2, v1

    shl-int/2addr v2, v3

    or-int/2addr v0, v2

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzc:Lcom/google/android/recaptcha/internal/zzj;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzb:Lcom/google/android/recaptcha/internal/zzv;

    iget v4, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    add-int/lit8 v9, v4, 0x1

    iput v9, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    invoke-interface {v2, v3, v4}, Lcom/google/android/recaptcha/internal/zzj;->zza(Lcom/google/android/recaptcha/internal/zzv;I)B

    move-result v2

    xor-int v3, v5, v6

    and-int/2addr v1, v2

    shl-int/2addr v1, v3

    or-int/2addr v0, v1

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzc:Lcom/google/android/recaptcha/internal/zzj;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzb:Lcom/google/android/recaptcha/internal/zzv;

    iget v3, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    add-int/lit8 v4, v3, 0x1

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    invoke-interface {v1, v2, v3}, Lcom/google/android/recaptcha/internal/zzj;->zza(Lcom/google/android/recaptcha/internal/zzv;I)B

    move-result p0
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_0

    xor-int v1, v7, v8

    shl-int/2addr p0, v1

    or-int/2addr p0, v0

    return p0

    :catch_0
    move-exception p0

    new-instance v0, Lcom/google/android/recaptcha/internal/zzz;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzz;-><init>(Ljava/lang/Throwable;)V

    throw v0
.end method

.method public final zze()J
    .locals 2

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaa;->zzg()J

    move-result-wide v0

    return-wide v0
.end method

.method public final zzf(J)Lcom/google/android/recaptcha/internal/zzv;
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

    const v1, 0x192e132e    # 8.9994625E-24f

    rem-int/2addr v0, v1

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaa;->zzb()J

    move-result-wide v1

    add-long/2addr v1, p1

    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzaa;->zzh(J)V

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    int-to-long v2, v1

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzb:Lcom/google/android/recaptcha/internal/zzv;

    iget-object v5, v4, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length v5, v5

    xor-int/2addr v0, v7

    shr-long/2addr p1, v0

    add-long/2addr p1, v2

    int-to-long v5, v5

    cmp-long v0, p1, v5

    if-gtz v0, :cond_0

    cmp-long v0, p1, v2

    if-ltz v0, :cond_0

    :try_start_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaa;->zzc:Lcom/google/android/recaptcha/internal/zzj;

    long-to-int p1, p1

    invoke-interface {v0, v4, v1, p1}, Lcom/google/android/recaptcha/internal/zzj;->zzb(Lcom/google/android/recaptcha/internal/zzv;II)Lcom/google/android/recaptcha/internal/zzv;

    move-result-object p2
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_0

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaa;->zza:I

    return-object p2

    :catch_0
    move-exception p0

    new-instance p1, Ljava/lang/AssertionError;

    const-string p2, "CEiv6BFfPnitUE+D"

    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-direct {p1, p2, p0}, Ljava/lang/AssertionError;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw p1

    :cond_0
    new-instance p0, Lcom/google/android/recaptcha/internal/zzz;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzz;-><init>()V

    throw p0

    :array_0
    .array-data 4
        0x7776bcd8
        0x379e1e80
        0x2c734511
        0x13ad1a82
        0x2061c01f
        0x521dc9b3
        0x77e9b28
        0x614c2ab0
        0x192e132e    # 8.9994625E-24f
    .end array-data
.end method
