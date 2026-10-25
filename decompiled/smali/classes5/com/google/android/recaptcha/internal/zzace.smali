.class public final Lcom/google/android/recaptcha/internal/zzace;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzst;


# static fields
.field private static final zza:[B

.field private static final zzb:[B


# instance fields
.field private final zzc:Ljava/security/interfaces/RSAPublicKey;

.field private final zzd:Ljava/lang/String;

.field private final zze:[B

.field private final zzf:[B

.field private final zzg:Ljava/security/Provider;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const/4 v0, 0x0

    new-array v1, v0, [B

    sput-object v1, Lcom/google/android/recaptcha/internal/zzace;->zza:[B

    const/4 v1, 0x1

    new-array v1, v1, [B

    aput-byte v0, v1, v0

    sput-object v1, Lcom/google/android/recaptcha/internal/zzace;->zzb:[B

    return-void
.end method

.method private constructor <init>(Ljava/security/interfaces/RSAPublicKey;Lcom/google/android/recaptcha/internal/zzzt;[B[BLjava/security/Provider;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x2

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 3
    invoke-interface {p1}, Ljava/security/interfaces/RSAKey;->getModulus()Ljava/math/BigInteger;

    move-result-object v0

    invoke-virtual {v0}, Ljava/math/BigInteger;->bitLength()I

    move-result v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzadx;->zzb(I)V

    .line 4
    invoke-interface {p1}, Ljava/security/interfaces/RSAPublicKey;->getPublicExponent()Ljava/math/BigInteger;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzadx;->zzc(Ljava/math/BigInteger;)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzace;->zzc:Ljava/security/interfaces/RSAPublicKey;

    .line 5
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzace;->zzc(Lcom/google/android/recaptcha/internal/zzzt;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzace;->zzd:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzace;->zze:[B

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzace;->zzf:[B

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzace;->zzg:Ljava/security/Provider;

    return-void

    .line 2
    :cond_0
    const-string p0, "Can not use RSA-PKCS1.5 in FIPS-mode, as BoringCrypto module is not available."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public static zzb()Ljava/security/Provider;
    .locals 1

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwc;->zza()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwc;->zzb()Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 2
    :cond_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzti;->zza()Ljava/security/Provider;

    move-result-object v0

    return-object v0
.end method

.method public static zzc(Lcom/google/android/recaptcha/internal/zzzt;)Ljava/lang/String;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzzt;->zza:Lcom/google/android/recaptcha/internal/zzzt;

    if-ne p0, v0, :cond_0

    const-string p0, "SHA256withRSA"

    return-object p0

    :cond_0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzzt;->zzb:Lcom/google/android/recaptcha/internal/zzzt;

    if-ne p0, v0, :cond_1

    const-string p0, "SHA384withRSA"

    return-object p0

    :cond_1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzzt;->zzc:Lcom/google/android/recaptcha/internal/zzzt;

    if-ne p0, v0, :cond_2

    const-string p0, "SHA512withRSA"

    return-object p0

    :cond_2
    const-string/jumbo p0, "unknown hash type"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static zzd(Lcom/google/android/recaptcha/internal/zzzz;)Lcom/google/android/recaptcha/internal/zzst;
    .locals 1

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzace;->zzb()Ljava/security/Provider;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 3
    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzace;->zze(Lcom/google/android/recaptcha/internal/zzzz;Ljava/security/Provider;)Lcom/google/android/recaptcha/internal/zzst;

    move-result-object p0

    return-object p0

    .line 2
    :cond_0
    new-instance p0, Ljava/security/NoSuchProviderException;

    const-string v0, "RSA-PKCS1.5 using Conscrypt is not supported."

    invoke-direct {p0, v0}, Ljava/security/NoSuchProviderException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public static zze(Lcom/google/android/recaptcha/internal/zzzz;Ljava/security/Provider;)Lcom/google/android/recaptcha/internal/zzst;
    .locals 7

    .line 1
    const-string v0, "RSA"

    invoke-static {v0, p1}, Ljava/security/KeyFactory;->getInstance(Ljava/lang/String;Ljava/security/Provider;)Ljava/security/KeyFactory;

    move-result-object v0

    .line 2
    new-instance v1, Ljava/security/spec/RSAPublicKeySpec;

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzz;->zzd()Ljava/math/BigInteger;

    move-result-object v2

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzz;->zzf()Lcom/google/android/recaptcha/internal/zzzv;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzzv;->zzc()Ljava/math/BigInteger;

    move-result-object v3

    invoke-direct {v1, v2, v3}, Ljava/security/spec/RSAPublicKeySpec;-><init>(Ljava/math/BigInteger;Ljava/math/BigInteger;)V

    .line 4
    invoke-virtual {v0, v1}, Ljava/security/KeyFactory;->generatePublic(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;

    move-result-object v0

    move-object v2, v0

    check-cast v2, Ljava/security/interfaces/RSAPublicKey;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzace;

    .line 5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzz;->zzf()Lcom/google/android/recaptcha/internal/zzzv;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzzv;->zze()Lcom/google/android/recaptcha/internal/zzzt;

    move-result-object v3

    .line 6
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzz;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v4

    .line 7
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzz;->zzf()Lcom/google/android/recaptcha/internal/zzzv;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzv;->zzd()Lcom/google/android/recaptcha/internal/zzzu;

    move-result-object p0

    sget-object v0, Lcom/google/android/recaptcha/internal/zzzu;->zzc:Lcom/google/android/recaptcha/internal/zzzu;

    invoke-virtual {p0, v0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzace;->zzb:[B

    :goto_0
    move-object v5, p0

    move-object v6, p1

    goto :goto_1

    .line 8
    :cond_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzace;->zza:[B

    goto :goto_0

    :goto_1
    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzace;-><init>(Ljava/security/interfaces/RSAPublicKey;Lcom/google/android/recaptcha/internal/zzzt;[B[BLjava/security/Provider;)V

    return-object v1
.end method


# virtual methods
.method public final zza([B[B)V
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzace;->zze:[B

    invoke-static {v0, p1}, Lcom/google/android/recaptcha/internal/zzwc;->zzc([B[B)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzace;->zzd:Ljava/lang/String;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzace;->zzg:Ljava/security/Provider;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzace;->zzc:Ljava/security/interfaces/RSAPublicKey;

    .line 3
    invoke-static {v1, v2}, Ljava/security/Signature;->getInstance(Ljava/lang/String;Ljava/security/Provider;)Ljava/security/Signature;

    move-result-object v1

    .line 4
    invoke-virtual {v1, v3}, Ljava/security/Signature;->initVerify(Ljava/security/PublicKey;)V

    .line 5
    invoke-virtual {v1, p2}, Ljava/security/Signature;->update([B)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzace;->zzf:[B

    array-length p2, p0

    if-lez p2, :cond_0

    .line 6
    invoke-virtual {v1, p0}, Ljava/security/Signature;->update([B)V

    :cond_0
    :try_start_0
    array-length p0, v0

    .line 7
    array-length p2, p1

    .line 8
    invoke-static {p1, p0, p2}, Ljava/util/Arrays;->copyOfRange([BII)[B

    move-result-object p0

    .line 9
    invoke-virtual {v1, p0}, Ljava/security/Signature;->verify([B)Z

    move-result p0
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    if-eqz p0, :cond_1

    return-void

    .line 10
    :catch_0
    :cond_1
    const-string p0, "Invalid signature"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 2
    :cond_2
    const-string p0, "Invalid signature (output prefix mismatch)"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
