.class public final Lcom/google/android/recaptcha/internal/zzn;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private zza:I

.field private zzb:[I


# direct methods
.method public constructor <init>(I[I)V
    .locals 5

    const v0, 0x54e49eb4

    not-int v1, v0

    const v2, 0x19d30240

    and-int/2addr v1, v2

    const v2, 0x824c60e

    or-int/2addr v1, v2

    const v2, 0x11d700c8

    and-int/2addr v0, v2

    const v2, 0x2041d8a

    or-int/2addr v0, v2

    add-int/2addr v1, v0

    const v0, 0x1abf57ab

    sub-int/2addr v1, v0

    const v0, 0xb03e0c6

    const v2, 0x4353d0cd

    rem-int/2addr v2, v0

    const v0, 0x47398c89

    not-int v3, v0

    const v4, 0x204ba295

    and-int/2addr v3, v4

    const v4, 0x5410062e

    or-int/2addr v3, v4

    const v4, 0x284ba093

    and-int/2addr v0, v4

    const v4, 0xd34162a

    or-int/2addr v0, v4

    add-int/2addr v3, v0

    const v0, 0x7c9fc2b3

    sub-int/2addr v3, v0

    const v0, 0x180115be

    const v4, 0x1cf10fd8

    rem-int/2addr v4, v0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    array-length v0, p2

    xor-int/2addr v1, v2

    if-ne v0, v1, :cond_0

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzn;->zza:I

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzn;->zzb:[I

    return-void

    :cond_0
    xor-int p0, v3, v4

    new-instance p1, Ljava/lang/IllegalArgumentException;

    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p2

    add-int/2addr p2, p0

    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0, p2}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string p2, "Ake3rgkWMjm+UlOd1Tg3PHccqBbIRJQk3bhyKj5k"

    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    const-string v1, "a0CvvBEaN339T0zNlXk="

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {p1, p0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method


# virtual methods
.method public final zza(I[B)V
    .locals 27

    move-object/from16 v0, p0

    const/4 v2, 0x0

    const v3, 0x7a8967ad

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/16 v17, 0x0

    const/16 v18, 0x0

    const/16 v19, 0x0

    :goto_0
    const v1, 0x45f0bc1c

    if-eq v3, v1, :cond_3

    const v1, 0x5ad3182f

    if-eq v3, v1, :cond_1

    const v1, 0x7a8967ad

    if-eq v3, v1, :cond_0

    shl-int v20, v12, v11

    ushr-int v21, v12, v13

    xor-int v20, v20, v21

    add-int v20, v20, v12

    iget-object v1, v0, Lcom/google/android/recaptcha/internal/zzn;->zzb:[I

    and-int v22, v2, v10

    aget v22, v1, v22

    add-int v22, v2, v22

    xor-int v20, v20, v22

    add-int v4, v4, v20

    add-int v2, v2, v18

    shl-int v20, v4, v11

    ushr-int v22, v4, v13

    ushr-int v23, v2, v19

    and-int v23, v23, v10

    aget v1, v1, v23

    add-int/2addr v1, v2

    xor-int v20, v20, v22

    add-int v20, v20, v4

    xor-int v1, v20, v1

    add-int/2addr v12, v1

    const v1, -0x1612e574

    add-int/2addr v3, v1

    goto :goto_0

    :cond_0
    const v1, 0x7abf196a

    not-int v1, v1

    const v2, 0x7c4475bb

    and-int/2addr v1, v2

    const v2, 0x2212c6d3

    or-int/2addr v1, v2

    const v2, 0x7abf196a

    const v4, -0x230bced8

    and-int/2addr v2, v4

    const v4, -0x7e44fbba

    or-int/2addr v2, v4

    add-int/2addr v1, v2

    const v2, 0x9af9c65

    sub-int/2addr v1, v2

    const v2, 0x43f8e1ac

    const v4, 0x4e42b6a8    # 8.166876E8f

    rem-int/2addr v4, v2

    const v2, 0x10a30d9c

    not-int v2, v2

    const v5, 0x822902c

    and-int/2addr v2, v5

    const v5, 0x1185572b

    or-int/2addr v2, v5

    const v5, 0x10a30d9c

    const v6, 0x4da38215    # 3.429014E8f

    and-int/2addr v5, v6

    const v6, 0x55d55e99

    or-int/2addr v5, v6

    add-int/2addr v2, v5

    const v5, 0x6eb690d7

    sub-int/2addr v2, v5

    const v5, 0x29784870

    const v6, 0x53b735d5

    rem-int/2addr v6, v5

    const v5, 0x64996e13

    not-int v5, v5

    const v7, 0x61031550    # 1.511287E20f

    and-int/2addr v5, v7

    const v7, 0x2e00429a

    or-int/2addr v5, v7

    const v7, 0x64996e13

    const v8, 0x491f9560    # 653654.0f

    and-int/2addr v7, v8

    const v8, 0x81ca231

    or-int/2addr v7, v8

    add-int/2addr v5, v7

    const v7, 0x71dfa452

    sub-int/2addr v5, v7

    const v7, 0xac68ffb

    const v8, 0x3b2125a3

    rem-int/2addr v8, v7

    const v7, 0x4cc32f1f    # 1.02332664E8f

    not-int v7, v7

    const v9, 0x3c06889

    and-int/2addr v7, v9

    const v9, 0x1a132ef3

    or-int/2addr v7, v9

    const v9, 0x4cc32f1f    # 1.02332664E8f

    const v10, 0x61c0401c

    and-int/2addr v9, v10

    const v10, 0x62022dd4

    or-int/2addr v9, v10

    add-int/2addr v7, v9

    const v9, 0x7ad806a5

    sub-int/2addr v7, v9

    const v9, 0x3c5e07c

    const v10, 0x55fee0d1

    rem-int/2addr v10, v9

    xor-int/2addr v10, v7

    const v7, 0x508ed897

    not-int v7, v7

    const v9, 0x2a804404

    and-int/2addr v7, v9

    const v9, 0x407bc280

    or-int/2addr v7, v9

    const v9, 0x508ed897

    const v11, -0x50377bfc

    and-int/2addr v9, v11

    const v11, -0x7a932ed0

    or-int/2addr v9, v11

    add-int/2addr v7, v9

    const v9, -0x691aab88

    sub-int/2addr v7, v9

    const v9, 0x1dcdf795

    const v11, 0x6fcc0624

    rem-int/2addr v11, v9

    const v9, 0x18a35fe3

    not-int v9, v9

    const v12, 0x60a8da00

    and-int/2addr v9, v12

    const v12, 0x506ac98

    or-int/2addr v9, v12

    const v12, 0x18a35fe3

    const v13, 0x60a85600

    and-int/2addr v12, v13

    const v13, 0x17002db9

    or-int/2addr v12, v13

    add-int/2addr v9, v12

    const v12, 0x5a3727ba

    sub-int/2addr v9, v12

    const v12, 0x3d75bc47    # 0.05999401f

    const v13, 0x5fedc0e3

    rem-int/2addr v13, v12

    const v12, 0x5398582c

    not-int v12, v12

    const v14, 0x3c292681

    and-int/2addr v12, v14

    const v14, 0x61f662bc

    or-int/2addr v12, v14

    const v14, 0x5398582c

    const v15, 0x1e09140b

    and-int/2addr v14, v15

    const v15, 0x2294b36a

    or-int/2addr v14, v15

    add-int/2addr v12, v14

    const v14, -0x7f5412da

    sub-int/2addr v12, v14

    const v14, 0x38d82e71

    const v15, 0x58c05b8a

    rem-int/2addr v15, v14

    const v14, 0x3b121183

    not-int v14, v14

    const v17, 0x1c572489

    and-int v14, v14, v17

    const v17, 0x490e8964    # 583830.25f

    or-int v14, v14, v17

    const v17, 0x3b121183

    const v18, 0x1551a4e9

    and-int v17, v17, v18

    const v18, 0x6ba29b72

    or-int v17, v17, v18

    add-int v14, v14, v17

    const v17, -0x4ae86462

    sub-int v14, v14, v17

    const v17, 0x4fb7a02a    # 6.161454E9f

    const v18, 0x63a24d68

    rem-int v18, v18, v17

    xor-int v14, v14, v18

    move/from16 v17, v1

    const v1, 0x746ae2e4

    not-int v1, v1

    const v18, 0x787e775c

    and-int v1, v1, v18

    const v18, 0x4e0016c2    # 5.372438E8f

    or-int v1, v1, v18

    const v18, 0x746ae2e4

    const v19, 0x307e693c

    and-int v18, v18, v19

    const v19, 0x8001c63

    or-int v18, v18, v19

    add-int v1, v1, v18

    const v18, 0x7bac8c2d

    sub-int v1, v1, v18

    const v18, 0x3b0a3b87

    const v19, 0x45dc439d

    rem-int v19, v19, v18

    move/from16 v18, v1

    const v1, 0x638fdbd1

    not-int v1, v1

    const v20, 0x19b480e

    and-int v1, v1, v20

    const v20, 0x130790b1

    or-int v1, v1, v20

    const v20, 0x638fdbd1

    const v22, 0x4a98488e    # 4990023.0f

    and-int v20, v20, v22

    const v22, 0x5f6130b0

    or-int v20, v20, v22

    add-int v1, v1, v20

    const v20, 0x58b6a93d

    sub-int v1, v1, v20

    const v20, 0x1b2b9e9d

    const v22, 0x3575fed1

    rem-int v22, v22, v20

    move/from16 v20, v1

    const v1, 0x646a232a

    not-int v1, v1

    const v23, 0xf4224c1

    and-int v1, v1, v23

    const v23, 0x31d85ad4

    or-int v1, v1, v23

    const v23, 0x646a232a

    const v24, 0xe023427

    and-int v23, v23, v24

    const v24, 0x610453f6

    or-int v23, v23, v24

    add-int v1, v1, v23

    const v23, -0x62da035d

    sub-int v1, v1, v23

    const v23, 0x13bae843

    const v24, 0x52a4773b

    rem-int v24, v24, v23

    move/from16 v23, v1

    const v1, 0xcf19f38

    not-int v1, v1

    const v25, 0x6f018c9

    and-int v1, v1, v25

    const v25, 0xc11e91d

    or-int v1, v1, v25

    const v25, 0xcf19f38

    const v26, 0x12e250d0

    and-int v25, v25, v26

    const v26, 0x1016c91d

    or-int v25, v25, v26

    add-int v1, v1, v25

    const v25, 0x18704992

    sub-int v1, v1, v25

    const v25, 0x18f40a33

    const v26, 0x1f8c83ab

    rem-int v26, v26, v25

    xor-int v23, v23, v24

    xor-int v20, v20, v22

    xor-int v18, v18, v19

    xor-int/2addr v12, v15

    xor-int v19, v9, v13

    xor-int/2addr v7, v11

    xor-int v13, v5, v8

    xor-int v11, v2, v6

    xor-int v17, v17, v4

    xor-int v6, v1, v26

    const v1, 0x6b9f7823

    not-int v1, v1

    const v2, 0x50a610b2

    and-int/2addr v1, v2

    const v2, 0x5c1d21de

    or-int/2addr v1, v2

    const v2, 0x6b9f7823

    const v4, 0xa31520

    and-int/2addr v2, v4

    const v4, 0x518dde

    or-int/2addr v2, v4

    add-int/2addr v1, v2

    const v2, 0x53dcaed6

    sub-int/2addr v1, v2

    const v2, 0x2fff2a9f

    const v4, 0x39333bad

    rem-int/2addr v4, v2

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzn;->zza:I

    const v5, -0x1fb64f7e

    add-int/2addr v3, v5

    xor-int v9, v1, v4

    move v4, v2

    move v5, v12

    move/from16 v2, v16

    move/from16 v8, v18

    move/from16 v15, v23

    move/from16 v12, p1

    move/from16 v18, v7

    move v7, v14

    move/from16 v14, v20

    goto/16 :goto_0

    :cond_1
    const v1, -0x14e25c13

    add-int/2addr v1, v3

    const v20, 0x1612e574

    add-int v3, v3, v20

    move/from16 v0, v17

    if-ne v2, v0, :cond_2

    move v3, v1

    :cond_2
    move-object/from16 v0, p0

    goto/16 :goto_0

    :cond_3
    shr-int v0, v4, v5

    int-to-byte v0, v0

    aput-byte v0, p2, v16

    shr-int v0, v4, v6

    and-int/2addr v0, v7

    shl-int/2addr v0, v5

    shr-int/2addr v0, v5

    int-to-byte v0, v0

    const/4 v1, 0x1

    aput-byte v0, p2, v1

    shr-int v0, v4, v9

    and-int/2addr v0, v7

    shl-int/2addr v0, v5

    shr-int/2addr v0, v5

    int-to-byte v0, v0

    aput-byte v0, p2, v8

    and-int v0, v4, v7

    shl-int/2addr v0, v5

    shr-int/2addr v0, v5

    int-to-byte v0, v0

    aput-byte v0, p2, v10

    shr-int v0, v12, v5

    int-to-byte v0, v0

    aput-byte v0, p2, v11

    shr-int v0, v12, v6

    and-int/2addr v0, v7

    shl-int/2addr v0, v5

    shr-int/2addr v0, v5

    int-to-byte v0, v0

    aput-byte v0, p2, v13

    shr-int v0, v12, v9

    and-int/2addr v0, v7

    shl-int/2addr v0, v5

    shr-int/2addr v0, v5

    int-to-byte v0, v0

    aput-byte v0, p2, v14

    and-int v0, v12, v7

    shl-int/2addr v0, v5

    shr-int/2addr v0, v5

    int-to-byte v0, v0

    aput-byte v0, p2, v15

    return-void
.end method
