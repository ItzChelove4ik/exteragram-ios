.class public final Lcom/google/android/recaptcha/internal/zzaai;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private zza:Lcom/google/android/recaptcha/internal/zzaal;

.field private zzb:Lcom/google/android/recaptcha/internal/zzadz;

.field private zzc:Lcom/google/android/recaptcha/internal/zzadz;

.field private zzd:Lcom/google/android/recaptcha/internal/zzadz;

.field private zze:Lcom/google/android/recaptcha/internal/zzadz;

.field private zzf:Lcom/google/android/recaptcha/internal/zzadz;

.field private zzg:Lcom/google/android/recaptcha/internal/zzadz;


# direct methods
.method public synthetic constructor <init>([B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaai;->zza:Lcom/google/android/recaptcha/internal/zzaal;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzb:Lcom/google/android/recaptcha/internal/zzadz;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzc:Lcom/google/android/recaptcha/internal/zzadz;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzd:Lcom/google/android/recaptcha/internal/zzadz;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaai;->zze:Lcom/google/android/recaptcha/internal/zzadz;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzf:Lcom/google/android/recaptcha/internal/zzadz;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzg:Lcom/google/android/recaptcha/internal/zzadz;

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzaal;)Lcom/google/android/recaptcha/internal/zzaai;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaai;->zza:Lcom/google/android/recaptcha/internal/zzaal;

    return-object p0
.end method

.method public final zzb(Lcom/google/android/recaptcha/internal/zzadz;Lcom/google/android/recaptcha/internal/zzadz;)Lcom/google/android/recaptcha/internal/zzaai;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzc:Lcom/google/android/recaptcha/internal/zzadz;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzd:Lcom/google/android/recaptcha/internal/zzadz;

    return-object p0
.end method

.method public final zzc(Lcom/google/android/recaptcha/internal/zzadz;)Lcom/google/android/recaptcha/internal/zzaai;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzb:Lcom/google/android/recaptcha/internal/zzadz;

    return-object p0
.end method

.method public final zzd(Lcom/google/android/recaptcha/internal/zzadz;Lcom/google/android/recaptcha/internal/zzadz;)Lcom/google/android/recaptcha/internal/zzaai;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaai;->zze:Lcom/google/android/recaptcha/internal/zzadz;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzf:Lcom/google/android/recaptcha/internal/zzadz;

    return-object p0
.end method

.method public final zze(Lcom/google/android/recaptcha/internal/zzadz;)Lcom/google/android/recaptcha/internal/zzaai;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzg:Lcom/google/android/recaptcha/internal/zzadz;

    return-object p0
.end method

.method public final zzf()Lcom/google/android/recaptcha/internal/zzaaj;
    .locals 12

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaai;->zza:Lcom/google/android/recaptcha/internal/zzaal;

    const/4 v1, 0x0

    if-eqz v0, :cond_b

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzc:Lcom/google/android/recaptcha/internal/zzadz;

    if-eqz v2, :cond_a

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzd:Lcom/google/android/recaptcha/internal/zzadz;

    if-eqz v2, :cond_a

    .line 30
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzb:Lcom/google/android/recaptcha/internal/zzadz;

    if-eqz v2, :cond_9

    .line 2
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaai;->zze:Lcom/google/android/recaptcha/internal/zzadz;

    if-eqz v2, :cond_8

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzf:Lcom/google/android/recaptcha/internal/zzadz;

    if-eqz v2, :cond_8

    .line 29
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzg:Lcom/google/android/recaptcha/internal/zzadz;

    if-eqz v2, :cond_7

    .line 3
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaal;->zzf()Lcom/google/android/recaptcha/internal/zzaah;

    move-result-object v0

    .line 4
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaah;->zzc()Ljava/math/BigInteger;

    move-result-object v0

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaai;->zza:Lcom/google/android/recaptcha/internal/zzaal;

    .line 5
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaal;->zzd()Ljava/math/BigInteger;

    move-result-object v2

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzc:Lcom/google/android/recaptcha/internal/zzadz;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v4

    .line 6
    invoke-virtual {v3, v4}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v3

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzd:Lcom/google/android/recaptcha/internal/zzadz;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v5

    .line 7
    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v4

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzb:Lcom/google/android/recaptcha/internal/zzadz;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v6

    .line 8
    invoke-virtual {v5, v6}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v5

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzaai;->zze:Lcom/google/android/recaptcha/internal/zzadz;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v7

    .line 9
    invoke-virtual {v6, v7}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v6

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzf:Lcom/google/android/recaptcha/internal/zzadz;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v8

    .line 10
    invoke-virtual {v7, v8}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v7

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzg:Lcom/google/android/recaptcha/internal/zzadz;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v9

    .line 11
    invoke-virtual {v8, v9}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v8

    const/16 v9, 0xa

    .line 12
    invoke-virtual {v3, v9}, Ljava/math/BigInteger;->isProbablePrime(I)Z

    move-result v10

    if-eqz v10, :cond_6

    .line 14
    invoke-virtual {v4, v9}, Ljava/math/BigInteger;->isProbablePrime(I)Z

    move-result v9

    if-eqz v9, :cond_5

    .line 16
    invoke-virtual {v3, v4}, Ljava/math/BigInteger;->multiply(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v9

    invoke-virtual {v9, v2}, Ljava/math/BigInteger;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_4

    .line 17
    sget-object v2, Ljava/math/BigInteger;->ONE:Ljava/math/BigInteger;

    .line 18
    invoke-virtual {v3, v2}, Ljava/math/BigInteger;->subtract(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v9

    .line 19
    invoke-virtual {v4, v2}, Ljava/math/BigInteger;->subtract(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v10

    .line 20
    invoke-virtual {v9, v10}, Ljava/math/BigInteger;->gcd(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v11

    invoke-virtual {v9, v11}, Ljava/math/BigInteger;->divide(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v11

    invoke-virtual {v11, v10}, Ljava/math/BigInteger;->multiply(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v11

    .line 21
    invoke-virtual {v0, v5}, Ljava/math/BigInteger;->multiply(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v5

    invoke-virtual {v5, v11}, Ljava/math/BigInteger;->mod(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v5

    invoke-virtual {v5, v2}, Ljava/math/BigInteger;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 23
    invoke-virtual {v0, v6}, Ljava/math/BigInteger;->multiply(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v5

    invoke-virtual {v5, v9}, Ljava/math/BigInteger;->mod(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v5

    invoke-virtual {v5, v2}, Ljava/math/BigInteger;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_2

    .line 25
    invoke-virtual {v0, v7}, Ljava/math/BigInteger;->multiply(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v0

    invoke-virtual {v0, v10}, Ljava/math/BigInteger;->mod(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v0

    invoke-virtual {v0, v2}, Ljava/math/BigInteger;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 27
    invoke-virtual {v4, v8}, Ljava/math/BigInteger;->multiply(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v0

    invoke-virtual {v0, v3}, Ljava/math/BigInteger;->mod(Ljava/math/BigInteger;)Ljava/math/BigInteger;

    move-result-object v0

    invoke-virtual {v0, v2}, Ljava/math/BigInteger;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 28
    new-instance v2, Lcom/google/android/recaptcha/internal/zzaaj;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzaai;->zza:Lcom/google/android/recaptcha/internal/zzaal;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzc:Lcom/google/android/recaptcha/internal/zzadz;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzd:Lcom/google/android/recaptcha/internal/zzadz;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzb:Lcom/google/android/recaptcha/internal/zzadz;

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzaai;->zze:Lcom/google/android/recaptcha/internal/zzadz;

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzf:Lcom/google/android/recaptcha/internal/zzadz;

    iget-object v9, p0, Lcom/google/android/recaptcha/internal/zzaai;->zzg:Lcom/google/android/recaptcha/internal/zzadz;

    const/4 v10, 0x0

    invoke-direct/range {v2 .. v10}, Lcom/google/android/recaptcha/internal/zzaaj;-><init>(Lcom/google/android/recaptcha/internal/zzaal;Lcom/google/android/recaptcha/internal/zzadz;Lcom/google/android/recaptcha/internal/zzadz;Lcom/google/android/recaptcha/internal/zzadz;Lcom/google/android/recaptcha/internal/zzadz;Lcom/google/android/recaptcha/internal/zzadz;Lcom/google/android/recaptcha/internal/zzadz;[B)V

    return-object v2

    :cond_0
    const-string p0, "qInv is invalid."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 26
    :cond_1
    const-string p0, "dQ is invalid."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 24
    :cond_2
    const-string p0, "dP is invalid."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 22
    :cond_3
    const-string p0, "D is invalid."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 17
    :cond_4
    const-string p0, "Prime p times prime q is not equal to the public key\'s modulus"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 15
    :cond_5
    const-string p0, "q is not a prime"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 13
    :cond_6
    const-string p0, "p is not a prime"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 3
    :cond_7
    const-string p0, "Cannot build without CRT coefficient"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 29
    :cond_8
    const-string p0, "Cannot build without prime exponents"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 2
    :cond_9
    const-string p0, "Cannot build without private exponent"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 30
    :cond_a
    const-string p0, "Cannot build without prime factors"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 1
    :cond_b
    const-string p0, "Cannot build without a RSA SSA PKCS1 public key"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method
