.class final Lcom/google/android/recaptcha/internal/zzrb;
.super Lcom/google/android/recaptcha/internal/zzra;
.source "SourceFile"


# static fields
.field static final zza:Lcom/google/android/recaptcha/internal/zzrf;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzrb;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzrb;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzrb;->zza:Lcom/google/android/recaptcha/internal/zzrf;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzra;-><init>()V

    return-void
.end method

.method public static zzd(JJ)J
    .locals 5

    xor-long/2addr p2, p0

    const-wide v0, -0x395b586ca42e166bL    # -2.0946245025644615E32

    mul-long/2addr p2, v0

    const/16 v2, 0x2f

    ushr-long v3, p2, v2

    xor-long/2addr p2, v3

    xor-long/2addr p0, p2

    mul-long/2addr p0, v0

    ushr-long p2, p0, v2

    xor-long/2addr p0, p2

    mul-long/2addr p0, v0

    return-wide p0
.end method

.method private static zze([BIJJ[J)V
    .locals 6

    .line 1
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v0

    add-long/2addr p2, v0

    add-long/2addr p4, p2

    add-int/lit8 v0, p1, 0x18

    add-int/lit8 v1, p1, 0x10

    add-int/lit8 p1, p1, 0x8

    .line 2
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v2

    .line 3
    invoke-static {p0, v1}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v4

    .line 4
    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide p0

    add-long/2addr p4, p0

    add-long/2addr v2, p2

    add-long/2addr v2, v4

    const/16 v0, 0x33

    .line 5
    invoke-static {p4, p5, v0}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide p4

    const/16 v0, 0x17

    .line 6
    invoke-static {v2, v3, v0}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v0

    add-long/2addr p4, v0

    const/4 v0, 0x0

    add-long/2addr v2, p0

    aput-wide v2, p6, v0

    add-long/2addr p4, p2

    const/4 p0, 0x1

    aput-wide p4, p6, p0

    return-void
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 0

    const-string p0, "Hashing.fingerprint2011()"

    return-object p0
.end method

.method public final zzb([BII)Lcom/google/android/recaptcha/internal/zzre;
    .locals 30

    move-object/from16 v0, p1

    move/from16 v7, p3

    .line 1
    array-length v1, v0

    const/4 v8, 0x0

    invoke-static {v8, v7, v1}, Lcom/google/android/recaptcha/internal/zzoq;->zzg(III)V

    const/16 v1, 0x20

    const/16 v11, 0x8

    const-wide v12, -0x5a47a3a1e67127b7L    # -5.623071142144676E-127

    const/16 v14, 0x2f

    if-gt v7, v1, :cond_3

    int-to-long v1, v7

    const-wide v3, -0x395b586ca42e166bL    # -2.0946245025644615E32

    mul-long/2addr v1, v3

    const-wide v5, -0x1364611973070723L    # -1.4877559216887398E215

    xor-long/2addr v1, v5

    move v5, v8

    :goto_0
    and-int/lit8 v6, v7, -0x8

    if-ge v5, v6, :cond_0

    .line 2
    invoke-static {v0, v5}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v15

    mul-long/2addr v15, v3

    ushr-long v17, v15, v14

    xor-long v15, v15, v17

    mul-long/2addr v15, v3

    xor-long/2addr v1, v15

    mul-long/2addr v1, v3

    add-int/lit8 v5, v5, 0x8

    goto :goto_0

    :cond_0
    and-int/lit8 v5, v7, 0x7

    if-eqz v5, :cond_2

    .line 3
    sget v15, Lcom/google/android/recaptcha/internal/zzrn;->$r8$clinit:I

    .line 4
    invoke-static {v5, v11}, Ljava/lang/Math;->min(II)I

    move-result v5

    move v15, v8

    const-wide/16 v16, 0x0

    :goto_1
    if-ge v15, v5, :cond_1

    add-int v18, v6, v15

    move-wide/from16 v19, v3

    .line 5
    aget-byte v3, v0, v18

    int-to-long v3, v3

    const-wide/16 v21, 0xff

    and-long v3, v3, v21

    mul-int/lit8 v18, v15, 0x8

    shl-long v3, v3, v18

    or-long v16, v16, v3

    add-int/lit8 v15, v15, 0x1

    move-wide/from16 v3, v19

    goto :goto_1

    :cond_1
    move-wide/from16 v19, v3

    xor-long v1, v1, v16

    mul-long v1, v1, v19

    goto :goto_2

    :cond_2
    move-wide/from16 v19, v3

    :goto_2
    ushr-long v3, v1, v14

    xor-long/2addr v1, v3

    mul-long v1, v1, v19

    ushr-long v3, v1, v14

    xor-long/2addr v1, v3

    move-wide/from16 v21, v12

    const-wide/16 v17, 0x0

    goto/16 :goto_4

    :cond_3
    const/16 v15, 0x40

    const/16 v1, 0x25

    if-gt v7, v15, :cond_4

    const/16 v2, 0x18

    .line 6
    invoke-static {v0, v2}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v2

    .line 7
    invoke-static {v0, v8}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v4

    add-int/lit8 v6, v7, -0x10

    invoke-static {v0, v6}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v15

    const-wide/16 v17, 0x0

    int-to-long v9, v7

    add-long/2addr v9, v15

    mul-long/2addr v9, v12

    add-long/2addr v4, v9

    add-long v9, v4, v2

    const/16 v15, 0x34

    .line 8
    invoke-static {v9, v10, v15}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v9

    .line 9
    invoke-static {v4, v5, v1}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v19

    .line 10
    invoke-static {v0, v11}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v21

    add-long v4, v4, v21

    move-wide/from16 v21, v12

    const/4 v12, 0x7

    .line 11
    invoke-static {v4, v5, v12}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v23

    add-long v19, v19, v23

    const/16 v13, 0x10

    .line 12
    invoke-static {v0, v13}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v23

    add-long v4, v4, v23

    add-long/2addr v2, v4

    move/from16 p0, v14

    const/16 v14, 0x1f

    .line 13
    invoke-static {v4, v5, v14}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v4

    add-long/2addr v9, v4

    .line 14
    invoke-static {v0, v13}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v4

    add-int/lit8 v13, v7, -0x20

    invoke-static {v0, v13}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v23

    add-long v4, v4, v23

    add-int/lit8 v13, v7, -0x8

    .line 15
    invoke-static {v0, v13}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v23

    move-wide/from16 v25, v9

    add-long v8, v4, v23

    .line 16
    invoke-static {v8, v9, v15}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v8

    .line 17
    invoke-static {v4, v5, v1}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v15

    add-int/lit8 v1, v7, -0x18

    .line 18
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v27

    add-long v4, v4, v27

    .line 19
    invoke-static {v4, v5, v12}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v12

    add-long/2addr v15, v12

    .line 20
    invoke-static {v0, v6}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v12

    add-long/2addr v4, v12

    add-long v23, v4, v23

    .line 21
    invoke-static {v4, v5, v14}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v4

    add-long/2addr v8, v4

    add-long v4, v25, v19

    add-long v23, v23, v4

    mul-long v23, v23, v21

    add-long/2addr v8, v15

    add-long/2addr v2, v8

    const-wide v8, -0x3b849161c568f12dL    # -8.096527404817815E21

    mul-long/2addr v2, v8

    add-long v2, v2, v23

    ushr-long v12, v2, p0

    xor-long v1, v2, v12

    mul-long v1, v1, v21

    add-long/2addr v1, v4

    ushr-long v3, v1, p0

    xor-long/2addr v1, v3

    mul-long/2addr v1, v8

    goto/16 :goto_4

    :cond_4
    move v2, v8

    move-wide/from16 v21, v12

    move/from16 p0, v14

    const-wide/16 v17, 0x0

    .line 22
    invoke-static {v0, v2}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v8

    add-int/lit8 v2, v7, -0x10

    .line 23
    invoke-static {v0, v2}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v2

    const-wide v12, -0x72a753d9501ed1b9L

    xor-long v4, v2, v12

    add-int/lit8 v2, v7, -0x38

    .line 24
    invoke-static {v0, v2}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v2

    xor-long v19, v2, v21

    move v2, v1

    add-int/lit8 v1, v7, -0x40

    move v6, v2

    int-to-long v2, v7

    const/4 v10, 0x2

    move v14, v6

    new-array v6, v10, [J

    new-array v10, v10, [J

    .line 25
    invoke-static/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzrb;->zze([BIJJ[J)V

    move-object/from16 v16, v6

    add-int/lit8 v1, v7, -0x20

    mul-long/2addr v2, v12

    move-wide/from16 v23, v4

    const-wide v4, -0x5a47a3a1e67127b7L    # -5.623071142144676E-127

    move-object/from16 v0, p1

    move-object v6, v10

    move-wide/from16 v25, v12

    move-wide/from16 v12, v23

    .line 26
    invoke-static/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzrb;->zze([BIJJ[J)V

    const/16 v23, 0x1

    aget-wide v1, v16, v23

    ushr-long v3, v1, p0

    xor-long/2addr v1, v3

    mul-long v1, v1, v25

    add-long v19, v19, v1

    add-long v8, v19, v8

    const/16 v1, 0x27

    .line 27
    invoke-static {v8, v9, v1}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v1

    mul-long v1, v1, v25

    const/16 v8, 0x21

    .line 28
    invoke-static {v12, v13, v8}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v3

    mul-long v3, v3, v25

    add-int/lit8 v5, v7, -0x1

    and-int/lit8 v5, v5, -0x40

    move v9, v5

    const/4 v6, 0x0

    move-wide v4, v3

    move-wide v2, v1

    const/4 v1, 0x0

    :goto_3
    aget-wide v12, v16, v6

    add-long/2addr v2, v4

    add-long/2addr v2, v12

    add-int/lit8 v6, v1, 0x10

    .line 29
    invoke-static {v0, v6}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v12

    add-long/2addr v2, v12

    invoke-static {v2, v3, v14}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v2

    mul-long v2, v2, v25

    aget-wide v12, v16, v23

    add-long/2addr v4, v12

    add-int/lit8 v6, v1, 0x30

    .line 30
    invoke-static {v0, v6}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v12

    add-long/2addr v4, v12

    const/16 v6, 0x2a

    invoke-static {v4, v5, v6}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v4

    mul-long v4, v4, v25

    aget-wide v12, v10, v23

    xor-long/2addr v12, v2

    const/16 v24, 0x0

    aget-wide v2, v16, v24

    xor-long v27, v4, v2

    aget-wide v2, v10, v24

    xor-long v2, v19, v2

    .line 31
    invoke-static {v2, v3, v8}, Ljava/lang/Long;->rotateRight(JI)J

    move-result-wide v19

    aget-wide v2, v16, v23

    mul-long v2, v2, v25

    aget-wide v4, v10, v24

    add-long/2addr v4, v12

    move-object/from16 v6, v16

    .line 32
    invoke-static/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzrb;->zze([BIJJ[J)V

    move/from16 v29, v1

    add-int/lit8 v1, v29, 0x20

    aget-wide v2, v10, v23

    add-long v2, v19, v2

    move-object/from16 v0, p1

    move-object v6, v10

    move-wide/from16 v4, v27

    .line 33
    invoke-static/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzrb;->zze([BIJJ[J)V

    add-int/lit8 v1, v29, 0x40

    add-int/lit8 v9, v9, -0x40

    if-nez v9, :cond_9

    aget-wide v1, v16, v24

    aget-wide v8, v6, v24

    invoke-static {v1, v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzrb;->zzd(JJ)J

    move-result-wide v1

    ushr-long v8, v4, p0

    xor-long v3, v4, v8

    mul-long v3, v3, v25

    add-long/2addr v1, v3

    add-long/2addr v1, v12

    aget-wide v3, v16, v23

    aget-wide v5, v6, v23

    invoke-static {v3, v4, v5, v6}, Lcom/google/android/recaptcha/internal/zzrb;->zzd(JJ)J

    move-result-wide v3

    add-long v3, v3, v19

    invoke-static {v1, v2, v3, v4}, Lcom/google/android/recaptcha/internal/zzrb;->zzd(JJ)J

    move-result-wide v1

    :goto_4
    if-lt v7, v11, :cond_5

    const/4 v3, 0x0

    .line 34
    invoke-static {v0, v3}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v3

    goto :goto_5

    :cond_5
    move-wide/from16 v3, v21

    :goto_5
    const/16 v5, 0x9

    if-lt v7, v5, :cond_6

    add-int/lit8 v5, v7, -0x8

    .line 35
    invoke-static {v0, v5}, Lcom/google/android/recaptcha/internal/zzrn;->zza([BI)J

    move-result-wide v12

    goto :goto_6

    :cond_6
    move-wide/from16 v12, v21

    :goto_6
    add-long/2addr v1, v12

    invoke-static {v1, v2, v3, v4}, Lcom/google/android/recaptcha/internal/zzrb;->zzd(JJ)J

    move-result-wide v0

    cmp-long v2, v0, v17

    if-eqz v2, :cond_7

    const-wide/16 v2, 0x1

    cmp-long v4, v0, v2

    if-nez v4, :cond_8

    move-wide v0, v2

    :cond_7
    const-wide/16 v2, -0x2

    add-long/2addr v0, v2

    .line 36
    :cond_8
    sget v2, Lcom/google/android/recaptcha/internal/zzre;->$r8$clinit:I

    .line 37
    new-instance v2, Lcom/google/android/recaptcha/internal/zzrd;

    invoke-direct {v2, v0, v1}, Lcom/google/android/recaptcha/internal/zzrd;-><init>(J)V

    return-object v2

    :cond_9
    move-object v10, v6

    move-wide/from16 v2, v19

    move/from16 v6, v24

    move-wide/from16 v19, v12

    goto/16 :goto_3
.end method
