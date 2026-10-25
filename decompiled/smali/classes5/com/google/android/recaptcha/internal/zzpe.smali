.class final Lcom/google/android/recaptcha/internal/zzpe;
.super Ljava/util/AbstractSet;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzpj;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/recaptcha/internal/zzpj;[B)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzpe;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-direct {p0}, Ljava/util/AbstractSet;-><init>()V

    return-void
.end method


# virtual methods
.method public final clear()V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpe;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->clear()V

    return-void
.end method

.method public final contains(Ljava/lang/Object;)Z
    .locals 3

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpe;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->zzb()Ljava/util/Map;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 2
    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p0

    invoke-interface {p0, p1}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result p0

    return p0

    .line 3
    :cond_0
    instance-of v0, p1, Ljava/util/Map$Entry;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 4
    check-cast p1, Ljava/util/Map$Entry;

    .line 5
    invoke-interface {p1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzpj;->zzh(Ljava/lang/Object;)I

    move-result v0

    const/4 v2, -0x1

    if-eq v0, v2, :cond_1

    .line 6
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzpj;->zzo(I)Ljava/lang/Object;

    move-result-object p0

    invoke-interface {p1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object p1

    invoke-static {p0, p1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    return v1
.end method

.method public final iterator()Ljava/util/Iterator;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpe;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->zzb()Ljava/util/Map;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 2
    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p0

    return-object p0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzpc;

    .line 3
    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzpc;-><init>(Lcom/google/android/recaptcha/internal/zzpj;)V

    return-object v0
.end method

.method public final remove(Ljava/lang/Object;)Z
    .locals 8

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpe;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->zzb()Ljava/util/Map;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 2
    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p0

    invoke-interface {p0, p1}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    move-result p0

    return p0

    .line 3
    :cond_0
    instance-of v0, p1, Ljava/util/Map$Entry;

    if-eqz v0, :cond_2

    .line 4
    check-cast p1, Ljava/util/Map$Entry;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->zza()Z

    move-result v0

    if-eqz v0, :cond_1

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->zzg()I

    move-result v3

    .line 5
    invoke-interface {p1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    .line 6
    invoke-interface {p1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    .line 7
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->zzj()Ljava/lang/Object;

    move-result-object v4

    .line 8
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->zzk()[I

    move-result-object v5

    .line 9
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->zzl()[Ljava/lang/Object;

    move-result-object v6

    .line 10
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->zzm()[Ljava/lang/Object;

    move-result-object v7

    .line 11
    invoke-static/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzpk;->zze(Ljava/lang/Object;Ljava/lang/Object;ILjava/lang/Object;[I[Ljava/lang/Object;[Ljava/lang/Object;)I

    move-result p1

    const/4 v0, -0x1

    if-eq p1, v0, :cond_2

    .line 12
    invoke-virtual {p0, p1, v3}, Lcom/google/android/recaptcha/internal/zzpj;->zzd(II)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->zzs()I

    move-result p1

    add-int/2addr p1, v0

    .line 13
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzpj;->zzt(I)V

    .line 14
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->zzc()V

    const/4 p0, 0x1

    return p0

    :cond_2
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public final size()I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpe;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->size()I

    move-result p0

    return p0
.end method
