.class final Lcom/google/android/recaptcha/internal/zzads;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzss;


# direct methods
.method public synthetic constructor <init>(Ljava/security/interfaces/RSAPrivateCrtKey;Lcom/google/android/recaptcha/internal/zzado;Lcom/google/android/recaptcha/internal/zzado;I[B[B[B)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zztf;->zza()Z

    move-result p0

    const/4 p4, 0x0

    if-nez p0, :cond_1

    .line 3
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzadx;->zza(Lcom/google/android/recaptcha/internal/zzado;)V

    .line 4
    invoke-virtual {p2, p3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 6
    invoke-interface {p1}, Ljava/security/interfaces/RSAKey;->getModulus()Ljava/math/BigInteger;

    move-result-object p0

    invoke-virtual {p0}, Ljava/math/BigInteger;->bitLength()I

    move-result p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzadx;->zzb(I)V

    .line 7
    invoke-interface {p1}, Ljava/security/interfaces/RSAPrivateCrtKey;->getPublicExponent()Ljava/math/BigInteger;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzadx;->zzc(Ljava/math/BigInteger;)V

    .line 8
    sget-object p0, Lcom/google/android/recaptcha/internal/zzadf;->zzc:Lcom/google/android/recaptcha/internal/zzadf;

    const-string p2, "RSA"

    invoke-virtual {p0, p2}, Lcom/google/android/recaptcha/internal/zzadf;->zzb(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/security/KeyFactory;

    .line 9
    new-instance p2, Ljava/security/spec/RSAPublicKeySpec;

    .line 10
    invoke-interface {p1}, Ljava/security/interfaces/RSAKey;->getModulus()Ljava/math/BigInteger;

    move-result-object p3

    invoke-interface {p1}, Ljava/security/interfaces/RSAPrivateCrtKey;->getPublicExponent()Ljava/math/BigInteger;

    move-result-object p1

    invoke-direct {p2, p3, p1}, Ljava/security/spec/RSAPublicKeySpec;-><init>(Ljava/math/BigInteger;Ljava/math/BigInteger;)V

    invoke-virtual {p0, p2}, Ljava/security/KeyFactory;->generatePublic(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;

    move-result-object p0

    check-cast p0, Ljava/security/interfaces/RSAPublicKey;

    return-void

    .line 5
    :cond_0
    const-string p0, "sigHash and mgf1Hash must be the same"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw p4

    .line 2
    :cond_1
    const-string p0, "Can not use RSA PSS in FIPS-mode, as BoringCrypto module is not available."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw p4
.end method


# virtual methods
.method public final zza([B)[B
    .locals 0

    const/4 p0, 0x0

    throw p0
.end method
