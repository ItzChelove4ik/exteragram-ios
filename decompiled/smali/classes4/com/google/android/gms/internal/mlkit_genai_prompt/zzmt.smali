.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;
.source "SourceFile"

# interfaces
.implements Ljava/util/NavigableSet;
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zznq;


# instance fields
.field final transient zza:Ljava/util/Comparator;

.field transient zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;


# direct methods
.method public constructor <init>(Ljava/util/Comparator;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zza:Ljava/util/Comparator;

    return-void
.end method

.method public static zzl(Ljava/util/Comparator;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;
    .locals 2

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmz;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmz;

    invoke-virtual {v0, p0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;

    return-object p0

    .line 3
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;

    sget v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->$r8$clinit:I

    .line 4
    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzng;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 3
    invoke-direct {v0, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Ljava/util/Comparator;)V

    return-object v0
.end method


# virtual methods
.method public final addFirst(Ljava/lang/Object;)V
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 1
    new-instance p0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {p0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw p0
.end method

.method public final addLast(Ljava/lang/Object;)V
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 1
    new-instance p0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {p0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw p0
.end method

.method public final comparator()Ljava/util/Comparator;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zza:Ljava/util/Comparator;

    return-object p0
.end method

.method public final bridge synthetic descendingSet()Ljava/util/NavigableSet;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzq()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    .line 2
    iput-object p0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    :cond_0
    return-object v0
.end method

.method public abstract first()Ljava/lang/Object;
.end method

.method public final getFirst()Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->first()Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final getLast()Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->last()Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic headSet(Ljava/lang/Object;Z)Ljava/util/NavigableSet;
    .locals 0

    .line 4
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 3
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzn(Ljava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic headSet(Ljava/lang/Object;)Ljava/util/SortedSet;
    .locals 1

    .line 2
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    const/4 v0, 0x0

    .line 1
    invoke-virtual {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzn(Ljava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    move-result-object p0

    return-object p0
.end method

.method public abstract last()Ljava/lang/Object;
.end method

.method public final pollFirst()Ljava/lang/Object;
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 1
    new-instance p0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {p0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw p0
.end method

.method public final pollLast()Ljava/lang/Object;
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 1
    new-instance p0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {p0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw p0
.end method

.method public final removeFirst()Ljava/lang/Object;
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 1
    new-instance p0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {p0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw p0
.end method

.method public final removeLast()Ljava/lang/Object;
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 1
    new-instance p0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {p0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw p0
.end method

.method public final bridge synthetic subSet(Ljava/lang/Object;ZLjava/lang/Object;Z)Ljava/util/NavigableSet;
    .locals 0

    .line 2
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzm(Ljava/lang/Object;ZLjava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    move-result-object p0

    return-object p0
.end method

.method public final bridge synthetic subSet(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/SortedSet;
    .locals 2

    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 1
    invoke-virtual {p0, p1, v0, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzm(Ljava/lang/Object;ZLjava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic tailSet(Ljava/lang/Object;Z)Ljava/util/NavigableSet;
    .locals 0

    .line 4
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 3
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzp(Ljava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic tailSet(Ljava/lang/Object;)Ljava/util/SortedSet;
    .locals 1

    .line 2
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    const/4 v0, 0x1

    .line 1
    invoke-virtual {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzp(Ljava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    move-result-object p0

    return-object p0
.end method

.method public final zzm(Ljava/lang/Object;ZLjava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;
    .locals 1

    .line 5
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 4
    invoke-virtual {p3}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zza:Ljava/util/Comparator;

    invoke-interface {v0, p1, p3}, Ljava/util/Comparator;->compare(Ljava/lang/Object;Ljava/lang/Object;)I

    move-result v0

    if-gtz v0, :cond_0

    .line 2
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;->zzo(Ljava/lang/Object;ZLjava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;

    move-result-object p0

    return-object p0

    .line 3
    :cond_0
    invoke-static {}, Lokio/Segment$$ExternalSyntheticBUOutline0;->m()V

    const/4 p0, 0x0

    return-object p0
.end method

.method public abstract zzn(Ljava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;
.end method

.method public abstract zzo(Ljava/lang/Object;ZLjava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;
.end method

.method public abstract zzp(Ljava/lang/Object;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;
.end method

.method public abstract zzq()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmt;
.end method
