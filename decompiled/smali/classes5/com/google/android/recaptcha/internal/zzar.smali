.class public final Lcom/google/android/recaptcha/internal/zzar;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field public zza:I

.field private zzb:Ljava/lang/Object;

.field private zzc:J

.field private zzd:D

.field private zze:Lcom/google/android/recaptcha/internal/zzv;

.field private zzf:Ljava/util/List;

.field private zzg:Lcom/google/android/recaptcha/internal/zzaj;


# direct methods
.method private constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzar;->zza:I

    return-void
.end method

.method public static zza(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzar;
    .locals 10

    new-instance v0, Lcom/google/android/recaptcha/internal/zzar;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzar;-><init>()V

    const/16 v1, 0x9

    new-array v1, v1, [I

    fill-array-data v1, :array_0

    const/4 v2, 0x0

    aget v2, v1, v2

    const/4 v3, 0x1

    aget v3, v1, v3

    const/4 v4, 0x2

    aget v4, v1, v4

    const/4 v5, 0x3

    aget v5, v1, v5

    const/4 v6, 0x4

    aget v6, v1, v6

    const/4 v7, 0x5

    aget v7, v1, v7

    const/4 v8, 0x6

    aget v8, v1, v8

    const/4 v9, 0x7

    aget v1, v1, v9

    not-int v9, v2

    and-int/2addr v3, v9

    or-int/2addr v3, v4

    and-int/2addr v2, v5

    or-int/2addr v2, v6

    add-int/2addr v3, v2

    sub-int/2addr v3, v7

    add-int/2addr v8, v3

    const v2, 0xce344b5

    rem-int/2addr v1, v2

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzar;->zzr()V

    xor-int/2addr v1, v8

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzar;->zza:I

    iput-object p0, v0, Lcom/google/android/recaptcha/internal/zzar;->zzb:Ljava/lang/Object;

    return-object v0

    nop

    :array_0
    .array-data 4
        0x6f49cd8a
        0xd154d15
        0x623016f0
        0x2f2f4ba5
        0x72fa82a8
        -0x1a0459e5
        0x95a298b
        0x52e2024d
        0xce344b5
    .end array-data
.end method

.method public static zzb(J)Lcom/google/android/recaptcha/internal/zzar;
    .locals 10

    new-instance v0, Lcom/google/android/recaptcha/internal/zzar;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzar;-><init>()V

    const/16 v1, 0x9

    new-array v1, v1, [I

    fill-array-data v1, :array_0

    const/4 v2, 0x0

    aget v2, v1, v2

    const/4 v3, 0x1

    aget v3, v1, v3

    const/4 v4, 0x2

    aget v4, v1, v4

    const/4 v5, 0x3

    aget v5, v1, v5

    const/4 v6, 0x4

    aget v6, v1, v6

    const/4 v7, 0x5

    aget v7, v1, v7

    const/4 v8, 0x6

    aget v8, v1, v8

    const/4 v9, 0x7

    aget v1, v1, v9

    not-int v9, v2

    and-int/2addr v3, v9

    or-int/2addr v3, v4

    and-int/2addr v2, v5

    or-int/2addr v2, v6

    add-int/2addr v3, v2

    sub-int/2addr v3, v7

    add-int/2addr v8, v3

    const v2, 0x3a86d445

    rem-int/2addr v1, v2

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzar;->zzr()V

    xor-int/2addr v1, v8

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzar;->zza:I

    iput-wide p0, v0, Lcom/google/android/recaptcha/internal/zzar;->zzc:J

    return-object v0

    nop

    :array_0
    .array-data 4
        0x5bc9a827
        0x5b822a61
        0x413d527f
        0x1a9a2810
        0x207c16b9
        -0x74dfaf66
        0x1887578d
        0x44296c6d
        0x3a86d445
    .end array-data
.end method

.method public static zzc(D)Lcom/google/android/recaptcha/internal/zzar;
    .locals 10

    new-instance v0, Lcom/google/android/recaptcha/internal/zzar;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzar;-><init>()V

    const/16 v1, 0x9

    new-array v1, v1, [I

    fill-array-data v1, :array_0

    const/4 v2, 0x0

    aget v2, v1, v2

    const/4 v3, 0x1

    aget v3, v1, v3

    const/4 v4, 0x2

    aget v4, v1, v4

    const/4 v5, 0x3

    aget v5, v1, v5

    const/4 v6, 0x4

    aget v6, v1, v6

    const/4 v7, 0x5

    aget v7, v1, v7

    const/4 v8, 0x6

    aget v8, v1, v8

    const/4 v9, 0x7

    aget v1, v1, v9

    not-int v9, v2

    and-int/2addr v3, v9

    or-int/2addr v3, v4

    and-int/2addr v2, v5

    or-int/2addr v2, v6

    add-int/2addr v3, v2

    sub-int/2addr v3, v7

    add-int/2addr v8, v3

    const v2, 0x28168302

    rem-int/2addr v1, v2

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzar;->zzr()V

    xor-int/2addr v1, v8

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzar;->zza:I

    iput-wide p0, v0, Lcom/google/android/recaptcha/internal/zzar;->zzd:D

    return-object v0

    nop

    :array_0
    .array-data 4
        0x3a541011
        0x4934690e    # 738960.9f
        0x3d1e3ec9
        0x40205186
        0x3a983ee1
        -0x53630dfb
        0xb54e53b
        0x66bbb7e5
        0x28168302
    .end array-data
.end method

.method public static zzd(Lcom/google/android/recaptcha/internal/zzv;)Lcom/google/android/recaptcha/internal/zzar;
    .locals 10

    new-instance v0, Lcom/google/android/recaptcha/internal/zzar;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzar;-><init>()V

    const/16 v1, 0x9

    new-array v1, v1, [I

    fill-array-data v1, :array_0

    const/4 v2, 0x0

    aget v2, v1, v2

    const/4 v3, 0x1

    aget v3, v1, v3

    const/4 v4, 0x2

    aget v4, v1, v4

    const/4 v5, 0x3

    aget v5, v1, v5

    const/4 v6, 0x4

    aget v6, v1, v6

    const/4 v7, 0x5

    aget v7, v1, v7

    const/4 v8, 0x6

    aget v8, v1, v8

    const/4 v9, 0x7

    aget v1, v1, v9

    not-int v9, v2

    and-int/2addr v3, v9

    or-int/2addr v3, v4

    and-int/2addr v2, v5

    or-int/2addr v2, v6

    add-int/2addr v3, v2

    sub-int/2addr v3, v7

    add-int/2addr v8, v3

    const v2, 0xe3dfe6

    rem-int/2addr v1, v2

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzar;->zzr()V

    xor-int/2addr v1, v8

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzar;->zza:I

    iput-object p0, v0, Lcom/google/android/recaptcha/internal/zzar;->zze:Lcom/google/android/recaptcha/internal/zzv;

    return-object v0

    nop

    :array_0
    .array-data 4
        0x71db7e02
        0x50052904
        0x569b3dd3
        0xa040004
        0x5bd141c8
        -0x3b403bf8
        0x12f9357a
        0x30bb2b99
        0xe3dfe6
    .end array-data
.end method

.method public static zze(Ljava/util/List;)Lcom/google/android/recaptcha/internal/zzar;
    .locals 10

    new-instance v0, Lcom/google/android/recaptcha/internal/zzar;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzar;-><init>()V

    const/16 v1, 0x9

    new-array v1, v1, [I

    fill-array-data v1, :array_0

    const/4 v2, 0x0

    aget v2, v1, v2

    const/4 v3, 0x1

    aget v3, v1, v3

    const/4 v4, 0x2

    aget v4, v1, v4

    const/4 v5, 0x3

    aget v5, v1, v5

    const/4 v6, 0x4

    aget v6, v1, v6

    const/4 v7, 0x5

    aget v7, v1, v7

    const/4 v8, 0x6

    aget v8, v1, v8

    const/4 v9, 0x7

    aget v1, v1, v9

    not-int v9, v2

    and-int/2addr v3, v9

    or-int/2addr v3, v4

    and-int/2addr v2, v5

    or-int/2addr v2, v6

    add-int/2addr v3, v2

    sub-int/2addr v3, v7

    add-int/2addr v8, v3

    const v2, 0x3b7139dd

    rem-int/2addr v1, v2

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzar;->zzr()V

    xor-int/2addr v1, v8

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzar;->zza:I

    iput-object p0, v0, Lcom/google/android/recaptcha/internal/zzar;->zzf:Ljava/util/List;

    return-object v0

    nop

    :array_0
    .array-data 4
        0x169cad8d
        0x21e4238
        0x53a0a90d
        -0x7be13d4f
        -0x68be6377
        -0x170d8cd8
        0x1f2bea4e
        0x5cab38c6
        0x3b7139dd
    .end array-data
.end method

.method public static zzf(Lcom/google/android/recaptcha/internal/zzaj;)Lcom/google/android/recaptcha/internal/zzar;
    .locals 10

    new-instance v0, Lcom/google/android/recaptcha/internal/zzar;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzar;-><init>()V

    const/16 v1, 0x9

    new-array v1, v1, [I

    fill-array-data v1, :array_0

    const/4 v2, 0x0

    aget v2, v1, v2

    const/4 v3, 0x1

    aget v3, v1, v3

    const/4 v4, 0x2

    aget v4, v1, v4

    const/4 v5, 0x3

    aget v5, v1, v5

    const/4 v6, 0x4

    aget v6, v1, v6

    const/4 v7, 0x5

    aget v7, v1, v7

    const/4 v8, 0x6

    aget v8, v1, v8

    const/4 v9, 0x7

    aget v1, v1, v9

    not-int v9, v2

    and-int/2addr v3, v9

    or-int/2addr v3, v4

    and-int/2addr v2, v5

    or-int/2addr v2, v6

    add-int/2addr v3, v2

    sub-int/2addr v3, v7

    add-int/2addr v8, v3

    const v2, 0xbd6d5b5

    rem-int/2addr v1, v2

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzar;->zzr()V

    xor-int/2addr v1, v8

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzar;->zza:I

    iput-object p0, v0, Lcom/google/android/recaptcha/internal/zzar;->zzg:Lcom/google/android/recaptcha/internal/zzaj;

    return-object v0

    nop

    :array_0
    .array-data 4
        0x787aa59c
        0x31291088
        0x3120c4d
        0x3c295084
        0xf54e06d
        0x644e6609
        0x2395a7ff
        0x3d096bbf
        0xbd6d5b5
    .end array-data
.end method

.method public static zzg(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzar;
    .locals 4

    instance-of v0, p0, Ljava/lang/Long;

    if-eqz v0, :cond_0

    check-cast p0, Ljava/lang/Long;

    invoke-virtual {p0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzar;->zzb(J)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :cond_0
    instance-of v0, p0, Ljava/lang/Boolean;

    if-eqz v0, :cond_2

    check-cast p0, Ljava/lang/Boolean;

    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    const/4 v0, 0x1

    if-eq v0, p0, :cond_1

    const-wide/16 v0, 0x0

    goto :goto_0

    :cond_1
    const-wide/16 v0, 0x1

    :goto_0
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzar;->zzb(J)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :cond_2
    instance-of v0, p0, Ljava/lang/Integer;

    if-eqz v0, :cond_3

    check-cast p0, Ljava/lang/Integer;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    int-to-long v0, p0

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzar;->zzb(J)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :cond_3
    instance-of v0, p0, Ljava/lang/Double;

    if-eqz v0, :cond_4

    check-cast p0, Ljava/lang/Double;

    invoke-virtual {p0}, Ljava/lang/Double;->doubleValue()D

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzar;->zzc(D)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :cond_4
    instance-of v0, p0, Ljava/lang/Float;

    if-eqz v0, :cond_5

    check-cast p0, Ljava/lang/Float;

    invoke-virtual {p0}, Ljava/lang/Float;->floatValue()F

    move-result p0

    float-to-double v0, p0

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzar;->zzc(D)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :cond_5
    instance-of v0, p0, Ljava/lang/Short;

    if-eqz v0, :cond_6

    check-cast p0, Ljava/lang/Short;

    invoke-virtual {p0}, Ljava/lang/Short;->shortValue()S

    move-result p0

    int-to-long v0, p0

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzar;->zzb(J)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :cond_6
    instance-of v0, p0, Ljava/lang/Byte;

    if-eqz v0, :cond_7

    check-cast p0, Ljava/lang/Byte;

    invoke-virtual {p0}, Ljava/lang/Byte;->byteValue()B

    move-result p0

    int-to-long v0, p0

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzar;->zzb(J)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :cond_7
    instance-of v0, p0, Lcom/google/android/recaptcha/internal/zzv;

    if-eqz v0, :cond_8

    check-cast p0, Lcom/google/android/recaptcha/internal/zzv;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzd(Lcom/google/android/recaptcha/internal/zzv;)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :cond_8
    instance-of v0, p0, Ljava/lang/String;

    if-eqz v0, :cond_9

    check-cast p0, Ljava/lang/String;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzv;->zzf(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzv;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzd(Lcom/google/android/recaptcha/internal/zzv;)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :cond_9
    instance-of v0, p0, Ljava/util/ArrayList;

    if-eqz v0, :cond_b

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    check-cast p0, Ljava/util/ArrayList;

    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v1

    const/4 v2, 0x0

    :goto_1
    if-ge v2, v1, :cond_a

    invoke-interface {p0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzar;->zzg(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object v3

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_a
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzar;->zze(Ljava/util/List;)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :cond_b
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzar;->zza(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0
.end method

.method public static zzj(Lcom/google/android/recaptcha/internal/zzar;)Lcom/google/android/recaptcha/internal/zzar;
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

    const v1, 0x8185827

    rem-int/2addr v0, v1

    :try_start_0
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzar;->zza:I
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzao; {:try_start_0 .. :try_end_0} :catch_0

    xor-int/2addr v0, v7

    add-int/2addr v0, v1

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    packed-switch v0, :pswitch_data_0

    const-string p0, "HkezqgQcPni/TE/NwjgYPC5H6Q2JRdEp275wOg=="

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lokio/Buffer$$ExternalSyntheticBUOutline2;->m(Ljava/lang/Object;)V

    return-object v2

    :pswitch_0
    :try_start_1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzq()D

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzar;->zzc(D)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :pswitch_1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzp()Lcom/google/android/recaptcha/internal/zzaj;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzf(Lcom/google/android/recaptcha/internal/zzaj;)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :pswitch_2
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzo()Ljava/util/List;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzar;

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzar;->zzj(Lcom/google/android/recaptcha/internal/zzar;)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_0
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzar;->zze(Ljava/util/List;)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :pswitch_3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzn()Lcom/google/android/recaptcha/internal/zzv;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzd(Lcom/google/android/recaptcha/internal/zzv;)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :pswitch_4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzm()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzar;->zzb(J)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :pswitch_5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzl()Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzar;->zza(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :pswitch_6
    new-instance p0, Lcom/google/android/recaptcha/internal/zzar;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzar;-><init>()V

    return-object p0

    :cond_1
    throw v2
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzao; {:try_start_1 .. :try_end_1} :catch_0

    :catch_0
    move-exception p0

    new-instance v0, Ljava/lang/AssertionError;

    const-string v1, "CEiv6BFfPnitUE+D"

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1, p0}, Ljava/lang/AssertionError;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v0

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch

    :array_0
    .array-data 4
        0x15eff133
        0x5ad784df
        0x688b7a4a
        -0x49ab7b6b
        -0x52fce6fe
        0x301c1fb9
        0x1a65b56
        0x67b6ce55
        0x8185827
    .end array-data
.end method

.method private final zzr()V
    .locals 2

    const/4 v0, 0x1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzar;->zza:I

    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/google/android/recaptcha/internal/zzar;->zzc:J

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzar;->zzb:Ljava/lang/Object;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzar;->zze:Lcom/google/android/recaptcha/internal/zzv;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzar;->zzf:Ljava/util/List;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzar;->zzg:Lcom/google/android/recaptcha/internal/zzaj;

    return-void
.end method

.method private final zzs(I)V
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzar;->zza:I

    if-ne p1, p0, :cond_0

    return-void

    :cond_0
    new-instance p0, Lcom/google/android/recaptcha/internal/zzao;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzao;-><init>()V

    throw p0
.end method


# virtual methods
.method public final zzh()Ljava/lang/Object;
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

    const v1, 0x2a86c699

    rem-int/2addr v0, v1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzar;->zza:I

    xor-int/2addr v0, v7

    add-int/2addr v0, v1

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    packed-switch v0, :pswitch_data_0

    const-string p0, "HkezqgQcPni/TE/NwjgYPC5H6Q2JRdEp275wOg=="

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lokio/Buffer$$ExternalSyntheticBUOutline2;->m(Ljava/lang/Object;)V

    return-object v2

    :pswitch_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzq()D

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object p0

    return-object p0

    :pswitch_1
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzo()Ljava/util/List;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzar;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzar;->zzh()Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_0
    return-object v0

    :pswitch_2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzn()Lcom/google/android/recaptcha/internal/zzv;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzv;->zza()[B

    move-result-object p0

    return-object p0

    :pswitch_3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzm()J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    return-object p0

    :pswitch_4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzar;->zzl()Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :pswitch_5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzao;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzao;-><init>()V

    throw p0

    :cond_1
    throw v2

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_5
        :pswitch_0
    .end packed-switch

    :array_0
    .array-data 4
        0x740aa9ba
        0x5d9545e1
        0xe8d733b
        -0xeed7930
        -0x5f5c4cc5
        0x7090f76
        0x4e340a
        0x5b873d67
        0x2a86c699
    .end array-data
.end method

.method public final zzi(Ljava/lang/Class;)Ljava/lang/Object;
    .locals 19

    move-object/from16 v0, p1

    const v1, 0x5d1706e

    not-int v2, v1

    const v3, 0x9d5014b

    and-int/2addr v2, v3

    const v3, 0x6d03c88

    or-int/2addr v2, v3

    const v3, 0x9050153

    and-int/2addr v1, v3

    const v3, 0x10c89e38

    or-int/2addr v1, v3

    add-int/2addr v2, v1

    const v1, 0x1b9ad2f3

    sub-int/2addr v2, v1

    const v1, 0x5dc4c860

    const v3, 0x62c7d160

    rem-int/2addr v3, v1

    const v1, 0x6558b9b2

    not-int v4, v1

    const v5, 0x23464006

    and-int/2addr v4, v5

    const v5, 0x85c0c63

    or-int/2addr v4, v5

    const v5, 0x23024225

    and-int/2addr v1, v5

    const v5, 0x14b58ae9

    or-int/2addr v1, v5

    add-int/2addr v4, v1

    const v1, 0x3f810f15

    sub-int/2addr v4, v1

    const v1, 0xc1ab6b

    const v5, 0x548c1b88

    rem-int/2addr v5, v1

    const v1, 0x4fe3afa2

    not-int v6, v1

    const v7, 0x5da0a49f

    and-int/2addr v6, v7

    const v7, 0x2a011f20

    or-int/2addr v6, v7

    const v7, -0xa5f5d01

    and-int/2addr v1, v7

    const v7, -0x5da2ada0    # -2.9994694E-18f

    or-int/2addr v1, v7

    add-int/2addr v6, v1

    const v1, 0x2ad08ce6

    sub-int/2addr v6, v1

    const v1, 0xcb478a2

    const v7, 0x550c4e92

    rem-int/2addr v7, v1

    move-object/from16 v1, p0

    iget v8, v1, Lcom/google/android/recaptcha/internal/zzar;->zza:I

    xor-int/2addr v6, v7

    add-int/2addr v6, v8

    if-eqz v8, :cond_22

    xor-int/2addr v4, v5

    xor-int/2addr v2, v3

    sget-object v5, Ljava/lang/Double;->TYPE:Ljava/lang/Class;

    const-class v8, Ljava/lang/Double;

    sget-object v9, Ljava/lang/Short;->TYPE:Ljava/lang/Class;

    const-class v10, Ljava/lang/Short;

    sget-object v11, Ljava/lang/Byte;->TYPE:Ljava/lang/Class;

    const-class v12, Ljava/lang/Byte;

    sget-object v13, Ljava/lang/Long;->TYPE:Ljava/lang/Class;

    const-class v14, Ljava/lang/Long;

    sget-object v15, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;

    const-class v3, Ljava/lang/Integer;

    const/16 v16, 0x0

    sget-object v7, Ljava/lang/Float;->TYPE:Ljava/lang/Class;

    const-class v1, Ljava/lang/Float;

    move/from16 v17, v2

    const-class v2, Ljava/lang/Object;

    packed-switch v6, :pswitch_data_0

    const-string v0, "HkezqgQcPni/TE/NwjgYPC5H6Q2JRdEp275wOg=="

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lokio/Buffer$$ExternalSyntheticBUOutline2;->m(Ljava/lang/Object;)V

    return-object v16

    :pswitch_0
    move v6, v4

    move-object/from16 v18, v5

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzar;->zzq()D

    move-result-wide v4

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_b

    invoke-virtual {v0, v7}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    goto/16 :goto_5

    :cond_0
    invoke-virtual {v0, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_a

    invoke-virtual {v0, v15}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    goto :goto_4

    :cond_1
    invoke-virtual {v0, v14}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_9

    invoke-virtual {v0, v13}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    goto :goto_3

    :cond_2
    invoke-virtual {v0, v12}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_8

    invoke-virtual {v0, v11}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_3

    goto :goto_2

    :cond_3
    invoke-virtual {v0, v10}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_7

    invoke-virtual {v0, v9}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_4

    goto :goto_1

    :cond_4
    invoke-virtual {v0, v8}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_6

    move-object/from16 v1, v18

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_5

    goto :goto_0

    :cond_5
    invoke-virtual {v0, v2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_21

    :cond_6
    :goto_0
    invoke-static {v4, v5}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object v0

    return-object v0

    :cond_7
    :goto_1
    double-to-int v0, v4

    shl-int v0, v0, v17

    shr-int v0, v0, v17

    int-to-short v0, v0

    invoke-static {v0}, Ljava/lang/Short;->valueOf(S)Ljava/lang/Short;

    move-result-object v0

    return-object v0

    :cond_8
    :goto_2
    double-to-int v0, v4

    shl-int/2addr v0, v6

    shr-int/2addr v0, v6

    int-to-byte v0, v0

    invoke-static {v0}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object v0

    return-object v0

    :cond_9
    :goto_3
    double-to-long v0, v4

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    return-object v0

    :cond_a
    :goto_4
    double-to-int v0, v4

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    return-object v0

    :cond_b
    :goto_5
    double-to-float v0, v4

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    return-object v0

    :pswitch_1
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzar;->zzp()Lcom/google/android/recaptcha/internal/zzaj;

    move-result-object v0

    return-object v0

    :pswitch_2
    const-class v1, Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_e

    invoke-virtual {v0, v2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_e

    const-class v1, Ljava/util/AbstractList;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_e

    const-class v1, Ljava/util/AbstractCollection;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_e

    const-class v1, Ljava/io/Serializable;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_e

    const-class v1, Ljava/lang/Cloneable;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_e

    const-class v1, Ljava/lang/Iterable;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_e

    const-class v1, Ljava/util/Collection;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_e

    const-class v1, Ljava/util/List;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_e

    const-class v1, Ljava/util/RandomAccess;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_c

    goto :goto_7

    :cond_c
    invoke-virtual {v0}, Ljava/lang/Class;->isArray()Z

    move-result v1

    if-eqz v1, :cond_21

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzar;->zzo()Ljava/util/List;

    move-result-object v1

    invoke-virtual {v0}, Ljava/lang/Class;->getComponentType()Ljava/lang/Class;

    move-result-object v0

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v2

    invoke-static {v0, v2}, Ljava/lang/reflect/Array;->newInstance(Ljava/lang/Class;I)Ljava/lang/Object;

    move-result-object v2

    const/4 v3, 0x0

    :goto_6
    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v4

    if-ge v3, v4, :cond_d

    invoke-interface {v1, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/google/android/recaptcha/internal/zzar;

    invoke-virtual {v4, v0}, Lcom/google/android/recaptcha/internal/zzar;->zzi(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v4

    invoke-static {v2, v3, v4}, Ljava/lang/reflect/Array;->set(Ljava/lang/Object;ILjava/lang/Object;)V

    add-int/lit8 v3, v3, 0x1

    goto :goto_6

    :cond_d
    return-object v2

    :cond_e
    :goto_7
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzar;->zzh()Ljava/lang/Object;

    move-result-object v0

    return-object v0

    :pswitch_3
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzar;->zzn()Lcom/google/android/recaptcha/internal/zzv;

    move-result-object v1

    const-class v3, Lcom/google/android/recaptcha/internal/zzv;

    invoke-virtual {v0, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_f

    return-object v1

    :cond_f
    invoke-virtual {v0, v2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_10

    goto :goto_8

    :cond_10
    const-class v2, Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_11

    const-class v2, [B

    invoke-virtual {v0, v2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_21

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzv;->zza()[B

    move-result-object v0

    return-object v0

    :cond_11
    :goto_8
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzv;->zzc()Ljava/lang/String;

    move-result-object v0

    return-object v0

    :pswitch_4
    move/from16 v16, v4

    move-object v4, v5

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzar;->zzm()J

    move-result-wide v5

    invoke-virtual {v0, v12}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v12

    if-nez v12, :cond_20

    invoke-virtual {v0, v11}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v11

    if-eqz v11, :cond_12

    goto/16 :goto_10

    :cond_12
    invoke-virtual {v0, v10}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-nez v10, :cond_1f

    invoke-virtual {v0, v9}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_13

    goto/16 :goto_f

    :cond_13
    invoke-virtual {v0, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_1e

    invoke-virtual {v0, v15}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_14

    goto/16 :goto_e

    :cond_14
    invoke-virtual {v0, v14}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_1d

    invoke-virtual {v0, v13}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_15

    goto :goto_d

    :cond_15
    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1c

    invoke-virtual {v0, v7}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_16

    goto :goto_c

    :cond_16
    invoke-virtual {v0, v8}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1b

    invoke-virtual {v0, v4}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_17

    goto :goto_b

    :cond_17
    const-class v1, Ljava/lang/Boolean;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_19

    sget-object v1, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_18

    goto :goto_9

    :cond_18
    const-class v1, Ljava/lang/Character;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_20

    sget-object v1, Ljava/lang/Character;->TYPE:Ljava/lang/Class;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_20

    invoke-virtual {v0, v2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_21

    goto :goto_d

    :cond_19
    :goto_9
    const-wide/16 v0, 0x0

    cmp-long v0, v5, v0

    if-eqz v0, :cond_1a

    const/4 v3, 0x1

    goto :goto_a

    :cond_1a
    const/4 v3, 0x0

    :goto_a
    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    return-object v0

    :cond_1b
    :goto_b
    long-to-double v0, v5

    invoke-static {v0, v1}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object v0

    return-object v0

    :cond_1c
    :goto_c
    long-to-float v0, v5

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    return-object v0

    :cond_1d
    :goto_d
    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    return-object v0

    :cond_1e
    :goto_e
    invoke-static {v5, v6}, Ljava/lang/Math;->toIntExact(J)I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    return-object v0

    :cond_1f
    :goto_f
    long-to-int v0, v5

    shl-int v0, v0, v17

    shr-int v0, v0, v17

    int-to-short v0, v0

    invoke-static {v0}, Ljava/lang/Short;->valueOf(S)Ljava/lang/Short;

    move-result-object v0

    return-object v0

    :cond_20
    :goto_10
    long-to-int v0, v5

    shl-int v0, v0, v16

    shr-int v0, v0, v16

    int-to-byte v0, v0

    invoke-static {v0}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object v0

    return-object v0

    :pswitch_5
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzar;->zzl()Ljava/lang/Object;

    move-result-object v0

    return-object v0

    :cond_21
    :pswitch_6
    new-instance v0, Lcom/google/android/recaptcha/internal/zzao;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzao;-><init>()V

    throw v0

    :cond_22
    const/16 v16, 0x0

    throw v16

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zzk(Ljava/io/OutputStream;)V
    .locals 22

    move-object/from16 v0, p1

    const/16 v1, 0x9

    new-array v1, v1, [J

    fill-array-data v1, :array_0

    const/4 v2, 0x0

    aget-wide v3, v1, v2

    const/4 v5, 0x1

    aget-wide v6, v1, v5

    const/4 v8, 0x2

    aget-wide v8, v1, v8

    const/4 v10, 0x3

    aget-wide v10, v1, v10

    const/4 v12, 0x4

    aget-wide v12, v1, v12

    const/4 v14, 0x5

    aget-wide v14, v1, v14

    const/16 v16, 0x6

    aget-wide v16, v1, v16

    const/16 v18, 0x7

    aget-wide v18, v1, v18

    move-wide/from16 v20, v6

    not-long v5, v3

    and-long v5, v5, v20

    or-long/2addr v5, v8

    and-long/2addr v3, v10

    or-long/2addr v3, v12

    add-long/2addr v5, v3

    sub-long/2addr v5, v14

    add-long v16, v16, v5

    const-wide/32 v3, 0x56d1953d

    rem-long v18, v18, v3

    const v3, 0x7d7cf8fb

    not-int v4, v3

    const v5, 0x7a2e6b01

    and-int/2addr v4, v5

    const v5, 0x653a841f

    or-int/2addr v4, v5

    const v5, -0x61fb9500

    and-int/2addr v3, v5

    const v5, -0x7a2cffd2

    or-int/2addr v3, v5

    add-int/2addr v4, v3

    const v3, 0x7e05b58

    sub-int/2addr v4, v3

    const v3, 0x3c5cb001

    const v5, 0x3f2b1c0b

    rem-int/2addr v5, v3

    const v3, 0x467cfb34

    not-int v6, v3

    const v7, 0x7e9e8288

    and-int/2addr v6, v7

    const v7, 0x4b9a00eb    # 2.0185558E7f

    or-int/2addr v6, v7

    const v7, 0x3404a250

    and-int/2addr v3, v7

    const v7, 0x437b74d7

    or-int/2addr v3, v7

    add-int/2addr v6, v3

    const v3, -0x3f390aa7

    sub-int/2addr v6, v3

    const v3, 0x1ecdffd2

    const v7, 0x3fef020e

    rem-int/2addr v7, v3

    const v3, 0x1882c9d

    not-int v8, v3

    const v9, 0x6c088b04

    and-int/2addr v8, v9

    const v9, 0x233d59b3

    or-int/2addr v8, v9

    const v9, 0x4cc28644    # 1.0198685E8f

    and-int/2addr v3, v9

    const v9, 0x3da5dea

    or-int/2addr v3, v9

    add-int/2addr v8, v3

    const v3, 0x70830785

    sub-int/2addr v8, v3

    const v3, 0x2ca49a6

    const v9, 0x3d2d3cbc

    rem-int/2addr v9, v3

    move-object/from16 v3, p0

    iget v10, v3, Lcom/google/android/recaptcha/internal/zzar;->zza:I

    xor-int/2addr v4, v5

    add-int/2addr v4, v10

    if-eqz v10, :cond_2

    packed-switch v4, :pswitch_data_0

    goto/16 :goto_2

    :pswitch_0
    xor-int v1, v6, v7

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzar;->zzq()D

    move-result-wide v3

    new-instance v5, Lcom/google/android/recaptcha/internal/zzaq;

    invoke-direct {v5, v0, v1}, Lcom/google/android/recaptcha/internal/zzaq;-><init>(Ljava/io/OutputStream;I)V

    const v0, 0x5a48d2f8

    not-int v1, v0

    const v6, 0x4ef469a

    and-int/2addr v1, v6

    const v6, 0x1a18b166

    or-int/2addr v1, v6

    const v6, 0x55e76698

    and-int/2addr v0, v6

    const v6, 0x53002942

    or-int/2addr v0, v6

    add-int/2addr v1, v0

    const v0, 0x71ec62d6

    sub-int/2addr v1, v0

    const v0, 0x1b01f4

    const v6, 0x49d2139e

    rem-int/2addr v6, v0

    invoke-static {v3, v4}, Ljava/lang/Double;->doubleToRawLongBits(D)J

    move-result-wide v3

    xor-int v0, v1, v6

    invoke-static {v0}, Ljava/nio/ByteBuffer;->allocate(I)Ljava/nio/ByteBuffer;

    move-result-object v0

    sget-object v1, Ljava/nio/ByteOrder;->LITTLE_ENDIAN:Ljava/nio/ByteOrder;

    invoke-virtual {v0, v1}, Ljava/nio/ByteBuffer;->order(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;

    invoke-virtual {v0, v3, v4}, Ljava/nio/ByteBuffer;->putLong(J)Ljava/nio/ByteBuffer;

    invoke-virtual {v0}, Ljava/nio/ByteBuffer;->array()[B

    move-result-object v1

    array-length v3, v1

    :goto_0
    if-ge v2, v3, :cond_0

    aget-byte v4, v1, v2

    invoke-virtual {v5, v4}, Lcom/google/android/recaptcha/internal/zzaq;->zza(B)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_0
    invoke-virtual {v0}, Ljava/nio/ByteBuffer;->array()[B

    move-result-object v0

    array-length v0, v0

    return-void

    :pswitch_1
    xor-int v2, v8, v9

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzar;->zzo()Ljava/util/List;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/List;->size()I

    move-result v4

    int-to-long v4, v4

    .line 1
    new-instance v6, Lcom/google/android/recaptcha/internal/zzaq;

    invoke-direct {v6, v0, v2}, Lcom/google/android/recaptcha/internal/zzaq;-><init>(Ljava/io/OutputStream;I)V

    const/4 v1, 0x1

    invoke-static {v4, v5, v6, v1}, Lcom/google/android/recaptcha/internal/zzr;->zzb(JLcom/google/android/recaptcha/internal/zzaq;Z)I

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzar;

    invoke-virtual {v2, v0}, Lcom/google/android/recaptcha/internal/zzar;->zzk(Ljava/io/OutputStream;)V

    goto :goto_1

    :cond_1
    :goto_2
    return-void

    :pswitch_2
    xor-long v4, v16, v18

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzar;->zzn()Lcom/google/android/recaptcha/internal/zzv;

    move-result-object v3

    iget-object v3, v3, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length v6, v3

    new-instance v7, Lcom/google/android/recaptcha/internal/zzaq;

    invoke-direct {v7, v0, v2}, Lcom/google/android/recaptcha/internal/zzaq;-><init>(Ljava/io/OutputStream;I)V

    int-to-long v8, v6

    mul-long/2addr v8, v4

    const/4 v1, 0x1

    invoke-static {v8, v9, v7, v1}, Lcom/google/android/recaptcha/internal/zzr;->zzb(JLcom/google/android/recaptcha/internal/zzaq;Z)I

    invoke-virtual {v0, v3}, Ljava/io/OutputStream;->write([B)V

    return-void

    :pswitch_3
    const/4 v1, 0x1

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzar;->zzm()J

    move-result-wide v2

    new-instance v4, Lcom/google/android/recaptcha/internal/zzaq;

    invoke-direct {v4, v0, v1}, Lcom/google/android/recaptcha/internal/zzaq;-><init>(Ljava/io/OutputStream;I)V

    invoke-static {v2, v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzr;->zzb(JLcom/google/android/recaptcha/internal/zzaq;Z)I

    return-void

    :pswitch_4
    new-instance v0, Lcom/google/android/recaptcha/internal/zzao;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzao;-><init>()V

    throw v0

    :cond_2
    const/4 v0, 0x0

    throw v0

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_4
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_4
        :pswitch_0
    .end packed-switch

    :array_0
    .array-data 8
        0x4154d83e
        0x145b684
        0xc4b6b39
        0x51849484
        0x50992231
        0x56a1124d
        0x19084cf6
        0x7721f1dc
        0x56d1953d
    .end array-data
.end method

.method public final zzl()Ljava/lang/Object;
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

    const v1, 0x5f4208c

    rem-int/2addr v0, v1

    xor-int/2addr v0, v7

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzar;->zzs(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzar;->zzb:Ljava/lang/Object;

    return-object p0

    :array_0
    .array-data 4
        0x25ef81d5
        0x15b1f100
        0x40166023
        0x7fe99380
        0x6a4c42ca
        -0x15b4412d
        0x2e326d9a
        0x5d38caea
        0x5f4208c
    .end array-data
.end method

.method public final zzm()J
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

    const v1, 0x4a35bf85    # 2977761.2f

    rem-int/2addr v0, v1

    xor-int/2addr v0, v7

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzar;->zzs(I)V

    iget-wide v0, p0, Lcom/google/android/recaptcha/internal/zzar;->zzc:J

    return-wide v0

    :array_0
    .array-data 4
        0x1cfac1a
        0x45f16508
        0x1226a935
        0x45d14c68
        0x220a864
        0x7b5708e1
        0x32e8ddfc
        0x5be02a44
        0x4a35bf85    # 2977761.2f
    .end array-data
.end method

.method public final zzn()Lcom/google/android/recaptcha/internal/zzv;
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

    const v1, 0x41123f79

    rem-int/2addr v0, v1

    xor-int/2addr v0, v7

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzar;->zzs(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzar;->zze:Lcom/google/android/recaptcha/internal/zzv;

    return-object p0

    :array_0
    .array-data 4
        0x4c68ed90    # 6.106067E7f
        0x573ad640
        0x328019e1
        0x653be602
        0x388539d3
        -0x5ca4ba04
        0x19076430    # 6.999569E-24f
        0x66fe7765
        0x41123f79
    .end array-data
.end method

.method public final zzo()Ljava/util/List;
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

    const v1, 0x2f298a4a

    rem-int/2addr v0, v1

    xor-int/2addr v0, v7

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzar;->zzs(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzar;->zzf:Ljava/util/List;

    return-object p0

    :array_0
    .array-data 4
        0x62a2ea91
        0x3906d659
        0x300724
        0x3d06d07d
        0x46912aa4
        -0x750a618e
        0x14fc67d6
        0x38f855ca
        0x2f298a4a
    .end array-data
.end method

.method public final zzp()Lcom/google/android/recaptcha/internal/zzaj;
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

    const v1, 0x33ed493    # 5.6080005E-37f

    rem-int/2addr v0, v1

    xor-int/2addr v0, v7

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzar;->zzs(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzar;->zzg:Lcom/google/android/recaptcha/internal/zzaj;

    return-object p0

    :array_0
    .array-data 4
        0x84467d2
        0x4e272f0a    # 7.0121946E8f
        0x4179b104
        0xe060e0a
        0x207144
        0x7c197ae2
        0x2e67a586
        0x25a17d41
        0x33ed493    # 5.6080005E-37f
    .end array-data
.end method

.method public final zzq()D
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

    const v1, 0x1cd484d5

    rem-int/2addr v0, v1

    xor-int/2addr v0, v7

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzar;->zzs(I)V

    iget-wide v0, p0, Lcom/google/android/recaptcha/internal/zzar;->zzd:D

    return-wide v0

    :array_0
    .array-data 4
        0x51194ed1
        0xa3bb3a
        0x771952d7
        0x28aea928
        0x2c5c42c2
        -0x48724268
        0x1a54d7bc
        0x5d5ce761
        0x1cd484d5
    .end array-data
.end method
