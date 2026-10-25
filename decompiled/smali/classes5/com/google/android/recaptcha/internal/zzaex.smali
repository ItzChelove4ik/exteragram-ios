.class final Lcom/google/android/recaptcha/internal/zzaex;
.super Lcom/google/android/recaptcha/internal/zzafa;
.source "SourceFile"


# instance fields
.field private final zzb:[B

.field private final zzc:I

.field private zzd:I


# direct methods
.method public constructor <init>([BIILcom/google/android/recaptcha/internal/zzaey;)V
    .locals 2

    const/4 p2, 0x0

    .line 1
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzafa;-><init>([B)V

    array-length p4, p1

    sub-int v0, p4, p3

    or-int/2addr v0, p3

    const/4 v1, 0x0

    if-ltz v0, :cond_0

    .line 4
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzb:[B

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    iput p3, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzc:I

    return-void

    .line 1
    :cond_0
    sget-object p0, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-static {p4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    .line 2
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p4

    .line 3
    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p3

    filled-new-array {p1, p4, p3}, [Ljava/lang/Object;

    move-result-object p1

    const-string p3, "Array range is invalid. Buffer.length=%d, offset=%d, length=%d"

    .line 4
    invoke-static {p0, p3, p1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw p2
.end method


# virtual methods
.method public final zza(II)V
    .locals 0

    shl-int/lit8 p1, p1, 0x3

    or-int/2addr p1, p2

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    return-void
.end method

.method public final zzb(II)V
    .locals 0

    shl-int/lit8 p1, p1, 0x3

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    .line 2
    invoke-virtual {p0, p2}, Lcom/google/android/recaptcha/internal/zzaex;->zzm(I)V

    return-void
.end method

.method public final zzc(II)V
    .locals 0

    shl-int/lit8 p1, p1, 0x3

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    .line 2
    invoke-virtual {p0, p2}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    return-void
.end method

.method public final zzd(II)V
    .locals 0

    shl-int/lit8 p1, p1, 0x3

    or-int/lit8 p1, p1, 0x5

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    .line 2
    invoke-virtual {p0, p2}, Lcom/google/android/recaptcha/internal/zzaex;->zzo(I)V

    return-void
.end method

.method public final zze(IJ)V
    .locals 0

    shl-int/lit8 p1, p1, 0x3

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    .line 2
    invoke-virtual {p0, p2, p3}, Lcom/google/android/recaptcha/internal/zzaex;->zzp(J)V

    return-void
.end method

.method public final zzf(IJ)V
    .locals 0

    shl-int/lit8 p1, p1, 0x3

    or-int/lit8 p1, p1, 0x1

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    .line 2
    invoke-virtual {p0, p2, p3}, Lcom/google/android/recaptcha/internal/zzaex;->zzq(J)V

    return-void
.end method

.method public final zzg(IZ)V
    .locals 0

    shl-int/lit8 p1, p1, 0x3

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    .line 2
    invoke-virtual {p0, p2}, Lcom/google/android/recaptcha/internal/zzaex;->zzl(B)V

    return-void
.end method

.method public final zzh(ILjava/lang/String;)V
    .locals 0

    shl-int/lit8 p1, p1, 0x3

    or-int/lit8 p1, p1, 0x2

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    .line 2
    invoke-virtual {p0, p2}, Lcom/google/android/recaptcha/internal/zzaex;->zzs(Ljava/lang/String;)V

    return-void
.end method

.method public final zzi(ILcom/google/android/recaptcha/internal/zzaer;)V
    .locals 0

    shl-int/lit8 p1, p1, 0x3

    or-int/lit8 p1, p1, 0x2

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    .line 2
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result p1

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    .line 3
    invoke-virtual {p2, p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzg(Lcom/google/android/recaptcha/internal/zzaej;)V

    return-void
.end method

.method public final zzl(B)V
    .locals 9

    .line 1
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    :try_start_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzb:[B
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_1

    add-int/lit8 v2, v1, 0x1

    :try_start_1
    aput-byte p1, v0, v1
    :try_end_1
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_1 .. :try_end_1} :catch_0

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :catch_0
    move-exception v0

    move v1, v2

    :goto_0
    move-object p1, v0

    move-object v8, p1

    goto :goto_1

    :catch_1
    move-exception v0

    goto :goto_0

    :goto_1
    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzc:I

    new-instance v2, Lcom/google/android/recaptcha/internal/zzaez;

    int-to-long v3, v1

    int-to-long v5, p0

    const/4 v7, 0x1

    .line 2
    invoke-direct/range {v2 .. v8}, Lcom/google/android/recaptcha/internal/zzaez;-><init>(JJILjava/lang/Throwable;)V

    .line 3
    throw v2
.end method

.method public final zzm(I)V
    .locals 9

    if-ltz p1, :cond_0

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    return-void

    :cond_0
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    :try_start_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzb:[B
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_0

    int-to-long v2, p1

    add-int/lit8 p1, v1, 0x1

    long-to-int v4, v2

    or-int/lit16 v4, v4, 0x80

    int-to-byte v4, v4

    .line 2
    :try_start_1
    aput-byte v4, v0, v1
    :try_end_1
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_1 .. :try_end_1} :catch_1

    add-int/lit8 v4, v1, 0x2

    const/4 v5, 0x7

    ushr-long v5, v2, v5

    long-to-int v5, v5

    or-int/lit16 v5, v5, 0x80

    int-to-byte v5, v5

    .line 3
    :try_start_2
    aput-byte v5, v0, p1
    :try_end_2
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_2 .. :try_end_2} :catch_3

    add-int/lit8 p1, v1, 0x3

    const/16 v5, 0xe

    ushr-long v5, v2, v5

    long-to-int v5, v5

    or-int/lit16 v5, v5, 0x80

    int-to-byte v5, v5

    .line 4
    :try_start_3
    aput-byte v5, v0, v4
    :try_end_3
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_3 .. :try_end_3} :catch_1

    add-int/lit8 v4, v1, 0x4

    const/16 v5, 0x15

    ushr-long v5, v2, v5

    long-to-int v5, v5

    or-int/lit16 v5, v5, 0x80

    int-to-byte v5, v5

    .line 5
    :try_start_4
    aput-byte v5, v0, p1
    :try_end_4
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_4 .. :try_end_4} :catch_3

    add-int/lit8 p1, v1, 0x5

    const/16 v5, 0x1c

    ushr-long/2addr v2, v5

    long-to-int v2, v2

    or-int/lit16 v2, v2, 0x80

    int-to-byte v2, v2

    .line 6
    :try_start_5
    aput-byte v2, v0, v4
    :try_end_5
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_5 .. :try_end_5} :catch_1

    add-int/lit8 v2, v1, 0x6

    const/4 v3, -0x1

    .line 7
    :try_start_6
    aput-byte v3, v0, p1
    :try_end_6
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_6 .. :try_end_6} :catch_2

    add-int/lit8 p1, v1, 0x7

    .line 8
    :try_start_7
    aput-byte v3, v0, v2
    :try_end_7
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_7 .. :try_end_7} :catch_1

    add-int/lit8 v2, v1, 0x8

    .line 9
    :try_start_8
    aput-byte v3, v0, p1
    :try_end_8
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_8 .. :try_end_8} :catch_2

    add-int/lit8 p1, v1, 0x9

    .line 10
    :try_start_9
    aput-byte v3, v0, v2
    :try_end_9
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_9 .. :try_end_9} :catch_1

    add-int/lit8 v1, v1, 0xa

    const/4 v2, 0x1

    .line 11
    :try_start_a
    aput-byte v2, v0, p1
    :try_end_a
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_a .. :try_end_a} :catch_0

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :catch_0
    move-exception v0

    move-object p1, v0

    move-object v8, p1

    goto :goto_0

    :catch_1
    move-exception v0

    move v1, p1

    move-object v8, v0

    goto :goto_0

    :catch_2
    move-exception v0

    move-object p1, v0

    move-object v8, p1

    move v1, v2

    goto :goto_0

    :catch_3
    move-exception v0

    move-object p1, v0

    move-object v8, p1

    move v1, v4

    :goto_0
    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzc:I

    new-instance v2, Lcom/google/android/recaptcha/internal/zzaez;

    int-to-long v3, v1

    int-to-long v5, p0

    const/16 v7, 0xa

    .line 12
    invoke-direct/range {v2 .. v8}, Lcom/google/android/recaptcha/internal/zzaez;-><init>(JJILjava/lang/Throwable;)V

    .line 13
    throw v2
