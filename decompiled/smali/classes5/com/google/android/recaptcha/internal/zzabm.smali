.class public final Lcom/google/android/recaptcha/internal/zzabm;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzst;


# static fields
.field static final zza:Lcom/google/android/recaptcha/internal/zztv;

.field static final zzb:Lcom/google/android/recaptcha/internal/zztv;

.field static final zzc:Lcom/google/android/recaptcha/internal/zztv;

.field private static final zze:[B

.field private static final zzf:[B


# instance fields
.field private final zzg:Ljava/security/interfaces/ECPublicKey;

.field private final zzh:Ljava/lang/String;

.field private final zzi:Lcom/google/android/recaptcha/internal/zzacz;

.field private final zzj:[B

.field private final zzk:[B

.field private final zzl:Ljava/security/Provider;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    const/4 v0, 0x0

    .line 1
    new-array v1, v0, [B

    sput-object v1, Lcom/google/android/recaptcha/internal/zzabm;->zze:[B

    const/4 v1, 0x1

    new-array v1, v1, [B

    aput-byte v0, v1, v0

    sput-object v1, Lcom/google/android/recaptcha/internal/zzabm;->zzf:[B

    invoke-static {}, Lcom/google/android/recaptcha/internal/zztv;->zza()Lcom/google/android/recaptcha/internal/zztu;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzado;->zzc:Lcom/google/android/recaptcha/internal/zzado;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzxu;->zza:Lcom/google/android/recaptcha/internal/zzxu;

    .line 2
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzado;->zzd:Lcom/google/android/recaptcha/internal/zzado;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzxu;->zzb:Lcom/google/android/recaptcha/internal/zzxu;

    .line 3
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzado;->zze:Lcom/google/android/recaptcha/internal/zzado;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzxu;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    .line 4
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    .line 5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zztu;->zzb()Lcom/google/android/recaptcha/internal/zztv;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabm;->zza:Lcom/google/android/recaptcha/internal/zztv;

    .line 6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zztv;->zza()Lcom/google/android/recaptcha/internal/zztu;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzacz;->zza:Lcom/google/android/recaptcha/internal/zzacz;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzxv;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    .line 7
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzacz;->zzb:Lcom/google/android/recaptcha/internal/zzacz;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzxv;->zzb:Lcom/google/android/recaptcha/internal/zzxv;

    .line 8
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    .line 9
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zztu;->zzb()Lcom/google/android/recaptcha/internal/zztv;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabm;->zzb:Lcom/google/android/recaptcha/internal/zztv;

    .line 10
    invoke-static {}, Lcom/google/android/recaptcha/internal/zztv;->zza()Lcom/google/android/recaptcha/internal/zztu;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzacy;->zza:Lcom/google/android/recaptcha/internal/zzacy;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzxt;->zza:Lcom/google/android/recaptcha/internal/zzxt;

    .line 11
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzacy;->zzb:Lcom/google/android/recaptcha/internal/zzacy;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzxt;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    .line 12
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzacy;->zzc:Lcom/google/android/recaptcha/internal/zzacy;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzxt;->zzc:Lcom/google/android/recaptcha/internal/zzxt;

    .line 13
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zztu;->zza(Ljava/lang/Enum;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zztu;

    .line 14
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zztu;->zzb()Lcom/google/android/recaptcha/internal/zztv;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzabm;->zzc:Lcom/google/android/recaptcha/internal/zztv;

    return-void
.end method

.method private constructor <init>(Ljava/security/interfaces/ECPublicKey;Lcom/google/android/recaptcha/internal/zzado;Lcom/google/android/recaptcha/internal/zzacz;[B[BLjava/security/Provider;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x2

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 3
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzadw;->zza(Lcom/google/android/recaptcha/internal/zzado;)Ljava/lang/String;

    move-result-object p2

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzh:Ljava/lang/String;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzg:Ljava/security/interfaces/ECPublicKey;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzi:Lcom/google/android/recaptcha/internal/zzacz;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzj:[B

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzk:[B

    iput-object p6, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzl:Ljava/security/Provider;

    return-void

    .line 2
    :cond_0
    const-string p0, "Can not use ECDSA in FIPS-mode, as BoringCrypto is not available."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzyb;Ljava/security/Provider;)Lcom/google/android/recaptcha/internal/zzst;
    .locals 8

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzabm;->zzc:Lcom/google/android/recaptcha/internal/zztv;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyb;->zzf()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzxx;->zzc()Lcom/google/android/recaptcha/internal/zzxt;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zztv;->zzb(Ljava/lang/Object;)Ljava/lang/Enum;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzacy;

    .line 2
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzada;->zzb(Lcom/google/android/recaptcha/internal/zzacy;)Ljava/security/spec/ECParameterSpec;

    move-result-object v0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyb;->zzd()Ljava/security/spec/ECPoint;

    move-result-object v1

    .line 3
    new-instance v2, Ljava/security/spec/ECPublicKeySpec;

    invoke-direct {v2, v1, v0}, Ljava/security/spec/ECPublicKeySpec;-><init>(Ljava/security/spec/ECPoint;Ljava/security/spec/ECParameterSpec;)V

    const-string v0, "EC"

    if-eqz p1, :cond_0

    .line 4
    invoke-static {v0, p1}, Ljava/security/KeyFactory;->getInstance(Ljava/lang/String;Ljava/security/Provider;)Ljava/security/KeyFactory;

    move-result-object v0

    goto :goto_0

    .line 5
    :cond_0
    sget-object v1, Lcom/google/android/recaptcha/internal/zzadf;->zzc:Lcom/google/android/recaptcha/internal/zzadf;

    invoke-virtual {v1, v0}, Lcom/google/android/recaptcha/internal/zzadf;->zzb(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/security/KeyFactory;

    .line 6
    :goto_0
    invoke-virtual {v0, v2}, Ljava/security/KeyFactory;->generatePublic(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;

    move-result-object v0

    move-object v2, v0

    check-cast v2, Ljava/security/interfaces/ECPublicKey;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzabm;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabm;->zza:Lcom/google/android/recaptcha/internal/zztv;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyb;->zzf()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v3

    .line 7
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzxx;->zzd()Lcom/google/android/recaptcha/internal/zzxu;

    move-result-object v3

    invoke-virtual {v0, v3}, Lcom/google/android/recaptcha/internal/zztv;->zzb(Ljava/lang/Object;)Ljava/lang/Enum;

    move-result-object v0

    move-object v3, v0

    check-cast v3, Lcom/google/android/recaptcha/internal/zzado;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzabm;->zzb:Lcom/google/android/recaptcha/internal/zztv;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyb;->zzf()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v4

    .line 8
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzxx;->zzb()Lcom/google/android/recaptcha/internal/zzxv;

    move-result-object v4

    invoke-virtual {v0, v4}, Lcom/google/android/recaptcha/internal/zztv;->zzb(Ljava/lang/Object;)Ljava/lang/Enum;

    move-result-object v0

    move-object v4, v0

    check-cast v4, Lcom/google/android/recaptcha/internal/zzacz;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyb;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v0

    .line 9
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object v5

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzyb;->zzf()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object p0

    .line 10
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxx;->zze()Lcom/google/android/recaptcha/internal/zzxw;

    move-result-object p0

    sget-object v0, Lcom/google/android/recaptcha/internal/zzxw;->zzc:Lcom/google/android/recaptcha/internal/zzxw;

    invoke-virtual {p0, v0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    sget-object p0, Lcom/google/android/recaptcha/internal/zzabm;->zzf:[B

    :goto_1
    move-object v6, p0

    move-object v7, p1

    goto :goto_2

    .line 11
    :cond_1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzabm;->zze:[B

    goto :goto_1

    :goto_2
    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzabm;-><init>(Ljava/security/interfaces/ECPublicKey;Lcom/google/android/recaptcha/internal/zzado;Lcom/google/android/recaptcha/internal/zzacz;[B[BLjava/security/Provider;)V

    return-object v1
.end method

.method private final zzc([B[B)V
    .locals 11

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzi:Lcom/google/android/recaptcha/internal/zzacz;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzacz;->zza:Lcom/google/android/recaptcha/internal/zzacz;

    const/16 v2, 0x8

    const-string v3, "Invalid signature"

    if-ne v0, v1, :cond_1

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzg:Ljava/security/interfaces/ECPublicKey;

    invoke-interface {v0}, Ljava/security/interfaces/ECKey;->getParams()Ljava/security/spec/ECParameterSpec;

    move-result-object v0

    invoke-virtual {v0}, Ljava/security/spec/ECParameterSpec;->getCurve()Ljava/security/spec/EllipticCurve;

    move-result-object v0

    .line 2
    array-length v1, p1

    .line 3
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zztt;->zzc(Ljava/security/spec/EllipticCurve;)Ljava/math/BigInteger;

    move-result-object v0

    sget-object v4, Ljava/math/BigInteger;->ONE:Ljava/math/BigInteger;

    .line 4
    invoke-virtual {v0, v4}, Ljava/math/BigInteger;->subtract(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v0

    invoke-virtual {v0}, Ljava/math/BigInteger;->bitLength()I

    move-result v0

    add-int/lit8 v0, v0, 0x7

    div-int/2addr v0, v2

    add-int/2addr v0, v0

    if-ne v1, v0, :cond_0

    .line 6
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzada;->zza([B)[B

    move-result-object p1

    goto :goto_0

    .line 5
    :cond_0
    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 7
    :cond_1
    :goto_0
    array-length v0, p1

    if-lt v0, v2, :cond_8

    const/4 v1, 0x0

    .line 8
    aget-byte v1, p1, v1

    const/16 v2, 0x30

    if-ne v1, v2, :cond_8

    const/4 v1, 0x1

    .line 9
    aget-byte v2, p1, v1

    and-int/lit16 v2, v2, 0xff

    const/16 v4, 0x81

    const/16 v5, 0x80

    const/4 v6, 0x2

    if-ne v2, v4, :cond_2

    .line 10
    aget-byte v2, p1, v6

    and-int/lit16 v2, v2, 0xff

    if-lt v2, v5, :cond_8

    move v4, v6

    goto :goto_1

    :cond_2
    if-eq v2, v5, :cond_8

    if-gt v2, v4, :cond_8

    move v4, v1

    :goto_1
    add-int/lit8 v7, v0, -0x1

    sub-int/2addr v7, v4

    if-ne v2, v7, :cond_8

    add-int/lit8 v2, v4, 0x1

    .line 11
    aget-byte v2, p1, v2

    if-ne v2, v6, :cond_8

    add-int/lit8 v2, v4, 0x2

    .line 12
    aget-byte v2, p1, v2

    and-int/lit16 v2, v2, 0xff

    add-int/lit8 v7, v4, 0x3

    add-int v8, v7, v2

    add-int/lit8 v9, v8, 0x1

    if-ge v9, v0, :cond_8

    if-eqz v2, :cond_8

    .line 13
    aget-byte v7, p1, v7

    and-int/lit16 v10, v7, 0xff

    if-ge v10, v5, :cond_8

    if-le v2, v1, :cond_3

    if-nez v7, :cond_3

    add-int/lit8 v7, v4, 0x4

    .line 14
    aget-byte v7, p1, v7

    and-int/lit16 v7, v7, 0xff

    if-lt v7, v5, :cond_8

    .line 15
    :cond_3
    aget-byte v7, p1, v8

    if-ne v7, v6, :cond_8

    .line 16
    aget-byte v7, p1, v9

    and-int/lit16 v7, v7, 0xff

    add-int/2addr v8, v6

    add-int/2addr v8, v7

    if-ne v8, v0, :cond_8

    if-eqz v7, :cond_8

    add-int/lit8 v0, v4, 0x5

    add-int/2addr v0, v2

    .line 17
    aget-byte v0, p1, v0

    and-int/lit16 v6, v0, 0xff

    if-ge v6, v5, :cond_8

    if-le v7, v1, :cond_4

    if-nez v0, :cond_4

    add-int/lit8 v4, v4, 0x6

    add-int/2addr v4, v2

    .line 18
    aget-byte v0, p1, v4

    and-int/lit16 v0, v0, 0xff

    if-lt v0, v5, :cond_8

    :cond_4
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzh:Ljava/lang/String;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzl:Ljava/security/Provider;

    if-eqz v1, :cond_5

    .line 19
    invoke-static {v0, v1}, Ljava/security/Signature;->getInstance(Ljava/lang/String;Ljava/security/Provider;)Ljava/security/Signature;

    move-result-object v0

    goto :goto_2

    .line 20
    :cond_5
    sget-object v1, Lcom/google/android/recaptcha/internal/zzadf;->zza:Lcom/google/android/recaptcha/internal/zzadf;

    invoke-virtual {v1, v0}, Lcom/google/android/recaptcha/internal/zzadf;->zzb(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/security/Signature;

    .line 19
    :goto_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzg:Ljava/security/interfaces/ECPublicKey;

    .line 21
    invoke-virtual {v0, v1}, Ljava/security/Signature;->initVerify(Ljava/security/PublicKey;)V

    .line 22
    invoke-virtual {v0, p2}, Ljava/security/Signature;->update([B)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzk:[B

    array-length p2, p0

    if-lez p2, :cond_6

    .line 23
    invoke-virtual {v0, p0}, Ljava/security/Signature;->update([B)V

    .line 24
    :cond_6
    :try_start_0
    invoke-virtual {v0, p1}, Ljava/security/Signature;->verify([B)Z

    move-result p0
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    if-eqz p0, :cond_7

    return-void

    .line 25
    :catch_0
    :cond_7
    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 26
    :cond_8
    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public final zza([B[B)V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzabm;->zzj:[B

    array-length v1, v0

    if-nez v1, :cond_0

    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzabm;->zzc([B[B)V

    return-void

    .line 2
    :cond_0
    invoke-static {v0, p1}, Lcom/google/android/recaptcha/internal/zzwc;->zzc([B[B)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 4
    array-length v0, p1

    invoke-static {p1, v1, v0}, Ljava/util/Arrays;->copyOfRange([BII)[B

    move-result-object p1

    .line 5
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzabm;->zzc([B[B)V

    return-void

    .line 3
    :cond_1
    const-string p0, "Invalid signature (output prefix mismatch)"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
