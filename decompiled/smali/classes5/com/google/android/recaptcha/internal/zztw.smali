.class public abstract Lcom/google/android/recaptcha/internal/zztw;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final synthetic $r8$clinit:I

.field private static final zzb:[I

.field private static final zzc:[I

.field private static final zzd:[I

.field private static final zze:[I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const/16 v0, 0xa

    new-array v1, v0, [I

    fill-array-data v1, :array_0

    sput-object v1, Lcom/google/android/recaptcha/internal/zztw;->zzb:[I

    new-array v0, v0, [I

    fill-array-data v0, :array_1

    sput-object v0, Lcom/google/android/recaptcha/internal/zztw;->zzc:[I

    const v0, 0x3ffffff

    const v1, 0x1ffffff

    filled-new-array {v0, v1}, [I

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zztw;->zzd:[I

    const/16 v0, 0x1a

    const/16 v1, 0x19

    filled-new-array {v0, v1}, [I

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zztw;->zze:[I

    return-void

    nop

    :array_0
    .array-data 4
        0x0
        0x3
        0x6
        0x9
        0xc
        0x10
        0x13
        0x16
        0x19
        0x1c
    .end array-data

    :array_1
    .array-data 4
        0x0
        0x2
        0x3
        0x5
        0x6
        0x0
        0x1
        0x3
        0x4
        0x6
    .end array-data
.end method

.method public static zza([J[J[J)V
    .locals 5

    const/4 v0, 0x0

    :goto_0
    const/16 v1, 0xa

    if-ge v0, v1, :cond_0

    .line 1
    aget-wide v1, p1, v0

    aget-wide v3, p2, v0

    add-long/2addr v1, v3

    aput-wide v1, p0, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method public static zzb([J[J[J)V
    .locals 5

    const/4 v0, 0x0

    :goto_0
    const/16 v1, 0xa

    if-ge v0, v1, :cond_0

    .line 1
    aget-wide v1, p1, v0

    aget-wide v3, p2, v0

    sub-long/2addr v1, v3

    aput-wide v1, p0, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method public static zzc([J[J)V
    .locals 9

    .line 1
    array-length v0, p0

    const/4 v1, 0x0

    const/16 v2, 0x13

    if-eq v0, v2, :cond_0

    new-array v2, v2, [J

    invoke-static {p0, v1, v2, v1, v0}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    move-object p0, v2

    :cond_0
    const/16 v0, 0x8

    aget-wide v2, p0, v0

    const/16 v4, 0x12

    .line 2
    aget-wide v4, p0, v4

    const/4 v6, 0x4

    shl-long v7, v4, v6

    add-long/2addr v2, v7

    aput-wide v2, p0, v0

    add-long v7, v4, v4

    add-long/2addr v2, v7

    .line 3
    aput-wide v2, p0, v0

    add-long/2addr v2, v4

    .line 4
    aput-wide v2, p0, v0

    const/4 v0, 0x7

    aget-wide v2, p0, v0

    const/16 v4, 0x11

    .line 5
    aget-wide v4, p0, v4

    shl-long v7, v4, v6

    add-long/2addr v2, v7

    aput-wide v2, p0, v0

    add-long v7, v4, v4

    add-long/2addr v2, v7

    .line 6
    aput-wide v2, p0, v0

    add-long/2addr v2, v4

    .line 7
    aput-wide v2, p0, v0

    const/4 v0, 0x6

    aget-wide v2, p0, v0

    const/16 v4, 0x10

    .line 8
    aget-wide v4, p0, v4

    shl-long v7, v4, v6

    add-long/2addr v2, v7

    aput-wide v2, p0, v0

    add-long v7, v4, v4

    add-long/2addr v2, v7

    .line 9
    aput-wide v2, p0, v0

    add-long/2addr v2, v4

    .line 10
    aput-wide v2, p0, v0

    const/4 v0, 0x5

    aget-wide v2, p0, v0

    const/16 v4, 0xf

    .line 11
    aget-wide v4, p0, v4

    shl-long v7, v4, v6

    add-long/2addr v2, v7

    aput-wide v2, p0, v0

    add-long v7, v4, v4

    add-long/2addr v2, v7

    .line 12
    aput-wide v2, p0, v0

    add-long/2addr v2, v4

    .line 13
    aput-wide v2, p0, v0

    aget-wide v2, p0, v6

    const/16 v0, 0xe

    .line 14
    aget-wide v4, p0, v0

    shl-long v7, v4, v6

    add-long/2addr v2, v7

    aput-wide v2, p0, v6

    add-long v7, v4, v4

    add-long/2addr v2, v7

    .line 15
    aput-wide v2, p0, v6

    add-long/2addr v2, v4

    .line 16
    aput-wide v2, p0, v6

    const/4 v0, 0x3

    aget-wide v2, p0, v0

    const/16 v4, 0xd

    .line 17
    aget-wide v4, p0, v4

    shl-long v7, v4, v6

    add-long/2addr v2, v7

    aput-wide v2, p0, v0

    add-long v7, v4, v4

    add-long/2addr v2, v7

    .line 18
    aput-wide v2, p0, v0

    add-long/2addr v2, v4

    .line 19
    aput-wide v2, p0, v0

    const/4 v0, 0x2

    aget-wide v2, p0, v0

    const/16 v4, 0xc

    .line 20
    aget-wide v4, p0, v4

    shl-long v7, v4, v6

    add-long/2addr v2, v7

    aput-wide v2, p0, v0

    add-long v7, v4, v4

    add-long/2addr v2, v7

    .line 21
    aput-wide v2, p0, v0

    add-long/2addr v2, v4

    .line 22
    aput-wide v2, p0, v0

    const/4 v0, 0x1

    aget-wide v2, p0, v0

    const/16 v4, 0xb

    .line 23
    aget-wide v4, p0, v4

    shl-long v7, v4, v6

    add-long/2addr v2, v7

    aput-wide v2, p0, v0

    add-long v7, v4, v4

    add-long/2addr v2, v7

    .line 24
    aput-wide v2, p0, v0

    add-long/2addr v2, v4

    .line 25
    aput-wide v2, p0, v0

    aget-wide v2, p0, v1

    const/16 v0, 0xa

    .line 26
    aget-wide v4, p0, v0

    shl-long v6, v4, v6

    add-long/2addr v2, v6

    aput-wide v2, p0, v1

    add-long v6, v4, v4

    add-long/2addr v2, v6

    .line 27
    aput-wide v2, p0, v1

    add-long/2addr v2, v4

    .line 28
    aput-wide v2, p0, v1

    .line 29
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zztw;->zzd([J)V

    .line 30
    invoke-static {p0, v1, p1, v1, v0}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    return-void
.end method

.method public static zzd([J)V
    .locals 14

    const/16 v0, 0xa

    const-wide/16 v1, 0x0

    .line 1
    aput-wide v1, p0, v0

    const/4 v3, 0x0

    move v4, v3

    :goto_0
    const/16 v5, 0x1a

    const-wide/32 v6, 0x4000000

    if-ge v4, v0, :cond_0

    .line 2
    aget-wide v8, p0, v4

    div-long v6, v8, v6

    shl-long v10, v6, v5

    sub-long/2addr v8, v10

    .line 3
    aput-wide v8, p0, v4

    add-int/lit8 v5, v4, 0x1

    .line 4
    aget-wide v8, p0, v5

    add-long/2addr v8, v6

    aput-wide v8, p0, v5

    const-wide/32 v6, 0x2000000

    div-long v6, v8, v6

    const/16 v10, 0x19

    shl-long v10, v6, v10

    sub-long/2addr v8, v10

    .line 5
    aput-wide v8, p0, v5

    add-int/lit8 v4, v4, 0x2

    .line 6
    aget-wide v8, p0, v4

    add-long/2addr v8, v6

    aput-wide v8, p0, v4

    goto :goto_0

    :cond_0
    aget-wide v8, p0, v3

    .line 7
    aget-wide v10, p0, v0

    const/4 v4, 0x4

    shl-long v12, v10, v4

    add-long/2addr v8, v12

    aput-wide v8, p0, v3

    add-long v12, v10, v10

    add-long/2addr v8, v12

    .line 8
    aput-wide v8, p0, v3

    add-long/2addr v8, v10

    .line 9
    aput-wide v8, p0, v3

    .line 10
    aput-wide v1, p0, v0

    div-long v0, v8, v6

    shl-long v4, v0, v5

    sub-long/2addr v8, v4

    aput-wide v8, p0, v3

    const/4 v2, 0x1

    aget-wide v3, p0, v2

    add-long/2addr v3, v0

    aput-wide v3, p0, v2

    return-void
.end method

.method public static zze([J[J[J)V
    .locals 84

    const/4 v0, 0x0

    .line 1
    aget-wide v1, p1, v0

    aget-wide v3, p2, v0

    mul-long v5, v1, v3

    const/4 v7, 0x1

    .line 2
    aget-wide v8, p2, v7

    mul-long v10, v1, v8

    aget-wide v12, p1, v7

    mul-long v14, v12, v3

    add-long/2addr v10, v14

    add-long v14, v12, v12

    mul-long/2addr v14, v8

    const/16 v16, 0x2

    .line 3
    aget-wide v17, p2, v16

    mul-long v19, v1, v17

    aget-wide v21, p1, v16

    mul-long v23, v21, v3

    add-long v14, v14, v19

    add-long v14, v14, v23

    mul-long v19, v12, v17

    mul-long v23, v21, v8

    const/16 v25, 0x3

    .line 4
    aget-wide v26, p2, v25

    mul-long v28, v1, v26

    aget-wide v30, p1, v25

    mul-long v32, v30, v3

    add-long v19, v19, v23

    add-long v19, v19, v28

    add-long v19, v19, v32

    mul-long v23, v21, v17

    mul-long v28, v12, v26

    mul-long v32, v30, v8

    const/16 v34, 0x4

    .line 5
    aget-wide v35, p2, v34

    mul-long v37, v1, v35

    aget-wide v39, p1, v34

    mul-long v41, v39, v3

    add-long v28, v28, v32

    add-long v28, v28, v28

    add-long v23, v23, v28

    add-long v23, v23, v37

    add-long v23, v23, v41

    mul-long v28, v21, v26

    mul-long v32, v30, v17

    mul-long v37, v12, v35

    mul-long v41, v39, v8

    const/16 v43, 0x5

    .line 6
    aget-wide v44, p2, v43

    mul-long v46, v1, v44

    aget-wide v48, p1, v43

    add-long v28, v28, v32

    add-long v28, v28, v37

    add-long v28, v28, v41

    mul-long v32, v48, v3

    add-long v28, v28, v46

    add-long v28, v28, v32

    mul-long v32, v30, v26

    mul-long v37, v12, v44

    mul-long v41, v48, v8

    mul-long v46, v21, v35

    mul-long v50, v39, v17

    const/16 v52, 0x6

    .line 7
    aget-wide v53, p2, v52

    mul-long v55, v1, v53

    aget-wide v57, p1, v52

    mul-long v59, v57, v3

    add-long v32, v32, v37

    add-long v32, v32, v41

    add-long v32, v32, v32

    add-long v32, v32, v46

    add-long v32, v32, v50

    add-long v32, v32, v55

    add-long v32, v32, v59

    mul-long v37, v30, v35

    mul-long v41, v39, v26

    mul-long v46, v21, v44

    mul-long v50, v48, v17

    mul-long v55, v12, v53

    mul-long v59, v57, v8

    const/16 v61, 0x7

    .line 8
    aget-wide v62, p2, v61

    mul-long v64, v1, v62

    aget-wide v66, p1, v61

    mul-long v68, v66, v3

    add-long v37, v37, v41

    add-long v37, v37, v46

    add-long v37, v37, v50

    add-long v37, v37, v55

    add-long v37, v37, v59

    add-long v37, v37, v64

    add-long v37, v37, v68

    mul-long v41, v39, v35

    mul-long v46, v30, v44

    mul-long v50, v48, v26

    mul-long v55, v12, v62

    mul-long v59, v66, v8

    mul-long v64, v21, v53

    mul-long v68, v57, v17

    const/16 v70, 0x8

    .line 9
    aget-wide v71, p2, v70

    mul-long v73, v1, v71

    aget-wide v75, p1, v70

    mul-long v77, v75, v3

    add-long v46, v46, v50

    add-long v46, v46, v55

    add-long v46, v46, v59

    add-long v46, v46, v46

    add-long v41, v41, v46

    add-long v41, v41, v64

    add-long v41, v41, v68

    add-long v41, v41, v73

    add-long v41, v41, v77

    mul-long v46, v39, v44

    mul-long v50, v48, v35

    mul-long v55, v30, v53

    mul-long v59, v57, v26

    mul-long v64, v21, v62

    mul-long v68, v66, v17

    mul-long v73, v12, v71

    mul-long v77, v75, v8

    const/16 v79, 0x9

    .line 10
    aget-wide v80, p2, v79

    mul-long v1, v1, v80

    aget-wide v82, p1, v79

    mul-long v3, v3, v82

    add-long v46, v46, v50

    add-long v46, v46, v55

    add-long v46, v46, v59

    add-long v46, v46, v64

    add-long v46, v46, v68

    add-long v46, v46, v73

    add-long v46, v46, v77

    add-long v46, v46, v1

    add-long v46, v46, v3

    mul-long v1, v48, v44

    mul-long v3, v30, v62

    mul-long v50, v66, v26

    mul-long v12, v12, v80

    mul-long v8, v8, v82

    mul-long v55, v39, v53

    mul-long v59, v57, v35

    mul-long v64, v21, v71

    mul-long v68, v75, v17

    add-long/2addr v1, v3

    add-long v1, v1, v50

    add-long/2addr v1, v12

    add-long/2addr v1, v8

    add-long/2addr v1, v1

    add-long v1, v1, v55

    add-long v1, v1, v59

    add-long v1, v1, v64

    add-long v1, v1, v68

    mul-long v3, v48, v53

    mul-long v8, v57, v44

    mul-long v12, v39, v62

    mul-long v50, v66, v35

    mul-long v55, v30, v71

    mul-long v59, v75, v26

    mul-long v21, v21, v80

    mul-long v17, v17, v82

    add-long/2addr v3, v8

    add-long/2addr v3, v12

    add-long v3, v3, v50

    add-long v3, v3, v55

    add-long v3, v3, v59

    add-long v3, v3, v21

    add-long v3, v3, v17

    mul-long v8, v57, v53

    mul-long v12, v48, v62

    mul-long v17, v66, v44

    mul-long v30, v30, v80

    mul-long v26, v26, v82

    mul-long v21, v39, v71

    mul-long v50, v75, v35

    add-long v12, v12, v17

    add-long v12, v12, v30

    add-long v12, v12, v26

    add-long/2addr v12, v12

    add-long/2addr v8, v12

    add-long v8, v8, v21

    add-long v8, v8, v50

    mul-long v12, v57, v62

    mul-long v17, v66, v53

    mul-long v21, v48, v71

    mul-long v26, v75, v44

    mul-long v39, v39, v80

    mul-long v35, v35, v82

    add-long v12, v12, v17

    add-long v12, v12, v21

    add-long v12, v12, v26

    add-long v12, v12, v39

    add-long v12, v12, v35

    mul-long v17, v66, v62

    mul-long v48, v48, v80

    mul-long v44, v44, v82

    mul-long v21, v57, v71

    mul-long v26, v75, v53

    add-long v17, v17, v48

    add-long v17, v17, v44

    add-long v17, v17, v17

    add-long v17, v17, v21

    add-long v17, v17, v26

    mul-long v21, v66, v71

    mul-long v26, v75, v62

    mul-long v57, v57, v80

    mul-long v53, v53, v82

    add-long v21, v21, v26

    add-long v21, v21, v57

    add-long v21, v21, v53

    mul-long v26, v75, v71

    mul-long v66, v66, v80

    mul-long v62, v62, v82

    add-long v66, v66, v62

    add-long v66, v66, v66

    add-long v26, v26, v66

    mul-long v75, v75, v80

    mul-long v71, v71, v82

    add-long v75, v75, v71

    add-long v82, v82, v82

    mul-long v82, v82, v80

    move/from16 v30, v0

    const/16 v0, 0x13

    .line 19
    new-array v0, v0, [J

    aput-wide v5, v0, v30

    aput-wide v10, v0, v7

    aput-wide v14, v0, v16

    aput-wide v19, v0, v25

    aput-wide v23, v0, v34

    aput-wide v28, v0, v43

    aput-wide v32, v0, v52

    aput-wide v37, v0, v61

    aput-wide v41, v0, v70

    aput-wide v46, v0, v79

    const/16 v5, 0xa

    aput-wide v1, v0, v5

    const/16 v1, 0xb

    aput-wide v3, v0, v1

    const/16 v1, 0xc

    aput-wide v8, v0, v1

    const/16 v1, 0xd

    aput-wide v12, v0, v1

    const/16 v1, 0xe

    aput-wide v17, v0, v1

    const/16 v1, 0xf

    aput-wide v21, v0, v1

    const/16 v1, 0x10

    aput-wide v26, v0, v1

    const/16 v1, 0x11

    aput-wide v75, v0, v1

    const/16 v1, 0x12

    aput-wide v82, v0, v1

    move-object/from16 v1, p0

    .line 20
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zztw;->zzc([J[J)V

    return-void
.end method

.method public static zzf([J[J)V
    .locals 58

    const/4 v0, 0x0

    .line 1
    aget-wide v1, p1, v0

    mul-long v3, v1, v1

    add-long v5, v1, v1

    const/4 v7, 0x1

    .line 2
    aget-wide v8, p1, v7

    mul-long/2addr v5, v8

    mul-long v10, v8, v8

    const/4 v12, 0x2

    .line 3
    aget-wide v13, p1, v12

    mul-long v15, v1, v13

    add-long/2addr v10, v15

    add-long/2addr v10, v10

    mul-long v15, v8, v13

    const/16 v17, 0x3

    .line 4
    aget-wide v18, p1, v17

    mul-long v20, v1, v18

    add-long v15, v15, v20

    add-long/2addr v15, v15

    mul-long v20, v13, v13

    const-wide/16 v22, 0x4

    mul-long v24, v8, v22

    mul-long v24, v24, v18

    add-long v26, v1, v1

    const/16 v28, 0x4

    .line 5
    aget-wide v29, p1, v28

    mul-long v26, v26, v29

    add-long v20, v20, v24

    add-long v20, v20, v26

    mul-long v24, v13, v18

    mul-long v26, v8, v29

    const/16 v31, 0x5

    .line 6
    aget-wide v32, p1, v31

    mul-long v34, v1, v32

    add-long v24, v24, v26

    add-long v24, v24, v34

    add-long v24, v24, v24

    mul-long v26, v18, v18

    mul-long v34, v13, v29

    const/16 v36, 0x6

    .line 7
    aget-wide v37, p1, v36

    mul-long v39, v1, v37

    add-long v41, v8, v8

    mul-long v41, v41, v32

    add-long v26, v26, v34

    add-long v26, v26, v39

    add-long v26, v26, v41

    add-long v26, v26, v26

    mul-long v34, v18, v29

    mul-long v39, v13, v32

    mul-long v41, v8, v37

    add-long v34, v34, v39

    const/16 v39, 0x7

    .line 8
    aget-wide v43, p1, v39

    mul-long v45, v1, v43

    add-long v34, v34, v41

    add-long v34, v34, v45

    add-long v34, v34, v34

    mul-long v40, v29, v29

    mul-long v45, v13, v37

    const/16 v42, 0x8

    .line 9
    aget-wide v47, p1, v42

    mul-long v49, v1, v47

    mul-long v51, v8, v43

    mul-long v53, v18, v32

    add-long v51, v51, v53

    add-long v45, v45, v49

    add-long v51, v51, v51

    add-long v45, v45, v51

    add-long v45, v45, v45

    add-long v40, v40, v45

    mul-long v45, v29, v32

    mul-long v49, v18, v37

    mul-long v51, v13, v43

    mul-long v53, v8, v47

    const/16 v55, 0x9

    .line 10
    aget-wide v56, p1, v55

    mul-long v1, v1, v56

    add-long v45, v45, v49

    add-long v45, v45, v51

    add-long v45, v45, v53

    add-long v45, v45, v1

    add-long v45, v45, v45

    mul-long v1, v32, v32

    mul-long v49, v29, v37

    mul-long v51, v13, v47

    mul-long v53, v18, v43

    mul-long v8, v8, v56

    add-long v53, v53, v8

    add-long v1, v1, v49

    add-long v1, v1, v51

    add-long v53, v53, v53

    add-long v1, v1, v53

    add-long/2addr v1, v1

    mul-long v8, v32, v37

    mul-long v49, v29, v43

    mul-long v51, v18, v47

    mul-long v13, v13, v56

    add-long v8, v8, v49

    add-long v8, v8, v51

    add-long/2addr v8, v13

    add-long/2addr v8, v8

    mul-long v13, v37, v37

    mul-long v49, v29, v47

    mul-long v51, v32, v43

    mul-long v18, v18, v56

    add-long v51, v51, v18

    add-long v51, v51, v51

    add-long v49, v49, v51

    add-long v49, v49, v49

    add-long v13, v13, v49

    mul-long v18, v37, v43

    mul-long v49, v32, v47

    mul-long v29, v29, v56

    add-long v18, v18, v49

    add-long v18, v18, v29

    add-long v18, v18, v18

    mul-long v29, v43, v43

    mul-long v49, v37, v47

    add-long v32, v32, v32

    mul-long v32, v32, v56

    add-long v29, v29, v49

    add-long v29, v29, v32

    add-long v29, v29, v29

    mul-long v32, v43, v47

    mul-long v37, v37, v56

    add-long v32, v32, v37

    add-long v32, v32, v32

    mul-long v37, v47, v47

    mul-long v43, v43, v22

    mul-long v43, v43, v56

    add-long v37, v37, v43

    add-long v47, v47, v47

    mul-long v47, v47, v56

    add-long v22, v56, v56

    mul-long v22, v22, v56

    move/from16 v43, v0

    const/16 v0, 0x13

    .line 19
    new-array v0, v0, [J

    aput-wide v3, v0, v43

    aput-wide v5, v0, v7

    aput-wide v10, v0, v12

    aput-wide v15, v0, v17

    aput-wide v20, v0, v28

    aput-wide v24, v0, v31

    aput-wide v26, v0, v36

    aput-wide v34, v0, v39

    aput-wide v40, v0, v42

    aput-wide v45, v0, v55

    const/16 v3, 0xa

    aput-wide v1, v0, v3

    const/16 v1, 0xb

    aput-wide v8, v0, v1

    const/16 v1, 0xc

    aput-wide v13, v0, v1

    const/16 v1, 0xd

    aput-wide v18, v0, v1

    const/16 v1, 0xe

    aput-wide v29, v0, v1

    const/16 v1, 0xf

    aput-wide v32, v0, v1

    const/16 v1, 0x10

    aput-wide v37, v0, v1

    const/16 v1, 0x11

    aput-wide v47, v0, v1

    const/16 v1, 0x12

    aput-wide v22, v0, v1

    move-object/from16 v1, p0

    .line 20
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zztw;->zzc([J[J)V

    return-void
.end method

.method public static zzg([B)[J
    .locals 12

    const/16 v0, 0xa

    .line 1
    new-array v1, v0, [J

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v0, :cond_0

    sget-object v3, Lcom/google/android/recaptcha/internal/zztw;->zzb:[I

    aget v3, v3, v2

    aget-byte v4, p0, v3

    and-int/lit16 v4, v4, 0xff

    add-int/lit8 v5, v3, 0x1

    aget-byte v5, p0, v5

    and-int/lit16 v5, v5, 0xff

    add-int/lit8 v6, v3, 0x2

    aget-byte v6, p0, v6

    and-int/lit16 v6, v6, 0xff

    add-int/lit8 v3, v3, 0x3

    aget-byte v3, p0, v3

    and-int/lit16 v3, v3, 0xff

    sget-object v7, Lcom/google/android/recaptcha/internal/zztw;->zzc:[I

    aget v7, v7, v2

    int-to-long v8, v5

    int-to-long v4, v4

    int-to-long v10, v6

    const/16 v6, 0x8

    shl-long/2addr v8, v6

    or-long/2addr v4, v8

    int-to-long v8, v3

    const/16 v3, 0x10

    shl-long/2addr v10, v3

    or-long v3, v4, v10

    const/16 v5, 0x18

    shl-long v5, v8, v5

    or-long/2addr v3, v5

    shr-long/2addr v3, v7

    and-int/lit8 v5, v2, 0x1

    sget-object v6, Lcom/google/android/recaptcha/internal/zztw;->zzd:[I

    aget v5, v6, v5

    int-to-long v5, v5

    and-long/2addr v3, v5

    aput-wide v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_0
    return-object v1
.end method

.method public static zzh([J)[B
    .locals 17

    const/16 v0, 0xa

    move-object/from16 v1, p0

    .line 1
    invoke-static {v1, v0}, Ljava/util/Arrays;->copyOf([JI)[J

    move-result-object v1

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    const-wide/16 v4, 0x13

    const/16 v6, 0x19

    const/16 v7, 0x9

    const/16 v8, 0x1f

    const/4 v9, 0x2

    if-ge v3, v9, :cond_1

    move v9, v2

    :goto_1
    if-ge v9, v7, :cond_0

    .line 2
    aget-wide v10, v1, v9

    shr-long v12, v10, v8

    and-long/2addr v12, v10

    and-int/lit8 v14, v9, 0x1

    sget-object v15, Lcom/google/android/recaptcha/internal/zztw;->zze:[I

    aget v14, v15, v14

    shr-long/2addr v12, v14

    long-to-int v12, v12

    neg-int v12, v12

    shl-int v13, v12, v14

    int-to-long v13, v13

    add-long/2addr v10, v13

    .line 3
    aput-wide v10, v1, v9

    add-int/lit8 v9, v9, 0x1

    .line 4
    aget-wide v10, v1, v9

    int-to-long v12, v12

    sub-long/2addr v10, v12

    aput-wide v10, v1, v9

    goto :goto_1

    .line 5
    :cond_0
    aget-wide v9, v1, v7

    shr-long v11, v9, v8

    and-long/2addr v11, v9

    shr-long/2addr v11, v6

    long-to-int v6, v11

    neg-int v6, v6

    shl-int/lit8 v8, v6, 0x19

    int-to-long v11, v8

    add-long/2addr v9, v11

    .line 6
    aput-wide v9, v1, v7

    .line 7
    aget-wide v7, v1, v2

    int-to-long v9, v6

    mul-long/2addr v9, v4

    sub-long/2addr v7, v9

    aput-wide v7, v1, v2

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 8
    :cond_1
    aget-wide v10, v1, v2

    shr-long v12, v10, v8

    and-long/2addr v12, v10

    const/16 v3, 0x1a

    shr-long/2addr v12, v3

    long-to-int v3, v12

    neg-int v3, v3

    shl-int/lit8 v12, v3, 0x1a

    int-to-long v12, v12

    add-long/2addr v10, v12

    .line 9
    aput-wide v10, v1, v2

    const/4 v10, 0x1

    .line 10
    aget-wide v11, v1, v10

    int-to-long v13, v3

    sub-long/2addr v11, v13

    aput-wide v11, v1, v10

    move v3, v2

    :goto_2
    if-ge v3, v9, :cond_3

    move v11, v2

    :goto_3
    if-ge v11, v7, :cond_2

    .line 11
    aget-wide v12, v1, v11

    and-int/lit8 v14, v11, 0x1

    sget-object v15, Lcom/google/android/recaptcha/internal/zztw;->zze:[I

    aget v15, v15, v14

    move/from16 p0, v2

    move/from16 v16, v3

    shr-long v2, v12, v15

    sget-object v15, Lcom/google/android/recaptcha/internal/zztw;->zzd:[I

    aget v14, v15, v14

    int-to-long v14, v14

    and-long/2addr v12, v14

    .line 12
    aput-wide v12, v1, v11

    add-int/lit8 v11, v11, 0x1

    .line 13
    aget-wide v12, v1, v11

    long-to-int v2, v2

    int-to-long v2, v2

    add-long/2addr v12, v2

    aput-wide v12, v1, v11

    move/from16 v2, p0

    move/from16 v3, v16

    goto :goto_3

    :cond_2
    move/from16 p0, v2

    move/from16 v16, v3

    add-int/lit8 v3, v16, 0x1

    goto :goto_2

    :cond_3
    move/from16 p0, v2

    .line 14
    aget-wide v2, v1, v7

    shr-long v11, v2, v6

    const-wide/32 v13, 0x1ffffff

    and-long/2addr v2, v13

    .line 15
    aput-wide v2, v1, v7

    .line 16
    aget-wide v2, v1, p0

    long-to-int v6, v11

    int-to-long v6, v6

    mul-long/2addr v6, v4

    add-long/2addr v2, v6

    aput-wide v2, v1, p0

    long-to-int v2, v2

    const v3, -0x3ffffed

    add-int/2addr v2, v3

    shr-int/2addr v2, v8

    not-int v2, v2

    move v3, v10

    :goto_4
    if-ge v3, v0, :cond_4

    .line 17
    aget-wide v4, v1, v3

    long-to-int v4, v4

    and-int/lit8 v5, v3, 0x1

    sget-object v6, Lcom/google/android/recaptcha/internal/zztw;->zzd:[I

    aget v5, v6, v5

    xor-int/2addr v4, v5

    not-int v4, v4

    shl-int/lit8 v5, v4, 0x10

    and-int/2addr v4, v5

    shl-int/lit8 v5, v4, 0x8

    and-int/2addr v4, v5

    shl-int/lit8 v5, v4, 0x4

    and-int/2addr v4, v5

    shl-int/lit8 v5, v4, 0x2

    and-int/2addr v4, v5

    add-int v5, v4, v4

    and-int/2addr v4, v5

    shr-int/2addr v4, v8

    and-int/2addr v2, v4

    add-int/lit8 v3, v3, 0x1

    goto :goto_4

    .line 18
    :cond_4
    aget-wide v3, v1, p0

    const v5, 0x3ffffed

    and-int/2addr v5, v2

    int-to-long v5, v5

    sub-long/2addr v3, v5

    aput-wide v3, v1, p0

    .line 19
    aget-wide v3, v1, v10

    const v5, 0x1ffffff

    and-int/2addr v5, v2

    int-to-long v5, v5

    sub-long/2addr v3, v5

    aput-wide v3, v1, v10

    :goto_5
    if-ge v9, v0, :cond_5

    .line 20
    aget-wide v3, v1, v9

    const v7, 0x3ffffff

    and-int/2addr v7, v2

    int-to-long v7, v7

    sub-long/2addr v3, v7

    aput-wide v3, v1, v9

    add-int/lit8 v3, v9, 0x1

    .line 21
    aget-wide v7, v1, v3

    sub-long/2addr v7, v5

    aput-wide v7, v1, v3

    add-int/lit8 v9, v9, 0x2

    goto :goto_5

    :cond_5
    move/from16 v2, p0

    :goto_6
    if-ge v2, v0, :cond_6

    .line 22
    aget-wide v3, v1, v2

    sget-object v5, Lcom/google/android/recaptcha/internal/zztw;->zzc:[I

    aget v5, v5, v2

    shl-long/2addr v3, v5

    aput-wide v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_6

    :cond_6
    const/16 v2, 0x20

    new-array v2, v2, [B

    move/from16 v3, p0

    :goto_7
    if-ge v3, v0, :cond_7

    sget-object v4, Lcom/google/android/recaptcha/internal/zztw;->zzb:[I

    .line 23
    aget v4, v4, v3

    aget-byte v5, v2, v4

    int-to-long v5, v5

    aget-wide v7, v1, v3

    const-wide/16 v9, 0xff

    and-long v11, v7, v9

    or-long/2addr v5, v11

    long-to-int v5, v5

    int-to-byte v5, v5

    aput-byte v5, v2, v4

    add-int/lit8 v5, v4, 0x1

    .line 24
    aget-byte v6, v2, v5

    int-to-long v11, v6

    const/16 v6, 0x8

    shr-long v13, v7, v6

    and-long/2addr v13, v9

    or-long/2addr v11, v13

    long-to-int v6, v11

    int-to-byte v6, v6

    aput-byte v6, v2, v5

    add-int/lit8 v5, v4, 0x2

    .line 25
    aget-byte v6, v2, v5

    int-to-long v11, v6

    const/16 v6, 0x10

    shr-long v13, v7, v6

    and-long/2addr v13, v9

    or-long/2addr v11, v13

    long-to-int v6, v11

    int-to-byte v6, v6

    aput-byte v6, v2, v5

    add-int/lit8 v4, v4, 0x3

    .line 26
    aget-byte v5, v2, v4

    int-to-long v5, v5

    const/16 v11, 0x18

    shr-long/2addr v7, v11

    and-long/2addr v7, v9

    or-long/2addr v5, v7

    long-to-int v5, v5

    int-to-byte v5, v5

    aput-byte v5, v2, v4

    add-int/lit8 v3, v3, 0x1

    goto :goto_7

    :cond_7
    return-object v2
.end method