.end method

.method public final zzn(I)V
    .locals 9

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    and-int/lit8 v1, p1, -0x80

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzb:[B

    if-nez v1, :cond_0

    add-int/lit8 v1, v0, 0x1

    int-to-byte p1, p1

    :try_start_0
    aput-byte p1, v2, v0

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :goto_0
    move-object v8, p1

    goto/16 :goto_1

    :cond_0
    add-int/lit8 v1, v0, 0x1

    or-int/lit16 v3, p1, 0x80

    int-to-byte v3, v3

    .line 2
    aput-byte v3, v2, v0
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_3

    ushr-int/lit8 v3, p1, 0x7

    and-int/lit8 v4, v3, -0x80

    if-nez v4, :cond_1

    add-int/lit8 p1, v0, 0x2

    int-to-byte v0, v3

    .line 9
    :try_start_1
    aput-byte v0, v2, v1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I
    :try_end_1
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_1 .. :try_end_1} :catch_0

    return-void

    :catch_0
    move-exception v0

    move v1, p1

    move-object v8, v0

    goto :goto_1

    :cond_1
    add-int/lit8 v4, v0, 0x2

    or-int/lit16 v3, v3, 0x80

    int-to-byte v3, v3

    .line 3
    :try_start_2
    aput-byte v3, v2, v1
    :try_end_2
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_2 .. :try_end_2} :catch_1

    ushr-int/lit8 v1, p1, 0xe

    and-int/lit8 v3, v1, -0x80

    if-nez v3, :cond_2

    add-int/lit8 p1, v0, 0x3

    int-to-byte v0, v1

    .line 8
    :try_start_3
    aput-byte v0, v2, v4

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I
    :try_end_3
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_3 .. :try_end_3} :catch_0

    return-void

    :cond_2
    add-int/lit8 v3, v0, 0x3

    or-int/lit16 v1, v1, 0x80

    int-to-byte v1, v1

    .line 4
    :try_start_4
    aput-byte v1, v2, v4
    :try_end_4
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_4 .. :try_end_4} :catch_2

    ushr-int/lit8 v1, p1, 0x15

    and-int/lit8 v4, v1, -0x80

    if-nez v4, :cond_3

    add-int/lit8 p1, v0, 0x4

    int-to-byte v0, v1

    .line 7
    :try_start_5
    aput-byte v0, v2, v3

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I
    :try_end_5
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_5 .. :try_end_5} :catch_0

    return-void

    :cond_3
    add-int/lit8 v4, v0, 0x4

    or-int/lit16 v1, v1, 0x80

    int-to-byte v1, v1

    .line 5
    :try_start_6
    aput-byte v1, v2, v3
    :try_end_6
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_6 .. :try_end_6} :catch_1

    ushr-int/lit8 p1, p1, 0x1c

    add-int/lit8 v1, v0, 0x5

    int-to-byte p1, p1

    .line 6
    :try_start_7
    aput-byte p1, v2, v4

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I
    :try_end_7
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_7 .. :try_end_7} :catch_3

    return-void

    :catch_1
    move-exception v0

    move-object p1, v0

    move-object v8, p1

    move v1, v4

    goto :goto_1

    :catch_2
    move-exception v0

    move-object p1, v0

    move-object v8, p1

    move v1, v3

    goto :goto_1

    :catch_3
    move-exception v0

    move-object p1, v0

    goto :goto_0

    :goto_1
    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzc:I

    new-instance v2, Lcom/google/android/recaptcha/internal/zzaez;

    int-to-long v3, v1

    int-to-long v5, p0

    const/4 v7, 0x1

    .line 10
    invoke-direct/range {v2 .. v8}, Lcom/google/android/recaptcha/internal/zzaez;-><init>(JJILjava/lang/Throwable;)V

    .line 11
    throw v2
