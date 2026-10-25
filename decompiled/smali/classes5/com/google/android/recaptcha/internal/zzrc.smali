.class final Lcom/google/android/recaptcha/internal/zzrc;
.super Lcom/google/android/recaptcha/internal/zzre;
.source "SourceFile"

# interfaces
.implements Ljava/io/Serializable;


# instance fields
.field final zza:[B


# direct methods
.method public constructor <init>([B)V
    .locals 0

    .line 2
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzre;-><init>()V

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 1
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzrc;->zza:[B

    return-void
.end method


# virtual methods
.method public final zza()I
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzrc;->zza:[B

    array-length p0, p0

    mul-int/lit8 p0, p0, 0x8

    return p0
.end method

.method public final zzb()[B
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzrc;->zza:[B

    invoke-virtual {p0}, [B->clone()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, [B

    return-object p0
.end method

.method public final zzc()I
    .locals 5

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzrc;->zza:[B

    array-length v0, p0

    const/4 v1, 0x4

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-lt v0, v1, :cond_0

    move v1, v3

    goto :goto_0

    :cond_0
    move v1, v2

    :goto_0
    const-string v4, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes)."

    invoke-static {v1, v4, v0}, Lcom/google/android/recaptcha/internal/zzoq;->zzd(ZLjava/lang/String;I)V

    .line 2
    aget-byte v0, p0, v2

    and-int/lit16 v0, v0, 0xff

    aget-byte v1, p0, v3

    and-int/lit16 v1, v1, 0xff

    const/4 v2, 0x2

    aget-byte v2, p0, v2

    and-int/lit16 v2, v2, 0xff

    const/4 v3, 0x3

    aget-byte p0, p0, v3

    and-int/lit16 p0, p0, 0xff

    shl-int/lit8 v1, v1, 0x8

    or-int/2addr v0, v1

    shl-int/lit8 v1, v2, 0x10

    or-int/2addr v0, v1

    shl-int/lit8 p0, p0, 0x18

    or-int/2addr p0, v0

    return p0
.end method

.method public final zzd()J
    .locals 8

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzrc;->zza:[B

    array-length v0, p0

    const/4 v1, 0x0

    const/4 v2, 0x1

    const/16 v3, 0x8

    if-lt v0, v3, :cond_0

    move v4, v2

    goto :goto_0

    :cond_0
    move v4, v1

    :goto_0
    const-string v5, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes)."

    invoke-static {v4, v5, v0}, Lcom/google/android/recaptcha/internal/zzoq;->zzd(ZLjava/lang/String;I)V

    .line 2
    aget-byte v0, p0, v1

    and-int/lit16 v0, v0, 0xff

    int-to-long v0, v0

    :goto_1
    array-length v4, p0

    .line 3
    invoke-static {v4, v3}, Ljava/lang/Math;->min(II)I

    move-result v4

    if-ge v2, v4, :cond_1

    .line 4
    aget-byte v4, p0, v2

    int-to-long v4, v4

    const-wide/16 v6, 0xff

    and-long/2addr v4, v6

    mul-int/lit8 v6, v2, 0x8

    shl-long/2addr v4, v6

    or-long/2addr v0, v4

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_1
    return-wide v0
.end method

.method public final zze()[B
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzrc;->zza:[B

    return-object p0
.end method

.method public final zzf(Lcom/google/android/recaptcha/internal/zzre;)Z
    .locals 6

    .line 1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzre;->zze()[B

    move-result-object v0

    array-length v0, v0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzrc;->zza:[B

    array-length v1, p0

    const/4 v2, 0x0

    if-ne v1, v0, :cond_2

    const/4 v0, 0x1

    move v3, v0

    move v1, v2

    :goto_0
    array-length v4, p0

    if-ge v1, v4, :cond_1

    .line 2
    aget-byte v4, p0, v1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzre;->zze()[B

    move-result-object v5

    aget-byte v5, v5, v1

    if-ne v4, v5, :cond_0

    move v4, v0

    goto :goto_1

    :cond_0
    move v4, v2

    :goto_1
    and-int/2addr v3, v4

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    return v3

    :cond_2
    return v2
.end method
