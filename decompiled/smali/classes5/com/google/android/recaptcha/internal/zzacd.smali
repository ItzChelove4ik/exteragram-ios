.class public final Lcom/google/android/recaptcha/internal/zzacd;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzss;


# static fields
.field private static final zzb:[B

.field private static final zzc:[B

.field private static final zzd:[B


# instance fields
.field zza:Ljava/security/Provider;

.field private final zze:Ljava/security/interfaces/RSAPrivateCrtKey;

.field private final zzf:Ljava/lang/String;

.field private final zzg:[B

.field private final zzh:[B

.field private final zzi:Lcom/google/android/recaptcha/internal/zzst;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const/4 v0, 0x0

    new-array v1, v0, [B

    sput-object v1, Lcom/google/android/recaptcha/internal/zzacd;->zzb:[B

    const/4 v1, 0x1

    new-array v1, v1, [B

    aput-byte v0, v1, v0

    sput-object v1, Lcom/google/android/recaptcha/internal/zzacd;->zzc:[B

    const/4 v0, 0x3

    new-array v0, v0, [B

    fill-array-data v0, :array_0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzacd;->zzd:[B

    return-void

    nop

    :array_0
    .array-data 1
        0x1t
        0x2t
        0x3t
    .end array-data
.end method

.method private constructor <init>(Ljava/security/interfaces/RSAPrivateCrtKey;Lcom/google/android/recaptcha/internal/zzzt;[B[BLcom/google/android/recaptcha/internal/zzst;Ljava/security/Provider;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x2

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result v0

    if-eqz v0, :cond_2

    sget-object v0, Lcom/google/android/recaptcha/internal/zzzt;->zza:Lcom/google/android/recaptcha/internal/zzzt;

    if-eq p2, v0, :cond_1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzzt;->zzb:Lcom/google/android/recaptcha/internal/zzzt;

    if-eq p2, v0, :cond_1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzzt;->zzc:Lcom/google/android/recaptcha/internal/zzzt;

    if-ne p2, v0, :cond_0

    goto :goto_0

    .line 5
    :cond_0
    new-instance p0, Ljava/security/GeneralSecurityException;

    invoke-static {p2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    const-string p2, "Unsupported hash: "

    invoke-virtual {p2, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 2
    :cond_1
    :goto_0
    invoke-interface {p1}, Ljava/security/interfaces/RSAKey;->getModulus()Ljava/math/BigInteger;

    move-result-object v0

    invoke-virtual {v0}, Ljava/math/BigInteger;->bitLength()I

    move-result v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzadx;->zzb(I)V

    .line 3
    invoke-interface {p1}, Ljava/security/interfaces/RSAPrivateCrtKey;->getPublicExponent()Ljava/math/BigInteger;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzadx;->zzc(Ljava/math/BigInteger;)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzacd;->zze:Ljava/security/interfaces/RSAPrivateCrtKey;

    .line 4
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzace;->zzc(Lcom/google/android/recaptcha/internal/zzzt;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzacd;->zzf:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzacd;->zzg:[B

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzacd;->zzh:[B

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzacd;->zzi:Lcom/google/android/recaptcha/internal/zzst;

    iput-object p6, p0, Lcom/google/android/recaptcha/internal/zzacd;->zza:Ljava/security/Provider;

    return-void

    .line 6
    :cond_2
    const-string p0, "Can not use RSA PKCS1.5 in FIPS-mode, as BoringCrypto module is not available."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzzx;)Lcom/google/android/recaptcha/internal/zzss;
    .locals 16

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzace;->zzb()Ljava/security/Provider;

    move-result-object v6

    const-string v0, "RSA"

    if-eqz v6, :cond_0

    .line 2
    invoke-static {v0, v6}, Ljava/security/KeyFactory;->getInstance(Ljava/lang/String;Ljava/security/Provider;)Ljava/security/KeyFactory;

    move-result-object v0

    goto :goto_0

    .line 3
    :cond_0
    sget-object v1, Lcom/google/android/recaptcha/internal/zzadf;->zzc:Lcom/google/android/recaptcha/internal/zzadf;

    invoke-virtual {v1, v0}, Lcom/google/android/recaptcha/internal/zzadf;->zzb(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/security/KeyFactory;

    .line 4
    :goto_0
    new-instance v7, Ljava/security/spec/RSAPrivateCrtKeySpec;

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zze()Lcom/google/android/recaptcha/internal/zzzz;

    move-result-object v1

    .line 5
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzzz;->zzd()Ljava/math/BigInteger;

    move-result-object v8

    .line 6
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zzd()Lcom/google/android/recaptcha/internal/zzzv;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzzv;->zzc()Ljava/math/BigInteger;

    move-result-object v9

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zzi()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v1

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v2

    .line 7
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v10

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zzf()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v1

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v2

    .line 8
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v11

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zzh()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v1

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v2

    .line 9
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v12

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zzj()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v1

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v2

    .line 10
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v13

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zzk()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v1

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v2

    .line 11
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v14

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zzl()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v1

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v2

    .line 12
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v15

    invoke-direct/range {v7 .. v15}, Ljava/security/spec/RSAPrivateCrtKeySpec;-><init>(Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;)V

    .line 13
    invoke-virtual {v0, v7}, Ljava/security/KeyFactory;->generatePrivate(Ljava/security/spec/KeySpec;)Ljava/security/PrivateKey;

    move-result-object v0

    move-object v1, v0

    check-cast v1, Ljava/security/interfaces/RSAPrivateCrtKey;

    if-eqz v6, :cond_1

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zze()Lcom/google/android/recaptcha/internal/zzzz;

    move-result-object v0

    .line 14
    invoke-static {v0, v6}, Lcom/google/android/recaptcha/internal/zzace;->zze(Lcom/google/android/recaptcha/internal/zzzz;Ljava/security/Provider;)Lcom/google/android/recaptcha/internal/zzst;

    move-result-object v0

    :goto_1
    move-object v5, v0

    goto :goto_2

    .line 21
    :cond_1
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zze()Lcom/google/android/recaptcha/internal/zzzz;

    move-result-object v0

    .line 15
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzadr;->zzb(Lcom/google/android/recaptcha/internal/zzzz;)Lcom/google/android/recaptcha/internal/zzst;

    move-result-object v0

    goto :goto_1

    .line 14
    :goto_2
    new-instance v0, Lcom/google/android/recaptcha/internal/zzacd;

    .line 16
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zzd()Lcom/google/android/recaptcha/internal/zzzv;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzzv;->zze()Lcom/google/android/recaptcha/internal/zzzt;

    move-result-object v2

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zze()Lcom/google/android/recaptcha/internal/zzzz;

    move-result-object v3

    .line 17
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzaax;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v3

    .line 18
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v3

    .line 19
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzzx;->zzd()Lcom/google/android/recaptcha/internal/zzzv;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzzv;->zzd()Lcom/google/android/recaptcha/internal/zzzu;

    move-result-object v4

    sget-object v7, Lcom/google/android/recaptcha/internal/zzzu;->zzc:Lcom/google/android/recaptcha/internal/zzzu;

    invoke-virtual {v4, v7}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_2

    sget-object v4, Lcom/google/android/recaptcha/internal/zzacd;->zzc:[B

    goto :goto_3

    .line 21
    :cond_2
    sget-object v4, Lcom/google/android/recaptcha/internal/zzacd;->zzb:[B

    .line 20
    :goto_3
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzacd;-><init>(Ljava/security/interfaces/RSAPrivateCrtKey;Lcom/google/android/recaptcha/internal/zzzt;[B[BLcom/google/android/recaptcha/internal/zzst;Ljava/security/Provider;)V

    sget-object v1, Lcom/google/android/recaptcha/internal/zzacd;->zzd:[B

    .line 21
    invoke-interface {v0, v1}, Lcom/google/android/recaptcha/internal/zzss;->zza([B)[B

    return-object v0
.end method


# virtual methods
.method public final zza([B)[B
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzacd;->zza:Ljava/security/Provider;

    .line 9
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzacd;->zzf:Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 1
    invoke-static {v1, v0}, Ljava/security/Signature;->getInstance(Ljava/lang/String;Ljava/security/Provider;)Ljava/security/Signature;

    move-result-object v0

    goto :goto_0

    .line 2
    :cond_0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzadf;->zza:Lcom/google/android/recaptcha/internal/zzadf;

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzadf;->zzb(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/security/Signature;

    .line 1
    :goto_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzacd;->zze:Ljava/security/interfaces/RSAPrivateCrtKey;

    .line 3
    invoke-virtual {v0, v1}, Ljava/security/Signature;->initSign(Ljava/security/PrivateKey;)V

    .line 4
    invoke-virtual {v0, p1}, Ljava/security/Signature;->update([B)V

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzacd;->zzh:[B

    array-length v2, v1

    if-lez v2, :cond_1

    .line 5
    invoke-virtual {v0, v1}, Ljava/security/Signature;->update([B)V

    .line 6
    :cond_1
    invoke-virtual {v0}, Ljava/security/Signature;->sign()[B

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzacd;->zzg:[B

    array-length v2, v1

    if-lez v2, :cond_2

    filled-new-array {v1, v0}, [[B

    move-result-object v0

    .line 7
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzacu;->zza([[B)[B

    move-result-object v0

    :cond_2
    :try_start_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzacd;->zzi:Lcom/google/android/recaptcha/internal/zzst;

    .line 8
    invoke-interface {p0, v0, p1}, Lcom/google/android/recaptcha/internal/zzst;->zza([B[B)V
    :try_end_0
    .catch Ljava/security/GeneralSecurityException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception p0

    const-string p1, "RSA signature computation error"

    .line 9
    invoke-static {p1, p0}, Lorg/mvel2/asm/Constants$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    return-object p0
.end method
