.class public final Lcom/google/android/recaptcha/internal/zzxy;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private zza:Lcom/google/android/recaptcha/internal/zzyb;

.field private zzb:Lcom/google/android/recaptcha/internal/zzadz;


# direct methods
.method public synthetic constructor <init>([B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxy;->zza:Lcom/google/android/recaptcha/internal/zzyb;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxy;->zzb:Lcom/google/android/recaptcha/internal/zzadz;

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzyb;)Lcom/google/android/recaptcha/internal/zzxy;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxy;->zza:Lcom/google/android/recaptcha/internal/zzyb;

    return-object p0
.end method

.method public final zzb(Lcom/google/android/recaptcha/internal/zzadz;)Lcom/google/android/recaptcha/internal/zzxy;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxy;->zzb:Lcom/google/android/recaptcha/internal/zzadz;

    return-object p0
.end method

.method public final zzc()Lcom/google/android/recaptcha/internal/zzxz;
    .locals 7

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzxy;->zza:Lcom/google/android/recaptcha/internal/zzyb;

    const/4 v1, 0x0

    if-eqz v0, :cond_3

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzxy;->zzb:Lcom/google/android/recaptcha/internal/zzadz;

    if-eqz v2, :cond_2

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsd;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v3

    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzadz;->zzb(Lcom/google/android/recaptcha/internal/zzsy;)Ljava/math/BigInteger;

    move-result-object v2

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzyb;->zzd()Ljava/security/spec/ECPoint;

    move-result-object v3

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzyb;->zzf()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object v0

    .line 3
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxx;->zzc()Lcom/google/android/recaptcha/internal/zzxt;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxt;->zza()Ljava/security/spec/ECParameterSpec;

    move-result-object v4

    .line 4
    invoke-virtual {v4}, Ljava/security/spec/ECParameterSpec;->getOrder()Ljava/math/BigInteger;

    move-result-object v4

    .line 5
    invoke-virtual {v2}, Ljava/math/BigInteger;->signum()I

    move-result v5

    const-string v6, "Invalid private value"

    if-lez v5, :cond_1

    invoke-virtual {v2, v4}, Ljava/math/BigInteger;->compareTo(Ljava/math/BigInteger;)I

    move-result v4

    if-gez v4, :cond_1

    .line 9
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxt;->zza()Ljava/security/spec/ECParameterSpec;

    move-result-object v0

    .line 6
    invoke-static {v2, v0}, Lcom/google/android/recaptcha/internal/zztt;->zzd(Ljava/math/BigInteger;Ljava/security/spec/ECParameterSpec;)Ljava/security/spec/ECPoint;

    move-result-object v0

    .line 7
    invoke-virtual {v0, v3}, Ljava/security/spec/ECPoint;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 8
    new-instance v0, Lcom/google/android/recaptcha/internal/zzxz;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzxy;->zza:Lcom/google/android/recaptcha/internal/zzyb;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzxy;->zzb:Lcom/google/android/recaptcha/internal/zzadz;

    invoke-direct {v0, v2, p0, v1}, Lcom/google/android/recaptcha/internal/zzxz;-><init>(Lcom/google/android/recaptcha/internal/zzyb;Lcom/google/android/recaptcha/internal/zzadz;[B)V

    return-object v0

    :cond_0
    invoke-static {v6}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 9
    :cond_1
    invoke-static {v6}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 2
    :cond_2
    const-string p0, "Cannot build without a private value"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 1
    :cond_3
    const-string p0, "Cannot build without a ecdsa public key"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method
