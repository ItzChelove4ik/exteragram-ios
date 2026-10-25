.class public abstract Lcom/google/android/recaptcha/internal/zztp;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field static final zza:[B

.field private static final zzb:Lcom/google/android/recaptcha/internal/zztk;

.field private static final zzc:Lcom/google/android/recaptcha/internal/zztm;


# direct methods
.method static constructor <clinit>()V
    .locals 6

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zztk;

    const/16 v1, 0xa

    new-array v2, v1, [J

    fill-array-data v2, :array_0

    new-array v3, v1, [J

    fill-array-data v3, :array_1

    new-array v4, v1, [J

    fill-array-data v4, :array_2

    invoke-direct {v0, v2, v3, v4}, Lcom/google/android/recaptcha/internal/zztk;-><init>([J[J[J)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zztp;->zzb:Lcom/google/android/recaptcha/internal/zztk;

    new-instance v0, Lcom/google/android/recaptcha/internal/zztm;

    new-instance v2, Lcom/google/android/recaptcha/internal/zztn;

    new-array v3, v1, [J

    fill-array-data v3, :array_3

    new-array v4, v1, [J

    fill-array-data v4, :array_4

    new-array v5, v1, [J

    fill-array-data v5, :array_5

    invoke-direct {v2, v3, v4, v5}, Lcom/google/android/recaptcha/internal/zztn;-><init>([J[J[J)V

    new-array v1, v1, [J

    fill-array-data v1, :array_6

    invoke-direct {v0, v2, v1}, Lcom/google/android/recaptcha/internal/zztm;-><init>(Lcom/google/android/recaptcha/internal/zztn;[J)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zztp;->zzc:Lcom/google/android/recaptcha/internal/zztm;

    const/16 v0, 0x20

    new-array v0, v0, [B

    fill-array-data v0, :array_7

    sput-object v0, Lcom/google/android/recaptcha/internal/zztp;->zza:[B

    return-void

    :array_0
    .array-data 8
        0x1
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
    .end array-data

    :array_1
    .array-data 8
        0x1
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
    .end array-data

    :array_2
    .array-data 8
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
    .end array-data

    :array_3
    .array-data 8
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
    .end array-data

    :array_4
    .array-data 8
        0x1
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
    .end array-data

    :array_5
    .array-data 8
        0x1
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
    .end array-data

    :array_6
    .array-data 8
        0x1
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
        0x0
    .end array-data

    :array_7
    .array-data 1
        -0x13t
        -0x2dt
        -0xbt
        0x5ct
        0x1at
        0x63t
        0x12t
        0x58t
        -0x2at
        -0x64t
        -0x9t
        -0x5et
        -0x22t
        -0x7t
        -0x22t
        0x14t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x10t
    .end array-data
.end method

.method public static zza([B)[B
    .locals 8

    const/16 v0, 0x40

    .line 1
    new-array v1, v0, [B

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    const/16 v4, 0x20

    const/4 v5, 0x1

    if-ge v3, v4, :cond_0

    aget-byte v4, p0, v3

    and-int/lit8 v4, v4, 0xf

    add-int v6, v3, v3

    int-to-byte v4, v4

    aput-byte v4, v1, v6

    add-int/2addr v6, v5

    .line 2
    aget-byte v4, p0, v3

    and-int/lit16 v4, v4, 0xff

    shr-int/lit8 v4, v4, 0x4

    int-to-byte v4, v4

    aput-byte v4, v1, v6

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_0
    move p0, v2

    move v3, p0

    :goto_1
    const/16 v4, 0x3f

    if-ge p0, v4, :cond_1

    .line 3
    aget-byte v4, v1, p0

    add-int/2addr v4, v3

    int-to-byte v3, v4

    aput-byte v3, v1, p0

    add-int/lit8 v4, v3, 0x8

    shr-int/lit8 v4, v4, 0x4

    shl-int/lit8 v6, v4, 0x4

    sub-int/2addr v3, v6

    int-to-byte v3, v3

    .line 4
    aput-byte v3, v1, p0

    add-int/lit8 p0, p0, 0x1

    move v3, v4

    goto :goto_1

    :cond_1
    aget-byte p0, v1, v4

    add-int/2addr p0, v3

    int-to-byte p0, p0

    aput-byte p0, v1, v4

    new-instance p0, Lcom/google/android/recaptcha/internal/zztm;

    sget-object v3, Lcom/google/android/recaptcha/internal/zztp;->zzc:Lcom/google/android/recaptcha/internal/zztm;

    .line 5
    invoke-direct {p0, v3}, Lcom/google/android/recaptcha/internal/zztm;-><init>(Lcom/google/android/recaptcha/internal/zztm;)V

    new-instance v3, Lcom/google/android/recaptcha/internal/zzto;

    invoke-direct {v3}, Lcom/google/android/recaptcha/internal/zzto;-><init>()V

    :goto_2
    if-ge v5, v0, :cond_2

    new-instance v4, Lcom/google/android/recaptcha/internal/zztk;

    sget-object v6, Lcom/google/android/recaptcha/internal/zztp;->zzb:Lcom/google/android/recaptcha/internal/zztk;

    .line 6
    invoke-direct {v4, v6}, Lcom/google/android/recaptcha/internal/zztk;-><init>(Lcom/google/android/recaptcha/internal/zztk;)V

    div-int/lit8 v6, v5, 0x2

    .line 7
    aget-byte v7, v1, v5

    invoke-static {v4, v6, v7}, Lcom/google/android/recaptcha/internal/zztp;->zzk(Lcom/google/android/recaptcha/internal/zztk;IB)V

    .line 8
    invoke-static {v3, p0}, Lcom/google/android/recaptcha/internal/zzto;->zza(Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zzto;

    invoke-static {p0, v3, v4}, Lcom/google/android/recaptcha/internal/zztp;->zzg(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztk;)V

    add-int/lit8 v5, v5, 0x2

    goto :goto_2

    :cond_2
    new-instance v4, Lcom/google/android/recaptcha/internal/zztn;

    invoke-direct {v4}, Lcom/google/android/recaptcha/internal/zztn;-><init>()V

    .line 9
    invoke-static {v4, p0}, Lcom/google/android/recaptcha/internal/zztn;->zza(Lcom/google/android/recaptcha/internal/zztn;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zztn;

    invoke-static {p0, v4}, Lcom/google/android/recaptcha/internal/zztp;->zzi(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zztn;)V

    .line 10
    invoke-static {v4, p0}, Lcom/google/android/recaptcha/internal/zztn;->zza(Lcom/google/android/recaptcha/internal/zztn;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zztn;

    invoke-static {p0, v4}, Lcom/google/android/recaptcha/internal/zztp;->zzi(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zztn;)V

    .line 11
    invoke-static {v4, p0}, Lcom/google/android/recaptcha/internal/zztn;->zza(Lcom/google/android/recaptcha/internal/zztn;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zztn;

    invoke-static {p0, v4}, Lcom/google/android/recaptcha/internal/zztp;->zzi(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zztn;)V

    .line 12
    invoke-static {v4, p0}, Lcom/google/android/recaptcha/internal/zztn;->zza(Lcom/google/android/recaptcha/internal/zztn;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zztn;

    invoke-static {p0, v4}, Lcom/google/android/recaptcha/internal/zztp;->zzi(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zztn;)V

    :goto_3
    if-ge v2, v0, :cond_3

    new-instance v4, Lcom/google/android/recaptcha/internal/zztk;

    sget-object v5, Lcom/google/android/recaptcha/internal/zztp;->zzb:Lcom/google/android/recaptcha/internal/zztk;

    .line 13
    invoke-direct {v4, v5}, Lcom/google/android/recaptcha/internal/zztk;-><init>(Lcom/google/android/recaptcha/internal/zztk;)V

    div-int/lit8 v5, v2, 0x2

    .line 14
    aget-byte v6, v1, v2

    invoke-static {v4, v5, v6}, Lcom/google/android/recaptcha/internal/zztp;->zzk(Lcom/google/android/recaptcha/internal/zztk;IB)V

    .line 15
    invoke-static {v3, p0}, Lcom/google/android/recaptcha/internal/zzto;->zza(Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zzto;

    invoke-static {p0, v3, v4}, Lcom/google/android/recaptcha/internal/zztp;->zzg(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztk;)V

    add-int/lit8 v2, v2, 0x2

    goto :goto_3

    :cond_3
    new-instance v0, Lcom/google/android/recaptcha/internal/zztn;

    .line 16
    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zztn;-><init>(Lcom/google/android/recaptcha/internal/zztm;)V

    iget-object p0, v0, Lcom/google/android/recaptcha/internal/zztn;->zza:[J

    const/16 v1, 0xa

    new-array v2, v1, [J

    .line 17
    invoke-static {v2, p0}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    iget-object p0, v0, Lcom/google/android/recaptcha/internal/zztn;->zzb:[J

    new-array v3, v1, [J

    .line 18
    invoke-static {v3, p0}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    iget-object p0, v0, Lcom/google/android/recaptcha/internal/zztn;->zzc:[J

    new-array v4, v1, [J

    .line 19
    invoke-static {v4, p0}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    new-array p0, v1, [J

    .line 20
    invoke-static {p0, v4}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    new-array v5, v1, [J

    .line 21
    invoke-static {v5, v3, v2}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    .line 22
    invoke-static {v5, v5, v4}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    new-array v1, v1, [J

    .line 23
    invoke-static {v1, v2, v3}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 24
    sget-object v2, Lcom/google/android/recaptcha/internal/zztr;->zza:[J

    invoke-static {v1, v1, v2}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 25
    invoke-static {v1, v1, p0}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    .line 26
    invoke-static {v1, v1}, Lcom/google/android/recaptcha/internal/zztw;->zzc([J[J)V

    .line 27
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zztw;->zzh([J)[B

    move-result-object p0

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zztw;->zzh([J)[B

    move-result-object v1

    .line 28
    invoke-static {p0, v1}, Ljava/security/MessageDigest;->isEqual([B[B)Z

    move-result p0

    if-eqz p0, :cond_4

    .line 29
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zztn;->zzb()[B

    move-result-object p0

    return-object p0

    .line 28
    :cond_4
    const-string p0, "arithmetic error in scalar multiplication"

    .line 30
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static zzb([B)[B
    .locals 3

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzadf;->zzb:Lcom/google/android/recaptcha/internal/zzadf;

    const-string v1, "SHA-512"

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzadf;->zzb(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/security/MessageDigest;

    const/16 v1, 0x20

    const/4 v2, 0x0

    .line 2
    invoke-virtual {v0, p0, v2, v1}, Ljava/security/MessageDigest;->update([BII)V

    .line 3
    invoke-virtual {v0}, Ljava/security/MessageDigest;->digest()[B

    move-result-object p0

    .line 4
    aget-byte v0, p0, v2

    and-int/lit16 v0, v0, 0xf8

    int-to-byte v0, v0

    aput-byte v0, p0, v2

    const/16 v0, 0x1f

    .line 5
    aget-byte v1, p0, v0

    and-int/lit8 v1, v1, 0x7f

    int-to-byte v2, v1

    aput-byte v2, p0, v0

    or-int/lit8 v1, v1, 0x40

    int-to-byte v1, v1

    .line 6
    aput-byte v1, p0, v0

    return-object p0
.end method

.method public static zzc([B[B[B)Z
    .locals 115

    move-object/from16 v0, p1

    move-object/from16 v1, p2

    .line 1
    array-length v2, v0

    const/4 v3, 0x0

    const/16 v4, 0x40

    if-eq v2, v4, :cond_0

    return v3

    :cond_0
    const/16 v2, 0x20

    .line 2
    invoke-static {v0, v2, v4}, Ljava/util/Arrays;->copyOfRange([BII)[B

    move-result-object v4

    const/16 v5, 0x1f

    move v6, v5

    :goto_0
    if-ltz v6, :cond_17

    .line 3
    aget-byte v7, v4, v6

    const/16 v8, 0xff

    and-int/2addr v7, v8

    sget-object v9, Lcom/google/android/recaptcha/internal/zztp;->zza:[B

    .line 4
    aget-byte v9, v9, v6

    and-int/2addr v9, v8

    if-eq v7, v9, :cond_18

    if-ge v7, v9, :cond_17

    .line 5
    sget-object v6, Lcom/google/android/recaptcha/internal/zzadf;->zzb:Lcom/google/android/recaptcha/internal/zzadf;

    const-string v7, "SHA-512"

    invoke-virtual {v6, v7}, Lcom/google/android/recaptcha/internal/zzadf;->zzb(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/security/MessageDigest;

    .line 6
    invoke-virtual {v6, v0, v3, v2}, Ljava/security/MessageDigest;->update([BII)V

    .line 7
    invoke-virtual {v6, v1}, Ljava/security/MessageDigest;->update([B)V

    move-object/from16 v7, p0

    .line 8
    invoke-virtual {v6, v7}, Ljava/security/MessageDigest;->update([B)V

    .line 9
    invoke-virtual {v6}, Ljava/security/MessageDigest;->digest()[B

    move-result-object v6

    .line 10
    invoke-static {v6, v3}, Lcom/google/android/recaptcha/internal/zztp;->zzn([BI)J

    move-result-wide v9

    const-wide/32 v11, 0x1fffff

    and-long/2addr v9, v11

    const/4 v7, 0x2

    .line 11
    invoke-static {v6, v7}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v13

    const/4 v15, 0x5

    shr-long/2addr v13, v15

    .line 12
    invoke-static {v6, v15}, Lcom/google/android/recaptcha/internal/zztp;->zzn([BI)J

    move-result-wide v16

    shr-long v16, v16, v7

    move/from16 v18, v3

    const/4 v3, 0x7

    .line 13
    invoke-static {v6, v3}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v19

    shr-long v19, v19, v3

    move/from16 p0, v3

    const/16 v3, 0xa

    .line 14
    invoke-static {v6, v3}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v21

    const/16 v23, 0x4

    shr-long v21, v21, v23

    move/from16 v24, v7

    const/16 v7, 0xd

    .line 15
    invoke-static {v6, v7}, Lcom/google/android/recaptcha/internal/zztp;->zzn([BI)J

    move-result-wide v25

    const/16 v27, 0x1

    shr-long v25, v25, v27

    move/from16 v28, v7

    const/16 v7, 0xf

    .line 16
    invoke-static {v6, v7}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v29

    const/16 v31, 0x6

    shr-long v29, v29, v31

    move/from16 v32, v7

    const/16 v7, 0x12

    .line 17
    invoke-static {v6, v7}, Lcom/google/android/recaptcha/internal/zztp;->zzn([BI)J

    move-result-wide v33

    const/16 v35, 0x3

    shr-long v33, v33, v35

    move/from16 v36, v7

    const/16 v7, 0x15

    .line 18
    invoke-static {v6, v7}, Lcom/google/android/recaptcha/internal/zztp;->zzn([BI)J

    move-result-wide v37

    and-long v37, v37, v11

    move/from16 v39, v7

    const/16 v7, 0x17

    .line 19
    invoke-static {v6, v7}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v40

    shr-long v40, v40, v15

    move/from16 v42, v7

    const/16 v7, 0x1a

    .line 20
    invoke-static {v6, v7}, Lcom/google/android/recaptcha/internal/zztp;->zzn([BI)J

    move-result-wide v43

    shr-long v43, v43, v24

    move/from16 v45, v7

    const/16 v7, 0x1c

    .line 21
    invoke-static {v6, v7}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v46

    shr-long v46, v46, p0

    .line 22
    invoke-static {v6, v5}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v48

    shr-long v48, v48, v23

    move/from16 v50, v5

    const/16 v5, 0x22

    .line 23
    invoke-static {v6, v5}, Lcom/google/android/recaptcha/internal/zztp;->zzn([BI)J

    move-result-wide v51

    shr-long v51, v51, v27

    const/16 v5, 0x24

    .line 24
    invoke-static {v6, v5}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v53

    shr-long v53, v53, v31

    const/16 v5, 0x27

    .line 25
    invoke-static {v6, v5}, Lcom/google/android/recaptcha/internal/zztp;->zzn([BI)J

    move-result-wide v55

    shr-long v55, v55, v35

    const/16 v5, 0x2a

    .line 26
    invoke-static {v6, v5}, Lcom/google/android/recaptcha/internal/zztp;->zzn([BI)J

    move-result-wide v57

    and-long v57, v57, v11

    const/16 v5, 0x2c

    .line 27
    invoke-static {v6, v5}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v59

    shr-long v59, v59, v15

    const/16 v5, 0x2f

    .line 28
    invoke-static {v6, v5}, Lcom/google/android/recaptcha/internal/zztp;->zzn([BI)J

    move-result-wide v61

    shr-long v61, v61, v24

    const/16 v5, 0x31

    .line 29
    invoke-static {v6, v5}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v63

    and-long v61, v61, v11

    and-long v53, v53, v11

    and-long v51, v51, v11

    and-long v48, v48, v11

    and-long v46, v46, v11

    and-long v43, v43, v11

    and-long v40, v40, v11

    and-long v29, v29, v11

    and-long v25, v25, v11

    and-long v21, v21, v11

    and-long v19, v19, v11

    and-long v16, v16, v11

    and-long/2addr v13, v11

    and-long v59, v59, v11

    shr-long v63, v63, p0

    and-long v63, v63, v11

    const/16 v5, 0x34

    .line 30
    invoke-static {v6, v5}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v65

    shr-long v65, v65, v23

    and-long v65, v65, v11

    const/16 v5, 0x37

    .line 31
    invoke-static {v6, v5}, Lcom/google/android/recaptcha/internal/zztp;->zzn([BI)J

    move-result-wide v67

    shr-long v67, v67, v27

    and-long v67, v67, v11

    const/16 v5, 0x39

    .line 32
    invoke-static {v6, v5}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v69

    shr-long v69, v69, v31

    and-long v11, v69, v11

    const/16 v5, 0x3c

    .line 33
    invoke-static {v6, v5}, Lcom/google/android/recaptcha/internal/zztp;->zzo([BI)J

    move-result-wide v69

    shr-long v69, v69, v35

    const-wide/32 v71, 0xa2c13

    mul-long v73, v65, v71

    add-long v37, v37, v73

    mul-long v73, v63, v71

    add-long v33, v33, v73

    mul-long v73, v61, v71

    add-long v29, v29, v73

    const-wide/32 v73, 0x100000

    add-long v75, v29, v73

    shr-long v75, v75, v39

    shl-long v77, v75, v39

    const-wide/32 v79, 0x72d18

    mul-long v81, v63, v79

    add-long v37, v37, v81

    const-wide/32 v81, 0x9fb67

    mul-long v83, v61, v81

    add-long v37, v37, v83

    add-long v83, v37, v73

    shr-long v83, v83, v39

    shl-long v85, v83, v39

    mul-long v87, v11, v71

    add-long v43, v43, v87

    mul-long v87, v67, v79

    add-long v43, v43, v87

    mul-long v87, v65, v81

    add-long v43, v43, v87

    const-wide/32 v87, 0xf39ad

    mul-long v89, v63, v87

    sub-long v43, v43, v89

    const-wide/32 v89, 0x215d1

    mul-long v91, v61, v89

    add-long v43, v43, v91

    add-long v91, v43, v73

    shr-long v91, v91, v39

    shl-long v93, v91, v39

    mul-long v95, v69, v79

    add-long v48, v48, v95

    mul-long v95, v11, v81

    add-long v48, v48, v95

    mul-long v95, v67, v87

    sub-long v48, v48, v95

    mul-long v95, v65, v89

    add-long v48, v48, v95

    const-wide/32 v95, 0xa6f7d

    mul-long v97, v63, v95

    sub-long v48, v48, v97

    add-long v97, v48, v73

    shr-long v97, v97, v39

    shl-long v99, v97, v39

    mul-long v101, v69, v87

    sub-long v53, v53, v101

    mul-long v101, v11, v89

    add-long v53, v53, v101

    mul-long v101, v67, v95

    sub-long v53, v53, v101

    add-long v101, v53, v73

    shr-long v101, v101, v39

    shl-long v103, v101, v39

    mul-long v105, v69, v95

    sub-long v57, v57, v105

    add-long v105, v57, v73

    shr-long v105, v105, v39

    shl-long v107, v105, v39

    mul-long v109, v61, v79

    add-long v33, v33, v109

    add-long v33, v33, v75

    add-long v75, v33, v73

    shr-long v75, v75, v39

    shl-long v109, v75, v39

    mul-long v111, v67, v71

    add-long v40, v40, v111

    mul-long v111, v65, v79

    add-long v40, v40, v111

    mul-long v111, v63, v81

    add-long v40, v40, v111

    mul-long v111, v61, v87

    sub-long v40, v40, v111

    add-long v40, v40, v83

    add-long v83, v40, v73

    shr-long v83, v83, v39

    shl-long v111, v83, v39

    mul-long v113, v69, v71

    add-long v46, v46, v113

    mul-long v113, v11, v79

    add-long v46, v46, v113

    mul-long v113, v67, v81

    add-long v46, v46, v113

    mul-long v113, v65, v87

    sub-long v46, v46, v113

    mul-long v63, v63, v89

    add-long v46, v46, v63

    mul-long v61, v61, v95

    sub-long v46, v46, v61

    add-long v46, v46, v91

    add-long v61, v46, v73

    shr-long v61, v61, v39

    shl-long v63, v61, v39

    mul-long v91, v69, v81

    add-long v51, v51, v91

    mul-long v91, v11, v87

    sub-long v51, v51, v91

    mul-long v67, v67, v89

    add-long v51, v51, v67

    mul-long v65, v65, v95

    sub-long v51, v51, v65

    add-long v51, v51, v97

    add-long v65, v51, v73

    shr-long v65, v65, v39

    shl-long v67, v65, v39

    mul-long v69, v69, v89

    add-long v55, v55, v69

    mul-long v11, v11, v95

    sub-long v55, v55, v11

    add-long v55, v55, v101

    add-long v11, v55, v73

    shr-long v11, v11, v39

    shl-long v69, v11, v39

    sub-long v48, v48, v99

    add-long v48, v48, v61

    mul-long v61, v48, v71

    add-long v9, v9, v61

    add-long v61, v9, v73

    shr-long v61, v61, v39

    shl-long v91, v61, v39

    sub-long v53, v53, v103

    add-long v53, v53, v65

    mul-long v65, v53, v71

    add-long v16, v16, v65

    sub-long v51, v51, v67

    mul-long v65, v51, v79

    add-long v16, v16, v65

    mul-long v65, v48, v81

    add-long v16, v16, v65

    add-long v65, v16, v73

    shr-long v65, v65, v39

    shl-long v67, v65, v39

    sub-long v57, v57, v107

    add-long v57, v57, v11

    mul-long v11, v57, v71

    add-long v21, v21, v11

    sub-long v55, v55, v69

    mul-long v11, v55, v79

    add-long v21, v21, v11

    mul-long v11, v53, v81

    add-long v21, v21, v11

    mul-long v11, v51, v87

    sub-long v21, v21, v11

    mul-long v11, v48, v89

    add-long v21, v21, v11

    add-long v11, v21, v73

    shr-long v11, v11, v39

    shl-long v69, v11, v39

    sub-long v29, v29, v77

    add-long v59, v59, v105

    mul-long v77, v59, v79

    add-long v29, v29, v77

    mul-long v77, v57, v81

    add-long v29, v29, v77

    mul-long v77, v55, v87

    sub-long v29, v29, v77

    mul-long v77, v53, v89

    add-long v29, v29, v77

    mul-long v77, v51, v95

    sub-long v29, v29, v77

    add-long v77, v29, v73

    shr-long v77, v77, v39

    shl-long v97, v77, v39

    sub-long v37, v37, v85

    add-long v37, v37, v75

    mul-long v75, v59, v87

    sub-long v37, v37, v75

    mul-long v75, v57, v89

    add-long v37, v37, v75

    mul-long v75, v55, v95

    sub-long v37, v37, v75

    add-long v75, v37, v73

    shr-long v75, v75, v39

    shl-long v85, v75, v39

    sub-long v43, v43, v93

    add-long v43, v43, v83

    mul-long v83, v59, v95

    sub-long v43, v43, v83

    add-long v83, v43, v73

    shr-long v83, v83, v39

    shl-long v93, v83, v39

    mul-long v99, v51, v71

    add-long v13, v13, v99

    mul-long v99, v48, v79

    add-long v13, v13, v99

    add-long v13, v13, v61

    add-long v61, v13, v73

    shr-long v61, v61, v39

    shl-long v99, v61, v39

    mul-long v101, v55, v71

    add-long v19, v19, v101

    mul-long v101, v53, v79

    add-long v19, v19, v101

    mul-long v101, v51, v81

    add-long v19, v19, v101

    mul-long v101, v48, v87

    sub-long v19, v19, v101

    add-long v19, v19, v65

    add-long v65, v19, v73

    shr-long v65, v65, v39

    shl-long v101, v65, v39

    mul-long v103, v59, v71

    add-long v25, v25, v103

    mul-long v103, v57, v79

    add-long v25, v25, v103

    mul-long v103, v55, v81

    add-long v25, v25, v103

    mul-long v103, v53, v87

    sub-long v25, v25, v103

    mul-long v51, v51, v89

    add-long v25, v25, v51

    mul-long v48, v48, v95

    sub-long v25, v25, v48

    add-long v25, v25, v11

    add-long v11, v25, v73

    shr-long v11, v11, v39

    shl-long v48, v11, v39

    sub-long v33, v33, v109

    mul-long v51, v59, v81

    add-long v33, v33, v51

    mul-long v51, v57, v87

    sub-long v33, v33, v51

    mul-long v55, v55, v89

    add-long v33, v33, v55

    mul-long v53, v53, v95

    sub-long v33, v33, v53

    add-long v33, v33, v77

    add-long v51, v33, v73

    shr-long v51, v51, v39

    shl-long v53, v51, v39

    sub-long v40, v40, v111

    mul-long v59, v59, v89

    add-long v40, v40, v59

    mul-long v57, v57, v95

    sub-long v40, v40, v57

    add-long v40, v40, v75

    add-long v55, v40, v73

    shr-long v55, v55, v39

    shl-long v57, v55, v39

    sub-long v46, v46, v63

    add-long v46, v46, v83

    add-long v73, v46, v73

    shr-long v59, v73, v39

    shl-long v63, v59, v39

    sub-long v9, v9, v91

    mul-long v73, v59, v71

    add-long v9, v9, v73

    shr-long v73, v9, v39

    shl-long v75, v73, v39

    sub-long v13, v13, v99

    mul-long v77, v59, v79

    add-long v13, v13, v77

    add-long v13, v13, v73

    shr-long v73, v13, v39

    shl-long v77, v73, v39

    sub-long v16, v16, v67

    add-long v16, v16, v61

    mul-long v61, v59, v81

    add-long v16, v16, v61

    add-long v16, v16, v73

    shr-long v61, v16, v39

    shl-long v67, v61, v39

    sub-long v19, v19, v101

    mul-long v73, v59, v87

    sub-long v19, v19, v73

    add-long v19, v19, v61

    shr-long v61, v19, v39

    shl-long v73, v61, v39

    sub-long v21, v21, v69

    add-long v21, v21, v65

    mul-long v65, v59, v89

    add-long v21, v21, v65

    add-long v21, v21, v61

    shr-long v61, v21, v39

    shl-long v65, v61, v39

    sub-long v25, v25, v48

    mul-long v59, v59, v95

    sub-long v25, v25, v59

    add-long v25, v25, v61

    shr-long v48, v25, v39

    shl-long v59, v48, v39

    sub-long v29, v29, v97

    add-long v29, v29, v11

    add-long v29, v29, v48

    shr-long v11, v29, v39

    shl-long v48, v11, v39

    sub-long v33, v33, v53

    add-long v33, v33, v11

    shr-long v11, v33, v39

    shl-long v53, v11, v39

    sub-long v37, v37, v85

    add-long v37, v37, v51

    add-long v37, v37, v11

    shr-long v11, v37, v39

    shl-long v51, v11, v39

    sub-long v40, v40, v57

    add-long v40, v40, v11

    shr-long v11, v40, v39

    shl-long v57, v11, v39

    sub-long v43, v43, v93

    add-long v43, v43, v55

    add-long v43, v43, v11

    shr-long v11, v43, v39

    shl-long v55, v11, v39

    sub-long v46, v46, v63

    add-long v46, v46, v11

    shr-long v11, v46, v39

    shl-long v61, v11, v39

    sub-long v9, v9, v75

    mul-long v71, v71, v11

    add-long v9, v9, v71

    shr-long v63, v9, v39

    shl-long v69, v63, v39

    sub-long v13, v13, v77

    mul-long v79, v79, v11

    add-long v13, v13, v79

    add-long v13, v13, v63

    shr-long v63, v13, v39

    shl-long v71, v63, v39

    sub-long v16, v16, v67

    mul-long v81, v81, v11

    add-long v16, v16, v81

    add-long v16, v16, v63

    shr-long v63, v16, v39

    shl-long v67, v63, v39

    sub-long v19, v19, v73

    mul-long v87, v87, v11

    sub-long v19, v19, v87

    add-long v19, v19, v63

    shr-long v63, v19, v39

    shl-long v73, v63, v39

    sub-long v21, v21, v65

    mul-long v89, v89, v11

    add-long v21, v21, v89

    add-long v21, v21, v63

    shr-long v63, v21, v39

    shl-long v65, v63, v39

    sub-long v25, v25, v59

    mul-long v11, v11, v95

    sub-long v25, v25, v11

    add-long v25, v25, v63

    shr-long v11, v25, v39

    shl-long v59, v11, v39

    sub-long v29, v29, v48

    add-long v29, v29, v11

    shr-long v11, v29, v39

    shl-long v48, v11, v39

    sub-long v33, v33, v53

    add-long v33, v33, v11

    shr-long v11, v33, v39

    shl-long v53, v11, v39

    sub-long v37, v37, v51

    add-long v37, v37, v11

    shr-long v11, v37, v39

    shl-long v51, v11, v39

    sub-long v40, v40, v57

    add-long v40, v40, v11

    shr-long v11, v40, v39

    shl-long v57, v11, v39

    sub-long v43, v43, v55

    add-long v43, v43, v11

    shr-long v11, v43, v39

    shl-long v55, v11, v39

    sub-long v9, v9, v69

    long-to-int v5, v9

    int-to-byte v5, v5

    .line 34
    aput-byte v5, v6, v18

    sub-long v33, v33, v53

    sub-long v29, v29, v48

    sub-long v25, v25, v59

    sub-long v21, v21, v65

    sub-long v19, v19, v73

    sub-long v16, v16, v67

    sub-long v13, v13, v71

    move-object/from16 v48, v6

    const/16 v49, 0x8

    shr-long v5, v9, v49

    long-to-int v5, v5

    int-to-byte v5, v5

    .line 35
    aput-byte v5, v48, v27

    const/16 v5, 0x10

    shr-long/2addr v9, v5

    shl-long v53, v13, v15

    or-long v9, v9, v53

    long-to-int v6, v9

    int-to-byte v6, v6

    .line 36
    aput-byte v6, v48, v24

    shr-long v9, v13, v35

    long-to-int v6, v9

    int-to-byte v6, v6

    .line 37
    aput-byte v6, v48, v35

    const/16 v6, 0xb

    shr-long v9, v13, v6

    long-to-int v9, v9

    int-to-byte v9, v9

    .line 38
    aput-byte v9, v48, v23

    const/16 v9, 0x13

    shr-long/2addr v13, v9

    shl-long v53, v16, v24

    or-long v13, v13, v53

    long-to-int v10, v13

    int-to-byte v10, v10

    .line 39
    aput-byte v10, v48, v15

    shr-long v13, v16, v31

    long-to-int v10, v13

    int-to-byte v10, v10

    .line 40
    aput-byte v10, v48, v31

    const/16 v10, 0xe

    shr-long v13, v16, v10

    shl-long v16, v19, p0

    or-long v13, v13, v16

    long-to-int v13, v13

    int-to-byte v13, v13

    .line 41
    aput-byte v13, v48, p0

    shr-long v13, v19, v27

    long-to-int v13, v13

    int-to-byte v13, v13

    .line 42
    aput-byte v13, v48, v49

    const/16 v13, 0x9

    move v14, v5

    move/from16 v16, v6

    shr-long v5, v19, v13

    long-to-int v5, v5

    int-to-byte v5, v5

    .line 43
    aput-byte v5, v48, v13

    const/16 v5, 0x11

    shr-long v19, v19, v5

    shl-long v53, v21, v23

    move/from16 v17, v5

    or-long v5, v19, v53

    long-to-int v5, v5

    int-to-byte v5, v5

    .line 44
    aput-byte v5, v48, v3

    shr-long v5, v21, v23

    long-to-int v5, v5

    int-to-byte v5, v5

    .line 45
    aput-byte v5, v48, v16

    const/16 v19, 0xc

    shr-long v5, v21, v19

    long-to-int v5, v5

    int-to-byte v5, v5

    .line 46
    aput-byte v5, v48, v19

    const/16 v5, 0x14

    shr-long v19, v21, v5

    add-long v21, v25, v25

    move v6, v9

    move/from16 v23, v10

    or-long v9, v19, v21

    long-to-int v9, v9

    int-to-byte v9, v9

    .line 47
    aput-byte v9, v48, v28

    shr-long v9, v25, p0

    long-to-int v9, v9

    int-to-byte v9, v9

    .line 48
    aput-byte v9, v48, v23

    shr-long v9, v25, v32

    shl-long v19, v29, v31

    or-long v9, v9, v19

    long-to-int v9, v9

    int-to-byte v9, v9

    .line 49
    aput-byte v9, v48, v32

    shr-long v9, v29, v24

    long-to-int v9, v9

    int-to-byte v9, v9

    .line 50
    aput-byte v9, v48, v14

    shr-long v9, v29, v3

    long-to-int v9, v9

    int-to-byte v9, v9

    .line 51
    aput-byte v9, v48, v17

    shr-long v9, v29, v36

    shl-long v19, v33, v35

    or-long v9, v9, v19

    long-to-int v9, v9

    int-to-byte v9, v9

    .line 52
    aput-byte v9, v48, v36

    sub-long v46, v46, v61

    sub-long v43, v43, v55

    add-long v46, v46, v11

    sub-long v40, v40, v57

    sub-long v9, v37, v51

    shr-long v11, v33, v15

    long-to-int v11, v11

    int-to-byte v11, v11

    .line 53
    aput-byte v11, v48, v6

    shr-long v11, v33, v28

    long-to-int v11, v11

    int-to-byte v11, v11

    .line 54
    aput-byte v11, v48, v5

    long-to-int v11, v9

    int-to-byte v11, v11

    .line 55
    aput-byte v11, v48, v39

    shr-long v11, v9, v49

    long-to-int v11, v11

    int-to-byte v11, v11

    const/16 v12, 0x16

    .line 56
    aput-byte v11, v48, v12

    shr-long/2addr v9, v14

    shl-long v11, v40, v15

    or-long/2addr v9, v11

    long-to-int v9, v9

    int-to-byte v9, v9

    .line 57
    aput-byte v9, v48, v42

    shr-long v9, v40, v35

    long-to-int v9, v9

    int-to-byte v9, v9

    const/16 v10, 0x18

    .line 58
    aput-byte v9, v48, v10

    shr-long v9, v40, v16

    long-to-int v9, v9

    int-to-byte v9, v9

    const/16 v10, 0x19

    .line 59
    aput-byte v9, v48, v10

    shr-long v9, v40, v6

    shl-long v11, v43, v24

    or-long/2addr v9, v11

    long-to-int v6, v9

    int-to-byte v6, v6

    .line 60
    aput-byte v6, v48, v45

    shr-long v9, v43, v31

    long-to-int v6, v9

    int-to-byte v6, v6

    const/16 v9, 0x1b

    .line 61
    aput-byte v6, v48, v9

    shr-long v9, v43, v23

    shl-long v11, v46, p0

    or-long/2addr v9, v11

    long-to-int v6, v9

    int-to-byte v6, v6

    .line 62
    aput-byte v6, v48, v7

    shr-long v6, v46, v27

    long-to-int v6, v6

    int-to-byte v6, v6

    const/16 v7, 0x1d

    .line 63
    aput-byte v6, v48, v7

    shr-long v6, v46, v13

    long-to-int v6, v6

    int-to-byte v6, v6

    const/16 v7, 0x1e

    .line 64
    aput-byte v6, v48, v7

    shr-long v6, v46, v17

    long-to-int v6, v6

    int-to-byte v6, v6

    .line 65
    aput-byte v6, v48, v50

    new-array v6, v3, [J

    .line 66
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zztw;->zzg([B)[J

    move-result-object v7

    new-array v9, v3, [J

    const-wide/16 v10, 0x1

    aput-wide v10, v9, v18

    new-array v10, v3, [J

    new-array v11, v3, [J

    new-array v12, v3, [J

    new-array v13, v3, [J

    new-array v14, v3, [J

    .line 67
    invoke-static {v11, v7}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    .line 68
    sget-object v2, Lcom/google/android/recaptcha/internal/zztr;->zza:[J

    invoke-static {v12, v11, v2}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 69
    invoke-static {v11, v11, v9}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    .line 70
    invoke-static {v12, v12, v9}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    new-array v2, v3, [J

    .line 71
    invoke-static {v2, v12}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    .line 72
    invoke-static {v2, v2, v12}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 73
    invoke-static {v6, v2}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    .line 74
    invoke-static {v6, v6, v12}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 75
    invoke-static {v6, v6, v11}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    new-array v8, v3, [J

    new-array v5, v3, [J

    new-array v15, v3, [J

    .line 76
    invoke-static {v8, v6}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    .line 77
    invoke-static {v5, v8}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    .line 78
    invoke-static {v5, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    .line 79
    invoke-static {v5, v6, v5}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 80
    invoke-static {v8, v8, v5}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 81
    invoke-static {v8, v8}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    .line 82
    invoke-static {v8, v5, v8}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 83
    invoke-static {v5, v8}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    move/from16 v3, v27

    const/4 v0, 0x5

    :goto_1
    if-ge v3, v0, :cond_1

    .line 84
    invoke-static {v5, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    .line 85
    :cond_1
    invoke-static {v8, v5, v8}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 86
    invoke-static {v5, v8}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    move/from16 v0, v27

    :goto_2
    const/16 v3, 0xa

    if-ge v0, v3, :cond_2

    .line 87
    invoke-static {v5, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    .line 88
    :cond_2
    invoke-static {v5, v5, v8}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 89
    invoke-static {v15, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    move/from16 v0, v27

    const/16 v3, 0x14

    :goto_3
    if-ge v0, v3, :cond_3

    .line 90
    invoke-static {v15, v15}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_3

    .line 91
    :cond_3
    invoke-static {v5, v15, v5}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 92
    invoke-static {v5, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    move/from16 v0, v27

    :goto_4
    const/16 v3, 0xa

    if-ge v0, v3, :cond_4

    .line 93
    invoke-static {v5, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_4

    .line 94
    :cond_4
    invoke-static {v8, v5, v8}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 95
    invoke-static {v5, v8}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    move/from16 v0, v27

    :goto_5
    const/16 v3, 0x32

    if-ge v0, v3, :cond_5

    .line 96
    invoke-static {v5, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_5

    .line 97
    :cond_5
    invoke-static {v5, v5, v8}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 98
    invoke-static {v15, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    move/from16 v0, v27

    :goto_6
    const/16 v3, 0x64

    if-ge v0, v3, :cond_6

    .line 99
    invoke-static {v15, v15}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_6

    .line 100
    :cond_6
    invoke-static {v5, v15, v5}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 101
    invoke-static {v5, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    move/from16 v0, v27

    const/16 v3, 0x32

    :goto_7
    if-ge v0, v3, :cond_7

    .line 102
    invoke-static {v5, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_7

    .line 103
    :cond_7
    invoke-static {v8, v5, v8}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 104
    invoke-static {v8, v8}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    .line 105
    invoke-static {v8, v8}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    .line 106
    invoke-static {v6, v8, v6}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 107
    invoke-static {v6, v6, v2}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 108
    invoke-static {v6, v6, v11}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 109
    invoke-static {v13, v6}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    .line 110
    invoke-static {v13, v13, v12}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 111
    invoke-static {v14, v13, v11}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    .line 112
    invoke-static {v14}, Lcom/google/android/recaptcha/internal/zztp;->zze([J)Z

    move-result v0

    if-eqz v0, :cond_9

    .line 113
    invoke-static {v14, v13, v11}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    .line 114
    invoke-static {v14}, Lcom/google/android/recaptcha/internal/zztp;->zze([J)Z

    move-result v0

    if-nez v0, :cond_8

    .line 115
    sget-object v0, Lcom/google/android/recaptcha/internal/zztr;->zzc:[J

    .line 116
    invoke-static {v6, v6, v0}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    goto :goto_8

    .line 115
    :cond_8
    const-string v0, "Cannot convert given bytes to extended projective coordinates. No square root exists for modulo 2^255-19"

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return v18

    .line 117
    :cond_9
    :goto_8
    invoke-static {v6}, Lcom/google/android/recaptcha/internal/zztp;->zze([J)Z

    move-result v0

    if-nez v0, :cond_b

    aget-byte v0, v1, v50

    const/16 v2, 0xff

    and-int/2addr v0, v2

    shr-int/lit8 v0, v0, 0x7

    if-nez v0, :cond_a

    goto :goto_9

    .line 118
    :cond_a
    const-string v0, "Cannot convert given bytes to extended projective coordinates. Computed x is zero and encoded x\'s least significant bit is not zero"

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return v18

    :cond_b
    const/16 v2, 0xff

    .line 119
    :goto_9
    invoke-static {v6}, Lcom/google/android/recaptcha/internal/zztp;->zzf([J)I

    move-result v0

    aget-byte v1, v1, v50

    and-int/2addr v1, v2

    shr-int/lit8 v1, v1, 0x7

    if-ne v0, v1, :cond_c

    .line 120
    invoke-static {v6, v6}, Lcom/google/android/recaptcha/internal/zztp;->zzm([J[J)V

    .line 121
    :cond_c
    invoke-static {v10, v6, v7}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    new-instance v0, Lcom/google/android/recaptcha/internal/zzto;

    new-instance v1, Lcom/google/android/recaptcha/internal/zztn;

    .line 122
    invoke-direct {v1, v6, v7, v9}, Lcom/google/android/recaptcha/internal/zztn;-><init>([J[J[J)V

    invoke-direct {v0, v1, v10}, Lcom/google/android/recaptcha/internal/zzto;-><init>(Lcom/google/android/recaptcha/internal/zztn;[J)V

    move/from16 v1, v49

    new-array v3, v1, [Lcom/google/android/recaptcha/internal/zztl;

    new-instance v1, Lcom/google/android/recaptcha/internal/zztl;

    .line 123
    invoke-direct {v1, v0}, Lcom/google/android/recaptcha/internal/zztl;-><init>(Lcom/google/android/recaptcha/internal/zzto;)V

    aput-object v1, v3, v18

    new-instance v1, Lcom/google/android/recaptcha/internal/zztm;

    new-instance v5, Lcom/google/android/recaptcha/internal/zztn;

    .line 124
    invoke-direct {v5}, Lcom/google/android/recaptcha/internal/zztn;-><init>()V

    const/16 v6, 0xa

    new-array v6, v6, [J

    invoke-direct {v1, v5, v6}, Lcom/google/android/recaptcha/internal/zztm;-><init>(Lcom/google/android/recaptcha/internal/zztn;[J)V

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzto;->zza:Lcom/google/android/recaptcha/internal/zztn;

    .line 125
    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zztp;->zzi(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zztn;)V

    new-instance v0, Lcom/google/android/recaptcha/internal/zzto;

    .line 126
    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzto;-><init>(Lcom/google/android/recaptcha/internal/zztm;)V

    move/from16 v5, v27

    const/16 v6, 0x8

    :goto_a
    if-ge v5, v6, :cond_d

    add-int/lit8 v7, v5, -0x1

    .line 127
    aget-object v7, v3, v7

    invoke-static {v1, v0, v7}, Lcom/google/android/recaptcha/internal/zztp;->zzg(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztk;)V

    new-instance v7, Lcom/google/android/recaptcha/internal/zztl;

    new-instance v8, Lcom/google/android/recaptcha/internal/zzto;

    .line 128
    invoke-direct {v8, v1}, Lcom/google/android/recaptcha/internal/zzto;-><init>(Lcom/google/android/recaptcha/internal/zztm;)V

    invoke-direct {v7, v8}, Lcom/google/android/recaptcha/internal/zztl;-><init>(Lcom/google/android/recaptcha/internal/zzto;)V

    aput-object v7, v3, v5

    add-int/lit8 v5, v5, 0x1

    goto :goto_a

    .line 129
    :cond_d
    invoke-static/range {v48 .. v48}, Lcom/google/android/recaptcha/internal/zztp;->zzl([B)[B

    move-result-object v0

    .line 130
    invoke-static {v4}, Lcom/google/android/recaptcha/internal/zztp;->zzl([B)[B

    move-result-object v1

    new-instance v4, Lcom/google/android/recaptcha/internal/zztm;

    sget-object v5, Lcom/google/android/recaptcha/internal/zztp;->zzc:Lcom/google/android/recaptcha/internal/zztm;

    .line 131
    invoke-direct {v4, v5}, Lcom/google/android/recaptcha/internal/zztm;-><init>(Lcom/google/android/recaptcha/internal/zztm;)V

    new-instance v5, Lcom/google/android/recaptcha/internal/zzto;

    invoke-direct {v5}, Lcom/google/android/recaptcha/internal/zzto;-><init>()V

    move v8, v2

    :goto_b
    if-ltz v8, :cond_f

    .line 132
    aget-byte v2, v0, v8

    if-nez v2, :cond_f

    aget-byte v2, v1, v8

    if-eqz v2, :cond_e

    goto :goto_c

    :cond_e
    add-int/lit8 v8, v8, -0x1

    goto :goto_b

    :cond_f
    :goto_c
    if-ltz v8, :cond_14

    new-instance v2, Lcom/google/android/recaptcha/internal/zztn;

    .line 133
    invoke-direct {v2, v4}, Lcom/google/android/recaptcha/internal/zztn;-><init>(Lcom/google/android/recaptcha/internal/zztm;)V

    invoke-static {v4, v2}, Lcom/google/android/recaptcha/internal/zztp;->zzi(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zztn;)V

    .line 134
    aget-byte v2, v0, v8

    if-lez v2, :cond_10

    .line 135
    invoke-static {v5, v4}, Lcom/google/android/recaptcha/internal/zzto;->zza(Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zzto;

    aget-byte v2, v0, v8

    div-int/lit8 v2, v2, 0x2

    aget-object v2, v3, v2

    invoke-static {v4, v5, v2}, Lcom/google/android/recaptcha/internal/zztp;->zzg(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztk;)V

    goto :goto_d

    :cond_10
    if-gez v2, :cond_11

    .line 136
    invoke-static {v5, v4}, Lcom/google/android/recaptcha/internal/zzto;->zza(Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zzto;

    aget-byte v2, v0, v8

    neg-int v2, v2

    div-int/lit8 v2, v2, 0x2

    aget-object v2, v3, v2

    invoke-static {v4, v5, v2}, Lcom/google/android/recaptcha/internal/zztp;->zzh(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztk;)V

    .line 137
    :cond_11
    :goto_d
    aget-byte v2, v1, v8

    if-lez v2, :cond_12

    .line 138
    invoke-static {v5, v4}, Lcom/google/android/recaptcha/internal/zzto;->zza(Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zzto;

    sget-object v2, Lcom/google/android/recaptcha/internal/zztr;->zze:[Lcom/google/android/recaptcha/internal/zztk;

    aget-byte v6, v1, v8

    div-int/lit8 v6, v6, 0x2

    aget-object v2, v2, v6

    invoke-static {v4, v5, v2}, Lcom/google/android/recaptcha/internal/zztp;->zzg(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztk;)V

    goto :goto_e

    :cond_12
    if-gez v2, :cond_13

    .line 139
    invoke-static {v5, v4}, Lcom/google/android/recaptcha/internal/zzto;->zza(Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zzto;

    sget-object v2, Lcom/google/android/recaptcha/internal/zztr;->zze:[Lcom/google/android/recaptcha/internal/zztk;

    aget-byte v6, v1, v8

    neg-int v6, v6

    div-int/lit8 v6, v6, 0x2

    aget-object v2, v2, v6

    invoke-static {v4, v5, v2}, Lcom/google/android/recaptcha/internal/zztp;->zzh(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztk;)V

    :cond_13
    :goto_e
    add-int/lit8 v8, v8, -0x1

    goto :goto_c

    .line 136
    :cond_14
    new-instance v0, Lcom/google/android/recaptcha/internal/zztn;

    .line 140
    invoke-direct {v0, v4}, Lcom/google/android/recaptcha/internal/zztn;-><init>(Lcom/google/android/recaptcha/internal/zztm;)V

    .line 141
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zztn;->zzb()[B

    move-result-object v0

    move/from16 v1, v18

    const/16 v2, 0x20

    :goto_f
    if-ge v1, v2, :cond_16

    .line 142
    aget-byte v3, v0, v1

    aget-byte v4, p1, v1

    if-eq v3, v4, :cond_15

    return v18

    :cond_15
    add-int/lit8 v1, v1, 0x1

    goto :goto_f

    :cond_16
    return v27

    :cond_17
    move/from16 v18, v3

    goto :goto_10

    :cond_18
    move-object/from16 v7, p0

    move/from16 v18, v3

    move/from16 v50, v5

    add-int/lit8 v6, v6, -0x1

    move-object/from16 v0, p1

    goto/16 :goto_0

    :goto_10
    return v18
.end method

.method public static zzd()V
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zztr;->zza:[J

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const-string v0, "Could not initialize Ed25519."

    .line 2
    invoke-static {v0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic zze([J)Z
    .locals 3

    const/16 v0, 0xb

    .line 1
    new-array v0, v0, [J

    const/16 v1, 0xa

    const/4 v2, 0x0

    invoke-static {p0, v2, v0, v2, v1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 2
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zztw;->zzd([J)V

    .line 3
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zztw;->zzh([J)[B

    move-result-object p0

    move v0, v2

    :goto_0
    const/16 v1, 0x20

    if-ge v0, v1, :cond_1

    .line 4
    aget-byte v1, p0, v0

    if-eqz v1, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return v2
.end method

.method public static synthetic zzf([J)I
    .locals 1

    .line 1
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zztw;->zzh([J)[B

    move-result-object p0

    const/4 v0, 0x0

    aget-byte p0, p0, v0

    and-int/lit8 p0, p0, 0x1

    return p0
.end method

.method private static zzg(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztk;)V
    .locals 7

    .line 1
    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zzto;->zza:Lcom/google/android/recaptcha/internal/zztn;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zztm;->zza:Lcom/google/android/recaptcha/internal/zztn;

    const/16 v2, 0xa

    new-array v2, v2, [J

    iget-object v3, v1, Lcom/google/android/recaptcha/internal/zztn;->zza:[J

    iget-object v4, v0, Lcom/google/android/recaptcha/internal/zztn;->zzb:[J

    iget-object v5, v0, Lcom/google/android/recaptcha/internal/zztn;->zza:[J

    invoke-static {v3, v4, v5}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    iget-object v6, v1, Lcom/google/android/recaptcha/internal/zztn;->zzb:[J

    .line 2
    invoke-static {v6, v4, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    .line 3
    iget-object v4, p2, Lcom/google/android/recaptcha/internal/zztk;->zzb:[J

    invoke-static {v6, v6, v4}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 4
    iget-object v4, p2, Lcom/google/android/recaptcha/internal/zztk;->zza:[J

    iget-object v1, v1, Lcom/google/android/recaptcha/internal/zztn;->zzc:[J

    invoke-static {v1, v3, v4}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zztm;->zzb:[J

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzto;->zzb:[J

    .line 5
    iget-object v4, p2, Lcom/google/android/recaptcha/internal/zztk;->zzc:[J

    invoke-static {p0, p1, v4}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    iget-object p1, v0, Lcom/google/android/recaptcha/internal/zztn;->zzc:[J

    .line 6
    invoke-virtual {p2, v3, p1}, Lcom/google/android/recaptcha/internal/zztk;->zza([J[J)V

    .line 7
    invoke-static {v2, v3, v3}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    .line 8
    invoke-static {v3, v1, v6}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    .line 9
    invoke-static {v6, v1, v6}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    .line 10
    invoke-static {v1, v2, p0}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    .line 11
    invoke-static {p0, v2, p0}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    return-void
.end method

.method private static zzh(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztk;)V
    .locals 7

    .line 1
    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zzto;->zza:Lcom/google/android/recaptcha/internal/zztn;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zztm;->zza:Lcom/google/android/recaptcha/internal/zztn;

    const/16 v2, 0xa

    new-array v2, v2, [J

    iget-object v3, v1, Lcom/google/android/recaptcha/internal/zztn;->zza:[J

    iget-object v4, v0, Lcom/google/android/recaptcha/internal/zztn;->zzb:[J

    iget-object v5, v0, Lcom/google/android/recaptcha/internal/zztn;->zza:[J

    invoke-static {v3, v4, v5}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    iget-object v6, v1, Lcom/google/android/recaptcha/internal/zztn;->zzb:[J

    .line 2
    invoke-static {v6, v4, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    .line 3
    iget-object v4, p2, Lcom/google/android/recaptcha/internal/zztk;->zza:[J

    invoke-static {v6, v6, v4}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    .line 4
    iget-object v4, p2, Lcom/google/android/recaptcha/internal/zztk;->zzb:[J

    iget-object v1, v1, Lcom/google/android/recaptcha/internal/zztn;->zzc:[J

    invoke-static {v1, v3, v4}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zztm;->zzb:[J

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzto;->zzb:[J

    .line 5
    iget-object v4, p2, Lcom/google/android/recaptcha/internal/zztk;->zzc:[J

    invoke-static {p0, p1, v4}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    iget-object p1, v0, Lcom/google/android/recaptcha/internal/zztn;->zzc:[J

    .line 6
    invoke-virtual {p2, v3, p1}, Lcom/google/android/recaptcha/internal/zztk;->zza([J[J)V

    .line 7
    invoke-static {v2, v3, v3}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    .line 8
    invoke-static {v3, v1, v6}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    .line 9
    invoke-static {v6, v1, v6}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    .line 10
    invoke-static {v1, v2, p0}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    .line 11
    invoke-static {p0, v2, p0}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    return-void
.end method

.method private static zzi(Lcom/google/android/recaptcha/internal/zztm;Lcom/google/android/recaptcha/internal/zztn;)V
    .locals 6

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zztm;->zza:Lcom/google/android/recaptcha/internal/zztn;

    iget-object v1, v0, Lcom/google/android/recaptcha/internal/zztn;->zza:[J

    iget-object v2, p1, Lcom/google/android/recaptcha/internal/zztn;->zza:[J

    const/16 v3, 0xa

    new-array v3, v3, [J

    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    iget-object v4, v0, Lcom/google/android/recaptcha/internal/zztn;->zzc:[J

    iget-object v5, p1, Lcom/google/android/recaptcha/internal/zztn;->zzb:[J

    .line 2
    invoke-static {v4, v5}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zztm;->zzb:[J

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zztn;->zzc:[J

    .line 3
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    .line 4
    invoke-static {p0, p0, p0}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    iget-object p1, v0, Lcom/google/android/recaptcha/internal/zztn;->zzb:[J

    .line 5
    invoke-static {p1, v2, v5}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    .line 6
    invoke-static {v3, p1}, Lcom/google/android/recaptcha/internal/zztw;->zzf([J[J)V

    .line 7
    invoke-static {p1, v4, v1}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    .line 8
    invoke-static {v4, v4, v1}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    .line 9
    invoke-static {v1, v3, p1}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    .line 10
    invoke-static {p0, p0, v4}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    return-void
.end method

.method private static zzj(II)I
    .locals 0

    xor-int/2addr p0, p1

    not-int p0, p0

    and-int/lit16 p0, p0, 0xff

    shl-int/lit8 p1, p0, 0x4

    and-int/2addr p0, p1

    shl-int/lit8 p1, p0, 0x2

    and-int/2addr p0, p1

    add-int p1, p0, p0

    and-int/2addr p0, p1

    shr-int/lit8 p0, p0, 0x7

    return p0
.end method

.method private static zzk(Lcom/google/android/recaptcha/internal/zztk;IB)V
    .locals 6

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zztr;->zzd:[[Lcom/google/android/recaptcha/internal/zztk;

    aget-object v1, v0, p1

    const/4 v2, 0x0

    aget-object v1, v1, v2

    and-int/lit16 v2, p2, 0xff

    const/4 v3, 0x7

    shr-int/2addr v2, v3

    neg-int v4, v2

    and-int/2addr v4, p2

    add-int/2addr v4, v4

    sub-int/2addr p2, v4

    const/4 v4, 0x1

    invoke-static {p2, v4}, Lcom/google/android/recaptcha/internal/zztp;->zzj(II)I

    move-result v5

    invoke-virtual {p0, v1, v5}, Lcom/google/android/recaptcha/internal/zztk;->zzb(Lcom/google/android/recaptcha/internal/zztk;I)V

    .line 2
    aget-object v1, v0, p1

    aget-object v1, v1, v4

    const/4 v4, 0x2

    invoke-static {p2, v4}, Lcom/google/android/recaptcha/internal/zztp;->zzj(II)I

    move-result v5

    invoke-virtual {p0, v1, v5}, Lcom/google/android/recaptcha/internal/zztk;->zzb(Lcom/google/android/recaptcha/internal/zztk;I)V

    .line 3
    aget-object v1, v0, p1

    aget-object v1, v1, v4

    const/4 v4, 0x3

    invoke-static {p2, v4}, Lcom/google/android/recaptcha/internal/zztp;->zzj(II)I

    move-result v5

    invoke-virtual {p0, v1, v5}, Lcom/google/android/recaptcha/internal/zztk;->zzb(Lcom/google/android/recaptcha/internal/zztk;I)V

    .line 4
    aget-object v1, v0, p1

    aget-object v1, v1, v4

    const/4 v4, 0x4

    invoke-static {p2, v4}, Lcom/google/android/recaptcha/internal/zztp;->zzj(II)I

    move-result v5

    invoke-virtual {p0, v1, v5}, Lcom/google/android/recaptcha/internal/zztk;->zzb(Lcom/google/android/recaptcha/internal/zztk;I)V

    .line 5
    aget-object v1, v0, p1

    aget-object v1, v1, v4

    const/4 v4, 0x5

    invoke-static {p2, v4}, Lcom/google/android/recaptcha/internal/zztp;->zzj(II)I

    move-result v5

    invoke-virtual {p0, v1, v5}, Lcom/google/android/recaptcha/internal/zztk;->zzb(Lcom/google/android/recaptcha/internal/zztk;I)V

    .line 6
    aget-object v1, v0, p1

    aget-object v1, v1, v4

    const/4 v4, 0x6

    invoke-static {p2, v4}, Lcom/google/android/recaptcha/internal/zztp;->zzj(II)I

    move-result v5

    invoke-virtual {p0, v1, v5}, Lcom/google/android/recaptcha/internal/zztk;->zzb(Lcom/google/android/recaptcha/internal/zztk;I)V

    .line 7
    aget-object v1, v0, p1

    aget-object v1, v1, v4

    invoke-static {p2, v3}, Lcom/google/android/recaptcha/internal/zztp;->zzj(II)I

    move-result v4

    invoke-virtual {p0, v1, v4}, Lcom/google/android/recaptcha/internal/zztk;->zzb(Lcom/google/android/recaptcha/internal/zztk;I)V

    .line 8
    aget-object p1, v0, p1

    aget-object p1, p1, v3

    const/16 v0, 0x8

    invoke-static {p2, v0}, Lcom/google/android/recaptcha/internal/zztp;->zzj(II)I

    move-result p2

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zztk;->zzb(Lcom/google/android/recaptcha/internal/zztk;I)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zztk;->zzc:[J

    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zztk;->zza:[J

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zztk;->zzb:[J

    const/16 v1, 0xa

    .line 9
    invoke-static {v0, v1}, Ljava/util/Arrays;->copyOf([JI)[J

    move-result-object v0

    .line 10
    invoke-static {p2, v1}, Ljava/util/Arrays;->copyOf([JI)[J

    move-result-object p2

    .line 11
    invoke-static {p1, v1}, Ljava/util/Arrays;->copyOf([JI)[J

    move-result-object p1

    .line 12
    invoke-static {p1, p1}, Lcom/google/android/recaptcha/internal/zztp;->zzm([J[J)V

    new-instance v1, Lcom/google/android/recaptcha/internal/zztk;

    invoke-direct {v1, v0, p2, p1}, Lcom/google/android/recaptcha/internal/zztk;-><init>([J[J[J)V

    .line 13
    invoke-virtual {p0, v1, v2}, Lcom/google/android/recaptcha/internal/zztk;->zzb(Lcom/google/android/recaptcha/internal/zztk;I)V

    return-void
.end method

.method private static zzl([B)[B
    .locals 10

    const/16 v0, 0x100

    .line 1
    new-array v1, v0, [B

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    const/4 v4, 0x1

    if-ge v3, v0, :cond_0

    shr-int/lit8 v5, v3, 0x3

    aget-byte v5, p0, v5

    and-int/lit16 v5, v5, 0xff

    and-int/lit8 v6, v3, 0x7

    shr-int/2addr v5, v6

    and-int/2addr v4, v5

    int-to-byte v4, v4

    aput-byte v4, v1, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_0
    move p0, v2

    :goto_1
    if-ge p0, v0, :cond_5

    .line 2
    aget-byte v3, v1, p0

    if-eqz v3, :cond_4

    move v3, v4

    :goto_2
    const/4 v5, 0x6

    if-gt v3, v5, :cond_4

    add-int v5, p0, v3

    if-ge v5, v0, :cond_4

    .line 3
    aget-byte v6, v1, v5

    if-eqz v6, :cond_3

    .line 4
    aget-byte v7, v1, p0

    shl-int/2addr v6, v3

    add-int v8, v7, v6

    const/16 v9, 0xf

    if-gt v8, v9, :cond_1

    int-to-byte v6, v8

    .line 5
    aput-byte v6, v1, p0

    .line 6
    aput-byte v2, v1, v5

    goto :goto_4

    :cond_1
    sub-int/2addr v7, v6

    const/16 v6, -0xf

    if-lt v7, v6, :cond_4

    int-to-byte v6, v7

    .line 7
    aput-byte v6, v1, p0

    :goto_3
    if-ge v5, v0, :cond_3

    .line 8
    aget-byte v6, v1, v5

    if-nez v6, :cond_2

    .line 10
    aput-byte v4, v1, v5

    goto :goto_4

    .line 9
    :cond_2
    aput-byte v2, v1, v5

    add-int/lit8 v5, v5, 0x1

    goto :goto_3

    :cond_3
    :goto_4
    add-int/lit8 v3, v3, 0x1

    goto :goto_2

    :cond_4
    add-int/lit8 p0, p0, 0x1

    goto :goto_1

    :cond_5
    return-object v1
.end method

.method private static zzm([J[J)V
    .locals 3

    const/4 v0, 0x0

    .line 1
    :goto_0
    array-length v1, p1

    if-ge v0, v1, :cond_0

    .line 2
    aget-wide v1, p1, v0

    neg-long v1, v1

    aput-wide v1, p0, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method private static zzn([BI)J
    .locals 6

    .line 1
    aget-byte v0, p0, p1

    int-to-long v0, v0

    add-int/lit8 v2, p1, 0x1

    .line 2
    aget-byte v2, p0, v2

    and-int/lit16 v2, v2, 0xff

    add-int/lit8 p1, p1, 0x2

    .line 3
    aget-byte p0, p0, p1

    and-int/lit16 p0, p0, 0xff

    int-to-long v2, v2

    int-to-long p0, p0

    const-wide/16 v4, 0xff

    and-long/2addr v0, v4

    const/16 v4, 0x8

    shl-long/2addr v2, v4

    or-long/2addr v0, v2

    const/16 v2, 0x10

    shl-long/2addr p0, v2

    or-long/2addr p0, v0

    return-wide p0
.end method

.method private static zzo([BI)J
    .locals 3

    add-int/lit8 v0, p1, 0x3

    .line 1
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zztp;->zzn([BI)J

    move-result-wide v1

    .line 2
    aget-byte p0, p0, v0

    and-int/lit16 p0, p0, 0xff

    int-to-long p0, p0

    const/16 v0, 0x18

    shl-long/2addr p0, v0

    or-long/2addr p0, v1

    return-wide p0
.end method
