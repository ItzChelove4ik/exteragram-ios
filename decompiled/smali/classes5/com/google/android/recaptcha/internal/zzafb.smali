.class final Lcom/google/android/recaptcha/internal/zzafb;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzafa;


# direct methods
.method private constructor <init>(Lcom/google/android/recaptcha/internal/zzafa;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    iput-object p0, p1, Lcom/google/android/recaptcha/internal/zzafa;->zza:Ljava/lang/Object;

    return-void
.end method

.method public static zza(Lcom/google/android/recaptcha/internal/zzafa;)Lcom/google/android/recaptcha/internal/zzafb;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafa;->zza:Ljava/lang/Object;

    if-eqz v0, :cond_0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafb;

    return-object v0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzafb;

    .line 2
    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzafb;-><init>(Lcom/google/android/recaptcha/internal/zzafa;)V

    return-object v0
.end method


# virtual methods
.method public final zzA(ILjava/util/List;Z)V
    .locals 3

    .line 9
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzagq;

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    check-cast p2, Lcom/google/android/recaptcha/internal/zzagq;

    if-eqz p3, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 11
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    :goto_0
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    .line 12
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    add-int/lit8 p3, p3, 0x8

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 13
    :cond_0
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    :goto_1
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 14
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzq(J)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_1
    :goto_2
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 10
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    invoke-virtual {p3, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzf(IJ)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_2
    if-eqz p3, :cond_4

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 3
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    .line 4
    :goto_3
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_3

    .line 5
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    add-int/lit8 p3, p3, 0x8

    add-int/lit8 p1, p1, 0x1

    goto :goto_3

    .line 6
    :cond_3
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 7
    :goto_4
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 8
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Long;

    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzq(J)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    .line 1
    :cond_4
    :goto_5
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    invoke-virtual {p3, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzf(IJ)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_5

    :cond_5
    return-void
.end method

.method public final zzB(ILjava/util/List;Z)V
    .locals 2

    .line 11
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzafo;

    const/4 v1, 0x2

    if-eqz v0, :cond_1

    invoke-static {p2}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p2, 0x0

    if-eqz p3, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 14
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    throw p2

    .line 18
    :cond_0
    throw p2

    :cond_1
    const/4 v0, 0x0

    if-eqz p3, :cond_3

    .line 13
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 4
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v0

    move p3, p1

    .line 5
    :goto_0
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v1

    if-ge p1, v1, :cond_2

    .line 6
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Float;

    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    add-int/lit8 p3, p3, 0x4

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 7
    :cond_2
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 8
    :goto_1
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v0, p1, :cond_4

    .line 9
    invoke-interface {p2, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Float;

    invoke-virtual {p1}, Ljava/lang/Float;->floatValue()F

    move-result p1

    .line 10
    invoke-static {p1}, Ljava/lang/Float;->floatToRawIntBits(F)I

    move-result p1

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzo(I)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 1
    :cond_3
    :goto_2
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v0, p3, :cond_4

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Float;

    invoke-virtual {v1}, Ljava/lang/Float;->floatValue()F

    move-result v1

    .line 3
    invoke-static {v1}, Ljava/lang/Float;->floatToRawIntBits(F)I

    move-result v1

    invoke-virtual {p3, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzd(II)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    :cond_4
    return-void
.end method

.method public final zzC(ILjava/util/List;Z)V
    .locals 3

    .line 11
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzafc;

    const/4 v1, 0x2

    if-eqz v0, :cond_1

    invoke-static {p2}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p2, 0x0

    if-eqz p3, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 14
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    throw p2

    .line 18
    :cond_0
    throw p2

    :cond_1
    const/4 v0, 0x0

    if-eqz p3, :cond_3

    .line 13
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 4
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v0

    move p3, p1

    .line 5
    :goto_0
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v1

    if-ge p1, v1, :cond_2

    .line 6
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Double;

    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    add-int/lit8 p3, p3, 0x8

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 7
    :cond_2
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 8
    :goto_1
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v0, p1, :cond_4

    .line 9
    invoke-interface {p2, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Double;

    invoke-virtual {p1}, Ljava/lang/Double;->doubleValue()D

    move-result-wide v1

    .line 10
    invoke-static {v1, v2}, Ljava/lang/Double;->doubleToRawLongBits(D)J

    move-result-wide v1

    invoke-virtual {p0, v1, v2}, Lcom/google/android/recaptcha/internal/zzafa;->zzq(J)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 1
    :cond_3
    :goto_2
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v0, p3, :cond_4

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Double;

    invoke-virtual {v1}, Ljava/lang/Double;->doubleValue()D

    move-result-wide v1

    .line 3
    invoke-static {v1, v2}, Ljava/lang/Double;->doubleToRawLongBits(D)J

    move-result-wide v1

    invoke-virtual {p3, p1, v1, v2}, Lcom/google/android/recaptcha/internal/zzafa;->zzf(IJ)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    :cond_4
    return-void
.end method

.method public final zzD(ILjava/util/List;Z)V
    .locals 3

    .line 12
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzafw;

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    check-cast p2, Lcom/google/android/recaptcha/internal/zzafw;

    if-eqz p3, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 15
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    :goto_0
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    .line 16
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result v0

    int-to-long v0, v0

    .line 17
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 18
    :cond_0
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    :goto_1
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 19
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result p1

    .line 20
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzm(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_1
    :goto_2
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 13
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result v0

    .line 14
    invoke-virtual {p3, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzb(II)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_2
    if-eqz p3, :cond_4

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 4
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    .line 5
    :goto_3
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_3

    .line 6
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    int-to-long v0, v0

    .line 7
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_3

    .line 8
    :cond_3
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 9
    :goto_4
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 10
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    .line 11
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzm(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    .line 1
    :cond_4
    :goto_5
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    .line 3
    invoke-virtual {p3, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzb(II)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_5

    :cond_5
    return-void
.end method

.method public final zzE(ILjava/util/List;Z)V
    .locals 2

    .line 10
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzaei;

    const/4 v1, 0x2

    if-eqz v0, :cond_1

    invoke-static {p2}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p2, 0x0

    if-eqz p3, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 12
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    throw p2

    .line 16
    :cond_0
    throw p2

    :cond_1
    const/4 v0, 0x0

    if-eqz p3, :cond_3

    .line 11
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 3
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v0

    move p3, p1

    .line 4
    :goto_0
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v1

    if-ge p1, v1, :cond_2

    .line 5
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Boolean;

    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    add-int/lit8 p3, p3, 0x1

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 6
    :cond_2
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 7
    :goto_1
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v0, p1, :cond_4

    .line 8
    invoke-interface {p2, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    .line 9
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzl(B)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 1
    :cond_3
    :goto_2
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v0, p3, :cond_4

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Boolean;

    invoke-virtual {v1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    invoke-virtual {p3, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzg(IZ)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    :cond_4
    return-void
.end method

.method public final zzF(ILjava/util/List;)V
    .locals 5

    .line 3
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzagn;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    move-object v0, p2

    check-cast v0, Lcom/google/android/recaptcha/internal/zzagn;

    .line 4
    :goto_0
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v2

    if-ge v1, v2, :cond_2

    .line 5
    invoke-interface {v0}, Lcom/google/android/recaptcha/internal/zzagn;->zzc()Ljava/lang/Object;

    move-result-object v2

    .line 6
    instance-of v3, v2, Ljava/lang/String;

    .line 7
    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    if-eqz v3, :cond_0

    check-cast v2, Ljava/lang/String;

    invoke-virtual {v4, p1, v2}, Lcom/google/android/recaptcha/internal/zzafa;->zzh(ILjava/lang/String;)V

    goto :goto_1

    .line 8
    :cond_0
    check-cast v2, Lcom/google/android/recaptcha/internal/zzaer;

    invoke-virtual {v4, p1, v2}, Lcom/google/android/recaptcha/internal/zzafa;->zzi(ILcom/google/android/recaptcha/internal/zzaer;)V

    :goto_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 1
    :cond_1
    :goto_2
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge v1, v0, :cond_2

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-virtual {v0, p1, v2}, Lcom/google/android/recaptcha/internal/zzafa;->zzh(ILjava/lang/String;)V

    add-int/lit8 v1, v1, 0x1

    goto :goto_2

    :cond_2
    return-void
.end method

.method public final zzG(ILjava/util/List;)V
    .locals 3

    const/4 v0, 0x0

    .line 1
    :goto_0
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v1

    if-ge v0, v1, :cond_0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzaer;

    invoke-virtual {v1, p1, v2}, Lcom/google/android/recaptcha/internal/zzafa;->zzi(ILcom/google/android/recaptcha/internal/zzaer;)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method public final zzH(ILjava/util/List;Z)V
    .locals 3

    .line 9
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzafw;

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    check-cast p2, Lcom/google/android/recaptcha/internal/zzafw;

    if-eqz p3, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 11
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    :goto_0
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    .line 12
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 13
    :cond_0
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    :goto_1
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 14
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result p1

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_1
    :goto_2
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 10
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result v0

    invoke-virtual {p3, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzc(II)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_2
    if-eqz p3, :cond_4

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 3
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    .line 4
    :goto_3
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_3

    .line 5
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_3

    .line 6
    :cond_3
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 7
    :goto_4
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 8
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    .line 1
    :cond_4
    :goto_5
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-virtual {p3, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzc(II)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_5

    :cond_5
    return-void
.end method

.method public final zzI(ILjava/util/List;Z)V
    .locals 3

    .line 11
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzafw;

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    check-cast p2, Lcom/google/android/recaptcha/internal/zzafw;

    if-eqz p3, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 14
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    :goto_0
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    .line 15
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    add-int/lit8 p3, p3, 0x4

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 16
    :cond_0
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    :goto_1
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 17
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result p1

    .line 18
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzo(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_1
    :goto_2
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 12
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result v0

    .line 13
    invoke-virtual {p3, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzd(II)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_2
    if-eqz p3, :cond_4

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 4
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    .line 5
    :goto_3
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_3

    .line 6
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    add-int/lit8 p3, p3, 0x4

    add-int/lit8 p1, p1, 0x1

    goto :goto_3

    .line 7
    :cond_3
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 8
    :goto_4
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 9
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    .line 10
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzo(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    .line 1
    :cond_4
    :goto_5
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    .line 3
    invoke-virtual {p3, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzd(II)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_5

    :cond_5
    return-void
.end method

.method public final zzJ(ILjava/util/List;Z)V
    .locals 3

    .line 11
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzagq;

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    check-cast p2, Lcom/google/android/recaptcha/internal/zzagq;

    if-eqz p3, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 14
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    :goto_0
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    .line 15
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    add-int/lit8 p3, p3, 0x8

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 16
    :cond_0
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    :goto_1
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 17
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    .line 18
    invoke-virtual {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzq(J)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_1
    :goto_2
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 12
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    .line 13
    invoke-virtual {p3, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzf(IJ)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_2
    if-eqz p3, :cond_4

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 4
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    .line 5
    :goto_3
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_3

    .line 6
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    add-int/lit8 p3, p3, 0x8

    add-int/lit8 p1, p1, 0x1

    goto :goto_3

    .line 7
    :cond_3
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 8
    :goto_4
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 9
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Long;

    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    .line 10
    invoke-virtual {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzq(J)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    .line 1
    :cond_4
    :goto_5
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    .line 3
    invoke-virtual {p3, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzf(IJ)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_5

    :cond_5
    return-void
.end method

.method public final zzK(ILjava/util/List;Z)V
    .locals 3

    .line 12
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzafw;

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    check-cast p2, Lcom/google/android/recaptcha/internal/zzafw;

    if-eqz p3, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 15
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    :goto_0
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    .line 16
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result v0

    add-int v1, v0, v0

    shr-int/lit8 v0, v0, 0x1f

    xor-int/2addr v0, v1

    .line 17
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 18
    :cond_0
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    :goto_1
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 19
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result p1

    add-int p3, p1, p1

    shr-int/lit8 p1, p1, 0x1f

    xor-int/2addr p1, p3

    .line 20
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_1
    :goto_2
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 13
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result v0

    add-int v1, v0, v0

    shr-int/lit8 v0, v0, 0x1f

    xor-int/2addr v0, v1

    .line 14
    invoke-virtual {p3, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzc(II)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_2
    if-eqz p3, :cond_4

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 4
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    .line 5
    :goto_3
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_3

    .line 6
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    add-int v1, v0, v0

    shr-int/lit8 v0, v0, 0x1f

    xor-int/2addr v0, v1

    .line 7
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_3

    .line 8
    :cond_3
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 9
    :goto_4
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 10
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    add-int p3, p1, p1

    shr-int/lit8 p1, p1, 0x1f

    xor-int/2addr p1, p3

    .line 11
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    .line 1
    :cond_4
    :goto_5
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    add-int v1, v0, v0

    shr-int/lit8 v0, v0, 0x1f

    xor-int/2addr v0, v1

    .line 3
    invoke-virtual {p3, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzc(II)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_5

    :cond_5
    return-void
.end method

.method public final zzL(ILjava/util/List;Z)V
    .locals 6

    .line 12
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzagq;

    const/4 v1, 0x2

    const/16 v2, 0x3f

    const/4 v3, 0x0

    if-eqz v0, :cond_2

    check-cast p2, Lcom/google/android/recaptcha/internal/zzagq;

    if-eqz p3, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 15
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v3

    move p3, p1

    :goto_0
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    .line 16
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    add-long v4, v0, v0

    shr-long/2addr v0, v2

    xor-long/2addr v0, v4

    .line 17
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 18
    :cond_0
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    :goto_1
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result p1

    if-ge v3, p1, :cond_5

    .line 19
    invoke-virtual {p2, v3}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    add-long v4, v0, v0

    shr-long/2addr v0, v2

    xor-long/2addr v0, v4

    .line 20
    invoke-virtual {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzp(J)V

    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    :cond_1
    :goto_2
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result p3

    if-ge v3, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 13
    invoke-virtual {p2, v3}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    add-long v4, v0, v0

    shr-long/2addr v0, v2

    xor-long/2addr v0, v4

    .line 14
    invoke-virtual {p3, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zze(IJ)V

    add-int/lit8 v3, v3, 0x1

    goto :goto_2

    :cond_2
    if-eqz p3, :cond_4

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 4
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v3

    move p3, p1

    .line 5
    :goto_3
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_3

    .line 6
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    add-long v4, v0, v0

    shr-long/2addr v0, v2

    xor-long/2addr v0, v4

    .line 7
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_3

    .line 8
    :cond_3
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 9
    :goto_4
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v3, p1, :cond_5

    .line 10
    invoke-interface {p2, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Long;

    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    add-long v4, v0, v0

    shr-long/2addr v0, v2

    xor-long/2addr v0, v4

    .line 11
    invoke-virtual {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzp(J)V

    add-int/lit8 v3, v3, 0x1

    goto :goto_4

    .line 1
    :cond_4
    :goto_5
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v3, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    add-long v4, v0, v0

    shr-long/2addr v0, v2

    xor-long/2addr v0, v4

    .line 3
    invoke-virtual {p3, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zze(IJ)V

    add-int/lit8 v3, v3, 0x1

    goto :goto_5

    :cond_5
    return-void
.end method

.method public final zzb(II)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzafa;->zzd(II)V

    return-void
.end method

.method public final zzc(IJ)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zze(IJ)V

    return-void
.end method

.method public final zzd(IJ)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzf(IJ)V

    return-void
.end method

.method public final zze(IF)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-static {p2}, Ljava/lang/Float;->floatToRawIntBits(F)I

    move-result p2

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzafa;->zzd(II)V

    return-void
.end method

.method public final zzf(ID)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-static {p2, p3}, Ljava/lang/Double;->doubleToRawLongBits(D)J

    move-result-wide p2

    invoke-virtual {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzf(IJ)V

    return-void
.end method

.method public final zzg(II)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzafa;->zzb(II)V

    return-void
.end method

.method public final zzh(IJ)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zze(IJ)V

    return-void
.end method

.method public final zzi(II)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzafa;->zzb(II)V

    return-void
.end method

.method public final zzj(IJ)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzf(IJ)V

    return-void
.end method

.method public final zzk(II)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzafa;->zzd(II)V

    return-void
.end method

.method public final zzl(IZ)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzafa;->zzg(IZ)V

    return-void
.end method

.method public final zzm(ILjava/lang/String;)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzafa;->zzh(ILjava/lang/String;)V

    return-void
.end method

.method public final zzn(ILcom/google/android/recaptcha/internal/zzaer;)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzafa;->zzi(ILcom/google/android/recaptcha/internal/zzaer;)V

    return-void
.end method

.method public final zzo(II)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzafa;->zzc(II)V

    return-void
.end method

.method public final zzp(II)V
    .locals 1

    add-int v0, p2, p2

    shr-int/lit8 p2, p2, 0x1f

    xor-int/2addr p2, v0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzafa;->zzc(II)V

    return-void
.end method

.method public final zzq(IJ)V
    .locals 3

    add-long v0, p2, p2

    const/16 v2, 0x3f

    shr-long/2addr p2, v2

    xor-long/2addr p2, v0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    invoke-virtual {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zze(IJ)V

    return-void
.end method

.method public final zzr(ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;)V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    check-cast p2, Lcom/google/android/recaptcha/internal/zzaec;

    const/4 v1, 0x2

    .line 2
    invoke-virtual {v0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    .line 3
    invoke-virtual {p2, p3}, Lcom/google/android/recaptcha/internal/zzaec;->zzx(Lcom/google/android/recaptcha/internal/zzahn;)I

    move-result p1

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 4
    invoke-interface {p3, p2, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zzf(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzafb;)V

    return-void
.end method

.method public final zzs(ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;)V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    check-cast p2, Lcom/google/android/recaptcha/internal/zzaec;

    const/4 v1, 0x3

    .line 2
    invoke-virtual {v0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    .line 3
    invoke-interface {p3, p2, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zzf(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzafb;)V

    const/4 p0, 0x4

    .line 4
    invoke-virtual {v0, p1, p0}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    return-void
.end method

.method public final zzt(I)V
    .locals 1
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    const/4 v0, 0x3

    invoke-virtual {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    return-void
.end method

.method public final zzu(I)V
    .locals 1
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    const/4 v0, 0x4

    invoke-virtual {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    return-void
.end method

.method public final zzw(ILjava/util/List;Z)V
    .locals 3

    .line 10
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzafw;

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    check-cast p2, Lcom/google/android/recaptcha/internal/zzafw;

    if-eqz p3, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 12
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    :goto_0
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    .line 13
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result v0

    int-to-long v0, v0

    .line 14
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 15
    :cond_0
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    :goto_1
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 16
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result p1

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzm(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_1
    :goto_2
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 11
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result v0

    invoke-virtual {p3, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzb(II)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_2
    if-eqz p3, :cond_4

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 3
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    .line 4
    :goto_3
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_3

    .line 5
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    int-to-long v0, v0

    .line 6
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_3

    .line 7
    :cond_3
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 8
    :goto_4
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 9
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzm(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    .line 1
    :cond_4
    :goto_5
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-virtual {p3, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzb(II)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_5

    :cond_5
    return-void
.end method

.method public final zzx(ILjava/util/List;Z)V
    .locals 3

    .line 9
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzafw;

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    check-cast p2, Lcom/google/android/recaptcha/internal/zzafw;

    if-eqz p3, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 11
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    :goto_0
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    .line 12
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    add-int/lit8 p3, p3, 0x4

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 13
    :cond_0
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    :goto_1
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 14
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result p1

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzo(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_1
    :goto_2
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 10
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzf(I)I

    move-result v0

    invoke-virtual {p3, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzd(II)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_2
    if-eqz p3, :cond_4

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 3
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    .line 4
    :goto_3
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_3

    .line 5
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    add-int/lit8 p3, p3, 0x4

    add-int/lit8 p1, p1, 0x1

    goto :goto_3

    .line 6
    :cond_3
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 7
    :goto_4
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 8
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzafa;->zzo(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    .line 1
    :cond_4
    :goto_5
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-virtual {p3, p1, v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzd(II)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_5

    :cond_5
    return-void
.end method

.method public final zzy(ILjava/util/List;Z)V
    .locals 3

    .line 12
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzagq;

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    check-cast p2, Lcom/google/android/recaptcha/internal/zzagq;

    if-eqz p3, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 15
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    :goto_0
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    .line 16
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    .line 17
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 18
    :cond_0
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    :goto_1
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 19
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    .line 20
    invoke-virtual {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzp(J)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_1
    :goto_2
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 13
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    .line 14
    invoke-virtual {p3, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zze(IJ)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_2
    if-eqz p3, :cond_4

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 4
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    .line 5
    :goto_3
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_3

    .line 6
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    .line 7
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_3

    .line 8
    :cond_3
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 9
    :goto_4
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 10
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Long;

    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    .line 11
    invoke-virtual {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzp(J)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    .line 1
    :cond_4
    :goto_5
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    .line 3
    invoke-virtual {p3, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zze(IJ)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_5

    :cond_5
    return-void
.end method

.method public final zzz(ILjava/util/List;Z)V
    .locals 3

    .line 9
    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzagq;

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    check-cast p2, Lcom/google/android/recaptcha/internal/zzagq;

    if-eqz p3, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 11
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    :goto_0
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    .line 12
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 13
    :cond_0
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    :goto_1
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 14
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzp(J)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_1
    :goto_2
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 10
    invoke-virtual {p2, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zze(I)J

    move-result-wide v0

    invoke-virtual {p3, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zze(IJ)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_2
    if-eqz p3, :cond_4

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 3
    invoke-virtual {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zza(II)V

    move p1, v2

    move p3, p1

    .line 4
    :goto_3
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_3

    .line 5
    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v0

    add-int/2addr p3, v0

    add-int/lit8 p1, p1, 0x1

    goto :goto_3

    .line 6
    :cond_3
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzafa;->zzn(I)V

    .line 7
    :goto_4
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p1

    if-ge v2, p1, :cond_5

    .line 8
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Long;

    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzp(J)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    .line 1
    :cond_4
    :goto_5
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p3

    if-ge v2, p3, :cond_5

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzafb;->zza:Lcom/google/android/recaptcha/internal/zzafa;

    .line 2
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    invoke-virtual {p3, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzafa;->zze(IJ)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_5

    :cond_5
    return-void
.end method
