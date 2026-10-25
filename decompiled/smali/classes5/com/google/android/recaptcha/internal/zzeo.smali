.class public final Lcom/google/android/recaptcha/internal/zzeo;
.super Lcom/google/android/recaptcha/internal/zzcl;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/util/List;

.field private zzb:Lcom/google/android/recaptcha/internal/zzaer;

.field private final zzc:Ljava/util/Map;


# direct methods
.method public constructor <init>(Ljava/util/List;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzcl;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzeo;->zza:Ljava/util/List;

    new-instance p1, Ljava/util/LinkedHashMap;

    invoke-direct {p1}, Ljava/util/LinkedHashMap;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzeo;->zzc:Ljava/util/Map;

    return-void
.end method

.method public static final synthetic zzi(Lcom/google/android/recaptcha/internal/zzeo;Lcom/google/android/recaptcha/internal/zzaer;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzeo;->zzb:Lcom/google/android/recaptcha/internal/zzaer;

    return-void
.end method

.method public static final synthetic zzj(Lcom/google/android/recaptcha/internal/zzeo;)Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzeo;->zza:Ljava/util/List;

    return-object p0
.end method

.method public static final synthetic zzk(Lcom/google/android/recaptcha/internal/zzeo;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzaku;
    .locals 0

    .line 1
    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzeo;->zzl(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzaku;

    move-result-object p0

    return-object p0
.end method

.method private final zzl(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzaku;
    .locals 5

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzeo;->zzc:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/List;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzhx;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzeo;->zzb:Lcom/google/android/recaptcha/internal/zzaer;

    if-nez v2, :cond_0

    const/4 v2, 0x0

    .line 2
    :cond_0
    invoke-direct {v1, v2}, Lcom/google/android/recaptcha/internal/zzhx;-><init>(Lcom/google/android/recaptcha/internal/zzaer;)V

    if-eqz v0, :cond_1

    .line 3
    check-cast v0, Ljava/lang/Iterable;

    const/16 v2, 0xa

    .line 4
    invoke-static {v0, v2}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v2

    invoke-static {v2}, Lkotlin/collections/MapsKt;->mapCapacity(I)I

    move-result v2

    const/16 v3, 0x10

    invoke-static {v2, v3}, Lkotlin/ranges/RangesKt;->coerceAtLeast(II)I

    move-result v2

    new-instance v3, Ljava/util/LinkedHashMap;

    .line 5
    invoke-direct {v3, v2}, Ljava/util/LinkedHashMap;-><init>(I)V

    .line 6
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 7
    check-cast v2, Lcom/google/android/recaptcha/internal/zzex;

    .line 3
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzex;->zzb()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    .line 7
    invoke-interface {v3, v4, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    .line 3
    :cond_1
    invoke-static {}, Lkotlin/collections/MapsKt;->emptyMap()Ljava/util/Map;

    move-result-object v3

    .line 8
    :cond_2
    invoke-direct {p0, v3, v1, p1}, Lcom/google/android/recaptcha/internal/zzeo;->zzm(Ljava/util/Map;Lcom/google/android/recaptcha/internal/zzhx;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalt;

    move-result-object p0

    .line 9
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaku;->zza()Lcom/google/android/recaptcha/internal/zzakt;

    move-result-object v0

    .line 10
    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzakt;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakt;

    .line 11
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzakm;->zza()Lcom/google/android/recaptcha/internal/zzakl;

    move-result-object p1

    .line 12
    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzakl;->zza(Lcom/google/android/recaptcha/internal/zzalt;)Lcom/google/android/recaptcha/internal/zzakl;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzhx;->zza()[B

    move-result-object p0

    const/4 v1, 0x0

    const/16 v2, 0xc

    .line 13
    invoke-static {p0, v1, v2}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    .line 14
    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzakl;->zzb(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzakl;

    .line 15
    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzakt;->zzc(Lcom/google/android/recaptcha/internal/zzakl;)Lcom/google/android/recaptcha/internal/zzakt;

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzaku;

    return-object p0
.end method

.method private final zzm(Ljava/util/Map;Lcom/google/android/recaptcha/internal/zzhx;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalt;
    .locals 6

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzalt;->zzd()Lcom/google/android/recaptcha/internal/zzals;

    move-result-object v0

    invoke-virtual {v0, p3}, Lcom/google/android/recaptcha/internal/zzals;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzals;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzeo;->zza:Ljava/util/List;

    .line 2
    check-cast p0, Ljava/lang/Iterable;

    new-instance p3, Ljava/util/ArrayList;

    .line 3
    invoke-direct {p3}, Ljava/util/ArrayList;-><init>()V

    .line 4
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_0
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v2, v1

    check-cast v2, Lcom/google/android/recaptcha/internal/zzev;

    .line 2
    invoke-interface {v2}, Lcom/google/android/recaptcha/internal/zzev;->zzb()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 4
    invoke-interface {p3, v1}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 2
    :cond_1
    invoke-virtual {p3}, Ljava/util/ArrayList;->size()I

    move-result p0

    const/4 v1, 0x0

    :cond_2
    :goto_1
    if-ge v1, p0, :cond_3

    invoke-virtual {p3, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    add-int/lit8 v1, v1, 0x1

    check-cast v2, Lcom/google/android/recaptcha/internal/zzev;

    .line 6
    invoke-interface {v2}, Lcom/google/android/recaptcha/internal/zzev;->zza()I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-interface {p1, v3}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_2

    .line 7
    invoke-interface {v2}, Lcom/google/android/recaptcha/internal/zzev;->zza()I

    move-result v2

    new-instance v3, Lcom/google/android/recaptcha/internal/zzep;

    .line 8
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzalx;->zza()Lcom/google/android/recaptcha/internal/zzalw;

    move-result-object v4

    .line 9
    invoke-virtual {v4, v2}, Lcom/google/android/recaptcha/internal/zzalw;->zza(I)Lcom/google/android/recaptcha/internal/zzalw;

    const/16 v5, 0xd

    .line 10
    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzalw;->zzb(I)Lcom/google/android/recaptcha/internal/zzalw;

    const/16 v5, 0x1b

    .line 11
    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzalw;->zzc(I)Lcom/google/android/recaptcha/internal/zzalw;

    .line 12
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v4

    check-cast v4, Lcom/google/android/recaptcha/internal/zzalx;

    invoke-direct {v3, v2, v4}, Lcom/google/android/recaptcha/internal/zzep;-><init>(ILcom/google/android/recaptcha/internal/zzalx;)V

    .line 14
    invoke-static {p2, v3}, Lcom/google/android/recaptcha/internal/zzeo;->zzn(Lcom/google/android/recaptcha/internal/zzhx;Lcom/google/android/recaptcha/internal/zzex;)Lcom/google/android/recaptcha/internal/zzalr;

    move-result-object v2

    .line 15
    invoke-virtual {v0, v2}, Lcom/google/android/recaptcha/internal/zzals;->zzb(Lcom/google/android/recaptcha/internal/zzalr;)Lcom/google/android/recaptcha/internal/zzals;

    goto :goto_1

    .line 16
    :cond_3
    invoke-interface {p1}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    new-instance p1, Ljava/util/ArrayList;

    const/16 p3, 0xa

    .line 17
    invoke-static {p0, p3}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result p3

    invoke-direct {p1, p3}, Ljava/util/ArrayList;-><init>(I)V

    .line 18
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_2
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p3

    if-eqz p3, :cond_4

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p3

    .line 19
    check-cast p3, Lcom/google/android/recaptcha/internal/zzex;

    .line 16
    invoke-static {p2, p3}, Lcom/google/android/recaptcha/internal/zzeo;->zzn(Lcom/google/android/recaptcha/internal/zzhx;Lcom/google/android/recaptcha/internal/zzex;)Lcom/google/android/recaptcha/internal/zzalr;

    move-result-object p3

    .line 19
    invoke-interface {p1, p3}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_2

    .line 20
    :cond_4
    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzals;->zzc(Ljava/lang/Iterable;)Lcom/google/android/recaptcha/internal/zzals;

    .line 21
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzalt;

    return-object p0
.end method

.method private static final zzn(Lcom/google/android/recaptcha/internal/zzhx;Lcom/google/android/recaptcha/internal/zzex;)Lcom/google/android/recaptcha/internal/zzalr;
    .locals 4

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzalr;->zzi()Lcom/google/android/recaptcha/internal/zzalp;

    move-result-object v0

    const/4 v1, 0x3

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzalp;->zzh(I)Lcom/google/android/recaptcha/internal/zzalp;

    .line 3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzex;->zzb()I

    move-result v1

    int-to-long v1, v1

    invoke-static {v1, v2}, Ljava/math/BigInteger;->valueOf(J)Ljava/math/BigInteger;

    move-result-object v1

    invoke-virtual {v1}, Ljava/math/BigInteger;->toByteArray()[B

    move-result-object v1

    sget-object v2, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    .line 4
    array-length v2, v1

    const/4 v3, 0x0

    invoke-static {v1, v3, v2}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v1

    .line 5
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzalp;->zzg(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzalp;

    instance-of v1, p1, Lcom/google/android/recaptcha/internal/zzeq;

    if-eqz v1, :cond_0

    .line 6
    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzeq;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzeq;->zza()Lcom/google/android/recaptcha/internal/zzamb;

    move-result-object v1

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzex;->zzb()I

    move-result p1

    .line 8
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzaec;->zzw()[B

    move-result-object v1

    invoke-virtual {p0, v1, p1}, Lcom/google/android/recaptcha/internal/zzhx;->zzb([BI)[B

    move-result-object p0

    .line 9
    array-length p1, p0

    invoke-static {p0, v3, p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    .line 10
    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzalp;->zze(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzalp;

    goto :goto_0

    .line 17
    :cond_0
    instance-of v1, p1, Lcom/google/android/recaptcha/internal/zzep;

    if-eqz v1, :cond_1

    .line 11
    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzep;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzep;->zza()Lcom/google/android/recaptcha/internal/zzalx;

    move-result-object v1

    .line 12
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzex;->zzb()I

    move-result p1

    .line 13
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzaec;->zzw()[B

    move-result-object v1

    invoke-virtual {p0, v1, p1}, Lcom/google/android/recaptcha/internal/zzhx;->zzb([BI)[B

    move-result-object p0

    .line 14
    array-length p1, p0

    invoke-static {p0, v3, p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    .line 15
    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzalp;->zzf(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzalp;

    .line 16
    :goto_0
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    .line 17
    check-cast p0, Lcom/google/android/recaptcha/internal/zzalr;

    return-object p0

    .line 18
    :cond_1
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    const/4 p0, 0x0

    return-object p0
.end method


# virtual methods
.method public final zzb(Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 1

    .line 1
    new-instance p2, Lcom/google/android/recaptcha/internal/zzen;

    const/4 v0, 0x0

    invoke-direct {p2, p1, p0, v0}, Lcom/google/android/recaptcha/internal/zzen;-><init>(Lcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zzeo;Lkotlin/coroutines/Continuation;)V

    new-instance p0, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    return-object p0
.end method

.method public final zzc(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 1

    .line 1
    new-instance p3, Lcom/google/android/recaptcha/internal/zzel;

    const/4 v0, 0x0

    invoke-direct {p3, p0, p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzel;-><init>(Lcom/google/android/recaptcha/internal/zzeo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)V

    new-instance p0, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    return-object p0
.end method

.method public final zzd(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzeo;->zzl(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzaku;

    move-result-object p0

    return-object p0
.end method

.method public final zzf()I
    .locals 0

    const/16 p0, 0x22

    return p0
.end method

.method public final zzg()I
    .locals 0

    const/16 p0, 0x23

    return p0
.end method

.method public final zzh()Ljava/util/Map;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzeo;->zzc:Ljava/util/Map;

    return-object p0
.end method
