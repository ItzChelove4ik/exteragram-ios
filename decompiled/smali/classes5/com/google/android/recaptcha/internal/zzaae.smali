.class public final Lcom/google/android/recaptcha/internal/zzaae;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zzg:Ljava/math/BigInteger;

.field private static final zzh:Ljava/math/BigInteger;


# instance fields
.field private zza:Ljava/lang/Integer;

.field private zzb:Ljava/math/BigInteger;

.field private zzc:Lcom/google/android/recaptcha/internal/zzaaf;

.field private zzd:Lcom/google/android/recaptcha/internal/zzaaf;

.field private zze:Ljava/lang/Integer;

.field private zzf:Lcom/google/android/recaptcha/internal/zzaag;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const-wide/16 v0, 0x2

    .line 1
    invoke-static {v0, v1}, Ljava/math/BigInteger;->valueOf(J)Ljava/math/BigInteger;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaae;->zzg:Ljava/math/BigInteger;

    const/16 v1, 0x100

    .line 2
    invoke-virtual {v0, v1}, Ljava/math/BigInteger;->pow(I)Ljava/math/BigInteger;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaae;->zzh:Ljava/math/BigInteger;

    return-void
.end method

.method public synthetic constructor <init>([B)V
    .locals 1

    .line 2
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaae;->zza:Ljava/lang/Integer;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaah;->zza:Ljava/math/BigInteger;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzb:Ljava/math/BigInteger;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzc:Lcom/google/android/recaptcha/internal/zzaaf;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzd:Lcom/google/android/recaptcha/internal/zzaaf;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaae;->zze:Ljava/lang/Integer;

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaag;->zzd:Lcom/google/android/recaptcha/internal/zzaag;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzf:Lcom/google/android/recaptcha/internal/zzaag;

    return-void
.end method


# virtual methods
.method public final zza(I)Lcom/google/android/recaptcha/internal/zzaae;
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaae;->zza:Ljava/lang/Integer;

    return-object p0
.end method

.method public final zzb(Ljava/math/BigInteger;)Lcom/google/android/recaptcha/internal/zzaae;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzb:Ljava/math/BigInteger;

    return-object p0
.end method

.method public final zzc(Lcom/google/android/recaptcha/internal/zzaag;)Lcom/google/android/recaptcha/internal/zzaae;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzf:Lcom/google/android/recaptcha/internal/zzaag;

    return-object p0
.end method

.method public final zzd(Lcom/google/android/recaptcha/internal/zzaaf;)Lcom/google/android/recaptcha/internal/zzaae;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzc:Lcom/google/android/recaptcha/internal/zzaaf;

    return-object p0
.end method

.method public final zze(Lcom/google/android/recaptcha/internal/zzaaf;)Lcom/google/android/recaptcha/internal/zzaae;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzd:Lcom/google/android/recaptcha/internal/zzaaf;

    return-object p0
.end method

.method public final zzf(I)Lcom/google/android/recaptcha/internal/zzaae;
    .locals 1

    if-ltz p1, :cond_0

    .line 4
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaae;->zze:Ljava/lang/Integer;

    return-object p0

    .line 1
    :cond_0
    new-instance p0, Ljava/security/GeneralSecurityException;

    .line 2
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const-string v0, "Invalid salt length in bytes %d; salt length must be positive"

    .line 3
    invoke-static {v0, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public final zzg()Lcom/google/android/recaptcha/internal/zzaah;
    .locals 9

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaae;->zza:Ljava/lang/Integer;

    const/4 v1, 0x0

    if-eqz v0, :cond_b

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzb:Ljava/math/BigInteger;

    if-eqz v2, :cond_a

    .line 2
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzc:Lcom/google/android/recaptcha/internal/zzaaf;

    if-eqz v2, :cond_9

    .line 3
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzd:Lcom/google/android/recaptcha/internal/zzaaf;

    if-eqz v2, :cond_8

    .line 4
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzf:Lcom/google/android/recaptcha/internal/zzaag;

    if-eqz v2, :cond_7

    .line 5
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaae;->zze:Ljava/lang/Integer;

    if-eqz v2, :cond_6

    .line 7
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/16 v2, 0x800

    if-lt v0, v2, :cond_5

    .line 10
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzc:Lcom/google/android/recaptcha/internal/zzaaf;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzd:Lcom/google/android/recaptcha/internal/zzaaf;

    if-ne v0, v2, :cond_4

    .line 11
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzb:Ljava/math/BigInteger;

    .line 12
    sget-object v1, Lcom/google/android/recaptcha/internal/zzaah;->zza:Ljava/math/BigInteger;

    invoke-virtual {v0, v1}, Ljava/math/BigInteger;->compareTo(Ljava/math/BigInteger;)I

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    :cond_0
    if-ltz v1, :cond_3

    .line 13
    sget-object v1, Lcom/google/android/recaptcha/internal/zzaae;->zzg:Ljava/math/BigInteger;

    .line 14
    invoke-virtual {v0, v1}, Ljava/math/BigInteger;->mod(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v1

    sget-object v2, Ljava/math/BigInteger;->ZERO:Ljava/math/BigInteger;

    invoke-virtual {v1, v2}, Ljava/math/BigInteger;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_2

    .line 18
    sget-object v1, Lcom/google/android/recaptcha/internal/zzaae;->zzh:Ljava/math/BigInteger;

    .line 16
    invoke-virtual {v0, v1}, Ljava/math/BigInteger;->compareTo(Ljava/math/BigInteger;)I

    move-result v0

    if-gtz v0, :cond_1

    .line 12
    :goto_0
    new-instance v1, Lcom/google/android/recaptcha/internal/zzaah;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaae;->zza:Ljava/lang/Integer;

    .line 17
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v2

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzb:Ljava/math/BigInteger;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzf:Lcom/google/android/recaptcha/internal/zzaag;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzc:Lcom/google/android/recaptcha/internal/zzaaf;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzaae;->zzd:Lcom/google/android/recaptcha/internal/zzaaf;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaae;->zze:Ljava/lang/Integer;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result v7

    const/4 v8, 0x0

    invoke-direct/range {v1 .. v8}, Lcom/google/android/recaptcha/internal/zzaah;-><init>(ILjava/math/BigInteger;Lcom/google/android/recaptcha/internal/zzaag;Lcom/google/android/recaptcha/internal/zzaaf;Lcom/google/android/recaptcha/internal/zzaaf;I[B)V

    return-object v1

    .line 18
    :cond_1
    new-instance p0, Ljava/security/InvalidAlgorithmParameterException;

    const-string v0, "Public exponent cannot be larger than 2^256."

    invoke-direct {p0, v0}, Ljava/security/InvalidAlgorithmParameterException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 15
    :cond_2
    new-instance p0, Ljava/security/InvalidAlgorithmParameterException;

    const-string v0, "Invalid public exponent"

    invoke-direct {p0, v0}, Ljava/security/InvalidAlgorithmParameterException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 13
    :cond_3
    new-instance p0, Ljava/security/InvalidAlgorithmParameterException;

    const-string v0, "Public exponent must be at least 65537."

    invoke-direct {p0, v0}, Ljava/security/InvalidAlgorithmParameterException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 11
    :cond_4
    const-string p0, "MGF1 hash is different from signature hash"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 8
    :cond_5
    new-instance v0, Ljava/security/InvalidAlgorithmParameterException;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaae;->zza:Ljava/lang/Integer;

    .line 9
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    filled-new-array {p0, v1}, [Ljava/lang/Object;

    move-result-object p0

    const-string v1, "Invalid key size in bytes %d; must be at least %d bits"

    .line 10
    invoke-static {v1, p0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/security/InvalidAlgorithmParameterException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 6
    :cond_6
    const-string p0, "salt length is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 5
    :cond_7
    const-string/jumbo p0, "variant is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 4
    :cond_8
    const-string p0, "mgf1 hash type is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 3
    :cond_9
    const-string p0, "signature hash type is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 2
    :cond_a
    const-string p0, "publicExponent is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 1
    :cond_b
    const-string p0, "key size is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method