.end method

.method public final zzo(I)V
    .locals 9

    .line 1
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    :try_start_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzb:[B

    int-to-byte v2, p1

    aput-byte v2, v0, v1

    add-int/lit8 v2, v1, 0x1

    shr-int/lit8 v3, p1, 0x8

    int-to-byte v3, v3

    .line 2
    aput-byte v3, v0, v2

    add-int/lit8 v2, v1, 0x2

    shr-int/lit8 v3, p1, 0x10

    int-to-byte v3, v3

    .line 3
    aput-byte v3, v0, v2

    add-int/lit8 v2, v1, 0x3

    shr-int/lit8 p1, p1, 0x18

    int-to-byte p1, p1

    .line 4
    aput-byte p1, v0, v2
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_0

    add-int/lit8 v1, v1, 0x4

    .line 6
    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :catch_0
    move-exception v0

    move-object p1, v0

    move-object v8, p1

    .line 4
    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzc:I

    int-to-long v3, v1

    new-instance v2, Lcom/google/android/recaptcha/internal/zzaez;

    int-to-long v5, p0

    const/4 v7, 0x4

    .line 5
    invoke-direct/range {v2 .. v8}, Lcom/google/android/recaptcha/internal/zzaez;-><init>(JJILjava/lang/Throwable;)V

    .line 6
    throw v2
.end method

.method public final zzp(J)V
    .locals 12

    const-wide/16 v0, -0x80

    and-long v2, p1, v0

    .line 1
    iget v4, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    const-wide/16 v5, 0x0

    cmp-long v2, v2, v5

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzb:[B

    if-nez v2, :cond_0

    long-to-int p1, p1

    int-to-byte p1, p1

    :try_start_0
    aput-byte p1, v3, v4

    add-int/lit8 p1, v4, 0x1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :catch_0
    move-exception v0

    move-object p1, v0

    move-object v11, p1

    goto/16 :goto_0

    :cond_0
    long-to-int v2, p1

    or-int/lit16 v2, v2, 0x80

    int-to-byte v2, v2

    .line 2
    aput-byte v2, v3, v4

    add-int/lit8 v2, v4, 0x1

    const/4 v7, 0x7

    ushr-long v7, p1, v7

    and-long v9, v7, v0

    cmp-long v9, v9, v5

    long-to-int v7, v7

    if-nez v9, :cond_1

    int-to-byte p1, v7

    .line 19
    aput-byte p1, v3, v2

    add-int/lit8 p1, v4, 0x2

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :cond_1
    or-int/lit16 v7, v7, 0x80

    int-to-byte v7, v7

    .line 3
    aput-byte v7, v3, v2

    add-int/lit8 v2, v4, 0x2

    const/16 v7, 0xe

    ushr-long v7, p1, v7

    and-long v9, v7, v0

    cmp-long v9, v9, v5

    long-to-int v7, v7

    if-nez v9, :cond_2

    int-to-byte p1, v7

    .line 18
    aput-byte p1, v3, v2

    add-int/lit8 p1, v4, 0x3

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :cond_2
    or-int/lit16 v7, v7, 0x80

    int-to-byte v7, v7

    .line 4
    aput-byte v7, v3, v2

    add-int/lit8 v2, v4, 0x3

    const/16 v7, 0x15

    ushr-long v7, p1, v7

    and-long v9, v7, v0

    cmp-long v9, v9, v5

    long-to-int v7, v7

    if-nez v9, :cond_3

    int-to-byte p1, v7

    .line 17
    aput-byte p1, v3, v2

    add-int/lit8 p1, v4, 0x4

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :cond_3
    or-int/lit16 v7, v7, 0x80

    int-to-byte v7, v7

    .line 5
    aput-byte v7, v3, v2

    add-int/lit8 v2, v4, 0x4

    const/16 v7, 0x1c

    ushr-long v7, p1, v7

    and-long v9, v7, v0

    cmp-long v9, v9, v5

    long-to-int v7, v7

    if-nez v9, :cond_4

    int-to-byte p1, v7

    .line 16
    aput-byte p1, v3, v2

    add-int/lit8 p1, v4, 0x5

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :cond_4
    or-int/lit16 v7, v7, 0x80

    int-to-byte v7, v7

    .line 6
    aput-byte v7, v3, v2

    add-int/lit8 v2, v4, 0x5

    const/16 v7, 0x23

    ushr-long v7, p1, v7

    and-long v9, v7, v0

    cmp-long v9, v9, v5

    long-to-int v7, v7

    if-nez v9, :cond_5

    int-to-byte p1, v7

    .line 15
    aput-byte p1, v3, v2

    add-int/lit8 p1, v4, 0x6

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :cond_5
    or-int/lit16 v7, v7, 0x80

    int-to-byte v7, v7

    .line 7
    aput-byte v7, v3, v2

    add-int/lit8 v2, v4, 0x6

    const/16 v7, 0x2a

    ushr-long v7, p1, v7

    and-long v9, v7, v0

    cmp-long v9, v9, v5

    long-to-int v7, v7

    if-nez v9, :cond_6

    int-to-byte p1, v7

    .line 14
    aput-byte p1, v3, v2

    add-int/lit8 p1, v4, 0x7

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :cond_6
    or-int/lit16 v7, v7, 0x80

    int-to-byte v7, v7

    .line 8
    aput-byte v7, v3, v2

    add-int/lit8 v2, v4, 0x7

    const/16 v7, 0x31

    ushr-long v7, p1, v7

    and-long v9, v7, v0

    cmp-long v9, v9, v5

    long-to-int v7, v7

    if-nez v9, :cond_7

    int-to-byte p1, v7

    .line 13
    aput-byte p1, v3, v2

    add-int/lit8 p1, v4, 0x8

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :cond_7
    or-int/lit16 v7, v7, 0x80

    int-to-byte v7, v7

    .line 9
    aput-byte v7, v3, v2

    add-int/lit8 v2, v4, 0x8

    const/16 v7, 0x38

    ushr-long v7, p1, v7

    and-long/2addr v0, v7

    cmp-long v0, v0, v5

    long-to-int v1, v7

    if-nez v0, :cond_8

    int-to-byte p1, v1

    .line 12
    aput-byte p1, v3, v2

    add-int/lit8 p1, v4, 0x9

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :cond_8
    or-int/lit16 v0, v1, 0x80

    int-to-byte v0, v0

    .line 10
    aput-byte v0, v3, v2

    add-int/lit8 v0, v4, 0x9

    const/16 v1, 0x3f

    ushr-long/2addr p1, v1

    long-to-int p1, p1

    int-to-byte p1, p1

    .line 11
    aput-byte p1, v3, v0

    add-int/lit8 p1, v4, 0xa

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :goto_0
    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzc:I

    int-to-long v6, v4

    new-instance v5, Lcom/google/android/recaptcha/internal/zzaez;

    int-to-long v8, p0

    const/4 v10, 0x1

    .line 20
    invoke-direct/range {v5 .. v11}, Lcom/google/android/recaptcha/internal/zzaez;-><init>(JJILjava/lang/Throwable;)V

    .line 21
    throw v5
.end method

.method public final zzq(J)V
    .locals 9

    .line 1
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    :try_start_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzb:[B

    long-to-int v2, p1

    int-to-byte v2, v2

    aput-byte v2, v0, v1

    add-int/lit8 v2, v1, 0x1

    const/16 v3, 0x8

    shr-long v4, p1, v3

    long-to-int v4, v4

    int-to-byte v4, v4

    .line 2
    aput-byte v4, v0, v2

    add-int/lit8 v2, v1, 0x2

    const/16 v4, 0x10

    shr-long v4, p1, v4

    long-to-int v4, v4

    int-to-byte v4, v4

    .line 3
    aput-byte v4, v0, v2

    add-int/lit8 v2, v1, 0x3

    const/16 v4, 0x18

    shr-long v4, p1, v4

    long-to-int v4, v4

    int-to-byte v4, v4

    .line 4
    aput-byte v4, v0, v2

    add-int/lit8 v2, v1, 0x4

    const/16 v4, 0x20

    shr-long v4, p1, v4

    long-to-int v4, v4

    int-to-byte v4, v4

    .line 5
    aput-byte v4, v0, v2

    add-int/lit8 v2, v1, 0x5

    const/16 v4, 0x28

    shr-long v4, p1, v4

    long-to-int v4, v4

    int-to-byte v4, v4

    .line 6
    aput-byte v4, v0, v2

    add-int/lit8 v2, v1, 0x6

    const/16 v4, 0x30

    shr-long v4, p1, v4

    long-to-int v4, v4

    int-to-byte v4, v4

    .line 7
    aput-byte v4, v0, v2

    add-int/lit8 v2, v1, 0x7

    const/16 v4, 0x38

    shr-long/2addr p1, v4

    long-to-int p1, p1

    int-to-byte p1, p1

    .line 8
    aput-byte p1, v0, v2
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_0

    add-int/2addr v1, v3

    .line 10
    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :catch_0
    move-exception v0

    move-object p1, v0

    move-object v8, p1

    .line 8
    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzc:I

    int-to-long v3, v1

    new-instance v2, Lcom/google/android/recaptcha/internal/zzaez;

    int-to-long v5, p0

    const/16 v7, 0x8

    .line 9
    invoke-direct/range {v2 .. v8}, Lcom/google/android/recaptcha/internal/zzaez;-><init>(JJILjava/lang/Throwable;)V

    .line 10
    throw v2
.end method

.method public final zzr([BII)V
    .locals 7

    .line 1
    :try_start_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzb:[B

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    invoke-static {p1, p2, v0, v1, p3}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_0

    .line 3
    iget p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    add-int/2addr p1, p3

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    :catch_0
    move-exception v0

    move-object p1, v0

    move-object v6, p1

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzaez;

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzc:I

    int-to-long v1, p1

    int-to-long v3, p0

    move v5, p3

    .line 2
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzaez;-><init>(JJILjava/lang/Throwable;)V

    .line 3
    throw v0
.end method

.method public final zzs(Ljava/lang/String;)V
    .locals 5

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    :try_start_0
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v1

    mul-int/lit8 v1, v1, 0x3

    .line 2
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v1

    .line 3
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v2

    if-ne v2, v1, :cond_0

    add-int v1, v0, v2

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzb:[B

    array-length v4, v3

    sub-int/2addr v4, v1

    .line 4
    invoke-static {p1, v3, v1, v4}, Lcom/google/android/recaptcha/internal/zzail;->zzb(Ljava/lang/String;[BII)I

    move-result p1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    sub-int v0, p1, v0

    sub-int/2addr v0, v2

    .line 5
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    return-void

    .line 6
    :cond_0
    sget v0, Lcom/google/android/recaptcha/internal/zzail;->$r8$clinit:I

    .line 7
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaij;->zzb(Ljava/lang/String;)I

    move-result v0

    .line 8
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzaex;->zzn(I)V

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzb:[B

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    array-length v2, v0

    sub-int/2addr v2, v1

    .line 9
    invoke-static {p1, v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzail;->zzb(Ljava/lang/String;[BII)I

    move-result p1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 8
    new-instance p1, Lcom/google/android/recaptcha/internal/zzaez;

    .line 10
    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzaez;-><init>(Ljava/lang/Throwable;)V

    throw p1
.end method

.method public final zzt()I
    .locals 1

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzc:I

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaex;->zzd:I

    sub-int/2addr v0, p0

    return v0
.end method
