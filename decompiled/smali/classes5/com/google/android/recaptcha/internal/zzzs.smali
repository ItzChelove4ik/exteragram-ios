.class public final Lcom/google/android/recaptcha/internal/zzzs;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zze:Ljava/math/BigInteger;

.field private static final zzf:Ljava/math/BigInteger;


# instance fields
.field private zza:Ljava/lang/Integer;

.field private zzb:Ljava/math/BigInteger;

.field private zzc:Lcom/google/android/recaptcha/internal/zzzt;

.field private zzd:Lcom/google/android/recaptcha/internal/zzzu;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const-wide/16 v0, 0x2

    .line 1
    invoke-static {v0, v1}, Ljava/math/BigInteger;->valueOf(J)Ljava/math/BigInteger;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzzs;->zze:Ljava/math/BigInteger;

    const/16 v1, 0x100

    .line 2
    invoke-virtual {v0, v1}, Ljava/math/BigInteger;->pow(I)Ljava/math/BigInteger;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzzs;->zzf:Ljava/math/BigInteger;

    return-void
.end method

.method public synthetic constructor <init>([B)V
    .locals 1

    .line 2
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzs;->zza:Ljava/lang/Integer;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzzv;->zza:Ljava/math/BigInteger;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzb:Ljava/math/BigInteger;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzc:Lcom/google/android/recaptcha/internal/zzzt;

    sget-object p1, Lcom/google/android/recaptcha/internal/zzzu;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    return-void
.end method


# virtual methods
.method public final zza(I)Lcom/google/android/recaptcha/internal/zzzs;
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzs;->zza:Ljava/lang/Integer;

    return-object p0
.end method

.method public final zzb(Ljava/math/BigInteger;)Lcom/google/android/recaptcha/internal/zzzs;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzb:Ljava/math/BigInteger;

    return-object p0
.end method

.method public final zzc(Lcom/google/android/recaptcha/internal/zzzu;)Lcom/google/android/recaptcha/internal/zzzs;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    return-object p0
.end method

.method public final zzd(Lcom/google/android/recaptcha/internal/zzzt;)Lcom/google/android/recaptcha/internal/zzzs;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzc:Lcom/google/android/recaptcha/internal/zzzt;

    return-object p0
.end method

.method public final zze()Lcom/google/android/recaptcha/internal/zzzv;
    .locals 7

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzs;->zza:Ljava/lang/Integer;

    const/4 v1, 0x0

    if-eqz v0, :cond_8

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzb:Ljava/math/BigInteger;

    if-eqz v2, :cond_7

    .line 2
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzc:Lcom/google/android/recaptcha/internal/zzzt;

    if-eqz v2, :cond_6

    .line 3
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    if-eqz v2, :cond_5

    .line 5
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/16 v1, 0x800

    if-lt v0, v1, :cond_4

    .line 7
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzb:Ljava/math/BigInteger;

    .line 8
    sget-object v1, Lcom/google/android/recaptcha/internal/zzzv;->zza:Ljava/math/BigInteger;

    invoke-virtual {v0, v1}, Ljava/math/BigInteger;->compareTo(Ljava/math/BigInteger;)I

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    :cond_0
    if-ltz v1, :cond_3

    .line 9
    sget-object v1, Lcom/google/android/recaptcha/internal/zzzs;->zze:Ljava/math/BigInteger;

    .line 10
    invoke-virtual {v0, v1}, Ljava/math/BigInteger;->mod(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v1

    sget-object v2, Ljava/math/BigInteger;->ZERO:Ljava/math/BigInteger;

    invoke-virtual {v1, v2}, Ljava/math/BigInteger;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_2

    .line 14
    sget-object v1, Lcom/google/android/recaptcha/internal/zzzs;->zzf:Ljava/math/BigInteger;

    .line 12
    invoke-virtual {v0, v1}, Ljava/math/BigInteger;->compareTo(Ljava/math/BigInteger;)I

    move-result v0

    if-gtz v0, :cond_1

    .line 8
    :goto_0
    new-instance v1, Lcom/google/android/recaptcha/internal/zzzv;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzs;->zza:Ljava/lang/Integer;

    .line 13
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v2

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzb:Ljava/math/BigInteger;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzzs;->zzc:Lcom/google/android/recaptcha/internal/zzzt;

    const/4 v6, 0x0

    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzzv;-><init>(ILjava/math/BigInteger;Lcom/google/android/recaptcha/internal/zzzu;Lcom/google/android/recaptcha/internal/zzzt;[B)V

    return-object v1

    .line 14
    :cond_1
    new-instance p0, Ljava/security/InvalidAlgorithmParameterException;

    const-string v0, "Public exponent cannot be larger than 2^256."

    invoke-direct {p0, v0}, Ljava/security/InvalidAlgorithmParameterException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 11
    :cond_2
    new-instance p0, Ljava/security/InvalidAlgorithmParameterException;

    const-string v0, "Invalid public exponent"

    invoke-direct {p0, v0}, Ljava/security/InvalidAlgorithmParameterException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 9
    :cond_3
    new-instance p0, Ljava/security/InvalidAlgorithmParameterException;

    const-string v0, "Public exponent must be at least 65537."

    invoke-direct {p0, v0}, Ljava/security/InvalidAlgorithmParameterException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 6
    :cond_4
    new-instance v0, Ljava/security/InvalidAlgorithmParameterException;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzzs;->zza:Ljava/lang/Integer;

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    const-string v1, "Invalid key size in bytes %d; must be at least 2048 bits"

    .line 7
    invoke-static {v1, p0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/security/InvalidAlgorithmParameterException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 4
    :cond_5
    const-string/jumbo p0, "variant is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 3
    :cond_6
    const-string p0, "hash type is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 2
    :cond_7
    const-string p0, "publicExponent is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 1
    :cond_8
    const-string p0, "key size is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method
