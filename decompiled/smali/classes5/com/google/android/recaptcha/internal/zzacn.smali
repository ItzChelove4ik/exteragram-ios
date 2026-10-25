.class public final Lcom/google/android/recaptcha/internal/zzacn;
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

.field private final zze:Ljava/security/spec/PSSParameterSpec;

.field private final zzf:[B

.field private final zzg:[B

.field private final zzh:Ljava/security/Provider;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const/4 v0, 0x0

    new-array v1, v0, [B

    sput-object v1, Lcom/google/android/recaptcha/internal/zzacn;->zza:[B

    const/4 v1, 0x1

    new-array v1, v1, [B

    aput-byte v0, v1, v0

    sput-object v1, Lcom/google/android/recaptcha/internal/zzacn;->zzb:[B

    return-void
.end method

.method private constructor <init>(Ljava/security/interfaces/RSAPublicKey;Lcom/google/android/recaptcha/internal/zzaaf;Lcom/google/android/recaptcha/internal/zzaaf;I[B[BLjava/security/Provider;)V
    .locals 2

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x2

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 3
    invoke-virtual {p2, p3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 5
    invoke-interface {p1}, Ljava/security/interfaces/RSAKey;->getModulus()Ljava/math/BigInteger;

    move-result-object v0

    invoke-virtual {v0}, Ljava/math/BigInteger;->bitLength()I

    move-result v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzadx;->zzb(I)V

    .line 6
    invoke-interface {p1}, Ljava/security/interfaces/RSAPublicKey;->getPublicExponent()Ljava/math/BigInteger;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzadx;->zzc(Ljava/math/BigInteger;)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzacn;->zzc:Ljava/security/interfaces/RSAPublicKey;

    .line 7
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzacn;->zzc(Lcom/google/android/recaptcha/internal/zzaaf;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzacn;->zzd:Ljava/lang/String;

    .line 8
    invoke-static {p2, p3, p4}, Lcom/google/android/recaptcha/internal/zzacn;->zzd(Lcom/google/android/recaptcha/internal/zzaaf;Lcom/google/android/recaptcha/internal/zzaaf;I)Ljava/security/spec/PSSParameterSpec;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzacn;->zze:Ljava/security/spec/PSSParameterSpec;

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzacn;->zzf:[B

    iput-object p6, p0, Lcom/google/android/recaptcha/internal/zzacn;->zzg:[B

    iput-object p7, p0, Lcom/google/android/recaptcha/internal/zzacn;->zzh:Ljava/security/Provider;

    return-void

    .line 4
    :cond_0
    const-string p0, "sigHash and mgf1Hash must be the same"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v1

    .line 2
    :cond_1
    const-string p0, "Cannot use RSA SSA PSS in FIPS-mode, as BoringCrypto module is not available."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v1
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

.method public static zzc(Lcom/google/android/recaptcha/internal/zzaaf;)Ljava/lang/String;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaaf;->zza:Lcom/google/android/recaptcha/internal/zzaaf;

    if-ne p0, v0, :cond_0

    const-string p0, "SHA256withRSA/PSS"

    return-object p0

    :cond_0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaaf;->zzb:Lcom/google/android/recaptcha/internal/zzaaf;

    if-ne p0, v0, :cond_1

    const-string p0, "SHA384withRSA/PSS"

    return-object p0

    :cond_1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaaf;->zzc:Lcom/google/android/recaptcha/internal/zzaaf;

    if-ne p0, v0, :cond_2

    const-string p0, "SHA512withRSA/PSS"

    return-object p0

    :cond_2
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "Unsupported hash: "

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static zzd(Lcom/google/android/recaptcha/internal/zzaaf;Lcom/google/android/recaptcha/internal/zzaaf;I)Ljava/security/spec/PSSParameterSpec;
    .locals 6

    .line 1
    new-instance v0, Ljava/security/spec/PSSParameterSpec;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaaf;->zza:Lcom/google/android/recaptcha/internal/zzaaf;

    const/4 v2, 0x0

    if-ne p0, v1, :cond_0

    const-string p0, "SHA-256"

    goto :goto_0

    .line 4
    :cond_0
    sget-object v3, Lcom/google/android/recaptcha/internal/zzaaf;->zzb:Lcom/google/android/recaptcha/internal/zzaaf;

    if-ne p0, v3, :cond_1

    const-string p0, "SHA-384"

    goto :goto_0

    :cond_1
    sget-object v3, Lcom/google/android/recaptcha/internal/zzaaf;->zzc:Lcom/google/android/recaptcha/internal/zzaaf;

    if-ne p0, v3, :cond_5

    .line 6
    const-string p0, "SHA-512"

    :goto_0
    if-ne p1, v1, :cond_2

    .line 2
    sget-object p1, Ljava/security/spec/MGF1ParameterSpec;->SHA256:Ljava/security/spec/MGF1ParameterSpec;

    :goto_1
    move-object v3, p1

    goto :goto_2

    .line 5
    :cond_2
    sget-object v1, Lcom/google/android/recaptcha/internal/zzaaf;->zzb:Lcom/google/android/recaptcha/internal/zzaaf;

    if-ne p1, v1, :cond_3

    .line 3
    sget-object p1, Ljava/security/spec/MGF1ParameterSpec;->SHA384:Ljava/security/spec/MGF1ParameterSpec;

    goto :goto_1

    :cond_3
    sget-object v1, Lcom/google/android/recaptcha/internal/zzaaf;->zzc:Lcom/google/android/recaptcha/internal/zzaaf;

    if-ne p1, v1, :cond_4

    .line 4
    sget-object p1, Ljava/security/spec/MGF1ParameterSpec;->SHA512:Ljava/security/spec/MGF1ParameterSpec;

    goto :goto_1

    .line 2
    :goto_2
    const-string v2, "MGF1"

    const/4 v5, 0x1

    move-object v1, p0

    move v4, p2

    .line 5
    invoke-direct/range {v0 .. v5}, Ljava/security/spec/PSSParameterSpec;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/security/spec/AlgorithmParameterSpec;II)V

    return-object v0

    .line 6
    :cond_4
    invoke-static {p1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string p1, "Unsupported MGF1 hash: "

    invoke-virtual {p1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v2

    .line 7
    :cond_5
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string p1, "Unsupported MD hash: "

    invoke-virtual {p1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v2
.end method

.method public static zze(Lcom/google/android/recaptcha/internal/zzaal;)Lcom/google/android/recaptcha/internal/zzst;
    .locals 8

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
    new-instance v1, Ljava/security/spec/RSAPublicKeySpec;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaal;->zzd()Ljava/math/BigInteger;

    move-result-object v2

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaal;->zzf()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object v3

    .line 5
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzaah;->zzc()Ljava/math/BigInteger;

    move-result-object v3

    invoke-direct {v1, v2, v3}, Ljava/security/spec/RSAPublicKeySpec;-><init>(Ljava/math/BigInteger;Ljava/math/BigInteger;)V

    .line 6
    invoke-virtual {v0, v1}, Ljava/security/KeyFactory;->generatePublic(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;

    move-result-object v0

    move-object v1, v0

    check-cast v1, Ljava/security/interfaces/RSAPublicKey;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaal;->zzf()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object v0

    move-object v2, v0

    new-instance v0, Lcom/google/android/recaptcha/internal/zzacn;

    move-object v3, v2

    .line 7
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzaah;->zze()Lcom/google/android/recaptcha/internal/zzaaf;

    move-result-object v2

    move-object v4, v3

    .line 8
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaah;->zzf()Lcom/google/android/recaptcha/internal/zzaaf;

    move-result-object v3

    .line 9
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaah;->zzg()I

    move-result v4

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaal;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v5

    .line 10
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v5

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaal;->zzf()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object p0

    .line 11
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaah;->zzd()Lcom/google/android/recaptcha/internal/zzaag;

    move-result-object p0

    sget-object v6, Lcom/google/android/recaptcha/internal/zzaag;->zzc:Lcom/google/android/recaptcha/internal/zzaag;

    invoke-virtual {p0, v6}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzacn;->zzb:[B

    :goto_0
    move-object v6, p0

    goto :goto_1

    .line 12
    :cond_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzacn;->zza:[B

    goto :goto_0

    :goto_1
    invoke-direct/range {v0 .. v7}, Lcom/google/android/recaptcha/internal/zzacn;-><init>(Ljava/security/interfaces/RSAPublicKey;Lcom/google/android/recaptcha/internal/zzaaf;Lcom/google/android/recaptcha/internal/zzaaf;I[B[BLjava/security/Provider;)V

    return-object v0

    .line 2
    :cond_1
    new-instance p0, Ljava/security/NoSuchProviderException;

    const-string v0, "RSA SSA PSS using Conscrypt is not supported."

    invoke-direct {p0, v0}, Ljava/security/NoSuchProviderException;-><init>(Ljava/lang/String;)V

    throw p0
.end method


# virtual methods
.method public final zza([B[B)V
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzacn;->zzf:[B

    invoke-static {v0, p1}, Lcom/google/android/recaptcha/internal/zzwc;->zzc([B[B)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzacn;->zzd:Ljava/lang/String;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzacn;->zzh:Ljava/security/Provider;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzacn;->zzc:Ljava/security/interfaces/RSAPublicKey;

    .line 3
    invoke-static {v1, v2}, Ljava/security/Signature;->getInstance(Ljava/lang/String;Ljava/security/Provider;)Ljava/security/Signature;

    move-result-object v1

    .line 4
    invoke-virtual {v1, v3}, Ljava/security/Signature;->initVerify(Ljava/security/PublicKey;)V

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzacn;->zze:Ljava/security/spec/PSSParameterSpec;

    .line 5
    invoke-virtual {v1, v2}, Ljava/security/Signature;->setParameter(Ljava/security/spec/AlgorithmParameterSpec;)V

    .line 6
    invoke-virtual {v1, p2}, Ljava/security/Signature;->update([B)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzacn;->zzg:[B

    array-length p2, p0

    if-lez p2, :cond_0

    .line 7
    invoke-virtual {v1, p0}, Ljava/security/Signature;->update([B)V

    .line 8
    :cond_0
    array-length p0, p1

    array-length p2, v0

    sub-int/2addr p0, p2

    invoke-virtual {v1, p1, p2, p0}, Ljava/security/Signature;->verify([BII)Z

    move-result p0

    if-eqz p0, :cond_1

    return-void

    .line 9
    :cond_1
    const-string p0, "signature verification failed"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 2
    :cond_2
    const-string p0, "Invalid signature (output prefix mismatch)"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
