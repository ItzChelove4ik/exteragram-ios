.class final Lcom/google/android/recaptcha/internal/zzqd;
.super Lcom/google/android/recaptcha/internal/zzqb;
.source "SourceFile"


# instance fields
.field private final transient zza:Lcom/google/android/recaptcha/internal/zzqe;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzqe;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzqb;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzqd;->zza:Lcom/google/android/recaptcha/internal/zzqe;

    return-void
.end method


# virtual methods
.method public final contains(Ljava/lang/Object;)Z
    .locals 2

    .line 1
    instance-of v0, p1, Ljava/util/Map$Entry;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 2
    check-cast p1, Ljava/util/Map$Entry;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzqd;->zza:Lcom/google/android/recaptcha/internal/zzqe;

    .line 3
    invoke-interface {p1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v0

    invoke-interface {p1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzoy;->zza()Ljava/util/Map;

    move-result-object p0

    .line 4
    invoke-interface {p0, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/Collection;

    if-eqz p0, :cond_0

    .line 5
    invoke-interface {p0, p1}, Ljava/util/Collection;->contains(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    return v1
.end method

.method public final synthetic iterator()Ljava/util/Iterator;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzpx;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzqd;->zza:Lcom/google/android/recaptcha/internal/zzqe;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzpx;-><init>(Lcom/google/android/recaptcha/internal/zzpz;)V

    return-object v0
.end method

.method public final size()I
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzqd;->zza:Lcom/google/android/recaptcha/internal/zzqe;

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzpz;->size:I

    return p0
.end method

.method public final zza()Lcom/google/android/recaptcha/internal/zzqu;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzpx;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzqd;->zza:Lcom/google/android/recaptcha/internal/zzqe;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzpx;-><init>(Lcom/google/android/recaptcha/internal/zzpz;)V

    return-object v0
.end method

.method public final zze()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method
