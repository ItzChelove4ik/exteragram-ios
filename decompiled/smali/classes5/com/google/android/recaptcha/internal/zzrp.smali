.class final Lcom/google/android/recaptcha/internal/zzrp;
.super Lcom/google/android/recaptcha/internal/zzqx;
.source "SourceFile"

# interfaces
.implements Ljava/io/Serializable;


# instance fields
.field private final zza:Ljava/security/MessageDigest;

.field private final zzb:I

.field private final zzc:Z

.field private final zzd:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzqx;-><init>()V

    const-string p1, "SHA-256"

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzrp;->zze(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzrp;->zza:Ljava/security/MessageDigest;

    .line 2
    invoke-virtual {p1}, Ljava/security/MessageDigest;->getDigestLength()I

    move-result p2

    iput p2, p0, Lcom/google/android/recaptcha/internal/zzrp;->zzb:I

    const-string p2, "Hashing.sha256()"

    .line 3
    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzrp;->zzd:Ljava/lang/String;

    .line 4
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzrp;->zzd(Ljava/security/MessageDigest;)Z

    move-result p1

    iput-boolean p1, p0, Lcom/google/android/recaptcha/internal/zzrp;->zzc:Z

    return-void
.end method

.method private static zzd(Ljava/security/MessageDigest;)Z
    .locals 0

    .line 1
    :try_start_0
    invoke-virtual {p0}, Ljava/security/MessageDigest;->clone()Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/CloneNotSupportedException; {:try_start_0 .. :try_end_0} :catch_0

    const/4 p0, 0x1

    return p0

    :catch_0
    const/4 p0, 0x0

    return p0
.end method

.method private static zze(Ljava/lang/String;)Ljava/security/MessageDigest;
    .locals 0

    .line 1
    :try_start_0
    invoke-static {p0}, Ljava/security/MessageDigest;->getInstance(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object p0
    :try_end_0
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    .line 2
    invoke-static {p0}, Lokio/Buffer$$ExternalSyntheticBUOutline2;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    return-object p0
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzrp;->zzd:Ljava/lang/String;

    return-object p0
.end method

.method public final zzc()Lcom/google/android/recaptcha/internal/zzrg;
    .locals 4

    .line 1
    iget-boolean v0, p0, Lcom/google/android/recaptcha/internal/zzrp;->zzc:Z

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    :try_start_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzro;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzrp;->zza:Ljava/security/MessageDigest;

    invoke-virtual {v2}, Ljava/security/MessageDigest;->clone()Ljava/lang/Object;

    move-result-object v2

    .line 2
    check-cast v2, Ljava/security/MessageDigest;

    iget v3, p0, Lcom/google/android/recaptcha/internal/zzrp;->zzb:I

    invoke-direct {v0, v2, v3, v1}, Lcom/google/android/recaptcha/internal/zzro;-><init>(Ljava/security/MessageDigest;I[B)V
    :try_end_0
    .catch Ljava/lang/CloneNotSupportedException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    :cond_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzrp;->zza:Ljava/security/MessageDigest;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzro;

    .line 3
    invoke-virtual {v0}, Ljava/security/MessageDigest;->getAlgorithm()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzrp;->zze(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object v0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzrp;->zzb:I

    invoke-direct {v2, v0, p0, v1}, Lcom/google/android/recaptcha/internal/zzro;-><init>(Ljava/security/MessageDigest;I[B)V

    return-object v2
.end method
