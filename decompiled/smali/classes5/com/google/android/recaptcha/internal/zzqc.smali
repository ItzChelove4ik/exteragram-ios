.class public final Lcom/google/android/recaptcha/internal/zzqc;
.super Lcom/google/android/recaptcha/internal/zzpy;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzpy;-><init>()V

    return-void
.end method


# virtual methods
.method public final varargs zzb(Ljava/lang/Object;[Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzqc;
    .locals 4

    .line 1
    invoke-static {p2}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object p2

    const/4 v0, 0x1

    if-nez p1, :cond_2

    new-instance p0, Ljava/lang/NullPointerException;

    .line 2
    invoke-interface {p2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v1, "["

    .line 3
    invoke-direct {p2, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 4
    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    if-nez v0, :cond_0

    const-string v0, ", "

    .line 5
    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 6
    :cond_0
    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const/4 v0, 0x0

    goto :goto_0

    :cond_1
    const/16 p1, 0x5d

    .line 7
    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "null key in entry: null="

    invoke-virtual {p2, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 8
    invoke-direct {p0, p1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 9
    :cond_2
    invoke-interface {p2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    .line 10
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-nez v2, :cond_3

    goto :goto_2

    .line 11
    :cond_3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpy;->zza()Ljava/util/Map;

    move-result-object v2

    invoke-interface {v2, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzpp;

    if-nez v2, :cond_5

    .line 12
    instance-of v2, p2, Ljava/util/Set;

    const/4 v3, 0x4

    if-eqz v2, :cond_4

    .line 13
    check-cast p2, Ljava/util/Set;

    .line 14
    invoke-interface {p2}, Ljava/util/Set;->size()I

    move-result p2

    invoke-static {v3, p2}, Ljava/lang/Math;->max(II)I

    move-result v3

    :cond_4
    const-string p2, "expectedSize"

    .line 15
    invoke-static {v3, p2}, Lcom/google/android/recaptcha/internal/zzpa;->zzb(ILjava/lang/String;)I

    new-instance v2, Lcom/google/android/recaptcha/internal/zzqa;

    .line 16
    invoke-direct {v2, v3, v0}, Lcom/google/android/recaptcha/internal/zzqa;-><init>(IZ)V

    .line 17
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpy;->zza()Ljava/util/Map;

    move-result-object p2

    invoke-interface {p2, p1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 18
    :cond_5
    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result p2

    if-eqz p2, :cond_6

    .line 19
    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p2

    .line 20
    invoke-static {p1, p2}, Lcom/google/android/recaptcha/internal/zzpa;->zza(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 21
    invoke-virtual {v2, p2}, Lcom/google/android/recaptcha/internal/zzpp;->zzb(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzpp;

    goto :goto_1

    :cond_6
    :goto_2
    return-object p0
.end method

.method public final zzc()Lcom/google/android/recaptcha/internal/zzqe;
    .locals 5

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpy;->zza:Ljava/util/Map;

    if-nez p0, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzpm;->zza:Lcom/google/android/recaptcha/internal/zzpm;

    return-object p0

    .line 2
    :cond_0
    invoke-interface {p0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p0

    .line 3
    invoke-interface {p0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 4
    sget-object p0, Lcom/google/android/recaptcha/internal/zzpm;->zza:Lcom/google/android/recaptcha/internal/zzpm;

    return-object p0

    :cond_1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzpv;

    .line 5
    invoke-interface {p0}, Ljava/util/Collection;->size()I

    move-result v1

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzpv;-><init>(I)V

    .line 6
    invoke-interface {p0}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object p0

    const/4 v1, 0x0

    :cond_2
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_3

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    .line 7
    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    .line 8
    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzqa;

    .line 9
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzqa;->zzd()Lcom/google/android/recaptcha/internal/zzqb;

    move-result-object v2

    .line 10
    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzqb;->zzh(Ljava/util/Collection;)Lcom/google/android/recaptcha/internal/zzqb;

    move-result-object v2

    .line 11
    invoke-virtual {v2}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_2

    .line 12
    invoke-virtual {v0, v3, v2}, Lcom/google/android/recaptcha/internal/zzpv;->zza(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzpv;

    .line 13
    invoke-virtual {v2}, Ljava/util/AbstractCollection;->size()I

    move-result v2

    add-int/2addr v1, v2

    goto :goto_0

    :cond_3
    new-instance p0, Lcom/google/android/recaptcha/internal/zzqe;

    .line 14
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzpv;->zzb()Lcom/google/android/recaptcha/internal/zzpw;

    move-result-object v0

    const/4 v2, 0x0

    invoke-direct {p0, v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzqe;-><init>(Lcom/google/android/recaptcha/internal/zzpw;ILjava/util/Comparator;)V

    return-object p0
.end method
