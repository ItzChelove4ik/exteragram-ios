.class public abstract Lcom/google/android/recaptcha/internal/zzada;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static zza([B)[B
    .locals 9

    .line 1
    array-length v0, p0

    and-int/lit8 v1, v0, 0x1

    if-nez v1, :cond_1

    if-eqz v0, :cond_1

    const/16 v1, 0x84

    if-gt v0, v1, :cond_1

    shr-int/lit8 v1, v0, 0x1

    .line 2
    invoke-static {p0, v1}, Ljava/util/Arrays;->copyOf([BI)[B

    move-result-object v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzada;->zzc([B)[B

    move-result-object v2

    .line 3
    invoke-static {p0, v1, v0}, Ljava/util/Arrays;->copyOfRange([BII)[B

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzada;->zzc([B)[B

    move-result-object p0

    array-length v0, v2

    add-int/lit8 v1, v0, 0x4

    array-length v3, p0

    add-int/2addr v1, v3

    const/16 v4, 0x80

    const/16 v5, 0x30

    const/4 v6, 0x0

    const/4 v7, 0x2

    const/4 v8, 0x1

    if-lt v1, v4, :cond_0

    add-int/lit8 v4, v1, 0x3

    .line 4
    new-array v4, v4, [B

    .line 5
    aput-byte v5, v4, v6

    const/16 v5, -0x7f

    .line 6
    aput-byte v5, v4, v8

    int-to-byte v1, v1

    .line 7
    aput-byte v1, v4, v7

    const/4 v1, 0x3

    goto :goto_0

    :cond_0
    add-int/lit8 v4, v1, 0x2

    .line 8
    new-array v4, v4, [B

    .line 9
    aput-byte v5, v4, v6

    int-to-byte v1, v1

    .line 10
    aput-byte v1, v4, v8

    move v1, v7

    :goto_0
    add-int/lit8 v5, v1, 0x1

    .line 11
    aput-byte v7, v4, v1

    add-int/2addr v1, v7

    int-to-byte v8, v0

    .line 12
    aput-byte v8, v4, v5

    .line 13
    invoke-static {v2, v6, v4, v1, v0}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    add-int/2addr v1, v0

    add-int/lit8 v0, v1, 0x1

    .line 14
    aput-byte v7, v4, v1

    add-int/2addr v1, v7

    int-to-byte v2, v3

    .line 15
    aput-byte v2, v4, v0

    .line 16
    invoke-static {p0, v6, v4, v1, v3}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    return-object v4

    .line 17
    :cond_1
    const-string p0, "Invalid IEEE_P1363 encoding"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzacy;)Ljava/security/spec/ECParameterSpec;
    .locals 2

    .line 1
    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result v0

    if-eqz v0, :cond_2

    const/4 v1, 0x1

    if-eq v0, v1, :cond_1

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    .line 4
    sget-object p0, Lcom/google/android/recaptcha/internal/zztt;->zzc:Ljava/security/spec/ECParameterSpec;

    return-object p0

    .line 5
    :cond_0
    new-instance v0, Ljava/security/NoSuchAlgorithmException;

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v1, "curve not implemented:"

    invoke-virtual {v1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/security/NoSuchAlgorithmException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 3
    :cond_1
    sget-object p0, Lcom/google/android/recaptcha/internal/zztt;->zzb:Ljava/security/spec/ECParameterSpec;

    return-object p0

    .line 2
    :cond_2
    sget-object p0, Lcom/google/android/recaptcha/internal/zztt;->zza:Ljava/security/spec/ECParameterSpec;

    return-object p0
.end method

.method private static zzc([B)[B
    .locals 5

    const/4 v0, 0x0

    move v1, v0

    .line 1
    :goto_0
    array-length v2, p0

    if-ge v1, v2, :cond_0

    aget-byte v3, p0, v1

    if-nez v3, :cond_0

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    if-ne v1, v2, :cond_1

    add-int/lit8 v1, v2, -0x1

    .line 2
    :cond_1
    aget-byte v3, p0, v1

    const/16 v4, 0x80

    and-int/2addr v3, v4

    if-ne v3, v4, :cond_2

    const/4 v0, 0x1

    :cond_2
    sub-int/2addr v2, v1

    add-int v3, v2, v0

    .line 3
    new-array v3, v3, [B

    .line 4
    invoke-static {p0, v1, v3, v0, v2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    return-object v3
.end method
