.class final Lcom/google/android/recaptcha/internal/zzrd;
.super Lcom/google/android/recaptcha/internal/zzre;
.source "SourceFile"

# interfaces
.implements Ljava/io/Serializable;


# instance fields
.field final zza:J


# direct methods
.method public constructor <init>(J)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzre;-><init>()V

    iput-wide p1, p0, Lcom/google/android/recaptcha/internal/zzrd;->zza:J

    return-void
.end method


# virtual methods
.method public final zza()I
    .locals 0

    const/16 p0, 0x40

    return p0
.end method

.method public final zzb()[B
    .locals 10

    iget-wide v0, p0, Lcom/google/android/recaptcha/internal/zzrd;->zza:J

    long-to-int p0, v0

    int-to-byte p0, p0

    const/16 v2, 0x8

    shr-long v3, v0, v2

    long-to-int v3, v3

    int-to-byte v3, v3

    const/16 v4, 0x10

    shr-long v4, v0, v4

    long-to-int v4, v4

    int-to-byte v4, v4

    const/16 v5, 0x18

    shr-long v5, v0, v5

    long-to-int v5, v5

    int-to-byte v5, v5

    const/16 v6, 0x20

    shr-long v6, v0, v6

    long-to-int v6, v6

    int-to-byte v6, v6

    const/16 v7, 0x28

    shr-long v7, v0, v7

    long-to-int v7, v7

    int-to-byte v7, v7

    const/16 v8, 0x30

    shr-long v8, v0, v8

    long-to-int v8, v8

    int-to-byte v8, v8

    const/16 v9, 0x38

    shr-long/2addr v0, v9

    long-to-int v0, v0

    int-to-byte v0, v0

    new-array v1, v2, [B

    const/4 v2, 0x0

    aput-byte p0, v1, v2

    const/4 p0, 0x1

    aput-byte v3, v1, p0

    const/4 p0, 0x2

    aput-byte v4, v1, p0

    const/4 p0, 0x3

    aput-byte v5, v1, p0

    const/4 p0, 0x4

    aput-byte v6, v1, p0

    const/4 p0, 0x5

    aput-byte v7, v1, p0

    const/4 p0, 0x6

    aput-byte v8, v1, p0

    const/4 p0, 0x7

    aput-byte v0, v1, p0

    return-object v1
.end method

.method public final zzc()I
    .locals 2

    iget-wide v0, p0, Lcom/google/android/recaptcha/internal/zzrd;->zza:J

    long-to-int p0, v0

    return p0
.end method

.method public final zzd()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/recaptcha/internal/zzrd;->zza:J

    return-wide v0
.end method

.method public final zzf(Lcom/google/android/recaptcha/internal/zzre;)Z
    .locals 2

    .line 1
    iget-wide v0, p0, Lcom/google/android/recaptcha/internal/zzrd;->zza:J

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzre;->zzd()J

    move-result-wide p0

    cmp-long p0, v0, p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
