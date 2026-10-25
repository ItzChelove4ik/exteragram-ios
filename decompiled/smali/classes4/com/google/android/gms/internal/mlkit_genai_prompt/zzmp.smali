.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmp;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/util/List;


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmp;->zza:Ljava/util/List;

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmp;
    .locals 1

    .line 1
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb()Z

    move-result v0

    if-nez v0, :cond_0

    .line 4
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmp;->zza:Ljava/util/List;

    .line 2
    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-object p0

    .line 1
    :cond_0
    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p0

    const-string p1, "range must not be empty, but was %s"

    .line 3
    invoke-static {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlb;->zzd(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    .line 4
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmp;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmp;
    .locals 1

    .line 1
    iget-object p1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmp;->zza:Ljava/util/List;

    .line 2
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    .line 3
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmp;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmp;

    goto :goto_0

    :cond_0
    return-object p0
.end method

.method public final zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;
    .locals 5

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmp;->zza:Ljava/util/List;

    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v1

    invoke-direct {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;-><init>(I)V

    .line 2
    sget v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->$r8$clinit:I

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznd;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zznb;

    invoke-static {p0, v1}, Ljava/util/Collections;->sort(Ljava/util/List;Ljava/util/Comparator;)V

    .line 3
    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    instance-of v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmv;

    if-eqz v1, :cond_0

    .line 4
    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmv;

    goto :goto_0

    .line 31
    :cond_0
    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmv;

    .line 5
    invoke-direct {v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmv;-><init>(Ljava/util/Iterator;)V

    move-object p0, v1

    .line 6
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 7
    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznc;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    .line 8
    :goto_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 9
    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznc;->zza()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    iget-object v3, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    .line 10
    iget-object v4, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    invoke-virtual {v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)I

    move-result v3

    if-gtz v3, :cond_1

    iget-object v3, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    iget-object v4, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    .line 11
    invoke-virtual {v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)I

    move-result v3

    if-gtz v3, :cond_1

    .line 12
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb()Z

    move-result v3

    const-string v4, "Overlapping ranges not permitted but found %s overlapping %s"

    .line 13
    invoke-static {v3, v4, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzla;->zzb(ZLjava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    .line 14
    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznc;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    move-result-object v1

    goto :goto_1

    .line 15
    :cond_1
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zzd(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    goto :goto_0

    .line 16
    :cond_2
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    .line 17
    invoke-virtual {p0}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 18
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;

    move-result-object p0

    return-object p0

    .line 19
    :cond_3
    invoke-virtual {p0}, Ljava/util/AbstractCollection;->size()I

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :cond_7

    const/4 v0, 0x0

    .line 20
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzs(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznt;

    move-result-object v1

    .line 21
    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 22
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-nez v3, :cond_4

    .line 19
    check-cast v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    move-result-object v0

    invoke-virtual {v2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_7

    .line 23
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;

    move-result-object p0

    return-object p0

    :cond_4
    new-instance p0, Ljava/lang/StringBuilder;

    const-string v3, "expected one element but was: <"

    .line 24
    invoke-direct {p0, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    :goto_2
    const/4 v2, 0x4

    if-ge v0, v2, :cond_5

    .line 25
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_5

    const-string v2, ", "

    .line 26
    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    .line 27
    :cond_5
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-nez v0, :cond_6

    goto :goto_3

    .line 30
    :cond_6
    const-string v0, ", ..."

    .line 28
    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :goto_3
    const/16 v0, 0x3e

    .line 29
    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    new-instance v0, Ljava/lang/IllegalArgumentException;

    .line 30
    invoke-direct {v0, p0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 31
    :cond_7
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)V

    return-object v0
.end method
