.class public abstract Lcom/google/android/recaptcha/internal/zzair;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final synthetic $r8$clinit:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    sget-object v0, Ljava/nio/charset/StandardCharsets;->UTF_16:Ljava/nio/charset/Charset;

    return-void
.end method

.method public static zza(II)I
    .locals 1

    rem-int/lit8 v0, p0, 0x2

    if-nez v0, :cond_0

    not-int v0, p1

    and-int/2addr v0, p0

    not-int p0, p0

    and-int/2addr p0, p1

    or-int/2addr p0, v0

    return p0

    :cond_0
    or-int v0, p0, p1

    and-int/2addr p0, p1

    sub-int/2addr v0, p0

    return v0
.end method

.method public static zzb([B[B[B)[B
    .locals 19

    move-object/from16 v0, p0

    move-object/from16 v6, p1

    move-object/from16 v7, p2

    const v1, 0x66513c8

    const v2, 0x14275b8e

    const v3, 0x1e77469f

    const v4, 0x4c275a94    # 4.38708E7f

    .line 1
    filled-new-array {v3, v4, v1, v2}, [I

    move-result-object v5

    array-length v1, v6

    const/16 v2, 0x20

    const/4 v3, 0x0

    if-ne v1, v2, :cond_8

    .line 2
    array-length v1, v7

    const/16 v11, 0xc

    if-ne v1, v11, :cond_7

    const/16 v12, 0x10

    .line 3
    new-array v9, v12, [I

    const/4 v13, 0x0

    move v1, v13

    :goto_0
    const/4 v2, 0x4

    if-ge v1, v2, :cond_0

    .line 4
    aget v2, v5, v1

    const v3, 0x7f073efa

    invoke-static {v2, v3}, Lcom/google/android/recaptcha/internal/zzair;->zza(II)I

    move-result v2

    aput v2, v9, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    move v1, v2

    :goto_1
    if-ge v1, v11, :cond_1

    add-int/lit8 v3, v1, -0x4

    mul-int/2addr v3, v2

    .line 5
    invoke-static {v6, v3}, Lcom/google/android/recaptcha/internal/zzair;->zze([BI)I

    move-result v3

    aput v3, v9, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    :cond_1
    const/4 v1, 0x1

    aput v1, v9, v11

    const/16 v3, 0xd

    :goto_2
    if-ge v3, v12, :cond_2

    add-int/lit8 v4, v3, -0xd

    mul-int/2addr v4, v2

    .line 6
    invoke-static {v7, v4}, Lcom/google/android/recaptcha/internal/zzair;->zze([BI)I

    move-result v4

    aput v4, v9, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_2

    :cond_2
    new-array v10, v12, [I

    .line 7
    invoke-static {v9, v13, v10, v13, v12}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    array-length v14, v0

    new-array v15, v14, [B

    move v8, v1

    move/from16 v16, v13

    move v1, v14

    :goto_3
    if-lez v1, :cond_6

    .line 8
    invoke-static {v10, v13, v9, v13, v12}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    aput v8, v9, v11

    move v2, v13

    :goto_4
    const/16 v3, 0xa

    if-ge v2, v3, :cond_3

    const/16 v3, 0x8

    const/16 v4, 0xc

    move/from16 v17, v1

    const/4 v1, 0x0

    move/from16 v18, v2

    const/4 v2, 0x4

    move/from16 v11, v17

    .line 9
    invoke-static/range {v1 .. v10}, Lcom/google/android/recaptcha/internal/zzair;->zzd(IIII[I[B[BI[I[I)V

    const/16 v3, 0x9

    const/16 v4, 0xd

    const/4 v1, 0x1

    const/4 v2, 0x5

    move-object/from16 v6, p1

    move-object/from16 v7, p2

    .line 10
    invoke-static/range {v1 .. v10}, Lcom/google/android/recaptcha/internal/zzair;->zzd(IIII[I[B[BI[I[I)V

    const/16 v3, 0xa

    const/16 v4, 0xe

    const/4 v1, 0x2

    const/4 v2, 0x6

    .line 11
    invoke-static/range {v1 .. v10}, Lcom/google/android/recaptcha/internal/zzair;->zzd(IIII[I[B[BI[I[I)V

    const/16 v3, 0xb

    const/16 v4, 0xf

    const/4 v1, 0x3

    const/4 v2, 0x7

    .line 12
    invoke-static/range {v1 .. v10}, Lcom/google/android/recaptcha/internal/zzair;->zzd(IIII[I[B[BI[I[I)V

    const/16 v3, 0xa

    const/4 v1, 0x0

    const/4 v2, 0x5

    .line 13
    invoke-static/range {v1 .. v10}, Lcom/google/android/recaptcha/internal/zzair;->zzd(IIII[I[B[BI[I[I)V

    const/16 v3, 0xb

    const/16 v4, 0xc

    const/4 v1, 0x1

    const/4 v2, 0x6

    .line 14
    invoke-static/range {v1 .. v10}, Lcom/google/android/recaptcha/internal/zzair;->zzd(IIII[I[B[BI[I[I)V

    const/16 v3, 0x8

    const/16 v4, 0xd

    const/4 v1, 0x2

    const/4 v2, 0x7

    .line 15
    invoke-static/range {v1 .. v10}, Lcom/google/android/recaptcha/internal/zzair;->zzd(IIII[I[B[BI[I[I)V

    const/16 v3, 0x9

    const/16 v4, 0xe

    const/4 v1, 0x3

    const/4 v2, 0x4

    .line 16
    invoke-static/range {v1 .. v10}, Lcom/google/android/recaptcha/internal/zzair;->zzd(IIII[I[B[BI[I[I)V

    add-int/lit8 v2, v18, 0x1

    move v1, v11

    const/16 v11, 0xc

    goto :goto_4

    :cond_3
    move v11, v1

    const/16 v1, 0x40

    new-array v2, v1, [B

    move v3, v13

    :goto_5
    if-ge v3, v12, :cond_4

    .line 17
    aget v4, v9, v3

    mul-int/lit8 v6, v3, 0x4

    and-int/lit16 v12, v4, 0xff

    int-to-byte v12, v12

    .line 18
    aput-byte v12, v2, v6

    shr-int/lit8 v12, v4, 0x8

    add-int/lit8 v18, v6, 0x1

    and-int/lit16 v12, v12, 0xff

    int-to-byte v12, v12

    .line 19
    aput-byte v12, v2, v18

    shr-int/lit8 v12, v4, 0x10

    add-int/lit8 v18, v6, 0x2

    and-int/lit16 v12, v12, 0xff

    int-to-byte v12, v12

    .line 20
    aput-byte v12, v2, v18

    shr-int/lit8 v4, v4, 0x18

    add-int/lit8 v6, v6, 0x3

    and-int/lit16 v4, v4, 0xff

    int-to-byte v4, v4

    .line 21
    aput-byte v4, v2, v6

    add-int/lit8 v3, v3, 0x1

    const/16 v12, 0x10

    goto :goto_5

    :cond_4
    move v3, v13

    .line 22
    :goto_6
    invoke-static {v1, v11}, Ljava/lang/Math;->min(II)I

    move-result v4

    if-ge v3, v4, :cond_5

    add-int v4, v16, v3

    .line 23
    aget-byte v6, v2, v3

    aget-byte v12, v0, v4

    invoke-static {v6, v12}, Lcom/google/android/recaptcha/internal/zzair;->zza(II)I

    move-result v6

    int-to-byte v6, v6

    aput-byte v6, v15, v4

    add-int/lit8 v3, v3, 0x1

    goto :goto_6

    :cond_5
    add-int/lit8 v8, v8, 0x1

    add-int/lit8 v1, v11, -0x40

    add-int/lit8 v16, v16, 0x40

    move-object/from16 v6, p1

    const/16 v11, 0xc

    const/16 v12, 0x10

    goto/16 :goto_3

    :cond_6
    add-int/lit8 v0, v14, 0xc

    .line 24
    new-array v0, v0, [B

    const/16 v1, 0xc

    .line 25
    invoke-static {v7, v13, v0, v13, v1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 26
    invoke-static {v15, v13, v0, v1, v14}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    return-object v0

    .line 3
    :cond_7
    invoke-static {}, Lokio/Segment$$ExternalSyntheticBUOutline0;->m()V

    return-object v3

    .line 1
    :cond_8
    invoke-static {}, Lokio/Segment$$ExternalSyntheticBUOutline0;->m()V

    return-object v3
.end method

.method public static final zzc(IIII[I[B[BI[I[I)V
    .locals 0

    aget p4, p8, p0

    aget p1, p8, p1

    add-int/2addr p4, p1

    aput p4, p8, p0

    aget p0, p8, p2

    invoke-static {p0, p4}, Lcom/google/android/recaptcha/internal/zzair;->zza(II)I

    move-result p0

    rsub-int/lit8 p1, p3, 0x20

    ushr-int p1, p0, p1

    shl-int/2addr p0, p3

    or-int/2addr p0, p1

    aput p0, p8, p2

    return-void
.end method

.method public static final zzd(IIII[I[B[BI[I[I)V
    .locals 14

    const/16 v3, 0x10

    move v0, p0

    move v1, p1

    move/from16 v2, p3

    move-object/from16 v4, p4

    move-object/from16 v5, p5

    move-object/from16 v6, p6

    move/from16 v7, p7

    move-object/from16 v8, p8

    move-object/from16 v9, p9

    .line 1
    invoke-static/range {v0 .. v9}, Lcom/google/android/recaptcha/internal/zzair;->zzc(IIII[I[B[BI[I[I)V

    const/16 v3, 0xc

    move v2, p1

    move/from16 v0, p2

    move/from16 v1, p3

    .line 2
    invoke-static/range {v0 .. v9}, Lcom/google/android/recaptcha/internal/zzair;->zzc(IIII[I[B[BI[I[I)V

    const/16 v7, 0x8

    move v4, p0

    move v5, p1

    move/from16 v6, p3

    move-object/from16 v8, p4

    move-object/from16 v9, p5

    move-object/from16 v10, p6

    move/from16 v11, p7

    move-object/from16 v12, p8

    move-object/from16 v13, p9

    .line 3
    invoke-static/range {v4 .. v13}, Lcom/google/android/recaptcha/internal/zzair;->zzc(IIII[I[B[BI[I[I)V

    const/4 v3, 0x7

    move-object/from16 v4, p4

    move-object/from16 v5, p5

    move-object/from16 v6, p6

    move/from16 v7, p7

    move-object/from16 v8, p8

    move-object/from16 v9, p9

    .line 4
    invoke-static/range {v0 .. v9}, Lcom/google/android/recaptcha/internal/zzair;->zzc(IIII[I[B[BI[I[I)V

    return-void
.end method

.method private static final zze([BI)I
    .locals 3

    .line 1
    aget-byte v0, p0, p1

    and-int/lit16 v0, v0, 0xff

    add-int/lit8 v1, p1, 0x1

    aget-byte v1, p0, v1

    and-int/lit16 v1, v1, 0xff

    add-int/lit8 v2, p1, 0x2

    aget-byte v2, p0, v2

    and-int/lit16 v2, v2, 0xff

    add-int/lit8 p1, p1, 0x3

    aget-byte p0, p0, p1

    and-int/lit16 p0, p0, 0xff

    shl-int/lit8 p1, v1, 0x8

    or-int/2addr p1, v0

    shl-int/lit8 v0, v2, 0x10

    or-int/2addr p1, v0

    shl-int/lit8 p0, p0, 0x18

    or-int/2addr p0, p1

    return p0
.end method
