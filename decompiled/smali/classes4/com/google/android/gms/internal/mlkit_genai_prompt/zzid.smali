.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzid;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbg;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;

.field final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;)V
    .locals 0

    .line 1
    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzid;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzid;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbg;-><init>()V

    return-void
.end method


# virtual methods
.method public final zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzci;)V
    .locals 13

    .line 1
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->$r8$clinit:I

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    .line 2
    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;-><init>()V

    .line 3
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzci;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v2

    const/4 v3, 0x0

    move v4, v3

    :goto_0
    if-ge v4, v2, :cond_2

    invoke-interface {v1, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcd;

    new-instance v6, Ljava/util/ArrayList;

    .line 4
    invoke-direct {v6}, Ljava/util/ArrayList;-><init>()V

    .line 5
    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcd;->zzd()Ljava/util/List;

    move-result-object v7

    if-eqz v7, :cond_1

    .line 6
    invoke-interface {v7}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v7

    :goto_1
    invoke-interface {v7}, Ljava/util/Iterator;->hasNext()Z

    move-result v8

    if-eqz v8, :cond_1

    invoke-interface {v7}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;

    new-instance v9, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;

    .line 7
    invoke-direct {v9}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;-><init>()V

    .line 8
    invoke-virtual {v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object v10

    if-eqz v10, :cond_0

    .line 9
    invoke-virtual {v10}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;

    move-result-object v10

    invoke-virtual {v10}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzns;

    move-result-object v10

    :goto_2
    invoke-interface {v10}, Ljava/util/Iterator;->hasNext()Z

    move-result v11

    if-eqz v11, :cond_0

    invoke-interface {v10}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Ljava/util/Map$Entry;

    .line 10
    invoke-interface {v11}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v12

    check-cast v12, Ljava/lang/String;

    invoke-interface {v11}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;

    invoke-static {v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zziw;->zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zziw;

    move-result-object v11

    invoke-virtual {v9, v12, v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;->zza(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;

    goto :goto_2

    .line 11
    :cond_0
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zziy;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzix;

    move-result-object v10

    invoke-virtual {v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zza()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v10, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzix;->zza(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzix;

    invoke-virtual {v9}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object v8

    invoke-virtual {v10, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzix;->zzb(Ljava/util/Map;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzix;

    invoke-virtual {v10}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzix;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zziy;

    move-result-object v8

    .line 12
    invoke-interface {v6, v8}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 13
    :cond_1
    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcd;->zza()Ljava/lang/String;

    move-result-object v7

    .line 14
    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcd;->zzb()F

    move-result v8

    iget v9, v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcd;->zza:I

    .line 15
    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcd;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzw;

    move-result-object v5

    invoke-static {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zzw(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzw;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;

    move-result-object v5

    .line 16
    invoke-static {v7, v8, v9, v5, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zzf(Ljava/lang/String;FILcom/google/android/gms/internal/mlkit_genai_prompt/zzho;Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;

    move-result-object v5

    .line 17
    invoke-virtual {v0, v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zzd(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    add-int/lit8 v4, v4, 0x1

    goto/16 :goto_0

    :cond_2
    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    .line 18
    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;-><init>()V

    .line 19
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzci;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v2

    if-eqz v2, :cond_3

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v4

    :goto_3
    if-ge v3, v4, :cond_3

    invoke-interface {v2, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v5

    .line 20
    check-cast v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcd;

    .line 21
    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcd;->zza()Ljava/lang/String;

    move-result-object v6

    .line 22
    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcd;->zzb()F

    move-result v7

    iget v8, v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcd;->zza:I

    .line 23
    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcd;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzw;

    move-result-object v5

    invoke-static {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zzw(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzw;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;

    move-result-object v5

    .line 24
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v9

    .line 25
    invoke-static {v6, v7, v8, v5, v9}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zzf(Ljava/lang/String;FILcom/google/android/gms/internal/mlkit_genai_prompt/zzho;Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;

    move-result-object v5

    .line 26
    invoke-virtual {v1, v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zzd(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    add-int/lit8 v3, v3, 0x1

    goto :goto_3

    .line 27
    :cond_3
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v0

    .line 28
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzci;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

    move-result-object v2

    .line 29
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzci;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzck;

    move-result-object p1

    sget v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->$r8$clinit:I

    if-nez p1, :cond_4

    const/4 p1, 0x0

    goto :goto_4

    .line 35
    :cond_4
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgz;

    move-result-object v3

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzck;->zza()Ljava/lang/String;

    move-result-object p1

    .line 30
    invoke-virtual {v3, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgz;->zza(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgz;

    invoke-virtual {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgz;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;

    move-result-object p1

    .line 31
    :goto_4
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;

    .line 32
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v0

    .line 33
    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    invoke-direct {v3, v0, v2, p1, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhl;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)V

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzid;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;

    .line 34
    invoke-interface {p1, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;->zza(Ljava/lang/Object;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzid;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;

    .line 35
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zzx(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;)V

    return-void
.end method

.method public final zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;)V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzid;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;->zza()I

    move-result v1

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzq;

    move-result-object p1

    invoke-interface {v0, v1, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;->zzc(ILcom/google/android/gms/internal/mlkit_genai_prompt/zzq;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzid;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;

    .line 2
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zzx(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;)V

    return-void
.end method
