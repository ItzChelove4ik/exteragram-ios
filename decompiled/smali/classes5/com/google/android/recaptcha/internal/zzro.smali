.class final Lcom/google/android/recaptcha/internal/zzro;
.super Lcom/google/android/recaptcha/internal/zzqw;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/security/MessageDigest;

.field private final zzb:I

.field private zzc:Z


# direct methods
.method public synthetic constructor <init>(Ljava/security/MessageDigest;I[B)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzqw;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzro;->zza:Ljava/security/MessageDigest;

    iput p2, p0, Lcom/google/android/recaptcha/internal/zzro;->zzb:I

    return-void
.end method

.method private final zze()V
    .locals 1

    .line 1
    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzro;->zzc:Z

    xor-int/lit8 p0, p0, 0x1

    const-string v0, "Cannot re-use a Hasher after calling hash() on it"

    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzoq;->zzc(ZLjava/lang/Object;)V

    return-void
.end method


# virtual methods
.method public final zza([BII)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzro;->zze()V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzro;->zza:Ljava/security/MessageDigest;

    const/4 p2, 0x0

    .line 2
    invoke-virtual {p0, p1, p2, p3}, Ljava/security/MessageDigest;->update([BII)V

    return-void
.end method

.method public final zzf()Lcom/google/android/recaptcha/internal/zzre;
    .locals 2

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzro;->zze()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/google/android/recaptcha/internal/zzro;->zzc:Z

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzro;->zzb:I

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzro;->zza:Ljava/security/MessageDigest;

    .line 2
    invoke-virtual {p0}, Ljava/security/MessageDigest;->getDigestLength()I

    move-result v1

    if-ne v0, v1, :cond_0

    .line 3
    invoke-virtual {p0}, Ljava/security/MessageDigest;->digest()[B

    move-result-object p0

    sget v0, Lcom/google/android/recaptcha/internal/zzre;->$r8$clinit:I

    .line 4
    new-instance v0, Lcom/google/android/recaptcha/internal/zzrc;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzrc;-><init>([B)V

    return-object v0

    .line 5
    :cond_0
    invoke-virtual {p0}, Ljava/security/MessageDigest;->digest()[B

    move-result-object p0

    invoke-static {p0, v0}, Ljava/util/Arrays;->copyOf([BI)[B

    move-result-object p0

    sget v0, Lcom/google/android/recaptcha/internal/zzre;->$r8$clinit:I

    .line 6
    new-instance v0, Lcom/google/android/recaptcha/internal/zzrc;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzrc;-><init>([B)V

    return-object v0
.end method
