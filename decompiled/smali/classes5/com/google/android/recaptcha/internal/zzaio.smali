.class public abstract Lcom/google/android/recaptcha/internal/zzaio;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafe;->zzc()Lcom/google/android/recaptcha/internal/zzafd;

    move-result-object v0

    const-wide v1, -0x4979cb9e00L

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzafd;->zza(J)Lcom/google/android/recaptcha/internal/zzafd;

    const v1, -0x3b9ac9ff

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafd;->zzb(I)Lcom/google/android/recaptcha/internal/zzafd;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafe;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafe;->zzc()Lcom/google/android/recaptcha/internal/zzafd;

    move-result-object v0

    const-wide v1, 0x4979cb9e00L

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzafd;->zza(J)Lcom/google/android/recaptcha/internal/zzafd;

    const v1, 0x3b9ac9ff

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafd;->zzb(I)Lcom/google/android/recaptcha/internal/zzafd;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafe;

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafe;->zzc()Lcom/google/android/recaptcha/internal/zzafd;

    move-result-object v0

    const-wide/16 v1, 0x0

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzafd;->zza(J)Lcom/google/android/recaptcha/internal/zzafd;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafd;->zzb(I)Lcom/google/android/recaptcha/internal/zzafd;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafe;

    return-void
.end method

.method public static zza(Lcom/google/android/recaptcha/internal/zzafe;)Lcom/google/android/recaptcha/internal/zzafe;
    .locals 8

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafe;->zza()J

    move-result-wide v0

    const-wide v2, -0x4979cb9e00L

    cmp-long v2, v0, v2

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafe;->zzb()I

    move-result v3

    if-ltz v2, :cond_2

    const-wide v4, 0x4979cb9e00L

    cmp-long v2, v0, v4

    if-gtz v2, :cond_2

    int-to-long v4, v3

    const-wide/32 v6, -0x3b9ac9ff

    cmp-long v2, v4, v6

    if-ltz v2, :cond_2

    const v2, 0x3b9aca00

    if-ge v3, v2, :cond_2

    const-wide/16 v4, 0x0

    cmp-long v2, v0, v4

    if-ltz v2, :cond_0

    if-gez v3, :cond_1

    :cond_0
    if-gtz v2, :cond_2

    if-gtz v3, :cond_2

    :cond_1
    return-object p0

    :cond_2
    new-instance p0, Ljava/lang/IllegalArgumentException;

    .line 3
    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v2

    invoke-static {v3}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v4

    add-int/lit16 v2, v2, 0x87

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v4

    add-int/2addr v2, v4

    add-int/lit8 v2, v2, 0x59

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v2}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v2, "Duration is not valid. See proto definition for valid values. Seconds ("

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ") must be in range [-315,576,000,000, +315,576,000,000]. Nanos ("

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ") must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds"

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzafe;)J
    .locals 4

    .line 1
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaio;->zza(Lcom/google/android/recaptcha/internal/zzafe;)Lcom/google/android/recaptcha/internal/zzafe;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafe;->zza()J

    move-result-wide v0

    const-wide/16 v2, 0x3e8

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->multiplyExact(JJ)J

    move-result-wide v0

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafe;->zzb()I

    move-result p0

    const v2, 0xf4240

    div-int/2addr p0, v2

    int-to-long v2, p0

    .line 4
    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->addExact(JJ)J

    move-result-wide v0

    return-wide v0
.end method

.method public static zzc(JI)Lcom/google/android/recaptcha/internal/zzafe;
    .locals 6

    const v0, 0x3b9aca00

    const v1, -0x3b9aca00

    if-le p2, v1, :cond_0

    if-lt p2, v0, :cond_1

    .line 1
    :cond_0
    div-int v2, p2, v0

    int-to-long v2, v2

    invoke-static {p0, p1, v2, v3}, Ljava/lang/Math;->addExact(JJ)J

    move-result-wide p0

    rem-int/2addr p2, v0

    :cond_1
    const-wide/16 v2, 0x0

    cmp-long v4, p0, v2

    if-lez v4, :cond_2

    if-gez p2, :cond_2

    add-int/2addr p2, v0

    const-wide/16 v4, -0x1

    add-long/2addr p0, v4

    :cond_2
    cmp-long v0, p0, v2

    if-gez v0, :cond_3

    if-lez p2, :cond_3

    add-int/2addr p2, v1

    const-wide/16 v0, 0x1

    add-long/2addr p0, v0

    .line 2
    :cond_3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafe;->zzc()Lcom/google/android/recaptcha/internal/zzafd;

    move-result-object v0

    invoke-virtual {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzafd;->zza(J)Lcom/google/android/recaptcha/internal/zzafd;

    invoke-virtual {v0, p2}, Lcom/google/android/recaptcha/internal/zzafd;->zzb(I)Lcom/google/android/recaptcha/internal/zzafd;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzafe;

    .line 3
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaio;->zza(Lcom/google/android/recaptcha/internal/zzafe;)Lcom/google/android/recaptcha/internal/zzafe;

    return-object p0
.end method
