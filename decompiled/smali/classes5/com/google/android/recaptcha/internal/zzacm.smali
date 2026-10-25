.class public final Lcom/google/android/recaptcha/internal/zzacm;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzss;


# static fields
.field private static final zza:[B

.field private static final zzb:[B


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const/4 v0, 0x0

    new-array v1, v0, [B

    sput-object v1, Lcom/google/android/recaptcha/internal/zzacm;->zza:[B

    const/4 v1, 0x1

    new-array v1, v1, [B

    aput-byte v0, v1, v0

    sput-object v1, Lcom/google/android/recaptcha/internal/zzacm;->zzb:[B

    return-void
.end method

.method private constructor <init>(Ljava/security/interfaces/RSAPrivateCrtKey;Lcom/google/android/recaptcha/internal/zzaaf;Lcom/google/android/recaptcha/internal/zzaaf;I[B[BLjava/security/Provider;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p0, 0x2

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 3
    invoke-interface {p1}, Ljava/security/interfaces/RSAKey;->getModulus()Ljava/math/BigInteger;

    move-result-object p0

    invoke-virtual {p0}, Ljava/math/BigInteger;->bitLength()I

    move-result p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzadx;->zzb(I)V

    .line 4
    invoke-interface {p1}, Ljava/security/interfaces/RSAPrivateCrtKey;->getPublicExponent()Ljava/math/BigInteger;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzadx;->zzc(Ljava/math/BigInteger;)V

    .line 5
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzacn;->zzc(Lcom/google/android/recaptcha/internal/zzaaf;)Ljava/lang/String;

    .line 6
    invoke-static {p2, p3, p4}, Lcom/google/android/recaptcha/internal/zzacn;->zzd(Lcom/google/android/recaptcha/internal/zzaaf;Lcom/google/android/recaptcha/internal/zzaaf;I)Ljava/security/spec/PSSParameterSpec;

    return-void

    .line 2
    :cond_0
    const-string p0, "Cannot use RSA PSS in FIPS-mode, as BoringCrypto module is not available."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzaaj;)Lcom/google/android/recaptcha/internal/zzss;
    .locals 18

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzacn;->zzb()Ljava/security/Provider;

    move-result-object v7

    if-eqz v7, :cond_1

    .line 2
    const-string v0, "RSA"

    .line 3
    invoke-static {v0, v7}, Ljava/security/KeyFactory;->getInstance(Ljava/lang/String;Ljava/security/Provider;)Ljava/security/KeyFactory;

    move-result-object v0

    .line 4
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzd()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object v1

    .line 5
    new-instance v8, Ljava/security/spec/RSAPrivateCrtKeySpec;

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zze()Lcom/google/android/recaptcha/internal/zzaal;

    move-result-object v2

    .line 6
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaal;->zzd()Ljava/math/BigInteger;

    move-result-object v9

    .line 7
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzaah;->zzc()Ljava/math/BigInteger;

    move-result-object v10

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzi()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v2

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v3

    .line 8
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v11

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzf()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v2

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v3

    .line 9
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v12

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzh()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v2

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v3

    .line 10
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v13

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzj()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v2

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v3

    .line 11
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v14

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzk()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v2

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v3

    .line 12
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v15

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zzl()Lcom/google/android/recaptcha/internal/zzadz;

    move-result-object v2

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v3

    .line 13
    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v16

    invoke-direct/range {v8 .. v16}, Ljava/security/spec/RSAPrivateCrtKeySpec;-><init>(Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;)V

    .line 14
    invoke-virtual {v0, v8}, Ljava/security/KeyFactory;->generatePrivate(Ljava/security/spec/KeySpec;)Ljava/security/PrivateKey;

    move-result-object v0

    check-cast v0, Ljava/security/interfaces/RSAPrivateCrtKey;

    move-object v2, v1

    move-object v1, v0

    new-instance v0, Lcom/google/android/recaptcha/internal/zzacm;

    move-object v3, v2

    .line 15
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzaah;->zze()Lcom/google/android/recaptcha/internal/zzaaf;

    move-result-object v2

    move-object v4, v3

    .line 16
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaah;->zzf()Lcom/google/android/recaptcha/internal/zzaaf;

    move-result-object v3

    move-object v5, v4

    .line 17
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzaah;->zzg()I

    move-result v4

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/recaptcha/internal/zzaaj;->zze()Lcom/google/android/recaptcha/internal/zzaal;

    move-result-object v6

    .line 18
    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzaax;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v6

    .line 19
    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v6

    .line 20
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzaah;->zzd()Lcom/google/android/recaptcha/internal/zzaag;

    move-result-object v5

    sget-object v8, Lcom/google/android/recaptcha/internal/zzaag;->zzc:Lcom/google/android/recaptcha/internal/zzaag;

    invoke-virtual {v5, v8}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_0

    sget-object v5, Lcom/google/android/recaptcha/internal/zzacm;->zzb:[B

    :goto_0
    move-object/from16 v17, v6

    move-object v6, v5

    move-object/from16 v5, v17

    goto :goto_1

    .line 21
    :cond_0
    sget-object v5, Lcom/google/android/recaptcha/internal/zzacm;->zza:[B

    goto :goto_0

    :goto_1
    invoke-direct/range {v0 .. v7}, Lcom/google/android/recaptcha/internal/zzacm;-><init>(Ljava/security/interfaces/RSAPrivateCrtKey;Lcom/google/android/recaptcha/internal/zzaaf;Lcom/google/android/recaptcha/internal/zzaaf;I[B[BLjava/security/Provider;)V

    return-object v0

    .line 2
    :cond_1
    new-instance v0, Ljava/security/NoSuchProviderException;

    const-string v1, "RSA SSA PSS using Conscrypt is not supported."

    invoke-direct {v0, v1}, Ljava/security/NoSuchProviderException;-><init>(Ljava/lang/String;)V

    throw v0
.end method


# virtual methods
.method public final zza([B)[B
    .locals 0

    const/4 p0, 0x0

    throw p0
.end method
