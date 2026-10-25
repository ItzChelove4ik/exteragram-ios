.class final Lcom/google/android/recaptcha/internal/zzahb;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzahn;


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzagx;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzahz;

.field private final zzc:Z

.field private final zzd:Lcom/google/android/recaptcha/internal/zzafi;


# direct methods
.method private constructor <init>(Lcom/google/android/recaptcha/internal/zzahz;Lcom/google/android/recaptcha/internal/zzafi;Lcom/google/android/recaptcha/internal/zzagx;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzahb;->zzb:Lcom/google/android/recaptcha/internal/zzahz;

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/google/android/recaptcha/internal/zzahb;->zzc:Z

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzahb;->zzd:Lcom/google/android/recaptcha/internal/zzafi;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzahb;->zza:Lcom/google/android/recaptcha/internal/zzagx;

    return-void
.end method

.method public static zzh(Lcom/google/android/recaptcha/internal/zzahz;Lcom/google/android/recaptcha/internal/zzafi;Lcom/google/android/recaptcha/internal/zzagx;)Lcom/google/android/recaptcha/internal/zzahb;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzahb;

    invoke-direct {v0, p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzahb;-><init>(Lcom/google/android/recaptcha/internal/zzahz;Lcom/google/android/recaptcha/internal/zzafi;Lcom/google/android/recaptcha/internal/zzagx;)V

    return-object v0
.end method


# virtual methods
.method public final zza()Ljava/lang/Object;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahb;->zza:Lcom/google/android/recaptcha/internal/zzagx;

    instance-of v0, p0, Lcom/google/android/recaptcha/internal/zzafv;

    if-eqz v0, :cond_0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzafv;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzC()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    return-object p0

    .line 2
    :cond_0
    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzagx;->zzX()Lcom/google/android/recaptcha/internal/zzagw;

    move-result-object p0

    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzagw;->zzr()Lcom/google/android/recaptcha/internal/zzagx;

    move-result-object p0

    return-object p0
.end method

.method public final zzb(Ljava/lang/Object;Ljava/lang/Object;)Z
    .locals 1

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 2
    check-cast p2, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object p2, p2, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 3
    invoke-virtual {v0, p2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzahb;->zzc:Z

    if-nez p0, :cond_1

    const/4 p0, 0x1

    return p0

    .line 4
    :cond_1
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public final zzc(Ljava/lang/Object;)I
    .locals 1

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 2
    invoke-virtual {v0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzahb;->zzc:Z

    if-nez p0, :cond_0

    return v0

    .line 3
    :cond_0
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public final zzd(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzahb;->zzb:Lcom/google/android/recaptcha/internal/zzahz;

    invoke-static {v0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaho;->zzD(Lcom/google/android/recaptcha/internal/zzahz;Ljava/lang/Object;Ljava/lang/Object;)V

    iget-boolean v0, p0, Lcom/google/android/recaptcha/internal/zzahb;->zzc:Z

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahb;->zzd:Lcom/google/android/recaptcha/internal/zzafi;

    .line 2
    invoke-static {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaho;->zzC(Lcom/google/android/recaptcha/internal/zzafi;Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_0
    return-void
.end method

.method public final zze(Ljava/lang/Object;)I
    .locals 1

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 3
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaia;->zzh()I

    move-result v0

    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzahb;->zzc:Z

    if-nez p0, :cond_0

    return v0

    .line 4
    :cond_0
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public final zzf(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzafb;)V
    .locals 0

    .line 1
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public final zzg(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzaew;Lcom/google/android/recaptcha/internal/zzafh;)V
    .locals 0

    .line 1
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaib;->zzj(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzaia;

    .line 2
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    .line 3
    throw p0
.end method

.method public final zzj(Ljava/lang/Object;[BIILcom/google/android/recaptcha/internal/zzaeg;)V
    .locals 0

    .line 1
    move-object p0, p1

    check-cast p0, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaia;->zza()Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object p3

    if-eq p2, p3, :cond_0

    goto :goto_0

    .line 4
    :cond_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaia;->zzb()Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object p2

    .line 2
    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 3
    :goto_0
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    .line 4
    throw p0
.end method

.method public final zzk(Ljava/lang/Object;)V
    .locals 0

    .line 1
    move-object p0, p1

    check-cast p0, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaia;->zzd()V

    .line 3
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public final zzl(Ljava/lang/Object;)Z
    .locals 0

    .line 1
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0
.end method
