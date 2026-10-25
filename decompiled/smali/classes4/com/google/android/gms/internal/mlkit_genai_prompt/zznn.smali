.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;
.source "SourceFile"


# static fields
.field static final zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;


# instance fields
.field final transient zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;

    sget v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->$r8$clinit:I

    .line 2
    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzng;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    sget-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmz;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmz;

    .line 1
    invoke-direct {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Ljava/util/Comparator;)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;

    return-void
.end method

.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Ljava/util/Comparator;)V
    .locals 0

    .line 1
    invoke-direct {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;-><init>(Ljava/util/Comparator;)V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-void
.end method


# virtual methods
.method public final ceiling(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 v1, 0x1

    invoke-virtual {p0, p1, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzt(Ljava/lang/Object;Z)I

    move-result p0

    .line 2
    invoke-virtual {v0}, Ljava/util/AbstractCollection;->size()I

    move-result p1

    if-ne p0, p1, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 3
    :cond_0
    invoke-interface {v0, p0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final contains(Ljava/lang/Object;)Z
    .locals 2

    const/4 v0, 0x0

    if-eqz p1, :cond_0

    .line 1
    :try_start_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zza:Ljava/util/Comparator;

    invoke-static {v1, p1, p0}, Ljava/util/Collections;->binarySearch(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;)I

    move-result p0
    :try_end_0
    .catch Ljava/lang/ClassCastException; {:try_start_0 .. :try_end_0} :catch_0

    if-ltz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :catch_0
    :cond_0
    return v0
.end method

.method public final containsAll(Ljava/util/Collection;)Z
    .locals 6

    .line 1
    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmy;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmy;

    invoke-interface {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmy;->zza()Ljava/util/Set;

    move-result-object p1

    :cond_0
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zza:Ljava/util/Comparator;

    .line 2
    invoke-static {v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznr;->zza(Ljava/util/Comparator;Ljava/lang/Iterable;)Z

    move-result v1

    if-eqz v1, :cond_6

    invoke-interface {p1}, Ljava/util/Collection;->size()I

    move-result v1

    const/4 v2, 0x1

    if-le v1, v2, :cond_6

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 v1, 0x0

    .line 3
    invoke-virtual {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzs(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznt;

    move-result-object p0

    .line 4
    invoke-interface {p1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object p1

    .line 5
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-nez v3, :cond_1

    return v1

    .line 6
    :cond_1
    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    .line 7
    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    .line 8
    :goto_0
    :try_start_0
    invoke-interface {v0, v4, v3}, Ljava/util/Comparator;->compare(Ljava/lang/Object;Ljava/lang/Object;)I

    move-result v5

    if-gez v5, :cond_3

    .line 9
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-nez v4, :cond_2

    return v1

    .line 10
    :cond_2
    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    goto :goto_0

    :cond_3
    if-nez v5, :cond_5

    .line 11
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-nez v3, :cond_4

    return v2

    .line 12
    :cond_4
    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/ClassCastException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    :cond_5
    return v1

    .line 13
    :cond_6
    invoke-super {p0, p1}, Ljava/util/AbstractCollection;->containsAll(Ljava/util/Collection;)Z

    move-result p0

    return p0
.end method

.method public final bridge synthetic descendingIterator()Ljava/util/Iterator;
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzr()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzns;

    move-result-object p0

    return-object p0
.end method

.method public final equals(Ljava/lang/Object;)Z
    .locals 6

    const/4 v0, 0x1

    if-ne p1, p0, :cond_0

    return v0

    .line 1
    :cond_0
    instance-of v1, p1, Ljava/util/Set;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    .line 2
    :cond_1
    check-cast p1, Ljava/util/Set;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 3
    invoke-virtual {v1}, Ljava/util/AbstractCollection;->size()I

    move-result v3

    .line 4
    invoke-interface {p1}, Ljava/util/Set;->size()I

    move-result v4

    if-eq v3, v4, :cond_2

    return v2

    .line 5
    :cond_2
    invoke-virtual {p0}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_3

    return v0

    :cond_3
    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zza:Ljava/util/Comparator;

    .line 6
    invoke-static {v3, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznr;->zza(Ljava/util/Comparator;Ljava/lang/Iterable;)Z

    move-result v3

    if-eqz v3, :cond_7

    .line 7
    invoke-interface {p1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p1

    .line 8
    :try_start_0
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzs(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznt;

    move-result-object v1

    .line 9
    :cond_4
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_6

    .line 10
    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    .line 11
    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    if-eqz v4, :cond_5

    iget-object v5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zza:Ljava/util/Comparator;

    .line 12
    invoke-interface {v5, v3, v4}, Ljava/util/Comparator;->compare(Ljava/lang/Object;Ljava/lang/Object;)I

    move-result v3
    :try_end_0
    .catch Ljava/lang/ClassCastException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/util/NoSuchElementException; {:try_start_0 .. :try_end_0} :catch_0

    if-eqz v3, :cond_4

    :cond_5
    return v2

    :cond_6
    return v0

    :catch_0
    return v2

    .line 13
    :cond_7
    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->containsAll(Ljava/util/Collection;)Z

    move-result p0

    return p0
.end method

.method public final first()Ljava/lang/Object;
    .locals 1

    .line 1
    invoke-virtual {p0}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 v0, 0x0

    .line 3
    invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    .line 2
    :cond_0
    invoke-static {}, Lretrofit2/Utils$$ExternalSyntheticBUOutline0;->m()V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final floor(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    const/4 v0, 0x1

    .line 1
    invoke-virtual {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzs(Ljava/lang/Object;Z)I

    move-result p1

    const/4 v0, -0x1

    add-int/2addr p1, v0

    if-ne p1, v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 2
    invoke-interface {p0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final higher(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 v1, 0x0

    invoke-virtual {p0, p1, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzt(Ljava/lang/Object;Z)I

    move-result p0

    .line 2
    invoke-virtual {v0}, Ljava/util/AbstractCollection;->size()I

    move-result p1

    if-ne p0, p1, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 3
    :cond_0
    invoke-interface {v0, p0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic iterator()Ljava/util/Iterator;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzs(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznt;

    move-result-object p0

    return-object p0
.end method

.method public final last()Ljava/lang/Object;
    .locals 1

    .line 1
    invoke-virtual {p0}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 3
    invoke-virtual {p0}, Ljava/util/AbstractCollection;->size()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    .line 4
    invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    .line 2
    :cond_0
    invoke-static {}, Lretrofit2/Utils$$ExternalSyntheticBUOutline0;->m()V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final lower(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    const/4 v0, 0x0

    .line 1
    invoke-virtual {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzs(Ljava/lang/Object;Z)I

    move-result p1

    const/4 v0, -0x1

    add-int/2addr p1, v0

    if-ne p1, v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 2
    invoke-interface {p0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final size()I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {p0}, Ljava/util/AbstractCollection;->size()I

    move-result p0

    return p0
.end method

.method public final zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzns;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzs(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznt;

    move-result-object p0

    return-object p0
.end method

.method public final zzb()[Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmg;->zzb()[Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final zzc()I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmg;->zzc()I

    move-result p0

    return p0
.end method

.method public final zzd()I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmg;->zzd()I

    move-result p0

    return p0
.end method

.method public final zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0
.end method

.method public final zzg([Ljava/lang/Object;I)I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 p2, 0x0

    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmg;->zzg([Ljava/lang/Object;I)I

    move-result p0

    return p0
.end method

.method public final zzn(Ljava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;
    .locals 1

    const/4 v0, 0x0

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzs(Ljava/lang/Object;Z)I

    move-result p1

    invoke-virtual {p0, v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzu(II)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;

    move-result-object p0

    return-object p0
.end method

.method public final zzo(Ljava/lang/Object;ZLjava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;
    .locals 0

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzp(Ljava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    move-result-object p0

    invoke-virtual {p0, p3, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzn(Ljava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    move-result-object p0

    return-object p0
.end method

.method public final zzp(Ljava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzt(Ljava/lang/Object;Z)I

    move-result p1

    .line 2
    invoke-virtual {v0}, Ljava/util/AbstractCollection;->size()I

    move-result p2

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzu(II)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;

    move-result-object p0

    return-object p0
.end method

.method public final zzq()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zza:Ljava/util/Comparator;

    invoke-static {v0}, Ljava/util/Collections;->reverseOrder(Ljava/util/Comparator;)Ljava/util/Comparator;

    move-result-object v0

    .line 2
    invoke-virtual {p0}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 3
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzl(Ljava/util/Comparator;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;

    move-result-object p0

    return-object p0

    :cond_0
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;

    .line 4
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    invoke-direct {v1, p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Ljava/util/Comparator;)V

    return-object v1
.end method

.method public final zzr()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzns;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    const/4 v0, 0x0

    .line 2
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzs(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznt;

    move-result-object p0

    return-object p0
.end method

.method public final zzs(Ljava/lang/Object;Z)I
    .locals 1

    .line 2
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zza:Ljava/util/Comparator;

    invoke-static {v0, p1, p0}, Ljava/util/Collections;->binarySearch(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;)I

    move-result p0

    if-ltz p0, :cond_1

    if-eqz p2, :cond_0

    add-int/lit8 p0, p0, 0x1

    :cond_0
    return p0

    :cond_1
    not-int p0, p0

    return p0
.end method

.method public final zzt(Ljava/lang/Object;Z)I
    .locals 1

    .line 2
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zza:Ljava/util/Comparator;

    invoke-static {v0, p1, p0}, Ljava/util/Collections;->binarySearch(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;)I

    move-result p0

    if-ltz p0, :cond_1

    if-eqz p2, :cond_0

    return p0

    :cond_0
    add-int/lit8 p0, p0, 0x1

    return p0

    :cond_1
    not-int p0, p0

    return p0
.end method

.method public final zzu(II)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;
    .locals 2

    if-nez p1, :cond_1

    .line 1
    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {p1}, Ljava/util/AbstractCollection;->size()I

    move-result p1

    if-eq p2, p1, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    :cond_0
    return-object p0

    :cond_1
    :goto_0
    if-ge p1, p2, :cond_2

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zza:Ljava/util/Comparator;

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;

    .line 2
    invoke-virtual {v0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzi(II)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p1

    invoke-direct {v1, p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Ljava/util/Comparator;)V

    return-object v1

    :cond_2
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zza:Ljava/util/Comparator;

    .line 3
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzl(Ljava/util/Comparator;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;

    move-result-object p0

    return-object p0
.end method
