.class public final Lcom/google/android/recaptcha/internal/zzr;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zza(J)Lcom/google/android/recaptcha/internal/zzar;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzq;

    invoke-direct {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzq;-><init>(J)V

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzar;->zzf(Lcom/google/android/recaptcha/internal/zzaj;)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0
.end method

.method public static zzb(JLcom/google/android/recaptcha/internal/zzaq;Z)I
    .locals 23

    const/16 v0, 0x9

    new-array v0, v0, [J

    fill-array-data v0, :array_0

    const/4 v1, 0x0

    aget-wide v2, v0, v1

    const/4 v4, 0x1

    aget-wide v5, v0, v4

    const/4 v7, 0x2

    aget-wide v7, v0, v7

    const/4 v9, 0x3

    aget-wide v9, v0, v9

    const/4 v11, 0x4

    aget-wide v11, v0, v11

    const/4 v13, 0x5

    aget-wide v13, v0, v13

    const/4 v15, 0x6

    aget-wide v15, v0, v15

    const/16 v17, 0x7

    aget-wide v17, v0, v17

    move-wide/from16 v19, v5

    not-long v4, v2

    and-long v4, v4, v19

    or-long/2addr v4, v7

    and-long/2addr v2, v9

    or-long/2addr v2, v11

    add-long/2addr v4, v2

    sub-long/2addr v4, v13

    add-long/2addr v15, v4

    const-wide/32 v2, 0xcbb32be

    rem-long v17, v17, v2

    const v2, 0x5be5cef1

    not-int v3, v2

    const v4, 0x30218034

    and-int/2addr v3, v4

    const v4, 0x696b7f1c

    or-int/2addr v3, v4

    const v4, 0x1c04b5a8

    and-int/2addr v2, v4

    const v4, 0x6c4f7ddd

    or-int/2addr v2, v4

    add-int/2addr v3, v2

    const v2, -0x2fb8ca78

    sub-int/2addr v3, v2

    const v2, 0x18836c53

    const v4, 0x5efe8c82

    rem-int/2addr v4, v2

    const v2, 0x4be399d1    # 2.9832098E7f

    not-int v5, v2

    const v6, 0x30413c89

    and-int/2addr v5, v6

    const v6, 0x1f31a42a

    or-int/2addr v5, v6

    const v6, 0x685818e5

    and-int/2addr v2, v6

    const v6, 0x4bbb6374    # 2.4561384E7f

    or-int/2addr v2, v6

    add-int/2addr v5, v2

    const v2, 0x7c2c5cd9

    sub-int/2addr v5, v2

    const v2, 0x33d2971b

    const v6, 0x42d35a5c

    rem-int/2addr v6, v2

    const v2, 0x401d6aed

    not-int v7, v2

    const v8, 0x202040f

    and-int/2addr v7, v8

    const v8, 0x36643b61

    or-int/2addr v7, v8

    const v8, 0x103e241e

    and-int/2addr v2, v8

    const v8, 0x1c7cfb70

    or-int/2addr v2, v8

    add-int/2addr v7, v2

    const v2, 0x5203e2ab

    sub-int/2addr v7, v2

    const v2, 0x4e4add4

    const v8, 0x23201980

    rem-int/2addr v8, v2

    if-eqz p3, :cond_0

    const v2, 0x38f6d910

    not-int v9, v2

    const v10, 0x66010924

    and-int/2addr v9, v10

    const v10, 0x38fe7010

    or-int/2addr v9, v10

    const v10, 0x47012b24

    and-int/2addr v2, v10

    const v10, 0x21387643

    or-int/2addr v2, v10

    add-int/2addr v9, v2

    const v2, -0x77c2b63c

    sub-int/2addr v9, v2

    const v2, 0x210c927a

    const v10, 0x39073806

    rem-int/2addr v10, v2

    add-long v11, p0, p0

    xor-int v2, v9, v10

    shr-long v9, p0, v2

    xor-long/2addr v9, v11

    goto :goto_0

    :cond_0
    move-wide/from16 v9, p0

    :goto_0
    const/4 v2, 0x1

    :goto_1
    xor-int v11, v5, v6

    xor-long v12, v15, v17

    ushr-long v19, v9, v11

    const-wide/16 v21, 0x0

    cmp-long v11, v19, v21

    if-nez v11, :cond_1

    if-gez v2, :cond_2

    :cond_1
    const/4 v11, 0x1

    goto :goto_2

    :cond_2
    move v11, v1

    :goto_2
    and-long/2addr v9, v12

    long-to-int v9, v9

    if-eqz v11, :cond_3

    xor-int v10, v7, v8

    xor-int v12, v3, v4

    or-int/2addr v9, v10

    shl-int/2addr v9, v12

    shr-int/2addr v9, v12

    :cond_3
    int-to-byte v9, v9

    move-object/from16 v10, p2

    invoke-virtual {v10, v9}, Lcom/google/android/recaptcha/internal/zzaq;->zza(B)V

    if-nez v11, :cond_4

    return v2

    :cond_4
    add-int/lit8 v2, v2, 0x1

    move-wide/from16 v9, v19

    goto :goto_1

    nop

    :array_0
    .array-data 8
        0x5b095029
        0x39386613
        0x28035100
        0x113a2617
        0x87494c
        0x5adeafac
        0x2c37de6b
        0x3e08ba59
        0xcbb32be
    .end array-data
.end method
