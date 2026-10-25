.class final Lcom/google/android/recaptcha/internal/zzadq;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzst;


# instance fields
.field private final zza:Ljava/security/interfaces/RSAPublicKey;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzado;

.field private final zzc:[B

.field private final zzd:[B


# direct methods
.method public synthetic constructor <init>(Ljava/security/interfaces/RSAPublicKey;Lcom/google/android/recaptcha/internal/zzado;[B[B[B)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zztf;->zza()Z

    move-result p5

    if-nez p5, :cond_0

    .line 3
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzadx;->zza(Lcom/google/android/recaptcha/internal/zzado;)V

    .line 4
    invoke-interface {p1}, Ljava/security/interfaces/RSAKey;->getModulus()Ljava/math/BigInteger;

    move-result-object p5

    invoke-virtual {p5}, Ljava/math/BigInteger;->bitLength()I

    move-result p5

    invoke-static {p5}, Lcom/google/android/recaptcha/internal/zzadx;->zzb(I)V

    .line 5
    invoke-interface {p1}, Ljava/security/interfaces/RSAPublicKey;->getPublicExponent()Ljava/math/BigInteger;

    move-result-object p5

    invoke-static {p5}, Lcom/google/android/recaptcha/internal/zzadx;->zzc(Ljava/math/BigInteger;)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzadq;->zza:Ljava/security/interfaces/RSAPublicKey;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzadq;->zzb:Lcom/google/android/recaptcha/internal/zzado;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzadq;->zzc:[B

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzadq;->zzd:[B

    return-void

    .line 2
    :cond_0
    const-string p0, "Conscrypt is not available, and we cannot use Java Implementation of RSA-PKCS1.5 in FIPS-mode."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method private final zzb([B[B)V
    .locals 8

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzadq;->zza:Ljava/security/interfaces/RSAPublicKey;

    invoke-interface {v0}, Ljava/security/interfaces/RSAPublicKey;->getPublicExponent()Ljava/math/BigInteger;

    move-result-object v1

    .line 2
    invoke-interface {v0}, Ljava/security/interfaces/RSAKey;->getModulus()Ljava/math/BigInteger;

    move-result-object v0

    .line 3
    invoke-virtual {v0}, Ljava/math/BigInteger;->bitLength()I

    move-result v2

    add-int/lit8 v2, v2, 0x7

    div-int/lit8 v2, v2, 0x8

    .line 4
    array-length v3, p1

    if-ne v2, v3, :cond_8

    .line 5
    new-instance v3, Ljava/math/BigInteger;

    const/4 v4, 0x1

    .line 6
    invoke-direct {v3, v4, p1}, Ljava/math/BigInteger;-><init>(I[B)V

    .line 7
    invoke-virtual {v3, v0}, Ljava/math/BigInteger;->compareTo(Ljava/math/BigInteger;)I

    move-result p1

    if-gez p1, :cond_7

    .line 9
    invoke-virtual {v3, v1, v0}, Ljava/math/BigInteger;->modPow(Ljava/math/BigInteger;Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object p1

    .line 10
    invoke-static {p1, v2}, Lcom/google/android/recaptcha/internal/zzth;->zzb(Ljava/math/BigInteger;I)[B

    move-result-object p1

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzadq;->zzb:Lcom/google/android/recaptcha/internal/zzado;

    .line 11
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzadx;->zza(Lcom/google/android/recaptcha/internal/zzado;)V

    .line 12
    sget-object v1, Lcom/google/android/recaptcha/internal/zzadf;->zzb:Lcom/google/android/recaptcha/internal/zzadf;

    .line 13
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzadw;->zzb(Lcom/google/android/recaptcha/internal/zzado;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Lcom/google/android/recaptcha/internal/zzadf;->zzb(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/security/MessageDigest;

    .line 14
    invoke-virtual {v1, p2}, Ljava/security/MessageDigest;->update([B)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzadq;->zzd:[B

    array-length p2, p0

    if-eqz p2, :cond_0

    .line 15
    invoke-virtual {v1, p0}, Ljava/security/MessageDigest;->update([B)V

    .line 16
    :cond_0
    invoke-virtual {v1}, Ljava/security/MessageDigest;->digest()[B

    move-result-object p0

    .line 17
    invoke-virtual {v0}, Ljava/lang/Enum;->ordinal()I

    move-result p2

    const/4 v1, 0x2

    if-eq p2, v1, :cond_3

    const/4 v3, 0x3

    if-eq p2, v3, :cond_2

    const/4 v3, 0x4

    if-ne p2, v3, :cond_1

    const-string p2, "3051300d060960864801650304020305000440"

    .line 18
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzadp;->zza(Ljava/lang/String;)[B

    move-result-object p2

    goto :goto_0

    .line 31
    :cond_1
    new-instance p0, Ljava/security/GeneralSecurityException;

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "Unsupported hash "

    invoke-virtual {p2, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 30
    :cond_2
    const-string p2, "3041300d060960864801650304020205000430"

    .line 19
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzadp;->zza(Ljava/lang/String;)[B

    move-result-object p2

    goto :goto_0

    .line 31
    :cond_3
    const-string p2, "3031300d060960864801650304020105000420"

    .line 20
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzadp;->zza(Ljava/lang/String;)[B

    move-result-object p2

    .line 21
    :goto_0
    array-length v0, p0

    add-int/lit8 v3, v0, 0x13

    add-int/lit8 v0, v0, 0x1e

    if-lt v2, v0, :cond_6

    .line 22
    new-array v0, v2, [B

    const/4 v5, 0x0

    .line 23
    aput-byte v5, v0, v5

    .line 24
    aput-byte v4, v0, v4

    move v4, v5

    :goto_1
    add-int/lit8 v6, v1, 0x1

    sub-int v7, v2, v3

    add-int/lit8 v7, v7, -0x3

    if-ge v4, v7, :cond_4

    const/4 v7, -0x1

    .line 25
    aput-byte v7, v0, v1

    add-int/lit8 v4, v4, 0x1

    move v1, v6

    goto :goto_1

    .line 26
    :cond_4
    aput-byte v5, v0, v1

    const/16 v2, 0x13

    .line 27
    invoke-static {p2, v5, v0, v6, v2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    add-int/lit8 v1, v1, 0x14

    .line 28
    array-length p2, p0

    invoke-static {p0, v5, v0, v1, p2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 29
    invoke-static {p1, v0}, Ljava/security/MessageDigest;->isEqual([B[B)Z

    move-result p0

    if-eqz p0, :cond_5

    return-void

    .line 30
    :cond_5
    const-string p0, "invalid signature"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 22
    :cond_6
    const-string p0, "intended encoded message length too short"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 8
    :cond_7
    const-string p0, "signature out of range"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 5
    :cond_8
    const-string p0, "invalid signature\'s length"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public final zza([B[B)V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzadq;->zzc:[B

    array-length v1, v0

    if-nez v1, :cond_0

    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzadq;->zzb([B[B)V

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
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzadq;->zzb([B[B)V

    return-void

    .line 3
    :cond_1
    const-string p0, "Invalid signature (output prefix mismatch)"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
