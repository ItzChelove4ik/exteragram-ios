.class final Lcom/google/android/recaptcha/internal/zzadu;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzst;


# instance fields
.field private final zza:Ljava/security/interfaces/RSAPublicKey;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzado;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzado;

.field private final zzd:I

.field private final zze:[B

.field private final zzf:[B


# direct methods
.method public synthetic constructor <init>(Ljava/security/interfaces/RSAPublicKey;Lcom/google/android/recaptcha/internal/zzado;Lcom/google/android/recaptcha/internal/zzado;I[B[B[B)V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zztf;->zza()Z

    move-result p7

    const/4 v0, 0x0

    if-nez p7, :cond_1

    .line 3
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzadx;->zza(Lcom/google/android/recaptcha/internal/zzado;)V

    .line 4
    invoke-virtual {p2, p3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p7

    if-eqz p7, :cond_0

    .line 6
    invoke-interface {p1}, Ljava/security/interfaces/RSAKey;->getModulus()Ljava/math/BigInteger;

    move-result-object p7

    invoke-virtual {p7}, Ljava/math/BigInteger;->bitLength()I

    move-result p7

    invoke-static {p7}, Lcom/google/android/recaptcha/internal/zzadx;->zzb(I)V

    .line 7
    invoke-interface {p1}, Ljava/security/interfaces/RSAPublicKey;->getPublicExponent()Ljava/math/BigInteger;

    move-result-object p7

    invoke-static {p7}, Lcom/google/android/recaptcha/internal/zzadx;->zzc(Ljava/math/BigInteger;)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzadu;->zza:Ljava/security/interfaces/RSAPublicKey;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzadu;->zzb:Lcom/google/android/recaptcha/internal/zzado;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzadu;->zzc:Lcom/google/android/recaptcha/internal/zzado;

    iput p4, p0, Lcom/google/android/recaptcha/internal/zzadu;->zzd:I

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzadu;->zze:[B

    iput-object p6, p0, Lcom/google/android/recaptcha/internal/zzadu;->zzf:[B

    return-void

    .line 5
    :cond_0
    const-string p0, "sigHash and mgf1Hash must be the same"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v0

    .line 2
    :cond_1
    const-string p0, "Can not use RSA PSS in FIPS-mode, as BoringCrypto module is not available."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v0
.end method

.method private final zzb([B[B)V
    .locals 22

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    .line 1
    iget-object v2, v0, Lcom/google/android/recaptcha/internal/zzadu;->zza:Ljava/security/interfaces/RSAPublicKey;

    invoke-interface {v2}, Ljava/security/interfaces/RSAPublicKey;->getPublicExponent()Ljava/math/BigInteger;

    move-result-object v3

    .line 2
    invoke-interface {v2}, Ljava/security/interfaces/RSAKey;->getModulus()Ljava/math/BigInteger;

    move-result-object v2

    .line 3
    invoke-virtual {v2}, Ljava/math/BigInteger;->bitLength()I

    move-result v4

    add-int/lit8 v4, v4, 0x7

    .line 4
    invoke-virtual {v2}, Ljava/math/BigInteger;->bitLength()I

    move-result v5

    add-int/lit8 v5, v5, 0x6

    const/16 v6, 0x8

    div-int/2addr v4, v6

    .line 5
    array-length v7, v1

    if-ne v4, v7, :cond_d

    new-instance v4, Ljava/math/BigInteger;

    const/4 v7, 0x1

    .line 6
    invoke-direct {v4, v7, v1}, Ljava/math/BigInteger;-><init>(I[B)V

    .line 7
    invoke-virtual {v4, v2}, Ljava/math/BigInteger;->compareTo(Ljava/math/BigInteger;)I

    move-result v1

    if-gez v1, :cond_c

    .line 8
    div-int/2addr v5, v6

    .line 9
    invoke-virtual {v4, v3, v2}, Ljava/math/BigInteger;->modPow(Ljava/math/BigInteger;Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v1

    .line 10
    invoke-static {v1, v5}, Lcom/google/android/recaptcha/internal/zzth;->zzb(Ljava/math/BigInteger;I)[B

    move-result-object v1

    .line 11
    invoke-virtual {v2}, Ljava/math/BigInteger;->bitLength()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    iget-object v3, v0, Lcom/google/android/recaptcha/internal/zzadu;->zzb:Lcom/google/android/recaptcha/internal/zzado;

    .line 12
    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzadx;->zza(Lcom/google/android/recaptcha/internal/zzado;)V

    .line 13
    sget-object v4, Lcom/google/android/recaptcha/internal/zzadf;->zzb:Lcom/google/android/recaptcha/internal/zzadf;

    .line 14
    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzadw;->zzb(Lcom/google/android/recaptcha/internal/zzado;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v4, v3}, Lcom/google/android/recaptcha/internal/zzadf;->zzb(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/security/MessageDigest;

    move-object/from16 v5, p2

    .line 15
    invoke-virtual {v3, v5}, Ljava/security/MessageDigest;->update([B)V

    iget-object v5, v0, Lcom/google/android/recaptcha/internal/zzadu;->zzf:[B

    .line 16
    array-length v8, v5

    if-eqz v8, :cond_0

    .line 17
    invoke-virtual {v3, v5}, Ljava/security/MessageDigest;->update([B)V

    .line 18
    :cond_0
    invoke-virtual {v3}, Ljava/security/MessageDigest;->digest()[B

    move-result-object v5

    .line 19
    invoke-virtual {v3}, Ljava/security/MessageDigest;->getDigestLength()I

    move-result v8

    .line 20
    array-length v9, v1

    iget v10, v0, Lcom/google/android/recaptcha/internal/zzadu;->zzd:I

    add-int v11, v8, v10

    add-int/lit8 v11, v11, 0x2

    const-string v12, "inconsistent"

    if-lt v9, v11, :cond_b

    add-int/lit8 v11, v9, -0x1

    .line 22
    aget-byte v11, v1, v11

    const/16 v13, -0x44

    if-ne v11, v13, :cond_a

    sub-int v11, v9, v8

    add-int/lit8 v13, v11, -0x1

    .line 24
    invoke-static {v1, v13}, Ljava/util/Arrays;->copyOf([BI)[B

    move-result-object v14

    .line 25
    array-length v15, v14

    move/from16 v16, v6

    add-int v6, v15, v8

    invoke-static {v1, v15, v6}, Ljava/util/Arrays;->copyOfRange([BII)[B

    move-result-object v1

    move/from16 v17, v7

    move/from16 p1, v8

    const/4 v15, 0x0

    :goto_0
    int-to-long v7, v9

    const-wide/16 v18, 0x8

    mul-long v7, v7, v18

    move-wide/from16 v18, v7

    int-to-long v6, v2

    move-wide/from16 v20, v6

    int-to-long v6, v15

    sub-long v18, v18, v20

    cmp-long v6, v6, v18

    if-gez v6, :cond_2

    div-int/lit8 v6, v15, 0x8

    rem-int/lit8 v7, v15, 0x8

    rsub-int/lit8 v7, v7, 0x7

    .line 26
    aget-byte v6, v14, v6

    shr-int/2addr v6, v7

    and-int/lit8 v6, v6, 0x1

    if-nez v6, :cond_1

    add-int/lit8 v15, v15, 0x1

    goto :goto_0

    .line 27
    :cond_1
    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    :cond_2
    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzadu;->zzc:Lcom/google/android/recaptcha/internal/zzado;

    .line 28
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzadw;->zzb(Lcom/google/android/recaptcha/internal/zzado;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v4, v0}, Lcom/google/android/recaptcha/internal/zzadf;->zzb(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/security/MessageDigest;

    .line 29
    invoke-virtual {v0}, Ljava/security/MessageDigest;->getDigestLength()I

    move-result v2

    .line 30
    new-array v4, v13, [B

    const/4 v6, 0x0

    const/4 v7, 0x0

    :goto_1
    add-int/lit8 v8, v11, -0x2

    .line 31
    div-int/2addr v8, v2

    if-gt v6, v8, :cond_3

    .line 32
    invoke-virtual {v0}, Ljava/security/MessageDigest;->reset()V

    .line 33
    invoke-virtual {v0, v1}, Ljava/security/MessageDigest;->update([B)V

    int-to-long v8, v6

    .line 34
    invoke-static {v8, v9}, Ljava/math/BigInteger;->valueOf(J)Ljava/math/BigInteger;

    move-result-object v8

    const/4 v9, 0x4

    .line 35
    invoke-static {v8, v9}, Lcom/google/android/recaptcha/internal/zzth;->zzb(Ljava/math/BigInteger;I)[B

    move-result-object v8

    .line 34
    invoke-virtual {v0, v8}, Ljava/security/MessageDigest;->update([B)V

    .line 36
    invoke-virtual {v0}, Ljava/security/MessageDigest;->digest()[B

    move-result-object v8

    .line 37
    array-length v9, v8

    sub-int v15, v13, v7

    invoke-static {v9, v15}, Ljava/lang/Math;->min(II)I

    move-result v15

    move-object/from16 p0, v0

    const/4 v0, 0x0

    invoke-static {v8, v0, v4, v7, v15}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    add-int/2addr v7, v9

    add-int/lit8 v6, v6, 0x1

    move-object/from16 v0, p0

    goto :goto_1

    :cond_3
    new-array v0, v13, [B

    const/4 v2, 0x0

    :goto_2
    if-ge v2, v13, :cond_4

    .line 38
    aget-byte v6, v4, v2

    aget-byte v7, v14, v2

    xor-int/2addr v6, v7

    int-to-byte v6, v6

    aput-byte v6, v0, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_4
    const/4 v2, 0x0

    :goto_3
    int-to-long v6, v2

    cmp-long v4, v6, v18

    if-gtz v4, :cond_5

    div-int/lit8 v4, v2, 0x8

    rem-int/lit8 v6, v2, 0x8

    rsub-int/lit8 v6, v6, 0x7

    .line 39
    aget-byte v7, v0, v4

    shl-int v6, v17, v6

    not-int v6, v6

    and-int/2addr v6, v7

    int-to-byte v6, v6

    aput-byte v6, v0, v4

    add-int/lit8 v2, v2, 0x1

    goto :goto_3

    :cond_5
    const/4 v2, 0x0

    :goto_4
    sub-int v4, v11, v10

    add-int/lit8 v4, v4, -0x2

    if-ge v2, v4, :cond_7

    .line 40
    aget-byte v4, v0, v2

    if-nez v4, :cond_6

    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    .line 41
    :cond_6
    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 42
    :cond_7
    aget-byte v2, v0, v4

    move/from16 v4, v17

    if-ne v2, v4, :cond_9

    sub-int v2, v13, v10

    .line 44
    invoke-static {v0, v2, v13}, Ljava/util/Arrays;->copyOfRange([BII)[B

    move-result-object v0

    add-int/lit8 v8, p1, 0x8

    add-int/2addr v10, v8

    .line 45
    new-array v2, v10, [B

    .line 46
    array-length v4, v5

    move/from16 v7, v16

    const/4 v6, 0x0

    invoke-static {v5, v6, v2, v7, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 47
    array-length v4, v0

    invoke-static {v0, v6, v2, v8, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 48
    invoke-virtual {v3, v2}, Ljava/security/MessageDigest;->digest([B)[B

    move-result-object v0

    .line 49
    invoke-static {v0, v1}, Ljava/security/MessageDigest;->isEqual([B[B)Z

    move-result v0

    if-eqz v0, :cond_8

    return-void

    .line 50
    :cond_8
    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 43
    :cond_9
    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 23
    :cond_a
    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 21
    :cond_b
    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 8
    :cond_c
    const-string v0, "signature out of range"

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 51
    :cond_d
    const-string v0, "invalid signature\'s length"

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public final zza([B[B)V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzadu;->zze:[B

    array-length v1, v0

    if-nez v1, :cond_0

    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzadu;->zzb([B[B)V

    return-void

    .line 2
    :cond_0
    invoke-static {v0, p1}, Lcom/google/android/recaptcha/internal/zzwc;->zzc([B[B)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 4
    array-length v0, p1

    .line 5
    invoke-static {p1, v1, v0}, Ljava/util/Arrays;->copyOfRange([BII)[B

    move-result-object p1

    .line 6
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzadu;->zzb([B[B)V

    return-void

    .line 3
    :cond_1
    const-string p0, "Invalid signature (output prefix mismatch)"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
