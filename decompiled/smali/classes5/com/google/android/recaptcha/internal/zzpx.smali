.class final Lcom/google/android/recaptcha/internal/zzpx;
.super Lcom/google/android/recaptcha/internal/zzqu;
.source "SourceFile"


# instance fields
.field final zza:Ljava/util/Iterator;

.field zzb:Ljava/lang/Object;

.field zzc:Ljava/util/Iterator;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzpz;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzpz;)V
    .locals 1

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzpx;->zzd:Lcom/google/android/recaptcha/internal/zzpz;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzqu;-><init>()V

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzpz;->map:Lcom/google/android/recaptcha/internal/zzpw;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzpw;->zza()Lcom/google/android/recaptcha/internal/zzqb;

    move-result-object p1

    .line 3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzqb;->zzj()Lcom/google/android/recaptcha/internal/zzpt;

    move-result-object p1

    const/4 v0, 0x0

    .line 4
    invoke-virtual {p1, v0}, Lcom/google/android/recaptcha/internal/zzpt;->zzj(I)Lcom/google/android/recaptcha/internal/zzqv;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzpx;->zza:Ljava/util/Iterator;

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzpx;->zzb:Ljava/lang/Object;

    .line 5
    sget-object p1, Lcom/google/android/recaptcha/internal/zzqf;->zza:Lcom/google/android/recaptcha/internal/zzqv;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzpx;->zzc:Ljava/util/Iterator;

    return-void
.end method


# virtual methods
.method public final hasNext()Z
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzpx;->zzc:Ljava/util/Iterator;

    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-nez v0, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpx;->zza:Ljava/util/Iterator;

    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method public final bridge synthetic next()Ljava/lang/Object;
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzpx;->zzc:Ljava/util/Iterator;

    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzpx;->zza:Ljava/util/Iterator;

    .line 2
    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 3
    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzpx;->zzb:Ljava/lang/Object;

    .line 4
    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzpq;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzpq;->zza()Lcom/google/android/recaptcha/internal/zzqu;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzpx;->zzc:Ljava/util/Iterator;

    :cond_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzpx;->zzb:Ljava/lang/Object;

    .line 5
    invoke-static {v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpx;->zzc:Ljava/util/Iterator;

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p0

    new-instance v1, Ljava/util/AbstractMap$SimpleImmutableEntry;

    .line 6
    invoke-direct {v1, v0, p0}, Ljava/util/AbstractMap$SimpleImmutableEntry;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object v1
.end method
